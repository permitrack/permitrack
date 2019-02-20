package com.sehinc.erosioncontrol.value.bmpdb;

import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpDb;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import com.sehinc.erosioncontrol.value.bmp.BMPValue;

public class BMPDbValue
    implements java.io.Serializable
{
    private
    Integer
        i =
        0;
    private
    String
        s =
        "";
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
    Integer
        libraryId;
    private
    String
        libraryName;
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

    public BMPDbValue()
    {
    }

    public BMPDbValue(EcBmpDb bmp)
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
        this.libraryId =
            bmp.getLibraryDbId();
        if (this.libraryId
            != null
            || this.libraryId
               != 0)
        {
            this.libraryName =
                BmpDbService.getLibraryNameById(bmp.getLibraryDbId());
        }
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

    public Integer getLibraryId()
    {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId)
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
        libraryName =
            StringUtil.html(libraryName);
        weblink =
            StringUtil.html(weblink);
    }
}

