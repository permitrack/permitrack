package com.sehinc.erosioncontrol.db.inspection;

import java.util.Date;

public class EcInspectionOverdueEmailLogEntry
{
    private
    Date
        sendDate;
    private
    Integer
        thresholdLevel;

    public Date getSendDate()
    {
        return sendDate;
    }

    public void setSendDate(Date sendDate)
    {
        this.sendDate =
            sendDate;
    }

    public int getThresholdLevel()
    {
        return thresholdLevel;
    }

    public void setThresholdLevel(Integer thresholdLevel)
    {
        this.thresholdLevel =
            thresholdLevel;
    }
}
