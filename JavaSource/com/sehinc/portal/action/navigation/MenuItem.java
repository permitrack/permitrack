package com.sehinc.portal.action.navigation;

import com.sehinc.security.SecureObjectPermissionData;
import org.apache.struts.action.ActionForward;

public class MenuItem
    implements Comparable
{
    private
    String
        name;
    private
    String
        description;
    private
    String
        module;
    private
    String
        location;
    private
    int
        sequence;
    private
    int
        minSecurityLevel;
    private
    SecureObjectPermissionData
        mobjSecureObjectPermission;

    public void setSecureObjectPermission(SecureObjectPermissionData secureObjectPermission)
    {
        this.mobjSecureObjectPermission =
            secureObjectPermission;
    }

    public SecureObjectPermissionData getSecureObjectPermission()
    {
        return this.mobjSecureObjectPermission;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setModule(String module)
    {
        this.module =
            module;
    }

    public String getModule()
    {
        return module;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setSequence(int sequence)
    {
        this.sequence =
            sequence;
    }

    public int getSequence()
    {
        return sequence;
    }

    public Integer getSequenceAsInteger()
    {
        return sequence;
    }

    public void setMinSecurityLevel(int minSecurityLevel)
    {
        this.minSecurityLevel =
            minSecurityLevel;
    }

    public int getMinSecurityLevel()
    {
        return minSecurityLevel;
    }

    public ActionForward getForward(boolean redirect)
    {
        ActionForward
            forward =
            new ActionForward(getLocation(),
                              redirect);
        forward.setModule(getModule());
        return forward;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof MenuItem)
        {
            return getSequenceAsInteger().compareTo(((MenuItem) obj).getSequenceAsInteger());
        }
        return 0;
    }
}
