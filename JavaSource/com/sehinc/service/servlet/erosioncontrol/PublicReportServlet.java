package com.sehinc.service.servlet.erosioncontrol;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.EcClientInformation;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.message.TextEmailMessage;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.util.MIMEType;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.report.ECInspectionFormReport;
import com.sehinc.erosioncontrol.action.report.ECInspectionReport;
import com.sehinc.erosioncontrol.action.report.ECProjectReport;
import com.sehinc.erosioncontrol.action.report.ECReportParameter;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectPublicComments;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

public class PublicReportServlet
    extends HttpServlet
{
    private static
    Logger
        log =
        Logger.getLogger(PublicReportServlet.class);
    private static final
    String
        PageContext =
        null;

    private void displayErrorPage(HttpServletRequest request, HttpServletResponse response, String msg)
        throws javax.servlet.ServletException, java.io.IOException
    {
        PortalUtils.forward("/sehsvc/error.do",
                            request,
                            response);
    }

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
            action =
            request.getParameter(RequestKeys.ACTION_PARAMETER);
        if (action
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.ACTION_PARAMETER
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            clientIdString;
        String
            clientIdEncryptedString =
            request.getParameter(RequestKeys.EC_MAP_CLIENT_ID);
        if (clientIdEncryptedString
            == null)
        {
            clientIdString =
                "1";
        }
        else
        {
            try
            {
                clientIdString =
                    CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                        clientIdEncryptedString);
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not decrypt parameter '"
                    + RequestKeys.EC_MAP_CLIENT_ID
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
        }
        Integer
            clientId =
            null;
        if (clientIdString
            != null)
        {
            clientId =
                new Integer(clientIdString);
        }
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientId);
        clientData.load();
        log.info("PublicReportServlet.performTask() clientId = "
                 + clientId);
        request.setAttribute(RequestKeys.CLIENT_DATA,
                             clientData);
        if (action.equalsIgnoreCase(RequestKeys.EC_PROJECT_MAP_VIEW_ACTION))
        {
            doProjectMapViewAction(request,
                                   response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_LIST_ACTION))
        {
            doInspectionListAction(request,
                                   response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_REPORT_ACTION))
        {
            doInspectionReportAction(request,
                                     response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_SUBMIT_COMMENT))
        {
            doInspectionSubmitCommentAction(request,
                                            response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_SAVE_COMMENT))
        {
            doInspectionSaveCommentAction(request,
                                          response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_REPORT_SECURE_ACTION))
        {
            doECInspectionReportSecureAction(request,
                                             response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_INSPECTION_FORM_REPORT_SECURE_ACTION))
        {
            doECInspectionFormReportSecureAction(request,
                                                 response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.EC_PROJECT_REPORT_SECURE_ACTION))
        {
            doECProjectReportSecureAction(request,
                                          response);
        }
        else
        {
            String
                msg =
                "The requested action '"
                + action
                + "' is undefined.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
    }

    private void doProjectMapViewAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doProjectMapViewAction() clientData.getName() = "
                 + clientData.getName());
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            != null)
        {
            String
                projectIdString;
            try
            {
                projectIdString =
                    CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                        projectIdEncryptedString);
                Integer
                    projectId =
                    null;
                if (projectIdString
                    != null)
                {
                    projectId =
                        new Integer(projectIdString);
                }
                request.setAttribute(RequestKeys.EC_PROJECT_ID,
                                     projectId);
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not decrypt parameter '"
                    + RequestKeys.EC_PROJECT_ID
                    + "'.";
                log.error("performTask(): "
                          + msg);
            }
        }
        List
            projectMapValueList =
            ProjectService.getProjectMapValueList(clientData,
                                                  request);
        request.setAttribute(RequestKeys.EC_PROJECT_MAP_VIEW_LIST,
                             projectMapValueList);
        String
            clientMapLogo =
            null;
        String
            mapLogoURL =
            null;
        if (clientData.getMapLogoLocation()
            != null
            && clientData.getMapLogoLocation()
                   .length()
               > 0)
        {
            clientMapLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                +
                clientData.getId()
                + "/logo/"
                + clientData.getMapLogoLocation();
        }
        /*
        else
        {
            File
                mapViewHeaderFile =
                new File(ApplicationProperties.getProperty("base.document.directory")
                         + "/client"
                         + clientData.getId()
                         + "/logo/MapViewHeader.jpg");
            if (mapViewHeaderFile.exists())
            {
                clientMapLogo =
                    mapViewHeaderFile.getAbsolutePath();
            }
        }
        */
        // If we have a logo to use, then create a link to it from the
        // image servlet and place the link on the request
        if (clientMapLogo
            != null)
        {
            try
            {
                mapLogoURL =
                    ApplicationProperties.getProperty("base.webapp.context")
                    + ApplicationProperties.getProperty("image.servlet")
                    + "?"
                    + ApplicationProperties.getProperty("param.document.location")
                    + "="
                    +
                    URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                          clientMapLogo),
                                      "UTF-8");
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not encrypt parameter mapLogoURL'"
                    + mapLogoURL
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
            request.setAttribute(RequestKeys.CLIENT_MAP_LOGO_LOCATION,
                                 mapLogoURL);
        }
        try
        {
            request.setAttribute(RequestKeys.YELLOW_MAP_MARKER_ICON,
                                 ApplicationProperties.getProperty("base.webapp.context")
                                 + ApplicationProperties.getProperty("image.servlet")
                                 + "?"
                                 + ApplicationProperties.getProperty("param.document.location")
                                 + "="
                                 +
                                 URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                       request.getSession()
                                                                           .getServletContext()
                                                                           .getRealPath(ApplicationProperties.getProperty("google.map.yellow.marker"))),
                                                   "UTF-8"));
            request.setAttribute(RequestKeys.RED_MAP_MARKER_ICON,
                                 ApplicationProperties.getProperty("base.webapp.context")
                                 + ApplicationProperties.getProperty("image.servlet")
                                 + "?"
                                 + ApplicationProperties.getProperty("param.document.location")
                                 + "="
                                 +
                                 URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                       request.getSession()
                                                                           .getServletContext()
                                                                           .getRealPath(ApplicationProperties.getProperty("google.map.red.marker"))),
                                                   "UTF-8"));
            request.setAttribute(RequestKeys.BLACK_MAP_MARKER_ICON,
                                 ApplicationProperties.getProperty("base.webapp.context")
                                 + ApplicationProperties.getProperty("image.servlet")
                                 + "?"
                                 + ApplicationProperties.getProperty("param.document.location")
                                 + "="
                                 +
                                 URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                       request.getSession()
                                                                           .getServletContext()
                                                                           .getRealPath(ApplicationProperties.getProperty("google.map.black.marker"))),
                                                   "UTF-8"));
            request.setAttribute(RequestKeys.GREEN_MAP_MARKER_ICON,
                                 ApplicationProperties.getProperty("base.webapp.context")
                                 + ApplicationProperties.getProperty("image.servlet")
                                 + "?"
                                 + ApplicationProperties.getProperty("param.document.location")
                                 + "="
                                 +
                                 URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                       request.getSession()
                                                                           .getServletContext()
                                                                           .getRealPath(ApplicationProperties.getProperty("google.map.green.marker"))),
                                                   "UTF-8"));
            request.setAttribute(RequestKeys.MAP_MARKER_SHADOW_ICON,
                                 ApplicationProperties.getProperty("base.webapp.context")
                                 + ApplicationProperties.getProperty("image.servlet")
                                 + "?"
                                 + ApplicationProperties.getProperty("param.document.location")
                                 + "="
                                 +
                                 URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                       request.getSession()
                                                                           .getServletContext()
                                                                           .getRealPath(ApplicationProperties.getProperty("google.map.marker.shadow"))),
                                                   "UTF-8"));
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not encrypt marker icons.";
            log.error("performTask(): "
                      + msg);
            log.error(ce.getMessage());
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        try
        {
            request.getRequestDispatcher("/public/html/esc/MapView.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doProjectMapViewAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doProjectMapViewAction(): "
                      + ioe.getMessage());
        }
    }

    private void doInspectionListAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("NEW ***** PublicReportServlet.doInspectionListAction() clientData.getName() = "
                 + clientData.getName());
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        EcProject
            ecProject =
            new EcProject();
        ecProject.setId(projectId);
        ecProject.load();
        Integer
            clientId =
            clientData.getId();
        ProjectValue
            projectValue =
            new ProjectValue(ecProject,
                             true);
        projectValue.setCommentUrl(clientId,
                                   request);
        request.setAttribute(RequestKeys.EC_PROJECT_VALUE,
                             projectValue);
        List
            inspectionMapValueList =
            InspectionService.getAllMapInspectionsByProjectId(clientData.getId(),
                                                              projectId,
                                                              request);
        request.setAttribute(RequestKeys.EC_INSPECTION_MAP_VIEW_LIST,
                             inspectionMapValueList);
        request.setAttribute(RequestKeys.EC_PROJECT_CONTACT_LIST,
                             ProjectService.getProjectContactValueList(projectId,
                                                                       false));
        String
            clientLogo;
        String
            logoURL =
            null;
        if (clientData.getLogoLocation()
            != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                +
                clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
            try
            {
                logoURL =
                    ApplicationProperties.getProperty("base.webapp.context")
                    + ApplicationProperties.getProperty("image.servlet")
                    + "?"
                    + ApplicationProperties.getProperty("param.document.location")
                    + "="
                    +
                    URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                          clientLogo),
                                      "UTF-8");
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not encrypt parameter logoURL'"
                    + logoURL
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
            request.setAttribute(RequestKeys.CLIENT_LOGO_LOCATION,
                                 logoURL);
        }
        request.setAttribute(RequestKeys.EC_PROJECT_DOCUMENT_LIST,
                             ProjectService.getPublicProjectDocumentValueList(projectId,
                                                                              request));
        try
        {
            log.debug("forwarding to /public/html/esc/InspectionListPublic.jsp");
            request.getRequestDispatcher("/public/html/esc/InspectionListPublic.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doInspectionListAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doInspectionListAction(): "
                      + ioe.getMessage());
        }
    }

    private void doInspectionReportAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doInspectionReportAction() clientData.getName() = "
                 + clientData.getName());
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        EcProject
            ecProject =
            new EcProject();
        ecProject.setId(projectId);
        ecProject.load();
        Integer
            clientId =
            clientData.getId();
        ProjectValue
            projectValue =
            new ProjectValue(ecProject,
                             false);
        projectValue.setCommentUrl(clientId,
                                   request);
        request.setAttribute(RequestKeys.EC_PROJECT_VALUE,
                             projectValue);
        String
            inspectionIdEncryptedString =
            request.getParameter(RequestKeys.EC_INSPECTION_ID);
        if (inspectionIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_INSPECTION_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            inspectionIdString;
        try
        {
            inspectionIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    inspectionIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_INSPECTION_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            inspectionId =
            null;
        if (inspectionIdString
            != null)
        {
            inspectionId =
                new Integer(inspectionIdString);
        }
        EcInspection
            ecInspection =
            new EcInspection();
        ecInspection.setId(inspectionId);
        ecInspection.load();
        request.setAttribute(RequestKeys.EC_PROJECT_CONTACT_LIST,
                             ProjectService.getProjectContactValueList(projectId,
                                                                       true));
        request.setAttribute(RequestKeys.EC_INSPECTION_VALUE,
                             new InspectionValue(ecInspection));
        request.setAttribute(RequestKeys.EC_INSPECTION_BMP_DEFECT_LIST,
                             InspectionService.getInspectionBmpValuesByInspectionId(inspectionId,
                                                                                    clientData.getId(),
                                                                                    "",
                                                                                    request));
        String
            clientLogo;
        String
            logoURL =
            null;
        if (clientData.getLogoLocation()
            != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                +
                clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
            try
            {
                logoURL =
                    ApplicationProperties.getProperty("base.webapp.context")
                    + ApplicationProperties.getProperty("image.servlet")
                    + "?"
                    + ApplicationProperties.getProperty("param.document.location")
                    + "="
                    +
                    URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                          clientLogo),
                                      "UTF-8");
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not encrypt parameter logoURL'"
                    + logoURL
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
            request.setAttribute(RequestKeys.CLIENT_LOGO_LOCATION,
                                 logoURL);
        }
        try
        {
            request.getRequestDispatcher("/public/html/esc/InspectionViewPublic.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doInspectionReportAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doInspectionReportAction(): "
                      + ioe.getMessage());
        }
        catch (Exception e)
        {
            log.error("doInspectionReportAction(): "
                      + e.getMessage());
        }
    }

    private void doInspectionSubmitCommentAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doInspectionSubmitCommentAction() clientData.getName() = "
                 + clientData.getName());
        String
            projectIdString;
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            /*
                        String
                            msg =
                            "The request param '"
                            + RequestKeys.EC_PROJECT_ID
                            + "' is required.";
                        log.error("performTask(): "
                                  + msg);
                        displayErrorPage(request,
                                         response,
                                         msg);
                        return;
            */
            projectIdString =
                "3077";
        }
        else
        {
            try
            {
                projectIdString =
                    CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                        projectIdEncryptedString);
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not decrypt parameter '"
                    + RequestKeys.EC_PROJECT_ID
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        EcProject
            ecProject =
            new EcProject();
        ecProject.setId(projectId);
        ecProject.load();
        request.setAttribute(RequestKeys.EC_PROJECT_VALUE,
                             new ProjectValue(ecProject,
                                              true));
        String
            clientLogo;
        String
            logoURL =
            null;
        if (clientData.getLogoLocation()
            != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                +
                clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
            try
            {
                logoURL =
                    ApplicationProperties.getProperty("base.webapp.context")
                    + ApplicationProperties.getProperty("image.servlet")
                    + "?"
                    + ApplicationProperties.getProperty("param.document.location")
                    + "="
                    +
                    URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                          clientLogo),
                                      "UTF-8");
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not encrypt parameter logoURL'"
                    + logoURL
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
            request.setAttribute(RequestKeys.CLIENT_LOGO_LOCATION,
                                 logoURL);
        }
        try
        {
            request.getRequestDispatcher("/public/html/esc/SubmitComment.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doInspectionSubmitCommentAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doInspectionSubmitCommentAction(): "
                      + ioe.getMessage());
        }
    }

    private void doInspectionSaveCommentAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doInspectionSaveCommentAction() clientData.getName() = "
                 + clientData.getName());
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        EcProject
            ecProject =
            new EcProject();
        ecProject.setId(projectId);
        ecProject.load();
        EcProjectPublicComments
            ecProjectPublicComments =
            new EcProjectPublicComments();
        ecProjectPublicComments.setClientOwnerId(ecProject.getOwnerClient()
                                                     .getId());
        ecProjectPublicComments.setProjectId(projectId);
        ecProjectPublicComments.setFirstName(request.getParameter("firstName"));
        ecProjectPublicComments.setLastName(request.getParameter("lastName"));
        ecProjectPublicComments.setStreetAddress(request.getParameter("street"));
        ecProjectPublicComments.setCity(request.getParameter("city"));
        ecProjectPublicComments.setState(request.getParameter("state"));
        ecProjectPublicComments.setZipCode(request.getParameter("zip"));
        ecProjectPublicComments.setEmailAddress(request.getParameter("emailAddress"));
        ecProjectPublicComments.setPhone(request.getParameter("phone"));
        ecProjectPublicComments.setComments(request.getParameter("CommentText"));
        ecProjectPublicComments.setCreateTimestamp(new Date());
        ecProjectPublicComments.setIpAddress(request.getRemoteAddr());
        ecProjectPublicComments.save();
        try
        {
            sendPublicCommentEmail(ecProjectPublicComments,
                                   clientData);
        }
        catch (MessagingException mex)
        {
            log.error("Public Comment Email Failed: "
                      + mex.getMessage());
        }
        try
        {
            CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        request.setAttribute(RequestKeys.EC_PROJECT_VALUE,
                             new ProjectValue(ecProject,
                                              true));
        String
            clientLogo;
        String
            logoURL =
            null;
        if (clientData.getLogoLocation()
            != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                +
                clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
            try
            {
                logoURL =
                    ApplicationProperties.getProperty("base.webapp.context")
                    + ApplicationProperties.getProperty("image.servlet")
                    + "?"
                    + ApplicationProperties.getProperty("param.document.location")
                    + "="
                    +
                    URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                          clientLogo),
                                      "UTF-8");
            }
            catch (CryptoException ce)
            {
                String
                    msg =
                    "Could not encrypt parameter logoURL'"
                    + logoURL
                    + "'.";
                log.error("performTask(): "
                          + msg);
                displayErrorPage(request,
                                 response,
                                 msg);
                return;
            }
            request.setAttribute(RequestKeys.CLIENT_LOGO_LOCATION,
                                 logoURL);
        }
        try
        {
            request.getRequestDispatcher("/public/html/esc/CommentThankYou.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doInspectionSaveCommentAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doInspectionSaveCommentAction(): "
                      + ioe.getMessage());
        }
    }

    private void sendEmail(String fromAddress, Set toAddress, String subject, String message)
        throws MessagingException
    {
        TextEmailMessage
            emailMessage =
            new TextEmailMessage();
        InternetAddress[]
            fromAddr =
            new InternetAddress[1];
        fromAddr[0] =
            new InternetAddress(fromAddress);
        InternetAddress[]
            toAddrs =
            new InternetAddress[toAddress.size()];
        Iterator
            toAddressIter =
            toAddress.iterator();
        int
            index =
            0;
        while (toAddressIter.hasNext())
        {
            toAddrs[index] =
                new InternetAddress((String) toAddressIter.next());
            index++;
        }
        emailMessage.setFrom(fromAddr);
        emailMessage.setRecipients(Message.RecipientType.TO,
                                   toAddrs);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        log.info(emailMessage.toString());
        log.info("Before sending public comment email");
        EmailService
            emailService =
            new EmailService();
        emailService.send(emailMessage);
        log.info("Successfully sent public comment email");
    }

    private void sendPublicCommentEmail(EcProjectPublicComments projectPublicComments, ClientData clientData)
        throws MessagingException
    {
        HashSet
            emailSet =
            new HashSet();
        String
            fromEmail =
            null;
        EcProject
            ecProject =
            new EcProject();
        ecProject.setId(projectPublicComments.getProjectId());
        ecProject.load();
        if (ecProject.getOwnerClient()
            != null)
        {
            EcClientInformation
                ownerClientInformation =
                EcClientInformation.findByClientId(ecProject.getOwnerClient()
                                                       .getId());
            if (ownerClientInformation
                != null)
            {
                if (ownerClientInformation.getPublicCommentEMail()
                    != null)
                {
                    emailSet.add(ownerClientInformation.getPublicCommentEMail());
                }
                else
                {
                    UserData
                        clientAdmin =
                        UserData.findClientAdminByClientId(ecProject.getOwnerClient()
                                                               .getId());
                    if (clientAdmin
                        != null)
                    {
                        emailSet.add(clientAdmin.getEmailAddress());
                    }
                }
                if (ownerClientInformation.getGeneralReplyToEMail()
                    != null)
                {
                    fromEmail =
                        ownerClientInformation.getGeneralReplyToEMail();
                }
            }
        }
        if (fromEmail
            == null)
        {
            fromEmail =
                ApplicationProperties.getProperty("mail.support.address");
        }
        if (ecProject.getPermitAuthorityClient()
            != null)
        {
            EcClientInformation
                permitAuthorityClientInformation =
                EcClientInformation.findByClientId(ecProject.getPermitAuthorityClient()
                                                       .getId());
            if (permitAuthorityClientInformation
                != null)
            {
                if (permitAuthorityClientInformation.getPublicCommentEMail()
                    != null)
                {
                    emailSet.add(permitAuthorityClientInformation.getPublicCommentEMail());
                }
            }
            if (permitAuthorityClientInformation
                == null
                || permitAuthorityClientInformation.getPublicCommentEMail()
                   == null)
            {
                UserData
                    clientAdmin =
                    UserData.findClientAdminByClientId(ecProject.getPermitAuthorityClient()
                                                           .getId());
                if (clientAdmin
                    != null)
                {
                    emailSet.add(clientAdmin.getEmailAddress());
                }
            }
        }
        if (ecProject.getPermittedClient()
            != null)
        {
            EcClientInformation
                permittedClientInformation =
                EcClientInformation.findByClientId(ecProject.getPermittedClient()
                                                       .getId());
            if (permittedClientInformation
                != null)
            {
                if (permittedClientInformation.getPublicCommentEMail()
                    != null)
                {
                    emailSet.add(permittedClientInformation.getPublicCommentEMail());
                }
            }
            if (permittedClientInformation
                == null
                || permittedClientInformation.getPublicCommentEMail()
                   == null)
            {
                UserData
                    clientAdmin =
                    UserData.findClientAdminByClientId(ecProject.getPermittedClient()
                                                           .getId());
                if (clientAdmin
                    != null)
                {
                    emailSet.add(clientAdmin.getEmailAddress());
                }
            }
        }
        if (ecProject.getAuthorizedInspectorClient()
            != null)
        {
            EcClientInformation
                authorizedInspectorClientInformation =
                EcClientInformation.findByClientId(ecProject.getAuthorizedInspectorClient()
                                                       .getId());
            if (authorizedInspectorClientInformation
                != null)
            {
                if (authorizedInspectorClientInformation.getPublicCommentEMail()
                    != null)
                {
                    emailSet.add(authorizedInspectorClientInformation.getPublicCommentEMail());
                }
            }
            if (authorizedInspectorClientInformation
                == null
                || authorizedInspectorClientInformation.getPublicCommentEMail()
                   == null)
            {
                UserData
                    clientAdmin =
                    UserData.findClientAdminByClientId(ecProject.getAuthorizedInspectorClient()
                                                           .getId());
                if (clientAdmin
                    != null)
                {
                    emailSet.add(clientAdmin.getEmailAddress());
                }
            }
        }
        StringBuffer
            messageContent =
            new StringBuffer();
        messageContent.append("Comments submitted by:  ");
        messageContent.append(projectPublicComments.getFirstName()
                              + " "
                              + projectPublicComments.getLastName());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Address:  ");
        messageContent.append(projectPublicComments.getStreetAddress()
                              + " ");
        messageContent.append(projectPublicComments.getCity()
                              + ", "
                              + projectPublicComments.getState()
                              + " "
                              + projectPublicComments.getZipCode());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Phone:  ");
        messageContent.append(projectPublicComments.getPhone());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("E-mail Address:  ");
        messageContent.append(projectPublicComments.getEmailAddress());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Referenced Project:  ");
        messageContent.append(ecProject.getName());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Permit Number:  "
                              + ecProject.getPermitNumber());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Project Address:  ");
        messageContent.append(ecProject.getAddress()
                              + " ");
        messageContent.append(ecProject.getCity()
                              + ", "
                              + ecProject.getState()
                              + " "
                              + ecProject.getZip());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Comments:");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(StringUtil.filterHTML(projectPublicComments.getComments()));
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("You can access PermiTrack Erosion Control (ESC) at "
                              + ApplicationProperties.getProperty("base.url"));
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("To access PermiTrack support, you can contact PermiTrack support via email at "
                              + ApplicationProperties.getProperty("mail.support.address")
                              + ", or call 612-284-6331.");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Thank you!");
        sendEmail(fromEmail,
                  emailSet,
                  "Erosion Control (ESC) - Notice of Public Comment",
                  messageContent.toString());
    }

    private void doECInspectionReportSecureAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doInspectionReportSecureAction() clientData.getName() = "
                 + clientData.getName());
        String
            usernameEncryptedString =
            request.getParameter(RequestKeys.USERNAME);
        if (usernameEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.USERNAME
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            usernameString;
        try
        {
            usernameString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    usernameEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.USERNAME
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            portalUser =
            getPortalUser(request);
        if (portalUser
            == null
            || !portalUser.equals(usernameString))
        {
            String
                msg =
                "The portal user does not appear to be logged in.  Access denied.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        String
            inspectionIdEncryptedString =
            request.getParameter(RequestKeys.EC_INSPECTION_ID);
        if (inspectionIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_INSPECTION_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            inspectionIdString;
        try
        {
            inspectionIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    inspectionIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_INSPECTION_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            inspectionId =
            null;
        if (inspectionIdString
            != null)
        {
            inspectionId =
                new Integer(inspectionIdString);
        }
        try
        {
            ECInspectionReport
                ecInspectionReport =
                new ECInspectionReport();
            ecInspectionReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                  clientData.getId());
            ecInspectionReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                                  projectId);
            ecInspectionReport.setReportParameter(ECReportParameter.INSPECTION_ID,
                                                  inspectionId);
            // TODO
            // copy attached documents to the output file directory
            log.info("Get File directory for input files.");
            String
                outputDir =
                ApplicationProperties.getProperty("base.public.directory")
                + "/client"
                + clientData.getId()
                + "/temp/";
            File
                pFileOut =
                new File(outputDir);
            if (!pFileOut.exists())
            {
                pFileOut.mkdirs();
            }
            try
            {
                File
                    aFileStorage =
                    new File(ApplicationProperties.getProperty("base.document.directory")
                             + "/client"
                             + clientData.getId()
                             +
                             "/"
                             + ApplicationProperties.getProperty("application.erosioncontrol")
                             + "/project"
                             + projectId
                             +
                             "/inspection"
                             + inspectionId);
                transferFiles(aFileStorage,
                              pFileOut);
            }
            catch (Exception e)
            {
                log.error("Could not copy files to publish directory ",
                          e);
            }
            String
                aPublishURI =
                "/client"
                + clientData.getId()
                + "/"
                + ApplicationProperties.getProperty("application.erosioncontrol")
                + "/project"
                + projectId
                + "/";
            ecInspectionReport.setReportParameter(ECReportParameter.BASE_URL,
                                                  ApplicationProperties.getBaseURL(request));
            ecInspectionReport.setReportParameter(ECReportParameter.PUBLISH_URI,
                                                  aPublishURI);
            ServletContext
                servletContext =
                request.getSession()
                    .getServletContext();
            ecInspectionReport.setReportParameter(ECReportParameter.INSP_DOCUMENT_SUBREPORT,
                                                  new File(servletContext.getRealPath("/reports/esc/ECInspectionDocumentsSubreport.jasper")));
            // Create the output file name based on the current time, user, and report format
            String
                reportFormat =
                "PDF";
            if (reportFormat.equalsIgnoreCase("XLS"))
            {
                ExcelApiReportRunner
                    excelApiReportRunner =
                    new ExcelApiReportRunner(ecInspectionReport);
                excelApiReportRunner.runToFile(request,
                                               getOutputFile(clientData,
                                                             reportFormat));
                downloadFile(excelApiReportRunner.getOutputFile(),
                             response);
            }
            else if (reportFormat.equalsIgnoreCase("PDF"))
            {
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(ecInspectionReport);
                pdfReportRunner.runToFile(request,
                                          getOutputFile(clientData,
                                                        reportFormat));
                downloadFile(pdfReportRunner.getOutputFile(),
                             response);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),
                      e);
        }
    }

    private void doECProjectReportSecureAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doECProjectReportSecureAction() clientData.getName() = "
                 + clientData.getName());
        String
            usernameEncryptedString =
            request.getParameter(RequestKeys.USERNAME);
        if (usernameEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.USERNAME
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            usernameString;
        try
        {
            usernameString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    usernameEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.USERNAME
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            portalUser =
            getPortalUser(request);
        if (portalUser
            == null
            || !portalUser.equals(usernameString))
        {
            String
                msg =
                "The portal user does not appear to be logged in.  Access denied.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        try
        {
            ECProjectReport
                ecProjectReport =
                new ECProjectReport();
            ecProjectReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                               clientData.getId());
            ecProjectReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                               projectId);
            // Create the output file name based on the current time, user, and report format
            String
                reportFormat =
                "PDF";
            PDFReportRunner
                pdfReportRunner =
                new PDFReportRunner(ecProjectReport);
            pdfReportRunner.runToFile(request,
                                      getOutputFile(clientData,
                                                    reportFormat));
            downloadFile(pdfReportRunner.getOutputFile(),
                         response);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),
                      e);
        }
    }

    private void doECInspectionFormReportSecureAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        ClientData
            clientData =
            (ClientData) request.getAttribute(RequestKeys.CLIENT_DATA);
        log.info("PublicReportServlet.doECInspectionFormReportSecureAction() clientData.getName() = "
                 + clientData.getName());
        String
            usernameEncryptedString =
            request.getParameter(RequestKeys.USERNAME);
        if (usernameEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.USERNAME
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            usernameString;
        try
        {
            usernameString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    usernameEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.USERNAME
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        // Check the RequestKeys.USERNAME passed on the request with the CommonConstants.PORTAL_USER
        // found in the client cookie.  If they do not match then display
        // an error.
        String
            portalUser =
            getPortalUser(request);
        if (portalUser
            == null
            || !portalUser.equals(usernameString))
        {
            String
                msg =
                "The portal user does not appear to be logged in.  Access denied.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdEncryptedString =
            request.getParameter(RequestKeys.EC_PROJECT_ID);
        if (projectIdEncryptedString
            == null)
        {
            String
                msg =
                "The request param '"
                + RequestKeys.EC_PROJECT_ID
                + "' is required.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            projectIdString;
        try
        {
            projectIdString =
                CryptoUtils.decrypt(CryptoUtils.getDefaultKey(),
                                    projectIdEncryptedString);
        }
        catch (CryptoException ce)
        {
            String
                msg =
                "Could not decrypt parameter '"
                + RequestKeys.EC_PROJECT_ID
                + "'.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        Integer
            projectId =
            null;
        if (projectIdString
            != null)
        {
            projectId =
                new Integer(projectIdString);
        }
        try
        {
            ECInspectionFormReport
                ecInspectionFormReport =
                new ECInspectionFormReport();
            ecInspectionFormReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                      clientData.getId());
            ecInspectionFormReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                                      projectId);
            String
                reportFormat =
                "PDF";
            PDFReportRunner
                pdfReportRunner =
                new PDFReportRunner(ecInspectionFormReport);
            pdfReportRunner.runToFile(request,
                                      getOutputFile(clientData,
                                                    reportFormat));
            downloadFile(pdfReportRunner.getOutputFile(),
                         response);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),
                      e);
        }
    }

    private String getPortalUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request)
            .getUsername();
    }

    private File getOutputFile(ClientData clientData, String fileExt)
    {
        Date
            nowDate =
            new Date();
        String
            nowTime =
            String.valueOf(nowDate.getTime());
        String
            outputFileName =
            "report_"
            + nowTime;
        String
            outputDir =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientData.getId()
            + "/temp/";
        File
            outputDirFile =
            new File(outputDir);
        if (!outputDirFile.exists())
        {
            outputDirFile.mkdirs();
        }
        return new File(outputDir
                        + outputFileName
                        + "."
                        + fileExt.toLowerCase());
    }

    private void downloadFile(File downloadFile, HttpServletResponse response)
        throws IOException
    {
        log.debug("downloadFile="
                  + downloadFile.getAbsolutePath());
        log.debug("downloadFile.canRead="
                  + downloadFile.canRead());
        log.debug("downloadFile.exists="
                  + downloadFile.exists());
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

    private void transferFiles(File pFileIn, File pFileOut)
        throws java.io.IOException
    {
        log.info("File In = "
                 + pFileIn.getName()
                 + " File Out = "
                 + pFileOut.getName());
        File[]
            aFileArray =
            pFileIn.listFiles();
        if (aFileArray
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < aFileArray.length; i++)
            {
                File
                    aFileIn =
                    aFileArray[i];
                if (aFileIn.isDirectory())
                {
                    File
                        pNewFileOut =
                        new File(pFileOut.getAbsolutePath()
                                 + "/"
                                 + aFileIn.getName());
                    pNewFileOut.mkdirs();
                    log.info("Made Dir: "
                             + pNewFileOut.getAbsolutePath());
                    File[]
                        directoryFiles =
                        pNewFileOut.listFiles();
                    if (directoryFiles
                        != null)
                    {
                        for (
                            int
                                j =
                                0; j
                                   < directoryFiles.length; j++)
                        {
                            log.info("Deleting: "
                                     + directoryFiles[j].getAbsolutePath());
                            if (directoryFiles[j].isFile())
                            {
                                try
                                {
                                    directoryFiles[j].delete();
                                }
                                catch (Exception deleteFileException)
                                {
                                }
                            }
                        }
                    }
                    transferFiles(aFileIn,
                                  pNewFileOut);
                    // if the directory is empty, delete it
                    File[]
                        listfiles =
                        pNewFileOut.listFiles();
                    if (listfiles
                        != null
                        && listfiles.length
                           == 0)
                    {
                        log.info("Deleting Dir: "
                                 + pNewFileOut.getAbsolutePath());
                        try
                        {
                            pNewFileOut.delete();
                        }
                        catch (Exception deleteDirException)
                        {
                        }
                    }
                }
                else
                {
                    File
                        aFileOut =
                        new File(pFileOut.getAbsolutePath()
                                 + "/"
                                 + aFileIn.getName());
                    log.info("Out File: "
                             + aFileOut.getAbsolutePath());
                    log.info("Exists: "
                             + aFileOut.exists());
                    FileInputStream
                        aFileStorageInputStream =
                        new FileInputStream(aFileIn);
                    FileOutputStream
                        aFileOutputStream =
                        new FileOutputStream(aFileOut);
                    int
                        bytesRead;
                    while ((
                               bytesRead =
                                   aFileStorageInputStream.read())
                           != -1)
                    {
                        aFileOutputStream.write(bytesRead);
                    }
                    aFileOutputStream.close();
                    aFileStorageInputStream.close();
                    log.info("Copied: "
                             + aFileIn.getAbsolutePath()
                             + " to "
                             + aFileOut.getAbsolutePath());
                }
            }
        }
    }
}
