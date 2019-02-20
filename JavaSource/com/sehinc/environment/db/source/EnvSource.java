package com.sehinc.environment.db.source;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.*;
import com.sehinc.environment.db.status.StatusData;

import java.math.BigDecimal;
import java.util.List;

public class EnvSource
    extends StatusData
{
    public static
    String
        FIND_SOURCE_BY_CLIENT_ID =
        "EnvSource.sourceListByClientId";
    public static
    String
        FIND_BY_CLIENT_ID_AND_NUMBER =
        "EnvSource.sourceListbyClientIdAndNumber";
    public static
    String
        FIND_SOURCE_BY_CLIENT_ID_AND_SOURCE_ID =
        "EnvSource.sourceListByClientIdAndSourceId";
    public static
    String
        FIND_DUPLICATE =
        "EnvSource.findDuplicate";
    private
    Integer
        clientId;
    private
    String
        displayName;
    private
    String
        partNumber;
    private
    String
        batchNumber;
    private
    String
        description;
    private
    BigDecimal
        lbsVoc;
    private
    BigDecimal
        density;
    private
    BigDecimal
        lbsHaps;
    private
    BigDecimal
        pctSolidsVolume;
    private
    BigDecimal
        pctSolidsWeight;
    private
    String
        infoOrigin;
    private
    EnvSourceTypeData
        sourceType;
    private
    EnvDisplayColorData
        displayColor;
    private
    Integer
        hcFuel;
    private
    long
        version;
    private
    EnvUnitOfMeasureData
        lbsVocUm;
    private
    EnvUnitOfMeasureData
        densityUm;
    private
    EnvUnitOfMeasureData
        lbsHapsUm;

    public EnvSource()
    {
    }

    public EnvSource(Integer id)
    {
        setId(id);
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName =
            displayName;
    }

    public String getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(String partNumber)
    {
        this.partNumber =
            partNumber;
    }

    public String getBatchNumber()
    {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber)
    {
        this.batchNumber =
            batchNumber;
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

    public BigDecimal getLbsVoc()
    {
        return lbsVoc;
    }

    public String getLbsVocString()
    {
        if (lbsVoc
            == null)
        {
            return "0";
        }
        else
        {
            return lbsVoc.toString();
        }
    }

    public void setLbsVoc(BigDecimal lbsVoc)
    {
        this.lbsVoc =
            lbsVoc;
    }

    public void setLbsVocString(String lbsVoc)
    {
        if (lbsVoc
            != null
            && lbsVoc.length()
               > 0)
        {
            this.lbsVoc =
                new BigDecimal(lbsVoc);
        }
        else
        {
            this.lbsVoc =
                new BigDecimal(0);
        }
    }

    public BigDecimal getDensity()
    {
        return density;
    }

    public String getDensityString()
    {
        if (density
            == null)
        {
            return "0";
        }
        else
        {
            return density.toString();
        }
    }

    public void setDensity(BigDecimal density)
    {
        this.density =
            density;
    }

    public void setDensityString(String density)
    {
        if (density
            != null
            && density.length()
               > 0)
        {
            this.density =
                new BigDecimal(density);
        }
        else
        {
            this.density =
                new BigDecimal(0);
        }
    }

    public BigDecimal getLbsHaps()
    {
        return lbsHaps;
    }

    public String getLbsHapsString()
    {
        if (lbsHaps
            == null)
        {
            return "0";
        }
        else
        {
            return lbsHaps.toString();
        }
    }

    public void setLbsHaps(BigDecimal lbsHaps)
    {
        this.lbsHaps =
            lbsHaps;
    }

    public void setLbsHapsString(String lbsHaps)
    {
        if (lbsHaps
            != null
            && lbsHaps.length()
               > 0)
        {
            this.lbsHaps =
                new BigDecimal(lbsHaps);
        }
        else
        {
            this.lbsHaps =
                new BigDecimal(0);
        }
    }

    public BigDecimal getPctSolidsVolume()
    {
        return pctSolidsVolume;
    }

    public String getPctSolidsVolumeString()
    {
        if (pctSolidsVolume
            == null)
        {
            return "0";
        }
        else
        {
            return pctSolidsVolume.toString();
        }
    }

    public void setPctSolidsVolume(BigDecimal pctSolidsVolume)
    {
        this.pctSolidsVolume =
            pctSolidsVolume;
    }

    public void setPctSolidsVolumeString(String pctSolidsVolume)
    {
        if (pctSolidsVolume
            != null
            && pctSolidsVolume.length()
               > 0)
        {
            this.pctSolidsVolume =
                new BigDecimal(pctSolidsVolume);
        }
        else
        {
            this.pctSolidsVolume =
                new BigDecimal(0);
        }
    }

    public BigDecimal getPctSolidsWeight()
    {
        return pctSolidsWeight;
    }

    public String getPctSolidsWeightString()
    {
        if (pctSolidsWeight
            == null)
        {
            return "0";
        }
        else
        {
            return pctSolidsWeight.toString();
        }
    }

    public void setPctSolidsWeight(BigDecimal pctSolidsWeight)
    {
        this.pctSolidsWeight =
            pctSolidsWeight;
    }

    public void setPctSolidsWeightString(String pctSolidsWeight)
    {
        if (pctSolidsWeight
            != null
            && pctSolidsWeight.length()
               > 0)
        {
            this.pctSolidsWeight =
                new BigDecimal(pctSolidsWeight);
        }
        else
        {
            this.pctSolidsWeight =
                new BigDecimal(0);
        }
    }

    public void setInfoOrigin(String infoOrigin)
    {
        this.infoOrigin =
            infoOrigin;
    }

    public String getInfoOrigin()
    {
        return infoOrigin;
    }

    public void setSourceType(EnvSourceTypeData sourceType)
    {
        this.sourceType =
            sourceType;
    }

    public EnvSourceTypeData getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(Integer sourceTypeId)
    {
        List<LookupData>
            list =
            this.findAll(EnvSourceTypeData.class);
        for (LookupData code : list)
        {
            if (sourceTypeId.equals(code.getCode()))
            {
                setSourceType((EnvSourceTypeData) code);
                break;
            }
        }
    }

    public void setDisplayColor(EnvDisplayColorData displayColor)
    {
        this.displayColor =
            displayColor;
    }

    public EnvDisplayColorData getDisplayColor()
    {
        return displayColor;
    }

    public void setHcFuel(Integer hcFuel)
    {
        this.hcFuel =
            hcFuel;
    }

    public Integer getHcFuel()
    {
        return hcFuel;
    }

    public void setVersion(long version)
    {
        this.version =
            version;
    }

    public long getVersion()
    {
        return version;
    }

    public void setDisplayColor(String displayColorCode)
    {
        List<StringLookupData>
            list =
            this.findAll(EnvDisplayColorData.class);
        for (StringLookupData code : list)
        {
            if (displayColorCode.equals(code.getCode()))
            {
                setDisplayColor((EnvDisplayColorData) code);
                break;
            }
        }
    }

    public EnvUnitOfMeasureData getLbsVocUm()
    {
        return lbsVocUm;
    }

    public void setLbsVocUm(EnvUnitOfMeasureData lbsVocUm)
    {
        this.lbsVocUm =
            lbsVocUm;
    }

    public EnvUnitOfMeasureData getDensityUm()
    {
        return densityUm;
    }

    public void setDensityUm(EnvUnitOfMeasureData densityUm)
    {
        this.densityUm =
            densityUm;
    }

    public EnvUnitOfMeasureData getLbsHapsUm()
    {
        return lbsHapsUm;
    }

    public void setLbsHapsUm(EnvUnitOfMeasureData lbsHapsUm)
    {
        this.lbsHapsUm =
            lbsHapsUm;
    }

    public void setLbsVocUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setLbsVocUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setDensityUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setDensityUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setLbsHapsUmType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setLbsHapsUm((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public static List findSourceByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_SOURCE_BY_CLIENT_ID,
                                              parameters);
    }

    public static EnvSource findSourceByClientIdAndSourceId(Integer clientId, Integer id)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "id",
                    id},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvSource) HibernateUtil.findUniqueByNamedQuery(FIND_SOURCE_BY_CLIENT_ID_AND_SOURCE_ID,
                                                                parameters);
    }

    public static EnvSource findSourceByClientIdAndNumber(Integer clientId, String partNumber)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "partNumber",
                    partNumber},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvSource) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CLIENT_ID_AND_NUMBER,
                                                                parameters);
    }

    public static List findDuplicateSources(Integer clientId, String desc, String partNumber)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "description",
                    desc},
                {
                    "partNumber",
                    partNumber},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_DUPLICATE,
                                              parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\npartNumber=")
            .append(partNumber);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
