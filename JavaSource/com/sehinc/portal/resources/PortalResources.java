package com.sehinc.portal.resources;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class PortalResources
{
    private static
    Logger
        LOG =
        Logger.getLogger(PortalResources.class);
    private static
    ResourceBundle
        portalResources;

    static
    {
        portalResources =
            ResourceBundle.getBundle("com.sehinc.portal.resources.PortalResources");
        //applicationResources.
        LOG.info("ApplicationResources loaded from com.sehinc.portal.resources.PortalResources");
    }

    private PortalResources()
    {
    }

    public static String getProperty(String key)
    {
        return portalResources.getString(key);
    }

    public static String getProperty(String key, Object[] args)
    {
        return MessageFormat.format(portalResources.getString(key),
                                    args);
    }
}
