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

public class SccLibraryCreateAction
    extends SccLibraryBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryCreateAction.class);

    public ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccLibraryCreateAction. ";
        String
            strLog =
            strMod
            + "sccLibraryAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.library.create.cancel.action"), request);
            return mapping.findForward("scc.library.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                SccLibraryForm
                    sccForm =
                    (SccLibraryForm) form;
                EnvSccInfoLibrary
                    dupScc =
                    EnvSccInfoLibrary.findBySccNumber(sccForm.getNumber());
                if (dupScc
                    != null)
                {
                    addMessage(new ActionMessage("scc.library.duplicate.error"), request);
                    return mapping.findForward("scc.library.list.page");
                }
                EnvSccInfoLibrary
                    sccData =
                    new EnvSccInfoLibrary();
                sccData.setNumber(sccForm.getNumber());
                sccData.setDescription(sccForm.getDescription());
                sccData.setMajIndustrialGroup(sccForm.getMajIndustrialGroup());
                sccData.setRawMaterial(sccForm.getRawMaterial());
                sccData.setEmittingProcess(sccForm.getEmittingProcess());
                Integer
                    sccLibraryId =
                    sccData.save(objUser);
                setSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                                    sccLibraryId,
                                    request);
                LOG.debug(strLog
                          + "New SccLibrary created with ID = "
                          + sccLibraryId.toString());
                setSessionAttribute(SessionKeys.EV_SCC_LIBRARY_ID,
                                    sccLibraryId,
                                    request);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("scc.library.error.create.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.library.error.create.failed"), request);
                return mapping.findForward("scc.library.list.page");
            }
        }
        addMessage(new ActionMessage("scc.library.create.success"), request);
        return mapping.findForward("continue");
    }
}
