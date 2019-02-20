package com.sehinc.erosioncontrol.server.bmpdb;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpCategoryDb;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpDb;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.value.bmp.BMPValue;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbCatValue;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BmpDbService
{
    private static
    Logger
        LOG =
        Logger.getLogger(BmpDbService.class);
    public static
    String
        FIND_BMPS_BY_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.bmpListByClientId";
    public static
    String
        FIND_BMPS_BY_CATEGORY_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmp.bmpListByCatId";
    public static
    String
        FIND_CATEGORIES_BY_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpCategory.bmpCategoriesByClientId";
    public static
    String
        BMP_DB_BY_CATEGORY =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpDb.bmpDbListByCatId";
    public static
    String
        BMPDB_CATEGORY_ID_BY_LIBRARY =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpCategoryDb.categoryIdsByLibrary";
    public static
    String
        FIND_ALL_DB_LIBRARIES =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpLibraryDb.allLibrariesList";
    public static
    String
        BMPDB_LIBRARY_LIST =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpLibraryDb.libraryList";
    public static
    String
        FIND_LIBRARY_NAME_BY_ID =
        "com.sehinc.erosioncontrol.db.bmp.EcBmpLibraryDb.libraryNameById";

    public BmpDbService()
    {
    }

    public static List getCategoryIdsByLibrary(int bmpDbLibraryId)
    {
        Object[][]
            parameters =
            {
                {
                    "libraryDbId",
                    bmpDbLibraryId}};
        List
            result =
            HibernateUtil.findByNamedQuery(BMPDB_CATEGORY_ID_BY_LIBRARY,
                                           parameters);
        return result;
    }

    public static String getLibraryNameById(int bmpDbLibraryyId)
    {
        Object[][]
            parameters =
            {
                {
                    "bmpLibraryId",
                    bmpDbLibraryyId}};
        String
            result =
            (String) HibernateUtil.findUniqueByNamedQuery(FIND_LIBRARY_NAME_BY_ID,
                                                          parameters);
        return result;
    }

    public static List getBmpDbLabelValueListByCategory(int bmpDbCategoryId)
    {
        List
            bmpDBValues;
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "categoryId",
                    new Integer(bmpDbCategoryId)}};
        bmpDBValues =
            HibernateUtil.findByNamedQuery(BMP_DB_BY_CATEGORY,
                                           parameters);
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < bmpDBValues.size(); i++)
        {
            BMPDbValue
                value =
                new BMPDbValue((EcBmpDb) bmpDBValues.get(i));
            returnValues.add(value);
        }
        return returnValues;
    }

    public static List getBmpDbListByDbCategoryId(Integer categoryId)
    {
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "categoryId",
                    new Integer(categoryId)}};
        return HibernateUtil.findByNamedQuery(BMP_DB_BY_CATEGORY,
                                              parameters);
    }

    public static BMPDbCatValue getBmpDbCategoryAndBmps(int bmpDbCategoryId)
    {
        EcBmpCategoryDb
            category =
            new EcBmpCategoryDb(bmpDbCategoryId);
        category.load();
        BMPDbCatValue
            categoryValue =
            new BMPDbCatValue(category);
        ArrayList<BMPDbValue>
            bmpValues =
            new ArrayList<BMPDbValue>();
        Object[][]
            parameters =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "categoryId",
                    new Integer(bmpDbCategoryId)}};
        List
            bmps =
            HibernateUtil.findByNamedQuery(BMP_DB_BY_CATEGORY,
                                           parameters);
        for (Object o : bmps)
        {
            EcBmpDb
                bmp =
                (EcBmpDb) o;
            BMPDbValue
                bmpValue =
                new BMPDbValue(bmp);
            bmpValues.add(bmpValue);
        }
        categoryValue.setBmpList(bmpValues);
        return categoryValue;
    }

    public static List getBmpDbLibraryLabelValueList(Integer bmpLibraryId)
    {
        List
            libraries;
        Object[][]
            parameters =
            {
                {
                    "bmpLibraryId",
                    new Integer(bmpLibraryId)}};
        libraries =
            HibernateUtil.findByNamedQuery(BMPDB_LIBRARY_LIST,
                                           parameters);
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < libraries.size(); i++)
        {
            Object[]
                library =
                (Object[]) libraries.get(i);
            returnValues.add(new LabelValueBean((String) library[0],
                                                library[1].toString()));
        }
        return returnValues;
    }

    public static List getBmpDbLibraryLabelValueList()
    {
        List
            libraries =
            HibernateUtil.findByNamedQuery(FIND_ALL_DB_LIBRARIES,
                                           new Object[][] {});
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < libraries.size(); i++)
        {
            Object[]
                library =
                (Object[]) libraries.get(i);
            returnValues.add(new LabelValueBean((String) library[0],
                                                library[1].toString()));
        }
        return returnValues;
    }

    public static List<EcBmpCategory> getBMPCategoryList(ClientValue clientValue)
    {
        ArrayList
            categoryList =
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
            EcBmpCategory
                category =
                (EcBmpCategory) bmpCatValueIterator.next();
            categoryList.add(category);
        }
        return categoryList;
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
}
