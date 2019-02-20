package com.sehinc.stormwater.action.permitperiod;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PermitPeriodForm
    extends BaseValidatorForm
{
    private
    String
        permitPeriodName;
    private
    String
        permitPeriodId;
    private
    List
        permitTimePeriods;
    private
    String
        addTimePeriodName;
    private
    Date
        addStartDate;
    private
    Date
        addEndDate;
    private
    String
        deletePermitTimePeriodId;
    private
    String
        editPermitTimePeriodId;

    public List getPermitTimePeriods()
    {
        if (permitTimePeriods
            == null)
        {
            return new ArrayList();
        }
        return permitTimePeriods;
    }

    public void setPermitTimePeriods(List permitTimePeriods)
    {
        this.permitTimePeriods =
            permitTimePeriods;
    }

    public void setPermitPeriodId(String permitPeriodId)
    {
        this.permitPeriodId =
            permitPeriodId;
    }

    public String getPermitPeriodId()
    {
        return permitPeriodId;
    }

    public void setPermitPeriodName(String permitPeriodName)
    {
        this.permitPeriodName =
            permitPeriodName;
    }

    public String getPermitPeriodName()
    {
        return permitPeriodName;
    }

    public void setTimePeriodName(String timePeriodName)
    {
        this.addTimePeriodName =
            timePeriodName;
    }

    public String getTimePeriodName()
    {
        return addTimePeriodName;
    }

    public void setStartDate(Date startDate)
    {
        this.addStartDate =
            startDate;
    }

    public Date getStartDate()
    {
        return addStartDate;
    }

    public void setStartDateString(String startDate)
    {
        this.addStartDate =
            DateUtil.parseDate(startDate);
    }

    public String getStartDateString()
    {
        return DateUtil.mdyDate(addStartDate);
    }

    public void setEndDate(Date endDate)
    {
        this.addEndDate =
            endDate;
    }

    public Date getEndDate()
    {
        return addEndDate;
    }

    public void setEndDateString(String endDate)
    {
        this.addEndDate =
            DateUtil.parseDate(endDate);
    }

    public String getEndDateString()
    {
        return DateUtil.mdyDate(addEndDate);
    }

    public void setDeletePermitTimePeriodId(String deletePermitTimePeriodId)
    {
        this.deletePermitTimePeriodId =
            deletePermitTimePeriodId;
    }

    public String getDeletePermitTimePeriodId()
    {
        return deletePermitTimePeriodId;
    }

    public void setEditPermitTimePeriodId(String editPermitTimePeriodId)
    {
        this.editPermitTimePeriodId =
            editPermitTimePeriodId;
    }

    public String getEditPermitTimePeriodId()
    {
        return editPermitTimePeriodId;
    }

    public void reset()
    {
        this.permitTimePeriods =
            null;
        this.permitPeriodName =
            null;
        this.permitPeriodId =
            null;
        this.deletePermitTimePeriodId =
            null;
        this.editPermitTimePeriodId =
            null;
        this.addTimePeriodName =
            null;
        this.addStartDate =
            null;
        this.addEndDate =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
