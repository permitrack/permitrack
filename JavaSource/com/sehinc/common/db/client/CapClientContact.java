package com.sehinc.common.db.client;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings(value = {"unused"})
public class CapClientContact
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(CapClientContact.class);
    public static
    String
        FIND_BY_CLIENT_ID =
        "com.sehinc.common.db.client.CapClientContact.byClientId";
    public static
    String
        FIND_BY_CLIENT_ID_AND_CONTACT_ID =
        "com.sehinc.common.db.client.CapClientContact.byClientIdContactId";
    public static
    String
        FIND_BY_CLIENT_ID_AND_CONTACT_ID_AND_CONTACT_TYPE_ID =
        "com.sehinc.common.db.client.CapClientContact.byClientIdContactIdContactTypeId";
    public static
    String
        FIND_BY_CLIENT_ID_AND_CONTACT_ID_AND_MODULE_ID =
        "com.sehinc.common.db.client.CapClientContact.byClientIdContactIdModuleId";
    private
    ClientData
        client;
    private
    CapContact
        contact;
    private
    CapContactType
        contactType;

    public CapClientContact()
    {
    }

    public CapClientContact(Integer id)
    {
        setId(id);
    }

    public ClientData getClient()
    {
        return this.client;
    }

    public void setClient(ClientData client)
    {
        this.client =
            client;
    }

    public CapContact getContact()
    {
        return this.contact;
    }

    public void setContact(CapContact contact)
    {
        this.contact =
            contact;
    }

    public CapContactType getContactType()
    {
        return this.contactType;
    }

    public void setContactType(CapContactType contactType)
    {
        this.contactType =
            contactType;
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public static CapClientContact findByClientIdAndContactId(Integer clientId, Integer contactId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "contactId",
                    contactId}};
        Iterator
            cci =
            HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_AND_CONTACT_ID,
                                           parameters)
                .iterator();
        if (cci.hasNext())
        {
            CapClientContact
                ccc =
                (CapClientContact) cci.next();
            return ccc;
        }
        return null;
    }

    public static CapClientContact findByClientIdAndContactIdAndContactType(Integer clientId, Integer contactId, Integer contactTypeId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "contactId",
                    contactId},
                {
                    "contactTypeId",
                    contactTypeId}};
        Iterator
            cci =
            HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_AND_CONTACT_ID_AND_CONTACT_TYPE_ID,
                                           parameters)
                .iterator();
        if (cci.hasNext())
        {
            CapClientContact
                ccc =
                (CapClientContact) cci.next();
            return ccc;
        }
        return null;
    }

    public static List findModuleSpecificTypes(Integer clientId, Integer contactId, Integer moduleId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "contactId",
                    contactId},
                {
                    "moduleId",
                    moduleId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_AND_CONTACT_ID_AND_MODULE_ID,
                                              parameters);
    }
}