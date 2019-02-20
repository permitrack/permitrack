package com.sehinc.environment.action.scc;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccEditAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccEditAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SccEditAction. ";
        strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.edit.cancel.action"), request);
            return mapping.findForward("scc.list.page");
        }
        else
        {
            SccForm
                sccForm =
                (SccForm) form;
            Integer
                sccId =
                sccForm.getId();
            EnvSccInfo
                sccData =
                new EnvSccInfo(sccId);
            ClientValue
                clientValue =
                getClientValue(request);
            UserValue
                userValue =
                getUserValue(request);
            try
            {
                sccData.load();
                sccData.setNumber(sccForm.getNumber());
                sccData.setDescription(sccForm.getDescription());
                sccData.setClientId(clientValue.getId());
                sccData.setMajIndustrialGroup(sccForm.getMajIndustrialGroup());
                sccData.setRawMaterial(sccForm.getRawMaterial());
                sccData.setEmittingProcess(sccForm.getEmittingProcess());
                sccData.save(userValue);
                setSessionAttribute(SessionKeys.EV_SCC_ID,
                                    sccId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sccId};
                LOG.error(ApplicationResources.getProperty("scc.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("scc.list.page");
            }
        }
        addMessage(new ActionMessage("scc.edit.success"), request);
        return mapping.findForward("continue");
    }
}
