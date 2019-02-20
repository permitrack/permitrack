package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.project.ProjectSearchForm;
import com.sehinc.erosioncontrol.server.event.EventService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class EventCreateProjectSearchAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventCreateProjectSearchAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In EventCreateProjectSearchAction");
        EventCreateForm
            eventForm =
            (EventCreateForm) form;
        if (eventForm.getNextPage()
            == null)
        {
            return mapping.findForward("error");
        }
        else if (eventForm.getNextPage()
                     .compareToIgnoreCase("next")
                 != 0)
        {
            return mapping.findForward(eventForm.getNextPage()
                                           .toLowerCase());
        }
        ClientValue
            clientValue =
            getClientValue(request);
        ProjectSearchForm
            projectSearchForm =
            eventForm.getProjectSearchForm();
        projectSearchForm.setClientId(clientValue.getId());
        List
            projects =
            EventService.getAllProjects(projectSearchForm);
        setSessionAttribute(SessionKeys.EC_PROJECT_LIST,
                            projects,
                            request);
        // MAKE ALL THE PROJECTS INITIALLY SELECTED
        String[]
            selectedProjects =
            new String[projects.size()];
        int
            i =
            0;
        for (
            Iterator
                iter =
                projects.iterator(); iter.hasNext(); i++)
        {
            ProjectValue
                project =
                (ProjectValue) iter.next();
            selectedProjects[i] =
                ""
                + project.getId();
        }
        setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                            i
                            + " Projects matched search criteria",
                            request);
        return mapping.findForward("continue");
    }
}
