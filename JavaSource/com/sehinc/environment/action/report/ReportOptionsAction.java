package com.sehinc.environment.action.report;

import com.sehinc.common.db.report.CapReportAccess;
import com.sehinc.common.db.report.CapReportObject;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.permit.EnvPermit;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReportOptionsAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportOptionsAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
//        LOG.info("In ReportOptionsAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        reportForm.reset();
        Date
            now =
            new Date();
        Integer
            thisYear =
            new Integer(DateUtil.year(now));
        reportForm.setYear(thisYear);
        reportForm.setSemiAnnualYear(thisYear);
        ClientValue
            clientValue =
            getClientValue(request);
        if (clientValue
            == null)
        {
            return mapping.findForward("environment");
        }
        List
            permitList =
            EnvPermit.findByClientId(clientValue.getId());
        setSessionAttribute(SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT,
                            permitList,
                            request);
        Boolean
            rollingReport =
            false;
        Boolean
            orgStructReport =
            false;
        Boolean
            semiAnnualReport =
            false;
        Boolean
            substanceReport =
            false;
        Boolean
            airEmInvReport =
            false;
        CapReportAccess
            rollAccess =
            CapReportAccess.findByReportAndClient(CapReportObject.EV_ROLLING_REPORT,
                                                  clientValue.getId());
        CapReportAccess
            orgAccess =
            CapReportAccess.findByReportAndClient(CapReportObject.EV_ORG_STRUCTURE_REPORT,
                                                  clientValue.getId());
        CapReportAccess
            semiAccess =
            CapReportAccess.findByReportAndClient(CapReportObject.EV_SEMI_ANNUAL_REPORT,
                                                  clientValue.getId());
        CapReportAccess
            substAccess =
            CapReportAccess.findByReportAndClient(CapReportObject.EV_SUBSTANCE_REPORT,
                                                  clientValue.getId());
        CapReportAccess
            aeiAccess =
            CapReportAccess.findByReportAndClient(CapReportObject.EV_AIR_EM_INV_REPORT,
                                                  clientValue.getId());
        if (rollAccess
            != null)
        {
            rollingReport =
                rollAccess.getEnabled();
        }
        if (orgAccess
            != null)
        {
            orgStructReport =
                orgAccess.getEnabled();
        }
        if (semiAccess
            != null)
        {
            semiAnnualReport =
                semiAccess.getEnabled();
        }
        if (substAccess
            != null)
        {
            substanceReport =
                substAccess.getEnabled();
        }
        if (aeiAccess
            != null)
        {
            airEmInvReport =
                aeiAccess.getEnabled();
        }
        setSessionAttribute(SessionKeys.ROLLING_REPORT_ACCESS,
                            rollingReport,
                            request);
        setSessionAttribute(SessionKeys.ORG_STRUCTURE_ACCESS,
                            orgStructReport,
                            request);
        setSessionAttribute(SessionKeys.SEMI_ANNUAL_ACCESS,
                            semiAnnualReport,
                            request);
        setSessionAttribute(SessionKeys.SUBSTANCE_ACCESS,
                            substanceReport,
                            request);
        setSessionAttribute(SessionKeys.AIR_EM_INV_ACCESS,
                            airEmInvReport,
                            request);
        return mapping.findForward("continue");
    }
}