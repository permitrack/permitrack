package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionOverdueEmailLogEntry;
import com.sehinc.erosioncontrol.event.ProjectStatusChangeEvent;
import org.apache.log4j.Logger;

import java.util.*;

public class EcProject
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProject.class);
    private
    ClientData
        ownerClient;
    private
    ClientData
        permitAuthorityClient;
    private
    ClientData
        permittedClient;
    private
    ClientData
        authorizedInspectorClient;
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
    EcZone
        zone;
    private
    EcProjectType
        projectType;
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
    Collection
        inspections;
    private
    ProjectStatusCodeData
        projectStatus;
    private
    Date
        completedDate;
    private
    Set<EcInspectionOverdueEmailLogEntry>
        overdueLogEntries =
        new HashSet<EcInspectionOverdueEmailLogEntry>();
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
    Integer
        inspectionOverdueInitialThreshold;
    private
    String
        inspectionOverdueInitialMessage;
    private
    boolean
        isSecondInspectionOverdueNotificationEnabled;
    private
    Integer
        inspectionOverdueSecondThreshold;
    private
    String
        inspectionOverdueSecondMessage;

    public String getInspectionOverdueSecondMessage()
    {
        return inspectionOverdueSecondMessage;
    }

    public void setInspectionOverdueSecondMessage(String inspectionOverdueSecondMessage)
    {
        this.inspectionOverdueSecondMessage =
            inspectionOverdueSecondMessage;
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

    public boolean getSecondInspectionOverdueNotificationEnabled()
    {
        return isSecondInspectionOverdueNotificationEnabled;
    }

    public void setSecondInspectionOverdueNotificationEnabled(boolean secondInspectionOverdueNotificationEnabled)
    {
        isSecondInspectionOverdueNotificationEnabled =
            secondInspectionOverdueNotificationEnabled;
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

    public Integer getInspectionOverdueInitialThreshold()
    {
        return inspectionOverdueInitialThreshold;
    }

    public void setInspectionOverdueInitialThreshold(Integer inspectionOverdueInitialThreshold)
    {
        this.inspectionOverdueInitialThreshold =
            inspectionOverdueInitialThreshold;
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

    public String getInspectionFrequency()
    {
        return inspectionFrequency;
    }

    public void setInspectionFrequency(String inspectionFrequency)
    {
        this.inspectionFrequency =
            inspectionFrequency;
    }

    public Set<EcInspectionOverdueEmailLogEntry> getOverdueLogEntries()
    {
        return overdueLogEntries;
    }

    public void setOverdueLogEntries(Set<EcInspectionOverdueEmailLogEntry> overdueLogEntries)
    {
        this.overdueLogEntries =
            overdueLogEntries;
    }

    public ProjectStatusCodeData getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatusCodeData projectStatus)
    {
        if (this.projectStatus
            != null
            && !this.projectStatus
            .equals(projectStatus))
        {
            ProjectStatusChangeEvent
                event =
                new ProjectStatusChangeEvent(this,
                                             this.projectStatus,
                                             projectStatus);
            SpringServiceLocator.publishEvent(event);
        }
        this.projectStatus =
            projectStatus;
    }

    public void setProjectStatus(String statusCode)
    {
        List<CodeData>
            list =
            this.findAll(ProjectStatusCodeData.class);
        for (CodeData code : list)
        {
            if (statusCode.equals(code.getCode()))
            {
                setProjectStatus((ProjectStatusCodeData) code);
                break;
            }
        }
    }

    public EcProject()
    {
    }

    public EcProject(Integer id)
    {
        setId(id);
    }

    public ClientData getOwnerClient()
    {
        return this.ownerClient;
    }

    public void setOwnerClient(ClientData ownerClient)
    {
        this.ownerClient =
            ownerClient;
    }

    public ClientData getPermitAuthorityClient()
    {
        return this.permitAuthorityClient;
    }

    public void setPermitAuthorityClient(ClientData permitAuthorityClient)
    {
        this.permitAuthorityClient =
            permitAuthorityClient;
    }

    public ClientData getPermittedClient()
    {
        return this.permittedClient;
    }

    public void setPermittedClient(ClientData permittedClient)
    {
        this.permittedClient =
            permittedClient;
    }

    public ClientData getAuthorizedInspectorClient()
    {
        return this.authorizedInspectorClient;
    }

    public void setAuthorizedInspectorClient(ClientData authorizedInspectorClient)
    {
        this.authorizedInspectorClient =
            authorizedInspectorClient;
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

    public String getDescription()
    {
        return this.description;
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

    public EcZone getZone()
    {
        return this.zone;
    }

    public void setZone(EcZone zone)
    {
        this.zone =
            zone;
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

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate =
            effectiveDate;
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

    public Date getSeedDate()
    {
        return seedDate;
    }

    public void setSeedDate(Date seedDate)
    {
        this.seedDate =
            seedDate;
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

    public EcProjectType getProjectType()
    {
        return this.projectType;
    }

    public void setProjectType(EcProjectType projectType)
    {
        this.projectType =
            projectType;
    }

    public Collection getInspections()
    {
        return this.inspections;
    }

    public void setInspections(Collection inspections)
    {
        this.inspections =
            inspections;
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

    public static EcProject findById(Integer id)
    {
        EcProject
            project;
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from EcProject as data where data.id =?";
        project =
            (EcProject) HibernateUtil.findUnique(queryString,
                                                 parameters);
        return project;
    }

    public static List findActiveByOwner(Integer ownerClientId)
    {
        Object
            parameters
            [
            ] =
            {
                ownerClientId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from EcProject as data where data.ownerClientId =? and data.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static Integer zoneCountByOwnerClientId(Integer zoneId, Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                zoneId};
        String
            queryString =
            "select new java.lang.Integer(count(data)) from EcProject as data where data.ownerClientId = ? and data.zone.id = ?";
        return (Integer) HibernateUtil.findUnique(queryString,
                                                  parameters);
    }

    public static List findByZoneId(Integer zoneId)
    {
        Object
            parameters
            [
            ] =
            {zoneId};
        String
            queryString =
            "select data from EcProject as data where data.zone.id = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllAuthorizedInspectorByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcProject as data where data.authorizedInspectorClient.id =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllPermittedClientByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcProject as data where data.permittedClient.id =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllPermitAuthorityClientByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcProject as data where data.permitAuthorityClient.id =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllOwnerClientByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcProject as data where data.ownerClient.id =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public Date getCompletedDate()
    {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate)
    {
        this.completedDate =
            completedDate;
    }

    public EcInspection getLastInspection()
    {
        EcInspection
            lastInspection =
            null;
        List<EcInspection>
            finalInspections =
            getFinalInspections();
        if (finalInspections
            != null
            && finalInspections.size()
               > 0)
        {
            for (Object obj : finalInspections)
            {
                EcInspection
                    inspection =
                    (EcInspection) obj;
                if (inspection
                    != null
                    && inspection.getStatus()
                       != null
                    && StatusCodeData.STATUS_CODE_ACTIVE
                    .equals(inspection.getStatus()
                                .getCode()))
                {
                    if (lastInspection
                        == null)
                    {
                        lastInspection =
                            inspection;
                    }
                    else
                    {
                        if (lastInspection.getInspectionDate()
                            .before(inspection.getInspectionDate()))
                        {
                            lastInspection =
                                inspection;
                        }
                    }
                }
            }
        }
        return lastInspection;
    }

    private List<EcInspection> getFinalInspections()
    {
        ArrayList<EcInspection>
            list =
            new ArrayList<EcInspection>();
        if (inspections
            != null)
        {
            for (Object obj : inspections)
            {
                if (obj instanceof EcInspection)
                {
                    EcInspection
                        inspection =
                        (EcInspection) obj;
                    if (StatusCodeData.STATUS_CODE_ACTIVE
                        .equals(inspection.getStatus()
                                    .getCode()))
                    {
                        list.add(inspection);
                    }
                }
            }
        }
        return list;
    }

    public boolean lastInspectionFailed()
    {
        EcInspection
            inspection =
            getLastInspection();
        if (inspection
            != null)
        {
            return inspection.isFailed() == 1;
        }
        return false;
    }

    public Long daysSinceLastInspection()
    {
        EcInspection
            inspection =
            getLastInspection();
        if (inspection
            == null
            || inspection.getInspectionDate()
               == null)
        {
            return null;
        }
        Calendar
            cal =
            Calendar.getInstance();
        long
            diffInMilis =
            cal.getTimeInMillis()
            - inspection.getInspectionDate()
                .getTime();
        Long
            diffInDays =
            diffInMilis
            / (1000
               * 60
               * 60
               * 24);
        return diffInDays;
    }
}