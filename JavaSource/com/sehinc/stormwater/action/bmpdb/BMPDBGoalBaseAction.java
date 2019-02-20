package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BMPDBGoalBaseAction
    extends BMPDBBaseAction
{
    protected abstract ActionForward bmpDbGoalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return bmpDbGoalAction(mapping,
                               form,
                               request,
                               response);
    }

    protected void setTreeMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.setTreeMenu(request, response);
        BMPDBGoalValue
            bmpDbGoalValue =
            getBMPDBGoal(request);
        if (bmpDbGoalValue
            != null)
        {
            response.setHeader("Set-Cookie",
                               "jstree_select=%23bmpDbGoal"
                               + bmpDbGoalValue.getId());
        }
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.BMPDB_VIEW_MENU_ITEM_NAME);
    }


    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        super.setTertiaryMenu(request);
        if (getBMPDBGoal(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.BMPDB_GOAL_MENU_NAME,
                                                     getBMPDBGoal(request).getId()),
                            request);
        }
    }

    protected BMPDBGoalValue getBMPDBGoal(HttpServletRequest request)
    {
        return (BMPDBGoalValue) getSessionAttribute(SessionKeys.BMP_DB_GOAL, request);
    }
}