package com.sehinc.common.db.client;

public class ClientAdminSettingsData
{
    private
    boolean
        isProjectStatusEmailsEnabled;
    private
    boolean
        isSecondInspectionOverdueNotificationEnabled;
    private
    boolean
        isInspectionOverdueNotificationEnabled;
    private
    Integer
        inspectionOverdueInitialThreshold;
    private
    String
        inspectionOverdueInitialMessage;
    private
    Integer
        inspectionOverdueSecondThreshold;
    private
    String
        inspectionOverdueSecondMessage;
    private
    boolean
        isInspectionCertificationEnabled;
    private
    String
        inspectionCertificationMessage;

    public boolean isProjectStatusEmailsEnabled()
    {
        return isProjectStatusEmailsEnabled;
    }

    public void setProjectStatusEmailsEnabled(boolean projectStatusEmailsEnabled)
    {
        isProjectStatusEmailsEnabled =
            projectStatusEmailsEnabled;
    }

    public boolean isInspectionOverdueNotificationEnabled()
    {
        return isInspectionOverdueNotificationEnabled;
    }

    public void setInspectionOverdueNotificationEnabled(boolean inspectionOverdueNotificationEnabled)
    {
        isInspectionOverdueNotificationEnabled =
            inspectionOverdueNotificationEnabled;
    }

    public Integer getInspectionOverdueInitialThreshold()
    {
        return inspectionOverdueInitialThreshold;
    }

    public void setInspectionOverdueInitialThreshold(Integer inspectionOverdueInitialThreshold)
    {
        this.inspectionOverdueInitialThreshold =
            inspectionOverdueInitialThreshold;
    }

    public String getInspectionOverdueInitialMessage()
    {
        return inspectionOverdueInitialMessage;
    }

    public void setInspectionOverdueInitialMessage(String inspectionOverdueInitialMessage)
    {
        this.inspectionOverdueInitialMessage =
            inspectionOverdueInitialMessage;
    }

    public Integer getInspectionOverdueSecondThreshold()
    {
        return inspectionOverdueSecondThreshold;
    }

    public void setInspectionOverdueSecondThreshold(Integer inspectionOverdueSecondThreshold)
    {
        this.inspectionOverdueSecondThreshold =
            inspectionOverdueSecondThreshold;
    }

    public String getInspectionOverdueSecondMessage()
    {
        return inspectionOverdueSecondMessage;
    }

    public void setInspectionOverdueSecondMessage(String inspectionOverdueSecondMessage)
    {
        this.inspectionOverdueSecondMessage =
            inspectionOverdueSecondMessage;
    }

    public boolean isSecondInspectionOverdueNotificationEnabled()
    {
        return isSecondInspectionOverdueNotificationEnabled;
    }

    public void setSecondInspectionOverdueNotificationEnabled(boolean secondInspectionOverdueNotificationEnabled)
    {
        isSecondInspectionOverdueNotificationEnabled =
            secondInspectionOverdueNotificationEnabled;
    }

    public String getInspectionCertificationMessage()
    {
        return inspectionCertificationMessage;
    }

    public void setInspectionCertificationMessage(String inspectionCertificationMessage)
    {
        this.inspectionCertificationMessage =
            inspectionCertificationMessage;
    }

    public boolean isInspectionCertificationEnabled()
    {
        return isInspectionCertificationEnabled;
    }

    public void setInspectionCertificationEnabled(boolean inspectionCertificationEnabled)
    {
        isInspectionCertificationEnabled =
            inspectionCertificationEnabled;
    }
}
