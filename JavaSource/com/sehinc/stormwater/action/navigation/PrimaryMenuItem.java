package com.sehinc.stormwater.action.navigation;

import com.sehinc.portal.action.navigation.MenuItem;

public class PrimaryMenuItem
    extends MenuItem
{
    public PrimaryMenuItem()
    {
    }

    public PrimaryMenuItem(String name, String description, String module, String location, int sequence, int minSecurityLevel)
    {
        setName(name);
        setDescription(description);
        setModule(module);
        setLocation(location);
        setSequence(sequence);
        setMinSecurityLevel(minSecurityLevel);
    }
}