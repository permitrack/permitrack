package com.sehinc.common.service.spring;

import com.sehinc.environment.db.code.CodeData;
import com.sehinc.environment.util.CodeComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.sehinc.common.db.hibernate.HibernateUtil.findAll;

public class EnvLookupService
{
    public CodeData fetchCode(String code, Class codeClass)
    {
        List<CodeData>
            codes =
            fetchCodes(codeClass);
        for (CodeData item : codes)
        {
            if (code.equals(item.getCode()))
            {
                return item;
            }
        }
        return null;
    }

    public List<CodeData> fetchCodes(Class codeClass)
    {
        List
            all =
            findAll(codeClass);
        List<CodeData>
            codes =
            new ArrayList<CodeData>(all.size());
        for (Object o : all)
        {
            if (o instanceof CodeData)
            {
                codes.add((CodeData) o);
            }
        }
        return sortCodes(codes);
    }

    public List<CodeData> fetchCodesDisplayable(Class codeClass)
    {
        List<CodeData>
            codes =
            fetchCodes(codeClass);
        return removeNulls(codes);
    }

    private List<CodeData> sortCodes(List<CodeData> codes)
    {
        Collections.sort(codes,
                         new CodeComparator());
        return codes;
    }

    private List<CodeData> removeNulls(List<CodeData> codes)
    {
        ArrayList<CodeData>
            displayCodes =
            new ArrayList();
        Iterator
            i =
            codes.iterator();
        while (i.hasNext())
        {
            CodeData
                codeCheck =
                (CodeData) i.next();
            Integer
                orderNum =
                codeCheck.getDisplayOrder();
            if (orderNum
                != 0)
            {
                displayCodes.add(codeCheck);
            }
        }
        return displayCodes;
    }
}
