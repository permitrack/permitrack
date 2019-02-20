package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBGoalEditAction
    extends BMPDBGoalBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBGoalEditAction.class);

    public ActionForward bmpDbGoalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBGoalForm
            bmpDbGoalForm =
            (BMPDBGoalForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPDBGoalValue
            bmpDbGoalValue = getBMPDBGoal(request);
        if (bmpDbGoalValue
            == null)
        {
            addError(new ActionMessage("bmpdb.goal.edit.error.goalNotFoundInSession"), request);
            return mapping.findForward("bmpdb.list.action");
        }
        BMPDBGoalData
            bmpDbGoalData =
            new BMPDBGoalData();
        bmpDbGoalData.setId(bmpDbGoalValue.getId());
        if (!bmpDbGoalData.retrieveByPrimaryKeysAlternate())
        {
            addError(new ActionMessage("bmpdb.error.loading.bmp"), request);
            return mapping.findForward("bmpdb.list.action");
        }
        bmpDbGoalData.setName(bmpDbGoalForm.getName());
        bmpDbGoalData.setNumber(bmpDbGoalForm.getNumber());
        bmpDbGoalData.setDescription(bmpDbGoalForm.getDescription());
        bmpDbGoalData.setGoalActivityFormId(bmpDbGoalForm.getGoalActivityFormId());
        bmpDbGoalData.setGoalActivityFrequencyId(bmpDbGoalForm.getGoalActivityFrequencyId());
        bmpDbGoalData.save(userValue);
        bmpDbGoalValue.setName(bmpDbGoalForm.getName());
        return mapping.findForward("continue");
    }
}