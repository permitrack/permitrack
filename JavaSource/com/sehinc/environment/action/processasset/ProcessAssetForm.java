package com.sehinc.environment.action.processasset;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class ProcessAssetForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        assetId;
    private
    String
        assetNumber;
    private
    String
        assetName;
    private
    Integer
        processId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getAssetId()
    {
        return assetId;
    }

    public void setAssetId(Integer assetId)
    {
        this.assetId =
            assetId;
    }

    public Integer getProcessId()
    {
        return processId;
    }

    public void setProcessId(Integer processId)
    {
        this.processId =
            processId;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public String getActiveTsString()
    {
        if (activeTs
            != null)
        {
            return DateUtil.mdyDate(activeTs);
        }
        else
        {
            return "";
        }
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public String getInactiveTsString()
    {
        if (inactiveTs
            != null)
        {
            return DateUtil.mdyDate(inactiveTs);
        }
        else
        {
            return "";
        }
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public void setActiveTsString(String activeTs)
    {
        this.activeTs =
            DateUtil.parseDate(activeTs);
    }

    public void setInactiveTsString(String inactiveTs)
    {
        this.inactiveTs =
            DateUtil.parseDate(inactiveTs);
    }

    public String getAssetNumber()
    {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber)
    {
        this.assetNumber =
            assetNumber;
    }

    public String getAssetName()
    {
        return assetName;
    }

    public void setAssetName(String assetName)
    {
        this.assetName =
            assetName;
    }

    public String getNumberAndName()
    {
        return assetNumber
               + " - "
               + assetName;
    }

    public void reset()
    {
        id =
            null;
        assetId =
            null;
        assetNumber =
            null;
        assetName =
            null;
        processId =
            null;
        activeTs =
            null;
        inactiveTs =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}

