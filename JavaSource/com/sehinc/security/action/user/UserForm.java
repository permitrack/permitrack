package com.sehinc.security.action.user;

import com.sehinc.common.value.contact.ContactValue;
import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class UserForm
    extends BaseValidatorForm
{
    private
    Integer
        i =
        0;
    private
    Integer
        id;
    private
    String
        clientName;
    private
    Integer
        clientId;
    private
    String
        accountType;
    private
    String
        username;
    private
    String
        password;
    private
    String
        newPassword;
    private
    String
        firstName;
    private
    String
        lastName;
    private
    String
        middleName;
    private
    String
        title;
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
    String
        groupName;
    private
    Integer
        addressId;
    private
    String
        addressName1;
    private
    String
        addressLine1;
    private
    String
        addressLine2;
    private
    String
        city;
    private
    String
        state;
    private
    String
        postalCode;
    private
    String
        country;
    private
    ArrayList
        moduleAccess;
    private
    String
        statusCode;
    private
    Integer
        contactId;
    private
    Integer
        roleIdSW;
    private
    String
        roleNameSW;
    private
    Integer
        roleIdEC;
    private
    String
        roleNameEC;
    private
    Integer
        roleIdEV;
    private
    String
        roleNameEV;
    private
    Integer
        roleIdDV;
    private
    String
        roleNameDV;
    private
    boolean
        accessEC;
    private
    boolean
        accessSW;
    private
    boolean
        accessDV;
    private
    boolean
        accessEV;
    private
    String
        replacementUserId;

    public String getRoleNameEC()
    {
        return this.roleNameEC;
    }

    public void setRoleNameEC(String v)
    {
        this.roleNameEC =
            v;
    }

    public String getRoleNameEV()
    {
        return this.roleNameEV;
    }

    public void setRoleNameEV(String v)
    {
        this.roleNameEV =
            v;
    }

    public String getRoleNameSW()
    {
        return this.roleNameSW;
    }

    public void setRoleNameSW(String v)
    {
        this.roleNameSW =
            v;
    }

    public String getRoleNameDV()
    {
        return this.roleNameDV;
    }

    public void setRoleNameDV(String v)
    {
        this.roleNameDV =
            v;
    }

    public String getReplacementUserId()
    {
        return replacementUserId;
    }

    public void setReplacementUserId(String v)
    {
        this.replacementUserId =
            v;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String v)
    {
        this.clientName =
            lt(v);
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer v)
    {
        this.clientId =
            v;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String v)
    {
        this.statusCode =
            v;
    }

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer v)
    {
        this.addressId =
            v;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer v)
    {
        this.contactId =
            v;
    }

    public Integer getRoleIdEC()
    {
        if (roleIdEC
            == null)
        {
            return i;
        }
        else
        {
            return roleIdEC;
        }
    }

    public void setRoleIdEC(Integer v)
    {
        this.roleIdEC =
            v;
    }

    public Integer getRoleIdSW()
    {
        if (roleIdSW
            == null)
        {
            return i;
        }
        else
        {
            return roleIdSW;
        }
    }

    public void setRoleIdSW(Integer v)
    {
        this.roleIdSW =
            v;
    }

    public Integer getRoleIdDV()
    {
        if (roleIdDV
            == null)
        {
            return i;
        }
        else
        {
            return roleIdDV;
        }
    }

    public void setRoleIdDV(Integer v)
    {
        this.roleIdDV =
            v;
    }

    public Integer getRoleIdEV()
    {
        if (roleIdEV
            == null)
        {
            return i;
        }
        else
        {
            return roleIdEV;
        }
    }

    public void setRoleIdEV(Integer v)
    {
        this.roleIdEV =
            v;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String v)
    {
        this.accountType =
            v;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String v)
    {
        this.username =
            lt(v);
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String v)
    {
        this.password =
            v;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword =
            newPassword;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String v)
    {
        this.firstName =
            lt(v);
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String v)
    {
        this.lastName =
            lt(v);
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String v)
    {
        this.middleName =
            v;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String v)
    {
        this.title =
            lt(v);
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String v)
    {
        this.emailAddress =
            lt(v);
    }

    public String getPrimaryPhone()
    {
        return primaryPhone;
    }

    public void setPrimaryPhone(String v)
    {
        this.primaryPhone =
            lt(v);
    }

    public String getSecondaryPhone()
    {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String v)
    {
        this.secondaryPhone =
            lt(v);
    }

    public String getFaxPhone()
    {
        return faxPhone;
    }

    public void setFaxPhone(String v)
    {
        this.faxPhone =
            lt(v);
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Integer v)
    {
        this.groupId =
            v;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String v)
    {
        this.groupName =
            lt(v);
    }

    public String getAddressName1()
    {
        return addressName1;
    }

    public void setAddressName1(String v)
    {
        this.addressName1 =
            lt(v);
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String v)
    {
        this.addressLine1 =
            lt(v);
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String v)
    {
        this.addressLine2 =
            lt(v);
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String v)
    {
        this.city =
            v;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String v)
    {
        this.state =
            v;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String v)
    {
        this.postalCode =
            v;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String v)
    {
        this.country =
            v;
    }

    public String getModuleAccessAsString()
    {
        if (moduleAccess
            != null)
        {
            return moduleAccess.toString();
        }
        return "";
    }

    public Object[] getModuleAccess()
    {
        if (moduleAccess
            != null)
        {
            return moduleAccess.toArray();
        }
        return new Object[] {};
    }

    public void setModuleAccess(String[] moduleAccess)
    {
        this.moduleAccess =
            new ArrayList(Arrays.asList(moduleAccess));
    }

    public boolean hasModuleAccess(String moduleCode)
    {
        if (moduleAccess
            != null)
        {
            Iterator
                iter =
                moduleAccess.iterator();
            while (iter.hasNext())
            {
                String
                    moduleAccessCode =
                    (String) iter.next();
                if (moduleAccessCode.equalsIgnoreCase(moduleCode))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void setModuleAccess(String moduleCode)
    {
        if (moduleAccess
            == null)
        {
            moduleAccess =
                new ArrayList();
        }
        Iterator
            iter =
            moduleAccess.iterator();
        while (iter.hasNext())
        {
            String
                moduleAccessCode =
                (String) iter.next();
            if (moduleAccessCode.equalsIgnoreCase(moduleCode))
            {
                return;
            }
        }
        moduleAccess.add(moduleCode);
    }

    public String getStatusCodeText()
    {
        String
            strStatus =
            "Unknown";
        if (this.statusCode
            .equals(new Integer(1)))
        {
            strStatus =
                "Active";
        }
        if (this.statusCode
            .equals(new Integer(2)))
        {
            strStatus =
                "In-Active";
        }
        if (this.statusCode
            .equals(new Integer(3)))
        {
            strStatus =
                "Deleted";
        }
        return strStatus;
    }

    public boolean getAccessEC()
    {
        return accessEC;
    }

    public void setAccessEC(boolean accessEC)
    {
        this.accessEC =
            accessEC;
    }

    public boolean getAccessSW()
    {
        return accessSW;
    }

    public void setAccessSW(boolean accessSW)
    {
        this.accessSW =
            accessSW;
    }

    public boolean getAccessDV()
    {
        return accessDV;
    }

    public void setAccessDV(boolean accessDV)
    {
        this.accessDV =
            accessDV;
    }

    public boolean getAccessEV()
    {
        return accessEV;
    }

    public void setAccessEV(boolean accessEV)
    {
        this.accessEV =
            accessEV;
    }

    public String getAccessECText()
    {
        if (accessEC)
        {
            return "Yes";
        }
        return "No";
    }

    public String getAccessSWText()
    {
        if (accessSW)
        {
            return "Yes";
        }
        return "No";
    }

    public String getAccessDVText()
    {
        if (accessDV)
        {
            return "Yes";
        }
        return "No";
    }

    public String getAccessEVText()
    {
        if (accessEV)
        {
            return "Yes";
        }
        return "No";
    }

    public void reset()
    {
        this.clientName =
            null;
        this.clientId =
            null;
        this.accountType =
            null;
        this.username =
            null;
        this.password =
            null;
        this.firstName =
            null;
        this.lastName =
            null;
        this.middleName =
            null;
        this.title =
            null;
        this.emailAddress =
            null;
        this.primaryPhone =
            null;
        this.secondaryPhone =
            null;
        this.faxPhone =
            null;
        this.groupId =
            null;
        this.groupName =
            null;
        this.addressId =
            null;
        this.addressName1 =
            null;
        this.addressLine1 =
            null;
        this.addressLine2 =
            null;
        this.city =
            null;
        this.state =
            null;
        this.postalCode =
            null;
        this.country =
            null;
        this.replacementUserId =
            null;
        this.roleIdEC =
            null;
        this.roleNameEC =
            null;
        this.roleIdEV =
            null;
        this.roleNameEV =
            null;
        this.roleIdSW =
            null;
        this.roleNameSW =
            null;
        this.roleIdDV =
            null;
        this.roleNameDV =
            null;
        this.statusCode =
            null;
        this.moduleAccess =
            new ArrayList();
        this.accessEC =
            false;
        this.accessSW =
            false;
        this.accessDV =
            false;
        this.accessEV =
            false;
    }

    public void loadContactValues()
        throws Exception
    {
        String
            strMod =
            "com.sehinc.security.action.user.UserForm.";
        String
            strLog =
            new String(strMod
                       + "loadContactValues() ");
        try
        {
            ContactValue
                objC =
                new ContactValue(contactId);
            this.firstName =
                objC.getFirstName();
            this.lastName =
                objC.getLastName();
            this.middleName =
                objC.getMi()
                    .toString();
            this.emailAddress =
                objC.getEmail();
            this.primaryPhone =
                objC.getPrimaryPhone();
        }
        catch (Exception e)
        {
            throw new Exception(strLog
                                + "Error loading contact values based on contact id ["
                                + contactId.toString()
                                + " <br>Message:<br>"
                                + e.getMessage());
        }
    }

    public void checkForHMTL()
    {
        this.clientName =
            html(clientName);
        this.firstName =
            html(firstName);
        this.lastName =
            html(lastName);
        this.middleName =
            html(middleName);
        this.title =
            html(title);
        this.emailAddress =
            html(emailAddress);
        this.primaryPhone =
            html(primaryPhone);
        this.secondaryPhone =
            html(secondaryPhone);
        this.faxPhone =
            html(faxPhone);
        this.groupName =
            html(groupName);
        this.addressLine1 =
            html(addressLine1);
        this.addressLine2 =
            html(addressLine2);
        this.city =
            html(city);
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
