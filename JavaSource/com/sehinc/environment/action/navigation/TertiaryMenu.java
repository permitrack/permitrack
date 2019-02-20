package com.sehinc.environment.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class TertiaryMenu
    implements INavMenu
{
    private static
    Logger
        LOG =
        Logger.getLogger(TertiaryMenu.class);
    public static final
    String
        PERMIT_VIEW_MENU =
        "PERMIT_VIEW_MENU";
    public static final
    String
        FACILITY_VIEW_MENU =
        "FACILITY_VIEW_MENU";
    public static final
    String
        FACILITY_EDIT_MENU_ITEM =
        "FACILITY_EDIT_MENU_ITEM";
    public static final
    String
        FACILITY_CONTACT_MENU_ITEM =
        "FACILITY_CONTACT_MENU_ITEM";
    public static final
    String
        PERMIT_DETAIL_VIEW_MENU =
        "PERMIT_DETAIL_VIEW_MENU";
    public static final
    String
        SUBSTANCE_LIST_MENU =
        "SUBSTANCE_LIST_MENU";
    public static final
    String
        SUBSTANCE_VIEW_MENU =
        "SUBSTANCE_VIEW_MENU";
    public static final
    String
        SOURCE_LIST_MENU =
        "SOURCE_LIST_MENU";
    public static final
    String
        SOURCE_VIEW_MENU =
        "SOURCE_VIEW_MENU";
    public static final
    String
        ASSET_LIST_MENU_ITEM =
        "ASSET_LIST_MENU_ITEM";
    public static final
    String
        ASSET_LIST_MENU =
        "ASSET_LIST_MENU";
    public static final
    String
        ASSET_FULL_LIST_MENU_ITEM =
        "ASSET_FULL_LIST_MENU_ITEM";
    public static final
    String
        ASSET_NEW_MENU_ITEM =
        "ASSET_NEW_MENU_ITEM";
    public static final
    String
        ASSET_VIEW_MENU_ITEM =
        "ASSET_VIEW_MENU_ITEM";
    public static final
    String
        ASSET_EDIT_MENU_ITEM =
        "ASSET_EDIT_MENU_ITEM";
    public static final
    String
        ASSET_FULL_LIST_MENU =
        "ASSET_FULL_LIST_MENU";
    public static final
    String
        ASSET_VIEW_MENU =
        "ASSET_VIEW_MENU";
    public static final
    String
        PROCESS_LIST_MENU_ITEM =
        "PROCESS_LIST_MENU_ITEM";
    public static final
    String
        PROCESS_NEW_MENU_ITEM =
        "PROCESS_NEW_MENU_ITEM";
    public static final
    String
        PROCESS_LIST_MENU =
        "PROCESS_LIST_MENU";
    public static final
    String
        PROCESS_VIEW_MENU =
        "PROCESS_VIEW_MENU";
    public static final
    String
        PROCESS_VIEW_MENU_ITEM =
        "PROCESS_VIEW_MENU_ITEM";
    public static final
    String
        PROCESS_EDIT_MENU_ITEM =
        "PROCESS_EDIT_MENU_ITEM";
    public static final
    String
        PROCESS_ASSET_MENU_ITEM =
        "PROCESS_ASSET_MENU_ITEM";
    public static final
    String
        SOURCE_USAGE_LIST_MENU =
        "SOURCE_USAGE_LIST_MENU";
    public static final
    String
        SOURCE_USAGE_LIST_MENU_ITEM =
        "SOURCE_USAGE_LIST_MENU_ITEM";
    public static final
    String
        SOURCE_USAGE_CREATE_MENU_ITEM =
        "SOURCE_USAGE_CREATE_MENU_ITEM";
    public static final
    String
        SOURCE_USAGE_CREATE_SET_MENU_ITEM =
        "SOURCE_USAGE_CREATE_SET_MENU_ITEM";
    public static final
    String
        CONTROL_USAGE_LIST_MENU =
        "CONTROL_USAGE_LIST_MENU";
    public static final
    String
        CONTROL_USAGE_VIEW_MENU =
        "CONTROL_USAGE_VIEW_MENU";
    public static final
    String
        CONTROL_USAGE_LIST_MENU_ITEM =
        "CONTROL_USAGE_LIST_MENU_ITEM";
    public static final
    String
        SOURCE_USAGE_VIEW_MENU =
        "SOURCE_USAGE_VIEW_MENU";
    public static final
    String
        SOURCE_USAGE_VIEW_MENU_ITEM =
        "SOURCE_USAGE_VIEW_MENU_ITEM";
    public static final
    String
        SOURCE_USAGE_EDIT_MENU_ITEM =
        "SOURCE_USAGE_EDIT_MENU_ITEM";
    public static final
    String
        CONTROL_USAGE_NEW_MENU_ITEM =
        "CONTROL_USAGE_NEW_MENU_ITEM";
    public static final
    String
        CONTROL_USAGE_VIEW_MENU_ITEM =
        "CONTROL_USAGE_VIEW_MENU_ITEM";
    public static final
    String
        CONTROL_USAGE_EDIT_MENU_ITEM =
        "CONTROL_USAGE_EDIT_MENU_ITEM";
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
        SCC_LIBRARY_VIEW_MENU =
        "SCC_LIBRARY_VIEW_MENU";
    private static
    TertiaryMenu
        noneMenu =
        null;
    private static
    TertiaryMenu
        permitViewMenu =
        null;
    private static
    TertiaryMenu
        permitDetailViewMenu =
        null;
    private static
    TertiaryMenu
        substanceViewMenu =
        null;
    private static
    TertiaryMenu
        sourceViewMenu =
        null;
    private static
    TertiaryMenu
        assetListMenu =
        null;
    private static
    TertiaryMenu
        assetFullListMenu =
        null;
    private static
    TertiaryMenu
        assetViewMenu =
        null;
    private static
    TertiaryMenu
        facilityViewMenu =
        null;
    private static
    TertiaryMenu
        processViewMenu =
        null;
    private static
    TertiaryMenu
        processListMenu =
        null;
    private static
    TertiaryMenu
        sourceUsageViewMenu =
        null;
    private static
    TertiaryMenu
        sourceUsageListMenu =
        null;
    private static
    TertiaryMenu
        controlUsageListMenu =
        null;
    private static
    TertiaryMenu
        controlUsageViewMenu =
        null;
    private static
    TertiaryMenu
        sccViewMenu =
        null;
    private static
    TertiaryMenu
        sccLibraryViewMenu =
        null;
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
        tertiaryMenu =
        null;
    private
    TertiaryMenuItem
        currentItem =
        null;
    public static final
    String
        PERMIT_DETAIL_MENU_ITEM =
        "PERMIT_DETAIL_MENU_ITEM";
    public static final
    String
        PERMIT_SUBSTANCE_MENU_ITEM =
        "PERMIT_SUBSTANCE_MENU_ITEM";
    public static final
    String
        PERMIT_FACILITY_MENU_ITEM =
        "PERMIT_FACILITY_MENU_ITEM";
    public static final
    String
        PERMIT_EDIT_MENU_ITEM =
        "PERMIT_EDIT_MENU_ITEM";
    public static final
    String
        SOURCE_EDIT_MENU_ITEM =
        "SOURCE_EDIT_MENU_ITEM";
    public static final
    String
        SOURCE_SUBSTANCE_MENU_ITEM =
        "SOURCE_SUBSTANCE_MENU_ITEM";
    public static final
    String
        SOURCE_SCC_MENU_ITEM =
        "SOURCE_SCC_MENU_ITEM";
    public static final
    String
        SUBSTANCE_EDIT_MENU_ITEM =
        "SUBSTANCE_EDIT_MENU_ITEM";
    public static final
    String
        SUBSTANCE_SCC_MENU_ITEM =
        "SUBSTANCE_SCC_MENU_ITEM";
    public static final
    String
        PERMIT_ASSET_MENU_ITEM =
        "PERMIT_ASSET_MENU_ITEM";
    public static final
    String
        PERMIT_DETAIL_EDIT_MENU_ITEM =
        "PERMIT_DETAIL_EDIT_MENU_ITEM";
    public static final
    String
        ASSET_CONTROL_MENU_ITEM =
        "ASSET_CONTROL_MENU_ITEM";
    public static final
    String
        ASSET_SOURCE_MENU_ITEM =
        "ASSET_SOURCE_MENU_ITEM";
    public static final
    String
        SCC_EDIT_MENU_ITEM =
        "SCC_EDIT_MENU_ITEM";

    private TertiaryMenu(String name, String description, TertiaryMenu subMenu)
    {
        this.name =
            name;
        this.description =
            description;
        TertiaryMenu
            subMenu1 =
            subMenu;
    }

    public static TertiaryMenu getInstance(String tertiaryMenuName)
    {
        TertiaryMenu
            menu =
            null;
        String
            strLog =
            "TertiaryMenu.getInstance("
            + tertiaryMenuName
            + ")";
        LOG.debug(strLog
                  + "in method");
        if (tertiaryMenuName.equals(PERMIT_VIEW_MENU))
        {
            if (permitViewMenu
                == null)
            {
                permitViewMenu =
                    new TertiaryMenu(PERMIT_VIEW_MENU,
                                     "Permit",
                                     null);
                permitViewMenu.add(new TertiaryMenuItem(PERMIT_DETAIL_MENU_ITEM,
                                                        "+ Add Permit Detail",
                                                        "/html/env/permit",
                                                        "/permitdetailcreatepage.do",
                                                        2,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_PERMIT_DETAIL_CREATE));
                permitViewMenu.add(new TertiaryMenuItem(PERMIT_SUBSTANCE_MENU_ITEM,
                                                        "+ Add Substance",
                                                        "/html/env/permit",
                                                        "/permitsubstancecreatepage.do",
                                                        3,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_PERMIT_SUBSTANCE_CREATE));
                permitViewMenu.add(new TertiaryMenuItem(PERMIT_FACILITY_MENU_ITEM,
                                                        "+ Add Facility",
                                                        "/html/env/permit",
                                                        "/permitfacilitycreatepage.do",
                                                        4,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_PERMIT_FACILITY_CREATE));
                permitViewMenu.add(new TertiaryMenuItem(PERMIT_EDIT_MENU_ITEM,
                                                        "Edit",
                                                        "/html/env/permit",
                                                        "/permiteditpage.do",
                                                        1,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_PERMIT_UPDATE));
            }
            menu =
                permitViewMenu;
        }
        else if (tertiaryMenuName.equals(PERMIT_DETAIL_VIEW_MENU))
        {
            if (permitDetailViewMenu
                == null)
            {
                permitDetailViewMenu =
                    new TertiaryMenu(PERMIT_DETAIL_VIEW_MENU,
                                     "Permit Details",
                                     null);
                permitDetailViewMenu.add(new TertiaryMenuItem(PERMIT_DETAIL_EDIT_MENU_ITEM,
                                                              "Edit",
                                                              "/html/env/permit",
                                                              "/permitdetaileditpage.do",
                                                              1,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_PERMIT_DETAIL_UPDATE));
                permitDetailViewMenu.add(new TertiaryMenuItem(PERMIT_ASSET_MENU_ITEM,
                                                              "+ Add Asset",
                                                              "/html/env/permit",
                                                              "/permitassetcreatepage.do",
                                                              2,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_PERMIT_ASSET_CREATE));
            }
            menu =
                permitDetailViewMenu;
        }
        else if (tertiaryMenuName.equals(SUBSTANCE_VIEW_MENU))
        {
            if (substanceViewMenu
                == null)
            {
                substanceViewMenu =
                    new TertiaryMenu(SUBSTANCE_VIEW_MENU,
                                     "Substance",
                                     null);
                substanceViewMenu.add(new TertiaryMenuItem(SUBSTANCE_EDIT_MENU_ITEM,
                                                           "Edit",
                                                           "/html/env/substance",
                                                           "/substanceeditpage.do",
                                                           1,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.EV_SUBSTANCE_UPDATE));
                substanceViewMenu.add(new TertiaryMenuItem(SUBSTANCE_SCC_MENU_ITEM,
                                                           "+ Add SCC",
                                                           "/html/env/substance",
                                                           "/substancescccreatepage.do",
                                                           2,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.EV_SUBSTANCE_SCC_CREATE));
            }
            menu =
                substanceViewMenu;
        }
        else if (tertiaryMenuName.equals(SOURCE_VIEW_MENU))
        {
            if (sourceViewMenu
                == null)
            {
                sourceViewMenu =
                    new TertiaryMenu(SOURCE_VIEW_MENU,
                                     "Source",
                                     null);
                sourceViewMenu.add(new TertiaryMenuItem(SOURCE_EDIT_MENU_ITEM,
                                                        "Edit",
                                                        "/html/env/source",
                                                        "/sourceeditpage.do",
                                                        1,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_UPDATE));
                sourceViewMenu.add(new TertiaryMenuItem(SOURCE_SUBSTANCE_MENU_ITEM,
                                                        "+ Add Substance",
                                                        "/html/env/source",
                                                        "/sourcesubstancecreatepage.do",
                                                        2,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_CREATE));
                sourceViewMenu.add(new TertiaryMenuItem(SOURCE_SCC_MENU_ITEM,
                                                        "+ Add SCC",
                                                        "/html/env/source",
                                                        "/sourcescccreatepage.do",
                                                        3,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.EV_SOURCE_SCC_CREATE));
            }
            menu =
                sourceViewMenu;
        }
        else if (tertiaryMenuName.equals(ASSET_LIST_MENU))
        {
            if (assetListMenu
                == null)
            {
                assetListMenu =
                    new TertiaryMenu(ASSET_LIST_MENU,
                                     "Asset",
                                     null);
                assetListMenu.add(new TertiaryMenuItem(ASSET_LIST_MENU_ITEM,
                                                       "Select",
                                                       "/html/env/asset",
                                                       "/assetlistpage.do",
                                                       1,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_READ));
                assetListMenu.add(new TertiaryMenuItem(ASSET_NEW_MENU_ITEM,
                                                       "New",
                                                       "/html/env/asset",
                                                       "/assetcreatepage.do",
                                                       2,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_CREATE));
                assetListMenu.add(new TertiaryMenuItem(ASSET_FULL_LIST_MENU_ITEM,
                                                       "Unstructured List",
                                                       "/html/env/asset",
                                                       "/assetfulllistpage.do",
                                                       3,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_READ));
            }
            menu =
                assetListMenu;
        }
        else if (tertiaryMenuName.equals(ASSET_FULL_LIST_MENU))
        {
            if (assetFullListMenu
                == null)
            {
                assetFullListMenu =
                    new TertiaryMenu(ASSET_FULL_LIST_MENU,
                                     "Asset",
                                     null);
                assetFullListMenu.add(new TertiaryMenuItem(ASSET_FULL_LIST_MENU_ITEM,
                                                           "Select",
                                                           "/html/env/asset",
                                                           "/assetfulllistpage.do",
                                                           1,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.EV_ASSET_READ));
                assetFullListMenu.add(new TertiaryMenuItem(ASSET_NEW_MENU_ITEM,
                                                           "New",
                                                           "/html/env/asset",
                                                           "/assetcreatepage.do",
                                                           2,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.EV_ASSET_CREATE));
                assetFullListMenu.add(new TertiaryMenuItem(ASSET_LIST_MENU_ITEM,
                                                           "Structured List",
                                                           "/html/env/asset",
                                                           "/assetlistpage.do",
                                                           3,
                                                           SecurityManager.USER_SECURITY_LEVEL,
                                                           SecureObjectPermissionData.EV_ASSET_READ));
            }
            menu =
                assetFullListMenu;
        }
        else if (tertiaryMenuName.equals(ASSET_VIEW_MENU))
        {
            if (assetViewMenu
                == null)
            {
                assetViewMenu =
                    new TertiaryMenu(ASSET_VIEW_MENU,
                                     "Asset",
                                     null);
                assetViewMenu.add(new TertiaryMenuItem(ASSET_LIST_MENU_ITEM,
                                                       "Select",
                                                       "/html/env/asset",
                                                       "/assetlistpage.do",
                                                       1,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_READ));
                assetViewMenu.add(new TertiaryMenuItem(ASSET_NEW_MENU_ITEM,
                                                       "New",
                                                       "/html/env/asset",
                                                       "/assetcreatepage.do",
                                                       2,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_CREATE));
                assetViewMenu.add(new TertiaryMenuItem(ASSET_VIEW_MENU_ITEM,
                                                       "View",
                                                       "/html/env/asset",
                                                       "/assetviewpage.do",
                                                       3,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_READ));
                assetViewMenu.add(new TertiaryMenuItem(ASSET_EDIT_MENU_ITEM,
                                                       "Edit",
                                                       "/html/env/asset",
                                                       "/asseteditpage.do",
                                                       4,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_SOURCE_UPDATE));
                assetViewMenu.add(new TertiaryMenuItem(ASSET_SOURCE_MENU_ITEM,
                                                       "+ Add Source",
                                                       "/html/env/asset",
                                                       "/assetsourcecreatepage.do",
                                                       5,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_SOURCE_CREATE));
                assetViewMenu.add(new TertiaryMenuItem(ASSET_CONTROL_MENU_ITEM,
                                                       "+ Add Control",
                                                       "/html/env/asset",
                                                       "/assetsubstancecreatepage.do",
                                                       6,
                                                       SecurityManager.USER_SECURITY_LEVEL,
                                                       SecureObjectPermissionData.EV_ASSET_SUBSTANCE_CREATE));
            }
            menu =
                assetViewMenu;
        }
        else if (tertiaryMenuName.equals(FACILITY_VIEW_MENU))
        {
            if (facilityViewMenu
                == null)
            {
                facilityViewMenu =
                    new TertiaryMenu(FACILITY_VIEW_MENU,
                                     "Facility",
                                     null);
                facilityViewMenu.add(new TertiaryMenuItem(FACILITY_EDIT_MENU_ITEM,
                                                          "Edit",
                                                          "/html/env/facility",
                                                          "/facilityeditpage.do",
                                                          1,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.EV_FACILITY_CONTACT_UPDATE));
                facilityViewMenu.add(new TertiaryMenuItem(FACILITY_CONTACT_MENU_ITEM,
                                                          "+ Add Contact",
                                                          "/html/env/facility",
                                                          "/facilitycontactcreatepage.do",
                                                          2,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.EV_FACILITY_CONTACT_CREATE));
            }
            menu =
                facilityViewMenu;
        }
        else if (tertiaryMenuName.equals(PROCESS_LIST_MENU))
        {
            if (processListMenu
                == null)
            {
                processListMenu =
                    new TertiaryMenu(PROCESS_LIST_MENU,
                                     "Process",
                                     null);
                processListMenu.add(new TertiaryMenuItem(PROCESS_LIST_MENU_ITEM,
                                                         "Select",
                                                         "/html/env/process",
                                                         "/processlistpage.do",
                                                         1,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_READ));
                processListMenu.add(new TertiaryMenuItem(PROCESS_NEW_MENU_ITEM,
                                                         "New",
                                                         "/html/env/process",
                                                         "/processcreatepage.do",
                                                         2,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_CREATE));
            }
            menu =
                processListMenu;
        }
        else if (tertiaryMenuName.equals(PROCESS_VIEW_MENU))
        {
            if (processViewMenu
                == null)
            {
                processViewMenu =
                    new TertiaryMenu(PROCESS_VIEW_MENU,
                                     "Process",
                                     null);
                processViewMenu.add(new TertiaryMenuItem(PROCESS_LIST_MENU_ITEM,
                                                         "Select",
                                                         "/html/env/process",
                                                         "/processlistpage.do",
                                                         1,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_READ));
                processViewMenu.add(new TertiaryMenuItem(PROCESS_NEW_MENU_ITEM,
                                                         "New",
                                                         "/html/env/process",
                                                         "/processcreatepage.do",
                                                         2,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_CREATE));
                processViewMenu.add(new TertiaryMenuItem(PROCESS_VIEW_MENU_ITEM,
                                                         "View",
                                                         "/html/env/process",
                                                         "/processviewpage.do",
                                                         3,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_READ));
                processViewMenu.add(new TertiaryMenuItem(PROCESS_EDIT_MENU_ITEM,
                                                         "Edit",
                                                         "/html/env/process",
                                                         "/processeditpage.do",
                                                         4,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_UPDATE));
                processViewMenu.add(new TertiaryMenuItem(PROCESS_ASSET_MENU_ITEM,
                                                         "+ Add Asset",
                                                         "/html/env/process",
                                                         "/processassetcreatepage.do",
                                                         5,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.EV_PROCESS_ASSET_CREATE));
            }
            menu =
                processViewMenu;
        }
        else if (tertiaryMenuName.equals(SOURCE_USAGE_LIST_MENU))
        {
            if (sourceUsageListMenu
                == null)
            {
                sourceUsageListMenu =
                    new TertiaryMenu(SOURCE_USAGE_LIST_MENU,
                                     "Source Usage",
                                     null);
                sourceUsageListMenu.add(new TertiaryMenuItem(SOURCE_USAGE_LIST_MENU_ITEM,
                                                             "Select",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagelistpage.do",
                                                             1,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
                sourceUsageListMenu.add(new TertiaryMenuItem(SOURCE_USAGE_CREATE_MENU_ITEM,
                                                             "New",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagecreatepage.do",
                                                             2,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
                sourceUsageListMenu.add(new TertiaryMenuItem(SOURCE_USAGE_CREATE_SET_MENU_ITEM,
                                                             "New Set",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagemulticreatedateselectpage.do",
                                                             3,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
            }
            menu =
                sourceUsageListMenu;
        }
        else if (tertiaryMenuName.equals(SOURCE_USAGE_VIEW_MENU))
        {
            if (sourceUsageViewMenu
                == null)
            {
                sourceUsageViewMenu =
                    new TertiaryMenu(SOURCE_USAGE_VIEW_MENU,
                                     "Source Usage",
                                     null);
                sourceUsageViewMenu.add(new TertiaryMenuItem(SOURCE_USAGE_LIST_MENU_ITEM,
                                                             "Select",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagelistpage.do",
                                                             1,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
                sourceUsageViewMenu.add(new TertiaryMenuItem(SOURCE_USAGE_CREATE_MENU_ITEM,
                                                             "New",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagecreatepage.do",
                                                             2,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
                sourceUsageViewMenu.add(new TertiaryMenuItem(SOURCE_USAGE_CREATE_SET_MENU_ITEM,
                                                             "New Set",
                                                             "/html/env/sourceusage",
                                                             "/sourceusagemulticreatedateselectpage.do",
                                                             3,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
                sourceUsageViewMenu.add(new TertiaryMenuItem(SOURCE_USAGE_VIEW_MENU_ITEM,
                                                             "View",
                                                             "/html/env/sourceusage",
                                                             "/sourceusageviewpage.do",
                                                             4,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
                sourceUsageViewMenu.add(new TertiaryMenuItem(SOURCE_USAGE_EDIT_MENU_ITEM,
                                                             "Edit",
                                                             "/html/env/sourceusage",
                                                             "/sourceusageeditpage.do",
                                                             5,
                                                             SecurityManager.USER_SECURITY_LEVEL,
                                                             SecureObjectPermissionData.EV_SOURCE_USAGE_UPDATE));
            }
            menu =
                sourceUsageViewMenu;
        }
        else if (tertiaryMenuName.equals(CONTROL_USAGE_LIST_MENU))
        {
            if (controlUsageListMenu
                == null)
            {
                controlUsageListMenu =
                    new TertiaryMenu(CONTROL_USAGE_LIST_MENU,
                                     "Control Malfunction",
                                     null);
                controlUsageListMenu.add(new TertiaryMenuItem(CONTROL_USAGE_LIST_MENU_ITEM,
                                                              "Select",
                                                              "/html/env/sourceusage",
                                                              "/controlusagelistpage.do",
                                                              1,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
                controlUsageListMenu.add(new TertiaryMenuItem(CONTROL_USAGE_NEW_MENU_ITEM,
                                                              "New",
                                                              "/html/env/sourceusage",
                                                              "/controlusagecreatepage.do",
                                                              2,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
            }
            menu =
                controlUsageListMenu;
        }
        else if (tertiaryMenuName.equals(CONTROL_USAGE_VIEW_MENU))
        {
            if (controlUsageViewMenu
                == null)
            {
                controlUsageViewMenu =
                    new TertiaryMenu(CONTROL_USAGE_VIEW_MENU,
                                     "Control Malfunction",
                                     null);
                controlUsageViewMenu.add(new TertiaryMenuItem(CONTROL_USAGE_LIST_MENU_ITEM,
                                                              "Select",
                                                              "/html/env/sourceusage",
                                                              "/controlusagelistpage.do",
                                                              1,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_READ));
                controlUsageViewMenu.add(new TertiaryMenuItem(CONTROL_USAGE_NEW_MENU_ITEM,
                                                              "New",
                                                              "/html/env/sourceusage",
                                                              "/controlusagecreatepage.do",
                                                              2,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
                controlUsageViewMenu.add(new TertiaryMenuItem(CONTROL_USAGE_VIEW_MENU_ITEM,
                                                              "View",
                                                              "/html/env/sourceusage",
                                                              "/controlusageviewpage.do",
                                                              3,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
                controlUsageViewMenu.add(new TertiaryMenuItem(CONTROL_USAGE_EDIT_MENU_ITEM,
                                                              "Edit",
                                                              "/html/env/sourceusage",
                                                              "/controlusageeditpage.do",
                                                              4,
                                                              SecurityManager.USER_SECURITY_LEVEL,
                                                              SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE));
            }
            menu =
                controlUsageViewMenu;
        }
        else if (tertiaryMenuName.equals(SCC_LIBRARY_VIEW_MENU))
        {
            if (sccLibraryViewMenu
                == null)
            {
                sccLibraryViewMenu =
                    new TertiaryMenu(SCC_LIBRARY_VIEW_MENU,
                                     "SCC Library",
                                     null);
                sccLibraryViewMenu.add(new TertiaryMenuItem(SCC_EDIT_MENU_ITEM,
                                                            "Edit",
                                                            "/html/env/scclibrary",
                                                            "/scclibraryeditpage.do",
                                                            1,
                                                            SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                            SecureObjectPermissionData.EV_SCC_LIBRARY_UPDATE));
            }
            menu =
                sccLibraryViewMenu;
        }
        else if (tertiaryMenuName.equals(SCC_VIEW_MENU))
        {
            if (sccViewMenu
                == null)
            {
                sccViewMenu =
                    new TertiaryMenu(SCC_VIEW_MENU,
                                     "SCC",
                                     null);
                sccViewMenu.add(new TertiaryMenuItem(SCC_EDIT_MENU_ITEM,
                                                     "Edit",
                                                     "/html/env/scc",
                                                     "/scceditpage.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.EV_SCC_UPDATE));
            }
            menu =
                sccViewMenu;
        }
        else if (tertiaryMenuName.equals(NONE_MENU))
        {
            if (noneMenu
                == null)
            {
                noneMenu =
                    new TertiaryMenu(NONE_MENU,
                                     "None Menu",
                                     null);
                noneMenu.add(new TertiaryMenuItem("",
                                                  "",
                                                  "",
                                                  "",
                                                  1,
                                                  SecurityManager.USER_SECURITY_LEVEL));
            }
            menu =
                noneMenu;
        }
        else
        {
            LOG.error("Could not locate Tertiary Menu "
                      + tertiaryMenuName);
        }
        return menu;
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

    public void add(TertiaryMenuItem tertiaryMenuItem)
    {
        if (tertiaryMenu
            == null)
        {
            tertiaryMenu =
                new TreeSet();
        }
        tertiaryMenu.add(tertiaryMenuItem);
    }

    public TertiaryMenuItem remove(TertiaryMenuItem tertiaryMenuItem)
    {
        TertiaryMenuItem
            smi;
        if (tertiaryMenu
            == null)
        {
            return null;
        }
        Iterator
            iter =
            tertiaryMenu.iterator();
        while (iter.hasNext())
        {
            smi =
                (TertiaryMenuItem) iter.next();
            if (smi.getName()
                .equals(tertiaryMenuItem.getName()))
            {
                tertiaryMenu.remove(smi);
                if (!tertiaryMenu.isEmpty())
                {
                    currentItem =
                        (TertiaryMenuItem) tertiaryMenu.first();
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
            tertiaryMenu.iterator();
        while (iter.hasNext())
        {
            TertiaryMenuItem
                smi =
                (TertiaryMenuItem) iter.next();
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
        return currentItem;
    }

    public List getMenuItems()
    {
        return new ArrayList(tertiaryMenu);
    }

    public int getItemCount()
    {
        if (tertiaryMenu
            == null)
        {
            return 0;
        }
        return tertiaryMenu.size();
    }
}