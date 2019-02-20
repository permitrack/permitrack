package com.sehinc.common.action.fileaccess;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PublicFileDownloadAction
    extends Action
{
    private static
    Logger
        LOG =
        Logger.getLogger(PublicFileDownloadAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        ActionErrors
            errors =
            new ActionErrors();
        LOG.debug("In PublicFileDownloadAction.execute()");
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
                return mapping.findForward("fail");
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
        catch (Exception e)
        {
            System.out
                .println("PublicFileDownloadAction failed: "
                         + e.getMessage());
            LOG.error("PublicFileDownloadAction failed: ",
                      e);
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("file.download.failed",
                                         e.getMessage()));
            request.getSession()
                .setAttribute(CommonConstants.ACTION_ERROR,
                              errors);
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
}
