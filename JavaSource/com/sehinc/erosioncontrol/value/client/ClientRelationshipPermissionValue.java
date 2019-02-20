package com.sehinc.erosioncontrol.value.client;

import com.sehinc.erosioncontrol.db.client.EcClientRelationPermission;

public class ClientRelationshipPermissionValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        moduleId;
    private
    String
        description;
    private
    Integer
        clientTypeId;
    private
    String
        clientTypeName;
    private
    String
        clientTypeDescription;
    private
    Integer
        clientRelationshipTypeId;
    private
    String
        clientRelationshipTypeCode;
    private
    String
        clientRelationshipTypeName;
    private
    String
        clientRelationshipTypeDescription;
    private
    Integer
        secureObjectId;
    private
    String
        secureObjectCode;
    private
    String
        secureObjectName;
    private
    String
        secureObjectDescription;
    private
    Integer
        permissionId;
    private
    String
        permissionCode;
    private
    String
        permissionName;
    private
    String
        permissionDescription;

    public ClientRelationshipPermissionValue()
    {
    }

    public ClientRelationshipPermissionValue(EcClientRelationPermission ecClientRelationPermission)
    {
        this.id =
            ecClientRelationPermission.getId();
        this.description =
            ecClientRelationPermission.getDescription();
        if (ecClientRelationPermission.getClientType()
            != null)
        {
            this.clientTypeId =
                ecClientRelationPermission.getClientType()
                    .getId();
            this.clientTypeName =
                ecClientRelationPermission.getClientType()
                    .getName();
            this.clientTypeDescription =
                ecClientRelationPermission.getClientType()
                    .getDescription();
        }
        if (ecClientRelationPermission.getClientRelationshipType()
            != null)
        {
            this.clientRelationshipTypeId =
                ecClientRelationPermission.getClientRelationshipType()
                    .getId();
            this.clientRelationshipTypeCode =
                ecClientRelationPermission.getClientRelationshipType()
                    .getCode();
            this.clientRelationshipTypeName =
                ecClientRelationPermission.getClientRelationshipType()
                    .getName();
            this.clientRelationshipTypeDescription =
                ecClientRelationPermission.getClientRelationshipType()
                    .getDescription();
        }
        if (ecClientRelationPermission.getSecureObject()
            != null)
        {
            this.moduleId =
                ecClientRelationPermission.getSecureObject()
                    .getModuleId();
            this.secureObjectId =
                ecClientRelationPermission.getSecureObject()
                    .getId();
            this.secureObjectCode =
                ecClientRelationPermission.getSecureObject()
                    .getCode();
            this.secureObjectName =
                ecClientRelationPermission.getSecureObject()
                    .getName();
            this.secureObjectDescription =
                ecClientRelationPermission.getSecureObject()
                    .getDescription();
        }
        if (ecClientRelationPermission.getPermission()
            != null)
        {
            this.permissionId =
                ecClientRelationPermission.getPermission()
                    .getId();
            this.permissionCode =
                ecClientRelationPermission.getPermission()
                    .getCode();
            this.permissionName =
                ecClientRelationPermission.getPermission()
                    .getName();
            this.permissionDescription =
                ecClientRelationPermission.getPermission()
                    .getDescription();
        }
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

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public Integer getModuleId()
    {
        return moduleId;
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

    public Integer getClientTypeId()
    {
        return clientTypeId;
    }

    public void setClientTypeId(Integer clientTypeId)
    {
        this.clientTypeId =
            clientTypeId;
    }

    public String getClientTypeName()
    {
        return clientTypeName;
    }

    public void setClientTypeName(String clientTypeName)
    {
        this.clientTypeName =
            clientTypeName;
    }

    public String getClientTypeDescription()
    {
        return clientTypeDescription;
    }

    public void setClientTypeDescription(String clientTypeDescription)
    {
        this.clientTypeDescription =
            clientTypeDescription;
    }

    public Integer getClientRelationshipTypeId()
    {
        return clientRelationshipTypeId;
    }

    public void setClientRelationshipTypeId(Integer clientRelationshipTypeId)
    {
        this.clientRelationshipTypeId =
            clientRelationshipTypeId;
    }

    public String getClientRelationshipTypeCode()
    {
        return clientRelationshipTypeCode;
    }

    public void setClientRelationshipTypeCode(String clientRelationshipTypeCode)
    {
        this.clientRelationshipTypeCode =
            clientRelationshipTypeCode;
    }

    public String getClientRelationshipTypeName()
    {
        return clientRelationshipTypeName;
    }

    public void setClientRelationshipTypeName(String clientRelationshipTypeName)
    {
        this.clientRelationshipTypeName =
            clientRelationshipTypeName;
    }

    public String getClientRelationshipTypeDescription()
    {
        return clientRelationshipTypeDescription;
    }

    public void setClientRelationshipTypeDescription(String clientRelationshipTypeDescription)
    {
        this.clientRelationshipTypeDescription =
            clientRelationshipTypeDescription;
    }

    public Integer getSecureObjectId()
    {
        return secureObjectId;
    }

    public void setSecureObjectId(Integer secureObjectId)
    {
        this.secureObjectId =
            secureObjectId;
    }

    public String getSecureObjectCode()
    {
        return secureObjectCode;
    }

    public void setSecureObjectCode(String secureObjectCode)
    {
        this.secureObjectCode =
            secureObjectCode;
    }

    public String getSecureObjectName()
    {
        return secureObjectName;
    }

    public void setSecureObjectName(String secureObjectName)
    {
        this.secureObjectName =
            secureObjectName;
    }

    public String getSecureObjectDescription()
    {
        return secureObjectDescription;
    }

    public void setSecureObjectDescription(String secureObjectDescription)
    {
        this.secureObjectDescription =
            secureObjectDescription;
    }

    public Integer getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId)
    {
        this.permissionId =
            permissionId;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode)
    {
        this.permissionCode =
            permissionCode;
    }

    public String getPermissionName()
    {
        return permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName =
            permissionName;
    }

    public String getPermissionDescription()
    {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription)
    {
        this.permissionDescription =
            permissionDescription;
    }

    public boolean equals(Object o)
    {
        if (o
            != null
            && o instanceof ClientRelationshipPermissionValue)
        {
            ClientRelationshipPermissionValue
                other =
                (ClientRelationshipPermissionValue) o;
            return (this.getClientTypeId()
                        .equals(other.getClientTypeId())
                    &&
                    this.getSecureObjectId()
                        .equals(other.getSecureObjectId())
                    &&
                    this.getPermissionId()
                        .equals(other.getPermissionId()));
        }
        return false;
    }

    public String toString()
    {
        return "id="
               + getId()
               +
               "\nmoduleId="
               + getModuleId()
               +
               "\ndescription="
               + getDescription()
               +
               "\nclientTypeId="
               + getClientTypeId()
               +
               "\nclientTypeName="
               + getClientTypeName()
               +
               "\nclientTypeDescription="
               + getClientTypeDescription()
               +
               "\nclientRelationshipTypeId="
               + getClientRelationshipTypeId()
               +
               "\nclientRelationshipTypeCode="
               + getClientRelationshipTypeCode()
               +
               "\nclientRelationshipTypeName="
               + getClientRelationshipTypeName()
               +
               "\nclientRelationshipTypeDescription="
               + getClientRelationshipTypeDescription()
               +
               "\nsecureObjectId="
               + getSecureObjectId()
               +
               "\nsecureObjectCode="
               + getSecureObjectCode()
               +
               "\nsecureObjectName="
               + getSecureObjectName()
               +
               "\nsecureObjectDescription="
               + getSecureObjectDescription()
               +
               "\npermissionId="
               + getPermissionId()
               +
               "\npermissionCode="
               + getPermissionCode()
               +
               "\npermissionName="
               + getPermissionName()
               +
               "\npermissionDescription="
               + getPermissionDescription();
    }
}
