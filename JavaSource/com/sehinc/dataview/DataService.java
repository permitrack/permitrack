package com.sehinc.dataview;

import com.sehinc.portal.PortalUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataService
    extends com.esri.esrimap.Esrimap
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        String
            command =
            req.getParameter("CMD");
        if (command
            != null
            && (command.equals("appserverping")
                || command.equals("connectorping")))
        {
            super.doGet(req,
                        res);
        }
        else
        {
            PortalUtils.forward("/sehsvc/html/dvo/index.jsp",
                                req,
                                res);
            /*
                        forward("/sehsvc/html/dvo/index.jsp",
                                req,
                                res);
            */
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        super.doPost(req,
                     res);
    }

    private void forward(String destination, HttpServletRequest aRequest, HttpServletResponse aResponse)
        throws ServletException, IOException
    {
        RequestDispatcher
            dispatcher =
            aRequest.getRequestDispatcher(destination);
        dispatcher.forward(aRequest,
                           aResponse);
    }
}
