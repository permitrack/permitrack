package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class PermitDetailEditAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailEditAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDetailEditAction. ";
        strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.detail.edit.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            PermitDetailForm
                permitDetailForm =
                (PermitDetailForm) form;
            Integer
                permitDetailId;
            LOG.debug("permitDetailId="
                      + permitDetailForm.getId());
            if (permitDetailForm.getId()
                != null)
            {
                permitDetailId =
                    permitDetailForm.getId();
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
                envPermitDetail.setName(permitDetailForm.getName());
                envPermitDetail.setDescription(permitDetailForm.getDescription());
                envPermitDetail.setAvgPeriod(permitDetailForm.getAvgPeriod());
                if (permitDetailForm.getAvgPeriod()
                    == 0)
                {
                    envPermitDetail.setAvgPeriodUm(null);
                }
                else
                {
                    envPermitDetail.setAvgPeriodUmType(permitDetailForm.getAvgPeriodUm());
                }
                String
                    emptyValue =
                    new String("0");
                String
                    vocLimit =
                    permitDetailForm.getVocLimit();
                BigDecimal
                    bdVocLimit;
                if (vocLimit
                    != null
                    && vocLimit.length()
                       > 0
                    && !vocLimit.equalsIgnoreCase(emptyValue))
                {
                    bdVocLimit =
                        new BigDecimal(vocLimit);
                    envPermitDetail.setVocLimitUmType(permitDetailForm.getVocLimitUm());
                }
                else
                {
                    bdVocLimit =
                        new BigDecimal("0");
                    envPermitDetail.setVocLimitUm(null);
                }
                envPermitDetail.setVocLimit(bdVocLimit);
                String
                    hapsLimit =
                    permitDetailForm.getHapsLimit();
                BigDecimal
                    bdHapsLimit;
                if (hapsLimit
                    != null
                    && hapsLimit.length()
                       > 0
                    && !hapsLimit.equalsIgnoreCase(emptyValue))
                {
                    bdHapsLimit =
                        new BigDecimal(hapsLimit);
                    envPermitDetail.setHapsLimitUmType(permitDetailForm.getHapsLimitUm());
                }
                else
                {
                    bdHapsLimit =
                        new BigDecimal("0");
                    envPermitDetail.setHapsLimitUm(null);
                }
                envPermitDetail.setHapsLimit(bdHapsLimit);
                String
                    mmbtuLimit =
                    permitDetailForm.getMmbtuLimit();
                BigDecimal
                    bdMmbtuLimit;
                if (mmbtuLimit
                    != null
                    && mmbtuLimit.length()
                       > 0
                    && !mmbtuLimit.equalsIgnoreCase(emptyValue))
                {
                    bdMmbtuLimit =
                        new BigDecimal(mmbtuLimit);
                    envPermitDetail.setMmbtuLimitUmType(permitDetailForm.getMmbtuLimitUm());
                }
                else
                {
                    bdMmbtuLimit =
                        new BigDecimal("0");
                    envPermitDetail.setMmbtuLimitUm(null);
                }
                envPermitDetail.setMmbtuLimit(bdMmbtuLimit);
                UserValue
                    userValue =
                    getUserValue(request);
                envPermitDetail.save(userValue);
                setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                    permitDetailId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitDetailId};
                LOG.error(ApplicationResources.getProperty("permit.detail.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.detail.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("permit.view.page");
            }
        }
        addMessage(new ActionMessage("permit.detail.edit.success"), request);
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
