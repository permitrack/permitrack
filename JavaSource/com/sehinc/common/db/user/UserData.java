package com.sehinc.common.db.user;

import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.project.ProjectSearchListForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserData
    extends StatusData
    implements User
{
    private
    String
        username;
    private
    String
        password;
    private
    String
        firstName;
    private
    String
        middleName;
    private
    String
        lastName;
    private
    String
        title;
    private
    String
        department;
    private
    Integer
        addressId;
    private
    String
        emailAddress;
    private
    String
        primaryPhone;
    private
    String
        secondaryPhone;
    private
    String
        faxPhone;
    private
    Integer
        groupId;
    private
    Date
        passwordExpirationDate;
    private
    boolean
        isPasswordChangeRequired;
    private
    Date
        lastLoginDate;
    private
    Integer
        contactId;
    private static final
    String
        FIND_BY_CLIENT_ID_AND_STATUS_CODE =
        "com.sehinc.common.value.user.UserValue.byClientIdAndStatusCode";

    public UserData()
    {
    }

    public UserData(Integer id)
    {
        setId(id);
    }

    public boolean equals(Object obj)
    {
        try
        {
            if (obj
                != null)
            {
                StatusData
                    userIn =
                    (UserData) obj;
                if (getId()
                    != null
                    && getId().equals(userIn.getId()))
                {
                    return true;
                }
            }
        }
        catch (ClassCastException e)
        {
            return false;
        }
        return false;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username =
            username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password =
            password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName =
            middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName =
            lastName;
    }

    public String getName()
    {
        return getFirstName()
               + " "
               + getLastName();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title =
            title;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department =
            department;
    }

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId =
            addressId;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress =
            emailAddress;
    }

    public String getPrimaryPhone()
    {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone)
    {
        this.primaryPhone =
            primaryPhone;
    }

    public String getSecondaryPhone()
    {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone)
    {
        this.secondaryPhone =
            secondaryPhone;
    }

    public String getFaxPhone()
    {
        return faxPhone;
    }

    public void setFaxPhone(String faxPhone)
    {
        this.faxPhone =
            faxPhone;
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Integer groupId)
    {
        this.groupId =
            groupId;
    }

    public Date getPasswordExpirationDate()
    {
        return passwordExpirationDate;
    }

    public void setPasswordExpirationDate(Date passwordExpirationDate)
    {
        this.passwordExpirationDate =
            passwordExpirationDate;
    }

    public boolean isPasswordChangeRequired()
    {
        return isPasswordChangeRequired;
    }

    public void setPasswordChangeRequired(boolean passwordChangeRequired)
    {
        isPasswordChangeRequired =
            passwordChangeRequired;
    }

    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate =
            lastLoginDate;
    }

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
    }

    public static UserData findById(Integer id)
    {
        UserData
            user;
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from UserData as data where data.id =?";
        user =
            (UserData) HibernateUtil.findUnique(queryString,
                                                parameters);
        return user;
    }

    public static List findActiveByClient(ClientValue clientValue)
    {
        Object
            parameters
            [
            ] =
            {
                clientValue.getId(),
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select user from UserData as user, ClientUserData as clientUser where clientUser.clientId = ? and clientUser.userId = user.id and user.status.code = ? order by user.lastName asc, user.firstName asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static UserData findActiveById(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from UserData as data where data.id = ? and data.status.code = ?";
        return (UserData) HibernateUtil.findUnique(queryString,
                                                   parameters);
    }

    public static UserData findByUsername(String username, String password)
    {
        String
            queryString;
        if (!StringUtil.isEmpty(password))
        {
            Object
                parameters
                [
                ] =
                {
                    username,
                    username,
                    password,
                    StatusCodeData.STATUS_CODE_ACTIVE};
            queryString =
                "select data from UserData as data where (data.username = ? or (data.emailAddress = ? and data.password = ?)) and data.status.code = ?";
            return (UserData) HibernateUtil.findUnique(queryString,
                                                       parameters);
        }
        else
        {
            return findByUsername(username);
        }
    }

    public static UserData findByUsername(String username)
    {
        Object
            parameters
            [
            ] =
            {
                username,
                username,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from UserData as data where (data.username = ? or data.emailAddress = ?) and data.status.code = ?";
        return (UserData) HibernateUtil.findUnique(queryString,
                                                   parameters);
    }

    public static UserData findByUsernameAndEmail(String username, String email)
    {
        Object
            parameters
            [
            ] =
            {
                username,
                email,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from UserData as data where lower(data.username) = lower(?) and lower(data.emailAddress) = lower(?) and data.status.code = ?";
        return (UserData) HibernateUtil.findUnique(queryString,
                                                   parameters);
    }

    public static UserData findClientAdminByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                StatusCodeData.STATUS_CODE_ACTIVE,
                GroupData.CLIENT_ADMINISTRATOR_GROUP_ID};
        String
            queryString =
            "select user from UserData as user,  ClientUserData as clientUser where clientUser.clientId = ? and clientUser.userId = user.id and user.status.code = ? and user.groupId = ?";
        List
            results =
            HibernateUtil.findTop(queryString,
                                  parameters,
                                  1);
        if (results
            != null
            && results.size()
               > 0)
        {
            return (UserData) results.get(0);
        }
        return null;
    }

    public static UserData findByContactId(Integer contactId)
    {
        Object
            parameters
            [
            ] =
            {contactId};
        String
            queryString =
            "select data from UserData as data where data.contactId = ?";
        return (UserData) HibernateUtil.findUnique(queryString,
                                                   parameters);
    }

    public static List findByClientIdAndStatusCode(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_AND_STATUS_CODE,
                                              parameters);
    }

    public static UserData findLastLoginByClientIdAndStatusCode(Integer clientId, String statusCode)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                statusCode};
        String
            queryString =
            "select user from UserData as user, ClientUserData as clientUser where clientUser.clientId = ? and clientUser.userId = user.id and user.status.code = ? order by user.lastLoginDate desc";
        List
            results =
            HibernateUtil.findTop(queryString,
                                  parameters,
                                  1);
        if (results
            != null
            && results.size()
               > 0)
        {
            return (UserData) results.get(0);
        }
        return null;
    }

    public static List findByWildcard(Integer clientId, String statusCode, String wildcard)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                statusCode,
                wildcard
                + "%",
                wildcard
                + "%",
                wildcard
                + "%"};
        String
            queryString =
            "select user from UserData as user, ClientUserData as clientUser where clientUser.clientId <> ? and clientUser.userId = user.id and user.status.code = ? and (user.username LIKE ? or user.firstName LIKE ? or user.lastName LIKE ?) order by user.username";
        return HibernateUtil.findTop(queryString,
                                     parameters,
                                     20);
    }
}
