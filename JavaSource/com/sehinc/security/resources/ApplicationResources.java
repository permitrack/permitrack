package com.sehinc.security.resources;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ApplicationResources
{
    private static
    Logger
        LOG =
        Logger.getLogger(ApplicationResources.class);
    private static
    ResourceBundle
        applicationResources;

    static
    {
        applicationResources =
            ResourceBundle.getBundle("com.sehinc.security.resources.ApplicationResources");
        //applicationResources.
        LOG.info("ApplicationResources loaded from com.sehinc.security.resources.ApplicationResources");
    }

    private ApplicationResources()
    {
    }

    public static String getProperty(String key)
    {
        return applicationResources.getString(key);
    }

    public static String getProperty(String key, Object[] args)
    {
        return MessageFormat.format(applicationResources.getString(key),
                                    args);
    }
}
