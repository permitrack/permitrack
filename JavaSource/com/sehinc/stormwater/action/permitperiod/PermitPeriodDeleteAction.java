package com.sehinc.stormwater.action.permitperiod;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitPeriodDeleteAction
    extends PermitPeriodBaseAction
{
    public ActionForward permitPeriodAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserValue
            userValue =
            SessionService.getUserValue(request);
        PermitPeriodValue
            permitPeriodValue =
            getPermitPeriod(request);
        if (permitPeriodValue
            == null)
        {
            addError(new ActionMessage("error.permitperiod.not.exist.session"), request);
            return mapping.findForward("fail");
        }
        if (!PermitPeriodService.validateDeletePermitPeriod(permitPeriodValue))
        {
            addError(new ActionMessage("error.permitperiod.cannot.delete.permitperiod",
                                       permitPeriodValue.getName()), request);
            return mapping.findForward("fail");
        }
        PermitPeriodService.deletePermitPeriod(permitPeriodValue,
                                               userValue);
        removeSessionAttribute(SessionKeys.PERMIT_PERIOD,
                            request);
        response.setHeader("Set-Cookie",
                           "jstree_select=%23permitPeriodBranch"
                           + 0
                           + ";Path=/sehsvc/pt/ms4/periods");
        return mapping.findForward("continue");
    }
}