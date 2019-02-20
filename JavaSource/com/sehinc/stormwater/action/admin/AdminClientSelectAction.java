package com.sehinc.stormwater.action.admin;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminClientSelectAction
    extends AdminBaseAction
{
    public ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        int
            id =
            getParamInt("id",
                        request);
        ClientData
            clientData;
        if (id
            > 0)
        {
            clientData =
                ClientService.getActiveClientById(id);
            if (clientData
                == null)
            {
                addError(new ActionMessage("error.client.not.exist"), request);
                return mapping.findForward("admin.client.list.action");
            }
        }
        else
        {
            return mapping.findForward("admin.client.list.action");
        }
//        clearClientSessionKeys();
        ClientValue
            clientValue =
            new ClientValue(clientData);
        SessionService.setClientValue(request,
                                      CommonConstants.STORM_WATER_MODULE,
                                      clientValue);
        return mapping.findForward("continue");
    }

/*
    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
*/
}