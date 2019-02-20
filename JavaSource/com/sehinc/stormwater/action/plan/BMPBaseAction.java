package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.plan.PlanBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BMPBaseAction
    extends PlanBaseAction
{
    protected abstract ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return bmpAction(mapping,
                         form,
                         request,
                         response);
    }

    @Override
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
            BMPValue
                bmpValue =
                getBMP(request);
            if (bmpValue
                != null)
            {
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23b"
                                   + bmpValue.getId());
            }
        }
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
        if (getBMP(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.BMP_MENU_NAME,
                                                     getBMP(request).getId()),
                            request);
        }
    }
}