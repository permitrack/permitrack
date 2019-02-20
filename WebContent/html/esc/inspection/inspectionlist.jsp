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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.action.inspection.InspectionListItem" %>
<fieldset>
    <legend>
        <span style="margin-right: 20px;">
            <bean:write name="<%= SessionKeys.EC_PROJECT %>"
                        property="name"
                        scope="session" />
        </span>
        <logic:present name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                       scope="session">
            <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                            scope="session">
                <div class="btn-group">
                    <a class="btn dropdown-toggle btn-success btn-mini"
                       data-toggle="dropdown"
                       href="#">
                        + Add Inspection to a Different Project
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <logic:iterate id="projectValue"
                                       name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                                       scope="session">
                            <bean:define id="inspectionSecurityValue"
                                         name="projectValue"
                                         property="inspectionSecurityPermissionValue" />
                            <logic:equal name="inspectionSecurityValue"
                                         property="isCreate"
                                         value="true">
                                <li>
                                    <html:link module="/html/esc/inspection"
                                               action="/inspectioncreatepage.do"
                                               paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                               paramName="projectValue"
                                               paramProperty="projectId">
                                        <bean:write name="projectValue"
                                                    property="name" />
                                    </html:link>
                                </li>
                            </logic:equal>
                        </logic:iterate>
                    </ul>
                </div>
            </logic:notEmpty>
        </logic:present>
    </legend>
</fieldset>
<table class="table table-hover table-condensed action-samesize action-large">
    <thead>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <logic:present name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>"
                           scope="session">
                <logic:iterate id="inspectionListItem"
                               name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>">
                    <logic:equal name="inspectionListItem"
                                 property="key"
                                 value="INSPECTION_FAILED">
                        <th>
                            <bean:write name="inspectionListItem"
                                        property="name" />
                        </th>
                    </logic:equal>
                    <logic:notEqual name="inspectionListItem"
                                    property="key"
                                    value="INSPECTION_FAILED">
                        <th>
                            <html:link action="/inspectionlistpage.do"
                                       paramId="ecInspectionListSortKey"
                                       paramName="inspectionListItem"
                                       paramProperty="sortQueryKey"
                                       title="Sort">
                                <bean:write name="inspectionListItem"
                                            property="name" />
                            </html:link>
                        </th>
                    </logic:notEqual>
                </logic:iterate>
            </logic:present>
            <logic:notPresent name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>"
                              scope="session">
                <th>
                    <html:link action="/inspectionlistpage.do?ecInspectionListSortKey=ecInspectionDateSort"
                               title="Sort">
                        Inspection Date
                    </html:link>
                </th>
            </logic:notPresent>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_INSPECTION_LIST %>"
                     scope="session">
            <tr>
                <td colspan="50">
                    There are no inspections in this project.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_INSPECTION_LIST %>"
                        scope="session">
            <logic:iterate id="inspectionListValue"
                           name="<%= SessionKeys.EC_INSPECTION_LIST %>">
                <tr>
                    <bean:define id="inspectionSecurityValue"
                                 name="inspectionListValue"
                                 property="inspectionSecurityPermissionValue" />
                    <td class="action-samesize">
                        <logic:equal name="inspectionSecurityValue"
                                     property="isUpdate"
                                     value="true">
                            <html:link styleClass='btn btn-small btn-success action-samesize'
                                       action="/inspectioneditpage.do"
                                       paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                                       paramName="inspectionListValue"
                                       paramProperty="inspectionId">
                                <img src="<html:rewrite module="/" page="/img/icons/sehedit.png" />"
                                     alt="Edit"
                                     title="Edit" />
                                <div style="line-height: 10px;"><small>Edit</small></div>
                            </html:link>
                        </logic:equal>
                    </td>
                    <td class="action-samesize">
                        <logic:equal name="inspectionSecurityValue"
                                     property="isDelete"
                                     value="true">
                            <html:link styleClass='btn btn-small btn-danger warn-delete action-samesize'
                                       action="/inspectiondeleteaction.do"
                                       paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                                       paramName="inspectionListValue"
                                       paramProperty="inspectionId">
                                <img src="<html:rewrite module="/" page="/img/icons/sehdelete.png" />"
                                     alt="Delete"
                                     title="Delete" />
                                <div style="line-height: 10px;"><small>Delete</small></div>
                            </html:link>
                        </logic:equal>
                    </td>
                    <td class="action-samesize">
                        <logic:equal name="inspectionSecurityValue"
                                     property="isRead"
                                     value="true">
                            <a class='btn btn-small action-samesize'
                               href="<bean:write name='inspectionListValue' property='reportUrl' filter='false' />">
                                <img src="<html:rewrite module="/" page="/img/icons/sehreport.png" />"
                                     alt="Inspection Report (PDF)"
                                     title="Inspection Report (PDF)" />
                                <div style="line-height: 10px;"><small>Report</small></div>
                            </a>
                        </logic:equal>
                    </td>
                    <td class="action-samesize">
                        <logic:equal name="inspectionSecurityValue"
                                     property="isRead"
                                     value="true">
                            <a class='btn btn-small action-samesize'
                               href="<bean:write name='inspectionListValue' property='inspectionFormUrl' filter='false' />">
                                <img src="<html:rewrite module="/" page="/img/icons/sehinspectform.png" />"
                                     alt="Inspection Form (PDF)"
                                     title="Inspection Form (PDF)" />
                                <div style="line-height: 10px;"><small>Form</small></div>
                            </a>
                        </logic:equal>
                    </td>
                    <logic:present name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>"
                                   scope="session">
                        <logic:iterate id="inspectionListItem"
                                       name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>">
                            <logic:equal name="inspectionListItem"
                                         property="key"
                                         value="INSPECTION_DATE">
                                <td>
                                    <logic:equal name="inspectionSecurityValue"
                                                 property="isRead"
                                                 value="true">
                                        <html:link action="/inspectionviewpage.do"
                                                   paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                                                   paramName="inspectionListValue"
                                                   paramProperty="inspectionId"
                                                   title="View Inspection">
                                            <bean:write name="inspectionListValue"
                                                        property="inspectionDateString" />
                                        </html:link>
                                    </logic:equal>
                                    <logic:notEqual name="inspectionSecurityValue"
                                                    property="isRead"
                                                    value="true">
                                        <bean:write name="inspectionListValue"
                                                    property="inspectionDateString" />
                                    </logic:notEqual>
                                </td>
                            </logic:equal>
                            <logic:notEqual name="inspectionListItem"
                                            property="key"
                                            value="<%= InspectionListItem.INSPECTION_DATE.getKey() %>">
                                <td>
                                    <logic:notEmpty name="inspectionListItem"
                                                    property="image">
                                        <img alt=""
                                             src="<bean:write name='inspectionListValue' property='<%= ((InspectionListItem) pageContext.getAttribute("inspectionListItem")).getImage() %>'/>" />
                                    </logic:notEmpty>
                                    <bean:write name='inspectionListValue'
                                                property='<%= ((InspectionListItem) pageContext.getAttribute("inspectionListItem")).getProperty() %>' />
                                    <logic:notEmpty name="inspectionListItem"
                                                    property="property2">
                                        <div>
                                            <bean:write name='inspectionListValue'
                                                        property='<%= ((InspectionListItem) pageContext.getAttribute("inspectionListItem")).getProperty2() %>' />
                                        </div>
                                    </logic:notEmpty>
                                </td>
                            </logic:notEqual>
                        </logic:iterate>
                    </logic:present>
                    <logic:notPresent name="<%= SessionKeys.EC_INSPECTION_FIELD_LIST %>"
                                      scope="session">
                        <td>
                            <logic:equal name="inspectionSecurityValue"
                                         property="isRead"
                                         value="true">
                                <html:link action="/inspectionviewpage.do"
                                           paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                                           paramName="inspectionListValue"
                                           paramProperty="inspectionId"
                                           title="View Inspection">
                                    <bean:write name="inspectionListValue"
                                                property="inspectionDateString" />
                                </html:link>
                            </logic:equal>
                            <logic:notEqual name="inspectionSecurityValue"
                                            property="isRead"
                                            value="true">
                                <bean:write name="inspectionListValue"
                                            property="name" />
                            </logic:notEqual>
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
