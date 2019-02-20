package com.sehinc.stormwater.server.plan;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.sql.RecordExistsSQLHelper;
import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.permitperiod.GoalPermitTimePeriodData;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class GoalService
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalService.class);
    public static
    String
        FIND_PERMIT_PERIODS_BY_GOALID =
        "com.sehinc.stormwater.db.permitperiod.PermitTimePeriodData.findByGoalId";
    public static
    String
        FIND_GOALS_ACTIVITY_FORM_INSTANCE_BY_GOAL_ACTIVITY_ID =
        "com.sehinc.stormwater.db.plan.GoalActivityFormInstanceData.byGoalActivityId";
    public static
    String
        FIND_GOAL_ACTIVITY_FORMS_BY_CLIENT_ID =
        "com.sehinc.stormwater.db.plan.GoalActivityFormData.byClientId";

    public GoalService()
    {
    }

    public static List getGoalActivityFormsList()
    {
        List
            results =
            new ArrayList();
        GoalActivityFormData
            goalActivityFormData =
            new GoalActivityFormData();
        List
            goalActivityFormList =
            goalActivityFormData.retrieveAll();
        Iterator
            i =
            goalActivityFormList.iterator();
        while (i.hasNext())
        {
            GoalActivityFormData
                form =
                (GoalActivityFormData) i.next();
            results.add(new LabelValueBean(form.getName(),
                                           form.getId()
                                               .toString()));
        }
        return results;
    }

    public static List getGoalActivityFormsList(Integer clientId)
    {
        List
            results =
            new ArrayList();
        List
            goalActivityFormList =
            getGoalActivityFormByClient(clientId);
        Iterator
            i =
            goalActivityFormList.iterator();
        while (i.hasNext())
        {
            GoalActivityFormData
                form =
                (GoalActivityFormData) i.next();
            results.add(new LabelValueBean(form.getName(),
                                           form.getId()
                                               .toString()));
        }
        return results;
    }

    public static List getGoalActivityFormByClient(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_GOAL_ACTIVITY_FORMS_BY_CLIENT_ID,
                                              parameters);
    }

    public static List getGoalActivityFormInstance(Integer goalActivityId)
    {
        Object[][]
            parameters =
            {
                {
                    "goalActivityId",
                    goalActivityId}};
        return HibernateUtil.findByNamedQuery(FIND_GOALS_ACTIVITY_FORM_INSTANCE_BY_GOAL_ACTIVITY_ID,
                                              parameters);
    }

    public static List getGoalActivityFrequencyList()
    {
        List
            results =
            new ArrayList();
        TreeSet
            goalActivityFrequencyList =
            new TreeSet(GoalActivityFrequencyData.getAll());
        Iterator
            i =
            goalActivityFrequencyList.iterator();
        while (i.hasNext())
        {
            GoalActivityFrequencyData
                frequency =
                (GoalActivityFrequencyData) i.next();
            results.add(new LabelValueBean(frequency.getName(),
                                           frequency.getId()
                                               .toString()));
        }
        return results;
    }

    public static String renderActivePermitPeriodCheckboxHTML(HttpServletRequest request, GoalValue goalValue, String parentCheckableElement, String formName)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        PlanValue
            planValue =
            (PlanValue) request.getSession()
                .getAttribute(SessionKeys.PLAN);
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
        List
            permitTimePeriodList =
            permitPeriodValue.getPermitTimePeriods();
        List
            goalPermitTimePeriodList;
        if (goalValue
            == null)
        {
            goalPermitTimePeriodList =
                new ArrayList();
        }
        else
        {
            goalPermitTimePeriodList =
                GoalPermitTimePeriodData.findGoalId(goalValue.getId());
        }
        boolean
            active;
        Iterator
            permitTimePeriodIterator =
            permitTimePeriodList.iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            active =
                false;
            buffer.append("<label class='checkbox inline'>");
            PermitTimePeriodValue
                permitTimePeriod =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            Iterator
                goalPermitTimePeriodIterator =
                goalPermitTimePeriodList.iterator();
            while (goalPermitTimePeriodIterator.hasNext())
            {
                GoalPermitTimePeriodData
                    goalPermitTimePeriod =
                    (GoalPermitTimePeriodData) goalPermitTimePeriodIterator.next();
                if (goalPermitTimePeriod.hasPermitTimePeriod(permitTimePeriod))
                {
                    active =
                        true;
                }
            }
            String
                statusTimePeriodId =
                "statusTimePeriodId_"
                + permitTimePeriod.getId();
            String
                javascript =
                ((parentCheckableElement
                  != null)
                     ? "document.forms['"
                       + formName
                       + "']."
                       + parentCheckableElement
                       + ".checked = true; "
                     : "")
                + "if (document.forms['"
                + formName
                + "'].activeTimePeriodId_"
                + permitTimePeriod.getId()
                + ".checked == false) {document.forms['"
                + formName
                + "']."
                + statusTimePeriodId
                + ".checked = false;}";
            buffer.append("<input type='checkbox' id='activeTimePeriodId_"
                          + permitTimePeriod.getId()
                          + "' name='activeTimePeriod' value='"
                          + permitTimePeriod.getId()
                          + "' "
                          + ((active)
                                 ? "checked=checked"
                                 : "")
                          + " onclick='"
                          + javascript
                          + "'>"
                          + permitTimePeriod.getName());
            buffer.append("</label>");
        }
        return buffer.toString();
    }

    public static String renderEditActivePermitPeriodCheckboxHTML(HttpServletRequest request, String parentCheckableElement, String formName)
    {
        GoalValue
            goalValue =
            (GoalValue) request.getSession()
                .getAttribute(SessionKeys.GOAL);
        return renderActivePermitPeriodCheckboxHTML(request,
                                                    goalValue,
                                                    parentCheckableElement,
                                                    formName);
    }

    public static String renderStatusPermitPeriodCheckboxHTML(HttpServletRequest request, GoalValue goalValue, String parentCheckableElement, String formName)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        PlanValue
            planValue =
            (PlanValue) request.getSession()
                .getAttribute(SessionKeys.PLAN);
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
        List
            permitTimePeriodList =
            permitPeriodValue.getPermitTimePeriods();
        List
            goalPermitTimePeriodList;
        if (goalValue
            == null)
        {
            goalPermitTimePeriodList =
                new ArrayList();
        }
        else
        {
            goalPermitTimePeriodList =
                GoalPermitTimePeriodData.findGoalId(goalValue.getId());
        }
        boolean
            complete;
        Iterator
            permitTimePeriodIterator =
            permitTimePeriodList.iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            complete =
                false;
            buffer.append("<label class='checkbox inline'>");
            PermitTimePeriodValue
                permitTimePeriod =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            Iterator
                goalPermitTimePeriodIterator =
                goalPermitTimePeriodList.iterator();
            while (goalPermitTimePeriodIterator.hasNext())
            {
                GoalPermitTimePeriodData
                    goalPermitTimePeriod =
                    (GoalPermitTimePeriodData) goalPermitTimePeriodIterator.next();
                if (goalPermitTimePeriod.hasPermitTimePeriod(permitTimePeriod))
                {
                    if (goalPermitTimePeriod.isComplete())
                    {
                        complete =
                            true;
                    }
                }
            }
            String
                activeTimePeriodId =
                "activeTimePeriodId_"
                + permitTimePeriod.getId();
            String
                javascript =
                ((parentCheckableElement
                  != null)
                     ? "document.forms['"
                       + formName
                       + "']."
                       + parentCheckableElement
                       + ".checked = true; "
                     : "")
                + "if (document.forms['"
                + formName
                + "'].statusTimePeriodId_"
                + permitTimePeriod.getId()
                + ".checked == true) {document.forms['"
                + formName
                + "']."
                + activeTimePeriodId
                + ".checked = true;}";
            buffer.append("<input type='checkbox' id='statusTimePeriodId_"
                          + permitTimePeriod.getId()
                          + "' name='statusTimePeriod' value='"
                          + permitTimePeriod.getId()
                          + "' "
                          + ((complete)
                                 ? "checked=checked"
                                 : "")
                          + " onclick='"
                          + javascript
                          + "'>"
                          + permitTimePeriod.getName());
            buffer.append("</label>");
        }
        return buffer.toString();
    }

/*
    public static String renderNewStatusPermitPeriodCheckboxHTML(HttpServletRequest request, String parentCheckableElement, String formName)
    {
        return renderStatusPermitPeriodCheckboxHTML(request,
                                                    null,
                                                    parentCheckableElement,
                                                    formName);
    }
*/

    public static String renderEditStatusPermitPeriodCheckboxHTML(HttpServletRequest request, String parentCheckableElement, String formName)
    {
        GoalValue
            goalValue =
            (GoalValue) request.getSession()
                .getAttribute(SessionKeys.GOAL);
        return renderStatusPermitPeriodCheckboxHTML(request,
                                                    goalValue,
                                                    parentCheckableElement,
                                                    formName);
    }

    public static void processPermitPeriodCheckbox(HttpServletRequest request, GoalValue goalValue)
    {
        PlanValue
            planValue =
            (PlanValue) request.getSession()
                .getAttribute(SessionKeys.PLAN);
        UserValue
            userValue =
            SessionService.getUserValue(request);
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
        List
            permitTimePeriodList =
            permitPeriodValue.getPermitTimePeriods();
        String[]
            activePermitPeriodArray =
            request.getParameterValues("activeTimePeriod");
        String[]
            statusPermitPeriodArray =
            request.getParameterValues("statusTimePeriod");
        if (activePermitPeriodArray
            == null)
        {
            activePermitPeriodArray =
                new String[] {};
        }
        if (statusPermitPeriodArray
            == null)
        {
            statusPermitPeriodArray =
                new String[] {};
        }
        Enumeration
            enumNames =
            request.getParameterNames();
        LOG.info("Parameter Names");
        while (enumNames.hasMoreElements())
        {
            LOG.info(enumNames.nextElement()
                         .toString());
        }
        Enumeration
            enum2 =
            request.getAttributeNames();
        LOG.info("Attribute Names");
        while (enum2.hasMoreElements())
        {
            LOG.info(enum2.nextElement()
                         .toString());
        }
        for (
            int
                a =
                0; a
                   < activePermitPeriodArray.length; a++)
        {
            LOG.info("activePermitPeriodArray["
                     + a
                     + "]="
                     + activePermitPeriodArray[a]);
        }
        for (
            int
                a =
                0; a
                   < statusPermitPeriodArray.length; a++)
        {
            LOG.info("statusPermitPeriodArray["
                     + a
                     + "]="
                     + statusPermitPeriodArray[a]);
        }
        Iterator
            permitTimePeriodIterator =
            permitTimePeriodList.iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            boolean
                active =
                false;
            boolean
                complete =
                false;
            PermitTimePeriodValue
                permitTimePeriod =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            for (
                int
                    i =
                    0; i
                       < activePermitPeriodArray.length; i++)
            {
                if (Integer.parseInt(activePermitPeriodArray[i])
                    == permitTimePeriod.getId())
                {
                    active =
                        true;
                }
            }
            for (
                int
                    j =
                    0; j
                       < statusPermitPeriodArray.length; j++)
            {
                if (Integer.parseInt(statusPermitPeriodArray[j])
                    == permitTimePeriod.getId())
                {
                    complete =
                        true;
                }
            }
            GoalPermitTimePeriodData
                goalPermitTimePeriodData =
                PermitPeriodService.getGoalPermitTimePeriod(goalValue.getId(),
                                                            permitTimePeriod.getId());
            if (active)
            {
                if (goalPermitTimePeriodData
                    == null)
                {
                    goalPermitTimePeriodData =
                        new GoalPermitTimePeriodData();
                }
                goalPermitTimePeriodData.setGoalId(goalValue.getId());
                goalPermitTimePeriodData.setPermitTimePeriodId(permitTimePeriod.getId());
                goalPermitTimePeriodData.setComplete(complete);
                goalPermitTimePeriodData.save(userValue);
            }
            else
            {
                if (goalPermitTimePeriodData
                    != null)
                {
                    goalPermitTimePeriodData.delete();
                }
            }
        }
    }

    public static boolean isClientHasAccessToGoalActivity(Integer clientId, Integer goalActivityId)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        RecordExistsSQLHelper
            recordExistsSQLHelper =
            new RecordExistsSQLHelper();
        buffer.append("SELECT ");
        buffer.append(recordExistsSQLHelper.getValueSubQuery());
        buffer.append(" FROM [PLAN], MCM, BMP, GOAL, GOAL_ACTIVITY ");
        buffer.append("WHERE [PLAN].CLIENT_ID = ? ");
        buffer.append("  AND [PLAN].STATUS_CD = "
                      + StatusCodeData.STATUS_CODE_ACTIVE
                      + " ");
        buffer.append("  AND [PLAN].ID = MCM.PLAN_ID ");
        buffer.append("  AND MCM.STATUS_CD = "
                      + StatusCodeData.STATUS_CODE_ACTIVE
                      + " ");
        buffer.append("  AND MCM.ID = BMP.MCM_ID ");
        buffer.append("  AND BMP.STATUS_CD = "
                      + StatusCodeData.STATUS_CODE_ACTIVE
                      + " ");
        buffer.append("  AND GOAL.BMP_ID = BMP.ID ");
        buffer.append("  AND GOAL.STATUS_CD = "
                      + StatusCodeData.STATUS_CODE_ACTIVE
                      + " ");
        buffer.append("  AND GOAL.ID = GOAL_ACTIVITY.GOAL_ID ");
        buffer.append("  AND GOAL_ACTIVITY.STATUS_CD = "
                      + StatusCodeData.STATUS_CODE_ACTIVE
                      + " ");
        buffer.append("  AND GOAL_ACTIVITY.ID = ?");
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(buffer.toString());
        statement.setInt(1,
                         clientId.intValue());
        statement.setInt(2,
                         goalActivityId.intValue());
        return SQLHelper.retrieveZeroOrOneValue(statement,
                                                recordExistsSQLHelper)
               != null;
    }

    public static List getGoalValuesList(Integer goalId)
    {
        Object[][]
            parameters =
            {
                {
                    "goalId",
                    goalId}};
        return HibernateUtil.findByNamedQuery(FIND_PERMIT_PERIODS_BY_GOALID,
                                              parameters);
    }
}