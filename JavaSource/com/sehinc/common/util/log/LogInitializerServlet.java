package com.sehinc.common.util.log;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public class LogInitializerServlet
    extends HttpServlet
{


    public void init()
    {
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

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
    }
}
