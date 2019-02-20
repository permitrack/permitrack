package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.user.UserUpdatableData;

public class BMPDeleteReasonData
    extends UserUpdatableData
{
    private
    Integer
        bmpId;
    private
    String
        deleteReason;

    public BMPDeleteReasonData()
    {
    }

    public Integer getBmpId()
    {
        return bmpId;
    }

    public void setBmpId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
    }

    public String getDeleteReason()
    {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason)
    {
        this.deleteReason =
            deleteReason;
    }
}
