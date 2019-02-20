package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpDocument;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionDocument;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public abstract class InspectionBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionBaseAction.class);
    protected static
    int
        BMP_MAX =
        50;

    public abstract ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        return inspectionAction(mapping,
                                form,
                                request,
                                response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_VIEW_MENU_NAME),
                         request);
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME);
    }

    private void processDocument(FormFile file, String aFileLocation)
        throws Exception
    {
        InputStream
            stream =
            file.getInputStream();
        File
            aNewFile =
            new File(aFileLocation);
        aNewFile.mkdirs();
        OutputStream
            bos =
            new FileOutputStream(aFileLocation
                                 + "/"
                                 + file.getFileName());
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
    }

    public void processInspectionDocument(InspectionDocumentValue inspectionDocumentValue, Integer clientId, Integer projectId, Integer inspectionId)
        throws Exception
    {
        if (inspectionDocumentValue.getFormFile()
            != null
            && inspectionDocumentValue.getFormFile()
                   .getFileName()
               != null
            && inspectionDocumentValue.getFormFile()
                   .getFileName()
                   .trim()
                   .length()
               > 0)
        {
            String
                aFileLocation =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                + clientId
                + "/"
                + ApplicationProperties.getProperty("application.erosioncontrol")
                + "/project"
                + projectId
                + "/inspection"
                + inspectionId;
            processDocument(inspectionDocumentValue.getFormFile(),
                            aFileLocation);
            LOG.debug("THE INSPECTION_ID = "
                      + inspectionDocumentValue.getInspectionId());
            LOG.debug("THE DOC ID = "
                      + inspectionDocumentValue.getId());
            Boolean
                update =
                false;
            // Check if docValue has an id.  This is an id for an existing doc in the db.
            // if id exists, then this doc will be an update.
            // if no id, then this doc will be an insert.
            if (inspectionDocumentValue.getId()
                != null)
            {
                EcInspectionDocument
                    existing =
                    new EcInspectionDocument(inspectionDocumentValue.getId());
                if (existing.load())
                {
                    File
                        aFile =
                        new File(existing.getLocation()
                                 + existing.getName());
                    aFile.delete();
                    update =
                        true;
                }
            }
            EcInspectionDocument
                newDoc =
                new EcInspectionDocument();
            newDoc.setInspectionId(inspectionDocumentValue.getInspectionId());
            newDoc.setLocation(aFileLocation
                               + "/");
            newDoc.setName(inspectionDocumentValue.getFormFile()
                               .getFileName());
            newDoc.setComment(inspectionDocumentValue.getComment());
            if (update)
            {
                newDoc.setId(inspectionDocumentValue.getId());
                newDoc.update();
            }
            else
            {
                newDoc.insert();
            }
        }
        else if (inspectionDocumentValue.getIsDeleted())
        {
            if (inspectionDocumentValue.getId()
                != null)
            {
                EcInspectionDocument
                    existing =
                    new EcInspectionDocument(inspectionDocumentValue.getId());
                if (existing.load())
                {
                    File
                        aFile =
                        new File(existing.getLocation()
                                 + existing.getName());
                    aFile.delete();
                    existing.delete();
                }
            }
        }
    }

    public void saveInspectionDocument(InspectionDocumentValue inspectionDocumentValue, Integer clientId, Integer projectId, Integer inspectionId)
        throws Exception
    {
        String
            aFileLocation =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientId
            + "/"
            + ApplicationProperties.getProperty("application.erosioncontrol")
            + "/project"
            + projectId
            + "/inspection"
            + inspectionId;
        processDocument(inspectionDocumentValue.getFormFile(),
                        aFileLocation);
        LOG.debug("THE INSPECTION_ID = "
                  + inspectionDocumentValue.getInspectionId());
        EcInspectionDocument
            inspectionDocument =
            new EcInspectionDocument();
        inspectionDocument.setInspectionId(inspectionDocumentValue.getInspectionId());
        inspectionDocument.setLocation(aFileLocation
                                       + "/");
        inspectionDocument.setName(inspectionDocumentValue.getFormFile()
                                       .getFileName());
        inspectionDocument.setComment(inspectionDocumentValue.getComment());
        inspectionDocument.insert();
    }

    public void processInspectionBmpDocument(InspectionBmpDocumentValue inspectionBmpDocumentValue, Integer clientId, Integer projectId, Integer inspectionId)
        throws Exception
    {
        String
            aFileLocation =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientId
            + "/"
            + ApplicationProperties.getProperty("application.erosioncontrol")
            + "/project"
            + projectId
            + "/inspection"
            + inspectionId
            + "/bmp"
            + inspectionBmpDocumentValue.getInspectionBmpId();
        processDocument(inspectionBmpDocumentValue.getFormFile(),
                        aFileLocation);
        LOG.debug("THE INSPECTION_BMP_ID = "
                  + inspectionBmpDocumentValue.getInspectionBmpId());
        EcInspectionBmpDocument
            inspectionBmpDocument =
            null;
        if (inspectionBmpDocumentValue.getInspectionBmpId()
            != null
            && inspectionBmpDocumentValue.getInspectionBmpId()
                   .intValue()
               > 0)
        {
            inspectionBmpDocument =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmpDocumentValue.getInspectionBmpId());
        }
        if (inspectionBmpDocument
            == null)
        {
            inspectionBmpDocument =
                new EcInspectionBmpDocument();
            inspectionBmpDocument.setInspectionBmpId(inspectionBmpDocumentValue.getInspectionBmpId());
        }
        inspectionBmpDocument.setLocation(aFileLocation
                                          + "/");
        inspectionBmpDocument.setName(inspectionBmpDocumentValue.getFormFile()
                                          .getFileName());
        inspectionBmpDocument.setComment(inspectionBmpDocumentValue.getComment());
        if (inspectionBmpDocument.getId()
            != null)
        {
            inspectionBmpDocument.update();
        }
        else
        {
            inspectionBmpDocument.insert();
        }
    }

    protected void deleteInspectionBmpDocument(InspectionBmpDocumentValue inspectionBmpDocumentValue)
    {
        EcInspectionBmpDocument
            inspectionBmpDocument =
            new EcInspectionBmpDocument();
        inspectionBmpDocument.setId(inspectionBmpDocumentValue.getId());
        inspectionBmpDocument.load();
        File
            aFile =
            new File(inspectionBmpDocument.getLocation()
                     + inspectionBmpDocument.getName());
        inspectionBmpDocument.delete();
        aFile.delete();
    }

    protected void deleteInspectionDocument(InspectionDocumentValue inspectionDocumentValue)
    {
        EcInspectionDocument
            inspectionDocument =
            new EcInspectionDocument();
        inspectionDocument.setId(inspectionDocumentValue.getId());
        inspectionDocument.load();
        File
            aFile =
            new File(inspectionDocument.getLocation()
                     + inspectionDocument.getName());
        inspectionDocument.delete();
        aFile.delete();
    }

    protected Integer getInspectionId(HttpServletRequest request)
    {
        Integer
            inspectionId =
            null;
        if (request.getParameter(RequestKeys.EC_INSPECTION_ID)
            != null)
        {
            inspectionId =
                new Integer(request.getParameter(RequestKeys.EC_INSPECTION_ID));
        }
        else
        {
            InspectionValue
                inspectionValue =
                (InspectionValue) request.getSession()
                    .getAttribute(SessionKeys.EC_INSPECTION);
            if (inspectionValue
                != null)
            {
                inspectionId =
                    inspectionValue.getId();
            }
        }
        return inspectionId;
    }
}