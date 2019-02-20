package com.sehinc.environment.db.process;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.Collection;
import java.util.List;

public class EnvProcess
    extends UserUpdatableData
{
    public static
    String
        FIND_PROCESS_BY_CLIENT_ID =
        "EnvProcess.processListByClientId";
    public static
    String
        FIND_PROCESS_BY_CLIENT_ID_AND_NUMBER =
        "EnvProcess.findProcessByClientIdAndNumber";
    public static
    String
        FIND_PROCESS_BY_PARENT_ID =
        "EnvProcess.findProcessByParentId";
    private
    Integer
        parentProcessId;
    private
    Integer
        clientId;
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
    Integer
        orderNum;
    private
    Collection<EnvProcess>
        subProcesses;
    private
    Integer
        facilityId;

    public EnvProcess()
    {
    }

    public EnvProcess(Integer id)
    {
        setId(id);
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

    public void setProcessNumber(String processNumber)
    {
        this.processNumber =
            processNumber;
    }

    public String getProcessNumber()
    {
        return processNumber;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum =
            orderNum;
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

    public Collection getSubProcesses()
    {
        return subProcesses;
    }

    public void setSubProcesses(Collection subProcesses)
    {
        this.subProcesses =
            subProcesses;
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

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
    }

    public String getNumberAndName()
    {
        return this.processNumber
               + " - "
               + this.name;
    }

    public static List findByClientIdAndFacilityId(Integer clientId, Integer facilityId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "facilityId",
                    facilityId}};
        return HibernateUtil.findByNamedQuery(FIND_PROCESS_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findByParentProcessId(Integer parentProcessId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "parentProcessId",
                    parentProcessId}};
        return HibernateUtil.findByNamedQuery(FIND_PROCESS_BY_PARENT_ID,
                                              parameters);
    }

    public static EnvProcess findByClientIdAndNumberAndFacilityId(Integer clientId, String processNumber, Integer facilityId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "processNumber",
                    processNumber},
                {
                    "facilityId",
                    facilityId}};
        return (EnvProcess) HibernateUtil.findUniqueByNamedQuery(FIND_PROCESS_BY_CLIENT_ID_AND_NUMBER,
                                                                 parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\nprocessNumber=")
            .append(processNumber);
        buffer.append("\nname=")
            .append(name);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
