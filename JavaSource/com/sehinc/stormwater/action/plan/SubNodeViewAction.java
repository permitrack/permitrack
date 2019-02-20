package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.plan.BranchConstants;
import com.sehinc.stormwater.value.plan.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubNodeViewAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubNodeViewAction.class);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        String
            nodeId =
            request.getParameter(RequestKeys.NODE_ID);
        Integer
            id;
        if (nodeId
            == null)
        {
            LOG.error("null node id");
            return mapping.findForward("stormwater");
        }
        else
        {
            id =
                new Integer(nodeId.substring(1));
        }
        String
            forward =
            "continue";
        if (nodeId.startsWith(BranchConstants.BMP_TYPE))
        {
            BMPValue
                value =
                new BMPValue();
            value.setId(id);
            setSessionAttribute(SessionKeys.BMP,
                                value, request);
            forward =
                "bmp";
        }
        else if (nodeId.startsWith(BranchConstants.GOAL_ACTIVITY_TYPE))
        {
            GoalActivityValue
                value =
                new GoalActivityValue();
            value.setId(id);
            setSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                                value, request);
            forward =
                "goalactivity";
        }
        else if (nodeId.startsWith(BranchConstants.MCM_TYPE))
        {
            MCMValue
                value =
                new MCMValue();
            value.setId(id);
            setSessionAttribute(SessionKeys.MCM,
                                value, request);
            forward =
                "mcm";
        }
        else if (nodeId.startsWith(BranchConstants.GOAL_TYPE))
        {
            GoalValue
                value =
                new GoalValue();
            value.setId(id);
            setSessionAttribute(SessionKeys.GOAL,
                                value, request);
            forward =
                "goal";
        }
        else if (nodeId.startsWith(BranchConstants.PLAN_TYPE))
        {
            PlanValue
                planValue =
                getPlanValue(request);
            if (planValue
                != null)
            {
                if (planValue.getId()
                    != id)
                {
                    removeSessionAttribute(SessionKeys.PLAN, request);
                }
            }
            request.setAttribute("id",
                                 id.toString());
        }
        response.setHeader("Set-Cookie",
                           "jstree_select=%23"
                           + nodeId);
        return mapping.findForward(forward);
    }

    @Override
    public void finalizeAction(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        //No-op: this action always redirects
    }
}