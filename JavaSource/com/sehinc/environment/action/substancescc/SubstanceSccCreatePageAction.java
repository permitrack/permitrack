package com.sehinc.environment.action.substancescc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.db.substance.EnvSubstance;
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
import java.util.List;

public class SubstanceSccCreatePageAction
    extends SubstanceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceSccCreatePageAction.class);

    public ActionForward substanceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceSccCreatePageAction. ";
        strLog =
            strMod
            + "substanceSccAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SUBSTANCE_SCC_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new substance scc info.");
            addError(new ActionMessage("substance.scc.create.unauthorized"), request);
            return mapping.findForward("substance.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new substance scc info page.");
        try
        {
            SubstanceSccForm
                ssForm =
                (SubstanceSccForm) form;
            ssForm.reset();
            Integer
                substanceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                              request);
            EnvSubstance
                envSubstance =
                new EnvSubstance(substanceId);
            try
            {
                envSubstance.load();
            }
            catch (Exception e)
            {
                return mapping.findForward("substance.list.page");
            }
            ssForm.setSubstanceId(substanceId);
            request.setAttribute("substanceName",
                                 envSubstance.getName());
            ClientValue
                clientValue =
                getClientValue(request);
            List
                sccInfo =
                EnvSccInfo.findByClientId(clientValue.getId());
            request.setAttribute(SessionKeys.EV_SCC_ACTIVE_LIST_BY_CLIENT,
                                 sccInfo);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("substance.scc.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("substance.scc.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SUBSTANCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SUBSTANCE_SCC_MENU_ITEM);
    }
}


