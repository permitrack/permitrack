package com.sehinc.stormwater.server.bmpdb;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BMPDBService
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBService.class);
    private static final
    String
        BMPDB_BY_CATEGORY =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.byCategoryId";
    private static final
    String
        BMPDB_BY_CATEGORY_MPCA =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.byCategoryId.MPCA";
    private static final
    String
        BMPDB_BY_CATEGORY_DEFAULT =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.byCategoryId.Default";
    private static final
    String
        BMPDB_CATEGORY_NAME_ID =
        "com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData.nameAndId";
    private static final
    String
        BMPDB_CATEGORY_BY_MPCA =
        "com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData.MPCA";
    private static final
    String
        BMPDB_CATEGORY_BY_DEFAULT =
        "com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData.Default";
    private static final
    String
        BMPDB_ALL =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.all";
    private static final
    String
        BMPDB_ALL_MPCA =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.all.MPCA";
    private static final
    String
        BMPDB_ALL_DEFAULT =
        "com.sehinc.stormwater.db.bmpdb.BMPDBValue.all.Default";

    public BMPDBService()
    {
    }

    public static List getGoalValues(BMPDBValue bmpdbValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            i =
            BMPDBGoalData.findByBmpDBId(bmpdbValue.getId())
                .iterator();
        while (i.hasNext())
        {
            results.add(new BMPDBGoalValue((BMPDBGoalData) i.next()));
        }
        return results;
    }

    public static List getGoals(BMPDBValue bmpdbValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            i =
            BMPDBGoalData.findByBmpDBId(bmpdbValue.getId())
                .iterator();
        while (i.hasNext())
        {
            results.add(i.next());
        }
        return results;
    }

    public static BMPDBData getBMP(int bmpDbId)
    {
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setId(bmpDbId);
        if (!bmpDbData.retrieveByPrimaryKeysAlternate())
        {
            return null;
        }
        return bmpDbData;
    }

    public static List getBmpDbCategoryLabelValueList(PlanValue planValue)
    {
        LOG.debug("Within getBmpDbCategoryLabelValueList()");
        List
            categoryList;
        if (planValue
            != null)
        {
            PlanPermitType
                planPermitType =
                planValue.getPermitType();
            if (planPermitType
                != null
                && planPermitType.equals(PlanPermitType.MPCA))
            {
                categoryList =
                    HibernateUtil.findByNamedQuery(BMPDB_CATEGORY_BY_MPCA,
                                                   new Object[][] {});
            }
            else
            {
                categoryList =
                    HibernateUtil.findByNamedQuery(BMPDB_CATEGORY_BY_DEFAULT,
                                                   new Object[][] {});
            }
        }
        else
        {
            categoryList =
                HibernateUtil.findByNamedQuery(BMPDB_CATEGORY_BY_DEFAULT,
                                               new Object[][] {});
        }
        List
            returnValues =
            new ArrayList();
        if (categoryList
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < categoryList.size(); i++)
            {
                BMPDBCategoryData
                    category =
                    (BMPDBCategoryData) categoryList.get(i);
                returnValues.add(new LabelValueBean(category.getName(),
                                                    category.getId()
                                                        .toString()));
            }
        }
        return returnValues;
    }

    public static List getBmpDbCategoryLabelValueList()
    {
        List
            categories =
            HibernateUtil.findByNamedQuery(BMPDB_CATEGORY_NAME_ID,
                                           new Object[][] {});
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < categories.size(); i++)
        {
            Object[]
                category =
                (Object[]) categories.get(i);
            returnValues.add(new LabelValueBean((String) category[0],
                                                category[1].toString()));
        }
        return returnValues;
    }

    public static String getBmpDbCategoryName(int bmpDbCategoryId)
    {
        BMPDBCategoryData
            bmpDbCategoryData =
            new BMPDBCategoryData();
        bmpDbCategoryData.setId(bmpDbCategoryId);
        if (!bmpDbCategoryData.retrieveByPrimaryKeysAlternate())
        {
            return null;
        }
        return bmpDbCategoryData.getName();
    }

    public static List getBMPDBLabelValueListByCategory(int bmpDbCategoryId)
    {
        List
            bmpDBValues;
        if (bmpDbCategoryId
            == 0)
        {
            bmpDBValues =
                HibernateUtil.findByNamedQuery(BMPDB_ALL,
                                               new Object[][] {});
        }
        else
        {
            Object[][]
                parameters =
                {
                    {
                        "categoryId",
                        new Integer(bmpDbCategoryId)}};
            bmpDBValues =
                HibernateUtil.findByNamedQuery(BMPDB_BY_CATEGORY,
                                               parameters);
        }
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < bmpDBValues.size(); i++)
        {
            BMPDBValue
                value =
                (BMPDBValue) bmpDBValues.get(i);
            returnValues.add(new LabelValueBean(value.getName(),
                                                value.getId()
                                                    .toString()));
        }
        return returnValues;
    }

    public static List getBMPDBLabelValueListByCategory(int bmpDbCategoryId, PlanValue planValue)
    {
        LOG.debug("Within getBMPDBLabelValueListByCategory()");
        List
            bmpDBValues;
        if (bmpDbCategoryId
            == 0)
        {
            if (planValue
                != null)
            {
                PlanPermitType
                    planPermitType =
                    planValue.getPermitType();
                if (planPermitType
                    != null
                    && planPermitType.equals(PlanPermitType.MPCA))
                {
                    bmpDBValues =
                        HibernateUtil.findByNamedQuery(BMPDB_ALL_MPCA,
                                                       new Object[][] {});
                }
                else
                {
                    bmpDBValues =
                        HibernateUtil.findByNamedQuery(BMPDB_ALL_DEFAULT,
                                                       new Object[][] {});
                }
            }
            else
            {
                bmpDBValues =
                    HibernateUtil.findByNamedQuery(BMPDB_ALL_DEFAULT,
                                                   new Object[][] {});
            }
        }
        else
        {
            Object[][]
                parameters =
                {
                    {
                        "categoryId",
                        new Integer(bmpDbCategoryId)}};
            if (planValue
                != null)
            {
                PlanPermitType
                    planPermitType =
                    planValue.getPermitType();
                if (planPermitType
                    != null
                    && planPermitType.equals(PlanPermitType.MPCA))
                {
                    bmpDBValues =
                        HibernateUtil.findByNamedQuery(BMPDB_BY_CATEGORY_MPCA,
                                                       parameters);
                }
                else
                {
                    bmpDBValues =
                        HibernateUtil.findByNamedQuery(BMPDB_BY_CATEGORY_DEFAULT,
                                                       parameters);
                }
            }
            else
            {
                bmpDBValues =
                    HibernateUtil.findByNamedQuery(BMPDB_BY_CATEGORY_DEFAULT,
                                                   parameters);
            }
        }
        List
            returnValues =
            new ArrayList();
        for (
            int
                i =
                0; i
                   < bmpDBValues.size(); i++)
        {
            BMPDBValue
                value =
                (BMPDBValue) bmpDBValues.get(i);
            returnValues.add(new LabelValueBean(value.getName(),
                                                value.getId()
                                                    .toString()));
        }
        return returnValues;
    }
}