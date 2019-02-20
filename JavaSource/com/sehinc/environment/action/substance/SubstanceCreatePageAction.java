package com.sehinc.environment.action.substance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
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

public class SubstanceCreatePageAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceCreatePageAction.class);

    public ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceCreatePageAction. ";
        strLog =
            strMod
            + "substanceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SUBSTANCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new substance.");
            addError(new ActionMessage("substance.create.unauthorized"), request);
            return mapping.findForward("substance.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new substance page.");
        try
        {
            SubstanceForm
                objS =
                (SubstanceForm) form;
            objS.reset();
            ClientValue
                clientValue =
                getClientValue(request);
            List
                subTypes =
                EnvSubstanceTypeData.findAllByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_SUBSTANCE_TYPE_LIST,
                                subTypes,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("substance.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("substance.create.load.failed",
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
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SUBSTANCE_CREATE_MENU_ITEM);
    }
}