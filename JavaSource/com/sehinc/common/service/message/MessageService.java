package com.sehinc.common.service.message;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.stormwater.server.message.NotificationService;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

public class MessageService
    extends Thread
{
    private static
    Logger
        LOG =
        Logger.getLogger(MessageService.class);
    private
    int
        wakeupInterval =
        600;
    private
    boolean
        killThread =
        false;
    private
    Date
        startDate =
        null;
    private
    Date
        stopDate =
        null;
    private
    Date
        lastRunDate =
        null;

    public MessageService()
    {
        synchronized (MessageService.class)
        {
        }
        log("Starting MessageService");
        log("Default sleep interval: "
            + wakeupInterval
            + " seconds");
        int
            paramWakeupInterval =
            Integer.parseInt(ApplicationProperties.getProperty("message.wakeup.interval"));
        if (paramWakeupInterval
            > 0)
        {
            log("Setting sleep intervals to: "
                + paramWakeupInterval
                + " seconds");
        }
        this.wakeupInterval =
            paramWakeupInterval;
        startDate =
            new java.util.Date();
    }

/*
    public void destroy()
    {
        log("Destroying thread");
        killThread =
            true;
        //super.destroy();
    }
*/

    public void kill()
    {
        log("Killing thread");
        stopDate =
            new java.util.Date();
        killThread =
            true;
    }

    public void run()
    {
        log("Starting run()");
        while (!killThread)
        {
            try
            {
                sleep(1000
                      * wakeupInterval);
                log("checking for message delivery window");
                if (isInWindow())
                {
                    NotificationService
                        notificationService =
                        new NotificationService();
                    notificationService.processNotifications();
                    lastRunDate =
                        new Date();
                }
            }
            catch (Exception e)
            {
                if (LOG
                    == null)
                {
                    LOG =
                        Logger.getLogger(MessageService.class);
                }
                LOG.error("Error encountered",
                          e);
                System.out
                    .println(e.getMessage());
            }
        }
        log("Ending run()");
    }

    private void log(String message)
    {
        if (LOG
            == null)
        {
            LOG =
                Logger.getLogger(MessageService.class);
        }
        LOG.debug(message
                  + " in message service thread:  "
                  + this);
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getStopDate()
    {
        return stopDate;
    }

    private boolean isInWindow()
    {
        Calendar
            today =
            Calendar.getInstance();
        today.setTime(new Date());
        if (today.get(Calendar.HOUR_OF_DAY)
            >= Integer.parseInt(ApplicationProperties.getProperty("message.hour.start"))
            && today.get(Calendar.HOUR_OF_DAY)
               <= Integer.parseInt(ApplicationProperties.getProperty("message.hour.end")))
        {
            if (lastRunDate
                != null)
            {
                Calendar
                    lastRun =
                    Calendar.getInstance();
                lastRun.setTime(lastRunDate);
                if (today.get(Calendar.DAY_OF_YEAR)
                    == lastRun.get(Calendar.DAY_OF_YEAR))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
