package com.sehinc.common.service.spring;

import com.sehinc.erosioncontrol.service.InspectorService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class SpringServiceLocator
    implements ApplicationContextAware
{
    private static
    SpringServiceLocator
        ourInstance =
        new SpringServiceLocator();
    private
    LookupService
        lookupService;
    private
    EnvLookupService
        envLookupService;
    private
    ApplicationContext
        context;
    private
    InspectorService
        inspectorService;
    private
    GenericDaoService
        genericDaoService;
    private
    EmailService
        emailService;

    public static SpringServiceLocator getInstance()
    {
        return ourInstance;
    }

    private SpringServiceLocator()
    {
    }

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        context =
            applicationContext;
    }

    static public ApplicationContext getContext()
    {
        return ourInstance.context;
    }

    static public void publishEvent(ApplicationEvent event)
    {
        ourInstance.context
            .publishEvent(event);
    }

    public void setLookupService(LookupService lookupService)
    {
        this.lookupService =
            lookupService;
    }

    static public LookupService getLookupService()
    {
        return ourInstance.lookupService;
    }

    public void setEnvLookupService(EnvLookupService envLookupService)
    {
        this.envLookupService =
            envLookupService;
    }

    static public EnvLookupService getEnvLookupService()
    {
        return ourInstance.envLookupService;
    }

    public void setInspectorService(InspectorService inspectorService)
    {
        this.inspectorService =
            inspectorService;
    }

    public static InspectorService getInspectorService()
    {
        return ourInstance.inspectorService;
    }

    public void setGenericDaoService(GenericDaoService genericDaoService)
    {
        this.genericDaoService =
            genericDaoService;
    }

    public static GenericDaoService getGenericDaoService()
    {
        return ourInstance.genericDaoService;
    }

    public void setEmailService(EmailService emailService)
    {
        this.emailService =
            emailService;
    }

    public static EmailService getEmailService()
    {
        return ourInstance.emailService;
    }
}
