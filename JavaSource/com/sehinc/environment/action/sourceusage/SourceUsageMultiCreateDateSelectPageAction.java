package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceUsageMultiCreateDateSelectPageAction
    extends SourceUsageBaseAction
{
    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE,
                           request))
        {
            return mapping.findForward("source.usage.list.page");
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_CREATE_SET_MENU_ITEM);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }
}