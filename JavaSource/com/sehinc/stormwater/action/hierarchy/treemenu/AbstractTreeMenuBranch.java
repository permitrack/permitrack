package com.sehinc.stormwater.action.hierarchy.treemenu;

public abstract class AbstractTreeMenuBranch
    implements TreeMenuBranch
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
    int
        typeId =
        0;
    private
    String
        name =
        null;
    private
    String
        url =
        null;
    private
    boolean
        expanded =
        false;
    private
    String
        icon =
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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url =
            url;
    }

    public void collapse()
    {
        expanded =
            false;
    }

    public void expand()
    {
        expanded =
            true;
    }

    public boolean isExpanded()
    {
        return expanded;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type =
            type;
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
