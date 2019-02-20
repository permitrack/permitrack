package com.sehinc.erosioncontrol.value.project;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.project.EcProject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ProjectMapValue
    implements java.io.Serializable
{
    public static final
    int
        GREEN_STATUS =
        1;
    public static final
    int
        YELLOW_STATUS =
        2;
    public static final
    int
        RED_STATUS =
        3;
    public static final
    int
        BLACK_STATUS =
        4;
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectMapValue.class);
    private
    Integer
        id;
    private
    String
        name;
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
    Integer
        permittedClientId;
    private
    String
        permittedClientName;
    private
    String
        parcelNumber;
    private
    String
        permitNumber;
    private
    String
        latitude;
    private
    String
        longitude;
    private
    String
        address;
    private
    String
        city;
    private
    String
        stateCode;
    private
    String
        zip;
    private
    String
        url;
    private
    String
        commentURL;
    private
    boolean
        lastInspectionFailed;
    private
    int
        inspectionStatus;
    private static final
    int
        TIER_TWO_STATUS_DAYS =
        13;
    private static final
    int
        TIER_ONE_STATUS_DAYS =
        7;

    public int getInspectionStatus()
    {
        return inspectionStatus;
    }

    public void setInspectionStatus(int inspectionStatus)
    {
        this.inspectionStatus =
            inspectionStatus;
    }

    public boolean isLastInspectionFailed()
    {
        return lastInspectionFailed;
    }

    public void setLastInspectionFailed(boolean lastInspectionFailed)
    {
        this.lastInspectionFailed =
            lastInspectionFailed;
    }

    public ProjectMapValue()
    {
    }

    public ProjectMapValue(EcProject project, String detailLevel, ClientData client)
    {
        this.id =
            project.getId();
        this.name =
            project.getName();
        this.ownerClientId =
            project.getOwnerClient()
                .getId();
        this.ownerClientName =
            project.getOwnerClient()
                .getName();
        this.permitAuthorityClientId =
            project.getPermitAuthorityClient()
                .getId();
        this.permitAuthorityClientName =
            project.getPermitAuthorityClient()
                .getName();
        this.permittedClientId =
            project.getPermittedClient()
                .getId();
        this.permittedClientName =
            project.getPermittedClient()
                .getName();
        this.parcelNumber =
            project.getParcelNumber();
        this.permitNumber =
            project.getPermitNumber();
        this.latitude =
            project.getGisY();
        this.longitude =
            project.getGisX();
        this.address =
            project.getAddress();
        this.city =
            project.getCity();
        this.stateCode =
            project.getState();
        this.zip =
            project.getZip();
        this.lastInspectionFailed =
            project.lastInspectionFailed();
        int
            tierOneStatusDays =
            getTierOneStatusDays(client,
                                 project);
        int
            tierTwoStatusDays =
            getTierTwoStatusDays(client,
                                 project);
        if (detailLevel.equals(RequestKeys.FULL_DETAIL))
        {
            if (project.daysSinceLastInspection()
                == null
                || project.daysSinceLastInspection()
                   >= tierTwoStatusDays
                || lastInspectionFailed)
            {
                if(lastInspectionFailed)
                {
                    this.inspectionStatus =
                        RED_STATUS;
                }
                else
                {
                    this.inspectionStatus =
                        BLACK_STATUS;
                }
            }
            else if (project.daysSinceLastInspection()
                     >= tierOneStatusDays)
            {
                this.inspectionStatus =
                    YELLOW_STATUS;
            }
            else
            {
                this.inspectionStatus =
                    GREEN_STATUS;
            }
        }
        else
        {
            if (lastInspectionFailed)
            {
                this.inspectionStatus =
                    RED_STATUS;
            }
            else
            {
                this.inspectionStatus =
                    GREEN_STATUS;
            }
        }
    }

    private int getTierOneStatusDays(ClientData client, EcProject project)
    {
        if (project.getInspectionOverdueNotificationEnabled())
        {
            if (project.getInspectionOverdueInitialThreshold()
                != null)
            {
                return project.getInspectionOverdueInitialThreshold();
            }
        }
        if (client.getClientAdminSettings()
            .isInspectionOverdueNotificationEnabled())
        {
            if (client.getClientAdminSettings()
                    .getInspectionOverdueInitialThreshold()
                != null)
            {
                return client.getClientAdminSettings()
                    .getInspectionOverdueInitialThreshold();
            }
        }

        return ProjectMapValue.TIER_ONE_STATUS_DAYS;
    }

    private int getTierTwoStatusDays(ClientData client, EcProject project)
    {
        if (project.getSecondInspectionOverdueNotificationEnabled())
        {
            if (project.getInspectionOverdueSecondThreshold()
                != null)
            {
                return project.getInspectionOverdueSecondThreshold();
            }
        }
        if (client.getClientAdminSettings()
            .isSecondInspectionOverdueNotificationEnabled())
        {
            if (client.getClientAdminSettings()
                    .getInspectionOverdueSecondThreshold()
                != null)
            {
                return client.getClientAdminSettings()
                    .getInspectionOverdueSecondThreshold();
            }
        }

        return ProjectMapValue.TIER_TWO_STATUS_DAYS;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public Integer getOwnerClientId()
    {
        return this.ownerClientId;
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
        return this.permitAuthorityClientId;
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

    public Integer getPermittedClientId()
    {
        return this.permittedClientId;
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

    public String getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude =
            latitude;
    }

    public String getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude =
            longitude;
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

    public String getStateCode()
    {
        return this.stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode =
            stateCode;
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

    public String getViewAddress()
    {
        return StringUtil.filterHTML(this.address
                                     + (StringUtil.isEmpty(this.city)
                                            ? ""
                                            : ", "
                                              + this.city)
                                     + (StringUtil.isEmpty(this.stateCode)
                                            ? ""
                                            : " "
                                              + this.stateCode)
                                     + (StringUtil.isEmpty(this.zip)
                                            ? ""
                                            : " "
                                              + this.zip));
    }

    public String getViewCity()
    {
        return StringUtil.filterHTML((StringUtil.isEmpty(this.city)
                                          ? ""
                                          : ", "
                                            + this.city)
                                     + (StringUtil.isEmpty(this.stateCode)
                                            ? ""
                                            : " "
                                              + this.stateCode)
                                     + (StringUtil.isEmpty(this.zip)
                                            ? ""
                                            : " "
                                              + this.zip));
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
            LOG.debug("Generating comment url for projectId: "
                      + getId());
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

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url =
            url;
    }

    public void setUrl(Integer clientId, HttpServletRequest request)
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
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_LIST_ACTION,
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
            LOG.debug("Generating project url for projectId: "
                      + getId());
            setUrl(buffer.toString());
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

    public boolean equals(Object o)
    {
        if (o instanceof ProjectMapValue)
        {
            ProjectMapValue
                other =
                (ProjectMapValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}