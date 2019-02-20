package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

public class EcProjectContactType
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectContactType.class);
    public static final
    String
        PERMIT_AUTHORITY =
        "PERMIT_AUTHORITY";
    public static final
    String
        PERMITTEE =
        "PERMITTEE";
    public static final
    String
        AUTHORIZED_INSPECTOR =
        "AUTHORIZED_INSPECTOR";
    public static final
    String
        GENERAL =
        "GENERAL";
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        sequence;
    private
    Integer
        clientId;
    private
    boolean
        isInternal;

    public EcProjectContactType()
    {
    }

    public EcProjectContactType(Integer id)
    {
        setId(id);
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
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

    public Integer getSequence()
    {
        return this.sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence =
            sequence;
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

    public boolean getIsInternal()
    {
        return this.isInternal;
    }

    public void setIsInternal(boolean isInternal)
    {
        this.isInternal =
            isInternal;
    }
}