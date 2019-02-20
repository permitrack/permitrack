package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.List;

public class InspectionListForm
    extends BaseValidatorForm
{
    private
    List
        inspectionList =
        new ArrayList();
    private
    String
        clientName =
        null;
    private
    Integer
        projectId =
        null;
    private
    String
        sortMethod =
        null;

    public List getInspectionList()
    {
        return inspectionList;
    }

    public void setInspectionList(List inspectionList)
    {
        this.inspectionList =
            inspectionList;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName =
            clientName;
    }

    public String getSortMethod()
    {
        return sortMethod;
    }

    public void setSortMethod(String sortMethod)
    {
        this.sortMethod =
            sortMethod;
    }

    public void reset()
    {
        this.inspectionList
            .clear();
        clientName =
            null;
        projectId =
            null;
        sortMethod =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
