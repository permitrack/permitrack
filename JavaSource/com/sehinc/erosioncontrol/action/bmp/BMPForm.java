package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class BMPForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPForm.class);
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    Integer
        BMPCategoryId;
    private
    String
        categoryName;
    private
    String
        name;
    private
    String
        description;
    private
    String
        statusCode;
    private
    String
        weblink;
    private
    boolean
        isNewCategory;

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName(String v)
    {
        this.categoryName =
            v;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientID(Integer v)
    {
        this.clientId =
            v;
    }

    public Integer getBMPCategoryId()
    {
        return this.BMPCategoryId;
    }

    public void setBMPCategoryId(Integer v)
    {
        this.BMPCategoryId =
            v;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String v)
    {
        this.name =
            v;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String v)
    {
        this.description =
            v;
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public void setStatusCode(String v)
    {
        this.statusCode =
            v;
    }

    public String getWeblink()
    {
        return weblink;
    }

    public void setWeblink(String weblink)
    {
        this.weblink =
            weblink;
    }

    public boolean getIsNewCategory()
    {
        return isNewCategory;
    }

    public void setIsNewCatetory(boolean isNewCategory)
    {
        this.isNewCategory =
            isNewCategory;
    }

    public String getIsNewCategoryText()
    {
        if (isNewCategory)
        {
            return "true";
        }
        return "false";
    }

    public void setIsNewCategoryText(String isNewCategory)
    {
        if (isNewCategory.equalsIgnoreCase("true"))
        {
            this.isNewCategory =
                true;
        }
        else
        {
            this.isNewCategory =
                false;
        }
    }

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        BMPCategoryId =
            null;
        name =
            null;
        description =
            null;
        statusCode =
            null;
        this.categoryName =
            null;
        isNewCategory =
            false;
        weblink =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
