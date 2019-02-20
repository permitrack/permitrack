package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmp.BmpService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPEditAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPEditAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            bmpForm.reset();
            return mapping.findForward("BMP.list.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        if (bmpForm.getId()
            == null
            || bmpForm.getId()
                   .intValue()
               == 0)
        {
            Object[]
                parameters =
                {bmpForm.getId()};
            addError(new ActionMessage("bmp.error.invalid",
                                       parameters),
                     request);
            LOG.info(ApplicationResources.getProperty("bmp.error.invalid",
                                                      parameters));
            return mapping.findForward("BMP.list.page");
        }
        EcBmp
            bmp =
            new EcBmp(bmpForm.getId());
        try
        {
            bmp.load();
            if (!bmp.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The BMP has been deleted.");
            }
            EcBmp
                dupBmp =
                BmpService.getDuplicateBmpInCategory(bmp.getId(),
                                                     bmpForm.getName(),
                                                     bmp.getCategory()
                                                         .getId(),
                                                     clientValue);
            if (dupBmp
                != null)
            {
                addError(new ActionMessage("bmp.already.exists.in.category"),
                         request);
                LOG.info(ApplicationResources.getProperty("bmp.already.exists.in.category"));
                return mapping.getInputForward();
            }
            bmp.setName(bmpForm.getName());
            bmp.setDescription(bmpForm.getDescription());
            bmp.setWeblink(bmpForm.getWeblink());
            bmp.save(userValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("bmp.edit.failed",
                                       parameters),
                     request);
            LOG.error(ApplicationResources.getProperty("bmp.edit.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            return mapping.findForward("BMP.list.page");
        }
        addMessage(new ActionMessage("bmp.edit.success"),
                   request);
        return mapping.findForward("continue");
    }
}