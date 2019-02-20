package com.sehinc.portal.action.navigation;

import java.util.List;

public interface INavMenu
{
    public static final
    String
        NONE_MENU =
        "NONE_MENU";
    public static final
    String
        EXIT_MENU_ITEM =
        "EXIT_MENU_ITEM";
    public static final
    String
        OPTIONS_LIST_MENU =
        "OPTIONS_LIST_MENU";
    public static final
    String
        OPTIONS_LIST_MENU_ITEM =
        "OPTIONS_LIST_MENU_ITEM";

    void setName(String name);

    String getName();

    void setDescription(String description);

    String getDescription();

    void setCurrentItem(String itemName);

    MenuItem getCurrentItem();

    List getMenuItems();

    public int getItemCount();
}
