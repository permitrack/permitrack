package com.sehinc.stormwater.action.plan;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BMPCopyForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPCopyForm.class);

    private
    Integer
        bmpId;
    private
    Integer
        mcmId;
    private
    String
        operation;

    public Integer getBmpId()
    {
        return bmpId;
    }

    public Integer getMcmId()
    {
        return mcmId;
    }

    public void setBmpId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
    }

    public void setMcmId(Integer mcmId)
    {
        this.mcmId =
            mcmId;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation =
            operation;
    }

    public void reset()
    {
        this.bmpId =
            null;
        this.mcmId =
            null;
        this.operation =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
