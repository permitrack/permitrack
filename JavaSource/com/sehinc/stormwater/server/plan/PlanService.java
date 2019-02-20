package com.sehinc.stormwater.server.plan;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.User;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.*;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlanService
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanService.class);
    public static
    String
        FIND_PLAN_TEMPLATES =
        "com.sehinc.stormwater.value.plan.PlanValue.byActive";
    public static
    String
        FIND_MCMS_BY_PLANID =
        "com.sehinc.stormwater.db.plan.MCMValue.byPlanId";
    public static
    String
        FIND_MCMS_BY_PLANID_WITH_MCM_EXCLUSION =
        "com.sehinc.stormwater.db.plan.MCMValue.byPlanIdWithMCMExclusion";
    public static
    String
        FIND_BMPS_BY_MCMID =
        "com.sehinc.stormwater.db.plan.BMPValue.byMCMId";
    public static
    String
        FIND_BMPS_BY_PLANID =
        "com.sehinc.stormwater.db.plan.BMPValue.byPlanId";
    public static
    String
        FIND_GOAL_ACTIVITY_BY_GOALID =
        "com.sehinc.stormwater.db.plan.GoalActivityValue.byGoal";
    public static
    String
        FIND_GOALS_BY_PLANID =
        "com.sehinc.stormwater.db.plan.GoalValue.byPlanId";
    public static
    String
        FIND_GOALS_ACTIVITY_BY_PLANID =
        "com.sehinc.stormwater.db.plan.GoalActivityValue.byPlanId";

    public PlanService()
    {
    }

    public static LabelValueBean getLabelValueBean(MCMValue mcmValue)
    {
        String
            label =
            ((mcmValue.getNumber()
              != null)
                 ? mcmValue.getNumber()
                .toString()
                 : "0")
            + " "
            + mcmValue.getName();
        String
            value =
            mcmValue.getId()
                .toString();
        return new LabelValueBean(label,
                                  value);
    }

    public static LabelValueBean getLabelValueBean(BMPValue bmpValue, MCMValue mcmValue)
    {
        BMPFormatter
            bmpFormatter =
            getPlanPermitType(bmpValue).getBMPFormatter();
        String
            label =
            ((mcmValue.getNumber()
              != null)
                 ? mcmValue.getNumber()
                .toString()
                 : "0")
            + ".";
        label +=
            bmpFormatter.formatBMPSubIdentifier(bmpValue);
        String
            value =
            bmpValue.getId()
                .toString();
        return new LabelValueBean(label,
                                  value);
    }

    public static LabelValueBean getTruncatedLabelValueBean(MCMValue mcmValue)
    {
        String
            name =
            (mcmValue.getName()
                 .length()
             <= 60)
                ? mcmValue.getName()
                : mcmValue.getName()
                    .substring(0,
                               (60
                                - 1));
        String
            label =
            ((mcmValue.getNumber()
              != null)
                 ? mcmValue.getNumber()
                .toString()
                 : "0")
            + " "
            + name;
        if (mcmValue.getName()
                .length()
            > 60)
        {
            label +=
                "...";
        }
        String
            value =
            mcmValue.getId()
                .toString();
        return new LabelValueBean(label,
                                  value);
    }

    public static LabelValueBean getTruncatedLabelValueBean(BMPValue bmpValue, MCMValue mcmValue)
    {
        BMPFormatter
            bmpFormatter =
            getPlanPermitType(bmpValue).getBMPFormatter();
        String
            name =
            (bmpValue.getName()
                 .length()
             <= 70)
                ? bmpValue.getName()
                : bmpValue.getName()
                    .substring(0,
                               (70
                                - 1));
        String
            label =
            ((mcmValue.getNumber()
              != null)
                 ? mcmValue.getNumber()
                .toString()
                 : "0")
            + ".";
        label +=
            bmpFormatter.formatBMPSubIdentifier(bmpValue)
            + " "
            + name;
        if (bmpValue.getName()
                .length()
            > 70)
        {
            label +=
                "...";
        }
        String
            value =
            bmpValue.getId()
                .toString();
        return new LabelValueBean(label,
                                  value);
    }

    public static List getBMPValues(MCMValue mcmValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            i =
            BMPData.findActiveByMcmId(mcmValue.getId())
                .iterator();
        while (i.hasNext())
        {
            results.add(new BMPValue((BMPData) i.next()));
        }
        return results;
    }

/*
    public static List getMCMLabelValueList(PlanValue planValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            values =
            getMCMValuesList(planValue).iterator();
        while (values.hasNext())
        {
            MCMValue
                value =
                (MCMValue) values.next();
            results.add(getLabelValueBean(value));
        }
        return results;
    }
*/

    public static List getMCMReportLabelValueList(PlanValue planValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            values =
            getMCMValuesList(planValue).iterator();
        while (values.hasNext())
        {
            MCMValue
                value =
                (MCMValue) values.next();
            results.add(getTruncatedLabelValueBean(value));
        }
        return results;
    }

    public static List getMCMValuesList(PlanValue planValue)
    {
        Object[][]
            parameters =
            {
                {
                    "planId",
                    planValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_MCMS_BY_PLANID,
                                              parameters);
    }

    public static List getMCMValuesList(PlanValue planValue, Integer excludeMcmId)
    {
        Object[][]
            parameters =
            {
                {
                    "planId",
                    planValue.getId()},
                {
                    "mcmId",
                    excludeMcmId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_MCMS_BY_PLANID_WITH_MCM_EXCLUSION,
                                              parameters);
    }

    public static List getBMPValuesList(MCMValue mcmValue)
    {
        Object[][]
            parameters =
            {
                {
                    "mcmId",
                    mcmValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BMPS_BY_MCMID,
                                              parameters);
    }

/*
    public static List getBMPValuesList(PlanValue plan)
    {
        Object[][]
            parameters =
            {
                {
                    "planId",
                    plan.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BMPS_BY_PLANID,
                                              parameters);
    }
*/

/*
    public static List getGoalValuesList(PlanValue plan)
    {
        Object[][]
            parameters =
            {
                {
                    "planId",
                    plan.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_GOALS_BY_PLANID,
                                              parameters);
    }
*/

/*
    public static List getGoalActivityValuesList(PlanValue plan)
    {
        Object[][]
            parameters =
            {
                {
                    "planId",
                    plan.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_GOALS_ACTIVITY_BY_PLANID,
                                              parameters);
    }
*/

/*
    public static List getBMPLabelValueList(PlanValue planValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            mcmValues =
            getMCMValuesList(planValue).iterator();
        while (mcmValues.hasNext())
        {
            MCMValue
                mcmValue =
                (MCMValue) mcmValues.next();
            Iterator
                values =
                getBMPValuesList(mcmValue).iterator();
            while (values.hasNext())
            {
                BMPValue
                    value =
                    (BMPValue) values.next();
                results.add(getLabelValueBean(value,
                                              mcmValue));
            }
        }
        return results;
    }
*/

    public static List getBMPReportLabelValueList(PlanValue planValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            mcmValues =
            getMCMValuesList(planValue).iterator();
        while (mcmValues.hasNext())
        {
            MCMValue
                mcmValue =
                (MCMValue) mcmValues.next();
            Iterator
                values =
                getBMPValuesList(mcmValue).iterator();
            while (values.hasNext())
            {
                BMPValue
                    value =
                    (BMPValue) values.next();
                results.add(getTruncatedLabelValueBean(value,
                                                       mcmValue));
            }
        }
        return results;
    }

    public static PlanPermitType getPlanPermitTypeByPlanId(Integer planId)
    {
        PlanData
            planData =
            new PlanData();
        planData.setId(planId);
        if (planData.load())
        {
            return PlanPermitType.getByPlanId(planData.getId());
        }
        return null;
    }

    public static PlanPermitType getPlanPermitType(BMPValue bmpValue)
    {
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(bmpValue.getMcmId());
        if (mcmData.load())
        {
            LOG.debug("mcmData.getId = "
                      + mcmData.getId());
            return PlanPermitType.getByPlanId(mcmData.getPlanId());
        }
        LOG.debug("could not load MCM Id = "
                  + bmpValue.getMcmId());
        return null;
    }

    public static List getGoalValues(BMPValue bmpValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            i =
            GoalData.findActiveByBmpId(bmpValue.getId())
                .iterator();
        while (i.hasNext())
        {
            results.add(new GoalValue((GoalData) i.next()));
        }
        return results;
    }

    public static List getGoalActivityValues(GoalValue goalValue)
    {
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "goalId",
                    goalValue.getId()}};
        return HibernateUtil.findByNamedQuery(FIND_GOAL_ACTIVITY_BY_GOALID,
                                              parameters);
    }

    public static void copyPlan(Integer fromPlanId, Integer toPlanId, User user)
    {
        Iterator
            m =
            MCMData.findActiveByPlanId(fromPlanId)
                .iterator();
        while (m.hasNext())
        {
            MCMData
                mcm =
                (MCMData) m.next();
            Integer
                oldMcmId =
                mcm.getId();
            mcm.setPlanId(toPlanId);
            mcm.setOwnerId(new Integer(0));
            Integer
                newMCMId =
                mcm.insert(user);
            Iterator
                b =
                BMPData.findActiveByMcmId(oldMcmId)
                    .iterator();
            while (b.hasNext())
            {
                BMPData
                    bmp =
                    (BMPData) b.next();
                Integer
                    bmpId =
                    bmp.getId();
                bmp.setMcmId(newMCMId);
                bmp.setOwnerId(new Integer(0));
                Integer
                    newBMPId =
                    bmp.insert(user);
                Iterator
                    g =
                    GoalData.findActiveByBmpId(bmpId)
                        .iterator();
                while (g.hasNext())
                {
                    GoalData
                        goal =
                        (GoalData) g.next();
                    goal.setBmpId(newBMPId);
                    goal.setOwnerId(new Integer(0));
                    goal.insert(user);
                }
            }
        }
    }

    public static List getPlanTemplateLabelValueBeanList()
    {
        List
            results =
            new ArrayList();
        Iterator
            iter =
            getPlanTemplates().iterator();
        while (iter.hasNext())
        {
            PlanValue
                planValue =
                (PlanValue) iter.next();
            results.add(new LabelValueBean(planValue.getName(),
                                           String.valueOf(planValue.getId())));
        }
        return results;
    }

    public static List getPlanTemplates()
    {
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_PLAN_TEMPLATES,
                                              parameters);
    }

    public static List getPlanList(ClientValue clientValue)
    {
        return PlanData.findActiveNonTemplatesByClientId(clientValue.getId());
    }

    public static PlanData getPlan(Integer planId)
    {
        if (planId
            == null)
        {
            LOG.debug("attempting to retrieve plan with null planId");
            return null;
        }
        PlanData
            planData =
            new PlanData();
        planData.setId(planId);
        planData.load();
        if (!planData.isActive())
        {
            return null;
        }
        return planData;
    }

    public static PlanData getPlan(PlanValue planValue)
    {
        return getPlan(planValue.getId());
    }

/*
    public static String getPlanName(Integer planId)
    {
        if (planId
            == null)
        {
            return null;
        }
        PlanData
            planData =
            getPlan(planId);
        if (planData
            == null)
        {
            return null;
        }
        return planData.getName();
    }
*/

    public static BMPData getBMP(Integer bmpId)
    {
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpId);
        bmpData.load();
        if (!bmpData.isActive())
        {
            return null;
        }
        return bmpData;
    }

    public static MCMData getMCM(Integer mcmId)
    {
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmId);
        mcmData.load();
        if (!mcmData.isActive())
        {
            return null;
        }
        return mcmData;
    }

    public static GoalData getGoal(Integer goalId)
    {
        GoalData
            goalData =
            new GoalData();
        goalData.setId(goalId);
        goalData.load();
        if (!goalData.isActive())
        {
            return null;
        }
        return goalData;
    }

    public static GoalActivityData getGoalActivity(Integer goalActivityId)
    {
        GoalActivityData
            goalActivityData =
            new GoalActivityData();
        goalActivityData.setId(goalActivityId);
        goalActivityData.load();
        if (!goalActivityData.isActive())
        {
            return null;
        }
        return goalActivityData;
    }

    public static int getMCMCount(Integer planId)
    {
        String
            query =
            "select count(data.id) from MCMData data where data.planId = ? and data.status.code = ?";
        Object[]
            parameters =
            {
                planId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        Long
            count =
            (Long) HibernateUtil.findUnique(query,
                                            parameters);
        return count.intValue();
    }

    public static int getBMPCount(Integer mcmId)
    {
        String
            query =
            "select count(data.id) from BMPData data where data.mcmId = ? and data.status.code = ?";
        Object[]
            parameters =
            {
                mcmId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        return ((Long) HibernateUtil.findUnique(query,
                                                parameters)).intValue();
    }

    public static int getGoalCount(Integer bmpId)
    {
        String
            query =
            "select count(data.id) from GoalData data where data.bmpId = ? and data.status.code = ?";
        Object[]
            parameters =
            {
                bmpId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        return ((Long) HibernateUtil.findUnique(query,
                                                parameters)).intValue();
    }

    public static int getBMPDBGoalCount(Integer bmpDbId)
    {
        String
            query =
            "select count(data.id) from BMPDBGoalData data where data.bmpDBId = ?";
        Object[]
            parameters =
            {bmpDbId};
        return ((Long) HibernateUtil.findUnique(query,
                                                parameters)).intValue();
    }

    public static Integer savePlanAsTemplate(Integer planId, User userValue)
    {
        PlanData
            planData =
            new PlanData();
        planData.setId(planId);
        if (!planData.retrieveByPrimaryKeysAlternate())
        {
            return 0;
        }
        planData.setClientId(CommonConstants.SEH_CLIENT_ID);
        planData.setName(planData.getName());
        planData.setDescription(planData.getDescription());
        planData.setPermitNumber(null);
        planData.setStartDate(null);
        planData.setEndDate(null);
        planData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        planData.setTemplate(true);
        planData.setCmomProgram(false);
        LOG.debug("planId = "
                  + planId);
        LOG.debug("planName = "
                  + planData.getName());
        Integer
            planTemplateId =
            planData.insert(userValue);
        LOG.debug("planTemplateId = "
                  + planData.getId());
        Iterator
            m =
            MCMData.findActiveByPlanId(planId)
                .iterator();
        while (m.hasNext())
        {
            MCMData
                mcm =
                (MCMData) m.next();
            Integer
                mcmId =
                mcm.getId();
            mcm.setPlanId(planTemplateId);
            mcm.setOwnerId(null);
            mcm.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            Integer
                mcmTemplateId =
                mcm.insert(userValue);
            Iterator
                b =
                BMPData.findActiveByMcmId(mcmId)
                    .iterator();
            while (b.hasNext())
            {
                BMPData
                    bmp =
                    (BMPData) b.next();
                Integer
                    bmpId =
                    bmp.getId();
                bmp.setMcmId(mcmTemplateId);
                bmp.setOwnerId(null);
                bmp.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                Integer
                    bmpTemplateId =
                    bmp.insert(userValue);
                Iterator
                    g =
                    GoalData.findActiveByBmpId(bmpId)
                        .iterator();
                while (g.hasNext())
                {
                    GoalData
                        goal =
                        (GoalData) g.next();
                    goal.setBmpId(bmpTemplateId);
                    goal.setOwnerId(null);
                    goal.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                    goal.insert(userValue);
                }
            }
        }
        return planTemplateId;
    }

    private static String renderPermitPeriodRadioBoxHTML(PlanValue planValue, String parentCheckableElement)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
        List
            permitTimePeriodList =
            permitPeriodValue.getPermitTimePeriods();
        String
            parentToggleFunction =
            "toggle"
            + parentCheckableElement
            + "()";
        buffer.append("<script type='text/javascript'>");
        buffer.append("function "
                      + parentToggleFunction
                      + " {");
        buffer.append("document.getElementById('"
                      + parentCheckableElement
                      + "').checked = true;");
        buffer.append("}");
        buffer.append("</script>");
        boolean
            isChecked =
            false;
        String
            checked;
        Iterator
            permitTimePeriodIterator =
            permitTimePeriodList.iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            PermitTimePeriodValue
                permitTimePeriod =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            if ((PermitPeriodService.isCurrentPermitTimePeriod(permitTimePeriod.getId())
                 || !permitTimePeriodIterator.hasNext())
                && !isChecked)
            {
                checked =
                    " checked='checked' ";
                isChecked =
                    true;
            }
            else
            {
                checked =
                    " ";
            }
            buffer.append("<label class='radio inline' for='"
                          + "permitTimePeriodId_"
                          + permitTimePeriod.getId()
                          + "'>"
                          + "<input type='radio' id='"
                          + "permitTimePeriodId_"
                          +
                          permitTimePeriod.getId()
                          + "' name='permitTimePeriod' "
                          +
                          "value='"
                          + permitTimePeriod.getId()
                          + "' "
                          + checked
                          +
                          "onclick='"
                          + parentToggleFunction
                          + ";' />"
                          + permitTimePeriod.getName()
                          + "</label>");
        }
        return buffer.toString();
    }

    public static String renderPermitPeriodRadioBoxHTML(HttpServletRequest request, String parentCheckableElement)
    {
        PlanValue
            planValue =
            (PlanValue) request.getSession()
                .getAttribute(SessionKeys.PLAN);
        return renderPermitPeriodRadioBoxHTML(planValue,
                                              parentCheckableElement);
    }

    private static PermitTimePeriodValue getSelectedPermitTimePeriodRadioBox(HttpServletRequest request, PlanValue planValue)
    {
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
        List
            permitTimePeriodList =
            permitPeriodValue.getPermitTimePeriods();
        int
            permitTimePeriodId;
        permitTimePeriodId =
            Integer.parseInt(request.getParameter("permitTimePeriod"));
        Iterator
            permitTimePeriodIterator =
            permitTimePeriodList.iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            PermitTimePeriodValue
                permitTimePeriod =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            if (permitTimePeriod.getId()
                    .intValue()
                == permitTimePeriodId)
            {
                return permitTimePeriod;
            }
        }
        return null;
    }

    public static PermitTimePeriodValue getSelectedPermitTimePeriodRadioBox(HttpServletRequest request)
    {
        PlanValue
            planValue =
            (PlanValue) request.getSession()
                .getAttribute(SessionKeys.PLAN);
        return getSelectedPermitTimePeriodRadioBox(request,
                                                   planValue);
    }

    public static Integer copyBMP(Integer bmpId, Integer mcmId, User user)
        throws HibernateException
    {
        BMPData
            bmp =
            new BMPData();
        bmp.setId(bmpId);
        try
        {
            bmp.load();
            bmp.setMcmId(mcmId);
            Integer
                newBmpId =
                bmp.insert(user);
            Iterator
                g =
                GoalData.findActiveByBmpId(bmpId)
                    .iterator();
            while (g.hasNext())
            {
                GoalData
                    goal =
                    (GoalData) g.next();
                goal.setBmpId(newBmpId);
                goal.insert(user);
            }
            return newBmpId;
        }
        catch (HibernateException he)
        {
            LOG.error(he.getMessage());
            throw he;
        }
    }

    public static void moveBMP(Integer bmpId, Integer mcmId, User user)
        throws HibernateException
    {
        BMPData
            bmp =
            new BMPData();
        bmp.setId(bmpId);
        try
        {
            bmp.load();
            bmp.setMcmId(mcmId);
            bmp.update(user);
        }
        catch (HibernateException he)
        {
            LOG.error(he.getMessage());
            throw he;
        }
    }
}