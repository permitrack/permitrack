package com.sehinc.erosioncontrol.db.user;

import com.sehinc.common.db.user.UserUpdatableData;
import org.apache.log4j.Logger;

public class EcSearch
    extends UserUpdatableData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcSearch.class);
    private
    String
        name =
        null;
    private
    String
        projectName =
        null;
    private
    String
        address =
        null;
    private
    String
        city =
        null;
    private
    String
        state =
        null;
    private
    String
        zip =
        null;
    private
    String
        zones =
        null;
    private
    String
        types =
        null;
    private
    String
        statuses =
        null;
    private
    String
        inspectionStatuses =
        null;
    private
    String
        permitNum =
        null;
    private
    String
        areaMin =
        null;
    private
    String
        areaMax =
        null;
    private
    String
        totalAreaMin =
        null;
    private
    String
        totalAreaMax =
        null;
    private
    String
        impAreaMin =
        null;
    private
    String
        impAreaMax =
        null;
    private
    String
        startDateA =
        null;
    private
    String
        startDateB =
        null;
    private
    String
        effDateA =
        null;
    private
    String
        effDateB =
        null;
    private
    String
        seedDateA =
        null;
    private
    String
        seedDateB =
        null;
    private
    String
        permitAuthName =
        null;
    private
    String
        permiteeName =
        null;
    private
    String
        inspectorName =
        null;

    public EcSearch()
    {
    }

    public EcSearch(Integer id)
    {
        setId(id);
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

    public String getAreaMax()
    {
        return areaMax;
    }

    public void setAreaMax(String areaMax)
    {
        this.areaMax =
            areaMax;
    }

    public String getAreaMin()
    {
        return areaMin;
    }

    public void setAreaMin(String areaMin)
    {
        this.areaMin =
            areaMin;
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

    public String getEffDateA()
    {
        return effDateA;
    }

    public void setEffDateA(String effDateA)
    {
        this.effDateA =
            effDateA;
    }

    public String getEffDateB()
    {
        return effDateB;
    }

    public void setEffDateB(String effDateB)
    {
        this.effDateB =
            effDateB;
    }

    public String getImpAreaMax()
    {
        return impAreaMax;
    }

    public void setImpAreaMax(String impAreaMax)
    {
        this.impAreaMax =
            impAreaMax;
    }

    public String getImpAreaMin()
    {
        return impAreaMin;
    }

    public void setImpAreaMin(String impAreaMin)
    {
        this.impAreaMin =
            impAreaMin;
    }

    public String getInspectorName()
    {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName)
    {
        this.inspectorName =
            inspectorName;
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

    public String getPermitAuthName()
    {
        return permitAuthName;
    }

    public void setPermitAuthName(String permitAuthName)
    {
        this.permitAuthName =
            permitAuthName;
    }

    public String getPermiteeName()
    {
        return permiteeName;
    }

    public void setPermiteeName(String permiteeName)
    {
        this.permiteeName =
            permiteeName;
    }

    public String getPermitNum()
    {
        return permitNum;
    }

    public void setPermitNum(String permitNum)
    {
        this.permitNum =
            permitNum;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName =
            projectName;
    }

    public String getSeedDateA()
    {
        return seedDateA;
    }

    public void setSeedDateA(String seedDateA)
    {
        this.seedDateA =
            seedDateA;
    }

    public String getSeedDateB()
    {
        return seedDateB;
    }

    public void setSeedDateB(String seedDateB)
    {
        this.seedDateB =
            seedDateB;
    }

    public String getStartDateA()
    {
        return startDateA;
    }

    public void setStartDateA(String startDateA)
    {
        this.startDateA =
            startDateA;
    }

    public String getStartDateB()
    {
        return startDateB;
    }

    public void setStartDateB(String startDateB)
    {
        this.startDateB =
            startDateB;
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

    public String getStatuses()
    {
        return statuses;
    }

    public void setStatuses(String statuses)
    {
        this.statuses =
            statuses;
    }

    public String getInspectionStatuses()
    {
        return inspectionStatuses;
    }

    public void setInspectionStatuses(String inspectionStatuses)
    {
        this.inspectionStatuses =
                inspectionStatuses;
    }

    public String getTotalAreaMax()
    {
        return totalAreaMax;
    }

    public void setTotalAreaMax(String totalAreaMax)
    {
        this.totalAreaMax =
            totalAreaMax;
    }

    public String getTotalAreaMin()
    {
        return totalAreaMin;
    }

    public void setTotalAreaMin(String totalAreaMin)
    {
        this.totalAreaMin =
            totalAreaMin;
    }

    public String getTypes()
    {
        return types;
    }

    public void setTypes(String types)
    {
        this.types =
            types;
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

    public String getZones()
    {
        return zones;
    }

    public void setZones(String zones)
    {
        this.zones =
            zones;
    }
}