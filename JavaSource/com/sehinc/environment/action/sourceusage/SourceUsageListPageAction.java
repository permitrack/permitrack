package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class SourceUsageListPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageListPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View source usage",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.SOURCE_USAGE_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.SOURCE_USAGE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_DELETE),
                            request);
        List
            sourceUsages;
        try
        {
            String
                sortMethod;
            boolean
                isChanged =
                false;
            if (request.getParameter(RequestKeys.SOURCE_USAGE_LIST_SORT)
                != null)
            {
                sortMethod =
                    request.getParameter(RequestKeys.SOURCE_USAGE_LIST_SORT);
                isChanged =
                    true;
                LOG.info("SourceUsageListPageAction.ActionForward() - request: "
                         + RequestKeys.SOURCE_USAGE_LIST_SORT
                         + " = "
                         + sortMethod);
            }
            else
            {
                sortMethod =
                    (String) getSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_METHOD,
                                                 request);
                LOG.info("SourceUsageListPageAction.ActionForward() - session: "
                         + SessionKeys.SOURCE_USAGE_SORT_METHOD
                         + " = "
                         + sortMethod);
            }
            Integer
                assetId =
                getParamInt("id",
                            request);
            if (assetId
                < 1)
            {
                assetId =
                    (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                                  request);
            }
            if (assetId
                != null
                && assetId
                   > 0)
            {
                setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                    assetId,
                                    request);
                try
                {
                    EnvAsset
                        selectedAsset =
                        new EnvAsset(assetId);
                    selectedAsset.load();
                    setSessionAttribute(SessionKeys.SELECTED_ASSET_NAME,
                                        selectedAsset.getNumberAndName(),
                                        request);
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {e.getMessage()};
                    addError(new ActionMessage("source.usage.load.failed",
                                               parameters), request);
                    LOG.error(ApplicationResources.getProperty("source.usage.load.failed",
                                                               parameters));
                }
            }
            else
            {
                LOG.error("Cannot retrieve asset id parameter 'id' from request.");
            }
            SourceUsageForm
                surForm =
                (SourceUsageForm) form;
            Date
                startDate =
                DateUtil.getOneYearAgo();
            Date
                endDate =
                new Date();
            if (surForm.getPeriodStartTsString()
                != null
                && !surForm.getPeriodStartTsString()
                .isEmpty())
            {
                try
                {
                    startDate =
                        DateUtil.parseDate(surForm.getPeriodStartTsString());
                }
                catch (Exception e)
                {
                    surForm.setPeriodStartTs(startDate);
                }
            }
            else
            {
                surForm.setPeriodStartTs(startDate);
            }
            if (surForm.getPeriodEndTsString()
                != null
                && !surForm.getPeriodEndTsString()
                .isEmpty())
            {
                try
                {
                    endDate =
                        DateUtil.parseDate(surForm.getPeriodEndTsString());
                }
                catch (Exception e)
                {
                    surForm.setPeriodEndTs(endDate);
                }
            }
            else
            {
                surForm.setPeriodEndTs(endDate);
            }
            if (request.getAttribute("type")
                == null
                || request.getAttribute("type")
                .equals("source"))
            {
                sourceUsages =
                    SourceUsageService.getSourceUsageList(getSortQuery(sortMethod,
                                                                       isChanged,
                                                                       request),
                                                          assetId,
                                                          startDate,
                                                          endDate);
                setSessionAttribute(SessionKeys.SOURCE_USAGE_FIELD_LIST,
                                    SourceUsageListItem.getSortedList(),
                                    request);
            }
            else
            {
                sourceUsages =
                    ControlUsageService.getControlUsageList(getSortQuery(sortMethod,
                                                                         isChanged,
                                                                         request),
                                                            assetId,
                                                            startDate,
                                                            endDate);
                setSessionAttribute(SessionKeys.SOURCE_USAGE_FIELD_LIST,
                                    ControlUsageListItem.getSortedList(),
                                    request);
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("source.usage.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("source.usage.load.failed",
                                                       parameters));
            return mapping.findForward("environment");
        }
        request.getSession()
            .setAttribute(SessionKeys.SOURCE_USAGE_READINGS_LIST,
                          createSourceUsageDisplayList(sourceUsages,
                                                       (SourceUsageForm) form,
                                                       request));
        return mapping.findForward("continue");
    }

    private List createSourceUsageDisplayList(List sourceUsages, SourceUsageForm form, HttpServletRequest request)
    {
        Integer
            currentPage =
            determineCurrentPage(sourceUsages,
                                 form,
                                 request);
        Integer
            sourceUsagesPerPage =
            determineSourceUsagesPerPage(request,
                                         form);
        int
            startIndex =
            (currentPage
             - 1)
            * (sourceUsagesPerPage);
        int
            endIndex =
            (currentPage)
            * (sourceUsagesPerPage);
        if (endIndex
            > sourceUsages.size())
        {
            endIndex =
                sourceUsages.size();
        }
        if (indexesOutOfSynch(startIndex,
                              endIndex,
                              sourceUsages.size()))
        {
            currentPage =
                1;
            startIndex =
                0;
            endIndex =
                resetEndIndex(sourceUsages,
                              sourceUsagesPerPage);
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE,
                          currentPage);
        setPageControlList(sourceUsages,
                           sourceUsagesPerPage,
                           request,
                           currentPage);
        return sourceUsages.subList(startIndex,
                                    endIndex);
    }

    private void setPageControlList(List sourceUsages, Integer sourceUsagesPerPage, HttpServletRequest request, Integer currentPage)
    {
        request.getSession()
            .setAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_PAGE_CONTROL,
                          getSourceUsageControlList(currentPage,
                                                    calculateTotalPages(sourceUsages,
                                                                        sourceUsagesPerPage)));
    }

    private List<LabelValueBean> getSourceUsageControlList(Integer currentPage, Integer totalPages)
    {
        if (totalPages
            <= 1)
        {
            return null;
        }
        ArrayList<LabelValueBean>
            pageControl =
            new ArrayList<LabelValueBean>();
        pageControl.add(new LabelValueBean("<<",
                                           "F"));
        pageControl.add(new LabelValueBean("<",
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
        pageControl.add(new LabelValueBean(">",
                                           "N"));
        pageControl.add(new LabelValueBean(">>",
                                           "L"));
        return pageControl;
    }

    private boolean indexesOutOfSynch(int startIndex, int endIndex, int i)
    {
        if ((startIndex
             < 0
             || (endIndex
                 > i))
            || startIndex
               > endIndex)
        {
            LOG.debug("startIndex="
                      + startIndex
                      + " endIndex="
                      + endIndex
                      + " sourceUsageList.size()="
                      + i);
            return true;
        }
        return false;
    }

    private int resetEndIndex(List sourceUsages, Integer sourceUsagesPerPage)
    {
        int
            endIndex;
        if (sourceUsages.size()
            < sourceUsagesPerPage)
        {
            endIndex =
                sourceUsages.size();
        }
        else
        {
            endIndex =
                sourceUsagesPerPage;
        }
        return endIndex;
    }

    private Integer determineCurrentPage(List sourceUsages, SourceUsageForm form, HttpServletRequest request)
    {
        boolean
            sourceUsagesPerPageChanged =
            isSourceUsagesPerPageChanged(form,
                                         request);
        Integer
            sourceUsagesPerPage =
            determineSourceUsagesPerPage(request,
                                         form);
        Integer
            currentPage =
            getCurrentPage(sourceUsages,
                           request,
                           sourceUsagesPerPage);
        if (currentPage
            == null
            || currentPage
               <= 0
            || sourceUsagesPerPageChanged
            || isSortChanged(request))
        {
            currentPage =
                1;
        }
        return currentPage;
    }

    private boolean isSortChanged(HttpServletRequest request)
    {
        return request.getParameter(RequestKeys.SOURCE_USAGE_LIST_SORT)
               != null;
    }

    private Integer getCurrentPage(List sourceUsages, HttpServletRequest request, Integer sourceUsagesPerPage)
    {
        Integer
            currentPage;
        if (request.getParameter(RequestKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE)
            != null)
        {
            currentPage =
                decodeCurrentPageAttribute(calculateTotalPages(sourceUsages,
                                                               sourceUsagesPerPage),
                                           request);
        }
        else
        {
            currentPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE);
        }
        return currentPage;
    }

    private Integer decodeCurrentPageAttribute(int totalPages, HttpServletRequest request)
    {
        String
            currentPageAttribute =
            request.getParameter(RequestKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE);
        Integer
            currentPage =
            (Integer) request.getSession()
                .getAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE);
        if (currentPage
            == null)
        {
            currentPage =
                1;
        }
        if (currentPageAttribute.equalsIgnoreCase("F"))
        {
            return 1;
        }
        else if (currentPageAttribute.equalsIgnoreCase("P"))
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
        else if (currentPageAttribute.equalsIgnoreCase("N"))
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
        else if (currentPageAttribute.equalsIgnoreCase("L"))
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
                LOG.error(nfe);
                LOG.error("Invalid page attribute: "
                          + currentPageAttribute);
            }
        }
        return null;
    }

    private int calculateTotalPages(List sourceUsages, Integer sourceUsagesPerPage)
    {
        int
            totalPages =
            sourceUsages.size()
            / sourceUsagesPerPage;
        if ((sourceUsages.size()
             % sourceUsagesPerPage)
            > 0)
        {
            totalPages++;
        }
        return totalPages;
    }

    private boolean isSourceUsagesPerPageChanged(SourceUsageForm form, HttpServletRequest request)
    {
        if (form.getSourceUsagesPerPage()
            == null
            || form.getSourceUsagesPerPage()
            .equals(request.getSession()
                        .getAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_ASSETS_PER_PAGE)))
        {
            return false;
        }
        return true;
    }

    private Integer determineSourceUsagesPerPage(HttpServletRequest request, SourceUsageForm form)
    {
        Integer
            sourceUsagesPerPage;
        if (form.getSourceUsagesPerPage()
            != null
            && form.getSourceUsagesPerPage()
               > 0)
        {
            sourceUsagesPerPage =
                form.getSourceUsagesPerPage();
        }
        else
        {
            sourceUsagesPerPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_ASSETS_PER_PAGE);
        }
        if (sourceUsagesPerPage
            == null
            || sourceUsagesPerPage
               <= 0)
        {
            sourceUsagesPerPage =
                new Integer(25);
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_ASSETS_PER_PAGE,
                          sourceUsagesPerPage);
        return sourceUsagesPerPage;
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_ASC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_HASHTABLE,
                                            request);
        if (sortHash
            == null)
        {
            sortHash =
                initSortHash();
        }
        LOG.debug("sortMethod = "
                  + sortMethod);
        SortHashEntry
            entry =
            (SortHashEntry) sortHash.get(sortMethod);
        if (entry
            == null)
        {
            LOG.debug("Invalid sortMethod passed: "
                      + sortMethod);
            LOG.debug("Returning default sortMethod");
            return SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_DESC;
        }
        LOG.debug("entry.q0 = "
                  + entry.getQ0());
        if (entry.getIndex()
            == 0)
        {
            if (isChanged)
            {
                entry.setIndex(1);
            }
            sortHash.put(sortMethod,
                         entry);
            setSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_METHOD,
                                sortMethod,
                                request);
            return entry.getQuery();
        }
        if (isChanged)
        {
            entry.setIndex(0);
        }
        sortHash.put(sortMethod,
                     entry);
        setSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.SOURCE_USAGE_SORT_METHOD,
                            sortMethod,
                            request);
        return entry.getQuery();
    }

    private Hashtable initSortHash()
    {
        Hashtable
            sortHash =
            new Hashtable();
        LOG.debug("in initSortHash");
        sortHash.put(RequestKeys.SOURCE_USAGE_LIST_SORT_BY_SOURCE,
                     new SortHashEntry(0,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_ASC,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_DESC));
        sortHash.put(RequestKeys.SOURCE_USAGE_LIST_SORT_BY_METER_READING,
                     new SortHashEntry(0,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_METER_READING_ASC,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_METER_READING_DESC));
        sortHash.put(RequestKeys.SOURCE_USAGE_LIST_SORT_BY_START_DATE,
                     new SortHashEntry(0,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_START_DATE_ASC,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_START_DATE_DESC));
        sortHash.put(RequestKeys.SOURCE_USAGE_LIST_SORT_BY_END_DATE,
                     new SortHashEntry(0,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_END_DATE_ASC,
                                       SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_END_DATE_DESC));
        LOG.debug("initSortHash ... done");
        return sortHash;
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        if (request.getAttribute("type")
            != null
            && request.getAttribute("type")
            .equals("control"))
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.CONTROL_USAGE_LIST_MENU),
                            request);
            getTertiaryMenu(request).setCurrentItem(TertiaryMenu.CONTROL_USAGE_LIST_MENU_ITEM);
        }
        else
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_LIST_MENU),
                            request);
            getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_LIST_MENU_ITEM);
        }
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }
}

class SortHashEntry
{
    private
    int
        index;
    private
    String
        q0;
    private
    String
        q1;

    SortHashEntry(int index, String q0, String q1)
    {
        this.index =
            index;
        this.q0 =
            q0;
        this.q1 =
            q1;
    }

    int getIndex()
    {
        return index;
    }

    void setIndex(int index)
    {
        this.index =
            index;
    }

    String getQuery()
    {
        if (index
            == 0)
        {
            return q0;
        }
        return q1;
    }

    String getQ0()
    {
        return q0;
    }

/*
    void setQ0(String q0)
    {
        this.q0 =
            q0;
    }

    String getQ1()
    {
        return q1;
    }

    void setQ1(String q1)
    {
        this.q1 =
            q1;
    }
*/
}