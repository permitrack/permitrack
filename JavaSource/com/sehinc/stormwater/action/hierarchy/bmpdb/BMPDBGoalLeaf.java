package com.sehinc.stormwater.action.hierarchy.bmpdb;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuLeaf;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BMPDBGoalLeaf
    extends TreeViewMenuLeaf
    implements Comparable
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBGoalLeaf.class);
*/

    private
    Integer
        bmpDbGoalId =
        null;
    private
    Integer
        bmpDbNumber =
        null;

    public BMPDBGoalLeaf(BMPDBGoalValue bmpDbGoal)
    {
        this.bmpDbGoalId =
            bmpDbGoal.getId();
        this.bmpDbNumber =
            bmpDbGoal.getNumber();
        setName(bmpDbGoal.getName());
        setType("bmpDbGoal");
        setTypeId(bmpDbGoalId);
        setUrl("/ms4/bmpdb/goallibraryviewaction.do?"
               + RequestKeys.BMP_DB_GOAL_ID
               + "="
               + bmpDbGoalId);
    }

    public Integer getBmpDbGoalId()
    {
        return bmpDbGoalId;
    }

    public Integer getNumber()
    {
        return bmpDbNumber;
    }

    public void setNumber(Integer bmpDbNumber)
    {
        this.bmpDbNumber =
            bmpDbNumber;
    }

    public int compareTo(Object obj)
    {
        int
            numberCompare;
        BMPDBGoalLeaf
            branch;
        if (obj instanceof BMPDBGoalLeaf)
        {
            branch =
                (BMPDBGoalLeaf) obj;
            numberCompare =
                getNumber().compareTo(branch.getNumber());
        }
        else
        {
            return 0;
        }
        if (numberCompare
            == 0)
        {
            return getName().compareTo(branch.getName());
        }
        return numberCompare;
    }
}
