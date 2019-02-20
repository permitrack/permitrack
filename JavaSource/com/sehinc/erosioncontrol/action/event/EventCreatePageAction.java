package com.sehinc.erosioncontrol.action.event;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.event.EventService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EventCreatePageAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventCreatePageAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("*** EventCreatePageAction");
        EventCreateForm
            eventCreateForm =
            (EventCreateForm) form;
        eventCreateForm.setImmutable(false);
        eventCreateForm.reset();
        eventCreateForm.setImmutable(true);
        LOG.debug("done manually calling reset on the form");
        eventCreateForm.reset();
        setPageSetup("New Event",
                     "Create a New Event",
                     request);
        List
            types =
            EventService.getEventTypeList();
        setSessionAttribute(SessionKeys.EC_EVENT_TYPE_LIST,
                            types,
                            request);
        String
            eventMessage =
            "";
        setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                            eventMessage,
                            request);
        setSessionAttribute(SessionKeys.EC_USER_VALUE,
                            getUserValue(request),
                            request);
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.EVENT_CREATE_MENU_ITEM_NAME);
    }
}
