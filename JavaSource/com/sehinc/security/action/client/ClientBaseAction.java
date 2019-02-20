package com.sehinc.security.action.client;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.security.action.base.BaseAction;
import com.sehinc.security.action.navigation.PrimaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ClientBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientBaseAction.class);

    public abstract ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Integer
            clientValue =
            getClientIdFromRequestOrSession(request);
        if (clientValue
            == null
            || clientValue
               < 1)
        {
            return mapping.findForward("client.list.page");
        }
        return clientAction(mapping,
                            form,
                            request,
                            response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws Exception
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.SECURITY_CLIENT_MENU_ITEM_NAME);
    }

    protected static void processFormFile(FormFile pFile, Integer pClientId)
        throws Exception
    {
        InputStream
            stream =
            pFile.getInputStream();
        String
            aFileLocation =
            "";
        try
        {
            aFileLocation =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                + pClientId
                + "/logo";
        }
        catch (Exception e)
        {
            String
                strMod =
                "com.sehinc.security.action.client.ClientBaseAction. ";
            LOG.debug(strMod
                      + "Unable to get base.document.directory from the ApplicationProperties.getProperty method.");
        }
        File
            aNewFile =
            new File(aFileLocation);
        aNewFile.mkdirs();
        OutputStream
            bos =
            new FileOutputStream(aFileLocation
                                 + "/"
                                 + pFile.getFileName());
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
}