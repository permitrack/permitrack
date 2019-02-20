package com.sehinc.common.service.message;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageServlet
    extends javax.servlet.http.HttpServlet
{
    private
    MessageService
        messageService =
        null;
    private static
    Logger
        LOG =
        Logger.getLogger(MessageServlet.class);

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
        try
        {
            LOG.info("Starting the MessageService");
            System.out
                .println("Starting the MessageService");
            messageService =
                new MessageService();
            messageService.start();
        }
        catch (Exception e)
        {
            LOG.error("MessageServlet.init(): "
                      + e.getMessage());
            System.out
                .println("MessageServlet.init(): "
                         + e.getMessage());
        }
    }

    public void performTask(HttpServletRequest req, HttpServletResponse res)
        throws IOException
    {
        if (req.getParameter("stop")
            != null)
        {
            LOG.debug("Received request to stop the daemon thread.");
            System.out
                .println("Received request to stop the daemon thread.");
            if (messageService
                != null
                && messageService.isAlive())
            {
                LOG.info("Killing daemon thread");
                messageService.kill();
            }
            else
            {
                LOG.debug("Daemon thread is not running. Couldn't kill it.");
                System.out
                    .println("Daemon thread is not running. Couldn't kill it.");
            }
        }
        if (req.getParameter("start")
            != null)
        {
            LOG.debug("Received request to start the daemon thread.");
            System.out
                .println("Received request to start the daemon thread.");
            if (messageService
                == null
                || !messageService.isAlive())
            {
                LOG.debug("Starting daemon thread");
                System.out
                    .println("Starting daemon thread");
                messageService =
                    new MessageService();
                messageService.start();
            }
            else
            {
                LOG.debug("Daemon thread is already running. Couldn't start.");
                System.out
                    .println("Daemon thread is already running. Couldn't start.");
            }
        }
        PrintWriter
            out =
            res.getWriter();
        out.println("<HTML><BODY>");
        out.println("<h2>Message Service</h2>\n");
        out.println("<b>Status:</b> \n");
        if (messageService
            == null
            || !messageService.isAlive())
        {
            out.println("Stopped<br>");
            if (messageService
                != null)
            {
                out.println("<b>Started On:</b> ");
                out.println(messageService.getStartDate()
                                .toString()
                            + "<br>");
                if (messageService.getStopDate()
                    != null)
                {
                    out.println("<b>Stopped On:</b> ");
                    out.println(messageService.getStopDate()
                                    .toString()
                                + "<br>");
                }
            }
        }
        else
        {
            out.println("Running<br>");
            out.println("<b>Started On:</b> ");
            out.println(messageService.getStartDate()
                            .toString()
                        + "<br>");
        }
        out.println("<br><br><br>\n");
        out.println("</BODY></HTML>");
        out.flush();
        out.close();
    }
}
