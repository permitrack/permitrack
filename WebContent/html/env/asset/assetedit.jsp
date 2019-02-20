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
<%@ page import="com.sehinc.environment.action.base.RequestKeys" %>
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<%pageContext.setAttribute("allAssetTypes",
                           request.getSession()
                                   .getAttribute(RequestKeys.EV_ASSET_TYPE_LIST));
    pageContext.setAttribute("allAssets",
                             request.getSession()
                                     .getAttribute(RequestKeys.EV_ASSET_LIST));
    pageContext.setAttribute("allMeters",
                             request.getSession()
                                     .getAttribute(RequestKeys.EV_METER_LIST));%>
<html:form styleClass="form-horizontal"
           action="/asseteditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:write name="AssetForm"
                    property="number" />
        <bean:write name="AssetForm"
                    property="name" />
    </legend></fieldset>
    <h4 class="myAccordian">
        Asset Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.number" />
            </label>
            <div class="controls">
                <html:text styleId="number"
                           property="number"
                           name="AssetForm" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><html:hidden property="id" />
                <bean:message key="asset.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             property="name"
                                             name="AssetForm"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.desc" />
            </label>
            <div class="controls"><html:text styleId="description"
                                             property="description"
                                             name="AssetForm"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.location" />
            </label>
            <div class="controls"><html:text styleId="location"
                                             name="AssetForm"
                                             property="location"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.active.date" />
            </label>
            <div class="controls"><html:text styleId="activeTsString"
                                             property="activeTsString"
                                             name="AssetForm"
                                             size="12"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.inactive.date" />
            </label>
            <div class="controls"><html:text styleId="inactiveTsString"
                                             property="inactiveTsString"
                                             name="AssetForm"
                                             size="12"
                                             maxlength="10" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Organization
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.belongs.to.meter" />
            </label>
            <div class="controls"><html:select styleId="meterSelect"
                                               name="AssetForm"
                                               property="meterId">
                <html:option value="">None</html:option>
                <html:options collection="allMeters"
                              property="id"
                              labelProperty="numberAndName" />
            </html:select>
                (Optional)
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.sub.asset" />
            </label>
            <div class="controls"><html:select styleId="parentSelect"
                                               name="AssetForm"
                                               property="parentAssetId">
                <html:option value="">None</html:option>
                <html:options collection="allAssets"
                              property="id"
                              labelProperty="numberAndName" />
            </html:select>
                (Optional)
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Type(s)
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Asset Types
            </label>
            <div class="controls controls-row">
                <label class="radio">
                    <html:radio styleId="meterRadio"
                                name="AssetForm"
                                property="assetDesignation"
                                value="meter" />
                    <bean:message key="asset.meter" />
                </label>
                <label class="radio">
                    <html:radio styleId="pointRadio"
                                name="AssetForm"
                                property="assetDesignation"
                                value="point" />
                    <bean:message key="asset.point" />
                </label>
                <label class="radio">
                    <html:radio styleId="storageTankRadio"
                                name="AssetForm"
                                property="assetDesignation"
                                value="storageTank" />
                    <bean:message key="asset.storage.tank" />
                </label>
                <logic:notEmpty name="AssetForm"
                                property="process">
                    <logic:equal value="true"
                                 name="AssetForm"
                                 property="process">
                        <label class="checkbox">
                            <html:checkbox property="process"
                                           value="process"
                                           disabled="true" />
                            <bean:message key="asset.process" />
                        </label>
                        <ul>
                            <logic:iterate id="processList"
                                           name="<%= SessionKeys.EV_ACTIVE_PROCESS_LIST %>"
                                           scope="session">
                                <li>
                                    <bean:write name="processList"
                                                property="processNumber" />
                                    <bean:write name="processList"
                                                property="name" />
                                </li>
                            </logic:iterate>
                        </ul>
                    </logic:equal>
                    <logic:equal value="false"
                                 name="AssetForm"
                                 property="process">
                        <label class="checkbox">
                            <html:checkbox property="buildingHeat"
                                           value="buildingHeat"
                                           disabled="false" />
                            <bean:message key="asset.building.heat" />
                        </label>
                    </logic:equal>
                </logic:notEmpty>
                <logic:empty name="AssetForm"
                             property="process">
                    <label class="checkbox">
                        <html:checkbox styleId="buildingHeatRadio"
                                       name="AssetForm"
                                       property="assetTypeSelection"
                                       value="buildingHeat" />
                        <bean:message key="asset.building.heat" />
                    </label>
                </logic:empty>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Emitted Substance Types
            </label>
            <div class="controls">
                <logic:iterate id="aType"
                               name="allAssetTypes">
                    <label class="checkbox">
                        <html:multibox property="selectedTypeCodes"
                                       disabled="false">
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
    <h4 class="myAccordian">
        Emission Point
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.ep.rated.mmbtu" />
            </label>
            <div class="controls">
                <html:text styleId="epRatedMmbtu"
                           name="AssetForm"
                           property="epRatedMmbtu"
                           size="15" />
                MmBTU/hr
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.ep.capacity.mmbtu" />
            </label>
            <div class="controls">
                <html:text styleId="epCapacityMmbtu"
                           name="AssetForm"
                           property="epCapacityMmbtu"
                           size="15" />
                MmBTU/hr
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="asset.transfer.rate" />
            </label>
            <div class="controls">
                <html:text styleId="transferRate"
                           name="AssetForm"
                           property="transferRate"
                           size="15" />
                <span class="help-inline">
                    <bean:message key="source.substance.ratio.note" />
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="alert">
                    <strong>Note</strong> If the selected Source does not have a Transfer Rate, or you want to use the default Transfer Rate of <code>0.55</code>, leave this field blank.
                </div>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Storage Tank Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.carrier" />
            </label>
            <div class="controls"><html:text styleId="tankCarrier"
                                             name="AssetForm"
                                             property="tankCarrier"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.loading.method" />
            </label>
            <div class="controls"><html:text styleId="tankLoadingMethod"
                                             name="AssetForm"
                                             property="tankLoadingMethod"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.vapor.recovery" />
            </label>
            <div class="controls"><html:checkbox styleId="tankVaporRecovery"
                                                 name="AssetForm"
                                                 property="tankVaporRecovery" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.type.desc" />
            </label>
            <div class="controls"><html:text styleId="tankTypeDesc"
                                             name="AssetForm"
                                             property="tankTypeDesc"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.contents.desc" />
            </label>
            <div class="controls"><html:text styleId="tankContentsDesc"
                                             name="AssetForm"
                                             property="tankContentsDesc"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.diameter" />
            </label>
            <div class="controls"><html:text styleId="tankDiameter"
                                             name="AssetForm"
                                             property="tankDiameter"
                                             size="15" />
                Feet
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.height" />
            </label>
            <div class="controls"><html:text styleId="tankHeight"
                                             name="AssetForm"
                                             property="tankHeight"
                                             size="15" />
                Feet
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.tank.capacity" />
            </label>
            <div class="controls"><html:text styleId="tankCapacity"
                                             name="AssetForm"
                                             property="tankCapacity"
                                             size="15" />
                Gallons
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Stack Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.latitude" />
            </label>
            <div class="controls"><html:text styleId="stackLatitude"
                                             name="AssetForm"
                                             property="stackLatitude"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.longitude" />
            </label>
            <div class="controls"><html:text styleId="stackLongitude"
                                             name="AssetForm"
                                             property="stackLongitude"
                                             size="40" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.height" />
            </label>
            <div class="controls"><html:text styleId="stackHeight"
                                             name="AssetForm"
                                             property="stackHeight"
                                             size="15" />
                Feet
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.diameter" />
            </label>
            <div class="controls"><html:text styleId="stackDiameter"
                                             name="AssetForm"
                                             property="stackDiameter"
                                             size="15" />
                Feet
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.exit.temp" />
            </label>
            <div class="controls"><html:text styleId="stackExitTemp"
                                             name="AssetForm"
                                             property="stackExitTemp"
                                             size="15" />
                Degrees F
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.exit.velocity" />
            </label>
            <div class="controls"><html:text styleId="stackExitVelocity"
                                             name="AssetForm"
                                             property="stackExitVelocity"
                                             size="15" />
                Ft/Sec
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.exit.flow.rate" />
            </label>
            <div class="controls"><html:text styleId="stackExitFlowRate"
                                             name="AssetForm"
                                             property="stackExitFlowRate"
                                             size="15" />
                Cu Ft/Min
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.stack.desc" />
            </label>
            <div class="controls"><html:text styleId="stackDesc"
                                             name="AssetForm"
                                             property="stackDesc"
                                             size="40" />
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
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="AssetForm" />
            <script type="text/javascript">
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
                            $('#dialog').html("Time period is invalid. Active Date must precede Inactive Date.").dialog('open');
                            return false;
                        }
                        return validateAssetForm(form);
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
