package com.sehinc.environment.action.facilitycontact;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class FacilityContactForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        facilityId;
    private
    String
        facilityName;
    private
    Integer
        contactId;
    private
    String
        contactFullName;
    private
    Integer
        roleCd;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
    }

    public String getFacilityName()
    {
        return facilityName;
    }

    public void setFacilityName(String facilityName)
    {
        this.facilityName =
            facilityName;
    }

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
    }

    public String getContactFullName()
    {
        return contactFullName;
    }

    public void setContactFullName(String contactFullName)
    {
        this.contactFullName =
            contactFullName;
    }

    public void setRoleCd(Integer roleCd)
    {
        this.roleCd =
            roleCd;
    }

    public Integer getRoleCd()
    {
        return roleCd;
    }

    public void reset()
    {
        id =
            null;
        facilityId =
            null;
        facilityName =
            null;
        contactId =
            null;
        contactFullName =
            null;
        roleCd =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}