package com.sehinc.common.service.spring.impl;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.service.spring.GenericDaoService;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class GenericDaoServiceImpl
    implements GenericDaoService
{
    Logger
        LOG =
        Logger.getLogger(GenericDaoServiceImpl.class);

    public <T> void save(T obj)
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Transaction
            tx =
            session.beginTransaction();
        try
        {
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
            LOG.error(e);
        }
        session.close();
    }

    public <T> T load(Serializable id, Class type)
    {
        Session
            session =
            HibernateUtil.getNewSession();
        T
            obj =
            null;
        try
        {
            obj =
                (T) session.get(type,
                                id);
            if (obj
                == null)
            {
                LOG.debug("Object not found: "
                          + type.getName()
                          + " id: "
                          + id);
            }
        }
        catch (org.hibernate.ObjectNotFoundException ex)
        {
            LOG.debug("Object not found: "
                      + type.getName()
                      + " id: "
                      + id);
            return null;
        }
        finally
        {
            session.close();
        }
        return obj;
    }

    public <T> List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria)
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Criteria
            criteria =
            detachedCriteria.getExecutableCriteria(session);
        List<T>
            list =
            criteria.list();
        session.close();
        return list;
    }
}
