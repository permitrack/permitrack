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
           action="/permitdetaileditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:write name="PermitDetailForm"
                    property="name" />
    </legend></fieldset>
    <html:hidden property="id" />
    <h4 class="myAccordian">
        Permit Detail Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.name" />
            </label>
            <div class="controls">
                <html:text styleId="name"
                           property="name"
                           size="50"
                           maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.desc" />
            </label>
            <div class="controls">
                <html:textarea styleId="description"
                               property="description"
                               rows="8"
                               cols="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.avg.period" />
            </label>
            <div class="controls">
                <html:text property="avgPeriod"
                           styleId="avgPeriod" />
                <html:select property="avgPeriodUm"
                             styleId="avgPeriodUm">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_AVG_PERIOD_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.voc.limit" />
            </label>
            <div class="controls">
                <html:text property="vocLimit"
                           styleId="vocLimit" />
                <html:select property="vocLimitUm"
                             styleId="vocLimitUm">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_VOC_LIMIT_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.haps.limit" />
            </label>
            <div class="controls">
                <html:text property="hapsLimit"
                           styleId="hapsLimit" />
                <html:select property="hapsLimitUm"
                             styleId="hapsLimitUm">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_HAPS_LIMIT_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.detail.mmbtu.limit" />
            </label>
            <div class="controls">
                <html:text property="mmbtuLimit"
                           styleId="mmbtuLimit" />
                <html:select name="PermitDetailForm"
                             property="mmbtuLimitUm"
                             styleId="mmbtuLimitUm">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_MMBTU_LIMIT_LIST%>"
                                  property="code"
                                  labelProperty="description" />
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
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="PermitDetailForm" />
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
                        var avgPeriod = document.getElementById("avgPeriod").value;
                        var vocLimit = document.getElementById("vocLimit").value;
                        var hapsLimit = document.getElementById("hapsLimit").value;
                        var mmbtuLimit = document.getElementById("mmbtuLimit").value;
                        if (avgPeriod
                                    < 0
                                || avgPeriod
                                > 0)
                        {
                            if (document.getElementById("avgPeriodUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for Averaging Period.").dialog('open');
                                return false;
                            }
                        }
                        if (vocLimit
                                    < 0
                                || vocLimit
                                > 0)
                        {
                            if (document.getElementById("vocLimitUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for the VOC Limit.").dialog('open');
                                return false;
                            }
                        }
                        if (hapsLimit
                                    < 0
                                || hapsLimit
                                > 0)
                        {
                            if (document.getElementById("hapsLimitUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for the HAPS Limit.").dialog('open');
                                return false;
                            }
                        }
                        if (mmbtuLimit
                                    < 0
                                || mmbtuLimit
                                > 0)
                        {
                            if (document.getElementById("mmbtuLimitUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for the MMBTU Limit.").dialog('open');
                                return false;
                            }
                        }
                        return validatePermitDetailForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

