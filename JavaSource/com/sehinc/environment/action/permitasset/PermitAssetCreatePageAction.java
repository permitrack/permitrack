package com.sehinc.environment.action.permitasset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermitAssetCreatePageAction
    extends PermitAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitAssetCreatePageAction.class);

    public ActionForward permitAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitAssetCreatePageAction. ";
        strLog =
            strMod
            + "PermitAssetAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_ASSET_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new permitted asset.");
            addError(new ActionMessage("permit.asset.create.unauthorized"), request);
            return mapping.findForward("permit.detail.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new permit asset page.");
        try
        {
            PermitAssetForm
                paForm =
                (PermitAssetForm) form;
            paForm.reset();
            Integer
                permitDetailId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                              request);
            EnvPermitDetail
                permitDetail =
                new EnvPermitDetail(permitDetailId);
            permitDetail.load();
            paForm.setPermitDetailId(permitDetailId);
            paForm.setPermitDetailName(permitDetail.getName());
            paForm.setPermitDetailDesc(permitDetail.getDescription());
            ClientValue
                clientValue =
                getClientValue(request);
            List
                assets =
                EnvAsset.findByClientIdSortByNumber(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_ASSET_LIST,
                                assets,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("permit.asset.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("permit.asset.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_DETAIL_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_DETAIL_MENU_ITEM);
    }
}