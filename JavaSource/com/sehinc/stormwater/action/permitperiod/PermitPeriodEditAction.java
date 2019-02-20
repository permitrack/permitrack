package com.sehinc.stormwater.action.permitperiod;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.permitperiod.PermitPeriodData;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitPeriodEditAction
    extends PermitPeriodBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitPeriodEditAction.class);

    public ActionForward permitPeriodAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String
            action =
            request.getParameter("addTimePeriod");
        if (action
            != null
            && action.length()
               > 0)
        {
            return addPermitTimePeriod(mapping,
                                       form, request);
        }
        action =
            request.getParameter("deleteTimePeriod");
        if (action
            != null
            && action.length()
               > 0)
        {
            return deletePermitTimePeriod(mapping,
                                          form,
                                          request);
        }
        action =
            request.getParameter("finish");
        if (action
            != null
            && action.length()
               > 0)
        {
            return savePermitPeriod(mapping,
                                    form,
                                    request,
                                    response);
        }
        return mapping.findForward("continue");
    }

    public ActionForward addPermitTimePeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request)
    {
        PermitPeriodForm
            permitPeriodForm =
            (PermitPeriodForm) form;
        LOG.info("In addPermitTimePeriod");
        UserValue
            userValue =
            getUserValue(request);
        PermitPeriodValue
            permitPeriodValue;
        if (permitPeriodForm.getPermitPeriodId()
            == null
            || permitPeriodForm.getPermitPeriodId()
                   .length()
               == 0)
        {
            permitPeriodValue =
                new PermitPeriodValue();
        }
        else
        {
            permitPeriodValue =
                getPermitPeriod(request);
            if (permitPeriodValue.getId()
                    .intValue()
                != Integer.parseInt(permitPeriodForm.getPermitPeriodId()))
            {
                LOG.debug("error adding to permitPeriod id: "
                          + permitPeriodValue.getId());
                addError(new ActionMessage("error.permitperiod.wrong.value.session"), request);
                return mapping.findForward("continue");
            }
        }
        permitPeriodForm.setPermitTimePeriods(permitPeriodValue.getPermitTimePeriods());
        permitPeriodValue.setName(permitPeriodForm.getPermitPeriodName());
        PermitTimePeriodValue
            permitTimePeriodValue =
            new PermitTimePeriodValue();
        permitTimePeriodValue.setName(permitPeriodForm.getTimePeriodName());
        permitTimePeriodValue.setStartDate(permitPeriodForm.getStartDate());
        permitTimePeriodValue.setEndDate(permitPeriodForm.getEndDate());
        permitTimePeriodValue.setPermitPeriodId(permitPeriodValue.getId());
        if (!PermitPeriodService.validatePermitTimePeriod(permitTimePeriodValue))
        {
            LOG.debug("error validating permitTimePeriod");
            addError(new ActionMessage("error.permitperiod.timeperiod.invalid"), request);
            return mapping.findForward("continue");
        }
        Integer
            permitPeriodId =
            PermitPeriodService.addPermitTimePeriod(permitPeriodValue,
                                                    permitTimePeriodValue,
                                                    userValue);
        setSessionAttribute(SessionKeys.PERMIT_PERIOD,
                            PermitPeriodService.getPermitPeriodValue(permitPeriodId), request);
        permitPeriodForm.reset();
        permitPeriodForm.setPermitTimePeriods(getPermitPeriod(request).getPermitTimePeriods());
        permitPeriodForm.setPermitPeriodName(getPermitPeriod(request).getName());
        /*
                if (request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM)
                    .equals("create"))
                {
                    return mapping.findForward("new");
                }
                else
                {
        */
        return mapping.findForward("continue");
        //        }
    }

    public ActionForward deletePermitTimePeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request)
    {
        PermitPeriodForm
            permitPeriodForm =
            (PermitPeriodForm) form;
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
            return mapping.findForward("continue");
        }
        int
            deletePermitTimePeriodId =
            getParamInt("deleteTimePeriod",
                        request);
        PermitTimePeriodValue
            permitTimePeriodValue =
            permitPeriodValue.getPermitTimePeriod(deletePermitTimePeriodId);
        if (permitTimePeriodValue
            == null)
        {
            LOG.debug("permitTimePeriodValue = null");
        }
        else
        {
            if (!PermitPeriodService.validateDeletePermitTimePeriod(permitTimePeriodValue))
            {
                LOG.debug("We cannot delete this time period");
                addError(new ActionMessage("error.permitperiod.cannot.delete.timeperiod",
                                           permitTimePeriodValue.getName()), request);
                return mapping.findForward("continue");
            }
            PermitPeriodService.deletePermitTimePeriod(permitTimePeriodValue,
                                                       userValue);
            setSessionAttribute(SessionKeys.PERMIT_PERIOD,
                                PermitPeriodService.getPermitPeriodValue(permitPeriodValue.getId()), request);
            permitPeriodForm.reset();
            permitPeriodForm.setPermitTimePeriods(getPermitPeriod(request).getPermitTimePeriods());
            permitPeriodForm.setPermitPeriodName(getPermitPeriod(request).getName());
        }
        return mapping.findForward("continue");
    }

    public ActionForward savePermitPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PermitPeriodForm
            permitPeriodForm =
            (PermitPeriodForm) form;
        LOG.info("In savePermitPeriod");
        UserValue
            userValue =
            SessionService.getUserValue(request);
        PermitPeriodValue
            permitPeriodValue;
        if (permitPeriodForm.getPermitPeriodId()
            == null
            ||
            permitPeriodForm.getPermitPeriodId()
                .length()
            == 0
            ||
            Integer.parseInt(permitPeriodForm.getPermitPeriodId())
            == 0)
        {
            permitPeriodValue =
                new PermitPeriodValue();
        }
        else
        {
            permitPeriodValue =
                PermitPeriodService.getPermitPeriodValue(new Integer(permitPeriodForm.getPermitPeriodId()));
            if (permitPeriodValue
                == null)
            {
                addError(new ActionMessage("error.permitperiod.not.exist"), request);
                return mapping.findForward("continue");
            }
            else
            {
                permitPeriodForm.setPermitTimePeriods(permitPeriodValue.getPermitTimePeriods());
            }
        }
        permitPeriodValue.setName(permitPeriodForm.getPermitPeriodName());
        if (!PermitPeriodService.validatePermitPeriod(permitPeriodValue))
        {
            addError(new ActionMessage("error.permitperiod.invalid.name"), request);
            return mapping.findForward("continue");
        }
        Integer
            permitPeriodId =
            null;
        if (permitPeriodValue.getId()
            == null)
        {
            PermitPeriodData
                permitPeriodData =
                new PermitPeriodData();
            permitPeriodData.setName(permitPeriodValue.getName());
            permitPeriodData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            permitPeriodId =
                permitPeriodData.insert(userValue);
            response.setHeader("Set-Cookie",
                               "jstree_select=%23permitPeriodLeaf"
                               + permitPeriodId
                               + ";Path=/sehsvc/pt/ms4/periods");
        }
        else
        {
            PermitPeriodData
                permitPeriodData =
                PermitPeriodService.getPermitPeriod(permitPeriodValue.getId());
            if (permitPeriodData
                != null)
            {
                permitPeriodData.setName(permitPeriodValue.getName());
                permitPeriodData.update(userValue);
                permitPeriodId =
                    permitPeriodData.getId();
            }
            else
            {
                addError(new ActionMessage("error.permitperiod.not.exist"), request);
            }
        }
        setSessionAttribute(SessionKeys.PERMIT_PERIOD,
                            PermitPeriodService.getPermitPeriodValue(permitPeriodId), request);
        return mapping.findForward("continue");
    }
}