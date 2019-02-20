package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class PermitDetailCreateAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailCreateAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDetailCreateAction. ";
        strLog =
            strMod
            + "permitetailAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.detail.create.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            try
            {
                PermitDetailForm
                    permitForm =
                    (PermitDetailForm) form;
                EnvPermitDetail
                    dupPermitD =
                    EnvPermitDetail.findByPermitIdAndName(permitForm.getPermitId(),
                                                          permitForm.getName());
                if (dupPermitD
                    != null)
                {
                    addMessage(new ActionMessage("permit.detail.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvPermitDetail
                        envDetail =
                        new EnvPermitDetail();
                    envDetail.setPermitId(permitForm.getPermitId());
                    envDetail.setName(permitForm.getName());
                    envDetail.setDescription(permitForm.getDescription());
                    envDetail.setAvgPeriod(permitForm.getAvgPeriod());
                    if (permitForm.getAvgPeriod()
                        == 0)
                    {
                        envDetail.setAvgPeriodUm(null);
                    }
                    else
                    {
                        envDetail.setAvgPeriodUmType(permitForm.getAvgPeriodUm());
                    }
                    String
                        emptyValue1 =
                        new String("0");
                    String
                        emptyValue2 =
                        new String("0.000000");
                    String
                        vocLimit =
                        permitForm.getVocLimit();
                    BigDecimal
                        bdVocLimit;
                    if (vocLimit
                        != null
                        && vocLimit.length()
                           > 0
                           & !vocLimit.equalsIgnoreCase(emptyValue1)
                           & !vocLimit.equalsIgnoreCase(emptyValue2))
                    {
                        bdVocLimit =
                            new BigDecimal(vocLimit);
                        envDetail.setVocLimitUmType(permitForm.getVocLimitUm());
                    }
                    else
                    {
                        bdVocLimit =
                            new BigDecimal("0");
                        envDetail.setVocLimitUm(null);
                    }
                    envDetail.setVocLimit(bdVocLimit);
                    String
                        hapsLimit =
                        permitForm.getHapsLimit();
                    BigDecimal
                        bdHapsLimit;
                    if (hapsLimit
                        != null
                        && hapsLimit.length()
                           > 0
                           & !hapsLimit.equalsIgnoreCase(emptyValue1)
                           & !hapsLimit.equalsIgnoreCase(emptyValue2))
                    {
                        bdHapsLimit =
                            new BigDecimal(hapsLimit);
                        envDetail.setHapsLimitUmType(permitForm.getHapsLimitUm());
                    }
                    else
                    {
                        bdHapsLimit =
                            new BigDecimal("0");
                        envDetail.setHapsLimitUm(null);
                    }
                    envDetail.setHapsLimit(bdHapsLimit);
                    String
                        mmbtuLimit =
                        permitForm.getMmbtuLimit();
                    BigDecimal
                        bdMmbtuLimit;
                    if (mmbtuLimit
                        != null
                        && mmbtuLimit.length()
                           > 0
                           & !mmbtuLimit.equalsIgnoreCase(emptyValue1)
                           & !mmbtuLimit.equalsIgnoreCase(emptyValue2))
                    {
                        bdMmbtuLimit =
                            new BigDecimal(mmbtuLimit);
                        envDetail.setMmbtuLimitUmType(permitForm.getMmbtuLimitUm());
                    }
                    else
                    {
                        bdMmbtuLimit =
                            new BigDecimal("0");
                        envDetail.setMmbtuLimitUm(null);
                    }
                    envDetail.setMmbtuLimit(bdMmbtuLimit);
                    envDetail.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        permitDetailId =
                        envDetail.save(objUser);
                    LOG.debug(strLog
                              + "New Permit Detail created with ID = "
                              + permitDetailId.toString());
                    setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                        permitDetailId,
                                        request);
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
        addMessage(new ActionMessage("permit.detail.create.success"), request);
        return mapping.findForward("continue");
    }
}
