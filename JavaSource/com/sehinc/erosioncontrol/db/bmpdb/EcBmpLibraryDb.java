package com.sehinc.erosioncontrol.db.bmpdb;

import com.sehinc.common.db.user.UserUpdatableData;

public class EcBmpLibraryDb
    extends UserUpdatableData
{
    private
    String
        name;

    public EcBmpLibraryDb()
    {
    }

    public EcBmpLibraryDb(Integer id)
    {
        setId(id);
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

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + getId());
        buffer.append("\nname="
                      + name);
        buffer.append("\n\n");
        return buffer.toString();
    }
}

