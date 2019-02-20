package com.sehinc.dataview.servlet;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.DvClientInformation;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.dataview.DataViewConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class CookieLogin
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(CookieLogin.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        doPost(req,
               res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        HttpSession
            session =
            req.getSession();
        DvClientInformation
            clientInformation;
        Integer
            clientId =
            getClientId(req);
        LOG.debug("login for client: "
                  + clientId);
        if (clientId
            == null)
        {
            LOG.error("null clientID found");
        }
        LOG.debug(req.getHeaderNames()
                      .toString());
        clientInformation =
            getClientInformation(clientId);
        session.setAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION,
                             clientInformation);
        res.sendRedirect("/sehsvc/html/dvo/index.htm");
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

    /**
     * This method checks for the client ID in the request cookies.
     *
     * @param request Request
     *
     * @return Returns client ID
     */
    private static Integer getClientId(HttpServletRequest request)
    {
        Cookie[]
            cookies =
            request.getCookies();
        String
            clientId =
            null;
        for (
            int
                i =
                0; i
                   < cookies.length; i++)
        {
            Cookie
                cookie =
                cookies[i];
            if (cookie.getName()
                .equals(DataViewConstants.DV_CLIENT_ID))
            {
                clientId =
                    cookie.getValue();
                break;
            }
        }
        LOG.debug("clientId=$"
                  + clientId
                  + "$");
        if (!StringUtil.isEmpty(clientId))
        {
            try
            {
                assert clientId
                       != null;
                ClientData
                    clientData =
                    ClientService.getActiveClientById(Integer.valueOf(clientId.trim()));
                if (clientData
                    != null)
                {
                    return clientData.getId();
                }
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                LOG.error("Could not load Client ID "
                          + clientId);
            }
        }
        return null;
    }
}
