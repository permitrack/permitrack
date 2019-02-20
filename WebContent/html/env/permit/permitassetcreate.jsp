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
           action="/permitassetcreateaction">
    <fieldset><legend>
        Add Asset To Permit
    </legend></fieldset>
    <h4 class="myAccordian">
        Asset
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.detail.name" />
            </label>
            <div class="controls"><html:hidden property="permitDetailId"
                                               name="PermitAssetForm" />
                <bean:write property="permitDetailName"
                            name="PermitAssetForm" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.detail.desc" />
            </label>
            <div class="controls"><bean:write property="permitDetailDesc"
                                              name="PermitAssetForm" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.asset.to.add" />
            </label>
            <div class="controls"><html:select name="PermitAssetForm"
                                               property="assetId"
                                               styleId="assetId">
                <html:option value="0">Select an Asset...</html:option>
                <html:options collection="<%=SessionKeys.EV_ASSET_LIST%>"
                              property="id"
                              labelProperty="numberAndName" />
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
