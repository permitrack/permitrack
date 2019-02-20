package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.scc.EnvSccInfoLibrary;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccLibraryEditAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryEditAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccLibraryEditAction. ";
        strLog =
            strMod
            + "sccLibraryAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.library.edit.cancel.action"), request);
            return mapping.findForward("scc.library.list.page");
        }
        else
        {
            SccLibraryForm
                sccForm =
                (SccLibraryForm) form;
            Integer
                sccLibraryId =
                sccForm.getId();
            EnvSccInfoLibrary
                sccData =
                new EnvSccInfoLibrary(sccLibraryId);
            UserValue
                userValue =
                getUserValue(request);
            try
            {
                sccData.load();
                sccData.setNumber(sccForm.getNumber());
                sccData.setDescription(sccForm.getDescription());
                sccData.setMajIndustrialGroup(sccForm.getMajIndustrialGroup());
                sccData.setRawMaterial(sccForm.getRawMaterial());
                sccData.setEmittingProcess(sccForm.getEmittingProcess());
                sccData.save(userValue);
                setSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                                    sccLibraryId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sccLibraryId};
                LOG.error(ApplicationResources.getProperty("scc.library.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.library.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("scc.library.list.page");
            }
        }
        addMessage(new ActionMessage("scc.library.edit.success"), request);
        return mapping.findForward("continue");
    }
}
