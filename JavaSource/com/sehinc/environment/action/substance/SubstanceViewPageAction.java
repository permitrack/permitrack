package com.sehinc.environment.action.substance;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.db.substancescc.EnvSubstanceSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SubstanceSccInfoValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SubstanceViewPageAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceViewPageAction.class);

    public ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceViewPageAction. ";
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
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_SUBSTANCE_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("substance.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("substance.view.page.not.allowed",
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
            LOG.debug("evSubstanceId="
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
        ClientData
            client =
            new ClientData(envSubstance.getClientId());
        try
        {
            client.load();
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("substance.error.client.load.failed"));
            addError(new ActionMessage("substance.error.client.load.failed"), request);
            return mapping.findForward("substance.list.page");
        }
        ArrayList<SubstanceSccInfoValue>
            substanceSccList =
            new ArrayList<SubstanceSccInfoValue>();
        List
            sccList =
            EnvSubstanceSccInfo.findBySubstanceId(substanceId);
        for (Object o : sccList)
        {
            EnvSubstanceSccInfo
                sscc =
                (EnvSubstanceSccInfo) o;
            try
            {
                SubstanceSccInfoValue
                    asv =
                    new SubstanceSccInfoValue();
                EnvSccInfo
                    esi =
                    new EnvSccInfo(sscc.getSccInfoId());
                esi.load();
                asv.setSubstanceSccInfoId(sscc.getId());
                asv.setSccNumber(esi.getNumber());
                String
                    grp =
                    esi.getMajIndustrialGroup();
                if (grp
                    != null)
                {
                    grp =
                        " "
                        + grp;
                }
                else
                {
                    grp =
                        "";
                }
                String
                    raw =
                    esi.getRawMaterial();
                if (raw
                    != null)
                {
                    raw =
                        " "
                        + raw;
                }
                else
                {
                    raw =
                        "";
                }
                String
                    proc =
                    esi.getEmittingProcess();
                if (proc
                    != null)
                {
                    proc =
                        " "
                        + proc;
                }
                else
                {
                    proc =
                        "";
                }
                asv.setSccDescription(esi.getDescription()
                                      + grp
                                      + raw
                                      + proc);
                substanceSccList.add(asv);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("substance.scc.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("substance.scc.error.load.failed"), request);
            }
        }
        setSessionAttribute(SessionKeys.EV_SUBSTANCE_SCC_ACTIVE_LIST,
                            substanceSccList,
                            request);
        substanceForm.setId(envSubstance.getId());
        substanceForm.setName(envSubstance.getName());
        substanceForm.setPartNum(envSubstance.getPartNum());
        substanceForm.setClientId(envSubstance.getClientId());
        substanceForm.setStatusCode(envSubstance.getStatus()
                                        .getCode());
        substanceForm.setSubstanceTypeDesc(envSubstance.getSubstanceType()
                                               .getDescription());
        setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                            envSubstance.getId(),
                            request);
        setSessionAttribute(SessionKeys.SUBSTANCE_SCC_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SUBSTANCE_SCC_DELETE),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SUBSTANCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(null);
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
}