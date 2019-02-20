package com.sehinc.environment.action.permitsubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.db.permitsubstance.EnvPermitSubstance;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitSubstanceCreateAction
    extends PermitSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitSubstanceCreateAction.class);

    public ActionForward permitSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitSubstanceCreateAction. ";
        strLog =
            strMod
            + "permitsubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.substance.create.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            try
            {
                PermitSubstanceForm
                    psForm =
                    (PermitSubstanceForm) form;
                Integer
                    permitId =
                    psForm.getPermitId();
                setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                    permitId,
                                    request);
                EnvPermit
                    permit =
                    new EnvPermit(permitId);
                if (permit.load())
                {
                    psForm.setPermitName(permit.getName());
                    psForm.setPermitDesc(permit.getDescription());
                }
                if (psForm.getSubstanceTypeCd()
                    == 0)
                {
                    addMessage(new ActionMessage("permit.substance.create.select"), request);
                    return mapping.getInputForward();
                }
                EnvPermitSubstance
                    dupPS =
                    EnvPermitSubstance.findByPermitAndSubstanceType(psForm.getPermitId(),
                                                                    psForm.getSubstanceTypeCd());
                if (dupPS
                    != null)
                {
                    addMessage(new ActionMessage("permit.substance.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvPermitSubstance
                        envPS =
                        new EnvPermitSubstance();
                    envPS.setPermitId(psForm.getPermitId());
                    envPS.setSubstanceType(psForm.getSubstanceTypeCd());
                    envPS.setChargeable(psForm.getChargeable());
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        permitSubstanceId =
                        envPS.save(objUser);
                    LOG.debug(strLog
                              + "New Permit Substance created with ID = "
                              + permitSubstanceId.toString());
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