package com.sehinc.common.service.group;

import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.SecurityLevelData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class GroupService
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
        USER_GROUP_ID =
        3;
    public static final
    String
        FIND_GROUP_BY_SECURITY_LEVEL =
        "com.sehinc.common.value.group.GroupValue.belowSecurityLevel";
    public static final
    String
        FIND_GROUP_BY_CLIENTID =
        "com.sehinc.common.value.group.GroupValue.findByClientId";

    public GroupService()
    {
    }

    public static List getGroupValueListByClient(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_GROUP_BY_CLIENTID,
                                              parameters);
    }

    public static int getGroupSecurityLevel(int groupId)
    {
        GroupData
            groupData =
            new GroupData();
        groupData.setId(groupId);
        if (!groupData.retrieveByPrimaryKeysAlternate())
        {
            return SecurityManager.ZERO_SECURITY_LEVEL;
        }
        SecurityLevelData
            securityLevelData =
            new SecurityLevelData();
        securityLevelData.setId(groupData.getSecurityLevelId());
        if (!securityLevelData.retrieveByPrimaryKeysAlternate())
        {
            return SecurityManager.ZERO_SECURITY_LEVEL;
        }
        return securityLevelData.getLevel()
            .intValue();
    }

    public static int getGroupSecurityLevelId(int groupId)
    {
        GroupData
            groupData =
            new GroupData();
        groupData.setId(groupId);
        if (!groupData.retrieveByPrimaryKeysAlternate())
        {
            return SecurityManager.ZERO_SECURITY_LEVEL;
        }
        SecurityLevelData
            securityLevelData =
            new SecurityLevelData();
        securityLevelData.setId(groupData.getSecurityLevelId());
        if (!securityLevelData.retrieveByPrimaryKeysAlternate())
        {
            return SecurityManager.ZERO_SECURITY_LEVEL;
        }
        return securityLevelData.getId();
    }

    public static int getGroupSecurityLevel(Integer groupId)
    {
        return getGroupSecurityLevel(groupId.intValue());
    }

    public static String getGroupName(Integer groupId)
    {
        return getGroupName(groupId.intValue());
    }

    public static String getGroupName(int groupId)
    {
        GroupData
            groupData =
            new GroupData();
        groupData.setId(groupId);
        groupData.retrieveByPrimaryKeysAlternate();
        return groupData.getName();
    }
}