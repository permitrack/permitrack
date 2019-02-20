package com.sehinc.environment.value;

import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.assetsource.EnvAssetSource;

import java.util.Date;

public class AssetSourceValue
{
    private
    Integer
        assetSourceId;
    private
    Integer
        assetId;
    private
    Integer
        sourceId;
    private
    String
        assetName;
    private
    String
        sourceName;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;
    private
    String
        displayName;
    private
    String
        displayColor;

    public AssetSourceValue()
    {
    }

    public AssetSourceValue(EnvAssetSource assetSource)
    {
        this.assetSourceId =
            assetSource.getId();
        this.assetId =
            assetSource.getAssetId();
        this.sourceId =
            assetSource.getSourceId();
        this.activeTs =
            assetSource.getActiveTs();
        this.inactiveTs =
            assetSource.getInactiveTs();
    }

    public Integer getAssetSourceId()
    {
        return assetSourceId;
    }

    public void setAssetSourceId(Integer assetSourceId)
    {
        this.assetSourceId =
            assetSourceId;
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

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName =
            displayName;
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

    public String getAssetName()
    {
        return assetName;
    }

    public void setAssetName(String assetName)
    {
        this.assetName =
            assetName;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName =
            sourceName;
    }

    public String getDisplayColor()
    {
        if (this.displayColor
            != null)
        {
            String
                colorHashCode =
                this.displayColor;
            int
                length =
                colorHashCode.length();
            colorHashCode =
                colorHashCode.substring(1,
                                        length);
            StringBuffer
                fullPath =
                new StringBuffer();
            fullPath.append("/sehsvc/img/icons/colors/");
            fullPath.append(colorHashCode);
            fullPath.append(".gif");
            return fullPath.toString();
        }
        else
        {
            return "/sehsvc/img/icons/sehblank.gif";
        }
    }

    public void setDisplayColor(String displayColor)
    {
        this.displayColor =
            displayColor;
    }
}
