package com.sehinc.security.action.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.client.ClientUserData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.contact.ContactForm;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientEditAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientEditAction.class);
    private static
    String
        strMod =
        "com.sehinc.security.action.client.ClientEditAction. ";

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strLog =
            strMod
            + "clientAction ";
        Integer
            intClientId;
        ActionMessages
            objClientErrors =
            new ActionMessages();
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("update.client.unauthorized"));
            addError(new ActionMessage("update.client.unauthorized"), request);
            return mapping.findForward("client.view.page");
        }
        if (isCancelled(request))
        {
            LOG.info(ApplicationResources.getProperty("update.client.canceled"));
            //addError(new ActionMessage("update.client.canceled"), request);
            return mapping.findForward("client.view.page");
        }
        ClientForm
            clientForm =
            (ClientForm) form;
        if (clientForm.getId()
            != 0)
        {
            ClientData
                sehMain =
                ClientData.findSEHClient();
            if (sehMain
                != null)
            {
                if (sehMain.getId()
                    .equals(clientForm.getId()))
                {
                    clientForm.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                }
            }
            clientForm.validateForm(objClientErrors);
            if (checkActionMessages(clientForm.getClientErrors(), request))
            {
                ActionMessages
                    clientErrors =
                    clientForm.getClientErrors();
                Iterator
                    ei =
                    clientErrors.get();
                while (ei.hasNext())
                {
                    ActionMessage
                        errorTxt =
                        (ActionMessage) ei.next();
                    LOG.info("Invalid data entered in client update. "
                             + errorTxt.toString());
                    addError(errorTxt, request);
                }
                return mapping.getInputForward();
            }
            else
            {
                try
                {
                    if (!clientForm.getStatusCode()
                        .equals(StatusCodeData.STATUS_CODE_ACTIVE))
                    {
                        clientForm.setCanAccessStormWater(false);
                        clientForm.setCanAccessErosionControl(false);
                        clientForm.setCanAccessDataView(false);
                        clientForm.setCanAccessEnvironment(false);
                    }
                    if (!securityManager.getIsSystemAdministrator())
                    {
                        clientForm.setCanAccessDataView(securityManager.getClientCanAccessDV());
                        clientForm.setCanAccessErosionControl(securityManager.getClientCanAccessEC());
                        clientForm.setCanAccessStormWater(securityManager.getClientCanAccessSW());
                        clientForm.setCanAccessEnvironment(securityManager.getClientCanAccessEV());
                    }
                    updateClient(clientForm,
                                 getUserValue(request));
                    LOG.info(strLog
                             + "Client updated.");
                    intClientId =
                        clientForm.getId();
                    this.setClientInSession(intClientId, request);
                    LOG.info(strLog
                             + "CLIENT_ID IN SESSION: "
                             + intClientId);
                    List
                        userList =
                        ClientUserData.findActiveByClientId(intClientId);
                    Iterator
                        ui =
                        userList.iterator();
                    while (ui.hasNext())
                    {
                        ClientUserData
                            clientUserData =
                            (ClientUserData) ui.next();
                        synchClientUserAccess(clientUserData,
                                              intClientId);
                    }
                }
                catch (Exception s)
                {
                    LOG.info(ApplicationResources.getProperty("error.update.client.failed")
                             + s.toString());
                    addError(new ActionMessage("error.update.client.failed"), request);
                    return mapping.findForward("client.edit.page");
                }
            }
        }
        else
        {
            LOG.info(ApplicationResources.getProperty("error.update.client.failed"));
            addError(new ActionMessage("error.update.client.failed"), request);
            return mapping.findForward("client.edit.page");
        }
        securityManager.setClientID(clientForm.getId(),
                                    true);
        return mapping.findForward("continue");
    }

    public static void updateClient(ClientForm objClient, UserValue objUser)
        throws Exception
    {
        Integer
            intClientAddressId =
            0;
        String
            strLog =
            new String(strMod
                       + "updateClient ");
        String
            strError =
            new String(strLog);
        ClientData
            objClientData =
            new ClientData();
        Integer
            intContactId;
        ContactForm
            objCF =
            new ContactForm();
        LOG.debug(strLog
                  + "in method");
        if (objClient
            == null)
        {
            strError =
                strError
                + "objClient object is null.  Unable to update client";
            LOG.debug(strError);
            throw new Exception(strError);
        }
        Integer
            intClientId =
            objClient.getId();
        objClientData.setId(intClientId);
        try
        {
            objClientData.load();
        }
        catch (Exception l)
        {
            strError =
                new String(ApplicationResources.getProperty("error.load.client.failed"));
            LOG.debug(strError);
            throw new Exception(strError);
        }
        try
        {
            objClient.getClientData(objClientData);
        }
        catch (Exception y)
        {
            strError =
                strError
                + "Unable to set client data from client form object. Client Id: "
                + intClientId.toString()
                + ".  Unable to update client.  <br>Message:<br> "
                + y.getMessage();
            LOG.debug(strError);
            throw new Exception(strError);
        }
        try
        {
            if (objClient.getAddressId()
                .equals(new Integer(0)))
            {
                AddressData
                    newAdd =
                    objClient.getAddressData();
                newAdd.setId(null);
                newAdd.save(objUser);
                LOG.debug(strLog
                          + "Client address inserted.");
                objClientData.setAddressId(intClientAddressId);
            }
            else
            {
                AddressData
                    updAdd =
                    new AddressData(objClient.getAddressId());
                updAdd.load();
                objClient.getAddressData(updAdd);
                updAdd.update(objUser);
                LOG.debug(strLog
                          + "Client address updated.");
            }
        }
        catch (Exception a)
        {
            strError =
                strError
                + "Unable to update client address for client "
                + intClientId.toString()
                + " address Id "
                + objClient.getAddressData()
                    .toString()
                + "<br>Message:<br> "
                + a.getMessage();
            LOG.debug(strError);
            throw new Exception(strError);
        }
        try
        {
            objClientData.update(objUser);
            LOG.debug(strLog
                      + "Client Data "
                      + intClientId.toString()
                      + " updated.");
        }
        catch (Exception u)
        {
            strError =
                strError
                + "Unable to update client data object for Client Id "
                + intClientId.toString()
                + ".  Unable to update client.  <br>Message:<br> "
                + u.getMessage();
            LOG.debug(strError);
            throw new Exception(strError);
        }
        try
        {
            AddressData
                objA =
                new AddressData();
            objA.setId(objClientData.getAddressId());
            objA.load();
            CapContactOrganization
                objCCO =
                CapContactOrganization.findByClientId(objClientData.getId());
            objCCO.setAddress(objA);
            objCCO.setClientId(objClientData.getId());
            objCCO.setIsClient(new Boolean(true));
            objCCO.setName(objClientData.getName());
            objCCO.setStatus(objClientData.getStatus());
            objCCO.update(objUser);
        }
        catch (Exception cco)
        {
            strError =
                strError
                + "Error updating the client to the contact organization table."
                + cco.getMessage();
            LOG.debug(strError);
            throw new Exception(strError);
        }
        CapContact
            capContact =
            new CapContact(objClient.getContactId());
        capContact.load();
        if (capContact
            != null)
        {
            intContactId =
                objClient.getContactId();
            objCF =
                ContactService.getContactForm(objCF,
                                              intClientId,
                                              intContactId);
            objCF.setFirstName(objClient.getContactFirstName());
            objCF.setLastName(objClient.getContactLastName());
            objCF.setTitle(objClient.getContactTitle());
            objCF.setAddressId(objClient.getContactAddressId());
            objCF.setAddress(objClient.getContactAddress());
            objCF.setAddress2(objClient.getContactAddress2());
            objCF.setCity(objClient.getContactCity());
            objCF.setStateCode(objClient.getContactState());
            objCF.setZip(objClient.getContactZip());
            objCF.setPrimaryPhone(objClient.getContactPrimaryPhone());
            objCF.setSecondaryPhone(objClient.getContactSecondaryPhone());
            objCF.setFaxPhone(objClient.getContactFaxPhone());
            objCF.setEmail(objClient.getContactEMail());
            ContactService.saveContact(objCF,
                                       objUser);
        }
        else
        {
            objCF.setFirstName(objClient.getContactFirstName());
            objCF.setLastName(objClient.getContactLastName());
            objCF.setTitle(objClient.getContactTitle());
            objCF.setAddressId(objClient.getContactAddressId());
            objCF.setAddress(objClient.getContactAddress());
            objCF.setAddress2(objClient.getContactAddress2());
            objCF.setCity(objClient.getContactCity());
            objCF.setStateCode(objClient.getContactState());
            objCF.setZip(objClient.getContactZip());
            objCF.setPrimaryPhone(objClient.getContactPrimaryPhone());
            objCF.setSecondaryPhone(objClient.getContactSecondaryPhone());
            objCF.setFaxPhone(objClient.getContactFaxPhone());
            objCF.setEmail(objClient.getContactEMail());
            objCF.setOrganizationId(objClient.getContactOrgId());
            intContactId =
                ContactService.saveContact(objCF,
                                           objUser);
            try
            {
                ClientData
                    objC =
                    new ClientData();
                objC.setId(intClientId);
                objC.load();
                objC.setContactId(intContactId);
                objC.update(objUser);
            }
            catch (Exception zz)
            {
                strError =
                    strError
                    + "Error updating the client with the contact id of the newly created main contact.  ClientId: "
                    + intClientId.toString()
                    + " Contact Id: "
                    + intContactId.toString()
                    + zz.getMessage();
                LOG.debug(strError);
                throw new Exception(strError);
            }
            ClientService.saveClientContact(intClientId,
                                            intContactId,
                                            CapContactType.getMainContactType()
                                                .getId());
        }
        try
        {
            CapModule
                ms4Module =
                CapModule.findByCode(CommonConstants.STORM_WATER_MODULE);
            ClientModule
                objCM =
                ClientModule.findClientModule(objClient.getId(),
                                              CommonConstants.STORM_WATER_MODULE);
            boolean
                blnClientApp =
                (objCM
                 != null);
            LOG.debug("updateClient() - Client access to MS4 is checked as ["
                      + objClient.getCanAccessStormWater()
                .booleanValue()
                      + "] on the page and Client Module exists in database ["
                      + blnClientApp
                      + "]");
            if (objClient.getCanAccessStormWater()
                    .booleanValue()
                && (!blnClientApp))
            {
                objCM =
                    new ClientModule();
                objCM.setClientId(objClient.getId());
                objCM.setModuleId(ms4Module.getId());
                objCM.save();
            }
            else if (!objClient.getCanAccessStormWater()
                .booleanValue()
                     && (blnClientApp))
            {
                objCM =
                    ClientModule.findClientModule(objClient.getId(),
                                                  CommonConstants.STORM_WATER_MODULE);
                objCM.delete();
            }
            CapModule
                escModule =
                CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
            objCM =
                ClientModule.findClientModule(objClient.getId(),
                                              CommonConstants.EROSION_CONTROL_MODULE);
            blnClientApp =
                (objCM
                 != null);
            LOG.debug("updateClient() - Client access to ESC is checked as ["
                      + objClient.getCanAccessErosionControl()
                .booleanValue()
                      + "] on the page and Client Module exists in database ["
                      + blnClientApp
                      + "]");
            if (objClient.getCanAccessErosionControl()
                    .booleanValue()
                && (!blnClientApp))
            {
                objCM =
                    new ClientModule();
                objCM.setClientId(objClient.getId());
                objCM.setModuleId(escModule.getId());
                objCM.save();
            }
            else if (!objClient.getCanAccessErosionControl()
                .booleanValue()
                     && (blnClientApp))
            {
                objCM =
                    ClientModule.findClientModule(objClient.getId(),
                                                  CommonConstants.EROSION_CONTROL_MODULE);
                objCM.delete();
            }
            CapModule
                dvoModule =
                CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE);
            objCM =
                ClientModule.findClientModule(objClient.getId(),
                                              CommonConstants.DATA_VIEW_MODULE);
            blnClientApp =
                (objCM
                 != null);
            LOG.debug("updateClient() - Client access to DVO is checked as ["
                      + objClient.getCanAccessDataView()
                .booleanValue()
                      + "] on the page and Client Module exists in database ["
                      + blnClientApp
                      + "]");
            if (objClient.getCanAccessDataView()
                    .booleanValue()
                && (!blnClientApp))
            {
                objCM =
                    new ClientModule();
                objCM.setClientId(objClient.getId());
                objCM.setModuleId(dvoModule.getId());
                objCM.save();
            }
            else if (!objClient.getCanAccessDataView()
                .booleanValue()
                     && (blnClientApp))
            {
                objCM =
                    ClientModule.findClientModule(objClient.getId(),
                                                  CommonConstants.DATA_VIEW_MODULE);
                objCM.delete();
            }
            CapModule
                envModule =
                CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
            objCM =
                ClientModule.findClientModule(objClient.getId(),
                                              CommonConstants.ENVIRONMENT_MODULE);
            blnClientApp =
                (objCM
                 != null);
            LOG.debug("updateClient() - Client access to ENV is checked as ["
                      + objClient.getCanAccessEnvironment()
                .booleanValue()
                      + "] on the page and Client Module exists in database ["
                      + blnClientApp
                      + "]");
            if (objClient.getCanAccessEnvironment()
                    .booleanValue()
                && (!blnClientApp))
            {
                objCM =
                    new ClientModule();
                objCM.setClientId(objClient.getId());
                objCM.setModuleId(envModule.getId());
                objCM.save();
            }
            else if (!objClient.getCanAccessEnvironment()
                .booleanValue()
                     && (blnClientApp))
            {
                objCM =
                    ClientModule.findClientModule(objClient.getId(),
                                                  CommonConstants.ENVIRONMENT_MODULE);
                objCM.delete();
            }
        }
        catch (Exception cm)
        {
            strError =
                strError
                + "Unable to update client modules for client "
                + intClientId.toString()
                + "<br>Message:<br> "
                + cm.getMessage();
            LOG.debug(strError);
            throw new Exception(strError);
        }
    }

    private void synchClientUserAccess(ClientUserData clientUserData, Integer intClientId)
        throws Exception
    {
        List<Integer>
            allowedClientModules =
            new ArrayList<Integer>();
        List
            clientModuleList =
            ClientModule.findAllClientModulesByClientId(intClientId);
        Iterator
            mi =
            clientModuleList.iterator();
        while (mi.hasNext())
        {
            ClientModule
                module =
                (ClientModule) mi.next();
            allowedClientModules.add(module.getModuleId());
        }
        List
            userModuleList =
            CapUserModule.findByUserId(clientUserData.getUserId(),
                                       intClientId);
        Iterator
            umi =
            userModuleList.iterator();
        CapModule
            securityModule =
            CapModule.findByCode(CommonConstants.SECURITY_MODULE);
        CapModule
            mainModule =
            CapModule.findByCode(CommonConstants.CAP_USER_MODULE);
        while (umi.hasNext())
        {
            CapUserModule
                userModule =
                (CapUserModule) umi.next();
            if (!allowedClientModules.contains(userModule.getModuleId()))
            {
                if ((userModule.getModuleId()
                         .intValue()
                     != securityModule.getId())
                    && (userModule.getModuleId()
                            .intValue()
                        != mainModule.getId()))
                {
                    userModule.delete();
                }
            }
        }
        UserData
            userData =
            UserData.findById(clientUserData.getUserId());
        GroupData
            groupData =
            new GroupData(userData.getGroupId());
        groupData.load();
        mi =
            clientModuleList.iterator();
        while (mi.hasNext())
        {
            ClientModule
                module =
                (ClientModule) mi.next();
            Integer
                securityLevel =
                groupData.getSecurityLevelId();
            if (securityLevel.intValue()
                <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID)
            {
                CapUserModule
                    userModule =
                    CapUserModule.findByUserIdAndModuleId(clientUserData.getUserId(),
                                                          module.getModuleId(),
                                                          intClientId);
                if (userModule
                    == null)
                {
                    LOG.debug("synchClientUserAccess(): Inserting new module access for client admin.");
                    CapUserModule
                        newModule =
                        new CapUserModule();
                    newModule.setModuleId(module.getModuleId());
                    newModule.setUserId(clientUserData.getUserId());
                    newModule.setClientId(intClientId);
                    newModule.setIsActive(true);
                    newModule.insert();
                }
            }
        }
    }
}