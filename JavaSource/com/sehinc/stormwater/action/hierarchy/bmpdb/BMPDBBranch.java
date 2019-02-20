package com.sehinc.stormwater.action.hierarchy.bmpdb;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuBranch;
import com.sehinc.stormwater.server.bmpdb.BMPDBService;
import com.sehinc.stormwater.value.bmpdb.BMPDBGoalValue;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BMPDBBranch
    extends TreeViewMenuBranch
{
    /*
        private static
        Logger
            LOG =
            Logger.getLogger(BMPDBBranch.class);
    */
    private
    List
        children =
        null;
    private
    Integer
        bmpDbId =
        null;

    public BMPDBBranch(BMPDBValue bmpDb)
    {
        this.bmpDbId =
            bmpDb.getId();
        setName(bmpDb.getName()
                + (this.getChildren()
                   != null
                       ? "&nbsp;("
                         + this.getChildren()
            .size()
                         + ")"
                       : ""));
        setType("bmpDb");
        setTypeId(bmpDbId);
        setUrl("/ms4/bmpdb/bmpdbviewaction.do?"
               + RequestKeys.BMP_DB_ID
               + "="
               + bmpDbId);
    }
    /*
        public Integer getBMPDBId()
        {
            return bmpDbId;
        }
    */

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
        List
            goalList =
            BMPDBService.getGoalValues(new BMPDBValue(bmpDbId,
                                                      getName()));
        Iterator
            i =
            goalList.iterator();
        while (i.hasNext())
        {
            BMPDBGoalValue
                value =
                (BMPDBGoalValue) i.next();
            boolean
                valueFound =
                false;
            Iterator
                j =
                children.iterator();
            while (j.hasNext())
            {
                BMPDBGoalLeaf
                    branchNode =
                    (BMPDBGoalLeaf) j.next();
                if (value.getId()
                    .equals(branchNode.getBmpDbGoalId()))
                {
                    branchNode.setName(value.getName());
                    branchNode.setNumber(value.getNumber());
                    branches.add(branchNode);
                    valueFound =
                        true;
                    break;
                }
            }
            if (!valueFound)
            {
                branches.add(new BMPDBGoalLeaf(value));
            }
        }
        children =
            new ArrayList(branches);
        return children;
    }
}
