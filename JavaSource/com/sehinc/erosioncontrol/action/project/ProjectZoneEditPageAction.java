package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.project.EcZone;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectZoneEditPageAction
    extends ProjectZoneBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectZoneEditPageAction.class);

    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        ProjectZoneForm
            projectZoneForm =
            (ProjectZoneForm) form;
        LOG.info("In ProjectZoneEditPageAction");
        //get the Client from the session
        // get the ID from the request
        Integer
            projectZoneId;
        if (request.getParameter(RequestKeys.EC_PROJECT_ZONE_ID)
            != null)
        {
            projectZoneId =
                new Integer(request.getParameter(RequestKeys.EC_PROJECT_ZONE_ID));
        }
        else
        {
            return mapping.findForward("project.zone.list.page");
        }
        EcZone
            projectZone =
            new EcZone();
        projectZone.setId(projectZoneId);
        projectZone.load();
        projectZoneForm.setId(projectZone.getId());
        projectZoneForm.setClientId(projectZone.getClientId());
        projectZoneForm.setName(projectZone.getName());
        projectZoneForm.setDescription(projectZone.getDescription());
        return (mapping.findForward("continue"));
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.ZONE_EDIT_MENU_NAME),
                         request);
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.ZONE_EDIT_MENU_ITEM_NAME);
    }

}