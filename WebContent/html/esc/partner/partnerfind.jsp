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
           action="/partnerfindaction"
           onsubmit="return validateForm(this);"
           method="get">
    <fieldset><legend>
        Partner Search
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="partner.name" />
        </label>
        <div class="controls">
            <html:text styleId="name"
                       property="name"
                       size="50"
                       maxlength="50"
                       alt=""
                       title="Partner Name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="partner.address.line1" />
        </label>
        <div class="controls">
            <html:text property="addressLine1"
                       size="50"
                       maxlength="50"
                       alt=""
                       title="Partner Address 1" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.city" />
        </label>
        <div class="controls">
            <html:text property="city"
                       size="50"
                       maxlength="50"
                       alt=""
                       title="Partner City" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="partner.state" />
        </label>
        <div class="controls"><html:select name="partnerForm"
                                           property="state"
                                           alt="Select State"
                                           title="Partner State">
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
        <div class="controls"><html:text property="postalCode"
                                         size="12"
                                         maxlength="12"
                                         alt=""
                                         title="Partner Zip Code" />
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
                             titleKey="partner.search"
                             value="Search" />
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
                }// End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
