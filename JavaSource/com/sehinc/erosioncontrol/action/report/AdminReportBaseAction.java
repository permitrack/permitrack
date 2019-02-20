package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.action.base.BaseAction;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;

public abstract class AdminReportBaseAction
    extends BaseAction
{
    public abstract ActionForward adminReportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return adminReportAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_REPORT_MENU_ITEM_NAME);
    }

    @Override
    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        PrimaryMenu
            menu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           menu,
                                           request);
        return (PrimaryMenu) SessionService.getSessionAttribute(SessionKeys.PRIMARY_MENU,
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