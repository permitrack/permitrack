package com.sehinc.stormwater.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PrimaryMenu
    implements INavMenu
{
    private static
    Logger
        LOG =
        Logger.getLogger(PrimaryMenu.class);
    public static final
    String
        SYSTEM_ADMIN_MENU_NAME =
        "SYSTEM_ADMIN_MENU";
    public static final
    String
        SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_ADMIN_MENU_ITEM";
    public static final
    String
        SYSTEM_ADMIN_PERMIT_PERIOD_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_PERMIT_PERIOD_MENU_ITEM";
    public static final
    String
        SYSTEM_ADMIN_TEMPLATE_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_TEMPLATE_MENU_ITEM";
    public static final
    String
        SYSTEM_ADMIN_BMPDB_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_BMPDB_MENU_ITEM";
    public static final
    String
        CLIENT_MENU_NAME =
        "CLIENT_MENU";
    public static final
    String
        CLIENT_PLAN_MENU_ITEM_NAME =
        "CLIENT_PLAN_MENU_ITEM";
    private
    String
        name =
        null;
    private
    String
        description =
        null;
    private
    int
        minSecurityLevel =
        0;
    private
    TreeSet
        primaryMenu =
        null;
    private
    PrimaryMenuItem
        currentItem =
        null;

    private PrimaryMenu(String name, String description, int minSecurityLevel)
    {
        this.name =
            name;
        this.description =
            description;
        this.minSecurityLevel =
            minSecurityLevel;
    }

    public static PrimaryMenu getInstance(String primaryMenuName, int id)
    {
        return getInstance(primaryMenuName,
                           id,
                           null);
    }

    public static PrimaryMenu getInstance(String primaryMenuName, int id, String clientName)
    {
        if (primaryMenuName.equals(SYSTEM_ADMIN_MENU_NAME))
        {
            PrimaryMenu
                systemAdminMenu =
                new PrimaryMenu(SYSTEM_ADMIN_MENU_NAME,
                                "System Admin",
                                SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL);
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME,
                                                    "Clients",
                                                    "/html/ms4/admin",
                                                    "/adminclientlistaction.do",
                                                    1,
                                                    SecurityManager.USER_SECURITY_LEVEL));
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_PERMIT_PERIOD_MENU_ITEM_NAME,
                                                    "Periods",
                                                    "/html/ms4/permitperiod",
                                                    "/permitperiodviewaction.do",
                                                    2,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_TEMPLATE_MENU_ITEM_NAME,
                                                    "Plan Templates",
                                                    "/html/ms4/template",
                                                    "/plantemplatelistaction.do",
                                                    3,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_BMPDB_MENU_ITEM_NAME,
                                                    "BMP Library",
                                                    "/html/ms4/bmpdb",
                                                    "/bmpdblistpageaction.do",
                                                    4,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return systemAdminMenu;
        }
        else
        {
            if (primaryMenuName.equals(CLIENT_MENU_NAME))
            {
                PrimaryMenu
                    clientMenu =
                    new PrimaryMenu(CLIENT_MENU_NAME,
                                    "Client",
                                    SecurityManager.USER_SECURITY_LEVEL);
                clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_PLAN_MENU_ITEM_NAME,
                                                   "Plans",
                                                   "/html/ms4/plan",
                                                   "/planlistaction.do?id="
                                                   + id,
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL));
                if (clientName
                    != null && !clientName.isEmpty())
                {
                    clientMenu.add(new PrimaryMenuItem(EXIT_MENU_ITEM,
                                                       "Exit "
                                                       + (clientName.length() <= 15 ? clientName : clientName.substring(0, 14) + "..."),
                                                       "/html/ms4/admin",
                                                       "/remoteadminreturn.do",
                                                       2,
                                                       SecurityManager.USER_SECURITY_LEVEL));
                }

                return clientMenu;
            }
            else
            {
                LOG.error("Could not locate Primary Menu "
                          + primaryMenuName);
            }
        }
        return null;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setMinSecurityLevel(int minSecurityLevel)
    {
        this.minSecurityLevel =
            minSecurityLevel;
    }

    public int getMinSecurityLevel()
    {
        return minSecurityLevel;
    }

    public void add(PrimaryMenuItem primaryMenuItem)
    {
        if (primaryMenu
            == null)
        {
            primaryMenu =
                new TreeSet();
        }
        primaryMenu.add(primaryMenuItem);
    }

    public PrimaryMenuItem remove(PrimaryMenuItem primaryMenuItem)
    {
        PrimaryMenuItem
            pmi;
        if (primaryMenu
            == null)
        {
            return null;
        }
        Iterator
            iter =
            primaryMenu.iterator();
        while (iter.hasNext())
        {
            pmi =
                (PrimaryMenuItem) iter.next();
            if (pmi.getName()
                .equals(primaryMenuItem.getName()))
            {
                primaryMenu.remove(pmi);
                if (!primaryMenu.isEmpty())
                {
                    currentItem =
                        (PrimaryMenuItem) primaryMenu.first();
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

    public void setCurrentItem(String itemName)
    {
        Iterator
            iter =
            primaryMenu.iterator();
        while (iter.hasNext())
        {
            PrimaryMenuItem
                pmi =
                (PrimaryMenuItem) iter.next();
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
                (PrimaryMenuItem) primaryMenu.first();
        }
    }

    public MenuItem getCurrentItem()
    {
        if (currentItem
            == null)
        {
            if (!primaryMenu.isEmpty())
            {
                return (PrimaryMenuItem) primaryMenu.first();
            }
        }
        return currentItem;
    }

    public List getMenuItems()
    {
        return new ArrayList(primaryMenu);
    }

    @Override
    public int getItemCount()
    {
        if (primaryMenu
            == null)
        {
            return 0;
        }
        return primaryMenu.size();
    }
}