package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class PermitDetailEditPageAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailEditPageAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDetailEditPageAction. ";
        strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        PermitDetailForm
            permitDetailForm =
            (PermitDetailForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_DETAIL_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("permit.detail.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.detail.update.page.not.allowed",
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
        String
            tableName =
            EnvUnitOfMeasureMap.PERMIT_DETAIL_TABLE;
        List
            avgPeriod_units =
            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                     tableName,
                                                     EnvUnitOfMeasureMap.PERM_DTL_AVG_PERIOD_COL);
        setSessionAttribute(SessionKeys.EV_AVG_PERIOD_LIST,
                            avgPeriod_units,
                            request);
        List
            vocLimit_units =
            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                     tableName,
                                                     EnvUnitOfMeasureMap.PERM_DTL_VOC_LIMIT_COL);
        setSessionAttribute(SessionKeys.EV_VOC_LIMIT_LIST,
                            vocLimit_units,
                            request);
        List
            hapsLimit_units =
            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                     tableName,
                                                     EnvUnitOfMeasureMap.PERM_DTL_HAPS_LIMIT_COL);
        setSessionAttribute(SessionKeys.EV_HAPS_LIMIT_LIST,
                            hapsLimit_units,
                            request);
        List
            mmbtuLimit_units =
            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                     tableName,
                                                     EnvUnitOfMeasureMap.PERM_DTL_MMBTU_LIMIT_COL);
        setSessionAttribute(SessionKeys.EV_MMBTU_LIMIT_LIST,
                            mmbtuLimit_units,
                            request);
        permitDetailForm.setId(envPermitDetail.getId());
        permitDetailForm.setName(envPermitDetail.getName());
        permitDetailForm.setDescription(envPermitDetail.getDescription());
        permitDetailForm.setAvgPeriod(envPermitDetail.getAvgPeriod());
        BigDecimal
            noValue =
            new BigDecimal("0");
        BigDecimal
            bdVocLimit =
            envPermitDetail.getVocLimit();
        if (bdVocLimit.compareTo(noValue)
            != 0)
        {
            permitDetailForm.setVocLimit(bdVocLimit.toString());
        }
        else
        {
            permitDetailForm.setVocLimit("0");
        }
        BigDecimal
            bdHapsLimit =
            envPermitDetail.getHapsLimit();
        if (bdHapsLimit.compareTo(noValue)
            != 0)
        {
            permitDetailForm.setHapsLimit(bdHapsLimit.toString());
        }
        else
        {
            permitDetailForm.setHapsLimit("0");
        }
        BigDecimal
            bdMmbtuLimit =
            envPermitDetail.getMmbtuLimit();
        if (bdMmbtuLimit.compareTo(noValue)
            != 0)
        {
            permitDetailForm.setMmbtuLimit(bdMmbtuLimit.toString());
        }
        else
        {
            permitDetailForm.setMmbtuLimit("0");
        }
        if (envPermitDetail.getAvgPeriodUm()
            != null)
        {
            permitDetailForm.setAvgPeriodUm(envPermitDetail.getAvgPeriodUm()
                                                .getCode());
            permitDetailForm.setAvgPeriodDesc(envPermitDetail.getAvgPeriodUm()
                                                  .getDescription());
        }
        if (envPermitDetail.getVocLimitUm()
            != null)
        {
            permitDetailForm.setVocLimitUm(envPermitDetail.getVocLimitUm()
                                               .getCode());
            permitDetailForm.setVocLimitDesc(envPermitDetail.getVocLimitUm()
                                                 .getDescription());
        }
        if (envPermitDetail.getHapsLimitUm()
            != null)
        {
            permitDetailForm.setHapsLimitUm(envPermitDetail.getHapsLimitUm()
                                                .getCode());
            permitDetailForm.setHapsLimitDesc(envPermitDetail.getHapsLimitUm()
                                                  .getDescription());
        }
        if (envPermitDetail.getMmbtuLimitUm()
            != null)
        {
            permitDetailForm.setMmbtuLimitUm(envPermitDetail.getMmbtuLimitUm()
                                                 .getCode());
            permitDetailForm.setMmbtuLimitDesc(envPermitDetail.getMmbtuLimitUm()
                                                   .getDescription());
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_DETAIL_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_DETAIL_EDIT_MENU_ITEM);
    }
}
