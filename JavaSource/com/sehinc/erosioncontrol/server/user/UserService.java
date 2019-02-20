package com.sehinc.erosioncontrol.server.user;

import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.SecurityLevelData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.util.LabelValueNameComparator;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class UserService
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserService.class);
    static
    String
        FIND_BY_CLIENT =
        "com.sehinc.common.db.user.UserValue.byClientId";
    static
    String
        FIND_BY_CLIENT_AND_GROUP =
        "com.sehinc.common.db.user.UserValue.byClientIdAndGroupId";
    static
    String
        FIND_BY_GROUP =
        "com.sehinc.common.db.user.UserValue.byGroupId";

    public UserService()
    {
    }

    public static List getUserValueList(Integer groupId, Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "groupId",
                    groupId},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_AND_GROUP,
                                              parameters);
    }

    public static List getUserValueListByGroup(Integer groupId)
    {
        Object[][]
            parameters =
            {
                {
                    "groupId",
                    groupId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_GROUP,
                                              parameters);
    }

    public static UserData getUser(Integer userId)
    {
        UserData
            userData =
            UserData.findById(userId);
        if (userData
            == null
            || !userData.isActive())
        {
            return null;
        }
        return userData;
    }

    public static List getUserValueList(ClientValue clientValue)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT,
                                              parameters);
    }

    public static List getUserBeanList(ClientValue clientValue)
    {
        TreeSet
            results =
            new TreeSet(new LabelValueNameComparator());
        Iterator
            userValueList =
            getUserValueList(clientValue).iterator();
        while (userValueList.hasNext())
        {
            UserValue
                userValue =
                (UserValue) userValueList.next();
            results.add(new LabelValueBean(userValue.getLastName()
                                           + ", "
                                           + userValue.getFirstName(),
                                           userValue.getId()
                                               .toString()));
        }
        return new ArrayList(results);
    }

    public static int getSecurityLevel(Integer groupId)
    {
        LOG.debug("retrieving security level");
        GroupData
            groupData =
            new GroupData();
        groupData.setId(groupId);
        //TODO: should this information be cached?
        if (groupData.retrieveByPrimaryKeysAlternate())
        {
            SecurityLevelData
                securityLevelData =
                new SecurityLevelData();
            securityLevelData.setId(groupData.getSecurityLevelId());
            if (securityLevelData.retrieveByPrimaryKeysAlternate())
            {
                return securityLevelData.getLevel();
            }
        }
        return 0;
    }
}