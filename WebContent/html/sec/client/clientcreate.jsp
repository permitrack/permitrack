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
    pageContext.setAttribute("stateList",
                             request.getSession()
                                     .getAttribute(SessionKeys.STATE_LIST));
%>
<html:form styleClass="form-horizontal"
           action="/clientcreateaction">
    <fieldset><legend>
        New Client
    </legend></fieldset>
    <h4 class="myAccordian">
        Client Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Name *
            </label>
            <div class="controls"><html:text styleId="name"
                                             name="clientForm"
                                             property="name"
                                             size="34"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Main Phone *
            </label>
            <div class="controls"><html:text property="phone"
                                             name="clientForm"
                                             styleId="phone"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Address
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Address Line 1
            </label>
            <div class="controls"><html:text styleId="line1"
                                             property="line1"
                                             name="clientForm"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 2
            </label>
            <div class="controls"><html:text property="line2"
                                             name="clientForm"
                                             styleId="line2"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls"><html:text property="city"
                                             name="clientForm"
                                             styleId="city"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State
            </label>
            <div class="controls"><html:select property="state"
                                               name="clientForm"
                                               styleId="state">
                <html:option value="0">...</html:option>
                <html:options collection="<%=SessionKeys.STATE_LIST%>"
                              property="code"
                              labelProperty="code" />
            </html:select>
                <html:hidden property="state"
                             name="clientForm"
                             styleId="state" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip
            </label>
            <div class="controls"><html:text styleId="postalCode"
                                             property="postalCode"
                                             name="clientForm"
                                             maxlength="10"
                                             size="34" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        General Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Federal Tax ID
            </label>
            <div class="controls"><html:text styleId="federalTaxId"
                                             property="federalTaxId"
                                             name="clientForm"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State Tax ID
            </label>
            <div class="controls"><html:text styleId="stateTaxId"
                                             property="stateTaxId"
                                             name="clientForm"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Web Page
            </label>
            <div class="controls"><html:text property="webPage"
                                             name="clientForm"
                                             styleId="webPage"
                                             maxlength="100"
                                             size="93" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Description
            </label>
            <div class="controls"><html:textarea property="comment"
                                                 name="clientForm"
                                                 styleId="comment"
                                                 rows="3"
                                                 cols="60" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Contact Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                First Name *
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactFirstName"
                                             property="contactFirstName"
                                             size="34"
                                             maxlength="100" />
                <html:hidden name="clientForm"
                             property="contactName"
                             styleId="contactName" />
                <html:hidden name="clientForm"
                             property="contactPhone"
                             styleId="contactPhone" />
                <html:hidden name="clientForm"
                             property="contactId"
                             styleId="contactId" />
                <html:hidden name="clientForm"
                             property="contactAddressId"
                             styleId="contactAddressId" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Title *
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactTitle"
                                             property="contactTitle"
                                             size="34"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Last Name *
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactLastName"
                                             property="contactLastName"
                                             size="34"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 1
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactAddress"
                                             property="contactAddress"
                                             size="93"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 2
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactAddress2"
                                             property="contactAddress2"
                                             size="93"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactCity"
                                             property="contactCity"
                                             size="34"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State
            </label>
            <div class="controls"><html:select property="contactState"
                                               name="clientForm"
                                               styleId="contactState">
                <html:option value="0">...</html:option>
                <html:options collection="<%=SessionKeys.STATE_LIST%>"
                              property="code"
                              labelProperty="code" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip Code
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactZip"
                                             property="contactZip"
                                             size="21"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Email Address *
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactEMail"
                                             property="contactEMail"
                                             size="34"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Primary Phone
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactPrimaryPhone"
                                             property="contactPrimaryPhone"
                                             size="34"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Secondary Phone
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactSecondaryPhone"
                                             property="contactSecondaryPhone"
                                             size="34"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Fax
            </label>
            <div class="controls"><html:text name="clientForm"
                                             styleId="contactFaxPhone"
                                             property="contactFaxPhone"
                                             size="34"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Contact Type
            </label>
            <div class="controls">
                <label class="checkbox"
                       for="contactAsUser">
                    <html:checkbox name="clientForm"
                                   styleId="contactAsUser"
                                   property="contactAsUser" />
                    Also create a User for this contact and allow access to my apps
                </label>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Subscription
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Paid Subscriptions
            </label>
            <div class="controls controls-row">
                <label class="checkbox"
                       for="canAccessStormWater">
                    <html:checkbox name="clientForm"
                                   styleId="canAccessStormWater"
                                   property="canAccessStormWater" />
                    Storm/Sewer (MS4)
                </label>
                <label class="checkbox"
                       for="canAccessErosionControl">
                    <html:checkbox name="clientForm"
                                   styleId="canAccessErosionControl"
                                   property="canAccessErosionControl" />
                    Erosion Control (ESC)
                </label>
                <label class="checkbox"
                       for="canAccessDataView">
                    <html:checkbox name="clientForm"
                                   styleId="canAccessDataView"
                                   property="canAccessDataView" />
                    DataView Online (DVO)
                </label>
                <label class="checkbox"
                       for="canAccessEnvironment">
                    <html:checkbox name="clientForm"
                                   styleId="canAccessEnvironment"
                                   property="canAccessEnvironment" />
                    Environment (ENV)
                </label>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Storm/Sewer (MS4) Specific
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Major Watershed
            </label>
            <div class="controls"><html:text property="majorWaterShed"
                                             name="clientForm"
                                             styleId="majorWaterShed"
                                             maxlength="50"
                                             size="93" />
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
                             value="Save"
                             onclick="return checkRequired();" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <!-- Include the Struts validation JavaScript -->
        <html:javascript formName="clientForm" />
        <script type="text/javascript">
            //<!-- Script Begin
            function checkRequired()
            {
                var blnRequiredFields = false;
                var blnPhone = new Object();
                blnPhone.valid
                        = false;
                var strMessage = "";
                var txtName = document.getElementById("name");
                var txtPhone = document.getElementById("phone");
                var txtContactPhone = document.getElementById("contactPhone");
                var txtContactFirstName = document.getElementById("contactFirstName");
                var txtContactLastName = document.getElementById("contactLastName");
                var txtContactTitle = document.getElementById("contactTitle");
                var txtContactEMail = document.getElementById("contactEMail");
                var txtContactPrimaryPhone = document.getElementById("contactPrimaryPhone");
                var txtContactSecondaryPhone = document.getElementById("contactSecondaryPhone");
                var txtContactFaxPhone = document.getElementById("contactFaxPhone");
                if (txtContactFirstName.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Contact First Name is required, ");
                }
                if (txtContactLastName.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Contact Last Name is required, ");
                }
                if (txtContactTitle.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Contact Title is required, ");
                }
                if (txtContactEMail.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Contact Email is required, ");
                }
                if (txtName.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Name is required, ");
                }
                if (txtPhone.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Phone is required, ");
                }
                else
                {
                    txtPhone.value
                            = formatPhone(txtPhone.value,
                                          'Main Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Main Phone number is invalid, ");
                    }
                }
                if (txtContactPhone.value
                        != "")
                {
                    txtContactPhone.value
                            = formatPhone(txtContactPhone.value,
                                          'Contact Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Contact Phone number is invalid, ");
                    }
                }
                if (txtContactPrimaryPhone.value
                        != "")
                {
                    txtContactPrimaryPhone.value
                            = formatPhone(txtContactPrimaryPhone.value,
                                          'Contact Primary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Contact Primary Phone number is invalid, ");
                    }
                }
                if (txtContactSecondaryPhone.value
                        != "")
                {
                    txtContactSecondaryPhone.value
                            = formatPhone(txtContactSecondaryPhone.value,
                                          'Contact Secondary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Contact Secondary Phone number is invalid, ");
                    }
                }
                if (txtContactFaxPhone.value
                        != "")
                {
                    txtContactFaxPhone.value
                            = formatPhone(txtContactFaxPhone.value,
                                          'Contact fax Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Contact Fax Phone number is invalid, ");
                    }
                }
                // Format the error message;
                if (strMessage.length
                        > 0)
                {
                    // Trim the last comma from the message string
                    strMessage
                            = strMessage.substr(0,
                                                strMessage.length
                                                        - 2)
                            + ".";
                    $('#dialog').html("Please provide required fields. "
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
