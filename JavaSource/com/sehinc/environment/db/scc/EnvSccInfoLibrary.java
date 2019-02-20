package com.sehinc.environment.db.scc;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

public class EnvSccInfoLibrary
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_SCC_NUMBER =
        "EnvSccInfoLibrary.findBySccNumber";
    private
    String
        number;
    private
    String
        description;
    private
    String
        majIndustrialGroup;
    private
    String
        rawMaterial;
    private
    String
        emittingProcess;

    public EnvSccInfoLibrary()
    {
    }

    public EnvSccInfoLibrary(Integer id)
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

    public static EnvSccInfoLibrary findBySccNumber(String number)
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
                    number}};
        return (EnvSccInfoLibrary) HibernateUtil.findUniqueByNamedQuery(FIND_BY_SCC_NUMBER,
                                                                        parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nscc_number=")
            .append(number);
        buffer.append("\nscc_description=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
