package com.sehinc.environment.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForward;

public class TertiaryMenuItem
    extends MenuItem
{
    public TertiaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
    }

    public TertiaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel, SecureObjectPermissionData secureObjectPermission)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
        setSecureObjectPermission(secureObjectPermission);
    }

    public ActionForward getForward()
    {
        ActionForward
            forward =
            new ActionForward(getLocation());
        forward.setModule(getModule());
        return forward;
    }
}