package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.List;

public class BMPDBListForm
    extends BaseValidatorForm
{

    private
    int
        bmpDbCategoryId;
    private
    List
        bmpDbCategoryList;
    private
    List
        bmpDbList;

    public int getBmpDbCategoryId()
    {
        return bmpDbCategoryId;
    }

    public void setBmpDbCategoryId(int bmpDbCategoryId)
    {
        this.bmpDbCategoryId =
            bmpDbCategoryId;
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

    public List getBmpDbCategoryList()
    {
        return bmpDbCategoryList;
    }

    public void setBmpDbCategoryList(List bmpDbCategoryList)
    {
        this.bmpDbCategoryList =
            bmpDbCategoryList;
    }

    public void reset()
    {
        this.bmpDbCategoryId =
            0;
        this.bmpDbList =
            null;
        this.bmpDbCategoryList =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
