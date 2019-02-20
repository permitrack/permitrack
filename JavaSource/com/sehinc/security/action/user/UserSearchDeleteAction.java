package com.sehinc.security.action.user;

import com.sehinc.erosioncontrol.db.user.EcSearch;
import com.sehinc.erosioncontrol.db.user.EcUserDefaultSearch;
import com.sehinc.erosioncontrol.db.user.EcUserSearch;
import com.sehinc.security.action.base.RequestKeys;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSearchDeleteAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchDeleteAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Integer
            mintUserId =
            getUserId(request);
        if (mintUserId
            == null
            || mintUserId.intValue()
               == 0)
        {
            LOG.error("User not found on request or session");
            addError(new ActionMessage("error.user.not.found.in.session"), request);
            return mapping.findForward("user.search.list.page");
        }
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
        try
        {
            EcUserSearch
                userSearch =
                EcUserSearch.findByUserIdAndSearchId(mintUserId,
                                                     searchId);
            if (userSearch
                != null)
            {
                userSearch.delete();
            }
            EcSearch
                search =
                new EcSearch(searchId);
            if (!search.load())
            {
                LOG.error("Failed to load search ID "
                          + searchId);
                addError(new ActionMessage("saved.searches.error.failed.to.load.delete",
                                           mintUserId), request);
                return mapping.findForward("user.search.list.page");
            }
            search.delete();
            EcUserDefaultSearch
                defaultSearch =
                EcUserDefaultSearch.findByUserId(mintUserId);
            if (defaultSearch
                != null)
            {
                if (defaultSearch.getDefaultSearchId()
                    .equals(searchId))
                {
                    defaultSearch.delete();
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete search ID "
                      + searchId);
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("delete.search.failed",
                                       mintUserId), request);
            return mapping.findForward("user.search.list.page");
        }
        return mapping.findForward("continue");
    }
}