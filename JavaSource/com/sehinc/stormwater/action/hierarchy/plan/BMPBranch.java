package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.common.util.StringUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class BMPBranch
    extends PlanMenuBranch
    implements Comparable
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPBranch.class);
    protected
    List
        children =
        null;
    private
    Integer
        bmpId =
        null;
    private
    Integer
        bmpNumber =
        null;
    private
    String
        section =
        null;

    public BMPBranch(BMPValue bmp)
    {
        this.bmpId =
            bmp.getId();
        this.bmpNumber =
            bmp.getNumber();
        this.section =
            bmp.getSection();
        String
            sect =
            (bmp.getSection()
             != null
                 ? bmp.getSection()
                .replaceAll("null",
                            "")
                 : "");
        setName((!sect.isEmpty()
                     ? sect
                       + "-"
                     : "")
                + bmp.getNumber()
                + ". "
                + bmp.getName()
                + (this.getChildren()
                   != null
                       ? "&nbsp;("
                         + this.getChildren()
            .size()
                         + ")"
                       : ""));
        setType(BranchConstants.BMP_TYPE);
        setTypeId(bmpId);
        setUrl(BranchConstants.SUBNODE_URL
               + "?"
               + RequestKeys.NODE_ID
               + "="
               + getID());
        setParentId(bmp.getMcmId()
                        .toString());
    }

    public Integer getBMPId()
    {
        return bmpId;
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

    public Integer getNumber()
    {
        return bmpNumber;
    }

    public void setNumber(Integer number)
    {
        bmpNumber =
            number;
    }

    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section =
            section;
    }

    protected List getBranchList()
    {
        TreeSet
            branches =
            new TreeSet();
        try
        {
            List
                goalList =
                PlanService.getGoalValues(new BMPValue(bmpId,
                                                       getName(),
                                                       bmpNumber,
                                                       null));
            Iterator
                i =
                goalList.iterator();
            while (i.hasNext())
            {
                GoalValue
                    value =
                    (GoalValue) i.next();
                branches.add(new GoalBranch(value));
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
            compareValue =
            0;
        BMPBranch
            branch;
        if (obj
            != null
            && obj instanceof BMPBranch)
        {
            branch =
                (BMPBranch) obj;
            if ((!StringUtil.isEmpty(getSection()))
                && (!StringUtil.isEmpty(branch.getSection())))
            {
                compareValue =
                    getSection().compareTo(branch.getSection());
            }
            if (compareValue
                == 0)
            {
                compareValue =
                    (getNumber()
                     != null)
                        ? getNumber().compareTo(branch.getNumber())
                        : compareValue;
                if (compareValue
                    == 0)
                {
                    compareValue =
                        (getName()
                         != null)
                            ? getName().compareTo(branch.getName())
                            : compareValue;
                }
            }
        }
        return compareValue;
    }

    public String getParentType()
    {
        return BranchConstants.MCM_TYPE;
    }
}
