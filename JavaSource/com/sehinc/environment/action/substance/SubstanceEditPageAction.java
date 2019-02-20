package com.sehinc.environment.action.substance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubstanceEditPageAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceEditPageAction.class);

    public ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceEditPageAction. ";
        strLog =
            strMod
            + "substanceAction() ";
        LOG.info(strLog
                 + "in action");
        SubstanceForm
            substanceForm =
            (SubstanceForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_SUBSTANCE_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("substance.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("substance.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("substance.list.page");
        }
        Integer
            substanceId;
        if (request.getParameter(RequestKeys.EV_SUBSTANCE_ID)
            != null)
        {
            substanceId =
                new Integer(request.getParameter(RequestKeys.EV_SUBSTANCE_ID));
            LOG.debug("substanceId="
                      + request.getParameter(RequestKeys.EV_SUBSTANCE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                     request)
                 != null)
        {
            substanceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                              request);
            LOG.debug("substanceId="
                      + getSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("substance.error.no.substance.on.request"));
            addError(new ActionMessage("substance.error.no.substance.on.request"), request);
            return mapping.findForward("substance.list.page");
        }
        EnvSubstance
            envSubstance =
            new EnvSubstance(substanceId);
        try
        {
            envSubstance.load();
            if (!envSubstance.getStatus()
                .getCode()
                .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The requested Substance ID = "
                                    + substanceId
                                    + " does not exist.");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {substanceId};
            LOG.error(ApplicationResources.getProperty("substance.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("substance.error.load.failed",
                                       parameters), request);
            return mapping.findForward("substance.list.page");
        }
        substanceForm.setId(envSubstance.getId());
        substanceForm.setName(envSubstance.getName());
        substanceForm.setPartNum(envSubstance.getPartNum());
        substanceForm.setClientId(envSubstance.getClientId());
        substanceForm.setStatusCode(envSubstance.getStatus()
                                        .getCode());
        substanceForm.setSubstanceTypeCode(envSubstance.getSubstanceType()
                                               .getCode());
        List
            subTypes =
            EnvSubstanceTypeData.findAllByClientId(envSubstance.getClientId());
        setSessionAttribute(SessionKeys.EV_SUBSTANCE_TYPE_LIST,
                            subTypes,
                            request);
        setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                            envSubstance.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SUBSTANCE_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.SUBSTANCE_VIEW_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SUBSTANCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SUBSTANCE_EDIT_MENU_ITEM);
    }
}