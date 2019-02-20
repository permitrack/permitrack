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
<%@ page import="com.sehinc.common.config.ApplicationProperties" %>
<%@ page import="com.sehinc.security.action.base.RequestKeys" %>
<%@ page import="java.util.List" %>
<%
    pageContext.setAttribute(RequestKeys.CURRENT_CLIENT_LIST,
                             request.getSession()
                                     .getAttribute(RequestKeys.CURRENT_CLIENT_LIST));
    pageContext.setAttribute(RequestKeys.CURRENT_CLIENT_LIST_TITLE,
                             request.getSession()
                                     .getAttribute(RequestKeys.CURRENT_CLIENT_LIST_TITLE));
    try
    {
        pageContext.setAttribute("count",
                                 ((List) (request.getSession()
                                                  .getAttribute(RequestKeys.CURRENT_CLIENT_LIST))).size());
        pageContext.setAttribute("url",
                                 ApplicationProperties.getProperty("service.crm.url"));
    }
    catch (Exception e)
    {
    }
%>
<h4 class="myAccordian">
    <bean:write name="cap.security.current.client.list.title" />
    <logic:notEmpty name="count">
        (<bean:write name="count" />)
    </logic:notEmpty>
</h4>
<div>
    <logic:notEmpty name="cap.security.current.client.list">
        <div class="progress progress-warning"
             style="margin-bottom: 0;">
            <div class="bar"
                 style="width: 20%;">Loading statuses from CRM...
            </div>
        </div>
    </logic:notEmpty>
    <table id="clientlist"
           class="table table-hover tablesorter">
        <%--
            <caption class="label">
            </caption>
        --%>
        <thead>
            <tr>
                <th>
                    ID&nbsp;&nbsp;
                </th>
                <th>
                    Name&nbsp;&nbsp;
                </th>
                <th>
                    Contact&nbsp;&nbsp;
                </th>
                <th>
                    Email&nbsp;&nbsp;
                </th>
                <th>
                    Last Active&nbsp;&nbsp;
                </th>
                <th>
                    Status&nbsp;&nbsp;
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:iterate id="client"
                           name="cap.security.current.client.list">
                <tr>
                    <td>
                        <logic:notEmpty name="client"
                                        property="id">
                            <bean:write name="client"
                                        property="id" />
                        </logic:notEmpty>
                    </td>
                    <td>
                        <html:link action="/clientviewpageaction.do"
                                   paramId="client_id"
                                   paramName="client"
                                   paramProperty="id">
                            <bean:write name="client"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <logic:notEmpty name="client"
                                        property="contactName">
                            <bean:write name="client"
                                        property="contactName" />
                        </logic:notEmpty>
                    </td>
                    <td>
                        <logic:notEmpty name="client"
                                        property="contactEmail">
                            <a href="mailto:<bean:write name='client' property='contactEmail'/>">
                                Email
                            </a>
                        </logic:notEmpty>
                    </td>
                    <td>
                        <div class="noWrap">
                            <bean:write name="client"
                                        property="comment" />
                        </div>
                    </td>
                    <td>
                        <div class="status"
                             id="c<bean:write name="client" property="id" />">
                            <br />
                            <br />
                        </div>
                    </td>
                </tr>
            </logic:iterate>
        </tbody>
    </table>
</div>
<h4 class="myAccordian">
    Emails of Active Users
</h4>
<div id="emails"
     class="pre-scrollable">
    <logic:iterate id="client"
                   name="cap.security.current.client.list">
        <logic:notEmpty name="client"
                        property="comment">
            <logic:notEmpty name="client"
                            property="contactEmail">
                <code>
                    <a href="mailto:<bean:write name='client' property='contactEmail'/>">
                        <bean:write name="client"
                                    property="contactEmail" />
                    </a>
                    ;
                </code>
                &nbsp;
            </logic:notEmpty>
        </logic:notEmpty>
    </logic:iterate>
</div>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            var lastId;
            setTimeout(function ()
                       {
                           $("#feedback").html($("#feedback").html()
                                                       + '<br>Starting.');
                           $(".progress .bar").css("width",
                                                   "40%");
                           $(".status").each(function ()
                                             {
                                                 var that = this;
                                                 var customerId = $(this).attr("id").substring(1);
                                                 lastId
                                                         = customerId;
                                                 $("#feedback").html($("#feedback").html()
                                                                             + '<br>Processing status div customer #'
                                                                             + customerId);
                                                 $(".progress .bar").css("width",
                                                                         "60%");
                                                 $.getJSON('<bean:write name="url" />'
                                                                   + customerId
                                                                   + '?callback=?',
                                                           null,
                                                           function (customers)
                                                           {
                                                               var customerId = $(that).attr("id").substring(1);
                                                               if (lastId
                                                                       == customerId)
                                                               {
                                                                   $(".progress .bar").css("width",
                                                                                           "100%");
                                                                   $(".progress .bar").html('Statuses loaded.');
                                                                   $(".progress").removeClass('progress-warning');
                                                                   $(".progress").addClass('progress-success');
                                                               }
                                                               else
                                                               {
                                                                   $(".progress.progress-warning .bar").css("width",
                                                                                           "80%");
                                                               }
                                                               $("#feedback").html($("#feedback").html()
                                                                                           + '<br>In callback customer #'
                                                                                           + customerId);
                                                               var style = "";
                                                               if (customers.length
                                                                       > 0)
                                                               {
                                                                   var customer = customers[0];
                                                                   var cssClass = "label label-success";
                                                                   if (customer.Status.indexOf("not set")
                                                                               > -1
                                                                               || customer.Type.indexOf("not set")
                                                                           > -1
                                                                           || customer.Subtype.indexOf("not set")
                                                                           > -1)
                                                                   {
                                                                       cssClass
                                                                               = "label label-warning";
                                                                   }
                                                                   else if (customer.Status.indexOf("Error")
                                                                           > 0)
                                                                   {
                                                                       cssClass
                                                                               = "label label-danger";
                                                                       style
                                                                               = "style='display:none;'";
                                                                   }
                                                                   $(that).html("<span class='"
                                                                                        + cssClass
                                                                                        + "'"
                                                                                        + ">"
                                                                                        + customer.Status.substr(0,
                                                                                                                 20)
                                                                                        + "&nbsp;"
                                                                                        + customer.Type.substr(0,
                                                                                                               20)
                                                                                        + "</span> "
                                                                                        + "<span class='"
                                                                                        + "label"
                                                                                        + "'"
                                                                                        + " "
                                                                                        + style
                                                                                        + ">"
                                                                                        + customer.Subtype.substr(0,
                                                                                                                  20)
                                                                                        + "</span>");
                                                               }
                                                               else
                                                               {
                                                                   $(that).html("<span class='label label-important'>"
                                                                                        + "Not found"
                                                                                        + "</span>");
                                                               }
                                                           }).error(function ()
                                                                    {
                                                                        $("#feedback").html($("#feedback").html()
                                                                                                    + '<br>Error.');
                                                                        $(that).html(".!.");
                                                                    });
                                             });
                           $("#feedback").html($("#feedback").html()
                                                       + '<br>Finished.');
                       },
                       5000);
            $(function ()
              {
                  $(".tablesorter").tablesorter({
                                                    headers:{ 4:{ sorter:'isoDate'} }
                                                });
              });
        </script>
    </tiles:put>
</tiles:definition>
