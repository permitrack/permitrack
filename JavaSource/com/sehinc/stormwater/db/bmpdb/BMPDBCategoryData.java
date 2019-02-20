package com.sehinc.stormwater.db.bmpdb;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

public class BMPDBCategoryData
    extends UserUpdatableData
{
    private
    String
        name;

    public BMPDBCategoryData()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public static Long countBMPById(Integer bmpDbCategoryId)
    {
        Object
            parameters
            [
            ] =
            {bmpDbCategoryId};
        String
            queryString =
            "select count(data.id) from BMPDBData as data where data.bmpDBCategoryId = ? ";
        return (Long) HibernateUtil.findUnique(queryString,
                                               parameters);
    }
}
