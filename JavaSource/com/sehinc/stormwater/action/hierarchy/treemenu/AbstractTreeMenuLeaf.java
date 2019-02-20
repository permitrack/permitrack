package com.sehinc.stormwater.action.hierarchy.treemenu;

public abstract class AbstractTreeMenuLeaf
    implements TreeMenuLeaf
{
    private
    String
        id =
        null;
    private
    String
        type =
        null;
    private
    String
        url =
        null;
    private
    int
        typeId =
        0;
    private
    String
        name =
        null;

    public String getID()
    {
        return id;
    }

    public void setID(String id)
    {
        this.id =
            id;
    }

    public void setUrl(String url)
    {
        this.url =
            url;
    }

    public String getUrl()
    {
        return url;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type =
            type;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId =
            typeId;
    }

    protected void setTypeId(Integer typeId)
    {
        this.typeId =
            typeId.intValue();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public boolean equals(TreeMenuLeaf treeLeaf)
    {
        if (treeLeaf
            == null)
        {
            return false;
        }
        return (this.type
                    .equals(treeLeaf.getType())
                && this.typeId
                   == treeLeaf.getTypeId());
    }
}
