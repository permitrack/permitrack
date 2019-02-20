package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.erosioncontrol.action.base.BaseAction;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BMPDBBaseAction
    extends BaseAction
{
    public abstract ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return bmpAction(mapping,
                         form,
                         request,
                         response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_BMP_LIBRARY_MENU_ITEM_NAME);
    }

    @Override
    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        PrimaryMenu
            menu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           menu,
                                           request);
        return (PrimaryMenu) SessionService.getSessionAttribute(SessionKeys.PRIMARY_MENU,
                                                                request);
    }
}