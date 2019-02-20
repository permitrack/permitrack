package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.plan.PlanBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class PlanBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanBaseAction.class);
    private
    Integer
        alreadyChecked =
        0;

    public abstract ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        /*
                ClientValue
                    clientValue =
                    getClientValue(request);
                if (clientValue
                    == null)
                {
                    return mapping.findForward("stormwater");
                }
        */
        return planAction(mapping,
                          form,
                          request,
                          response);
    }

    protected PlanValue getPlanValue(HttpServletRequest request)
    {
        int
            id;
        PlanValue
            plan =
            (PlanValue) getSessionAttribute(SessionKeys.PLAN,
                                            request);
        if (plan
            == null)
        {
            id =
                getParamInt("id",
                            request);
        }
        else
        {
            id =
                plan.getId();
            if (alreadyChecked.equals(id))
            {
                return plan;
            }
        }
        if (id
            < 1)
        {
            //            addError(new ActionMessage("error.plan.not.found.in.session"), request);
            return null;
        }
        if (setPlanSessionKeys(SessionKeys.PLAN,
                               id,
                               request))
        {
            alreadyChecked =
                id;
            return (PlanValue) getSessionAttribute(SessionKeys.PLAN,
                                                   request);
        }
        return null;
    }

    private void setSelectedMenuItem(SecondaryMenu secondaryMenu, String selectedMenuItem)
    {
        try
        {
            if (selectedMenuItem
                == null
                || selectedMenuItem.equals("view"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.PLAN_VIEW_MENU_ITEM_NAME);
            }
            else if (selectedMenuItem.equals("list"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.PLAN_SELECT_MENU_ITEM_NAME);
            }
            else if (selectedMenuItem.equals("create"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.PLAN_NEW_MENU_ITEM_NAME);
            }
            else if (selectedMenuItem.equals("publish"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.PLAN_PUB_MENU_ITEM_NAME);
            }
        }
        catch (Exception e)
        {
            LOG.debug("Here is what is going on: "
                      + e.getMessage());
        }
    }

    @Override
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
                ? SecondaryMenu.getInstance(SecondaryMenu.DEFAULT_PLAN_MENU_NAME,
                                            getClientValue(request)
                                            != null
                                                ? getClientValue(request).getId()
                                                : 0)
                : SecondaryMenu.getInstance(SecondaryMenu.PLAN_MENU_NAME,
                                            planValue.getId());
        setSelectedMenuItem(secondaryMenu,
                            selectedMenuItem);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
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
            setSessionAttribute(SessionKeys.PLAN_HIERARCHY,
                                treeMenu,
                                request);
        }
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        super.setTertiaryMenu(request);
        if (getPlanValue(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PLAN_MENU_NAME,
                                                     getPlanValue(request).getId()),
                            request);
        }
    }

    @Override
    public void finalizeAction(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.finalizeAction(request,
                             response);
        setTreeMenu(request,
                    response);
    }
}