package com.sehinc.stormwater.resources;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ApplicationResources
{
    private static
    ResourceBundle
        applicationResources;

    static
    {
        applicationResources =
            ResourceBundle.getBundle("com.sehinc.stormwater.resources.ApplicationResources");
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
