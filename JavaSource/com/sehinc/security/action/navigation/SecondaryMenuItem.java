package com.sehinc.security.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;

public class SecondaryMenuItem
    extends MenuItem
    implements Comparable
{
    private
    String
        capModuleCode;

    public SecondaryMenuItem()
    {
    }

    public SecondaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
    }

    public SecondaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel, SecureObjectPermissionData secureObjectPermission)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
        setSecureObjectPermission(secureObjectPermission);
    }

    public SecondaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel, SecureObjectPermissionData secureObjectPermission, String capModuleCode)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
        setSecureObjectPermission(secureObjectPermission);
        setCapModuleCode(capModuleCode);
    }

    public void setCapModuleCode(String capModuleCode)
    {
        this.capModuleCode =
            capModuleCode;
    }

    public String getCapModuleCode()
    {
        return capModuleCode;
    }
}