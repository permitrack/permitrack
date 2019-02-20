package com.sehinc.portal.action.navigation;

import com.sehinc.common.db.security.CapModule;
import org.apache.struts.action.ActionForward;

public class PortalMenuItem
    implements Comparable
{
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private
    String
        modulePath;
    private
    String
        path;
    private
    int
        sequence;
    private
    int
        minSecurityLevel;
    private
    String
        image;

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image =
            image;
    }

    public PortalMenuItem()
    {
    }

    public PortalMenuItem(CapModule module)
    {
        setCode(module.getCode());
        setName(module.getName());
        setDescription(module.getDescription());
        setPath(module.getPath());
        setModulePath(module.getModulePath());
        setSequence(module.getOrderNum());
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getCode()
    {
        return code;
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

    public void setModulePath(String modulePath)
    {
        this.modulePath =
            modulePath;
    }

    public String getModulePath()
    {
        return modulePath;
    }

    public void setPath(String path)
    {
        this.path =
            path;
    }

    public String getPath()
    {
        return path;
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

    public ActionForward getForward()
    {
        ActionForward
            forward =
            new ActionForward(getPath(),
                              true);
        forward.setModule(getModulePath());
        return forward;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof PortalMenuItem)
        {
            return getSequenceAsInteger().compareTo(((PortalMenuItem) obj).getSequenceAsInteger());
        }
        return 0;
    }
}