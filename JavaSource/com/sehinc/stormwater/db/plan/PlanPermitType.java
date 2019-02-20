package com.sehinc.stormwater.db.plan;

import com.sehinc.common.util.LabelValueBean;
import com.sehinc.stormwater.server.plan.PlanService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class PlanPermitType
    extends LabelValueBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanPermitType.class);
    public static final
    PlanPermitType
        MPCA =
        new PlanPermitType("Standard",
                           1,
                           BMPFormatter.MPCA,
                           false);
    public static final
    PlanPermitType
        OTHER =
        new PlanPermitType("Default EPA",
                           2,
                           BMPFormatter.DEFAULT,
                           true);
    public static
    ArrayList
        ALL_TYPES =
        null;
    Integer
        id;
    BMPFormatter
        formatter;
    boolean
        addBMPtoDB;

    public PlanPermitType(String name, int value, BMPFormatter formatter, boolean addBMPtoDB)
    {
        super(name,
              String.valueOf(value));
        id =
            new Integer(value);
        this.formatter =
            formatter;
        this.addBMPtoDB =
            addBMPtoDB;
    }

    public static synchronized ArrayList getPlanPermitTypes()
    {
        if (ALL_TYPES
            == null)
        {
            ALL_TYPES =
                new ArrayList();
            ALL_TYPES.add(MPCA);
            ALL_TYPES.add(OTHER);
        }
        return ALL_TYPES;
    }

    public static PlanPermitType getById(Integer id)
    {
        Iterator
            it =
            getPlanPermitTypes().iterator();
        while (it.hasNext())
        {
            PlanPermitType
                type =
                (PlanPermitType) it.next();
            if (type.id
                .equals(id))
            {
                LOG.debug("returning PlanPermitType label="
                          + type.getLabel()
                          + " value="
                          + type.getValue());
                return type;
            }
        }
        return null;
    }

    public static PlanPermitType getByPlanId(Integer planId)
    {
        PlanData
            planData =
            PlanService.getPlan(planId);
        if (planData
            == null)
        {
            return null;
        }
        Iterator
            it =
            getPlanPermitTypes().iterator();
        while (it.hasNext())
        {
            PlanPermitType
                type =
                (PlanPermitType) it.next();
            if (type.id
                .equals(planData.getPermitTypeId()))
            {
                LOG.debug("returning PlanPermitType label="
                          + type.getLabel()
                          + " value="
                          + type.getValue());
                return type;
            }
        }
        return null;
    }

    public static boolean getAddBMPtoDBbyId(Integer id)
    {
        Iterator
            it =
            getPlanPermitTypes().iterator();
        while (it.hasNext())
        {
            PlanPermitType
                type =
                (PlanPermitType) it.next();
            if (type.id
                .equals(id))
            {
                return type.getAddBMPtoDB();
            }
        }
        return false;
    }

    public static boolean getAddBMPtoDBbyType(PlanPermitType permitType)
    {
        Iterator
            it =
            getPlanPermitTypes().iterator();
        while (it.hasNext())
        {
            PlanPermitType
                type =
                (PlanPermitType) it.next();
            if (type.equals(permitType))
            {
                return type.getAddBMPtoDB();
            }
        }
        return false;
    }

    public boolean getAddBMPtoDB()
    {
        return this.addBMPtoDB;
    }

    public BMPFormatter getBMPFormatter()
    {
        return formatter;
    }

    public boolean equals(PlanPermitType planPermitType)
    {
        if (planPermitType.id
            == this.id)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
