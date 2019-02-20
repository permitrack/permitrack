package com.sehinc.common.db.user;

import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class StatusData
    extends UserUpdatableData
{
    private
    StatusCodeData
        status;

    public StatusCodeData getStatus()
    {
        return status;
    }

    public void setStatus(StatusCodeData status)
    {
        this.status =
            status;
    }

    public void setStatusCode(String statusCode)
    {
        List<CodeData>
            list =
            this.findAll(StatusCodeData.class);
        for (CodeData code : list)
        {
            if (statusCode.equals(code.getCode()))
            {
                setStatus((StatusCodeData) code);
                break;
            }
        }
    }

    public boolean isActive()
    {
        return StatusCodeData.STATUS_CODE_ACTIVE
            .equals(status.getCode());
    }
}
