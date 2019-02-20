package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.portal.resources.PortalResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class InspectionDeleteAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionDeleteAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        LOG.info("In InspectionDeleteAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            inspectionForm.reset();
            return mapping.findForward("inspection.view.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            LOG.error(PortalResources.getProperty("error.no.user.in.session"));
            addError(new ActionMessage("error.no.user.in.session"),
                     request);
            return mapping.findForward(CommonConstants.FORWARD_ERROR);
        }
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            return mapping.findForward("project.list.page");
        }
        EcInspection
            inspection =
            new EcInspection();
        inspection.setId(getInspectionId(request));
        if (!inspection.load())
        {
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.not.found.in.session"));
            addError(new ActionMessage("inspection.error.inspection.not.found.in.session"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        InspectionValue
            inspectionValue =
            new InspectionValue(inspection);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (inspectionValue.getStatus()
                .equals("Final")
            && !securityManager.getIsSystemAdministrator())
        {
            addError(new ActionMessage("inspection.error.already.final.delete"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        if (!securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_DELETE,
                                             projectValue.getId()))
        {
            addError(new ActionMessage("inspection.delete.no.access"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        inspection.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        inspection.save(userValue);
        addMessage(new ActionMessage("inspection.delete.success"),
                   request);
        setSessionAttribute(SessionKeys.EC_INSPECTION,
                            null,
                            request);
        return (mapping.findForward("continue"));
    }
}