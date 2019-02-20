<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ include file="eventPageHeader.jsp" %>
<%
    List
            compliantProjectsList =
            (List) session.getAttribute(SessionKeys.EC_COMPLIANT_PROJECT_LIST);
    List
            nonCompliantProjectsList =
            (List) session.getAttribute(SessionKeys.EC_NONCOMPLIANT_PROJECT_LIST);%>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="event.type" />
    </dt>
    <dd>
        <bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>"
                    property="eventType.name" />
    </dd>
    <dt>
        <bean:message key="event.description" />
    </dt>
    <dd>
        <bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>"
                    property="description" /></dd>
    <dt>
        <bean:message key="event.notice" />
    </dt>
    <dd>
        <bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>"
                    property="notice" />
    </dd>
    <logic:notEmpty name="<%= RequestKeys.EC_EVENT_VALUE %>"
                    property="complianceDate"
                    scope="request">
        <dt>
            <bean:message key="event.compiancebydate" />
        </dt>
        <dd>
            <bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>"
                        property="complianceDateString" />
        </dd>
    </logic:notEmpty>
    <dt>
        <bean:message key="event.document" />
    </dt>
    <dd>
        <logic:empty name="<%= SessionKeys.EC_EVENT_DOCUMENT_VALUE %>"
                     property='docName'>
            None
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_EVENT_DOCUMENT_VALUE %>"
                        property='docName'>
            <a href="<bean:write name="<%= SessionKeys.EC_EVENT_DOCUMENT_VALUE %>" property='downloadURL' />">
                <bean:write name="<%= SessionKeys.EC_EVENT_DOCUMENT_VALUE %>"
                            property='docName' />
            </a>
        </logic:notEmpty>
    </dd>
</dl>
<table class="table table-hover">
    <caption class="label">
        Non-Compliant Projects: <%=nonCompliantProjectsList.size()%>
    </caption>
    <colgroup>
        <col style="width: 33%;" />
        <col />
    </colgroup>
    <thead>
        <tr>
            <th>Project Name</th>
            <th>Contacts</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_NONCOMPLIANT_PROJECT_LIST %>"
                     scope="session">
            <tr>
                <td colspan="10">
                    There are no non-compliant projects.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="nonCompliantProjectsList"
                        scope="session">
            <logic:iterate id="projectValue"
                           name="nonCompliantProjectsList"
                           scope="session">
                <tr>
                    <td>
                        <bean:define id="projSec"
                                     name="projectValue"
                                     property="projectSecurityPermissionValue" />
                        <logic:equal name="projSec"
                                     property="isRead"
                                     value="true">
                            <html:link href="/sehsvc/html/esc/project/projectviewpage.do"
                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                       paramName="projectValue"
                                       paramProperty="id">
                                <bean:write name="projectValue"
                                            property="name" />
                            </html:link>
                        </logic:equal>
                        <logic:notEqual name="projSec"
                                        property="isRead"
                                        value="true">
                            <bean:write name="projectValue"
                                        property="name" />
                        </logic:notEqual>
                    </td>
                    <logic:notEmpty name="projectValue">
                        <logic:notEmpty name="projectValue"
                                        property="permittedClientContact">
                            <bean:define id="projectClientContact"
                                         name="projectValue"
                                         property="permittedClientContact" />
                            <logic:empty name="projectClientContact">
                                <td class="warning">
                                    No Permittee Contact Assigned
                                </td>
                            </logic:empty>
                            <logic:notEmpty name="projectClientContact">
                                <td>
                                    <p>
                                        <bean:write name="projectValue"
                                                    property="permittedClientName" />
                                    </p>
                                    <p>
                                        <bean:write name="projectClientContact"
                                                    property="firstName" />
                                        <bean:write name="projectClientContact"
                                                    property="lastName" />
                                    </p>
                                    <p>
                                        <a href="mailto:<bean:write name='projectClientContact' property='email'/>?Subject=<bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>" property="description"/>">
                                            <bean:write name='projectClientContact'
                                                        property='email' />
                                        </a>
                                    </p>
                                    <p>
                                        <bean:write name="projectClientContact"
                                                    property="primaryPhone" />
                                    </p>
                                </td>
                            </logic:notEmpty>
                        </logic:notEmpty>
                        <logic:empty name="projectValue"
                                     property="permittedClientContact">
                            <td class="warning">
                                No Permittee Contact Assigned
                            </td>
                        </logic:empty>
                    </logic:notEmpty>
                    <logic:empty name="projectValue">
                        <td class="warning">
                            No Permittee Contact Assigned
                        </td>
                    </logic:empty>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<table class="table table-hover">
    <caption class="label">
        Compliant Projects: <%=compliantProjectsList.size()%>
    </caption>
    <colgroup>
        <col style="width: 33%;" />
        <col />
    </colgroup>
    <thead>
        <tr>
            <th>Project Name</th>
            <th>Contacts</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="compliantProjectsList"
                     scope="session">
            <tr>
                <td colspan="10">
                    There are no compliant projects.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_COMPLIANT_PROJECT_LIST %>"
                        scope="session">
            <logic:iterate id="projectValue"
                           name="compliantProjectsList"
                           scope="session">
                <tr>
                    <td>
                        <bean:define id="projSec2"
                                     name="projectValue"
                                     property="projectSecurityPermissionValue" />
                        <logic:equal name="projSec2"
                                     property="isRead"
                                     value="true">
                            <html:link href="/sehsvc/html/esc/project/projectviewpage.do"
                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                       paramName="projectValue"
                                       paramProperty="id">
                                <bean:write name="projectValue"
                                            property="name" />
                            </html:link>
                        </logic:equal>
                        <logic:notEqual name="projSec2"
                                        property="isRead"
                                        value="true">
                            <bean:write name="projectValue"
                                        property="name" />
                        </logic:notEqual>
                    </td>
                    <logic:notEmpty name="projectValue">
                        <logic:notEmpty name="projectValue"
                                        property="permittedClientContact">
                            <bean:define id="projectClientContact"
                                         name="projectValue"
                                         property="permittedClientContact" />
                            <logic:empty name="projectClientContact">
                                <td class="warning">
                                    No Permittee Contact Assigned
                                </td>
                            </logic:empty>
                            <logic:notEmpty name="projectClientContact">
                                <td>
                                    <p>
                                        <bean:write name="projectValue"
                                                    property="permittedClientName" />
                                    </p>
                                    <p>
                                        <bean:write name="projectClientContact"
                                                    property="firstName" />
                                        <bean:write name="projectClientContact"
                                                    property="lastName" />
                                    </p>
                                    <p>
                                        <a href="mailto:<bean:write name='projectClientContact' property='email'/>?Subject=<bean:write name="<%= RequestKeys.EC_EVENT_VALUE %>" property="description"/>">
                                            <bean:write name='projectClientContact'
                                                        property='email' />
                                        </a>
                                    </p>
                                    <p>
                                        <bean:write name="projectClientContact"
                                                    property="primaryPhone" />
                                    </p>
                                </td>
                            </logic:notEmpty>
                        </logic:notEmpty>
                        <logic:empty name="projectValue"
                                     property="permittedClientContact">
                            <td class="warning">No Permittee Contact Assigned</td>
                        </logic:empty>
                    </logic:notEmpty>
                    <logic:empty name="projectValue">
                        <td class="warning">No Permittee Contact Assigned</td>
                    </logic:empty>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>

