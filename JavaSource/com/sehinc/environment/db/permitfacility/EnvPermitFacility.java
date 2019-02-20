package com.sehinc.environment.db.permitfacility;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvPermitFacility
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_PERMIT_ID =
        "EnvPermitFacility.findByPermitId";
    public static
    String
        FIND_BY_PERMIT_AND_FACILITY_ID =
        "EnvPermitFacility.findByPermitAndFacilityId";
    public static
    String
        FIND_BY_FACILITY_ID =
        "EnvPermitFacility.findByFacilityId";
    private
    Integer
        permitId;
    private
    Integer
        facilityId;
    private
    String
        name;
    private
    String
        description;

    public EnvPermitFacility()
    {
    }

    public EnvPermitFacility(Integer id)
    {
        setId(id);
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

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public static List findByPermitId(Integer permitId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "permitId",
                    permitId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_PERMIT_ID,
                                              parameters);
    }

    public static List findByFacilityId(Integer facilityId)
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

    public static EnvPermitFacility findByPermitAndFacilityId(Integer permitId, Integer facilityId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "permitId",
                    permitId},
                {
                    "facilityId",
                    facilityId}};
        return (EnvPermitFacility) HibernateUtil.findUniqueByNamedQuery(FIND_BY_PERMIT_AND_FACILITY_ID,
                                                                        parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\npermitId=")
            .append(permitId);
        buffer.append("\nfacilityId=")
            .append(facilityId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
