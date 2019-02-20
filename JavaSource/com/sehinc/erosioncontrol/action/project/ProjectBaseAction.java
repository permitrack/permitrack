package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.db.project.EcProjectDocument;
import com.sehinc.erosioncontrol.value.project.ProjectDocumentValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ProjectBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception;

    @Override
    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        return projectAction(mapping,
                             form,
                             request,
                             response,
                             clientValue,
                             userValue,
                             securityManager);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME);
    }

    public void processProjectDocument(ProjectDocumentValue projectDocumentValue, Integer pClientId)
        throws Exception
    {
        InputStream
            stream =
            projectDocumentValue.getFormFile()
                .getInputStream();
        String
            aFileLocation =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + pClientId
            + "/"
            + "erosioncontrol"
            + "/project"
            + projectDocumentValue.getProjectId();
        File
            aNewFile =
            new File(aFileLocation);
        aNewFile.mkdirs();
        OutputStream
            bos =
            new FileOutputStream(aFileLocation
                                 + "/"
                                 + projectDocumentValue.getFormFile()
                .getFileName());
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       stream.read(buffer,
                                   0,
                                   8192))
               != -1)
        {
            bos.write(buffer,
                      0,
                      bytesRead);
        }
        bos.close();
        stream.close();
        EcProjectDocument
            projectDocument =
            new EcProjectDocument();
        projectDocument.setProjectId(projectDocumentValue.getProjectId());
        projectDocument.setLocation(aFileLocation
                                    + "/");
        projectDocument.setName(projectDocumentValue.getFormFile()
                                    .getFileName());
        projectDocument.setComment(projectDocumentValue.getComment());
        if (projectDocumentValue.getId()
            != null
            && projectDocumentValue.getId()
               > 0)
        {
            projectDocument.setId(projectDocumentValue.getId());
            projectDocument.update();
        }
        else
        {
            projectDocument.insert();
        }
    }

    public void deleteProjectDocument(ProjectDocumentValue projectDocumentValue)
    {
        EcProjectDocument
            projectDocument =
            new EcProjectDocument();
        projectDocument.setId(projectDocumentValue.getId());
        projectDocument.load();
        File
            aFile =
            new File(projectDocument.getLocation()
                     + projectDocument.getName());
        projectDocument.delete();
        aFile.delete();
    }

    protected Integer getProjectId(HttpServletRequest request)
    {
        Integer
            projectId =
            null;
        if (request.getParameter(RequestKeys.EC_PROJECT_ID)
            != null)
        {
            projectId =
                new Integer(request.getParameter(RequestKeys.EC_PROJECT_ID));
        }
        else
        {
            ProjectValue
                projectValue =
                (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                                   request);
            if (projectValue
                != null)
            {
                projectId =
                    projectValue.getId();
            }
        }
        return projectId;
    }

    protected void setMapLink(HttpServletRequest request, ClientValue clientValue, Integer projectId)
    {
        request.setAttribute(SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL,
                             getPublicMapUrl(clientValue.getId(),
                                             request));
        request.setAttribute(SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL,
                             getPrivateMapUrl(clientValue.getId(), projectId != null ? projectId : 0,
                                              request));
    }
}