<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ include file="eventPageHeader.jsp" %>
<html:form styleClass="form-horizontal"
           method="POST"
           action="/eventCreateAction"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <html:hidden styleId="nextPage"
                 property="nextPage" />
    <div class="control-group">
        <label class="control-label">
            <bean:message key="event.type.description" /> *
        </label>
        <div class="controls">
            <html:select styleId="eventType"
                         property="eventType"
                         onchange="return eventTypeOnChange();">
                <html:option value="0">Select an Event Type...</html:option>
                <html:options collection="<%=SessionKeys.EC_EVENT_TYPE_LIST%>"
                              property="id"
                              labelProperty="name" />
            </html:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="event.date.occurred.description" />
        </label>
        <div class="controls">
            <html:text styleId="eventDateString"
                       property="eventDateString"
                       size="12" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="event.description.description" />
        </label>
        <div class="controls">
            <html:textarea styleId="description"
                           property="description"
                           cols="75"
                           rows="4" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="event.notice.description" />
        </label>
        <div class="controls">
            <html:textarea styleId="notice"
                           property="notice"
                           cols="75"
                           rows="4" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Inspection Compliance Time Limit
        </label>
        <div class="controls controls-row">
            <label class="radio"
                   for="compDate">
                <html:radio styleId="compDate"
                            property="isComplianceByDate"
                            value="true" />
                <bean:message key="event.compliance.date.description" />
            </label>
            <html:text styleId="complianceDateString"
                       property="complianceDateString"
                       size="12"
                       onfocus="toggleDate();" />
            <p class="muted">
                or
            </p>
            <label class="radio"
                   for="compHrs">
                <html:radio styleId="compHrs"
                            property="isComplianceByDate"
                            value="false" />
                <bean:message key="event.compliance.time.description" />
            </label>
            <html:text styleId="complianceHours"
                       property="complianceHours"
                       size="12"
                       onfocus="toggleHours();" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="event.document" />
        </label>
        <div class="controls">
            <div class="fileupload fileupload-new"
                 data-provides="fileupload">
                <div class="input-append">
                    <div class="uneditable-input span3">
                        <i class="icon-file fileupload-exists"></i>
                        <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                        <span class="fileupload-new">Select file</span>
                        <span class="fileupload-exists">Change</span>
                        <input type="file"
                               id="formFile"
                               name="formFile"
                               accept="image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png" />
                    </span>
                    <a href="#"
                       class="btn fileupload-exists"
                       data-dismiss="fileupload">Remove
                    </a>
                </div>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:submit styleClass="btn btn-large"
                             onclick="return Cancel_OnClick();"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             onclick="return Next_OnClick();"
                             value="Next" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <html:javascript formName="eventCreateForm" />
        <script type="text/javascript">
            <!--
            function validateForm(form)
            {
                var theField = document.getElementById("nextPage");
                if (theField.value
                        == "cancel")
                    return true;
                if (!validateEventType())
                {
                    return false;
                }
                return validateEventCreateForm(form);
            }// -->
        </script>
        <script type="text/javascript">
            //<!-- Script Begin
            function toggleDate()
            {
                var radioA = document.getElementById("compDate");
                var compliance = document.getElementById("isComplianceByDate");
                radioA.checked
                        = true;
                compliance.checked
                        = true;
            }
            function toggleHours()
            {
                var radioB = document.getElementById("compHrs");
                var compliance = document.getElementById("isComplianceByDate");
                radioB.checked
                        = true;
                compliance.checked
                        = false;
            }
            function validateEventType()
            {
                var returnStatus = true;
                var errorMsg = "";
                var eventType = document.getElementById("eventType").value;
                var complianceDate = document.getElementById("complianceDateString").value;
                var complianceHours = document.getElementById("complianceHours").value;
                var eventDate = document.getElementById("eventDateString").value;
                if (eventType
                        < 1)
                {
                    errorMsg
                            += "Please select an Event Type\n";
                    returnStatus
                            = false;
                }
                if ((complianceHours.length
                        < 1)
                        && (complianceDate.length
                        < 1))
                {
                    if (eventType
                            != '3')
                    {
                        errorMsg
                                += "Please enter a compliance date or time\n";
                        returnStatus
                                = false;
                    }
                }
                if ((eventDate.length
                        < 1))
                {
                    errorMsg
                            += "Please enter an event date\n";
                    returnStatus
                            = false;
                }
                if (errorMsg.length
                        > 1)
                {
                    $('#dialog').html(errorMsg).dialog('open');
                }
                return returnStatus;
            }
            function eventTypeOnChange()
            {
                var type = document.getElementById('eventType');
                if (type.options[type.selectedIndex].value
                        == '3')
                {
                    // don't display compliance info
                    document.getElementById('complianceDateString').readOnly
                            = true;
                    document.getElementById('complianceHours').readOnly
                            = true;
                }
                else
                {
                    // display compliance info.
                    document.getElementById('eventDateString').readOnly
                            = false;
                    document.getElementById('complianceHours').readOnly
                            = false;
                }
            }//  Script End -->
        </script>
        <script type="text/javascript">
            <!--
            $(function ()
              {
                  $("#eventDateString").datepicker({autoclose:true});
                  $("#complianceDateString").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
