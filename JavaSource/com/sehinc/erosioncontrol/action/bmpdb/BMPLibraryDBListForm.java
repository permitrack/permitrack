package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.List;

public class BMPLibraryDBListForm
    extends BaseValidatorForm
{
    private
    int
        bmpDbLibraryId;
    private
    List
        bmpDbLibraryList;
    private
    String
        bmpDbLibraryName;
    private
    int
        bmpDbCategoryId;
    private
    List
        bmpDbList;
    private
    String
        submitButton;

    public String getSubmitButton()
    {
        return submitButton;
    }

    public void setSubmitButton(String submitButton)
    {
        this.submitButton =
            submitButton;
    }

    public String getBmpDbLibraryName()
    {
        return bmpDbLibraryName;
    }

    public void setBmpDbLibraryName(String bmpDbLibraryName)
    {
        this.bmpDbLibraryName =
            bmpDbLibraryName;
    }

    public int getBmpDbCategoryId()
    {
        return bmpDbCategoryId;
    }

    public void setBmpDbCategoryId(int bmpDbCategoryId)
    {
        this.bmpDbCategoryId =
            bmpDbCategoryId;
    }

    public int getBmpDbLibraryId()
    {
        return bmpDbLibraryId;
    }

    public void setBmpDbLibraryId(int bmpDbLibraryId)
    {
        this.bmpDbLibraryId =
            bmpDbLibraryId;
    }

    public List getBmpDbLibraryList()
    {
        return bmpDbLibraryList;
    }

    public void setBmpDbLibraryList(List bmpDbLibraryList)
    {
        this.bmpDbLibraryList =
            bmpDbLibraryList;
    }

    public List getBmpDbList()
    {
        return bmpDbList;
    }

    public void setBmpDbList(List bmpDbList)
    {
        this.bmpDbList =
            bmpDbList;
    }

    public void reset()
    {
        this.bmpDbCategoryId =
            0;
        this.bmpDbLibraryId =
            0;
        this.bmpDbList =
            null;
        this.bmpDbLibraryList =
            null;
        this.submitButton =
            null;
        this.bmpDbLibraryName =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
