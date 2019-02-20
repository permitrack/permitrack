/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: DefaultAction.java,v $
 *
 */

package com.sehinc.dataview.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(DefaultAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        LOG.debug("DefaultAction.doAction()");
        // Get the userValue from the session
        UserValue
            userValue =
            getUserValue(request);
        // Get the clientValue from the session
        ClientValue
            clientValue =
            getClientValue(request);
        // Get the group ID in order to identify if the user is a sys admin
        int
            groupId;
        if (userValue.getGroupId()
            != null)
        {
            groupId =
                userValue.getGroupId();
        }
        else
        {
            groupId =
                GroupService.USER_GROUP_ID;
        }
        LOG.debug("groupId="
                  + groupId);
        if (groupId
            == GroupService.SYSTEM_ADMINISTRATOR_GROUP_ID)
        {
            LOG.debug("User is a SYSADMIN!");
            // Set the security group in the session so that we know this
            // user is a SYSTEM_ADMINISTRATOR
            setSessionAttribute(SessionKeys.SECURITY_GROUP_ID,
                                String.valueOf(GroupService.SYSTEM_ADMINISTRATOR_GROUP_ID), request);
            // Set the client list in the session so the user can choose
            // which client they would like to view
            setSessionAttribute(SessionKeys.DV_ADMIN_CLIENT_SELECT_LIST,
                                ClientService.getActiveClientsByModule(CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE)), request);
            // Forward to the client select page if the user is a SYSTEM_ADMINISTRATOR
            return mapping.findForward("client.select.page");
        }
        // This user is not a SYSTEM_ADMINISTRATOR, so forward them to the page
        // from which they can access their DataViewOnline site
        LOG.debug("USER IS NOT A SYSADMIN!");
        // Set the client ID in the request variable DV_CLIENT_ID
        // The jsp will use this to set the cookie
        request.setAttribute(RequestKeys.DV_CLIENT_ID,
                             clientValue.getId()
                                 .toString());
        LOG.debug("Set dvClientId="
                  + clientValue.getId()
            .toString()
                  + " in request");
        // forward to the front page
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPortalMenu(request);
    }
}