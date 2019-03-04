package com.sehinc.common.value.user;

import com.sehinc.common.db.user.User;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.security.SecurityPermissionValue;
import com.sehinc.erosioncontrol.action.project.ProjectSearchListForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Set;

public class UserValue
    implements java.io.Serializable, User
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserValue.class);
    private
    Integer
        id;
    private
    String
        username;
    private
    String
        firstName;
    private
    String
        lastName;
    private
    Integer
        groupId;
    private
    String
        statusCode;
    private
    String
        emailAddress;
    private
    String
        primaryPhone;
    private
    Date
        lastLoginDate;
    private
    String
        password;
    private
    Integer
        contactId;
    private
    SecurityPermissionValue
        securityPermissionValue;
    private
    Set<ProjectSearchListForm>
        searches;

    public UserValue()
    {
    }

    public UserValue(UserData userData)
    {
        this.id =
            userData.getId();
        this.username =
            userData.getUsername();
        this.firstName =
            userData.getFirstName();
        this.lastName =
            userData.getLastName();
        this.groupId =
            userData.getGroupId();
        this.statusCode =
            userData.getStatus()
                .getCode();
        this.emailAddress =
            userData.getEmailAddress();
        this.primaryPhone =
            userData.getPrimaryPhone();
        this.lastLoginDate =
            userData.getLastLoginDate();
        this.password =
            userData.getPassword();
        this.contactId =
            userData.getContactId();
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

    public void setUsername(String username)
    {
        this.username =
            username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress =
            emailAddress;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setPrimaryPhone(String primaryPhone)
    {
        this.primaryPhone =
            primaryPhone;
    }

    public String getPrimaryPhone()
    {
        return primaryPhone;
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

    public String getLastLoginDateSimple()
    {
        return DateUtil.shortDate(this.lastLoginDate)
               + " "
               + DateUtil.shortTime(this.lastLoginDate);
    }

    public void setAccountType(String accountType)
    {
    }

    public String getAccountType()
    {
        return GroupService.getGroupName(this.getGroupId());
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName =
            lastName;
    }

    public String getLastName()
    {
        return lastName;
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

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public boolean isActive()
    {
        return StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode);
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

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
    }

    public void setSecurityPermissionValue(SecurityPermissionValue securityPermissionValue)
    {
        this.securityPermissionValue =
            securityPermissionValue;
    }

    public SecurityPermissionValue getSecurityPermissionValue()
    {
        if (securityPermissionValue
            == null)
        {
            securityPermissionValue =
                new SecurityPermissionValue();
        }
        return securityPermissionValue;
    }

    public boolean equals(Object o)
    {
        if (o instanceof UserValue)
        {
            UserValue
                other =
                (UserValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
