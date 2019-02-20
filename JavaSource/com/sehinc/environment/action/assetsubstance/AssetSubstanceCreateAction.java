package com.sehinc.environment.action.assetsubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AssetSubstanceCreateAction
    extends AssetSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSubstanceCreateAction.class);

    public ActionForward assetSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSubstanceCreateAction. ";
        strLog =
            strMod
            + "assetSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.substance.create.cancel.action"), request);
            return mapping.findForward("asset.view.page");
        }
        else
        {
            try
            {
                AssetSubstanceForm
                    ssForm =
                    (AssetSubstanceForm) form;
                Integer
                    assetId =
                    ssForm.getAssetId();
                setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                    assetId,
                                    request);
                if (ssForm.getSubstanceTypeCd()
                    == 0)
                {
                    addMessage(new ActionMessage("asset.substance.type.select"), request);
                    return mapping.getInputForward();
                }
                List<EnvAssetSubstance>
                    dupAS =
                    EnvAssetSubstance.findByAssetIdAndSubstanceTypeCd(ssForm.getAssetId(),
                                                                      ssForm.getSubstanceTypeCd());
                if (dupAS.size()
                    > 0)
                {
                    addMessage(new ActionMessage("asset.substance.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvAssetSubstance
                        assetSubstance =
                        new EnvAssetSubstance();
                    assetSubstance.setAssetId(assetId);
                    assetSubstance.setSubstanceTypeCd(ssForm.getSubstanceTypeCd());
                    assetSubstance.setEfficiencyFactor(ssForm.getEfficiencyFactor());
                    assetSubstance.setAdditionalInfo(ssForm.getAdditionalInfo());
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        assetSubstanceId =
                        assetSubstance.save(objUser);
                    LOG.debug(strLog
                              + "New Asset Substance created with ID = "
                              + assetSubstanceId.toString());
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