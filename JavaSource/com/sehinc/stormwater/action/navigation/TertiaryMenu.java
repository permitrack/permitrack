package com.sehinc.stormwater.action.navigation;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.MenuItem;
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
        PLAN_MENU_NAME =
        "PLAN_MENU";
    public static final
    String
        PLAN_DELETE_MENU_ITEM_NAME =
        "PLAN_DELETE_MENU_ITEM";
    public static final
    String
        PLAN_PUB_MENU_ITEM_NAME =
        "PLAN_PUB_MENU_ITEM";
    public static final
    String
        MCM_OTHER_MENU_NAME =
        "MCM_OTHER_MENU";
    public static final
    String
        MCM_DELETE_MENU_ITEM_NAME =
        "MCM_DELETE_MENU_ITEM";
    public static final
    String
        BMP_MENU_NAME =
        "BMP_MENU";
    public static final
    String
        BMP_COPY_MENU_ITEM_NAME =
        "BMP_COPY_MENU_ITEM";
    public static final
    String
        BMP_MOVE_MENU_ITEM_NAME =
        "BMP_MOVE_MENU_ITEM";
    public static final
    String
        BMP_DELETE_MENU_ITEM_NAME =
        "BMP_DELETE_MENU_ITEM";
    public static final
    String
        GOAL_MENU_NAME =
        "GOAL_MENU";
    public static final
    String
        GOAL_DELETE_MENU_ITEM_NAME =
        "GOAL_DELETE_MENU_ITEM";
    public static final
    String
        GOAL_ACTIVITY_MENU_NAME =
        "GOAL_ACTIVITY_MENU";
    public static final
    String
        GOAL_ACTIVITY_DELETE_MENU_ITEM_NAME =
        "GOAL_ACTIVITY_DELETE_MENU_ITEM";
    public static final
    String
        PLAN_SAVE_AS_TEMPLATE_MENU_ITEM_NAME =
        "PLAN_SAVE_AS_TEMPLATE_MENU_ITEM_NAME";
    public static final
    String
        TEMPLATE_MENU_NAME =
        "TEMPLATE_MENU";
    public static final
    String
        TEMPLATE_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_DELETE_MENU_ITEM";
    public static final
    String
        TEMPLATE_MCM_MPCA_MENU_NAME =
        "TEMPLATE_MCM_MPCA_MENU";
    public static final
    String
        TEMPLATE_MCM_OTHER_MENU_NAME =
        "TEMPLATE_MCM_OTHER_MENU";
    public static final
    String
        TEMPLATE_MCM_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_MCM_DELETE_MENU_ITEM";
    public static final
    String
        TEMPLATE_BMP_MENU_NAME =
        "TEMPLATE_BMP_MENU";
    public static final
    String
        TEMPLATE_BMP_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_BMP_DELETE_MENU_ITEM";
    public static final
    String
        TEMPLATE_GOAL_MENU_NAME =
        "TEMPLATE_GOAL_MENU";
    public static final
    String
        TEMPLATE_GOAL_DELETE_MENU_ITEM_NAME =
        "TEMPLATE_GOAL_DELETE_MENU_ITEM";
    public static final
    String
        BMPDB_MENU_NAME =
        "BMPDB_MENU";
    public static final
    String
        BMPDB_DELETE_MENU_ITEM_NAME =
        "BMPDB_DELETE_MENU_ITEM";
    public static final
    String
        BMPDB_ADD_GOAL_MENU_ITEM_NAME =
        "BMPDB_ADD_GOAL_MENU_ITEM";
    public static final
    String
        BMPDB_GOAL_MENU_NAME =
        "BMPDB_GOAL_MENU";
    public static final
    String
        BMPDB_GOAL_DELETE_MENU_ITEM_NAME =
        "BMPDB_GOAL_DELETE_MENU_ITEM";
    public static final
    String
        PERMIT_PERIOD_MENU_NAME =
        "PERMIT_PERIOD_MENU";
    public static final
    String
        PERMIT_PERIOD_DELETE_MENU_ITEM_NAME =
        "PERMIT_PERIOD_DELETE_MENU_ITEM";
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
        new TreeSet();
    private
    TertiaryMenuItem
        currentItem =
        null;

    private TertiaryMenu(String name, String description)
    {
        this.name =
            name;
        this.description =
            description;
    }

    public static TertiaryMenu getInstance(String tertiaryMenuName, int id)
    {
        if (tertiaryMenuName.equals(PLAN_MENU_NAME))
        {
            TertiaryMenu
                planMenu =
                new TertiaryMenu(PLAN_MENU_NAME,
                                 "Plan");
            planMenu.add(new TertiaryMenuItem(TertiaryMenu.PLAN_DELETE_MENU_ITEM_NAME,
                                              "Delete",
                                              "/html/ms4/plan",
                                              "/plandeleteaction.do?id="
                                              + id,
                                              1,
                                              SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
/*
            planMenu.add(new TertiaryMenuItem(TertiaryMenu.PLAN_PUB_MENU_ITEM_NAME,
                                              "Publish",
                                              "/html/ms4/plan",
                                              "/planpublishaction.do?id="
                                              + id,
                                              2,
                                              SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
*/
            planMenu.add(new TertiaryMenuItem(TertiaryMenu.PLAN_SAVE_AS_TEMPLATE_MENU_ITEM_NAME,
                                              "Save As Template",
                                              "/html/ms4/plan",
                                              "/plantemplatecreate.do?id="
                                              + id,
                                              2,
                                              SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return planMenu;
        }
        else if (tertiaryMenuName.equals(MCM_OTHER_MENU_NAME))
        {
            TertiaryMenu
                mcmOtherMenu =
                new TertiaryMenu(MCM_OTHER_MENU_NAME,
                                 "MCM");
            mcmOtherMenu.add(new TertiaryMenuItem(TertiaryMenu.MCM_DELETE_MENU_ITEM_NAME,
                                                  "Delete",
                                                  "/html/ms4/plan",
                                                  "/mcmdeleteaction.do?id="
                                                  + id,
                                                  1,
                                                  SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            return mcmOtherMenu;
        }
        else if (tertiaryMenuName.equals(BMP_MENU_NAME))
        {
            TertiaryMenu
                bmpMenu =
                new TertiaryMenu(BMP_MENU_NAME,
                                 "BMP");
            bmpMenu.add(new TertiaryMenuItem(TertiaryMenu.BMP_DELETE_MENU_ITEM_NAME,
                                             "Delete",
                                             "/html/ms4/plan",
                                             "/bmpdeleteaction.do?id="
                                             + id,
                                             1,
                                             SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            bmpMenu.add(new TertiaryMenuItem(TertiaryMenu.BMP_MOVE_MENU_ITEM_NAME,
                                             "Move",
                                             "/html/ms4/plan",
                                             "/bmpmoveaction.do?id="
                                             + id,
                                             2,
                                             SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            bmpMenu.add(new TertiaryMenuItem(TertiaryMenu.BMP_COPY_MENU_ITEM_NAME,
                                             "Copy",
                                             "/html/ms4/plan",
                                             "/bmpcopyaction.do?id="
                                             + id,
                                             3,
                                             SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            return bmpMenu;
        }
        else if (tertiaryMenuName.equals(GOAL_MENU_NAME))
        {
            TertiaryMenu
                goalMenu =
                new TertiaryMenu(GOAL_MENU_NAME,
                                 "Goal");
            goalMenu.add(new TertiaryMenuItem(TertiaryMenu.GOAL_DELETE_MENU_ITEM_NAME,
                                              "Delete",
                                              "/html/ms4/plan",
                                              "/goaldeleteaction.do?id="
                                              + id,
                                              1,
                                              SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL));
            return goalMenu;
        }
        else if (tertiaryMenuName.equals(GOAL_ACTIVITY_MENU_NAME))
        {
            TertiaryMenu
                goalActivityMenu =
                new TertiaryMenu(GOAL_ACTIVITY_MENU_NAME,
                                 "Goal Activity");
            goalActivityMenu.add(new TertiaryMenuItem(TertiaryMenu.GOAL_ACTIVITY_DELETE_MENU_ITEM_NAME,
                                                      "Delete",
                                                      "/html/ms4/plan",
                                                      "/goalactivitydeleteaction.do?id="
                                                      + id,
                                                      1,
                                                      SecurityManager.USER_SECURITY_LEVEL));
            return goalActivityMenu;
        }
        else if (tertiaryMenuName.equals(TEMPLATE_MENU_NAME))
        {
            TertiaryMenu
                templateMenu =
                new TertiaryMenu(TEMPLATE_MENU_NAME,
                                 "Template");
            templateMenu.add(new TertiaryMenuItem(TertiaryMenu.TEMPLATE_DELETE_MENU_ITEM_NAME,
                                                  "Delete",
                                                  "/html/ms4/template",
                                                  "/plantemplatedelete.do?id="
                                                  + id,
                                                  1,
                                                  SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return templateMenu;
        }
        else if (tertiaryMenuName.equals(TEMPLATE_MCM_OTHER_MENU_NAME))
        {
            TertiaryMenu
                templateMcmOtherMenu =
                new TertiaryMenu(TEMPLATE_MCM_OTHER_MENU_NAME,
                                 "Template MCM");
            templateMcmOtherMenu.add(new TertiaryMenuItem(TertiaryMenu.TEMPLATE_MCM_DELETE_MENU_ITEM_NAME,
                                                          "Delete",
                                                          "/html/ms4/template",
                                                          "/mcmtemplatedelete.do?id="
                                                          + id,
                                                          1,
                                                          SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return templateMcmOtherMenu;
        }
        else if (tertiaryMenuName.equals(TEMPLATE_MCM_MPCA_MENU_NAME))
        {
            TertiaryMenu
                templateMcmMPCAMenu =
                new TertiaryMenu(TEMPLATE_MCM_MPCA_MENU_NAME,
                                 "Template MCM");
            templateMcmMPCAMenu.add(new TertiaryMenuItem(TertiaryMenu.TEMPLATE_MCM_DELETE_MENU_ITEM_NAME,
                                                         "Delete",
                                                         "/html/ms4/template",
                                                         "/mcmtemplatedelete.do?id="
                                                         + id,
                                                         3,
                                                         SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return templateMcmMPCAMenu;
        }
        else if (tertiaryMenuName.equals(TEMPLATE_BMP_MENU_NAME))
        {
            TertiaryMenu
                templateBmpMenu =
                new TertiaryMenu(TEMPLATE_BMP_MENU_NAME,
                                 "Template BMP");
            templateBmpMenu.add(new TertiaryMenuItem(TertiaryMenu.TEMPLATE_BMP_DELETE_MENU_ITEM_NAME,
                                                     "Delete",
                                                     "/html/ms4/template",
                                                     "/bmptemplatedelete.do?id="
                                                     + id,
                                                     3,
                                                     SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return templateBmpMenu;
        }
        else if (tertiaryMenuName.equals(TEMPLATE_GOAL_MENU_NAME))
        {
            TertiaryMenu
                templateGoalMenu =
                new TertiaryMenu(TEMPLATE_GOAL_MENU_NAME,
                                 "Template Goal");
            templateGoalMenu.add(new TertiaryMenuItem(TertiaryMenu.TEMPLATE_GOAL_DELETE_MENU_ITEM_NAME,
                                                      "Delete",
                                                      "/html/ms4/template",
                                                      "/goaltemplatedelete.do?id="
                                                      + id,
                                                      3,
                                                      SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return templateGoalMenu;
        }
        else if (tertiaryMenuName.equals(BMPDB_MENU_NAME))
        {
            TertiaryMenu
                bmpDbMenu =
                new TertiaryMenu(BMPDB_MENU_NAME,
                                 "BMP Library");
            bmpDbMenu.add(new TertiaryMenuItem(TertiaryMenu.BMPDB_DELETE_MENU_ITEM_NAME,
                                               "Delete",
                                               "/html/ms4/bmpdb",
                                               "/bmpdbdelete.do?id="
                                               + id,
                                               1,
                                               SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return bmpDbMenu;
        }
        else if (tertiaryMenuName.equals(BMPDB_GOAL_MENU_NAME))
        {
            TertiaryMenu
                bmpDbGoalMenu =
                new TertiaryMenu(BMPDB_GOAL_MENU_NAME,
                                 "BMP Library Goal");
            bmpDbGoalMenu.add(new TertiaryMenuItem(TertiaryMenu.BMPDB_GOAL_DELETE_MENU_ITEM_NAME,
                                                   "Delete",
                                                   "/html/ms4/bmpdb",
                                                   "/bmpdbgoaldelete.do?id="
                                                   + id,
                                                   3,
                                                   SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return bmpDbGoalMenu;
        }
        else if (tertiaryMenuName.equals(PERMIT_PERIOD_MENU_NAME))
        {
            TertiaryMenu
                permitPeriodMenu =
                new TertiaryMenu(PERMIT_PERIOD_MENU_NAME,
                                 "Permit Period");
            permitPeriodMenu.add(new TertiaryMenuItem(TertiaryMenu.PERMIT_PERIOD_DELETE_MENU_ITEM_NAME,
                                                      "Delete",
                                                      "/html/ms4/permitperiod",
                                                      "/permitperioddelete.do?id="
                                                      + id,
                                                      1,
                                                      SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL));
            return permitPeriodMenu;
        }
        else
        {
            LOG.error("Could not locate Tertiary Menu "
                      + tertiaryMenuName);
        }
        return null;
    }

    @Override
    public void setName(String name)
    {
        this.name =
            name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setDescription(String description)
    {
        this.description =
            description;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    public void add(TertiaryMenuItem tertiaryMenuItem)
    {
        tertiaryMenu.add(tertiaryMenuItem);
    }

    public TertiaryMenuItem remove(TertiaryMenuItem tertiaryMenuItem)
    {
        TertiaryMenuItem
            smi;
        if (tertiaryMenu
            == null
            || tertiaryMenu.isEmpty())
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
        if (currentItem
            == null)
        {
            if (!tertiaryMenu.isEmpty())
            {
                currentItem =
                    (TertiaryMenuItem) tertiaryMenu.first();
            }
        }
        return currentItem;
    }

    @Override
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