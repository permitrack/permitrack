package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.service.user.UserService;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventCreateProjectSearchPageAction
    extends EventBaseAction
{
    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        setPageSetup("Project Search",
                     "Select the criteria for finding projects to associate with this new event",
                     request);
        setSessionAttribute(SessionKeys.EC_STATE_LIST,
                            UserService.getStateValueList(),
                            request);
        return mapping.findForward("continue");
    }

/*
    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.EVENT_LIST_MENU_ITEM_NAME);
    }
*/

}
