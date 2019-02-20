package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class InspectionTemplateListPageAction
    extends InspectionTemplateBaseAction
{
    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        ClientValue
            clientValue =
            getClientValue(request);
        List
            inspectionTemplateList =
            InspectionService.getInspectionTemplateValueList(clientValue);
        setSessionAttribute(SessionKeys.EC_INSPECTION_TEMPLATE_LIST,
                            inspectionTemplateList,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            null,
                            request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        setSessionAttribute(SessionKeys.BMP_TEMPLATE_CAN_UPDATE,
                            new Boolean(securityManager.HasECPermission(SecureObjectPermissionData.BMP_UPDATE)),
                            request);
        setSessionAttribute(SessionKeys.BMP_TEMPLATE_CAN_DELETE,
                            new Boolean(securityManager.HasECPermission(SecureObjectPermissionData.BMP_DELETE)),
                            request);
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME),
                         request);
    }
}