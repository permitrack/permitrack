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
<%@ page import="com.sehinc.stormwater.server.plan.PlanService" %>
<bean:define id="mcms"
             name="reportForm"
             property="mcmList" />
<bean:define id="bmps"
             name="reportForm"
             property="bmpList" />
<bean:define id="users"
             name="reportForm"
             property="userList" />
<logic:present name="reportFormMS4"
               property="reportURL"
               scope="session">
    <bean:define id="reportType"
                 name='reportFormMS4'
                 property='reportType'
                 scope='session' />
    <bean:define id="dateType"
                 name="reportFormMS4"
                 property="dateType"
                 scope="session" />
    <div class="alert alert-block">
        <button type="button"
                class="close"
                data-dismiss="alert">&times;</button>
        <h4>
            <bean:message key="most.recent.report" />
        </h4>
        <p>
            <bean:message key="report.access.link" />
        </p>
        <br>
        <div class="well well-small"
             style="margin-bottom: 0;">
            <strong>
                <a href="<bean:write name="reportFormMS4" property="reportURL" scope="session" />">
                    <bean:write name="reportFormMS4"
                                property="title"
                                scope="session" />
                    -
                    <bean:message key='<%= "report.select." + pageContext.getAttribute("reportType") %>' />,
                    <bean:message key='<%= "report.select." + pageContext.getAttribute("dateType") %>' />
                </a>
            </strong>
        </div>
    </div>
</logic:present>
<html:form styleClass="form-horizontal"
           action="/reportrun"
           onsubmit="return validateForm();">
    <fieldset><legend>
        Reports for plan
        "<bean:write name="reportForm"
                     property="planName" />"
    </legend></fieldset>
    <h4>
        Type
    </h4>
    <div class="well">
        <div class="control-group">
            <label class="radio"
                   for="reportTypeAllMCM">
                <input type="radio"
                       id="reportTypeAllMCM"
                       name="reportType"
                       checked="checked"
                       value="all.mcm" />
                <bean:message key="report.select.all.mcm" />
            </label>
        </div>
        <logic:equal property="displayMPCAReportOption"
                     name="reportForm"
                     value="true">
            <div class="control-group">
                <label class="radio"
                       for="reportTypeMPCAPermitApp">
                    <input type="radio"
                           id="reportTypeMPCAPermitApp"
                           name="reportType"
                           value="mpca.permit" />
                    <bean:message key="report.select.mpca.permit" />
                </label>
            </div>
        </logic:equal>
        <div class="control-group">
            <label class="radio"
                   for="reportTypeOneMCM">
                <input type="radio"
                       id="reportTypeOneMCM"
                       name="reportType"
                       value="one.mcm" />
                <bean:message key="report.select.one.mcm" />
            </label>
        </div>
        <div class="control-group">
            <div class="control-label">
                MCM
            </div>
            <div class="controls">
                <html:select styleId="MCMId"
                             property="MCMId"
                             onchange="return toggleMCMSelect();">
                    <html:option value="0">
                        <bean:message key="report.mcm.select.option" />
                    </html:option>
                    <html:options collection="mcms"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="radio"
                   for="reportTypeOneBMP">
                <input type="radio"
                       id="reportTypeOneBMP"
                       name="reportType"
                       value="one.bmp" />
                <bean:message key="report.select.one.bmp" />
            </label>
        </div>
        <div class="control-group">
            <div class="control-label">
                BMP
            </div>
            <div class="controls">
                <html:select styleId="BMPId"
                             property="BMPId"
                             onchange="return toggleBMPSelect();">
                    <html:option value="0">
                        <bean:message key="report.bmp.select.option" />
                    </html:option>
                    <html:options collection="bmps"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
    </div>
    <h4>
        <bean:message key="report.select.dates" />
    </h4>
    <div class="well">
        <div class="control-group">
            <label class="radio"
                   for="timeperiodDateType">
                <input type="radio"
                       id="timeperiodDateType"
                       name="dateType"
                       value="timeperiod"
                       checked="checked" />
                <bean:message key="report.select.timeperiod" />
            </label>
        </div>
        <div class="control-group">
            <div class="control-label">
                Permit Period
            </div>
            <div class="controls controls-row">
                    <%= PlanService.renderPermitPeriodRadioBoxHTML(request,
                                                                   "timeperiodDateType") %>
            </div>
        </div>
        <div class="control-group">
            <div class="control-label">
                <bean:message key="report.select.goal.status" />
            </div>
            <div class="controls controls-row">
                <label class="radio"
                       for="radioStatusNone">
                    <input type="radio"
                           id="radioStatusNone"
                           name="goalStatus"
                           value="none.status"
                           checked="checked"
                           onchange="return toggletimeperiodDateType();" />
                    <bean:message key="report.select.no.filter.status" />
                </label>
                <label class="radio"
                       for="radioStatusAll">
                    <input type="radio"
                           id="radioStatusAll"
                           name="goalStatus"
                           value="all.status"
                           onchange="return toggletimeperiodDateType();" />
                    <bean:message key="report.select.all.status" />
                </label>
                <label class="radio"
                       for="radioStatusComplete">
                    <input type="radio"
                           id="radioStatusComplete"
                           name="goalStatus"
                           value="complete.status"
                           onchange="return toggletimeperiodDateType();" />
                    <bean:message key="report.select.complete.status" />
                </label>
                <label class="radio"
                       for="radioStatusIncomplete">
                    <input type="radio"
                           id="radioStatusIncomplete"
                           name="goalStatus"
                           value="incomplete.status"
                           onchange="return toggletimeperiodDateType();" />
                    <bean:message key="report.select.incomplete.status" />
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="radio"
                   for="customDateType">
                <input type="radio"
                       id="customDateType"
                       name="dateType"
                       value="custom.dates" />
                <bean:message key="report.select.date.range" />
            </label>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="startDateString"
                           property="startDateString"
                           size="12"
                           onchange="return toggleCustomDateType();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.end.date" />
            </label>
            <div class="controls">
                <html:text styleId="endDateString"
                           property="endDateString"
                           size="12"
                           onchange="return toggleCustomDateType();" />
            </div>
        </div>
        <div class="control-group">
            <label class="radio"
                   for="allDateType">
                <input type="radio"
                       id="allDateType"
                       name="dateType"
                       value="all.dates" />
                <bean:message key="report.select.all.activities" />
            </label>
        </div>
        <div class="control-group">
            <label class="radio"
                   for="noDateType">
                <input type="radio"
                       id="noDateType"
                       name="dateType"
                       value="no.activity" />
                <bean:message key="report.select.no.activity" />
            </label>
        </div>
    </div>
    <h4>
        <bean:message key="report.select.user.type" />
    </h4>
    <div class="well">
        <div class="control-group">
            <label class="radio"
                   for="allUserType">
                <input type="radio"
                       id="allUserType"
                       name="userType"
                       checked="checked"
                       value="all.users" />
                <bean:message key="report.select.all.users" />
            </label>
        </div>
        <label class="radio"
               for="oneUserType">
            <input type="radio"
                   id="oneUserType"
                   name="userType"
                   value="one.user" />
            <bean:message key="report.select.one.user" />
        </label>
        <div class="control-group">
            <div class="control-label">
                Owner
            </div>
            <div class="controls">
                <html:select styleId="userSelect"
                             name="reportForm"
                             property="userId"
                             onchange="return toggleUserSelect();">
                    <html:option value="0">
                        <bean:message key="report.user.select.option" /></html:option>
                    <html:options collection="users"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Run Report"
                             onclick="return isSelected();" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!--
                function validateForm()
                {
                    if (document.getElementById("oneUserType").checked)
                    {
                        if (document.getElementById("userSelect")
                                    == null
                                || document.getElementById("userSelect").options[0].selected)
                        {
                            $('#dialog').html("Please select an Owner from the list.").dialog('open');
                            return false;
                        }
                    }
                    if (document.getElementById("reportTypeOneBMP").checked)
                    {
                        if (document.getElementById("BMPId")
                                    == null
                                || document.getElementById("BMPId").options[0].selected)
                        {
                            $('#dialog').html("Please select a BMP from the list.").dialog('open');
                            return false;
                        }
                    }
                    if (document.getElementById("reportTypeOneMCM").checked)
                    {
                        if (document.getElementById("MCMId")
                                    == null
                                || document.getElementById("MCMId").options[0].selected)
                        {
                            $('#dialog').html("Please select an MCM from the list.").dialog('open');
                            return false;
                        }
                    }
                    return true;
                }
                function isSelected()
                {
                    var radioD = document.getElementById("customDateType");
                    if (radioD.checked)
                    {
                        var startDate = document.getElementById("startDateString");
                        var endDate = document.getElementById("endDateString");
                        if (startDate.value
                                == '')
                        {
                            $('#dialog').html("Please enter a Start Date.").dialog('open');
                            return false;
                        }
                        else if (endDate.value
                                == '')
                        {
                            $('#dialog').html("Please enter an End Date.").dialog('open');
                            return false;
                        }
                        else
                        {
                            var startD = new Date(startDate.value);
                            var endD = new Date(endDate.value);
                            if (endD.getTime()
                                    < startD.getTime())
                            {
                                $('#dialog').html("Date Selection is invalid. Start Date must precede End Date.").dialog('open');
                                return false;
                            }
                        }
                    }
                    return true;
                }
                function toggleUserSelect()
                {
                    document.getElementById("oneUserType").checked
                            = true;
                }
                function toggleMCMSelect()
                {
                    document.getElementById("reportTypeOneMCM").checked
                            = true;
                }
                function toggleBMPSelect()
                {
                    document.getElementById("reportTypeOneBMP").checked
                            = true;
                }
                function toggleCustomDateType()
                {
                    document.getElementById("customDateType").checked
                            = true;
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#startDateString").datepicker({autoclose:true});
                      $("#endDateString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

