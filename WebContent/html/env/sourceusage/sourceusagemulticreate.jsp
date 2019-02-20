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
           action="/sourceusagemulticreateaction">
    <fieldset><legend>
        New Set of Readings
        <small>
            for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
        </small>
    </legend></fieldset>
    <html:hidden name="SourceUsageMultiForm"
                 property="assetId" />
    <h4>
        <bean:write name="SourceUsageMultiForm"
                    property="startDateString" />
        -
        <bean:write name="SourceUsageMultiForm"
                    property="endDateString" />
        <html:hidden name="SourceUsageMultiForm"
                     property="startDateString"
                     styleId="startDateString" />
        <html:hidden name="SourceUsageMultiForm"
                     property="endDateString"
                     styleId="endDateString" />
    </h4>
    <div class="alert">
        <bean:message key="source.usage.info1" />
        <bean:message key="source.usage.info2" />
        <div>
            Transfer Rate: <bean:message key="source.substance.ratio.note" />
        </div>
    </div>
    <logic:notEmpty name="SourceUsageMultiForm"
                    property="sourceUsages">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>
                        Source
                    </th>
                    <th>
                        Reading
                    </th>
                    <th>
                        Unit of Measure
                    </th>
                    <th>
                        Transfer Rate
                    </th>
                    <th>
                        Start Date
                    </th>
                    <th>
                        End Date
                    </th>
                </tr>
            </thead>
            <tbody>
                <logic:iterate id="source"
                               name="SourceUsageMultiForm"
                               property="sourceUsages">
                    <logic:equal name="source"
                                 property="ableToEnterReading"
                                 value="true">
                        <tr>
                            <td>
                                <html:hidden name="source"
                                             property="sourceId"
                                             styleId="sourceId" />
                                <html:hidden name="source"
                                             property="sourceName"
                                             styleId="sourceName" />
                                <bean:write name="source"
                                            property="sourceName" />
                            </td>
                            <td>
                                <html:text name="source"
                                           property="meterReading"
                                           styleId="meterReading" />
                            </td>
                            <td>
                                <html:select name="source"
                                             property="unitOfMeasureCd"
                                             styleId="unitOfMeasureCd">
                                    <html:option value="0">Select a Unit...</html:option>
                                    <html:options collection="<%=SessionKeys.EV_SRC_USAGE_CD_LIST%>"
                                                  property="code"
                                                  labelProperty="description" />
                                </html:select>
                            </td>
                            <td>
                                <html:text name="source"
                                           property="transferRate"
                                           styleId="transferRate" />
                            </td>
                            <td>
                                <bean:write name="SourceUsageMultiForm"
                                            property="startDateString" />
                            </td>
                            <td>
                                <bean:write name="SourceUsageMultiForm"
                                            property="endDateString" />
                            </td>
                        </tr>
                    </logic:equal>
                    <logic:equal name="source"
                                 property="ableToEnterReading"
                                 value="false">
                        <tr>
                            <td>
                                <bean:write name="source"
                                            property="sourceName" />
                            </td>
                            <td colspan="3">
                                <bean:message key="source.usage.source.not.active" />
                            </td>
                            <td>
                                <bean:write name="source"
                                            property="periodStartTsString" />
                            </td>
                            <td>
                                <bean:write name="source"
                                            property="periodEndTsString" />
                            </td>
                        </tr>
                    </logic:equal>
                </logic:iterate>
            </tbody>
        </table>
    </logic:notEmpty>
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
</html:form>