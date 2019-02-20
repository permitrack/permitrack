package com.sehinc.common.action.fileaccess;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

public class FileDownloadAction
    extends Action
{
    private static
    Logger
        LOG =
        Logger.getLogger(FileDownloadAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        LOG.debug("In FileDownloadAction.execute()");
        try
        {
            serverInfo(request);
            String
                portalUser =
                getPortalUser(request);
            String
                username;
            String
                documentLocation;
            username =
                request.getParameter(ApplicationProperties.getProperty("param.username"));
            documentLocation =
                request.getParameter(ApplicationProperties.getProperty("param.document.location"));
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
            if (documentLocation
                == null)
            {
                System.out
                    .println("Missing document location");
                LOG.error("Missing document location");
                return mapping.findForward("fail");
            }
            String
                realPath;
            try
            {
                documentLocation =
                    documentLocation.replace(' ',
                                             '+');
                realPath =
                    CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                        documentLocation);
                System.out
                    .println("DocumentLocation="
                             + realPath);
                LOG.info("DocumentLocation="
                         + realPath);
            }
            catch (CryptoException ce)
            {
                System.out
                    .println("Decrypt failed: "
                             + ce.getMessage());
                LOG.error("Decrypt failed",
                          ce);
                return mapping.findForward("fail");
            }
            File
                downloadFile =
                new File(realPath);
            if (!downloadFile.exists())
            {
                System.out
                    .println("Download file does not exist");
                LOG.error("Download file does not exist");
                return mapping.findForward("fail");
            }
            downloadFile(downloadFile,
                         response);
        }
        catch (java.net.SocketException se)
        {
            System.out
                .println("FileDownloadAction failed: "
                         + se.getMessage());
            LOG.error("FileDownloadAction failed: ",
                      se);
        }
        catch (Exception e)
        {
            System.out
                .println("FileDownloadAction failed: "
                         + e.getMessage());
            LOG.error("FileDownloadAction failed: ",
                      e);
            return mapping.findForward("fail");
        }
        return null;
    }

    protected void downloadFile(File downloadFile, HttpServletResponse response)
        throws IOException
    {
        BufferedInputStream
            inputStream =
            new BufferedInputStream(new FileInputStream(downloadFile));
        response.setHeader("Content-length",
                           ""
                           + downloadFile.length());
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + downloadFile.getName());
        response.setContentType(MIMEType.getMIMEType(downloadFile.getName()));
        response.setHeader("Pragma",
                           "public");
        response.setHeader("Cache-control",
                           "must-revalidate");
        ServletOutputStream
            ouputStream =
            response.getOutputStream();
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       inputStream.read(buffer,
                                        0,
                                        8192))
               != -1)
        {
            ouputStream.write(buffer,
                              0,
                              bytesRead);
        }
        ouputStream.flush();
        ouputStream.close();
        inputStream.close();
    }

    private void serverInfo(HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("********** FileDownload Start *********\n");
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
        buffer.append("********** FileDownload End *********\n");
        System.out
            .println(buffer.toString());
        LOG.info(buffer.toString());
    }

    private String getPortalUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request)
            .getUsername();
    }
}
