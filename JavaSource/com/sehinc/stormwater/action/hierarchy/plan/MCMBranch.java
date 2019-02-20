package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class MCMBranch
    extends PlanMenuBranch
    implements Comparable
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMBranch.class);
    protected
    List
        children =
        null;
    private
    Integer
        mCMId =
        null;
    private
    Integer
        number =
        null;

    public MCMBranch(MCMValue mcm)
    {
        this.mCMId =
            mcm.getId();
        this.number =
            mcm.getNumber();
        setName(mcm.getNumber()
                + ". "
                + mcm.getName()
                + (this.getChildren()
                   != null
                       ? "&nbsp;("
                         + this.getChildren()
            .size()
                         + ")"
                       : ""));
        setType(BranchConstants.MCM_TYPE);
        setTypeId(mCMId);
        setUrl(BranchConstants.SUBNODE_URL
               + "?"
               + RequestKeys.NODE_ID
               + "="
               + getID());
        setParentId(mcm.getPlanId()
                        .toString());
    }

    public Integer getMCMId()
    {
        return mCMId;
    }

    public void setMCMId(Integer mCMId)
    {
        this.mCMId =
            mCMId;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public void setChildren(List children)
    {
        this.children =
            children;
    }

    public List getChildren()
    {
        if (children
            == null)
        {
            children =
                new ArrayList();
            getBranchList();
        }
        return children;
    }

    public List getBranchList()
    {
        TreeSet
            branches =
            new TreeSet();
        try
        {
            List
                bmpList =
                PlanService.getBMPValues(new MCMValue(mCMId,
                                                      getName(),
                                                      number));
            Iterator
                i =
                bmpList.iterator();
            while (i.hasNext())
            {
                BMPValue
                    value =
                    (BMPValue) i.next();
                branches.add(new BMPBranch(value));
            }
            children =
                new ArrayList(branches);
        }
        catch (Exception ex)
        {
            LOG.debug("Exception: ",
                      ex);
        }
        return children;
    }

    public int compareTo(Object obj)
    {
        int
            numberCompare =
            0;
        MCMBranch
            branch;
        if (obj
            != null
            && obj instanceof MCMBranch)
        {
            branch =
                (MCMBranch) obj;
            numberCompare =
                (getNumber()
                 != null)
                    ? getNumber().compareTo(branch.getNumber())
                    : numberCompare;
            if (numberCompare
                == 0)
            {
                numberCompare =
                    (getName()
                     != null)
                        ? getName().compareTo(branch.getName())
                        : numberCompare;
            }
            if (numberCompare
                == 0)
            {
                numberCompare =
                    (getMCMId()
                     != null)
                        ? getMCMId().compareTo(branch.getMCMId())
                        : numberCompare;
            }
        }
        return numberCompare;
    }

    public String getParentType()
    {
        return BranchConstants.PLAN_TYPE;
    }
}
