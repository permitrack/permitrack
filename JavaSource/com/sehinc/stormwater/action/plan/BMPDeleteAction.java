package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.BMPDeleteReasonData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDeleteAction
    extends BMPBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDeleteAction.class);
*/

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            bmpDeleteForm =
            (PlanDeleteForm) form;
//        LOG.info("In BMPDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue =
            getBMP(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        bmpData.retrieveByPrimaryKeys();
        Integer
            mcmId =
            bmpData.getMcmId();
        bmpData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        bmpData.save(userValue);
        BMPDeleteReasonData
            bmpDeleteReasonData =
            new BMPDeleteReasonData();
        bmpDeleteReasonData.setBmpId(bmpValue.getId());
        bmpDeleteReasonData.setDeleteReason(bmpDeleteForm.getDeleteReason());
        bmpDeleteReasonData.save(userValue);
        removeSessionAttribute(SessionKeys.BMP,
                            request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmId);
        boolean
            foundMcmData =
            mcmData.retrieveByPrimaryKeys();
        if (foundMcmData)
        {
/*
            setPlanSessionKeys(SessionKeys.MCM,
                               mcmData.getId());
*/
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + mcmData.getId(),
                                     true);
        }
        else
        {
            return mapping.findForward("plan.list.action");
        }
    }
}