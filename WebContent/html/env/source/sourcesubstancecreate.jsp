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
           action="/sourcesubstancecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Add Substance to <bean:write name="SourceSubstanceForm"
                                     property="sourceDisplayName" />
    </legend></fieldset>
    <html:hidden property="sourceId" />
    <h4 class="myAccordian">
        Substance
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="substance.name" />
            </label>
            <div class="controls">
                <html:select name="SourceSubstanceForm"
                             property="substanceId"
                             styleId="substanceId">
                    <html:option value="0">Select a Substance...</html:option>
                    <html:options collection="<%=SessionKeys.EV_SUBSTANCE_ACTIVE_LIST_BY_CLIENT%>"
                                  property="id"
                                  labelProperty="numberAndName" />
                </html:select>
            </div>
        </div>
        <%if (codeType
              == 1)
        {%>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.substance.ratio1" /> *
            </label>
            <div class="controls">
                <html:text property="ratio"
                           name="SourceSubstanceForm" />
            </div>
        </div>
        <%}
        else if (codeType
                 == 2)
        {%>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.substance.ratio2" />
            </label>
            <div class="controls">
                <html:text property="natGasEmFactor"
                           name="SourceSubstanceForm" />
                <html:select name="SourceSubstanceForm"
                             property="natGasEfUnit"
                             styleId="natGasEfUnit">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_NAT_GAS_EMISSION_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <%}
        else if (codeType
                 == 3)
        {%>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.substance.ratio3" />
            </label>
            <div class="controls">
                <html:text property="bulkLiqEmFactor"
                           name="SourceSubstanceForm" />
                <html:select name="SourceSubstanceForm"
                             property="bulkLiqEfUnit"
                             styleId="bulkLiqEfUnit">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_BULK_LIQ_EMISSION_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <%}%>
        <div class="control-group">
            <div class="controls">
                <p class="text-info">
                    <bean:message key="source.substance.ratio.note" />
                </p>
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
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="SourceSubstanceForm" />
            <!-- This JavaScript function calls the validate<form> method of the
            Struts Validation JavaScript included above. This is necessary
            because we are unable to portal encode the form name on the
            html:form onsubmit event. -->
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
                        var natGasEmFactorVal = document.getElementById("natGasEmFactor");
                        var bulkLiqEmFactorVal = document.getElementById("bulkLiqEmFactor");
                        if (natGasEmFactorVal
                                != null)
                        {
                            if (natGasEmFactorVal.value
                                    > 0)
                            {
                                if (document.getElementById("natGasEfUnit").options[0].selected)
                                {
                                    $('#dialog').html("Please select a Unit of Measure.").dialog('open');
                                    return false;
                                }
                            }
                        }
                        if (bulkLiqEmFactorVal
                                != null)
                        {
                            if (bulkLiqEmFactorVal.value
                                    > 0)
                            {
                                if (document.getElementById("bulkLiqEfUnit").options[0].selected)
                                {
                                    $('#dialog').html("Please select a Unit of Measure.").dialog('open');
                                    return false;
                                }
                            }
                        }
                        return validateSourceSubstanceForm(form);
                    }
                }//-->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>