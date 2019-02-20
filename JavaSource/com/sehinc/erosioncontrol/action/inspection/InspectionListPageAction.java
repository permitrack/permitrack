package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

@SuppressWarnings("unchecked")
public class InspectionListPageAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionListPageAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
//        LOG.info("In InspectionListPageAction");
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        Integer
            projectId =
            null;
        ProjectValue
            projectValue;
        if (request.getParameter(RequestKeys.EC_PROJECT_ID)
            != null)
        {
            projectId =
                new Integer(request.getParameter(RequestKeys.EC_PROJECT_ID));
        }
        else
        {
            projectValue =
                (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                                   request);
            if (projectValue
                != null)
            {
                projectId =
                    projectValue.getId();
            }
        }
        if (projectId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            return mapping.findForward("project.list.page");
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        try
        {
            EcProject
                project =
                EcProject.findById(projectId);
            projectValue =
                ProjectService.getProjectValue(project,
                                               clientValue,
                                               userValue,
                                               securityManager);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {projectId};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return mapping.findForward("project.list.page");
        }
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            projectValue,
                            request);
        String
            sortMethod;
        boolean
            isChanged =
            false;
        if (request.getParameter(RequestKeys.EC_INSPECTION_LIST_SORT)
            != null)
        {
            sortMethod =
                request.getParameter(RequestKeys.EC_INSPECTION_LIST_SORT);
            isChanged =
                true;
            LOG.debug("InspectionListPageAction.ActionForward() - request: "
                      + RequestKeys.EC_INSPECTION_LIST_SORT
                      + " = "
                      + sortMethod);
        }
        else
        {
            sortMethod =
                (String) getSessionAttribute(SessionKeys.EC_INSPECTION_SORT_METHOD,
                                             request);
            LOG.debug("InspectionListPageAction.ActionForward() - session: "
                      + SessionKeys.EC_INSPECTION_SORT_METHOD
                      + " = "
                      + sortMethod);
        }
        setSessionAttribute(SessionKeys.EC_INSPECTION_FIELD_LIST,
                            InspectionListItem.getSortedList(),
                            request);
        securityManager.setProjectID(projectValue);
        setSessionAttribute(SessionKeys.EC_INSPECTION_LIST,
                            InspectionService.getInspectionList(getSortQuery(sortMethod,
                                                                             isChanged,
                                                                             request),
                                                                projectValue,
                                                                clientValue,
                                                                userValue,
                                                                securityManager,
                                                                request),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    private String getSortQuery(String sortMethod, boolean isChanged, HttpServletRequest request)
    {
        if (sortMethod
            == null)
        {
            return InspectionService.INSPECTION_LIST_BY_INSPECTION_DATE_DESC;
        }
        Hashtable
            sortHash =
            (Hashtable) getSessionAttribute(SessionKeys.EC_INSPECTION_SORT_HASHTABLE,
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
            return InspectionService.INSPECTION_LIST_BY_INSPECTION_DATE_DESC;
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
            setSessionAttribute(SessionKeys.EC_INSPECTION_SORT_HASHTABLE,
                                sortHash,
                                request);
            setSessionAttribute(SessionKeys.EC_INSPECTION_SORT_METHOD,
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
        setSessionAttribute(SessionKeys.EC_INSPECTION_SORT_HASHTABLE,
                            sortHash,
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_SORT_METHOD,
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
        sortHash.put(RequestKeys.EC_INSPECTION_LIST_SORT_BY_INSPECTION_DATE,
                     new SortHashEntry(0,
                                       InspectionService.INSPECTION_LIST_BY_INSPECTION_DATE_DESC,
                                       InspectionService.INSPECTION_LIST_BY_INSPECTION_DATE_ASC));
        sortHash.put(RequestKeys.EC_INSPECTION_LIST_SORT_BY_ENTERED_DATE,
                     new SortHashEntry(0,
                                       InspectionService.INSPECTION_LIST_BY_ENTERED_DATE_ASC,
                                       InspectionService.INSPECTION_LIST_BY_ENTERED_DATE_DESC));
        sortHash.put(RequestKeys.EC_INSPECTION_LIST_SORT_BY_INSPECTOR_NAME,
                     new SortHashEntry(0,
                                       InspectionService.INSPECTION_LIST_BY_INSPECTOR_NAME_ASC,
                                       InspectionService.INSPECTION_LIST_BY_INSPECTOR_NAME_DESC));
        sortHash.put(RequestKeys.EC_INSPECTION_LIST_SORT_BY_REASON,
                     new SortHashEntry(0,
                                       InspectionService.INSPECTION_LIST_BY_REASON_ASC,
                                       InspectionService.INSPECTION_LIST_BY_REASON_DESC));
        sortHash.put(RequestKeys.EC_INSPECTION_LIST_SORT_BY_STATUS,
                     new SortHashEntry(0,
                                       InspectionService.INSPECTION_LIST_BY_STATUS_ASC,
                                       InspectionService.INSPECTION_LIST_BY_STATUS_DESC));
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
