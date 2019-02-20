package com.sehinc.erosioncontrol.action.admin;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.security.action.client.ClientListPageAction;
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
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (securityManager.getIsSystemAdministrator())
        {
            if (request.getParameter("partners")
                != null)
            {
                setRequestAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST,
                                    ClientService.getActiveClientsByModuleAndType(CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE),
                                                                                  CommonConstants.SECONDARY_CLIENT),
                                    request);
            }
            else
            {
                List
                    lstClient =
                    ClientService.getActiveClientsByModuleAndType(CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE),
                                                                  CommonConstants.PRIMARY_CLIENT);
                lstClient =
                    ClientListPageAction.updateClientInfo(lstClient);

                setRequestAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST,
                                    lstClient,
                                    request);
            }
        }
        else
        {
            List
                lstClient =
                ClientService.getClients(securityManager.getUserID());

            lstClient =
                ClientListPageAction.updateClientInfo(lstClient);

            setRequestAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST,
                                lstClient,
                                request);

        }
        return mapping.findForward("continue");
    }
}