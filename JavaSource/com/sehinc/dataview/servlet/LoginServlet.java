package com.sehinc.dataview.servlet;

import com.esri.aims.mtier.io.ConnectionProxy;
import com.esri.aims.mtier.model.envelope.Envelope;
import com.esri.aims.mtier.model.map.Map;
import com.sehinc.common.db.client.DvClientInformation;
import com.sehinc.dataview.DataViewConstants;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class LoginServlet
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(LoginServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        LOG.debug("doGet()");
        doPost(req,
               res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        LOG.debug("doPost()");
        HttpSession
            session =
            req.getSession();
        DvClientInformation
            clientInformation;
        String
            clientName =
            req.getParameter("client");
        if (clientName
            != null)
        {
            clientInformation =
                getClientInformation(clientName);
        }
        else
        {
            String
                clientId =
                req.getParameter("ID");
            clientInformation =
                getClientInformation(new Integer(clientId));
        }
        session.setAttribute("client",
                             clientName);
        session.setAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION,
                             clientInformation);
        LOG.debug("forwarding to index.jsp");
        PortalUtils.forward("/index.jsp",
                            req,
                            res);
    }

    private DvClientInformation getClientInformation(Integer id)
    {
        DvClientInformation
            clientInformation =
            new DvClientInformation();
        clientInformation.setId(id);
        clientInformation.load();
        return clientInformation;
    }

    private DvClientInformation getClientInformation(String clientName)
    {
        DvClientInformation
            clientInformation =
            new DvClientInformation();
        String
            clientLocation =
            null;
        String
            imsServiceName;
        String
            imsOVServiceName =
            null;
        imsServiceName =
            clientName;
        if (clientName.equals("Springfield"))
        {
            clientLocation =
                "Springfield, Wisconsin";
            imsOVServiceName =
                "SpringfieldOV";
        }
        if (clientName.equals("Chetek2"))
        {
            clientLocation =
                "Chetek, Wisconsin";
            imsOVServiceName =
                "ChetekOV2";
        }
        if (clientName.equals("SantaClara"))
        {
            clientLocation =
                "Santa Clara";
            imsOVServiceName =
                "ChetekOV2";
        }
        if (clientLocation
            != null)
        {
            try
            {
                ConnectionProxy
                    connection =
                    new ConnectionProxy();
                connection.setConnectionType("tcp");
                connection.setHost("localhost");
                connection.setPort(5300);
                connection.setService(clientName);
                Map
                    map =
                    new Map();
                map.setHeight(400);
                map.setWidth(400);
                map.setBackground("200,163,33");
                map.initMap(connection,
                            750,
                            false,
                            false,
                            false,
                            false);
                map.refresh();
                Envelope
                    envelope =
                    map.getEnvelope();
                double
                    minX =
                    envelope.getMinX();
                double
                    minY =
                    envelope.getMinY();
                double
                    maxX =
                    envelope.getMaxX();
                double
                    maxY =
                    envelope.getMaxY();
                double
                    xEdge =
                    (maxX
                     - minX)
                    * DataViewConstants.MAP_BORDER;
                double
                    yEdge =
                    (maxY
                     - minY)
                    * DataViewConstants.MAP_BORDER;
                clientInformation.setStartLeft(new BigDecimal(minX
                                                              - xEdge));
                clientInformation.setStartRight(new BigDecimal(maxX
                                                               + xEdge));
                clientInformation.setStartTop(new BigDecimal(maxY
                                                             + yEdge));
                clientInformation.setStartBottom(new BigDecimal(minY
                                                                - yEdge));
                clientInformation.setLimitLeft(new BigDecimal(minX
                                                              - xEdge));
                clientInformation.setLimitRight(new BigDecimal(maxX
                                                               + xEdge));
                clientInformation.setLimitTop(new BigDecimal(maxY
                                                             + yEdge));
                clientInformation.setLimitBottom(new BigDecimal(minY
                                                                - yEdge));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        clientInformation.setClientName(clientName);
        clientInformation.setClientFullName(clientLocation);
        clientInformation.setImsService(imsServiceName);
        clientInformation.setImsOvService(imsOVServiceName);
        return clientInformation;
    }
}
