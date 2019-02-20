package com.sehinc.stormwater.action.permitperiod;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.permitperiod.PermitPeriodBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PermitPeriodBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitPeriodBaseAction.class);

    public abstract ActionForward permitPeriodAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return permitPeriodAction(mapping,
                                  form,
                                  request,
                                  response);
    }

    private void setSelectedMenuItem(SecondaryMenu secondaryMenu, String selectedMenuItem)
    {
        if (selectedMenuItem
            == null
            || selectedMenuItem.equals("view"))
        {
            secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_PERIOD_VIEW_MENU_ITEM_NAME);
        }
        else if (selectedMenuItem.equals("create"))
        {
            secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_PERIOD_NEW_MENU_ITEM_NAME);
        }
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME,
                                    0);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_PERMIT_PERIOD_MENU_ITEM_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        String
            selectedMenuItem =
            (String) request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM);
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.PERMIT_PERIOD_MENU_NAME,
                                      0);
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
        TreeMenuFactory
            treeMenuFactory =
            TreeMenuFactory.getInstance();
        LOG.debug("Building permit period hierarchy");
        PermitPeriodBranch
            treeMenuLeaf =
            new PermitPeriodBranch();
        ITreeMenu
            treeMenu =
            treeMenuFactory.getTreeMenu(treeMenuLeaf);
        request.getSession()
            .setAttribute(SessionKeys.PERMIT_PERIOD_HIERARCHY,
                          treeMenu);
    }

    protected PermitPeriodValue getPermitPeriod(HttpServletRequest request)
    {
        return (PermitPeriodValue) getSessionAttribute(SessionKeys.PERMIT_PERIOD,
                                                       request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        super.setTertiaryMenu(request);
        if (getPermitPeriod(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_PERIOD_MENU_NAME,
                                                     getPermitPeriod(request).getId()),
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

    protected Integer getPermitPeriodId(HttpServletRequest request)
    {
        Integer
            permitPeriodId =
            null;
        if (request.getParameter(RequestKeys.PERMIT_PERIOD_ID)
            != null)
        {
            permitPeriodId =
                new Integer(request.getParameter(RequestKeys.PERMIT_PERIOD_ID));
        }
        else
        {
            PermitPeriodValue
                permitPeriodValue =
                getPermitPeriod(request);
            if (permitPeriodValue
                != null)
            {
                permitPeriodId =
                    permitPeriodValue.getId();
            }
        }
        return permitPeriodId;
    }
}