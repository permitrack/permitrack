package com.sehinc.environment.action.permitfacility;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.db.permitfacility.EnvPermitFacility;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitFacilityCreateAction
    extends PermitFacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitFacilityCreateAction.class);

    public ActionForward permitFacilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitFacilityCreateAction. ";
        strLog =
            strMod
            + "permitFacilityAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.facility.create.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            try
            {
                PermitFacilityForm
                    pfForm =
                    (PermitFacilityForm) form;
                Integer
                    permitId =
                    pfForm.getPermitId();
                setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                    permitId,
                                    request);
                EnvPermit
                    permit =
                    new EnvPermit(permitId);
                if (permit.load())
                {
                    pfForm.setPermitName(permit.getName());
                    pfForm.setPermitDesc(permit.getDescription());
                }
                if (pfForm.getFacilityId()
                    == 0)
                {
                    addMessage(new ActionMessage("permit.facility.create.select"), request);
                    return mapping.getInputForward();
                }
                EnvPermitFacility
                    dupPA =
                    EnvPermitFacility.findByPermitAndFacilityId(pfForm.getPermitId(),
                                                                pfForm.getFacilityId());
                if (dupPA
                    != null)
                {
                    addMessage(new ActionMessage("permit.facility.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    UserValue
                        objUser =
                        getUserValue(request);
                    EnvPermitFacility
                        pFacility =
                        new EnvPermitFacility();
                    pFacility.setPermitId(pfForm.getPermitId());
                    pFacility.setFacilityId(pfForm.getFacilityId());
                    Integer
                        permitFacilityId =
                        pFacility.save(objUser);
                    LOG.debug(strLog
                              + "Permit Facility saved with FACILITY_ID = "
                              + permitFacilityId.toString());
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