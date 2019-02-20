package com.sehinc.stormwater.action.plan;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanPublishData;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.util.List;

public class PlanViewPublishAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanViewPublishAction.class);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        DynaActionForm
            aForm =
            (DynaActionForm) form;
        LOG.info("In PlanViewPublishAction");
        PlanValue
            planValue =
            getPlanValue(request);
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        PermitTimePeriodValue
            permitTimePeriodValue =
            PermitPeriodService.getCurrentPermitTimePeriodValue(planValue.getPermitPeriodId());
        DateFormat
            aDateFormat =
            DateUtil.MDY_FORMAT;
        DateFormat
            bDateFormat =
            DateUtil.MDYT_FORMAT;
        aForm.set("planName",
                  planValue.getName());
        if (permitTimePeriodValue
            != null)
        {
            aForm.set("startDate",
                      aDateFormat.format(permitTimePeriodValue.getStartDate()));
            aForm.set("endDate",
                      aDateFormat.format(permitTimePeriodValue.getEndDate()));
        }
        if (UserService.getSecurityLevel(userValue.getGroupId())
            == SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL)
        {
            aForm.set("isSystemAdmin",
                      "true");
        }
        request.getSession()
            .removeAttribute(SessionKeys.STORM_PLAN_PUBLISH_URL);
        String
            aPublishURL =
            ApplicationProperties.getProperty("base.public.web.url")
            + "/"
            + ApplicationProperties.getProperty("base.public.web.path")
            + "/client"
            + clientValue.getId()
            + "/"
            + ApplicationProperties.getProperty("application.stormwater")
            + "/plan"
            + planValue.getId()
            + "/PlanReport.pdf";
        LOG.info(aPublishURL);
        String
            aPublishLocation =
            ApplicationProperties.getProperty("base.public.directory")
            + "/client"
            + clientValue.getId()
            + "/"
            + ApplicationProperties.getProperty("application.stormwater")
            + "/plan"
            + planValue.getId()
            + "/PlanReport.pdf";
        LOG.info(aPublishLocation);
        File
            aReportFile =
            new File(aPublishLocation);
        if (aReportFile.exists())
        {
            setSessionAttribute(SessionKeys.STORM_PLAN_PUBLISH_URL,
                                aPublishURL.toString(), request);
            List
                publishList =
                PlanPublishData.findActiveByClientAndPlan(clientValue.getId(),
                                                          planValue.getId());
            for (Object o : publishList)
            {
                PlanPublishData
                    publishData =
                    (PlanPublishData) o;
                UserData
                    auditUser =
                    new UserData(publishData.getUpdateUserId());
                auditUser.load();
                String
                    firstName =
                    auditUser.getFirstName();
                String
                    lastName =
                    auditUser.getLastName();
                aForm.set("prevStartDate",
                          aDateFormat.format(publishData.getStartDate()));
                aForm.set("prevEndDate",
                          aDateFormat.format(publishData.getEndDate()));
                aForm.set("publishedDate",
                          bDateFormat.format(publishData.getUpdateTimestamp()));
                aForm.set("publishedUser",
                          firstName
                          + " "
                          + lastName);
                break;
            }
        }
        return mapping.findForward("continue");
    }
}
