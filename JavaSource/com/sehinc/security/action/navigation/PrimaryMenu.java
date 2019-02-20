package com.sehinc.security.action.navigation;

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
        SECURITY_MENU_NAME =
        "SECURITY_MENU";
    public static final
    String
        SECURITY_MENU_NONE_NAME =
        "SECURITY_MENU_NONE";
    public static final
    String
        SECURITY_NONE_MENU_ITEM_NAME =
        "SECURITY_NONE_MENU_ITEM";
    public static final
    String
        SECURITY_USER_MENU_ITEM_NAME =
        "SECURITY_USER_MENU_ITEM";
    public static final
    String
        SECURITY_ROLE_MENU_ITEM_NAME =
        "SECURITY_ROLE_MENU_ITEM";
    public static final
    String
        SECURITY_CLIENT_MENU_ITEM_NAME =
        "SECURITY_CLIENT_MENU_ITEM";
    public static final
    String
        SECURITY_CONTACT_MENU_ITEM_NAME =
        "SECURITY_CONTACT_MENU_ITEM";
    public static final
    String
        SECURITY_ACTIVITY_MENU_ITEM_NAME =
        "SECURITY_ACTIVITY_MENU_ITEM_NAME";
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

    public static PrimaryMenu getInstanceNone()
    {
        PrimaryMenu
            securityMenu =
            new PrimaryMenu(SECURITY_MENU_NONE_NAME,
                            "Security",
                            SecurityManager.USER_SECURITY_LEVEL);
        securityMenu.add(new PrimaryMenuItem(SECURITY_CLIENT_MENU_ITEM_NAME,
                                             "Clients",
                                             "/html/sec/client",
                                             "/clientlistpageaction.do",
                                             1,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.CLIENT_READ));
        securityMenu.add(new PrimaryMenuItem(SECURITY_ACTIVITY_MENU_ITEM_NAME,
                                             "Activity",
                                             "/html/sec/client",
                                             "/clientactivitypageaction.do?type=timeline",
                                             2,
                                             SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL,
                                             SecureObjectPermissionData.CLIENT_READ));
        return securityMenu;
    }

    public static PrimaryMenu getInstance()
    {
        return getInstance(null);
    }

    public static PrimaryMenu getInstance(String clientName)
    {
        PrimaryMenu
            securityMenu =
            new PrimaryMenu(SECURITY_MENU_NAME,
                            "Security",
                            SecurityManager.USER_SECURITY_LEVEL);
        securityMenu.add(new PrimaryMenuItem(SECURITY_CLIENT_MENU_ITEM_NAME,
                                             "Client",
                                             "/html/sec/client",
                                             "/clientviewpageaction.do",
                                             1,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.CLIENT_READ));
        securityMenu.add(new PrimaryMenuItem(SECURITY_USER_MENU_ITEM_NAME,
                                             "Users",
                                             "/html/sec/user",
                                             "/userlistpageaction.do",
                                             2,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.USER_READ));
        securityMenu.add(new PrimaryMenuItem(SECURITY_ROLE_MENU_ITEM_NAME,
                                             "Roles",
                                             "/html/sec/role",
                                             "/rolelistpageaction.do",
                                             3,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.ROLE_READ));
        securityMenu.add(new PrimaryMenuItem(SECURITY_CONTACT_MENU_ITEM_NAME,
                                             "Contacts",
                                             "/html/sec/contact",
                                             "/contactlistpageaction.do",
                                             4,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.CONTACT_READ));
        securityMenu.add(new PrimaryMenuItem(OPTIONS_LIST_MENU_ITEM,
                                             "Settings",
                                             "/html/sec/client",
                                             "/clientlogoselectaction.do",
                                             5,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.PROJECT_READ));
        securityMenu.add(new PrimaryMenuItem(SECURITY_ACTIVITY_MENU_ITEM_NAME,
                                             "Activity",
                                             "/html/sec/client",
                                             "/clientactivitypageaction.do?type=timeline",
                                             6,
                                             SecurityManager.USER_SECURITY_LEVEL,
                                             SecureObjectPermissionData.CLIENT_READ));
        if (clientName
            != null
            && !clientName.isEmpty())
        {
            securityMenu.add(new PrimaryMenuItem(EXIT_MENU_ITEM,
                                                 "Exit "
                                                 + (clientName.length() <= 15 ? clientName : clientName.substring(0, 14) + "..."),
                                                 "/html/sec/client",
                                                 "/clientlistpageaction.do",
                                                 7,
                                                 SecurityManager.USER_SECURITY_LEVEL,
                                                 SecureObjectPermissionData.CLIENT_READ));
        }
        return securityMenu;
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
        if (!itemName.equals(SECURITY_NONE_MENU_ITEM_NAME))
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
            currentItem.setName(SECURITY_NONE_MENU_ITEM_NAME);
            currentItem.setDescription(SECURITY_NONE_MENU_ITEM_NAME);
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