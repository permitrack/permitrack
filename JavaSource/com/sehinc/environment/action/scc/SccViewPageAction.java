package com.sehinc.environment.action.scc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.scc.EnvSccInfo;
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

public class SccViewPageAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccViewPageAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccViewPageAction. ";
        String
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
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("scc.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("scc.view.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("scc.list.page");
        }
        Integer
            sccId;
        LOG.debug("sccId="
                  + request.getParameter(RequestKeys.EV_SCC_ID));
        if (request.getParameter(RequestKeys.EV_SCC_ID)
            != null)
        {
            sccId =
                new Integer(request.getParameter(RequestKeys.EV_SCC_ID));
            LOG.debug("Getting the sccId="
                      + sccId
                      + " from the request.");
        }
        else if (getSessionAttribute(SessionKeys.EV_SCC_ID,
                                     request)
                 != null)
        {
            sccId =
                (Integer) getSessionAttribute(SessionKeys.EV_SCC_ID,
                                              request);
            LOG.debug("Getting the sccId="
                      + sccId
                      + " from the session.");
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
            sccForm.setId(envScc.getId());
            sccForm.setClientId(envScc.getClientId());
            sccForm.setNumber(envScc.getNumber());
            sccForm.setDescription(envScc.getDescription());
            sccForm.setMajIndustrialGroup(envScc.getMajIndustrialGroup());
            sccForm.setRawMaterial(envScc.getRawMaterial());
            sccForm.setEmittingProcess(envScc.getEmittingProcess());
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
        getTertiaryMenu(request).setCurrentItem(null);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SCC_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.SCC_VIEW_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}