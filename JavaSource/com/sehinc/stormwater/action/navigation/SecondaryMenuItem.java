package com.sehinc.stormwater.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;
import org.apache.struts.action.ActionForward;

public class SecondaryMenuItem
    extends MenuItem
{
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

    public ActionForward getForward()
    {
        ActionForward
            forward =
            new ActionForward(getLocation());
        forward.setModule(getModule());
        return forward;
    }
}