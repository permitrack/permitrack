package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

public class GISForm
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
        parcelNumber;
    private
    String
        gisX;
    private
    String
        gisY;
    private
    boolean
        isUpdate =
        false;
    private
    FormFile
        importFile;
    private
    String
        delimiterText =
        "comma";

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getParcelNumber()
    {
        return this.parcelNumber;
    }

    public void setParcelNumber(String parcelNumber)
    {
        this.parcelNumber =
            parcelNumber;
    }

    public String getGisX()
    {
        return this.gisX;
    }

    public void setGisX(String gisX)
    {
        this.gisX =
            gisX;
    }

    public String getGisY()
    {
        return this.gisY;
    }

    public void setGisY(String gisY)
    {
        this.gisY =
            gisY;
    }

    public char getDelimiter()
    {
        if (delimiterText
            != null
            && delimiterText.equalsIgnoreCase("tab"))
        {
            return '\t';
        }
        return ',';
    }

    public void setDelimiter(char delimiter)
    {
    }

    public String getDelimiterText()
    {
        return this.delimiterText;
    }

    public void setDelimiterText(String delimiterText)
    {
        this.delimiterText =
            delimiterText;
    }

    public boolean getIsUpdate()
    {
        return this.isUpdate;
    }

    public void setIsUpdate(boolean isUpdate)
    {
        this.isUpdate =
            isUpdate;
    }

    public FormFile getImportFile()
    {
        return this.importFile;
    }

    public void setImportFile(FormFile importFile)
    {
        this.importFile =
            importFile;
    }

    public String getIsUpdateText()
    {
        if (isUpdate)
        {
            return "true";
        }
        return "false";
    }

    public void setIsUpdateText(String isUpdate)
    {
        if (isUpdate
            != null
            && isUpdate
               != ""
            && isUpdate.equalsIgnoreCase("true"))
        {
            this.isUpdate =
                true;
        }
        else
        {
            this.isUpdate =
                false;
        }
    }

    public void reset()
    {
        this.id =
            null;
        this.clientId =
            null;
        this.parcelNumber =
            null;
        this.gisX =
            null;
        this.gisY =
            null;
        this.delimiterText =
            "comma";
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
