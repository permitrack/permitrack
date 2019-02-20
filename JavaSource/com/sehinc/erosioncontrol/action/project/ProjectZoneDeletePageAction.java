package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectZoneDeletePageAction
    extends ProjectZoneBaseAction
{
    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ProjectZoneDeleteForm
            projectZoneDeleteForm =
            (ProjectZoneDeleteForm) form;
        //get the Client from the session
        ClientValue
            clientValue =
            getClientValue(request);
        // get the ID from the request
        Integer
            projectZoneId =
            null;
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
        /**
         * load the zone
         */
        EcZone
            projectZone =
            new EcZone();
        projectZone.setId(projectZoneId);
        projectZone.load();
        projectZoneDeleteForm.setId(projectZone.getId());
        projectZoneDeleteForm.setName(projectZone.getName());
        projectZoneDeleteForm.setDescription(projectZone.getDescription());
        // Set the session variable
        setSessionAttribute(SessionKeys.EC_ZONE_LIST,
                            ProjectService.getProjectZoneValueList(clientValue,
                                                                   projectZoneId),
                            request);
        return (mapping.findForward("continue"));
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setSecondaryMenuItem(request);
    }
    /*
        protected void setSecondaryMenu(HttpServletRequest request)
        {
            setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.ZONE_DELETE_MENU_NAME),
                             request);
        }

        protected void setSecondaryMenuItem(HttpServletRequest request)
        {
            getSecondaryMenu(request).setCurrentItem(SecondaryMenu.ZONE_DELETE_MENU_ITEM_NAME);
        }
    */
}