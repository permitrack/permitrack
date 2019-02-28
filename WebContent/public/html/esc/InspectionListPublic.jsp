<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.common.util.StringUtil,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.value.inspection.InspectionMapValue,
                 com.sehinc.erosioncontrol.value.project.ProjectContactValue,
                 com.sehinc.erosioncontrol.value.project.ProjectDocumentValue,
                 java.util.Iterator,
                 java.util.List" %>
<jsp:useBean id="clientData"
             scope="request"
             class="com.sehinc.common.db.client.ClientData" />
<jsp:useBean id="ecProjectValue"
             scope="request"
             class="com.sehinc.erosioncontrol.value.project.ProjectValue" />
<tiles:insert page="/html/layout.jsp">
    <tiles:put name="title">
        Inspections | PermiTrack
    </tiles:put>
    <tiles:put name="portal-bar"
               type="string">
        <tiles:insert page="/html/portal-bar.jsp"
                      flush="false">
            <tiles:put name="portalMenu"
                       type="string"
                       value="" />
        </tiles:insert>
    </tiles:put>
    <tiles:put name="layout"
               direct="true">
        <div class="container">
            <h1>
                <logic:notEmpty name="<%= RequestKeys.CLIENT_LOGO_LOCATION %>"
                                scope="request">
                    <img alt=""
                         style="max-width: 100px;"
                         src="<%= (String) request.getAttribute(RequestKeys.CLIENT_LOGO_LOCATION) %>" />
                </logic:notEmpty>
                <jsp:getProperty name="ecProjectValue"
                                 property="name" />
                <small>
                    <bean:write name="clientData"
                                property="name"
                                filter="true" />
                </small>
                <a class="btn btn-mini btn-success pull-right"
                   href='<jsp:getProperty name="ecProjectValue" property="commentURL" />'
                   title="Submit a comment to the project manager">
                        <%--onclick="window.open('<jsp:getProperty name="ecProjectValue" property="commentURL" />',null, 'height=650,width=800,scrollbars=yes')"--%>
                    Send Comments
                </a>
            </h1>
            <p class="lead">
                <bean:write name="ecProjectValue"
                            property="description"
                            filter="true" />
            </p>
            <h4 class="myAccordian">
                Project Info
            </h4>
            <dl class="dl-horizontal">
                <dt>
                    Project
                </dt>
                <dd>
                    <jsp:getProperty name="ecProjectValue"
                                     property="name" />
                </dd>
                <dt>Parcel #
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="parcelNumber" />
                </dd>
                <dt>Address
                </dt>
                <dd>
                    <jsp:getProperty name="ecProjectValue"
                                     property="viewAddress" />
                </dd>
                <dt>Total Site Area
                </dt>
                <dd><logic:notEqual name="ecProjectValue"
                                    property="totalSiteArea"
                                    value="0">
                    <bean:write name="ecProjectValue"
                                property="totalSiteArea"
                                format="#.##" />
                    <bean:write name="ecProjectValue"
                                property="totalSiteAreaUnits" />
                </logic:notEqual>
                </dd>
                <dt>Disturbed Area
                </dt>
                <dd><logic:notEqual name="ecProjectValue"
                                    property="disturbedArea"
                                    value="0">
                    <bean:write name="ecProjectValue"
                                property="disturbedArea"
                                format="#.##" />
                    <bean:write name="ecProjectValue"
                                property="disturbedAreaUnits" />
                </logic:notEqual>
                </dd>
                <dt>New Impervious Area
                </dt>
                <dd><logic:notEqual name="ecProjectValue"
                                    property="newImperviousArea"
                                    value="0">
                    <bean:write name="ecProjectValue"
                                property="newImperviousArea"
                                format="#.##" />
                    <bean:write name="ecProjectValue"
                                property="newImperviousAreaUnits" />
                </logic:notEqual>
                </dd>
            </dl>
            <h4 class="myAccordian">
                Permit Info
            </h4>
            <dl class="dl-horizontal">
                <dt>Permit #
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="permitNumber" />
                </dd>
                <dt>Permit Authority
                </dt>
                <dd>
                    <table>
                        <tr>
                            <td>
                                <bean:write name="ecProjectValue"
                                            property="permitAuthorityClientName" />
                            </td>
                        </tr>
                        <logic:notEmpty name="ecProjectValue"
                                        property="permitAuthorityClientContact">
                            <bean:define id="permitAuthorityContact"
                                         name="ecProjectValue"
                                         property="permitAuthorityClientContact" />
                            <tr>
                                <td>
                                    <bean:write name="permitAuthorityContact"
                                                property="firstName" />
                                    <bean:write name="permitAuthorityContact"
                                                property="lastName" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permitAuthorityContact"
                                                property="viewAddress" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permitAuthorityContact"
                                                property="primaryPhone" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permitAuthorityContact"
                                                property="email" />
                                </td>
                            </tr>
                        </logic:notEmpty>
                    </table>
                </dd>
                <dt>
                    Permittee
                </dt>
                <dd>
                    <table>
                        <tr>
                            <td>
                                <bean:write name="ecProjectValue"
                                            property="permittedClientName" />
                            </td>
                        </tr>
                        <logic:notEmpty name="ecProjectValue"
                                        property="permittedClientContact">
                            <bean:define id="permittedContact"
                                         name="ecProjectValue"
                                         property="permittedClientContact" />
                            <tr>
                                <td>
                                    <bean:write name="permittedContact"
                                                property="firstName" /><bean:write name="permittedContact"
                                                                                   property="lastName" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permittedContact"
                                                property="viewAddress" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permittedContact"
                                                property="primaryPhone" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <bean:write name="permittedContact"
                                                property="email" />
                                </td>
                            </tr>
                        </logic:notEmpty>
                    </table>
                </dd>
                <dt>Effective Date
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="effectiveDateString" />
                </dd>
                <dt>Start Date
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="startDateString" />
                </dd>
                <dt>Seed Date
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="seedDateString" />
                </dd>
            </dl>
            <table class="table table-hover">
                <caption class="label">
                    Project Contacts
                </caption>
                <thead>
                    <tr>
                        <th>
                            Contact Type
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Contact Info
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr> <%
                                            Iterator
                                                    projectContactList =
                                                    ((List) request.getAttribute(RequestKeys.EC_PROJECT_CONTACT_LIST)).iterator();
                                            if (projectContactList.hasNext())
                                            {
                                                while (projectContactList.hasNext())
                                                {
                                                    ProjectContactValue
                                                            projectContactValue =
                                                            (ProjectContactValue) projectContactList.next(); %>
                    <tr>
                        <td>
                            <%= StringUtil.filterHTML(projectContactValue.getContactTypeName()) %></td>
                        <td>
                            <%= StringUtil.filterHTML(projectContactValue.getFirstName()) %>
                            &nbsp;
                            <%= StringUtil.filterHTML(projectContactValue.getLastName()) %>
                        </td>
                        <td>
                            <div>
                                <%= StringUtil.filterHTML(projectContactValue.getPrimaryPhone()) %>
                            </div>
                            <div>
                                <a href="mailto:<%= StringUtil.filterHTML(projectContactValue.getEmail()) %>">
                                    <%= StringUtil.filterHTML(projectContactValue.getEmail()) %>
                                </a>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    }
                    else
                    {
                    %>
                    <tr>
                        <td colspan="3">
                            There are no contacts for this project
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <table class="table table-hover">
                <caption class="label">
                    Project Documents
                </caption>
                <thead>
                    <tr>
                        <th>
                            Document
                        </th>
                        <th>
                            Comment
                        </th>
                    </tr>
                </thead>
                <tbody>
                <%
                    Iterator
                            projectDocumentListIterator =
                            ((List) request.getAttribute(RequestKeys.EC_PROJECT_DOCUMENT_LIST)).iterator();
                    if (projectDocumentListIterator.hasNext())
                    {
                        while (projectDocumentListIterator.hasNext())
                        {
                            ProjectDocumentValue
                                    projectDocumentValue =
                                    (ProjectDocumentValue) projectDocumentListIterator.next();%>
                    <tr>
                        <td>
                            <a href='<%= projectDocumentValue.getPublicDownloadURL(request) %>'>
                                <%--onclick="window.open('<%= projectDocumentValue.getPublicDownloadURL(request) %>')"--%>
                                <%= StringUtil.filterHTML(projectDocumentValue.getName()) %>
                            </a>
                        </td>
                        <td>
                            <%= StringUtil.filterHTML(projectDocumentValue.getComment()) %>
                        </td>
                    </tr>
                    <%
                        }
                    }
                    else
                    {
                    %>
                    <tr>
                        <td colspan="2">
                            There are no Documents for this project
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <table class="table table-hover">
                <caption class="label">
                    Inspections
                </caption>
                <thead>
                    <tr>
                        <th>
                            Inspection Date
                        </th>
                        <th>
                            Entered Date
                        </th>
                        <th>
                            Inspector
                        </th>
                        <th>
                            Reason
                        </th>
                    </tr>
                </thead>
                <%
                    Iterator
                            inspectionMapValueListIterator =
                            ((List) request.getAttribute(RequestKeys.EC_INSPECTION_MAP_VIEW_LIST)).iterator();
                    if (inspectionMapValueListIterator.hasNext())
                    {
                        while (inspectionMapValueListIterator.hasNext())
                        {
                            InspectionMapValue
                                    inspectionMapValue =
                                    (InspectionMapValue) inspectionMapValueListIterator.next(); %>
                <tr>
                    <td>
                        <a href='<%= inspectionMapValue.getUrl() %>'>
                            <%--onclick="window.open('<%= inspectionMapValue.getUrl() %>')"--%>
                            <%= inspectionMapValue.getInspectionDateString() %>
                        </a>
                    </td>
                    <td>
                        <%= StringUtil.escapeSingleQuote(inspectionMapValue.getEnteredDateString()) %>
                    </td>
                    <td>
                                <%= StringUtil.filterHTML(inspectionMapValue.getInspector()
                                                                  .getFirstName()) %>
                        &nbsp;
                        <%= StringUtil.filterHTML(inspectionMapValue.getInspector()
                                                          .getLastName()) %>
                    </td>
                    <td>
                        <%= StringUtil.filterHTML(inspectionMapValue.getInspectionReason()) %>
                    </td>
                </tr>
                <%
                    }
                }
                else
                {
                %>
                <tr>
                    <td colspan="4">
                        There are no Inspections for this project.
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        <tiles:definition id="scripts"
                          scope="request">
            <tiles:put name="scripts"
                       type="string"
                       direct="true">
            </tiles:put>
        </tiles:definition>

    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
