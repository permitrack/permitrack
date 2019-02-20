package com.sehinc.portal.action.auth;

import com.sehinc.common.action.base.BaseActionForm;
import org.apache.struts.action.ActionErrors;

public class LoginForm
    extends BaseActionForm
{
    private
    String
        userid;
    private
    String
        password;
    private
    String
        redirect;
    private
    String
        rememberme;

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid =
            userid;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password =
            password;
    }

    public void reset()
    {
        this.userid =
            null;
        this.password =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect =
            redirect;
    }

    public String getRememberme()
    {
        return rememberme;
    }

    public void setRememberme(String rememberme)
    {
        this.rememberme =
            rememberme;
    }
}
