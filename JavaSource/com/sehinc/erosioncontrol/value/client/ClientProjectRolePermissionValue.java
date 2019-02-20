package com.sehinc.erosioncontrol.value.client;

import com.sehinc.erosioncontrol.db.client.EcClientProjectRolePermission;

public class ClientProjectRolePermissionValue
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
        projectRoleId;
    private
    String
        projectRoleCode;
    private
    String
        projectRoleDescription;
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

    public ClientProjectRolePermissionValue()
    {
    }

    public ClientProjectRolePermissionValue(EcClientProjectRolePermission ecClientProjectRolePermission)
    {
        this.id =
            ecClientProjectRolePermission.getId();
        this.description =
            ecClientProjectRolePermission.getDescription();
        if (ecClientProjectRolePermission.getClientProjectRole()
            != null)
        {
            this.projectRoleId =
                ecClientProjectRolePermission.getClientProjectRole()
                    .getId();
            this.projectRoleCode =
                ecClientProjectRolePermission.getClientProjectRole()
                    .getCode();
            this.projectRoleDescription =
                ecClientProjectRolePermission.getClientProjectRole()
                    .getDescription();
        }
        if (ecClientProjectRolePermission.getSecureObject()
            != null)
        {
            this.moduleId =
                ecClientProjectRolePermission.getSecureObject()
                    .getModuleId();
            this.secureObjectId =
                ecClientProjectRolePermission.getSecureObject()
                    .getId();
            this.secureObjectCode =
                ecClientProjectRolePermission.getSecureObject()
                    .getCode();
            this.secureObjectName =
                ecClientProjectRolePermission.getSecureObject()
                    .getName();
            this.secureObjectDescription =
                ecClientProjectRolePermission.getSecureObject()
                    .getDescription();
        }
        if (ecClientProjectRolePermission.getPermission()
            != null)
        {
            this.permissionId =
                ecClientProjectRolePermission.getPermission()
                    .getId();
            this.permissionCode =
                ecClientProjectRolePermission.getPermission()
                    .getCode();
            this.permissionName =
                ecClientProjectRolePermission.getPermission()
                    .getName();
            this.permissionDescription =
                ecClientProjectRolePermission.getPermission()
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

    public Integer getProjectRoleId()
    {
        return projectRoleId;
    }

    public void setProjectRoleId(Integer projectRoleId)
    {
        this.projectRoleId =
            projectRoleId;
    }

    public String getProjectRoleCode()
    {
        return projectRoleCode;
    }

    public void setProjectRoleCode(String projectRoleCode)
    {
        this.projectRoleCode =
            projectRoleCode;
    }

    public String getProjectRoleDescription()
    {
        return projectRoleDescription;
    }

    public void setProjectRoleDescription(String projectRoleDescription)
    {
        this.projectRoleDescription =
            projectRoleDescription;
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
            && o instanceof ClientProjectRolePermissionValue)
        {
            ClientProjectRolePermissionValue
                other =
                (ClientProjectRolePermissionValue) o;
            return (this.getProjectRoleId()
                        .equals(other.getProjectRoleId())
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
               "\nprojectRoleId="
               + getProjectRoleId()
               +
               "\nprojectRoleCode="
               + getProjectRoleCode()
               +
               "\nprojectRoleDescription="
               + getProjectRoleDescription()
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
