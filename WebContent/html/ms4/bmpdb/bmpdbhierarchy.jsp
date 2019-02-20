<div data-spy="affix">
    <div class="scrollBar img-polaroid img-rounded">
        <div class="handle"></div>
    </div>
    <div id="frame"
         class="frame img-polaroid img-rounded">
        <div class="slidee">
            <div id="treeView">
                <%= ((com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu) request.getSession()
                        .getAttribute(com.sehinc.stormwater.action.base.SessionKeys.BMP_DB_HIERARCHY)).render(request,
                                                                                                              response) %>
            </div>
        </div>
    </div>
</div>

