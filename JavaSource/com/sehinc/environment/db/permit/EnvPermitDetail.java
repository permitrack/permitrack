package com.sehinc.environment.db.permit;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureData;
import com.sehinc.environment.db.lookup.LookupData;
import com.sehinc.environment.db.status.StatusData;

import java.math.BigDecimal;
import java.util.List;

public class EnvPermitDetail
    extends StatusData
{
    public static
    String
        FIND_BY_PERMIT_ID =
        "EnvPermitDetail.permitDetailListByPermitId";
    public static
    String
        FIND_BY_PERMIT_ID_AND_NAME =
        "EnvPermitDetail.permitDetailByPermitIdAndName";
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        avgPeriod;
    private
    BigDecimal
        vocLimit;
    private
    BigDecimal
        hapsLimit;
    private
    BigDecimal
        mmbtuLimit;
    private
    Integer
        permitId;
    private
    EnvUnitOfMeasureData
        avgPeriodUm;
    private
    EnvUnitOfMeasureData
        vocLimitUm;
    private
    EnvUnitOfMeasureData
        hapsLimitUm;
    private
    EnvUnitOfMeasureData
        mmbtuLimitUm;

    public EnvPermitDetail()
    {
    }

    public EnvPermitDetail(Integer id)
    {
        setId(id);
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getAvgPeriod()
    {
        return avgPeriod;
    }

    public void setAvgPeriod(Integer avgPeriod)
    {
        this.avgPeriod =
            avgPeriod;
    }

    public BigDecimal getVocLimit()
    {
        return vocLimit;
    }

    public void setVocLimit(BigDecimal vocLimit)
    {
        this.vocLimit =
            vocLimit;
    }

    public BigDecimal getHapsLimit()
    {
        return hapsLimit;
    }

    public void setHapsLimit(BigDecimal hapsLimit)
    {
        this.hapsLimit =
            hapsLimit;
    }

    public BigDecimal getMmbtuLimit()
    {
        return mmbtuLimit;
    }

    public void setMmbtuLimit(BigDecimal mmbtuLimit)
    {
        this.mmbtuLimit =
            mmbtuLimit;
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

    public EnvUnitOfMeasureData getAvgPeriodUm()
    {
        return avgPeriodUm;
    }

    public void setAvgPeriodUm(EnvUnitOfMeasureData avgPeriodUm)
    {
        this.avgPeriodUm =
            avgPeriodUm;
    }

    public EnvUnitOfMeasureData getVocLimitUm()
    {
        return vocLimitUm;
    }

    public void setVocLimitUm(EnvUnitOfMeasureData vocLimitUm)
    {
        this.vocLimitUm =
            vocLimitUm;
    }

    public EnvUnitOfMeasureData getHapsLimitUm()
    {
        return hapsLimitUm;
    }

    public void setHapsLimitUm(EnvUnitOfMeasureData hapsLimitUm)
    {
        this.hapsLimitUm =
            hapsLimitUm;
    }

    public EnvUnitOfMeasureData getMmbtuLimitUm()
    {
        return mmbtuLimitUm;
    }

    public void setMmbtuLimitUm(EnvUnitOfMeasureData mmbtuLimitUm)
    {
        this.mmbtuLimitUm =
            mmbtuLimitUm;
    }

    public void setAvgPeriodUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setAvgPeriodUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setVocLimitUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setVocLimitUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setHapsLimitUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setHapsLimitUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setMmbtuLimitUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setMmbtuLimitUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
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
                    permitId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_PERMIT_ID,
                                              parameters);
    }

    public static EnvPermitDetail findByPermitIdAndName(Integer permitId, String name)
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
                    "name",
                    name},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvPermitDetail) HibernateUtil.findUniqueByNamedQuery(FIND_BY_PERMIT_ID_AND_NAME,
                                                                      parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nname=")
            .append(name);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
