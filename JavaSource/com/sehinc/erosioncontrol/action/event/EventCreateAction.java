package com.sehinc.erosioncontrol.action.event;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.server.event.EventService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventCreateAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventCreateAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In EventCreateAction");
        EventCreateForm
            eventForm =
            (EventCreateForm) form;
        String
            strForward;
        if (eventForm.getNextPage()
            == null)
        {
            strForward =
                "error";
        }
        else if (eventForm.getNextPage()
                     .compareToIgnoreCase("next")
                 == 0)
        {
            if (eventForm.getIsComplianceByDate()
                    .booleanValue()
                && !eventForm.getEventType()
                .equals(EventService.OTHER_EVENT_TYPE))
            {
                if (eventForm.getComplianceDate()
                    .before(eventForm.getEventDate()))
                {
                    setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                        "The Compliance Date must be later than the Event Date.",
                                        request);
                    return mapping.getInputForward();
                }
            }
            setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                "",
                                request);
            strForward =
                "continue";
        }
        else if (eventForm.getNextPage()
                     .compareToIgnoreCase("cancel")
                 == 0)
        {
            setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                "Event creation canceled.",
                                request);
            strForward =
                "cancel";
        }
        else
        {
            setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                "Error creating event.",
                                request);
            strForward =
                "error";
        }
        LOG.info("EventCreateAction returning = "
                 + strForward);
        return mapping.findForward(strForward);
    }
}
