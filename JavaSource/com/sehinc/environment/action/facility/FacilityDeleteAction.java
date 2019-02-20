package com.sehinc.environment.action.facility;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.db.permitfacility.EnvPermitFacility;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityDeleteAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityDeleteAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "FacilityDeleteAction. ";
        String
            strLog =
            strMod
            + "facilityAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("facility.delete.cancel.action"), request);
            return mapping.findForward("facility.list.page");
        }
        else
        {
            FacilityForm
                facilityForm =
                (FacilityForm) form;
            UserValue
                userValue =
                getUserValue(request);
            Integer
                facilityId =
                facilityForm.getId();
            List<EnvPermitFacility>
                permitFacilities =
                EnvPermitFacility.findByFacilityId(facilityId);
            try
            {
                for (EnvPermitFacility permFac : permitFacilities)
                {
                    permFac.delete();
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {facilityId};
                LOG.error(ApplicationResources.getProperty("permit.facility.delete.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.facility.delete.failed",
                                           parameters), request);
                return mapping.findForward("facility.list.page");
            }
            try
            {
                List<EnvFacilityAsset>
                    facilityAssets =
                    EnvFacilityAsset.findByFacilityId(facilityId);
                for (Object o : facilityAssets)
                {
                    EnvFacilityAsset
                        fa =
                        (EnvFacilityAsset) o;
                    EnvAsset
                        asset =
                        new EnvAsset(fa.getAssetId());
                    asset.load();
                    asset.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                    asset.save(userValue);
                }
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                return mapping.findForward("facility.list.page");
            }
            EnvFacility
                envFacility =
                new EnvFacility(facilityId);
            try
            {
                envFacility.load();
                envFacility.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envFacility.save(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {facilityId};
                LOG.error(ApplicationResources.getProperty("facility.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("facility.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("facility.list.page");
            }
            addMessage(new ActionMessage("facility.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}