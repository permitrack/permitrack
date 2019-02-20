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
<%@ page import="com.sehinc.common.db.contact.CapContact,
                 com.sehinc.common.util.StringUtil,
                 com.sehinc.security.action.base.SessionKeys,
                 java.util.Iterator,
                 java.util.List" %>
<%pageContext.setAttribute("stateList",
                           request.getSession()
                                   .getAttribute(SessionKeys.STATE_LIST));%>
<html:form styleClass="form-horizontal"
           action="/clienteditaction">
    <style>
        .controls div.help-inline
        {
            padding-top: 5px;
            padding-left: 0;
        }
    </style>
    <fieldset>
        <legend>
            <bean:write name="clientForm"
                        property="name" />
        </legend>
    </fieldset>
    <html:hidden styleId="shortName"
                 name="clientForm"
                 property="shortName" />
    <html:hidden styleId="id"
                 name="clientForm"
                 property="id" />
    <html:hidden styleId="addressId"
                 name="clientForm"
                 property="addressId" />
    <html:hidden styleId="statusCode"
                 name="clientForm"
                 property="statusCode" />
    <h4 class="myAccordian">
        Client Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Name *
            </label>
            <div class="controls">
                <logic:equal name='clientForm'
                             property='blnSystemAdmin'
                             value='true'>
                    <html:text styleId="name"
                               name="clientForm"
                               property="name"
                               size="34"
                               maxlength="50" />
                </logic:equal>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <bean:write name="clientForm"
                                property="name" />
                    <html:hidden styleId="name"
                                 name="clientForm"
                                 property="name" />
                </logic:notEqual>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Main Phone *
            </label>
            <div class="controls">
                <logic:equal name='clientForm'
                             property='blnSystemAdmin'
                             value='true'>
                    <html:text styleId="phone"
                               name="clientForm"
                               property="phone"
                               maxlength="50"
                               size="34" />
                </logic:equal>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <bean:write name="clientForm"
                                property="phone" />
                    <html:hidden styleId="phone"
                                 name="clientForm"
                                 property="phone" />
                </logic:notEqual>
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
            <div class="controls">
                <html:text styleId="line1"
                           name="clientForm"
                           property="line1"
                           maxlength="50"
                           size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Address Line 2
            </label>
            <div class="controls">
                <html:text styleId="line2"
                           name="clientForm"
                           property="line2"
                           maxlength="50"
                           size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls"><html:text styleId="city"
                                             name="clientForm"
                                             property="city"
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
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip
            </label>
            <div class="controls"><html:text styleId="postalCode"
                                             name="clientForm"
                                             property="postalCode"
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
                                             name="clientForm"
                                             property="federalTaxId"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State Tax ID
            </label>
            <div class="controls"><html:text styleId="stateTaxId"
                                             name="clientForm"
                                             property="stateTaxId"
                                             maxlength="50"
                                             size="34" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Web Page
            </label>
            <div class="controls"><html:text styleId="webPage"
                                             name="clientForm"
                                             property="webPage"
                                             maxlength="100"
                                             size="95" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Description
            </label>
            <div class="controls"><html:textarea styleId="comment"
                                                 name="clientForm"
                                                 property="comment"
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
                Select Contact
            </label>
            <div class="controls">
                <div id="mainClientContactSelect"></div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">First Name
            </label>
            <div class="controls">
                <div id="contactFirstNameDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactFirstName"
                             styleId="contactFirstName" />
                <html:hidden styleId="contactName"
                             name="clientForm"
                             property="contactName" />
                <html:hidden styleId="contactPhone"
                             name="clientForm"
                             property="contactPhone" />
                <html:hidden styleId="txtContactName"
                             name="clientForm"
                             property="contactName" />
                <html:hidden styleId="txtContactPhone"
                             name="clientForm"
                             property="contactPhone" />
                <html:hidden styleId="contactId"
                             name="clientForm"
                             property="contactId" />
                <html:hidden styleId="contactAddressId"
                             name="clientForm"
                             property="contactAddressId" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Last Name
            </label>
            <div class="controls">
                <div id="contactLastNameDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactLastName"
                             styleId="contactLastName" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Title
            </label>
            <div class="controls">
                <div id="contactTitleDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactTitle"
                             styleId="contactTitle" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 1
            </label>
            <div class="controls">
                <div id="contactAddressDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactAddress"
                             styleId="contactAddress" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 2
            </label>
            <div class="controls">
                <div id="contactAddress2Display" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactAddress2"
                             styleId="contactAddress2" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls">
                <div id="contactCityDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactCity"
                             styleId="contactCity" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State
            </label>
            <div class="controls">
                <div id="contactStateDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactState"
                             styleId="contactState" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip
            </label>
            <div class="controls">
                <div id="contactZipDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactZip"
                             styleId="contactZip" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Email Address
            </label>
            <div class="controls">
                <div id="contactEMailDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactEMail"
                             styleId="contactEMail" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Primary Phone
            </label>
            <div class="controls">
                <div id="contactPrimaryPhoneDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactPrimaryPhone"
                             styleId="contactPrimaryPhone" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Secondary Phone
            </label>
            <div class="controls">
                <div id="contactSecondaryPhoneDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactSecondaryPhone"
                             styleId="contactSecondaryPhone" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Fax
            </label>
            <div class="controls">
                <div id="contactFaxPhoneDisplay" class="help-inline"></div>
                <html:hidden name="clientForm"
                             property="contactFaxPhone"
                             styleId="contactFaxPhone" />
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
                <label class="checkbox">
                    <logic:equal name='clientForm'
                                 property='blnSystemAdmin'
                                 value='true'>
                        <html:checkbox name="clientForm"
                                       property="canAccessStormWater"
                                       styleId="canAccessStormWater" />
                    </logic:equal>
                    Storm/Sewer (MS4)
                </label>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <span class="label">
                        <bean:write name="clientForm"
                                    property="canAccessStormWaterText" />
                        <html:hidden styleId="canAccessStormWater"
                                     name="clientForm"
                                     property="canAccessStormWater" />
                    </span>
                </logic:notEqual>
                <label class="checkbox">
                    <logic:equal name='clientForm'
                                 property='blnSystemAdmin'
                                 value='true'>
                        <html:checkbox name="clientForm"
                                       property="canAccessErosionControl"
                                       styleId="canAccessErosionControl" />
                    </logic:equal>
                    Erosion Control (ESC)
                </label>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <span class="label">
                        <bean:write name="clientForm"
                                    property="canAccessErosionControlText" />
                        <html:hidden styleId="canAccessErosionControl"
                                     name="clientForm"
                                     property="canAccessErosionControl" />
                    </span>
                </logic:notEqual>
                <label class="checkbox">
                    <logic:equal name='clientForm'
                                 property='blnSystemAdmin'
                                 value='true'>
                        <html:checkbox name="clientForm"
                                       property="canAccessDataView"
                                       styleId="canAccessDataView" />
                    </logic:equal>
                    DataView Online (DVO)
                </label>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <span class="label">
                        <bean:write name="clientForm"
                                    property="canAccessDataViewText" />
                        <html:hidden styleId="canAccessDataView"
                                     name="clientForm"
                                     property="canAccessDataView" />
                    </span>
                </logic:notEqual>
                <label class="checkbox">
                    <logic:equal name='clientForm'
                                 property='blnSystemAdmin'
                                 value='true'>
                        <html:checkbox name="clientForm"
                                       property="canAccessEnvironment"
                                       styleId="canAccessEnvironment" />
                    </logic:equal>
                    Environment (ENV)
                </label>
                <logic:notEqual name='clientForm'
                                property='blnSystemAdmin'
                                value='true'>
                    <span class="label">
                        <bean:write name="clientForm"
                                    property="canAccessEnvironmentText" />
                        <html:hidden styleId="canAccessEnvironment"
                                     name="clientForm"
                                     property="canAccessEnvironment" />
                    </span>
                </logic:notEqual>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Storm/Sewer (MS4) Specific Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Major Watershed
            </label>
            <div class="controls"><html:text styleId="majorWaterShed"
                                             name="clientForm"
                                             property="majorWaterShed"
                                             maxlength="50"
                                             size="80" />
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
                             onclick="return RequiredFields();" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="clientForm" />
            <script type="text/javascript">
                var mainClientContactArray = new Array(0);// Create main contact dropdown.
                initMainClientContactArray();
                initMainClientContact();
                function initMainClientContactArray()
                {
                <% Iterator mainClientContactListIterator = ((List) request.getSession().getAttribute(SessionKeys.CONTACT_LIST_ACTIVE)).iterator();
        while (mainClientContactListIterator.hasNext()) {
           CapContact capContact = (CapContact) mainClientContactListIterator.next(); %>
                    mainClientContactArray.push(new Contact('<%= capContact.getId() %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getOrganizationName()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getFirstName()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getLastName()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getTitle()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getAddress()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getAddress2()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getCity()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getStateCode()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getZip()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getPrimaryPhone()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getSecondaryPhone()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getFaxPhone()) %>',
                                                            '<%= StringUtil.escapeSingleQuote(capContact.getEmail()) %>',
                                                            '<%= capContact.getAddressData().getId() %>'));
                <% } %>
                }
                function initMainClientContact()
                {
                    //            var index = 0;
                    var inputHTML = "";
                    var selectedContactId = "0";
                    var pageContactId = <bean:write name="clientForm" property="contactId"/>;
                    for (var index = 0; index
                            < mainClientContactArray.length; index++)
                    {
                        var contact = mainClientContactArray[index];
                        if (contact.id
                                == pageContactId)
                        { // Highlight the current contact as 'selected' in the dropdown
                            selectedContactId
                                    = contact.id;
                            document.getElementById('contactId').value
                                    = contact.id;
                            document.getElementById('contactFirstName').value
                                    = contact.firstName;
                            document.getElementById('contactLastName').value
                                    = contact.lastName;
                            document.getElementById('contactTitle').value
                                    = contact.title;
                            document.getElementById('contactAddress').value
                                    = contact.address;
                            document.getElementById('contactAddress2').value
                                    = contact.address2;
                            document.getElementById('contactCity').value
                                    = contact.city;
                            document.getElementById('contactState').value
                                    = contact.stateCode;
                            document.getElementById('contactZip').value
                                    = contact.zipCode;
                            document.getElementById('contactEMail').value
                                    = contact.email;
                            document.getElementById('contactPrimaryPhone').value
                                    = contact.phone;
                            document.getElementById('contactSecondaryPhone').value
                                    = contact.phone2;
                            document.getElementById('contactFaxPhone').value
                                    = contact.fax;
                            document.getElementById('contactAddressId').value
                                    = contact.addressId;
                            document.getElementById('contactName').value
                                    = contact.firstName
                                              + " "
                                    + contact.lastName;
                            document.getElementById('contactPhone').value
                                    = contact.phone;
                            document.getElementById('contactFirstNameDisplay').innerHTML
                                    = contact.firstName;
                            document.getElementById('contactLastNameDisplay').innerHTML
                                    = contact.lastName;
                            document.getElementById('contactTitleDisplay').innerHTML
                                    = contact.title;
                            document.getElementById('contactAddressDisplay').innerHTML
                                    = contact.address;
                            document.getElementById('contactAddress2Display').innerHTML
                                    = contact.address2;
                            document.getElementById('contactCityDisplay').innerHTML
                                    = contact.city;
                            document.getElementById('contactStateDisplay').innerHTML
                                    = contact.stateCode;
                            document.getElementById('contactZipDisplay').innerHTML
                                    = contact.zipCode;
                            document.getElementById('contactEMailDisplay').innerHTML
                                    = contact.email;
                            document.getElementById('contactPrimaryPhoneDisplay').innerHTML
                                    = contact.phone;
                            document.getElementById('contactSecondaryPhoneDisplay').innerHTML
                                    = contact.phone2;
                            document.getElementById('contactFaxPhoneDisplay').innerHTML
                                    = contact.fax;
                        }
                    }
                    //Build the table that contains the contact select list
                    inputHTML
                            += "<select id=\"selectContactId\" name=\"selectContactId\" onchange=\"mainClientContactOnChange();\">";
                    inputHTML
                            += "<option value=\"-1\">Select...</option>";
                    for (index
                                 = 0; index
                            < mainClientContactArray.length; index++)
                    {
                        inputHTML
                                += "<option "
                                           + ((mainClientContactArray[index].id
                                == selectedContactId)
                                ? "SELECTED"
                                : "")
                                           + " value=\""
                                           + mainClientContactArray[index].id
                                           + "\">"
                                           + mainClientContactArray[index].lastName
                                           + ", "
                                           + mainClientContactArray[index].firstName
                                + "</option>";
                    }
                    inputHTML
                            += "</select>";
                    //Update the HTML element
                    document.getElementById('mainClientContactSelect').innerHTML
                            = inputHTML;
                }
                function mainClientContactOnChange()
                {
                    var selected = document.getElementById('selectContactId').selectedIndex;
                    var contactId = document.getElementById('selectContactId').options[selected].value;
                    //Clear the contact fields
                    clearMainClientContact();
                    var contact = getClientContact(contactId);
                    document.getElementById('contactId').value
                            = contact.id;
                    document.getElementById('contactFirstName').value
                            = contact.firstName;
                    document.getElementById('contactLastName').value
                            = contact.lastName;
                    document.getElementById('contactTitle').value
                            = contact.title;
                    document.getElementById('contactAddress').value
                            = contact.address;
                    document.getElementById('contactAddress2').value
                            = contact.address2;
                    document.getElementById('contactCity').value
                            = contact.city;
                    document.getElementById('contactState').value
                            = contact.stateCode;
                    document.getElementById('contactZip').value
                            = contact.zipCode;
                    document.getElementById('contactEMail').value
                            = contact.email;
                    document.getElementById('contactPrimaryPhone').value
                            = contact.phone;
                    document.getElementById('contactSecondaryPhone').value
                            = contact.phone2;
                    document.getElementById('contactFaxPhone').value
                            = contact.fax;
                    document.getElementById('contactAddressId').value
                            = contact.addressId;
                    document.getElementById('contactName').value
                            = contact.firstName
                                      + " "
                            + contact.lastName;
                    document.getElementById('contactPhone').value
                            = contact.phone;
                    document.getElementById('contactFirstNameDisplay').innerHTML
                            = contact.firstName;
                    document.getElementById('contactLastNameDisplay').innerHTML
                            = contact.lastName;
                    document.getElementById('contactTitleDisplay').innerHTML
                            = contact.title;
                    document.getElementById('contactAddressDisplay').innerHTML
                            = contact.address;
                    document.getElementById('contactAddress2Display').innerHTML
                            = contact.address2;
                    document.getElementById('contactCityDisplay').innerHTML
                            = contact.city;
                    document.getElementById('contactStateDisplay').innerHTML
                            = contact.stateCode;
                    document.getElementById('contactZipDisplay').innerHTML
                            = contact.zipCode;
                    document.getElementById('contactEMailDisplay').innerHTML
                            = contact.email;
                    document.getElementById('contactPrimaryPhoneDisplay').innerHTML
                            = contact.phone;
                    document.getElementById('contactSecondaryPhoneDisplay').innerHTML
                            = contact.phone2;
                    document.getElementById('contactFaxPhoneDisplay').innerHTML
                            = contact.fax;
                }
                function clearMainClientContact()
                {
                    document.getElementById('contactId').value
                            = '';
                    document.getElementById('contactFirstName').value
                            = '';
                    document.getElementById('contactLastName').value
                            = '';
                    document.getElementById('contactTitle').value
                            = '';
                    document.getElementById('contactAddress').value
                            = '';
                    document.getElementById('contactAddress2').value
                            = '';
                    document.getElementById('contactCity').value
                            = '';
                    document.getElementById('contactState').value
                            = '';
                    document.getElementById('contactZip').value
                            = '';
                    document.getElementById('contactEMail').value
                            = '';
                    document.getElementById('contactPrimaryPhone').value
                            = '';
                    document.getElementById('contactSecondaryPhone').value
                            = '';
                    document.getElementById('contactFaxPhone').value
                            = '';
                    document.getElementById('contactAddressId').value
                            = '';
                    document.getElementById('contactPhone').value
                            = '';
                    document.getElementById('contactFirstNameDisplay').innerHTML
                            = '';
                    document.getElementById('contactLastNameDisplay').innerHTML
                            = '';
                    document.getElementById('contactTitleDisplay').innerHTML
                            = '';
                    document.getElementById('contactAddressDisplay').innerHTML
                            = '';
                    document.getElementById('contactAddress2Display').innerHTML
                            = '';
                    document.getElementById('contactCityDisplay').innerHTML
                            = '';
                    document.getElementById('contactStateDisplay').innerHTML
                            = '';
                    document.getElementById('contactZipDisplay').innerHTML
                            = '';
                    document.getElementById('contactEMailDisplay').innerHTML
                            = '';
                    document.getElementById('contactPrimaryPhoneDisplay').innerHTML
                            = '';
                    document.getElementById('contactSecondaryPhoneDisplay').innerHTML
                            = '';
                    document.getElementById('contactFaxPhoneDisplay').innerHTML
                            = '';
                }
                function getClientContact(contactId)
                {
                    //            var index = 0;
                    for (var index = 0; index
                            < mainClientContactArray.length; index++)
                    {
                        if (mainClientContactArray[index].id
                                == contactId)
                        {
                            return mainClientContactArray[index];
                        }
                    }
                    return new Contact('',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '',
                                       '');
                }
                function SetValue(sStatusComboBox, sStatusHiddenElement)
                {
                    var cboS = document.getElementById(sStatusComboBox);
                    var cboSH = document.getElementById(sStatusHiddenElement);
                    if (cboSH.value
                            == "")
                    {
                        cboS.value
                                = "0";
                    }
                    else
                    {
                        cboS.value
                                = cboSH.value;
                    }
                }
                function RequiredFields()
                {
                    var blnRequiredFields = false;
                    var blnPhone = new Object();
                    blnPhone.valid
                            = false;
                    var strMessage = "";
                    var txtName = document.getElementById('name');
                    var txtPhone = document.getElementById('phone');
                    var contactSelect = document.getElementById('selectContactId');
                    if (contactSelect.value
                            == '-1')
                    {
                        strMessage
                                = strMessage.concat('A main contact must be selected, ');
                    }
                    if (txtName.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat('Name is required, ');
                    }
                    if (txtPhone.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat('Phone is required, ');
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
                                    = strMessage.concat('Main Phone number is invalid, ');
                        }
                    }
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
                }    // CONTACT JS
                function Contact(id, organizationName, firstName, lastName, title, address, address2, city, stateCode, zipCode, phone, phone2, fax, email, addressId)
                {
                    this.id
                            = id;
                    this.organizationName
                            = organizationName;
                    this.firstName
                            = firstName;
                    this.lastName
                            = lastName;
                    this.title
                            = title;
                    this.address
                            = address;
                    this.address2
                            = address2;
                    this.city
                            = city;
                    this.stateCode
                            = stateCode;
                    this.zipCode
                            = zipCode;
                    this.phone
                            = phone;
                    this.phone2
                            = phone2;
                    this.fax
                            = fax;
                    this.email
                            = email;
                    this.addressId
                            = addressId;
                }
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
