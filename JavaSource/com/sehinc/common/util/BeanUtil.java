package com.sehinc.common.util;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil
{
    private BeanUtil()
    {
    }

    public static List getYesNo()
    {
        ArrayList
            list =
            new ArrayList();
        list.add(new LabelValueBean("Yes",
                                    "Yes"));
        list.add(new LabelValueBean("No",
                                    "No"));
        return list;
    }

    public static String getJavaScriptArray(ArrayList list)
    {
        StringBuilder
            array =
            new StringBuilder();
        boolean
            comma =
            false;
        if (list
            != null)
        {
            array.append("{value: ")
                .append("''")
                .append(",")
                .append("text: '")
                .append("")
                .append("'}");

            comma =
                true;

            for (Object item : list)
            {
                LabelValueBean
                    bean =
                    (LabelValueBean) item;
                if (comma)
                {
                    array.append(",");
                }
                array.append("{value: ")
                    .append(bean.getValue())
                    .append(",")
                    .append("text: '")
                    .append(bean.getLabel())
                    .append("'}");
            }
        }
        return array.toString();
    }
}
