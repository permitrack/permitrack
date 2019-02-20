package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class InspectionTemplateCreatePageAction
    extends InspectionTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateCreatePageAction.class);

    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In InspectionTemplateCreatePageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        setSessionAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT,
                            EcBmp.findByClientId(clientValue.getId()),
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                            ProjectService.getProjectTypeLabelValueList(clientValue),
                            request);
        return (mapping.findForward("continue"));
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.INSPECTION_TEMPLATE_CREATE_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.INSPECTION_TEMPLATE_CREATE_MENU_NAME),
                         request);
    }
}