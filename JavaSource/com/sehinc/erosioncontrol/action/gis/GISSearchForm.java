package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class GISSearchForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISSearchForm.class);
    private
    String
        searchParcelNumber;
    private
    String[]
        parcelNumber;
    private
    String[]
        gisX;
    private
    String[]
        gisY;
    private
    Integer[]
        isDeleted;

    public String getSearchParcelNumber()
    {
        return this.searchParcelNumber;
    }

    public void setSearchParcelNumber(String searchParcelNumber)
    {
        this.searchParcelNumber =
            searchParcelNumber;
    }

    public String[] getParcelNumber()
    {
        return this.parcelNumber;
    }

    public void setParcelNumber(String[] parcelNumber)
    {
        this.parcelNumber =
            parcelNumber;
    }

    public String[] getGisX()
    {
        return this.gisX;
    }

    public void setGisX(String[] gisX)
    {
        this.gisX =
            gisX;
    }

    public String[] getGisY()
    {
        return this.gisY;
    }

    public void setGisY(String[] gisY)
    {
        this.gisY =
            gisY;
    }

    public Integer[] getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(Integer[] isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public void reset()
    {
        this.searchParcelNumber =
            null;
        this.parcelNumber =
            null;
        this.gisX =
            null;
        this.gisY =
            null;
        this.isDeleted =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
