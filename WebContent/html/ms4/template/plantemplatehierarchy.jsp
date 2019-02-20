<div data-spy="affix">
    <form class="form-search">
        <input type="text" id="treeInput" placeholder="Highlight in tree" class="input-medium search-query" />
        <button type="button" id="treeSearch" class="btn btn-mini">Go</button>
    </form>
    <div class="scrollBar img-polaroid img-rounded">
        <div class="handle"></div>
    </div>
    <div id="frame"
         class="frame img-polaroid img-rounded">
        <div class="slidee">
            <div id="treeView">
        <%= ((com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu) request.getSession()
                .getAttribute(com.sehinc.stormwater.action.base.SessionKeys.PLAN_TEMPLATE_HIERARCHY)).render(request,
                                                                                                             response) %>
            </div>
        </div>
    </div>
</div>
