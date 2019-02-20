package com.sehinc.security.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForward;

public class PrimaryMenuItem
    extends MenuItem
    implements Comparable
{
    public PrimaryMenuItem()
    {
    }

    public PrimaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel, SecureObjectPermissionData userSecurity)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
        setSecureObjectPermission(userSecurity);
    }

    public ActionForward getForward()
    {
        ActionForward
            forward =
            new ActionForward(getLocation(),
                              true);
        forward.setModule(getModule());
        return forward;
    }
}