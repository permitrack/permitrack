package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.template.PlanTemplateBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BMPTemplateBaseAction
    extends PlanTemplateBaseAction
{
    protected abstract ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException;

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return bmpAction(mapping,
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
            BMPValue
                bmpValue =
                getBMPTemplate(request);
            if (bmpValue
                != null)
            {
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23bmp_temp"
                                   + bmpValue.getId());
            }
        }
    }

    protected BMPValue getBMPTemplate(HttpServletRequest request)
    {
        return (BMPValue) getSessionAttribute(SessionKeys.BMP_TEMPLATE, request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        if (getBMPTemplate(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.TEMPLATE_BMP_MENU_NAME,
                                                     getBMPTemplate(request).getId()),
                            request);
        }
    }
}