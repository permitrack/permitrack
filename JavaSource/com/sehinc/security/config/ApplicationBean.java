package com.sehinc.security.config;

import com.sehinc.common.config.ApplicationProperties;

public class ApplicationBean
{
    public String getVersion()
    {
        return ApplicationProperties.getProperty("version");
    }

    public String getBaseURL()
    {
        return ApplicationProperties.getProperty("base.url");
    }
}