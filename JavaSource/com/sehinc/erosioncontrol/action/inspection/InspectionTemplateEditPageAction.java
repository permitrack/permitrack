package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplate;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class InspectionTemplateEditPageAction
    extends InspectionTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateEditPageAction.class);

    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionTemplateForm
            inspectionTemplateForm =
            (InspectionTemplateForm) form;
        LOG.info("In InspectionTemplateEditPageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        Integer
            inspectionTemplateId;
        if (request.getParameter(RequestKeys.EC_INSPECTION_TEMPLATE_ID)
            != null)
        {
            inspectionTemplateId =
                new Integer(request.getParameter(RequestKeys.EC_INSPECTION_TEMPLATE_ID));
        }
        else
        {
            return mapping.findForward("inspection.template.list.page");
        }
        EcInspectionTemplate
            inspectionTemplate =
            new EcInspectionTemplate();
        inspectionTemplate.setId(inspectionTemplateId);
        inspectionTemplate.load();
        inspectionTemplateForm.setId(inspectionTemplate.getId());
        inspectionTemplateForm.setClientId(inspectionTemplate.getClientId());
        inspectionTemplateForm.setName(inspectionTemplate.getName());
        inspectionTemplateForm.setDescription(inspectionTemplate.getDescription());
        inspectionTemplateForm.setStatusCode(inspectionTemplate.getStatus()
                                                 .getCode());
        List
            inspectionTemplates =
            InspectionService.getInspectionTemplateBmpValuesByInspectionTemplateId(inspectionTemplateId,
                                                                                   inspectionTemplate.getClientId());
        List
            bmpList =
            EcBmp.findByClientId(clientValue.getId());
        List
            projTypes =
            ProjectService.getProjectTypeLabelValueList(clientValue);
        setSessionAttribute(SessionKeys.EC_INSPECTION_TEMPLATE_BMP_LIST,
                            inspectionTemplates,
                            request);
        setSessionAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT,
                            bmpList,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                            projTypes,
                            request);
        return (mapping.findForward("continue"));
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.INSPECTION_TEMPLATE_EDIT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.INSPECTION_TEMPLATE_EDIT_MENU_NAME),
                         request);
    }
}