package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;

public class EcProjectSearchData
{
    private
    String
        projectId;
    private
    String
        name;
    private
    String
        permitNumber;
    private
    String
        permitAuthorityClientName;
    private
    String
        permittedClientName;
    private
    String
        lastInspectionDate;
    private
    String
        lastInspectionBmpStatus;
    private
    String
        disturbedArea;
    private
    String
        disturbedAreaUnits;
    private
    String
        projectStatusDescription;
    private
    String
        zone;
    private
    String
        projectType;
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
        startDate;
    private
    String
        effectiveDate;
    private
    String
        seedDate;
    private
    String
        totalSiteArea;
    private
    String
        totalSiteAreaUnits;
    private
    String
        newImperviousArea;
    private
    String
        newImperviousAreaUnits;
    private
    String
        authorizedInspectorClientName;
    private
    String
        ownerName;
    private
    SecurityPermissionValue
        projectSecurityPermissionValue;
    private
    SecurityPermissionValue
        inspectionSecurityPermissionValue;
    private
    String
        reportUrl;
    private
    String
        inspectionFormUrl;
    private
    String
        sortColumn;
    private
    String
        sortDirection;
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
    String
        inspectionCount;
    public static final
    String
        PROJECT_NAME =
        "project.name";
    public static final
    String
        PROJECT_TYPE =
        "type.name";
    public static final
    String
        ZONE_NAME =
        "zone.name";
    public static final
    String
        PERMIT_NUMBER =
        "project.permit_number";
    public static final
    String
        PERMIT_AUTH_NAME =
        "permit_auth.name";
    public static final
    String
        PERMITEE_NAME =
        "permitee.name";
    public static final
    String
        INSPECTOR_NAME =
        "inspector.name";
    public static final
    String
        ADDRESS =
        "project.address";
    public static final
    String
        LAST_INSPECTION_DATE =
        "last_final_inspections.inspection_date";
    public static final
    String
        START_DATE =
        "project.start_date";
    public static final
    String
        EFFECTIVE_DATE =
        "project.effective_date";
    public static final
    String
        SEED_DATE =
        "project.seed_date";
    public static final
    String
        TOTAL_SITE_AREA =
        "project.total_site_area";
    public static final
    String
        NEW_IMPERVIOUS_AREA =
        "project.new_impervious_area";
    public static final
    String
        DISTURBED_AREA =
        "project.disturbed_area";
    public static final
    String
        PROJECT_STATUS_DESCRIPTION =
        "project_status.description";
    public static final
    String
        CITY =
        "project.city";
    public static final
    String
        STATE =
        "project.state";
    public static final
    String
        ZIP =
        "project.zip";
    public static final
    String
        RFA_NUMBER =
        "project.rfa_number";
    public static final
    String
        BLOCK_NUMBER =
        "project.block_number";
    public static final
    String
        LOT_NUMBER =
        "project.lot_number";
    public static final
    String
        INSPECTION_FREQUENCY =
        "project.inspection_frequency";
    public static final
    String
        ASCENDING =
        "ASC";
    public static final
    String
        DESCENDING =
        "DESC";

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId =
            projectId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getPermitNumber()
    {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber)
    {
        this.permitNumber =
            permitNumber;
    }

    public String getPermitAuthorityClientName()
    {
        return permitAuthorityClientName;
    }

    public void setPermitAuthorityClientName(String permitAuthorityClientName)
    {
        this.permitAuthorityClientName =
            permitAuthorityClientName;
    }

    public String getPermittedClientName()
    {
        return permittedClientName;
    }

    public void setPermittedClientName(String permittedClientName)
    {
        this.permittedClientName =
            permittedClientName;
    }

    public String getLastInspectionDate()
    {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(String lastInspectionDate)
    {
        this.lastInspectionDate =
            lastInspectionDate;
    }

    public String getDisturbedArea()
    {
        return disturbedArea;
    }

    public void setDisturbedArea(String disturbedArea)
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

    public String getProjectStatusDescription()
    {
        return projectStatusDescription;
    }

    public void setProjectStatusDescription(String projectStatusDescription)
    {
        this.projectStatusDescription =
            projectStatusDescription;
    }

    public String getZone()
    {
        return zone;
    }

    public void setZone(String zone)
    {
        this.zone =
            zone;
    }

    public String getProjectType()
    {
        return projectType;
    }

    public void setProjectType(String projectType)
    {
        this.projectType =
            projectType;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address =
            address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city =
            city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state =
            state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip =
            zip;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate =
            startDate;
    }

    public String getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate)
    {
        this.effectiveDate =
            effectiveDate;
    }

    public String getSeedDate()
    {
        return seedDate;
    }

    public void setSeedDate(String seedDate)
    {
        this.seedDate =
            seedDate;
    }

    public String getTotalSiteArea()
    {
        return totalSiteArea;
    }

    public void setTotalSiteArea(String totalSiteArea)
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

    public String getNewImperviousArea()
    {
        return newImperviousArea;
    }

    public void setNewImperviousArea(String newImperviousArea)
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

    public String getAuthorizedInspectorClientName()
    {
        return authorizedInspectorClientName;
    }

    public void setAuthorizedInspectorClientName(String authorizedInspectorClientName)
    {
        this.authorizedInspectorClientName =
            authorizedInspectorClientName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName =
            ownerName;
    }

    public SecurityPermissionValue getProjectSecurityPermissionValue()
    {
        return projectSecurityPermissionValue;
    }

    public void setProjectSecurityPermissionValue(SecurityPermissionValue projectSecurityPermissionValue)
    {
        this.projectSecurityPermissionValue =
            projectSecurityPermissionValue;
    }

    public SecurityPermissionValue getInspectionSecurityPermissionValue()
    {
        return inspectionSecurityPermissionValue;
    }

    public void setInspectionSecurityPermissionValue(SecurityPermissionValue inspectionSecurityPermissionValue)
    {
        this.inspectionSecurityPermissionValue =
            inspectionSecurityPermissionValue;
    }

    public String getReportUrl()
    {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl)
    {
        this.reportUrl =
            reportUrl;
    }

    public String getInspectionFormUrl()
    {
        return inspectionFormUrl;
    }

    public void setInspectionFormUrl(String inspectionFormUrl)
    {
        this.inspectionFormUrl =
            inspectionFormUrl;
    }

    public String getCityStateZip()
    {
        return ((StringUtil.isEmpty(this.city)
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

    public String getSortColumn()
    {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn)
    {
        this.sortColumn =
            sortColumn;
    }

    public String getSortDirection()
    {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection)
    {
        this.sortDirection =
            sortDirection;
    }

    public String getLastInspectionBmpStatus()
    {
        return lastInspectionBmpStatus;
    }

    public void setLastInspectionBmpStatus(String lastInspectionBmpStatus)
    {
        if (lastInspectionBmpStatus
            == null)
        {
            this.lastInspectionBmpStatus =
                "";
        }
        else
        {
            this.lastInspectionBmpStatus =
                lastInspectionBmpStatus;
        }
    }

    public String getLastInspectionDateAndBmpStatus()
    {
        return lastInspectionDate
               + " "
               + lastInspectionBmpStatus;
    }

    public String getBmpStatusImage()
    {
        if (lastInspectionBmpStatus.equals("PASS"))
        {
            return "/sehsvc/img/icons/sehinspect.png";
        }
        else if (lastInspectionBmpStatus.equals("FAIL"))
        {
            return "/sehsvc/img/icons/sehdelete.png";
        }
        else if (lastInspectionBmpStatus.equals("WARN"))
        {
            return "/sehsvc/img/icons/sehwarning.png";
        }
        else
        {
            return "";
        }
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

    public String getRfaNumber()
    {
        return rfaNumber;
    }

    public void setRfaNumber(String rfaNumber)
    {
        this.rfaNumber =
            rfaNumber;
    }

    public void setInspectionCount(String inspectionCount)
    {
        this.inspectionCount =
            inspectionCount;
    }

    public String getInspectionCount()
    {
        return inspectionCount;
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
}
