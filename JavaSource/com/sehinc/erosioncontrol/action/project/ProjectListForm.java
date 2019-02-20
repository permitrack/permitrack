package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.ArrayList;
import java.util.List;

public class ProjectListForm
    extends BaseValidatorForm
{
    private
    List
        projectList =
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
    private
    Integer
        projectsPerPage =
        null;

    public List getProjectList()
    {
        return projectList;
    }

    public void setProjectList(List projectList)
    {
        this.projectList =
            projectList;
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

    public Integer getProjectsPerPage()
    {
        return projectsPerPage;
    }

    public void setProjectsPerPage(Integer projectsPerPage)
    {
        this.projectsPerPage =
            projectsPerPage;
    }

    public void reset()
    {
        this.projectList
            .clear();
        clientName =
            null;
        projectId =
            null;
        sortMethod =
            null;
        projectsPerPage =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (getProjectId()
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("error.project.not.selected"));
        }
    }
}
