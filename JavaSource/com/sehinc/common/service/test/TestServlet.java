package com.sehinc.common.service.test;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserData;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestServlet
    extends javax.servlet.http.HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(TestServlet.class);


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
        LOG.debug("Initializing test servlet");
    }

    public void performTask(HttpServletRequest req, HttpServletResponse res)
        throws IOException
    {
        LOG.debug("Performing  test");
        try
        {
            String
                bodyMessage =
                performTest();
            req.setAttribute("headerMessage",
                             "success");
            req.setAttribute("bodyMessage",
                             bodyMessage
                             + new Date());
            req.getRequestDispatcher("/status/status.jsp")
                .forward(req,
                         res);
        }
        catch (Exception e)
        {
            LOG.debug("Exception occurred",
                      e);
        }
    }

    public static String performTest()
        throws SQLException
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Connection
            connection =
            session.connection();
        Statement
            statement =
            connection.createStatement();
        String
            query =
            "select ID, USERNAME from [USER] ";
        ResultSet
            results =
            statement.executeQuery(query);
        List
            userIds =
            new ArrayList();
        while (results.next())
        {
            userIds.add(results.getString(1)
                        + ":"
                        + results.getString(2));
            UserData
                data2 =
                new UserData();
            data2.setId(new Integer(2));
            data2.load();
        }
        String
            bodyMessage =
            "";
        for (
            int
                i =
                0; i
                   < userIds.size(); i++)
        {
            bodyMessage =
                bodyMessage
                + userIds.get(i)
                + "<BR>";
        }
        return bodyMessage;
    }
}
