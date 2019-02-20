package com.sehinc.environment.action.permitasset;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class PermitAssetForm
    extends BaseValidatorForm
{
    private
    Integer
        assetId;
    private
    Integer
        permitDetailId;
    private
    String
        permitDetailName;
    private
    String
        permitDetailDesc;

    public Integer getAssetId()
    {
        return assetId;
    }

    public void setAssetId(Integer assetId)
    {
        this.assetId =
            assetId;
    }

    public Integer getPermitDetailId()
    {
        return permitDetailId;
    }

    public void setPermitDetailId(Integer permitDetailId)
    {
        this.permitDetailId =
            permitDetailId;
    }

    public String getPermitDetailName()
    {
        return permitDetailName;
    }

    public void setPermitDetailName(String permitDetailName)
    {
        this.permitDetailName =
            permitDetailName;
    }

    public String getPermitDetailDesc()
    {
        return permitDetailDesc;
    }

    public void setPermitDetailDesc(String permitDetailDesc)
    {
        this.permitDetailDesc =
            permitDetailDesc;
    }

    public void reset()
    {
        assetId =
            null;
        permitDetailId =
            null;
        permitDetailName =
            null;
        permitDetailDesc =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}