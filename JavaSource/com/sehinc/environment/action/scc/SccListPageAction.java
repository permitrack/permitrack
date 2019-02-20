package com.sehinc.environment.action.scc;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SccValue;
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

public class SccListPageAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccListPageAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SccListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Scc Info",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        if (securityManager.getIsSystemAdministrator())
        {
            setSessionAttribute(SessionKeys.SCC_CAN_UPDATE,
                                securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_UPDATE),
                                request);
            setSessionAttribute(SessionKeys.SCC_CAN_DELETE,
                                securityManager.HasECPermission(SecureObjectPermissionData.EV_SCC_DELETE),
                                request);
        }
        else
        {
            setSessionAttribute(SessionKeys.SCC_CAN_UPDATE,
                                false,
                                request);
            setSessionAttribute(SessionKeys.SCC_CAN_DELETE,
                                false,
                                request);
        }
        ClientValue
            clientValue =
            getClientValue(request);
        List<SccValue>
            sccList;
        try
        {
            String
                sortMethod;
            boolean
                isChanged =
                false;
            if (request.getParameter(RequestKeys.SCC_LIST_SORT)
                != null)
            {
                sortMethod =
                    request.getParameter(RequestKeys.SCC_LIST_SORT);
                isChanged =
                    true;
                LOG.debug("SccListPageAction.ActionForward() - request: "
                          + RequestKeys.SCC_LIST_SORT
                          + " = "
                          + sortMethod);
            }
            else
            {
                sortMethod =
                    (String) getSessionAttribute(SessionKeys.SCC_SORT_METHOD,
                                                 request);
                LOG.debug("SccListPageAction.ActionForward() - session: "
                          + SessionKeys.SCC_SORT_METHOD
                          + " = "
                          + sortMethod);
            }
            sccList =
                SccService.getSccList(getSortQuery(sortMethod,
                                                   isChanged,
                                                   request),
                                      clientValue.getId());
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("scc.error.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("scc.error.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        setSessionAttribute(SessionKeys.SCC_FIELD_LIST,
                            SccListItem.getSortedList(),
                            request);
        setSessionAttribute(SessionKeys.EV_ACTIVE_SCC_LIST,
                            sccList,
                            request);
        setSessionAttribute(SessionKeys.EV_SCC_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SCC_LIST_MENU),
                        request);
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return SccService.SCC_LIST_SORT_BY_NUMBER_ASC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.SCC_SORT_HASHTABLE,
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
            return SccService.SCC_LIST_SORT_BY_NUMBER_ASC;
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
            setSessionAttribute(SessionKeys.SCC_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.SCC_SORT_METHOD,
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
        setSessionAttribute(SessionKeys.SCC_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.SCC_SORT_METHOD,
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
        sortHash.put(RequestKeys.SCC_LIST_SORT_BY_SCC_NUMBER,
                     new SortHashEntry(0,
                                       SccService.SCC_LIST_SORT_BY_NUMBER_ASC,
                                       SccService.SCC_LIST_SORT_BY_NUMBER_DESC));
        sortHash.put(RequestKeys.SCC_LIST_SORT_BY_SCC_DESCRIPTION,
                     new SortHashEntry(0,
                                       SccService.SCC_LIST_SORT_BY_DESCRIPTION_ASC,
                                       SccService.SCC_LIST_SORT_BY_DESCRIPTION_DESC));
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
