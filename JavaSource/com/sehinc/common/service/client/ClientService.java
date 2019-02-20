package com.sehinc.common.service.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.*;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.*;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.contact.ContactValue;
import com.sehinc.common.value.contact.ContactValueComparator;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.client.ClientDVForm;
import com.sehinc.security.action.client.ClientECForm;
import com.sehinc.security.action.client.ClientForm;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import java.util.*;

public class ClientService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientService.class);

    public ClientService()
    {
    }

    public static List getClients(UserValue userValue)
    {
        return getClients(userValue.getId());
    }

    public static List getClients(int userId)
    {
        Object
            parameters
            [
            ] =
            {
                userId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client, com.sehinc.common.db.client.ClientUserData as clientUser where client.id = clientUser.clientId and clientUser.userId = ? and client.status.code = ? order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getClientsByUserAndModule(UserValue userValue, String module)
    {
        Object
            parameters
            [
            ] =
            {
                userValue.getId(),
                CapModule.findByCode(module)
                    .getId(),
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client"
            + ", com.sehinc.common.db.client.ClientUserData as clientUser"
            + ", com.sehinc.common.db.client.ClientModule as clientModule"
            + " where client.id = clientUser.clientId"
            + " and client.id = clientModule.clientId"
            + " and clientUser.userId = ? and clientModule.moduleId = ? and client.status.code = ? order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static ClientData getClient(ClientValue clientValue)
    {
        return getActiveClientById(clientValue.getId());
    }

    public static ClientData getActiveClientById(Integer clientId)
    {
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientId);
        if (clientData.load())
        {
            // todo
            if (clientData.isActive())
            {
                return clientData;
            }
        }
        return null;
    }

    public static List getActiveClientsByModule(CapModule module)
    {
        Object
            parameters
            [
            ] =
            {
                module.getId(),
                0,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client, com.sehinc.common.db.client.ClientModule as clientModule where client.id = clientModule.clientId and clientModule.moduleId = ? and client.id <> ? and client.status.code = ? order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getActiveClientsByModuleAndType(CapModule module, Integer clientType)
    {
        Object
            parameters
            [
            ];
        if (clientType
            > 0)
        {
            parameters =
                new Object[] {
                    module.getId(),
                    0,
                    StatusCodeData.STATUS_CODE_ACTIVE,
                    clientType};
        }
        else
        {
            parameters =
                new Object[] {
                    module.getId(),
                    0,
                    StatusCodeData.STATUS_CODE_ACTIVE};
        }
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client, "
            +
            "com.sehinc.common.db.client.ClientModule as clientModule"
            + (clientType
               == 0
                   ? ""
                   : ", com.sehinc.common.db.client.CapClientType as clientType ")
            +
            "where client.id = clientModule.clientId"
            +
            " and clientModule.moduleId = ? and client.id <> ? and client.status.code = ?"
            + (clientType
               == 0
                   ? ""
                   : " and client.id = clientType.clientId")
            + (clientType
               == 0
                   ? " and client.id not in (select client_id from cap_client_type)"
                   : " and clientType.clientTypeId = ?")
            + " order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getActiveSecondaryClients(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                StatusCodeData.STATUS_CODE_ACTIVE,
                clientId};
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client "
            +
            ",com.sehinc.erosioncontrol.db.client.EcClientRelationship as clientRelationship "
            +
            "where client.id = clientRelationship.relatedClientId "
            +
            "and client.status.code = ? "
            +
            "and clientRelationship.clientId = ? order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getPrimaryClientsFromSecondaryClient(Integer secondaryClientId)
    {
        Object
            parameters
            [
            ] =
            {
                secondaryClientId};
        String
            queryString =
            "select client from com.sehinc.common.db.client.ClientData as client "
            +
            ",com.sehinc.erosioncontrol.db.client.EcClientRelationship as clientRelationship "
            +
            "where client.id = clientRelationship.clientId "
            +
            "and clientRelationship.relatedClientId = ? order by client.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List getClientContactValueList(ClientValue clientValue)
    {
        TreeSet
            clientContactValueSet =
            new TreeSet(new ContactValueComparator());
        Iterator
            clientContactValueIterator =
            CapClientContact.findByClientId(clientValue.getId())
                .iterator();
        while (clientContactValueIterator.hasNext())
        {
            CapClientContact
                clientContact =
                (CapClientContact) clientContactValueIterator.next();
            ContactValue
                contactValue =
                new ContactValue(clientContact.getContact());
            if (contactValue.getStatusCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE))
            {
                if (contactValue.getOrgRefClientId()
                    != null
                    && contactValue.getOrgRefClientId()
                           .intValue()
                       > 0)
                {
                    CapContactOrganization
                        contactOrg =
                        CapContactOrganization.findByClientId(contactValue.getOrgRefClientId());
                    if (contactOrg
                        != null)
                    {
                        contactValue.setOrganizationName(contactOrg.getName());
                    }
                }
                clientContactValueSet.add(contactValue);
            }
        }
        return new ArrayList(clientContactValueSet);
    }

    public static String getNewClientShortName(String clientName)
    {
        int
            i =
            1;
        boolean
            found =
            false;
        String
            theName =
            StringUtil.stripNonAlphaNumericCharactersExceptSpaces(clientName);
        while (!found)
        {
            Object
                parameters
                [
                ] =
                {theName};
            ClientData
                clientData =
                (ClientData) HibernateUtil.findUnique("select data from com.sehinc.common.db.client.ClientData as data where data.shortName = ?",
                                                      parameters);
            if (clientData
                == null)
            {
                found =
                    true;
            }
            else
            {
                if (theName.length()
                    == 50
                    && i
                       > 9)
                {
                    theName =
                        StringUtil.replaceAt(theName,
                                             theName.length()
                                             - 2,
                                             new Integer(i).toString());
                }
                else
                {
                    theName =
                        StringUtil.replaceAt(theName,
                                             theName.length()
                                             - 1,
                                             new Integer(i).toString());
                }
            }
            i++;
        }
        return theName;
    }

    public static AddressData getClientAddress(Integer clientId)
    {
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientId);
        if (clientData.load())
        {
            if (clientData.isActive()
                && clientData.getAddressId()
                   != null)
            {
                AddressData
                    address =
                    new AddressData(clientData.getAddressId());
                if (address.load())
                {
                    return address;
                }
            }
        }
        return null;
    }

    public static Integer saveNewClient(ClientForm objNewClient, UserValue objUser)
        throws Exception
    {
        Integer
            intNewClientId;
        Integer
            intNewClientAddressId;
        ClientData
            objNewClientData;
        Integer
            intContactId;
        Integer
            intCO;
        AddressData
            objNewAddressData;
        AddressData
            objContactAddress =
            new AddressData();
        LOG.debug("saveNewClient(): in method");
        try
        {
            objNewClientData =
                objNewClient.getClientData();
            objNewClientData.setId(null);
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String("Unable to get client data from the client form: "
                           + e.getMessage());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        try
        {
            objNewAddressData =
                objNewClient.getAddressData();
            objNewAddressData.setId(null);
            intNewClientAddressId =
                objNewAddressData.save(objUser);
            objNewClientData.setAddressId(intNewClientAddressId);
            objNewClientData.setContactId(null);
            objNewClientData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            ClientAdminSettingsData
                settings =
                new ClientAdminSettingsData();
            settings.setInspectionOverdueNotificationEnabled(false);
            settings.setSecondInspectionOverdueNotificationEnabled(false);
            settings.setProjectStatusEmailsEnabled(false);
            objNewClientData.setClientAdminSettings(settings);
            intNewClientId =
                objNewClientData.save(objUser);
            LOG.debug("New client Id is "
                      + intNewClientId.toString());
            objNewClient.setId(intNewClientId);
            CapClientType
                newClientType =
                new CapClientType();
            newClientType.setClientId(intNewClientId);
            newClientType.setClientTypeId(CommonConstants.PRIMARY_CLIENT);
            newClientType.insert();
        }
        catch (Exception s)
        {
            String
                strMessage =
                "Unable to save new client to the database: "
                + s.getMessage();
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        List
            moduleList =
            CapModule.findAll();
        Iterator
            mi =
            moduleList.iterator();
        while (mi.hasNext())
        {
            CapModule
                module =
                (CapModule) mi.next();
            List
                roleList =
                CapRole.findByModule(module.getId(),
                                     intNewClientId);
            Iterator
                ri =
                roleList.iterator();
            if (!ri.hasNext())
            {
                LOG.debug("saveNewClient(): Creating new role for new client, module: "
                          + module.getId());
                CapRole
                    capRole =
                    new CapRole();
                capRole.setClientId(intNewClientId);
                capRole.setCode("DEFAULT");
                capRole.setName("Default Role");
                capRole.setDescription("Empty Default User Role");
                capRole.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                capRole.setModuleId(module.getId());
                capRole.setUpdateUserId(new Integer(0));
                capRole.setCreateTimestamp(new Date());
                capRole.setUpdateTimestamp(new Date());
                Integer
                    roleId =
                    capRole.insert(objUser);
                List
                    objectList =
                    CapSecureObject.findByModuleId(module.getId());
                Iterator
                    oi =
                    objectList.iterator();
                LOG.debug("saveNewClient(): Role inserted.  Getting SecureObject for module: "
                          + module.getId());
                while (oi.hasNext())
                {
                    CapSecureObject
                        sOb =
                        (CapSecureObject) oi.next();
                    LOG.debug("saveNewClient(): Inserting new SecureObjectPermission, for object: "
                              + sOb.getId());
                    CapPermission
                        permission =
                        CapPermission.findByCode(CapPermission.READ_PERMISSION);
                    CapRoleSecureObjectPermission
                        objectPermission =
                        new CapRoleSecureObjectPermission();
                    objectPermission.setPermissionId(permission.getId());
                    objectPermission.setRoleId(roleId);
                    objectPermission.setSecureObjectId(sOb.getId());
                    Integer
                        permId =
                        objectPermission.insert();
                    LOG.debug("saveNewClient(): SecureObjectPermission inserted, new id: "
                              + permId);
                }
            }
        }
        try
        {
            CapContactOrganization
                objCCO =
                new CapContactOrganization();
            objCCO.setId(null);
            objCCO.setAddress(objNewAddressData);
            objCCO.setClientId(intNewClientId);
            objCCO.setRefClientId(intNewClientId);
            objCCO.setIsClient(new Boolean(true));
            objCCO.setName(objNewClient.getName());
            objCCO.setStatus(objNewClientData.getStatus());
            intCO =
                objCCO.insert(objUser);
        }
        catch (Exception cco)
        {
            String
                strMessage =
                "Error saving the new client to the contact organization table: "
                + cco.getMessage();
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        try
        {
            saveClientModules(objNewClient);
        }
        catch (Exception m)
        {
            String
                strMessage =
                "Unable to get save client modules to database: "
                + m.getMessage();
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        String
            contactName =
            objNewClient.getContactFirstName()
            + " "
            + objNewClient.getContactLastName();
        String
            contactPhone =
            objNewClient.getContactPrimaryPhone();
        try
        {
            objContactAddress.setId(null);
            objContactAddress.setLine1(objNewClient.getContactAddress());
            objContactAddress.setLine2(objNewClient.getContactAddress2());
            objContactAddress.setCity(objNewClient.getContactCity());
            objContactAddress.setState(objNewClient.getContactState());
            objContactAddress.setPostalCode(objNewClient.getContactZip());
            objContactAddress.save(objUser);
            CapContact
                capContact =
                new CapContact();
            capContact.setId(null);
            capContact.setAddressData(objContactAddress);
            capContact.setAddress(objNewClient.getContactAddress());
            capContact.setAddress2(objNewClient.getContactAddress2());
            capContact.setCity(objNewClient.getContactCity());
            capContact.setStateCode(objNewClient.getContactState());
            capContact.setZip(objNewClient.getContactZip());
            capContact.setEmail(objNewClient.getContactEMail());
            capContact.setFirstName(objNewClient.getContactFirstName());
            capContact.setLastName(objNewClient.getContactLastName());
            capContact.setTitle(objNewClient.getContactTitle());
            capContact.setPrimaryPhone(objNewClient.getContactPrimaryPhone());
            capContact.setSecondaryPhone(objNewClient.getContactSecondaryPhone());
            capContact.setFaxPhone(objNewClient.getContactFaxPhone());
            capContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            CapContactOrganization
                createdOrg =
                new CapContactOrganization(intCO);
            createdOrg.load();
            capContact.setOrganization(createdOrg);
            capContact.setOrganizationName(createdOrg.getName());
            intContactId =
                capContact.insert(objUser);
            // Create new cap contact type as well, type of "main contact"
            CapContactType
                mainContact =
                CapContactType.getMainContactType();
            CapClientContact
                capClientContact =
                new CapClientContact();
            capClientContact.setId(null);
            capClientContact.setClient(objNewClientData);
            capClientContact.setContact(capContact);
            capClientContact.setContactType(mainContact);
            capClientContact.insert();
        }
        catch (Exception ii)
        {
            String
                strMessage =
                new String("Error saving main contact for client: "
                           + intNewClientId.toString()
                           + " "
                           + ii.getMessage());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        try
        {
            ClientData
                objC1 =
                new ClientData();
            objC1.setId(intNewClientId);
            objC1.load();
            objC1.setContactId(intContactId);
            objC1.setContactName(contactName);
            objC1.setContactPhone(contactPhone);
            objC1.update(objUser);
        }
        catch (Exception zz)
        {
            String
                strMessage =
                "Error updating the client with the contact id of the newly created main contact.  ClientId: "
                + intNewClientId.toString()
                + " Contact Id: "
                + intContactId.toString()
                + zz.getMessage();
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return intNewClientId;
    }

    public static boolean clientExists(Integer intClientId)
        throws Exception
    {
        boolean
            blnClientExists =
            false;
        LOG.debug("clientExists(): in method");
        try
        {
            if (intClientId
                != null)
            {
                List
                    clientDataList =
                    ClientData.findById(intClientId);
                Iterator
                    cdi =
                    clientDataList.iterator();
                if (cdi.hasNext())
                {
                    blnClientExists =
                        true;
                }
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String("clientExists(): "
                           + e.getMessage());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return blnClientExists;
    }

    public static ClientForm getClientFormById(ClientForm clientForm, Integer intClientId, UserValue objUser)
        throws Exception
    {
        String
            strLog =
            "getClientFormById(): ";
        LOG.debug(strLog
                  + "in method");
        ClientData
            clientData =
            new ClientData(intClientId);
        clientData.load();
        if (clientData.getName()
            == null)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.load.client.failed"));
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        clientForm.setClientData(clientData);
        AddressData
            addressData =
            new AddressData(clientForm.getAddressId());
        if (!addressData.load())
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.load.address.failed"));
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        clientForm.setClientAddressData(addressData);
        // get contact with contact id if possible
        //   if loads, populate client form with relevant contact info
        //   else, populate with blank info.
        Integer
            contactId;
        String
            contactFirstName;
        String
            contactLastName;
        String
            contactTitle;
        Integer
            contactAddressId;
        String
            contactAddress;
        String
            contactAddress2;
        String
            contactCity;
        String
            contactState;
        String
            contactZip;
        String
            contactPrimaryPhone;
        String
            contactSecondaryPhone;
        String
            contactFaxPhone;
        String
            contactEmail;
        Integer
            contactOrgId;
        boolean
            noContact =
            false;
        CapContact
            capContact =
            new CapContact();
        if (clientData.getContactId()
            == null)
        {
            noContact =
                true;
        }
        else
        {
            capContact =
                new CapContact(clientData.getContactId());
            if (!capContact.load())
            {
                noContact =
                    true;
            }
        }
        if (noContact)
        {
            contactId =
                new Integer(0);
            contactFirstName =
                "";
            contactLastName =
                "";
            contactTitle =
                "";
            contactAddressId =
                new Integer(0);
            contactAddress =
                "";
            contactAddress2 =
                "";
            contactCity =
                "";
            contactState =
                "";
            contactZip =
                "";
            contactPrimaryPhone =
                "";
            contactSecondaryPhone =
                "";
            contactFaxPhone =
                "";
            contactEmail =
                "";
            contactOrgId =
                new Integer(0);
        }
        else
        {
            contactId =
                capContact.getId();
            contactFirstName =
                capContact.getFirstName();
            contactLastName =
                capContact.getLastName();
            contactTitle =
                capContact.getTitle();
            contactAddressId =
                capContact.getAddressData()
                    .getId();
            contactAddress =
                capContact.getAddress();
            contactAddress2 =
                capContact.getAddress2();
            contactCity =
                capContact.getCity();
            contactState =
                capContact.getStateCode();
            contactZip =
                capContact.getZip();
            contactPrimaryPhone =
                capContact.getPrimaryPhone();
            contactSecondaryPhone =
                capContact.getSecondaryPhone();
            contactFaxPhone =
                capContact.getFaxPhone();
            contactEmail =
                capContact.getEmail();
            contactOrgId =
                capContact.getOrganization()
                    .getId();
        }
        clientForm.setContactId(contactId);
        clientForm.setContactFirstName(contactFirstName);
        clientForm.setContactLastName(contactLastName);
        clientForm.setContactTitle(contactTitle);
        clientForm.setContactAddressId(contactAddressId);
        clientForm.setContactAddress(contactAddress);
        clientForm.setContactAddress2(contactAddress2);
        clientForm.setContactCity(contactCity);
        clientForm.setContactState(contactState);
        clientForm.setContactZip(contactZip);
        clientForm.setContactPrimaryPhone(contactPrimaryPhone);
        clientForm.setContactSecondaryPhone(contactSecondaryPhone);
        clientForm.setContactFaxPhone(contactFaxPhone);
        clientForm.setContactEMail(contactEmail);
        clientForm.setContactOrgId(contactOrgId);
        try
        {
            ClientModule
                ms4Module =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.STORM_WATER_MODULE);
            if (ms4Module
                != null)
            {
                clientForm.setCanAccessStormWater(true);
            }
            else
            {
                clientForm.setCanAccessStormWater(false);
            }
            ClientModule
                escModule =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.EROSION_CONTROL_MODULE);
            if (escModule
                != null)
            {
                clientForm.setCanAccessErosionControl(true);
            }
            else
            {
                clientForm.setCanAccessErosionControl(false);
            }
            ClientModule
                dvoModule =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.DATA_VIEW_MODULE);
            if (dvoModule
                != null)
            {
                clientForm.setCanAccessDataView(true);
            }
            else
            {
                clientForm.setCanAccessDataView(false);
            }
            ClientModule
                envModule =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.ENVIRONMENT_MODULE);
            if (envModule
                != null)
            {
                clientForm.setCanAccessEnvironment(true);
            }
            else
            {
                clientForm.setCanAccessEnvironment(false);
            }
        }
        catch (Exception p)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.load.module.failed")
                           + p.toString());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return clientForm;
    }

    public static Integer saveClientContact(Integer intClientId, Integer intContactId, Integer intContactTypeId)
        throws Exception
    {
        String
            strLog =
            new String("saveClientContact() ");
        Integer
            intId =
            new Integer(0);
        CapContact
            capContact =
            new CapContact();
        ClientData
            clientData =
            new ClientData();
        CapContactType
            capContactType =
            new CapContactType();
        LOG.debug(strLog
                  + "in method");
        if ((intClientId
             == null)
            || (intContactId
                == null)
            || (intContactTypeId
                == null))
        {
            String
                strMessage =
                strLog
                + "An input parameter is null.  Exiting method.";
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        try
        {
            clientData.setId(intClientId);
            clientData.load();
            if (clientData
                == null)
            {
                String
                    strMessage =
                    new String(ApplicationResources.getProperty("error.load.client.failed"));
                LOG.debug(strMessage);
                throw new Exception(strMessage);
            }
            capContact.setId(intContactId);
            capContact.load();
            if (capContact
                == null)
            {
                String
                    strMessage =
                    new String(ApplicationResources.getProperty("error.load.contact.failed"));
                LOG.debug(strMessage);
                throw new Exception(strMessage);
            }
            capContactType.setId(intContactTypeId);
            capContactType.load();
            if (capContactType
                == null)
            {
                String
                    strMessage =
                    new String(ApplicationResources.getProperty("error.load.contact.type.failed"));
                LOG.debug(strMessage);
                throw new Exception(strMessage);
            }
            CapClientContact
                existingContact =
                CapClientContact.findByClientIdAndContactIdAndContactType(clientData.getId(),
                                                                          capContact.getId(),
                                                                          capContactType.getId());
            if (existingContact
                == null)
            {
                CapClientContact
                    capClientContact =
                    new CapClientContact();
                capClientContact.setId(null);
                capClientContact.setClient(clientData);
                capClientContact.setContact(capContact);
                capClientContact.setContactType(capContactType);
                intId =
                    capClientContact.insert();
            }
        }
        catch (Exception c)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.insert.client.contact.failed")
                           + c.toString());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return intId;
    }

    public static void saveClientModules(ClientForm objNewClient)
        throws Exception
    {
        String
            strLog =
            new String("saveClientModule(): ");
        if (objNewClient
            == null)
        {
            String
                strMessage =
                new String("Client Form is null.  Cannot save client modules.");
            LOG.debug(strLog
                      + strMessage);
            throw new Exception(strMessage);
        }
        try
        {
            if (objNewClient.getCanAccessStormWater())
            {
                ClientModule
                    ms4ClientModule =
                    ClientModule.findClientModule(objNewClient.getId(),
                                                  CommonConstants.STORM_WATER_MODULE);
                if (ms4ClientModule
                    == null)
                {
                    CapModule
                        ms4Module =
                        CapModule.findByCode(CommonConstants.STORM_WATER_MODULE);
                    Integer
                        moduleId =
                        ms4Module.getId();
                    ClientModule
                        ms4CM =
                        new ClientModule();
                    ms4CM.setClientId(objNewClient.getId());
                    ms4CM.setModuleId(moduleId);
                    ms4CM.save();
                }
            }
            if (objNewClient.getCanAccessErosionControl())
            {
                ClientModule
                    escClientModule =
                    ClientModule.findClientModule(objNewClient.getId(),
                                                  CommonConstants.EROSION_CONTROL_MODULE);
                if (escClientModule
                    == null)
                {
                    CapModule
                        escModule =
                        CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
                    Integer
                        moduleId =
                        escModule.getId();
                    ClientModule
                        escCM =
                        new ClientModule();
                    escCM.setClientId(objNewClient.getId());
                    escCM.setModuleId(moduleId);
                    escCM.save();
                }
            }
            if (objNewClient.getCanAccessDataView())
            {
                ClientModule
                    dvoClientModule =
                    ClientModule.findClientModule(objNewClient.getId(),
                                                  CommonConstants.DATA_VIEW_MODULE);
                if (dvoClientModule
                    == null)
                {
                    CapModule
                        dvoModule =
                        CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE);
                    Integer
                        moduleId =
                        dvoModule.getId();
                    ClientModule
                        dvoCM =
                        new ClientModule();
                    dvoCM.setClientId(objNewClient.getId());
                    dvoCM.setModuleId(moduleId);
                    dvoCM.save();
                }
            }
            if (objNewClient.getCanAccessEnvironment())
            {
                ClientModule
                    envClientModule =
                    ClientModule.findClientModule(objNewClient.getId(),
                                                  CommonConstants.ENVIRONMENT_MODULE);
                if (envClientModule
                    == null)
                {
                    CapModule
                        envModule =
                        CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
                    Integer
                        moduleId =
                        envModule.getId();
                    ClientModule
                        envCM =
                        new ClientModule();
                    envCM.setClientId(objNewClient.getId());
                    envCM.setModuleId(moduleId);
                    envCM.save();
                }
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String(strLog
                           + new String(ApplicationResources.getProperty("error.insert.client.type")
                                        + e.toString()));
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
    }

    public static ClientDVForm getClientDVForm(ClientDVForm c, Integer intClientId)
        throws Exception
    {
        String
            strLog =
            new String("getClientDVForm() ");
        LOG.debug(strLog
                  + "in method");
        try
        {
            List
                dvList =
                DvClientInformation.findByClientId(intClientId);
            Iterator
                dvi =
                dvList.iterator();
            if (dvi.hasNext())
            {
                LOG.debug(strLog
                          + "Retrieving client DV form data.");
                DvClientInformation
                    dvInfo =
                    (DvClientInformation) dvi.next();
                c.setClientId(dvInfo.getClientId());
                c.setId(dvInfo.getId());
                c.setClientFullName(dvInfo.getClientFullName());
                c.setClientName(dvInfo.getClientName());
                c.setImsService(dvInfo.getImsService());
                c.setImsOvService(dvInfo.getImsOvService());
                c.setDownLoads(dvInfo.getDownLoads());
                c.setAttachmentLayers(dvInfo.getAttachmentLayers());
                c.setStartLeft(dvInfo.getStartLeft());
                c.setStartRight(dvInfo.getStartRight());
                c.setStartTop(dvInfo.getStartTop());
                c.setStartBottom(dvInfo.getStartBottom());
                c.setLimitLeft(dvInfo.getLimitLeft());
                c.setLimitRight(dvInfo.getLimitRight());
                c.setLimitTop(dvInfo.getLimitTop());
                c.setLimitBottom(dvInfo.getLimitBottom());
            }
            else
            {
                LOG.debug(strLog
                          + "Client does not have DataView setup values associated with it.");
                c.reset();
                ClientData
                    clientData =
                    new ClientData(intClientId);
                clientData.load();
                c.setClientId(intClientId);
                c.setClientFullName(clientData.getName());
                c.setClientName(clientData.getShortName());
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String(strLog
                           + "Error getting the client DV form"
                           + e.toString());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return c;
    }

    public static ClientECForm getClientECForm(ClientECForm c, Integer intClientId)
        throws Exception
    {
        String
            strLog =
            new String("getClientECForm() ");
        EcClientInformation
            d =
            new EcClientInformation();
        LOG.debug(strLog
                  + "in method");
        try
        {
            if (intClientId
                == null)
            {
                String
                    strMessage =
                    new String(strLog
                               + "Client ID is null");
                LOG.debug(strMessage);
                throw new Exception(strMessage);
            }
            EcClientInformation
                ecClientInfo =
                EcClientInformation.findByClientId(intClientId);
            if (ecClientInfo
                == null)
            {
                LOG.debug(strLog
                          + "Client does not have Erosion Control (ESC) setup values associated with it.");
                c.reset();
                c.setClientId(intClientId);
                Collection
                    capCTC =
                    CapClientType.getClientTypeByClientId(intClientId);
                Iterator
                    ci =
                    capCTC.iterator();
                if (ci.hasNext())
                {
                    CapClientType
                        capClientType =
                        (CapClientType) ci.next();
                    Integer
                        intClientTypeId =
                        capClientType.getClientTypeId();
                    LOG.debug(strLog
                              + "Client Type Id from ec client client type table = "
                              + intClientTypeId.toString());
                    c.setClientTypeId(intClientTypeId);
                    CapClientTypeInfo
                        typeInfo =
                        new CapClientTypeInfo(intClientTypeId);
                    typeInfo.load();
                    if (typeInfo
                        != null)
                    {
                        c.setClientTypeName(typeInfo.getName());
                        LOG.debug(strLog
                                  + "Client Type Name  from ec client client type table: "
                                  + c.getClientTypeName());
                    }
                }
            }
            else
            {
                LOG.debug(strLog
                          + "Retrieving client EC form data for id: "
                          + ecClientInfo.getId());
                d.setId(ecClientInfo.getId());
                d.load();
                c.setClientId(d.getClientId());
                c.setId(d.getId());
                c.setPublicCommentEMail(d.getPublicCommentEMail());
                c.setGeneralReplyToEMail(d.getGeneralReplyToEMail());
                c.setClientTypeId(d.getClientTypeId());
                ClientData
                    client =
                    new ClientData(intClientId);
                client.load();
                ClientAdminSettingsData
                    settings =
                    client.getClientAdminSettings();
                c.setInspectionOverdueInitialMessage(settings.getInspectionOverdueInitialMessage());
                c.setInspectionOverdueInitialThreshold(settings.getInspectionOverdueInitialThreshold());
                c.setInspectionOverdueSecondMessage(settings.getInspectionOverdueSecondMessage());
                c.setInspectionOverdueSecondThreshold(settings.getInspectionOverdueSecondThreshold());
                c.setInspectionOverdueNotificationEnabled(settings.isInspectionOverdueNotificationEnabled());
                c.setSecondInspectionOverdueNotificationEnabled(settings.isSecondInspectionOverdueNotificationEnabled());
                c.setProjectStatusEmailsEnabled(settings.isProjectStatusEmailsEnabled());
                c.setInspectionCertificationEnabled(settings.isInspectionCertificationEnabled());
                c.setInspectionCertificationMessage(settings.getInspectionCertificationMessage());
                // This section gets and sets the client type id and checks that the client type within
                // the EC_CLIENT_INFORMATION table is the same as the client type id value in the
                // CAP_CLIENT_TYPE table.  The EC_CLIENT_INFORMATION value over rules the sister table.
                Integer
                    intClientTypeId =
                    new Integer(0);
                Integer
                    id =
                    new Integer(0);
                CapClientType
                    newCapClientType =
                    new CapClientType();
                // Get the client type from the Partners table CAP_CLIENT_TYPE
                Collection
                    ccTypeCol =
                    CapClientType.getClientTypeByClientId(intClientId);
                Iterator
                    cci =
                    ccTypeCol.iterator();
                if (cci.hasNext())
                {
                    CapClientType
                        clientType =
                        (CapClientType) cci.next();
                    intClientTypeId =
                        clientType.getClientTypeId();
                    id =
                        clientType.getId();
                }
                if (c.getClientTypeId()
                    .equals(new Integer(0)))
                {
                    c.setClientTypeId(intClientTypeId);
                }
                else
                {
                    if (!intClientTypeId.equals(new Integer(0)))
                    {
                        LOG.debug(strLog
                                  + "Checking if Client Types are in sync "
                                  + c.getClientTypeId()
                            .toString());
                        if (!intClientTypeId.equals(c.getClientTypeId()))
                        {
                            LOG.debug(strLog
                                      + "Client Types are not in sync "
                                      + c.getClientTypeId()
                                .toString()
                                      + " "
                                      + intClientTypeId.toString());
                            // The EC_CLIENT_INFORMATION table is the defining rule for the client type id.
                            // Update the CAP_CLIENT_TYPE table to reflect the client type id in the sister table.
                            if (id.equals(new Integer(0)))
                            {
                                newCapClientType.setClientId(intClientId);
                                newCapClientType.setClientTypeId(c.getClientTypeId());
                                newCapClientType.save();
                            }
                            else
                            {
                                newCapClientType.setId(id);
                                newCapClientType.load();
                                newCapClientType.setClientTypeId(c.getClientTypeId());
                                newCapClientType.update();
                            }
                        }
                    }
                    else
                    {
                        LOG.debug(strLog
                                  + "Client Type Id does not exist for the client in the CAP_CLIENT_TYPE table. "
                                  + c.getClientTypeId()
                            .toString());
                        newCapClientType.setClientId(intClientId);
                        newCapClientType.setClientTypeId(c.getClientTypeId());
                        newCapClientType.insert();
                    }
                }
                CapClientTypeInfo
                    info =
                    new CapClientTypeInfo(c.getClientTypeId());
                info.load();
                if (info.getName()
                    != null)
                {
                    c.setClientTypeName(info.getName());
                }
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                strLog
                + "Error getting client Erosion Control (ESC) information to load Form.  ClientId "
                + intClientId.toString()
                + e.toString();
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return c;
    }

    public static List getActivity(Integer userId, Integer clientId, Date startDate, Date endDate, Integer count)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("EXEC sp_user_activity");
        buffer.append(" @userId = "
                      + userId);
        buffer.append(",@clientId = "
                      + clientId);
        if (startDate
            != null)
        {
            buffer.append(",@startDate = '"
                          + DateUtil.mdytDate(startDate)
                          + "'");
        }
        if (endDate
            != null)
        {
            buffer.append(",@endDate = '"
                          + DateUtil.mdytDate(endDate)
                          + "'");
        }
        if (count
            > 0)
        {
            buffer.append(",@top = "
                          + count);
        }
        SQLQuery
            query =
            HibernateUtil.getNewSession()
                .createSQLQuery(buffer.toString());
        List
            list =
            query.list();
        return list;
    }
}
