package com.sehinc.common.service.option;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.option.DataElementOptionValue;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataElementOptionValueService
{
    public static final
    String
        SKY =
        "SKY";
    public static final
    String
        OUTFALL_TYPE =
        "OUTFALL_TYPE";
    public static final
    String
        PIPE_TYPE =
        "PIPE_TYPE";
    public static final
    String
        PIPE_MATERIAL =
        "PIPE_MATERIAL";
    public static final
    String
        LINEAR_UNITS =
        "LINEAR_UNITS";
    public static final
    String
        DITCH_WIDTH =
        "DITCH_WIDTH";
    public static final
    String
        DITCH_DEPTH =
        "DITCH_DEPTH";
    public static final
    String
        WEIR_TYPE =
        "WEIR_TYPE";
    public static final
    String
        OUTFALL_DAMAGE =
        "OUTFALL_DAMAGE";
    public static final
    String
        EROSION =
        "EROSION";
    public static final
    String
        FLOW_DEPTH =
        "FLOW_DEPTH";
    public static final
    String
        ODOR =
        "ODER";
    public static final
    String
        WATER_COLOR =
        "WATER_COLOR";
    public static final
    String
        TURBIDITY =
        "TURBIDITY";
    public static final
    String
        FLOATABLES =
        "FLOATABLES";
    public static final
    String
        OUTFALL_MATERIAL =
        "OUTFALL_MATERIAL";
    public static final
    String
        SIDE_OF_ROAD =
        "SIDE_OF_ROAD";
    public static final
    String
        INSPECTION_FREQUENCY =
        "INSPECTION_FREQUENCY";
    public static final
    String
        OTHER =
        "other";
    public static final
    String
        DATA_ELEMENT_OPTION_VALUE_LIST_BY_MODULE =
        "com.sehinc.common.db.option.DataElement.optionValueByCapModuleId";

    public DataElementOptionValueService()
    {
    }

    public static List getSWOptionsList(String value)
    {
        CapModule
            capModule =
            CapModule.findByCode(CommonConstants.STORM_WATER_MODULE);
        return getOptionsList(value,
                              capModule);
    }

    public static List getECOptionsList(String value)
    {
        CapModule
            capModule =
            CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
        return getOptionsList(value,
                              capModule);
    }

    public static List getDVOptionsList(String value)
    {
        CapModule
            capModule =
            CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE);
        return getOptionsList(value,
                              capModule);
    }

    public static List getOptionsList(String value, CapModule capModule)
    {
        Object[][]
            parameters =
            {
                {
                    "name",
                    value},
                {
                    "capModuleId",
                    capModule.getId()}};
        List
            optionValueList =
            new ArrayList();
        Iterator
            optionValueIterator =
            HibernateUtil.findByNamedQuery(DATA_ELEMENT_OPTION_VALUE_LIST_BY_MODULE,
                                           parameters)
                .iterator();
        while (optionValueIterator.hasNext())
        {
            DataElementOptionValue
                optionValue =
                (DataElementOptionValue) optionValueIterator.next();
            LabelValueBean
                labelValue =
                new LabelValueBean(optionValue.getValue(),
                                   optionValue.getValue());
            optionValueList.add(labelValue);
        }
        return optionValueList;
    }

    public static boolean valueInList(String dataElementName, String value, CapModule capModule)
    {
        boolean
            foundInList =
            false;
        Object[][]
            parameters =
            {
                {
                    "name",
                    dataElementName},
                {
                    "capModuleId",
                    capModule.getId()}};
        Iterator
            optionValueIterator =
            HibernateUtil.findByNamedQuery(DATA_ELEMENT_OPTION_VALUE_LIST_BY_MODULE,
                                           parameters)
                .iterator();
        while (optionValueIterator.hasNext())
        {
            DataElementOptionValue
                optionValue =
                (DataElementOptionValue) optionValueIterator.next();
            if (optionValue.getValue()
                .equalsIgnoreCase(value))
            {
                foundInList =
                    true;
                break;
            }
        }
        return foundInList;
    }
}