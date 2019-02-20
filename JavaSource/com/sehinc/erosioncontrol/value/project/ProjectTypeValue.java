package com.sehinc.erosioncontrol.value.project;

import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.project.EcProjectType;

public class ProjectTypeValue
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
        description;
    private
    Integer
        endPointTypeId;
    private
    String
        endPointTypeName;
    private
    String
        endPointTypeDescription;
    private
    Boolean
        swmp;
    private
    Integer
        extendedOnlineAccessMonths;
    private
    Integer
        monthsFromStart;

    public ProjectTypeValue()
    {
    }

    public ProjectTypeValue(EcProjectType projectType)
    {
        this.id =
            projectType.getId();
        this.name =
            projectType.getName();
        this.description =
            projectType.getDescription();
        this.endPointTypeId =
            projectType.getEndPointType()
                .getId();
        this.endPointTypeName =
            projectType.getEndPointType()
                .getName();
        this.endPointTypeDescription =
            projectType.getEndPointType()
                .getDescription();
        this.swmp =
            projectType.getSwmp();
        this.extendedOnlineAccessMonths =
            projectType.getExtendedOnlineAccessMonths();
        this.monthsFromStart =
            projectType.getMonthsFromStart();
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String v)
    {
        this.name =
            StringUtil.lt(v);
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String v)
    {
        this.description =
            StringUtil.lt(v);
    }

    public String getDescription()
    {
        return description;
    }

    public void setEndPointTypeName(String v)
    {
        this.endPointTypeName =
            v;
    }

    public String getEndPointTypeName()
    {
        return endPointTypeName;
    }

    public void setEndPointTypeId(Integer v)
    {
        this.endPointTypeId =
            v;
    }

    public Integer getEndPointTypeId()
    {
        return endPointTypeId;
    }

    public void setEndPointTypeDescription(String v)
    {
        this.endPointTypeDescription =
            v;
    }

    public String getEndPointTypeDescription()
    {
        return endPointTypeDescription;
    }

    public Boolean getSwmp()
    {
        return this.swmp;
    }

    public void setSwmp(Boolean v)
    {
        this.swmp =
            v;
    }

    public void setExtendedOnlineAccessMonths(Integer v)
    {
        this.extendedOnlineAccessMonths =
            v;
    }

    public Integer getExtendedOnlineAccessMonths()
    {
        return extendedOnlineAccessMonths;
    }

    public void setMonthsFromStart(Integer v)
    {
        this.monthsFromStart =
            v;
    }

    public Integer getMonthsFromStart()
    {
        return monthsFromStart;
    }

    public String getSwmpText()
    {
        if (swmp.booleanValue())
        {
            return "Yes";
        }
        return "No";
    }

    public void setSwmpText(String swmp)
    {
        if (swmp
            != null
            && swmp
               != ""
            && swmp.equalsIgnoreCase("true"))
        {
            this.swmp =
                new Boolean(true);
        }
        else
        {
            this.swmp =
                false;
        }
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProjectTypeValue)
        {
            ProjectTypeValue
                other =
                (ProjectTypeValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }

    public void resetProjectTypeValue()
    {
        Integer
            i =
            new Integer(0);
        String
            s =
            new String("");
        id =
            i;
        name =
            s;
        description =
            s;
        endPointTypeId =
            i;
        endPointTypeName =
            s;
        endPointTypeDescription =
            s;
        swmp =
            false;
        extendedOnlineAccessMonths =
            i;
        monthsFromStart =
            i;
    }

    public void checkForHTML()
    {
        description =
            StringUtil.html(description);
        name =
            StringUtil.html(name);
    }
}
