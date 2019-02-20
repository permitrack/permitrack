package com.sehinc.erosioncontrol.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
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
        SYSTEM_ADMIN_REPORT_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_REPORT_MENU_ITEM";
    public static final
    String
        SYSTEM_ADMIN_BMP_LIBRARY_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_BMP_LIBRARY_MENU_ITEM_NAME";
    public static final
    String
        CLIENT_MENU_NAME =
        "CLIENT_MENU";
    public static final
    String
        CLIENT_NONE_MENU_ITEM_NAME =
        "CLIENT_NONE_MENU_ITEM";
    public static final
    String
        CLIENT_PROJECT_MENU_ITEM_NAME =
        "CLIENT_PROJECT_MENU_ITEM";
    public static final
    String
        CLIENT_PARTNER_MENU_ITEM_NAME =
        "CLIENT_PARTNER_MENU_ITEM";
    public static final
    String
        CLIENT_REPORT_MENU_ITEM_NAME =
        "CLIENT_REPORT_MENU_ITEM";
    public static final
    String
        EVENT_LIST_MENU_ITEM_NAME =
        "EVENT_LIST_MENU_ITEM_NAME";
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

    public static PrimaryMenu getInstance(String primaryMenuName)
    {
        return getInstance(primaryMenuName,
                           null);
    }

    public static PrimaryMenu getInstance(String primaryMenuName, String clientName)
    {
        PrimaryMenu
            objReturnPrimaryMenu;
        if (primaryMenuName.equals(SYSTEM_ADMIN_MENU_NAME))
        {
            PrimaryMenu
                systemAdminMenu =
                new PrimaryMenu(SYSTEM_ADMIN_MENU_NAME,
                                "System Admin",
                                SecurityManager.USER_SECURITY_LEVEL);
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME,
                                                    "Clients",
                                                    "/html/esc/admin",
                                                    "/adminclientlistaction.do",
                                                    1,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.PROJECT_READ));
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_REPORT_MENU_ITEM_NAME,
                                                    "Reports",
                                                    "/html/esc/report",
                                                    "/adminreportoptionspage.do",
                                                    2,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.REPORT_READ));
            systemAdminMenu.add(new PrimaryMenuItem(PrimaryMenu.SYSTEM_ADMIN_BMP_LIBRARY_MENU_ITEM_NAME,
                                                    "BMP Library",
                                                    "/html/esc/bmpdb",
                                                    "/adminbmplibrarypage.do",
                                                    3,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.BMP_LIBRARY_READ));
            objReturnPrimaryMenu =
                systemAdminMenu;
        }
        else if (primaryMenuName.equals(CLIENT_MENU_NAME))
        {
            PrimaryMenu
                clientMenu =
                new PrimaryMenu(CLIENT_MENU_NAME,
                                "Client",
                                SecurityManager.USER_SECURITY_LEVEL);
            clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME,
                                               "Projects",
                                               "/html/esc/project",
                                               "/projectlistpage.do",
                                               1,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.PROJECT_READ));
            clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_PARTNER_MENU_ITEM_NAME,
                                               "Partners",
                                               "/html/esc/partner",
                                               "/partnerlistpage.do",
                                               2,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.CLIENT_READ));
            clientMenu.add(new PrimaryMenuItem(PrimaryMenu.EVENT_LIST_MENU_ITEM_NAME,
                                               "Events",
                                               "/html/esc/event",
                                               "/eventListPageAction.do",
                                               3,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EVENT_READ));
            /*
                        projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.EVENT_LIST_MENU_ITEM_NAME,
                                                                  "Events",
                                                                  "/html/esc/event",
                                                                  "/eventListPageAction.do",
                                                                  3,
                                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                                  SecureObjectPermissionData.EVENT_READ));
            */
            /*
                        clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_BMP_MENU_ITEM_NAME,
                                                           "BMPs",
                                                           "/html/esc/bmp",
                                                           "/bmplistpage.do",
                                                           4,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.BMP_READ));
                        clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_INSPECTION_TEMPLATE_MENU_ITEM_NAME,
                                                           "Templates",
                                                           "/html/esc/inspection",
                                                           "/inspectiontemplatelistpage.do",
                                                           5,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.BMP_TEMPLATE_READ));
            */
            clientMenu.add(new PrimaryMenuItem(PrimaryMenu.CLIENT_REPORT_MENU_ITEM_NAME,
                                               "Reports",
                                               "/html/esc/report",
                                               "/reportoptionspage.do",
                                               4,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.REPORT_READ));
            clientMenu.add(new PrimaryMenuItem(OPTIONS_LIST_MENU_ITEM,
                                               "Settings",
                                               "/html/esc/project",
                                               "/projecttypelistpage.do",
                                               5,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.PROJECT_READ));
            if (clientName
                != null && !clientName.isEmpty())
            {
                clientMenu.add(new PrimaryMenuItem(EXIT_MENU_ITEM,
                                                   "Exit "
                                                   + (clientName.length() <= 15 ? clientName : clientName.substring(0, 14) + "..."),
                                                   "/html/esc/admin",
                                                   "/remoteadminreturn.do",
                                                   7,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_READ));
            }
            objReturnPrimaryMenu =
                clientMenu;
        }
        else
        {
            LOG.error("Could not locate Primary Menu "
                      + primaryMenuName);
            objReturnPrimaryMenu =
                null;
        }
        return objReturnPrimaryMenu;
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
        if (!itemName.equals(CLIENT_NONE_MENU_ITEM_NAME))
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
        else
        {
            currentItem =
                new PrimaryMenuItem();
            currentItem.setName(CLIENT_NONE_MENU_ITEM_NAME);
            currentItem.setDescription(CLIENT_NONE_MENU_ITEM_NAME);
            currentItem.setLocation("");
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