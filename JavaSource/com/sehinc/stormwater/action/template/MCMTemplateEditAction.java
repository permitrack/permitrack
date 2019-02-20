package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMTemplateEditAction
    extends MCMTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMTemplateEditAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        MCMTemplateForm
            mcmTemplateForm =
            (MCMTemplateForm) form;
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue =
            getMCMTemplate(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        mcmData.retrieveByPrimaryKeys();
        mcmData.setName(mcmTemplateForm.getName());
        mcmData.setNumber(mcmTemplateForm.getNumber());
        mcmData.setRequiredDescription(mcmTemplateForm.getRequiredDescription());
        mcmData.setNecessaryDescription(mcmTemplateForm.getNecessaryDescription());
        mcmData.save(userValue);
        mcmValue.setName(mcmTemplateForm.getName());
        return mapping.findForward("continue");
    }
}