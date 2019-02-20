package com.sehinc.environment.value;

import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.process.EnvProcess;

import java.util.ArrayList;
import java.util.List;

public class ProcessValue
    implements java.io.Serializable
{
    private
    Integer
        id;
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
    List<EnvAsset>
        assetList;
    private
    List<ProcessValue>
        subProcesses;

    public ProcessValue()
    {
    }

    public ProcessValue(EnvProcess proc)
    {
        this.id =
            proc.getId();
        this.processNumber =
            proc.getProcessNumber();
        this.name =
            proc.getName();
        this.description =
            proc.getDescription();
        this.assetList =
            new ArrayList<EnvAsset>();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public List getAssetList()
    {
        return assetList;
    }

    public void setAssetList(List assetList)
    {
        this.assetList =
            assetList;
    }

    public List<ProcessValue> getSubProcesses()
    {
        return subProcesses;
    }

    public void setSubProcesses(List<ProcessValue> subProcesses)
    {
        this.subProcesses =
            subProcesses;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProcessValue)
        {
            ProcessValue
                other =
                (ProcessValue) o;
            return other.getProcessNumber()
                .equals(processNumber);
        }
        return false;
    }
}
