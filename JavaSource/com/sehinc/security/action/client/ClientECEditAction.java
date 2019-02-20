package com.sehinc.security.action.client;

import com.sehinc.common.db.client.CapClientType;
import com.sehinc.common.db.client.CapClientTypeInfo;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.EcClientInformation;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;

public class ClientECEditAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientECEditAction.class);
    private static
    String
        strMod =
        "com.sehinc.security.action.client.ClientECEditAction. ";

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog =
            strMod
            + "clientAction ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("edit.ec.unauthorized"));
            addError(new ActionMessage("edit.ec.unauthorized"), request);
            return mapping.findForward("client.ec.view.page");
        }
        if (isCancelled(request))
        {
            LOG.info(ApplicationResources.getProperty("cancel.edit.ec"));
            addError(new ActionMessage("cancel.edit.ec"), request);
            return mapping.findForward("client.ec.view.page");
        }
        ClientECForm
            objEC =
            (ClientECForm) form;
        updateClientEC(objEC, request);
        LOG.debug(strLog
                  + "Client Erosion Control (ESC) Information has been updated for client id "
                  + objEC.getClientId()
            .toString());
        LOG.debug(strLog
                  + "strForward = client.ec.view.page");
        return mapping.findForward("client.ec.view.page");
    }

    private void updateClientEC(ClientECForm c, HttpServletRequest request)
        throws Exception
    {
        String
            strLog =
            strMod
            + "updateClientEC() ";
        saveClientAdminSettings(c, request);
        EcClientInformation
            d =
            new EcClientInformation();
        try
        {
            Integer
                intClientId =
                c.getClientId();
            EcClientInformation
                ecInfo =
                EcClientInformation.findByClientId(intClientId);
            if (ecInfo
                != null)
            {
                LOG.debug(strLog
                          + "Updating existing client values for ec id: "
                          + c.getId());
                d.setId(c.getId());
                d.load();
                d.setPublicCommentEMail(c.getPublicCommentEMail());
                d.setClientTypeId(c.getClientTypeId());
                d.setGeneralReplyToEMail(c.getGeneralReplyToEMail());
                d.update();
                updateClientClientType(d.getClientId(),
                                       d.getClientTypeId());
                LOG.debug(strLog
                          + "Client Erosion Control (ESC) Information values updated for id "
                          + d.getId()
                    .toString());
            }
            else
            {
                LOG.debug(strLog
                          + "Client does not have Erosion Control (ESC) setup values associated with it.");
                //d.setId(c.getClientId()); // Per documentation by Nathan Yourchuck the id and the client id need to be identical.
                d.setClientId(c.getClientId());
                d.setPublicCommentEMail(c.getPublicCommentEMail());
                d.setClientTypeId(c.getClientTypeId());
                d.setGeneralReplyToEMail(c.getGeneralReplyToEMail());
                LOG.debug(d.getId());
                LOG.debug(d.getClientId());
                LOG.debug(d.getPublicCommentEMail());
                LOG.debug(d.getClientTypeId());
                LOG.debug(d.getGeneralReplyToEMail());
                d.insert();
                updateClientClientType(d.getClientId(),
                                       d.getClientTypeId());
                LOG.debug(strLog
                          + "New client Erosion Control (ESC) values inserted.  New Id "
                          + d.getId()
                    .toString());
            }
        }
        catch (Exception e)
        {
            String
                strErr =
                strLog
                + ApplicationResources.getProperty("error.update.esc")
                + "<br>Message:<br> "
                + e.getMessage();
            LOG.debug(strErr);
            throw new Exception(strErr);
        }
    }

    private void saveClientAdminSettings(ClientECForm c, HttpServletRequest request)
        throws Exception
    {
        String
            strLog =
            strMod
            + "saveClientAdminSettings() ";
        ClientData
            client =
            new ClientData(c.getClientId());
        client.load();
        try
        {
            client.getClientAdminSettings()
                .setInspectionOverdueInitialMessage(c.getInspectionOverdueInitialMessage());
            client.getClientAdminSettings()
                .setInspectionOverdueInitialThreshold(c.getInspectionOverdueInitialThreshold());
            client.getClientAdminSettings()
                .setInspectionOverdueNotificationEnabled(c.getInspectionOverdueNotificationEnabled());
            client.getClientAdminSettings()
                .setSecondInspectionOverdueNotificationEnabled(c.getSecondInspectionOverdueNotificationEnabled());
            client.getClientAdminSettings()
                .setInspectionOverdueSecondMessage(c.getInspectionOverdueSecondMessage());
            client.getClientAdminSettings()
                .setInspectionOverdueSecondThreshold(c.getInspectionOverdueSecondThreshold());
            client.getClientAdminSettings()
                .setProjectStatusEmailsEnabled(c.getProjectStatusEmailsEnabled());
            client.getClientAdminSettings()
                .setInspectionCertificationEnabled(c.getInspectionCertificationEnabled());
            client.getClientAdminSettings()
                .setInspectionCertificationMessage(c.getInspectionCertificationMessage());
            UserValue
                user =
                getUserValue(request);
            client.save(user);
        }
        catch (Exception e)
        {
            String
                strErr =
                strLog
                + ApplicationResources.getProperty("error.update.esc")
                + "<br>Message:<br> "
                + e.getMessage();
            LOG.debug(strErr);
            throw new Exception(strErr);
        }
    }

    private void updateClientClientType(Integer intClientId, Integer intClientTypeId)
        throws Exception
    {
        String
            strLog =
            strMod
            + "updateClientClientType() ";
        CapClientType
            capClientType =
            new CapClientType();
        CapClientTypeInfo
            capClientTypeInfo =
            new CapClientTypeInfo();
        LOG.debug(strLog
                  + "in method");
        try
        {
            capClientTypeInfo.setId(intClientTypeId);
            capClientTypeInfo.load();
            Collection
                clientTypeCollection =
                CapClientType.getClientTypeByClientId(intClientId);
            Iterator
                cti =
                clientTypeCollection.iterator();
            if (cti.hasNext())
            {
                LOG.debug(strLog
                          + "Updating client client type.  ClientId "
                          + intClientId.toString()
                          + " ClientTypeId "
                          + intClientTypeId.toString());
                capClientType =
                    (CapClientType) cti.next();
                capClientType.setClientTypeId(intClientTypeId);
                capClientType.update();
            }
            else
            {
                LOG.debug(strLog
                          + "Inserting client client type.  ClientId "
                          + intClientId.toString()
                          + " ClientTypeId "
                          + intClientTypeId.toString());
                capClientType.setClientId(intClientId);
                capClientType.setClientTypeId(intClientTypeId);
                capClientType.insert();
            }
            LOG.debug(strLog
                      + "Success.");
        }
        catch (Exception e)
        {
            String
                strErr =
                strLog
                + ApplicationResources.getProperty("error.update.client.type")
                + "<br>Message:<br> "
                + e.getMessage();
            LOG.debug(strErr);
            throw new Exception(strErr);
        }
    }
}