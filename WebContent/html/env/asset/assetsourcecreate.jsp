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
           action="/assetsourcecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Add Source to Asset
    </legend></fieldset>
    <html:hidden property="assetId" />
    <h4 class="myAccordian">
        Source
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.displayName" />
            </label>
            <div class="controls">
                <html:select name="AssetSourceForm"
                             property="sourceId"
                             styleId="sourceId">
                    <html:option value="0">Select a Source...</html:option>
                    <html:options collection="<%=SessionKeys.EV_SOURCE_ACTIVE_LIST_BY_CLIENT%>"
                                  property="id"
                                  labelProperty="displayName" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.source.active.date" />
            </label>
            <div class="controls">
                <html:text name="AssetSourceForm"
                           styleId="activeTsString"
                           property="activeTsString"
                           size="12"
                           maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.source.inactive.date" />
            </label>
            <div class="controls">
                <html:text name="AssetSourceForm"
                           styleId="inactiveTsString"
                           property="inactiveTsString"
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
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="AssetSourceForm" />
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
                        var activeDate = new Date(document.getElementById("activeTsString").value);
                        var inactiveDate = new Date(document.getElementById("inactiveTsString").value);
                        if (inactiveDate.getTime()
                                < activeDate.getTime())
                        {
                            $('#dialog').html("Asset source time period is invalid. Active Date must precede Inactive Date.").dialog('open');
                            return false;
                        }
                        return validateAssetSourceForm(form);
                    }
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#activeTsString").datepicker({autoclose:true});
                      $("#inactiveTsString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
