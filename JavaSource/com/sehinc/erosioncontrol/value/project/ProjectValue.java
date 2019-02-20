package com.sehinc.erosioncontrol.value.project;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.NumberUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class ProjectValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectValue.class);
    private
    Integer
        projectId;
    private
    String
        projectType;
    private
    String
        name;
    private
    String
        zone;
    private
    String
        parcelNumber;
    private
    String
        permitNumber;
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
    String
        description;
    private
    String
        statusCode;
    private
    String
        status;
    private
    String
        gisX;
    private
    String
        gisY;
    private
    Date
        createTimestamp;
    private
    Date
        updateTimestamp;
    private
    Date
        lastInspectionDate;
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
        reportUrl;
    private
    String
        inspectionFormUrl;
    private
    String
        commentURL;
    private
    SecurityPermissionValue
        projectSecurityPermissionValue;
    private
    SecurityPermissionValue
        inspectionSecurityPermissionValue;
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

    public ProjectValue()
    {
    }

    public ProjectValue(EcProject project, boolean loadContacts)
    {
        this.projectId =
            project.getId();
        if (project.getProjectType()
            != null)
        {
            this.projectType =
                project.getProjectType()
                    .getName();
        }
        this.description =
            project.getDescription();
        this.name =
            project.getName();
        this.description =
            project.getDescription();
        if (project.getOwnerClient()
            != null)
        {
            this.ownerClientId =
                project.getOwnerClient()
                    .getId();
            this.ownerClientName =
                project.getOwnerClient()
                    .getName();
        }
        if (project.getPermitAuthorityClient()
            != null)
        {
            if (loadContacts
                == true)
            {
                this.permitAuthorityClientContact =
                    ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                                  EcProjectContactType.PERMIT_AUTHORITY);
            }
            this.permitAuthorityClientId =
                project.getPermitAuthorityClient()
                    .getId();
            this.permitAuthorityClientName =
                project.getPermitAuthorityClient()
                    .getName();
        }
        if (project.getPermittedClient()
            != null)
        {
            if (loadContacts
                == true)
            {
                this.permittedClientContact =
                    ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                                  EcProjectContactType.PERMITTEE);
            }
            this.permittedClientId =
                project.getPermittedClient()
                    .getId();
            this.permittedClientName =
                project.getPermittedClient()
                    .getName();
        }
        if (project.getAuthorizedInspectorClient()
            != null)
        {
            if (loadContacts)
            {
                this.authorizedInspectorClientContact =
                    ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                                  EcProjectContactType.AUTHORIZED_INSPECTOR);
            }
            this.authorizedInspectorClientId =
                project.getAuthorizedInspectorClient()
                    .getId();
            this.authorizedInspectorClientName =
                project.getAuthorizedInspectorClient()
                    .getName();
        }
        if (project.getZone()
            != null)
        {
            this.zone =
                project.getZone()
                    .getName();
        }
        this.parcelNumber =
            project.getParcelNumber();
        this.permitNumber =
            project.getPermitNumber();
        this.address =
            project.getAddress();
        this.city =
            project.getCity();
        this.state =
            project.getState();
        this.zip =
            project.getZip();
        this.gisX =
            project.getGisX();
        this.gisY =
            project.getGisY();
        this.lastInspectionDate =
            project.getLastInspectionDate();
        this.effectiveDate =
            project.getEffectiveDate();
        this.startDate =
            project.getStartDate();
        this.seedDate =
            project.getSeedDate();
        this.totalSiteArea =
            project.getTotalSiteArea();
        this.totalSiteAreaUnits =
            project.getTotalSiteAreaUnits();
        this.disturbedArea =
            project.getDisturbedArea();
        this.disturbedAreaUnits =
            project.getDisturbedAreaUnits();
        this.newImperviousArea =
            project.getNewImperviousArea();
        this.newImperviousAreaUnits =
            project.getNewImperviousAreaUnits();
        this.statusCode =
            project.getStatus()
                .getCode();
        this.createTimestamp =
            project.getCreateTimestamp();
        this.updateTimestamp =
            project.getUpdateTimestamp();
        this.rfaNumber =
            project.getRfaNumber();
        this.blockNumber =
            project.getBlockNumber();
        this.lotNumber =
            project.getLotNumber();
        this.inspectionFrequency =
            project.getInspectionFrequency();
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

    public ProjectValue(EcProject project, ClientValue clientValue, UserValue userValue)
    {
        this.projectId =
            project.getId();
        if (project.getProjectType()
            != null)
        {
            this.projectType =
                project.getProjectType()
                    .getName();
        }
        this.description =
            project.getDescription();
        this.name =
            project.getName();
        this.description =
            project.getDescription();
        if (project.getOwnerClient()
            != null)
        {
            this.ownerClientId =
                project.getOwnerClient()
                    .getId();
            this.ownerClientName =
                project.getOwnerClient()
                    .getName();
        }
        if (project.getPermitAuthorityClient()
            != null)
        {
            this.permitAuthorityClientContact =
                ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                              EcProjectContactType.PERMIT_AUTHORITY);
            this.permitAuthorityClientId =
                project.getPermitAuthorityClient()
                    .getId();
            this.permitAuthorityClientName =
                project.getPermitAuthorityClient()
                    .getName();
        }
        if (project.getPermittedClient()
            != null)
        {
            this.permittedClientContact =
                ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                              EcProjectContactType.PERMITTEE);
            this.permittedClientId =
                project.getPermittedClient()
                    .getId();
            this.permittedClientName =
                project.getPermittedClient()
                    .getName();
        }
        if (project.getAuthorizedInspectorClient()
            != null)
        {
            this.authorizedInspectorClientContact =
                ProjectService.getProjectContactValueByProjectContactTypeCode(this.projectId,
                                                                              EcProjectContactType.AUTHORIZED_INSPECTOR);
            this.authorizedInspectorClientId =
                project.getAuthorizedInspectorClient()
                    .getId();
            this.authorizedInspectorClientName =
                project.getAuthorizedInspectorClient()
                    .getName();
        }
        if (project.getZone()
            != null)
        {
            this.zone =
                project.getZone()
                    .getName();
        }
        this.parcelNumber =
            project.getParcelNumber();
        this.permitNumber =
            project.getPermitNumber();
        this.address =
            project.getAddress();
        this.city =
            project.getCity();
        this.state =
            project.getState();
        this.zip =
            project.getZip();
        this.gisX =
            project.getGisX();
        this.gisY =
            project.getGisY();
        this.lastInspectionDate =
            project.getLastInspectionDate();
        this.effectiveDate =
            project.getEffectiveDate();
        this.startDate =
            project.getStartDate();
        this.seedDate =
            project.getSeedDate();
        this.totalSiteArea =
            project.getTotalSiteArea();
        this.totalSiteAreaUnits =
            project.getTotalSiteAreaUnits();
        this.disturbedArea =
            project.getDisturbedArea();
        this.disturbedAreaUnits =
            project.getDisturbedAreaUnits();
        this.newImperviousArea =
            project.getNewImperviousArea();
        this.newImperviousAreaUnits =
            project.getNewImperviousAreaUnits();
        this.statusCode =
            project.getStatus()
                .getCode();
        this.createTimestamp =
            project.getCreateTimestamp();
        this.updateTimestamp =
            project.getUpdateTimestamp();
        setReportUrl(clientValue,
                     userValue);
        setInspectionFormUrl(clientValue,
                             userValue);
    }

    public void setId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectType(String projectType)
    {
        this.projectType =
            projectType;
    }

    public String getProjectType()
    {
        return projectType;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public void setZone(String zone)
    {
        this.zone =
            zone;
    }

    public String getZone()
    {
        return zone;
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

    public String getProjectTypeName()
    {
        return this.projectType;
    }

    public void setProjectTypeName(String projectType)
    {
        this.projectType =
            projectType;
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

    public String getAddress1()
    {
        return new String((StringUtil.isEmpty(this.address)
                               ? ""
                               : this.address)).trim();
    }

    public String getAddress2()
    {
        return new String((StringUtil.isEmpty(this.city)
                               ? ""
                               : this.city
                                 + ", ")
                          + (StringUtil.isEmpty(this.state)
                                 ? ""
                                 : this.state
                                   + " ")
                          + (StringUtil.isEmpty(this.zip)
                                 ? ""
                                 : this.zip)).trim();
    }

    public String getViewAddress()
    {
        return new String(this.address
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
                                   + this.zip));
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

    public String getStateCode()
    {
        return this.state;
    }

    public void setStateCode(String state)
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

    public Integer getOwnerClientId()
    {
        return ownerClientId;
    }

    public void setOwnerClientId(Integer ownerClientId)
    {
        this.ownerClientId =
            ownerClientId;
    }

    public void setOwnerClientName(String ownerClientName)
    {
        this.ownerClientName =
            ownerClientName;
    }

    public String getOwnerClientName()
    {
        return ownerClientName;
    }

    public Integer getPermitAuthorityClientId()
    {
        return permitAuthorityClientId;
    }

    public void setPermitAuthorityClientId(Integer permitAuthorityClientId)
    {
        this.permitAuthorityClientId =
            permitAuthorityClientId;
    }

    public void setPermitAuthorityClientName(String permitAuthorityClientName)
    {
        this.permitAuthorityClientName =
            permitAuthorityClientName;
    }

    public String getPermitAuthorityClientName()
    {
        return permitAuthorityClientName;
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
        return permittedClientId;
    }

    public void setPermittedClientId(Integer permittedClientId)
    {
        this.permittedClientId =
            permittedClientId;
    }

    public void setPermittedClientName(String permittedClientName)
    {
        this.permittedClientName =
            permittedClientName;
    }

    public String getPermittedClientName()
    {
        return permittedClientName;
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

    public void setAuthorizedInspectorClientName(String authorizedInspectorClientName)
    {
        this.authorizedInspectorClientName =
            authorizedInspectorClientName;
    }

    public String getAuthorizedInspectorClientName()
    {
        return authorizedInspectorClientName;
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

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public Date getLastInspectionDate()
    {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate)
    {
        this.lastInspectionDate =
            lastInspectionDate;
    }

    public String getLastInspectionDateString()
    {
        return DateUtil.mdyDate(lastInspectionDate);
    }

    public Date getCreateTimestamp()
    {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp)
    {
        this.createTimestamp =
            createTimestamp;
    }

    public Date getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp)
    {
        this.updateTimestamp =
            updateTimestamp;
    }

    public void setStatus(String status)
    {
    }

    public String getStatus()
    {
        if (StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode))
        {
            return StatusCodeData.getStatusCodeName(StatusCodeData.STATUS_CODE_ACTIVE);
        }
        else if (StatusCodeData.STATUS_CODE_INACTIVE
            .equals(statusCode))
        {
            return StatusCodeData.getStatusCodeName(StatusCodeData.STATUS_CODE_INACTIVE);
        }
        else if (StatusCodeData.STATUS_CODE_AUTO_ACTIVATE
            .equals(statusCode))
        {
            return StatusCodeData.getStatusCodeName(StatusCodeData.STATUS_CODE_AUTO_ACTIVATE);
        }
        return "Unknown";
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

    public String getTotalSiteAreaString()
    {
        return NumberUtil.formatFloatToString(totalSiteArea);
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

    public String getDisturbedAreaString()
    {
        return NumberUtil.formatFloatToString(disturbedArea);
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

    public String getNewImperviousAreaString()
    {
        return NumberUtil.formatFloatToString(newImperviousArea);
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

    public String getLotNumber()
    {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber)
    {
        this.lotNumber =
            lotNumber;
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

    public String getBlockNumber()
    {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber)
    {
        this.blockNumber =
            blockNumber;
    }

    public void setProjectSecurityPermissionValue(SecurityPermissionValue projectSecurityPermissionValue)
    {
        this.projectSecurityPermissionValue =
            projectSecurityPermissionValue;
    }

    public SecurityPermissionValue getProjectSecurityPermissionValue()
    {
        if (projectSecurityPermissionValue
            == null)
        {
            projectSecurityPermissionValue =
                new SecurityPermissionValue();
        }
        return projectSecurityPermissionValue;
    }

    public void setInspectionSecurityPermissionValue(SecurityPermissionValue inspectionSecurityPermissionValue)
    {
        this.inspectionSecurityPermissionValue =
            inspectionSecurityPermissionValue;
    }

    public SecurityPermissionValue getInspectionSecurityPermissionValue()
    {
        if (inspectionSecurityPermissionValue
            == null)
        {
            inspectionSecurityPermissionValue =
                new SecurityPermissionValue();
        }
        return inspectionSecurityPermissionValue;
    }

    public String getCommentURL()
    {
        return this.commentURL;
    }

    public void setCommentURL(String commentURL)
    {
        this.commentURL =
            commentURL;
    }

    public void setCommentUrl(Integer clientId, HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_SUBMIT_COMMENT,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientId.toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getId().toString()),
                                              "UTF-8"));
            setCommentURL(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating project URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
    }

    public String getReportUrl()
    {
        return this.reportUrl;
    }

    public void setReportUrl(String reportUrl)
    {
        this.reportUrl =
            reportUrl;
    }

    public void setReportUrl(ClientValue clientValue, UserValue userValue)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getProperty("base.url"));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_PROJECT_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientValue.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getProjectId().toString()),
                                              "UTF-8"));
            setReportUrl(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Project Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
    }

    public String getInspectionFormUrl()
    {
        return this.inspectionFormUrl;
    }

    public void setInspectionFormUrl(String inspectionFormUrl)
    {
        this.inspectionFormUrl =
            inspectionFormUrl;
    }

    public void setInspectionFormUrl(ClientValue clientValue, UserValue userValue)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getProperty("base.url"));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_FORM_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientValue.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getProjectId().toString()),
                                              "UTF-8"));
            setInspectionFormUrl(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Project Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\nprojectType="
                      + projectType);
        buffer.append("\nname="
                      + name);
        buffer.append("\nzone="
                      + zone);
        buffer.append("\npermitNumber="
                      + permitNumber);
        buffer.append("\npermitAuthroityClientName="
                      + permitAuthorityClientName);
        buffer.append("\npermittedClientName="
                      + permittedClientName);
        buffer.append("\nstatus="
                      + status);
        buffer.append("\n\n");
        return buffer.toString();
    }

    public String getEncryptedProjectId()
    {
        try
        {
            return CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                       getId().toString());
        }
        catch (CryptoException ce)
        {
        }
        return null;
    }

    public String getEncryptedOwnerClientId()
    {
        try
        {
            return CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                       getOwnerClientId().toString());
        }
        catch (CryptoException ce)
        {
        }
        return null;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProjectValue)
        {
            ProjectValue
                other =
                (ProjectValue) o;
            return other.getId()
                .equals(getId());
        }
        return false;
    }

    public String getCityStateZip()
    {
        String
            addressEtc =
            "";
        if (city
            != null)
        {
            if (city.length()
                > 0)
            {
                addressEtc =
                    city.toString()
                    + " ";
            }
        }
        if (state
            != null)
        {
            if (state.length()
                > 0)
            {
                addressEtc +=
                    state.toString()
                    + " ";
            }
        }
        if (zip
            != null)
        {
            if (zip.length()
                > 0)
            {
                addressEtc +=
                    zip.toString();
            }
        }
        return addressEtc;
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

    public boolean getInspectionOverdueNotificationEnabled()
    {
        return isInspectionOverdueNotificationEnabled;
    }

    public void setInspectionOverdueNotificationEnabled(boolean inspectionOverdueNotificationEnabled)
    {
        isInspectionOverdueNotificationEnabled =
            inspectionOverdueNotificationEnabled;
    }

    public boolean getSecondInspectionOverdueNotificationEnabled()
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
