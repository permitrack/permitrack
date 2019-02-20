package com.sehinc.environment.action.source;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
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

public class SourceEditAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceEditAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceEditAction. ";
        strLog =
            strMod
            + "sourceAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.edit.cancel.action"), request);
            return mapping.findForward("source.list.page");
        }
        else
        {
            SourceForm
                sourceForm =
                (SourceForm) form;
            Integer
                sourceId =
                sourceForm.getId();
            EnvSource
                sourceData =
                new EnvSource(sourceId);
            ClientValue
                clientValue =
                getClientValue(request);
            UserValue
                userValue =
                getUserValue(request);
            try
            {
                sourceData.load();
                if (!sourceData.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    throw new Exception("The requested Source ID = "
                                        + sourceId
                                        + " does not exist.");
                }
                if (checkForDuplicate(clientValue.getId(),
                                      sourceData,
                                      sourceForm.getDescription(),
                                      sourceForm.getPartNumber(),
                                      sourceForm.getBatchNumber()))
                {
                    addMessage(new ActionMessage("source.duplicate.error"), request);
                    return mapping.findForward("source.list.page");
                }
                else
                {
                    String
                        emptyValue1 =
                        new String("0");
                    String
                        emptyValue2 =
                        new String("0.000000");
                    sourceData.setPartNumber(sourceForm.getPartNumber());
                    sourceData.setBatchNumber(sourceForm.getBatchNumber());
                    sourceData.setClientId(clientValue.getId());
                    sourceData.setDescription(sourceForm.getDescription());
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
                    sourceData.setInfoOrigin(sourceForm.getInfoOrigin());
                    sourceData.setSourceType(sourceForm.getSourceTypeCd());
                    sourceData.setDisplayColor(sourceForm.getDisplayColorCd());
                    sourceData.setHcFuel(sourceForm.getHcFuel());
                    sourceData.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    StringBuffer
                        displayName =
                        new StringBuffer();
                    displayName.append(sourceForm.getDescription())
                        .append("_");
                    displayName.append(sourceForm.getPartNumber())
                        .append("_");
                    displayName.append(sourceForm.getBatchNumber());
                    sourceData.setDisplayName(displayName.toString());
                    sourceData.save(userValue);
                    setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                        sourceId,
                                        request);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sourceId};
                LOG.error(ApplicationResources.getProperty("source.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("source.list.page");
            }
        }
        addMessage(new ActionMessage("source.edit.success"), request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_VIEW_MENU),
                        request);
    }

    private boolean checkForDuplicate(Integer clientId, EnvSource source, String newDesc, String newPartNum, String newBatchNum)
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
                    if (source.getId()
                            .intValue()
                        != existing.getId()
                        .intValue())
                    {
                        flag =
                            true;
                    }
                }
            }
        }
        return flag;
    }
}