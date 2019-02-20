package com.sehinc.erosioncontrol.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

public class EcUserPreferences
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcUserPreferences.class);
    private
    Integer
        userId;
    private
    String
        projectListItems;

    public EcUserPreferences()
    {
    }

    public EcUserPreferences(Integer id)
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

    public String getProjectListItems()
    {
        return this.projectListItems;
    }

    public void setProjectListItems(String projectListItems)
    {
        this.projectListItems =
            projectListItems;
    }

    public static EcUserPreferences findByUserId(Integer userId)
    {
        Object
            parameters
            [
            ] =
            {userId};
        String
            queryString =
            "select data from EcUserPreferences as data where data.userId = ?";
        return (EcUserPreferences) HibernateUtil.findUnique(queryString,
                                                            parameters);
    }
}