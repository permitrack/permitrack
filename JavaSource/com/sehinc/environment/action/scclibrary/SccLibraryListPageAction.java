package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SccLibraryValue;
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

public class SccLibraryListPageAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryListPageAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SccLibraryListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_LIBRARY_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View SCC Library",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.SCC_LIBRARY_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_LIBRARY_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.SCC_LIBRARY_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_LIBRARY_DELETE),
                            request);
        List<SccLibraryValue>
            sccList;
        try
        {
            String
                sortMethod;
            boolean
                isChanged =
                false;
            if (request.getParameter(RequestKeys.SCC_LIBRARY_LIST_SORT)
                != null)
            {
                sortMethod =
                    request.getParameter(RequestKeys.SCC_LIBRARY_LIST_SORT);
                isChanged =
                    true;
                LOG.debug("SccLibraryListPageAction.ActionForward() - request: "
                          + RequestKeys.SCC_LIBRARY_LIST_SORT
                          + " = "
                          + sortMethod);
            }
            else
            {
                sortMethod =
                    (String) getSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_METHOD,
                                                 request);
                LOG.debug("SccLibraryListPageAction.ActionForward() - session: "
                          + SessionKeys.SCC_LIBRARY_SORT_METHOD
                          + " = "
                          + sortMethod);
            }
            sccList =
                SccLibraryService.getSccLibraryList(getSortQuery(sortMethod,
                                                                 isChanged,
                                                                 request));
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("scc.library.error.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("scc.library.error.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        setSessionAttribute(SessionKeys.SCC_LIBRARY_FIELD_LIST,
                            SccLibraryListItem.getSortedList(),
                            request);
        setSessionAttribute(SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST,
                            sccList,
                            request);
        setSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_HASHTABLE,
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
            return SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC;
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
            setSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_METHOD,
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
        setSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.SCC_LIBRARY_SORT_METHOD,
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
        sortHash.put(RequestKeys.SCC_LIBRARY_LIST_SORT_BY_SCC_NUMBER,
                     new SortHashEntry(0,
                                       SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC,
                                       SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_NUMBER_DESC));
        sortHash.put(RequestKeys.SCC_LIBRARY_LIST_SORT_BY_SCC_DESCRIPTION,
                     new SortHashEntry(0,
                                       SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_ASC,
                                       SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_DESC));
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
