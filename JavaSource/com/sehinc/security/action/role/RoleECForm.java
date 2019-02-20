package com.sehinc.security.action.role;

import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

import java.util.ArrayList;

public class RoleECForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        moduleId;
    private
    String
        statusCode;
    private
    ArrayList
        secureObjectPermissions;
    private
    Integer
        userCount;
    private
    String
        projectCreate;
    private
    String
        projectRead;
    private
    String
        projectUpdate;
    private
    String
        projectDelete;
    private
    String
        projectStatus;
    private
    String
        projectAction;
    private
    String
        inspectionCreate;
    private
    String
        inspectionRead;
    private
    String
        inspectionUpdate;
    private
    String
        inspectionDelete;
    private
    String
        inspectionStatus;
    private
    String
        bmpCreate;
    private
    String
        bmpRead;
    private
    String
        bmpUpdate;
    private
    String
        bmpDelete;
    private
    String
        bmpStatus;
    private
    String
        bmpTemplateCreate;
    private
    String
        bmpTemplateRead;
    private
    String
        bmpTemplateUpdate;
    private
    String
        bmpTemplateDelete;
    private
    String
        bmpTemplateStatus;
    private
    String
        contactCreate;
    private
    String
        contactRead;
    private
    String
        contactUpdate;
    private
    String
        contactDelete;
    private
    String
        contactStatus;
    private
    String
        roleCreate;
    private
    String
        roleRead;
    private
    String
        roleUpdate;
    private
    String
        roleDelete;
    private
    String
        roleStatus;
    private
    String
        eventCreate;
    private
    String
        eventRead;
    private
    String
        eventUpdate;
    private
    String
        eventDelete;
    private
    String
        eventStatus;
    private
    String
        userCreate;
    private
    String
        userRead;
    private
    String
        userUpdate;
    private
    String
        userDelete;
    private
    String
        userStatus;
    private
    String
        clientCreate;
    private
    String
        clientRead;
    private
    String
        clientUpdate;
    private
    String
        clientDelete;
    private
    String
        clientStatus;
    private
    String
        userRoleCreate;
    private
    String
        userRoleRead;
    private
    String
        userRoleUpdate;
    private
    String
        userRoleDelete;
    private
    String
        zoneCreate;
    private
    String
        zoneRead;
    private
    String
        zoneUpdate;
    private
    String
        zoneDelete;
    private
    String
        zoneStatus;
    private
    String
        partnerCreate;
    private
    String
        partnerRead;
    private
    String
        partnerUpdate;
    private
    String
        partnerDelete;
    private
    String
        partnerStatus;
    private
    String
        reportCreate;
    private
    String
        reportRead;
    private
    String
        reportUpdate;
    private
    String
        reportDelete;
    private
    String
        reportStatus;
    private
    boolean
        canDelete;

    public String getProjectCreate()
    {
        return projectCreate;
    }

    public String getProjectRead()
    {
        return projectRead;
    }

    public String getProjectUpdate()
    {
        return projectUpdate;
    }

    public String getProjectDelete()
    {
        return projectDelete;
    }

    public String getProjectStatus()
    {
        return projectStatus;
    }

    public String getProjectAction()
    {
        return projectAction;
    }

    public String getInspectionCreate()
    {
        return inspectionCreate;
    }

    public String getInspectionRead()
    {
        return inspectionRead;
    }

    public String getInspectionUpdate()
    {
        return inspectionUpdate;
    }

    public String getInspectionDelete()
    {
        return inspectionDelete;
    }

    public String getInspectionStatus()
    {
        return inspectionStatus;
    }

    public String getBmpCreate()
    {
        return bmpCreate;
    }

    public String getBmpRead()
    {
        return bmpRead;
    }

    public String getBmpUpdate()
    {
        return bmpUpdate;
    }

    public String getBmpDelete()
    {
        return bmpDelete;
    }

    public String getBmpStatus()
    {
        return bmpStatus;
    }

    public String getBmpTemplateCreate()
    {
        return bmpTemplateCreate;
    }

    public String getBmpTemplateRead()
    {
        return bmpTemplateRead;
    }

    public String getBmpTemplateUpdate()
    {
        return bmpTemplateUpdate;
    }

    public String getBmpTemplateDelete()
    {
        return bmpTemplateDelete;
    }

    public String getBmpTemplateStatus()
    {
        return bmpTemplateStatus;
    }

    public String getContactCreate()
    {
        return contactCreate;
    }

    public String getContactRead()
    {
        return contactRead;
    }

    public String getContactUpdate()
    {
        return contactUpdate;
    }

    public String getContactDelete()
    {
        return contactDelete;
    }

    public String getContactStatus()
    {
        return contactStatus;
    }

    public String getRoleCreate()
    {
        return roleCreate;
    }

    public String getRoleRead()
    {
        return roleRead;
    }

    public String getRoleUpdate()
    {
        return roleUpdate;
    }

    public String getRoleDelete()
    {
        return roleDelete;
    }

    public String getRoleStatus()
    {
        return roleStatus;
    }

    public String getEventCreate()
    {
        return eventCreate;
    }

    public String getEventRead()
    {
        return eventRead;
    }

    public String getEventUpdate()
    {
        return eventUpdate;
    }

    public String getEventDelete()
    {
        return eventDelete;
    }

    public String getEventStatus()
    {
        return eventStatus;
    }

    public String getUserCreate()
    {
        return userCreate;
    }

    public String getUserRead()
    {
        return userRead;
    }

    public String getUserUpdate()
    {
        return userUpdate;
    }

    public String getUserDelete()
    {
        return userDelete;
    }

    public String getUserStatus()
    {
        return userStatus;
    }

    public String getClientCreate()
    {
        return clientCreate;
    }

    public String getClientRead()
    {
        return clientRead;
    }

    public String getClientUpdate()
    {
        return clientUpdate;
    }

    public String getClientDelete()
    {
        return clientDelete;
    }

    public String getClientStatus()
    {
        return clientStatus;
    }

    public String getUserRoleCreate()
    {
        return userRoleCreate;
    }

    public String getUserRoleRead()
    {
        return userRoleRead;
    }

    public String getUserRoleUpdate()
    {
        return userRoleUpdate;
    }

    public String getUserRoleDelete()
    {
        return userRoleDelete;
    }

    public String getZoneCreate()
    {
        return zoneCreate;
    }

    public String getZoneRead()
    {
        return zoneRead;
    }

    public String getZoneUpdate()
    {
        return zoneUpdate;
    }

    public String getZoneDelete()
    {
        return zoneDelete;
    }

    public String getZoneStatus()
    {
        return zoneStatus;
    }

    public String getPartnerCreate()
    {
        return partnerCreate;
    }

    public String getPartnerRead()
    {
        return partnerRead;
    }

    public String getPartnerUpdate()
    {
        return partnerUpdate;
    }

    public String getPartnerDelete()
    {
        return partnerDelete;
    }

    public String getPartnerStatus()
    {
        return partnerStatus;
    }

    public String getReportCreate()
    {
        return reportCreate;
    }

    public String getReportRead()
    {
        return reportRead;
    }

    public String getReportUpdate()
    {
        return reportUpdate;
    }

    public String getReportDelete()
    {
        return reportDelete;
    }

    public String getReportStatus()
    {
        return reportStatus;
    }

    public void setProjectCreate(String s)
    {
        projectCreate =
            s;
    }

    public void setProjectRead(String s)
    {
        projectRead =
            s;
    }

    public void setProjectUpdate(String s)
    {
        projectUpdate =
            s;
    }

    public void setProjectDelete(String s)
    {
        projectDelete =
            s;
    }

    public void setProjectStatus(String s)
    {
        projectStatus =
            s;
    }

    public void setProjectAction(String s)
    {
        projectAction =
            s;
    }

    public void setInspectionCreate(String s)
    {
        inspectionCreate =
            s;
    }

    public void setInspectionRead(String s)
    {
        inspectionRead =
            s;
    }

    public void setInspectionUpdate(String s)
    {
        inspectionUpdate =
            s;
    }

    public void setInspectionDelete(String s)
    {
        inspectionDelete =
            s;
    }

    public void setInspectionStatus(String s)
    {
        inspectionStatus =
            s;
    }

    public void setBmpCreate(String s)
    {
        bmpCreate =
            s;
    }

    public void setBmpRead(String s)
    {
        bmpRead =
            s;
    }

    public void setBmpUpdate(String s)
    {
        bmpUpdate =
            s;
    }

    public void setBmpDelete(String s)
    {
        bmpDelete =
            s;
    }

    public void setBmpStatus(String s)
    {
        bmpStatus =
            s;
    }

    public void setBmpTemplateCreate(String s)
    {
        bmpTemplateCreate =
            s;
    }

    public void setBmpTemplateRead(String s)
    {
        bmpTemplateRead =
            s;
    }

    public void setBmpTemplateUpdate(String s)
    {
        bmpTemplateUpdate =
            s;
    }

    public void setBmpTemplateDelete(String s)
    {
        bmpTemplateDelete =
            s;
    }

    public void setBmpTemplateStatus(String s)
    {
        bmpTemplateStatus =
            s;
    }

    public void setContactCreate(String s)
    {
        contactCreate =
            s;
    }

    public void setContactRead(String s)
    {
        contactRead =
            s;
    }

    public void setContactUpdate(String s)
    {
        contactUpdate =
            s;
    }

    public void setContactDelete(String s)
    {
        contactDelete =
            s;
    }

    public void setContactStatus(String s)
    {
        contactStatus =
            s;
    }

    public void setRoleCreate(String s)
    {
        roleCreate =
            s;
    }

    public void setRoleRead(String s)
    {
        roleRead =
            s;
    }

    public void setRoleUpdate(String s)
    {
        roleUpdate =
            s;
    }

    public void setRoleDelete(String s)
    {
        roleDelete =
            s;
    }

    public void setRoleStatus(String s)
    {
        roleStatus =
            s;
    }

    public void setEventCreate(String s)
    {
        eventCreate =
            s;
    }

    public void setEventRead(String s)
    {
        eventRead =
            s;
    }

    public void setEventUpdate(String s)
    {
        eventUpdate =
            s;
    }

    public void setEventDelete(String s)
    {
        eventDelete =
            s;
    }

    public void setEventStatus(String s)
    {
        eventStatus =
            s;
    }

    public void setUserCreate(String s)
    {
        userCreate =
            s;
    }

    public void setUserRead(String s)
    {
        userRead =
            s;
    }

    public void setUserUpdate(String s)
    {
        userUpdate =
            s;
    }

    public void setUserDelete(String s)
    {
        userDelete =
            s;
    }

    public void setUserStatus(String s)
    {
        userStatus =
            s;
    }

    public void setClientCreate(String s)
    {
        clientCreate =
            s;
    }

    public void setClientRead(String s)
    {
        clientRead =
            s;
    }

    public void setClientUpdate(String s)
    {
        clientUpdate =
            s;
    }

    public void setClientDelete(String s)
    {
        clientDelete =
            s;
    }

    public void setClientStatus(String s)
    {
        clientStatus =
            s;
    }

    public void setUserRoleCreate(String s)
    {
        userRoleCreate =
            s;
    }

    public void setUserRoleRead(String s)
    {
        userRoleRead =
            s;
    }

    public void setUserRoleUpdate(String s)
    {
        userRoleUpdate =
            s;
    }

    public void setUserRoleDelete(String s)
    {
        userRoleDelete =
            s;
    }

    public void setZoneCreate(String s)
    {
        zoneCreate =
            s;
    }

    public void setZoneRead(String s)
    {
        zoneRead =
            s;
    }

    public void setZoneUpdate(String s)
    {
        zoneUpdate =
            s;
    }

    public void setZoneDelete(String s)
    {
        zoneDelete =
            s;
    }

    public void setZoneStatus(String s)
    {
        zoneStatus =
            s;
    }

    public void setPartnerCreate(String s)
    {
        partnerCreate =
            s;
    }

    public void setPartnerRead(String s)
    {
        partnerRead =
            s;
    }

    public void setPartnerUpdate(String s)
    {
        partnerUpdate =
            s;
    }

    public void setPartnerDelete(String s)
    {
        partnerDelete =
            s;
    }

    public void setPartnerStatus(String s)
    {
        partnerStatus =
            s;
    }

    public void setReportCreate(String s)
    {
        reportCreate =
            s;
    }

    public void setReportRead(String s)
    {
        reportRead =
            s;
    }

    public void setReportUpdate(String s)
    {
        reportUpdate =
            s;
    }

    public void setReportDelete(String s)
    {
        reportDelete =
            s;
    }

    public void setReportStatus(String s)
    {
        reportStatus =
            s;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer v)
    {
        this.clientId =
            v;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String v)
    {
        this.code =
            v;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String v)
    {
        this.name =
            lt(v);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String v)
    {
        this.description =
            lt(v);
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String v)
    {
        this.statusCode =
            v;
    }

    public String getStatusCodeText()
    {
        String
            strStatus =
            "Unknown";
        if (this.statusCode
            .equals(new Integer(1)))
        {
            strStatus =
                "Active";
        }
        if (this.statusCode
            .equals(new Integer(2)))
        {
            strStatus =
                "In-Active";
        }
        if (this.statusCode
            .equals(new Integer(3)))
        {
            strStatus =
                "Deleted";
        }
        return strStatus;
    }

    public Integer getModuleId()
    {
        return moduleId;
    }

    public void setModuleId(Integer v)
    {
        this.moduleId =
            v;
    }

    public ArrayList getSecureObjectPermissions()
    {
        return secureObjectPermissions;
    }

    public void setSecureObjectPermissions(ArrayList v)
    {
        this.secureObjectPermissions =
            v;
    }

    public Integer getUserCount()
    {
        return userCount;
    }

    public void setUserCount(Integer v)
    {
        this.userCount =
            v;
    }

    public boolean getCanDelete()
    {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete)
    {
        this.canDelete =
            canDelete;
    }

    public void reset()
    {
        this.id =
            null;
        this.code =
            null;
        this.name =
            null;
        this.description =
            null;
        this.moduleId =
            null;
        this.clientId =
            null;
        this.statusCode =
            null;
        this.secureObjectPermissions =
            null;
        this.userCount =
            null;
        projectCreate =
            null;
        projectRead =
            null;
        projectUpdate =
            null;
        projectDelete =
            null;
        projectStatus =
            null;
        projectAction =
            null;
        inspectionCreate =
            null;
        inspectionRead =
            null;
        inspectionUpdate =
            null;
        inspectionDelete =
            null;
        inspectionStatus =
            null;
        bmpCreate =
            null;
        bmpRead =
            null;
        bmpUpdate =
            null;
        bmpDelete =
            null;
        bmpStatus =
            null;
        bmpTemplateCreate =
            null;
        bmpTemplateRead =
            null;
        bmpTemplateUpdate =
            null;
        bmpTemplateDelete =
            null;
        bmpTemplateStatus =
            null;
        contactCreate =
            null;
        contactRead =
            null;
        contactUpdate =
            null;
        contactDelete =
            null;
        contactStatus =
            null;
        roleCreate =
            null;
        roleRead =
            null;
        roleUpdate =
            null;
        roleDelete =
            null;
        roleStatus =
            null;
        eventCreate =
            null;
        eventRead =
            null;
        eventUpdate =
            null;
        eventDelete =
            null;
        eventStatus =
            null;
        userCreate =
            null;
        userRead =
            null;
        userUpdate =
            null;
        userDelete =
            null;
        userStatus =
            null;
        clientCreate =
            null;
        clientRead =
            null;
        clientUpdate =
            null;
        clientDelete =
            null;
        clientStatus =
            null;
        userRoleCreate =
            null;
        userRoleRead =
            null;
        userRoleUpdate =
            null;
        userRoleDelete =
            null;
        zoneCreate =
            null;
        zoneRead =
            null;
        zoneUpdate =
            null;
        zoneDelete =
            null;
        zoneStatus =
            null;
        partnerCreate =
            null;
        partnerRead =
            null;
        partnerUpdate =
            null;
        partnerDelete =
            null;
        partnerStatus =
            null;
        reportCreate =
            null;
        reportRead =
            null;
        reportUpdate =
            null;
        reportDelete =
            null;
        reportStatus =
            null;
        canDelete =
            false;
    }

    public void defaultValues()
    {
        String
            s =
            new String("");
        this.code =
            s;
        this.name =
            s;
        this.description =
            s;
        this.moduleId =
            null;
        this.clientId =
            null;
        this.statusCode =
            StatusCodeData.STATUS_CODE_INACTIVE;
        projectCreate =
            s;
        projectRead =
            s;
        projectUpdate =
            s;
        projectDelete =
            s;
        projectStatus =
            s;
        projectAction =
            s;
        inspectionCreate =
            s;
        inspectionRead =
            s;
        inspectionUpdate =
            s;
        inspectionDelete =
            s;
        inspectionStatus =
            s;
        bmpCreate =
            s;
        bmpRead =
            s;
        bmpUpdate =
            s;
        bmpDelete =
            s;
        bmpStatus =
            s;
        bmpTemplateCreate =
            s;
        bmpTemplateRead =
            s;
        bmpTemplateUpdate =
            s;
        bmpTemplateDelete =
            s;
        bmpTemplateStatus =
            s;
        contactCreate =
            s;
        contactRead =
            s;
        contactUpdate =
            s;
        contactDelete =
            s;
        contactStatus =
            s;
        roleCreate =
            s;
        roleRead =
            s;
        roleUpdate =
            s;
        roleDelete =
            s;
        roleStatus =
            s;
        eventCreate =
            s;
        eventRead =
            s;
        eventUpdate =
            s;
        eventDelete =
            s;
        eventStatus =
            s;
        userCreate =
            s;
        userRead =
            s;
        userUpdate =
            s;
        userDelete =
            s;
        userStatus =
            s;
        clientCreate =
            s;
        clientRead =
            s;
        clientUpdate =
            s;
        clientDelete =
            s;
        clientStatus =
            s;
        userRoleCreate =
            s;
        userRoleRead =
            s;
        userRoleUpdate =
            s;
        userRoleDelete =
            s;
        zoneCreate =
            s;
        zoneRead =
            s;
        zoneUpdate =
            s;
        zoneDelete =
            s;
        zoneStatus =
            s;
        partnerCreate =
            s;
        partnerRead =
            s;
        partnerUpdate =
            s;
        partnerDelete =
            s;
        partnerStatus =
            s;
        reportCreate =
            s;
        reportRead =
            s;
        reportUpdate =
            s;
        reportDelete =
            s;
        reportStatus =
            s;
    }

    public void checkForHTML()
    {
        this.name =
            html(name);
        this.description =
            html(description);
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
