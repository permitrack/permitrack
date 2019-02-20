<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ include file="eventPageHeader.jsp" %>
<table class="table table-hover action-first action-small">
<%--
    <colgroup>
        <col style="width:50px;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>Type</th>
            <th>Description</th>
            <th>Event Date</th>
            <th>Compliance Date</th>
            <th>Compliance Hours</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%=SessionKeys.EC_EVENT_LIST%>"
                     scope="session">
            <tr>
                <td colspan="6">
                    There are no Events defined for <%= strClientName %>
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%=SessionKeys.EC_EVENT_LIST%>"
                        scope="session">
            <logic:iterate id="eventValue"
                           name="<%=SessionKeys.EC_EVENT_LIST%>">
                <tr>
                    <td>
                        <html:link styleClass="btn btn-mini"
                                   action="/eventComplianceReportPageAction.do"
                                   paramId="<%=RequestKeys.EC_EVENT_ID%>"
                                   paramName="eventValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehreport.png"
                                      alt="Compliance Report" />
                        </html:link>
                    </td>
                    <td>
                        <div class="label">
                            <bean:write name="eventValue"
                                        property="eventType.name" />
                        </div>
                    </td>
                    <td>
                        <html:link action="/eventComplianceReportPageAction.do"
                                   paramId="<%=RequestKeys.EC_EVENT_ID%>"
                                   paramName="eventValue"
                                   paramProperty="id">
                            <bean:write name="eventValue"
                                        property="description" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="eventValue"
                                    property="eventDateString" />
                    </td>
                    <td>
                        <bean:write name="eventValue"
                                    property="complianceDateString" />
                    </td>
                    <td>
                        <bean:write name="eventValue"
                                    property="complianceHours" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>