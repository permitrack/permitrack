package com.sehinc.portal.action.auth;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.portal.PortalUtils;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.action.navigation.PortalMenuItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction
    extends BaseActionUnsecure
{
    private
    String
        markup =
        null;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        request.getSession()
            .invalidate();
        return mapping.findForward(markup);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
        try
        {
            markup =
                PortalUtils.getUserMarkup(request);
            PortalMenu
                portalMenu =
                (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU,
                                                 request);
            if (portalMenu
                != null)
            {
                PortalMenuItem
                    item =
                    portalMenu.getCurrentItem();
                if (item.getCode()
                    != null
                    && item.getCode()
                           .length()
                       > 0)
                {
                    markup =
                        item.getCode();
                }
            }
        }
        catch (Exception e)
        {
        }
    }
}