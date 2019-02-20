package com.sehinc.environment.action.source;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.List;

public class SourceListPageAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceListPageAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SourceListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Sources",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.SOURCE_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.SOURCE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_DELETE),
                            request);
        ClientValue
            clientValue =
            getClientValue(request);
        List
            sourceList;
        try
        {
            String
                sortMethod;
            boolean
                isChanged =
                false;
            if (request.getParameter(RequestKeys.SOURCE_LIST_SORT)
                != null)
            {
                sortMethod =
                    request.getParameter(RequestKeys.SOURCE_LIST_SORT);
                isChanged =
                    true;
                LOG.debug("SourceListPageAction.ActionForward() - request: "
                          + RequestKeys.SOURCE_LIST_SORT
                          + " = "
                          + sortMethod);
            }
            else
            {
                sortMethod =
                    (String) getSessionAttribute(SessionKeys.SOURCE_SORT_METHOD,
                                                 request);
                LOG.debug("SourceListPageAction.ActionForward() - session: "
                          + SessionKeys.SOURCE_SORT_METHOD
                          + " = "
                          + sortMethod);
            }
            sourceList =
                SourceService.getSourceList(getSortQuery(sortMethod,
                                                         isChanged,
                                                         request),
                                            clientValue.getId());
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("source.error.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("source.error.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        setSessionAttribute(SessionKeys.SOURCE_FIELD_LIST,
                            SourceListItem.getSortedList(),
                            request);
        setSessionAttribute(SessionKeys.EV_ACTIVE_SOURCE_LIST,
                            sourceList,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_LIST_MENU),
                        request);
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return SourceService.SOURCE_LIST_SORT_BY_DISPLAY_LABEL_ASC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.SOURCE_SORT_HASHTABLE,
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
            return SourceService.SOURCE_LIST_SORT_BY_DISPLAY_LABEL_ASC;
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
            setSessionAttribute(SessionKeys.SOURCE_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.SOURCE_SORT_METHOD,
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
        setSessionAttribute(SessionKeys.SOURCE_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.SOURCE_SORT_METHOD,
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
        sortHash.put(RequestKeys.SOURCE_LIST_SORT_BY_DISPLAY_LABEL,
                     new SortHashEntry(0,
                                       SourceService.SOURCE_LIST_SORT_BY_DISPLAY_LABEL_ASC,
                                       SourceService.SOURCE_LIST_SORT_BY_DISPLAY_LABEL_DESC));
        sortHash.put(RequestKeys.SOURCE_LIST_SORT_BY_TYPE,
                     new SortHashEntry(0,
                                       SourceService.SOURCE_LIST_SORT_BY_TYPE_ASC,
                                       SourceService.SOURCE_LIST_SORT_BY_TYPE_DESC));
        LOG.debug("initSortHash ... done");
        return sortHash;
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