package com.sehinc.environment.action.scc;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.scclibrary.SccLibraryService;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SccLibraryValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SccLibraryImportPageAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryImportPageAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SccLibraryImportPageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        List<SccLibraryValue>
            sccList;
        try
        {
            String
                sortMethod =
                SccLibraryService.SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC;
            sccList =
                SccLibraryService.filterSccLibraryListByClient(sortMethod,
                                                               clientValue.getId());
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
        setSessionAttribute(SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST,
                            sccList,
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SCC_IMPORT_MENU_ITEM);
    }
}
