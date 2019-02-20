package com.sehinc.service.servlet.util;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class ImageServlet
    extends HttpServlet
{
    private static
    Logger
        log =
        Logger.getLogger(ImageServlet.class);

/*
    private void displayErrorPage(HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        File
            imageFile =
            new File(ApplicationProperties.getProperty("base.document.directory")
                     + "/img/notfound.gif");
        response.setContentType(MIMEType.getMIMEType(imageFile.getName()));
        log.debug("MIMEType = "
                  + MIMEType.getMIMEType(imageFile.getName()));
        log.debug("performTask(): Got the Image Not Found image! Preparing to stream to the client.");
        OutputStream
            out =
            response.getOutputStream();
        BufferedInputStream
            in =
            new BufferedInputStream(new FileInputStream(imageFile));
        int
            byteIn;
        while ((
                   byteIn =
                       in.read())
               != -1)
        {
            out.write(byteIn);
        }
        in.close();
        out.close();
        log.debug("performTask(): Image stream completed successfully!");
    }
*/

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

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public void init(ServletConfig configIn)
    {
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        String
            encryptedImageFileString =
            request.getParameter(ApplicationProperties.getProperty("param.document.location"));
        String
            size =
            request.getParameter(ApplicationProperties.getProperty("param.document.size"));
        if (encryptedImageFileString
            == null)
        {
            String
                msg =
                "The request param '"
                + ApplicationProperties.getProperty("param.document.location")
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            //            displayErrorPage(response);
            return;
        }
        String
            imageFileString;
        try
        {
            imageFileString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    encryptedImageFileString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + ApplicationProperties.getProperty("param.document.location")
                + "'.";
            log.error("performTask(): "
                      + msg);
            //            displayErrorPage(response);
            return;
        }
        try
        {
            imageFileString = FileDownloadServlet.getRealPath(imageFileString);

            if (size
                != null)
            {
                if (size.equals("small"))
                {
                    imageFileString = FileDownloadServlet.resize(imageFileString, 400);
                }
            }
            File
                imageFile =
                new File(imageFileString);
            response.setContentType(MIMEType.getMIMEType(imageFile.getName()));
            log.debug("MIMEType = "
                      + MIMEType.getMIMEType(imageFile.getName()));
            log.debug("performTask(): Found Image! Preparing to stream to the client.");
            OutputStream
                out =
                response.getOutputStream();
            BufferedInputStream
                in =
                new BufferedInputStream(new FileInputStream(imageFile));
            int
                byteIn;
            while ((
                       byteIn =
                           in.read())
                   != -1)
            {
                out.write(byteIn);
            }
            in.close();
            out.close();
            log.debug("performTask(): Image stream completed successfully!");
        }
        catch (java.io.IOException e)
        {
            log.error("performTask: "
                      + e.getMessage());
            //            displayErrorPage(response);
        }
        catch (java.lang.Exception e)
        {
            log.error("performTask: "
                      + e.getMessage());
            //            displayErrorPage(response);
        }
    }
}
