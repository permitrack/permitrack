package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;
import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProjectSearchListForm
    extends ActionForm
{
    private
    List<EcProjectSearchData>
        projectList =
        new ArrayList<EcProjectSearchData>();
    private
    String
        clientName =
        null;
    private
    Integer
        projectId =
        null;
    private
    String
        sortMethod =
        null;
    private
    Integer
        projectsPerPage =
        null;
    private
    Integer
        totalPages =
        null;
    private
    Boolean
        projectsPerPageChanged =
        false;
    private
    String
        searchProjectName =
        null;
    private
    String
        searchAddress =
        null;
    private
    String
        searchCity =
        null;
    private
    String
        searchState =
        null;
    private
    String
        searchZip =
        null;
    private
    Integer[]
        searchZones =
        null;
    private
    Integer[]
        searchProjectTypes =
        null;
    private
    String[]
        searchProjectStatuses =
        null;
    private
    String
        sortColumn =
        null;
    private
    String
        sortDirection =
        null;
    private
    int
        id;
    private
    String
        searchName;
    private
    CapContact
        contact;
    private
    boolean
        isDefaultSearch;
    private
    int
        userId;
    private
    Integer
        savedSearchId =
        null;
    private
    String
        btnSubmit =
        null;
    private
    String
        searchPermitNumber =
        null;
    private
    String
        searchAreaSizeMin =
        null;
    private
    String
        searchAreaSizeMax =
        null;
    private
    String
        searchTotalAreaSizeMin =
        null;
    private
    String
        searchTotalAreaSizeMax =
        null;
    private
    String
        searchImpAreaSizeMin =
        null;
    private
    String
        searchImpAreaSizeMax =
        null;
    private
    String
        searchStartDateA =
        null;
    private
    String
        searchStartDateB =
        null;
    private
    String
        searchEffDateA =
        null;
    private
    String
        searchEffDateB =
        null;
    private
    String
        searchSeedDateA =
        null;
    private
    String
        searchSeedDateB =
        null;
    private
    String
        searchPermitAuthName =
        null;
    private
    String
        searchPermiteeName =
        null;
    private
    String
        searchInspectorName =
        null;
    public static final
    String
        SORT_COLUMN =
        "sortColumn";
    public static final
    String
        SORT_DIRECTION =
        "sortDirection";

    public void setSearchZonesString(String zones)
    {
        if (zones
            != null)
        {
            StringTokenizer
                tokenizer =
                new StringTokenizer(zones,
                                    " ,",
                                    false);
            ArrayList<Integer>
                list =
                new ArrayList<Integer>();
            while (tokenizer.hasMoreTokens())
            {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
            this.searchZones =
                list.toArray(new Integer[list.size()]);
        }
    }

    public String getSearchZonesString()
    {
        StringBuilder
            builder =
            new StringBuilder();
        if (searchZones
            != null)
        {
            for (int i : searchZones)
            {
                builder.append(i);
                builder.append(", ");
            }
            return builder.substring(0,
                                     builder.length()
                                     - 2);
        }
        return null;
    }

    public void setSearchProjectTypesString(String types)
    {
        if (types
            != null)
        {
            StringTokenizer
                tokenizer =
                new StringTokenizer(types,
                                    " ,",
                                    false);
            ArrayList<Integer>
                list =
                new ArrayList<Integer>();
            while (tokenizer.hasMoreTokens())
            {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
            this.searchProjectTypes =
                list.toArray(new Integer[list.size()]);
        }
    }

    public String getSearchProjectTypesString()
    {
        StringBuilder
            builder =
            new StringBuilder();
        if (searchProjectTypes
            != null)
        {
            for (int i : searchProjectTypes)
            {
                builder.append(i);
                builder.append(", ");
            }
            return builder.substring(0,
                                     builder.length()
                                     - 2);
        }
        return null;
    }

    public void setSearchProjectStatusesString(String statuses)
    {
        if (statuses
            != null)
        {
            StringTokenizer
                tokenizer =
                new StringTokenizer(statuses,
                                    " ,",
                                    false);
            ArrayList<String>
                list =
                new ArrayList<String>();
            while (tokenizer.hasMoreTokens())
            {
                list.add(tokenizer.nextToken());
            }
            this.searchProjectStatuses =
                list.toArray(new String[list.size()]);
        }
    }

    public String getSearchProjectStatusesString()
    {
        StringBuilder
            builder =
            new StringBuilder();
        if (searchProjectStatuses
            != null)
        {
            for (String i : searchProjectStatuses)
            {
                builder.append(i);
                builder.append(", ");
            }
            return builder.substring(0,
                                     builder.length()
                                     - 2);
        }
        return null;
    }

    public Boolean getProjectsPerPageChanged()
    {
        return projectsPerPageChanged;
    }

    public void setProjectsPerPageChanged(Boolean projectsPerPageChanged)
    {
        this.projectsPerPageChanged =
            projectsPerPageChanged;
    }

    public List<EcProjectSearchData> getProjectList()
    {
        return projectList;
    }

    public void setProjectList(List<EcProjectSearchData> projectList)
    {
        this.projectList =
            projectList;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName =
            clientName;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public String getSortMethod()
    {
        return sortMethod;
    }

    public void setSortMethod(String sortMethod)
    {
        this.sortMethod =
            sortMethod;
    }

    public Integer getProjectsPerPage()
    {
        return projectsPerPage;
    }

    public void setProjectsPerPage(Integer projectsPerPage)
    {
        this.projectsPerPage =
            projectsPerPage;
    }

    public String getSearchProjectName()
    {
        return searchProjectName;
    }

    public void setSearchProjectName(String searchProjectName)
    {
        this.searchProjectName =
            searchProjectName;
    }

    public String getSearchAddress()
    {
        return searchAddress;
    }

    public void setSearchAddress(String searchAddress)
    {
        this.searchAddress =
            searchAddress;
    }

    public String getSearchCity()
    {
        return searchCity;
    }

    public void setSearchCity(String searchCity)
    {
        this.searchCity =
            searchCity;
    }

    public String getSearchState()
    {
        return searchState;
    }

    public void setSearchState(String searchState)
    {
        this.searchState =
            searchState;
    }

    public String getSearchZip()
    {
        return searchZip;
    }

    public void setSearchZip(String searchZip)
    {
        this.searchZip =
            searchZip;
    }

    public Integer[] getSearchZones()
    {
        return searchZones;
    }

    public void setSearchZones(Integer[] searchZones)
    {
        this.searchZones =
            searchZones;
    }

    public Integer[] getSearchProjectTypes()
    {
        return searchProjectTypes;
    }

    public void setSearchProjectTypes(Integer[] searchProjectTypes)
    {
        this.searchProjectTypes =
            searchProjectTypes;
    }

    public String[] getSearchProjectStatuses()
    {
        return searchProjectStatuses;
    }

    public void setSearchProjectStatuses(String[] searchProjectStatuses)
    {
        this.searchProjectStatuses =
            searchProjectStatuses;
    }

    public void reset()
    {
        this.projectList
            .clear();
        clientName =
            null;
        projectId =
            null;
        sortMethod =
            null;
        projectsPerPage =
            null;
    }

    public String getSortColumn()
    {
        return sortColumn;
/*
               == null
            ? EcProjectSearchData.PROJECT_NAME
            : sortColumn;
*/
    }

    public void setSortColumn(String sortColumn)
    {
        this.sortColumn =
            sortColumn;
    }

    public String getSortDirection()
    {
        return sortDirection
               == null
            ? EcProjectSearchData.ASCENDING
            : sortDirection;
    }

    public void setSortDirection(String sortDirection)
    {
        this.sortDirection =
            sortDirection;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id =
            id;
    }

    public String getSearchName()
    {
        return searchName;
    }

    public void setSearchName(String searchName)
    {
        this.searchName =
            searchName;
    }

    public CapContact getContact()
    {
        return contact;
    }

    public void setContact(CapContact contact)
    {
        this.contact =
            contact;
    }

    public boolean isDefaultSearch()
    {
        return isDefaultSearch;
    }

    public void setIsDefaultSearch(boolean defaultSearch)
    {
        isDefaultSearch =
            defaultSearch;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId =
            userId;
    }

    public String getSearchAreaSizeMax()
    {
        return searchAreaSizeMax;
    }

    public void setSearchAreaSizeMax(String searchAreaSizeMax)
    {
        this.searchAreaSizeMax =
            searchAreaSizeMax;
    }

    public String getSearchAreaSizeMin()
    {
        return searchAreaSizeMin;
    }

    public void setSearchAreaSizeMin(String searchAreaSizeMin)
    {
        this.searchAreaSizeMin =
            searchAreaSizeMin;
    }

    public String getSearchEffDateA()
    {
        return searchEffDateA;
    }

    public void setSearchEffDateA(String searchEffDateA)
    {
        this.searchEffDateA =
            searchEffDateA;
    }

    public String getSearchEffDateB()
    {
        return searchEffDateB;
    }

    public void setSearchEffDateB(String searchEffDateB)
    {
        this.searchEffDateB =
            searchEffDateB;
    }

    public String getSearchInspectorName()
    {
        return searchInspectorName;
    }

    public void setSearchInspectorName(String searchInspectorName)
    {
        this.searchInspectorName =
            searchInspectorName;
    }

    public String getSearchPermitAuthName()
    {
        return searchPermitAuthName;
    }

    public void setSearchPermitAuthName(String searchPermitAuthName)
    {
        this.searchPermitAuthName =
            searchPermitAuthName;
    }

    public String getSearchPermiteeName()
    {
        return searchPermiteeName;
    }

    public void setSearchPermiteeName(String searchPermiteeName)
    {
        this.searchPermiteeName =
            searchPermiteeName;
    }

    public String getSearchPermitNumber()
    {
        return searchPermitNumber;
    }

    public void setSearchPermitNumber(String searchPermitNumber)
    {
        this.searchPermitNumber =
            searchPermitNumber;
    }

    public String getSearchStartDateA()
    {
        return searchStartDateA;
    }

    public void setSearchStartDateA(String searchStartDateA)
    {
        this.searchStartDateA =
            searchStartDateA;
    }

    public String getSearchStartDateB()
    {
        return searchStartDateB;
    }

    public void setSearchStartDateB(String searchStartDateB)
    {
        this.searchStartDateB =
            searchStartDateB;
    }

    public String getSearchImpAreaSizeMax()
    {
        return searchImpAreaSizeMax;
    }

    public void setSearchImpAreaSizeMax(String searchImpAreaSizeMax)
    {
        this.searchImpAreaSizeMax =
            searchImpAreaSizeMax;
    }

    public String getSearchImpAreaSizeMin()
    {
        return searchImpAreaSizeMin;
    }

    public void setSearchImpAreaSizeMin(String searchImpAreaSizeMin)
    {
        this.searchImpAreaSizeMin =
            searchImpAreaSizeMin;
    }

    public String getSearchSeedDateA()
    {
        return searchSeedDateA;
    }

    public void setSearchSeedDateA(String searchSeedDateA)
    {
        this.searchSeedDateA =
            searchSeedDateA;
    }

    public String getSearchSeedDateB()
    {
        return searchSeedDateB;
    }

    public void setSearchSeedDateB(String searchSeedDateB)
    {
        this.searchSeedDateB =
            searchSeedDateB;
    }

    public String getSearchTotalAreaSizeMax()
    {
        return searchTotalAreaSizeMax;
    }

    public void setSearchTotalAreaSizeMax(String searchTotalAreaSizeMax)
    {
        this.searchTotalAreaSizeMax =
            searchTotalAreaSizeMax;
    }

    public String getSearchTotalAreaSizeMin()
    {
        return searchTotalAreaSizeMin;
    }

    public void setSearchTotalAreaSizeMin(String searchTotalAreaSizeMin)
    {
        this.searchTotalAreaSizeMin =
            searchTotalAreaSizeMin;
    }

    public Integer getSavedSearchId()
    {
        return savedSearchId;
    }

    public void setSavedSearchId(Integer savedSearchId)
    {
        this.savedSearchId =
            savedSearchId;
    }

    public String getBtnSubmit()
    {
        return btnSubmit;
    }

    public void setBtnSubmit(String btnSubmit)
    {
        this.btnSubmit =
            btnSubmit;
    }

    public Integer getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages)
    {
        this.totalPages =
            totalPages;
    }
}
