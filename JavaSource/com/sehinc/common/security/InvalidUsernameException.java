package com.sehinc.common.security;

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