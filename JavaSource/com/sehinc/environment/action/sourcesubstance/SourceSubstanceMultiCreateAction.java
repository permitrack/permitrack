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
import java.math.BigDecimal;

public class SourceSubstanceMultiCreateAction
    extends SourceSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSubstanceMultiCreateAction.class);

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
        StringBuffer
            failedEntries =
            new StringBuffer("");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.substance.create.cancel.action"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            try
            {
                SourceSubstanceMultiForm
                    ssForm =
                    (SourceSubstanceMultiForm) form;
                Integer
                    sourceId =
                    ssForm.getSourceId();
                setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                    sourceId,
                                    request);
                Integer
                    srcTypeCode =
                    (Integer) getSessionAttribute(SessionKeys.EV_SRC_SUB_SRC_TYPE,
                                                  request);
                String[]
                    substances =
                    request.getParameterValues("substanceId");
                String[]
                    names =
                    request.getParameterValues("substanceName");
                String[]
                    ratios =
                    request.getParameterValues("ratio");
                String[]
                    natGasEmFactors =
                    request.getParameterValues("natGasEmFactor");
                String[]
                    natGasEfUnits =
                    request.getParameterValues("natGasEfUnit");
                String[]
                    bulkLiqEmFactors =
                    request.getParameterValues("bulkLiqEmFactor");
                String[]
                    bulkLiqEfUnits =
                    request.getParameterValues("bulkLiqEfUnit");
                if (substances.length
                    > 0)
                {
                    for (
                        int
                            i =
                            0; i
                               < substances.length; i++)
                    {
                        Integer
                            substanceId =
                            new Integer(substances[i]);
                        if (srcTypeCode
                            == 1)
                        {
                            if (ratios[i]
                                != null
                                && ratios[i]
                                   != "")
                            {
                                BigDecimal
                                    ratio;
                                try
                                {
                                    ratio =
                                        new BigDecimal(ratios[i]);
                                }
                                catch (NumberFormatException n)
                                {
                                    failedEntries.append(names[i])
                                        .append(", ");
                                    continue;
                                }
                                EnvSourceSubstance
                                    envPS =
                                    new EnvSourceSubstance();
                                envPS.setSourceId(sourceId);
                                envPS.setSubstanceId(substanceId);
                                envPS.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                                envPS.setRatio(ratio);
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
                        }
                        else if (srcTypeCode
                                 == 2)
                        {
                            if (natGasEmFactors[i]
                                != null
                                && natGasEmFactors[i]
                                   != "")
                            {
                                BigDecimal
                                    natGasEmFactor;
                                try
                                {
                                    natGasEmFactor =
                                        new BigDecimal(natGasEmFactors[i]);
                                }
                                catch (NumberFormatException n)
                                {
                                    failedEntries.append(names[i])
                                        .append(", ");
                                    continue;
                                }
                                Integer
                                    natGasEfUnit =
                                    new Integer(natGasEfUnits[i]);
                                if (natGasEfUnit
                                    == 0)
                                {
                                    failedEntries.append(names[i])
                                        .append(", ");
                                    continue;
                                }
                                EnvSourceSubstance
                                    envPS =
                                    new EnvSourceSubstance();
                                envPS.setSourceId(sourceId);
                                envPS.setSubstanceId(substanceId);
                                envPS.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                                envPS.setNatGasEmFactorString(natGasEmFactor.toString());
                                envPS.setNatGasEfUnitType(natGasEfUnit);
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
                        }
                        else if (srcTypeCode
                                 == 3)
                        {
                            if (bulkLiqEmFactors[i]
                                != null
                                && bulkLiqEmFactors[i]
                                   != "")
                            {
                                BigDecimal
                                    bulkLiqEmFactor;
                                try
                                {
                                    bulkLiqEmFactor =
                                        new BigDecimal(bulkLiqEmFactors[i]);
                                }
                                catch (NumberFormatException n)
                                {
                                    failedEntries.append(names[i])
                                        .append(", ");
                                    continue;
                                }
                                Integer
                                    bulkLiqEfUnit =
                                    new Integer(bulkLiqEfUnits[i]);
                                if (bulkLiqEfUnit
                                    == 0)
                                {
                                    failedEntries.append(names[i])
                                        .append(", ");
                                    continue;
                                }
                                EnvSourceSubstance
                                    envPS =
                                    new EnvSourceSubstance();
                                envPS.setSourceId(sourceId);
                                envPS.setSubstanceId(substanceId);
                                envPS.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                                envPS.setBulkLiqEmFactorString(bulkLiqEmFactor.toString());
                                envPS.setBulkLiqEfUnitType(bulkLiqEfUnit);
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
                        }
                        setSessionAttribute(SessionKeys.EV_SRC_SUB_SRC_TYPE,
                                            null,
                                            request);
                    }
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
        if (!failedEntries.toString()
            .equals(""))
        {
            String
                failedConnects =
                failedEntries.substring(0,
                                        failedEntries.length()
                                        - 2);
            Object[]
                parameters =
                {failedConnects};
            LOG.error(ApplicationResources.getProperty("source.substance.error.multi",
                                                       parameters));
            addError(new ActionMessage("source.substance.error.multi",
                                       parameters), request);
        }
        return mapping.findForward("continue");
    }
}