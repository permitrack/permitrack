package com.sehinc.stormwater.action.template;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.portal.resources.PortalResources;
import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.resources.ApplicationResources;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class TemplateBaseAction
    extends BaseAction
{
    protected PlanValue getPlanValue(HttpServletRequest request)
    {
        PlanValue
            planValue =
            (PlanValue) getSessionAttribute(SessionKeys.PLAN_TEMPLATE,
                                            request);
        int
            id =
            getParamInt("id",
                        request);
        if (id
            > 0
            && (planValue
                == null
                || (planValue.getId()
                    != id)))
        {
            PlanData
                planData =
                new PlanData();
            planData.setId(id);
            planData.load();
            if (!planData.isTemplate())
            {
                addError(new ActionMessage("error.no.such.template",
                                           String.valueOf(id)),
                         request);
            }
            else
            {
                planValue =
                    new PlanValue(planData);
            }
        }
        return planValue;
    }

    protected PlanData getPlanTemplateData(HttpServletRequest request)
    {
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            != null)
        {
            PlanData
                planData =
                new PlanData();
            planData.setId(planValue.getId());
            planData.retrieveByPrimaryKeys();
            return planData;
        }
        return null;
    }

    protected void setPlanTemplateSessionKeys(String sessionKey, Integer id, HttpServletRequest request)
    {
        if (sessionKey.equals(SessionKeys.PLAN_TEMPLATE))
        {
            PlanData
                plan =
                PlanService.getPlan(id);
            setSessionAttribute(SessionKeys.PLAN_TEMPLATE,
                                new PlanValue(plan),
                                request);
        }
        else if (sessionKey.equals(SessionKeys.MCM_TEMPLATE))
        {
            MCMData
                mcm =
                PlanService.getMCM(id);
            setSessionAttribute(SessionKeys.MCM_TEMPLATE,
                                new MCMValue(mcm),
                                request);
            setPlanTemplateSessionKeys(SessionKeys.PLAN_TEMPLATE,
                                       mcm.getPlanId(),
                                       request);
        }
        else if (sessionKey.equals(SessionKeys.BMP_TEMPLATE))
        {
            BMPData
                bmp =
                PlanService.getBMP(id);
            setSessionAttribute(SessionKeys.BMP_TEMPLATE,
                                new BMPValue(bmp),
                                request);
            setPlanTemplateSessionKeys(SessionKeys.MCM_TEMPLATE,
                                       bmp.getMcmId(),
                                       request);
        }
        else if (sessionKey.equals(SessionKeys.GOAL_TEMPLATE))
        {
            GoalData
                goal =
                PlanService.getGoal(id);
            setSessionAttribute(SessionKeys.GOAL_TEMPLATE,
                                new GoalValue(goal),
                                request);
            setPlanTemplateSessionKeys(SessionKeys.BMP_TEMPLATE,
                                       goal.getBmpId(),
                                       request);
        }
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME,
                                    0);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_TEMPLATE_MENU_ITEM_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        /*
                PlanValue
                    planValue =
                    getPlanValue(request);
        */
        String
            selectedMenuItem =
            (String) request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM);
        SecondaryMenu
            secondaryMenu =
            //            planValue
            //            == null
            //                ?
            SecondaryMenu.getInstance(SecondaryMenu.DEFAULT_TEMPLATE_MENU_NAME,
                                      0);
        /*
                        : SecondaryMenu.getInstance(SecondaryMenu.TEMPLATE_MENU_NAME,
                                                    planValue.getId());
        */
        setSelectedMenuItem(secondaryMenu,
                            selectedMenuItem);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    @Override
    public void finalizeAction(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.finalizeAction(request,
                             response);
        setTreeMenu(request,
                    response);
    }

    protected void setSelectedMenuItem(SecondaryMenu secondaryMenu, String selectedMenuItem)
    {
        try
        {
            if (selectedMenuItem
                == null
                || selectedMenuItem.equals("list"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.TEMPLATE_SELECT_MENU_ITEM_NAME);
            }
            else
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.TEMPLATE_VIEW_MENU_ITEM_NAME);
            }
        }
        catch (Exception e)
        {
            LOG.debug(e.getMessage());
        }
    }

    protected ActionForward handleError(ActionMapping mapping, String messageKey, HttpServletRequest request, Object... params)
    {
        String
            message =
            ApplicationResources.getProperty(messageKey,
                                             params);
        if (message
            == null
            || message.isEmpty())
        {
            message =
                PortalResources.getProperty(messageKey,
                                            params);
        }
        LOG.error(message);
        addError(new ActionMessage(messageKey,
                                   params),
                 request);
        return mapping.findForward("plan.template.list.action");
    }
}
