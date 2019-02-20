package com.sehinc.common.db.base;

import java.util.List;

public interface ReferenceData
{
    public int getID();

    public String getName();

    public void setID(int id);

    public void setName(String name);

    public List retrieveAll();
}

