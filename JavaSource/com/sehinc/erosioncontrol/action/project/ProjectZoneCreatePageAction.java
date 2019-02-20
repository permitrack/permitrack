package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectZoneCreatePageAction
    extends ProjectZoneBaseAction
{
    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return (mapping.findForward("continue"));
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.ZONE_CREATE_MENU_ITEM_NAME);
    }
}