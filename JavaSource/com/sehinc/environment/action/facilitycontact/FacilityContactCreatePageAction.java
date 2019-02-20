package com.sehinc.environment.action.facilitycontact;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.lookup.EnvFacilityContactTypeData;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityContactCreatePageAction
    extends FacilityContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityContactCreatePageAction.class);

    public ActionForward facilityContactAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "FacilityContactCreatePageAction. ";
        strLog =
            strMod
            + "facilityContactAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_CONTACT_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new facility contact.");
            addError(new ActionMessage("facility.contact.create.unauthorized"), request);
            return mapping.findForward("facility.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new facility contact page.");
        try
        {
            FacilityContactForm
                fcForm =
                (FacilityContactForm) form;
            fcForm.reset();
            Integer
                fId =
                (Integer) getFacility(request);
            EnvFacility
                facility =
                new EnvFacility(fId);
            facility.load();
            fcForm.setFacilityId(fId);
            fcForm.setFacilityName(facility.getName());
            ClientValue
                clientValue =
                getClientValue(request);
            List
                contacts =
                CapContact.findByClientContactListAndStatus(clientValue.getId(),
                                                            EnvStatusCodeData.STATUS_CODE_ACTIVE);
            setSessionAttribute(SessionKeys.EV_CONTACT_ACTIVE_LIST_BY_CLIENT,
                                contacts,
                                request);
            List
                roleTypes =
                EnvFacilityContactTypeData.findAllByClientId(clientValue.getId());
            request.setAttribute(SessionKeys.EV_FACILITY_ROLES_LIST,
                                 roleTypes);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("facility.contact.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("facility.contact.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.FACILITY_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.FACILITY_CONTACT_MENU_ITEM);
    }
}