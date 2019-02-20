package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.scc.EnvSccInfoLibrary;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccLibraryEditPageAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryEditPageAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccLibraryEditPageAction. ";
        strLog =
            strMod
            + "sccLibraryAction() ";
        LOG.info(strLog
                 + "in action");
        SccLibraryForm
            sccForm =
            (SccLibraryForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_SCC_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("scc.library.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("scc.library.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("scc.library.list.page");
        }
        Integer
            sccLibraryId;
        if (request.getParameter(RequestKeys.EV_SCC_LIBRARY_ID)
            != null)
        {
            sccLibraryId =
                new Integer(request.getParameter(RequestKeys.EV_SCC_LIBRARY_ID));
            LOG.debug("sccId="
                      + sccLibraryId);
        }
        else if (getSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                                     request)
                 != null)
        {
            sccLibraryId =
                (Integer) getSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                                              request);
            LOG.debug("sccId="
                      + sccLibraryId);
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("scc.library.error.no.scc.info.on.request"));
            addError(new ActionMessage("scc.library.error.no.scc.info.on.request"), request);
            return mapping.findForward("scc.library.list.page");
        }
        EnvSccInfoLibrary
            envScc =
            new EnvSccInfoLibrary(sccLibraryId);
        try
        {
            envScc.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {sccLibraryId};
            LOG.error(ApplicationResources.getProperty("scc.library.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("scc.library.error.load.failed",
                                       parameters), request);
            return mapping.findForward("scc.library.list.page");
        }
        sccForm.setId(envScc.getId());
        sccForm.setNumber(envScc.getNumber());
        sccForm.setDescription(envScc.getDescription());
        sccForm.setMajIndustrialGroup(envScc.getMajIndustrialGroup());
        sccForm.setRawMaterial(envScc.getRawMaterial());
        sccForm.setEmittingProcess(envScc.getEmittingProcess());
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SCC_LIBRARY_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SCC_EDIT_MENU_ITEM);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.SCC_LIBRARY_VIEW_MENU),
                         request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SCC_LIBRARY_VIEW_MENU_ITEM);
    }
}
