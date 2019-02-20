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
           action="/partnercreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Partner
    </legend></fieldset>
    <h4 class="myAccordian">
        <bean:message key="partner.information.heading" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="partner.name" /> *
            </label>
            <div class="controls"><html:text styleId="name"
                                             name="partnerForm"
                                             property="name"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="Organization Name" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.relationship.type" /> *
            </label>
            <div class="controls"><html:select styleId="clientRelationshipTypeId"
                                               name="partnerForm"
                                               property="clientRelationshipTypeId"
                                               alt="Select Partner Type"
                                               title="Select Partner Type">
                <html:options collection="<%= SessionKeys.EC_CLIENT_RELATIONSHIP_TYPE_LIST %>"
                              property="id"
                              labelProperty="name" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.address.line1" />
            </label>
            <div class="controls"><html:text styleId="addressLine1"
                                             name="partnerForm"
                                             property="addressLine1"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="Address 1" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.address.line2" />
            </label>
            <div class="controls"><html:text styleId="addressLine2"
                                             name="partnerForm"
                                             property="addressLine2"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="Address 2" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.city" />
            </label>
            <div class="controls"><html:text styleId="city"
                                             name="partnerForm"
                                             property="city"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="City" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.state" />
            </label>
            <div class="controls"><html:select styleId="state"
                                               name="partnerForm"
                                               property="state"
                                               alt="Select State"
                                               title="State">
                <html:option value="">Select...</html:option>
                <html:options collection="<%= SessionKeys.EC_STATE_LIST %>"
                              property="code"
                              labelProperty="code" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.postal.code" />
            </label>
            <div class="controls"><html:text styleId="postalCode"
                                             name="partnerForm"
                                             property="postalCode"
                                             size="12"
                                             maxlength="12"
                                             alt=""
                                             title="Zip Code" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="partner.contact.information.heading" /></h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="partner.username" /> *
            </label>
            <div class="controls"><html:text styleId="contactUsername"
                                             name="partnerForm"
                                             property="contactUsername"
                                             size="30"
                                             maxlength="30"
                                             alt=""
                                             title="Username" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.contact.firstname" /> *
            </label>
            <div class="controls"><html:text styleId="contactFirstName"
                                             name="partnerForm"
                                             property="contactFirstName"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="Contact First Name" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.contact.lastname" /> *
            </label>
            <div class="controls"><html:text styleId="contactLastName"
                                             name="partnerForm"
                                             property="contactLastName"
                                             size="50"
                                             maxlength="50"
                                             alt=""
                                             title="Contact Last Name" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.contact.phone" /> *
            </label>
            <div class="controls"><html:text styleId="contactPhone"
                                             name="partnerForm"
                                             property="contactPhone"
                                             size="25"
                                             maxlength="25"
                                             alt=""
                                             title="Contact Phone" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.contact.email" /> *
            </label>
            <div class="controls"><html:text styleId="contactEmail"
                                             name="partnerForm"
                                             property="contactEmail"
                                             size="75"
                                             maxlength="100"
                                             alt=""
                                             title="Contact E-Mail" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="partner.contact.email2" /> *
            </label>
            <div class="controls"><html:text styleId="contactEmailConfirm"
                                             name="partnerForm"
                                             property="contactEmailConfirm"
                                             size="75"
                                             maxlength="100"
                                             alt=""
                                             title="Confirm Contact E-Mail" />
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
            <html:javascript formName="partnerForm" />
            <script type="text/javascript">
                //<!--
                var isCancelled = false;
                function validateForm(form)
                {
                    if (isCancelled)
                    {
                        return true;
                    }
                    else
                    {
                        if (document.getElementById("contactEmail").value
                                != document.getElementById("contactEmailConfirm").value)
                        {
                            $('#dialog').html("The confirmation email address you entered does not match the original. Please re-enter the contact email address.").dialog('open');
                            document.getElementById("contactEmail").value
                                    = '';
                            document.getElementById("contactEmailConfirm").value
                                    = '';
                            return false;
                        }
                        return validatePartnerForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
