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
           action="/permitsubstancecreateaction">
    <fieldset><legend>
        Add Substance to <bean:write property="permitName"
                                     name="PermitSubstanceForm" />
    </legend></fieldset>
    <html:hidden property="permitId" />
    <h4 class="myAccordian">
        Substance
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.substance.to.add" />
            </label>
            <div class="controls"><html:select name="PermitSubstanceForm"
                                               property="substanceTypeCd"
                                               styleId="substanceTypeCd">
                <html:option value="0">Select a Substance Type...</html:option>
                <html:options collection="<%=SessionKeys.EV_SUBSTANCE_TYPE_LIST%>"
                              property="code"
                              labelProperty="description" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.detail.chargeable" />
            </label>
            <div class="controls"><html:checkbox property="chargeable"
                                                 name="PermitSubstanceForm" />
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
