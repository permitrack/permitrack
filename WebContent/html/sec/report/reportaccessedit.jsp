<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ page import="com.sehinc.security.action.base.RequestKeys" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%
    pageContext.setAttribute("allReports",
                             request.getSession()
                                     .getAttribute(RequestKeys.ALL_REPORTS));
%>
<html:form styleClass="form-horizontal"
           action="/reportaccesseditaction">
    <h4 class="myAccordian">
        ENV Reports
    </h4>
    <table class="table table-hover action-first action-small">
            <%--
                    <caption class="label">
                        Active Reports
                    </caption>
            --%>
        <thead>
            <tr>
                <th>
                    Enabled
                </th>
                    <%--
                                    <th>
                                        Module
                                    </th>
                    --%>
                <th>
                    Report
                </th>
                <th>
                    Description
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="allReports">
                <tr>
                    <td colspan="4">
                        <bean:message key="report.access.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="allReports">
                <logic:iterate id="aReport"
                               name="allReports">
                    <tr>
                        <td>
                            <html:multibox name="ReportAccessForm"
                                           property="selectedReports">
                                <bean:write name="aReport"
                                            property="reportId" />
                            </html:multibox>
                        </td>
                            <%--
                                                    <td>
                                                        <bean:write name="aReport"
                                                                    property="moduleName" />
                                                    </td>
                            --%>
                        <td>
                            <bean:write name="aReport"
                                        property="name" />
                        </td>
                        <td>
                            <bean:write name="aReport"
                                        property="description" />
                        </td>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>