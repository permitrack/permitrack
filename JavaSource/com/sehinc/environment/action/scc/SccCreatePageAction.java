package com.sehinc.environment.action.scc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
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

public class SccCreatePageAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccCreatePageAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccCreatePageAction. ";
        strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create new SccInfo.");
            addError(new ActionMessage("scc.create.unauthorized"), request);
            return mapping.findForward("scc.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new SccInfo page.");
        try
        {
            SccForm
                sccForm =
                (SccForm) form;
            sccForm.reset();
            ClientValue
                clientValue =
                getClientValue(request);
            sccForm.setClientId(clientValue.getId());
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("scc.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("scc.create.load.failed",
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
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SCC_CREATE_MENU_ITEM);
    }
}
