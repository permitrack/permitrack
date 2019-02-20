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
           action="/processassetcreateaction">
    <fieldset><legend>
        Add Asset to Process
    </legend></fieldset>
    <html:hidden property="processId" />
    <h4 class="myAccordian">
        Asset
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="process.asset.select" />
            </label>
            <div class="controls">
                <html:select styleId="assetId"
                             name="ProcessAssetForm"
                             property="assetId">
                    <html:option value="">Select an Asset</html:option>
                    <html:options collection="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT %>"
                                  property="id"
                                  labelProperty="numberAndName" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="process.asset.active.date" />
            </label>
            <div class="controls">
                <html:text styleId="activeTsString"
                           property="activeTsString"
                           name="ProcessAssetForm"
                           size="12"
                           maxlength="10" />
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
        <script type="text/javascript">
            <!--
            $(function ()
              {
                  $("#activeTsString").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
