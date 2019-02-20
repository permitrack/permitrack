package com.sehinc.security.action.client;

import com.sehinc.common.db.client.DvClientInformation;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ClientDVEditAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientDVEditAction.class);
    private static
    String
        strMod =
        "com.sehinc.security.action.client.ClientDVEditAction. ";

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        ClientDVForm
            objDV;
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("edit.dataview.unauthorized"));
            addError(new ActionMessage("edit.dataview.unauthorized"), request);
            return mapping.findForward("client.dv.view.page");
        }
        if (isCancelled(request))
        {
            LOG.info(ApplicationResources.getProperty("cancel.edit.dataview"));
            addError(new ActionMessage("cancel.edit.dataview"), request);
            return mapping.findForward("client.dv.view.page");
        }
        try
        {
            objDV =
                (ClientDVForm) form;
            updateClientDV(objDV);
            LOG.debug(strLog
                      + "Client DataView Information has been updated for client id "
                      + objDV.getClientId()
                .toString());
        }
        catch (Exception e)
        {
            LOG.debug(strLog
                      + ApplicationResources.getProperty("error.update.dataview")
                      + "<br>Message:<br> "
                      + e.getMessage());
            addError(new ActionMessage("error.update.dataview"), request);
            return mapping.findForward("client.dv.view.page");
        }
        LOG.debug(strLog
                  + "strForward = "
                  + "client.dv.view.page");
        return mapping.findForward("client.dv.view.page");
    }

    public static void updateClientDV(ClientDVForm c)
        throws Exception
    {
        String
            strLog =
            new String(strMod
                       + "updateClientDV() ");
        try
        {
            Integer
                intClientId =
                c.getClientId();
            List
                dvInfoList =
                DvClientInformation.findByClientId(intClientId);
            Iterator
                dvi =
                dvInfoList.iterator();
            if (dvi.hasNext())
            {
                LOG.debug(strLog
                          + "Updating existing client values.");
                DvClientInformation
                    dvInfo =
                    (DvClientInformation) dvi.next();
                dvInfo.setClientFullName(c.getClientFullName());
                dvInfo.setClientName(c.getClientName());
                dvInfo.setImsService(c.getImsService());
                dvInfo.setImsOvService(c.getImsOvService());
                dvInfo.setDownLoads(c.getDownLoads());
                dvInfo.setAttachmentLayers(c.getAttachmentLayers());
                dvInfo.setStartTop(c.getStartTop());
                dvInfo.setStartLeft(c.getStartLeft());
                dvInfo.setStartRight(c.getStartRight());
                dvInfo.setStartBottom(c.getStartBottom());
                dvInfo.setLimitTop(c.getLimitTop());
                dvInfo.setLimitLeft(c.getLimitLeft());
                dvInfo.setLimitRight(c.getLimitRight());
                dvInfo.setLimitBottom(c.getLimitBottom());
                dvInfo.update();
                LOG.debug(strLog
                          + "Client DataView Information values updated for id "
                          + dvInfo.getId()
                    .toString());
            }
            else
            {
                LOG.debug(strLog
                          + "Client does not have DataView setup values associated with it.");
                DvClientInformation
                    dvInfo =
                    new DvClientInformation();
                dvInfo.setId(c.getClientId()); // Per documentation by Nathan Yourchuck the id and the client id need to be identical.
                dvInfo.setClientId(c.getClientId());
                dvInfo.setClientFullName(c.getClientFullName());
                dvInfo.setClientName(c.getClientName());
                dvInfo.setImsService(c.getImsService());
                dvInfo.setImsOvService(c.getImsOvService());
                dvInfo.setDownLoads(c.getDownLoads());
                dvInfo.setAttachmentLayers(c.getAttachmentLayers());
                dvInfo.setStartTop(c.getStartTop());
                dvInfo.setStartLeft(c.getStartLeft());
                dvInfo.setStartRight(c.getStartRight());
                dvInfo.setStartBottom(c.getStartBottom());
                dvInfo.setLimitTop(c.getLimitTop());
                dvInfo.setLimitLeft(c.getLimitLeft());
                dvInfo.setLimitRight(c.getLimitRight());
                dvInfo.setLimitBottom(c.getLimitBottom());
                dvInfo.insert();
                LOG.debug("******"
                          + dvInfo.getId());
                LOG.debug("******"
                          + dvInfo.getClientId()
                    .toString()
                          + " "
                          + dvInfo.getClientFullName()
                          + " "
                          + dvInfo.getClientName()
                          + " "
                          + dvInfo.getImsService()
                          + " "
                          + dvInfo.getImsOvService()
                          + " ");
                LOG.debug("******"
                          + dvInfo.getDownLoads()
                          + " "
                          + dvInfo.getAttachmentLayers()
                          + " "
                          + dvInfo.getStartTop()
                          + " "
                          + dvInfo.getStartBottom()
                          + " "
                          + dvInfo.getStartLeft()
                          + " "
                          + dvInfo.getStartRight());
                LOG.debug("******"
                          + dvInfo.getLimitTop()
                          + " "
                          + dvInfo.getLimitBottom()
                          + " "
                          + dvInfo.getLimitLeft()
                          + " "
                          + dvInfo.getLimitRight());
                LOG.debug(strLog
                          + "New client DataView values inserted.  New Id "
                          + dvInfo.getId()
                    .toString());
            }
        }
        catch (Exception e)
        {
            String
                strErr =
                new String(strLog
                           + ApplicationResources.getProperty("error.update.dataview")
                           + "<br>Message:<br> "
                           + e.getMessage());
            LOG.debug(strErr);
            throw new Exception(strErr);
        }
    }
}