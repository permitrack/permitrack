package com.sehinc.environment.action.sourcescc;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.sourcescc.EnvSourceSccInfo;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceSccCreateAction
    extends SourceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSccCreateAction.class);

    public ActionForward sourceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSccCreateAction. ";
        strLog =
            strMod
            + "sourceSccAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.scc.create.cancel.action"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            try
            {
                SourceSccForm
                    ssForm =
                    (SourceSccForm) form;
                Integer
                    sourceId =
                    ssForm.getSourceId();
                setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                    sourceId,
                                    request);
                if (ssForm.getSccInfoId()
                    == 0)
                {
                    addMessage(new ActionMessage("source.scc.select"), request);
                    return mapping.getInputForward();
                }
                EnvSourceSccInfo
                    dupSS =
                    EnvSourceSccInfo.findBySourceIdAndSccInfoId(ssForm.getSourceId(),
                                                                ssForm.getSccInfoId());
                if (dupSS
                    != null)
                {
                    addMessage(new ActionMessage("source.scc.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvSourceSccInfo
                        sourceScc =
                        new EnvSourceSccInfo();
                    sourceScc.setSourceId(sourceId);
                    sourceScc.setSccInfoId(ssForm.getSccInfoId());
                    sourceScc.setSccNumber(ssForm.getSccNumber());
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        sourceSccId =
                        sourceScc.save(objUser);
                    LOG.debug(strLog
                              + "New Source SccInfo created with ID = "
                              + sourceSccId.toString());
                }
            }
            catch (Exception eActive)
            {
                LOG.debug("Error.  Message: "
                          + eActive.getMessage());
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strLog
                                                  + "Error.  Message: "
                                                  + eActive.getMessage()),
                                    request);
            }
        }
        return mapping.findForward("continue");
    }
}