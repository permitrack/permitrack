package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.resources.ApplicationResources;
import com.sehinc.stormwater.server.bmpdb.BMPDBService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class MCMSelectBMPDBAction
    extends MCMBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMSelectBMPDBAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        DynaActionForm
            aForm =
            (DynaActionForm) form;
//        LOG.info("In MCMSelectBMPDBAction");
        Integer
            aBmpId =
            (Integer) aForm.get("bmp_db_id");
        if (aBmpId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("mcm.error.add.bmpdb.missing.parameter"));
            addError(new ActionMessage("mcm.error.add.bmpdb.missing.parameter"), request);
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        BMPDBData
            aBmpDbData =
            new BMPDBData();
        aBmpDbData.setId(aBmpId.intValue());
        if (!aBmpDbData.load())
        {
            LOG.warn(ApplicationResources.getProperty("mcm.error.add.bmpdb.not.found")
                     + "  ID = "
                     + aBmpId);
            addError(new ActionMessage("mcm.error.add.bmpdb.not.found"), request);
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            aMcmValue =
            getMCMValue(request);
        if (aMcmValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("mcm.error.not.found.in.session"));
            addError(new ActionMessage("mcm.error.not.found.in.session"), request);
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "p"
                                     + getPlanValue(request).getId(),
                                     true);
        }
        MCMData
            aMcmData =
            new MCMData();
        aMcmData.setId(aMcmValue.getId());
        if (!aMcmData.load())
        {
            LOG.warn(ApplicationResources.getProperty("mcm.error.load.failed")
                     + "  ID = "
                     + aBmpId);
            addError(new ActionMessage("mcm.error.load.failed"), request);
//            addError(new ActionMessage("mcm.error.load.failed"));
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "p"
                                     + getPlanValue(request).getId(),
                                     true);
        }
        BMPData
            aBmpData =
            new BMPData();
        aBmpData.setMcmId(aMcmValue.getId());
        aBmpData.setName(aBmpDbData.getName());
        aBmpData.setNumber(PlanService.getBMPCount(aMcmValue.getId())
                           + 1);
        aBmpData.setOwnerId(aMcmData.getOwnerId());
        aBmpData.setRequired(aBmpDbData.isRequired());
        aBmpData.setFormType(aBmpDbData.getFormType());
        aBmpData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        aBmpData.setField_value1(aBmpDbData.getFieldValue1());
        aBmpData.setField_type1(aBmpDbData.getFieldType1());
        aBmpData.setField_value2(aBmpDbData.getFieldValue2());
        aBmpData.setField_type2(aBmpDbData.getFieldType2());
        aBmpData.setField_value3(aBmpDbData.getFieldValue3());
        aBmpData.setField_type3(aBmpDbData.getFieldType3());
        aBmpData.setField_value4(aBmpDbData.getFieldValue4());
        aBmpData.setField_type4(aBmpDbData.getFieldType4());
        aBmpData.setField_value5(aBmpDbData.getFieldValue5());
        aBmpData.setField_type5(aBmpDbData.getFieldType5());
        try
        {
            aBmpData.insert(userValue);
        }
        catch (HibernateException he)
        {
            LOG.error(ApplicationResources.getProperty("mcm.error.bmpdb.insert.failed")
                      + "\n"
                      + he.getMessage());
            addError(new ActionMessage("mcm.error.bmpdb.insert.failed"), request);
//            addError(new ActionMessage("mcm.error.load.failed"));
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "p"
                                     + getPlanValue(request).getId(),
                                     true);
        }
        Iterator
            iter =
            BMPDBService.getGoals(new BMPDBValue(aBmpDbData))
                .iterator();
        BMPDBGoalData
            aBmpDbGoalData;
        while (iter.hasNext())
        {
            aBmpDbGoalData =
                (BMPDBGoalData) iter.next();
            GoalData
                aGoalData =
                new GoalData();
            aGoalData.setBmpId(aBmpData.getId());
            aGoalData.setDescription(aBmpDbGoalData.getDescription());
            aGoalData.setGoalActivityFormId(aBmpDbGoalData.getGoalActivityFormId());
            aGoalData.setGoalActivityFrequencyId(aBmpDbGoalData.getGoalActivityFrequencyId());
            aGoalData.setName(aBmpDbGoalData.getName());
            aGoalData.setNumber(aBmpDbGoalData.getNumber());
            aGoalData.setOwnerId(aBmpData.getOwnerId());
            aGoalData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            try
            {
                aGoalData.insert(userValue);
            }
            catch (HibernateException he)
            {
                LOG.error(ApplicationResources.getProperty("mcm.error.bmpdb.goal.insert.failed")
                          + "\n"
                          + he.getMessage());
                addError(new ActionMessage("mcm.error.bmpdb.goal.insert.failed",
                                           aBmpDbGoalData.getId()), request);
//                addError(new ActionMessage("mcm.error.load.failed"));
                return new ActionForward("/subnodeviewaction.do?"
                                         + UrlUtil.subNodeString
                                         + "="
                                         + "p"
                                         + getPlanValue(request).getId(),
                                         true);
            }
        }
        setSessionAttribute(SessionKeys.BMP,
                            new BMPValue(aBmpData), request);
        return mapping.findForward("continue");
    }
}
