<%@ page import="com.sehinc.common.db.client.ClientData" %>
<%@ page import="java.util.List" %>
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
<tiles:useAttribute name="clients"
                    id="clients" />
<div class="row">
    <div class="span3">
        <div class="well sidebar-nav"
             data-spy="affix">
            <ul class="nav nav-list">
                <li class="nav-header">Jump To</li>
                <%
                    Integer
                            ix =
                            0;
                    Integer
                            count =
                            ((List) pageContext.getAttribute("clients")).size();%>
                <logic:iterate id="client"
                               name="clients">
                    <%
                        ix++;
                        if (count
                            < 10
                            || ix
                               % (count
                                  / 10)
                               == 0)
                        {
                    %>
                    <li>
                        <a href='#jump<bean:write name="client" property="id" />'>
                            <bean:write name="client"
                                        property="name" />
                        </a>
                    </li>
                    <%}%>
                </logic:iterate>
            </ul>
        </div>
    </div>
    <div class="span9">
        <ul class="nav nav-pills nav-stacked">
            <logic:iterate id="client"
                           name="clients">
                <li>
                    <html:link styleId='<%= "jump" + ((ClientData) pageContext.getAttribute("client")).getId().toString() %>'
                               action="/adminclientselect.do"
                               paramId="id"
                               paramName="client"
                               paramProperty="id">
                        <div class="row">
                            <div class="span3">
                                <bean:write name="client"
                                            property="name" />
                            </div>
                            <div class="span3">
                                <p class="muted">
                                    <bean:write name="client"
                                                property="comment"
                                                ignore="true" />
                                </p>
                            </div>
                            <div class="span2">
                                <p class="muted">
                                    <bean:write name="client"
                                                property="contactName"
                                                ignore="true" />
                                </p>
                                <p class="muted">
                                    <bean:write name="client"
                                                property="contactPhone"
                                                ignore="true" />
                                </p>
                            </div>
                        </div>
                    </html:link>
                </li>
            </logic:iterate>
        </ul>
    </div>
</div>
