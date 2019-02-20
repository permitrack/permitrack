package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMEditAction
    extends MCMBaseAction
{
    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        MCMForm
            mcmForm =
            (MCMForm) form;
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue =
            getMCMValue(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        mcmData.retrieveByPrimaryKeys();
        mcmData.setName(mcmForm.getName());
        // Attempted for X-Editable for Indentation - didn't work, jasper strips div and/or style
        // mcmData.setRequiredDescription(mcmForm.getRequiredDescription().replaceAll("\\<blockquote\\>", "<div style='margin-left:50px;'>").replaceAll("\\<\\/blockquote\\>", "</div>"));
        mcmData.setRequiredDescription(mcmForm.getRequiredDescription());
        mcmData.setNecessaryDescription(mcmForm.getNecessaryDescription());
        mcmData.setNumber(mcmForm.getNumber());
        mcmData.setOwnerId(mcmForm.getOwnerId());
        mcmData.save(userValue);
        mcmValue.setName(mcmForm.getName());
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "m"
                                 + mcmData.getId(),
                                 true);
    }
}