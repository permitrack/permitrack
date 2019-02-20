package com.sehinc.stormwater.action.hierarchy.treemenu;

public interface TreeMenuLeaf
    extends java.io.Serializable
{
    public String getUrl();

    public void setUrl(String url);

    public String getID();

    public void setID(String id);

    public String getType();

    public void setType(String type);

    public int getTypeId();

    public void setTypeId(int typeId);

    public String getName();

    public void setName(String name);

    public boolean equals(TreeMenuLeaf treeLeaf);
}
