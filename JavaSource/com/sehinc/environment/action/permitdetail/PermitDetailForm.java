package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class PermitDetailForm
    extends BaseValidatorForm
{
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
    Integer
        avgPeriodUm;
    private
    String
        avgPeriodDesc;
    private
    String
        vocLimit;
    private
    Integer
        vocLimitUm;
    private
    String
        vocLimitDesc;
    private
    String
        hapsLimit;
    private
    Integer
        hapsLimitUm;
    private
    String
        hapsLimitDesc;
    private
    String
        mmbtuLimit;
    private
    Integer
        mmbtuLimitUm;
    private
    String
        mmbtuLimitDesc;
    private
    Integer
        permitId;
    private
    Integer
        id;
    private
    String
        statusCode;

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public String getVocLimit()
    {
        return vocLimit;
    }

    public void setVocLimit(String vocLimit)
    {
        this.vocLimit =
            vocLimit;
    }

    public String getHapsLimit()
    {
        return hapsLimit;
    }

    public void setHapsLimit(String hapsLimit)
    {
        this.hapsLimit =
            hapsLimit;
    }

    public String getMmbtuLimit()
    {
        return mmbtuLimit;
    }

    public void setMmbtuLimit(String mmbtuLimit)
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

    public Integer getAvgPeriodUm()
    {
        return avgPeriodUm;
    }

    public void setAvgPeriodUm(Integer avgPeriodUm)
    {
        this.avgPeriodUm =
            avgPeriodUm;
    }

    public Integer getVocLimitUm()
    {
        return vocLimitUm;
    }

    public void setVocLimitUm(Integer vocLimitUm)
    {
        this.vocLimitUm =
            vocLimitUm;
    }

    public Integer getHapsLimitUm()
    {
        return hapsLimitUm;
    }

    public void setHapsLimitUm(Integer hapsLimitUm)
    {
        this.hapsLimitUm =
            hapsLimitUm;
    }

    public Integer getMmbtuLimitUm()
    {
        return mmbtuLimitUm;
    }

    public void setMmbtuLimitUm(Integer mmbtuLimitUm)
    {
        this.mmbtuLimitUm =
            mmbtuLimitUm;
    }

    public String getAvgPeriodDesc()
    {
        return avgPeriodDesc;
    }

    public void setAvgPeriodDesc(String avgPeriodDesc)
    {
        this.avgPeriodDesc =
            avgPeriodDesc;
    }

    public String getVocLimitDesc()
    {
        return vocLimitDesc;
    }

    public void setVocLimitDesc(String vocLimitDesc)
    {
        this.vocLimitDesc =
            vocLimitDesc;
    }

    public String getHapsLimitDesc()
    {
        return hapsLimitDesc;
    }

    public void setHapsLimitDesc(String hapsLimitDesc)
    {
        this.hapsLimitDesc =
            hapsLimitDesc;
    }

    public String getMmbtuLimitDesc()
    {
        return mmbtuLimitDesc;
    }

    public void setMmbtuLimitDesc(String mmbtuLimitDesc)
    {
        this.mmbtuLimitDesc =
            mmbtuLimitDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public void reset()
    {
        name =
            null;
        description =
            null;
        avgPeriod =
            null;
        vocLimit =
            null;
        hapsLimit =
            null;
        mmbtuLimit =
            null;
        permitId =
            null;
        avgPeriodUm =
            null;
        vocLimitUm =
            null;
        hapsLimitUm =
            null;
        mmbtuLimitUm =
            null;
        avgPeriodDesc =
            null;
        vocLimitDesc =
            null;
        hapsLimitDesc =
            null;
        mmbtuLimitDesc =
            null;
        statusCode =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
