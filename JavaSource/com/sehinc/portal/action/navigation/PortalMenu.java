package com.sehinc.portal.action.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PortalMenu
{
    private
    TreeSet
        portalMenu =
        null;
    private
    PortalMenuItem
        currentItem =
        null;

    public PortalMenu()
    {
    }

    public void add(PortalMenuItem PortalMenuItem)
    {
        if (portalMenu
            == null)
        {
            portalMenu =
                new TreeSet();
        }
        portalMenu.add(PortalMenuItem);
    }

    public PortalMenuItem remove(PortalMenuItem PortalMenuItem)
    {
        PortalMenuItem
            pmi;
        if (portalMenu
            == null)
        {
            return null;
        }
        Iterator
            iter =
            portalMenu.iterator();
        while (iter.hasNext())
        {
            pmi =
                (PortalMenuItem) iter.next();
            if (pmi.getName()
                .equals(PortalMenuItem.getName()))
            {
                portalMenu.remove(pmi);
                if (!portalMenu.isEmpty())
                {
                    currentItem =
                        (PortalMenuItem) portalMenu.first();
                }
                else
                {
                    currentItem =
                        null;
                }
                return pmi;
            }
        }
        return null;
    }

    public void setCurrentItemByName(String itemName)
    {
        Iterator
            iter =
            portalMenu.iterator();
        while (iter.hasNext())
        {
            PortalMenuItem
                pmi =
                (PortalMenuItem) iter.next();
            if (pmi.getName()
                .equals(itemName))
            {
                currentItem =
                    pmi;
                break;
            }
        }
        if (currentItem
            == null)
        {
            currentItem =
                (PortalMenuItem) portalMenu.first();
        }
    }

    public void setCurrentItemByCode(String itemCode)
    {
        Iterator
            iter =
            portalMenu.iterator();
        while (iter.hasNext())
        {
            PortalMenuItem
                pmi =
                (PortalMenuItem) iter.next();
            if (pmi.getCode()
                .equals(itemCode))
            {
                currentItem =
                    pmi;
                break;
            }
        }
        if (currentItem
            == null)
        {
            currentItem =
                (PortalMenuItem) portalMenu.first();
        }
    }

    public PortalMenuItem getCurrentItem()
    {
        if (currentItem
            == null)
        {
            if (!portalMenu.isEmpty())
            {
                return (PortalMenuItem) portalMenu.first();
            }
        }
        return currentItem;
    }

    public void setCurrentItem(PortalMenuItem currentItem)
    {
        this.currentItem =
            currentItem;
    }

    public List getMenuItems()
    {
        return new ArrayList(portalMenu);
    }

    public boolean isEmpty()
    {
        return portalMenu
               == null
               || portalMenu.isEmpty();
    }

    public int size()
    {
        if (portalMenu
            != null)
        {
            return portalMenu.size();
        }
        return 0;
    }

    public PortalMenuItem last()
    {
        if (!portalMenu.isEmpty())
        {
            return (PortalMenuItem) portalMenu.last();
        }
        return null;
    }
}
