package com.sehinc.erosioncontrol.action.admin;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
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
            id =
            BaseActionUnsecure.getParamInt("id",
                                           request);
        if (id
            > 0)
        {
            ClientData
                clientData =
                ClientService.getActiveClientById(id);
            if (clientData
                == null)
            {
                LOG.error(PortalResources.getProperty("error.client.not.exist"));
                addError(new ActionMessage("error.client.not.exist"),
                         request);
                return mapping.getInputForward();
            }
            ClientValue
                clientValue =
                new ClientValue(clientData);
            clearClientSessionKeys(request);
            setSessionAttribute(SessionKeys.ESC_CLIENT,
                                clientValue,
                                request);
            /*
                        setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                            NavigationService.getPrimaryMenu(request),
                                            request);
            */
            return mapping.findForward("continue");
        }
        else
        {
            return mapping.findForward("admin.client.list.action");
        }
    }

    private void clearClientSessionKeys(HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.USER_QUERY,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_AUTHORIZED_PROJECT_LIST,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_LIST_CURRENT_PAGE,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_USER_PROJECT_LIST_ITEMS,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_SORT_HASHTABLE,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_SORT_METHOD,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_LIST_PROJECTS_PER_PAGE,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EC_BMP,
                            null,
                            request);
    }
}