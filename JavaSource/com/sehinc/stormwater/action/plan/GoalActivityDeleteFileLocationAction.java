package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class GoalActivityDeleteFileLocationAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityDeleteFileLocationAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        DynaActionForm
            aForm =
            (DynaActionForm) form;
        LOG.debug("In GoalActivityDeleteFileLocationAction");
        Integer
            aFileLocationId =
            (Integer) aForm.get("fileId");
        if (aFileLocationId
            == null
            || getGoalActivity(request)
               == null)
        {
            LOG.error(ApplicationResources.getProperty("goal.activity.file.missing.reference"));
            addError(new ActionMessage("goal.activity.file.missing.reference"), request);
        }
        else
        {
            GoalActivityFileLocationData
                aLocation =
                new GoalActivityFileLocationData();
            aLocation.setId(aFileLocationId.intValue());
            if (aLocation.load())
            {
                File
                    aFile =
                    new File(aLocation.getLocation()
                             + aLocation.getName());
                if (aFile.exists())
                {
                    aFile.delete();
                }
                aLocation.delete();
            }
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "a"
                                 + getGoalActivity(request).getId(),
                                 true);
    }
}
