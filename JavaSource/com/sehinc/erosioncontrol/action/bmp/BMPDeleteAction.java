package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmp.BmpService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BMPDeleteAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDeleteAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.BMP_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("bmp.permission.denied",
                                                      parameters));
            addError(new ActionMessage("bmp.permission.denied",
                                       parameters),
                     request);
            return mapping.findForward("BMP.list.page");
        }
        Integer
            bmpId;
        LOG.debug("bmpId="
                  + request.getParameter(RequestKeys.EC_BMP_ID));
        if (request.getParameter(RequestKeys.EC_BMP_ID)
            != null)
        {
            LOG.debug("bmpId="
                      + request.getParameter(RequestKeys.EC_BMP_ID));
            bmpId =
                new Integer(request.getParameter(RequestKeys.EC_BMP_ID));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("bmp.error.no.bmp.on.request"));
            addError(new ActionMessage("bmp.error.no.bmp.on.request"),
                     request);
            return mapping.findForward("BMP.list.page");
        }
        EcBmp
            bmp =
            new EcBmp(bmpId);
        try
        {
            bmp.load();
            if (!bmp.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The requested BMP ID = "
                                    + bmpId
                                    + " does not exist.");
            }
            bmp.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
            bmp.save(userValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {bmpId};
            LOG.error(ApplicationResources.getProperty("bmp.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("bmp.error.load.failed",
                                       parameters),
                     request);
            return mapping.findForward("BMP.list.page");
        }
        List
            bmpList =
            BmpService.getBMPList(bmp.getCategory()
                                      .getId(),
                                  clientValue);
        if (bmpList.isEmpty())
        {
            try
            {
                bmp.load();
                EcBmpCategory
                    bmpCategory =
                    bmp.getCategory();
                bmpCategory.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                bmpCategory.save(userValue);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("bmp.error.delete.category.failed"));
                LOG.error("bmpId="
                          + bmp.getId()
                          + "\n"
                          + e.getMessage());
                addError(new ActionMessage("bmp.error.delete.category.failed"),
                         request);
                return mapping.findForward("BMP.list.page");
            }
        }
        addMessage(new ActionMessage("bmp.delete.success"),
                   request);
        return mapping.findForward("continue");
    }
}

