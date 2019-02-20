package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

import java.util.Date;

public class EcProjectPublicComments
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectContact.class);
    private
    Integer
        projectId;
    private
    Integer
        clientOwnerId;
    private
    String
        firstName;
    private
    String
        lastName;
    private
    String
        streetAddress;
    private
    String
        city;
    private
    String
        state;
    private
    String
        zipCode;
    private
    String
        phone;
    private
    String
        emailAddress;
    private
    String
        ipAddress;
    private
    String
        comments;
    private
    Date
        createTimestamp;

    public EcProjectPublicComments()
    {
    }

    public EcProjectPublicComments(Integer id)
    {
        setId(id);
    }

    public String getCity()
    {
        return this.city;
    }

    public Integer getClientOwnerId()
    {
        return this.clientOwnerId;
    }

    public String getComments()
    {
        return this.comments;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getIpAddress()
    {
        return this.ipAddress;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public String getState()
    {
        return this.state;
    }

    public String getStreetAddress()
    {
        return this.streetAddress;
    }

    public String getZipCode()
    {
        return this.zipCode;
    }

    public void setCity(String city)
    {
        this.city =
            city;
    }

    public void setClientOwnerId(Integer clientOwnerId)
    {
        this.clientOwnerId =
            clientOwnerId;
    }

    public void setComments(String comments)
    {
        this.comments =
            comments;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress =
            emailAddress;
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress =
            ipAddress;
    }

    public void setLastName(String lastName)
    {
        this.lastName =
            lastName;
    }

    public void setPhone(String phone)
    {
        this.phone =
            phone;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public void setState(String state)
    {
        this.state =
            state;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress =
            streetAddress;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode =
            zipCode;
    }

    public Date getCreateTimestamp()
    {
        return this.createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp)
    {
        this.createTimestamp =
            createTimestamp;
    }
}