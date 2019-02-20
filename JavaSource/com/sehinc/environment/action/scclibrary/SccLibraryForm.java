package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SccLibraryForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    String
        number;
    private
    String
        description;
    private
    String
        majIndustrialGroup;
    private
    String
        rawMaterial;
    private
    String
        emittingProcess;
    private
    Integer[]
        sccLibraryItems =
        {};

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
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

    public String getMajIndustrialGroup()
    {
        return majIndustrialGroup;
    }

    public void setMajIndustrialGroup(String majIndustrialGroup)
    {
        this.majIndustrialGroup =
            majIndustrialGroup;
    }

    public String getRawMaterial()
    {
        return rawMaterial;
    }

    public void setRawMaterial(String rawMaterial)
    {
        this.rawMaterial =
            rawMaterial;
    }

    public String getEmittingProcess()
    {
        return emittingProcess;
    }

    public void setEmittingProcess(String emittingProcess)
    {
        this.emittingProcess =
            emittingProcess;
    }

    public Integer[] getSccLibraryItems()
    {
        return this.sccLibraryItems;
    }

    public void setSccLibraryItems(Integer[] sccLibraryItems)
    {
        this.sccLibraryItems =
            sccLibraryItems;
    }

    public void reset()
    {
        id =
            null;
        number =
            null;
        description =
            null;
        majIndustrialGroup =
            null;
        rawMaterial =
            null;
        emittingProcess =
            null;
        this.sccLibraryItems =
            new Integer[] {};
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
