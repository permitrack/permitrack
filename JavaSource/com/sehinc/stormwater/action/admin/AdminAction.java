package com.sehinc.stormwater.action.admin;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.security.action.client.ClientListPageAction;
import com.sehinc.stormwater.action.base.SessionKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAction
    extends AdminBaseAction
{
    public ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        removeSessionAttribute(SessionKeys.MS4_CLIENT, request);
        SecurityManager
            securityManager = getSecurityManager(request);
        if (securityManager.getIsSystemAdministrator())
        {
            List
                lstClient =
                ClientService.getActiveClientsByModule(CapModule.findByCode(CommonConstants.STORM_WATER_MODULE));
            lstClient =
                ClientListPageAction.updateClientInfo(lstClient);

            setRequestAttribute(SessionKeys.SW_ADMIN_CLIENT_SELECT_LIST,
                                lstClient,
                                request);
        }
        else
        {
            List
                lstClient =
                ClientService.getClients(securityManager.getUserID());

            lstClient =
                ClientListPageAction.updateClientInfo(lstClient);

            setRequestAttribute(SessionKeys.SW_ADMIN_CLIENT_SELECT_LIST,
                                lstClient,
                                request);
        }
        return mapping.findForward("continue");
    }
}