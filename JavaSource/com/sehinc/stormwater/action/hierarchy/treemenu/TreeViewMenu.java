package com.sehinc.stormwater.action.hierarchy.treemenu;

import com.sehinc.stormwater.action.hierarchy.plan.PlanBranch;
import com.sehinc.stormwater.action.hierarchy.template.PlanTemplateBranch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TreeViewMenu
    implements ITreeMenu
{
    private
    TreeMenuLeaf
        root =
        null;
    private
    HttpServletRequest
        request =
        null;
    private
    HttpServletResponse
        response =
        null;
    private
    TreeViewMenuRenderer
        renderer =
        null;

    public TreeViewMenu(TreeMenuLeaf root)
    {
/*
        this(root,
             null,
             null);
*/
        this.root =
            root;
        if (root instanceof PlanBranch
            && !(root instanceof PlanTemplateBranch))
        {
            renderer =
                new PlanTreeViewMenuRenderer();
        }
        else
        {
            renderer =
                new TreeViewMenuRenderer();
        }
    }

/*
    public TreeViewMenu(TreeMenuLeaf root, HttpServletRequest request, HttpServletResponse response)
    {
        this.request =
            request;
        this.response =
            response;
        this.root =
            root;
    }
*/

    public String render()
        throws Exception
    {
        if (this.request
            == null)
        {
            throw new Exception("TreeViewMenu.render() requires an HttpServletRequest object");
        }
        return renderer.render(root,
                               request,
                               response);
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        this.request =
            request;
        this.response =
            response;
        return render();
    }
}
