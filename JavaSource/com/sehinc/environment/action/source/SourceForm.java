package com.sehinc.environment.action.source;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SourceForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        clientName;
    private
    String
        partNumber;
    private
    String
        batchNumber;
    private
    String
        displayName;
    private
    String
        description;
    private
    String
        lbsVoc;
    private
    Integer
        lbsVocUm;
    private
    String
        lbsVocDesc;
    private
    String
        density;
    private
    Integer
        densityUm;
    private
    String
        densityDesc;
    private
    String
        lbsHaps;
    private
    Integer
        lbsHapsUm;
    private
    String
        lbsHapsDesc;
    private
    String
        pctSolidsWeight;
    private
    String
        pctSolidsVolume;
    private
    String
        infoOrigin;
    private
    Integer
        sourceTypeCd;
    private
    String
        sourceTypeDesc;
    private
    String
        statusCode;
    private
    String
        displayColorCd;
    private
    String
        displayColorDesc;
    private
    Integer
        hcFuel;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public void setClientName(String clientName)
    {
        this.clientName =
            clientName;
    }

    public String getClientName()
    {
        return clientName;
    }

    public Integer getClientId()
    {
        return clientId;
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

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName =
            displayName;
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

    public String getLbsVoc()
    {
        return lbsVoc;
    }

    public void setLbsVoc(String lbsVoc)
    {
        this.lbsVoc =
            lbsVoc;
    }

    public String getDensity()
    {
        return density;
    }

    public void setDensity(String density)
    {
        this.density =
            density;
    }

    public String getLbsHaps()
    {
        return lbsHaps;
    }

    public void setLbsHaps(String lbsHaps)
    {
        this.lbsHaps =
            lbsHaps;
    }

    public String getPctSolidsWeight()
    {
        return pctSolidsWeight;
    }

    public void setPctSolidsWeight(String pctSolidsWeight)
    {
        this.pctSolidsWeight =
            pctSolidsWeight;
    }

    public String getPctSolidsVolume()
    {
        return pctSolidsVolume;
    }

    public void setPctSolidsVolume(String pctSolidsVolume)
    {
        this.pctSolidsVolume =
            pctSolidsVolume;
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

    public void setSourceTypeCd(Integer sourceTypeCd)
    {
        this.sourceTypeCd =
            sourceTypeCd;
    }

    public Integer getSourceTypeCd()
    {
        return sourceTypeCd;
    }

    public void setSourceTypeDesc(String sourceTypeDesc)
    {
        this.sourceTypeDesc =
            sourceTypeDesc;
    }

    public String getSourceTypeDesc()
    {
        return sourceTypeDesc;
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

    public Integer getLbsVocUm()
    {
        return lbsVocUm;
    }

    public void setLbsVocUm(Integer lbsVocUm)
    {
        this.lbsVocUm =
            lbsVocUm;
    }

    public String getLbsVocDesc()
    {
        return lbsVocDesc;
    }

    public void setLbsVocDesc(String lbsVocDesc)
    {
        this.lbsVocDesc =
            lbsVocDesc;
    }

    public Integer getDensityUm()
    {
        return densityUm;
    }

    public void setDensityUm(Integer densityUm)
    {
        this.densityUm =
            densityUm;
    }

    public String getDensityDesc()
    {
        return densityDesc;
    }

    public void setDensityDesc(String densityDesc)
    {
        this.densityDesc =
            densityDesc;
    }

    public Integer getLbsHapsUm()
    {
        return lbsHapsUm;
    }

    public void setLbsHapsUm(Integer lbsHapsUm)
    {
        this.lbsHapsUm =
            lbsHapsUm;
    }

    public String getLbsHapsDesc()
    {
        return lbsHapsDesc;
    }

    public void setLbsHapsDesc(String lbsHapsDesc)
    {
        this.lbsHapsDesc =
            lbsHapsDesc;
    }

    public String getDisplayColorCd()
    {
        return displayColorCd;
    }

    public void setDisplayColorCd(String displayColorCd)
    {
        this.displayColorCd =
            displayColorCd;
    }

    public String getDisplayColorDesc()
    {
        return displayColorDesc;
    }

    public void setDisplayColorDesc(String displayColorDesc)
    {
        this.displayColorDesc =
            displayColorDesc;
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

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        clientName =
            null;
        partNumber =
            null;
        batchNumber =
            null;
        displayName =
            null;
        description =
            null;
        lbsVoc =
            null;
        density =
            null;
        lbsHaps =
            null;
        pctSolidsVolume =
            null;
        pctSolidsWeight =
            null;
        infoOrigin =
            null;
        sourceTypeCd =
            null;
        sourceTypeDesc =
            null;
        statusCode =
            null;
        lbsVocUm =
            null;
        lbsVocDesc =
            null;
        densityUm =
            null;
        densityDesc =
            null;
        lbsHapsUm =
            null;
        lbsHapsDesc =
            null;
        displayColorCd =
            null;
        displayColorDesc =
            null;
        hcFuel =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
