package com.sehinc.common.db.contact;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapContactType
    extends HibernateData
{
    private
    Integer
        moduleId;
    private
    String
        name;
    private
    Boolean
        internal;

    public CapContactType()
    {
    }

    public CapContactType(Integer id)
    {
        setId(id);
    }

    public Integer getModuleId()
    {
        return this.moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
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

    public Boolean getInternal()
    {
        return this.internal;
    }

    public void setInternal(Boolean internal)
    {
        this.internal =
            internal;
    }

    public static List findByModuleId(Integer moduleId)
    {
        Object
            parameters
            [
            ] =
            {moduleId};
        String
            queryString =
            "select data from CapContactType as data where data.moduleId =? order by data.name";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static CapContactType getMainContactType()
        throws Exception
    {
        Object
            parameters
            [
            ] =
            {
                new Integer(0),
                "MAIN CONTACT",
                new Boolean(true)};
        String
            queryString =
            "select data from CapContactType as data where data.moduleId = ? and upper(data.name) = ? and data.internal = ?";
        return (CapContactType) HibernateUtil.findUnique(queryString,
                                                         parameters);
    }

    public static CapContactType getUserContactType()
        throws Exception
    {
        Object
            parameters
            [
            ] =
            {
                new Integer(0),
                "USER",
                new Boolean(true)};
        String
            queryString =
            "select data from CapContactType as data where data.moduleId = ? and upper(data.name) = ? and data.internal = ?";
        return (CapContactType) HibernateUtil.findUnique(queryString,
                                                         parameters);
    }

    public static List findByModuleIdNoUsers(Integer moduleId)
    {
        Object
            parameters
            [
            ] =
            {moduleId};
        String
            queryString =
            "select data from CapContactType as data where data.moduleId =? and data.name <> 'user' order by data.name";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static CapContactType findSecurityGeneralType(Integer moduleId)
    {
        String
            general =
            "General";
        Object
            parameters
            [
            ] =
            {
                moduleId,
                general};
        String
            queryString =
            "select data from CapContactType as data where data.moduleId =? and data.name =?";
        return (CapContactType) HibernateUtil.findUnique(queryString,
                                                         parameters);
    }
}