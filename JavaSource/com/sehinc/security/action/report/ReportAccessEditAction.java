package com.sehinc.security.action.report;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.report.CapReportAccess;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReportAccessEditAction
    extends ReportAccessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportAccessEditAction.class);

    public ActionForward reportAccessAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ReportAccessForm
            raForm =
            (ReportAccessForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("report.access.edit.cancel.action"), request);
            return mapping.findForward("client.view.page");
        }
        else
        {
            List
                accessList =
                CapReportAccess.findByClientId(clientValue.getId());
            for (Object o : accessList)
            {
                CapReportAccess
                    access =
                    (CapReportAccess) o;
                try
                {
                    access.delete();
                }
                catch (Exception e)
                {
                    LOG.error(ApplicationResources.getProperty("report.access.error.reset.failed"));
                    LOG.error(e.getMessage());
                    addError(new ActionMessage("report.access.error.reset.failed"), request);
                    return mapping.findForward(CommonConstants.ACTION_ERROR);
                }
            }
            Integer[]
                selectedReportsFromPage =
                raForm.getSelectedReports();
            if (selectedReportsFromPage
                != null)
            {
                for (Integer reportId : selectedReportsFromPage)
                {
                    try
                    {
                        CapReportAccess
                            access =
                            new CapReportAccess();
                        access.setReportId(reportId);
                        access.setClientId(clientValue.getId());
                        access.setEnabled(true);
                        access.save();
                    }
                    catch (Exception e)
                    {
                        LOG.error(ApplicationResources.getProperty("report.access.error.edit.failed"));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("report.access.error.edit.failed"), request);
                        return mapping.findForward(CommonConstants.ACTION_ERROR);
                    }
                }
            }
        }
        raForm.reset();
        addMessage(new ActionMessage("report.access.edit.success"), request);
        return mapping.findForward("continue");
    }
}
