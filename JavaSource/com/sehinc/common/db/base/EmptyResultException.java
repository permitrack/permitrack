package com.sehinc.common.db.base;

public class EmptyResultException
    extends RuntimeException
{
    public EmptyResultException(String message)
    {
        super(message);
    }
}

