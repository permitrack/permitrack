package com.sehinc.common.action.fileaccess;

import com.sehinc.common.action.base.BaseAction;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.value.user.UserValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

public abstract class DownloadAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(DownloadAction.class);

    public abstract ActionForward downloadAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        LOG.debug("In DownloadAction.doAction()");
        serverInfo(request);
        String
            cookieUsername =
            getCookieUsername(request);
        UserValue
            userValue =
            getUserValue(request);
        if (cookieUsername
            == null
            || !cookieUsername.equalsIgnoreCase(userValue.getUsername()))
        {
            LOG.warn("Missing cookie for username "
                     + userValue.getUsername());
            return mapping.findForward("login.page");
        }
        return downloadAction(mapping,
                              form,
                              request,
                              response);
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
            buffer.append(name)
                .append("=")
                .append(request.getAttribute(name)
                            .toString())
                .append("\n");
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

    private String getCookieUsername(HttpServletRequest request)
    {
        return SessionService.getUserValue(request)
            .getUsername();
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
}
