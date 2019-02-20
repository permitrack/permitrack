package com.sehinc.common.action.fileaccess;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class FileStreamAction
    extends Action
{
    private static
    Logger
        LOG =
        Logger.getLogger(FileStreamAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String
                portalUser =
                getPortalUser(request);
            String
                username;
            String
                filename;
            String
                bufferName;
            username =
                request.getParameter(ApplicationProperties.getProperty("param.username"));
            filename =
                request.getParameter(ApplicationProperties.getProperty("param.filename"));
            bufferName =
                request.getParameter(ApplicationProperties.getProperty("param.buffer"));
            if (username
                == null)
            {
                System.out
                    .println("Missing username");
                LOG.error("Missing username");
                return mapping.findForward("fail");
            }
            if (portalUser
                == null
                || !portalUser.equalsIgnoreCase(username))
            {
                System.out
                    .println("Missing portal user");
                LOG.error("Missing portal user");
                return mapping.findForward("fail");
            }
            if (bufferName
                == null)
            {
                System.out
                    .println("Missing buffer");
                LOG.error("Missing buffer");
                return mapping.findForward("fail");
            }
            if (filename
                == null)
            {
                System.out
                    .println("Missing filename");
                LOG.error("Missing filename");
                return mapping.findForward("fail");
            }
            String
                key;
            try
            {
                key =
                    findSessionAttribute(CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                                             bufferName),
                                         request);
                System.out
                    .println("The decrypted key = "
                             + key);
                LOG.info("The decrypted key = "
                         + key);
            }
            catch (Exception e)
            {
                System.out
                    .println("Could not decrypt the buffer name");
                LOG.error("Could not decrypt the buffer name",
                          e);
                return mapping.findForward("fail");
            }
            byte[]
                buffer =
                (byte[]) request.getSession()
                    .getAttribute(key);
            if (buffer
                == null)
            {
                System.out
                    .println("Could not find buffer under key "
                             + key);
                LOG.error("Could not find buffer under key "
                          + key);
                return mapping.findForward("fail");
            }
            streamFile(filename,
                       buffer,
                       response);
        }
        catch (Exception e)
        {
            System.out
                .println("FileStreamAction failed");
            LOG.error("FileStreamAction failed: ",
                      e);
            return mapping.findForward("fail");
        }
        return null;
    }

    protected void streamFile(String filename, byte[] buffer, HttpServletResponse response)
        throws IOException
    {
        System.out
            .println("in streamFile");
        response.setContentType(MIMEType.getMIMEType(filename));
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + filename);
        ServletOutputStream
            ouputStream =
            response.getOutputStream();
        ouputStream.write(buffer,
                          0,
                          buffer.length);
        ouputStream.flush();
        ouputStream.close();
    }

    private void serverInfo(HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("********** FileStream Start *********\n");
        buffer.append("Request Attributes\n");
        Enumeration
            attributeNames =
            request.getAttributeNames();
        while (attributeNames.hasMoreElements())
        {
            String
                name =
                (String) attributeNames.nextElement();
            buffer.append(name
                          + "="
                          + request.getAttribute(name)
                .toString()
                          + "\n");
        }
        Enumeration
            portletHeaders =
            request.getHeaderNames();
        buffer.append("Headers\n");
        while (portletHeaders.hasMoreElements())
        {
            String
                name =
                (String) portletHeaders.nextElement();
            buffer.append(name
                          + "="
                          + request.getHeader(name)
                .toString()
                          + "\n");
        }
        buffer.append("Request Parameters\n");
        Enumeration
            parameterNames =
            request.getParameterNames();
        while (parameterNames.hasMoreElements())
        {
            String
                name =
                (String) parameterNames.nextElement();
            buffer.append(name
                          + "="
                          + request.getParameter(name)
                .toString()
                          + "\n");
        }
        buffer.append("Request URI="
                      + request.getRequestURI()
                      + "\n");
        buffer.append("Servlet Path="
                      + request.getServletPath()
                      + "\n");
        buffer.append("Context Path="
                      + request.getContextPath()
                      + "\n");
        buffer.append("Requested Session ID="
                      + request.getRequestedSessionId()
                      + "\n");
        HttpSession
            session =
            request.getSession();
        buffer.append("Session ID="
                      + session.getId()
                      + "\n");
        buffer.append("Session Attributes\n");
        attributeNames =
            session.getAttributeNames();
        while (attributeNames.hasMoreElements())
        {
            String
                name =
                (String) attributeNames.nextElement();
            buffer.append(name
                          + "="
                          + session.getAttribute(name)
                .toString()
                          + "\n");
        }
        buffer.append("********** FileStream End *********\n");
        System.out
            .println(buffer.toString());
        LOG.info(buffer.toString());
    }

    private String getPortalUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request)
            .getUsername();
    }

    private String findSessionAttribute(String key, HttpServletRequest request)
    {
        Enumeration
            attributeNames =
            request.getSession()
                .getAttributeNames();
        while (attributeNames.hasMoreElements())
        {
            String
                name =
                (String) attributeNames.nextElement();
            if (name.endsWith(key))
            {
                return name;
            }
        }
        return null;
    }
}
