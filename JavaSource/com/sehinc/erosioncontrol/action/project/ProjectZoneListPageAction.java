package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ProjectZoneListPageAction
    extends ProjectZoneBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectZoneListPageAction.class);

    public ActionForward projectZoneAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        //get the Client value from the session
        ClientValue
            clientValue =
            getClientValue(request);
        // get the list of zones for this client
        List
            projectZoneList =
            ProjectService.getProjectZoneValueList(clientValue);
        LOG.debug("projectZoneList.size()="
                  + projectZoneList.size());
        //Save the list of all zones in the session
        setSessionAttribute(SessionKeys.EC_ZONE_LIST,
                            projectZoneList,
                            request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        setSessionAttribute(SessionKeys.ZONE_UPDATE,
                            new Boolean(securityManager.HasECPermission(SecureObjectPermissionData.ZONE_UPDATE)),
                            request);
        setSessionAttribute(SessionKeys.ZONE_DELETE,
                            new Boolean(securityManager.HasECPermission(SecureObjectPermissionData.ZONE_DELETE)),
                            request);
        return mapping.findForward("continue");
    }

}