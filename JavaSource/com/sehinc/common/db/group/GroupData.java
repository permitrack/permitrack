package com.sehinc.common.db.group;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.security.SecurityManager;

import java.util.Date;
import java.util.List;

public class GroupData
    extends HibernateData
{
    public static final
    int
        SYSTEM_ADMINISTRATOR_GROUP_ID =
        1;
    public static final
    int
        CLIENT_ADMINISTRATOR_GROUP_ID =
        2;
    public static final
    int
        CLIENT_USER_GROUP_ID =
        3;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        securityLevelId;
    private
    Date
        createTimestamp;
    private
    Date
        updateTimestamp;

    public GroupData()
    {
    }

    public GroupData(Integer id)
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

    public Integer getSecurityLevelId()
    {
        return securityLevelId;
    }

    public void setSecurityLevelId(Integer securityLevelId)
    {
        this.securityLevelId =
            securityLevelId;
    }

    public Date getCreateTimestamp()
    {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp)
    {
        this.createTimestamp =
            createTimestamp;
    }

    public Date getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp)
    {
        this.updateTimestamp =
            updateTimestamp;
    }

    public static List findBySecurityLevel(Integer securityLevelId)
    {
        Object
            parameters
            [
            ] =
            {securityLevelId};
        String
            queryString =
            "select data from GroupData as data where data.securityLevelId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getListBySecurityLevelId(Integer securityLevelId, boolean excludeSystemAdmin)
    {
        String
            queryString;
        Object
            parameters
            [
            ] =
            {securityLevelId};
        if (excludeSystemAdmin)
        {
            queryString =
                "select data from GroupData as data where data.securityLevelId >= ? and data.securityLevelId <> "
                + SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL_ID;
        }
        else
        {
            queryString =
                "select data from GroupData as data where data.securityLevelId >= ?";
        }
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static GroupData findClientAdmin()
    {
        String
            admin =
            "Client Administrator";
        Object
            parameters
            [
            ] =
            {admin};
        String
            queryString =
            "select data from GroupData as data where data.name =?";
        return (GroupData) HibernateUtil.findUnique(queryString,
                                                    parameters);
    }

    public static GroupData findUser()
    {
        String
            user =
            "User";
        Object
            parameters
            [
            ] =
            {user};
        String
            queryString =
            "select data from GroupData as data where data.name =?";
        return (GroupData) HibernateUtil.findUnique(queryString,
                                                    parameters);
    }
}
