package com.sehinc.common.value.role;

@SuppressWarnings("serial")
public class RoleSecureObjectPermissionValue
    implements java.io.Serializable
{
    private
    String
        soName;
    private
    Integer
        soId;
    private
    Integer
        pId;
    private
    Integer
        sopId;
    private
    boolean
        selected;

    public RoleSecureObjectPermissionValue()
    {
    }

    public String getSoName()
    {
        return soName;
    }

    public void setSoName(String soName)
    {
        this.soName =
            soName;
    }

    public Integer getSoId()
    {
        return soId;
    }

    public void setSoId(Integer soId)
    {
        this.soId =
            soId;
    }

    public Integer getPId()
    {
        return pId;
    }

    public void setPId(Integer id)
    {
        pId =
            id;
    }

    public Integer getSopId()
    {
        return sopId;
    }

    public void setSopId(Integer sopId)
    {
        this.sopId =
            sopId;
    }

    public boolean getSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected =
            selected;
    }
}
