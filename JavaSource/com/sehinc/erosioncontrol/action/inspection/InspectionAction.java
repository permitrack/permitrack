package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.value.inspection.InspectionActionValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionReasonValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class InspectionAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        inspectionForm.reset();
        LOG.info("In InspectionAction");
        Integer
            inspectionId;
        if (request.getParameter(RequestKeys.EC_INSPECTION_ID)
            != null)
        {
            inspectionId =
                new Integer(request.getParameter(RequestKeys.EC_INSPECTION_ID));
        }
        else
        {
            InspectionValue
                inspectionValue =
                (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                      request);
            inspectionId =
                inspectionValue.getId();
        }
        EcInspection
            inspection =
            new EcInspection();
        inspection.setId(inspectionId);
        inspection.retrieveByPrimaryKeys();
        inspectionForm.setId(inspection.getId());
        inspectionForm.setInspectionDate(inspection.getInspectionDate());
        inspectionForm.setEnteredDate(inspection.getEnteredDate());
        inspectionForm.setWeatherTrends(inspection.getWeatherTrends());
        inspectionForm.setTemperature(inspection.getTemperature());
        inspectionForm.setComment(inspection.getComment());
        inspectionForm.setPrecipEndDate(inspection.getPrecipEndDate());
        inspectionForm.setPrecipAmount(inspection.getPrecipAmount());
        inspectionForm.setPrecipSource(inspection.getPrecipSource());
        inspectionForm.setInspectionActionComment(inspection.getInspectionActionComment());
        inspectionForm.setInspectionAction(new InspectionActionValue(inspection.getInspectionAction()));
        inspectionForm.setInspectionReason(new InspectionReasonValue(inspection.getInspectionReason()));
        inspectionForm.setStatusCode(inspection.getStatus()
                                         .getCode());
        if (inspection.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_INCOMPLETE))
        {
            inspectionForm.setIsWorkInProgress(true);
        }
        else
        {
            inspectionForm.setIsWorkInProgress(false);
        }
        LOG.debug("leaving inspectionAction");
        return mapping.findForward("continue");
    }
}