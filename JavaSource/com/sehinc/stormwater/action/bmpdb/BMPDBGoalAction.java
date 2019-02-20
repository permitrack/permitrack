package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBGoalAction
    extends BMPDBGoalBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBGoalAction.class);
*/

    public ActionForward bmpDbGoalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBGoalForm
            bmpDbGoalForm =
            (BMPDBGoalForm) form;
        bmpDbGoalForm.reset();

        Integer
            bmpDbGoalId = 0;
        if (request.getParameter(RequestKeys.BMP_DB_GOAL_ID)
            != null)
        {
            bmpDbGoalId =
                new Integer(request.getParameter(RequestKeys.BMP_DB_GOAL_ID));
        }
        else
        {
            BMPDBGoalValue
                bmpDbGoalValue = getBMPDBGoal(request);
            if(bmpDbGoalValue != null)
            {
                bmpDbGoalId =
                    bmpDbGoalValue.getId();
            }
        }

        if(bmpDbGoalId > 0)
        {
            BMPDBGoalData
                bmpDbGoalData;
            bmpDbGoalData =
                new BMPDBGoalData();
            bmpDbGoalData.setId(bmpDbGoalId);
            if (bmpDbGoalData.retrieveByPrimaryKeysAlternate())
            {
                setSessionAttribute(SessionKeys.BMP_DB_GOAL,
                                    new BMPDBGoalValue(bmpDbGoalData), request);
                bmpDbGoalForm.setId(bmpDbGoalData.getId());
                bmpDbGoalForm.setBMPDBId(bmpDbGoalData.getBmpDBId());
                bmpDbGoalForm.setName(bmpDbGoalData.getName());
                bmpDbGoalForm.setNumber(bmpDbGoalData.getNumber());
                bmpDbGoalForm.setDescription(bmpDbGoalData.getDescription());
                bmpDbGoalForm.setGoalActivityFormId(bmpDbGoalData.getGoalActivityFormId());
                bmpDbGoalForm.setGoalActivityFrequencyId(bmpDbGoalData.getGoalActivityFrequencyId());
                return mapping.findForward("continue");
            }
        }
        addError(new ActionMessage("bmpdb.error.loading.bmp"), request);
        return mapping.findForward("bmpdb.list.action");
    }
}