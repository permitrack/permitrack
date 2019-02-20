package com.sehinc.stormwater.action.plan;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.portal.PortalUtils;
import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.db.plan.*;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class ClientBaseAction
    extends BaseAction
{
    private
    Integer
        alreadyChecked =
        0;

    protected abstract ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return clientAction(mapping,
                            form,
                            request,
                            response);
    }

    protected MCMValue getMCMValue(HttpServletRequest request)
    {
        return (MCMValue) getSessionAttribute(SessionKeys.MCM, request);
    }

    protected BMPValue getBMP(HttpServletRequest request)
    {
        return (BMPValue) getSessionAttribute(SessionKeys.BMP, request);
    }

    protected GoalValue getGoal(HttpServletRequest request)
    {
        GoalValue
            goalValue =
            (GoalValue) getSessionAttribute(SessionKeys.GOAL, request);
        if (goalValue
            == null)
        {
            int
                id =
                getParamInt("id",
                            request);
            if (id
                > 0)
            {
                GoalData
                    planData =
                    new GoalData();
                planData.setId(id);
                if (planData.retrieveByPrimaryKeys())
                {
                    goalValue =
                        new GoalValue(planData);
                    request.setAttribute(SessionKeys.GOAL,
                                         goalValue);
                }
            }
        }
        return goalValue;
    }

    protected ClientValue getClientValue(HttpServletRequest request)
    {
        int
            id;
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
        if (clientValue
            == null)
        {
            id =
                getParamInt("id",
                            request);
        }
        else
        {
            id =
                clientValue.getId();
            if (alreadyChecked.equals(id))
            {
                return clientValue;
            }
        }

        if (id
            < 1)
        {
            clientValue =
                getClientValueDefault(CommonConstants.STORM_WATER_MODULE, request);
/*
            if (clientValue
                != null)
            {
                id =
                    clientValue.getId();
            }
*/
        }
        else
        {
            ClientData
                clientData =
                ClientService.getActiveClientById(id);
            if (clientData != null && PortalUtils.userHasClient(getUserValue(request),
                                          clientData))
            {
                clientValue =
                    new ClientValue(clientData);
            }
            else
            {
                clientValue = null;
            }

        }

        if (clientValue == null)
        {
//            addError(new ActionMessage("error.client.not.exist"), request);
            return null;
        }
        else
        {
/*
            if (clientValue
                == null)
            {
*/
//                addError(new ActionMessage("error.invalid.client"));
//                return null;
//            }
            alreadyChecked =
                clientValue.getId();
            SessionService.setClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE,
                                          clientValue);
            return SessionService.getClientValue(request,
                                                 CommonConstants.STORM_WATER_MODULE);
        }
    }

    protected Boolean setPlanSessionKeys(String sessionKey, Integer id, HttpServletRequest request)
    {
        if (sessionKey.equals(SessionKeys.MS4_CLIENT))
        {
            ClientData
                clientData =
                ClientService.getActiveClientById(id);
            SessionService.setClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE,
                                          new ClientValue(clientData));
            return getClientValue(request)
                   != null;
        }
        else if (sessionKey.equals(SessionKeys.PLAN))
        {
            PlanData
                plan =
                PlanService.getPlan(id);
            setSessionAttribute(SessionKeys.PLAN,
                                new PlanValue(plan), request);
            return setPlanSessionKeys(SessionKeys.MS4_CLIENT,
                                      plan.getClientId(), request);
        }
        else if (sessionKey.equals(SessionKeys.MCM))
        {
            MCMData
                mcm =
                PlanService.getMCM(id);
            setSessionAttribute(SessionKeys.MCM,
                                new MCMValue(mcm), request);
            return setPlanSessionKeys(SessionKeys.PLAN,
                                      mcm.getPlanId(), request);
        }
        else if (sessionKey.equals(SessionKeys.BMP))
        {
            BMPData
                bmp =
                PlanService.getBMP(id);
            setSessionAttribute(SessionKeys.BMP,
                                new BMPValue(bmp), request);
            return setPlanSessionKeys(SessionKeys.MCM,
                                      bmp.getMcmId(), request);
        }
        else if (sessionKey.equals(SessionKeys.GOAL))
        {
            GoalData
                goal =
                PlanService.getGoal(id);
            setSessionAttribute(SessionKeys.GOAL,
                                new GoalValue(goal), request);
            return setPlanSessionKeys(SessionKeys.BMP,
                                      goal.getBmpId(), request);
        }
        else if (sessionKey.equals(SessionKeys.GOAL_ACTIVITY))
        {
            GoalActivityData
                goalActivity =
                PlanService.getGoalActivity(id);
            setSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                                new GoalActivityValue(goalActivity), request);
            return setPlanSessionKeys(SessionKeys.GOAL,
                                      goalActivity.getGoalId(), request);
        }
        return false;
    }

    protected PlanData getPlanData(HttpServletRequest request)
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
            if (planData.retrieveByPrimaryKeys())
            {
                return planData;
            }
        }
        return null;
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PLAN_MENU_ITEM_NAME);
    }
}
