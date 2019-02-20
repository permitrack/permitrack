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
<fieldset><legend>
    <bean:write name="SourceUsageForm"
                property="sourceDisplayName" />
    <small>
        <bean:write name="SourceUsageForm"
                    property="periodStartTsString" />
        -
        <bean:write name="SourceUsageForm"
                    property="periodEndTsString" />
        for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
    </small>
</legend></fieldset>
<h4 class="myAccordian">
    Source Usage Reading
</h4>
<dl class="dl-horizontal">
    <dt>
        Source
    </dt>
    <dd><bean:write name="SourceUsageForm"
                    property="sourceDisplayName" />
    </dd>
    <dt>Reading
    </dt>
    <dd><bean:write name="SourceUsageForm"
                    property="meterReading" />
        <bean:write name="SourceUsageForm"
                    property="unitOfMeasureDesc" />
    </dd>
    <dt>Reading Time Period
    </dt>
    <dd><bean:write name="SourceUsageForm"
                    property="periodStartTsString" />
        -
        <bean:write name="SourceUsageForm"
                    property="periodEndTsString" />
    </dd>
    <dt>
        Transfer Rate
    </dt>
    <dd>
        <bean:write name="SourceUsageForm"
                    property="transferRate" />
    </dd>
</dl>
<table class="table table-hover">
    <caption class="label">
        Content Breakdown
    </caption>
    <thead>
        <tr>
            <th>
                Substance Name
            </th>
            <th>
                % Formula (by Weight)
            </th>
            <th>
                X
            </th>
            <th>
                Metered Reading
            </th>
            <th></th>
            <th>
                X
            </th>
            <th>
                Source Density
            </th>
            <th>
                =
            </th>
            <th>
                Total
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="9">
                    <bean:message key="source.usage.source.substance.values.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST  %>"
                        scope="session">
            <logic:iterate id="sourceSubstanceValues"
                           name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>
                        <bean:write name="sourceSubstanceValues"
                                    property="substanceName" />
                    </td>
                    <td>
                        <bean:write name="sourceSubstanceValues"
                                    property="ratio" />
                    </td>
                    <td>
                        X
                    </td>
                    <td>
                        <bean:write name="SourceUsageForm"
                                    property="meterReading" />
                        <bean:write name="SourceUsageForm"
                                    property="unitOfMeasureDesc" />
                    </td>
                    <td>
                        <logic:notEqual name="SourceUsageForm"
                                        property="unitOfMeasureDesc"
                                        value="Gallons">
                            (<bean:write name="sourceSubstanceValues"
                                         property="newReading" />
                            Gallons)
                        </logic:notEqual>
                    </td>
                    <td>
                        X
                    </td>
                    <td>
                        <bean:write name="SourceUsageForm"
                                    property="sourceDensity" />
                        <bean:write name="SourceUsageForm"
                                    property="sourceDensityUmDesc" />
                    </td>
                    <td>
                        =
                    </td>
                    <td class="mainbodytext">
                        <bean:write name="sourceSubstanceValues"
                                    property="calculatedTotal" />
                        <bean:write name="sourceSubstanceValues"
                                    property="calculatedTotalUnit" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<div class="alert alert-info">
    <bean:message key="source.usage.content.breakdown" />
</div>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
