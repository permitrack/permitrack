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
                 com.sehinc.environment.action.source.SourceListItem" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_CAN_DELETE);%>
<html:form styleClass="form-horizontal"
           action="/sourcelistpage">
    <table class="table table-hover action-first">
<%--
        <colgroup>
            <col style="width: 90px; white-space: nowrap;" />
        </colgroup>
--%>
        <thead>
            <tr>
                <th></th>
                <logic:present name="<%= SessionKeys.SOURCE_FIELD_LIST %>"
                               scope="session">
                    <logic:iterate id="sourceListItem"
                                   name="<%= SessionKeys.SOURCE_FIELD_LIST %>">
                        <th>
                            <html:link action="/sourcelistpage.do"
                                       paramId="sourceListSortKey"
                                       paramName="sourceListItem"
                                       paramProperty="sortQueryKey"
                                       title="Sort">
                                <bean:write name="sourceListItem"
                                            property="name" />
                            </html:link>
                        </th>
                    </logic:iterate>
                </logic:present>
                <logic:notPresent name="<%= SessionKeys.SOURCE_FIELD_LIST %>"
                                  scope="session">
                    <th>
                        <html:link action="/sourcelistpage.do?sourceListSortKey=sourceListSortBySourceName"
                                   title="Sort">
                            Source
                        </html:link>
                    </th>
                </logic:notPresent>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EV_ACTIVE_SOURCE_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="10">
                        <bean:message key="source.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SOURCE_LIST %>"
                            scope="session">
                <logic:iterate id="sourceListValue"
                               name="<%= SessionKeys.EV_ACTIVE_SOURCE_LIST %>"
                               scope="session">
                    <tr>
                        <td>

                            <%
                                if (blnCanEdit)
                                {
                            %>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/sourceeditpage.do"
                                       paramId="evSourceId"
                                       paramName="sourceListValue"
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
                                       action="/sourcedeleteaction.do"
                                       paramId="id"
                                       paramName="sourceListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <logic:present name="<%= SessionKeys.SOURCE_FIELD_LIST %>"
                                       scope="session">
                            <logic:iterate id="sourceListItem"
                                           name="<%= SessionKeys.SOURCE_FIELD_LIST %>">
                                <logic:equal name="sourceListItem"
                                             property="key"
                                             value="DISPLAY_LABEL">
                                    <td>
                                        <logic:notEmpty name="sourceListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sourceListValue' property='<%= ((SourceListItem) pageContext.getAttribute("sourceListItem")).getImage() %>' ignore="true"/>" />
                                        </logic:notEmpty>
                                        <html:link action="/sourceviewpage.do"
                                                   paramId="evSourceId"
                                                   paramName="sourceListValue"
                                                   paramProperty="id"
                                                   title="View Source">
                                            <bean:write name="sourceListValue"
                                                        property="displayName" />
                                        </html:link>
                                    </td>
                                </logic:equal>
                                <logic:notEqual name="sourceListItem"
                                                property="key"
                                                value="DISPLAY_LABEL">
                                    <td>
                                        <logic:notEmpty name="sourceListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sourceListValue' property='<%= ((SourceListItem) pageContext.getAttribute("sourceListItem")).getImage() %>' ignore='true'/>" />
                                        </logic:notEmpty>
                                        <bean:write name='sourceListValue'
                                                    property='<%= ((SourceListItem) pageContext.getAttribute("sourceListItem")).getProperty() %>' />
                                    </td>
                                </logic:notEqual>
                            </logic:iterate>
                        </logic:present>
                        <logic:notPresent name="<%= SessionKeys.SOURCE_FIELD_LIST %>"
                                          scope="session">
                            <td>
                                <html:link action="/sourceviewpage.do"
                                           paramId="evSourceId"
                                           paramName="sourceListValue"
                                           paramProperty="id"
                                           title="View Source">
                                    <bean:write name="sourceListValue"
                                                property="displayName" />
                                </html:link>
                            </td>
                        </logic:notPresent>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
