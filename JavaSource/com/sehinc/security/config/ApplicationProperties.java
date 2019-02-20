package com.sehinc.security.config;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationProperties
{
    public final static
    String
        NEWLINE =
        getJavaProperty("line.separator");
    private static
    Logger
        LOG =
        Logger.getLogger(ApplicationProperties.class);
    private static
    ResourceBundle
        systemResourceBundle;
    private final static
    String
        SYSTEM_PROPERTIES =
        "com.sehinc.security.config.System";

    private ApplicationProperties()
    {
    }

    public static String getBaseURL(HttpServletRequest request)
    {
        String
            aBaseURL =
            null;
        try
        {
            LOG.debug(" URI:"
                      + request.getRequestURI());
            LOG.debug(" URL:"
                      + request.getRequestURL()
                .toString());
            URL
                aURL =
                new URL(request.getRequestURL()
                            .toString());
            aBaseURL =
                aURL.getProtocol()
                + "://"
                + aURL.getHost();
            if (aURL.getPort()
                != 80
                && aURL.getPort()
                   > 0)
            {
                aBaseURL +=
                    ":"
                    + aURL.getPort();
            }
            LOG.info("base URL = "
                     + aBaseURL);
        }
        catch (Exception e)
        {
            LOG.error("Exception generating base URL",
                      e);
        }
        if (aBaseURL
            == null)
        {
            LOG.debug("base URL was null using default base.url");
            return getProperty("base.url");
        }
        return aBaseURL;
    }

    public static String getNewline()
    {
        return NEWLINE;
    }

    private static ResourceBundle getSystemProperties()
    {
        if (systemResourceBundle
            == null)
        {
            systemResourceBundle =
                ResourceBundle.getBundle(SYSTEM_PROPERTIES);
        }
        return systemResourceBundle;
    }

    public static String getProperty(String key)
    {
        return getSystemProperties().getString(key);
    }

    public static String getJavaProperty(String key)
    {
        String
            property =
            null;
        try
        {
            property =
                System.getProperty(key);
        }
        catch (Exception e)
        {
            LOG.error("Exception retrieving propery \""
                      + key
                      + "\"",
                      e);
        }
        if (property
            == null)
        {
            LOG.warn("Propery \""
                     + key
                     + "\" is null.");
        }
        return property;
    }

    public static URL getClasspathResource(String resourceName)
    {
        ApplicationProperties
            ap =
            new ApplicationProperties();
        ClassLoader
            cl =
            (ap.getClass()).getClassLoader();
        return cl.getResource(resourceName);
    }
}
