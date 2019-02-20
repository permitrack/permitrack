package com.sehinc.environment.action.facility;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacilityEditAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityEditAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "FacilityEditPageAction. ";
        String
            strLog =
            strMod
            + "facilityAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("facility.edit.cancel.action"), request);
            return mapping.findForward("facility.list.page");
        }
        else
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
            Integer
                facilityId =
                facilityForm.getId();
            EnvFacility
                envFacility =
                new EnvFacility(facilityId);
            try
            {
                envFacility.load();
                FacilityService.saveFacilityInformation(facilityForm,
                                                        envFacility,
                                                        objUser,
                                                        objClient);
                setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                    facilityId,
                                    request);
                setSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                                    envFacility.getName(),
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {facilityId};
                LOG.error(ApplicationResources.getProperty("facility.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("facility.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("facility.list.page");
            }
        }
        addMessage(new ActionMessage("facility.edit.success"), request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(SecondaryMenu.FACILITY_VIEW_MENU),
                        request);
    }
}