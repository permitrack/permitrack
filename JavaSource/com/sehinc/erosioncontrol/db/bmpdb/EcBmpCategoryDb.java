package com.sehinc.erosioncontrol.db.bmpdb;

import com.sehinc.common.db.user.StatusData;

public class EcBmpCategoryDb
    extends StatusData
{
    private
    Integer
        clientId;
    private
    String
        name;
    private
    Integer
        libraryDbId;

    public EcBmpCategoryDb()
    {
    }

    public EcBmpCategoryDb(Integer id)
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

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public Integer getLibraryDbId()
    {
        return libraryDbId;
    }

    public void setLibraryDbId(Integer libraryDbId)
    {
        this.libraryDbId =
            libraryDbId;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + getId());
        buffer.append("\nclientId="
                      + clientId);
        buffer.append("\nlibraryDbId="
                      + libraryDbId);
        buffer.append("\nname="
                      + name);
        buffer.append("\nstatus="
                      + this.getStatus()
            .getDescription());
        buffer.append("\n\n");
        return buffer.toString();
    }
}
