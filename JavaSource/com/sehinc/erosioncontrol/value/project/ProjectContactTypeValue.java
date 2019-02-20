package com.sehinc.erosioncontrol.value.project;

import com.sehinc.erosioncontrol.db.project.EcProjectContactType;

public class ProjectContactTypeValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        code;
    private
    boolean
        isInternal;

    public ProjectContactTypeValue()
    {
    }

    public ProjectContactTypeValue(EcProjectContactType contactType)
    {
        this.id =
            contactType.getId();
        this.name =
            contactType.getName();
        this.code =
            contactType.getCode();
        this.isInternal =
            contactType.getIsInternal();
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
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

    public String getIsInternalText()
    {
        if (isInternal)
        {
            return "true";
        }
        return "false";
    }

    public void setIsInternalText(String isInternalText)
    {
        if (isInternalText
            != null
            && isInternalText.equalsIgnoreCase("true"))
        {
            this.isInternal =
                true;
        }
        this.isInternal =
            false;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProjectContactTypeValue)
        {
            ProjectContactTypeValue
                other =
                (ProjectContactTypeValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
