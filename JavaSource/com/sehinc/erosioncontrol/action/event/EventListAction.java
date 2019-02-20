package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.value.client.ClientValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventListAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventListAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ClientValue
            clientValue =
            getClientValue(request);
        LOG.debug("***** EventListAction - clientId = "
                  + clientValue.getId()
            .toString());
        LOG.debug("***** EventListAction - clientName = "
                  + clientValue.getName());
        return mapping.findForward("continue");
    }
}