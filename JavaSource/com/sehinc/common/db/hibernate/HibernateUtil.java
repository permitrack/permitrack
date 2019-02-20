package com.sehinc.common.db.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.NullableType;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class HibernateUtil
{
    private static
    Logger
        LOG =
        Logger.getLogger(HibernateUtil.class);
    static
    SessionFactory
        hibernateSessionFactory;

    public static SessionFactory getSessionFactory()
    {
        if (hibernateSessionFactory
            == null)
        {
            LOG.debug("Initializing Hibernate Configuration");
            try
            {
                hibernateSessionFactory =
                    new Configuration().configure()
                        .buildSessionFactory();
            }
            catch (Throwable t)
            {
                LOG.error("Exception while configuring hibernate sessionFactory",
                          t);
            }
            LOG.debug("Finished Hibernate Configuration");
        }
        return hibernateSessionFactory;
    }

    public static Session getNewSession()
    {
        return getSessionFactory().openSession();
    }

    public static List find(String queryString, Object[] parameters)
    {
        List
            results;
        Session
            session =
            getNewSession();
        Query
            query =
            session.createQuery(queryString);
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter(i,
                               parameters[i]);
        }
        results =
            query.list();
        session.close();
        return results;
    }

    public static int update(String queryString, Object[] parameters)
    {
        int
            updatedEntities;
        Session
            session =
            getNewSession();
        Query
            query =
            session.createQuery(queryString);
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter(i,
                               parameters[i]);
        }
        updatedEntities =
            query.executeUpdate();
        session.close();
        return updatedEntities;
    }

    public static List findSQL(String queryString, Hashtable scalars, Object[] parameters)
    {
        List
            results;
        Session
            session =
            getNewSession();
        SQLQuery
            query =
            session.createSQLQuery(queryString);
        Enumeration
            cols =
            scalars.keys();
        while (cols.hasMoreElements())
        {
            String
                column =
                (String) cols.nextElement();
            NullableType
                type =
                (NullableType) scalars.get(column);
            query.addScalar(column,
                            type);
        }
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter(i,
                               parameters[i]);
        }
        results =
            query.list();
        session.close();
        return results;
    }

    public static List findTop(String queryString, Object[] parameters, int top)
    {
        List
            results;
        Session
            session =
            getNewSession();
        Query
            query =
            session.createQuery(queryString);
        query.setMaxResults(top);
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter(i,
                               parameters[i]);
        }
        results =
            query.list();
        session.close();
        return results;
    }

    public static Object findUnique(String queryString, Object[] parameters)
    {
        List
            results =
            find(queryString,
                 parameters);
        if (results.size()
            == 1)
        {
            return results.get(0);
        }
        else
        {
            return null;
        }
    }

    public static List findByNamedQuery(String queryName, Object[][] parameters)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("executing named query: "
                      + queryName);
            for (
                int
                    i =
                    0; i
                       < parameters.length; i++)
            {
                LOG.debug("parameter: "
                          + parameters[i][0]
                          + " => "
                          + parameters[i][1]);
            }
        }
        List
            results;
        Session
            session =
            getNewSession();
        Query
            query =
            session.getNamedQuery(queryName);
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter((String) parameters[i][0],
                               parameters[i][1]);
        }
        results =
            query.list();
        session.close();
        LOG.info("returning "
                 + results.size()
                 + " results for query "
                 + queryName);
        return results;
    }

    public static Object findUniqueByNamedQuery(String queryName, Object[][] parameters)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("executing named query: "
                      + queryName);
            for (
                int
                    i =
                    0; i
                       < parameters.length; i++)
            {
                LOG.debug("parameter: "
                          + parameters[i][0]
                          + " => "
                          + parameters[i][1]);
            }
        }
        List
            results;
        Session
            session =
            getNewSession();
        Query
            query =
            session.getNamedQuery(queryName);
        for (
            int
                i =
                0; i
                   < parameters.length; i++)
        {
            query.setParameter((String) parameters[i][0],
                               parameters[i][1]);
        }
        results =
            query.list();
        session.close();
        if (results.size()
            == 1)
        {
            LOG.debug("returning unique result for query "
                      + queryName);
            return results.get(0);
        }
        else
        {
            if (LOG.isDebugEnabled())
            {
                if (results.isEmpty())
                {
                    LOG.error("expected a unique result but the query returned 0 records");
                }
                else
                {
                    LOG.error("expected a unique result but the query returned "
                              + results.size()
                              + " records");
                }
            }
        }
        return null;
    }

    public static List findAll(Class hibernateClass)
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from "
            + hibernateClass.getName()
            + " as data";
        return find(queryString,
                    parameters);
    }

    public static Object load(Class theClass, Integer id)
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Object
            obj =
            null;
        try
        {
            obj =
                session.load(theClass,
                             id);
        }
        catch (org.hibernate.ObjectNotFoundException ex)
        {
            LOG.debug("Object not found: "
                      + theClass.getName()
                      + " id: "
                      + id);
        }
        finally
        {
            session.close();
        }
        return obj;
    }

    public static Object get(Class theClass, Integer id)
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Object
            obj =
            null;
        try
        {
            obj =
                session.get(theClass,
                            id);
        }
        catch (org.hibernate.ObjectNotFoundException ex)
        {
            LOG.debug("Object not found: "
                      + theClass.getName()
                      + " id: "
                      + id);
        }
        finally
        {
            session.close();
        }
        return obj;
    }
}
