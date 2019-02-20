package com.sehinc.security.action.role;

import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

import java.util.ArrayList;

public class RoleEVForm
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
    boolean
        canDelete;
    private
    String
        permitCreate;
    private
    String
        permitRead;
    private
    String
        permitUpdate;
    private
    String
        permitDelete;
    private
    String
        permitDetailCreate;
    private
    String
        permitDetailRead;
    private
    String
        permitDetailUpdate;
    private
    String
        permitDetailDelete;
    private
    String
        substanceCreate;
    private
    String
        substanceRead;
    private
    String
        substanceUpdate;
    private
    String
        substanceDelete;
    private
    String
        permitSubstanceCreate;
    private
    String
        permitSubstanceRead;
    private
    String
        permitSubstanceUpdate;
    private
    String
        permitSubstanceDelete;
    private
    String
        sourceCreate;
    private
    String
        sourceRead;
    private
    String
        sourceUpdate;
    private
    String
        sourceDelete;
    private
    String
        processCreate;
    private
    String
        processRead;
    private
    String
        processUpdate;
    private
    String
        processDelete;
    private
    String
        assetCreate;
    private
    String
        assetRead;
    private
    String
        assetUpdate;
    private
    String
        assetDelete;
    private
    String
        sourceSubstanceCreate;
    private
    String
        sourceSubstanceRead;
    private
    String
        sourceSubstanceUpdate;
    private
    String
        sourceSubstanceDelete;
    private
    String
        assetSourceCreate;
    private
    String
        assetSourceRead;
    private
    String
        assetSourceUpdate;
    private
    String
        assetSourceDelete;
    private
    String
        facilityCreate;
    private
    String
        facilityRead;
    private
    String
        facilityUpdate;
    private
    String
        facilityDelete;
    private
    String
        permitAssetCreate;
    private
    String
        permitAssetRead;
    private
    String
        permitAssetUpdate;
    private
    String
        permitAssetDelete;
    private
    String
        facilityContactCreate;
    private
    String
        facilityContactRead;
    private
    String
        facilityContactUpdate;
    private
    String
        facilityContactDelete;

    public String getPermitCreate()
    {
        return permitCreate;
    }

    public void setPermitCreate(String permitCreate)
    {
        this.permitCreate =
            permitCreate;
    }

    public String getPermitRead()
    {
        return permitRead;
    }

    public void setPermitRead(String permitRead)
    {
        this.permitRead =
            permitRead;
    }

    public String getPermitUpdate()
    {
        return permitUpdate;
    }

    public void setPermitUpdate(String permitUpdate)
    {
        this.permitUpdate =
            permitUpdate;
    }

    public String getPermitDelete()
    {
        return permitDelete;
    }

    public void setPermitDelete(String permitDelete)
    {
        this.permitDelete =
            permitDelete;
    }

    public String getPermitDetailCreate()
    {
        return permitDetailCreate;
    }

    public void setPermitDetailCreate(String permitDetailCreate)
    {
        this.permitDetailCreate =
            permitDetailCreate;
    }

    public String getPermitDetailRead()
    {
        return permitDetailRead;
    }

    public void setPermitDetailRead(String permitDetailRead)
    {
        this.permitDetailRead =
            permitDetailRead;
    }

    public String getPermitDetailUpdate()
    {
        return permitDetailUpdate;
    }

    public void setPermitDetailUpdate(String permitDetailUpdate)
    {
        this.permitDetailUpdate =
            permitDetailUpdate;
    }

    public String getPermitDetailDelete()
    {
        return permitDetailDelete;
    }

    public void setPermitDetailDelete(String permitDetailDelete)
    {
        this.permitDetailDelete =
            permitDetailDelete;
    }

    public String getSubstanceCreate()
    {
        return substanceCreate;
    }

    public void setSubstanceCreate(String substanceCreate)
    {
        this.substanceCreate =
            substanceCreate;
    }

    public String getSubstanceRead()
    {
        return substanceRead;
    }

    public void setSubstanceRead(String substanceRead)
    {
        this.substanceRead =
            substanceRead;
    }

    public String getSubstanceUpdate()
    {
        return substanceUpdate;
    }

    public void setSubstanceUpdate(String substanceUpdate)
    {
        this.substanceUpdate =
            substanceUpdate;
    }

    public String getSubstanceDelete()
    {
        return substanceDelete;
    }

    public void setSubstanceDelete(String substanceDelete)
    {
        this.substanceDelete =
            substanceDelete;
    }

    public String getPermitSubstanceCreate()
    {
        return permitSubstanceCreate;
    }

    public void setPermitSubstanceCreate(String permitSubstanceCreate)
    {
        this.permitSubstanceCreate =
            permitSubstanceCreate;
    }

    public String getPermitSubstanceRead()
    {
        return permitSubstanceRead;
    }

    public void setPermitSubstanceRead(String permitSubstanceRead)
    {
        this.permitSubstanceRead =
            permitSubstanceRead;
    }

    public String getPermitSubstanceUpdate()
    {
        return permitSubstanceUpdate;
    }

    public void setPermitSubstanceUpdate(String permitSubstanceUpdate)
    {
        this.permitSubstanceUpdate =
            permitSubstanceUpdate;
    }

    public String getPermitSubstanceDelete()
    {
        return permitSubstanceDelete;
    }

    public void setPermitSubstanceDelete(String permitSubstanceDelete)
    {
        this.permitSubstanceDelete =
            permitSubstanceDelete;
    }

    public String getSourceCreate()
    {
        return sourceCreate;
    }

    public void setSourceCreate(String sourceCreate)
    {
        this.sourceCreate =
            sourceCreate;
    }

    public String getSourceRead()
    {
        return sourceRead;
    }

    public void setSourceRead(String sourceRead)
    {
        this.sourceRead =
            sourceRead;
    }

    public String getSourceUpdate()
    {
        return sourceUpdate;
    }

    public void setSourceUpdate(String sourceUpdate)
    {
        this.sourceUpdate =
            sourceUpdate;
    }

    public String getSourceDelete()
    {
        return sourceDelete;
    }

    public void setSourceDelete(String sourceDelete)
    {
        this.sourceDelete =
            sourceDelete;
    }

    public String getProcessCreate()
    {
        return processCreate;
    }

    public void setProcessCreate(String processCreate)
    {
        this.processCreate =
            processCreate;
    }

    public String getProcessRead()
    {
        return processRead;
    }

    public void setProcessRead(String processRead)
    {
        this.processRead =
            processRead;
    }

    public String getProcessUpdate()
    {
        return processUpdate;
    }

    public void setProcessUpdate(String processUpdate)
    {
        this.processUpdate =
            processUpdate;
    }

    public String getProcessDelete()
    {
        return processDelete;
    }

    public void setProcessDelete(String processDelete)
    {
        this.processDelete =
            processDelete;
    }

    public String getAssetCreate()
    {
        return assetCreate;
    }

    public void setAssetCreate(String assetCreate)
    {
        this.assetCreate =
            assetCreate;
    }

    public String getAssetRead()
    {
        return assetRead;
    }

    public void setAssetRead(String assetRead)
    {
        this.assetRead =
            assetRead;
    }

    public String getAssetUpdate()
    {
        return assetUpdate;
    }

    public void setAssetUpdate(String assetUpdate)
    {
        this.assetUpdate =
            assetUpdate;
    }

    public String getAssetDelete()
    {
        return assetDelete;
    }

    public void setAssetDelete(String assetDelete)
    {
        this.assetDelete =
            assetDelete;
    }

    public String getSourceSubstanceCreate()
    {
        return sourceSubstanceCreate;
    }

    public void setSourceSubstanceCreate(String sourceSubstanceCreate)
    {
        this.sourceSubstanceCreate =
            sourceSubstanceCreate;
    }

    public String getSourceSubstanceRead()
    {
        return sourceSubstanceRead;
    }

    public void setSourceSubstanceRead(String sourceSubstanceRead)
    {
        this.sourceSubstanceRead =
            sourceSubstanceRead;
    }

    public String getSourceSubstanceUpdate()
    {
        return sourceSubstanceUpdate;
    }

    public void setSourceSubstanceUpdate(String sourceSubstanceUpdate)
    {
        this.sourceSubstanceUpdate =
            sourceSubstanceUpdate;
    }

    public String getSourceSubstanceDelete()
    {
        return sourceSubstanceDelete;
    }

    public void setSourceSubstanceDelete(String sourceSubstanceDelete)
    {
        this.sourceSubstanceDelete =
            sourceSubstanceDelete;
    }

    public String getAssetSourceCreate()
    {
        return assetSourceCreate;
    }

    public void setAssetSourceCreate(String assetSourceCreate)
    {
        this.assetSourceCreate =
            assetSourceCreate;
    }

    public String getAssetSourceRead()
    {
        return assetSourceRead;
    }

    public void setAssetSourceRead(String assetSourceRead)
    {
        this.assetSourceRead =
            assetSourceRead;
    }

    public String getAssetSourceUpdate()
    {
        return assetSourceUpdate;
    }

    public void setAssetSourceUpdate(String assetSourceUpdate)
    {
        this.assetSourceUpdate =
            assetSourceUpdate;
    }

    public String getAssetSourceDelete()
    {
        return assetSourceDelete;
    }

    public void setAssetSourceDelete(String assetSourceDelete)
    {
        this.assetSourceDelete =
            assetSourceDelete;
    }

    public String getFacilityCreate()
    {
        return facilityCreate;
    }

    public void setFacilityCreate(String facilityCreate)
    {
        this.facilityCreate =
            facilityCreate;
    }

    public String getFacilityRead()
    {
        return facilityRead;
    }

    public void setFacilityRead(String facilityRead)
    {
        this.facilityRead =
            facilityRead;
    }

    public String getFacilityUpdate()
    {
        return facilityUpdate;
    }

    public void setFacilityUpdate(String facilityUpdate)
    {
        this.facilityUpdate =
            facilityUpdate;
    }

    public String getFacilityDelete()
    {
        return facilityDelete;
    }

    public void setFacilityDelete(String facilityDelete)
    {
        this.facilityDelete =
            facilityDelete;
    }

    public String getPermitAssetCreate()
    {
        return permitAssetCreate;
    }

    public void setPermitAssetCreate(String permitAssetCreate)
    {
        this.permitAssetCreate =
            permitAssetCreate;
    }

    public String getPermitAssetRead()
    {
        return permitAssetRead;
    }

    public void setPermitAssetRead(String permitAssetRead)
    {
        this.permitAssetRead =
            permitAssetRead;
    }

    public String getPermitAssetUpdate()
    {
        return permitAssetUpdate;
    }

    public void setPermitAssetUpdate(String permitAssetUpdate)
    {
        this.permitAssetUpdate =
            permitAssetUpdate;
    }

    public String getPermitAssetDelete()
    {
        return permitAssetDelete;
    }

    public void setPermitAssetDelete(String permitAssetDelete)
    {
        this.permitAssetDelete =
            permitAssetDelete;
    }

    public String getFacilityContactCreate()
    {
        return facilityContactCreate;
    }

    public void setFacilityContactCreate(String facilityContactCreate)
    {
        this.facilityContactCreate =
            facilityContactCreate;
    }

    public String getFacilityContactRead()
    {
        return facilityContactRead;
    }

    public void setFacilityContactRead(String facilityContactRead)
    {
        this.facilityContactRead =
            facilityContactRead;
    }

    public String getFacilityContactUpdate()
    {
        return facilityContactUpdate;
    }

    public void setFacilityContactUpdate(String facilityContactUpdate)
    {
        this.facilityContactUpdate =
            facilityContactUpdate;
    }

    public String getFacilityContactDelete()
    {
        return facilityContactDelete;
    }

    public void setFacilityContactDelete(String facilityContactDelete)
    {
        this.facilityContactDelete =
            facilityContactDelete;
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
        canDelete =
            false;
        permitCreate =
            null;
        permitRead =
            null;
        permitUpdate =
            null;
        permitDelete =
            null;
        permitDetailCreate =
            null;
        permitDetailRead =
            null;
        permitDetailUpdate =
            null;
        permitDetailDelete =
            null;
        substanceCreate =
            null;
        substanceRead =
            null;
        substanceUpdate =
            null;
        substanceDelete =
            null;
        permitSubstanceCreate =
            null;
        permitSubstanceRead =
            null;
        permitSubstanceUpdate =
            null;
        permitSubstanceDelete =
            null;
        sourceCreate =
            null;
        sourceRead =
            null;
        sourceUpdate =
            null;
        sourceDelete =
            null;
        processCreate =
            null;
        processRead =
            null;
        processUpdate =
            null;
        processDelete =
            null;
        assetCreate =
            null;
        assetRead =
            null;
        assetUpdate =
            null;
        assetDelete =
            null;
        sourceSubstanceCreate =
            null;
        sourceSubstanceRead =
            null;
        sourceSubstanceUpdate =
            null;
        sourceSubstanceDelete =
            null;
        assetSourceCreate =
            null;
        assetSourceRead =
            null;
        assetSourceUpdate =
            null;
        assetSourceDelete =
            null;
        facilityCreate =
            null;
        facilityRead =
            null;
        facilityUpdate =
            null;
        facilityDelete =
            null;
        permitAssetCreate =
            null;
        permitAssetRead =
            null;
        permitAssetUpdate =
            null;
        permitAssetDelete =
            null;
        facilityContactCreate =
            null;
        facilityContactRead =
            null;
        facilityContactUpdate =
            null;
        facilityContactDelete =
            null;
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