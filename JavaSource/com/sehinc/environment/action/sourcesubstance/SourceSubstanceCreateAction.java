package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SourceSubstanceCreateAction
    extends SourceSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSubstanceCreateAction.class);

    public ActionForward sourceSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSubstanceCreateAction. ";
        strLog =
            strMod
            + "sourceSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.substance.create.cancel.action"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            try
            {
                SourceSubstanceForm
                    ssForm =
                    (SourceSubstanceForm) form;
                Integer
                    sourceId =
                    ssForm.getSourceId();
                setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                    sourceId,
                                    request);
                Integer
                    substanceId =
                    ssForm.getSubstanceId();
                if (substanceId
                    == 0)
                {
                    addMessage(new ActionMessage("source.substance.select"), request);
                    return mapping.getInputForward();
                }
                List
                    dupSub =
                    EnvSourceSubstance.findBySubstanceIdAndSourceId(substanceId,
                                                                    sourceId);
                if (dupSub.size()
                    > 0)
                {
                    addMessage(new ActionMessage("source.substance.duplicate.error"), request);
                    return mapping.findForward("source.view.page");
                }
                EnvSourceSubstance
                    envPS =
                    new EnvSourceSubstance();
                envPS.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                envPS.setSourceId(sourceId);
                envPS.setSubstanceId(substanceId);
                Integer
                    srcTypeCode =
                    (Integer) getSessionAttribute(SessionKeys.EV_SRC_SUB_SRC_TYPE,
                                                  request);
                String
                    inputValue;
                if (srcTypeCode
                    == 1)
                {
                    LOG.info("Getting ratio");
                    inputValue =
                        ssForm.getRatio();
                    if (inputValue
                        == null
                        || inputValue.length()
                           == 0)
                    {
                        LOG.error(ApplicationResources.getProperty("error.no.ratio.entered"));
                        addError(new ActionMessage("error.no.ratio.entered"), request);
                        return mapping.getInputForward();
                    }
                    else
                    {
                        LOG.info("Ratio was set as "
                                 + inputValue);
                        envPS.setRatioString(ssForm.getRatio());
                    }
                }
                else if (srcTypeCode
                         == 2)
                {
                    LOG.info("Getting natual gas emission factor");
                    inputValue =
                        ssForm.getNatGasEmFactor();
                    if (inputValue
                        == null
                        || inputValue.length()
                           == 0)
                    {
                        LOG.error(ApplicationResources.getProperty("error.no.emission.factor.entered"));
                        addError(new ActionMessage("error.no.emission.factor.entered"), request);
                        return mapping.getInputForward();
                    }
                    else
                    {
                        LOG.info("Natual gas emission factor was set as "
                                 + inputValue);
                        envPS.setNatGasEmFactorString(ssForm.getNatGasEmFactor());
                        if (ssForm.getNatGasEfUnit()
                            != null
                            & ssForm.getNatGasEfUnit()
                              != 0)
                        {
                            envPS.setNatGasEfUnitType(ssForm.getNatGasEfUnit());
                        }
                        else
                        {
                            LOG.error(ApplicationResources.getProperty("error.no.unit.selected"));
                            addError(new ActionMessage("error.no.unit.selected"), request);
                            return mapping.getInputForward();
                        }
                    }
                }
                else if (srcTypeCode
                         == 3)
                {
                    LOG.info("Getting bulk liquid emission factor");
                    inputValue =
                        ssForm.getBulkLiqEmFactor();
                    if (inputValue
                        == null
                        || inputValue.length()
                           == 0)
                    {
                        LOG.error(ApplicationResources.getProperty("error.no.emission.factor.entered"));
                        addError(new ActionMessage("error.no.emission.factor.entered"), request);
                        return mapping.getInputForward();
                    }
                    else
                    {
                        LOG.info("Bulk liquid emission factor was set as "
                                 + inputValue);
                        envPS.setBulkLiqEmFactorString(ssForm.getBulkLiqEmFactor());
                        if (ssForm.getBulkLiqEfUnit()
                            != null
                            & ssForm.getBulkLiqEfUnit()
                              != 0)
                        {
                            envPS.setBulkLiqEfUnitType(ssForm.getBulkLiqEfUnit());
                        }
                        else
                        {
                            LOG.error(ApplicationResources.getProperty("error.no.unit.selected"));
                            addError(new ActionMessage("error.no.unit.selected"), request);
                            return mapping.getInputForward();
                        }
                    }
                }
                else
                {
                    LOG.error(ApplicationResources.getProperty("error.src.type.not.found"));
                    addError(new ActionMessage("error.src.type.not.found"), request);
                    return mapping.getInputForward();
                }
                UserValue
                    objUser =
                    getUserValue(request);
                Integer
                    sourceSubstanceId =
                    envPS.save(objUser);
                LOG.debug(strLog
                          + "New Source Substance created with ID = "
                          + sourceSubstanceId.toString());
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