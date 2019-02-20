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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<bean:define id="gisCoordList"
             name="<%= RequestKeys.GIS_SEARCH_RESULTS %>"
             scope="request" />
<fieldset><legend>
    Coordinate Search Results
</legend></fieldset>
<div class="alert">
    <div>
        You can edit the coordinate information for each parcel number in the search results by editing the latitude and longitude fields.
    </div>
    You can delete a coordinate entry by checking the box next to the left of the parcel number.
</div>
<logic:notEmpty name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                scope="request">
    <p class="text-warning">
        <bean:write name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                    scope="request" />
    </p>
</logic:notEmpty>
<html:form styleClass="form-horizontal"
           action="/giseditaction">
    <table class="table table-hover action-first action-small">
        <thead>
            <tr>
                <th>
                    Delete
                </th>
                <th>
                    <bean:message key="project.parcelNumber" />
                </th>
                <th>
                    <bean:message key="project.gisy" />
                </th>
                <th>
                    <bean:message key="project.gisx" />
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:notEmpty name="gisCoordList">
                    <%
                        int
                                count =
                                0; %>
                <logic:iterate id="gisCoord"
                               name="gisCoordList">
                    <tr>
                        <td>
                            <html:multibox name="gisCoord"
                                           property="isDeleted"
                                           value="<%= String.valueOf(count) %>" />
                        </td>
                        <td>
                            <bean:write name="gisCoord"
                                        property="parcelNumber" />
                            <html:hidden name="gisCoord"
                                         property="parcelNumber" />
                        </td>
                        <td>
                            <html:text name="gisCoord"
                                       property="gisY"
                                       size="20"
                                       maxlength="20" />
                        </td>
                        <td>
                            <html:text name="gisCoord"
                                       property="gisX"
                                       size="20"
                                       maxlength="20" />
                        </td>
                    </tr>
                    <% count++;%>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:link styleClass="btn btn-large btn-success"
                           action="/gissearchpage">
                    Search Again
                </html:link>
            </div>
            <div class="span6">
                <logic:notEmpty name="gisCoordList">
                    <html:submit styleClass="btn btn-danger btn-large pull-right"
                                 property="submit"
                                 value="Delete Checked" />
                </logic:notEmpty>
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
