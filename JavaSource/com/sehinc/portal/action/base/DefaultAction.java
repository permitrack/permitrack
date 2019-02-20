package com.sehinc.portal.action.base;

import com.sehinc.common.action.base.BaseAction;
import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.portal.PortalUtils;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.action.navigation.PortalMenuItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction
    extends BaseAction
{
    @Override
    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return mapping.findForward(PortalUtils.getUserMarkup(request));
    }

    @Override
    protected void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        PortalMenu
            portalMenu =
            (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU,
                                             request);
        portalMenu.setCurrentItem(new PortalMenuItem());
    }

    @Override
    protected ClientValue getClientValue(HttpServletRequest request)
    {
        return null;
    }
}
