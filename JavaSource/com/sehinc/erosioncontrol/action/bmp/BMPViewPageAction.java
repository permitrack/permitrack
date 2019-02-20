package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPViewPageAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPViewPageAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception, SQLException, ServletException
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        LOG.info("In BMPViewPageAction");
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.BMP_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
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
        bmpForm.setId(bmp.getId());
        bmpForm.setCategoryName(bmp.getCategory()
                                    .getName());
        bmpForm.setName(bmp.getName());
        bmpForm.setDescription(bmp.getDescription());
        bmpForm.setWeblink(bmp.getWeblink());
        return mapping.findForward("continue");
    }
    /*
        public void finalizeAction(HttpServletRequest request)
            throws Exception
        {
            super.finalizeAction(request);
            setPrimaryMenuItem(request);
            setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.BMP_NONE_MENU_NAME),
                             request);
        }
    */
}

