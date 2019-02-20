package com.sehinc.erosioncontrol.action.correctiveaction;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.correctiveaction.CorrectiveActionService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

public class CorrectiveActionListPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(CorrectiveActionListPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In CorrectiveActionListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            userValue =
            getUserValue(request);
        String
            sortMethod;
        boolean
            isChanged =
            false;
        if (request.getParameter(RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT)
            != null)
        {
            sortMethod =
                request.getParameter(RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT);
            isChanged =
                true;
            LOG.debug("CorrectiveActionListPageAction.ActionForward() - request: "
                      + RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT
                      + " = "
                      + sortMethod);
        }
        else
        {
            sortMethod =
                (String) getSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_METHOD,
                                             request);
            LOG.debug("CorrectiveActionListPageAction.ActionForward() - session: "
                      + SessionKeys.EC_CORRECTIVE_ACTION_SORT_METHOD
                      + " = "
                      + sortMethod);
        }
        setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_FIELD_LIST,
                            CorrectiveActionListItem.getSortedList(),
                            request);
        setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_LIST,
                            CorrectiveActionService.getCorrectiveActionList(securityManager,
                                                                            clientValue,
                                                                            userValue,
                                                                            getSortQuery(sortMethod,
                                                                                         isChanged,
                                                                                         request)),
                            request);
        return mapping.findForward("continue");
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_ASC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_HASHTABLE,
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
            return CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_ASC;
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
            setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_METHOD,
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
        setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.EC_CORRECTIVE_ACTION_SORT_METHOD,
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
        sortHash.put(RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_LAST_INSPECTION_DATE,
                     new SortHashEntry(0,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_DESC,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_ASC));
        sortHash.put(RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_PROJECT_NAME,
                     new SortHashEntry(0,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_ASC,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_DESC));
        sortHash.put(RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_BMP_NAME,
                     new SortHashEntry(0,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_BMP_NAME_ASC,
                                       CorrectiveActionService.CORRECTIVE_ACTION_LIST_BY_BMP_NAME_DESC));
        LOG.debug("initSortHash ... done");
        return sortHash;
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.CORRECTIVE_ACTION_LIST_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_LIST_MENU_NAME),
                         request);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME);
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
}
