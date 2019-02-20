package com.sehinc.common.db.report;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class CapReportObject
    extends UserUpdatableData
{
    public static final
    Integer
        EV_ROLLING_REPORT =
        1;
    public static final
    Integer
        EV_ORG_STRUCTURE_REPORT =
        2;
    public static final
    Integer
        EV_SEMI_ANNUAL_REPORT =
        3;
    public static final
    Integer
        EV_SUBSTANCE_REPORT =
        4;
    public static final
    Integer
        EV_AIR_EM_INV_REPORT =
        5;
    private
    Integer
        id;
    private
    Integer
        moduleId;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private static final
    String
        FIND_ALL =
        "com.sehinc.common.db.report.CapReportObject.findAll";

    public CapReportObject()
    {
    }

    public CapReportObject(Integer id)
    {
        setId(id);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getModuleId()
    {
        return moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
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

    public static List findAllReports()
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {};
        return HibernateUtil.findByNamedQuery(FIND_ALL,
                                              parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nName=")
            .append(name);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
