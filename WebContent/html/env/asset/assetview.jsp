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
<%
    pageContext.setAttribute("allAssetTypes",
                             request.getAttribute(RequestKeys.EV_ASSET_TYPE_LIST));
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ASSET_SOURCE_CAN_DELETE);
    Boolean
            blnCanDeleteSub =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ASSET_SUBSTANCE_CAN_DELETE);
    Boolean
            firstRunThrough =
            true;
    Integer
            substanceCounter =
            1;
    StringBuffer
            divName;
%>
<fieldset><legend>
    <bean:write name="AssetForm"
                property="number" />
    <bean:write name="AssetForm"
                property="name" />
</legend></fieldset>
<h4 class="myAccordian">
    Asset Description
</h4>
<dl class="dl-horizontal">
    <%--
        <dt>
            <bean:message key="asset.number" />
        </dt>
        <dd><bean:write name="AssetForm"
                        property="number" /></dd>
        <dt><bean:message key="asset.name" />
        </dt>
        <dd><bean:write name="AssetForm"
                        property="name" /></dd>
    --%>
    <dt><bean:message key="asset.desc" />
    </dt>
    <dd>
        <bean:write name="AssetForm"
                    property="description" />
    </dd>
    <dt><bean:message key="asset.location" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="location" /></dd>
    <dt><bean:message key="asset.active.date" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="activeTsString" /></dd>
    <dt><bean:message key="asset.inactive.date" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="inactiveTsString" /></dd>
</dl>
<h4 class="myAccordian">
    Asset Organization
</h4>
<dl class="dl-horizontal">
    <logic:notEmpty name="AssetForm"
                    property="meterId">
        <dt><bean:message key="asset.belongs.to.meter" />
        </dt>
        <dd><bean:write name="AssetForm"
                        property="meterNumber" />
            <bean:write name="AssetForm"
                        property="meterName" /></dd>
    </logic:notEmpty>
    <logic:notEmpty name="AssetForm"
                    property="parentAssetId">
        <dt><bean:message key="asset.sub.asset" />
        </dt>
        <dd><bean:write name="AssetForm"
                        property="parentAssetNumber" />
            <bean:write name="AssetForm"
                        property="parentAssetName" /></dd>
    </logic:notEmpty>
    <logic:notEmpty name="AssetForm"
                    property="permitDetailNames">
        <dt><bean:message key="asset.permit.detail" />
        </dt>
        <dd><logic:iterate id="permitDetail"
                           name="AssetForm"
                           property="permitDetailNames">
            <logic:equal value="1"
                         name="permitDetail"
                         property="status.code">
                <div>
                    <bean:write name="permitDetail"
                                property="name" />
                </div>
            </logic:equal>
        </logic:iterate></dd>
    </logic:notEmpty>
</dl>
<h4 class="myAccordian">Types
</h4>
<dl class="dl-horizontal">
    <dt>
        Asset Types
    </dt>
    <dd>
        <label class="checkbox">
            <html:checkbox name="AssetForm"
                           property="meter"
                           disabled="true" />
            <bean:message key="asset.meter" />
        </label>
        <label class="checkbox">
            <html:checkbox name="AssetForm"
                           property="point"
                           disabled="true" />
            <bean:message key="asset.point" />
        </label>
        <label class="checkbox">
            <html:checkbox name="AssetForm"
                           property="storageTank"
                           disabled="true" />
            <bean:message key="asset.storage.tank" />
        </label>
        <logic:notEmpty name="AssetForm"
                        property="buildingHeat">
            <logic:equal value="true"
                         name="AssetForm"
                         property="buildingHeat">
                <label class="checkbox">
                    <html:checkbox name="AssetForm"
                                   property="buildingHeat"
                                   value="buildingHeat"
                                   disabled="true" />
                    <bean:message key="asset.building.heat" />
                </label>
            </logic:equal>
        </logic:notEmpty>
        <logic:notEmpty name="AssetForm"
                        property="process">
            <logic:equal value="true"
                         name="AssetForm"
                         property="process">
                <label class="checkbox">
                    <html:checkbox name="AssetForm"
                                   property="process"
                                   value="process"
                                   disabled="true" />
                    <bean:message key="asset.process" />
                </label>
                <ul class="unstyled"
                    style="padding-left: 20px;">
                    <logic:iterate id="processList"
                                   name="<%= SessionKeys.EV_ACTIVE_PROCESS_LIST %>"
                                   scope="session">
                        <li>
                            <html:link module="/html/env/process"
                                       action="/processviewpage"
                                       paramId="evProcessId"
                                       paramName="processList"
                                       paramProperty="id">
                                <bean:write name="processList"
                                            property="processNumber" />
                                <bean:write name="processList"
                                            property="name" />
                            </html:link>
                        </li>
                    </logic:iterate>
                </ul>
            </logic:equal>
            <logic:equal value="false"
                         name="AssetForm"
                         property="process">
                <logic:notEmpty name="AssetForm"
                                property="buildingHeat">
                    <logic:equal value="false"
                                 name="AssetForm"
                                 property="buildingHeat">
                        <p class="text-info">
                            <bean:message key="asset.no.type.set" />
                        </p>
                    </logic:equal>
                </logic:notEmpty>
            </logic:equal>
        </logic:notEmpty>
        <logic:empty name="AssetForm"
                     property="buildingHeat">
            <logic:empty name="AssetForm"
                         property="process">
                <p class="text-info">
                    <bean:message key="asset.no.type.set" />
                </p>
            </logic:empty>
            <logic:equal value="false"
                         name="AssetForm"
                         property="process">
                <p class="text-info">
                    <bean:message key="asset.no.type.set" />
                </p>
            </logic:equal>
        </logic:empty>
    </dd>
    <dt>
        Emitted Substance Types
    </dt>
    <dd>
        <logic:iterate id="aType"
                       name="allAssetTypes">
            <label class="checkbox">
                <html:multibox name="AssetForm"
                               property="selectedTypeCodes"
                               disabled="true">
                    <bean:write name="aType"
                                property='code' />
                </html:multibox>
                <bean:write name="aType"
                            property='description' />
            </label>
        </logic:iterate>
    </dd>
</dl>
<h4 class="myAccordian">
    Emission Point Information
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="asset.ep.rated.mmbtu" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="epRatedMmbtu" />
        <logic:notEmpty name="AssetForm"
                        property="epRatedMmbtu">
            MmBTU/hr
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.ep.capacity.mmbtu" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="epCapacityMmbtu" />
        <logic:notEmpty name="AssetForm"
                        property="epCapacityMmbtu">
            MmBTU/hr
        </logic:notEmpty></dd>
    <dt>
        <bean:message key="asset.transfer.rate" />
    </dt>
    <dd>
        <bean:write name="AssetForm"
                    property="transferRate" />
    </dd>
</dl>
<h4 class="myAccordian">
    Storage Tank Information
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="asset.tank.carrier" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankCarrier" /></dd>
    <dt><bean:message key="asset.tank.loading.method" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankLoadingMethod" /></dd>
    <dt><bean:message key="asset.tank.vapor.recovery" />
    </dt>
    <dd>
        <html:checkbox name="AssetForm"
                       property="tankVaporRecovery"
                       disabled="true" />
    </dd>
    <dt><bean:message key="asset.tank.type.desc" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankTypeDesc" /></dd>
    <dt><bean:message key="asset.tank.contents.desc" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankContentsDesc" /></dd>
    <dt><bean:message key="asset.tank.diameter" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankDiameter" />
        <logic:notEmpty name="AssetForm"
                        property="tankDiameter">
            Feet
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.tank.height" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankHeight" />
        <logic:notEmpty name="AssetForm"
                        property="tankHeight">
            Feet
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.tank.capacity" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="tankCapacity" />
        <logic:notEmpty name="AssetForm"
                        property="tankCapacity">
            Gallons
        </logic:notEmpty></dd>
</dl>
<h4 class="myAccordian">Stack Information
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="asset.stack.latitude" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackLatitude" /></dd>
    <dt><bean:message key="asset.stack.longitude" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackLongitude" /></dd>
    <dt><bean:message key="asset.stack.height" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackHeight" />
        <logic:notEmpty name="AssetForm"
                        property="stackHeight">
            Feet
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.stack.diameter" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackDiameter" />
        <logic:notEmpty name="AssetForm"
                        property="stackDiameter">
            Feet
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.stack.exit.temp" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackExitTemp" />
        <logic:notEmpty name="AssetForm"
                        property="stackExitTemp">
            Degrees F
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.stack.exit.velocity" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackExitVelocity" />
        <logic:notEmpty name="AssetForm"
                        property="stackExitVelocity">
            Ft/Sec
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.stack.exit.flow.rate" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackExitFlowRate" />
        <logic:notEmpty name="AssetForm"
                        property="stackExitFlowRate">
            Cu Ft/Min
        </logic:notEmpty></dd>
    <dt><bean:message key="asset.stack.desc" />
    </dt>
    <dd><bean:write name="AssetForm"
                    property="stackDesc" /></dd>
</dl>
<table class="table table-hover  action-first action-small">
    <caption class="label">
        Substance Efficiency Factors
    </caption>
<%--
    <colgroup>
        <col style="width: 50px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>
                Substance Type
            </th>
            <th>
                Efficiency Factor
            </th>
            <th>
                Add'l Info
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_ASSET_SUBSTANCE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="4">
                    <bean:message key="asset.substance.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_ASSET_SUBSTANCE_LIST %>"
                        scope="session">
            <logic:iterate id="effListVal"
                           name="<%= SessionKeys.EV_ASSET_SUBSTANCE_LIST %>"
                           scope="session">
                <tr>
                    <td>
                        <% if (blnCanDeleteSub)
                        { %>
                        <html:link styleClass="btn btn-mini btn-danger warn-delete"
                                   action="/assetsubstancedeleteaction.do"
                                   paramId="evAssetSubstanceId"
                                   paramName="effListVal"
                                   paramProperty="assetSubstanceId">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <bean:write name="effListVal"
                                    property="substanceTypeName" />
                    </td>
                    <td>
                        <bean:write name="effListVal"
                                    property="efficiencyFactor" />%
                    </td>
                    <td>
                        <bean:write name="effListVal"
                                    property="additionalInfo" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Asset Sources
    </caption>
<%--
    <colgroup>
        <col style="width: 50px; white-space: nowrap;" />
    </colgroup>
--%>
    <tr>
        <th></th>
        <th></th>
        <th>
            Name
        </th>
        <th>
            Active Date
        </th>
        <th>
            Inactive Date
        </th>
    </tr>
    <logic:empty name="<%= SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST %>"
                 scope="session">
        <tr>
            <td colspan="5">
                <bean:message key="asset.source.empty.list" />
            </td>
        </tr>
    </logic:empty>
    <logic:notEmpty name="<%= SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST %>"
                    scope="session">
        <logic:iterate id="subListValue"
                       name="<%= SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST %>"
                       indexId="evenOrOdd"
                       scope="session">
            <tr>
                <td>
                            <%
                                if (blnCanDelete)
                                {
                            %>
                    <html:link styleClass="btn btn-mini btn-danger warn-delete"
                               action="/assetsourcedeleteaction.do"
                               paramId="evAssetSourceId"
                               paramName="subListValue"
                               paramProperty="assetSourceId">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                    <%}%>
                </td>
                <td>
                    <logic:notEmpty name="subListValue"
                                    property="displayColor">
                        <img alt=""
                             src="<bean:write name='subListValue' property='displayColor'/>" />
                    </logic:notEmpty>
                </td>
                <td>
                    <bean:define id="assetDisplayName"
                                 name="subListValue"
                                 property="displayName" />
                    <bean:write name="assetDisplayName" />
                </td>
                <td>
                    <bean:write name="subListValue"
                                property="activeTsString" />
                </td>
                <td>
                    <bean:write name="subListValue"
                                property="inactiveTsString" />
                </td>
            </tr>
            <!-- SOURCE SUBSTANCES LIST SECTION -->
            <logic:notEmpty name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                            scope="session">

                        <%
                            String
                                    assetNameToCompare =
                                    null;
                            if (firstRunThrough)
                            {
                                assetNameToCompare =
                                        (String) assetDisplayName;
                            }%>
                <logic:iterate id="subListValue"
                               name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                               scope="session">
                            <% if (assetNameToCompare
                                   != (String) assetDisplayName)
                            {
                                assetNameToCompare =
                                        (String) assetDisplayName;
                                firstRunThrough =
                                        true;
                            }%>
                    <logic:equal name="subListValue"
                                 property="assetName"
                                 value="<%= (String) assetDisplayName %>">
                                <%
                                    if (firstRunThrough)
                                    {
                                        substanceCounter++;
                                        divName =
                                                new StringBuffer();
                                        divName.append("substAttrs");
                                        divName.append(substanceCounter);
                                %>
                        <tr class="muted">
                            <th colspan="2"></th>
                            <th>
                                Substance Name
                            </th>
                            <th>
                                Type
                            </th>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="1">
                                <th>
                                    % Formula By Weight
                                </th>
                            </logic:equal>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="2">
                                <th>
                                    Emission Factor
                                </th>
                            </logic:equal>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="3">
                                <th>
                                    Emission Factor
                                </th>
                            </logic:equal>
                        </tr>
                        <%
                            }
                            firstRunThrough =
                                    false;
                        %>
                    </logic:equal>
                    <logic:equal name="subListValue"
                                 property="assetName"
                                 value="<%= (String) assetDisplayName %>">
                        <tr class="muted">
                            <td colspan="2">
                                <html:hidden name="subListValue"
                                             property="assetName" />
                            </td>
                            <td>
                                <bean:write name="subListValue"
                                            property="substanceName" />
                            </td>
                            <td>
                                <bean:write name="subListValue"
                                            property="substanceType" />
                            </td>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="1">
                                <td>
                                    <bean:write name="subListValue"
                                                property="ratio" />
                                </td>
                            </logic:equal>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="2">
                                <td>
                                    <bean:write name="subListValue"
                                                property="natGasEmFactor" />
                                    <bean:write name="subListValue"
                                                property="natGasEfUnitDesc" />
                                </td>
                            </logic:equal>
                            <logic:equal name="subListValue"
                                         property="sourceTypeCode"
                                         value="3">
                                <td>
                                    <bean:write name="subListValue"
                                                property="bulkLiqEmFactor" />
                                    <bean:write name="subListValue"
                                                property="bulkLiqEfUnitDesc" />
                                </td>
                            </logic:equal>
                        </tr>
                    </logic:equal>
                </logic:iterate>
            </logic:notEmpty>
        </logic:iterate>
    </logic:notEmpty>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
