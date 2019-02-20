package com.sehinc.stormwater.value.message;

import com.sehinc.common.db.sql.SQLValueHelper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationValueSQLHelper
    implements SQLValueHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(NotificationValueSQLHelper.class);

    public NotificationValueSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        NotificationValue
            notificationValue =
            new NotificationValue();
        notificationValue.setPlanId(new Integer(rs.getInt(1)));
        notificationValue.setPlanName(rs.getString(2));
        notificationValue.setPermitTypeId(new Integer(rs.getInt(3)));
        notificationValue.setMcmId(new Integer(rs.getInt(4)));
        notificationValue.setMcmNumber(new Integer(rs.getInt(5)));
        notificationValue.setBmpId(new Integer(rs.getInt(6)));
        notificationValue.setBmpNumber(new Integer(rs.getInt(7)));
        notificationValue.setBmpSection(rs.getString(8));
        notificationValue.setGoalId(new Integer(rs.getInt(9)));
        notificationValue.setGoalNumber(new Integer(rs.getInt(10)));
        notificationValue.setMcmOwnerFirstName(rs.getString(11));
        notificationValue.setMcmOwnerLastName(rs.getString(12));
        notificationValue.setMcmOwnerEmailAddress(rs.getString(13));
        notificationValue.setBmpOwnerFirstName(rs.getString(14));
        notificationValue.setBmpOwnerLastName(rs.getString(15));
        notificationValue.setBmpOwnerEmailAddress(rs.getString(16));
        notificationValue.setGoalOwnerFirstName(rs.getString(17));
        notificationValue.setGoalOwnerLastName(rs.getString(18));
        notificationValue.setGoalOwnerEmailAddress(rs.getString(19));
        notificationValue.setPermitTimePeriodId(new Integer(rs.getInt(20)));
        notificationValue.setPermitTimePeriodName(rs.getString(21));
        notificationValue.setGoalName(rs.getString(22));
        notificationValue.setGoalActivityFrequency(new Integer(rs.getInt(23)));
        notificationValue.setGoalDate(rs.getDate(24));
        notificationValue.setNotifyDaysInAdvance(new Integer(rs.getInt(25)));
        return notificationValue;
    }

    public String getValueSubQuery()
    {
        String
            VALUE_SUB_QUERY =
            "[PLAN].[ID], [PLAN].[NAME], [PLAN].PERMIT_TYPE_ID, "
            +
            "MCM.[ID], MCM.NUMBER, BMP.[ID], BMP.NUMBER, BMP.[SECTION], GOAL.[ID], GOAL.NUMBER,"
            +
            "MCM_USER.FIRST_NAME, MCM_USER.LAST_NAME, MCM_USER.EMAIL_ADDRESS,"
            +
            "BMP_USER.FIRST_NAME, BMP_USER.LAST_NAME, BMP_USER.EMAIL_ADDRESS,"
            +
            "GOAL_USER.FIRST_NAME, GOAL_USER.LAST_NAME, GOAL_USER.EMAIL_ADDRESS,"
            +
            "GOAL_PERMIT_TIME_PERIOD.PERMIT_TIME_PERIOD_ID, PERMIT_TIME_PERIOD.NAME,"
            +
            "GOAL.NAME, GOAL.GOAL_ACTIVITY_FREQUENCY_ID, GOAL.GOAL_DATE, GOAL.NOTIFY_DAYS_IN_ADVANCE";
        return VALUE_SUB_QUERY;
    }
}