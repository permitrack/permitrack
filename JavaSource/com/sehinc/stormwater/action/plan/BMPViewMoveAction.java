package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPViewMoveAction
    extends BMPBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(BMPViewMoveAction.class);
*/

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
//        LOG.info("In BMPViewMoveAction");
        PlanValue
            planValue =
            getPlanValue(request);
        BMPValue
            bmpValue =
            getBMP(request);
        setRequestAttribute(SessionKeys.BMP_MOVE_MCM_LIST,
                            PlanService.getMCMValuesList(planValue,
                                                         bmpValue.getMcmId()),
                            request);
        return mapping.findForward("continue");
    }
}