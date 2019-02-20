package com.sehinc.stormwater.server.permitperiod;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.sql.CountSQLHelper;
import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.hierarchy.permitperiod.PermitPeriodLeaf;
import com.sehinc.stormwater.db.permitperiod.GoalPermitTimePeriodData;
import com.sehinc.stormwater.db.permitperiod.PermitPeriodData;
import com.sehinc.stormwater.db.permitperiod.PermitTimePeriodData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.value.permitperiod.GoalPermitTimePeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;

import java.util.*;

public class PermitPeriodService
{
    public PermitPeriodService()
    {
    }

    public static List getPermitPeriodsAsLeafList()
    {
        List
            results =
            new ArrayList();
        List
            activePermitPeriods =
            findActivePermitPeriods();
        Iterator
            i =
            activePermitPeriods.iterator();
        while (i.hasNext())
        {
            results.add(new PermitPeriodLeaf(new PermitPeriodValue((PermitPeriodData) i.next())));
        }
        return results;
    }

    public static List getPermitPeriodLabelValueBeanList()
    {
        List
            results =
            new ArrayList();
        Iterator
            i =
            findActivePermitPeriods().iterator();
        while (i.hasNext())
        {
            PermitPeriodData
                permitPeriod =
                (PermitPeriodData) i.next();
            results.add(new LabelValueBean(permitPeriod.getName(),
                                           String.valueOf(permitPeriod.getId())));
        }
        return results;
    }

    public static List getPermitTypeLabelValueBeanList()
    {
        return PlanPermitType.getPlanPermitTypes();
    }

    public static List findActivePermitPeriods()
    {
        Object
            parameters
            [
            ] =
            {StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select permitPeriodData from PermitPeriodData as permitPeriodData where permitPeriodData.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static PermitPeriodData getPermitPeriod(Integer permitPeriodId)
    {
        PermitPeriodData
            permitPeriodData =
            new PermitPeriodData();
        permitPeriodData.setId(permitPeriodId);
        permitPeriodData.load();
        if (!permitPeriodData.isActive())
        {
            return null;
        }
        return permitPeriodData;
    }

/*
    public static PermitPeriodData getPermitPeriod(PermitPeriodValue permitPeriodValue)
    {
        return getPermitPeriod(permitPeriodValue.getId());
    }
*/

    public static List findPermitTimePeriodDataByPermitId(PermitPeriodData permitPeriodData)
    {
        return findPermitTimePeriodDataByPermitId(permitPeriodData.getId());
    }

    public static PermitTimePeriodValue getPermitTimePeriodValue(Integer permitTimePeriodId)
    {
        PermitTimePeriodData
            permitTimePeriodData =
            new PermitTimePeriodData();
        permitTimePeriodData.setId(permitTimePeriodId);
        if (permitTimePeriodData.retrieveByPrimaryKeysAlternate())
        {
            return new PermitTimePeriodValue(permitTimePeriodData);
        }
        return null;
    }

/*
    public static PermitTimePeriodValue getPermitTimePeriodValue(GoalPermitTimePeriodData goalPermitTimePeriodData)
    {
        return getPermitTimePeriodValue(goalPermitTimePeriodData.getPermitTimePeriodId());
    }
*/

    public static PermitTimePeriodData getPermitTimePeriod(Integer permitTimePeriodId)
    {
        PermitTimePeriodData
            permitTimePeriodData =
            new PermitTimePeriodData();
        permitTimePeriodData.setId(permitTimePeriodId);
        if (permitTimePeriodData.load())
        {
            return permitTimePeriodData;
        }
        return null;
    }

/*
    public static PermitTimePeriodData getPermitTimePeriod(int permitTimePeriodId)
    {
        return getPermitTimePeriod(new Integer(permitTimePeriodId));
    }
*/

    public static PermitTimePeriodData getPermitTimePeriod(GoalPermitTimePeriodData goalPermitTimePeriodData)
    {
        return getPermitTimePeriod(goalPermitTimePeriodData.getPermitTimePeriodId());
    }

    public static PermitPeriodValue getPermitPeriodValue(PermitPeriodData permitPeriodData)
    {
        PermitPeriodValue
            permitPeriodValue =
            new PermitPeriodValue(permitPeriodData);
        for (Object o : findPermitTimePeriodDataByPermitId(permitPeriodData))
        {
            PermitTimePeriodData
                permitTimePeriod =
                (PermitTimePeriodData) o;
            permitPeriodValue.addPermitTimePeriod(new PermitTimePeriodValue(permitTimePeriod));
        }
        return permitPeriodValue;
    }

/*
    public static PermitPeriodValue getPermitPeriodValue(int permitPeriodId)
    {
        return getPermitPeriodValue(new Integer(permitPeriodId));
    }
*/

    public static PermitPeriodValue getPermitPeriodValue(Integer permitPeriodId)
    {
        PermitPeriodData
            permitPeriodData =
            new PermitPeriodData();
        permitPeriodData.setId(permitPeriodId);
        if (!permitPeriodData.load())
        {
            return null;
        }
        if (!permitPeriodData.isActive())
        {
            return null;
        }
        return getPermitPeriodValue(permitPeriodData);
    }

    public static boolean validatePermitPeriod(PermitPeriodValue permitPeriodValue)
    {
        if (permitPeriodValue.getName()
            == null
            || permitPeriodValue.getName()
                   .length()
               == 0)
        {
            return false;
        }
        return true;
    }

    public static boolean validatePermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue)
    {
        if (permitTimePeriodValue
            == null)
        {
            return false;
        }
        String
            name =
            permitTimePeriodValue.getName();
        Integer
            permitPeriodId =
            permitTimePeriodValue.getPermitPeriodId();
        if (findPermitTimePeriodDataByNameAndPermitId(name,
                                                      permitPeriodId).size()
            > 0)
        {
            return false;
        }
        if (permitTimePeriodValue.getStartDate()
            == null
            || permitTimePeriodValue.getEndDate()
               == null)
        {
            return false;
        }
        if (permitTimePeriodValue.getStartDate()
                .compareTo(permitTimePeriodValue.getEndDate())
            >= 0)
        {
            return false;
        }
        return true;
    }

    public static List findPermitTimePeriodDataByPermitId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from PermitTimePeriodData as data where data.permitPeriodId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findPermitTimePeriodDataByNameAndPermitId(String name, Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                name,
                id};
        String
            queryString =
            "select data from PermitTimePeriodData as data where data.name = ? and data.permitPeriodId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static boolean validateDeletePermitPeriod(PermitPeriodValue permitPeriodValue)
    {
        if (permitPeriodValue
            == null)
        {
            return false;
        }
        List
            plans =
            PlanData.findActiveByPermitPeriodId(permitPeriodValue.getId());
        if (plans.size()
            > 0)
        {
            return false;
        }
        return true;
    }

    public static void deletePermitPeriod(PermitPeriodValue permitPeriodValue, UserValue userValue)
    {
        PermitPeriodData
            permitPeriodData =
            new PermitPeriodData();
        permitPeriodData.setId(permitPeriodValue.getId());
        if (permitPeriodData.retrieveByPrimaryKeysAlternate())
        {
            permitPeriodData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
            permitPeriodData.update(userValue);
            return;
        }
    }

/*
    public static Integer updatePermitPeriod(PermitPeriodValue permitPeriodValue, UserValue userValue)
    {
        PermitPeriodData
            permitPeriodData =
            new PermitPeriodData();
        if (permitPeriodValue.getId()
            != null)
        {
            permitPeriodData.setId(permitPeriodValue.getId());
            permitPeriodData.retrieveByPrimaryKeysAlternate();
        }
        permitPeriodData.setName(permitPeriodValue.getName());
        if (permitPeriodValue.getId()
            == null)
        {
            permitPeriodData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            permitPeriodData.insert(userValue);
        }
        else
        {
            permitPeriodData.update(userValue);
        }
        Iterator
            permitTimePeriodIterator =
            permitPeriodValue.getPermitTimePeriods()
                .iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            PermitTimePeriodValue
                permitTimePeriodValue =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            PermitTimePeriodData
                permitTimePeriodData =
                new PermitTimePeriodData();
            if (permitTimePeriodValue.getId()
                != null)
            {
                permitTimePeriodData.setId(permitTimePeriodValue.getId());
                permitTimePeriodData.retrieveByPrimaryKeysAlternate();
            }
            permitTimePeriodData.setName(permitTimePeriodValue.getName());
            permitTimePeriodData.setPermitPeriodId(permitPeriodData.getId());
            permitTimePeriodData.setStartDate(permitTimePeriodValue.getStartDate());
            permitTimePeriodData.setEndDate(permitTimePeriodValue.getEndDate());
            if (permitTimePeriodValue.getId()
                == null)
            {
                permitTimePeriodData.insert(userValue);
            }
            else
            {
                permitTimePeriodData.update(userValue);
            }
        }
        return permitPeriodData.getId();
    }
*/

    public static Integer addPermitTimePeriod(PermitPeriodValue permitPeriodValue, PermitTimePeriodValue permitTimePeriodValue, UserValue userValue)
    {
        PermitPeriodData
            permitPeriodData =
            new PermitPeriodData();
        if (permitPeriodValue.getId()
            != null)
        {
            permitPeriodData.setId(permitPeriodValue.getId());
            permitPeriodData.retrieveByPrimaryKeysAlternate();
        }
        permitPeriodData.setName(permitPeriodValue.getName());
        if (permitPeriodValue.getId()
            == null)
        {
            permitPeriodData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        }
        permitPeriodData.save(userValue);
        PermitTimePeriodData
            permitTimePeriodData =
            new PermitTimePeriodData();
        permitTimePeriodData.setName(permitTimePeriodValue.getName());
        permitTimePeriodData.setPermitPeriodId(permitPeriodData.getId());
        permitTimePeriodData.setStartDate(permitTimePeriodValue.getStartDate());
        permitTimePeriodData.setEndDate(permitTimePeriodValue.getEndDate());
        permitTimePeriodData.insert(userValue);
        return permitPeriodData.getId();
    }

    public static boolean validateDeletePermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue)
    {
        if (permitTimePeriodValue
            == null)
        {
            return false;
        }
        Integer
            id =
            permitTimePeriodValue.getId();
        if (GoalPermitTimePeriodData.findByPermitTimePeriodId(id)
                .size()
            > 0)
        {
            return false;
        }
        return true;
    }

    public static void deletePermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue, UserValue userValue)
    {
        deletePermitTimePeriod(permitTimePeriodValue);
    }

    public static void deletePermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue)
    {
        PermitTimePeriodData
            permitTimePeriodData =
            new PermitTimePeriodData();
        permitTimePeriodData.setId(permitTimePeriodValue.getId());
        permitTimePeriodData.delete();
    }

    public static List getGoalPermitTimePeriodValues(Integer goalId)
    {
        TreeSet
            goalPermitTimePeriodValues =
            new TreeSet();
        Iterator
            goalPermitTimePeriodIterator =
            GoalPermitTimePeriodData.findGoalId(goalId)
                .iterator();
        while (goalPermitTimePeriodIterator.hasNext())
        {
            GoalPermitTimePeriodData
                goalPermitTimePeriod =
                (GoalPermitTimePeriodData) goalPermitTimePeriodIterator.next();
            PermitTimePeriodData
                permitTimePeriod =
                PermitPeriodService.getPermitTimePeriod(goalPermitTimePeriod);
            goalPermitTimePeriodValues.add(new GoalPermitTimePeriodValue(goalPermitTimePeriod,
                                                                         permitTimePeriod));
        }
        return new ArrayList(goalPermitTimePeriodValues);
    }

    public static GoalPermitTimePeriodData getGoalPermitTimePeriod(int goalId, Integer permitTimePeriodId)
    {
        return getGoalPermitTimePeriod(new Integer(goalId),
                                       permitTimePeriodId);
    }

    public static GoalPermitTimePeriodData getGoalPermitTimePeriod(Integer goalId, Integer permitTimePeriodId)
    {
        List
            goalPeriods =
            GoalPermitTimePeriodData.findByPermitTimePeriodIdAndGoalId(permitTimePeriodId,
                                                                       goalId);
        if (goalPeriods.size()
            == 1)
        {
            return (GoalPermitTimePeriodData) goalPeriods.get(0);
        }
        else
        {
            return null;
        }
    }

/*
    public static int getGoalActivityCountInTimePeriod(int goalId, int permitTimePeriodId)
    {
        return getGoalActivityCountInTimePeriod(new Integer(goalId),
                                                new Integer(permitTimePeriodId)).intValue();
    }
*/

    public static Integer getGoalActivityCountInTimePeriod(Integer goalId, Integer permitTimePeriodId)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        CountSQLHelper
            countSQLHelper =
            new CountSQLHelper();
        buffer.append("SELECT ");
        buffer.append(countSQLHelper.getValueSubQuery());
        buffer.append(" FROM GOAL_ACTIVITY, GOAL_PERMIT_TIME_PERIOD ");
        buffer.append("WHERE GOAL_ACTIVITY.GOAL_ID = "
                      + goalId);
        buffer.append("  AND GOAL_PERMIT_TIME_PERIOD.GOAL_ID = GOAL_ACTIVITY.GOAL_ID");
        buffer.append("  AND GOAL_PERMIT_TIME_PERIOD.PERMIT_TIME_PERIOD_ID = "
                      + permitTimePeriodId);
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(buffer.toString());
        Integer
            count =
            (Integer) SQLHelper.retrieveValue(statement,
                                              countSQLHelper);
        if (count
            == null)
        {
            count =
                new Integer(0);
        }
        return count;
    }

    public static boolean isCurrentPermitTimePeriod(Integer permitTimePeriodId)
    {
        PermitTimePeriodData
            permitTimePeriodData =
            getPermitTimePeriod(permitTimePeriodId);
        if (permitTimePeriodData
            == null)
        {
            return false;
        }
        return DateUtil.isInDateRange(new Date(),
                                      permitTimePeriodData.getStartDate(),
                                      permitTimePeriodData.getEndDate());
    }

    public static PermitTimePeriodValue getCurrentPermitTimePeriodValue(Integer permitPeriodId)
    {
        Date
            today =
            new Date();
        PermitPeriodValue
            permitPeriodValue =
            getPermitPeriodValue(permitPeriodId);
        if (permitPeriodValue
            != null)
        {
            Iterator
                permitTimePeriodIterator =
                permitPeriodValue.getPermitTimePeriods()
                    .iterator();
            while (permitTimePeriodIterator.hasNext())
            {
                PermitTimePeriodValue
                    permitTimePeriodValue =
                    (PermitTimePeriodValue) permitTimePeriodIterator.next();
                if (DateUtil.isInDateRange(today,
                                           permitTimePeriodValue.getStartDate(),
                                           permitTimePeriodValue.getEndDate()))
                {
                    return permitTimePeriodValue;
                }
            }
        }
        return null;
    }
}