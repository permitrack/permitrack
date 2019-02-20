/*
package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceUsageReadingsAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageReadingsPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int
            assetId =
            getParamInt("id",
                        request);
        if (assetId
            > 0)
        {
            setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                assetId,
                                request);
            try
            {
                EnvAsset
                    selectedAsset =
                    new EnvAsset(assetId);
                selectedAsset.load();
                setSessionAttribute(SessionKeys.SELECTED_ASSET_NAME,
                                    selectedAsset.getNumberAndName(),
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {e.getMessage()};
                addError(new ActionMessage("source.usage.load.failed",
                                           parameters), request);
                LOG.error(ApplicationResources.getProperty("source.usage.load.failed",
                                                           parameters));
                return mapping.getInputForward();
            }
            return mapping.findForward("continue");
        }
        else
        {
            LOG.error("Cannot retrieve asset id parameter 'id' from request.");
            return mapping.findForward("error");
        }
    }
}
*/
