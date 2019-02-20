package com.sehinc.service.servlet.util;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;
import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientData;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class StatusServlet
    extends javax.servlet.http.HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(StatusServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        performTask(req,
                    res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        performTask(req,
                    res);
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        LOG.debug("Initializing status servlet");
    }

    public void performTask(HttpServletRequest req, HttpServletResponse res)
        throws IOException
    {
        LOG.debug("Retrieving status");
        System.out
            .println("Retrieving status");
        try
        {
            ClientData
                clientData =
                new ClientData();
            clientData.setId(CommonConstants.SEH_CLIENT_ID);
            clientData.load();
            req.setAttribute("headerMessage",
                             "success");
            String
                bodyMessage =
                "Status: ok "
                + new Date()
                + "<br/><br/>"
                + getC3P0PoolStatus();
            req.setAttribute("bodyMessage",
                             bodyMessage);
        }
        catch (Exception e)
        {
            LOG.debug("Error Retrieving status",
                      e);
            req.setAttribute("headerMessage",
                             "fail");
            req.setAttribute("bodyMessage",
                             e.getMessage()
                             + "<br/><br/>"
                             + getC3P0PoolStatus());
        }
        try
        {
            req.getRequestDispatcher("/status/status.jsp")
                .forward(req,
                         res);
        }
        catch (Exception e)
        {
            LOG.debug("Error Forwarding",
                      e);
            PrintWriter
                out =
                res.getWriter();
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<META NAME=\"description\" CONTENT=\"PermiTrack Status Page\">");
            out.println("<META NAME=\"status\" CONTENT=\"fail\">");
            out.println("</HEAD>");
            out.println("<BODY>");
            out.println(e.getMessage());
            out.println("</BODY>");
            out.println("</HTML>");
            out.flush();
            out.close();
        }
    }

    private String getC3P0PoolStatus()
    {
        StringBuilder
            buffer =
            new StringBuilder();
        Set
            pooledDataSources =
            C3P0Registry.getPooledDataSources();
        if (pooledDataSources
            != null
            && pooledDataSources.size()
               > 0)
        {
            Iterator
                iter =
                pooledDataSources.iterator();
            while (iter.hasNext())
            {
                try
                {
                    PooledDataSource
                        pds =
                        (PooledDataSource) iter.next();
                    buffer.append("C3P0 PooledDataSource: ")
                        .append(pds.getDataSourceName())
                        .append("<br/>");
                    buffer.append("num_connections: ")
                        .append(pds.getNumConnectionsDefaultUser())
                        .append("<br/>");
                    buffer.append("num_busy_connections: ")
                        .append(pds.getNumBusyConnectionsDefaultUser())
                        .append("<br/>");
                    buffer.append("num_idle_connections: "
                                  + pds.getNumIdleConnectionsDefaultUser()
                                  + "<br/>");
                    buffer.append("num_unclosed_orphaned_connections: "
                                  + pds.getNumUnclosedOrphanedConnectionsDefaultUser()
                                  + "<br/>");
                    buffer.append("start_time: "
                                  + pds.getStartTimeMillisDefaultUser()
                                  + "<br/>");
                    buffer.append("stmt_cache_num_connections_with_stmts: "
                                  + pds.getStatementCacheNumConnectionsWithCachedStatementsDefaultUser()
                                  + "<br/>");
                    buffer.append("stmt_cache_num_stmts: "
                                  + pds.getStatementCacheNumStatementsDefaultUser()
                                  + "<br/>");
                    buffer.append("stmt_cache_num_checkout: "
                                  + pds.getStatementCacheNumCheckedOutDefaultUser()
                                  + "<br/>");
                    buffer.append("num_user_pools: "
                                  + pds.getNumUserPools()
                                  + "<br/>");
                    buffer.append("thread_pool_num_active: "
                                  + pds.getThreadPoolNumActiveThreads()
                                  + "<br/>");
                    buffer.append("thread_pool_num_idle: "
                                  + pds.getThreadPoolNumIdleThreads()
                                  + "<br/>");
                    buffer.append("thread_pool_num_pending: "
                                  + pds.getThreadPoolNumTasksPending()
                                  + "<br/>");
                    buffer.append("thread_pool_size: "
                                  + pds.getThreadPoolSize()
                                  + "<br/>");
                    buffer.append("last_acq_failure: "
                                  + ((pds.getLastAcquisitionFailureDefaultUser()
                                      != null)
                                         ? pds.getLastAcquisitionFailureDefaultUser()
                        .getMessage()
                                         : "")
                                  + "<br/>");
                    buffer.append("last_checkin_failure: "
                                  + ((pds.getLastCheckinFailureDefaultUser()
                                      != null)
                                         ? pds.getLastCheckinFailureDefaultUser()
                        .getMessage()
                                         : "")
                                  + "<br/>");
                    buffer.append("last_checkout_failure: "
                                  + ((pds.getLastCheckoutFailureDefaultUser()
                                      != null)
                                         ? pds.getLastCheckoutFailureDefaultUser()
                        .getMessage()
                                         : "")
                                  + "<br/>");
                    buffer.append("last_conn_test_failure: "
                                  + ((pds.getLastConnectionTestFailureDefaultUser()
                                      != null)
                                         ? pds.getLastConnectionTestFailureDefaultUser()
                        .getMessage()
                                         : "")
                                  + "<br/>");
                    buffer.append("last_idle_test_failure: "
                                  + ((pds.getLastIdleTestFailureDefaultUser()
                                      != null)
                                         ? pds.getLastIdleTestFailureDefaultUser()
                        .getMessage()
                                         : "")
                                  + "<br/>");
                }
                catch (SQLException se)
                {
                    buffer.append(se.getMessage()
                                  + "<br/><br/>");
                }
            }
        }
        else
        {
            buffer.append("No c3p0 PooledDataSource found!");
        }
        return buffer.toString();
    }
}
