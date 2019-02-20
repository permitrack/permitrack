package com.sehinc.environment.action.permitfacility;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class PermitFacilityForm
    extends BaseValidatorForm
{
    private
    Integer
        facilityId;
    private
    Integer
        permitId;
    private
    String
        permitName;
    private
    String
        permitDesc;

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
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

    public void reset()
    {
        facilityId =
            null;
        permitId =
            null;
        permitName =
            null;
        permitDesc =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}