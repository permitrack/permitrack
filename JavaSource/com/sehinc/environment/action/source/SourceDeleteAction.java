package com.sehinc.environment.action.source;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SourceDeleteAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceDeleteAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SourceDeleteAction. ";
        String
            strLog =
            strMod
            + "sourceAction() ";
        LOG.info(strLog
                 + "in action");
        SourceForm
            sForm =
            (SourceForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.delete.cancel.action"), request);
            return mapping.findForward("source.list.page");
        }
        else
        {
            UserValue
                userValue =
                getUserValue(request);
            Integer
                sourceId;
            LOG.debug("sourceId="
                      + sForm.getId());
            if (sForm.getId()
                != null)
            {
                sourceId =
                    sForm.getId();
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("source.error.no.source.id"));
                addError(new ActionMessage("source.error.no.source.id"), request);
                return mapping.findForward("source.list.page");
            }
            List<EnvAssetSource>
                assetSources =
                EnvAssetSource.findBySourceId(sourceId);
            try
            {
                for (EnvAssetSource asstSrc : assetSources)
                {
                    asstSrc.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                    asstSrc.save(userValue);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sourceId};
                LOG.error(ApplicationResources.getProperty("asset.source.delete.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.source.delete.failed",
                                           parameters), request);
                return mapping.findForward("source.list.page");
            }
            EnvSource
                envSource =
                new EnvSource(sourceId);
            try
            {
                envSource.load();
                if (!envSource.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    throw new Exception("The requested Source ID = "
                                        + sourceId
                                        + " does not exist.");
                }
                envSource.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envSource.save(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sourceId};
                LOG.error(ApplicationResources.getProperty("source.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("source.list.page");
            }
            addMessage(new ActionMessage("source.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}