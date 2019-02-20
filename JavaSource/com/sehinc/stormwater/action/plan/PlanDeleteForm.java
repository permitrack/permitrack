package com.sehinc.stormwater.action.plan;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class PlanDeleteForm
    extends BaseValidatorForm
{

    private
    int
        id;
    private
    int
        planId;
    private
    String
        deleteReason;

    public int getId()
    {
        return id;
    }

    public int getPlanId()
    {
        return planId;
    }

    public String getDeleteReason()
    {
        return deleteReason;
    }

    public void setId(int id)
    {
        this.id =
            id;
    }

    public void setPlanId(int planId)
    {
        this.planId =
            planId;
    }

    public void setDeleteReason(String deleteReason)
    {
        this.deleteReason =
            deleteReason;
    }

    public void reset()
    {
        this.id =
            0;
        this.planId =
            0;
        this.deleteReason =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (deleteReason
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Delete Reason"));
        }
    }
}
