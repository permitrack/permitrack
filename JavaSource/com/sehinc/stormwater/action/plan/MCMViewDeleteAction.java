package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMViewDeleteAction
    extends MCMBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMViewDeleteAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            mcmDeleteForm =
            (PlanDeleteForm) form;
        mcmDeleteForm.reset();
        LOG.info("In MCMViewDeleteAction");
        MCMValue
            mcmValue =
            getMCMValue(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        mcmData.retrieveByPrimaryKeys();
        return mapping.findForward("continue");
    }
}