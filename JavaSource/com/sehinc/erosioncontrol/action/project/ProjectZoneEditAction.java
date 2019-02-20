package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.db.project.EcZone;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectZoneEditAction
    extends ProjectZoneBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectZoneEditAction.class);

    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        ProjectZoneForm
            projectZoneForm =
            (ProjectZoneForm) form;
        //        LOG.info("In ProjectZoneEditAction");
        // process if the user canceled
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            projectZoneForm.reset();
            return (mapping.findForward("project.zone.list.page"));
        }
        //get the Client from the session
        ClientValue
            clientValue =
            getClientValue(request);
        EcZone
            projectZone =
            new EcZone();
        if (projectZoneForm.getId()
            != null
            && projectZoneForm.getId()
                   .intValue()
               > 0)
        {
            // Check to see if this zone name already exists
            EcZone
                duplicateZone =
                EcZone.findDuplicateByName(clientValue.getId(),
                                                    projectZoneForm.getId(),
                                                    projectZoneForm.getName());
            if (duplicateZone
                != null)
            {
                addError(new ActionMessage("project.zone.duplicate.name"),
                         request);
                return mapping.getInputForward();
            }
            projectZone.setId(projectZoneForm.getId());
            projectZone.load();
        }
        else
        {
            addError(new ActionMessage("project.zone.edit.failed"),
                     request);
            return mapping.findForward("project.zone.list.page");
        }
        projectZone.setName(projectZoneForm.getName());
        projectZone.setDescription(projectZoneForm.getDescription());
        if (projectZone.getId()
            == null)
        {
            projectZone.insert();
        }
        else
        {
            projectZone.update();
        }
        return (mapping.findForward("continue"));
    }
}