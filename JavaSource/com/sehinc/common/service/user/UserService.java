package com.sehinc.common.service.user;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.SecurityLevelData;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.util.LabelValueNameComparator;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.StateValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class UserService
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserService.class);
    static
    String
        FIND_BY_CLIENT =
        "com.sehinc.common.value.user.UserValue.byClientId";
    static
    String
        FIND_BY_CLIENT_AND_GROUP =
        "com.sehinc.common.value.user.UserValue.byClientIdAndGroupId";
    static
    String
        FIND_BY_GROUP =
        "com.sehinc.common.value.user.UserValue.byGroupId";
    static
    String
        FIND_BY_CONTACT_ID =
        "com.sehinc.common.value.user.UserValue.byContactId";
    static
    String
        FIND_BY_STATUS_CODE =
        "com.sehinc.common.value.user.UserValue.byStatusCode";
    static
    String
        FIND_SEARCHES_BY_USER =
        "com.sehinc.erosioncontrol.db.user.EcUserSearch.byUserId";

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

    public static List getUserValueListByStatusCode(String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_STATUS_CODE,
                                              parameters);
    }

    public static List getUserValueListAllActive()
    {
        return getUserValueListByStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
    }

    public static List getUserValueListAllDeleted()
    {
        return getUserValueListByStatusCode(StatusCodeData.STATUS_CODE_DELETED);
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

    public static UserData getUserIgnoreStatus(Integer userId)
    {
        UserData
            userData =
            UserData.findById(userId);
        if (userData
            == null)
        {
            return null;
        }
        return userData;
    }

    public static UserValue getUserValue(Integer userId)
    {
        UserData
            userData =
            getUser(userId);
        if (userData
            != null)
        {
            return new UserValue(userData);
        }
        return null;
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
                return securityLevelData.getLevel()
                    .intValue();
            }
        }
        return 0;
    }

    public static List getStateValueList()
    {
        ArrayList
            stateValueList =
            new ArrayList();
        Iterator
            stateValueIterator =
            HibernateUtil.findAll(CapState.class)
                .iterator();
        while (stateValueIterator.hasNext())
        {
            StateValue
                stateValue =
                new StateValue((CapState) stateValueIterator.next());
            stateValueList.add(stateValue);
        }
        return stateValueList;
    }

    public static UserValue getUserValueByContactId(Integer contactId)
    {
        UserData
            userData =
            getUserByContactId(contactId);
        if (userData
            != null)
        {
            return new UserValue(userData);
        }
        return null;
    }

    public static UserData getUserByContactId(Integer contactId)
    {
        return UserData.findByContactId(contactId);
    }

    public static List findUserValuesByClient(ClientValue clientValue)
    {
        List
            output =
            new ArrayList();
        List
            results =
            UserData.findActiveByClient(clientValue);
        convertToValue(output,
                       results);
        return output;
    }

    public static List findByWildcard(Integer clientId, String statusCode, String wildcard)
    {
        List
            output =
            new ArrayList();
        List
            results =
            UserData.findByWildcard(clientId,
                                    statusCode,
                                    wildcard);
        convertToValue(output,
                       results);
        return output;
    }

    public static void convertToValue(List output, List results)
    {
        if (results
            != null)
        {
            Iterator
                iter =
                results.iterator();
            while (iter.hasNext())
            {
                UserData
                    user =
                    (UserData) iter.next();
                UserValue
                    userValue =
                    new UserValue(user);
                output.add(userValue);
            }
        }
    }

    public static UserData getUserByContactType(Integer clientId, CapContactType contactType)
    {
        CapContact
            contact =
            ContactService.getContactByContactType(clientId,
                                                   contactType);
        if (contact
            != null)
        {
            return UserData.findByContactId(contact.getId());
        }
        return null;
    }
}