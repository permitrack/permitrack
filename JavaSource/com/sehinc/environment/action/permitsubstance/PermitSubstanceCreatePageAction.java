package com.sehinc.environment.action.permitsubstance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.permit.EnvPermit;
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

public class PermitSubstanceCreatePageAction
    extends PermitSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitSubstanceCreatePageAction.class);

    public ActionForward permitSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitSubstanceCreatePageAction. ";
        strLog =
            strMod
            + "PermitSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_SUBSTANCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new permitted substance.");
            addError(new ActionMessage("permit.substance.create.unauthorized"), request);
            return mapping.findForward("permit.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new permit substance page.");
        try
        {
            PermitSubstanceForm
                psForm =
                (PermitSubstanceForm) form;
            psForm.reset();
            Integer
                permitId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                              request);
            EnvPermit
                permit =
                new EnvPermit(permitId);
            permit.load();
            psForm.setPermitId(permitId);
            psForm.setPermitName(permit.getName());
            psForm.setPermitDesc(permit.getDescription());
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
            addError(new ActionMessage("permit.substance.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("permit.substance.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_SUBSTANCE_MENU_ITEM);
    }
}