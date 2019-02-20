package com.sehinc.security.action.user;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.List;

public class UserSearchListForm
    extends BaseValidatorForm
{
    private
    List
        userSearchList =
        new ArrayList();

    public List getUserSearchList()
    {
        return userSearchList;
    }

    public void setUserSearchList(List userSearchList)
    {
        this.userSearchList =
            userSearchList;
    }

    public void reset()
    {
        this.userSearchList
            .clear();
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
