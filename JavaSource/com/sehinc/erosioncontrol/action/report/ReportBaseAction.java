package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public abstract class ReportBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return reportAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_REPORT_MENU_ITEM_NAME);
    }

    protected String getFileDownloadURL(String username, Integer clientId, File file, HttpServletRequest request)
    {
        FileDownloadWrapper
            fileDownloadWrapper =
            new FileDownloadWrapper();
        fileDownloadWrapper.setClientId(clientId);
        fileDownloadWrapper.setUsername(username);
        fileDownloadWrapper.setFile(file);
        return fileDownloadWrapper.getDownloadURL(request);
    }
}