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

public class ProjectZoneCreateAction
    extends ProjectZoneBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectZoneCreateAction.class);

    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        ProjectZoneForm
            projectZoneForm =
            (ProjectZoneForm) form;
        LOG.info("In ProjectZoneCreateAction");
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
        // Check to see if this zone name already exists
        EcZone
            duplicateZone =
            EcZone.findDuplicateByName(clientValue.getId(),
                                                projectZoneForm.getName());
        if (duplicateZone
            != null)
        {
            addError(new ActionMessage("project.zone.duplicate.name"),
                     request);
            return mapping.getInputForward();
        }
        EcZone
            projectZone =
            new EcZone();
        projectZone.setClientId(clientValue.getId());
        projectZone.setName(projectZoneForm.getName());
        projectZone.setDescription(projectZoneForm.getDescription());
        projectZone.insert();
        return (mapping.findForward("continue"));
    }
}