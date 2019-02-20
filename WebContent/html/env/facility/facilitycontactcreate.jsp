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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/facilitycontactcreateaction">
    <fieldset><legend>
        Add Contact to <bean:write name="FacilityContactForm"
                                   property="facilityName" />
    </legend></fieldset>
    <html:hidden property="facilityId" />
    <h4 class="myAccordian">
        Contact
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="facility.contact" />
            </label>
            <div class="controls"><html:select name="FacilityContactForm"
                                               property="contactId"
                                               styleId="contactId">
                <html:option value="0">Select a Contact...</html:option>
                <html:options collection="<%=SessionKeys.EV_CONTACT_ACTIVE_LIST_BY_CLIENT%>"
                              property="id"
                              labelProperty="fullName" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.contact.type.list" />
            </label>
            <div class="controls"><html:select name="FacilityContactForm"
                                               property="roleCd"
                                               styleId="roleCd">
                <html:option value="0">Select a Role...</html:option>
                <html:options collection="<%=SessionKeys.EV_FACILITY_ROLES_LIST%>"
                              property="code"
                              labelProperty="description" />
            </html:select>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel"
                             onclick="isCancelled=true;" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             value="Add" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
