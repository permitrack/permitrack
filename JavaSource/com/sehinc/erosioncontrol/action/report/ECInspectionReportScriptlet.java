package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.user.UserService;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionAction;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionReason;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.server.report.ReportService;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ECInspectionReportScriptlet
    extends JRDefaultScriptlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(ECInspectionReportScriptlet.class);

    public void afterGroupInit(String groupName)
        throws JRScriptletException
    {
        if (groupName.equals("BMPNameGroup"))
        {
            this.setVariableValue("GoalNumber",
                                  new Integer(1));
        }
    }

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
                ", "
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
                "\n"
                + inspector.getEmailAddress();
        }
        return inspectorString;
    }

    public String getInspectorByInspection(Integer inspectionId)
        throws JRScriptletException
    {
        String
            inspectorString;
        if (inspectionId
            == null)
        {
            return null;
        }
        EcInspection
            inspection =
            new EcInspection(inspectionId);
        inspection.load();
        CapContact
            inspector =
            inspection.getInspector();
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
                ", "
                + inspector.getTitle();
        }
        if (inspector.getPrimaryPhone()
            != null)
        {
            inspectorString +=
                " "
                + inspector.getPrimaryPhone();
        }
        if (inspector.getEmail()
            != null)
        {
            inspectorString +=
                "\n"
                + inspector.getEmail();
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

    public String getStatusCode(String statusCode)
        throws JRScriptletException
    {
        // TODO - JJM - Jun 27, 2008 - should this use a different code this seems tobe pulling double duty, plus this could use a convience method
        if (statusCode.equals(StatusCodeData.STATUS_CODE_INCOMPLETE))
        {
            return "Draft";
        }
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
        //LOG.debug("Scriptlet: getInspectionBmpValue(" + inspectionBmpId + ")\n" + inspectionBmpValue.toString());
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
        if (inspectionBmpDocument
            == null)
        {
            return false;
        }
        return inspectionBmpDocument.exists();
    }

    public ProjectValue getProject(Integer projectId)
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

    public String getCheckedImage()
        throws JRScriptletException
    {
        return ApplicationProperties.getProperty("base.document.directory")
               + "/img/icons/checkedbox.gif";
    }

    public String getUncheckedImage()
        throws JRScriptletException
    {
        return ApplicationProperties.getProperty("base.document.directory")
               + "/img/icons/uncheckedbox.gif";
    }
}
