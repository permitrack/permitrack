package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.value.SourceUsageValue;
import org.apache.struts.action.ActionErrors;

import java.util.Date;
import java.util.List;

public class SourceUsageMultiForm
    extends BaseValidatorForm
{
    private
    Integer
        assetId;
    private
    Date
        startDate;
    private
    Date
        endDate;
    private
    List<SourceUsageValue>
        sourceUsages;

    public Integer getAssetId()
    {
        return assetId;
    }

    public void setAssetId(Integer assetId)
    {
        this.assetId =
            assetId;
    }

    public List<SourceUsageValue> getSourceUsages()
    {
        return sourceUsages;
    }

    public void setSourceUsages(List<SourceUsageValue> sourceUsages)
    {
        this.sourceUsages =
            sourceUsages;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate =
            endDate;
    }

    public void setStartDateString(String startDate)
    {
        this.startDate =
            DateUtil.parseDate(startDate);
    }

    public String getStartDateString()
    {
        return DateUtil.mdyDate(startDate);
    }

    public void setEndDateString(String endDate)
    {
        this.endDate =
            DateUtil.parseDate(endDate);
    }

    public String getEndDateString()
    {
        return DateUtil.mdyDate(endDate);
    }

    public void reset()
    {
        assetId =
            null;
        sourceUsages =
            null;
        startDate =
            null;
        endDate =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}