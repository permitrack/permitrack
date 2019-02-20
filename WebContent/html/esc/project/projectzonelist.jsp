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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ZONE_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ZONE_DELETE);%>
<fieldset><legend>
    Project Groups
</legend></fieldset>
<table class="table table-hover action-first">
<%--
    <colgroup>
        <col style="width: 90px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>Group Name</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_ZONE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    There are no Groups defined. Click the 'New Group' link above to create a Group.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_ZONE_LIST %>"
                        scope="session">
            <logic:iterate id="projectZoneValue"
                           name="<%= SessionKeys.EC_ZONE_LIST %>">
                <tr>
                    <td>

                        <% if (blnCanEdit)
                        { %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/projectzoneeditpage.do"
                                   paramId="id"
                                   paramName="projectZoneValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehedit.png"
                                      alt="Edit" />
                        </html:link>
                        <% } %>
                        <% if (blnCanDelete)
                        { %>
                        <html:link styleClass='btn btn-mini btn-danger'
                                   action="/projectzonedeletepage.do"
                                   paramId="id"
                                   paramName="projectZoneValue"
                                   paramProperty="id">
                            <html:img src="/sehsvc/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <bean:write name="projectZoneValue"
                                    property="name" />
                    </td>
                    <td>
                        <bean:write name="projectZoneValue"
                                    property="description" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
