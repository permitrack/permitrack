package com.sehinc.environment.action.process;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.environment.value.ProcessAssetValue;
import org.apache.struts.action.ActionErrors;

import java.util.List;

public class ProcessForm
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
        clientName;
    private
    String
        processNumber;
    private
    String
        name;
    private
    String
        description;
    private
    List<ProcessAssetValue>
        assetList;
    private
    Integer
        parentProcessId;
    private
    String
        parentProcessName;
    private
    String
        parentProcessNumber;

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public void setClientName(String clientName)
    {
        this.clientName =
            clientName;
    }

    public String getClientName()
    {
        return clientName;
    }

    public String getProcessNumber()
    {
        return processNumber;
    }

    public void setProcessNumber(String processNumber)
    {
        this.processNumber =
            processNumber;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public List<ProcessAssetValue> getAssetList()
    {
        return assetList;
    }

    public void setAssetList(List<ProcessAssetValue> assetList)
    {
        this.assetList =
            assetList;
    }

    public Integer getParentProcessId()
    {
        return parentProcessId;
    }

    public void setParentProcessId(Integer parentProcessId)
    {
        this.parentProcessId =
            parentProcessId;
    }

    public String getParentProcessName()
    {
        return parentProcessName;
    }

    public void setParentProcessName(String parentProcessName)
    {
        this.parentProcessName =
            parentProcessName;
    }

    public String getParentProcessNumber()
    {
        return parentProcessNumber;
    }

    public void setParentProcessNumber(String parentProcessNumber)
    {
        this.parentProcessNumber =
            parentProcessNumber;
    }

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        clientName =
            null;
        processNumber =
            null;
        name =
            null;
        description =
            null;
        assetList =
            null;
        parentProcessName =
            null;
        parentProcessNumber =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
