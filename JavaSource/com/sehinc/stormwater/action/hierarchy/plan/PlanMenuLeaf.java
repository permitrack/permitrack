package com.sehinc.stormwater.action.hierarchy.plan;

public interface PlanMenuLeaf
{
    public String getParentType();

    public String getParentId();

    public void setParentId(String parentId);
}
