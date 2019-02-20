package com.sehinc.security.action.user;

import com.sehinc.erosioncontrol.db.user.EcSearch;
import com.sehinc.erosioncontrol.db.user.EcUserDefaultSearch;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSearchEditAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchEditAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserSearchForm
            objNewSearchForm =
            (UserSearchForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            objNewSearchForm.reset();
            return mapping.findForward("user.search.list.page");
        }
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
        Integer
            mintSearchId =
            objNewSearchForm.getId();
        LOG.debug("searchId="
                  + mintSearchId);
        if (mintSearchId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("saved.searches.error.no.search.on.request"));
            addError(new ActionMessage("saved.searches.error.no.search.on.request"), request);
            return mapping.findForward("user.search.list.page");
        }
        EcSearch
            ecSearch =
            new EcSearch();
        if (mintSearchId
            != null)
        {
            ecSearch.setId(mintSearchId);
            try
            {
                ecSearch.load();
            }
            catch (Exception e)
            {
                LOG.error("Failed to load Search ID "
                          + mintSearchId);
                addError(new ActionMessage("saved.searches.error.failed.to.load.edit",
                                           mintSearchId), request);
                return mapping.findForward("user.search.list.page");
            }
        }
        ecSearch.setName(objNewSearchForm.getSearchName());
        ecSearch.setAddress(objNewSearchForm.getSearchAddress());
        ecSearch.setAreaMax(objNewSearchForm.getSearchAreaSizeMax());
        ecSearch.setAreaMin(objNewSearchForm.getSearchAreaSizeMin());
        ecSearch.setCity(objNewSearchForm.getSearchCity());
        ecSearch.setEffDateA(objNewSearchForm.getSearchEffDateA());
        ecSearch.setEffDateB(objNewSearchForm.getSearchEffDateB());
        ecSearch.setImpAreaMax(objNewSearchForm.getSearchImpAreaSizeMax());
        ecSearch.setImpAreaMin(objNewSearchForm.getSearchImpAreaSizeMin());
        ecSearch.setInspectorName(objNewSearchForm.getSearchInspectorName());
        ecSearch.setPermitAuthName(objNewSearchForm.getSearchPermitAuthName());
        ecSearch.setPermiteeName(objNewSearchForm.getSearchPermiteeName());
        ecSearch.setPermitNum(objNewSearchForm.getSearchPermitNumber());
        ecSearch.setProjectName(objNewSearchForm.getSearchProjectName());
        ecSearch.setSeedDateA(objNewSearchForm.getSearchSeedDateA());
        ecSearch.setSeedDateB(objNewSearchForm.getSearchSeedDateB());
        ecSearch.setStartDateA(objNewSearchForm.getSearchStartDateA());
        ecSearch.setStartDateB(objNewSearchForm.getSearchStartDateB());
        ecSearch.setState(objNewSearchForm.getSearchState());
        ecSearch.setTotalAreaMax(objNewSearchForm.getSearchTotalAreaSizeMax());
        ecSearch.setTotalAreaMin(objNewSearchForm.getSearchTotalAreaSizeMin());
        ecSearch.setZip(objNewSearchForm.getSearchZip());
        if (objNewSearchForm.getSearchProjectTypes()
            != null)
        {
            StringBuffer
                typesStr =
                new StringBuffer();
            Integer[]
                types =
                objNewSearchForm.getSearchProjectTypes();
            for (
                int
                    i =
                    0; i
                       < types.length; i++)
            {
                Integer
                    type =
                    types[i];
                typesStr.append(type.toString());
                if (i
                    != (types.length
                        - 1))
                {
                    typesStr.append(",");
                }
            }
            ecSearch.setTypes(typesStr.toString());
        }
        if (objNewSearchForm.getSearchProjectStatuses()
            != null)
        {
            StringBuffer
                statusStr =
                new StringBuffer();
            String[]
                statuses =
                objNewSearchForm.getSearchProjectStatuses();
            for (
                int
                    i =
                    0; i
                       < statuses.length; i++)
            {
                String
                    status =
                    statuses[i];
                statusStr.append(status.toString());
                if (i
                    != (statuses.length
                        - 1))
                {
                    statusStr.append(",");
                }
            }
            ecSearch.setStatuses(statusStr.toString());
        }
        if (objNewSearchForm.getSearchZones()
            != null)
        {
            StringBuffer
                zonesStr =
                new StringBuffer();
            Integer[]
                zones =
                objNewSearchForm.getSearchZones();
            for (
                int
                    i =
                    0; i
                       < zones.length; i++)
            {
                Integer
                    zone =
                    zones[i];
                zonesStr.append(zone.toString());
                if (i
                    != (zones.length
                        - 1))
                {
                    zonesStr.append(",");
                }
            }
            ecSearch.setZones(zonesStr.toString());
        }
        ecSearch.update(getUserValue(request));
        if (objNewSearchForm.getDefaultSearch())
        {
            EcUserDefaultSearch
                existingDefault =
                EcUserDefaultSearch.findByUserId(editUserId);
            if (existingDefault
                != null)
            {
                existingDefault.setDefaultSearchId(mintSearchId);
                existingDefault.update(getUserValue(request));
            }
            else
            {
                EcUserDefaultSearch
                    defaultSearch =
                    new EcUserDefaultSearch();
                defaultSearch.setUserId(editUserId);
                defaultSearch.setDefaultSearchId(mintSearchId);
                defaultSearch.insert(getUserValue(request));
            }
        }
        else
        {
            EcUserDefaultSearch
                existingDefault =
                EcUserDefaultSearch.findByUserId(editUserId);
            if (existingDefault
                != null)
            {
                if (existingDefault.getDefaultSearchId()
                    .equals(mintSearchId))
                {
                    existingDefault.delete();
                }
            }
        }
        return mapping.findForward("continue");
    }
}