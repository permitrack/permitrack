package com.sehinc.security.action.navigation;

import com.sehinc.common.CommonConstants;
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
        CLIENT_LIST_MENU_NAME =
        "CLIENT_LIST_MENU";
    public static final
    String
        CLIENT_VIEW_MENU_NAME =
        "CLIENT_VIEW_MENU";
    public static final
    String
        CLIENT_DV_MENU_NAME =
        "CLIENT_DV_MENU_NAME";
    public static final
    String
        CLIENT_ACTIVITY_MENU_NAME =
        "CLIENT_ACTIVITY_MENU_NAME";
    public static final
    String
        CLIENT_EC_MENU_NAME =
        "CLIENT_EC_MENU_NAME";
    public static final
    String
        X_CLIENT_CREATE_MENU_ITEM_NAME =
        "X_CLIENT_CREATE_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_EDIT_MENU_ITEM_NAME =
        "X_CLIENT_EDIT_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_DELETE_MENU_ITEM_NAME =
        "X_CLIENT_DELETE_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_DV_EDIT_MENU_ITEM_NAME =
        "X_CLIENT_DV_EDIT_MENU_ITEM_NAME";
    /*
        public static final
        String
            X_CLIENT_EC_MENU_ITEM_NAME =
            "X_CLIENT_EC_MENU_ITEM_NAME";
    */
    public static final
    String
        X_CLIENT_LOGO_MENU_ITEM_NAME =
        "X_CLIENT_LOGO_MENU_ITEM";
    public static final
    String
        USER_LIST_MENU_NAME =
        "USER_LIST_MENU_NAME";
    public static final
    String
        USER_VIEW_MENU_NAME =
        "USER_VIEW_MENU_NAME";
    public static final
    String
        X_USER_CREATE_MENU_ITEM_NAME =
        "X_USER_CREATE_MENU_ITEM_NAME";
    public static final
    String
        X_USER_ADD_MENU_ITEM_NAME =
        "X_USER_ADD_MENU_ITEM_NAME";
    public static final
    String
        X_USER_EDIT_MENU_ITEM_NAME =
        "X_USER_EDIT_MENU_ITEM_NAME";
    public static final
    String
        X_USER_PASSWORD_MENU_ITEM_NAME =
        "X_USER_PASSWORD_MENU_ITEM_NAME";
    public static final
    String
        X_USER_SEARCHES_MENU_ITEM_NAME =
        "X_USER_SEARCH_MENU_ITEM_NAME";
    public static final
    String
        SEARCH_LIST_MENU_NAME =
        "SEARCH_LIST_MENU_NAME";
    public static final
    String
        X_SEARCH_CREATE_MENU_ITEM_NAME =
        "X_SEARCH_CREATE_MENU_ITEM_NAME";
    /*
        public static final
        String
            X_SEARCH_USER_VIEW_MENU_ITEM_NAME =
            "X_SEARCH_USER_VIEW_MENU_ITEM_NAME";
    */
    public static final
    String
        ROLE_LIST_MENU_NAME =
        "ROLE_LIST_MENU_NAME";
    public static final
    String
        ROLE_EC_VIEW_MENU_NAME =
        "ROLE_EC_VIEW_MENU_NAME";
    public static final
    String
        X_ROLE_EC_CREATE_MENU_ITEM_NAME =
        "X_ROLE_EC_CREATE_MENU_ITEM_NAME";
    public static final
    String
        X_ROLE_EC_EDIT_MENU_ITEM_NAME =
        "X_ROLE_EC_EDIT_MENU_ITEM_NAME";
    /*
        public static final
        String
            X_ROLE_EC_DELETE_MENU_ITEM_NAME =
            "X_ROLE_EC_DELETE_MENU_ITEM_NAME";
    */
    public static final
    String
        ROLE_EV_VIEW_MENU_NAME =
        "ROLE_EV_VIEW_MENU_NAME";
    public static final
    String
        X_ROLE_EV_CREATE_MENU_ITEM_NAME =
        "X_ROLE_EV_CREATE_MENU_ITEM_NAME";
    public static final
    String
        X_ROLE_EV_EDIT_MENU_ITEM_NAME =
        "X_ROLE_EV_EDIT_MENU_ITEM_NAME";
    /*
        public static final
        String
            X_ROLE_EV_DELETE_MENU_ITEM_NAME =
            "X_ROLE_EV_DELETE_MENU_ITEM_NAME";
    */
    public static final
    String
        CONTACT_LIST_MENU_NAME =
        "CONTACT_LIST_MENU_NAME";
    public static final
    String
        CONTACT_VIEW_MENU_NAME =
        "CONTACT_VIEW_MENU_NAME";
    public static final
    String
        X_CONTACT_CREATE_MENU_ITEM_NAME =
        "X_CONTACT_CREATE_MENU_ITEM_NAME";
    public static final
    String
        X_CONTACT_EDIT_MENU_ITEM_NAME =
        "X_CONTACT_EDIT_MENU_ITEM_NAME";
    public static final
    String
        REPORT_LIST_MENU_NAME =
        "REPORT_LIST_MENU_NAME";
    private static
    SecondaryMenu
        noneMenu =
        null;
    /*
        public static final
        String
            X_USER_DELETE_MENU_ITEM_NAME =
            "X_USER_DELETE_MENU_ITEM_NAME";
    */
    public static final
    String
        SECURITY_USER_MENU_ITEM_NAME =
        "SECURITY_USER_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_VIEW_MENU_ITEM_NAME =
        "X_CLIENT_VIEW_MENU_ITEM_NAME";
    public static final
    String
        X_USER_VIEW_MENU_ITEM_NAME =
        "X_USER_VIEW_MENU_ITEM_NAME";
    public static final
    String
        CLIENT_LOGO_MENU_NAME =
        "CLIENT_LOGO_MENU_NAME";
    public static final
    String
        SECURITY_REPORT_MENU_ITEM_NAME =
        "SECURITY_REPORT_MENU_ITEM";
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
    public static final
    String
        X_CLIENT_SELECT_MENU_ITEM_NAME =
        "X_CLIENT_SELECT_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_DV_VIEW_ITEM_NAME =
        "X_CLIENT_DV_VIEW_ITEM_NAME";
    public static final
    String
        X_CLIENT_EC_VIEW_MENU_ITEM_NAME =
        "X_CLIENT_EC_VIEW_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_EC_EDIT_MENU_ITEM_NAME =
        "X_CLIENT_EC_EDIT_MENU_ITEM_NAME";
    public static final
    String
        X_ROLE_LIST_MENU_ITEM_NAME =
        "X_ROLE_LIST_MENU_ITEM_NAME";
    public static final
    String
        X_ROLE_VIEW_MENU_ITEM_NAME =
        "X_ROLE_VIEW_MENU_ITEM_NAME";
    public static final
    String
        X_SEARCH_LIST_MENU_ITEM_NAME =
        "X_SEARCH_LIST_MENU_ITEM_NAME";
    public static final
    String
        X_CONTACT_LIST_MENU_ITEM_NAME =
        "X_CONTACT_LIST_MENU_ITEM_NAME";
    /*
        public static final
        String
            X_CONTACT_DELETE_MENU_ITEM_NAME =
            "X_CONTACT_DELETE_MENU_ITEM_NAME";
    */
    public static final
    String
        X_CONTACT_VIEW_MENU_ITEM_NAME =
        "X_CONTACT_VIEW_MENU_ITEM_NAME";
    public static final
    String
        X_CLIENT_ACTIVITY_BUBBLES_MENU_NAME =
        "X_CLIENT_ACTIVITY_BUBBLES_MENU_NAME";
    public static final
    String
        X_CLIENT_ACTIVITY_GRID_MENU_NAME =
        "X_CLIENT_ACTIVITY_GRID_MENU_NAME";
    public static final
    String
        X_CLIENT_ACTIVITY_GRAPH_MENU_NAME =
        "X_CLIENT_ACTIVITY_GRAPH_MENU_NAME";

    private SecondaryMenu(String name, String description)
    {
        this.name =
            name;
        this.description =
            description;
    }

    public static SecondaryMenu getInstance(String secondaryMenuName)
    {
        return getInstance(secondaryMenuName,
                           null);
    }

    public static SecondaryMenu getInstance(String secondaryMenuName, Integer clientId)
    {
        String
            strLog =
            "com.sehinc.security.action.navigation.SecondaryMenu.getInstance("
            + secondaryMenuName
            + ")";
        LOG.debug(strLog
                  + "in method");
        if (secondaryMenuName.equals(CLIENT_LIST_MENU_NAME))
        {
            SecondaryMenu
                clientListMenu =
                new SecondaryMenu(CLIENT_LIST_MENU_NAME,
                                  "Client List");
            clientListMenu.add(new SecondaryMenuItem(X_CLIENT_SELECT_MENU_ITEM_NAME,
                                                     "Select",
                                                     "/html/sec/client",
                                                     "/clientlistpageaction.do",
                                                     1,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_READ));
            clientListMenu.add(new SecondaryMenuItem(X_CLIENT_CREATE_MENU_ITEM_NAME,
                                                     "New",
                                                     "/html/sec/client",
                                                     "/clientcreatepageaction.do",
                                                     2,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_CREATE));
            return clientListMenu;
        }
        else if (secondaryMenuName.equals(CLIENT_ACTIVITY_MENU_NAME))
        {
            SecondaryMenu
                menu =
                new SecondaryMenu(CLIENT_ACTIVITY_MENU_NAME,
                                  "Activity");
            menu.add(new SecondaryMenuItem(X_CLIENT_ACTIVITY_BUBBLES_MENU_NAME,
                                           "Timeline",
                                           "/html/sec/client",
                                           "/clientactivitypageaction.do?type=timeline",
                                           1,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                           SecureObjectPermissionData.CLIENT_READ));
            menu.add(new SecondaryMenuItem(X_CLIENT_ACTIVITY_GRID_MENU_NAME,
                                           "Grid",
                                           "/html/sec/client",
                                           "/clientactivitypageaction.do?type=grid",
                                           2,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                           SecureObjectPermissionData.CLIENT_READ));
            menu.add(new SecondaryMenuItem(X_CLIENT_ACTIVITY_GRAPH_MENU_NAME,
                                           "Graph",
                                           "/html/sec/client",
                                           "/clientactivitypageaction.do?type=graph",
                                           3,
                                           SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                           SecureObjectPermissionData.CLIENT_READ));
            return menu;
        }
        else if (secondaryMenuName.equals(CLIENT_DV_MENU_NAME))
        {
            SecondaryMenu
                clientDVMenu =
                new SecondaryMenu(CLIENT_DV_MENU_NAME,
                                  "DataView Online");
            clientDVMenu.add(new SecondaryMenuItem(X_CLIENT_DV_VIEW_ITEM_NAME,
                                                   "View",
                                                   "/html/sec/client",
                                                   "/clientdvviewpageaction.do",
                                                   1,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_READ,
                                                   CommonConstants.DATA_VIEW_MODULE));
            clientDVMenu.add(new SecondaryMenuItem(X_CLIENT_DV_EDIT_MENU_ITEM_NAME,
                                                   "Edit",
                                                   "/html/sec/client",
                                                   "/clientdveditpageaction.do",
                                                   2,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_UPDATE,
                                                   CommonConstants.DATA_VIEW_MODULE));
            return clientDVMenu;
        }
        else if (secondaryMenuName.equals(CLIENT_EC_MENU_NAME))
        {
            SecondaryMenu
                clientECMenu =
                new SecondaryMenu(CLIENT_EC_MENU_NAME,
                                  "Erosion Control (ESC)");
            clientECMenu.add(new SecondaryMenuItem(X_CLIENT_EC_VIEW_MENU_ITEM_NAME,
                                                   "View",
                                                   "/html/sec/client",
                                                   "/clienteceditpageaction.do",
                                                   1,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_UPDATE,
                                                   CommonConstants.EROSION_CONTROL_MODULE));
            clientECMenu.add(new SecondaryMenuItem(X_CLIENT_EC_EDIT_MENU_ITEM_NAME,
                                                   "Edit",
                                                   "/html/sec/client",
                                                   "/clienteceditpageaction.do",
                                                   2,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_UPDATE,
                                                   CommonConstants.EROSION_CONTROL_MODULE));
            return clientECMenu;
        }
        else if (secondaryMenuName.equals(OPTIONS_LIST_MENU))
        {
            SecondaryMenu
                optionsMenu =
                new SecondaryMenu(OPTIONS_LIST_MENU,
                                  "Settings");
            optionsMenu.add(new SecondaryMenuItem(X_CLIENT_LOGO_MENU_ITEM_NAME,
                                                  "Logo",
                                                  "/html/sec/client",
                                                  "/clientlogoselectaction.do",
                                                  1,
                                                  SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.CLIENT_UPDATE));
            optionsMenu.add(new SecondaryMenuItem(X_CLIENT_DV_VIEW_ITEM_NAME,
                                                  "DVO",
                                                  "/html/sec/client",
                                                  "/clientdvviewpageaction.do",
                                                  2,
                                                  SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.CLIENT_READ,
                                                  CommonConstants.DATA_VIEW_MODULE));
            optionsMenu.add(new SecondaryMenuItem(X_CLIENT_EC_VIEW_MENU_ITEM_NAME,
                                                  "ESC",
                                                  "/html/sec/client",
                                                  "/clientecviewpageaction.do",
                                                  3,
                                                  SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.CLIENT_READ,
                                                  CommonConstants.EROSION_CONTROL_MODULE));
            optionsMenu.add(new SecondaryMenuItem(SECURITY_REPORT_MENU_ITEM_NAME,
                                                  "ENV",
                                                  "/html/sec/report",
                                                  "/reportaccesseditpageaction.do",
                                                  4,
                                                  SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                  SecureObjectPermissionData.RO_REPORT_UPDATE,
                                                  CommonConstants.ENVIRONMENT_MODULE));
            return optionsMenu;
        }
        else if (secondaryMenuName.equals(CLIENT_LOGO_MENU_NAME))
        {
            SecondaryMenu
                clientLogoMenu =
                new SecondaryMenu(CLIENT_LOGO_MENU_NAME,
                                  "Client Logo");
            clientLogoMenu.add(new SecondaryMenuItem(X_CLIENT_LOGO_MENU_ITEM_NAME,
                                                     "Logo",
                                                     "/html/sec/client",
                                                     "/clientlogoselectaction.do",
                                                     1,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_UPDATE));
            return clientLogoMenu;
        }
        else if (secondaryMenuName.equals(CLIENT_VIEW_MENU_NAME))
        {
            SecondaryMenu
                clientViewMenu =
                new SecondaryMenu(CLIENT_VIEW_MENU_NAME,
                                  "Client View");
            clientViewMenu.add(new SecondaryMenuItem(X_CLIENT_SELECT_MENU_ITEM_NAME,
                                                     "Select",
                                                     "/html/sec/client",
                                                     "/clientlistpageaction.do",
                                                     1,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_READ));
            clientViewMenu.add(new SecondaryMenuItem(X_CLIENT_CREATE_MENU_ITEM_NAME,
                                                     "New",
                                                     "/html/sec/client",
                                                     "/clientcreatepageaction.do",
                                                     2,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_CREATE));
            clientViewMenu.add(new SecondaryMenuItem(X_CLIENT_VIEW_MENU_ITEM_NAME,
                                                     "View",
                                                     "/html/sec/client",
                                                     "/clientviewpageaction.do",
                                                     3,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_READ));
            clientViewMenu.add(new SecondaryMenuItem(X_CLIENT_EDIT_MENU_ITEM_NAME,
                                                     "Edit",
                                                     "/html/sec/client",
                                                     "/clienteditpageaction.do",
                                                     7,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_UPDATE));
            clientViewMenu.add(new SecondaryMenuItem(X_CLIENT_DELETE_MENU_ITEM_NAME,
                                                     "Delete",
                                                     "/html/sec/client",
                                                     "/clientdeleteaction.do?id="
                                                     + clientId,
                                                     8,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.CLIENT_DELETE));
            return clientViewMenu;
        }
        else if (secondaryMenuName.equals(USER_LIST_MENU_NAME))
        {
            SecondaryMenu
                userListMenu =
                new SecondaryMenu(USER_LIST_MENU_NAME,
                                  "User List");
            userListMenu.add(new SecondaryMenuItem(SECURITY_USER_MENU_ITEM_NAME,
                                                   "Select",
                                                   "/html/sec/user",
                                                   "/userlistpageaction.do",
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_READ));
            userListMenu.add(new SecondaryMenuItem(X_USER_CREATE_MENU_ITEM_NAME,
                                                   "New",
                                                   "/html/sec/user",
                                                   "/usercreatepageaction.do",
                                                   2,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_CREATE));
            userListMenu.add(new SecondaryMenuItem(X_USER_ADD_MENU_ITEM_NAME,
                                                   "Add Existing",
                                                   "/html/sec/user",
                                                   "/useraddpageaction.do",
                                                   3,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_CREATE));
            return userListMenu;
        }
        else if (secondaryMenuName.equals(USER_VIEW_MENU_NAME))
        {
            SecondaryMenu
                userViewMenu =
                new SecondaryMenu(USER_VIEW_MENU_NAME,
                                  "User View");
            userViewMenu.add(new SecondaryMenuItem(SECURITY_USER_MENU_ITEM_NAME,
                                                   "Select",
                                                   "/html/sec/user",
                                                   "/userlistpageaction.do",
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_READ));
            userViewMenu.add(new SecondaryMenuItem(X_USER_CREATE_MENU_ITEM_NAME,
                                                   "New",
                                                   "/html/sec/user",
                                                   "/usercreatepageaction.do",
                                                   2,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_CREATE));
            userViewMenu.add(new SecondaryMenuItem(X_USER_VIEW_MENU_ITEM_NAME,
                                                   "View",
                                                   "/html/sec/user",
                                                   "/userviewpageaction.do",
                                                   3,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_READ));
            userViewMenu.add(new SecondaryMenuItem(X_USER_PASSWORD_MENU_ITEM_NAME,
                                                   "Password",
                                                   "/html/sec/user",
                                                   "/userchangepasswordpageaction.do",
                                                   4,
                                                   SecurityManager.USER_SECURITY_LEVEL));
            userViewMenu.add(new SecondaryMenuItem(X_USER_SEARCHES_MENU_ITEM_NAME,
                                                   "Saved Search Filters (ESC)",
                                                   "/html/sec/user",
                                                   "/usersearchlistpageaction.do",
                                                   5,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.CLIENT_READ,
                                                   CommonConstants.EROSION_CONTROL_MODULE));
            userViewMenu.add(new SecondaryMenuItem(X_USER_EDIT_MENU_ITEM_NAME,
                                                   "Edit",
                                                   "/html/sec/user",
                                                   "/usereditpageaction.do",
                                                   6,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.USER_READ));
            return userViewMenu;
        }
        else if (secondaryMenuName.equals(SEARCH_LIST_MENU_NAME))
        {
            SecondaryMenu
                searchListMenu =
                new SecondaryMenu(SEARCH_LIST_MENU_NAME,
                                  "Search List");
            searchListMenu.add(new SecondaryMenuItem(X_SEARCH_LIST_MENU_ITEM_NAME,
                                                     "Select",
                                                     "/html/sec/user",
                                                     "/usersearchlistpageaction.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL));
            searchListMenu.add(new SecondaryMenuItem(X_SEARCH_CREATE_MENU_ITEM_NAME,
                                                     "New",
                                                     "/html/sec/user",
                                                     "/usersearchcreatepageaction.do",
                                                     2,
                                                     SecurityManager.USER_SECURITY_LEVEL));
            return searchListMenu;
        }
        else if (secondaryMenuName.equals(ROLE_LIST_MENU_NAME))
        {
            SecondaryMenu
                roleListMenu =
                new SecondaryMenu(ROLE_LIST_MENU_NAME,
                                  "Role List");
            roleListMenu.add(new SecondaryMenuItem(X_ROLE_LIST_MENU_ITEM_NAME,
                                                   "Select",
                                                   "/html/sec/role",
                                                   "/rolelistpageaction.do",
                                                   1,
                                                   SecurityManager.USER_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ROLE_READ));
            roleListMenu.add(new SecondaryMenuItem(X_ROLE_EC_CREATE_MENU_ITEM_NAME,
                                                   "New ESC Role",
                                                   "/html/sec/role",
                                                   "/roleeccreatepageaction.do",
                                                   2,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ROLE_CREATE,
                                                   CommonConstants.EROSION_CONTROL_MODULE));
            roleListMenu.add(new SecondaryMenuItem(X_ROLE_EV_CREATE_MENU_ITEM_NAME,
                                                   "New ENV Role",
                                                   "/html/sec/role",
                                                   "/roleevcreatepageaction.do",
                                                   3,
                                                   SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                   SecureObjectPermissionData.ROLE_CREATE,
                                                   CommonConstants.ENVIRONMENT_MODULE));
            return roleListMenu;
        }
        else if (secondaryMenuName.equals(ROLE_EC_VIEW_MENU_NAME))
        {
            SecondaryMenu
                roleECViewMenu =
                new SecondaryMenu(ROLE_EC_VIEW_MENU_NAME,
                                  "Role EC View");
            roleECViewMenu.add(new SecondaryMenuItem(X_ROLE_LIST_MENU_ITEM_NAME,
                                                     "Select",
                                                     "/html/sec/role",
                                                     "/rolelistpageaction.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_READ));
            roleECViewMenu.add(new SecondaryMenuItem(X_ROLE_EC_CREATE_MENU_ITEM_NAME,
                                                     "New ESC",
                                                     "/html/sec/role",
                                                     "/roleeccreatepageaction.do",
                                                     2,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_CREATE,
                                                     CommonConstants.EROSION_CONTROL_MODULE));
            roleECViewMenu.add(new SecondaryMenuItem(X_ROLE_EV_CREATE_MENU_ITEM_NAME,
                                                     "New ENV",
                                                     "/html/sec/role",
                                                     "/roleevcreatepageaction.do",
                                                     3,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_CREATE,
                                                     CommonConstants.ENVIRONMENT_MODULE));
            roleECViewMenu.add(new SecondaryMenuItem(X_ROLE_VIEW_MENU_ITEM_NAME,
                                                     "View",
                                                     "/html/sec/role",
                                                     "/roleecviewpageaction.do",
                                                     4,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_READ,
                                                     CommonConstants.EROSION_CONTROL_MODULE));
            roleECViewMenu.add(new SecondaryMenuItem(X_ROLE_EC_EDIT_MENU_ITEM_NAME,
                                                     "Edit",
                                                     "/html/sec/role",
                                                     "/roleeceditpageaction.do",
                                                     5,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_UPDATE,
                                                     CommonConstants.EROSION_CONTROL_MODULE));
            return roleECViewMenu;
        }
        else if (secondaryMenuName.equals(ROLE_EV_VIEW_MENU_NAME))
        {
            SecondaryMenu
                roleEVViewMenu =
                new SecondaryMenu(ROLE_EV_VIEW_MENU_NAME,
                                  "Role EV View");
            roleEVViewMenu.add(new SecondaryMenuItem(X_ROLE_LIST_MENU_ITEM_NAME,
                                                     "Select",
                                                     "/html/sec/role",
                                                     "/rolelistpageaction.do",
                                                     1,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_READ));
            roleEVViewMenu.add(new SecondaryMenuItem(X_ROLE_EC_CREATE_MENU_ITEM_NAME,
                                                     "New ESC",
                                                     "/html/sec/role",
                                                     "/roleeccreatepageaction.do",
                                                     2,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_CREATE,
                                                     CommonConstants.EROSION_CONTROL_MODULE));
            roleEVViewMenu.add(new SecondaryMenuItem(X_ROLE_EV_CREATE_MENU_ITEM_NAME,
                                                     "New ENV",
                                                     "/html/sec/role",
                                                     "/roleevcreatepageaction.do",
                                                     3,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_CREATE,
                                                     CommonConstants.ENVIRONMENT_MODULE));
            roleEVViewMenu.add(new SecondaryMenuItem(X_ROLE_VIEW_MENU_ITEM_NAME,
                                                     "View",
                                                     "/html/sec/role",
                                                     "/roleevviewpageaction.do",
                                                     4,
                                                     SecurityManager.USER_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_READ,
                                                     CommonConstants.ENVIRONMENT_MODULE));
            roleEVViewMenu.add(new SecondaryMenuItem(X_ROLE_EV_EDIT_MENU_ITEM_NAME,
                                                     "Edit",
                                                     "/html/sec/role",
                                                     "/roleeveditpageaction.do",
                                                     5,
                                                     SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL,
                                                     SecureObjectPermissionData.ROLE_UPDATE,
                                                     CommonConstants.ENVIRONMENT_MODULE));
            return roleEVViewMenu;
        }
        else if (secondaryMenuName.equals(CONTACT_LIST_MENU_NAME))
        {
            SecondaryMenu
                contactListMenu =
                new SecondaryMenu(CONTACT_LIST_MENU_NAME,
                                  "Contact List");
            contactListMenu.add(new SecondaryMenuItem(X_CONTACT_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/sec/contact",
                                                      "/contactlistpageaction.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_READ));
            contactListMenu.add(new SecondaryMenuItem(X_CONTACT_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/sec/contact",
                                                      "/contactcreatepageaction.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_CREATE));
            return contactListMenu;
        }
        else if (secondaryMenuName.equals(CONTACT_VIEW_MENU_NAME))
        {
            SecondaryMenu
                contactViewMenu =
                new SecondaryMenu(CONTACT_VIEW_MENU_NAME,
                                  "Contact View");
            contactViewMenu.add(new SecondaryMenuItem(X_CONTACT_LIST_MENU_ITEM_NAME,
                                                      "Select",
                                                      "/html/sec/contact",
                                                      "/contactlistpageaction.do",
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_READ));
            contactViewMenu.add(new SecondaryMenuItem(X_CONTACT_CREATE_MENU_ITEM_NAME,
                                                      "New",
                                                      "/html/sec/contact",
                                                      "/contactcreatepageaction.do",
                                                      2,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_CREATE));
            contactViewMenu.add(new SecondaryMenuItem(X_CONTACT_VIEW_MENU_ITEM_NAME,
                                                      "View",
                                                      "/html/sec/contact",
                                                      "/contactviewpageaction.do",
                                                      3,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_READ));
            contactViewMenu.add(new SecondaryMenuItem(X_CONTACT_EDIT_MENU_ITEM_NAME,
                                                      "Edit",
                                                      "/html/sec/contact",
                                                      "/contacteditpageaction.do",
                                                      4,
                                                      SecurityManager.USER_SECURITY_LEVEL,
                                                      SecureObjectPermissionData.CONTACT_UPDATE));
            return contactViewMenu;
        }
        else if (secondaryMenuName.equals(REPORT_LIST_MENU_NAME))
        {
            SecondaryMenu
                reportListMenu =
                new SecondaryMenu(REPORT_LIST_MENU_NAME,
                                  "Report Menu");
            return reportListMenu;
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

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        if (name
            != null)
        {
            return name;
        }
        else
        {
            return "";
        }
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
    }

    public MenuItem getCurrentItem()
    {
        if (currentItem
            == null)
        {
            if (!secondaryMenu.isEmpty()
                && !this.name
                .equals(INavMenu.OPTIONS_LIST_MENU))
            {
                currentItem =
                    (SecondaryMenuItem) secondaryMenu.first();
            }
        }
        return currentItem;
    }

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