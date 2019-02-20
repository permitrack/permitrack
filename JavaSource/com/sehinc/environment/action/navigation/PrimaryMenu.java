package com.sehinc.environment.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
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
        SYSTEM_ADMIN_SCC_MENU_ITEM_NAME =
        "SYSTEM_ADMIN_SCC_MENU_ITEM";
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
        CLIENT_READINGS_MENU_ITEM =
        "CLIENT_READINGS_MENU_ITEM";
    public static final
    String
        CLIENT_REPORTS_MENU_ITEM =
        "CLIENT_REPORTS_MENU_ITEM";
    public static final
    String
        CLIENT_PERMIT_MENU_ITEM_NAME =
        "CLIENT_PERMIT_MENU_ITEM";
    public static final
    String
        CLIENT_FACILITY_MENU_ITEM_NAME =
        "CLIENT_FACILITY_MENU_ITEM_NAME";
    public static final
    String
        CLIENT_SOURCE_MENU_ITEM_NAME =
        "CLIENT_SOURCE_MENU_ITEM_NAME";
    public static final
    String
        CLIENT_SUBSTANCE_MENU_ITEM_NAME =
        "CLIENT_SUBSTANCE_MENU_ITEM";
    public static final
    String
        CLIENT_SCC_MENU_ITEM_NAME =
        "CLIENT_SCC_MENU_ITEM_NAME";
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
    TreeSet<PrimaryMenuItem>
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
            systemAdminMenu.add(new PrimaryMenuItem(SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME,
                                                    "Clients",
                                                    "/html/env/admin",
                                                    "/adminclientlistaction.do",
                                                    1,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.CLIENT_READ));
            systemAdminMenu.add(new PrimaryMenuItem(SYSTEM_ADMIN_SCC_MENU_ITEM_NAME,
                                                    "SCC Library",
                                                    "/html/env/scclibrary",
                                                    "/scclibrarylistpage.do",
                                                    2,
                                                    SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.EV_SCC_LIBRARY_READ));
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
            clientMenu.add(new PrimaryMenuItem(CLIENT_PERMIT_MENU_ITEM_NAME,
                                               "Permits",
                                               "/html/env/permit",
                                               "/permitlistpage.do",
                                               1,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_PERMIT_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_FACILITY_MENU_ITEM_NAME,
                                               "Facilities",
                                               "/html/env/facility",
                                               "/facilitylistpage.do",
                                               2,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_FACILITY_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_SOURCE_MENU_ITEM_NAME,
                                               "Sources",
                                               "/html/env/source",
                                               "/sourcelistpage.do",
                                               3,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_SOURCE_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_SUBSTANCE_MENU_ITEM_NAME,
                                               "Substances",
                                               "/html/env/substance",
                                               "/substancelistpage.do",
                                               4,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_SUBSTANCE_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_SCC_MENU_ITEM_NAME,
                                               "SCCs",
                                               "/html/env/scc",
                                               "/scclistpage.do",
                                               5,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_SCC_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_READINGS_MENU_ITEM,
                                               "Readings",
                                               "/html/env/sourceusage",
                                               "/sourceusagereadingspage.do",
                                               6,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
            clientMenu.add(new PrimaryMenuItem(CLIENT_REPORTS_MENU_ITEM,
                                               "Reports",
                                               "/html/env/report",
                                               "/reportoptionspage.do",
                                               7,
                                               SecurityManager.USER_SECURITY_LEVEL,
                                               SecureObjectPermissionData.EV_REPORT_READ));
            if (clientName
                != null && !clientName.isEmpty())
            {
                clientMenu.add(new PrimaryMenuItem(EXIT_MENU_ITEM,
                                                   "Exit "
                                                   + (clientName.length() <= 15 ? clientName : clientName.substring(0, 14) + "..."),
                                                   "/html/env/admin",
                                                   "/remoteadminreturn.do",
                                                   8,
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
                new TreeSet<PrimaryMenuItem>();
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
        for (PrimaryMenuItem aPrimaryMenu : primaryMenu)
        {
            pmi =
                aPrimaryMenu;
            if (pmi.getName()
                .equals(primaryMenuItem.getName()))
            {
                primaryMenu.remove(pmi);
                if (!primaryMenu.isEmpty())
                {
                    currentItem =
                        primaryMenu.first();
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
            for (PrimaryMenuItem aPrimaryMenu : primaryMenu)
            {
                if (aPrimaryMenu.getName()
                    .equals(itemName))
                {
                    currentItem =
                        aPrimaryMenu;
                    break;
                }
            }
            if (currentItem
                == null)
            {
                currentItem =
                    primaryMenu.first();
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
                return primaryMenu.first();
            }
        }
        return currentItem;
    }

    public List<PrimaryMenuItem> getMenuItems()
    {
        return new ArrayList<PrimaryMenuItem>(primaryMenu);
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