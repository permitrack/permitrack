package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccLibraryCreatePageAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryCreatePageAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccLibraryCreatePageAction. ";
        strLog =
            strMod
            + "sccLibraryAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_LIBRARY_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create new Scc Library item.");
            addError(new ActionMessage("scc.library.create.unauthorized"), request);
            return mapping.findForward("scc.library.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new Scc Library item page.");
        try
        {
            SccLibraryForm
                sccForm =
                (SccLibraryForm) form;
            sccForm.reset();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("scc.library.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("scc.library.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SCC_LIBRARY_CREATE_MENU_ITEM);
    }
}
