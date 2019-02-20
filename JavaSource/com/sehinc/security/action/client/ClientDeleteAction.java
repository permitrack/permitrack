package com.sehinc.security.action.client;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class ClientDeleteAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientDeleteAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientDeleteAction. ";
        String
            strLog =
            new String(strMod
                       + "ClientDeleteAction() ");
        ClientForm
            objClientDelete;
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("create.client.unauthorized"));
            addError(new ActionMessage("create.client.unauthorized"), request);
            return mapping.findForward("client.view.page");
        }
        objClientDelete =
            (ClientForm) form;
        Integer
            clientId =
            objClientDelete.getId();
        if (clientId.intValue()
            == 0)
        {
            LOG.info(ApplicationResources.getProperty("delete.client.failed.id"));
            addError(new ActionMessage("delete.client.failed.id"), request);
            return mapping.findForward("client.view.page");
        }
        ClientData
            sehMain =
            ClientData.findSEHClient();
        if (sehMain
            != null)
        {
            if (sehMain.getId()
                .equals(clientId))
            {
                LOG.debug(ApplicationResources.getProperty("delete.client.SEH"));
                addError(new ActionMessage("delete.client.SEH"), request);
                return mapping.findForward("client.view.page");
            }
        }
        try
        {
            objClientDelete.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
            objClientDelete.setCanAccessStormWater(false);
            objClientDelete.setCanAccessErosionControl(false);
            objClientDelete.setCanAccessDataView(false);
            objClientDelete.setCanAccessEnvironment(false);
            ClientData
                objClientData =
                new ClientData(clientId);
            objClientData.load();
            objClientData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
            objClientData.update(getUserValue(request));
        }
        catch (Exception s)
        {
            LOG.debug(strLog
                      + ApplicationResources.getProperty("delete.client.failed")
                      + "<br>Message:<br> "
                      + s.getMessage());
            addError(new ActionMessage("delete.client.failed"), request);
            return mapping.findForward("client.view.page");
        }
        List
            userList =
            UserData.findByClientIdAndStatusCode(clientId,
                                                 StatusCodeData.STATUS_CODE_ACTIVE);
        Iterator
            ui =
            userList.iterator();
        while (ui.hasNext())
        {
            UserValue
                userValue =
                (UserValue) ui.next();
            Integer
                userId =
                userValue.getId();
            UserData
                userData =
                new UserData(userId);
            if (userData.load())
            {
                userData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                userData.update(getUserValue(request));
                LOG.info(strLog
                         + "User "
                         + userId
                         + " deleted.");
            }
        }
        addMessage(new ActionMessage("delete.client.success",
                                     clientId.toString()), request);
        return mapping.findForward("continue");
    }
}