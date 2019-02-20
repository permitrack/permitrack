package com.sehinc.environment.action.substance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubstanceDeleteAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceDeleteAction.class);

    public ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceDeleteAction. ";
        strLog =
            strMod
            + "substanceAction() ";
        LOG.info(strLog
                 + "in action");
        SubstanceForm
            sForm =
            (SubstanceForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("substance.delete.cancel.action"), request);
            return mapping.findForward("substance.list.page");
        }
        else
        {
            UserValue
                userValue =
                getUserValue(request);
            Integer
                substanceId;
            LOG.debug("substanceId="
                      + sForm.getId());
            if (sForm.getId()
                != null)
            {
                substanceId =
                    sForm.getId();
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("substance.error.no.substance.id"));
                addError(new ActionMessage("substance.error.no.substance.id"), request);
                return mapping.findForward("substance.list.page");
            }
            List<EnvSourceSubstance>
                sourceSubstances =
                EnvSourceSubstance.findBySubstanceId(substanceId);
            try
            {
                for (EnvSourceSubstance srcSub : sourceSubstances)
                {
                    srcSub.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                    srcSub.save(userValue);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {substanceId};
                LOG.error(ApplicationResources.getProperty("source.substance.delete.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.substance.delete.failed",
                                           parameters), request);
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
                envSubstance.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envSubstance.save(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {substanceId};
                LOG.error(ApplicationResources.getProperty("substance.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("substance.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("substance.list.page");
            }
            addMessage(new ActionMessage("substance.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}