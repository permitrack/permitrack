package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.action.base.BaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class InspectionTemplateBaseAction
    extends BaseAction
{
    /*
        private static
        Logger
            LOG =
            Logger.getLogger(InspectionTemplateBaseAction.class);
    */

    public abstract ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        //        LOG.info("In InspectionTemplateBaseAction");
        return inspectionTemplateAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }
}