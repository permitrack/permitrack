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
<%pageContext.setAttribute("addressList",
                           request.getSession()
                                   .getAttribute(SessionKeys.CONTACT_ORGANIZATION_ADDRESSES_LIST_ACTIVE));
    pageContext.setAttribute("orgList",
                             request.getSession()
                                     .getAttribute(SessionKeys.CONTACT_ORGANIZATION_LIST_ACTIVE));
    pageContext.setAttribute("stateList",
                             request.getSession()
                                     .getAttribute(SessionKeys.STATE_LIST));
    pageContext.setAttribute("allContactTypes",
                             request.getAttribute(SessionKeys.CONTACT_TYPE_LIST));%>
<html:form styleClass="form-horizontal"
           action="/contacteditaction">
    <fieldset><legend>
        <bean:write name="ContactForm"
                    property="firstName" />
        <bean:write name="ContactForm"
                    property="lastName" />
    </legend></fieldset>
    <h4 class="myAccordian">
        Contact Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                First Name *
            </label>
            <div class="controls">
                <html:text name="ContactForm"
                           property="firstName"
                           styleId="firstName"
                           size="26"
                           maxlength="100" />
                <html:hidden name="ContactForm"
                             property="id"
                             styleId="id" />
                <html:hidden name="ContactForm"
                             property="mi"
                             styleId="mi" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Last Name *
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="lastName"
                                             styleId="lastName"
                                             size="26"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Email Address *
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="email"
                                             styleId="email"
                                             size="26"
                                             maxlength="50" />
                <html:hidden name="ContactForm"
                             property="statusCode"
                             styleId="statusCode" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Title
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="title"
                                             styleId="title"
                                             size="26"
                                             maxlength="100" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Organization
    </h4>
    <div>
    <div class="control-group">
        <label class="control-label">
            Organization *
        </label>
        <div class="controls controls-row">
            <html:select name="ContactForm"
                         property="organizationId"
                         styleId="organizationId"
                         onchange="return Org_OnSelect();">
                <html:option value="0">Create new organization</html:option>
                <html:options collection="<%=SessionKeys.CONTACT_ORGANIZATION_LIST_ACTIVE%>"
                              property="id"
                              labelProperty="name" />
            </html:select>
            <p class="text-info">
                Select an existing organization
            </p>
            <p>
                or
            </p>
            <div class="text-success">
                Create a new organization below
            </div>
            <logic:iterate id="o"
                           name="orgList">
                <input type="hidden"
                       name="oa_<bean:write name='o' property='id'/>"
                       id="oa_<bean:write name='o' property='id'/>"
                       value="<bean:write name='o' property='address.id'/>" />
            </logic:iterate>
            <logic:iterate id="a"
                           name="addressList">
                <input type="hidden"
                       name="a_l1_<bean:write name='a' property='id'/>"
                       id="a_l1_<bean:write name='a' property='id'/>"
                       value="<bean:write name='a' property='line1'/>" />
                <input type="hidden"
                       name="a_l2_<bean:write name='a' property='id'/>"
                       id="a_l2_<bean:write name='a' property='id'/>"
                       value="<bean:write name='a' property='line2'/>" />
                <input type="hidden"
                       name="a_ct_<bean:write name='a' property='id'/>"
                       id="a_ct_<bean:write name='a' property='id'/>"
                       value="<bean:write name='a' property='city'/>" />
                <input type="hidden"
                       name="a_st_<bean:write name='a' property='id'/>"
                       id="a_st_<bean:write name='a' property='id'/>"
                       value="<bean:write name='a' property='state'/>" />
                <input type="hidden"
                       name="a_zp_<bean:write name='a' property='id'/>"
                       id="a_zp_<bean:write name='a' property='id'/>"
                       value="<bean:write name='a' property='postalCode'/>" />
            </logic:iterate>
        </div>
    </div>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationName">
                Name
            </label>
            <div class="controls">
                <input type="text"
                       name="organizationName"
                       id="organizationName"
                       size="70"
                       maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationAddress">
                Address Line 1
            </label>
            <div class="controls">
                <input type="text"
                       name="organizationAddress"
                       id="organizationAddress"
                       size="70"
                       maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationAddress2">
                Address Line 2
            </label>
            <div class="controls">
                <input type="text"
                       name="organizationAddress2"
                       id="organizationAddress2"
                       size="70"
                       maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationCity">
                City
            </label>
            <div class="controls">
                <input type="text"
                       name="organizationCity"
                       id="organizationCity"
                       size="26"
                       maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationState">
                State
            </label>
            <div class="controls">
                <select name="organizationState"
                        id="organizationState">
                    <option value="0"
                            selected>...
                    </option>
                    <logic:iterate id="theState"
                                   name="stateList">
                        <option value="<bean:write name='theState' property='code'/>">
                            <bean:write name='theState'
                                        property='code' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="organizationZip">
                Zip Code
            </label>
            <div class="controls">
                <input type="text"
                       name="organizationZip"
                       id="organizationZip"
                       size="14"
                       maxlength="10" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Address Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Address Line 1
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="address"
                                             styleId="address"
                                             size="70"
                                             maxlength="50" />
                <html:hidden name="ContactForm"
                             property="addressId"
                             styleId="addressId" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 2
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="address2"
                                             styleId="address2"
                                             size="70"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="city"
                                             styleId="city"
                                             size="26"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State
            </label>
            <div class="controls"><html:select name="ContactForm"
                                               property="stateCode"
                                               styleId="stateCode">
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
            <div class="controls"><html:text name="ContactForm"
                                             property="zip"
                                             styleId="zip"
                                             size="14"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Primary Phone
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="primaryPhone"
                                             styleId="primaryPhone"
                                             size="22"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Secondary Phone
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="secondaryPhone"
                                             styleId="secondaryPhone"
                                             size="22"
                                             maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Fax
            </label>
            <div class="controls"><html:text name="ContactForm"
                                             property="faxPhone"
                                             styleId="faxPhone"
                                             size="22"
                                             maxlength="100" />
            </div>
        </div>
    </div>
    <logic:equal value="true"
                 name="ContactForm"
                 property="canViewESC">
        <h4 class="myAccordian">
            Erosion Control (ESC)
        </h4>
        <div>
            <div class="control-group">
                <label class="control-label">
                    Contact Types
                </label>
                <div class="controls controls-row">
                    <logic:iterate id="aType"
                                   name="allContactTypes">
                        <label class="checkbox"
                               for="selectedTypes">
                            <html:multibox property="selectedTypes"
                                           styleId="selectedTypes">
                                <bean:write name="aType"
                                            property='code' />
                            </html:multibox>
                            <bean:write name="aType"
                                        property='description' />
                        </label>
                    </logic:iterate>
                </div>
            </div>
        </div>
    </logic:equal>
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
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            //<!-- Script Begin
            function Trim(sString)
            {
                sString
                        = rightTrim(leftTrim(sString));
                return sString;
            }
            function rightTrim(sString)
            {
                while (sString.substring(sString.length
                                                 - 1,
                                         sString.length)
                        == ' ')
                {
                    sString
                            = sString.substring(0,
                                                sString.length
                                                        - 1);
                }
                return sString;
            }
            function leftTrim(sString)
            {
                while (sString.substring(0,
                                         1)
                        == ' ')
                {
                    sString
                            = sString.substring(1,
                                                sString.length);
                }
                return sString;
            }
            function UpdateCheckValue(sMod, sInt)
            { // Update the value of the check box field based on its checked value
                var b = false;
                var c = document.getElementById("contactType"
                                                        + sMod
                                                        + sInt);
                var ch = document.getElementById("contactType"
                                                         + sMod
                                                         + "H"
                                                         + sInt);
                if (c
                            != null
                        && ch
                        != null)
                {
                    if (c.checked)
                    { // If the contact type check box is not checked then set it's value to zero.  That way
                        // only the check boxes that have none zero values are the contact types the user wants.
                        // Use the contact type id to insert to cap client contact table.
                        c.value
                                = ch.value;
                    }
                    else
                    {
                        c.value
                                = "0";
                    }
                    b
                            = c.value
                            != "0";
                }
                return b;
            }
            function RequiredFields()
            {
                var blnReturn = false;
                var blnPhone = new Object();
                blnPhone.valid
                        = false;
                var i = 0;
                var s = "";
                var x = 0;
                var blnContactType = false;
                var txtFN = document.getElementById("firstName");
                var txtLN = document.getElementById("lastName");
                var txtEM = document.getElementById("email");
                var txtPP = document.getElementById("primaryPhone");
                var txtSP = document.getElementById("secondaryPhone");
                var txtFX = document.getElementById("faxPhone");
                var cboOID = document.getElementById("organizationId");
                var txtOON = document.getElementById("organizationName");
                var strMessage = "";
                if (txtPP.value
                        != "")
                {
                    txtPP.value
                            = formatPhone(txtPP.value,
                                          'Primary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Primary Phone is invalid, ");
                    }
                }
                if (txtSP.value
                        != "")
                {
                    txtSP.value
                            = formatPhone(txtSP.value,
                                          'Secondary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Secondary Phone is invalid, ");
                    }
                }
                if (txtFX.value
                        != "")
                {
                    txtFX.value
                            = formatPhone(txtFX.value,
                                          'Fax',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Fax Phone is invalid, ");
                    }
                }
                // Validate if any of the input values are blank.
                if (txtFN.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("First Name is required, ");
                }
                if (txtLN.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Last Name is required, ");
                }
                if (txtEM.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Email is required, ");
                }
                if (cboOID.value
                            == "0"
                        && txtOON.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Organization is required, ");
                }
                for (x
                             = 1; x
                        <= 3; x++)
                {
                    if (x
                            == 1) s
                            = "EC";
                    if (x
                            == 2) s
                            = "SW";
                    if (x
                            == 3) s
                            = "DV";
                    for (i
                                 = 1; i
                            <= 9; i++)
                    {
                        var c = document.getElementById("contactType"
                                                                + s
                                                                + i);
                        if (c
                                != null)
                        {
                            if (!blnContactType)
                            {
                                if (c.checked)
                                {
                                    blnContactType
                                            = 'true';
                                }
                            }
                        }
                    }
                }
                // no spaces allowed
                if (!NoSpaces(txtFN.value))
                {
                    strMessage
                            = strMessage.concat("First Name can not contain any spaces, ");
                }
                if (!NoSpaces(txtLN.value))
                {
                    strMessage
                            = strMessage.concat("Last Name can not contain any spaces, ");
                }
                // valid characters only
                if (!ValidChars(txtFN.value))
                {
                    strMessage
                            = strMessage.concat("First Name contains invalid characters, ");
                }
                if (!ValidChars(txtLN.value))
                {
                    strMessage
                            = strMessage.concat("Last Name contains invalid characters, ");
                }
                // Format the error message;
                if (strMessage.length
                        > 0)
                { // Information not all provided
                    // Trim the last comma from the message string
                    strMessage
                            = strMessage.substr(0,
                                                strMessage.length
                                                        - 2)
                            + ".";
                    $('#dialog').html("Please enter required fields. "
                                              + strMessage).dialog('open');
                } // Information not all provided
                else
                { //
                    blnReturn
                            = true;
                } //
                return blnReturn;
            }
            function Org_OnSelect()
            {
                var cboO = document.getElementById("organizationId");
                var txtA = document.getElementById("address");
                //    var txtA2 = document.getElementById("address2");
                var txtC = document.getElementById("city");
                //    var cboS = document.getElementById("stateCode");
                //    var txtZ = document.getElementById("zip");
                var intO_Id = null;
                //    var intA_Id = null;
                var txtA_Id = null;
                var txtOA_L1 = null;
                var txtOA_L2 = null;
                var txtOA_CT = null;
                var txtOA_ST = null;
                var txtOA_ZP = null;
                txtA.value
                        = Trim(txtA.value);
                txtC.value
                        = Trim(txtC.value);
                if (cboO.value
                        != "0")
                {
                    //if (txtA.value == "" && txtC.value == "")
                    //{
                    intO_Id
                            = cboO.value;
                    txtA_Id
                            = document.getElementById("oa_"
                                                              + intO_Id);
                    txtOA_L1
                            = document.getElementById("a_l1_"
                                                              + txtA_Id.value);
                    txtOA_L2
                            = document.getElementById("a_l2_"
                                                              + txtA_Id.value);
                    txtOA_CT
                            = document.getElementById("a_ct_"
                                                              + txtA_Id.value);
                    txtOA_ST
                            = document.getElementById("a_st_"
                                                              + txtA_Id.value);
                    txtOA_ZP
                            = document.getElementById("a_zp_"
                                                              + txtA_Id.value);
                }
            }//  Script End -->
        </script>
    </tiles:put>
</tiles:definition>
