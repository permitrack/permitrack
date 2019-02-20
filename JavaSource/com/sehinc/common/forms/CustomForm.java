package com.sehinc.common.forms;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class CustomForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(CustomForm.class);
    private
    FileList
        fileList;
    private
    ElementList
        elementList;
    private
    IntList
        elementIndexes;
    private
    IntList
        fileIndexes;
    private
    IntList
        deleteList;
    private
    Integer
        formId;
    private
    Integer
        formInstanceId;
    private
    Integer
        clientId;
    private
    Integer
        planId;
    private
    Integer
        programId;
    private
    Integer
        goalActivityId;
    private
    Integer
        goalId;
    private
    Integer
        goalActivityFormId;
    private
    Integer
        activityNameElementId;
    private
    Integer
        activityDateElementId;
    private
    Integer
        activityDescElementId;
    private
    String
        activityName;
    private
    String
        activityDate;
    private
    String
        activityDesc;

    public void validateForm(ActionErrors errors)
    {
    }

    public void reset()
    {
        this.formId =
            null;
        this.formInstanceId =
            null;
        this.elementList =
            new ElementList();
        this.fileList =
            new FileList();
        this.deleteList =
            new IntList();
        this.elementIndexes =
            new IntList();
        this.fileIndexes =
            new IntList();
        this.clientId =
            null;
        this.planId =
            null;
        this.programId =
            null;
        this.goalActivityId =
            null;
        this.goalId =
            null;
        this.goalActivityFormId =
            null;
        this.activityNameElementId =
            null;
        this.activityName =
            null;
        this.activityDateElementId =
            null;
        this.activityDate =
            null;
        this.activityDescElementId =
            null;
        this.activityDesc =
            null;
    }

    public FileList getFileList()
    {
        return fileList;
    }

    public void setFileList(FileList fileList)
    {
        this.fileList =
            fileList;
    }

    public ElementList getElementList()
    {
        return elementList;
    }

    public void setElementList(ElementList elementList)
    {
        this.elementList =
            elementList;
    }

    public IntList getElementIndexes()
    {
        return elementIndexes;
    }

    public void setElementIndexes(IntList elementIndexes)
    {
        this.elementIndexes =
            elementIndexes;
    }

    public IntList getFileIndexes()
    {
        return fileIndexes;
    }

    public void setFileIndexes(IntList fileIndexes)
    {
        this.fileIndexes =
            fileIndexes;
    }

    public IntList getDeleteList()
    {
        return deleteList;
    }

    public void setDeleteList(IntList deleteList)
    {
        this.deleteList =
            deleteList;
    }

    public Integer getFormId()
    {
        return formId;
    }

    public void setFormId(Integer formId)
    {
        this.formId =
            formId;
    }

    public Integer getFormInstanceId()
    {
        return formInstanceId;
    }

    public void setFormInstanceId(Integer formInstanceId)
    {
        this.formInstanceId =
            formInstanceId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getPlanId()
    {
        return planId;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
    }

    public Integer getProgramId()
    {
        return programId;
    }

    public void setProgramId(Integer programId)
    {
        this.programId =
            programId;
    }

    public Integer getGoalActivityId()
    {
        return goalActivityId;
    }

    public void setGoalActivityId(Integer goalActivityId)
    {
        this.goalActivityId =
            goalActivityId;
    }

    public Integer getGoalId()
    {
        return goalId;
    }

    public void setGoalId(Integer goalId)
    {
        this.goalId =
            goalId;
    }

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public void setGoalActivityFormId(Integer goalActivityFormId)
    {
        this.goalActivityFormId =
            goalActivityFormId;
    }

    public Integer getActivityNameElementId()
    {
        return activityNameElementId;
    }

    public void setActivityNameElementId(Integer activityNameElementId)
    {
        this.activityNameElementId =
            activityNameElementId;
    }

    public Integer getActivityDateElementId()
    {
        return activityDateElementId;
    }

    public void setActivityDateElementId(Integer activityDateElementId)
    {
        this.activityDateElementId =
            activityDateElementId;
    }

    public Integer getActivityDescElementId()
    {
        return activityDescElementId;
    }

    public void setActivityDescElementId(Integer activityDescElementId)
    {
        this.activityDescElementId =
            activityDescElementId;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public void setActivityName(String activityName)
    {
        this.activityName =
            activityName;
    }

    public String getActivityDate()
    {
        return activityDate;
    }

    public void setActivityDate(String activityDate)
    {
        this.activityDate =
            activityDate;
    }

    public String getActivityDesc()
    {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc)
    {
        this.activityDesc =
            activityDesc;
    }
}
