package com.sehinc.environment.action.source;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SourceCreateAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceCreateAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SourceCreateAction. ";
        String
            strLog =
            strMod
            + "sourceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.create.cancel.action"), request);
            return mapping.findForward("source.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    clientValue =
                    getClientValue(request);
                SourceForm
                    sourceForm =
                    (SourceForm) form;
                if (checkForDuplicate(clientValue.getId(),
                                      sourceForm.getDescription(),
                                      sourceForm.getPartNumber(),
                                      sourceForm.getBatchNumber()))
                {
                    addMessage(new ActionMessage("source.duplicate.error"), request);
                    return mapping.findForward("source.list.page");
                }
                else
                {
                    EnvSource
                        sourceData =
                        new EnvSource();
                    sourceData.setPartNumber(sourceForm.getPartNumber());
                    sourceData.setBatchNumber(sourceForm.getBatchNumber());
                    sourceData.setClientId(clientValue.getId());
                    sourceData.setDescription(sourceForm.getDescription());
                    sourceData.setInfoOrigin(sourceForm.getInfoOrigin());
                    sourceData.setSourceType(sourceForm.getSourceTypeCd());
                    sourceData.setDisplayColor(sourceForm.getDisplayColorCd());
                    sourceData.setHcFuel(sourceForm.getHcFuel());
                    sourceData.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    String
                        emptyValue1 =
                        new String("0");
                    String
                        emptyValue2 =
                        new String("0.000000");
                    String
                        lbsVoc =
                        sourceForm.getLbsVoc();
                    sourceData.setLbsVocString(lbsVoc);
                    if (!lbsVoc.equalsIgnoreCase(emptyValue1)
                        & !lbsVoc.equalsIgnoreCase(emptyValue2)
                        & !lbsVoc.equalsIgnoreCase(""))
                    {
                        sourceData.setLbsVocUmType(sourceForm.getLbsVocUm());
                    }
                    else
                    {
                        sourceData.setLbsVocUm(null);
                    }
                    String
                        density =
                        sourceForm.getDensity();
                    sourceData.setDensityString(density);
                    if (!density.equalsIgnoreCase(emptyValue1)
                        & !density.equalsIgnoreCase(emptyValue2)
                        & !density.equalsIgnoreCase(""))
                    {
                        sourceData.setDensityUmType(sourceForm.getDensityUm());
                    }
                    else
                    {
                        sourceData.setDensityUm(null);
                    }
                    String
                        lbsHaps =
                        sourceForm.getLbsHaps();
                    sourceData.setLbsHapsString(lbsHaps);
                    if (!lbsHaps.equalsIgnoreCase(emptyValue1)
                        & !lbsHaps.equalsIgnoreCase(emptyValue2)
                        & !lbsHaps.equalsIgnoreCase(""))
                    {
                        sourceData.setLbsHapsUmType(sourceForm.getLbsHapsUm());
                    }
                    else
                    {
                        sourceData.setLbsHapsUm(null);
                    }
                    sourceData.setPctSolidsWeightString(sourceForm.getPctSolidsWeight());
                    sourceData.setPctSolidsVolumeString(sourceForm.getPctSolidsVolume());
                    StringBuffer
                        displayName =
                        new StringBuffer();
                    displayName.append(sourceForm.getDescription())
                        .append("_");
                    displayName.append(sourceForm.getPartNumber())
                        .append("_");
                    displayName.append(sourceForm.getBatchNumber());
                    sourceData.setDisplayName(displayName.toString());
                    Integer
                        intSourceId =
                        sourceData.save(objUser);
                    setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                        intSourceId,
                                        request);
                    LOG.debug(strLog
                              + "New Source created with ID = "
                              + intSourceId.toString());
                    setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                        intSourceId,
                                        request);
                }
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("source.error.create.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.error.create.failed"), request);
                return mapping.findForward("source.list.page");
            }
        }
        addMessage(new ActionMessage("source.create.success"), request);
        return mapping.findForward("continue");
    }

    private boolean checkForDuplicate(Integer clientId, String newDesc, String newPartNum, String newBatchNum)
    {
        boolean
            flag =
            false;
        List
            existingSources =
            EnvSource.findDuplicateSources(clientId,
                                           newDesc,
                                           newPartNum);
        for (Object o : existingSources)
        {
            EnvSource
                existing =
                (EnvSource) o;
            if (existing.getBatchNumber()
                != null)
            {
                if (newBatchNum.equals(existing.getBatchNumber()))
                {
                    flag =
                        true;
                }
            }
        }
        return flag;
    }
}