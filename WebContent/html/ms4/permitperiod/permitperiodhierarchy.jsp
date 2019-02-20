<%@page import="com.sehinc.stormwater.action.base.SessionKeys, com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu" %>
<div data-spy="affix">
    <div class="scrollBar img-polaroid img-rounded">
        <div class="handle"></div>
    </div>
    <div id="frame"
         class="frame img-polaroid img-rounded">
        <div class="slidee">
            <div id="treeView">
        <%= ((ITreeMenu) request.getSession()
                .getAttribute(SessionKeys.PERMIT_PERIOD_HIERARCHY)).render(request,
                                                                           response) %>
            </div>
        </div>
    </div>
</div>
