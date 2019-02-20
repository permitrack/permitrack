package com.sehinc.environment.action.permit;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermit;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitCreateAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitCreateAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward =
            CommonConstants.FORWARD_CONTINUE;
        String
            strMod =
            "PermitCreateAction. ";
        String
            strLog =
            strMod
            + "permitAction() ";
        String
            strError =
            strLog;
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.create.cancel.action"), request);
            return mapping.findForward("permit.list.page");
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
                PermitForm
                    objP =
                    (PermitForm) form;
                EnvPermit
                    dupPermit =
                    EnvPermit.findByClientIdAndName(objClient.getId(),
                                                    objP.getName());
                if (dupPermit
                    != null)
                {
                    addMessage(new ActionMessage("permit.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvPermit
                        permitData =
                        new EnvPermit();
                    permitData.setClientId(objClient.getId());
                    permitData.setName(objP.getName());
                    permitData.setDescription(objP.getDescription());
                    permitData.setActiveTs(DateUtil.parseDate(objP.getActiveTsString()));
                    permitData.setInactiveTs(DateUtil.parseDate(objP.getInactiveTsString()));
                    permitData.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    Integer
                        intPermitId =
                        permitData.save(objUser);
                    LOG.debug(strLog
                              + "New Permit created with ID = "
                              + intPermitId.toString());
                    setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                        intPermitId,
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
        addMessage(new ActionMessage("permit.create.success"), request);
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
    }
}