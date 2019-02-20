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
                 com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue,
                 com.sehinc.erosioncontrol.value.project.ProjectContactValue,
                 java.util.Iterator,
                 java.util.List" %>
<jsp:useBean id="clientData"
             scope="request"
             class="com.sehinc.common.db.client.ClientData" />
<jsp:useBean id="ecProjectValue"
             scope="request"
             class="com.sehinc.erosioncontrol.value.project.ProjectValue" />
<jsp:useBean id="ecInspectionValue"
             scope="request"
             class="com.sehinc.erosioncontrol.value.inspection.InspectionValue" />
<tiles:insert page="/html/layout.jsp">
    <tiles:put name="title">
        Inspection Report | PermiTrack
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
            <logic:notEmpty name="<%= RequestKeys.CLIENT_LOGO_LOCATION %>"
                            scope="request">
                <img alt=""
                     style="max-width: 100px;"
                     src="<%= (String) request.getAttribute(RequestKeys.CLIENT_LOGO_LOCATION) %>" />
            </logic:notEmpty>
            <fieldset>
                <legend>
                    <jsp:getProperty name="ecProjectValue"
                                     property="name" />
                    Inspection on
                    <bean:write name="ecInspectionValue"
                                property="inspectionDateString" />
                    <small>
                        <bean:write name="clientData"
                                    property="name"
                                    filter="true" />
                    </small>
                    <a class="btn btn-mini btn-success pull-right"
                       href='<jsp:getProperty name="ecProjectValue" property="commentURL" />'
                       title="Send Comments to the Project Manager">
                            <%--onclick="window.open('<jsp:getProperty name="ecProjectValue" property="commentURL" />',null, 'height=650,width=800,scrollbars=yes')"--%>
                        Send Comments
                    </a>
                </legend>
            </fieldset>
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
            </dl>
            <h4 class="myAccordian">
                Project Contacts
            </h4>
            <dl class="dl-horizontal">
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
                                    &nbsp;
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
                <dt>Permittee
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
                                                property="firstName" />&nbsp;<bean:write name="permittedContact"
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
                <logic:notEmpty name="ecProjectValue"
                                property="authorizedInspectorClientName">
                    <dt>Authorized Inspector</dt>
                    <dd>
                        <table>
                            <tr>
                                <td>
                                    <bean:write name="ecProjectValue"
                                                property="authorizedInspectorClientName" />
                                </td>
                            </tr>
                            <logic:notEmpty name="ecProjectValue"
                                            property="authorizedInspectorClientContact">
                                <bean:define id="authorizedInspectorContact"
                                             name="ecProjectValue"
                                             property="authorizedInspectorClientContact" />
                                <tr>
                                    <td>
                                        <bean:write name="authorizedInspectorContact"
                                                    property="firstName" />&nbsp;<bean:write name="authorizedInspectorContact"
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
                                        <bean:write name="authorizedInspectorContact"
                                                    property="email" />
                                    </td>
                                </tr>
                            </logic:notEmpty>
                        </table>
                    </dd>
                </logic:notEmpty>
                <%
                    Iterator
                            projectContactList =
                            ((List) request.getAttribute(RequestKeys.EC_PROJECT_CONTACT_LIST)).iterator();
                    while (projectContactList.hasNext())
                    {
                        ProjectContactValue
                                projectContactValue =
                                (ProjectContactValue) projectContactList.next();%>
                <dt>
                    <%= projectContactValue.getContactTypeName() %>
                </dt>
                <dd>
                    <table>
                        <tr>
                            <td>
                                <%= StringUtil.filterHTML(projectContactValue.getFirstName()) %>
                                &nbsp;
                                <%= StringUtil.filterHTML(projectContactValue.getLastName()) %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <%= StringUtil.filterHTML(projectContactValue.getViewAddress()) %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <%= StringUtil.filterHTML(projectContactValue.getPrimaryPhone()) %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <%= StringUtil.filterHTML(projectContactValue.getEmail()) %>
                            </td>
                        </tr>
                    </table>
                </dd>
                <%}%>
            </dl>
            <h4 class="myAccordian">
                Inspection Information
            </h4>
            <dl class="dl-horizontal">
                <dt>
                    Inspector
                </dt>
                <dd>
                    <table>
                        <tr>
                            <td>
                                <bean:write name="ecInspectionValue"
                                            property="inspectorNameString"
                                            filter="true" />
                            </td>
                        </tr>
                        <logic:notEmpty name="ecInspectionValue"
                                        property="inspectorPhoneNumber">
                            <tr>
                                <td>
                                    <bean:write name="ecInspectionValue"
                                                property="inspectorPhoneNumber"
                                                filter="true" />
                                </td>
                            </tr>
                        </logic:notEmpty>
                        <logic:notEmpty name="ecInspectionValue"
                                        property="inspectorEmailAddress">
                            <tr>
                                <td>
                                    <bean:write name="ecInspectionValue"
                                                property="inspectorEmailAddress"
                                                filter="true" />
                                </td>
                            </tr>
                        </logic:notEmpty>
                    </table>
                </dd>
                <dt>
                    Reason for Inspection
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="inspectionReasonString"
                                filter="true" />
                </dd>
                <dt>
                    Inspection Date
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="inspectionDateString" />
                </dd>
                <dt>
                    Weather Data Source
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="precipSource"
                                filter="true" />
                </dd>
                <dt>
                    Last Precip. End
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="precipEndDateString" />
                </dd>
                <dt>
                    Last Precip. Amount
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="precipAmount"
                                filter="true" />&nbsp;in.
                </dd>
                <dt>
                    Weather Trends
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="weatherTrends"
                                filter="true" />
                </dd>
                <dt>
                    Temperature
                </dt>
                <dd>
                    <bean:write name="ecInspectionValue"
                                property="temperature"
                                filter="true" />&nbsp;F
                </dd>
            </dl>
            <h4 class="myAccordian">
                Inspection Items
            </h4>
            <div>
            <%
                Iterator
                        inspectionBmpIter =
                        ((List) request.getAttribute(RequestKeys.EC_INSPECTION_BMP_DEFECT_LIST)).iterator();
                if (!inspectionBmpIter.hasNext())
                {%>
                <p class="text-warning">
                    There are no BMP's defined for this inspection.
                </p>
                <%}%>
                <%while (inspectionBmpIter.hasNext())
                {
                    InspectionBmpValue
                            inspectionBmpValue =
                            (InspectionBmpValue) inspectionBmpIter.next();%>
                <div class="media">
                                    <%if (inspectionBmpValue.getBmpDocument()
                                                  .getPublicDownloadImageURL(request)
                                          != null)
                                    {%>
                    <a class="pull-left"
                       href="javascript:void(0)">
                        <img class="media-object"
                             data-src="holder.js/64x64"
                             src="<%= inspectionBmpValue.getBmpDocument().getPublicDownloadImageURL(request) %>&size=small"
                             style="max-width: 64px; max-height: 64px; min-width: 64px;"
                             title="Click to enlarge"
                             alt="Photo"
                             onclick="window.open('<%= inspectionBmpValue.getBmpDocument().getPublicDownloadImageURL(request) %>', null, 'height=625,width=825')" />
                    </a>
                    <%}
                    else
                    {%>
                    <div class="pull-left muted"
                         style='width: 64px; height: 64px; vertical-align: middle; text-align: center;'>&nbsp;</div>
                    <%}%>
                    <div class="media-body">
                        <h4 class="media-heading">
                                            <%= StringUtil.filterHTML(inspectionBmpValue.getBmpCategoryName()
                                                                      + ": "
                                                                      + inspectionBmpValue.getName()) %>
                        </h4>
                        <%= StringUtil.filterHTML(inspectionBmpValue.getDescription()) %>
                        <table class="table table-hover table-condensed">
                            <tr>
                                <td>
                                    Inspected:
                                    &nbsp;
                                    <%= inspectionBmpValue.getIsInspectedYesNoText() %>
                                </td>
                                <td>
                                    Status:
                                    &nbsp;
                                    <%= inspectionBmpValue.getBmpStatus()
                                            .getName() %>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Required:
                                    &nbsp;
                                    <%= inspectionBmpValue.getIsRequiredYesNoText() %>
                                </td>
                                <td>
                                    Condition:
                                    &nbsp;
                                    <%= inspectionBmpValue.getBmpCondition()
                                            .getName() %>
                                </td>
                            </tr>
                            <%if (!StringUtil.isEmpty(inspectionBmpValue.getComment()))
                            {%>
                            <tr>
                                <td colspan="2">
                                    Comment:
                                    &nbsp;
                                    <%= StringUtil.filterHTML(inspectionBmpValue.getComment()) %>
                                </td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                </div>
                <%}%>
            </div>
            <logic:notEqual name="ecInspectionValue"
                            property="inspectionAction.id"
                            value="1">
                <h4 class="myAccordian">
                    Permit Authority
                </h4>
                <dl>
                    <dt>
                        Permit Authority Action
                    </dt>
                    <dd>
                        <bean:write name="ecInspectionValue"
                                    property="inspectionActionString"
                                    filter="true" />
                    </dd>
                    <dt>
                        Comment
                    </dt>
                    <dd>
                        <bean:write name="ecInspectionValue"
                                    property="inspectionActionComment"
                                    filter="true" />
                    </dd>
                </dl>
            </logic:notEqual>
            <tiles:definition id="scripts"
                              scope="request">
                <tiles:put name="scripts"
                           type="string"
                           direct="true">
                    <%-- TODO?
                                        <script type="text/javascript">
                                            //<!--
                                            function ExplodeImage(imgUrl)
                                            {
                                                newWindow
                                                        = window.open('',
                                                                      'newWin',
                                                                      'resizable=yes, width=200, height=200');
                                                newWindow.document.write('<html><head><title>Inspection Image</title></head>'
                                                                                 + '<body><img src='
                                                                                 + imgUrl
                                                                                 + '/></body></html>');
                                                newWindow.document.close()
                                            }//-->
                                        </script>
                    --%>
                </tiles:put>
            </tiles:definition>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
