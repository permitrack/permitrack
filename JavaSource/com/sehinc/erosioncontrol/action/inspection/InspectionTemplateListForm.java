package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.List;

public class InspectionTemplateListForm
    extends BaseValidatorForm
{
    private
    List
        inspectionTemplateList =
        new ArrayList();

    public List getInspectionTemplateList()
    {
        return inspectionTemplateList;
    }

    public void setInspectionTemplateList(List inspectionTemplateList)
    {
        this.inspectionTemplateList =
            inspectionTemplateList;
    }

    public void reset()
    {
        this.inspectionTemplateList
            .clear();
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
