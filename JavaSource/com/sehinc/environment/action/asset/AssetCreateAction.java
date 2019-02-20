package com.sehinc.environment.action.asset;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
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
import java.util.List;

public class AssetCreateAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetCreateAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward =
            CommonConstants.FORWARD_CONTINUE;
        String
            strMod =
            "AssetCreateAction. ";
        String
            strLog =
            strMod
            + "assetAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.create.cancel.action"), request);
            return mapping.findForward("asset.list.page");
        }
        else
        {
            try
            {
                AssetForm
                    objA =
                    (AssetForm) form;
                Integer
                    facilityId =
                    (Integer) getFacility(request);
                String
                    facilityName =
                    (String) getSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                                                 request);
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    objClient =
                    getClientValue(request);
                List
                    dupOrgs =
                    EnvAsset.findByNameAndNumber(objClient.getId(),
                                                 objA.getName(),
                                                 objA.getNumber());
                if (dupOrgs.size()
                    != 0)
                {
                    Object[]
                        parameters =
                        {
                            facilityId,
                            facilityName};
                    addMessage(new ActionMessage("asset.duplicate.error",
                                                 parameters), request);
                    LOG.error(ApplicationResources.getProperty("asset.duplicate.error",
                                                               parameters));
                    return mapping.findForward("asset.list.page");
                }
                else
                {
                    Integer
                        intAssetId =
                        AssetService.saveAssetInformation(objA,
                                                          objUser,
                                                          objClient);
                    LOG.debug(strLog
                              + "New Asset created with ID = "
                              + intAssetId.toString());
                    setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                        intAssetId,
                                        request);
                    AssetService.saveFacilityAsset(facilityId,
                                                   intAssetId,
                                                   objUser);
                }
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("asset.error.create.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.error.create.failed"), request);
                return mapping.findForward("asset.list.page");
            }
        }
        addMessage(new ActionMessage("asset.create.success"), request);
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
    }
}