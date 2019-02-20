package com.sehinc.common.db.base;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool
{
    private static
    Logger
        LOG =
        Logger.getLogger(DBConnectionPool.class);
    private static
    DataSource
        ds =
        null;

    private DBConnectionPool()
    {
    }

    public static Connection getConnection()
        throws SQLException
    {
        if (ds
            == null)
        {
            LOG.info("Initializing Data Source");
            try
            {
                Context
                    initCtx =
                    new InitialContext();
                Context
                    envCtx =
                    (Context) initCtx.lookup("java:comp/env");
                LOG.debug("Context: "
                          + envCtx.toString());
                ds =
                    (DataSource) envCtx.lookup("jdbc/CAPDB");
                if (ds
                    == null)
                {
                    LOG.info("Error: Failed to retrieve jndi data source");
                    throw new Exception("Error: Failed to retrieve data source");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace(System.out);
            }
        }
        return ds.getConnection();
    }
}

