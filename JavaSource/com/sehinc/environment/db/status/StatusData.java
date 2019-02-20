package com.sehinc.environment.db.status;

import com.sehinc.common.db.user.UserUpdatableData;
import com.sehinc.environment.db.code.CodeData;
import com.sehinc.environment.db.code.EnvStatusCodeData;

import java.util.List;

public class StatusData
    extends UserUpdatableData
{
    private
    EnvStatusCodeData
        status;

    public EnvStatusCodeData getStatus()
    {
        return status;
    }

    public void setStatus(EnvStatusCodeData status)
    {
        this.status =
            status;
    }

    public void setStatusCode(String statusCode)
    {
        List<CodeData>
            list =
            this.findAll(EnvStatusCodeData.class);
        for (CodeData code : list)
        {
            if (statusCode.equals(code.getCode()))
            {
                setStatus((EnvStatusCodeData) code);
                break;
            }
        }
    }

    public boolean isActive()
    {
        return EnvStatusCodeData.STATUS_CODE_ACTIVE
            .equals(status.getCode());
    }
}