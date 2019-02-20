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

public class SecondaryMenu
    implements INavMenu
{
    private static
    Logger
        LOG =
        Logger.getLogger(SecondaryMenu.class);
    public static final
    String
        NONE_MENU_NAME =
        "NONE_MENU";
    public static final
    String
        PROJECT_LIST_MENU_NAME =
        "PROJECT_LIST_MENU";
    public static final
    String
        PROJECT_VIEW_MENU_NAME =
        "PROJECT_VIEW_MENU";
    public static final
    String
        PROJECT_EMAIL_MENU_NAME =
        "PROJECT_EMAIL_MENU";
    public static final
    String
        PROJECT_CREATE_MENU_NAME =
        "PROJECT_CREATE_MENU";
    public static final
    String
        PROJECT_TYPE_LIST_MENU_NAME =
        "PROJECT_TYPE_LIST_MENU";
    public static final
    String
        PROJECT_TYPE_NONE_MENU_NAME =
        "PROJECT_TYPE_NONE_MENU";
    public static final
    String
        PROJECT_TYPE_VIEW_MENU_NAME =
        "PROJECT_TYPE_VIEW_MENU";
    public static final
    String
        PROJECT_TYPE_LIST_MENU_ITEM_NAME =
        "PROJECT_TYPE_LIST_MENU_ITEM";
    public static final
    String
        PROJECT_TYPE_VIEW_MENU_ITEM_NAME =
        "PROJECT_TYPE_VIEW_MENU_ITEM";
    public static final
    String
        PROJECT_TYPE_CREATE_MENU_ITEM_NAME =
        "PROJECT_TYPE_CREATE_MENU_ITEM";
    public static final
    String
        PROJECT_TYPE_EDIT_MENU_ITEM_NAME =
        "PROJECT_TYPE_EDIT_MENU_ITEM";
    public static final
    String
        PROJECT_LIST_MENU_ITEM_NAME =
        "PROJECT_LIST_MENU_ITEM";
    public static final
    String
        PROJECT_VIEW_MENU_ITEM_NAME =
        "PROJECT_VIEW_MENU_ITEM";
    public static final
    String
        PROJECT_EDIT_MENU_ITEM_NAME =
        "PROJECT_EDIT_MENU_ITEM";
    public static final
    String
        PROJECT_CREATE_MENU_ITEM_NAME =
        "PROJECT_CREATE_MENU_ITEM";
    public static final
    String
        ZONE_LIST_MENU_NAME =
        "ZONE_LIST_MENU";
    public static final
    String
        ZONE_EDIT_MENU_NAME =
        "ZONE_EDIT_MENU";
    public static final
    String
        ZONE_LIST_MENU_ITEM_NAME =
        "ZONE_LIST_MENU_ITEM";
    public static final
    String
        ZONE_EDIT_MENU_ITEM_NAME =
        "ZONE_EDIT_MENU_ITEM";
    public static final
    String
        ZONE_CREATE_MENU_ITEM_NAME =
        "ZONE_CREATE_MENU_ITEM";
    public static final
    String
        GIS_IMPORT_MENU_NAME =
        "GIS_IMPORT_MENU";
    public static final
    String
        GIS_IMPORT_MENU_ITEM_NAME =
        "GIS_IMPORT_MENU_ITEM";
    public static final
    String
        GIS_SEARCH_MENU_ITEM_NAME =
        "GIS_SEARCH_MENU_ITEM";
    public static final
    String
        PROJECT_LIST_PREFERENCES_MENU_NAME =
        "PROJECT_LIST_PREFERENCES_MENU_NAME";
    public static final
    String
        PROJECT_LIST_PREFERENCES_MENU_ITEM_NAME =
        "PROJECT_LIST_PREFERENCES_MENU_ITEM_NAME";
    public static final
    String
        INSPECTION_LIST_MENU_NAME =
        "INSPECTION_LIST_MENU";
    public static final
    String
        INSPECTION_VIEW_MENU_NAME =
        "INSPECTION_VIEW_MENU";
    public static final
    String
        INSPECTION_VIEW_NO_EDIT_MENU_NAME =
        "INSPECTION_VIEW_NO_EDIT_MENU";
    public static final
    String
        INSPECTION_LIST_MENU_ITEM_NAME =
        "INSPECTION_LIST_MENU_ITEM";
    public static final
    String
        INSPECTION_VIEW_MENU_ITEM_NAME =
        "INSPECTION_VIEW_MENU_ITEM";
    public static final
    String
        INSPECTION_CREATE_MENU_ITEM_NAME =
        "INSPECTION_CREATE_MENU_ITEM";
    public static final
    String
        INSPECTION_EDIT_MENU_ITEM_NAME =
        "INSPECTION_EDIT_MENU";
    public static final
    String
        INSPECTION_EMAIL_MENU_NAME =
        "INSPECTION_EMAIL_MENU";
    public static final
    String
        INSPECTION_TEMPLATE_LIST_MENU_NAME =
        "INSPECTION_TEMPLATE_LIST_MENU";
    public static final
    String
        INSPECTION_TEMPLATE_CREATE_MENU_NAME =
        "INSPECTION_TEMPLATE_CREATE_MENU";
    public static final
    String
        INSPECTION_TEMPLATE_EDIT_MENU_NAME =
        "INSPECTION_TEMPLATE_EDIT_MENU";
    public static final
    String
        INSPECTION_TEMPLATE_CREATE_MENU_ITEM_NAME =
        "INSPECTION_TEMPLATE_CREATE_MENU_ITEM";
    public static final
    String
        INSPECTION_TEMPLATE_EDIT_MENU_ITEM_NAME =
        "INSPECTION_TEMPLATE_EDIT_MENU";
    public static final
    String
        PARTNER_LIST_MENU_NAME =
        "PARTNER_LIST_MENU";
    public static final
    String
        PARTNER_EDIT_MENU_NAME =
        "PARTNER_EDIT_MENU";
    public static final
    String
        PARTNER_LIST_MENU_ITEM_NAME =
        "PARTNER_LIST_MENU_ITEM_NAME";
    public static final
    String
        PARTNER_CREATE_MENU_ITEM_NAME =
        "PARTNER_CREATE_MENU_ITEM_NAME";
    public static final
    String
        PARTNER_EDIT_MENU_ITEM_NAME =
        "PARTNER_EDIT_MENU_ITEM_NAME";
    public static final
    String
        PARTNER_FIND_MENU_ITEM_NAME =
        "PARTNER_FIND_MENU_ITEM_NAME";
    public static final
    String
        BMP_LIST_MENU_NAME =
        "BMP_LIST_MENU";
    public static final
    String
        BMP_VIEW_MENU_NAME =
        "BMP_VIEW_MENU";
    public static final
    String
        BMP_NONE_MENU_NAME =
        "BMP_NONE_MENU";
    public static final
    String
        BMP_CREATE_MENU_ITEM_NAME =
        "BMP_CREATE_MENU_ITEM";
    public static final
    String
        BMP_EDIT_MENU_ITEM_NAME =
        "BMP_EDIT_MENU_ITEM";
    public static final
    String
        BMP_ADD_MENU_ITEM_NAME =
        "BMP_ADD_MENU_ITEM";
    public static final
    String
        SECURITY_MENU_NAME =
        "SECURITY_MENU";
    public static final
    String
        REPORT_MENU_NAME =
        "REPORT_MENU";
    public static final
    String
        ADMIN_REPORT_MENU_NAME =
        "ADMIN_REPORT_MENU";
    public static final
    String
        ADMIN_MENU_NAME =
        "ADMIN_ADMIN_MENU";
    public static final
    String
        ADMIN_CLIENT_SELECT_MENU_ITEM_NAME =
        "ADMIN_CLIENT_SELECT_MENU_ITEM";
    public static final
    String
        EVENT_LIST_MENU_NAME =
        "EVENT_LIST_MENU_NAME";
    public static final
    String
        EVENT_COMPLIANCE_REPORT_MENU_NAME =
        "EVENT_COMPLIANCE_REPORT_MENU_NAME";
    public static final
    String
        EVENT_LIST_MENU_ITEM_NAME =
        "EVENT_LIST_MENU_ITEM_NAME";
    public static final
    String
        EVENT_CREATE_MENU_ITEM_NAME =
        "EVENT_CREATE_MENU_ITEM_NAME";
    public static final
    String
        EVENT_COMPLIANCE_REPORT_MENU_ITEM_NAME =
        "EVENT_COMPLIANCE_REPORT_MENU_ITEM_NAME";
    public static final
    String
        CORRECTIVE_ACTION_LIST_MENU_NAME =
        "CORRECTIVE_ACTION_LIST_MENU";
    public static final
    String
        CORRECTIVE_ACTION_LIST_MENU_ITEM_NAME =
        "CORRECTIVE_ACTION_LIST_ITEM";
    public static final
    String
        ADMIN_PARTNER_SELECT_MENU_ITEM_NAME =
        "ADMIN_PARTNER_SELECT_MENU_ITEM_NAME";
    private static
    SecondaryMenu
        noneMenu =
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
        secondaryMenu =
        new TreeSet();
    private
    SecondaryMenuItem
        currentItem =
        null;

    protected SecondaryMenu()
    {
    }

    protected SecondaryMenu(String name, String description)
    {
        this.name =
            name;
        this.description =
            description;
    }

    public static SecondaryMenu getInstance(String secondaryMenuName)
    {
        SecondaryMenu
            projectListMenu;
        if (secondaryMenuName.equals(PROJECT_LIST_MENU_NAME))
        {
            projectListMenu =
                new SecondaryMenu(PROJECT_LIST_MENU_NAME,
                                  "Project List");
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/esc/project",
                                                      "/projectlistpage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/esc/project",
                                                      "/projectcreatestep1.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_CREATE));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.CORRECTIVE_ACTION_LIST_MENU_ITEM_NAME,
                                                      "Corrective Actions",
                                                      "/html/esc/correctiveaction",
                                                      "/correctiveactionlistpage.do",
                                                      7,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ));
            return projectListMenu;
        }
        else if (secondaryMenuName.equals(OPTIONS_LIST_MENU))
        {
            projectListMenu =
                new SecondaryMenu(OPTIONS_LIST_MENU,
                                  "Settings");
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_LIST_MENU_ITEM_NAME,
                                                      "Project Types",
                                                      "/html/esc/project",
                                                      "/projecttypelistpage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_CREATE));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_LIST_MENU_ITEM_NAME,
                                                      "Groups",
                                                      "/html/esc/project",
                                                      "/projectzonelistpage.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.ZONE_READ));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.BMP_LIST_MENU_NAME,
                                                      "BMPs",
                                                      "/html/esc/bmp",
                                                      "/bmplistpage.do",
                                                      3,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.BMP_READ));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME,
                                                      "Templates",
                                                      "/html/esc/inspection",
                                                      "/inspectiontemplatelistpage.do",
                                                      4,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.BMP_TEMPLATE_READ));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.GIS_IMPORT_MENU_ITEM_NAME,
                                                      "Coordinates",
                                                      "/html/esc/gis",
                                                      "/gisimportpage.do",
                                                      5,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_CREATE));
            projectListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_PREFERENCES_MENU_ITEM_NAME,
                                                      "My Settings",
                                                      "/html/esc/project",
                                                      "/projectlistitempage.do",
                                                      6,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ));
            return projectListMenu;
        }
        else if (secondaryMenuName.equals(PROJECT_VIEW_MENU_NAME))
        {
            SecondaryMenu
                projectViewMenu =
                new SecondaryMenu(PROJECT_VIEW_MENU_NAME,
                                  "Project View");
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/esc/project",
                                                      "/projectlistpage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ));
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/esc/project",
                                                      "/projectcreatestep1.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_CREATE));
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_VIEW_MENU_ITEM_NAME,
                                                      "View",
                                                      "/html/esc/project",
                                                      "/projectviewpage.do",
                                                      3,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ,
                                                      true));
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_EDIT_MENU_ITEM_NAME,
                                                      "Edit",
                                                      "/html/esc/project",
                                                      "/projecteditpage.do",
                                                      4,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_UPDATE,
                                                      true));
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME,
                                                      "Inspections",
                                                      "/html/esc/inspection",
                                                      "/inspectionlistpage.do",
                                                      5,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.INSPECTION_READ,
                                                      true));
            projectViewMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_CREATE_MENU_ITEM_NAME,
                                                      "+ Add Inspection",
                                                      "/html/esc/inspection",
                                                      "/inspectioncreatepage.do",
                                                      6,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.INSPECTION_CREATE,
                                                      true));
            return projectViewMenu;
        }
        else if (secondaryMenuName.equals(PROJECT_EMAIL_MENU_NAME))
        {
            SecondaryMenu
                projectEmailMenu =
                new SecondaryMenu(PROJECT_EMAIL_MENU_NAME,
                                  "Project Email");
            return projectEmailMenu;
        }
        else if (secondaryMenuName.equals(PROJECT_CREATE_MENU_NAME))
        {
            SecondaryMenu
                projectCreateMenu =
                new SecondaryMenu(PROJECT_CREATE_MENU_NAME,
                                  "Project Create");
            projectCreateMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_MENU_ITEM_NAME,
                                                        "Select",
                                                        "/html/esc/project",
                                                        "/projectlistpage.do",
                                                        1,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.PROJECT_READ));
            projectCreateMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_CREATE_MENU_ITEM_NAME,
                                                        "New",
                                                        "/html/esc/project",
                                                        "/projectcreatestep1.do",
                                                        2,
                                                        SecurityManager.USER_SECURITY_LEVEL,
                                                        SecureObjectPermissionData.PROJECT_CREATE));
            return projectCreateMenu;
        }
        else if (secondaryMenuName.equals(ZONE_LIST_MENU_NAME))
        {
            SecondaryMenu
                zoneListMenu =
                new SecondaryMenu(ZONE_LIST_MENU_NAME,
                                  "Groups");
            zoneListMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_LIST_MENU_ITEM_NAME,
                                                   "Select",
                                                   "/html/esc/project",
                                                   "/projectzonelistpage.do",
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.PROJECT_READ));
            zoneListMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_CREATE_MENU_ITEM_NAME,
                                                   "New",
                                                   "/html/esc/project",
                                                   "/projectzonecreatepage.do",
                                                   2,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ZONE_CREATE));
            return zoneListMenu;
        }
        else if (secondaryMenuName.equals(ZONE_EDIT_MENU_NAME))
        {
            SecondaryMenu
                zoneEditMenu =
                new SecondaryMenu(ZONE_EDIT_MENU_NAME,
                                  "Edit Group");
            zoneEditMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_LIST_MENU_ITEM_NAME,
                                                   "Select",
                                                   "/html/esc/project",
                                                   "/projectzonelistpage.do",
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.PROJECT_READ));
            zoneEditMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_CREATE_MENU_ITEM_NAME,
                                                   "New",
                                                   "/html/esc/project",
                                                   "/projectzonecreatepage.do",
                                                   2,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ZONE_CREATE));
            zoneEditMenu.add(new SecondaryMenuItem(SecondaryMenu.ZONE_EDIT_MENU_ITEM_NAME,
                                                   "Edit",
                                                   "/html/esc/project",
                                                   "/projectzoneeditpage.do",
                                                   3,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ZONE_UPDATE));
            return zoneEditMenu;
        }
        else if (secondaryMenuName.equals(GIS_IMPORT_MENU_NAME))
        {
            SecondaryMenu
                gisImportMenu =
                new SecondaryMenu(GIS_IMPORT_MENU_NAME,
                                  "GIS Import");
            gisImportMenu.add(new SecondaryMenuItem(SecondaryMenu.GIS_IMPORT_MENU_ITEM_NAME,
                                                    "Import",
                                                    "/html/esc/gis",
                                                    "/gisimportpage.do",
                                                    1,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.PROJECT_READ));
            gisImportMenu.add(new SecondaryMenuItem(SecondaryMenu.GIS_SEARCH_MENU_ITEM_NAME,
                                                    "Search",
                                                    "/html/esc/gis",
                                                    "/gissearchpage.do",
                                                    2,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.PROJECT_READ));
            return gisImportMenu;
        }
        else if (secondaryMenuName.equals(PROJECT_LIST_PREFERENCES_MENU_NAME))
        {
            SecondaryMenu
                projectPrefMenu =
                new SecondaryMenu(PROJECT_LIST_PREFERENCES_MENU_NAME,
                                  "Project List Preferences");
            projectPrefMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_PREFERENCES_MENU_ITEM_NAME,
                                                      "My Settings",
                                                      "/html/esc/project",
                                                      "/projectlistitempage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.PROJECT_READ));
            return projectPrefMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_LIST_MENU_NAME))
        {
            SecondaryMenu
                inspectionListMenu =
                new SecondaryMenu(INSPECTION_LIST_MENU_NAME,
                                  "Inspections");
            inspectionListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_LIST_MENU_ITEM_NAME,
                                                         "Projects",
                                                         "/html/esc/project",
                                                         "/projectlistpage.do",
                                                         1,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.PROJECT_READ));
            inspectionListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_VIEW_MENU_ITEM_NAME,
                                                         "View Project",
                                                         "/html/esc/project",
                                                         "/projectviewpage.do",
                                                         2,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.PROJECT_READ,
                                                         true));
            inspectionListMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME,
                                                         "Inspections",
                                                         "/html/esc/inspection",
                                                         "/inspectionlistpage.do",
                                                         3,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_READ,
                                                         true));
            inspectionListMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_CREATE_MENU_ITEM_NAME,
                                                         "New Inspection",
                                                         "/html/esc/inspection",
                                                         "/inspectioncreatepage.do",
                                                         4,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_CREATE,
                                                         true));
            return inspectionListMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_VIEW_MENU_NAME))
        {
            SecondaryMenu
                inspectionViewMenu =
                new SecondaryMenu(INSPECTION_VIEW_MENU_NAME,
                                  "Inspection View");
            inspectionViewMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME,
                                                         "Inspections",
                                                         "/html/esc/inspection",
                                                         "/inspectionlistpage.do",
                                                         1,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_READ,
                                                         true));
            inspectionViewMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_VIEW_MENU_ITEM_NAME,
                                                         "View Inspection",
                                                         "/html/esc/inspection",
                                                         "/inspectionviewpage.do",
                                                         2,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_READ,
                                                         true));
            inspectionViewMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_EDIT_MENU_ITEM_NAME,
                                                         "Edit Inspection",
                                                         "/html/esc/inspection",
                                                         "/inspectioneditpage.do",
                                                         3,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_UPDATE,
                                                         true));
            return inspectionViewMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_VIEW_NO_EDIT_MENU_NAME))
        {
            SecondaryMenu
                inspectionViewNoEditMenu =
                new SecondaryMenu(INSPECTION_VIEW_MENU_NAME,
                                  "Inspection View");
            inspectionViewNoEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME,
                                                               "Inspections",
                                                               "/html/esc/inspection",
                                                               "/inspectionlistpage.do",
                                                               1,
                                                               SecurityManager.USER_SECURITY_LEVEL,
                                                               SecureObjectPermissionData.INSPECTION_READ,
                                                               true));
            inspectionViewNoEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_VIEW_MENU_ITEM_NAME,
                                                               "View Inspection",
                                                               "/html/esc/inspection",
                                                               "/inspectionviewpage.do",
                                                               2,
                                                               SecurityManager.USER_SECURITY_LEVEL,
                                                               SecureObjectPermissionData.INSPECTION_READ,
                                                               true));
            return inspectionViewNoEditMenu;
        }
/*
        else if (secondaryMenuName.equals(INSPECTION_EDIT_MENU_NAME))
        {
            SecondaryMenu
                inspectionEditMenu =
                new SecondaryMenu(INSPECTION_EDIT_MENU_NAME,
                                  "Inspection Edit");
            inspectionEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME,
                                                         "Inspections",
                                                         "/html/esc/inspection",
                                                         "/inspectionlistpage.do",
                                                         1,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_READ,
                                                         true));
            inspectionEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_VIEW_MENU_ITEM_NAME,
                                                         "View Inspection",
                                                         "/html/esc/inspection",
                                                         "/inspectionviewpage.do",
                                                         2,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_READ,
                                                         true));
            inspectionEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_EDIT_MENU_ITEM_NAME,
                                                         "Edit Inspection",
                                                         "/html/esc/inspection",
                                                         "/inspectioneditpage.do",
                                                         3,
                                                         SecurityManager.USER_SECURITY_LEVEL,
                                                         SecureObjectPermissionData.INSPECTION_UPDATE,
                                                         true));
            return inspectionEditMenu;
        }
*/
        else if (secondaryMenuName.equals(INSPECTION_EMAIL_MENU_NAME))
        {
            SecondaryMenu
                inspectionEmailMenu =
                new SecondaryMenu(INSPECTION_EMAIL_MENU_NAME,
                                  "Inspection Email");
            return inspectionEmailMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_TEMPLATE_LIST_MENU_NAME))
        {
            SecondaryMenu
                inspectionTemplateListMenu =
                new SecondaryMenu(INSPECTION_TEMPLATE_LIST_MENU_NAME,
                                  "Inspection Tempaltes");
            inspectionTemplateListMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME,
                                                                 "Select",
                                                                 "/html/esc/inspection",
                                                                 "/inspectiontemplatelistpage.do",
                                                                 1,
                                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                                 SecureObjectPermissionData.BMP_TEMPLATE_READ));
            inspectionTemplateListMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_CREATE_MENU_ITEM_NAME,
                                                                 "New",
                                                                 "/html/esc/inspection",
                                                                 "/inspectiontemplatecreatepage.do",
                                                                 2,
                                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                                 SecureObjectPermissionData.BMP_TEMPLATE_CREATE));
            return inspectionTemplateListMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_TEMPLATE_EDIT_MENU_NAME))
        {
            SecondaryMenu
                inspectionTemplateEditMenu =
                new SecondaryMenu(INSPECTION_TEMPLATE_EDIT_MENU_NAME,
                                  "Edit Template");
            inspectionTemplateEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME,
                                                                 "Select",
                                                                 "/html/esc/inspection",
                                                                 "/inspectiontemplatelistpage.do",
                                                                 1,
                                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                                 SecureObjectPermissionData.BMP_TEMPLATE_READ));
            inspectionTemplateEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_CREATE_MENU_ITEM_NAME,
                                                                 "New",
                                                                 "/html/esc/inspection",
                                                                 "/inspectiontemplatecreatepage.do",
                                                                 2,
                                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                                 SecureObjectPermissionData.BMP_TEMPLATE_CREATE));
            inspectionTemplateEditMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_EDIT_MENU_ITEM_NAME,
                                                                 "Edit",
                                                                 "/html/esc/inspection",
                                                                 "/inspectiontemplateeditpage.do",
                                                                 3,
                                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                                 SecureObjectPermissionData.BMP_TEMPLATE_CREATE));
            return inspectionTemplateEditMenu;
        }
        else if (secondaryMenuName.equals(INSPECTION_TEMPLATE_CREATE_MENU_NAME))
        {
            SecondaryMenu
                inspectionTemplateCreateMenu =
                new SecondaryMenu(INSPECTION_TEMPLATE_CREATE_MENU_NAME,
                                  "New Template");
            inspectionTemplateCreateMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_LIST_MENU_NAME,
                                                                   "Select",
                                                                   "/html/esc/inspection",
                                                                   "/inspectiontemplatelistpage.do",
                                                                   1,
                                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                                   SecureObjectPermissionData.BMP_TEMPLATE_READ));
            inspectionTemplateCreateMenu.add(new SecondaryMenuItem(SecondaryMenu.INSPECTION_TEMPLATE_CREATE_MENU_ITEM_NAME,
                                                                   "New",
                                                                   "/html/esc/inspection",
                                                                   "/inspectiontemplatecreatepage.do",
                                                                   2,
                                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                                   SecureObjectPermissionData.BMP_TEMPLATE_CREATE));
            return inspectionTemplateCreateMenu;
        }
        else if (secondaryMenuName.equals(PARTNER_LIST_MENU_NAME))
        {
            SecondaryMenu
                partnerListMenu =
                new SecondaryMenu(PARTNER_LIST_MENU_NAME,
                                  "Partners");
            partnerListMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/esc/partner",
                                                      "/partnerlistpage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_CREATE));
            partnerListMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/esc/partner",
                                                      "/partnercreatepage.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_CREATE));
            partnerListMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_FIND_MENU_ITEM_NAME,
                                                      "Search",
                                                      "/html/esc/partner",
                                                      "/partnerfindpage.do",
                                                      3,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_READ));
            return partnerListMenu;
        }
        else if (secondaryMenuName.equals(PARTNER_EDIT_MENU_NAME))
        {
            SecondaryMenu
                partnerEditMenu =
                new SecondaryMenu(PARTNER_EDIT_MENU_NAME,
                                  "Edit Partner");
            partnerEditMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/esc/partner",
                                                      "/partnerlistpage.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_READ));
            partnerEditMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/esc/partner",
                                                      "/partnercreatepage.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_CREATE));
            partnerEditMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_EDIT_MENU_ITEM_NAME,
                                                      "View",
                                                      "/html/esc/partner",
                                                      "/partnereditpage.do",
                                                      3,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_UPDATE));
            partnerEditMenu.add(new SecondaryMenuItem(SecondaryMenu.PARTNER_FIND_MENU_ITEM_NAME,
                                                      "Search",
                                                      "/html/esc/partner",
                                                      "/partnerfindpage.do",
                                                      4,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CLIENT_READ));
            return partnerEditMenu;
        }
        else if (secondaryMenuName.equals(REPORT_MENU_NAME))
        {
            SecondaryMenu
                reportMenu =
                new SecondaryMenu(REPORT_MENU_NAME,
                                  "Report");
            return reportMenu;
        }
        else if (secondaryMenuName.equals(ADMIN_MENU_NAME))
        {
            SecondaryMenu
                adminMenu =
                new SecondaryMenu(ADMIN_MENU_NAME,
                                  "Admin");
            adminMenu.add(new SecondaryMenuItem(SecondaryMenu.ADMIN_CLIENT_SELECT_MENU_ITEM_NAME,
                                                "Select",
                                                "/html/esc/admin",
                                                "/adminclientlistaction.do",
                                                1,
                                                SecurityManager.USER_SECURITY_LEVEL));
            adminMenu.add(new SecondaryMenuItem(SecondaryMenu.ADMIN_PARTNER_SELECT_MENU_ITEM_NAME,
                                                "Partners",
                                                "/html/esc/admin",
                                                "/adminclientlistaction.do?partners",
                                                2,
                                                SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return adminMenu;
        }
        else if (secondaryMenuName.equals(ADMIN_REPORT_MENU_NAME))
        {
            SecondaryMenu
                adminReportMenu =
                new SecondaryMenu(ADMIN_REPORT_MENU_NAME,
                                  "Report");
            return adminReportMenu;
        }
        else if (secondaryMenuName.equals(BMP_NONE_MENU_NAME)
                 || secondaryMenuName.equals(BMP_LIST_MENU_NAME)
                 || secondaryMenuName.equals(BMP_VIEW_MENU_NAME))
        {
            return bmpSecondaryMenu(secondaryMenuName);
        }
        else if (secondaryMenuName.equals(PROJECT_TYPE_NONE_MENU_NAME)
                 || secondaryMenuName.equals(PROJECT_TYPE_LIST_MENU_NAME)
                 || secondaryMenuName.equals(PROJECT_TYPE_VIEW_MENU_NAME))
        {
            return projectTypeSecondaryMenu(secondaryMenuName);
        }
        else if (secondaryMenuName.equals(EVENT_LIST_MENU_NAME))
        {
            SecondaryMenu
                eventListMenu =
                new SecondaryMenu(EVENT_LIST_MENU_NAME,
                                  "Events");
            eventListMenu.add(new SecondaryMenuItem(SecondaryMenu.EVENT_LIST_MENU_ITEM_NAME,
                                                    "Select",
                                                    "/html/esc/event",
                                                    "/eventListPageAction.do",
                                                    2,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.EVENT_READ));
            eventListMenu.add(new SecondaryMenuItem(SecondaryMenu.EVENT_CREATE_MENU_ITEM_NAME,
                                                    "New",
                                                    "/html/esc/event",
                                                    "/eventCreatePageAction.do",
                                                    3,
                                                    SecurityManager.USER_SECURITY_LEVEL,
                                                    SecureObjectPermissionData.EVENT_CREATE));
            return eventListMenu;
        }
        else if (secondaryMenuName.equals(EVENT_COMPLIANCE_REPORT_MENU_NAME))
        {
            SecondaryMenu
                eventComplianceReportMenu =
                new SecondaryMenu(EVENT_COMPLIANCE_REPORT_MENU_NAME,
                                  "Event Compliance Report");
            eventComplianceReportMenu.add(new SecondaryMenuItem(SecondaryMenu.EVENT_LIST_MENU_ITEM_NAME,
                                                                "Events",
                                                                "/html/esc/event",
                                                                "/eventListPageAction.do",
                                                                1,
                                                                SecurityManager.USER_SECURITY_LEVEL,
                                                                SecureObjectPermissionData.EVENT_READ));
            eventComplianceReportMenu.add(new SecondaryMenuItem(SecondaryMenu.EVENT_COMPLIANCE_REPORT_MENU_ITEM_NAME,
                                                                "Report",
                                                                "/html/esc/event",
                                                                "/eventComplianceReportPageAction.do",
                                                                2,
                                                                SecurityManager.USER_SECURITY_LEVEL,
                                                                SecureObjectPermissionData.REPORT_READ));
            return eventComplianceReportMenu;
        }
        else if (secondaryMenuName.equals(CORRECTIVE_ACTION_LIST_MENU_NAME))
        {
            SecondaryMenu
                correctiveActionListMenu =
                new SecondaryMenu(CORRECTIVE_ACTION_LIST_MENU_NAME,
                                  "Corrective Actions");
            correctiveActionListMenu.add(new SecondaryMenuItem(SecondaryMenu.CORRECTIVE_ACTION_LIST_MENU_ITEM_NAME,
                                                               "Corrective Actions",
                                                               "/html/esc/correctiveaction",
                                                               "/correctiveactionlistpage.do",
                                                               2,
                                                               SecurityManager.USER_SECURITY_LEVEL,
                                                               SecureObjectPermissionData.PROJECT_READ));
            return correctiveActionListMenu;
        }
        else if (secondaryMenuName.equals(NONE_MENU_NAME))
        {
            if (noneMenu
                == null)
            {
                noneMenu =
                    new SecondaryMenu(NONE_MENU_NAME,
                                      "None Menu");
            }
            return noneMenu;
        }
        else
        {
            LOG.error("Could not locate Secondary Menu "
                      + secondaryMenuName);
        }
        return null;
    }

    private static SecondaryMenu projectTypeSecondaryMenu(String name)
    {
        SecondaryMenu
            returnMenu =
            null;
        LOG.debug("SecondaryMenu.bmpCatSecondaryMenu("
                  + name
                  + ")");
        if (name.equals(PROJECT_TYPE_NONE_MENU_NAME))
        {
            SecondaryMenu
                projectTypeNoneMenu =
                new SecondaryMenu(PROJECT_TYPE_NONE_MENU_NAME,
                                  "Project Type None");
            returnMenu =
                projectTypeNoneMenu;
        }
        else if (name.equals(PROJECT_TYPE_LIST_MENU_NAME))
        {
            SecondaryMenu
                projectTypeListMenu =
                new SecondaryMenu(PROJECT_TYPE_LIST_MENU_NAME,
                                  "Project Type List");
            projectTypeListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_LIST_MENU_ITEM_NAME,
                                                          "Select",
                                                          "/html/esc/project",
                                                          "/projecttypelistpage.do",
                                                          1,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_READ));
            projectTypeListMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_CREATE_MENU_ITEM_NAME,
                                                          "New",
                                                          "/html/esc/project",
                                                          "/projecttypecreatepage.do",
                                                          2,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_CREATE));
            returnMenu =
                projectTypeListMenu;
        }
        else if (name.equals(PROJECT_TYPE_VIEW_MENU_NAME))
        {
            SecondaryMenu
                projectTypeViewMenu =
                new SecondaryMenu(PROJECT_TYPE_VIEW_MENU_NAME,
                                  "Project Type View");
            projectTypeViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_LIST_MENU_ITEM_NAME,
                                                          "Select",
                                                          "/html/esc/project",
                                                          "/projecttypelistpage.do",
                                                          1,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_CREATE));
            projectTypeViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_CREATE_MENU_ITEM_NAME,
                                                          "New",
                                                          "/html/esc/project",
                                                          "/projecttypecreatepage.do",
                                                          2,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_CREATE));
            projectTypeViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_VIEW_MENU_ITEM_NAME,
                                                          "View",
                                                          "/html/esc/project",
                                                          "/projecttypeviewpage.do",
                                                          3,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_READ));
            projectTypeViewMenu.add(new SecondaryMenuItem(SecondaryMenu.PROJECT_TYPE_EDIT_MENU_ITEM_NAME,
                                                          "Edit",
                                                          "/html/esc/project",
                                                          "/projecttypeeditpage.do",
                                                          4,
                                                          SecurityManager.USER_SECURITY_LEVEL,
                                                          SecureObjectPermissionData.PROJECT_UPDATE));
            returnMenu =
                projectTypeViewMenu;
        }
        if (returnMenu
            != null)
        {
            LOG.debug("SecondaryMenu.projectTypeSecondaryMenuNameSecondaryMenu: Return Menu Description = "
                      + returnMenu.getDescription());
        }
        return returnMenu;
    }

    private static SecondaryMenu bmpSecondaryMenu(String bmpSecondaryMenuName)
    {
        SecondaryMenu
            bmpReturnMenu =
            null;
        LOG.debug("SecondaryMenu.bmpSecondaryMenu("
                  + bmpSecondaryMenuName
                  + ")");
        if (bmpSecondaryMenuName.equals(BMP_NONE_MENU_NAME))
        {
            SecondaryMenu
                bmpNoneMenu =
                new SecondaryMenu(BMP_NONE_MENU_NAME,
                                  "BMP None");
            bmpReturnMenu =
                bmpNoneMenu;
        }
        else if (bmpSecondaryMenuName.equals(BMP_LIST_MENU_NAME))
        {
            SecondaryMenu
                bmpListMenu =
                new SecondaryMenu(BMP_LIST_MENU_NAME,
                                  "BMP List");
            bmpListMenu.add(new SecondaryMenuItem(SecondaryMenu.BMP_LIST_MENU_NAME,
                                                  "Select",
                                                  "/html/esc/bmp",
                                                  "/bmplistpage.do",
                                                  1,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.BMP_READ));
            bmpListMenu.add(new SecondaryMenuItem(SecondaryMenu.BMP_CREATE_MENU_ITEM_NAME,
                                                  "New",
                                                  "/html/esc/bmp",
                                                  "/bmpcreateaction.do",
                                                  2,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.BMP_CREATE));
            bmpListMenu.add(new SecondaryMenuItem(SecondaryMenu.BMP_EDIT_MENU_ITEM_NAME,
                                                  "Load from BMP Library",
                                                  "/html/esc/bmpdb",
                                                  "/bmploadlibraryaction.do",
                                                  3,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.BMP_LIBRARY_READ));
            bmpListMenu.add(new SecondaryMenuItem(SecondaryMenu.BMP_ADD_MENU_ITEM_NAME,
                                                  "Save As BMP Library",
                                                  "/html/esc/bmpdb",
                                                  "/bmpaddlibraryaction.do",
                                                  4,
                                                  SecurityManager.USER_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.BMP_LIBRARY_UPDATE));
            bmpReturnMenu =
                bmpListMenu;
        }
        if (bmpReturnMenu
            != null)
        {
            LOG.debug("SecondaryMenu.bmpSecondaryMenu: Return Menu Description = "
                      + bmpReturnMenu.getDescription());
        }
        return bmpReturnMenu;
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
            == null)
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
            new SecondaryMenuItem();
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
