package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.ClassInspector;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class ProjectSearchForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectSearchForm.class);
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
        address;
    private
    String
        city;
    private
    String
        state;
    private
    String
        zipCode;
    private
    String
        inspectionDateBegin;
    private
    String
        inspectionDateEnd;
    private
    Integer
        clientId;
    private
    String
        areaSizeMin;
    private
    String
        areaSizeMax;
    // DROP DOWN LIST
    private
    Integer
        zone;
    // CREATION DATE MIN AND MAX
    private
    String
        creationDateMin;
    private
    String
        creationDateMax;
    private
    String[]
        selectedProjectTypes =
        {};
    private
    String[]
        selectedInspectionActions =
        {};
    private
    String[]
        selectedInspectionReasons =
        {};
    private
    String[]
        selectedProjectZones =
        {};
    private
    String
        eventConcern =
        "";
    private
    Integer
        eventId =
        new Integer(0);
    // RADIO BUTTONS
    // project status is
    // 0 ANY
    // 1 ACTIVE
    // 2 INACTIVE
    private
    String
        projectStatusAIB =
        "0";
    // #############################################################
    // THE QUERY THAT WAS BUILT UP, PUT HERE FOR DEVELOPMENT VIEWING
    private
    String
        query;
    // #############################################################
    // AFTER THE SEARCH SETUP HTML FORM, WE HAVE THIS PROPERTY FOR THE SEARCH RESULTS HTML FORM ( <html:multibox> uses this)
    //private List selectedProjects;
    //public List getSelectedProjects(){return selectedProjects;}public void setSelectedProjects(List selection){this.selectedProjects=selection;}
    private
    String[]
        selectedProjects;

    public String[] getSelectedInspectionActions()
    {
        return selectedInspectionActions;
    }

    public void setSelectedInspectionActions(String[] actions)
    {
        this.selectedInspectionActions =
            actions;
    }

    public String[] getSelectedInspectionReasons()
    {
        return selectedInspectionReasons;
    }

    public void setSelectedInspectionReasons(String[] reasons)
    {
        this.selectedInspectionReasons =
            reasons;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        LOG.info("ProjectSearchForm.setClientId() = "
                 + clientId);
        this.clientId =
            clientId;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String q)
    {
        LOG.info("ProjectSearchForm.setQuery() = "
                 + q);
        query =
            q;
    }

    public String[] getSelectedProjects()
    {
        return selectedProjects;
    }

    public void setSelectedProjects(String[] selection)
    {
        this.selectedProjects =
            selection;
    }

    public String[] getSelectedProjectTypes()
    {
        return selectedProjectTypes;
    }

    public void setSelectedProjectTypes(String[] zones)
    {
        LOG.info("ProjectSearchForm.setSelectedProjectTypes() = "
                 + zones);
        this.selectedProjectTypes =
            zones;
    }

    public String[] getSelectedProjectZones()
    {
        return selectedProjectZones;
    }

    public void setSelectedProjectZones(String[] zones)
    {
        this.selectedProjectZones =
            zones;
    }
    // RADIO BUTTONS
    // 0 ANY
    // 1 COMPLIANT
    // 2 NON COMPLIANT
    //public String compliantInspection = "0";
    //public String getCompliantInspection(){return compliantInspection;}public void setCompliantInspection(String compliantInspection){this.compliantInspection=compliantInspection;}
    // To get compliant projects, we can use the combination of:
    //  inspection begin
    //  inspection end
    //  eventId

    public Integer getEventId()
    {
        return eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId =
            eventId;
    }

    public String getEventConcern()
    {
        return eventConcern;
    }

    public void setEventConcern(String eventConcern)
    {
        this.eventConcern =
            eventConcern;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getParcelNumber()
    {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber)
    {
        this.parcelNumber =
            parcelNumber;
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

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode =
            zipCode;
    }

    public String getViewAddress()
    {
        return viewString(this.address
                          + ((this.city
                              != null
                              && this.city
                                 != "")
                                 ? ", "
                                   + this.city
                                 : "")
                          + ((this.state
                              != null
                              && this.state
                                 != "")
                                 ? ", "
                                   + this.state
                                 : "")
                          + ((this.zipCode
                              != null
                              && this.zipCode
                                 != "")
                                 ? " "
                                   + this.zipCode
                                 : ""));
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

    public String getAreaSizeMax()
    {
        return areaSizeMax;
    }

    public void setAreaSizeMax(String areaSizeMax)
    {
        this.areaSizeMax =
            areaSizeMax;
    }

    public Integer getZone()
    {
        return zone;
    }

    public void setZone(Integer zone)
    {
        this.zone =
            zone;
    }

    public String getCreationDateMin()
    {
        return creationDateMin;
    }

    public void setCreationDateMin(String creationDateMin)
    {
        LOG.info("ProjectSearchForm.setCreationDateMin() = "
                 + creationDateMin);
        this.creationDateMin =
            creationDateMin;
    }

    public String getCreationDateMax()
    {
        return creationDateMax;
    }

    public void setCreationDateMax(String creationDateMax)
    {
        LOG.info("ProjectSearchForm.setCreationDateMax() = "
                 + creationDateMax);
        this.creationDateMax =
            creationDateMax;
    }

    public String getProjectStatusAIB()
    {
        return projectStatusAIB;
    }

    public void setProjectStatusAIB(String status)
    {
        this.projectStatusAIB =
            status;
    }

    public String getInspectionDateBegin()
    {
        return inspectionDateBegin;
    }

    public void setInspectionDateBegin(String date)
    {
        LOG.info("ProjectSearchForm.setInspectionDateBegin() = "
                 + date);
        this.inspectionDateBegin =
            date;
    }

    public String getInspectionDateEnd()
    {
        return inspectionDateEnd;
    }

    public void setInspectionDateEnd(String date)
    {
        LOG.info("ProjectSearchForm.setInspectionDateEnd() = "
                 + date);
        this.inspectionDateEnd =
            date;
    }

    public ProjectSearchForm()
    {
        reset();
    }

    public void reset()
    {
        clientId =
            new Integer(0);
        eventId =
            new Integer(0);
        name =
            null;
        description =
            null;
        parcelNumber =
            null;
        permitNumber =
            null;
        address =
            null;
        city =
            null;
        state =
            null;
        zipCode =
            null;
        areaSizeMin =
            null;
        areaSizeMax =
            null;
        zone =
            new Integer(0);
        creationDateMin =
            null;
        creationDateMax =
            null;
        projectStatusAIB =
            "0";
        selectedInspectionActions =
            new String[] {};
        selectedInspectionReasons =
            new String[] {};
        inspectionDateBegin =
            null;
        inspectionDateEnd =
            null;
        selectedProjects =
            new String[0];
        selectedProjectZones =
            new String[] {};
        selectedProjectTypes =
            new String[] {};
        Logger.getLogger(ProjectSearchForm.class)
            .debug("JUST RESET THE SELECTED PROJECTS IN THE ProjectSearchForm.");
    }

    public void validateForm(ActionErrors errors)
    {
        // At this time do nothing.
    }

    public String toString()
    {
        return "ProjectSearchForm:["
               + ClassInspector.objectToMap(this)
               + "]";
    }
}
