package com.sehinc.service.servlet.util;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

public class PublicFileDownloadServlet
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(PublicFileDownloadServlet.class);

    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        performTask(request,
                    response);
    }

    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        performTask(request,
                    response);
    }

    public void init(ServletConfig configIn)
    {
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        // Process the file download action
        doDownloadAction(request,
                         response);
    }

    public void doDownloadAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        LOG.debug("In PublicFileDownloadServlet()");
        try
        {
            String
                documentLocation;
            documentLocation =
                request.getParameter(ApplicationProperties.getProperty("param.document.location"));
            if (documentLocation
                == null)
            {
                System.out
                    .println("Missing document location");
                LOG.error("Missing document location");
                displayErrorPage(request,
                                 response,
                                 "Missing document location");
                return;
            }
            String
                realPath;
            try
            {
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
                displayErrorPage(request,
                                 response,
                                 "Decrypt failed: "
                                 + ce.getMessage());
                return;
            }
            File
                downloadFile =
                new File(realPath);
            if (!downloadFile.exists())
            {
                System.out
                    .println("Download file does not exist");
                LOG.error("Download file does not exist");
                displayErrorPage(request,
                                 response,
                                 "Download file does not exist");
                return;
            }
            downloadFile(downloadFile,
                         response);
        }
        catch (Exception e)
        {
            System.out
                .println("PublicFileDownloadAction failed: "
                         + e.getMessage());
            LOG.error("PublicFileDownloadAction failed: ",
                      e);
            displayErrorPage(request,
                             response,
                             "PublicFileDownloadAction failed: "
                             + e.getMessage());
            return;
        }
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
        StringBuilder
            buffer =
            new StringBuilder();
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
            buffer.append(name)
                .append("=")
                .append(request.getAttribute(name)
                            .toString())
                .append("\n");
        }
        Enumeration
            requestHeaders =
            request.getHeaderNames();
        buffer.append("Request Headers\n");
        while (requestHeaders.hasMoreElements())
        {
            String
                name =
                (String) requestHeaders.nextElement();
            buffer.append(name)
                .append("=")
                .append(request.getHeader(name))
                .append("\n");
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
            buffer.append(name)
                .append("=")
                .append(request.getParameter(name))
                .append("\n");
        }
        buffer.append("********** FileDownload End *********\n");
        System.out
            .println(buffer.toString());
        LOG.info(buffer.toString());
    }

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    private void displayErrorPage(HttpServletRequest request, HttpServletResponse response, String msg)
        throws javax.servlet.ServletException, java.io.IOException
    {
        response.setContentType("text/html");
        PrintWriter
            out =
            response.getWriter();
        out.println("<HTML><BODY><H2>Could not complete your request!</H2>");
        out.println(" Please click the 'BACK' button on your browser and try your request again. ");
        if (msg
            != null)
        {
            out.println("<P>"
                        + msg
                        + "</P>");
        }
        out.println("</BODY></HTML>");
        out.flush();
        out.close();
    }
}
