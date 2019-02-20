package com.sehinc.environment.db.facility;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvFacilityContact
    extends HibernateData
{
    public static
    String
        FIND_BY_FACILITY_ID =
        "EnvFacilityContact.findByFacilityId";
    public static
    String
        FIND_BY_FACILITY_ID_AND_ROLE_CD =
        "EnvFacilityContact.findByFacilityIdAndRoleCd";
    public static
    String
        FIND_BY_FACILITY_ID_AND_CONTACT_ID =
        "EnvFacilityContact.findByFacilityIdAndContactId";
    private
    Integer
        facilityId;
    private
    Integer
        contactId;
    private
    Integer
        roleCd;
    private
    String
        roleDesc;

    public EnvFacilityContact()
    {
    }

    public EnvFacilityContact(Integer id)
    {
        setId(id);
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

    public void setRoleDesc(String roleDesc)
    {
        this.roleDesc =
            roleDesc;
    }

    public String getRoleDesc()
    {
        return roleDesc;
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

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
    }

    public static List findAllByFacilityId(Integer facilityId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "facilityId",
                    facilityId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_FACILITY_ID,
                                              parameters);
    }

    public static EnvFacilityContact findByFacilityIdAndContactId(Integer facilityId, Integer contactId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "facilityId",
                    facilityId},
                {
                    "contactId",
                    contactId}};
        return (EnvFacilityContact) HibernateUtil.findUniqueByNamedQuery(FIND_BY_FACILITY_ID_AND_CONTACT_ID,
                                                                         parameters);
    }

    public static EnvFacilityContact findByFacilityIdAndRole(Integer facilityId, Integer roleCd)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "facilityId",
                    facilityId},
                {
                    "roleCd",
                    roleCd}};
        return (EnvFacilityContact) HibernateUtil.findUniqueByNamedQuery(FIND_BY_FACILITY_ID_AND_ROLE_CD,
                                                                         parameters);
    }
}