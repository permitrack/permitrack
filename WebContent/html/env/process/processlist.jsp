<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld"
           prefix="nested" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PROCESS_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PROCESS_CAN_DELETE);%>
<fieldset><legend>
    Processes for
    <%=session.getAttribute(SessionKeys.EV_FACILITY_NAME)%>
</legend></fieldset>
<html:form styleClass="form-horizontal"
           action="/processlistpage">
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
                    Number
                </th>
                <th>
                    Name
                </th>
                <th>
                    Description
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT %>"
                         scope="session">
                <tr>
                    <td colspan="4">
                        <bean:message key="process.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT %>"
                            scope="session">
                <logic:iterate id="processListValue"
                               name="<%= SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT %>"
                               indexId="evenOrOdd"
                               scope="session">
                    <tr>
                        <td>

                            <%
                                if (blnCanEdit)
                                {
                            %>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/processeditpage.do"
                                       paramId="evProcessId"
                                       paramName="processListValue"
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
                                       action="/processdeleteaction.do"
                                       paramId="id"
                                       paramName="processListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <td>
                            <html:link styleClass='btn btn-mini'
                                       action="/processviewpage.do"
                                       paramId="evProcessId"
                                       paramName="processListValue"
                                       paramProperty="id">
                                Process
                                <bean:write name="processListValue"
                                            property="processNumber" />
                            </html:link>
                        </td>
                        <td>
                            <bean:write name="processListValue"
                                        property="name" />
                        </td>
                        <td>
                            <ul>
                                <bean:write name="processListValue"
                                            property="description" />
                                <logic:iterate id="myList"
                                               name="processListValue"
                                               property="assetList">
                                    <li>
                                        <bean:write name="myList"
                                                    property="number" />
                                        <bean:write name="myList"
                                                    property="name" />
                                    </li>
                                </logic:iterate>
                            </ul>
                        </td>
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
