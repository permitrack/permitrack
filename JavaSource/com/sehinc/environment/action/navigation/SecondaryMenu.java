package com.sehinc.environment.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SecondaryMenu
{
    private static
    Logger
        LOG =
        Logger.getLogger(SecondaryMenu.class);
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
        CLIENT_NONE_MENU_ITEM_NAME =
        "CLIENT_NONE_MENU_ITEM";
    public static final
    String
        CLIENT_READINGS_MENU_NAME =
        "CLIENT_READINGS_MENU";
    public static final
    String
        CLIENT_SOURCE_USAGE_MENU_ITEM_NAME =
        "CLIENT_SOURCE_USAGE_MENU_ITEM_NAME";
    public static final
    String
        CLIENT_CONTROL_USAGE_MENU_ITEM_NAME =
        "CLIENT_CONTROL_USAGE_MENU_ITEM_NAME";
    public static final
    String
        FACILITY_VIEW_MENU =
        "FACILITY_VIEW_MENU";
    public static final
    String
        PROCESS_LIST_MENU_ITEM =
        "PROCESS_LIST_MENU_ITEM";
    public static final
    String
        ASSET_LIST_MENU_ITEM =
        "ASSET_LIST_MENU_ITEM";
    public static final
    String
        FACILITY_LIST_MENU =
        "FACILITY_LIST_MENU";
    public static final
    String
        SUBSTANCE_LIST_MENU =
        "SUBSTANCE_LIST_MENU";
    public static final
    String
        SUBSTANCE_LIST_MENU_ITEM =
        "SUBSTANCE_LIST_MENU_ITEM";
    public static final
    String
        SUBSTANCE_CREATE_MENU_ITEM =
        "SUBSTANCE_CREATE_MENU_ITEM";
    public static final
    String
        SUBSTANCE_VIEW_MENU =
        "SUBSTANCE_VIEW_MENU";
    public static final
    String
        PERMIT_LIST_MENU =
        "PERMIT_LIST_MENU";
    public static final
    String
        PERMIT_VIEW_MENU =
        "PERMIT_VIEW_MENU";
    public static final
    String
        SCC_LIST_MENU =
        "SCC_LIST_MENU";
    public static final
    String
        SCC_VIEW_MENU =
        "SCC_VIEW_MENU";
    public static final
    String
        SCC_LIST_MENU_ITEM =
        "SCC_LIST_MENU_ITEM";
    public static final
    String
        SCC_CREATE_MENU_ITEM =
        "SCC_CREATE_MENU_ITEM";
    public static final
    String
        SCC_VIEW_MENU_ITEM =
        "SCC_VIEW_MENU_ITEM";
    public static final
    String
        SCC_IMPORT_MENU_ITEM =
        "SCC_IMPORT_MENU_ITEM";
    public static final
    String
        SUBSTANCE_VIEW_MENU_ITEM =
        "SUBSTANCE_VIEW_MENU_ITEM";
    public static final
    String
        FACILITY_VIEW_MENU_ITEM =
        "FACILITY_VIEW_MENU_ITEM";
    public static final
    String
        SOURCE_LIST_MENU =
        "SOURCE_LIST_MENU";
    public static final
    String
        SOURCE_LIST_MENU_ITEM =
        "SOURCE_LIST_MENU_ITEM";
    public static final
    String
        SOURCE_CREATE_MENU_ITEM =
        "SOURCE_CREATE_MENU_ITEM";
    public static final
    String
        SOURCE_VIEW_MENU_ITEM =
        "SOURCE_VIEW_MENU_ITEM";
    public static final
    String
        SOURCE_VIEW_MENU =
        "SOURCE_VIEW_MENU";
    public static final
    String
        PERMIT_LIST_MENU_ITEM =
        "PERMIT_LIST_MENU_ITEM";
    public static final
    String
        PERMIT_CREATE_MENU_ITEM =
        "PERMIT_CREATE_MENU_ITEM";
    public static final
    String
        PERMIT_VIEW_MENU_ITEM =
        "PERMIT_VIEW_MENU_ITEM";
    public static final
    String
        FACILITY_LIST_MENU_ITEM =
        "FACILITY_LIST_MENU_ITEM";
    public static final
    String
        FACILITY_CREATE_MENU_ITEM =
        "FACILITY_CREATE_MENU_ITEM";
    public static final
    String
        REPORT_LIST_MENU =
        "REPORT_LIST_MENU";
    public static final
    String
        REPORT_LIST_MENU_ITEM =
        "REPORT_LIST_MENU_ITEM";
    public static final
    String
        SCC_LIBRARY_LIST_MENU =
        "SCC_LIBRARY_LIST_MENU";
    public static final
    String
        SCC_LIBRARY_VIEW_MENU =
        "SCC_LIBRARY_VIEW_MENU";
    public static final
    String
        SCC_LIBRARY_LIST_MENU_ITEM =
        "SCC_LIBRARY_LIST_MENU_ITEM";
    public static final
    String
        SCC_LIBRARY_CREATE_MENU_ITEM =
        "SCC_LIBRARY_CREATE_MENU_ITEM";
    public static final
    String
        SCC_LIBRARY_VIEW_MENU_ITEM =
        "SCC_LIBRARY_VIEW_MENU_ITEM";
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
    TreeSet<SecondaryMenuItem>
        secondaryMenu =
        null;
    private
    SecondaryMenuItem
        currentItem =
        null;

    private SecondaryMenu(String name, String description, int minSecurityLevel)
    {
        this.name =
            name;
        this.description =
            description;
        this.minSecurityLevel =
            minSecurityLevel;
    }

    public static SecondaryMenu getInstance(String secondaryMenuName)
    {
        SecondaryMenu
            secondaryMenu;
        if (secondaryMenuName.equals(SYSTEM_ADMIN_MENU_NAME))
        {
            SecondaryMenu
                systemAdminMenu =
                new SecondaryMenu(SYSTEM_ADMIN_MENU_NAME,
                                  "System Admin",
                                  SecurityManager.USER_SECURITY_LEVEL);
            systemAdminMenu.add(new SecondaryMenuItem(SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/env/admin",
                                                      "/adminclientlistaction.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_READ));
            secondaryMenu =
                systemAdminMenu;
        }
        else if (secondaryMenuName.equals(SCC_LIBRARY_LIST_MENU))
        {
            SecondaryMenu
                sccLibraryListMenu =
                new SecondaryMenu(SCC_LIST_MENU,
                                  "SCC Library",
                                  SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL);
            sccLibraryListMenu.add(new SecondaryMenuItem(SCC_LIBRARY_LIST_MENU_ITEM,
                                                         "Select",
                                                         "/html/env/scclibrary",
                                                         "/scclibrarylistpage.do",
                                                         1,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_SCC_LIBRARY_READ));
            sccLibraryListMenu.add(new SecondaryMenuItem(SCC_LIBRARY_CREATE_MENU_ITEM,
                                                         "New",
                                                         "/html/env/scclibrary",
                                                         "/scclibrarycreatepage.do",
                                                         2,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_SCC_LIBRARY_CREATE));
            secondaryMenu =
                sccLibraryListMenu;
        }
        else if (secondaryMenuName.equals(SCC_LIBRARY_VIEW_MENU))
        {
            SecondaryMenu
                sccLibraryListMenu =
                new SecondaryMenu(SCC_LIBRARY_VIEW_MENU,
                                  "SCC Library",
                                  SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL);
            sccLibraryListMenu.add(new SecondaryMenuItem(SCC_LIBRARY_LIST_MENU_ITEM,
                                                         "Select",
                                                         "/html/env/scclibrary",
                                                         "/scclibrarylistpage.do",
                                                         1,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_SCC_LIBRARY_READ));
            sccLibraryListMenu.add(new SecondaryMenuItem(SCC_LIBRARY_CREATE_MENU_ITEM,
                                                         "New",
                                                         "/html/env/scclibrary",
                                                         "/scclibrarycreatepage.do",
                                                         2,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_SCC_LIBRARY_CREATE));
            sccLibraryListMenu.add(new SecondaryMenuItem(SCC_LIBRARY_VIEW_MENU_ITEM,
                                                         "View",
                                                         "/html/env/scclibrary",
                                                         "/scclibraryviewpage.do",
                                                         3,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_SCC_LIBRARY_READ));
            secondaryMenu =
                sccLibraryListMenu;
        }
        else if (secondaryMenuName.equals(CLIENT_READINGS_MENU_NAME))
        {
            SecondaryMenu
                clientMenu =
                new SecondaryMenu(CLIENT_READINGS_MENU_NAME,
                                  "Client Readings",
                                  SecurityManager.USER_SECURITY_LEVEL);
            clientMenu.add(new SecondaryMenuItem(CLIENT_SOURCE_USAGE_MENU_ITEM_NAME,
                                                 "Source Usage",
                                                 "/html/env/sourceusage",
                                                 "/sourceusagereadingspage.do",
                                                 1,
                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                 SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
            clientMenu.add(new SecondaryMenuItem(CLIENT_CONTROL_USAGE_MENU_ITEM_NAME,
                                                 "Control Malfunctions",
                                                 "/html/env/sourceusage",
                                                 "/controlusagereadingspage.do",
                                                 2,
                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                 SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
            secondaryMenu =
                clientMenu;
        }
        else if (secondaryMenuName.equals(FACILITY_LIST_MENU))
        {
            SecondaryMenu
                facilityListMenu =
                new SecondaryMenu(FACILITY_LIST_MENU,
                                  "Facility",
                                  SecurityManager.USER_SECURITY_LEVEL);
            facilityListMenu.add(new SecondaryMenuItem(FACILITY_LIST_MENU_ITEM,
                                                       "Select",
                                                       "/html/env/facility",
                                                       "/facilitylistpage.do",
                                                       1,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_FACILITY_READ));
            facilityListMenu.add(new SecondaryMenuItem(FACILITY_CREATE_MENU_ITEM,
                                                       "New",
                                                       "/html/env/facility",
                                                       "/facilitycreatepage.do",
                                                       2,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_FACILITY_CREATE));
            secondaryMenu =
                facilityListMenu;
        }
        else if (secondaryMenuName.equals(FACILITY_VIEW_MENU))
        {
            SecondaryMenu
                facilityViewMenu =
                new SecondaryMenu(FACILITY_VIEW_MENU,
                                  "Facility",
                                  SecurityManager.USER_SECURITY_LEVEL);
            facilityViewMenu.add(new SecondaryMenuItem(FACILITY_LIST_MENU_ITEM,
                                                       "Select",
                                                       "/html/env/facility",
                                                       "/facilitylistpage.do",
                                                       1,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_FACILITY_READ));
            facilityViewMenu.add(new SecondaryMenuItem(FACILITY_CREATE_MENU_ITEM,
                                                       "New",
                                                       "/html/env/facility",
                                                       "/facilitycreatepage.do",
                                                       2,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_FACILITY_CREATE));
            facilityViewMenu.add(new SecondaryMenuItem(FACILITY_VIEW_MENU_ITEM,
                                                       "View",
                                                       "/html/env/facility",
                                                       "/facilityviewpage.do",
                                                       3,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_FACILITY_READ));
            facilityViewMenu.add(new SecondaryMenuItem(PROCESS_LIST_MENU_ITEM,
                                                       "Processes",
                                                       "/html/env/process",
                                                       "/processlistpage.do",
                                                       4,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_PROCESS_READ));
            facilityViewMenu.add(new SecondaryMenuItem(ASSET_LIST_MENU_ITEM,
                                                       "Assets",
                                                       "/html/env/asset",
                                                       "/assetlistpage.do",
                                                       5,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_READ));
            secondaryMenu =
                facilityViewMenu;
        }
        else if (secondaryMenuName.equals(SUBSTANCE_LIST_MENU))
        {
            SecondaryMenu
                substanceListMenu =
                new SecondaryMenu(SUBSTANCE_LIST_MENU,
                                  "Substance",
                                  SecurityManager.USER_SECURITY_LEVEL);
            substanceListMenu.add(new SecondaryMenuItem(SUBSTANCE_LIST_MENU_ITEM,
                                                        "Select",
                                                        "/html/env/substance",
                                                        "/substancelistpage.do",
                                                        1,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SUBSTANCE_READ));
            substanceListMenu.add(new SecondaryMenuItem(SUBSTANCE_CREATE_MENU_ITEM,
                                                        "New",
                                                        "/html/env/substance",
                                                        "/substancecreatepage.do",
                                                        2,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SUBSTANCE_CREATE));
            secondaryMenu =
                substanceListMenu;
        }
        else if (secondaryMenuName.equals(SUBSTANCE_VIEW_MENU))
        {
            SecondaryMenu
                substanceViewMenu =
                new SecondaryMenu(SUBSTANCE_VIEW_MENU,
                                  "Substance",
                                  SecurityManager.USER_SECURITY_LEVEL);
            substanceViewMenu.add(new SecondaryMenuItem(SUBSTANCE_LIST_MENU_ITEM,
                                                        "Select",
                                                        "/html/env/substance",
                                                        "/substancelistpage.do",
                                                        1,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SUBSTANCE_READ));
            substanceViewMenu.add(new SecondaryMenuItem(SUBSTANCE_CREATE_MENU_ITEM,
                                                        "New",
                                                        "/html/env/substance",
                                                        "/substancecreatepage.do",
                                                        2,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SUBSTANCE_CREATE));
            substanceViewMenu.add(new SecondaryMenuItem(SUBSTANCE_VIEW_MENU_ITEM,
                                                        "View",
                                                        "/html/env/substance",
                                                        "/substanceviewpage.do",
                                                        3,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SUBSTANCE_READ));
            secondaryMenu =
                substanceViewMenu;
        }
        else if (secondaryMenuName.equals(PERMIT_LIST_MENU))
        {
            SecondaryMenu
                permitListMenu =
                new SecondaryMenu(PERMIT_LIST_MENU,
                                  "Permit",
                                  SecurityManager.USER_SECURITY_LEVEL);
            permitListMenu.add(new SecondaryMenuItem(PERMIT_LIST_MENU_ITEM,
                                                     "Select",
                                                     "/html/env/permit",
                                                     "/permitlistpage.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_PERMIT_READ));
            permitListMenu.add(new SecondaryMenuItem(PERMIT_CREATE_MENU_ITEM,
                                                     "New",
                                                     "/html/env/permit",
                                                     "/permitcreatepage.do",
                                                     2,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_PERMIT_CREATE));
            secondaryMenu =
                permitListMenu;
        }
        else if (secondaryMenuName.equals(PERMIT_VIEW_MENU))
        {
            SecondaryMenu
                permitListMenu =
                new SecondaryMenu(PERMIT_VIEW_MENU,
                                  "Permit",
                                  SecurityManager.USER_SECURITY_LEVEL);
            permitListMenu.add(new SecondaryMenuItem(PERMIT_LIST_MENU_ITEM,
                                                     "Select",
                                                     "/html/env/permit",
                                                     "/permitlistpage.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_PERMIT_READ));
            permitListMenu.add(new SecondaryMenuItem(PERMIT_CREATE_MENU_ITEM,
                                                     "New",
                                                     "/html/env/permit",
                                                     "/permitcreatepage.do",
                                                     2,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_PERMIT_CREATE));
            permitListMenu.add(new SecondaryMenuItem(PERMIT_VIEW_MENU_ITEM,
                                                     "View",
                                                     "/html/env/permit",
                                                     "/permitviewpage.do",
                                                     3,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_PERMIT_READ));
            secondaryMenu =
                permitListMenu;
        }
        else if (secondaryMenuName.equals(SOURCE_LIST_MENU))
        {
            SecondaryMenu
                sourceListMenu =
                new SecondaryMenu(SOURCE_LIST_MENU,
                                  "Source",
                                  SecurityManager.USER_SECURITY_LEVEL);
            sourceListMenu.add(new SecondaryMenuItem(SOURCE_LIST_MENU_ITEM,
                                                     "Select",
                                                     "/html/env/source",
                                                     "/sourcelistpage.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SOURCE_READ));
            sourceListMenu.add(new SecondaryMenuItem(SOURCE_CREATE_MENU_ITEM,
                                                     "New",
                                                     "/html/env/source",
                                                     "/sourcecreatepage.do",
                                                     2,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SOURCE_CREATE));
            secondaryMenu =
                sourceListMenu;
        }
        else if (secondaryMenuName.equals(SOURCE_VIEW_MENU))
        {
            SecondaryMenu
                sourceListMenu =
                new SecondaryMenu(SOURCE_VIEW_MENU,
                                  "Source",
                                  SecurityManager.USER_SECURITY_LEVEL);
            sourceListMenu.add(new SecondaryMenuItem(SOURCE_LIST_MENU_ITEM,
                                                     "Select",
                                                     "/html/env/source",
                                                     "/sourcelistpage.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SOURCE_READ));
            sourceListMenu.add(new SecondaryMenuItem(SOURCE_CREATE_MENU_ITEM,
                                                     "New",
                                                     "/html/env/source",
                                                     "/sourcecreatepage.do",
                                                     2,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SOURCE_CREATE));
            sourceListMenu.add(new SecondaryMenuItem(SOURCE_VIEW_MENU_ITEM,
                                                     "View",
                                                     "/html/env/source",
                                                     "/sourceviewpage.do",
                                                     3,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SOURCE_READ));
            secondaryMenu =
                sourceListMenu;
        }
        else if (secondaryMenuName.equals(SCC_LIST_MENU))
        {
            SecondaryMenu
                sccListMenu =
                new SecondaryMenu(SCC_LIST_MENU,
                                  "SCC Info",
                                  SecurityManager.USER_SECURITY_LEVEL);
            sccListMenu.add(new SecondaryMenuItem(SCC_LIST_MENU_ITEM,
                                                  "Select",
                                                  "/html/env/scc",
                                                  "/scclistpage.do",
                                                  1,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_READ));
            sccListMenu.add(new SecondaryMenuItem(SCC_CREATE_MENU_ITEM,
                                                  "New",
                                                  "/html/env/scc",
                                                  "/scccreatepage.do",
                                                  2,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_CREATE));
            sccListMenu.add(new SecondaryMenuItem(SCC_IMPORT_MENU_ITEM,
                                                  "Import",
                                                  "/html/env/scc",
                                                  "/sccimportpage.do",
                                                  3,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_CREATE));
            secondaryMenu =
                sccListMenu;
        }
        else if (secondaryMenuName.equals(SCC_VIEW_MENU))
        {
            SecondaryMenu
                sccViewMenu =
                new SecondaryMenu(SCC_VIEW_MENU,
                                  "SCC Info",
                                  SecurityManager.USER_SECURITY_LEVEL);
            sccViewMenu.add(new SecondaryMenuItem(SCC_LIST_MENU_ITEM,
                                                  "Select",
                                                  "/html/env/scc",
                                                  "/scclistpage.do",
                                                  1,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_READ));
            sccViewMenu.add(new SecondaryMenuItem(SCC_CREATE_MENU_ITEM,
                                                  "New",
                                                  "/html/env/scc",
                                                  "/scccreatepage.do",
                                                  2,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_CREATE));
            sccViewMenu.add(new SecondaryMenuItem(SCC_IMPORT_MENU_ITEM,
                                                  "Import",
                                                  "/html/env/scc",
                                                  "/sccimportpage.do",
                                                  3,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_CREATE));
            sccViewMenu.add(new SecondaryMenuItem(SCC_VIEW_MENU_ITEM,
                                                  "View",
                                                  "/html/env/scc",
                                                  "/sccviewpage.do",
                                                  4,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.EV_SCC_READ));
            secondaryMenu =
                sccViewMenu;
        }
        else
        {
            LOG.error("Could not locate Secondary Menu "
                      + secondaryMenuName);
            secondaryMenu =
                null;
        }
        return secondaryMenu;
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

    public void add(SecondaryMenuItem secondaryMenuItem)
    {
        if (secondaryMenu
            == null)
        {
            secondaryMenu =
                new TreeSet<SecondaryMenuItem>();
        }
        secondaryMenu.add(secondaryMenuItem);
    }

    public SecondaryMenuItem remove(SecondaryMenuItem secondaryMenuItem)
    {
        SecondaryMenuItem
            pmi;
        if (secondaryMenu
            == null)
        {
            return null;
        }
        for (SecondaryMenuItem aSecondaryMenu : secondaryMenu)
        {
            pmi =
                aSecondaryMenu;
            if (pmi.getName()
                .equals(secondaryMenuItem.getName()))
            {
                secondaryMenu.remove(pmi);
                if (!secondaryMenu.isEmpty())
                {
                    currentItem =
                        secondaryMenu.first();
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
            for (SecondaryMenuItem aSecondaryMenu : secondaryMenu)
            {
                if (aSecondaryMenu.getName()
                    .equals(itemName))
                {
                    currentItem =
                        aSecondaryMenu;
                    break;
                }
            }
            if (currentItem
                == null)
            {
                currentItem =
                    secondaryMenu.first();
            }
        }
        else
        {
            currentItem =
                new SecondaryMenuItem();
            currentItem.setName(CLIENT_NONE_MENU_ITEM_NAME);
            currentItem.setDescription(CLIENT_NONE_MENU_ITEM_NAME);
            currentItem.setLocation("");
        }
    }

    public SecondaryMenuItem getCurrentItem()
    {
        if (currentItem
            == null)
        {
            if (!secondaryMenu.isEmpty())
            {
                return secondaryMenu.first();
            }
        }
        return currentItem;
    }

    public List<SecondaryMenuItem> getMenuItems()
    {
        return new ArrayList<SecondaryMenuItem>(secondaryMenu);
    }
}