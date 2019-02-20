package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionAction;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionReason;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.server.report.ReportService;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue;
import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ECProjectReportScriptlet
    extends JRDefaultScriptlet
{
    public String getInspector(Integer userId)
        throws JRScriptletException
    {
        String
            inspectorString;
        if (userId
            == null)
        {
            return null;
        }
        UserData
            inspector =
            UserService.getUser(userId);
        if (inspector
            == null)
        {
            return null;
        }
        inspectorString =
            inspector.getFirstName()
            + " "
            + inspector.getLastName();
        if (inspector.getTitle()
            != null)
        {
            inspectorString +=
                " ,"
                + inspector.getTitle();
        }
        if (inspector.getPrimaryPhone()
            != null)
        {
            inspectorString +=
                " "
                + inspector.getPrimaryPhone();
        }
        if (inspector.getEmailAddress()
            != null)
        {
            inspectorString +=
                "<br/> "
                + inspector.getEmailAddress();
        }
        return inspectorString;
    }

    public String getInspectionAction(Integer inspectionActionId)
        throws JRScriptletException
    {
        EcInspectionAction
            inspectionAction =
            new EcInspectionAction();
        inspectionAction.setId(inspectionActionId);
        if (inspectionAction.load())
        {
            return inspectionAction.getName();
        }
        return null;
    }

    public String getInspectionReason(Integer inspectionReasonId)
        throws JRScriptletException
    {
        EcInspectionReason
            inspectionReason =
            new EcInspectionReason();
        inspectionReason.setId(inspectionReasonId);
        if (inspectionReason.load())
        {
            return inspectionReason.getName();
        }
        return null;
    }

    public static String getContactTypeName(Integer contactTypeId)
        throws JRScriptletException
    {
        EcProjectContactType
            ecProjectContactType =
            new EcProjectContactType();
        ecProjectContactType.setId(contactTypeId);
        if (ecProjectContactType.load())
        {
            return ecProjectContactType.getName();
        }
        return null;
    }

    public static String getStatusCode(String statusCode)
        throws JRScriptletException
    {
        return StatusCodeData.getStatusCodeName(statusCode);
    }

    public Integer getInspectionBmpValueList(Integer inspectionId)
        throws JRScriptletException
    {
        List
            inspectionBmpValueList =
            ReportService.getInspectionBmpValueList(inspectionId);
        this.setVariableValue("InspectionBmpValueList",
                              inspectionBmpValueList);
        return new Integer(inspectionBmpValueList.size());
    }

    public InspectionBmpValue getInspectionBmpValue(Integer inspectionBmpId)
        throws JRScriptletException
    {
        InspectionBmpValue
            inspectionBmpValue =
            ReportService.getInspectionBmpValue(inspectionBmpId);
        this.setVariableValue("InspectionBmpValue",
                              inspectionBmpValue);
        return inspectionBmpValue;
    }

    public boolean getInspectionBmpDocument(Integer inspectionBmpDocumentId)
        throws JRScriptletException, IOException
    {
        File
            inspectionBmpDocument =
            ReportService.getInspectionBmpDocument(inspectionBmpDocumentId);
        this.setVariableValue("InspectionBmpDocument",
                              inspectionBmpDocument);
        return inspectionBmpDocument.exists();
    }

    public static ProjectValue getProject(Integer projectId)
        throws JRScriptletException
    {
        EcProject
            project =
            new EcProject();
        project.setId(projectId);
        if (project.load())
        {
            return new ProjectValue(project,
                                    false);
        }
        return null;
    }

    public ProjectContactValue getPermitAuthorityProjectContactValue(Integer projectId)
        throws JRScriptletException
    {
        return ProjectService.getProjectContactValueByProjectContactTypeCode(projectId,
                                                                             EcProjectContactType.PERMIT_AUTHORITY);
    }

    public ProjectContactValue getPermittedProjectContactValue(Integer projectId)
        throws JRScriptletException
    {
        return ProjectService.getProjectContactValueByProjectContactTypeCode(projectId,
                                                                             EcProjectContactType.PERMITTEE);
    }

    public ProjectContactValue getAuthorizedInspectorProjectContactValue(Integer projectId)
        throws JRScriptletException
    {
        return ProjectService.getProjectContactValueByProjectContactTypeCode(projectId,
                                                                             EcProjectContactType.AUTHORIZED_INSPECTOR);
    }

    public static String formatAddress(String address, String city, String state, String zipCode)
        throws JRScriptletException
    {
        return StringUtil.filterHTML((address
                                      + ((city
                                          != null
                                          && city
                                             != "")
                                             ? ", "
                                               + city
                                             : "")
                                      + ((state
                                          != null
                                          && state
                                             != "")
                                             ? ", "
                                               + state
                                             : "")
                                      + ((zipCode
                                          != null
                                          && zipCode
                                             != "")
                                             ? " "
                                               + zipCode
                                             : "")));
    }

    public static String filterHTML(String content)
    {
        //String result = null;
        //result = StringUtil.replace(content, "&", "&amp;amp;");
        //if (result == null) {
        //	return "";
        //}
        //return result;
        return content;
    }
}
