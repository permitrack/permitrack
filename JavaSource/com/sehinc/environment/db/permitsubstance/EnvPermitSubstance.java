package com.sehinc.environment.db.permitsubstance;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.lookup.LookupData;

import java.util.List;

public class EnvPermitSubstance
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_PERMIT_ID =
        "EnvPermitSubstance.findByPermitId";
    public static
    String
        FIND_BY_PERMIT_AND_SUBSTANCE_TYPE =
        "EnvPermitSubstance.findByPermitAndSubstanceType";
    private
    Integer
        permitId;
    private
    EnvSubstanceTypeData
        substanceType;
    private
    Boolean
        chargeable;

    public EnvPermitSubstance()
    {
    }

    public EnvPermitSubstance(Integer id)
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

    public EnvSubstanceTypeData getSubstanceType()
    {
        return substanceType;
    }

    public void setSubstanceType(EnvSubstanceTypeData substanceType)
    {
        this.substanceType =
            substanceType;
    }

    public void setSubstanceType(Integer substanceTypeId)
    {
        List<LookupData>
            list =
            this.findAll(EnvSubstanceTypeData.class);
        for (LookupData code : list)
        {
            if (substanceTypeId.equals(code.getCode()))
            {
                setSubstanceType((EnvSubstanceTypeData) code);
                break;
            }
        }
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

    public static EnvPermitSubstance findByPermitAndSubstanceType(Integer permitId, Integer substanceTypeCd)
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
                    "substanceTypeCd",
                    substanceTypeCd}};
        return (EnvPermitSubstance) HibernateUtil.findUniqueByNamedQuery(FIND_BY_PERMIT_AND_SUBSTANCE_TYPE,
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
        buffer.append("\nsubstanceType=")
            .append(substanceType.getCode());
        buffer.append("\n\n");
        return buffer.toString();
    }
}