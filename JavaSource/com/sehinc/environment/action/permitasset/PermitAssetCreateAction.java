package com.sehinc.environment.action.permitasset;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitAssetCreateAction
    extends PermitAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitAssetCreateAction.class);

    public ActionForward permitAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitAssetCreateAction. ";
        strLog =
            strMod
            + "permitAssetAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.asset.create.cancel.action"), request);
            return mapping.findForward("permit.detail.view.page");
        }
        else
        {
            try
            {
                PermitAssetForm
                    paForm =
                    (PermitAssetForm) form;
                Integer
                    permitDetailId =
                    paForm.getPermitDetailId();
                setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                                    permitDetailId,
                                    request);
                EnvPermitDetail
                    permitDetail =
                    new EnvPermitDetail(permitDetailId);
                if (permitDetail.load())
                {
                    paForm.setPermitDetailName(permitDetail.getName());
                    paForm.setPermitDetailDesc(permitDetail.getDescription());
                }
                if (paForm.getAssetId()
                    == 0)
                {
                    addMessage(new ActionMessage("permit.asset.create.select"), request);
                    return mapping.getInputForward();
                }
                EnvPermitAsset
                    dupPA =
                    EnvPermitAsset.findByPermitDetailAndAssetId(paForm.getPermitDetailId(),
                                                                paForm.getAssetId());
                if (dupPA
                    != null)
                {
                    addMessage(new ActionMessage("permit.asset.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    UserValue
                        objUser =
                        getUserValue(request);
                    EnvPermitAsset
                        pAsset =
                        new EnvPermitAsset();
                    pAsset.setPermitDetailId(paForm.getPermitDetailId());
                    pAsset.setAssetId(paForm.getAssetId());
                    Integer
                        permitAssetId =
                        pAsset.save(objUser);
                    LOG.debug(strLog
                              + "Permit Asset saved with ASSET_ID = "
                              + permitAssetId.toString());
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
        return mapping.findForward("continue");
    }
}