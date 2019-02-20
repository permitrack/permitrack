package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GISSearchPageAction
    extends GISBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISSearchPageAction.class);

    public ActionForward gisAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        GISSearchForm
            gisSearchForm =
            (GISSearchForm) form;
        gisSearchForm.reset();
        LOG.info("In GISSearchPageAction");
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.GIS_SEARCH_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.GIS_IMPORT_MENU_NAME),
                         request);
    }
}