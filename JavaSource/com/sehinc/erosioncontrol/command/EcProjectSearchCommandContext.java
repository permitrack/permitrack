package com.sehinc.erosioncontrol.command;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EcProjectSearchCommandContext
{
    private
    List<EcProjectSearchData>
        results;
    private
    String
        projectName;
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
        permitNumber;
    private
    String
        areaSizeMin;
    private
    String
        areaSizeMax;
    private
    String
        totalAreaSizeMin;
    private
    String
        totalAreaSizeMax;
    private
    String
        impAreaSizeMin;
    private
    String
        impAreaSizeMax;
    private
    String
        startDateA;
    private
    String
        startDateB;
    private
    String
        effDateA;
    private
    String
        effDateB;
    private
    String
        seedDateA;
    private
    String
        seedDateB;
    private
    String
        permitAuthName;
    private
    String
        permiteeName;
    private
    String
        inspectorName;
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
    List<String>
        orderColumns;
    private
    List<String>
        projectStatuses;
    private
    List<Integer>
        projectTypes;
    private
    List<Integer>
        zones;
    private
    ClientValue
        client;
    private
    UserValue
        user;
    private
    com.sehinc.common.security.SecurityManager
        securityManager;
    private
    int
        currentPage;
    private
    int
        projectsPerPage;
    private
    int
        totalPages;

    public List<EcProjectSearchData> getResults()
    {
        return results;
    }

    public void setResults(List<EcProjectSearchData> results)
    {
        this.results =
            results;
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

    public List<String> getOrderColumns()
    {
        return orderColumns;
    }

    public void setOrderColumns(String... orderColumns)
    {
        if (orderColumns
            != null)
        {
            this.orderColumns =
                new ArrayList<String>(orderColumns.length);
            this.orderColumns
                .addAll(Arrays.asList(orderColumns));
        }
    }

    public List<String> getProjectStatuses()
    {
        return projectStatuses;
    }

    public void setProjectStatuses(String... projectStatuses)
    {
        if (projectStatuses
            != null)
        {
            this.projectStatuses =
                new ArrayList<String>(projectStatuses.length);
            this.projectStatuses
                .addAll(Arrays.asList(projectStatuses));
        }
    }

    public List<Integer> getProjectTypes()
    {
        return projectTypes;
    }

    public void setProjectTypes(Integer... projectTypes)
    {
        if (projectTypes
            != null)
        {
            this.projectTypes =
                new ArrayList<Integer>(projectTypes.length);
            this.projectTypes
                .addAll(Arrays.asList(projectTypes));
        }
    }

    public List<Integer> getZones()
    {
        return zones;
    }

    public void setZones(Integer... zones)
    {
        if (zones
            != null)
        {
            this.zones =
                new ArrayList<Integer>(zones.length);
            this.zones
                .addAll(Arrays.asList(zones));
        }
    }

    public void setClient(ClientValue client)
    {
        this.client =
            client;
    }

    public void setUser(UserValue user)
    {
        this.user =
            user;
    }

    public void setSecurityManager(SecurityManager securityManager)
    {
        this.securityManager =
            securityManager;
    }

    public ClientValue getClient()
    {
        return client;
    }

    public UserValue getUser()
    {
        return user;
    }

    public SecurityManager getSecurityManager()
    {
        return securityManager;
    }

    public String getAreaSizeMax()
    {
        return areaSizeMax;
    }

    public void setAreaSizeMax(String areaSizeMax)
    {
        this.areaSizeMax =
            areaSizeMax;
    }

    public String getAreaSizeMin()
    {
        return areaSizeMin;
    }

    public void setAreaSizeMin(String areaSizeMin)
    {
        this.areaSizeMin =
            areaSizeMin;
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

    public String getInspectorName()
    {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName)
    {
        this.inspectorName =
            inspectorName;
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

    public String getPermitNumber()
    {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber)
    {
        this.permitNumber =
            permitNumber;
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

    public String getImpAreaSizeMax()
    {
        return impAreaSizeMax;
    }

    public void setImpAreaSizeMax(String impAreaSizeMax)
    {
        this.impAreaSizeMax =
            impAreaSizeMax;
    }

    public String getImpAreaSizeMin()
    {
        return impAreaSizeMin;
    }

    public void setImpAreaSizeMin(String impAreaSizeMin)
    {
        this.impAreaSizeMin =
            impAreaSizeMin;
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

    public String getTotalAreaSizeMax()
    {
        return totalAreaSizeMax;
    }

    public void setTotalAreaSizeMax(String totalAreaSizeMax)
    {
        this.totalAreaSizeMax =
            totalAreaSizeMax;
    }

    public String getTotalAreaSizeMin()
    {
        return totalAreaSizeMin;
    }

    public void setTotalAreaSizeMin(String totalAreaSizeMin)
    {
        this.totalAreaSizeMin =
            totalAreaSizeMin;
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

    public int getProjectsPerPage()
    {
        return projectsPerPage;
    }

    public void setProjectsPerPage(int projectsPerPage)
    {
        this.projectsPerPage =
            projectsPerPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage =
            currentPage;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages =
            totalPages;
    }
}
