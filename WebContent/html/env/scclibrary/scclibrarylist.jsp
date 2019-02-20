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
                 com.sehinc.environment.action.scclibrary.SccLibraryListItem" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SCC_LIBRARY_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SCC_LIBRARY_CAN_DELETE);%>
<html:form styleClass="form-horizontal"
           action="/scclibrarylistpage">
    <fieldset><legend>
        SCC Library
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
                <logic:present name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>"
                               scope="session">
                    <logic:iterate id="sccLibraryListItem"
                                   name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>">
                        <th>
                            <html:link action="/scclibrarylistpage.do"
                                       paramId="sccLibraryListSortKey"
                                       paramName="sccLibraryListItem"
                                       paramProperty="sortQueryKey"
                                       title="Sort">
                                <bean:write name="sccLibraryListItem"
                                            property="name" />
                            </html:link>
                        </th>
                    </logic:iterate>
                </logic:present>
                <logic:notPresent name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>"
                                  scope="session">
                    <th>
                        <html:link action="/scclibrarylistpage.do?sccLibraryListSortKey=sccLibraryListSortByNumber"
                                   title="Sort">
                            Scc Library Item
                        </html:link>
                    </th>
                </logic:notPresent>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="10">
                        <bean:message key="scc.library.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST  %>"
                            scope="session">
                <logic:iterate id="sccLibraryListValue"
                               name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST %>"
                               scope="session">
                    <tr>
                        <td>

                            <%
                                if (blnCanEdit)
                                {
                            %>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/scclibraryeditpage.do"
                                       paramId="evSccLibraryId"
                                       paramName="sccLibraryListValue"
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
                                       action="/scclibrarydeleteaction.do"
                                       paramId="id"
                                       paramName="sccLibraryListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <logic:present name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>"
                                       scope="session">
                            <logic:iterate id="sccLibraryListItem"
                                           name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>">
                                <logic:equal name="sccLibraryListItem"
                                             property="key"
                                             value="SCC_NUMBER">
                                    <td>
                                        <logic:notEmpty name="sccLibraryListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sccLibraryListValue' property='<%= ((SccLibraryListItem) pageContext.getAttribute("sccLibraryListItem")).getImage() %>'/>" />
                                        </logic:notEmpty>
                                        <html:link action="/scclibraryviewpage.do"
                                                   paramId="evSccLibraryId"
                                                   paramName="sccLibraryListValue"
                                                   paramProperty="id"
                                                   title="View SCC Library Item">
                                            <bean:write name="sccLibraryListValue"
                                                        property="number" />
                                        </html:link>
                                    </td>
                                </logic:equal>
                                <logic:notEqual name="sccLibraryListItem"
                                                property="key"
                                                value="SCC_NUMBER">
                                    <td>
                                        <logic:notEmpty name="sccLibraryListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sccLibraryListValue' property='<%= ((SccLibraryListItem) pageContext.getAttribute("sccLibraryListItem")).getImage() %>'/>" />
                                        </logic:notEmpty>
                                        <bean:write name='sccLibraryListValue'
                                                    property='<%= ((SccLibraryListItem) pageContext.getAttribute("sccLibraryListItem")).getProperty() %>' />
                                    </td>
                                </logic:notEqual>
                            </logic:iterate>
                        </logic:present>
                        <logic:notPresent name="<%= SessionKeys.SCC_LIBRARY_FIELD_LIST %>"
                                          scope="session">
                            <td>
                                <html:link action="/scclibraryviewpage.do"
                                           paramId="evSccLibraryId"
                                           paramName="sccLibraryListValue"
                                           paramProperty="id"
                                           title="View Scc Library Item">
                                    <bean:write name="sccLibraryListValue"
                                                property="name" />
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
