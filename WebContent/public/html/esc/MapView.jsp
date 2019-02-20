<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.common.util.StringUtil,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.value.project.ProjectMapValue,
                 com.sehinc.portal.action.navigation.PortalMenuBean,
                 java.util.Iterator" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="clientData"
             scope="request"
             class="com.sehinc.common.db.client.ClientData" />
<tiles:insert page="/html/layout.jsp">
    <tiles:put name="title">
        Project Map | PermiTrack
    </tiles:put>
    <tiles:put name="portal-bar"
               type="string">
        <tiles:insert page="/html/portal-bar.jsp"
                      flush="false">
            <tiles:put name="portalMenu"
                       type="string">
                <%try
                {%>
                <%= new PortalMenuBean().render(request,
                                                response) %>
                <%}
                catch (Exception e)
                {
                }%>
            </tiles:put>
        </tiles:insert>
    </tiles:put>
    <tiles:put name="layout"
               direct="true">
        <div class="container">
            <fieldset>
                <legend>
                    <logic:present name="<%= RequestKeys.CLIENT_MAP_LOGO_LOCATION %>"
                                   scope="request">
                        <logic:notEmpty name="<%= RequestKeys.CLIENT_MAP_LOGO_LOCATION %>"
                                        scope="request">
                            <img id="clientImage"
                                 alt=""
                                 onerror="this.onerror=null;this.src='';"
                                 src="<%= (String) request.getAttribute(RequestKeys.CLIENT_MAP_LOGO_LOCATION) %>" />
                        </logic:notEmpty>
                    </logic:present>
                    <jsp:getProperty name="clientData"
                                     property="name" />
                </legend>
            </fieldset>
            <div class="row">
                <div class="span4"
                     style="margin-bottom: 5px;">
                    <button type="button"
                            class="btn btn-mini"
                            id="hideprojects">
                        Hide / Show Project List
                    </button>
                </div>
                <div class="span8"
                     style="margin-bottom: 5px; font-size: x-small;">
                    <img src="<%= (String) request.getAttribute(RequestKeys.GREEN_MAP_MARKER_ICON) %>">
                    Passed
                    <img src="<%= (String) request.getAttribute(RequestKeys.YELLOW_MAP_MARKER_ICON) %>">
                    Passed but Aged Past Initial Due Date
                    <img src="<%= (String) request.getAttribute(RequestKeys.BLACK_MAP_MARKER_ICON) %>">
                    Passed but Aged Past Secondary Due Date
                    <img src="<%= (String) request.getAttribute(RequestKeys.RED_MAP_MARKER_ICON) %>">
                    Failed
                </div>
            </div>
            <div class="row">
                <div class="span4"
                     id="sidebarParent">
                    <div id="sidebar"></div>
                </div>
                <div class="span8">
                    <div id="map_canvas"
                         class="gradient-control"
                         style="min-width: 800px; min-height: 600px; text-align: left;"></div>
                </div>
            </div>
            <tiles:definition id="scripts"
                              scope="request">
                <tiles:put name="scripts"
                           type="string"
                           direct="true">
                    <script src="//maps.googleapis.com/maps/api/js?key=@google.map.key@&sensor=false"
                            type="text/javascript"></script>
                    <%--
                                        <script type="text/javascript"
                                                src="//google-maps-utility-library-v3.googlecode.com/svn/trunk/markermanager/src/markermanager.js"></script>
                    --%>
                    <script type="text/javascript">
                        //<!--
                        var map_canvas;
                        var firstIndex;
                        var lastIndex;
                        var maxPerPage = 100;
                        var sidebar_html = "";
                        var empty_sidebar_html = "<div class='warning'>There are no projects to display.</div>";
                        var gmarkers;
                        var htmls;
                        var numPages = 1;
                        var logoImage;
                        var mgrOptions;
                        var the_mgr;
                        var projectMapArray = new Array(0);
                        var initialProject = 0;
                        function encodeHtml(mystring)
                        {
                            var newstring = mystring;
                            newstring
                                    = $.fn.editableutils.escape(newstring);
                            return newstring;
                        }
                        function Project(id, projectId, name, ownerClientName, permitAuthorityClientName, permittedClientName, parcelNumber, permitNumber, latitude, longitude, address, city, url, commentURL, lastInspectionFailed, inspectionStatus)
                        {
                            this.id
                                    = id;
                            this.projectId
                                    = projectId;
                            this.name
                                    = name;
                            //this.ownerClientName
                            //        = ownerClientName;
                            this.permitAuthorityClientName
                                    = permitAuthorityClientName;
                            this.permittedClientName
                                    = permittedClientName;
                            this.parcelNumber
                                    = parcelNumber;
                            this.permitNumber
                                    = permitNumber;
                            this.latitude
                                    = latitude;
                            this.longitude
                                    = longitude;
                            this.address
                                    = address;
                            this.city
                                    = city;
                            this.url
                                    = url;
                            this.commentURL
                                    = commentURL;
                            //this.lastInspectionFailed
                            //        = lastInspectionFailed;
                            this.inspectionStatus
                                    = inspectionStatus;
                        }
                        function initProjectMapArray()
                        {
                        <%
                            Iterator projectMapValueListIterator = ((List) request.getAttribute(RequestKeys.EC_PROJECT_MAP_VIEW_LIST)).iterator();
                            int projectMapListIndex = 0;
                            while (projectMapValueListIterator.hasNext()) {
                                ProjectMapValue projectMapValue = (ProjectMapValue) projectMapValueListIterator.next();
                        %>
                            projectMapArray.push(new Project('<%= projectMapListIndex++ %>',
                                                             '<%= projectMapValue.getId() %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getName()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getOwnerClientName()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getPermitAuthorityClientName()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getPermittedClientName()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getParcelNumber()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getPermitNumber()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getLatitude()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getLongitude()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getViewAddress()) %>',
                                                             '<%= StringUtil.escapeSingleQuote(projectMapValue.getViewCity()) %>',
                                                             '<%= projectMapValue.getUrl() %>',
                                                             '<%= projectMapValue.getCommentURL() %>',
                                                              <%= projectMapValue.isLastInspectionFailed() %>,
                                                              <%= projectMapValue.getInspectionStatus()%>));
                        <%
                            }
                        %>
                        }
                        /*
                         Creates a marker at the given point with the given number label
                         */
                        function createMarker(map, point, shape, index, inspectionStatus)
                        {
                            var project = projectMapArray[index];
                            var icon;
                            if (inspectionStatus
                                    == <%=ProjectMapValue.BLACK_STATUS%>)
                            {
                                icon
                                        = "<%= (String) request.getAttribute(RequestKeys.BLACK_MAP_MARKER_ICON) %>";
                            }
                            else if (inspectionStatus
                                    == <%=ProjectMapValue.RED_STATUS%>)
                            {
                                icon
                                        = "<%= (String) request.getAttribute(RequestKeys.RED_MAP_MARKER_ICON) %>";
                            }
                            else if (inspectionStatus
                                    == <%=ProjectMapValue.YELLOW_STATUS%>)
                            {
                                icon
                                        = "<%= (String) request.getAttribute(RequestKeys.YELLOW_MAP_MARKER_ICON) %>";
                            }
                            else
                            {
                                icon
                                        = "<%= (String) request.getAttribute(RequestKeys.GREEN_MAP_MARKER_ICON) %>";
                            }
                            var marker = new google.maps.Marker({
                                                                    icon:icon,
                                                                    shape:shape,
                                                                    position:point,
                                                                    map:map,
                                                                    title:project.name
                                                                });
                            var infoWindowHtml = "<div style='min-width: 300px; min-height: 260px;'><h5>"
                                                         + (index
                                    + 1)
                                                         + ".&nbsp;"
                                                         + entityify(project.name)
                                                         + "</h5>"
                                                         + "<small><table class='table table-condensed'><tr><td>"
                                                         + "Address</td><td><span class='wrapTextVerySmall'>"
                                                         + ((project.address
                                    != '')
                                    ? entityify(project.address)
                                    : "")
                                                         + "</span></td></tr><tr><td>Permit #</td><td>"
                                                         + entityify(project.permitNumber)
                                                         + "</td></tr><tr><td>Parcel #</td><td>"
                                                         + entityify(project.parcelNumber)
                                                         + "</td></tr><tr><td>"
                                                         + "Permit Authority</td><td>"
                                                         + entityify(project.permitAuthorityClientName)
                                                         + "</td></tr><tr><td>"
                                                         + "Permittee</td><td>"
                                                         + entityify(project.permittedClientName)
                                                         + "</td></tr></table></small><div style='min-height: 30px;' />"
                                                         + "<a class='btn btn-mini btn-info' href='#' onclick='window.open(\""
                                                         + project.url
                                                         + "\");'>View Inspections</a>"
                                                         + "<a class='btn btn-mini btn-success pull-right' href='#' "
                                                         + "onclick='window.open(\""
                                                         + project.commentURL
                                    + "\", null, \"height=600,width=800,scrollbars=yes\")'>Send Comments</a></div></div>";
                            var infowindow = new google.maps.InfoWindow({
                                                                            content:infoWindowHtml
                                                                        });
                            google.maps.event.addListener(marker,
                                                          "click",
                                                          function ()
                                                          {
                                                              myclick(index);
                                                          });
                            gmarkers[index]
                                    = marker;
                            htmls[index]
                                    = infowindow;
                            return marker;
                        }
                        /*
                         This is the function that handles clicks on the sidebar icons
                         */
                        function myclick(i)
                        {
                            for (var infoIndex = 0; infoIndex
                                    < htmls.length; ++infoIndex)
                            {
                                htmls[infoIndex].close();
                            }
                            htmls[i].open(map_canvas,
                                          gmarkers[i]);
                            setTimeout(function ()
                                       {
                                           $(".btn").css("visibility",
                                                         "visible");
                                       },
                                       1000);
                        }
                        /*
                         Populate the map with projects
                         */
                        function populateMap(map/*, mgr*/)
                        {
                            gmarkers
                                    =
                            [
                                projectMapArray.length
                            ];
                            htmls
                                    =
                            [
                                projectMapArray.length
                            ];
                            //the_mgr = new MarkerManager(map);
                            //add the points to the map
                            for (var i = 0; i
                                    < projectMapArray.length; i++)
                            {
                                var shape = {
                                    coord:
                                            [
                                                1,
                                                1,
                                                1,
                                                20,
                                                18,
                                                20,
                                                18 ,
                                                1
                                            ],
                                    type:'poly'
                                };
                                var point = new google.maps.LatLng(projectMapArray[i].latitude,
                                                                   projectMapArray[i].longitude);
                                var marker = createMarker(map,
                                                          point,
                                                          shape,
                                                          i,
                                                          projectMapArray[i].inspectionStatus);
                                //the_mgr.addMarker(marker, 1);
                                if(projectMapArray[i].projectId == '<%= request.getAttribute(RequestKeys.EC_PROJECT_ID) != null ? request.getAttribute(RequestKeys.EC_PROJECT_ID) : 0 %>')
                                {
                                    initialProject = i;
                                }
                            }
                            /* a LatLngBounds object */
                            var bounds =
                                getZoomBounds(0,
                                           projectMapArray.length
                                                   - 1);
                            map.fitBounds(bounds);

                            //map.setZoom(Math.min(map.getZoom(), 11));
                            //mgr
                            //        = new google.maps.MarkerManager(map);
                            //Add the markers to the marker manager
                            //mgr.addMarkers(gmarkers,
                            //               1);
                            //refresh the marker manager
                            //the_mgr.refresh();
                        }
                        /*
                         Populate the map with projects
                         */
                        function createSideBar(page)
                        {
                            if (projectMapArray.length
                                    > 0)
                            {
                                //set the first and last index
                                firstIndex
                                        = (page
                                        - 1)
                                        * maxPerPage;
                                if ((projectMapArray.length
                                        - (page
                                        * maxPerPage))
                                        >= 0)
                                {
                                    lastIndex
                                            = firstIndex
                                            + (maxPerPage
                                            - 1);
                                }
                                else
                                {
                                    lastIndex
                                            = firstIndex
                                            + ((projectMapArray.length
                                            - ((page
                                            - 1)
                                            * maxPerPage))
                                            - 1);
                                }
                                //build the sidebar table
                                sidebar_html
                                        = "<div style='max-height: 600px; overflow-y: auto;'><ol class='nav nav-tabs nav-stacked'>";
                                //add the points to the map
                                for (var i = firstIndex; i
                                        <= lastIndex; i++)
                                {
                                    sidebar_html
                                            += "<li>"
                                                       + "<a href='javascript:myclick("
                                                       + i
                                                       + ")'><table><tr><td style='width:30px'>"
                                                       + "<img src='/sehsvc/img/icons/Google_Maps_Marker.png' alt='' /></td><td>"
                                                       + entityify(projectMapArray[i].name)
                                            + "</td></tr></table></a></li>";
                                }
                                sidebar_html
                                        += "</ol></div>";
                                //create the page links
                                sidebar_html
                                        += "<div class='pagination'><ul>";
                                createPageLinks(page);
                                sidebar_html
                                        += "</ul></div>";
                                document.getElementById("sidebar").innerHTML
                                        = sidebar_html;
                            }
                            else
                            {
                                document.getElementById("sidebar").innerHTML
                                        = empty_sidebar_html;
                            }
                        }
                        /*
                         Calculate the limits (bounds) of the map (SW and NE limiting points)
                         */
                        function getZoomBounds(firstIndex, lastIndex)
                        {
                            var maxLat = 0;
                            var minLat = 0;
                            var maxLng = 0;
                            var minLng = 0;

                            var bounds = new google.maps.LatLngBounds();

                            for (var i = firstIndex; i
                                    <= lastIndex; i++)
                            {
                                var myLatLng = new google.maps.LatLng(projectMapArray[i].latitude, projectMapArray[i].longitude);

                                bounds.extend(myLatLng);
                            }

                            bounds.extend(
                                    new google.maps.LatLng(bounds.getSouthWest().lat() * 1.00005, bounds.getSouthWest().lng() * 0.99995));

                            bounds.extend(
                                    new google.maps.LatLng(bounds.getNorthEast().lat() * 0.99995, bounds.getNorthEast().lng() * 1.00005));

                            return bounds;
                        }
                        function createPageLinks(page)
                        {
                            if (numPages
                                    > 1)
                            {
                                for (var i = 1; i
                                        <= numPages; i++)
                                {
                                    sidebar_html
                                            += "<li";
                                    if (page
                                            == i)
                                    {
                                        sidebar_html
                                                += " class='active'";
                                    }
                                    sidebar_html
                                            += "><a href='javascript:reload("
                                                       + i
                                                       + ")'>"
                                                       + i
                                            + "</a>";
                                    sidebar_html
                                            += "</li>";
                                }
                            }
                        }
                        function reload(page)
                        {
                            createSideBar(page);
                        }
                        function load()
                        {
                            var myOptions = {
                                center:new google.maps.LatLng(43.110007,
                                                              -89.35318),
                                zoom: 11,
                                mapTypeId:google.maps.MapTypeId.ROADMAP,
                                // Add controls
                                mapTypeControl:true,
                                scaleControl:true,
                                overviewMapControl:true,
                                overviewMapControlOptions:{
                                    opened:true
                                }
                            };
                            map_canvas
                                    = new google.maps.Map(document.getElementById('map_canvas'),
                                                          myOptions);
                            initProjectMapArray();
                            numPages
                                    = parseInt(projectMapArray.length
                                                       / maxPerPage);
                            if ((projectMapArray.length
                                    % maxPerPage)
                                    > 0)
                            {
                                numPages++;
                            }
                            if (projectMapArray.length
                                    > 0)
                            {
                                populateMap(map_canvas
                                        /*, the_mgr*/);
                            }
                            createSideBar(1);
                            if(initialProject > 0)
                            {
                                myclick(initialProject);
                            }
                        }
                        google.maps.event.addDomListener(window,
                                                         'load',
                                                         load);
                        $("#hideprojects").click(function ()
                                                 {
                                                     $("#sidebar").slideToggle("fast",
                                                                               function ()
                                                                               {
                                                                                   var parent = $('#map_canvas').parent();
                                                                                   parent.hasClass('span8')
                                                                                           ? parent.removeClass('span8').addClass('span12')
                                                                                           : parent.removeClass('span12').addClass('span8');
                                                                                   // Animation complete.
                                                                                   google.maps.event.trigger(map_canvas,
                                                                                                             'resize');
                                                                               });
                                                 });
                    </script>
                </tiles:put>
            </tiles:definition>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>

