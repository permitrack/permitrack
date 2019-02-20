package com.sehinc.stormwater.action.permitperiod;

import com.sehinc.common.util.MathUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitPeriodAction
    extends PermitPeriodBaseAction
{
    public ActionForward permitPeriodAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PermitPeriodForm
            permitPeriodForm =
            (PermitPeriodForm) form;
        permitPeriodForm.reset();
        Integer
            permitPeriodId =
            getPermitPeriodId(request);
        if (request.getParameter(RequestKeys.PERMIT_PERIOD_ID)
            == null)
        {
            if (request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM)
                .equals("view"))
            {
                ActionForward
                    f =
                    redirect(permitPeriodId,
                             "/permitperiodviewaction.do?"
                             + RequestKeys.PERMIT_PERIOD_ID
                             + "=", request);
                if (f
                    != null)
                {
                    return f;
                }
            }
        }
        PermitPeriodValue
            permitPeriodValue =
            null;
        if (permitPeriodId
            != null
            &&
            permitPeriodId
            > 0
            && !(request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM)
                     .equals("create")))
        {
            permitPeriodValue =
                PermitPeriodService.getPermitPeriodValue(permitPeriodId);
        }
        if (permitPeriodValue
            != null)
        {
            permitPeriodForm.setPermitPeriodName(permitPeriodValue.getName());
            permitPeriodForm.setPermitPeriodId(String.valueOf(permitPeriodValue.getId()));
            permitPeriodForm.setPermitTimePeriods(permitPeriodValue.getPermitTimePeriods());
            setSessionAttribute(SessionKeys.PERMIT_PERIOD,
                                permitPeriodValue, request);
        }
        else
        {
            removeSessionAttribute(SessionKeys.PERMIT_PERIOD,
                                request);
        }
        return mapping.findForward("continue");
    }

    @Override
    protected String getSavedNode(Integer id, HttpServletRequest request)
    {
        String
            savedNode;
        Cookie[]
            cookies =
            request.getCookies();
        for (Cookie cookie : cookies)
        {
            if (cookie.getName()
                    .equals("jstree_select")
                && cookie.getValue()
                   != null)
            {
                String
                    value =
                    cookie.getValue()
                        .replaceAll("%23",
                                    "");
                if (value.contains("permitPeriodLeaf")
                    && MathUtil.isInteger(value.substring("permitPeriodLeaf".length())))
                {
                    Integer
                        iii =
                        Integer.valueOf(value.substring("permitPeriodLeaf".length()));
                    if (iii
                        != null
                        && !iii.equals(id))
                    {
                        savedNode =
                            iii.toString();
                        return savedNode;
                    }
                }
            }
        }
        return null;
    }
}