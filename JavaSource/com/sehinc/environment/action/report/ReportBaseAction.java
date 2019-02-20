package com.sehinc.environment.action.report;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.environment.action.base.ClientBaseAction;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import org.apache.log4j.Logger;
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
    private static
    Logger
        LOG =
        Logger.getLogger(ReportBaseAction.class);

    public abstract ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In ReportBaseAction");
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
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_REPORTS_MENU_ITEM);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
/*
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.REPORT_LIST_MENU);
*/
        setSecondaryMenu(null,
                         request);
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