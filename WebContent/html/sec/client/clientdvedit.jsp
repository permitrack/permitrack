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
<html:form styleClass="form-horizontal"
           action="/clientdveditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Client Settings for DVO
    </legend></fieldset>
    <h4 class="myAccordian">
        Client Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Full Name
            </label>
            <div class="controls"><html:text styleId="clientFullName"
                                             name="clientDvForm"
                                             property="clientFullName"
                                             size="34"
                                             maxlength="26" />
                <html:hidden styleId="clientId"
                             name="clientDvForm"
                             property="clientId" />
                <html:hidden styleId="id"
                             name="clientDvForm"
                             property="id" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Name
            </label>
            <div class="controls"><html:text styleId="clientName"
                                             name="clientDvForm"
                                             property="clientName"
                                             size="34"
                                             maxlength="26" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Services
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                IMS Service
            </label>
            <div class="controls"><html:text styleId="imsService"
                                             name="clientDvForm"
                                             property="imsService"
                                             size="34"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">IMS OV Service
            </label>
            <div class="controls"><html:text styleId="imsOvService"
                                             name="clientDvForm"
                                             property="imsOvService"
                                             size="34"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Download Directory
            </label>
            <div class="controls"><html:text styleId="downLoads"
                                             name="clientDvForm"
                                             property="downLoads"
                                             size="34"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Attachment Layers
            </label>
            <div class="controls"><html:textarea styleId="attachmentLayers"
                                                 name="clientDvForm"
                                                 property="attachmentLayers"
                                                 cols="60"
                                                 rows="2" />
                <div class="mainbodytext">
                    Comma separated list of layer names
                </div>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Start
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Start Top
            </label>
            <div class="controls"><html:text styleId="startTop"
                                             name="clientDvForm"
                                             property="startTop"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Start Left
            </label>
            <div class="controls"><html:text styleId="startLeft"
                                             name="clientDvForm"
                                             property="startLeft"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Start Right
            </label>
            <div class="controls"><html:text styleId="startRight"
                                             name="clientDvForm"
                                             property="startRight"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Start Bottom
            </label>
            <div class="controls"><html:text styleId="startBottom"
                                             name="clientDvForm"
                                             property="startBottom"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Limit
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Limit Top
            </label>
            <div class="controls"><html:text styleId="limitTop"
                                             name="clientDvForm"
                                             property="limitTop"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Limit Left
            </label>
            <div class="controls"><html:text styleId="limitLeft"
                                             name="clientDvForm"
                                             property="limitLeft"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Limit Right
            </label>
            <div class="controls"><html:text styleId="limitRight"
                                             name="clientDvForm"
                                             property="limitRight"
                                             size="15"
                                             maxlength="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Limit Bottom
            </label>
            <div class="controls"><html:text styleId="limitBottom"
                                             name="clientDvForm"
                                             property="limitBottom"
                                             size="15"
                                             maxlength="26" />
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
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="clientDvForm" />
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    if (bCancel) return true;
                    if (RequiredFields())
                    {
                        return validateClientDvForm(form);
                    }
                    else
                    {
                        return false;
                    }
                }
                function RequiredFields()
                {
                    var blnRequiredFields = false;
                    var strMessage = "";
                    var txtClientFullName = document.getElementById("clientFullName");
                    var txtClientName = document.getElementById("clientName");
                    var txtImsService = document.getElementById("imsService");
                    var txtImsOvService = document.getElementById("imsOvService");
                    var txtDownLoads = document.getElementById("downLoads");
                    var txtAttachmentLayers = document.getElementById("attachmentLayers");
                    var txtStartTop = document.getElementById("startTop");
                    var txtStartLeft = document.getElementById("startLeft");
                    var txtStartRight = document.getElementById("startRight");
                    var txtStartBottom = document.getElementById("startBottom");
                    var txtLimitTop = document.getElementById("limitTop");
                    var txtLimitLeft = document.getElementById("limitLeft");
                    var txtLimitRight = document.getElementById("limitRight");
                    var txtLimitBottom = document.getElementById("limitBottom");
                    if (txtClientFullName.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Client Full Name is required, ");
                    }
                    if (txtClientName.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Client Name is required, ");
                    }
                    if (txtImsService.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("IMS Service is required, ");
                    }
                    if (txtImsOvService.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("IMS OV Service is required, ");
                    }
                    if (txtDownLoads.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Download Directory is required, ");
                    }
                    if (txtAttachmentLayers.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Attachment Layers is required, ");
                    }
                    if (txtStartTop.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Start Top is required, ");
                    }
                    if (txtStartLeft.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Start Left is required, ");
                    }
                    if (txtStartRight.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Start Right is required, ");
                    }
                    if (txtStartBottom.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Start Bottom is required, ");
                    }
                    if (txtLimitTop.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Limit Top is required, ");
                    }
                    if (txtLimitLeft.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Limit Left is required, ");
                    }
                    if (txtLimitRight.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Limit Right is required, ");
                    }
                    if (txtLimitBottom.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Limit Bottom is required, ");
                    }
                    // Format the error message
                    if (strMessage.length
                            > 0)
                    {
                        // Trim the last comma from the message string
                        strMessage
                                = strMessage.substr(0,
                                                    strMessage.length
                                                            - 2);
                        $('#dialog').html("Please enter required fields. "
                                                  + strMessage).dialog('open');
                    }
                    else
                    {
                        blnRequiredFields
                                = true;
                    }
                    return blnRequiredFields;
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>