package com.sehinc.erosioncontrol.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForward;

public class SecondaryMenuItem
    extends MenuItem
    implements Comparable
{
    private
    boolean
        isProjectSecure =
        false;
    private
    SecureObjectPermissionData
        mobjSecureObjectPermission;

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

    public SecondaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel, SecureObjectPermissionData secureObjectPermission, boolean isProjectSecure)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
        setSecureObjectPermission(secureObjectPermission);
        setIsProjectSecure(isProjectSecure);
    }

    public void setSecureObjectPermission(SecureObjectPermissionData secureObjectPermission)
    {
        this.mobjSecureObjectPermission =
            secureObjectPermission;
    }

    public SecureObjectPermissionData getSecureObjectPermission()
    {
        return this.mobjSecureObjectPermission;
    }

    public void setIsProjectSecure(boolean isProjectSecure)
    {
        this.isProjectSecure =
            isProjectSecure;
    }

    public boolean getIsProjectSecure()
    {
        return isProjectSecure;
    }

    public ActionForward getForward()
    {
        ActionForward
            forward =
            new ActionForward(getLocation());
        forward.setModule(getModule());
        return forward;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof SecondaryMenuItem)
        {
            return getSequenceAsInteger().compareTo(((SecondaryMenuItem) obj).getSequenceAsInteger());
        }
        return 0;
    }
}