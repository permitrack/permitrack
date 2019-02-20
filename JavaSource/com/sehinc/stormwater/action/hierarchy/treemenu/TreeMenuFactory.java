package com.sehinc.stormwater.action.hierarchy.treemenu;

public class TreeMenuFactory
{
    private static
    TreeMenuFactory
        treeMenuFactory;

    private TreeMenuFactory()
    {
    }

    ;

    public static TreeMenuFactory getInstance()
    {
        if (treeMenuFactory
            == null)
        {
            treeMenuFactory =
                new TreeMenuFactory();
        }
        return treeMenuFactory;
    }

    public ITreeMenu getTreeMenu(TreeMenuLeaf treeMenuLeaf)
    {
        return new TreeViewMenu(treeMenuLeaf);
    }
}
