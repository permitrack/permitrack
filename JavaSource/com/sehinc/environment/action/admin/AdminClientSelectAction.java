package com.sehinc.environment.action.admin;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminClientSelectAction
    extends AdminBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AdminClientSelectAction.class);

    public ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        int
            clientId =
            BaseActionUnsecure.getParamInt("id",
                                           request);
        if (clientId
            > 0)
        {
            ClientData
                clientData =
                ClientService.getActiveClientById(clientId);
            if (clientData
                == null)
            {
                LOG.error(PortalResources.getProperty("error.client.not.exist"));
                addError(new ActionMessage("error.client.not.exist"), request);
                return mapping.getInputForward();
            }
            ClientValue
                clientValue =
                new ClientValue(clientData);
            setSessionAttribute(SessionKeys.ENV_CLIENT,
                                clientValue,
                                request);
            return mapping.findForward("continue");
        }
        LOG.error(ApplicationResources.getProperty("error.no.primary.client.selected"));
        addError(new ActionMessage("error.no.primary.client.selected"), request);
        return mapping.getInputForward();
    }
}