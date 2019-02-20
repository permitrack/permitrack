package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBCreateGoalAction
    extends BMPDBBaseAction
{
    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBGoalForm
            bmpDbGoalForm =
            (BMPDBGoalForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPDBValue
            bmpDbValue = getBMPDB(request);
        BMPDBGoalData
            bmpDbGoalData =
            new BMPDBGoalData();
        bmpDbGoalData.setBmpDBId(bmpDbValue.getId());
        bmpDbGoalData.setName(bmpDbGoalForm.getName());
        bmpDbGoalData.setNumber(bmpDbGoalForm.getNumber());
        bmpDbGoalData.setDescription(bmpDbGoalForm.getDescription());
        bmpDbGoalData.setGoalActivityFormId(bmpDbGoalForm.getGoalActivityFormId());
        bmpDbGoalData.setGoalActivityFrequencyId(bmpDbGoalForm.getGoalActivityFrequencyId());
        bmpDbGoalData.insert(userValue);
        setSessionAttribute(SessionKeys.BMP_DB_GOAL,
                            new BMPDBGoalValue(bmpDbGoalData), request);
        return new ActionForward("/goallibraryviewaction.do?"
                                 + RequestKeys.BMP_DB_GOAL_ID
                                 + "="
                                 + bmpDbGoalData.getId(),
                                 true);

//        return mapping.findForward("continue");
    }
}