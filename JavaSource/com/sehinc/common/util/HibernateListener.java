package com.sehinc.common.util;

import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener
    implements ServletContextListener
{
    private static
    Logger
        LOG =
        Logger.getLogger(HibernateListener.class);

    public void contextInitialized(ServletContextEvent event)
    {
        HibernateUtil.getSessionFactory();
    }

    public void contextDestroyed(ServletContextEvent event)
    {
        HibernateUtil.getSessionFactory()
            .close();
    }
}
