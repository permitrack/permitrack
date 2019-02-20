package com.sehinc.environment.action.scc;

import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.db.sourcescc.EnvSourceSccInfo;
import com.sehinc.environment.db.substancescc.EnvSubstanceSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SccDeleteAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccDeleteAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccDeleteAction. ";
        String
            strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        SccForm
            sccForm =
            (SccForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.delete.cancel.action"), request);
            return mapping.findForward("scc.list.page");
        }
        else
        {
            Integer
                sccId;
            LOG.debug("sccId="
                      + sccForm.getId());
            if (sccForm.getId()
                != null)
            {
                sccId =
                    sccForm.getId();
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("scc.error.no.scc.info.id"));
                addError(new ActionMessage("scc.error.no.scc.info.id"), request);
                return mapping.findForward("scc.list.page");
            }
            List
                sourceSCCs =
                EnvSourceSccInfo.findBySccInfoId(sccId);
            for (Object sSCC : sourceSCCs)
            {
                EnvSourceSccInfo
                    sourceSCC =
                    (EnvSourceSccInfo) sSCC;
                sourceSCC.delete();
            }
            List
                subSCCs =
                EnvSubstanceSccInfo.findBySccInfoId(sccId);
            for (Object subSCC : subSCCs)
            {
                EnvSubstanceSccInfo
                    substanceSCC =
                    (EnvSubstanceSccInfo) subSCC;
                substanceSCC.delete();
            }
            EnvSccInfo
                envScc =
                new EnvSccInfo(sccId);
            try
            {
                envScc.load();
                envScc.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sccId};
                LOG.error(ApplicationResources.getProperty("scc.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("scc.list.page");
            }
            addMessage(new ActionMessage("scc.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
