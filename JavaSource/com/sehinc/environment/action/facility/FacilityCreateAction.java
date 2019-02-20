package com.sehinc.environment.action.facility;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityCreateAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityCreateAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward =
            CommonConstants.FORWARD_CONTINUE;
        String
            strMod =
            "FacilityCreateAction. ";
        String
            strLog =
            strMod
            + "facilityAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("facility.create.cancel.action"), request);
            return mapping.findForward("facility.list.page");
        }
        else
        {
            try
            {
                FacilityForm
                    facilityForm =
                    (FacilityForm) form;
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    objClient =
                    getClientValue(request);
                List
                    dupFacility =
                    EnvFacility.findByFacilityNameAndClientId(facilityForm.getName(),
                                                              objClient.getId());
                if (dupFacility.size()
                    != 0)
                {
                    addMessage(new ActionMessage("facility.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    Integer
                        intFacilityId =
                        FacilityService.saveFacilityInformation(facilityForm,
                                                                objUser,
                                                                objClient);
                    LOG.debug(strLog
                              + "New Facility created with ID = "
                              + intFacilityId.toString());
                    setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                        intFacilityId,
                                        request);
                }
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("facility.error.create.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("facility.error.create.failed"), request);
                return mapping.findForward("facility.list.page");
            }
        }
        addMessage(new ActionMessage("facility.create.success"), request);
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
    }
}
