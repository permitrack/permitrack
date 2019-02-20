package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class InspectionEmailPageAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionEmailPageAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        Integer
            inspectionId =
            getInspectionId(request);
/*
        if (request.getParameter(RequestKeys.EC_INSPECTION_ID)
            != null)
        {
            inspectionId =
                new Integer(request.getParameter(RequestKeys.EC_INSPECTION_ID));
        }
        else
        {
            InspectionValue
                inspectionValue =
                (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                      request);
            inspectionId =
                inspectionValue.getId();
        }
*/
        if (inspectionId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.not.found.in.session"));
            addError(new ActionMessage("inspection.error.inspection.not.found.in.session"),
                     request);
            return (mapping.findForward("inspection.list.page"));
        }
        EcInspection
            inspection =
            new EcInspection(inspectionId);
        try
        {
            if (!inspection.load())
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionId};
            LOG.error(ApplicationResources.getProperty("inspection.error.loading.inspection",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("inspection.error.loading.inspection",
                                       parameters),
                     request);
            return (mapping.findForward("inspection.list.page"));
        }
        EcProject
            project =
            new EcProject(inspection.getProjectId());
        try
        {
            if (!project.load())
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspection.getProjectId()};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        List
            projectContactList =
            ProjectService.getProjectContactValueListAll(project.getId());
        if (projectContactList
            == null
            || projectContactList.isEmpty())
        {
            return mapping.findForward("inspection.list.page");
        }
        setSessionAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                            projectContactList,
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION,
                            new InspectionValue(inspection),
                            request);
        return mapping.findForward("continue");
    }

}