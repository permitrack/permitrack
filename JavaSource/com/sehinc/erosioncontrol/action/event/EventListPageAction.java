package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.event.EventService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EventListPageAction
    extends EventBaseAction
{
    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        setPageSetup("Event List",
                     "List of events associated with this client, with most recent events shown first",
                     request);
        ClientValue
            clientValue =
            getClientValue(request);
        String
            eventMessage =
            "";
        setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                            eventMessage,
                            request);
        List
            projectEventList =
            EventService.getEventValueList(clientValue);
        setSessionAttribute(SessionKeys.EC_EVENT_LIST,
                            projectEventList,
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.EVENT_LIST_MENU_ITEM_NAME);
    }
}