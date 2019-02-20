package com.sehinc.environment.action.assetsubstance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AssetSubstanceCreatePageAction
    extends AssetSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSubstanceCreatePageAction.class);

    public ActionForward assetSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSubstanceCreatePageAction. ";
        strLog =
            strMod
            + "assetSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SUBSTANCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new asset substance.");
            addError(new ActionMessage("asset.substance.create.unauthorized"), request);
            return mapping.findForward("asset.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new asset substance page.");
        try
        {
            AssetSubstanceForm
                ssForm =
                (AssetSubstanceForm) form;
            ssForm.reset();
            Integer
                assetId =
                (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                              request);
            ssForm.setAssetId(assetId);
            ClientValue
                clientValue =
                getClientValue(request);
            List
                substanceTypes =
                EnvSubstanceTypeData.findAllByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_SUBSTANCE_TYPE_LIST,
                                substanceTypes,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("asset.substance.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("asset.substance.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }
}