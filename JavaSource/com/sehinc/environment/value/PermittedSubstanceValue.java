package com.sehinc.environment.value;

import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;

public class PermittedSubstanceValue
{
    private
    Integer
        permitSubstanceId;
    private
    EnvSubstanceTypeData
        substanceType;
    private
    Integer
        permitId;
    private
    Boolean
        chargeable;

    public PermittedSubstanceValue()
    {
    }

    public Integer getPermitSubstanceId()
    {
        return permitSubstanceId;
    }

    public void setPermitSubstanceId(Integer permitSubstanceId)
    {
        this.permitSubstanceId =
            permitSubstanceId;
    }

    public EnvSubstanceTypeData getSubstanceType()
    {
        return substanceType;
    }

    public void setSubstanceType(EnvSubstanceTypeData substanceType)
    {
        this.substanceType =
            substanceType;
    }

    public Integer getPermitId()
    {
        return permitId;
    }

    public void setPermitId(Integer permitId)
    {
        this.permitId =
            permitId;
    }

    public Boolean getChargeable()
    {
        return chargeable;
    }

    public void setChargeable(Boolean chargeable)
    {
        this.chargeable =
            chargeable;
    }
}