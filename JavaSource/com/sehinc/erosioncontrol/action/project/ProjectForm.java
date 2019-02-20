package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class ProjectForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectForm.class);
    // Fields
    private
    Integer
        id;
    private
    Integer
        ownerClientId;
    private
    String
        ownerClientName;
    private
    Integer
        permitAuthorityClientId;
    private
    String
        permitAuthorityClientName;
    private
    ProjectContactValue
        permitAuthorityClientContact;
    private
    Integer
        permittedClientId;
    private
    String
        permittedClientName;
    private
    ProjectContactValue
        permittedClientContact;
    private
    Integer
        authorizedInspectorClientId;
    private
    String
        authorizedInspectorClientName;
    private
    ProjectContactValue
        authorizedInspectorClientContact;
    private
    Integer
        inspectionTemplateId;
    private
    Integer
        projectTypeId;
    private
    String
        projectTypeName;
    private
    String
        name;
    private
    String
        description;
    private
    String
        parcelNumber;
    private
    String
        permitNumber;
    private
    String
        gisX;
    private
    String
        gisY;
    private
    String
        address;
    private
    String
        city;
    private
    String
        state;
    private
    String
        zip;
    private
    String
        areaSize;
    private
    Integer
        zoneId;
    private
    String
        zoneName;
    private
    String
        statusCode;
    private
    boolean
        isWorkInProgress =
        false;
    private
    Date
        effectiveDate;
    private
    Date
        startDate;
    private
    Date
        seedDate;
    private
    Float
        totalSiteArea;
    private
    String
        totalSiteAreaUnits;
    private
    Float
        disturbedArea;
    private
    String
        disturbedAreaUnits;
    private
    Float
        newImperviousArea;
    private
    String
        newImperviousAreaUnits;
    private
    String
        projectStatusCode;
    private
    String
        projectStatusCodeDesc;
    private
    String
        completedDate;
    private
    String
        rfaNumber;
    private
    String
        blockNumber;
    private
    String
        lotNumber;
    private
    String
        inspectionFrequency;
    private
    boolean
        isInspectionOverdueNotificationEnabled;
    private
    boolean
        isSecondInspectionOverdueNotificationEnabled;
    private
    Integer
        inspectionOverdueInitialThreshold;
    private
    String
        inspectionOverdueInitialMessage;
    private
    Integer
        inspectionOverdueSecondThreshold;
    private
    String
        inspectionOverdueSecondMessage;
    private
    ProjectBmpList
        bmps;
    private
    ProjectContactList
        contacts;
    private
    ProjectDocumentList
        documents;
    private
    String
        submit;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getOwnerClientId()
    {
        return this.ownerClientId;
    }

    public void setOwnerClientId(Integer ownerClientId)
    {
        this.ownerClientId =
            ownerClientId;
    }

    public String getOwnerClientName()
    {
        return this.ownerClientName;
    }

    public void setOwnerClientName(String ownerClientName)
    {
        this.ownerClientName =
            ownerClientName;
    }

    public Integer getPermitAuthorityClientId()
    {
        return this.permitAuthorityClientId;
    }

    public void setPermitAuthorityClientId(Integer permitAuthorityClientId)
    {
        this.permitAuthorityClientId =
            permitAuthorityClientId;
    }

    public String getPermitAuthorityClientName()
    {
        return this.permitAuthorityClientName;
    }

    public void setPermitAuthorityClientName(String permitAuthorityClientName)
    {
        this.permitAuthorityClientName =
            permitAuthorityClientName;
    }

    public ProjectContactValue getPermitAuthorityClientContact()
    {
        return this.permitAuthorityClientContact;
    }

    public void setPermitAuthorityClientContact(ProjectContactValue permitAuthorityClientContact)
    {
        this.permitAuthorityClientContact =
            permitAuthorityClientContact;
    }

    public Integer getPermittedClientId()
    {
        return this.permittedClientId;
    }

    public void setPermittedClientId(Integer permittedClientId)
    {
        this.permittedClientId =
            permittedClientId;
    }

    public String getPermittedClientName()
    {
        return this.permittedClientName;
    }

    public void setPermittedClientName(String permittedClientName)
    {
        this.permittedClientName =
            permittedClientName;
    }

    public ProjectContactValue getPermittedClientContact()
    {
        return this.permittedClientContact;
    }

    public void setPermittedClientContact(ProjectContactValue permittedClientContact)
    {
        this.permittedClientContact =
            permittedClientContact;
    }

    public Integer getAuthorizedInspectorClientId()
    {
        return this.authorizedInspectorClientId;
    }

    public void setAuthorizedInspectorClientId(Integer authorizedInspectorClientId)
    {
        this.authorizedInspectorClientId =
            authorizedInspectorClientId;
    }

    public String getAuthorizedInspectorClientName()
    {
        return this.authorizedInspectorClientName;
    }

    public void setAuthorizedInspectorClientName(String authorizedInspectorClientName)
    {
        this.authorizedInspectorClientName =
            authorizedInspectorClientName;
    }

    public ProjectContactValue getAuthorizedInspectorClientContact()
    {
        return this.authorizedInspectorClientContact;
    }

    public void setAuthorizedInspectorClientContact(ProjectContactValue authorizedInspectorClientContact)
    {
        this.authorizedInspectorClientContact =
            authorizedInspectorClientContact;
    }

    public Integer getInspectionTemplateId()
    {
        return this.inspectionTemplateId;
    }

    public void setInspectionTemplateId(Integer inspectionTemplateId)
    {
        this.inspectionTemplateId =
            inspectionTemplateId;
    }

    public Integer getProjectTypeId()
    {
        return this.projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId)
    {
        this.projectTypeId =
            projectTypeId;
    }

    public String getProjectTypeName()
    {
        return this.projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName)
    {
        this.projectTypeName =
            projectTypeName;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getEncodedName()
    {
        try
        {
            return URLEncoder.encode(this.name,
                                     "UTF-8");
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("ProjectForm.getEncodedName(): "
                      + uee.getMessage());
        }
        return null;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getViewDescription()
    {
        return StringUtil.filterHTML(this.description);
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getParcelNumber()
    {
        return this.parcelNumber;
    }

    public void setParcelNumber(String parcelNumber)
    {
        this.parcelNumber =
            parcelNumber;
    }

    public String getPermitNumber()
    {
        return this.permitNumber;
    }

    public void setPermitNumber(String permitNumber)
    {
        this.permitNumber =
            permitNumber;
    }

    public String getGisX()
    {
        return this.gisX;
    }

    public void setGisX(String gisX)
    {
        this.gisX =
            gisX;
    }

    public String getGisY()
    {
        return this.gisY;
    }

    public void setGisY(String gisY)
    {
        this.gisY =
            gisY;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address =
            address;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city =
            city;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state =
            state;
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setZip(String zip)
    {
        this.zip =
            zip;
    }

    public String getAreaSize()
    {
        return this.areaSize;
    }

    public void setAreaSize(String areaSize)
    {
        this.areaSize =
            areaSize;
    }

    public String getViewAddress()
    {
        return this.address
               + (StringUtil.isEmpty(this.city)
                      ? ""
                      : ", "
                        + this.city)
               + (StringUtil.isEmpty(this.state)
                      ? ""
                      : " "
                        + this.state)
               + (StringUtil.isEmpty(this.zip)
                      ? ""
                      : " "
                        + this.zip);
    }

    public Integer getZoneId()
    {
        return this.zoneId;
    }

    public void setZoneId(Integer zoneId)
    {
        this.zoneId =
            zoneId;
    }

    public String getZoneName()
    {
        return this.zoneName;
    }

    public void setZoneName(String zoneName)
    {
        this.zoneName =
            zoneName;
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }
    // TODO - JJM - Jun 27, 2008 - Should this be a dfferent code seems to be pulling double duty

    public String getStatusCodeName()
    {
        if (statusCode.equals(StatusCodeData.STATUS_CODE_INCOMPLETE))
        {
            return "Draft";
        }
        return StatusCodeData.getStatusCodeName(this.statusCode);
    }

    public void setStatusCodeName(String statusCodeName)
    {
        //no op
    }

    public boolean getIsWorkInProgress()
    {
        return this.isWorkInProgress;
    }

    public void setIsWorkInProgress(boolean isWorkInProgress)
    {
        this.isWorkInProgress =
            isWorkInProgress;
    }

    public String getIsWorkInProgressText()
    {
        if (isWorkInProgress)
        {
            return "true";
        }
        return "false";
    }

    public void setIsWorkInProgressText(String isWorkInProgress)
    {
        this.isWorkInProgress =
            isWorkInProgress
            != null
            && !isWorkInProgress.equals("")
            && isWorkInProgress.equalsIgnoreCase("true");
    }

    public ProjectBmpList getBmps()
    {
        return this.bmps;
    }

    public void setBmps(ProjectBmpList bmps)
    {
        this.bmps =
            bmps;
    }

    public ProjectContactList getContacts()
    {
        return this.contacts;
    }

    public void setContacts(ProjectContactList contacts)
    {
        this.contacts =
            contacts;
    }

    public ProjectDocumentList getDocuments()
    {
        return this.documents;
    }

    public void setDocuments(ProjectDocumentList documents)
    {
        this.documents =
            documents;
    }

    public String getSubmit()
    {
        return submit;
    }

    public void setSubmit(String submit)
    {
        this.submit =
            submit;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate =
            effectiveDate;
    }

    public String getEffectiveDateString()
    {
        return DateUtil.mdyDate(effectiveDate);
    }

    public void setEffectiveDateString(String effectiveDateString)
    {
        this.effectiveDate =
            DateUtil.parseDate(effectiveDateString);
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public String getStartDateString()
    {
        return DateUtil.mdyDate(startDate);
    }

    public void setStartDateString(String startDateString)
    {
        this.startDate =
            DateUtil.parseDate(startDateString);
    }

    public Date getSeedDate()
    {
        return seedDate;
    }

    public void setSeedDate(Date seedDate)
    {
        this.seedDate =
            seedDate;
    }

    public String getSeedDateString()
    {
        return DateUtil.mdyDate(seedDate);
    }

    public void setSeedDateString(String seedDateString)
    {
        this.seedDate =
            DateUtil.parseDate(seedDateString);
    }

    public Float getTotalSiteArea()
    {
        return totalSiteArea;
    }

    public void setTotalSiteArea(Float totalSiteArea)
    {
        this.totalSiteArea =
            totalSiteArea;
    }

    public String getTotalSiteAreaUnits()
    {
        return totalSiteAreaUnits;
    }

    public void setTotalSiteAreaUnits(String totalSiteAreaUnits)
    {
        this.totalSiteAreaUnits =
            totalSiteAreaUnits;
    }

    public Float getDisturbedArea()
    {
        return disturbedArea;
    }

    public void setDisturbedArea(Float disturbedArea)
    {
        this.disturbedArea =
            disturbedArea;
    }

    public String getDisturbedAreaUnits()
    {
        return disturbedAreaUnits;
    }

    public void setDisturbedAreaUnits(String disturbedAreaUnits)
    {
        this.disturbedAreaUnits =
            disturbedAreaUnits;
    }

    public Float getNewImperviousArea()
    {
        return newImperviousArea;
    }

    public void setNewImperviousArea(Float newImperviousArea)
    {
        this.newImperviousArea =
            newImperviousArea;
    }

    public String getNewImperviousAreaUnits()
    {
        return newImperviousAreaUnits;
    }

    public void setNewImperviousAreaUnits(String newImperviousAreaUnits)
    {
        this.newImperviousAreaUnits =
            newImperviousAreaUnits;
    }

    public String getProjectStatusCode()
    {
        return projectStatusCode;
    }

    public void setProjectStatusCode(String projectStatusCode)
    {
        this.projectStatusCode =
            projectStatusCode;
    }

    public String getCompletedDate()
    {
        return completedDate;
    }

    public void setCompletedDate(String completedDate)
    {
        this.completedDate =
            completedDate;
    }

    public String getProjectStatusCodeDesc()
    {
        return projectStatusCodeDesc;
    }

    public void setProjectStatusCodeDesc(String projectStatusCodeDesc)
    {
        this.projectStatusCodeDesc =
            projectStatusCodeDesc;
    }

    public String getBlockNumber()
    {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber)
    {
        this.blockNumber =
            blockNumber;
    }

    public String getLotNumber()
    {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber)
    {
        this.lotNumber =
            lotNumber;
    }

    public String getInspectionFrequency()
    {
        return inspectionFrequency;
    }

    public void setInspectionFrequency(String inspectionFrequency)
    {
        this.inspectionFrequency =
            inspectionFrequency;
    }

    public String getRfaNumber()
    {
        return rfaNumber;
    }

    public void setRfaNumber(String rfaNumber)
    {
        this.rfaNumber =
            rfaNumber;
    }

    public void reset()
    {
        this.id =
            null;
        this.ownerClientId =
            null;
        this.ownerClientName =
            null;
        this.permitAuthorityClientId =
            null;
        this.permitAuthorityClientName =
            null;
        this.permitAuthorityClientContact =
            new ProjectContactValue();
        this.permittedClientId =
            null;
        this.permittedClientName =
            null;
        this.permittedClientContact =
            new ProjectContactValue();
        this.authorizedInspectorClientId =
            null;
        this.authorizedInspectorClientName =
            null;
        this.authorizedInspectorClientContact =
            new ProjectContactValue();
        this.inspectionTemplateId =
            null;
        this.projectTypeId =
            null;
        this.name =
            null;
        this.description =
            null;
        this.parcelNumber =
            null;
        this.permitNumber =
            null;
        this.gisX =
            null;
        this.gisY =
            null;
        this.address =
            null;
        this.city =
            null;
        this.state =
            null;
        this.zip =
            null;
        this.areaSize =
            null;
        this.zoneId =
            null;
        this.zoneName =
            null;
        this.isWorkInProgress =
            false;
        this.statusCode =
            null;
        this.effectiveDate =
            null;
        this.startDate =
            null;
        this.seedDate =
            null;
        this.totalSiteArea =
            null;
        this.totalSiteAreaUnits =
            null;
        this.disturbedArea =
            null;
        this.disturbedAreaUnits =
            null;
        this.newImperviousArea =
            null;
        this.newImperviousAreaUnits =
            null;
        this.startDate =
            null;
        this.bmps =
            new ProjectBmpList();
        this.contacts =
            new ProjectContactList();
        this.documents =
            new ProjectDocumentList();
        this.submit =
            null;
        this.completedDate =
            null;
        this.projectStatusCode =
            null;
        this.projectStatusCodeDesc =
            null;
        this.rfaNumber =
            null;
        this.blockNumber =
            null;
        this.lotNumber =
            null;
        this.inspectionFrequency =
            null;
        this.isInspectionOverdueNotificationEnabled =
            false;
        this.isSecondInspectionOverdueNotificationEnabled =
            false;
        this.inspectionOverdueInitialThreshold =
            null;
        this.inspectionOverdueInitialMessage =
            null;
        this.inspectionOverdueSecondThreshold =
            null;
        this.inspectionOverdueSecondMessage =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (this.name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Project Name"));
        }
    }

    public boolean isInspectionOverdueNotificationEnabled()
    {
        return isInspectionOverdueNotificationEnabled;
    }

    public void setInspectionOverdueNotificationEnabled(boolean inspectionOverdueNotificationEnabled)
    {
        isInspectionOverdueNotificationEnabled =
            inspectionOverdueNotificationEnabled;
    }

    public boolean isSecondInspectionOverdueNotificationEnabled()
    {
        return isSecondInspectionOverdueNotificationEnabled;
    }

    public void setSecondInspectionOverdueNotificationEnabled(boolean secondInspectionOverdueNotificationEnabled)
    {
        isSecondInspectionOverdueNotificationEnabled =
            secondInspectionOverdueNotificationEnabled;
    }

    public Integer getInspectionOverdueInitialThreshold()
    {
        return inspectionOverdueInitialThreshold;
    }

    public void setInspectionOverdueInitialThreshold(Integer inspectionOverdueInitialThreshold)
    {
        this.inspectionOverdueInitialThreshold =
            inspectionOverdueInitialThreshold;
    }

    public String getInspectionOverdueInitialMessage()
    {
        return inspectionOverdueInitialMessage;
    }

    public void setInspectionOverdueInitialMessage(String inspectionOverdueInitialMessage)
    {
        this.inspectionOverdueInitialMessage =
            inspectionOverdueInitialMessage;
    }

    public Integer getInspectionOverdueSecondThreshold()
    {
        return inspectionOverdueSecondThreshold;
    }

    public void setInspectionOverdueSecondThreshold(Integer inspectionOverdueSecondThreshold)
    {
        this.inspectionOverdueSecondThreshold =
            inspectionOverdueSecondThreshold;
    }

    public String getInspectionOverdueSecondMessage()
    {
        return inspectionOverdueSecondMessage;
    }

    public void setInspectionOverdueSecondMessage(String inspectionOverdueSecondMessage)
    {
        this.inspectionOverdueSecondMessage =
            inspectionOverdueSecondMessage;
    }
}
