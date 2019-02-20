package com.sehinc.common.db.sql;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DataAccessException
    extends RuntimeException
{
    Exception
        e =
        null;
    String
        message =
        null;

    public DataAccessException(Exception e)
    {
        this.e =
            e;
    }

    public DataAccessException(String message)
    {
        this.message =
            message;
    }

    public String getMessage()
    {
        if (e
            != null)
        {
            return e.getMessage();
        }
        else
        {
            return message;
        }
    }

    public void printStackTrace()
    {
        if (e
            != null)
        {
            e.printStackTrace();
        }
        else
        {
            super.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream st)
    {
        if (e
            != null)
        {
            e.printStackTrace(st);
        }
        else
        {
            super.printStackTrace(st);
        }
    }

    public void printStackTrace(PrintWriter pw)
    {
        if (e
            != null)
        {
            e.printStackTrace(pw);
        }
        else
        {
            super.printStackTrace(pw);
        }
    }
}
