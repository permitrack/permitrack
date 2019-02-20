package com.sehinc.environment.action.scc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccEditPageAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccEditPageAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccEditPageAction. ";
        strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        SccForm
            sccForm =
            (SccForm) form;
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
            LOG.info(ApplicationResources.getProperty("scc.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("scc.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("scc.list.page");
        }
        Integer
            sccId;
        if (request.getParameter(RequestKeys.EV_SCC_ID)
            != null)
        {
            sccId =
                new Integer(request.getParameter(RequestKeys.EV_SCC_ID));
            LOG.debug("sccId="
                      + request.getParameter(RequestKeys.EV_SCC_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SCC_ID,
                                     request)
                 != null)
        {
            sccId =
                (Integer) getSessionAttribute(SessionKeys.EV_SCC_ID,
                                              request);
            LOG.debug("sccId="
                      + getSessionAttribute(SessionKeys.EV_SCC_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("scc.error.no.scc.info.on.request"));
            addError(new ActionMessage("scc.error.no.scc.info.on.request"), request);
            return mapping.findForward("scc.list.page");
        }
        EnvSccInfo
            envScc =
            new EnvSccInfo(sccId);
        try
        {
            envScc.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {sccId};
            LOG.error(ApplicationResources.getProperty("scc.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("scc.error.load.failed",
                                       parameters), request);
            return mapping.findForward("scc.list.page");
        }
        sccForm.setId(envScc.getId());
        sccForm.setClientId(envScc.getClientId());
        sccForm.setNumber(envScc.getNumber());
        sccForm.setDescription(envScc.getDescription());
        sccForm.setMajIndustrialGroup(envScc.getMajIndustrialGroup());
        sccForm.setRawMaterial(envScc.getRawMaterial());
        sccForm.setEmittingProcess(envScc.getEmittingProcess());
        setSessionAttribute(SessionKeys.EV_SCC_ID,
                            envScc.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SCC_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SCC_EDIT_MENU_ITEM);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.SCC_VIEW_MENU),
                         request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SCC_VIEW_MENU_ITEM);
    }
}
