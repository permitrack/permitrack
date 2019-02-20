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
<%@ page import="com.sehinc.environment.action.base.SessionKeys,
                 com.sehinc.environment.action.scc.SccListItem" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SCC_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SCC_CAN_DELETE);%>
<table class="table table-hover action-first">
<%--
    <colgroup>
        <col style="width: 90px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <logic:present name="<%= SessionKeys.SCC_FIELD_LIST %>"
                           scope="session">
                <logic:iterate id="sccListItem"
                               name="<%= SessionKeys.SCC_FIELD_LIST %>">
                    <th>
                        <html:link action="/scclistpage.do"
                                   paramId="sccListSortKey"
                                   paramName="sccListItem"
                                   paramProperty="sortQueryKey"
                                   title="Sort">
                            <bean:write name="sccListItem"
                                        property="name" />
                        </html:link>
                    </th>
                </logic:iterate>
            </logic:present>
            <logic:notPresent name="<%= SessionKeys.SCC_FIELD_LIST %>"
                              scope="session">
                <th>
                    <html:link action="/scclistpage.do?sccListSortKey=sccListSortByNumber"
                               title="Sort">
                        Scc Info
                    </html:link>
                </th>
            </logic:notPresent>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_ACTIVE_SCC_LIST %>"
                     scope="session">
            <tr>
                <td colspan="10">
                    <bean:message key="scc.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SCC_LIST  %>"
                        scope="session">
            <logic:iterate id="sccListValue"
                           name="<%= SessionKeys.EV_ACTIVE_SCC_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanEdit)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/scceditpage.do"
                                   paramId="evSccId"
                                   paramName="sccListValue"
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
                                   action="/sccdeleteaction.do"
                                   paramId="id"
                                   paramName="sccListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <logic:present name="<%= SessionKeys.SCC_FIELD_LIST %>"
                                   scope="session">
                        <logic:iterate id="sccListItem"
                                       name="<%= SessionKeys.SCC_FIELD_LIST %>">
                            <logic:equal name="sccListItem"
                                         property="key"
                                         value="SCC_NUMBER">
                                <td>
                                    <logic:notEmpty name="sccListItem"
                                                    property="image">
                                        <img alt=""
                                             src="<bean:write name='sccListValue' property='<%= ((SccListItem) pageContext.getAttribute("sccListItem")).getImage() %>'/>" />
                                    </logic:notEmpty>
                                    <html:link action="/sccviewpage.do"
                                               paramId="evSccId"
                                               paramName="sccListValue"
                                               paramProperty="id"
                                               title="View Scc Info">
                                        <bean:write name="sccListValue"
                                                    property="number" />
                                    </html:link>
                                </td>
                            </logic:equal>
                            <logic:notEqual name="sccListItem"
                                            property="key"
                                            value="SCC_NUMBER">
                                <td>
                                    <logic:notEmpty name="sccListItem"
                                                    property="image">
                                        <img alt=""
                                             src="<bean:write name='sccListValue' property='<%= ((SccListItem) pageContext.getAttribute("sccListItem")).getImage() %>'/>" />
                                    </logic:notEmpty>
                                    <bean:write name='sccListValue'
                                                property='<%= ((SccListItem) pageContext.getAttribute("sccListItem")).getProperty() %>' />
                                </td>
                            </logic:notEqual>
                        </logic:iterate>
                    </logic:present>
                    <logic:notPresent name="<%= SessionKeys.SCC_FIELD_LIST %>"
                                      scope="session">
                        <td>
                            <html:link action="/sccviewpage.do"
                                       paramId="evSccId"
                                       paramName="sccListValue"
                                       paramProperty="id"
                                       title="View Scc Info">
                                <bean:write name="sccListValue"
                                            property="name" />
                            </html:link>
                        </td>
                    </logic:notPresent>
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
