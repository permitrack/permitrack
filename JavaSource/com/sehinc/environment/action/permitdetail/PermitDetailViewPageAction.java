package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PermitDetailViewPageAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailViewPageAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In PermitDetailViewPageAction");
        PermitDetailForm
            permitForm =
            (PermitDetailForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_DETAIL_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("permit.detail.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.detail.view.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("permit.view.page");
        }
        Integer
            permitDetailId;
        LOG.debug("permitDetailId="
                  + request.getParameter(RequestKeys.EV_PERMIT_DETAIL_ID));
        if (request.getParameter(RequestKeys.EV_PERMIT_DETAIL_ID)
            != null)
        {
            permitDetailId =
                new Integer(request.getParameter(RequestKeys.EV_PERMIT_DETAIL_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                     request)
                 != null)
        {
            permitDetailId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                              request);
            LOG.debug("Got permitDetailId="
                      + permitDetailId
                      + " from the session.");
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("permit.detail.error.no.permit.on.request"));
            addError(new ActionMessage("permit.detail.error.no.permit.on.request"), request);
            return mapping.findForward("permit.view.page");
        }
        EnvPermitDetail
            envPermitDetail =
            new EnvPermitDetail(permitDetailId);
        try
        {
            envPermitDetail.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {permitDetailId};
            LOG.error(ApplicationResources.getProperty("permit.detail.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("permit.detail.error.load.failed",
                                       parameters), request);
            return mapping.findForward("permit.view.page");
        }
        permitForm.setId(envPermitDetail.getId());
        permitForm.setName(envPermitDetail.getName());
        permitForm.setDescription(envPermitDetail.getDescription());
        permitForm.setAvgPeriod(envPermitDetail.getAvgPeriod());
        BigDecimal
            noValue =
            new BigDecimal("0");
        BigDecimal
            bdVocLimit =
            envPermitDetail.getVocLimit();
        if (bdVocLimit.compareTo(noValue)
            != 0)
        {
            permitForm.setVocLimit(bdVocLimit.toString());
        }
        else
        {
            permitForm.setVocLimit("0");
        }
        BigDecimal
            bdHapsLimit =
            envPermitDetail.getHapsLimit();
        if (bdHapsLimit.compareTo(noValue)
            != 0)
        {
            permitForm.setHapsLimit(bdHapsLimit.toString());
        }
        else
        {
            permitForm.setHapsLimit("0");
        }
        BigDecimal
            bdMmbtuLimit =
            envPermitDetail.getMmbtuLimit();
        if (bdMmbtuLimit.compareTo(noValue)
            != 0)
        {
            permitForm.setMmbtuLimit(bdMmbtuLimit.toString());
        }
        else
        {
            permitForm.setMmbtuLimit("0");
        }
        if (envPermitDetail.getAvgPeriodUm()
            != null)
        {
            permitForm.setAvgPeriodUm(envPermitDetail.getAvgPeriodUm()
                                          .getCode());
            permitForm.setAvgPeriodDesc(envPermitDetail.getAvgPeriodUm()
                                            .getDescription());
        }
        if (envPermitDetail.getVocLimitUm()
            != null)
        {
            permitForm.setVocLimitUm(envPermitDetail.getVocLimitUm()
                                         .getCode());
            permitForm.setVocLimitDesc(envPermitDetail.getVocLimitUm()
                                           .getDescription());
        }
        if (envPermitDetail.getHapsLimitUm()
            != null)
        {
            permitForm.setHapsLimitUm(envPermitDetail.getHapsLimitUm()
                                          .getCode());
            permitForm.setHapsLimitDesc(envPermitDetail.getHapsLimitUm()
                                            .getDescription());
        }
        if (envPermitDetail.getMmbtuLimitUm()
            != null)
        {
            permitForm.setMmbtuLimitUm(envPermitDetail.getMmbtuLimitUm()
                                           .getCode());
            permitForm.setMmbtuLimitDesc(envPermitDetail.getMmbtuLimitUm()
                                             .getDescription());
        }
        setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                            envPermitDetail.getId(),
                            request);
        ArrayList
            pmtAssetList =
            new ArrayList();
        try
        {
            pmtAssetList =
                PermitDetailService.findPermittedAssets(envPermitDetail.getId());
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("permit.detail.asset.error.load.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("permit.detail.asset.error.load.failed"), request);
        }
        setSessionAttribute(SessionKeys.EV_PERMIT_ASSET_ACTIVE_LIST,
                            pmtAssetList,
                            request);
        setSessionAttribute(SessionKeys.EV_ASSET_ID,
                            null,
                            request);
        setSessionAttribute(SessionKeys.PERMIT_ASSET_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_ASSET_CREATE),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_ASSET_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_ASSET_DELETE),
                            request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_DETAIL_VIEW_MENU),
                        request);
    }
}
