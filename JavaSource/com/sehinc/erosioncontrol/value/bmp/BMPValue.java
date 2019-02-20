package com.sehinc.erosioncontrol.value.bmp;

import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

public class BMPValue
    implements java.io.Serializable
{
    private
    Integer
        i =
        new Integer(0);
    private
    String
        s =
        new String("");
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    Integer
        categoryId;
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

    public BMPValue()
    {
    }

    public BMPValue(EcBmp bmp)
    {
        this.id =
            bmp.getId();
        this.name =
            bmp.getName();
        this.clientId =
            bmp.getClientId();
        this.description =
            bmp.getDescription();
        this.statusCode =
            bmp.getStatus()
                .getCode();
        this.weblink =
            bmp.getWeblink();
        if (bmp.getCategory()
            != null)
        {
            this.categoryId =
                bmp.getCategory()
                    .getId();
            this.categoryName =
                bmp.getCategory()
                    .getName();
        }
    }

    public void resetBmpValue()
    {
        this.id =
            i;
        this.name =
            s;
        this.clientId =
            i;
        this.categoryId =
            i;
        this.description =
            s;
        this.statusCode =
            StatusCodeData.STATUS_CODE_INACTIVE;
        this.weblink =
            s;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer v)
    {
        this.clientId =
            v;
    }

    public String getCategoryName()
    {
        if (this.categoryName
            == null)
        {
            return s;
        }
        else
        {
            return this.categoryName;
        }
    }

    public void setCategoryName(String v)
    {
        if (v
            != null)
        {
            this.categoryName =
                StringUtil.lt(v);
        }
        else
        {
            this.categoryName =
                s;
        }
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer v)
    {
        this.categoryId =
            v;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String v)
    {
        this.name =
            v;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String v)
    {
        this.description =
            StringUtil.lt(v);
    }

    public final String getStatusCode()
    {
        return statusCode;
    }

    public final void setStatusCode(String v)
    {
        this.statusCode =
            v;
    }

    public boolean isActive()
    {
        return StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode);
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

    public boolean equals(Object o)
    {
        if (o instanceof BMPValue)
        {
            BMPValue
                other =
                (BMPValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }

    public void checkForHTML()
    {
        name =
            StringUtil.html(name);
        description =
            StringUtil.html(description);
        categoryName =
            StringUtil.html(categoryName);
        weblink =
            StringUtil.html(weblink);
    }
}
