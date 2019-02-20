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
<%@ page import="com.sehinc.security.action.base.SessionKeys" %>
<%
    String
            strPublicReportURL =
            (String) request.getSession()
                    .getAttribute(SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL);
    String
            strPrivateReportURL =
            (String) request.getSession()
                    .getAttribute(SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL);%>
<fieldset><legend>
    ESC Settings
</legend></fieldset>
<h4 class="myAccordian">
    Client Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Public Access URL
    </dt>
    <dd>
        <a href="<%= strPublicReportURL %>">
            <%= strPublicReportURL %>
        </a>
    </dd>
    <dt>
        Private Access URL
    </dt>
    <dd>
        <a href="<%= strPrivateReportURL %>">
            <%= strPrivateReportURL %>
        </a>
    </dd>
    <dt>
        Client Type
    </dt>
    <dd>
        <bean:write name='clientEcForm'
                    property='clientTypeName' />
    </dd>
    <dt>
        Public Comment Email Address
    </dt>
    <dd>
        <a href="mailto:<bean:write name='clientEcForm' property='publicCommentEMail'/>">
            <bean:write name='clientEcForm'
                        property='publicCommentEMail' />
        </a>
    </dd>
    <dt>
        General Reply To Email Address
    </dt>
    <dd>
        <a href="mailto:<bean:write name='clientEcForm' property='generalReplyToEMail'/>">
            <bean:write name='clientEcForm'
                        property='generalReplyToEMail' />
        </a>
    </dd>
</dl>
<h4 class="myAccordian">
    Email Settings
</h4>
<dl class="dl-horizontal">
    <dt>
        Project Email Settings
    </dt>
    <dd>
        <label class="checkbox">
            <html:checkbox property="projectStatusEmailsEnabled"
                           name="clientEcForm"
                           disabled="true" />
            Enable Automatic Project Status Change Notifications
        </label>
    </dd>
    <dt>
        Inspection Report Settings
    </dt>
    <dd>
        <label class="checkbox">
            <html:checkbox property="inspectionCertificationEnabled"
                           name="clientEcForm"
                           disabled="true" />
            Enable the following Inspection Certification message on Inspection Reports
        </label>
        <label>
            Inspection Certification Message
        </label>
        <code>
            <bean:write name='clientEcForm'
                        property='inspectionCertificationMessage' />
            &nbsp;
        </code>
    </dd>
    <dt>
        Inspection Email Settings
    </dt>
    <dd>
        <div>
            <label class="checkbox">
                <html:checkbox property="inspectionOverdueNotificationEnabled"
                               name="clientEcForm"
                               disabled="true" />
                Enable Inspection Overdue Notifications
            </label>
            <label>
                Send Initial Inspection Overdue Notification at
            </label>
            <code>
                <bean:write name='clientEcForm'
                            property='inspectionOverdueInitialThreshold' /> days since last inspection.
            </code>
            <label>
                Inital Inspection Overdue Message
            </label>
            <code>
                <bean:write name='clientEcForm'
                            property='inspectionOverdueInitialMessage' />
                &nbsp;
            </code>
        </div>
        <div>
            <label class="checkbox">
                <html:checkbox property="secondInspectionOverdueNotificationEnabled"
                               name="clientEcForm"
                               disabled="true" />
                Enable Secondary Inspection Overdue Notifications
            </label>
            <label>
                Send Secondary Inspection Overdue Notification at
            </label>
            <code>
                <bean:write name='clientEcForm'
                            property='inspectionOverdueSecondThreshold' /> days since last inspection.
            </code>
            <label>
                Secondary Inspection Overdue Message
            </label>
            <code>
                <bean:write name='clientEcForm'
                            property='inspectionOverdueSecondMessage' />
                &nbsp;
            </code>
        </div>
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
