package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.struts.action.ActionErrors;

public class InspectionTemplateForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;
    private
    String
        statusCode;
    private
    InspectionTemplateBmpList
        bmps =
        new InspectionTemplateBmpList();

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public final String getStatusCode()
    {
        return statusCode;
    }

    public final void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public void setStatus(String status)
    {
    }

    public String getStatus()
    {
        // TODO - JJM - Jun 27, 2008 - can this just use the get code string method instead of the if block
        if (StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode))
        {
            return "Active";
        }
        else if (StatusCodeData.STATUS_CODE_DELETED
            .equals(statusCode))
        {
            return "Deleted";
        }
        return "Unknown";
    }

    public String getStatusCodeName()
    {
        return StatusCodeData.getStatusCodeName(this.statusCode);
    }

    public void setStatusCodeName(String statusCodeName)
    {
    }

    public InspectionTemplateBmpList getBmps()
    {
        return this.bmps;
    }

    public void setBmps(InspectionTemplateBmpList bmps)
    {
        this.bmps =
            bmps;
    }

    public void reset()
    {
        this.id =
            null;
        this.clientId =
            null;
        this.name =
            null;
        this.description =
            null;
        this.statusCode =
            null;
        this.bmps =
            new InspectionTemplateBmpList();
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
