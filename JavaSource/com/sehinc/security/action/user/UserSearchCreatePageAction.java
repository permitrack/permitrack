package com.sehinc.security.action.user;

import com.sehinc.common.db.user.CapState;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.project.EcProjectType;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserSearchCreatePageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchCreatePageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Integer
            mintClientId =
            getClientIdFromRequestOrSession(request);
        if (mintClientId
            == null
            || mintClientId.intValue()
               == 0)
        {
            LOG.error("Could not find Client ID on request or session");
            return mapping.findForward("user.search.list.page");
        }
        Integer
            editUserId =
            getUserId(request);
        if (editUserId
            == null
            || editUserId.intValue()
               == 0)
        {
            LOG.error("User not found on request or session");
            addError(new ActionMessage("error.user.not.found.in.session"), request);
            return mapping.findForward("user.search.list.page");
        }
        setProjectZonesOnRequest(request,
                                 mintClientId);
        setProjectTypesOnRequest(request,
                                 mintClientId);
        setProjectStatusesOnRequest(request);
        setStateListOnRequest(request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        setPrimaryMenuItem(request);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.USER_VIEW_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_USER_SEARCHES_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }

    private void setProjectZonesOnRequest(HttpServletRequest request, Integer clientId)
    {
        List
            lstZones =
            new ArrayList();
        lstZones.addAll(EcZone.findByClientId(clientId));
        request.setAttribute(com.sehinc.erosioncontrol.action.base.SessionKeys.EC_ZONE_LIST,
                             lstZones);
    }

    private void setProjectTypesOnRequest(HttpServletRequest request, Integer clientId)
    {
        List
            lstTypes =
            new ArrayList();
        lstTypes.addAll(EcProjectType.findByClientId(clientId));
        request.setAttribute(com.sehinc.erosioncontrol.action.base.SessionKeys.EC_PROJECT_TYPE_LIST,
                             lstTypes);
    }

    private void setProjectStatusesOnRequest(HttpServletRequest request)
    {
        List<CodeData>
            lstStatuses =
            SpringServiceLocator.getLookupService()
                .fetchCodes(ProjectStatusCodeData.class);
        request.setAttribute(com.sehinc.erosioncontrol.action.base.SessionKeys.PROJECT_STATUS_CODE_LIST,
                             lstStatuses);
    }

    private void setStateListOnRequest(HttpServletRequest request)
    {
        List
            lstC =
            CapState.findNonArmedForcesStates();
        request.setAttribute(com.sehinc.erosioncontrol.action.base.SessionKeys.EC_STATE_LIST,
                             lstC);
    }
}