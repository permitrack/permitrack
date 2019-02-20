package com.sehinc.stormwater.action.plan;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

import java.util.Date;

public class GoalActivityForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityForm.class);
    private
    Integer
        id;
    private
    Integer
        goalId;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        ownerId;
    private
    String
        ownerName;
    private
    Integer
        submitterUserId;
    private
    Date
        activityDate;
    private
    FormFile
        aAttachFile1;
    private
    FormFile
        aAttachFile2;
    private
    FormFile
        aAttachFile3;

    public Integer getId()
    {
        return id;
    }

    public Integer getGoalId()
    {
        return goalId;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getViewDescription()
    {
        return viewString(description);
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public String getOwner()
    {
        return ownerName;
    }

    public Integer getSubmitterUserId()
    {
        return submitterUserId;
    }

    public Date getActivityDate()
    {
        return activityDate;
    }

    public String getActivityDateString()
    {
        return DateUtil.mdyDate(activityDate);
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setGoalId(Integer goalId)
    {
        this.goalId =
            goalId;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public void setActivityDate(Date activityDate)
    {
        this.activityDate =
            activityDate;
    }

    public void setActivityDateString(String activityDate)
    {
        this.activityDate =
            DateUtil.parseDate(activityDate);
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
    }

    public void setSubmitterUserId(Integer submitterUserId)
    {
        this.submitterUserId =
            submitterUserId;
    }

    public void setOwner(String ownerName)
    {
        this.ownerName =
            ownerName;
    }

    public void reset()
    {
        this.id =
            null;
        this.goalId =
            null;
        this.name =
            null;
        this.description =
            null;
        this.ownerId =
            null;
        this.submitterUserId =
            null;
        this.activityDate =
            null;
        this.ownerName =
            null;
        this.aAttachFile1 =
            null;
        this.aAttachFile2 =
            null;
        this.aAttachFile3 =
            null;
    }

    public FormFile getAttachFile1()
    {
        return aAttachFile1;
    }

    public FormFile getAttachFile2()
    {
        return aAttachFile2;
    }

    public FormFile getAttachFile3()
    {
        return aAttachFile3;
    }

    public void setAttachFile1(FormFile file)
    {
        aAttachFile1 =
            file;
    }

    public void setAttachFile2(FormFile file)
    {
        aAttachFile2 =
            file;
    }

    public void setAttachFile3(FormFile file)
    {
        aAttachFile3 =
            file;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
