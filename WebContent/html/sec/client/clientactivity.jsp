<%@ page import="com.sehinc.common.util.DateUtil" %>
<%@ page import="com.sehinc.security.action.base.SessionKeys" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%
    pageContext.setAttribute(SessionKeys.CLIENT_ACTIVITY,
                             request.getAttribute(SessionKeys.CLIENT_ACTIVITY));
    String
            script_counts =
            "";
%>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif|Open+Sans:400,700'
      rel='stylesheet'
      type='text/css'>
<%--
<link href='<html:rewrite module="/" page="/javascript/vertical-timeline/css/reset.css"/>'
      rel="styleSheet"
      type="text/css" />
--%>
<link href='<html:rewrite module="/" page="/javascript/vertical-timeline/css/style.css"/>'
      rel="styleSheet"
      type="text/css" />
<%
    String
            typeParameter =
            request.getParameter("type");
    if (typeParameter
        != null
        && typeParameter.equals("timeline"))
    {%>
<legend>
    User Activity Timeline
</legend>

<div id="timeline">
<section id="cd-timeline"
class="cd-container">
<%
    String
            currentSet;
    String
            lastSet =
            "";
    String
            currentClient;
    String
            lastClient =
            "";
    Date
            currentDate =
            null;
    Date
            lastDate =
            new Date();
    Integer
            count =
            0;
    Integer
            index =
            0;
    String
            activityDate;%>
<logic:iterate id="a"
               name="client.activity">
            <%
                Object[]
                        arr =
                        (Object[]) a;
                currentDate =
                        DateUtil.MDYTT_FORMAT
                                .parse(arr[3].toString());
                activityDate =
                        DateUtil.MMDDHHMM_FORMAT
                                .format(currentDate);
                currentSet =
                        String.valueOf(arr[10])
                        + String.valueOf(arr[11])
                        + String.valueOf(arr[1])
                        + String.valueOf(arr[5]);
                currentClient =
                        String.valueOf(arr[7]);
                if (lastSet.equals(currentSet))
                {
                    count++;
                }
                else
                {
                    index++;
                    if (index
                        > 1
                        && count
                           > 1)
                    {
                        script_counts +=
                                "$('#count"
                                + (index
                                   - 1)
                                + "').html('"
                                + count
                                + "');";
                    }
                    count =
                            1;
                    lastSet =
                            String.valueOf(arr[10])
                            + String.valueOf(arr[11])
                            + String.valueOf(arr[1])
                            + String.valueOf(arr[5]);
                    if (!lastClient.equals(currentClient) || currentDate.getTime() <= (lastDate.getTime() - DateUtil.MILLISEC_PER_HOUR))
                    {
                        if (!lastClient.equals(""))
                        {%>
                            </div>
                            </div>
                        <%}%>
                        <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-picture">
                            <img src="<html:rewrite module="/" page="/javascript/vertical-timeline/img/cd-icon-picture.svg" />"
                                 alt="" />
                        </div>
                        <!-- cd-timeline-img -->
                        <div class="cd-timeline-content">
                        <h2>
                            <bean:write name="a"
                                        property="[7]" />
                        </h2>
                    <%}%>
            <p>
                <strong>
                    <bean:write name="a"
                                property="[10]" />
                    <bean:write name="a"
                                property="[11]" />
                </strong>
                <logic:equal name="a"
                             property="[5]"
                             value="Logged In">
                    <bean:write name="a"
                                property="[5]" />
                </logic:equal>
                <logic:notEqual name="a"
                                property="[5]"
                                value="Logged In">
                    updated
                    (<span id="count<%=index%>">1</span>)
                    <strong>
                        <bean:write name="a"
                                    property="[1]" />
                    </strong>
                    :
                    <strong>
                        <bean:write name="a"
                                    property="[5]" />
                    </strong>
                    <logic:notEmpty name="a"
                                    property="[8]">
                        on behalf of
                        <strong>
                            <bean:write name="a"
                                        property="[8]" />
                        </strong>
                    </logic:notEmpty>
                </logic:notEqual>
            </p>
            <%--
                                <a href="#"
                                   class="cd-read-more">Read more
                                </a>
            --%>
            <%
                if (!lastClient.equals(currentClient) || currentDate.getTime() <= (lastDate.getTime() - DateUtil.MILLISEC_PER_HOUR))
                {
            %>
                    <span class="cd-date">
                        <%=activityDate%>
                    </span>
            <%
                    lastDate =
                            currentDate;
                }
                lastClient =
                        currentClient;
            }
            %>
        </logic:iterate>
        </section>
</div>
<%}%>
<%--
<html:form styleClass="form-search"
           action="/useraddpageaction"
           method="get">
    <html:text styleClass="input-medium search-query"
               styleId="firstName"
               property="firstName" />
    <button type="submit"
            class="btn">
        Search
    </button>
</html:form>
--%>
<%--
<html:form styleClass="form-search"
           action="/clientcreateaction">
    <html:hidden name="userForm"
                 property="id"
                 styleId="id" />
--%>
<%
    if (typeParameter
        != null
        && typeParameter.equals("grid"))
    {
%>
<legend>
    User Activity Grid
</legend>

<table id="activitylist"
       class="table table-hover tablesorter table-condensed"
       style="font-size: smaller;"><%--action-first action-small--%>
    <%--
            <caption class="label">
                Active Users
            </caption>
    --%>
    <thead>
        <tr>
            <%--<th></th>--%>
            <th>
                Date
            </th>
            <th>
                First Name
            </th>
            <th>
                Last Name
            </th>
            <th>
                Username
            </th>
            <th>
                Client
            </th>
            <th>
                On Behalf Of
            </th>
            <th>
                Email
            </th>
            <th>
                Action
            </th>
            <th>
                Project
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="a"
                       name="client.activity">
            <tr>
                    <%--
                                        <td>
                                            <button class="btn btn-success btn-mini"
                                                    type="submit"
                                                    value="Add"
                                                    onclick="return setId('<bean:write name="a" property="id" />');">
                                                Add
                                            </button>
                                        </td>
                    --%>
                <td>
                    <bean:write name="a"
                                property="[3]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[10]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[11]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[9]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[7]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[8]" />
                </td>
                <td>
                    <a href="mailto:<bean:write name='a' property='[13]'/>">
                        <bean:write name="a"
                                    property="[13]" />
                    </a>
                </td>
                <td>
                    <bean:write name="a"
                                property="[1]" />
                </td>
                <td>
                    <bean:write name="a"
                                property="[5]" />
                </td>
            </tr>
        </logic:iterate>
        <logic:empty name="client.activity">
            <tr>
                <td colspan="10">
                    No activity found
                </td>
            </tr>
        </logic:empty>
    </tbody>
</table>
<%}%>
<%
    if (typeParameter
        != null
        && typeParameter.equals("graph"))
    {
%>
    <link href='<html:rewrite module="/" page="/css/graph/graph.css"/>'
          rel="styleSheet"
          type="text/css" />
    <link href='<html:rewrite module="/" page="/css/graph/lines.css"/>'
          rel="styleSheet"
          type="text/css" />
    <style type="text/css">
        #chart {
            position: relative;
            left: 40px;
            display: block;
        }

        #y_axis {
            position: absolute;
            top: 0;
            bottom: 0;
            width: 40px;
        }

        #x_axis, #x_axis2 {
            position: relative;
            left: 40px;
            height: 40px;
        }
        #x_axis2
        {
            top: -20px;
        }
        #x_axis2 .x_ticks_d3 path, #x_axis2 .x_ticks_d3 .tick
        {
            display: none;
        }
    </style>
    <legend>
        User Activity Graph
    </legend>
    <div id="chart_container">
        <div id="y_axis"></div>
        <div id="chart"></div>
        <div id="x_axis"></div>
        <div id="x_axis2"></div>
    </div>
<%}%>

<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript"
                src='<html:rewrite module="/" page="/javascript/vendor/d3.min.js" />'></script>
        <script type="text/javascript"
                src='<html:rewrite module="/" page="/javascript/vendor/d3.layout.min.js" />'></script>
        <script type="text/javascript"
                src='<html:rewrite module="/" page="/javascript/rickshaw.min.js" />'></script>
        <script type="text/javascript">
            $("#activitylist").tablesorter({
                                               headers:{ 0:{ sorter:'isoDate'} }
                                           });
            <%=script_counts%>

            <%
                Integer hour_index = -1;

                if (typeParameter
                    != null
                    && typeParameter.equals("graph"))
                {
                    Integer current_hour;
                    Integer last_hour = -1;
                    Integer hour_count = 1;

                    String graphData = "";
                    String graphAxisX = "";
                    String graphAxisX2 = "";


                    for(Object row : (List)pageContext.getAttribute(SessionKeys.CLIENT_ACTIVITY))
                    {
                        Object[]
                                arr =
                                (Object[]) row;
                        Date ddd = DateUtil.MDYTT_FORMAT.parse(arr[3].toString());
                        String activityDate2 =
                                DateUtil.EEHA_FORMAT.format(ddd);
                        String activityDate3 =
                                DateUtil.PARSE_DATE_FORMAT.format(ddd);
                        current_hour = ddd.getHours();

                        if(current_hour.equals(last_hour))
                        {
                            hour_count++;
                        }
                        else
                        {
                            if(hour_index > -1)
                            {
                                graphData += "{ x:" + hour_index + ", y:" + hour_count + " },";

                                String xlabel =  (hour_index % 4 == 0) ? activityDate2 : "";
                                String xlabel2 =  (hour_index % 4 == 0) ? activityDate3 : "";

                                graphAxisX += hour_index + ": '" + xlabel + "',";
                                graphAxisX2 += hour_index + ": '" + xlabel2 + "',";

                                /*
                                                    0: 'zero',
                                                    1: 'first',
                                                    2: 'second',
                                */

                                /*
                                   { x:0, y:40 },
                                   { x:0, y:0 }
                                */
                            }
                            hour_index++;
                            last_hour = current_hour;
                            hour_count = 1;
                        }

                        if(hour_index >= 50)
                        {
                            break;
                        }
                    }

                    graphData = graphData.substring(0, graphData.length() - 1);
                    graphAxisX = graphAxisX.substring(0, graphAxisX.length() - 1);
                    graphAxisX2 = graphAxisX2.substring(0, graphAxisX2.length() - 1);
            %>
            var graph = new Rickshaw.Graph({
                                               element:document.querySelector("#chart"),
                                               width:1000,
                                               height:600,
                                               series:
                                                       [
                                                           {
                                                               color:'steelblue',
                                                               data:
                                                                       [
                                                                           <%=graphData%>
                                                                       ]
                                                           }
                                                       ]
                                           });
            var format = function (n)
            {
                var map = {
                    <%=graphAxisX%>
                };
                return map[n];
            }
            var format2 = function (n)
            {
                var map = {
                    <%=graphAxisX2%>
                };
                return map[n];
            }
            var x_ticks = new Rickshaw.Graph.Axis.X({
                                                        graph:graph,
                                                        orientation:'bottom',
                                                        element:document.getElementById('x_axis'),
                                                        pixelsPerTick:1000
                                                                / <%=hour_index%>,
                                                        tickFormat:format
                                                    });
            var x_ticks2 = new Rickshaw.Graph.Axis.X({
                                                        graph:graph,
                                                        orientation:'bottom',
                                                        element:document.getElementById('x_axis2'),
                                                        pixelsPerTick:1000
                                                                / <%=hour_index%>,
                                                        tickFormat:format2
                                                    });
            graph.render();
            <%}%>
        </script>
    </tiles:put>
</tiles:definition>
