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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_CAN_DELETE);%>
<table class="table table-hover action-first">
<%--
    <colgroup>
        <col style="width: 90px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>
                Name
            </th>
            <th>
                Active Date
            </th>
            <th>
                Inactive Date
            </th>
            <th>
                Description
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT %>"
                     scope="session">
            <tr>
                <td colspan="5">
                    <bean:message key="permit.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT %>"
                        scope="session">
            <logic:iterate id="permitListValue"
                           name="<%= SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanEdit)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/permiteditpage.do"
                                   paramId="evPermitId"
                                   paramName="permitListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehedit.png"
                                      alt="Edit" />
                        </html:link>
                        <%}%>
                        <%
                            if (blnCanDelete)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/permitdeleteaction.do"
                                   paramId="id"
                                   paramName="permitListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <html:link action="/permitviewpage.do"
                                   paramId="evPermitId"
                                   paramName="permitListValue"
                                   paramProperty="id">
                            <bean:write name="permitListValue"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="permitListValue"
                                    property="activeTsText" />
                    </td>
                    <td>
                        <bean:write name="permitListValue"
                                    property="inactiveTsText" />
                    </td>
                    <td>
                        <bean:write name="permitListValue"
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
