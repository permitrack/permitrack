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
    Boolean
            rollingAccess =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ROLLING_REPORT_ACCESS);
    Boolean
            orgAccess =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ORG_STRUCTURE_ACCESS);
    Boolean
            semiAccess =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SEMI_ANNUAL_ACCESS);
    Boolean
            substAccess =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SUBSTANCE_ACCESS);
    Boolean
            aeiAccess =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.AIR_EM_INV_ACCESS);%>
<fieldset><legend>
    Reports
</legend></fieldset>
<logic:present name="reportFormENV"
               property="reportURL"
               scope="session">
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
                <a href="<bean:write name="reportFormENV" property="reportURL" scope="session" />">
                    <bean:write name="reportFormENV"
                                property="title"
                                scope="session" />
                </a>
            </strong>
        </div>
    </div>
</logic:present>
<html:form styleClass="form-horizontal"
           action="/reportrunaction">
    <%if (!substAccess)
    {%>
    <html:hidden styleId="startDateString"
                 name="ReportForm"
                 property="startDateString" />
    <html:hidden styleId="endDateString"
                 name="ReportForm"
                 property="endDateString" />
    <%}%>
    <%--
        <%
            if (rollingAccess)
            {
        %>
    --%>
    <div class="well">
        <label class="radio">
            <input type="radio"
                   id="rollingReport"
                   name="runReport"
                   value='<%= RequestKeys.EV_ROLLING_REPORT %>'
                   checked="checked"
                    <%= (!rollingAccess
                                 ? "disabled='disabled'"
                                 : "") %> />
            <bean:message key="rolling.report" />
            <p class="muted">
                <bean:message key="rolling.report.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="rolling.report.year" />
            </label>
            <div class="controls">
                <html:select property="year"
                             name="ReportForm"
                             styleId="year"
                             onfocus="toggleRolling();">
                    <html:option value="-1">Select...</html:option>
                    <html:option value="2008">2008</html:option>
                    <html:option value="2009">2009</html:option>
                    <html:option value="2010">2010</html:option>
                    <html:option value="2011">2011</html:option>
                    <html:option value="2012">2012</html:option>
                    <html:option value="2013">2013</html:option>
                    <html:option value="2014">2014</html:option>
                    <html:option value="2015">2015</html:option>
                    <html:option value="2016">2016</html:option>
                    <html:option value="2017">2017</html:option>
                    <html:option value="2018">2018</html:option>
                    <html:option value="2019">2019</html:option>
                </html:select>
            </div>
        </div>
    </div>
    <%--
        <%}%>
    --%>
    <%--
        <%
            if (orgAccess)
            {
        %>
    --%>
    <div class="well">
        <label class="radio">
            <input type="radio"
                   id="orgStructureReport"
                   name="runReport"
                   value='<%= RequestKeys.EV_ORG_STRUCTURE_REPORT %>'
                    <%= (!orgAccess
                                 ? "disabled='disabled'"
                                 : "") %> />
            <bean:message key="asset.structure.report" />
            <p class="muted">
                <bean:message key="asset.structure.report.desc" />
            </p>
        </label>
    </div>
    <%--
        <%}%>
    --%>
    <%--
        <%
            if (semiAccess)
            {
        %>
    --%>
    <div class="well">
        <label class="radio">
            <input type="radio"
                   id="semiAnnual"
                   name="runReport"
                   value='<%= RequestKeys.EV_SEMI_ANNUAL_REPORT %>'
                    <%= (!semiAccess
                                 ? "disabled='disabled'"
                                 : "") %> />
            <bean:message key="semi.annual.report" />
            <p class="muted">
                <bean:message key="semi.annual.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="semi.annual.year" />
            </label>
            <div class="controls">
                <html:select property="semiAnnualYear"
                             name="ReportForm"
                             styleId="semiAnnualYear"
                             onfocus="toggleSemi();">
                    <html:option value="-1">Select...</html:option>
                    <html:option value="2008">2008</html:option>
                    <html:option value="2009">2009</html:option>
                    <html:option value="2010">2010</html:option>
                    <html:option value="2011">2011</html:option>
                    <html:option value="2012">2012</html:option>
                    <html:option value="2013">2013</html:option>
                    <html:option value="2014">2014</html:option>
                    <html:option value="2015">2015</html:option>
                    <html:option value="2016">2016</html:option>
                    <html:option value="2017">2017</html:option>
                    <html:option value="2018">2018</html:option>
                    <html:option value="2019">2019</html:option>
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="semi.annual.permit" />
            </label>
            <div class="controls"><html:select name="ReportForm"
                                               property="permitId"
                                               styleId="permitId"
                                               onfocus="toggleSemi();">
                <html:option value="0">Select Permit...</html:option>
                <html:options collection="<%=SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT%>"
                              property="id"
                              labelProperty="name" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="semi.annual.period" />
            </label>
            <div class="controls">
                <label class="radio">
                    <input type="radio"
                           id="janThruJun"
                           name="period"
                           onfocus="toggleSemi();"
                           value='<%= RequestKeys.EV_SEMI_ANNUAL_REPORT_JAN_JUN %>' />
                    <bean:message key="semi.annual.jan.thru.jun" />
                </label>
                <label class="radio">
                    <input type="radio"
                           id="julThruDec"
                           name="period"
                           onfocus="toggleSemi();"
                           value='<%= RequestKeys.EV_SEMI_ANNUAL_REPORT_JUL_DEC %>' />
                    <bean:message key="semi.annual.jul.thru.dec" />
                </label>
            </div>
        </div>
    </div>
    <%--
        <%}%>
    --%>
    <%--
        <%
            if (substAccess)
            {
        %>
    --%>
    <div class="well">
        <label class="radio">
            <input type="radio"
                   id="substanceReport"
                   name="runReport"
                   value='<%= RequestKeys.EV_SUBSTANCE_REPORT %>'
                    <%= (!substAccess
                                 ? "disabled='disabled'"
                                 : "") %> />
            <bean:message key="substance.report" />
            <p class="muted">
                <bean:message key="substance.report.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="substance.report.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="startDateString"
                           name="ReportForm"
                           property="startDateString"
                           size="12"
                           maxlength="10"
                           onfocus="toggleSubstance();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="substance.report.end.date" />
            </label>
            <div class="controls">
                <html:text styleId="endDateString"
                           name="ReportForm"
                           property="endDateString"
                           size="12"
                           maxlength="10"
                           onfocus="toggleSubstance();" />
            </div>
        </div>
    </div>
    <%--
        <%}%>
    --%>
    <%--
        <%
            if (aeiAccess)
            {
        %>
    --%>
    <div class="well">
        <label class="radio">
            <input type="radio"
                   id="annual"
                   name="runReport"
                   value='<%= RequestKeys.EV_AIR_EM_INV_REPORT %>'
                    <%= (!aeiAccess
                                 ? "disabled='disabled'"
                                 : "") %> />
            <bean:message key="air.emissions.report" />
            <p class="muted">
                <bean:message key="air.emissions.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="air.emissions.year" />
            </label>
            <div class="controls"><html:select property="annualYear"
                                               name="ReportForm"
                                               styleId="annualYear"
                                               onfocus="toggleAnnual();">
                <html:option value="-1">Select...</html:option>
                <html:option value="2008">2008</html:option>
                <html:option value="2009">2009</html:option>
                <html:option value="2010">2010</html:option>
                <html:option value="2011">2011</html:option>
                <html:option value="2012">2012</html:option>
                <html:option value="2013">2013</html:option>
                <html:option value="2014">2014</html:option>
                <html:option value="2015">2015</html:option>
                <html:option value="2016">2016</html:option>
                <html:option value="2017">2017</html:option>
				<html:option value="2018">2018</html:option>
				<html:option value="2019">2019</html:option>
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="air.emissions.permit" />
            </label>
            <div class="controls"><html:select name="ReportForm"
                                               property="annualPermitId"
                                               styleId="annualPermitId"
                                               onfocus="toggleAnnual();">
                <html:option value="0">Select Permit...</html:option>
                <html:options collection="<%= SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT %>"
                              property="id"
                              labelProperty="name" />
            </html:select>
            </div>
        </div>
    </div>
    <%--
        <%}%>
    --%>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:select styleClass="input-small"
                             name="ReportForm"
                             property="reportFormat">
                    <html:option value="PDF">
                        PDF
                    </html:option>
                </html:select>
                <%if (!rollingAccess
                      && !aeiAccess
                      && !substAccess
                      && !orgAccess
                      && !semiAccess)
                {%>
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Run Report"
                             disabled="true" />
                <%}
                else
                {%>
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Run Report"
                             onclick="return isSelected();" />
                <%}%>
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
            function toggleRolling()
            {
                var radioB = document.getElementById("rollingReport");
                radioB.checked
                        = true;
            }
            function toggleStructure()
            {
                var radioB = document.getElementById("orgStructureReport");
                radioB.checked
                        = true;
            }
            function toggleSemi()
            {
                var radioB = document.getElementById("semiAnnual");
                radioB.checked
                        = true;
            }
            function toggleSubstance()
            {
                var radioB = document.getElementById("substanceReport");
                radioB.checked
                        = true;
            }
            function toggleAnnual()
            {
                var radioB = document.getElementById("annual");
                radioB.checked
                        = true;
            }
            function isSelected()
            {
                var radioA = document.getElementById("rollingReport");
                var radioB = document.getElementById("orgStructureReport");
                var radioC = document.getElementById("semiAnnual");
                var radioD = document.getElementById("substanceReport");
                var radioE = document.getElementById("annual");
                if (radioA.checked)
                {
                    var year = document.getElementById("year");
                    if (year.value
                            == '-1')
                    {
                        $('#dialog').html("Please select a Year for which to run the Rolling Report.").dialog('open');
                        return false;
                    }
                }
                else if (radioB.checked)
                {
                    //nothing to check
                }
                else if (radioC.checked)
                {
                    var firstB = document.getElementById("janThruJun");
                    var secondB = document.getElementById("julThruDec");
                    var yearB = document.getElementById("semiAnnualYear");
                    var permit = document.getElementById("permitId");
                    if (firstB.checked)
                    {
                        if (yearB.value
                                == '-1')
                        {
                            $('#dialog').html("Please select a Year for which to run the Semi Annual Report.").dialog('open');
                            return false;
                        }
                        if (permit.value
                                == 0)
                        {
                            $('#dialog').html("Please select a Permit for which to run the Semi Annual Report.").dialog('open');
                            return false;
                        }
                    }
                    else if (secondB.checked)
                    {
                        if (yearB.value
                                == '-1')
                        {
                            $('#dialog').html("Please select a Year for which to run the Semi Annual Report.").dialog('open');
                            return false;
                        }
                        if (permit.value
                                == 0)
                        {
                            $('#dialog').html("Please select a Permit for which to run the Semi Annual Report.").dialog('open');
                            return false;
                        }
                    }
                    else
                    {
                        $('#dialog').html("Please select a Time Period for which to run the Semi Annual Report.").dialog('open');
                        return false;
                    }
                }
                else if (radioD.checked)
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
                            $('#dialog').html("Date Selection is invalid.  Start Date must precede End Date.").dialog('open');
                            return false;
                        }
                    }
                }
                else if (radioE.checked)
                {
                    yearB
                            = document.getElementById("annualYear");
                    permit
                            = document.getElementById("annualPermitId");
                    if (yearB.value
                            == '-1')
                    {
                        $('#dialog').html("Please select a Year for which to run the Semi Annual Report.").dialog('open');
                        return false;
                    }
                    if (permit.value
                            == 0)
                    {
                        $('#dialog').html("Please select a Permit for which to run the Semi Annual Report.").dialog('open');
                        return false;
                    }
                }
                else
                {
                    $('#dialog').html("Please select a report to run.").dialog('open');
                    return false;
                }
                return true;
            }// -->
        </script>
        <script type="text/javascript">
            <!--
            $(function ()
              {
                  $("#startDateString").datepicker({autoclose:true});
                  $("#endDateString").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
