package com.sehinc.erosioncontrol.db.bmpdb;

import com.sehinc.common.db.user.StatusData;

public class EcBmpDb
    extends StatusData
{
    private
    Integer
        clientId;
    private
    EcBmpCategoryDb
        category;
    private
    String
        name;
    private
    String
        description;
    private
    String
        weblink;

    public EcBmpDb()
    {
    }

    public EcBmpDb(Integer id)
    {
        setId(id);
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

    public EcBmpCategoryDb getCategory()
    {
        return this.category;
    }

    public void setCategory(EcBmpCategoryDb category)
    {
        this.category =
            category;
    }

    public Integer getLibraryDbId()
    {
        if (this.category
            != null)
        {
            return category.getLibraryDbId();
        }
        else
        {
            return null;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
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

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\nname=")
            .append(name);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\nweblink=")
            .append(weblink);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
