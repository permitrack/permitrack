package com.sehinc.erosioncontrol.db.user;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;
import org.apache.log4j.Logger;

public class EcUserDefaultSearch
    extends UserUpdatableData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcUserDefaultSearch.class);
    private
    Integer
        userId;
    private
    Integer
        defaultSearchId;

    public EcUserDefaultSearch()
    {
    }

    public EcUserDefaultSearch(Integer id)
    {
        setId(id);
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId =
            userId;
    }

    public Integer getDefaultSearchId()
    {
        return defaultSearchId;
    }

    public void setDefaultSearchId(Integer defaultSearchId)
    {
        this.defaultSearchId =
            defaultSearchId;
    }

    public static EcUserDefaultSearch findByUserId(Integer userId)
    {
        Object
            parameters
            [
            ] =
            {userId};
        String
            queryString =
            "select data from EcUserDefaultSearch as data where data.userId = ?";
        return (EcUserDefaultSearch) HibernateUtil.findUnique(queryString,
                                                              parameters);
    }
}