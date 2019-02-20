package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.user.UserUpdatableData;

public class MCMDeleteReasonData
    extends UserUpdatableData
{
    private
    Integer
        mcmId;
    private
    String
        deleteReason;

    public MCMDeleteReasonData()
    {
    }

    public Integer getMcmId()
    {
        return mcmId;
    }

    public void setMcmId(Integer mcmId)
    {
        this.mcmId =
            mcmId;
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
