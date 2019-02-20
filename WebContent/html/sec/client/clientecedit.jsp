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
<html:form styleClass="form-horizontal"
           action="/clienteceditaction">
    <fieldset><legend>
        Client Settings for ESC
    </legend></fieldset>
    <h4 class="myAccordian">
        Client Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">Client Type
            </label>
            <div class="controls"><logic:equal name='clientEcForm'
                                               property='blnSystemAdmin'
                                               value='true'>
                <html:select name="clientEcForm"
                             property="clientTypeId"
                             styleId="clientTypeId">
                    <html:options collection="<%=SessionKeys.CLIENT_EC_FORM_CLIENT_TYPE_LIST%>"
                                  property="id"
                                  labelProperty="name" />
                </html:select>
            </logic:equal>
                <logic:notEqual name='clientEcForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <bean:write name='clientEcForm'
                                property='clientTypeName' />
                    <html:hidden name='clientEcForm'
                                 property='clientTypeId'
                                 styleId='clientTypeId' />
                </logic:notEqual>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Public Comment Email Address
            </label>
            <div class="controls"><html:text styleId="publicCommentEMail"
                                             name="clientEcForm"
                                             property="publicCommentEMail"
                                             size="89"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">General Reply To Email Address
            </label>
            <div class="controls"><html:text styleId="generalReplyToEMail"
                                             name="clientEcForm"
                                             property="generalReplyToEMail"
                                             size="89"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Public Access URL
            </label>
            <div class="controls">
                <a href="<%= strPublicReportURL %>">
                    <%= strPublicReportURL %>
                </a>
                <html:hidden styleId="id"
                             name="clientEcForm"
                             property="id" />
                <html:hidden styleId="clientId"
                             name="clientEcForm"
                             property="clientId" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Private Access URL
            </label>
            <div class="controls">
                <a href="<%= strPublicReportURL %>">
                    <%= strPrivateReportURL %>
                </a>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Email Settings
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Project Email Settings
            </label>
            <div class="controls">
                <label class="checkbox">
                    <html:checkbox property="projectStatusEmailsEnabled"
                                   name="clientEcForm" />
                    Enable Automatic Project Status Change Notifications
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Inspection Report Settings
            </label>
            <div class="controls controls-row">
                <label class="checkbox">
                    <html:checkbox property="inspectionCertificationEnabled"
                                   name="clientEcForm" />
                    Enable the following Inspection Certification message on Inspection Reports
                </label>
                <label>
                    Inspection Certification Message
                </label>
                <html:textarea styleId='inspectionCertificationMessage'
                               name='clientEcForm'
                               property='inspectionCertificationMessage'
                               rows='4'
                               cols='60' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Inspection Email Settings
            </label>
            <div class="controls controls-row">
                <div>
                    <label class="checkbox">
                        <html:checkbox property="inspectionOverdueNotificationEnabled"
                                       styleId="inspectionOverdueNotificationEnabled"
                                       name="clientEcForm" />
                        Enable Inspection Overdue Notifications
                    </label>
                    <label>
                        Send <strong>Initial</strong> Inspection Overdue Notification at
                    </label>
                    <html:text name='clientEcForm'
                               property='inspectionOverdueInitialThreshold'
                               styleId='inspectionOverdueInitialThreshold'
                               size="5" />
                    days since last inspection.
                    <br/>
                    <p>
                        <label>
                            Inital Inspection Overdue Message
                        </label>
                        <html:textarea name='clientEcForm'
                                       property='inspectionOverdueInitialMessage'
                                       styleId='inspectionOverdueInitialMessage'
                                       rows='4'
                                       cols='60' />
                    </p>
                </div>
                <br/>
                <div>
                    <label class="checkbox">
                        <html:checkbox property="secondInspectionOverdueNotificationEnabled"
                                       styleId="secondInspectionOverdueNotificationEnabled"
                                       name="clientEcForm" />
                        Enable Secondary Inspection Overdue Notifications
                    </label>
                    <label>
                        Send <strong>Secondary</strong> Inspection Overdue Notification at
                    </label>
                    <html:text name='clientEcForm'
                               property='inspectionOverdueSecondThreshold'
                               styleId='inspectionOverdueSecondThreshold'
                               size="5" />
                    <span class="help-inline">
                        days since last inspection.
                    </span>
                    <br/>
                    <p>
                        <label>
                            Secondary Inspection Overdue Message
                        </label>
                        <html:textarea name='clientEcForm'
                                       property='inspectionOverdueSecondMessage'
                                       styleId='inspectionOverdueSecondMessage'
                                       rows='4'
                                       cols='60' />
                    </p>
                </div>
                <br/>
                <div class="alert alert-block">
                    <p>
                        Use the following tags to populate custom data within the automatic notification:
                    </p>
                    <div class="well well-small">
                        $project.name, $project.lastInspectionDate, $inspection.inspector, $project.days_since_last_inspection
                    </div>
                </div>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Update Settings"
                             onclick="return validateRequired();" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            //<!-- Script Begin

            var notificationInitialCheckbox = $("#inspectionOverdueNotificationEnabled");
            var notificationSecondaryCheckbox = $("#secondInspectionOverdueNotificationEnabled");

            function updateNotificationInitial()
            {
                if(notificationInitialCheckbox.is(":checked"))
                {
                    $("#inspectionOverdueInitialThreshold").removeProp("disabled");
                    $("#inspectionOverdueInitialMessage").removeProp("disabled");
                    $("#secondInspectionOverdueNotificationEnabled").removeProp("disabled");
                }
                else
                {
                    $("#inspectionOverdueInitialThreshold").attr("disabled", "disabled");
                    $("#inspectionOverdueInitialMessage").attr("disabled", "disabled");
                    $("#secondInspectionOverdueNotificationEnabled").attr("disabled", "disabled");
                }

                updateNotificationSecondary();
            }

            function updateNotificationSecondary()
            {
                if(notificationSecondaryCheckbox.is(":checked"))
                {
                    $("#inspectionOverdueSecondThreshold").removeProp("disabled");
                    $("#inspectionOverdueSecondMessage").removeProp("disabled");
                }

                if(!notificationSecondaryCheckbox.is(":checked") || !notificationInitialCheckbox.is(":checked") )
                {
                    $("#inspectionOverdueSecondThreshold").attr("disabled", "disabled");
                    $("#inspectionOverdueSecondMessage").attr("disabled", "disabled");
                }
            }

            $(function ()
            {
                notificationInitialCheckbox.change(updateNotificationInitial);
                notificationSecondaryCheckbox.change(updateNotificationSecondary);

                updateNotificationInitial();
            });

            function validateRequired()
            {
                var blnRequiredFields = false;
                var strMessage = "";
                var txtPublicCommentEMail = document.getElementById("publicCommentEMail");
                var txtGeneralReplyToEMail = document.getElementById("generalReplyToEMail");
                var cboClientTypeId = document.getElementById("clientTypeId");
                var txtCertMsg = document.getElementById("inspectionCertificationMessage");
                if (txtPublicCommentEMail.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Public Comment Email is required, ");
                }
                if (txtGeneralReplyToEMail.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("General Reply To Email is required, ");
                }
                if (cboClientTypeId.value
                        == 0)
                {
                    strMessage
                            = strMessage.concat("Client Type is required, ");
                }
                if (txtCertMsg.value.length
                        > 1000)
                {
                    strMessage
                            = strMessage.concat("Certification Message can be a maximum of 1000 characters, ");
                }
                if (strMessage.length
                        > 0)
                {
                    // Trim the last comma from the message string
                    strMessage
                            = strMessage.substr(0,
                                                strMessage.length
                                                        - 2)
                            + ".";
                    $('#dialog').html("Please enter required fields. "
                                              + strMessage).dialog('open');
                }
                else
                {
                    blnRequiredFields
                            = true;
                }
                return blnRequiredFields;
            }//  Script End -->
        </script>
    </tiles:put>
</tiles:definition>
