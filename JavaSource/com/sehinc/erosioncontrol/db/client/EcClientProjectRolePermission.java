package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.security.CapPermission;
import com.sehinc.common.db.security.CapSecureObject;
import com.sehinc.common.util.StringUtil;

public class EcClientProjectRolePermission
    extends HibernateData
{
    private
    EcClientProjectRole
        clientProjectRole =
        new EcClientProjectRole();
    private
    CapSecureObject
        secureObject =
        new CapSecureObject();
    private
    CapPermission
        permission =
        new CapPermission();
    private
    String
        description =
        new String("");

    public EcClientProjectRolePermission()
    {
    }

    public EcClientProjectRole getClientProjectRole()
    {
        return clientProjectRole;
    }

    public String getDescription()
    {
        return description;
    }

    public CapPermission getPermission()
    {
        return permission;
    }

    public CapSecureObject getSecureObject()
    {
        return secureObject;
    }

    public void setClientProjectRole(EcClientProjectRole value)
    {
        clientProjectRole =
            value;
    }

    public void setDescription(String string)
    {
        description =
            StringUtil.maxLength(string,
                                 100);
    }

    public void setPermission(CapPermission value)
    {
        permission =
            value;
    }

    public void setSecureObject(CapSecureObject value)
    {
        secureObject =
            value;
    }
}
