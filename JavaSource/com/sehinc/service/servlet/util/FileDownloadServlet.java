package com.sehinc.service.servlet.util;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.portal.PortalUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

public class FileDownloadServlet
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(FileDownloadServlet.class);

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
        doDownloadAction(request,
                         response);
    }

    public void doDownloadAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        LOG.debug("In FileDownloadAction.execute()");
        try
        {
            serverInfo(request);
            String
                portalUser =
                getPortalUser(request);
            String
                username =
                request.getParameter(ApplicationProperties.getProperty("param.username"));
            String
                documentLocation =
                request.getParameter(ApplicationProperties.getProperty("param.document.location"));
            String
                size =
                request.getParameter(ApplicationProperties.getProperty("param.document.size"));
            if (username
                == null)
            {
                System.out
                    .println("Missing username");
                LOG.error("Missing username");
                PortalUtils.forward("/sehsvc/login",
                                    request,
                                    response);
            }
            if (portalUser
                == null
                || !portalUser.equalsIgnoreCase(username))
            {
                System.out
                    .println("Missing portal user");
                LOG.error("Missing portal user");
                PortalUtils.forward("/sehsvc/error.do",
                                    request,
                                    response);
                return;
            }
            if (documentLocation
                == null)
            {
                System.out
                    .println("Missing document location");
                LOG.error("Missing document location");
                PortalUtils.forward("/sehsvc/error.do",
                                    request,
                                    response);
                return;
            }
            // We must first decrypt the document location parameter
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
                PortalUtils.forward("/sehsvc/error.do",
                                    request,
                                    response);
                return;
            }
            realPath =
                getRealPath(realPath);
            if (size
                != null)
            {
                if (size.equals("small"))
                {
                    realPath =
                        resize(realPath,
                               400);
                }
            }
            // Create a File object for the requested file
            File
                downloadFile =
                new File(realPath);
            if (!downloadFile.exists())
            {
                System.out
                    .println("Download file does not exist");
                LOG.error("Download file does not exist");
                PortalUtils.forward("/sehsvc/error.do",
                                    request,
                                    response);
                return;
            }
            downloadFile(downloadFile,
                         response);
        }
        catch (java.net.SocketException se)
        {
            System.out
                .println("FileDownloadServlet failed: "
                         + se.getMessage());
            LOG.error("FileDownloadServlet failed: ",
                      se);
            PortalUtils.forward("/sehsvc/error.do",
                                request,
                                response);
            return;
        }
        catch (Exception e)
        {
            System.out
                .println("FileDownloadAction failed: "
                         + e.getMessage());
            LOG.error("FileDownloadAction failed: ",
                      e);
            PortalUtils.forward("/sehsvc/error.do",
                                request,
                                response);
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
                           + downloadFile.getName()
                               .replace(',',
                                        '_'));
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
            requestHeaders =
            request.getHeaderNames();
        buffer.append("Request Headers\n");
        while (requestHeaders.hasMoreElements())
        {
            String
                name =
                (String) requestHeaders.nextElement();
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

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public static String getRealPath(String realPath)
    {
        // TODO: Fix Hack that changes drive letter in prod-refreshed database if you are in DEV or TEST i.e. changes "E:" drive to "C:" drive
        String
            realDriveLetter =
            realPath.substring(0,
                               1)
                .toUpperCase();
        String
            envDriveLetter =
            ApplicationProperties.getProperty("base.document.directory")
                .substring(0,
                           1)
                .toUpperCase();
        if (realDriveLetter
            != envDriveLetter)
        {
            realPath =
                envDriveLetter
                + realPath.substring(1);
            LOG.info("Changed DocumentLocation to "
                     + realPath
                     + " because of environment");
        }
        return realPath;
    }

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     *
     * @param inputImagePath Path of the original image
     * @param width absolute width in pixels
     * @param height absolute height in pixels
     *
     * @throws IOException
     */
    private static String resize(String inputImagePath, int width, int height)
        throws IOException
    {
        File
            inputFile =
            new File(inputImagePath);
        String
            outputImagePath =
            getResizedPath(inputFile);
        File
            newFile =
            new File(outputImagePath);
        if (!newFile.exists())
        {
            if (!newFile.getParentFile()
                .exists())
            {
                newFile.getParentFile()
                    .mkdirs();
            }
            Thumbnails.of(inputFile)
                .size(width,
                      height)
                .toFile(newFile);
        }
        return outputImagePath;
    }

    public static String resize(String inputImagePath, int width)
        throws IOException
    {
        try
        {
            if (MIMEType.isStandardImage(inputImagePath))
            {
                File
                    inputFile =
                    new File(inputImagePath);
                String
                    outputImagePath =
                    getResizedPath(inputFile);
                File
                    newFile =
                    new File(outputImagePath);
                if (!newFile.exists())
                {
                    BufferedImage
                        inputImage =
                        ImageIO.read(inputFile);
                    if (inputImage.getWidth()
                        > width
                        || inputImage.getHeight()
                           > width)
                    {
                        outputImagePath =
                            resize(inputImagePath,
                                   width,
                                   width);
                    }
                }
                return outputImagePath;
            }
            else if (inputImagePath.toLowerCase()
                .endsWith(".pdf"))
            {
                return getRealPath("C:\\cap\\img\\icons\\acrobat_white.png");
            }
        }
        catch (Exception e)
        {
            LOG.error("Error creating image file"
                      + inputImagePath);
        }
        return inputImagePath;
    }

    private static String getResizedPath(File inputFile)
    {
        String
            inputImagePath;
        inputImagePath =
            inputFile.getPath();
        return inputImagePath.substring(0,
                                        inputImagePath.lastIndexOf("\\")
                                        + 1)
               + "small"
               + inputImagePath.substring(inputImagePath.lastIndexOf("\\"));
    }
}
