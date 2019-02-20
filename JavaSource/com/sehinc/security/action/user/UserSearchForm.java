package com.sehinc.security.action.user;

import com.sehinc.erosioncontrol.action.project.ProjectSearchListForm;

public class UserSearchForm
    extends ProjectSearchListForm
{
    private
    boolean
        isDefaultSearch;

    public void reset()
    {
        super.reset();
        isDefaultSearch =
            false;
    }

    public boolean getDefaultSearch()
    {
        return isDefaultSearch;
    }

    public void setDefaultSearch(boolean defaultSearch)
    {
        isDefaultSearch =
            defaultSearch;
    }
}