package com.sehinc.environment.action.asset;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssetEditAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetEditAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "AssetEditPageAction. ";
        String
            strLog =
            strMod
            + "assetAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.edit.cancel.action"), request);
            return mapping.findForward("asset.list.page");
        }
        else
        {
            AssetForm
                objA =
                (AssetForm) form;
            UserValue
                userValue =
                getUserValue(request);
            ClientValue
                objClient =
                getClientValue(request);
            Integer
                assetId =
                objA.getId();
            EnvAsset
                assetData =
                new EnvAsset(assetId);
            try
            {
                assetData.load();
                if (!assetData.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    throw new Exception("The requested Asset ID = "
                                        + assetId
                                        + " does not exist.");
                }
                AssetService.saveAssetInformation(objA,
                                                  assetData,
                                                  userValue,
                                                  objClient);
                setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                    assetId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetId};
                LOG.error(ApplicationResources.getProperty("asset.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("asset.list.page");
            }
        }
        addMessage(new ActionMessage("asset.edit.success"), request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.ASSET_VIEW_MENU),
                        request);
    }
}