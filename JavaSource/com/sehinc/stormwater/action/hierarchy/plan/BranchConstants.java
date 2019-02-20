package com.sehinc.stormwater.action.hierarchy.plan;

public class BranchConstants
{
    public static final
    String
        BMP_TYPE =
        "b";
    public static final
    String
        GOAL_TYPE =
        "g";
    public static final
    String
        GOAL_ACTIVITY_TYPE =
        "a";
    public static final
    String
        PLAN_TYPE =
        "p";
    public static final
    String
        MCM_TYPE =
        "m";
    public static final
    String
        SUBNODE_URL =
        "/ms4/plan/subnodeviewaction.do";

    public static String generateTreeId(String type, int id)
    {
        return type
               + id;
    }

    public static String generateTreeId(String type, String id)
    {
        return type
               + id;
    }
}
