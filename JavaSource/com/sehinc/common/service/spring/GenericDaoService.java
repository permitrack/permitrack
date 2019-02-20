package com.sehinc.common.service.spring;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoService
{
    <T> void save(T obj);

    <T> T load(Serializable id, Class type);

    <T> List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria);
}
