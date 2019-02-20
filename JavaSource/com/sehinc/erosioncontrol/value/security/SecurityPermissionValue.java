package com.sehinc.erosioncontrol.value.security;

public class SecurityPermissionValue
    implements java.io.Serializable
{
    private
    boolean
        isCreate;
    private
    boolean
        isRead;
    private
    boolean
        isUpdate;
    private
    boolean
        isDelete;

    public SecurityPermissionValue()
    {
    }

    public void resetBmpValue()
    {
        this.isCreate =
            false;
        this.isRead =
            false;
        this.isUpdate =
            false;
        this.isDelete =
            false;
    }

    public void setIsCreate(boolean isCreate)
    {
        this.isCreate =
            isCreate;
    }

    public boolean getIsCreate()
    {
        return isCreate;
    }

    public void setIsRead(boolean isRead)
    {
        this.isRead =
            isRead;
    }

    public boolean getIsRead()
    {
        return isRead;
    }

    public void setIsUpdate(boolean isUpdate)
    {
        this.isUpdate =
            isUpdate;
    }

    public boolean getIsUpdate()
    {
        return isUpdate;
    }

    public void setIsDelete(boolean isDelete)
    {
        this.isDelete =
            isDelete;
    }

    public boolean getIsDelete()
    {
        return isDelete;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("isCreate="
                      + isCreate);
        buffer.append("\nisRead="
                      + isRead);
        buffer.append("\nisUpdate="
                      + isUpdate);
        buffer.append("\nisDelete="
                      + isDelete);
        return buffer.toString();
    }
}
