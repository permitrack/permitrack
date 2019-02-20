package com.sehinc.environment.action.scclibrary;

import com.sehinc.environment.db.scc.EnvSccInfoLibrary;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccLibraryDeleteAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryDeleteAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccLibraryDeleteAction. ";
        String
            strLog =
            strMod
            + "sccLibraryAction() ";
        LOG.info(strLog
                 + "in action");
        SccLibraryForm
            sccForm =
            (SccLibraryForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.library.delete.cancel.action"), request);
            return mapping.findForward("scc.library.list.page");
        }
        else
        {
            Integer
                sccLibraryId;
            if (sccForm.getId()
                != null)
            {
                sccLibraryId =
                    sccForm.getId();
                LOG.debug("sccLibraryId="
                          + sccLibraryId);
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("scc.library.error.no.scc.info.id"));
                addError(new ActionMessage("scc.library.error.no.scc.info.id"), request);
                return mapping.findForward("scc.library.list.page");
            }
            EnvSccInfoLibrary
                envScc =
                new EnvSccInfoLibrary(sccLibraryId);
            try
            {
                envScc.load();
                envScc.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sccLibraryId};
                LOG.error(ApplicationResources.getProperty("scc.library.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.library.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("scc.library.list.page");
            }
            addMessage(new ActionMessage("scc.library.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
