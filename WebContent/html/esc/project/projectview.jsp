<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<fieldset>
    <legend>
        <bean:write name="projectForm"
                        property="name" />
    </legend>
</fieldset>
<h4 class="myAccordian">
    <bean:message key="project.permit.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="project.permit.number" />
    </dt>
    <dd>
        <bean:write name="projectForm"
                    property="permitNumber" />
    </dd>
    <dt><bean:message key="project.permit.issuing.agency" />
    </dt>
    <dd>
        <table>
            <tr>
                <td>
                    <bean:write name="projectForm"
                                property="permitAuthorityClientName" />
                </td>
            </tr>
            <logic:notEmpty name="projectForm"
                            property="permitAuthorityClientContact">
                <bean:define id="permitAuthorityContact"
                             name="projectForm"
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
                        <a href="mailto:<bean:write name='permitAuthorityContact' property='email' />">
                            <bean:write name="permitAuthorityContact"
                                        property="email" />
                        </a>
                    </td>
                </tr>
            </logic:notEmpty>
        </table>
    </dd>
    <dt><bean:message key="project.permit.permitted" />
    </dt>
    <dd>
        <table>
            <tr>
                <td>
                    <bean:write name="projectForm"
                                property="permittedClientName" />
                </td>
            </tr>
            <logic:notEmpty name="projectForm"
                            property="permittedClientContact">
                <bean:define id="permittedContact"
                             name="projectForm"
                             property="permittedClientContact" />
                <tr>
                    <td>
                        <bean:write name="permittedContact"
                                    property="firstName" />
                        <bean:write name="permittedContact"
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
                        <a href="mailto:<bean:write name='permittedContact' property='email' />">
                            <bean:write name="permittedContact"
                                        property="email" />
                        </a>
                    </td>
                </tr>
            </logic:notEmpty>
        </table>
    </dd>
    <dt><bean:message key="project.permit.authorized.inspector" />
    </dt>
    <dd>
        <table>
            <tr>
                <td>
                    <bean:write name="projectForm"
                                property="authorizedInspectorClientName" />
                </td>
            </tr>
            <logic:notEmpty name="projectForm"
                            property="authorizedInspectorClientContact">
                <bean:define id="authorizedInspectorContact"
                             name="projectForm"
                             property="authorizedInspectorClientContact" />
                <logic:notEmpty name="authorizedInspectorContact"
                                property="id">
                    <tr>
                        <td>
                            <bean:write name="authorizedInspectorContact"
                                        property="firstName" />
                            <bean:write name="authorizedInspectorContact"
                                        property="lastName" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <bean:write name="authorizedInspectorContact"
                                        property="viewAddress" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <bean:write name="authorizedInspectorContact"
                                        property="primaryPhone" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="mailto:<bean:write name='authorizedInspectorContact' property='email' />">
                                <bean:write name="authorizedInspectorContact"
                                            property="email" />
                            </a>
                        </td>
                    </tr>
                </logic:notEmpty>
            </logic:notEmpty>
        </table>
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.contact.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        Contacts
    </dt>
    <dd>
        <logic:empty name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                     scope="session">
            <p class="text-warning">
                <bean:message key="project.contacts.empty" />
            </p>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                        scope="session">
            <logic:iterate id="projectContact"
                           name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                           scope="session">
                <div class="well well-small">
                    <span class="label">
                        <bean:write name='projectContact'
                                    property='contactTypeName' />
                    </span>
                    <p>
                        <bean:write name='projectContact'
                                    property='organizationName' />
                    </p>
                    <p>
                        <bean:write name='projectContact'
                                    property='viewAddress' />
                    </p>
                    <p>
                        <bean:write name='projectContact'
                                    property='firstName' />
                        <bean:write name='projectContact'
                                    property='lastName' />
                    </p>
                    <p>
                        <bean:write name='projectContact'
                                    property='primaryPhone' />
                    </p>
                    <a href="mailto:<bean:write name='projectContact' property='email' />">
                        <bean:write name='projectContact'
                                    property='email' />
                    </a>
                </div>
            </logic:iterate>
        </logic:notEmpty>
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.information.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="project.name" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="name" />
    </dd>
    <dt><bean:message key="project.status" />
    </dt>
    <dd><span class="label"><bean:write name="projectForm"
                    property="projectStatusCodeDesc" /></span>
    </dd>
    <dt><bean:message key="project.completed.date" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="completedDate"
                    format="MM/dd/yyyy" />
    </dd>
    <dt><bean:message key="project.type" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="projectTypeName" />
    </dd>
    <dt><bean:message key="project.zone" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="zoneName" />
    </dd>
    <dt><bean:message key="project.rfaNumber" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="rfaNumber" />
    </dd>
    <dt><bean:message key="project.effective.date" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="effectiveDateString" />
    </dd>
    <dt><bean:message key="project.start.date" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="startDateString" />
    </dd>
    <dt><bean:message key="project.seed.date" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="seedDateString" />
    </dd>
    <dt><bean:message key="project.description" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="viewDescription"
                    filter="false" />
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.inspection.information.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="project.inspection.frequency" />
    </dt>
    <dd>
        <bean:write name="projectForm"
                    property="inspectionFrequency"
                    filter="false" />
    </dd>
    <dt>
        Inspection Email Settings
    </dt>
    <dd>
        <div>
            <label class="checkbox">
                <html:checkbox property="inspectionOverdueNotificationEnabled"
                               name="projectForm"
                               disabled="true" />
                Enable Inspection Overdue Notifications
            </label>
            <label>
                Send Initial Inspection Overdue Notification at
            </label>
            <code>
                <bean:write name='projectForm'
                            property='inspectionOverdueInitialThreshold' /> days since last inspection.
            </code>
            <label>
                Inital Inspection Overdue Message
            </label>
            <code>
                <bean:write name='projectForm'
                            property='inspectionOverdueInitialMessage' />
                &nbsp;
            </code>
        </div>
        <div>
            <label class="checkbox">
                <html:checkbox property="secondInspectionOverdueNotificationEnabled"
                               name="projectForm"
                               disabled="true" />
                Enable Secondary Inspection Overdue Notifications
            </label>
            <label>
                Send Secondary Inspection Overdue Notification at
            </label>
            <code>
                <bean:write name='projectForm'
                            property='inspectionOverdueSecondThreshold' /> days since last inspection.
            </code>
            <label>
                Secondary Inspection Overdue Message
            </label>
            <code>
                <bean:write name='projectForm'
                            property='inspectionOverdueSecondMessage' />
                &nbsp;
            </code>
        </div>
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.site.information.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="project.address" />
    </dt>
    <dd>
        <logic:present name="<%=SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL%>">
            <a title="View map"
               href='<bean:write name="<%=SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL%>" />'>
        </logic:present>
                <bean:write name="projectForm"
                            property="viewAddress" />
        <logic:present name="<%=SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL%>">
            </a>
        </logic:present>
    </dd>
    <dt><bean:message key="project.parcelNumber" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="parcelNumber" />
    </dd>
    <dt><bean:message key="project.blockNumber" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="blockNumber" />
    </dd>
    <dt><bean:message key="project.lotNumber" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="lotNumber" />
    </dd>
    <dt><bean:message key="project.gisx" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="gisX" />
    </dd>
    <dt><bean:message key="project.gisy" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="gisY" />
    </dd>
    <dt><bean:message key="project.total.site.area" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="totalSiteArea" />
        <bean:write name="projectForm"
                    property="totalSiteAreaUnits" />
    </dd>
    <dt><bean:message key="project.disturbed.area" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="disturbedArea" />
        <bean:write name="projectForm"
                    property="disturbedAreaUnits" />
    </dd>
    <dt><bean:message key="project.new.impervious.area" />
    </dt>
    <dd><bean:write name="projectForm"
                    property="newImperviousArea" />
        <bean:write name="projectForm"
                    property="newImperviousAreaUnits" />
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.document.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        Attachment(s)
    </dt>
    <dd>
        <logic:empty name="<%= SessionKeys.EC_PROJECT_DOCUMENT_LIST %>"
                     scope="session">
            <a class="text-warning">
                <bean:message key="project.documents.empty" /></a>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_DOCUMENT_LIST %>"
                        scope="session">
            <logic:iterate id="projectDocument"
                           name="<%= SessionKeys.EC_PROJECT_DOCUMENT_LIST %>"
                           scope="session">
                <div class="media">
                    <a class="pull-left"
                       href="<bean:write name='projectDocument' property='downloadURL'/>">
                        <img class="media-object"
                             data-src="holder.js/64x64"
                             src="<bean:write name='projectDocument' property='downloadURL'/>&size=small"
                             style="max-width: 64px; max-height: 64px;">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">
                            <a href="<bean:write name='projectDocument' property='downloadURL'/>">
                                <bean:write name='projectDocument'
                                            property='name' />
                            </a>
                        </h4>
                        <bean:write name='projectDocument'
                                    property='comment' />
                    </div>
                </div>
            </logic:iterate>
        </logic:notEmpty>
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="project.bmp.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        Best Management Practices
    </dt>
    <dd>
        <logic:empty name="<%= SessionKeys.EC_PROJECT_BMP_LIST %>"
                     scope="session">
            <p class="text-warning">
                <bean:message key="project.bmps.empty" />
            </p>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_BMP_LIST %>"
                        scope="session">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Required</th>
                        <th>Category</th>
                        <th>BMP</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate id="projectBmp"
                                   name="<%= SessionKeys.EC_PROJECT_BMP_LIST %>"
                                   scope="session">
                        <tr>
                            <td>
                                <logic:equal name="projectBmp"
                                             property="isRequired"
                                             value="true">
                                    <span class="label label-warning">
                                        Yes
                                    </span>
                                </logic:equal>
                                <logic:notEqual name="projectBmp"
                                                property="isRequired"
                                                value="true">
                                    <span class="label">
                                        No
                                    </span>
                                </logic:notEqual>
                            </td>
                            <td>
                                <bean:write name='projectBmp'
                                            property='bmpCategoryName' />
                            </td>
                            <td>
                                    <%--
                                                                    <html:link module="/html/esc/bmp"
                                                                               action="bmpviewpage.do"
                                                                               paramName="projectBmp"
                                                                               paramProperty="projectBmpId"
                                                                               paramId="ecBmpId">
                                    --%>
                                <bean:write name='projectBmp'
                                            property='bmpName' />
                                    <%--</html:link>--%>
                            </td>
                            <td>
                                <bean:write name='projectBmp'
                                            property='bmpDescription' />
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:notEmpty>
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
