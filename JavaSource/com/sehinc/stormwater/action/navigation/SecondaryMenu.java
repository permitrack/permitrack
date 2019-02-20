package com.sehinc.stormwater.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.stormwater.action.base.RequestKeys;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class SecondaryMenu
    implements INavMenu
{
    private static
    Logger
        LOG =
        Logger.getLogger(SecondaryMenu.class);
    public static final
    String
        PLAN_MENU_NAME =
        "PLAN_MENU";
    public static final
    String
        PLAN_NEW_MENU_ITEM_NAME =
        "PLAN_NEW_MENU_ITEM";
    public static final
    String
        PLAN_SELECT_MENU_ITEM_NAME =
        "PLAN_SELECT_MENU_ITEM";
    public static final
    String
        PLAN_VIEW_MENU_ITEM_NAME =
        "PLAN_VIEW_MENU_ITEM";
    public static final
    String
        CLIENT_REPORT_MENU_ITEM_NAME =
        "CLIENT_REPORT_MENU_ITEM";
    public static final
    String
        PLAN_PUB_MENU_ITEM_NAME =
        "PLAN_PUB_MENU_ITEM";
    public static final
    String
        DEFAULT_PLAN_MENU_NAME =
        "DEFAULT_PLAN_MENU";
    public static final
    String
        ADMIN_ADMIN_MENU_NAME =
        "ADMIN_ADMIN_MENU";
    public static final
    String
        ADMIN_CLIENT_SELECT_MENU_ITEM_NAME =
        "ADMIN_CLIENT_SELECT_MENU_ITEM";
    public static final
    String
        TEMPLATE_MENU_NAME =
        "TEMPLATE_MENU";
    public static final
    String
        TEMPLATE_SELECT_MENU_ITEM_NAME =
        "TEMPLATE_SELECT_MENU_ITEM";
    public static final
    String
        TEMPLATE_VIEW_MENU_ITEM_NAME =
        "TEMPLATE_VIEW_MENU_ITEM";
    public static final
    String
        TEMPLATE_EDIT_MENU_ITEM_NAME =
        "TEMPLATE_EDIT_MENU_ITEM";
    public static final
    String
        TEMPLATE_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_DELETE_MENU_ITEM";
    public static final
    String
        TEMPLATE_ADD_MCM_MENU_ITEM_NAME =
        "TEMPLATE_ADD_MCM_MENU_ITEM";
    public static final
    String
        DEFAULT_TEMPLATE_MENU_NAME =
        "DEFAULT_TEMPLATE_MENU";
    public static final
    String
        TEMPLATE_GOAL_VIEW_MENU_ITEM_NAME =
        "TEMPLATE_GOAL_VIEW_MENU_ITEM";
    public static final
    String
        TEMPLATE_GOAL_EDIT_MENU_ITEM_NAME =
        "TEMPLATE_GOAL_EDIT_MENU_ITEM";
    public static final
    String
        TEMPLATE_GOAL_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_GOAL_DELETE_MENU_ITEM";
    public static final
    String
        DEFAULT_BMPDB_MENU_NAME =
        "DEFAULT_BMPDB_MENU";
    public static final
    String
        BMPDB_MENU_NAME =
        "BMPDB_MENU";
    public static final
    String
        BMPDB_SELECT_MENU_ITEM_NAME =
        "BMPDB_SELECT_MENU_ITEM";
    public static final
    String
        BMPDB_NEW_MENU_ITEM_NAME =
        "BMPDB_NEW_MENU_ITEM";
    public static final
    String
        BMPDB_VIEW_MENU_ITEM_NAME =
        "BMPDB_VIEW_MENU_ITEM";
    public static final
    String
        PERMIT_PERIOD_MENU_NAME =
        "PERMIT_PERIOD_MENU";
    public static final
    String
        PERMIT_PERIOD_NEW_MENU_ITEM_NAME =
        "PERMIT_PERIOD_NEW_MENU_ITEM";
    public static final
    String
        PERMIT_PERIOD_VIEW_MENU_ITEM_NAME =
        "PERMIT_PERIOD_VIEW_MENU_ITEM";
    private
    String
        name =
        null;
    private
    String
        description =
        null;
    private
    TreeSet
        secondaryMenu =
        new TreeSet();
    private
    SecondaryMenuItem
        currentItem =
        null;

    private SecondaryMenu(String name, String description)
    {
        this.name =
            name;
        this.description =
            description;
    }

    public static SecondaryMenu getInstance(String secondaryMenuName, int id)
    {
        if (secondaryMenuName.equals(PLAN_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(PLAN_MENU_NAME,
                                  "Plan");
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/plan",
                                           "/planlistaction.do?id="
                                           + id,
                                           1,
                                           SecurityManager.USER_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_NEW_MENU_ITEM_NAME,
                                           "New",
                                           "/html/ms4/plan",
                                           "/plannewaction.do?id="
                                           + id,
                                           2,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_VIEW_MENU_ITEM_NAME,
                                           "View",
                                           "/html/ms4/plan",
                                           "/planviewaction.do?id="
                                           + id,
                                           3,
                                           SecurityManager.USER_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.CLIENT_REPORT_MENU_ITEM_NAME,
                                           "Reports",
                                           "/html/ms4/report",
                                           "/reportoptionsaction.do?id="
                                           + id,
                                           4,
                                           SecurityManager.USER_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_PUB_MENU_ITEM_NAME,
                                           "Publish",
                                           "/html/ms4/plan",
                                           "/planpublishaction.do?id="
                                           + id,
                                           5,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(DEFAULT_PLAN_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(DEFAULT_PLAN_MENU_NAME,
                                  "Plan");
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/plan",
                                           "/planlistaction.do?id="
                                           + id,
                                           1,
                                           SecurityManager.USER_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.PLAN_NEW_MENU_ITEM_NAME,
                                           "New",
                                           "/html/ms4/plan",
                                           "/plannewaction.do?id="
                                           + id,
                                           2,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(ADMIN_ADMIN_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(ADMIN_ADMIN_MENU_NAME,
                                  "Admin");
            menu.add(new SecondaryMenuItem(SecondaryMenu.ADMIN_CLIENT_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/admin",
                                           "/adminclientlistaction.do",
                                           1,
                                           SecurityManager.USER_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(TEMPLATE_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(TEMPLATE_MENU_NAME,
                                  "Template");
            menu.add(new SecondaryMenuItem(SecondaryMenu.TEMPLATE_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/template",
                                           "/plantemplatelistaction.do",
                                           1,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.TEMPLATE_VIEW_MENU_ITEM_NAME,
                                           "View",
                                           "/html/ms4/template",
                                           "/plantemplateviewaction.do?id="
                                           + id,
                                           2,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(DEFAULT_TEMPLATE_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(DEFAULT_TEMPLATE_MENU_NAME,
                                  "Template");
            menu.add(new SecondaryMenuItem(SecondaryMenu.TEMPLATE_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/template",
                                           "/plantemplatelistaction.do",
                                           1,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(DEFAULT_BMPDB_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(DEFAULT_BMPDB_MENU_NAME,
                                  "Default BMP Libary");
            menu.add(new SecondaryMenuItem(SecondaryMenu.BMPDB_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/bmpdb",
                                           "/bmpdblistpageaction.do",
                                           1,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.BMPDB_NEW_MENU_ITEM_NAME,
                                           "New",
                                           "/html/ms4/bmpdb",
                                           "/bmpdbnewaction.do",
                                           2,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(BMPDB_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(BMPDB_MENU_NAME,
                                  "BMP Library");
            menu.add(new SecondaryMenuItem(SecondaryMenu.BMPDB_SELECT_MENU_ITEM_NAME,
                                           "Select",
                                           "/html/ms4/bmpdb",
                                           "/bmpdblistpageaction.do",
                                           1,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.BMPDB_NEW_MENU_ITEM_NAME,
                                           "New",
                                           "/html/ms4/bmpdb",
                                           "/bmpdbnewaction.do",
                                           2,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.BMPDB_VIEW_MENU_ITEM_NAME,
                                           "View",
                                           "/html/ms4/bmpdb",
                                           "/bmpdbviewaction.do?"
                                           + RequestKeys.BMP_DB_ID
                                           + "="
                                           + id,
                                           3,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else if (secondaryMenuName.equals(PERMIT_PERIOD_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(PERMIT_PERIOD_MENU_NAME,
                                  "Permit Period");
            menu.add(new SecondaryMenuItem(SecondaryMenu.PERMIT_PERIOD_VIEW_MENU_ITEM_NAME,
                                           "View",
                                           "/html/ms4/permitperiod",
                                           "/permitperiodviewaction.do",
                                           1,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            menu.add(new SecondaryMenuItem(SecondaryMenu.PERMIT_PERIOD_NEW_MENU_ITEM_NAME,
                                           "New",
                                           "/html/ms4/permitperiod",
                                           "/permitperiodnewaction.do",
                                           2,
                                           SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return menu;
        }
        else
        {
            LOG.error("Could not locate Secondary Menu "
                      + secondaryMenuName);
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

    @SuppressWarnings("unchecked")
    public void add(SecondaryMenuItem secondaryMenuItem)
    {
        secondaryMenu.add(secondaryMenuItem);
    }

    public SecondaryMenuItem remove(SecondaryMenuItem secondaryMenuItem)
    {
        SecondaryMenuItem
            smi;
        if (secondaryMenu
            == null
            || secondaryMenu.isEmpty())
        {
            return null;
        }
        Iterator
            iter =
            secondaryMenu.iterator();
        while (iter.hasNext())
        {
            smi =
                (SecondaryMenuItem) iter.next();
            if (smi.getName()
                .equals(secondaryMenuItem.getName()))
            {
                secondaryMenu.remove(smi);
                if (!secondaryMenu.isEmpty())
                {
                    currentItem =
                        (SecondaryMenuItem) secondaryMenu.first();
                }
                else
                {
                    currentItem =
                        null;
                }
                return smi;
            }
        }
        return null;
    }

    public void setCurrentItem(String itemName)
    {
        Iterator
            iter =
            secondaryMenu.iterator();
        while (iter.hasNext())
        {
            SecondaryMenuItem
                smi =
                (SecondaryMenuItem) iter.next();
            if (smi.getName()
                .equals(itemName))
            {
                currentItem =
                    smi;
                return;
            }
        }
        currentItem =
            null;
    }

    public MenuItem getCurrentItem()
    {
        if (currentItem
            == null)
        {
            if (!secondaryMenu.isEmpty())
            {
                currentItem =
                    (SecondaryMenuItem) secondaryMenu.first();
            }
        }
        return currentItem;
    }

    @SuppressWarnings("unchecked")
    public List getMenuItems()
    {
        return new ArrayList(secondaryMenu);
    }

    public int getItemCount()
    {
        if (secondaryMenu
            == null)
        {
            return 0;
        }
        return secondaryMenu.size();
    }
}