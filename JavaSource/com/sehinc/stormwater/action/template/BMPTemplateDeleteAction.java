package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPTemplateDeleteAction
    extends BMPTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPTemplateDeleteAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        LOG.info("In BMPTemplateAction");
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue = getBMPTemplate(request);
        if (bmpValue
            == null)
        {
            addError(new ActionMessage("bmp.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        BMPData
            bmpData =
            PlanService.getBMP(bmpValue.getId());
        if (bmpData
            == null)
        {
            addError(new ActionMessage("bmp.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        Integer
            mcmId =
            bmpData.getMcmId();
        bmpData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        bmpData.save(userValue);
        removeSessionAttribute(SessionKeys.BMP_TEMPLATE,
                            request);
        MCMData
            mcmData =
            PlanService.getMCM(mcmId);
        if (mcmData
            != null)
        {
            setPlanTemplateSessionKeys(SessionKeys.MCM_TEMPLATE,
                                       mcmData.getId(), request);
            return new ActionForward("/mcmtemplateviewaction.do?"
                                     + "id"
                                     + "="
                                     + mcmData.getId(),
                                     true);
        }
        return mapping.findForward("continue");
    }
}