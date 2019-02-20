package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseActionForm;
import org.apache.log4j.Logger;

public class ProjectListItemForm
    extends BaseActionForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectListItemForm.class);
    // Fields
    private
    String[]
        ecProjectListItems;

    public ProjectListItemForm()
    {
    }

    public String[] getEcProjectListItems()
    {
        return this.ecProjectListItems;
    }

    public void setEcProjectListItems(String[] ecProjectListItems)
    {
        this.ecProjectListItems =
            ecProjectListItems;
    }

    public void reset()
    {
        this.ecProjectListItems =
            new String[] {};
    }
}