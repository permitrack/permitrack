package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.template.PlanTemplateBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.value.plan.MCMValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MCMTemplateBaseAction
    extends PlanTemplateBaseAction
{
    public abstract ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return mcmAction(mapping,
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
            MCMValue
                mcmValue =
                getMCMTemplate(request);
            if (mcmValue
                != null)
            {
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23mcm_temp"
                                   + mcmValue.getId());
            }
        }
    }

    protected MCMValue getMCMTemplate(HttpServletRequest request)
    {
        return (MCMValue) getSessionAttribute(SessionKeys.MCM_TEMPLATE, request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        if (getMCMTemplate(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(getPermitType(request).equals(PlanPermitType.OTHER)
                                                         ? TertiaryMenu.TEMPLATE_MCM_OTHER_MENU_NAME
                                                         : TertiaryMenu.TEMPLATE_MCM_MPCA_MENU_NAME,
                                                     getMCMTemplate(request).getId()),
                            request);
        }
    }
}