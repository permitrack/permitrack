package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.plan.PlanBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GoalActivityBaseAction
    extends PlanBaseAction
{
    protected abstract ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return goalActivityAction(mapping,
                                  form,
                                  request,
                                  response);
    }

    protected void setTreeMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            != null)
        {
            TreeMenuFactory
                treeMenuFactory =
                TreeMenuFactory.getInstance();
            PlanBranch
                treeMenuLeaf =
                new PlanBranch(planValue);
            ITreeMenu
                treeMenu =
                treeMenuFactory.getTreeMenu(treeMenuLeaf);
            request.getSession()
                .setAttribute(SessionKeys.PLAN_HIERARCHY,
                              treeMenu);
            GoalActivityValue
                goalActivityValue =
                getGoalActivity(request);
            if (goalActivityValue
                != null)
            {
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23a"
                                   + goalActivityValue.getId());
            }
        }
    }

    protected GoalActivityValue getGoalActivity(HttpServletRequest request)
    {
        return (GoalActivityValue) getSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                                                       request);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.PLAN_VIEW_MENU_ITEM_NAME);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        super.setTertiaryMenu(request);
        if (getGoalActivity(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.GOAL_ACTIVITY_MENU_NAME,
                                                     getGoalActivity(request).getId()),
                            request);
        }
    }
}