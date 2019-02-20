package com.sehinc.environment.action.assetsource;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class AssetSourceForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        assetId;
    private
    Integer
        sourceId;
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

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
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

    public void reset()
    {
        id =
            null;
        assetId =
            null;
        sourceId =
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
