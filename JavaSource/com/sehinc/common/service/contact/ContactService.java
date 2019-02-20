package com.sehinc.common.service.contact;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.code.ContactTypeData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.value.contact.ContactTypeValue;
import com.sehinc.common.value.user.AddressValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.security.action.contact.ContactForm;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;

import java.util.*;

@SuppressWarnings("unchecked")
public class ContactService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactService.class);

    public ContactService()
    {
    }

    public static List getContactTypeValueList(Integer moduleId)
    {
        ArrayList
            contactTypeValueList =
            new ArrayList();
        Iterator
            contactTypeValueIterator =
            CapContactType.findByModuleId(moduleId)
                .iterator();
        while (contactTypeValueIterator.hasNext())
        {
            contactTypeValueList.add(new ContactTypeValue((CapContactType) contactTypeValueIterator.next()));
        }
        return contactTypeValueList;
    }

    public static CapContact getMainContactByClientId(Integer clientId)
    {
        ClientData
            clientData =
            new ClientData(clientId);
        if (clientData.load())
        {
            if (clientData.getContactId()
                != null)
            {
                CapContact
                    contact =
                    CapContact.getActiveById(clientData.getContactId());
                if (contact
                    != null)
                {
                    return contact;
                }
            }
        }
        return null;
    }

    public static CapContact getContactByContactType(Integer clientId, CapContactType contactType)
    {
        List
            clientContactList =
            CapClientContact.findByClientId(clientId);
        if (clientContactList
            != null
            && !clientContactList.isEmpty())
        {
            Iterator
                clientContactIter =
                clientContactList.iterator();
            while (clientContactIter.hasNext())
            {
                CapClientContact
                    clientContact =
                    (CapClientContact) clientContactIter.next();
                if (clientContact.getContactType()
                    != null
                    &&
                    clientContact.getContactType()
                        .getId()
                    != null
                    &&
                    clientContact.getContactType()
                        .getId()
                        .equals(contactType.getId()))
                {
                    return clientContact.getContact();
                }
            }
        }
        return null;
    }

    public static List getAllContactsByContactType(Integer clientId, CapContactType contactType)
    {
        List
            results =
            new ArrayList();
        List
            clientContactList =
            CapClientContact.findByClientId(clientId);
        if (clientContactList
            != null
            && !clientContactList.isEmpty())
        {
            Iterator
                clientContactIter =
                clientContactList.iterator();
            while (clientContactIter.hasNext())
            {
                CapClientContact
                    clientContact =
                    (CapClientContact) clientContactIter.next();
                if (clientContact.getContactType()
                    != null
                    &&
                    clientContact.getContactType()
                        .getId()
                    != null
                    &&
                    clientContact.getContactType()
                        .getId()
                        .equals(contactType.getId()))
                {
                    results.add(clientContact.getContact());
                }
            }
        }
        return results;
    }

    public static CapContact copyContact(Integer fromContactId)
    {
        CapContact
            fromContact =
            new CapContact(fromContactId);
        CapContact
            toContact =
            null;
        if (fromContactId
            != null
            && fromContact.load())
        {
            toContact =
                new CapContact();
            toContact.setOrganization(fromContact.getOrganization());
            toContact.setOrganizationName(fromContact.getOrganizationName());
            toContact.setFirstName(fromContact.getFirstName());
            toContact.setLastName(fromContact.getLastName());
            toContact.setAddressData(AddressData.copyAddress(fromContact.getAddressData()));
            toContact.setPrimaryPhone(fromContact.getPrimaryPhone());
            toContact.setEmail(fromContact.getEmail());
        }
        return toContact;
    }

    public static Integer saveContact(ContactForm objCF, UserValue objU)
        throws Exception
    {
        String
            strLog =
            new String("saveContact() ");
        Integer
            intContactId;
        CapContactOrganization
            objCCO =
            new CapContactOrganization();
        LOG.debug(strLog
                  + "in method");
        try
        {
            LOG.debug(strLog
                      + "Saving contact address data.");
            AddressData
                addressData =
                saveAddressData(new AddressValue(objCF),
                                objU);
            if (objCF.getOrganizationId()
                != null)
            {
                objCCO.setId(objCF.getOrganizationId());
                if (!objCCO.load())
                {
                    String
                        strMessage =
                        new String(ApplicationResources.getProperty("error.contact.org.does.not.exist"));
                    LOG.debug(strMessage);
                    throw new Exception(strMessage);
                }
            }
            else
            {
                String
                    strMessage =
                    new String(ApplicationResources.getProperty("error.contact.missing.org.id"));
                LOG.debug(strMessage);
                throw new Exception(strMessage);
            }
            intContactId =
                objCF.getId();
            LOG.debug(strLog
                      + "Saving cap_contact data for contact: "
                      + intContactId);
            if (intContactId.intValue()
                == 0)
            {
                CapContact
                    objC =
                    new CapContact();
                objC.setAddressData(addressData);
                objC.setAddress(objCF.getAddress());
                objC.setAddress2(objCF.getAddress2());
                objC.setCity(objCF.getCity());
                objC.setStateCode(objCF.getStateCode());
                objC.setZip(objCF.getZip());
                objC.setEmail(objCF.getEmail());
                objC.setFirstName(objCF.getFirstName());
                objC.setLastName(objCF.getLastName());
                objC.setMi(objCF.getMi());
                objC.setTitle(objCF.getTitle());
                objC.setOrganization(objCCO);
                objC.setOrganizationName(objCF.getOrganizationName());
                if (objC.getOrganizationName()
                        .trim()
                        .length()
                    == 0)
                {
                    objC.setOrganizationName(objCCO.getName());
                }
                objC.setPrimaryPhone(objCF.getPrimaryPhone());
                objC.setSecondaryPhone(objCF.getSecondaryPhone());
                objC.setFaxPhone(objCF.getFaxPhone());
                objC.setStatusCode(objCF.getStatusCode());
                objC.setContactTypes(setSelectedContactTypes(objCF));
                intContactId =
                    objC.insert(objU);
            }
            else
            {
                CapContact
                    objC =
                    new CapContact(intContactId);
                objC.load();
                objC.setAddressData(addressData);
                objC.setAddress(objCF.getAddress());
                objC.setAddress2(objCF.getAddress2());
                objC.setCity(objCF.getCity());
                objC.setStateCode(objCF.getStateCode());
                objC.setZip(objCF.getZip());
                objC.setEmail(objCF.getEmail());
                objC.setFirstName(objCF.getFirstName());
                objC.setLastName(objCF.getLastName());
                objC.setMi(objCF.getMi());
                objC.setTitle(objCF.getTitle());
                objC.setOrganization(objCCO);
                objC.setOrganizationName(objCF.getOrganizationName());
                if (objC.getOrganizationName()
                        .trim()
                        .length()
                    == 0)
                {
                    objC.setOrganizationName(objCCO.getName());
                }
                objC.setPrimaryPhone(objCF.getPrimaryPhone());
                objC.setSecondaryPhone(objCF.getSecondaryPhone());
                objC.setFaxPhone(objCF.getFaxPhone());
                objC.setStatusCode(objCF.getStatusCode());
                objC.setContactTypes(setSelectedContactTypes(objCF));
                objC.update(objU);
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.load.contact.failed")
                           + e.toString());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return intContactId;
    }

    private static Set setSelectedContactTypes(ContactForm objCF)
    {
        String[]
            selectedTypesFromPage =
            objCF.getSelectedTypes();
        HashSet
            contactSelectedTypes =
            new HashSet();
        if (selectedTypesFromPage
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < selectedTypesFromPage.length; i++)
            {
                ContactTypeData
                    contactType =
                    (ContactTypeData) SpringServiceLocator.getLookupService()
                        .fetchCode(selectedTypesFromPage[i],
                                   ContactTypeData.class);
                contactSelectedTypes.add(contactType);
            }
        }
        return contactSelectedTypes;
    }

    public static AddressData saveAddressData(AddressValue addressValue, UserValue user)
        throws Exception
    {
        AddressData
            addressData =
            new AddressData();
        try
        {
            if (addressValue.getId()
                != null)
            {
                addressData.setId(addressValue.getId());
                addressData.load();
            }
            addressData.setName1(addressValue.getName1());
            addressData.setName2(addressValue.getName2());
            addressData.setName3(addressValue.getName3());
            addressData.setLine1(addressValue.getLine1());
            addressData.setLine2(addressValue.getLine2());
            addressData.setLine3(addressValue.getLine3());
            addressData.setCity(addressValue.getCity());
            addressData.setState(addressValue.getState());
            addressData.setPostalCode(addressValue.getPostalCode());
            addressData.setCountry(addressValue.getCountry());
            addressData.save(user);
        }
        catch (Exception u)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("error.save.address.failed")
                           + u.toString());
            LOG.error(strMessage);
            throw new Exception(strMessage);
        }
        return addressData;
    }

    public static ContactForm getContactForm(ContactForm objCF, Integer intClientId, Integer intContactId)
        throws Exception
    {
        String
            strLog =
            new String("getContactForm() ");
        CapContact
            objCC =
            new CapContact();
        CapClientContact
            objCCC;
        try
        {
            objCC.setId(intContactId);
            objCC.load();
            objCF.setId(intContactId);
            objCF.setOrganizationId(objCC.getOrganization()
                                        .getId());
            objCF.setOrganizationName(objCC.getOrganizationName());
            objCF.setOrganizationAddress(objCC.getOrganization()
                                             .getAddress()
                                             .getLine1());
            objCF.setOrganizationAddress2(objCC.getOrganization()
                                              .getAddress()
                                              .getLine2());
            objCF.setOrganizationCity(objCC.getOrganization()
                                          .getAddress()
                                          .getCity());
            objCF.setOrganizationState(objCC.getOrganization()
                                           .getAddress()
                                           .getState());
            objCF.setOrganizationZip(objCC.getOrganization()
                                         .getAddress()
                                         .getPostalCode());
            objCF.setAddressId(objCC.getAddressData()
                                   .getId());
            objCF.setAddress(objCC.getAddressData()
                                 .getLine1());
            objCF.setAddress2(objCC.getAddressData()
                                  .getLine2());
            objCF.setCity(objCC.getAddressData()
                              .getCity());
            objCF.setStateCode(objCC.getAddressData()
                                   .getState());
            objCF.setZip(objCC.getAddressData()
                             .getPostalCode());
            objCF.setFirstName(objCC.getFirstName());
            objCF.setLastName(objCC.getLastName());
            objCF.setMi(objCC.getMi());
            objCF.setTitle(objCC.getTitle());
            objCF.setPrimaryPhone(objCC.getPrimaryPhone());
            objCF.setSecondaryPhone(objCC.getSecondaryPhone());
            objCF.setFaxPhone(objCC.getFaxPhone());
            objCF.setEmail(objCC.getEmail());
            objCF.setStateCode(objCC.getStateCode());
            objCF.setStatusCode(objCC.getStatus()
                                    .getCode());
            objCF.setAllContactTypes(objCC.getContactTypes());
            setSelectedContactTypes(objCF,
                                    objCC);
            ClientModule
                escModule =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.EROSION_CONTROL_MODULE);
            if (escModule
                != null)
            {
                objCF.setCanViewESC(true);
            }
            else
            {
                objCF.setCanViewESC(false);
            }
            CapModule
                module =
                CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
            Integer
                intModuleId =
                module.getId();
            List
                contactList =
                CapClientContact.findModuleSpecificTypes(intClientId,
                                                         intContactId,
                                                         intModuleId);
            Iterator
                ci =
                contactList.iterator();
            int
                roleNum =
                1;
            while (ci.hasNext())
            {
                objCCC =
                    (CapClientContact) ci.next();
                switch (roleNum)
                {
                    case 1:
                        objCF.setContactTypeEC1(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 2:
                        objCF.setContactTypeEC2(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 3:
                        objCF.setContactTypeEC3(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 4:
                        objCF.setContactTypeEC4(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 5:
                        objCF.setContactTypeEC5(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 6:
                        objCF.setContactTypeEC6(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 7:
                        objCF.setContactTypeEC7(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 8:
                        objCF.setContactTypeEC8(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 9:
                        objCF.setContactTypeEC9(objCCC.getContactType()
                                                    .getId());
                        break;
                }
                roleNum++;
            }
            contactList =
                CapClientContact.findModuleSpecificTypes(intClientId,
                                                         intContactId,
                                                         CapModule.findByCode(CommonConstants.STORM_WATER_MODULE)
                                                             .getId());
            ci =
                contactList.iterator();
            roleNum =
                1;
            while (ci.hasNext())
            {
                objCCC =
                    (CapClientContact) ci.next();
                switch (roleNum)
                {
                    case 1:
                        objCF.setContactTypeSW1(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 2:
                        objCF.setContactTypeSW2(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 3:
                        objCF.setContactTypeSW3(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 4:
                        objCF.setContactTypeSW4(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 5:
                        objCF.setContactTypeSW5(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 6:
                        objCF.setContactTypeSW6(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 7:
                        objCF.setContactTypeSW7(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 8:
                        objCF.setContactTypeSW8(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 9:
                        objCF.setContactTypeSW9(objCCC.getContactType()
                                                    .getId());
                        break;
                }
                roleNum++;
            }
            contactList =
                CapClientContact.findModuleSpecificTypes(intClientId,
                                                         intContactId,
                                                         CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE)
                                                             .getId());
            ci =
                contactList.iterator();
            roleNum =
                1;
            while (ci.hasNext())
            {
                objCCC =
                    (CapClientContact) ci.next();
                switch (roleNum)
                {
                    case 1:
                        objCF.setContactTypeDV1(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 2:
                        objCF.setContactTypeDV2(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 3:
                        objCF.setContactTypeDV3(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 4:
                        objCF.setContactTypeDV4(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 5:
                        objCF.setContactTypeDV5(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 6:
                        objCF.setContactTypeDV6(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 7:
                        objCF.setContactTypeDV7(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 8:
                        objCF.setContactTypeDV8(objCCC.getContactType()
                                                    .getId());
                        break;
                    case 9:
                        objCF.setContactTypeDV9(objCCC.getContactType()
                                                    .getId());
                        break;
                }
                roleNum++;
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String(strLog
                           + "Error getting contact form data for contact id "
                           + intContactId.toString()
                           + e.toString());
            LOG.debug(strMessage);
            throw new Exception(strMessage);
        }
        return objCF;
    }

    private static void setSelectedContactTypes(ContactForm objCF, CapContact objCC)
    {
        if (objCC.getContactTypes()
            == null)
        {
            String[]
                empty =
                {};
            objCF.setSelectedTypes(empty);
        }
        else
        {
            ArrayList<String>
                selectedCodes =
                new ArrayList();
            Iterator
                i =
                objCC.getContactTypes()
                    .iterator();
            while (i.hasNext())
            {
                ContactTypeData
                    type =
                    (ContactTypeData) i.next();
                selectedCodes.add(type.getCode());
            }
            objCF.setSelectedTypes(selectedCodes.toArray(new String[selectedCodes.size()]));
        }
    }

    public static List getContactTypeList(Integer intModuleId, boolean blnNoUser, Integer clientId, Integer contactId)
        throws Exception
    {
        String
            strLog =
            new String("getContactTypeList("
                       + intModuleId.toString()
                       + ") ");
        List
            lstC;
        List
            lstCV =
            new ArrayList();
        LOG.debug(strLog
                  + "in method");
        try
        {
            if (blnNoUser)
            {
                lstC =
                    CapContactType.findByModuleId(intModuleId);
            }
            else
            {
                lstC =
                    CapContactType.findByModuleIdNoUsers(intModuleId);
            }
            LOG.debug(strLog
                      + "Contact Type List returned "
                      + lstC.size()
                      + " items.");
            Iterator
                cti =
                lstC.iterator();
            while (cti.hasNext())
            {
                CapContactType
                    contactType =
                    (CapContactType) cti.next();
                ContactTypeValue
                    typeVal =
                    new ContactTypeValue(contactType);
                // Check if this contact type is selected for this contact.
                CapClientContact
                    clientContact =
                    CapClientContact.findByClientIdAndContactIdAndContactType(clientId,
                                                                              contactId,
                                                                              contactType.getId());
                if (clientContact
                    != null)
                {
                    LOG.debug(strLog
                              + "Contact Type "
                              + contactType.getId()
                              + " is selected.");
                    typeVal.setSelected("true");
                }
                else
                {
                    LOG.debug(strLog
                              + "Contact Type "
                              + contactType.getId()
                              + " is NOT selected.");
                    typeVal.setSelected("false");
                }
                lstCV.add(typeVal);
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String(ApplicationResources.getProperty("load.contact.type.list.failed")
                           + "for module id = "
                           + intModuleId.toString()
                           + e.toString());
            LOG.error(strMessage);
            throw new Exception(strMessage);
        }
        return lstCV;
    }

    public static Integer getClientContactOrganizationId(Integer intClientId, UserValue objUser)
        throws Exception
    {
        String
            strLog =
            new String("getClientContactOrganizationId() ");
        CapContactOrganization
            objC =
            new CapContactOrganization();
        ClientData
            objCD =
            new ClientData();
        Integer
            intId;
        LOG.debug(strLog
                  + "in method");
        try
        {
            //Get the cap contact organization id for the client.
            CapContactOrganization
                capContactOrganization =
                CapContactOrganization.findByClientId(intClientId);
            if (capContactOrganization
                == null)
            {
                objCD.setId(intClientId);
                try
                {
                    objCD.load();
                }
                catch (Exception c)
                {
                    String
                        strMessage =
                        new String("Error loading the client data object."
                                   + c.toString());
                    LOG.error(strMessage);
                    throw new Exception(strMessage);
                }
                objC.setId(null);
                objC.setClientId(intClientId);
                objC.setIsClient(new Boolean(true));
                objC.setName(objCD.getName());
                objC.setAddressId(objCD.getAddressId());
                objC.setStatus(objCD.getStatus());
                try
                {
                    intId =
                        objC.save(objUser);
                }
                catch (Exception x)
                {
                    String
                        strMessage =
                        new String("Error saving new contact organization specific for the client."
                                   + x.toString());
                    LOG.error(strMessage);
                    throw new Exception(strMessage);
                }
            }
            else
            {
                intId =
                    capContactOrganization.getId();
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String("Error getting client contact organization Id for client id = "
                           + intClientId.toString()
                           + e.toString());
            LOG.error(strMessage);
            throw new Exception(strMessage);
        }
        return intId;
    }

    public static void saveClientContactFromContactForm(ContactForm objCF, Integer intClientId)
        throws Exception
    {
        String
            strLog =
            new String("saveClientContactFromContactForm() ");
        LOG.debug(strLog
                  + "in method");
        Integer
            intContactId =
            objCF.getId();
        try
        {
            Integer
                i =
                objCF.getContactTypeEC1();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC2();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC3();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC4();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC5();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC6();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC7();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC8();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeEC9();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW1();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW2();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW3();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW4();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW5();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW6();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW7();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW8();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeSW9();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV1();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV2();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV3();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV4();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV5();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV6();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV7();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV8();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
            i =
                objCF.getContactTypeDV9();
            if (!(i
                  == null))
            {
                saveClientContact(intClientId,
                                  intContactId,
                                  i);
            }
        }
        catch (Exception b)
        {
            String
                strMessage =
                new String("Error inserting client contacts for this contact"
                           + b.toString());
            LOG.error(strMessage);
            throw new Exception(strMessage);
        }
    }

    public static Integer saveClientContact(Integer intClientId, Integer intContactId, Integer intContactTypeId)
        throws Exception
    {
        String
            strLog =
            new String("saveClientContact() ");
        Integer
            intId =
            null;
        LOG.debug(strLog
                  + "in method. Passed in params: ["
                  + intClientId
                  + "] ["
                  + intContactId
                  + "] ["
                  + intContactTypeId
                  + "]");
        try
        {
            CapClientContact
                objCCC =
                CapClientContact.findByClientIdAndContactIdAndContactType(intClientId,
                                                                          intContactId,
                                                                          intContactTypeId);
            if (objCCC
                == null)
            {
                objCCC =
                    new CapClientContact();
                ClientData
                    objCD =
                    new ClientData(intClientId);
                objCD.load();
                CapContact
                    objCC =
                    new CapContact(intContactId);
                objCC.load();
                CapContactType
                    objCCT =
                    new CapContactType(intContactTypeId);
                objCCT.load();
                objCCC.setClient(objCD);
                objCCC.setContact(objCC);
                objCCC.setContactType(objCCT);
                try
                {
                    intId =
                        objCCC.insert();
                }
                catch (Exception x)
                {
                    String
                        strMessage =
                        new String("Error inserting new client contact into CapClientContact table."
                                   + x.toString());
                    LOG.error(strMessage);
                    throw new Exception(strMessage);
                }
            }
            else
            {
                LOG.debug(strLog
                          + "Client Contact relationship already exists. intClientId="
                          + intClientId.toString()
                          + ", intContactId="
                          + intContactId.toString()
                          + ", intContactTypeId="
                          + intContactTypeId.toString());
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                new String("Error saving client contact relationship.  intClientId="
                           + intClientId.toString()
                           + ", intContactId="
                           + intContactId.toString()
                           + ", intContactTypeId="
                           + intContactTypeId.toString()
                           + e.toString());
            LOG.error(strMessage);
            throw new Exception(strMessage);
        }
        return intId;
    }
}