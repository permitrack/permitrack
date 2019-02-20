package com.sehinc.environment.action.substance;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubstanceEditAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceEditAction.class);

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
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("substance.edit.cancel.action"), request);
            return mapping.findForward("substance.list.page");
        }
        else
        {
            SubstanceForm
                substanceForm =
                (SubstanceForm) form;
            UserValue
                userValue =
                getUserValue(request);
            ClientValue
                objClient =
                getClientValue(request);
            Integer
                substanceId =
                substanceForm.getId();
            EnvSubstance
                dupSub =
                EnvSubstance.findByNameAndNumber(objClient.getId(),
                                                 substanceForm.getName(),
                                                 substanceForm.getPartNum());
            if (dupSub
                != null)
            {
                if (substanceId.intValue()
                    != dupSub.getId()
                    .intValue())
                {
                    addMessage(new ActionMessage("substance.duplicate.error"), request);
                    request.setAttribute(RequestKeys.EV_SUBSTANCE_ID,
                                         substanceId);
                    return mapping.findForward("substance.list.page");
                }
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
                envSubstance.setName(substanceForm.getName());
                envSubstance.setPartNum(substanceForm.getPartNum());
                envSubstance.setClientId(substanceForm.getClientId());
                envSubstance.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                envSubstance.setSubstanceType(substanceForm.getSubstanceTypeCode());
                envSubstance.setClientId(objClient.getId());
                envSubstance.save(userValue);
                setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                    substanceId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {substanceId};
                LOG.error(ApplicationResources.getProperty("substance.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("substance.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("substance.list.page");
            }
        }
        addMessage(new ActionMessage("substance.edit.success"), request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SUBSTANCE_VIEW_MENU),
                        request);
    }
}