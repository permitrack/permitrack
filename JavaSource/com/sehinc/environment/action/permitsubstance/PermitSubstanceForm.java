package com.sehinc.environment.action.permitsubstance;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class PermitSubstanceForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        substanceTypeCd;
    private
    Integer
        permitId;
    private
    String
        permitName;
    private
    String
        permitDesc;
    private
    Boolean
        chargeable;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getSubstanceTypeCd()
    {
        return substanceTypeCd;
    }

    public void setSubstanceTypeCd(Integer substanceTypeCd)
    {
        this.substanceTypeCd =
            substanceTypeCd;
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

    public String getPermitName()
    {
        return permitName;
    }

    public void setPermitName(String permitName)
    {
        this.permitName =
            permitName;
    }

    public String getPermitDesc()
    {
        return permitDesc;
    }

    public void setPermitDesc(String permitDesc)
    {
        this.permitDesc =
            permitDesc;
    }

    public Boolean getChargeable()
    {
        if (this.chargeable
            == null)
        {
            return false;
        }
        return chargeable;
    }

    public void setChargeable(Boolean chargeable)
    {
        this.chargeable =
            chargeable;
    }

    public void reset()
    {
        id =
            null;
        substanceTypeCd =
            null;
        permitId =
            null;
        permitName =
            null;
        permitDesc =
            null;
        chargeable =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}