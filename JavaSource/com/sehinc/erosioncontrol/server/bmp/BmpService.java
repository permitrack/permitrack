package com.sehinc.erosioncontrol.server.bmp;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.value.bmp.BMPCatValue;
import com.sehinc.erosioncontrol.value.bmp.BMPValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BmpService
{
    private static
    Logger
        LOG =
        Logger.getLogger(BmpService.class);
    public static
    String
        FIND_BMPS_BY_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.bmpListByClientId";
    public static
    String
        FIND_BMP_CATEGORY_BY_NAME =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpCategory.bmpCategoryByName";
    public static
    String
        FIND_BMP_BY_CAT_ID_AND_NAME =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.bmpByCatIdAndName";
    public static
    String
        FIND_DUPLICATE_BMP_IN_CATEGORY =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.duplicateBmpInCategory";
    public static
    String
        FIND_BMPS_BY_CATEGORY_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.bmpListByCatId";
    public static
    String
        FIND_CATEGORIES_BY_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpCategory.bmpCategoriesByClientId";

    public BmpService()
    {
    }

    public static List getBMPList(ClientValue clientValue)
    {
        ArrayList
            bmpValueList =
            new ArrayList();
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpValueIterator =
            HibernateUtil.findByNamedQuery(FIND_BMPS_BY_CLIENT_ID,
                                           parameters)
                .iterator();
        while (bmpValueIterator.hasNext())
        {
            BMPValue
                bmpValue =
                new BMPValue((EcBmp) bmpValueIterator.next());
            bmpValueList.add(bmpValue);
        }
        return bmpValueList;
    }

    public static List getBMPList(Integer categoryId, ClientValue clientValue)
    {
        ArrayList
            bmpValueList =
            new ArrayList();
        Object[][]
            parameters =
            {
                {
                    "categoryId",
                    categoryId},
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpValueIterator =
            HibernateUtil.findByNamedQuery(FIND_BMPS_BY_CATEGORY_ID,
                                           parameters)
                .iterator();
        while (bmpValueIterator.hasNext())
        {
            BMPValue
                bmpValue =
                new BMPValue((EcBmp) bmpValueIterator.next());
            bmpValueList.add(bmpValue);
        }
        return bmpValueList;
    }

    public static EcBmpCategory getBmpCategoryByName(String categoryName, ClientValue clientValue)
    {
        Object[][]
            parameters =
            {
                {
                    "categoryName",
                    categoryName},
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpCategoryIterator =
            HibernateUtil.findByNamedQuery(FIND_BMP_CATEGORY_BY_NAME,
                                           parameters)
                .iterator();
        while (bmpCategoryIterator.hasNext())
        {
            EcBmpCategory
                bmpCategory =
                (EcBmpCategory) bmpCategoryIterator.next();
            return bmpCategory;
        }
        return null;
    }

    public static EcBmp getBmpByCategoryIdAndName(Integer categoryId, String bmpName, ClientValue clientValue)
    {
        Object[][]
            parameters =
            {
                {
                    "categoryId",
                    categoryId},
                {
                    "bmpName",
                    bmpName},
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpIterator =
            HibernateUtil.findByNamedQuery(FIND_BMP_BY_CAT_ID_AND_NAME,
                                           parameters)
                .iterator();
        while (bmpIterator.hasNext())
        {
            EcBmp
                bmp =
                (EcBmp) bmpIterator.next();
            return bmp;
        }
        return null;
    }

    public static EcBmp getDuplicateBmpInCategory(Integer bmpId, String bmpName, Integer categoryId, ClientValue clientValue)
    {
        Object[][]
            parameters =
            {
                {
                    "bmpId",
                    bmpId},
                {
                    "categoryId",
                    categoryId},
                {
                    "name",
                    bmpName},
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpIterator =
            HibernateUtil.findByNamedQuery(FIND_DUPLICATE_BMP_IN_CATEGORY,
                                           parameters)
                .iterator();
        while (bmpIterator.hasNext())
        {
            EcBmp
                ecBmp =
                (EcBmp) bmpIterator.next();
            return ecBmp;
        }
        return null;
    }

    public static List getBMPCategoryList(ClientValue clientValue)
    {
        ArrayList
            bmpCatValueList =
            new ArrayList();
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            bmpCatValueIterator =
            HibernateUtil.findByNamedQuery(FIND_CATEGORIES_BY_CLIENT_ID,
                                           parameters)
                .iterator();
        while (bmpCatValueIterator.hasNext())
        {
            BMPCatValue
                bmpCatValue =
                new BMPCatValue((EcBmpCategory) bmpCatValueIterator.next());
            bmpCatValueList.add(bmpCatValue);
        }
        return bmpCatValueList;
    }
}