package com.sehinc.security.action.client;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ClientLogoCreateAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientLogoCreateAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        ClientValue
            clientValue =
            new ClientValue();
        Object
            obj;
        ClientForm
            clientForm =
            (ClientForm) form;
        String
            mstrMod =
            "com.sehinc.security.action.client.ClientLogoCreateAction ";
        LOG.info("In "
                 + mstrMod
                 + ": "
                 + clientForm.getId()
            .toString());
        obj =
            getClientValue(request);
        LOG.info(mstrMod
                 + " Session Object object Type of "
                 + obj.getClass()
            .getName());
        LOG.info(mstrMod
                 + " Local object Type of "
                 + clientValue.getClass()
            .getName());
        clientValue =
            (ClientValue) obj;
        LOG.debug(mstrMod
                  + "Client Value "
                  + clientValue.getName());
        UserValue
            userValue =
            getUserValue(request);
        LOG.debug(mstrMod
                  + "User Value "
                  + userValue.getUsername());
        ClientData
            clientData =
            ClientService.getClient(clientValue);
        LOG.debug(mstrMod
                  + "ClientData "
                  + clientData.getName());
        if (clientData
            != null)
        {
            FormFile
                logoFile =
                clientForm.getLogoFile();
            LOG.debug(mstrMod
                      + "LogoFile "
                      + logoFile.getFileName());
            FormFile
                mapLogoFile =
                clientForm.getMapLogoFile();
            LOG.debug(mstrMod
                      + "MapLogoFile "
                      + mapLogoFile.getFileName());
            try
            {
                if (logoFile
                    != null
                    && !logoFile.getFileName()
                    .equals(""))
                {
                    LOG.debug(mstrMod
                              + " Logo file name is not empty or blank.");
                    ClientBaseAction.processFormFile(logoFile,
                                                     clientValue.getId());
                    LOG.debug(mstrMod
                              + " processed form file of size: "
                              + Integer.toString(logoFile.getFileSize()));
                    clientData.setLogoLocation(logoFile.getFileName());
                    clientData.update(userValue);
                    LOG.debug(mstrMod
                              + "logoFile name saved to client data.");
                    logoFile.destroy();
                }
                else
                {
                    LOG.debug(mstrMod
                              + " logoFile is null or empty string.");
                }
            }
            catch (Exception e)
            {
                LOG.error("Error uploading logo file ",
                          e);
            }
            try
            {
                if (mapLogoFile
                    != null
                    && !mapLogoFile.getFileName()
                    .equals(""))
                {
                    LOG.debug(mstrMod
                              + " Map Logo file name is not empty or blank.");
                    ClientBaseAction.processFormFile(mapLogoFile,
                                                     clientValue.getId());
                    LOG.debug(mstrMod
                              + " processed form file of size: "
                              + Integer.toString(mapLogoFile.getFileSize()));
                    clientData.setMapLogoLocation(mapLogoFile.getFileName());
                    clientData.update(userValue);
                    LOG.debug(mstrMod
                              + "mapLogoFile name saved to client data.");
                    mapLogoFile.destroy();
                }
                else
                {
                    LOG.debug(mstrMod
                              + " mapLogoFile is null or empty string.");
                }
            }
            catch (Exception e)
            {
                LOG.error("Error uploading mapLogo file ",
                          e);
            }
        }
        else
        {
            LOG.debug(mstrMod
                      + " clientData is null");
        }
        return mapping.findForward("continue");
    }
}