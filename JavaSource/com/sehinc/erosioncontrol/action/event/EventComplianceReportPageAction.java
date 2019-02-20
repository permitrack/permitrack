package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.event.EcEvent;
import com.sehinc.erosioncontrol.db.event.EcEventDocument;
import com.sehinc.erosioncontrol.server.event.EventService;
import com.sehinc.erosioncontrol.value.event.EventDocumentValue;
import com.sehinc.erosioncontrol.value.event.EventValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EventComplianceReportPageAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventComplianceReportPageAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In EventComplianceReportPageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            userValue =
            getUserValue(request);
        setPageSetup("Event Compliance Report",
                     "Projects associated with this event that are compliant and not compliant",
                     request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        Integer
            eventId =
            null;
        if (request.getParameter(RequestKeys.EC_EVENT_ID)
            != null)
        {
            eventId =
                new Integer(request.getParameter(RequestKeys.EC_EVENT_ID));
        }
        else
        {
            EventValue
                eventValue =
                (EventValue) request.getSession()
                    .getAttribute(SessionKeys.EC_EVENT);
            if (eventValue
                != null)
            {
                eventId =
                    eventValue.getId();
            }
        }
        if (eventId
            == null)
        {
            setPageSetup("Event List",
                         "ERROR! Could not locate requested Event.",
                         request);
            return mapping.findForward("event.list.page");
        }
        EcEvent
            ecEvent =
            new EcEvent(eventId);
        ecEvent.load();
        EcEventDocument
            eventDocument =
            EcEventDocument.findByEventId(eventId);
        EventDocumentValue
            eventDocumentValue =
            new EventDocumentValue(eventDocument);
        if (eventDocument
            != null)
        {
            eventDocumentValue.setClientId(ecEvent.getClient()
                                               .getId());
            eventDocumentValue.setLocation(eventDocument.getLocation());
            eventDocumentValue.setUsername(userValue.getUsername());
            eventDocumentValue.setDownloadURL(eventDocumentValue.getDownloadURL(request));
        }
        request.setAttribute(RequestKeys.EC_EVENT_DOCUMENT_VALUE,
                             eventDocumentValue);
        setSessionAttribute(SessionKeys.EC_EVENT_DOCUMENT_VALUE,
                            eventDocumentValue,
                            request);
        EventValue
            eventValue =
            new EventValue(ecEvent);
        request.setAttribute(RequestKeys.EC_EVENT_VALUE,
                             eventValue);
        setSessionAttribute(SessionKeys.EC_EVENT_VALUE,
                            eventValue,
                            request);
        List
            compliantProjectsList =
            EventService.determineCompliantProjects(clientValue,
                                                    eventValue,
                                                    securityManager);
        List
            nonCompliantProjectsList =
            EventService.determineNonCompliantProjects(clientValue,
                                                       eventValue,
                                                       securityManager);
        request.getSession()
            .setAttribute(SessionKeys.EC_COMPLIANT_PROJECT_LIST,
                          compliantProjectsList);
        request.getSession()
            .setAttribute(SessionKeys.EC_NONCOMPLIANT_PROJECT_LIST,
                          nonCompliantProjectsList);
        String
            eventMessage =
            "There are "
            + nonCompliantProjectsList.size()
            + " non-compliant projects and "
            + compliantProjectsList.size()
            + " compliant projects."
            + " Compliance deadline is "
            + eventValue.getComplianceDateString();
        request.getSession()
            .setAttribute(SessionKeys.EC_EVENT_MESSAGE,
                          eventMessage);
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.EVENT_COMPLIANCE_REPORT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.EVENT_COMPLIANCE_REPORT_MENU_NAME),
                         request);
    }
}
