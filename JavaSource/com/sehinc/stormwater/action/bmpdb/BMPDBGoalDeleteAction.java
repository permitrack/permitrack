package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBGoalDeleteAction
    extends BMPDBGoalBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBGoalDeleteAction.class);

    public ActionForward bmpDbGoalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
/*
        BMPDBGoalForm
            bmpDbGoalForm =
            (BMPDBGoalForm) form;
        LOG.info("In BMPDBGoalDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
*/
        BMPDBGoalValue
            bmpDbGoalValue = getBMPDBGoal(request);
        BMPDBGoalData
            bmpDbGoalData =
            new BMPDBGoalData();
        bmpDbGoalData.setId(bmpDbGoalValue.getId());
        if (!bmpDbGoalData.load())
        {
            addError(new ActionMessage("bmpdb.error.loading.bmp"), request);
            return mapping.findForward("bmpdb.list.action");
        }
/*
        Integer
            bmpDbId =
            bmpDbGoalData.getBmpDBId();
*/
        try
        {
            bmpDbGoalData.delete();
        }
        catch (HibernateException he)
        {
            LOG.error("In bmpdbGoalAction(): Failed to delete BMPDBGoalData.id = "
                      + bmpDbGoalData.getId());
        }
        removeSessionAttribute(SessionKeys.BMP_DB_GOAL,
                            request);
/*
        Iterator
            goalList =
            BMPDBGoalData.findByBmpDBId(bmpDbId)
                .iterator();
        BMPDBGoalData
            viewBmpDbGoalData =
            null;
        while (goalList.hasNext())
        {
            viewBmpDbGoalData =
                (BMPDBGoalData) goalList.next();
            setSessionAttribute(SessionKeys.BMP_DB_GOAL,
                                new BMPDBGoalValue(viewBmpDbGoalData));
            break;
        }
        if (viewBmpDbGoalData
            == null)
        {
            return mapping.findForward("bmpdb.view.action");
        }
*/
        return mapping.findForward("continue");
    }
}