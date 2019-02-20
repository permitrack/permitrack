package com.sehinc.environment.action.assetsource;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssetSourceCreateAction
    extends AssetSourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSourceCreateAction.class);

    public ActionForward assetSourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSourceCreateAction. ";
        strLog =
            strMod
            + "assetSourceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.source.create.cancel.action"), request);
            return mapping.findForward("asset.view.page");
        }
        else
        {
            try
            {
                AssetSourceForm
                    ssForm =
                    (AssetSourceForm) form;
                Integer
                    assetId =
                    ssForm.getAssetId();
                setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                    assetId,
                                    request);
                if (ssForm.getSourceId()
                    == 0)
                {
                    addMessage(new ActionMessage("asset.source.select"), request);
                    return mapping.getInputForward();
                }
                EnvAssetSource
                    dupSS =
                    EnvAssetSource.findByAssetIdAndSourceId(ssForm.getAssetId(),
                                                            ssForm.getSourceId());
                if (dupSS
                    != null)
                {
                    addMessage(new ActionMessage("asset.source.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvAssetSource
                        assetSource =
                        new EnvAssetSource();
                    assetSource.setAssetId(assetId);
                    assetSource.setSourceId(ssForm.getSourceId());
                    assetSource.setActiveTs(DateUtil.parseDate(ssForm.getActiveTsString()));
                    assetSource.setInactiveTs(DateUtil.parseDate(ssForm.getInactiveTsString()));
                    assetSource.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        assetSourceId =
                        assetSource.save(objUser);
                    LOG.debug(strLog
                              + "New Asset Source created with ID = "
                              + assetSourceId.toString());
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