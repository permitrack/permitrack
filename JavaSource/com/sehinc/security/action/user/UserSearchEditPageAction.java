package com.sehinc.security.action.user;

import com.sehinc.common.db.user.CapState;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.project.EcProjectType;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import com.sehinc.erosioncontrol.db.user.EcSearch;
import com.sehinc.erosioncontrol.db.user.EcUserDefaultSearch;
import com.sehinc.security.action.base.RequestKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserSearchEditPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchEditPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserSearchForm
            objEditSearchForm =
            (UserSearchForm) form;
        LOG.info("In userSearchEditPageAction");
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
        Integer
            searchId;
        if (request.getParameter(RequestKeys.SEARCH_ID)
            != null)
        {
            searchId =
                new Integer(request.getParameter(RequestKeys.SEARCH_ID));
            LOG.debug("searchId="
                      + searchId);
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("saved.searches.error.no.search.on.request"));
            addError(new ActionMessage("saved.searches.error.no.search.on.request"), request);
            return mapping.findForward("user.search.list.page");
        }
        EcSearch
            ecSearch =
            new EcSearch(searchId);
        try
        {
            ecSearch.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {searchId};
            LOG.error(ApplicationResources.getProperty("saved.searches.error.failed.to.load",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("saved.searches.error.failed.to.load",
                                       parameters), request);
            return mapping.findForward("user.search.list.page");
        }
        EcUserDefaultSearch
            defaultSearch =
            EcUserDefaultSearch.findByUserId(editUserId);
        Boolean
            isDefaultSearch =
            false;
        if (defaultSearch
            != null)
        {
            if (defaultSearch.getDefaultSearchId()
                .equals(searchId))
            {
                isDefaultSearch =
                    true;
            }
        }
        objEditSearchForm.setDefaultSearch(isDefaultSearch);
        objEditSearchForm.setId(searchId);
        objEditSearchForm.setSearchAddress(ecSearch.getAddress());
        objEditSearchForm.setSearchAreaSizeMax(ecSearch.getAreaMax());
        objEditSearchForm.setSearchAreaSizeMin(ecSearch.getAreaMin());
        objEditSearchForm.setSearchCity(ecSearch.getCity());
        objEditSearchForm.setSearchEffDateA(ecSearch.getEffDateA());
        objEditSearchForm.setSearchEffDateB(ecSearch.getEffDateB());
        objEditSearchForm.setSearchInspectorName(ecSearch.getInspectorName());
        objEditSearchForm.setSearchName(ecSearch.getName());
        objEditSearchForm.setSearchImpAreaSizeMax(ecSearch.getImpAreaMax());
        objEditSearchForm.setSearchImpAreaSizeMin(ecSearch.getImpAreaMin());
        objEditSearchForm.setSearchPermitAuthName(ecSearch.getPermitAuthName());
        objEditSearchForm.setSearchPermiteeName(ecSearch.getPermiteeName());
        objEditSearchForm.setSearchPermitNumber(ecSearch.getPermitNum());
        objEditSearchForm.setSearchProjectName(ecSearch.getProjectName());
        objEditSearchForm.setSearchSeedDateA(ecSearch.getSeedDateA());
        objEditSearchForm.setSearchSeedDateB(ecSearch.getSeedDateB());
        objEditSearchForm.setSearchStartDateA(ecSearch.getStartDateA());
        objEditSearchForm.setSearchStartDateB(ecSearch.getStartDateB());
        objEditSearchForm.setSearchState(ecSearch.getState());
        objEditSearchForm.setSearchTotalAreaSizeMax(ecSearch.getTotalAreaMax());
        objEditSearchForm.setSearchTotalAreaSizeMin(ecSearch.getTotalAreaMin());
        objEditSearchForm.setSearchZip(ecSearch.getZip());
        objEditSearchForm.setUserId(editUserId);
        objEditSearchForm.setSearchProjectStatusesString(ecSearch.getStatuses());
        objEditSearchForm.setSearchProjectTypesString(ecSearch.getTypes());
        objEditSearchForm.setSearchZonesString(ecSearch.getZones());
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