package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.template.PlanTemplateBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GoalTemplateBaseAction
    extends PlanTemplateBaseAction
{
    public abstract ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return goalAction(mapping,
                          form,
                          request,
                          response);
    }

    protected void setTreeMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
/*
        ITreeMenu
            treeMenu =
            (ITreeMenu) getSessionAttribute(SessionKeys.PLAN_TEMPLATE_HIERARCHY);
*/
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            != null)
        {
            TreeMenuFactory
                treeMenuFactory =
                TreeMenuFactory.getInstance();
/*
            if (treeMenu
                == null)
            {
*/
                PlanTemplateBranch
                    treeMenuLeaf =
                    new PlanTemplateBranch(planValue);
            ITreeMenu
                treeMenu =
                    treeMenuFactory.getTreeMenu(treeMenuLeaf);
//            }
            setSessionAttribute(SessionKeys.PLAN_TEMPLATE_HIERARCHY,
                                treeMenu, request);
            GoalValue
                goalValue =
                getGoalTemplate(request);
            if (goalValue
                != null)
            {
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23goal_temp"
                                   + goalValue.getId());
            }
        }
    }

    protected GoalValue getGoalTemplate(HttpServletRequest request)
    {
        return (GoalValue) getSessionAttribute(SessionKeys.GOAL_TEMPLATE, request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        if (getGoalTemplate(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.TEMPLATE_GOAL_MENU_NAME,
                                                     getGoalTemplate(request).getId()),
                            request);
        }
    }
}