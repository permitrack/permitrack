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

public class SccCreateAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccCreateAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccCreateAction. ";
        String
            strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.create.cancel.action"), request);
            return mapping.findForward("scc.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    clientValue =
                    getClientValue(request);
                SccForm
                    sccForm =
                    (SccForm) form;
                EnvSccInfo
                    dupScc =
                    EnvSccInfo.findBySccNumberAndClientId(sccForm.getNumber(),
                                                          clientValue.getId());
                if (dupScc
                    != null)
                {
                    addMessage(new ActionMessage("scc.duplicate.error"), request);
                    return mapping.findForward("scc.list.page");
                }
                EnvSccInfo
                    sccData =
                    new EnvSccInfo();
                sccData.setClientId(clientValue.getId());
                sccData.setNumber(sccForm.getNumber());
                sccData.setDescription(sccForm.getDescription());
                sccData.setMajIndustrialGroup(sccForm.getMajIndustrialGroup());
                sccData.setRawMaterial(sccForm.getRawMaterial());
                sccData.setEmittingProcess(sccForm.getEmittingProcess());
                Integer
                    intSccId =
                    sccData.save(objUser);
                setSessionAttribute(SessionKeys.EV_SCC_ID,
                                    intSccId,
                                    request);
                LOG.debug(strLog
                          + "New SccInfo created with ID = "
                          + intSccId.toString());
                setSessionAttribute(SessionKeys.EV_SCC_ID,
                                    intSccId,
                                    request);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("scc.error.create.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("scc.error.create.failed"), request);
                return mapping.findForward("scc.list.page");
            }
        }
        addMessage(new ActionMessage("scc.create.success"), request);
        return mapping.findForward("continue");
    }
}
