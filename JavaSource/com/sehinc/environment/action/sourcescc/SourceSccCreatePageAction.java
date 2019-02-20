package com.sehinc.environment.action.sourcescc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
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
import java.util.List;

public class SourceSccCreatePageAction
    extends SourceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSccCreatePageAction.class);

    public ActionForward sourceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSccCreatePageAction. ";
        strLog =
            strMod
            + "sourceSccAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SCC_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new source scc info.");
            addError(new ActionMessage("source.scc.create.unauthorized"), request);
            return mapping.findForward("source.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new source scc info page.");
        try
        {
            SourceSccForm
                ssForm =
                (SourceSccForm) form;
            ssForm.reset();
            Integer
                sourceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                              request);
            ssForm.setSourceId(sourceId);
            ClientValue
                clientValue =
                getClientValue(request);
            List
                sccInfo =
                EnvSccInfo.findByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_SCC_ACTIVE_LIST_BY_CLIENT,
                                sccInfo,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("source.scc.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("source.scc.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_SCC_MENU_ITEM);
    }
}


