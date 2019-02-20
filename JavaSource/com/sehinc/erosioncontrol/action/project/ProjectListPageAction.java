package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.ECConstants;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.command.EcProjectSearchCommand;
import com.sehinc.erosioncontrol.command.EcProjectSearchCommandContext;
import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;
import com.sehinc.erosioncontrol.db.project.EcProjectType;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import com.sehinc.erosioncontrol.db.user.EcSearch;
import com.sehinc.erosioncontrol.db.user.EcUserDefaultSearch;
import com.sehinc.erosioncontrol.db.user.EcUserSearch;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.client.EcClientService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProjectListPageAction
    extends ProjectBaseAction
{
    private static final
    Logger
        LOG =
        Logger.getLogger(ProjectListPageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception
    {
        ProjectSearchListForm
            projectSearchListForm =
            (ProjectSearchListForm) form;
        List<EcProjectSearchData>
            projectList =
            getProjectList(request,
                           userValue,
                           clientValue,
                           projectSearchListForm);
        clearResetKey(request);
        setDisplayColumns(request,
                          userValue);
        setMapLink(request,
                   clientValue,
                   0);
        setStateListOnRequest(request);
        setProjectStatusesOnRequest(request);
        List<Integer>
            clientIds =
            getClientIds(clientValue);
        setProjectTypesOnRequest(request,
                                 clientIds);
        setProjectZonesOnRequest(request,
                                 clientIds);
        setUserSearchesOnRequest(request,
                                 userValue);
        clearCurrentProject(request);
        return mapping.findForward(processList(projectList,
                                               projectSearchListForm,
                                               request));
    }

    private boolean isSavedSearch(ProjectSearchListForm projectForm)
    {
        return projectForm.getBtnSubmit()
               != null
               && projectForm.getBtnSubmit()
            .equalsIgnoreCase(ApplicationResources.getProperty("project.search.saved.filter")); //  "Apply Saved Filter"
    }

    private List<Integer> getClientIds(ClientValue clientValue)
    {
        List<Integer>
            clients =
            EcClientService.findPrimaryClients(clientValue);
        clients.add(clientValue.getId());
        return clients;
    }

    private void setProjectZonesOnRequest(HttpServletRequest request, List<Integer> clientIds)
    {
        List
            lstZones =
            new ArrayList();
        for (Integer id : clientIds)
        {
            lstZones.addAll(EcZone.findByClientId(id));
        }
        request.setAttribute(SessionKeys.EC_ZONE_LIST,
                             lstZones);
    }

    private void setUserSearchesOnRequest(HttpServletRequest request, UserValue userValue)
    {
        List
            userSearchList =
            EcUserSearch.findByUserId(userValue.getId());
        request.setAttribute(SessionKeys.USER_SEARCH_LIST,
                             userSearchList);
    }

    private void setProjectTypesOnRequest(HttpServletRequest request, List<Integer> clientIds)
    {
        List
            lstTypes =
            new ArrayList();
        for (Integer id : clientIds)
        {
            lstTypes.addAll(EcProjectType.findByClientId(id));
        }
        request.setAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                             lstTypes);
    }

    private void setProjectStatusesOnRequest(HttpServletRequest request)
    {
        List<CodeData>
            lstStatuses =
            SpringServiceLocator.getLookupService()
                .fetchCodes(ProjectStatusCodeData.class);
        request.setAttribute(SessionKeys.PROJECT_STATUS_CODE_LIST,
                             lstStatuses);
    }

    private void setStateListOnRequest(HttpServletRequest request)
    {
        List
            lstC =
            CapState.findNonArmedForcesStates();
        request.setAttribute(SessionKeys.EC_STATE_LIST,
                             lstC);
    }

    private void clearCurrentProject(HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT,
                          null);
    }

    private void setDisplayColumns(HttpServletRequest request, UserValue userValue)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_USER_PROJECT_LIST_ITEMS,
                          ProjectService.getUserProjectListItems(userValue));
    }

    private String processList(List<EcProjectSearchData> projectList, ProjectSearchListForm form, HttpServletRequest request)
    {
        putAuthorizedProjectListInSession(projectList,
                                          request);
        putDisplayListInSession(request,
                                createProjectDisplayList(projectList,
                                                         form,
                                                         request));
        return CommonConstants.FORWARD_CONTINUE;
    }

    private void putDisplayListInSession(HttpServletRequest request, List<EcProjectSearchData> subList)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST,
                          subList);
    }

    private void putAuthorizedProjectListInSession(List<EcProjectSearchData> projectList, HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_AUTHORIZED_PROJECT_LIST,
                          projectList);
    }

    private List<EcProjectSearchData> createProjectDisplayList(List<EcProjectSearchData> projectList, ProjectSearchListForm form, HttpServletRequest request)
    {
        Integer
            currentPage =
            determineCurrentPage(form,
                                 request);
        setCurrentPage(request,
                       currentPage);
        setPageControlList(request,
                           currentPage,
                           form);
        return projectList;
    }

    private void setPageControlList(HttpServletRequest request, Integer currentPage, ProjectSearchListForm form)
    {
        int
            totalPages =
            calculateTotalPages(form);
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL,
                          getPageControlList(currentPage,
                                             totalPages));
    }

    private void setCurrentPage(HttpServletRequest request, Integer currentPage)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_LIST_CURRENT_PAGE,
                          currentPage);
    }

    private Integer determineCurrentPage(ProjectSearchListForm form, HttpServletRequest request)
    {
        //Declare the current page and projects per page variables
        //These will be stored in the session
        boolean
            projectsPerPageChanged =
            isProjectsPerPageChanged(form,
                                     request);
        Integer
            currentPage =
            getCurrentPage(request,
                           form);
        if (currentPage
            == null
            || currentPage
               <= 0
            || isSortChanged(request)
            || projectsPerPageChanged)
        {
            currentPage =
                1;
        }
        return currentPage;
    }

    private Integer getCurrentPage(HttpServletRequest request, ProjectSearchListForm form)
    {
        Integer
            currentPage;
        //Try to get the current page from the request
        if (request.getParameter(RequestKeys.EC_PROJECT_LIST_CURRENT_PAGE)
            != null)
        {
            currentPage =
                decodeCurrentPageAttribute(calculateTotalPages(form),
                                           request);
        }
        else
        {
            //get current page from the session
            currentPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EC_PROJECT_LIST_CURRENT_PAGE);
        }
        return currentPage;
    }

    private boolean isProjectsPerPageChanged(ProjectSearchListForm form, HttpServletRequest request)
    {
        return !(form.getProjectsPerPage()
                 == null
                 || form.getProjectsPerPage()
            .equals(request.getSession()
                        .getAttribute(SessionKeys.EC_PROJECT_LIST_PROJECTS_PER_PAGE)));
    }

    private int calculateTotalPages(ProjectSearchListForm form)
    {
        return form.getTotalPages();
    }

    private void clearResetKey(HttpServletRequest request)
    {
        request.getSession()
            .removeAttribute(SessionKeys.EC_RESET_PROJECT_LIST);
    }

    private List<EcProjectSearchData> getProjectList(HttpServletRequest request, UserValue userValue, ClientValue clientValue, ProjectSearchListForm form)
        throws Exception
    {
        List<EcProjectSearchData>
            projectList;
        EcProjectSearchCommandContext
            context =
            createSearchContext(form,
                                clientValue,
                                userValue,
                                request);
        EcProjectSearchCommand
            command =
            new EcProjectSearchCommand();
        command.execute(context);
        projectList =
            context.getResults();
        form.setTotalPages(context.getTotalPages());
        int
            totalPages =
            form.getTotalPages();
        Object[]
            parameters =
            {
                totalPages
                > 1
                    ? "~"
                      + (totalPages
                         * context.getProjectsPerPage())
                    : projectList.size()};
        addMessage(new ActionMessage("project.search.list.returned",
                                     parameters),
                   request);
        return projectList;
    }

    private String getOrderColumns(ProjectSearchListForm form, UserValue user)
    {
        StringBuilder
            builder =
            new StringBuilder();
        if (StringUtil.isEmpty(form.getSortColumn()))
        {
            if (ProjectService.getUserProjectListItems(user)
                .contains(ProjectListItem.LAST_INSPECTION_DATE))
            {
                builder.append(EcProjectSearchData.LAST_INSPECTION_DATE)
                    .append(" ")
                    .append(EcProjectSearchData.DESCENDING);
            }
            else
            {
                builder.append(EcProjectSearchData.PROJECT_NAME)
                    .append(" ")
                    .append(EcProjectSearchData.ASCENDING);
            }
        }
        else
        {
            builder.append(form.getSortColumn());
            if (!StringUtil.isEmpty(form.getSortDirection()))
            {
                builder.append(" ")
                    .append(form.getSortDirection());
            }
        }
        if (!builder.toString()
            .contains(EcProjectSearchData.PROJECT_NAME))
        {
            builder.append(", ")
                .append(EcProjectSearchData.PROJECT_NAME)
                .append(" ")
                .append(EcProjectSearchData.ASCENDING);
        }
        return builder.toString();
    }

    private EcProjectSearchCommandContext createSearchContext(ProjectSearchListForm form, ClientValue client, UserValue user, HttpServletRequest request)
        throws Exception
    {
        EcProjectSearchCommandContext
            context =
            new EcProjectSearchCommandContext();
        if (isSavedSearch(form))
        { // add elements to context from the saved search
            // get saved search id and load search
            context =
                createContextFromSearch(context,
                                        form,
                                        form.getSavedSearchId(),
                                        request);
        }
        else
        {
            if (StringUtil.isEmpty(form.getSearchProjectName())
                && StringUtil.isEmpty(form.getSearchAddress())
                && StringUtil.isEmpty(form.getSearchCity())
                && StringUtil.isEmpty(form.getSearchState())
                && StringUtil.isEmpty(form.getSearchZip())
                && form.getSearchProjectStatuses()
                   == null
                && form.getSearchProjectTypes()
                   == null
                && form.getSearchZones()
                   == null
                && StringUtil.isEmpty(form.getSearchPermitNumber())
                && StringUtil.isEmpty(form.getSearchAreaSizeMin())
                && StringUtil.isEmpty(form.getSearchAreaSizeMax())
                && StringUtil.isEmpty(form.getSearchStartDateA())
                && StringUtil.isEmpty(form.getSearchStartDateB())
                && StringUtil.isEmpty(form.getSearchEffDateA())
                && StringUtil.isEmpty(form.getSearchEffDateB())
                && StringUtil.isEmpty(form.getSearchPermitAuthName())
                && StringUtil.isEmpty(form.getSearchPermiteeName())
                && StringUtil.isEmpty(form.getSearchInspectorName())
                && StringUtil.isEmpty(form.getSearchTotalAreaSizeMin())
                && StringUtil.isEmpty(form.getSearchTotalAreaSizeMax())
                && StringUtil.isEmpty(form.getSearchImpAreaSizeMin())
                && StringUtil.isEmpty(form.getSearchImpAreaSizeMax())
                && StringUtil.isEmpty(form.getSearchSeedDateA())
                && StringUtil.isEmpty(form.getSearchSeedDateB())
                && (form.getSavedSearchId()
                    == null
                    || form.getSavedSearchId()
                       < 1))
            {
                // The user has entered no search criteria. Perform their default search.
                EcUserDefaultSearch
                    defaultSearch =
                    EcUserDefaultSearch.findByUserId(user.getId());
                if (defaultSearch
                    != null)
                {
                    context =
                        createContextFromSearch(context,
                                                form,
                                                defaultSearch.getDefaultSearchId(),
                                                request);
                }
                else
                {
                    // No default search. Return ACTIVE projects.
                    addMessage(new ActionMessage("project.search.list.active"),
                               request);
                    context.setProjectStatuses(StatusCodeData.STATUS_CODE_ACTIVE);
                }
            }
            else
            {
                context =
                    createContextFromForm(context,
                                          form,
                                          request);
            }
        }
        context.setClient(client);
        context.setUser(user);
        context.setOrderColumns(getOrderColumns(form,
                                                user));
        context.setCurrentPage(determineCurrentPage(form,
                                                    request));
        context.setProjectsPerPage(determineProjectsPerPage(request,
                                                            form));
        SecurityManager
            securityManager =
            getSecurityManager(request);
        context.setSecurityManager(securityManager);
        return context;
    }

    private EcProjectSearchCommandContext createContextFromSearch(EcProjectSearchCommandContext context, ProjectSearchListForm form, Integer searchId, HttpServletRequest request)
    {
        // get saved search id and load search
        EcSearch
            search =
            new EcSearch(searchId);
        try
        {
            search.load();
            // each criteria from EcSearch object loads into context
            context.setProjectName(search.getProjectName());
            context.setAddress(search.getAddress());
            context.setCity(search.getCity());
            context.setState(search.getState());
            context.setZip(search.getZip());
            context.setProjectStatuses(convertToStringList(search.getStatuses()));
            context.setProjectTypes(convertToIntList(search.getTypes()));
            context.setZones(convertToIntList(search.getZones()));
            context.setPermitNumber(search.getPermitNum());
            context.setAreaSizeMin(search.getAreaMin());
            context.setAreaSizeMax(search.getAreaMax());
            context.setTotalAreaSizeMin(search.getTotalAreaMin());
            context.setTotalAreaSizeMax(search.getTotalAreaMax());
            context.setImpAreaSizeMin(search.getImpAreaMin());
            context.setImpAreaSizeMax(search.getImpAreaMax());
            context.setStartDateA(search.getStartDateA());
            context.setStartDateB(search.getStartDateB());
            context.setEffDateA(search.getEffDateA());
            context.setEffDateB(search.getEffDateB());
            context.setSeedDateA(search.getSeedDateA());
            context.setSeedDateB(search.getSeedDateB());
            context.setPermitAuthName(search.getPermitAuthName());
            context.setPermiteeName(search.getPermiteeName());
            context.setInspectorName(search.getInspectorName());
            form.setSearchProjectName(search.getProjectName());
            form.setSearchAddress(search.getAddress());
            form.setSearchCity(search.getCity());
            form.setSearchState(search.getState());
            form.setSearchZip(search.getZip());
            form.setSearchProjectStatuses(convertToStringList(search.getStatuses()));
            form.setSearchProjectTypes(convertToIntList(search.getTypes()));
            form.setSearchZones(convertToIntList(search.getZones()));
            form.setSearchPermitNumber(search.getPermitNum());
            form.setSearchAreaSizeMin(search.getAreaMin());
            form.setSearchAreaSizeMax(search.getAreaMax());
            form.setSearchTotalAreaSizeMin(search.getTotalAreaMin());
            form.setSearchTotalAreaSizeMax(search.getTotalAreaMax());
            form.setSearchImpAreaSizeMin(search.getImpAreaMin());
            form.setSearchImpAreaSizeMax(search.getImpAreaMax());
            form.setSearchStartDateA(search.getStartDateA());
            form.setSearchStartDateB(search.getStartDateB());
            form.setSearchEffDateA(search.getEffDateA());
            form.setSearchEffDateB(search.getEffDateB());
            form.setSearchSeedDateA(search.getSeedDateA());
            form.setSearchSeedDateB(search.getSeedDateB());
            form.setSearchPermitAuthName(search.getPermitAuthName());
            form.setSearchPermiteeName(search.getPermiteeName());
            form.setSearchInspectorName(search.getInspectorName());
            form.setSavedSearchId(searchId);
        }
        catch (Exception e)
        {
            LOG.error("Failed to load the Saved Search for id: "
                      + searchId);
            LOG.error(e.getMessage());
        }
        String
            searchName =
            search.getName();
        if (searchName
            == null)
        {
            searchName =
                "";
        }
        Object[]
            parameters =
            {searchName};
        addMessage(new ActionMessage("project.search.list.user.search",
                                     parameters),
                   request);
        return context;
    }

    private EcProjectSearchCommandContext createContextFromForm(EcProjectSearchCommandContext context, ProjectSearchListForm form, HttpServletRequest request)
    {
        context.setProjectName(form.getSearchProjectName());
        context.setAddress(form.getSearchAddress());
        context.setCity(form.getSearchCity());
        context.setState(form.getSearchState());
        context.setZip(form.getSearchZip());
        context.setProjectStatuses(form.getSearchProjectStatuses());
        context.setProjectTypes(form.getSearchProjectTypes());
        context.setZones(form.getSearchZones());
        context.setPermitNumber(form.getSearchPermitNumber());
        context.setAreaSizeMin(form.getSearchAreaSizeMin());
        context.setAreaSizeMax(form.getSearchAreaSizeMax());
        context.setTotalAreaSizeMin(form.getSearchTotalAreaSizeMin());
        context.setTotalAreaSizeMax(form.getSearchTotalAreaSizeMax());
        context.setImpAreaSizeMin(form.getSearchImpAreaSizeMin());
        context.setImpAreaSizeMax(form.getSearchImpAreaSizeMax());
        context.setStartDateA(form.getSearchStartDateA());
        context.setStartDateB(form.getSearchStartDateB());
        context.setEffDateA(form.getSearchEffDateA());
        context.setEffDateB(form.getSearchEffDateB());
        context.setSeedDateA(form.getSearchSeedDateA());
        context.setSeedDateB(form.getSearchSeedDateB());
        context.setPermitAuthName(form.getSearchPermitAuthName());
        context.setPermiteeName(form.getSearchPermiteeName());
        context.setInspectorName(form.getSearchInspectorName());
        addMessage(new ActionMessage("project.search.list.adhoc"),
                   request);
        return context;
    }

    private String[] convertToStringList(String statuses)
    {
        String[]
            stringArr =
            null;
        if (statuses
            != null)
        {
            StringTokenizer
                tokenizer =
                new StringTokenizer(statuses,
                                    " ,",
                                    false);
            ArrayList<String>
                list =
                new ArrayList<String>();
            while (tokenizer.hasMoreTokens())
            {
                list.add(tokenizer.nextToken());
            }
            stringArr =
                list.toArray(new String[list.size()]);
        }
        return stringArr;
    }

    private Integer[] convertToIntList(String statuses)
    {
        Integer[]
            intArr =
            null;
        if (statuses
            != null)
        {
            StringTokenizer
                tokenizer =
                new StringTokenizer(statuses,
                                    " ,",
                                    false);
            ArrayList<Integer>
                list =
                new ArrayList<Integer>();
            while (tokenizer.hasMoreTokens())
            {
                list.add(new Integer(tokenizer.nextToken()));
            }
            intArr =
                list.toArray(new Integer[list.size()]);
        }
        return intArr;
    }

    private Integer determineProjectsPerPage(HttpServletRequest request, ProjectSearchListForm form)
    {
        Integer
            projectsPerPage;
        if (form.getProjectsPerPage()
            != null
            && form.getProjectsPerPage()
               > 0)
        {
            projectsPerPage =
                form.getProjectsPerPage();
        }
        else
        {
            //get the last sort method from the session
            projectsPerPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EC_PROJECT_LIST_PROJECTS_PER_PAGE);
        }
        //If the number of projects per page is null or not greater than zero, then set it to the default
        if (projectsPerPage
            == null
            || projectsPerPage
               <= 0)
        {
            projectsPerPage =
                ECConstants.PROJECT_LIST_DEFAULT_PROJECTS_PER_PAGE;
        }
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_LIST_PROJECTS_PER_PAGE,
                          projectsPerPage);
        return projectsPerPage;
    }

    private List<LabelValueBean> getPageControlList(Integer currentPage, Integer totalPages)
    {
        if (totalPages
            <= 1)
        {
            return null;
        }
        ArrayList<LabelValueBean>
            pageControl =
            new ArrayList<LabelValueBean>();
        ArrayList<LabelValueBean>
            pageVcrControl =
            new ArrayList<LabelValueBean>();
        pageVcrControl.add(new LabelValueBean("<<",
                                              "F"));
        pageVcrControl.add(new LabelValueBean("<",
                                              "P"));
        for (
            int
                i =
                1; i
                   <= totalPages; i++)
        {
            if (i
                == currentPage)
            {
                pageControl.add(new LabelValueBean(""
                                                   + i,
                                                   "true"));
            }
            else
            {
                pageControl.add(new LabelValueBean(""
                                                   + i,
                                                   ""
                                                   + i));
            }
        }
        pageVcrControl.add(new LabelValueBean(">",
                                              "N"));
        pageVcrControl.add(new LabelValueBean(">>",
                                              "L"));
        pageVcrControl.addAll(pageControl);
        return pageVcrControl;
    }

    private Integer decodeCurrentPageAttribute(int totalPages, HttpServletRequest request)
    {
        //Get the current page attribute passed on the request
        String
            currentPageAttribute =
            request.getParameter(RequestKeys.EC_PROJECT_LIST_CURRENT_PAGE);
        //Get the current page in the session
        Integer
            currentPage =
            (Integer) request.getSession()
                .getAttribute(SessionKeys.EC_PROJECT_LIST_CURRENT_PAGE);
        //If the current page from the session is null, default to page 1
        if (currentPage
            == null)
        {
            currentPage =
                1;
        }
        //F: First Page
        //P: Previous Page
        //N: Next Page
        //L: Last Page
        if (currentPageAttribute.equalsIgnoreCase("F"))
        {
            return 1;
        }
        else
        {
            if (currentPageAttribute.equalsIgnoreCase("P"))
            {
                if (currentPage
                    > 1)
                {
                    return currentPage
                           - 1;
                }
                else
                {
                    return 1;
                }
            }
            else
            {
                if (currentPageAttribute.equalsIgnoreCase("N"))
                {
                    if (currentPage
                        < totalPages)
                    {
                        return currentPage
                               + 1;
                    }
                    else
                    {
                        return totalPages;
                    }
                }
                else
                {
                    if (currentPageAttribute.equalsIgnoreCase("L"))
                    {
                        return totalPages;
                    }
                    else
                    {
                        try
                        {
                            Integer
                                thePage =
                                Integer.decode(currentPageAttribute);
                            if (thePage
                                > 0
                                && thePage
                                   <= totalPages)
                            {
                                return thePage;
                            }
                        }
                        catch (NumberFormatException nfe)
                        {
                            LOG.error(nfe
                                      + " : Invalid page attribute: "
                                      + currentPageAttribute);
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean isSortChanged(HttpServletRequest request)
    {
        return request.getParameter(RequestKeys.EC_PROJECT_LIST_SORT)
               != null;
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROJECT_LIST_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_LIST_MENU_NAME),
                         request);
    }
}
