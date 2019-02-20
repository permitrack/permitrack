package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectZoneDeleteAction
    extends ProjectZoneBaseAction
{
    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        ProjectZoneDeleteForm
            projectZoneDeleteForm =
            (ProjectZoneDeleteForm) form;
        // process if the user canceled
        if (isCancelled(request))
        {
            projectZoneDeleteForm.reset();
            return (mapping.findForward("project.zone.list.page"));
        }
        //get the User from the session
        UserValue
            userValue =
            getUserValue(request);
        //get the Client from the session
        ClientValue
            clientValue =
            getClientValue(request);
        if (projectZoneDeleteForm.getDeleteOption()
            != null
            && projectZoneDeleteForm.getDeleteOption()
            .equals("delete"))
        {
            EcZone
                projectZone =
                new EcZone();
            if (projectZoneDeleteForm.getId()
                != null
                && projectZoneDeleteForm.getId()
                       .intValue()
                   > 0)
            {
                projectZone.setId(projectZoneDeleteForm.getId());
                projectZone.load();
            }
            else
            {
                addError(new ActionMessage("project.zone.delete.failed"),
                         request);
                return mapping.getInputForward();
            }
            // Update the zoneId for all projects with the old zone to 1 (N/A)
            ProjectService.replaceZone(clientValue.getId(),
                                       projectZoneDeleteForm.getId(),
                                       new Integer(1),
                                       userValue);
            // Then delete the old zone
            projectZone.delete();
        }
        else if (projectZoneDeleteForm.getDeleteOption()
                 != null
                 && projectZoneDeleteForm.getDeleteOption()
            .equals("replace"))
        {
            EcZone
                projectZone =
                new EcZone();
            if (projectZoneDeleteForm.getProjectZoneReplaceId()
                == null
                || projectZoneDeleteForm.getProjectZoneReplaceId()
                       .intValue()
                   == 0)
            {
                addError(new ActionMessage("project.zone.delete.failed"),
                         request);
                return mapping.getInputForward();
            }
            if (projectZoneDeleteForm.getId()
                != null
                && projectZoneDeleteForm.getId()
                       .intValue()
                   > 0)
            {
                projectZone.setId(projectZoneDeleteForm.getId());
                projectZone.load();
            }
            else
            {
                addError(new ActionMessage("project.zone.delete.failed"),
                         request);
                return mapping.getInputForward();
            }
            // Update the zoneId for all projects with the old zone to the new zone
            ProjectService.replaceZone(clientValue.getId(),
                                       projectZoneDeleteForm.getId(),
                                       projectZoneDeleteForm.getProjectZoneReplaceId(),
                                       userValue);
            // Then delete the old zone
            projectZone.delete();
        }
        else
        {
            addError(new ActionMessage("project.zone.delete.failed"),
                     request);
            return mapping.getInputForward();
        }
        return (mapping.findForward("continue"));
    }
}