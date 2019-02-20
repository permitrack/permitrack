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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/partneraddaction"
           onsubmit="return validateForm(this);">
    <html:hidden styleId="id"
                 name="partnerAddForm"
                 property="id" />
    <fieldset><legend>
        <bean:message key="partner.information.heading" /></legend></fieldset>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="partner.name" />
        </label>
        <div class="controls"><bean:write name="partnerAddForm"
                                          property="name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.relationship.type" /> *
        </label>
        <div class="controls"><html:select name="partnerAddForm"
                                           property="clientRelationshipTypeId"
                                           alt="Select Partner Type">
            <html:options collection="<%= SessionKeys.EC_CLIENT_RELATIONSHIP_TYPE_LIST %>"
                          property="id"
                          labelProperty="name" />
        </html:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.address.line1" />
        </label>
        <div class="controls"><bean:write name="partnerAddForm"
                                          property="addressLine1" />
            <logic:notEmpty name="partnerAddForm"
                            property="addressLine2">
                <div>
                    <bean:write name="partnerAddForm"
                                property="addressLine2" />
                </div>
            </logic:notEmpty>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.city" />
        </label>
        <div class="controls"><bean:write name="partnerAddForm"
                                          property="city" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.state" />
        </label>
        <div class="controls"><bean:write name="partnerAddForm"
                                          property="state" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.postal.code" />
        </label>
        <div class="controls"><bean:write name="partnerAddForm"
                                          property="postalCode" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.contact.information.heading" />
        </label>
        <div class="controls">
            <logic:notPresent name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                              scope="session">
                <div class="error">
                    You cannot add this partner because they do not have any active user accounts.
                </div>
                <div>
                    You should contact this partner to have them contact their PermiTrack support representative.
                </div>
            </logic:notPresent>
            <logic:present name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                           scope="session">
                <logic:empty name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                             scope="session">
                    <div class="error">
                        You cannot add this partner because they do not have any active user accounts.
                    </div>
                    <div>
                        You should contact this partner to have them contact their PermiTrack support representative.
                    </div>
                </logic:empty>
                <logic:notEmpty name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                                scope="session">
                    <table class="table table-hover action-first action-small">
                        <thead>
                            <tr>
                                <th>
                                    Notify
                                </th>
                                <th>
                                    <bean:message key="partner.contact.firstname" />
                                </th>
                                <th>
                                    <bean:message key="partner.contact.lastname" />
                                </th>
                                <th>
                                    <bean:message key="partner.contact.phone" />
                                </th>
                                <th>
                                    <bean:message key="partner.contact.email" />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="userValue"
                                           name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                                           scope="session"
                                           indexId="index">
                                <bean:define id="userId"
                                             name="userValue"
                                             property="id" />
                                <logic:present name="userValue"
                                               property="emailAddress">
                                    <bean:define id="userEmail"
                                                 name="userValue"
                                                 property="emailAddress" />
                                </logic:present>
                                <tr>
                                    <td>
                                        <html:radio styleId="selectedIndex"
                                                    name="partnerAddForm"
                                                    property="selectedIndex"
                                                    value='<%= pageContext.getAttribute("index").toString() %>' />
                                        <html:hidden styleId="contactUserList"
                                                     name="partnerAddForm"
                                                     property="contactUserList"
                                                     value='<%= pageContext.getAttribute("userId").toString() %>' />
                                    </td>
                                    <td>
                                        <bean:write name="userValue"
                                                    property="firstName" />
                                    </td>
                                    <td>
                                        <bean:write name="userValue"
                                                    property="lastName" />
                                    </td>
                                    <td>
                                        <bean:write name="userValue"
                                                    property="primaryPhone" />
                                    </td>
                                    <td>
                                        <logic:present name="userValue"
                                                       property="emailAddress">
                                            <html:text name="partnerAddForm"
                                                       property="contactEmailList"
                                                       size="75"
                                                       maxlength="100"
                                                       alt=""
                                                       title="Contact Email"
                                                       value='<%= (java.lang.String) pageContext.getAttribute("userEmail") %>' />
                                        </logic:present>
                                        <logic:notPresent name="userValue"
                                                          property="emailAddress">
                                            <html:text name="partnerAddForm"
                                                       property="contactEmailList"
                                                       size="75"
                                                       maxlength="100"
                                                       alt=""
                                                       title="Contact Email"
                                                       value='' />
                                        </logic:notPresent>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </logic:notEmpty>
            </logic:present>
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
                <logic:present name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                               scope="session">
                    <logic:notEmpty name="<%= SessionKeys.EC_PARTNER_USER_LIST %>"
                                    scope="session">
                        <html:submit styleClass="btn btn-success btn-large pull-right"
                                     property="submit"
                                     value="Add" />
                    </logic:notEmpty>
                </logic:present>
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!-- Begin
                var bCancel = false;
                function validateForm(form)
                {
                    if (bCancel) return true;
                    var selectedIndex = -1;
                    var selectedIndexElement = document.partnerAddForm.selectedIndex;
                    if (selectedIndexElement.type
                            == 'radio')
                    { //We have a single radio button element
                        if (selectedIndexElement.checked)
                        {
                            selectedIndex
                                    = selectedIndexElement.value;
                        }
                    }
                    else
                    {  //We have muliple radio buttons in an array
                        for (i
                                     = 0; i
                                < selectedIndexElement.length; i++)
                        {
                            if (selectedIndexElement[i].checked)
                            {
                                selectedIndex
                                        = selectedIndexElement[i].value;
                                break;
                            }
                        }
                    }
                    if (selectedIndex
                            < 0)
                    {
                        $('#dialog').html("Please select a contact from the list.").dialog('open');
                        return false;
                    }
                    var contactEmailListElement = document.partnerAddForm.contactEmailList;
                    var contactEmailElement;
                    if (contactEmailListElement.type
                            == 'text')
                    {  //We have a single text element
                        contactEmailElement
                                = contactEmailListElement;
                    }
                    else
                    {  //We have an array of text elements
                        if (contactEmailListElement.length
                                > selectedIndex)
                        {
                            contactEmailElement
                                    = contactEmailListElement[selectedIndex];
                            if (contactEmailElement
                                        != null
                                    && contactEmailElement
                                    != 'undefined')
                            {
                                if (contactEmailElement.value
                                            == null
                                        || contactEmailElement.value
                                        == '')
                                {
                                    $('#dialog').html("Contact email address is required.").dialog('open');
                                    return false;
                                }
                            }
                            else
                            {
                                $('#dialog').html("Contact email address is required.").dialog('open');
                                return false;
                            }
                        }
                        else
                        {
                            $('#dialog').html("Contact email address is required.").dialog('open');
                            return false;
                        }
                    }
                    return validateEmail(form,
                                         contactEmailElement);
                }
                function Email(element)
                {
                    this.aa
                            = new Array(element,
                                        "Contact email address is invalid.",
                                        new Function("varName",
                                                     "this.maxlength='100';  return this[varName];"));
                }
                function validateEmail(form, element)
                {
                    var bValid = true;
                    var focusField = null;
                    var i = 0;
                    var fields = new Array();
                    var oEmail = new Email(element);
                    for (var x in oEmail)
                    {
                        if ((oEmail[x][0].type
                                     == 'text'
                                || oEmail[x][0].type
                                == 'textarea')
                                && (oEmail[x][0].value.length
                                > 0))
                        {
                            if (!checkEmail(oEmail[x][0].value))
                            {
                                if (i
                                        == 0)
                                {
                                    focusField
                                            = oEmail[x][0];
                                }
                                fields[i++]
                                        = oEmail[x][1];
                                bValid
                                        = false;
                            }
                        }
                    }
                    if (fields.length
                            > 0)
                    {
                        focusField.focus();
                        $('#dialog').html(fields.join('\n')).dialog('open');
                    }
                    return bValid;
                }
                /**
                 * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
                 * http://javascript.internet.com
                 */
                function checkEmail(emailStr)
                {
                    if (emailStr.length
                            == 0)
                    {
                        return true;
                    }
                    var emailPat = /^(.+)@(.+)$/;
                    var specialChars = "\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
                    var validChars = "\[^\\s"
                                             + specialChars
                            + "\]";
                    var quotedUser = "(\"[^\"]*\")";
                    var ipDomainPat = /^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
                    var atom = validChars
                            + '+';
                    var word = "("
                                       + atom
                                       + "|"
                                       + quotedUser
                            + ")";
                    var userPat = new RegExp("^"
                                                     + word
                                                     + "(\\."
                                                     + word
                                                     + ")*$");
                    var domainPat = new RegExp("^"
                                                       + atom
                                                       + "(\\."
                                                       + atom
                                                       + ")*$");
                    var matchArray = emailStr.match(emailPat);
                    if (matchArray
                            == null)
                    {
                        return false;
                    }
                    var user = matchArray[1];
                    var domain = matchArray[2];
                    if (user.match(userPat)
                            == null)
                    {
                        return false;
                    }
                    var IPArray = domain.match(ipDomainPat);
                    if (IPArray
                            != null)
                    {
                        for (var i = 1; i
                                <= 4; i++)
                        {
                            if (IPArray[i]
                                    > 255)
                            {
                                return false;
                            }
                        }
                        return true;
                    }
                    var domainArray = domain.match(domainPat);
                    if (domainArray
                            == null)
                    {
                        return false;
                    }
                    var atomPat = new RegExp(atom,
                                             "g");
                    var domArr = domain.match(atomPat);
                    var len = domArr.length;
                    if ((domArr[domArr.length
                            - 1].length
                            < 2)
                            || (domArr[domArr.length
                            - 1].length
                            > 3))
                    {
                        return false;
                    }
                    return len
                            >= 2;
                }// End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
