package com.sehinc.environment.db.scc;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvSccInfo
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_SCC_NUMBER_AND_CLIENT_ID =
        "EnvSccInfo.findBySccNumberAndClientId";
    public static
    String
        FIND_BY_CLIENT_ID =
        "EnvSccInfo.findByClientId";
    private
    String
        number;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    String
        majIndustrialGroup;
    private
    String
        rawMaterial;
    private
    String
        emittingProcess;
    private
    Integer
        sccLibraryId;

    public EnvSccInfo()
    {
    }

    public EnvSccInfo(Integer id)
    {
        setId(id);
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
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

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public String getMajIndustrialGroup()
    {
        return majIndustrialGroup;
    }

    public void setMajIndustrialGroup(String majIndustrialGroup)
    {
        this.majIndustrialGroup =
            majIndustrialGroup;
    }

    public String getRawMaterial()
    {
        return rawMaterial;
    }

    public void setRawMaterial(String rawMaterial)
    {
        this.rawMaterial =
            rawMaterial;
    }

    public String getEmittingProcess()
    {
        return emittingProcess;
    }

    public void setEmittingProcess(String emittingProcess)
    {
        this.emittingProcess =
            emittingProcess;
    }

    public String getNumberAndEmProc()
    {
        return this.number
               + " "
               + this.description
               + " "
               + this.emittingProcess;
    }

    public Integer getSccLibraryId()
    {
        return sccLibraryId;
    }

    public void setSccLibraryId(Integer sccLibraryId)
    {
        this.sccLibraryId =
            sccLibraryId;
    }

    public static List findByClientId(Integer clientId)
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
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public static EnvSccInfo findBySccNumberAndClientId(String number, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "number",
                    number},
                {
                    "clientId",
                    clientId}};
        return (EnvSccInfo) HibernateUtil.findUniqueByNamedQuery(FIND_BY_SCC_NUMBER_AND_CLIENT_ID,
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
        buffer.append("\nscc_number=")
            .append(number);
        buffer.append("\nscc_description=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
