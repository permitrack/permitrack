package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.MIMEType;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.server.plan.GoalService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GoalActivityFileDownloadAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityFileDownloadAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
/*
        ActionMessages
            messages =
            new ActionMessages();
*/
/*
        PropertyResourceBundle
            properties =
            (PropertyResourceBundle) ResourceBundle.getBundle("com.sehinc.stormwater.action.plan.PlanResources");
*/
        ClientValue
            clientValue =
            getClientValue(request);
        int
            goalActivityFileLocationId;
        if (request.getParameter(RequestKeys.GOAL_ACTIVITY_FILE_LOCATION_ID)
            != null)
        {
            goalActivityFileLocationId =
                Integer.parseInt(request.getParameter(RequestKeys.GOAL_ACTIVITY_FILE_LOCATION_ID));
        }
        else
        {
/*
            setSessionAttribute(SessionKeys.ACTION_MESSAGE,
                                new Exception(properties.getObject("goal.activity.file.missing.reference")
                                                  .toString()));
*/
            addError(new ActionMessage("goal.activity.file.missing.reference"), request);
            return mapping.findForward("fail");
        }
        GoalActivityFileLocationData
            goalActivityFileLocation =
            new GoalActivityFileLocationData();
        goalActivityFileLocation.setId(goalActivityFileLocationId);
        if (!goalActivityFileLocation.retrieveByPrimaryKeysAlternate())
        {
/*
            setSessionAttribute(SessionKeys.ACTION_MESSAGE,
                                new Exception(properties.getObject("goal.activity.file.no.record.found")
                                                  .toString()));
*/
            addError(new ActionMessage("goal.activity.file.no.record.found"), request);
            return mapping.findForward("fail");
        }
        if (!GoalService.isClientHasAccessToGoalActivity(clientValue.getId(),
                                                         goalActivityFileLocation.getGoalActivityId()))
        {
/*
            setSessionAttribute(SessionKeys.ACTION_MESSAGE,
                                new Exception(properties.getObject("goal.activity.file.client.not.authorized")
                                                  .toString()));
*/
            addError(new ActionMessage("goal.activity.file.client.not.authorized"), request);
            return mapping.findForward("fail");
        }
        File
            downloadFile =
            new File(goalActivityFileLocation.getLocation()
                     + goalActivityFileLocation.getName());
        LOG.debug("download file exists? "
                  + downloadFile.exists());
        if (!downloadFile.exists())
        {
/*
            LOG.debug("download file does not exist");
            setSessionAttribute(SessionKeys.ACTION_MESSAGE,
                                new Exception(properties.getObject("goal.activity.file.not.exist")
                                                  .toString()));
*/
            addError(new ActionMessage("goal.activity.file.not.exist"), request);
            return mapping.findForward("fail");
        }
        try
        {
            LOG.debug("calling downloadFile()");
            downloadFile(downloadFile,
                         response);
        }
        catch (IOException ioe)
        {
            LOG.error(ioe);
/*
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("goal.activity.file.io.exception",
                                           ioe.getMessage()));
            setSessionAttribute(SessionKeys.ACTION_MESSAGE,
                                messages);
*/
            addError(new ActionMessage("goal.activity.file.io.exception"), request);
            return mapping.findForward("fail");
        }
        return null;
    }

    private void downloadFile(File downloadFile, HttpServletResponse response)
        throws IOException
    {
        BufferedInputStream
            inputStream =
            new BufferedInputStream(new FileInputStream(downloadFile));
        response.setContentType(MIMEType.getMIMEType(downloadFile.getName()));
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + downloadFile.getName());
        ServletOutputStream
            ouputStream =
            response.getOutputStream();
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       inputStream.read(buffer,
                                        0,
                                        8192))
               != -1)
        {
            ouputStream.write(buffer,
                              0,
                              bytesRead);
        }
        ouputStream.flush();
        ouputStream.close();
        inputStream.close();
    }
}