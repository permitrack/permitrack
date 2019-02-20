package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbCatValue;
import org.apache.struts.action.ActionErrors;

import java.util.List;

public class BMPLibraryEditForm
    extends BaseValidatorForm
{
    private
    String
        submitButton;
    private
    int
        libraryId;
    private
    String
        libraryName;
    private
    List<BMPDbCatValue>
        bmpCategories;
    private
    Integer[]
        bmpDeletes =
        {};

    public String getSubmitButton()
    {
        return submitButton;
    }

    public void setSubmitButton(String submitButton)
    {
        this.submitButton =
            submitButton;
    }

    public int getLibraryId()
    {
        return libraryId;
    }

    public void setLibraryId(int libraryId)
    {
        this.libraryId =
            libraryId;
    }

    public String getLibraryName()
    {
        return libraryName;
    }

    public void setLibraryName(String libraryName)
    {
        this.libraryName =
            libraryName;
    }

    public List<BMPDbCatValue> getBmpCategories()
    {
        return bmpCategories;
    }

    public void setBmpCategories(List<BMPDbCatValue> bmpCategories)
    {
        this.bmpCategories =
            bmpCategories;
    }

    public Integer[] getBmpDeletes()
    {
        return bmpDeletes;
    }

    public void setBmpDeletes(Integer[] bmpDeletes)
    {
        this.bmpDeletes =
            bmpDeletes;
    }

    public void reset()
    {
        this.libraryId =
            0;
        this.submitButton =
            null;
        this.libraryName =
            null;
        this.bmpCategories =
            null;
        this.bmpDeletes =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
