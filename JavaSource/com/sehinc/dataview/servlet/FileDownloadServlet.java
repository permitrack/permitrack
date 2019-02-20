package com.sehinc.dataview.servlet;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.DvClientInformation;
import com.sehinc.common.util.MIMEType;
import com.sehinc.dataview.DataViewConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownloadServlet
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(FileDownloadServlet.class);

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
        try
        {
            String
                layer =
                req.getParameter("layer");
            String
                id =
                req.getParameter("id");
            DvClientInformation
                clientInfo =
                (DvClientInformation) req.getSession()
                    .getAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION);
            String
                basePath =
                ApplicationProperties.getProperty("base.document.directory");
            String
                path =
                basePath
                + DataViewConstants.FILE_LOCATION
                + clientInfo.getDownLoads()
                + "/"
                + layer
                + "/"
                + id;
            // Create a File object for the requested file
            File
                requestedFile =
                new File(path);
            // If the requested file does not exist, return error
            if (!requestedFile.exists())
            {
                LOG.error("Download file does not exist");
                //TODO: fail here
            }
            else
            {
                downloadFile(requestedFile,
                             res);
            }
        }
        catch (Exception ex)
        {
            LOG.error("Exception downloading file",
                      ex);
        }
    }

    protected void downloadFile(File downloadFile, HttpServletResponse response)
        throws IOException
    {
        BufferedInputStream
            inputStream =
            new BufferedInputStream(new FileInputStream(downloadFile));
        response.setContentType(MIMEType.getMIMEType(downloadFile.getName()));
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + downloadFile.getName());
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
}

