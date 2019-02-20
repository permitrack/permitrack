package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPCopyAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPCopyAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String
            successMessage =
            null;
        BMPCopyForm
            bmpCopyForm =
            (BMPCopyForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue =
            getBMP(request);
        if (bmpValue
            == null)
        {
            LOG.error("ERROR: BMP not found in session");
            addError(new ActionMessage("bmp.error.not.found.in.session"), request);
            return mapping.findForward("continue");
        }
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELLED");
            bmpCopyForm.reset();
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "b"
                                     + bmpValue.getId(),
                                     true);
        }
        String
            operation =
            bmpCopyForm.getOperation();
        if (operation
            != null
            && operation.equalsIgnoreCase("copy"))
        {
            LOG.info("BMP Copy requested.");
            try
            {
                Integer
                    newBmpId =
                    PlanService.copyBMP(bmpValue.getId(),
                                        bmpCopyForm.getMcmId(),
                                        userValue);
                successMessage =
                    "bmp.copy.success";
                return new ActionForward("/subnodeviewaction.do?"
                                         + UrlUtil.subNodeString
                                         + "="
                                         + "b"
                                         + newBmpId,
                                         true);
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                addError(new ActionMessage("bmp.error.copy.failed"), request);
            }
        }
        else if (operation
                 != null
                 && operation.equalsIgnoreCase("move"))
        {
            LOG.info("BMP Move requested.");
            try
            {
                PlanService.moveBMP(bmpValue.getId(),
                                    bmpCopyForm.getMcmId(),
                                    userValue);
                successMessage =
                    "bmp.move.success";
                return new ActionForward("/subnodeviewaction.do?"
                                         + UrlUtil.subNodeString
                                         + "="
                                         + "b"
                                         + bmpValue.getId(),
                                         true);
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                addError(new ActionMessage("bmp.error.move.failed"), request);
            }
        }
        if (successMessage
            != null)
        {
            addMessage(new ActionMessage(successMessage), request);
        }
        return mapping.findForward("continue");
    }
}