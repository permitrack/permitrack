package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.template.PlanTemplateBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PlanTemplateBaseAction
    extends TemplateBaseAction
{
    protected abstract ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            == null)
        {
            ActionForward
                errorHandler =
                handleError(mapping,
                            "error.no.plan.selected", request);
            if (errorHandler
                != null)
            {
                return errorHandler;
            }
        }
        return templateAction(mapping,
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
        }
    }

    public void clearPlanTemplateSessionKeys(HttpServletRequest request)
    {
        removeSessionAttribute(SessionKeys.PLAN_TEMPLATE, request);
        removeSessionAttribute(SessionKeys.MCM_TEMPLATE, request);
        removeSessionAttribute(SessionKeys.BMP_TEMPLATE, request);
        removeSessionAttribute(SessionKeys.GOAL_TEMPLATE, request);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        PlanValue
            planValue =
            getPlanValue(request);
        String
            selectedMenuItem =
            (String) request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM);
        SecondaryMenu
            secondaryMenu =
            planValue
            == null
                ? SecondaryMenu.getInstance(SecondaryMenu.DEFAULT_TEMPLATE_MENU_NAME,
                                            0)
                : SecondaryMenu.getInstance(SecondaryMenu.TEMPLATE_MENU_NAME,
                                            planValue.getId());
        setSelectedMenuItem(secondaryMenu,
                            selectedMenuItem);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu, request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        if (getPlanValue(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.TEMPLATE_MENU_NAME,
                                                     getPlanValue(request).getId()),
                            request);
        }
    }
}