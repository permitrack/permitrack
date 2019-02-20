package com.sehinc.environment.action.substance;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.substance.EnvSubstance;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubstanceCreateAction
    extends SubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceCreateAction.class);

    public ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward =
            CommonConstants.FORWARD_CONTINUE;
        String
            strMod =
            "SubstanceCreateAction. ";
        String
            strLog =
            strMod
            + "substanceAction() ";
        String
            strError =
            strLog;
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("substance.create.cancel.action"), request);
            return mapping.findForward("substance.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    objClient =
                    getClientValue(request);
                SubstanceForm
                    objS =
                    (SubstanceForm) form;
                EnvSubstance
                    dupSub =
                    EnvSubstance.findByNameAndNumber(objClient.getId(),
                                                     objS.getName(),
                                                     objS.getPartNum());
                if (dupSub
                    != null)
                {
                    addMessage(new ActionMessage("substance.duplicate.error"), request);
                    return mapping.findForward("substance.list.page");
                }
                else
                {
                    EnvSubstance
                        substanceData =
                        new EnvSubstance();
                    substanceData.setClientId(objClient.getId());
                    substanceData.setName(objS.getName());
                    substanceData.setPartNum(objS.getPartNum());
                    substanceData.setSubstanceType(objS.getSubstanceTypeCode());
                    substanceData.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    Integer
                        intSubstanceId =
                        substanceData.save(objUser);
                    LOG.debug(strLog
                              + "New Substance created with ID = "
                              + intSubstanceId.toString());
                    setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                                        intSubstanceId,
                                        request);
                }
            }
            catch (Exception eActive)
            {
                strError =
                    strError
                    + "Error.  Message: "
                    + eActive.getMessage();
                LOG.debug(strError);
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strError),
                                    request);
                strForward =
                    CommonConstants.FORWARD_FAIL;
            }
        }
        addMessage(new ActionMessage("substance.create.success"), request);
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
    }
}