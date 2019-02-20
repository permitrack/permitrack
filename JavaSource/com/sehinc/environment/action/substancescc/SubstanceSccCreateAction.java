package com.sehinc.environment.action.substancescc;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.substancescc.EnvSubstanceSccInfo;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubstanceSccCreateAction
    extends SubstanceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceSccCreateAction.class);

    public ActionForward substanceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceSccCreateAction. ";
        strLog =
            strMod
            + "substanceSccAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("substance.scc.create.cancel.action"), request);
            return mapping.findForward("substance.view.page");
        }
        else
        {
            try
            {
                SubstanceSccForm
                    ssForm =
                    (SubstanceSccForm) form;
                Integer
                    substanceId =
                    ssForm.getSubstanceId();
                setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                    substanceId,
                                    request);
                if (ssForm.getSccInfoId()
                    == 0)
                {
                    addMessage(new ActionMessage("substance.scc.select"), request);
                    return mapping.getInputForward();
                }
                EnvSubstanceSccInfo
                    dupSS =
                    EnvSubstanceSccInfo.findBySubstanceIdAndSccInfoId(ssForm.getSubstanceId(),
                                                                      ssForm.getSccInfoId());
                if (dupSS
                    != null)
                {
                    addMessage(new ActionMessage("substance.scc.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvSubstanceSccInfo
                        substanceScc =
                        new EnvSubstanceSccInfo();
                    substanceScc.setSubstanceId(substanceId);
                    substanceScc.setSccInfoId(ssForm.getSccInfoId());
                    substanceScc.setSccNumber(ssForm.getSccNumber());
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        substanceSccId =
                        substanceScc.save(objUser);
                    LOG.debug(strLog
                              + "New SubstanceScc SccInfo created with ID = "
                              + substanceSccId.toString());
                }
            }
            catch (Exception eActive)
            {
                LOG.debug("Error.  Message: "
                          + eActive.getMessage());
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strLog
                                                  + "Error.  Message: "
                                                  + eActive.getMessage()),
                                    request);
            }
        }
        return mapping.findForward("continue");
    }
}
