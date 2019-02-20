package com.sehinc.erosioncontrol.db.user;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;
import org.apache.log4j.Logger;

import java.util.List;

public class EcUserSearch
    extends UserUpdatableData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcUserSearch.class);
    private
    Integer
        userId;
    private
    Integer
        searchId;
    private
    EcSearch
        ecSearch;

    public EcUserSearch()
    {
    }

    public EcUserSearch(Integer id)
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

    public Integer getSearchId()
    {
        return searchId;
    }

    public void setSearchId(Integer searchId)
    {
        this.searchId =
            searchId;
    }

    public EcSearch getEcSearch()
    {
        return ecSearch;
    }

    public void setEcSearch(EcSearch ecSearch)
    {
        this.ecSearch =
            ecSearch;
    }

    public static List findByUserId(Integer userId)
    {
        Object
            parameters
            [
            ] =
            {userId};
        String
            queryString =
            "select data from EcUserSearch as data where data.userId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static EcUserSearch findByUserIdAndSearchId(Integer userId, Integer searchId)
    {
        Object
            parameters
            [
            ] =
            {
                userId,
                searchId};
        String
            queryString =
            "select data from EcUserSearch as data where data.userId = ? and data.searchId = ?";
        return (EcUserSearch) HibernateUtil.findUnique(queryString,
                                                       parameters);
    }
}