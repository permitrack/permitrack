package com.sehinc.common.db.user;

public class LoginFailedException
    extends Exception
{
    public LoginFailedException()
    {
        super();
    }

    public LoginFailedException(String s)
    {
        super(s);
    }
}