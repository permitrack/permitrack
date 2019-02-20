package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.List;

public class ProjectZoneListForm
    extends BaseValidatorForm
{
    private
    List
        projectZoneList =
        new ArrayList();

    public List getProjectZoneeList()
    {
        return projectZoneList;
    }

    public void setProjectZoneList(List projectZoneList)
    {
        this.projectZoneList =
            projectZoneList;
    }

    public void reset()
    {
        this.projectZoneList
            .clear();
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
