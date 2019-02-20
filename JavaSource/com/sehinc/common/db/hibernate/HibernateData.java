package com.sehinc.common.db.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateData
{
    Integer
        id;
    private static final
    Logger
        LOG =
        Logger.getLogger(HibernateData.class);

    public int delete()
    {
        if (getId()
            == null)
        {
            return 0;
        }
        Session
            session =
            HibernateUtil.getNewSession();
        Transaction
            tx =
            session.beginTransaction();
        session.delete(this);
        tx.commit();
        session.close();
        return 1;
    }

    public boolean retrieveByPrimaryKeysAlternate()
    {
        return load();
    }

    public boolean retrieveByPrimaryKeys()
    {
        return load();
    }

    public boolean load()
    {
        Session
            session =
            HibernateUtil.getNewSession();
        try
        {
            session.load(this,
                         getId());
        }
        catch (org.hibernate.ObjectNotFoundException ex)
        {
            LOG.warn("Object not found: "
                     + this.getClass()
                .getName()
                     + " id: "
                     + getId());
            return false;
        }
        catch (HibernateException e)
        {
            LOG.warn("Exception thrown while loading Object: "
                     + this.getClass()
                .getName()
                     + " id: "
                     + getId(),
                     e);
            return false;
        }
        finally
        {
            session.close();
        }
        return true;
    }

    public Integer insert()
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Transaction
            tx =
            session.beginTransaction();
        session.save(this);
        tx.commit();
        session.close();
        return this.getId();
    }

    public Integer save()
    {
        if (id
            == null)
        {
            id =
                this.insert();
            return id;
        }
        else
        {
            this.update();
            return id;
        }
    }

    public void update()
    {
        Session
            session =
            HibernateUtil.getNewSession();
        Transaction
            tx2 =
            session.beginTransaction();
        try
        {
            session.update(this);
            tx2.commit();
        }
        catch (Exception e)
        {
            LOG.error(e);
        }
        finally
        {
            session.close();
        }
    }

    public List findAll(Class hibernateClass)
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
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setId(int id)
    {
        this.id =
            id;
    }

    public boolean equals(Object o)
    {
        if (o instanceof HibernateData)
        {
            HibernateData
                other =
                (HibernateData) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
