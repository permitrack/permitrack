<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<%
    pageContext.setAttribute("itemList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_CORRECTIVE_ACTION_LIST));
    pageContext.setAttribute("headingList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_CORRECTIVE_ACTION_FIELD_LIST));
%>
<table class="table table-hover action-samesize">
<%--
    <colgroup>
        <col style="width: 50px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <logic:notEmpty name="headingList">
                <logic:iterate id="headingListItem"
                               name="headingList">
                    <th>
                        <html:link action="/correctiveactionlistpage.do"
                                   paramId="ecCorrectiveActionListSortKey"
                                   paramName="headingListItem"
                                   paramProperty="sortQueryKey"
                                   title="Sort">
                            <bean:write name="headingListItem"
                                        property="name" />
                        </html:link>
                    </th>
                </logic:iterate>
            </logic:notEmpty>
            <th>BMP Category</th>
            <th>BMP Comment</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="itemList">
            <tr>
                <td colspan="6">
                    There are no items in the list.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="itemList">
            <logic:iterate id="item"
                           name="itemList">
                <bean:define id="projectValue"
                             name="item"
                             property="projectValue" />
                <bean:define id="inspectionSecurityValue"
                             name="projectValue"
                             property="inspectionSecurityPermissionValue" />
                <bean:define id="projectSecurityValue"
                             name="projectValue"
                             property="projectSecurityPermissionValue" />
                <tr>
                    <td class="action-samesize">
                        <logic:equal name="inspectionSecurityValue"
                                     property="isCreate"
                                     value="true">
                            <html:link styleClass="btn btn-small btn-success action-samesize"
                                       href="/sehsvc/html/esc/inspection/inspectioncreatepage.do"
                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                       paramName="item"
                                       paramProperty="projectId">
                                <html:img module="/"
                                          page="/img/icons/sehinspect.png"
                                          alt="NEW INSPECTION: Create a new inspection for this project"
                                          title="NEW INSPECTION: Create a new inspection for this project" />
                                <div style="line-height: 10px;"><small>Inspect</small></div>
                            </html:link>
                        </logic:equal>
                    </td>
                    <td>
                        <logic:equal name="projectSecurityValue"
                                     property="isRead"
                                     value="true">
                            <html:link href="/sehsvc/html/esc/project/projectviewpage.do"
                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                       title="View Project"
                                       paramName="item"
                                       paramProperty="projectId">
                                <bean:write name="item"
                                            property="projectName" />
                            </html:link>
                        </logic:equal>
                    </td>
                    <td>
                        <bean:write name="item"
                                    property="inspectionDateAsString" />
                    </td>
                    <td>
                        <bean:write name="item"
                                    property="bmpName" />
                    </td>
                    <td>
                        <bean:write name="item"
                                    property="bmpCategoryName" />
                    </td>
                    <td>
                        <bean:write name="item"
                                    property="bmpComment" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>