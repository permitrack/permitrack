package com.sehinc.common.servlet.struts;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import java.net.URL;

public class ActionServlet
    extends org.apache.struts.action.ActionServlet
{
    public void init()
        throws ServletException
    {
        super.init();
        ClassLoader
            cl =
            (this.getClass()).getClassLoader();
        URL
            configPath =
            cl.getResource("log4j.xml");
        //ConfigureAndWatch allows you to change log4j.properties
        //settings and see results without having to restart/recompile
        PropertyConfigurator.configureAndWatch(configPath.toString(),
                                               10000);
    }
}
