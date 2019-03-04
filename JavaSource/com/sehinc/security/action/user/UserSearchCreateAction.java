package com.sehinc.security.action.user;

import com.sehinc.erosioncontrol.db.user.EcSearch;
import com.sehinc.erosioncontrol.db.user.EcUserDefaultSearch;
import com.sehinc.erosioncontrol.db.user.EcUserSearch;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSearchCreateAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchCreateAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserSearchForm
            objNewSearchForm =
            (UserSearchForm) form;
        LOG.info("In userSearchCreateAction");
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
        EcSearch
            ecSearch =
            new EcSearch();
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
        if (objNewSearchForm.getSearchInspectionStatusTypes()
                != null) {
            StringBuffer
                    inspectionStatusStr =
                    new StringBuffer();
            String[]
                    inspectionStatuses =
                    objNewSearchForm.getSearchInspectionStatusTypes();
            for (
                    int
                            i =
                            0; i
                    < inspectionStatuses.length; i++) {
                String
                        inspectionStatus =
                        inspectionStatuses[i];
                inspectionStatusStr.append(inspectionStatus);
                if (i
                        != (inspectionStatuses.length
                        - 1)) {
                    inspectionStatusStr.append(",");
                }
            }
            ecSearch.setInspectionStatuses(inspectionStatusStr.toString());
        }
        Integer
            ecSearchId =
            ecSearch.insert(getUserValue(request));
        EcUserSearch
            ecUserSearch =
            new EcUserSearch();
        ecUserSearch.setUserId(editUserId);
        ecUserSearch.setSearchId(ecSearchId);
        ecUserSearch.insert(getUserValue(request));
        if (objNewSearchForm.getDefaultSearch())
        {
            EcUserDefaultSearch
                existingDefault =
                EcUserDefaultSearch.findByUserId(editUserId);
            if (existingDefault
                != null)
            {
                existingDefault.setDefaultSearchId(ecSearchId);
                existingDefault.update(getUserValue(request));
            }
            else
            {
                EcUserDefaultSearch
                    defaultSearch =
                    new EcUserDefaultSearch();
                defaultSearch.setUserId(editUserId);
                defaultSearch.setDefaultSearchId(ecSearchId);
                defaultSearch.insert(getUserValue(request));
            }
        }
        return mapping.findForward("continue");
    }
}
