package com.sehinc.common.db.user;

public class InvalidUsernameException
    extends Exception
{
    public InvalidUsernameException()
    {
        super();
    }

    public InvalidUsernameException(String s)
    {
        super(s);
    }
}