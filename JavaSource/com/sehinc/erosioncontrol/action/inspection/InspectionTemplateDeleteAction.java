package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplate;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class InspectionTemplateDeleteAction
    extends InspectionTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateDeleteAction.class);

    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In InspectionTemplateDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
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
            addError(new ActionMessage("inspection.template.delete.failed"),
                     request);
            return mapping.findForward("inspection.template.list.page");
        }
        EcInspectionTemplate
            inspectionTemplate =
            new EcInspectionTemplate();
        inspectionTemplate.setId(inspectionTemplateId);
        inspectionTemplate.load();
        inspectionTemplate.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        inspectionTemplate.update(userValue);
        return (mapping.findForward("continue"));
    }
}