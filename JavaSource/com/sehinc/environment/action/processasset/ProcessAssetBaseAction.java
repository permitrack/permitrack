package com.sehinc.environment.action.processasset;

import com.sehinc.environment.action.base.ClientBaseAction;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ProcessAssetBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessAssetBaseAction.class);

    public abstract ActionForward processAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In ProcessAssetBaseAction");
        return processAssetAction(mapping,
                                  form,
                                  request,
                                  response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_FACILITY_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.FACILITY_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROCESS_LIST_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PROCESS_ASSET_MENU_ITEM);
    }
}
