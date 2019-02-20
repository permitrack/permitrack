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
<%
    Integer
            codeType =
            (Integer) request.getSession()
                    .getAttribute(SessionKeys.EV_SRC_SUB_SRC_TYPE); %>
<html:form styleClass="form-horizontal"
           action="/sourcesubstancemulticreateaction">
    <fieldset><legend>
        Add Substances to
        <bean:write name="MultiSourceSubstanceForm"
                    property="sourceDisplayName" />
    </legend></fieldset>
    <html:hidden name="MultiSourceSubstanceForm"
                 property="sourceId" />
    <div class="alert alert-info">
        <bean:message key="source.substance.info1" />
        <bean:message key="source.substance.info2" />
        <bean:message key="source.substance.ratio.note" />
    </div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>
                    <bean:message key="substance.partNum" />
                </th>
                <th>
                    <bean:message key="substance.name" />
                </th>
                <th>
                    <bean:message key="substance.substanceType" />
                </th>
                <th>
                    <bean:message key="source.substance.ratio4" />*
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="MultiSourceSubstanceForm"
                         property="sourceSubstances">
                <tr>
                    <td colspan="4">
                        <bean:message key="source.substance.empty.list2" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="MultiSourceSubstanceForm"
                            property="sourceSubstances">
                <logic:iterate id="subListValue"
                               name="MultiSourceSubstanceForm"
                               property="sourceSubstances">
                    <tr>
                        <td>
                            <html:hidden name="subListValue"
                                         property="substanceId"
                                         styleId="substanceId" />
                            <html:hidden name="subListValue"
                                         property="substanceName"
                                         styleId="substanceName" />
                            <bean:write name="subListValue"
                                        property="substancePartNumber" />
                        </td>
                        <td>
                            <bean:write name="subListValue"
                                        property="substanceName" />
                        </td>
                        <td>
                            <bean:write name="subListValue"
                                        property="substanceTypeDesc" />
                        </td>
                        <%if (codeType
                              == 1)
                        {%>
                        <td>
                            <html:text property="ratio"
                                       name="MultiSourceSubstanceForm" />
                        </td>
                        <%}
                        else if (codeType
                                 == 2)
                        {%>
                        <td>
                            <html:text property="natGasEmFactor"
                                       name="MultiSourceSubstanceForm" />
                            <html:select name="MultiSourceSubstanceForm"
                                         property="natGasEfUnit"
                                         styleId="natGasEfUnit">
                                <html:option value="0">Select a Unit...</html:option>
                                <html:options collection="<%=SessionKeys.EV_NAT_GAS_EMISSION_LIST%>"
                                              property="code"
                                              labelProperty="description" />
                            </html:select>
                        </td>
                        <%}
                        else if (codeType
                                 == 3)
                        {%>
                        <td>
                            <html:text property="bulkLiqEmFactor"
                                       name="MultiSourceSubstanceForm" />
                            <html:select name="MultiSourceSubstanceForm"
                                         property="bulkLiqEfUnit"
                                         styleId="bulkLiqEfUnit">
                                <html:option value="0">
                                    Select a Unit...
                                </html:option>
                                <html:options collection="<%=SessionKeys.EV_BULK_LIQ_EMISSION_LIST%>"
                                              property="code"
                                              labelProperty="description" />
                            </html:select>
                        </td>
                        <%}%>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
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