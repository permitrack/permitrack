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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<logic:present name="reportFormESC"
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
                <a href="<bean:write name="reportFormESC" property="reportURL" scope="session" />">
                    <bean:write name="reportFormESC"
                                property="title"
                                scope="session" />
                </a>
            </strong>
        </div>
    </div>
</logic:present>
<html:form styleClass="form-horizontal"
           action="/reportrunaction">
    <div class="well">
        <label class="radio"
               for="projectSummaryReport">
            <input type="radio"
                   id="projectSummaryReport"
                   name="runReport"
                   value='<%= RequestKeys.EC_PROJECT_SUMMARY_REPORT %>'
                   checked="checked" />
            <bean:message key="report.project.summary" />
            <p class="muted">
                <bean:message key="report.project.summary.desc" />
            </p>
        </label>
    </div>
    <div class="well">
        <label class="radio"
               for="invoiceSummaryReport">
            <input type="radio"
                   id="invoiceSummaryReport"
                   name="runReport"
                   value='<%= RequestKeys.EC_INVOICE_SUMMARY_REPORT %>' />
            <bean:message key="report.invoice.summary" />
            <p class="muted">
                <bean:message key="report.invoice.summary.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="startDateString"
                           property="startDateString"
                           size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.end.date" />
            </label>
            <div class="controls"><html:text styleId="endDateString"
                                             property="endDateString"
                                             size="12" />
            </div>
        </div>
    </div>
    <div class="well">
        <label class="radio"
               for="inspectionSummaryReport">
            <input type="radio"
                   id="inspectionSummaryReport"
                   name="runReport"
                   value='<%= RequestKeys.EC_INSPECTION_SUMMARY_REPORT %>' />
            <bean:message key="report.inspection.summary" />
            <p class="muted">
                <bean:message key="report.inspection.summary.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="inspStartDateString"
                           property="inspStartDateString"
                           size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.end.date" />
            </label>
            <div class="controls">
                <html:text styleId="inspEndDateString"
                           property="inspEndDateString"
                           size="12" />
            </div>
        </div>
    </div>
    <div class="well">
        <label class="radio"
               for="clientExportAll">
            <input type="radio"
                   id="clientExportAll"
                   name="runReport"
                   value='<%= RequestKeys.EC_CLIENT_EXPORT_ALL %>' />
            <bean:message key="report.client.export.all" />
            <p class="muted">
                <bean:message key="report.client.export.all.desc" />
            </p>
        </label>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:select name="reportForm"
                             property="reportFormat">
                    <html:option value="PDF">PDF</html:option>
                    <html:option value="XLS">MS Excel</html:option>
                </html:select>
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Run Report"
                             onclick="return isSelected();" />
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
            //<!--
            function isSelected()
            {
                var radioA = document.getElementById("projectSummaryReport");
                var radioB = document.getElementById("invoiceSummaryReport");
                var radioC = document.getElementById("inspectionSummaryReport");
                var radioD = document.getElementById("clientExportAll");
                if (radioA.checked || radioD.checked)
                {
                    // Do nothing, allow report to run.
                }
                else if (radioB.checked)
                {
                    start
                            = document.getElementById("startDateString");
                    end
                            = document.getElementById("endDateString");
                    if (start.value
                            == '')
                    {
                        $('#dialog').html("Please enter a valid start date.").dialog('open');
                        return false;
                    }
                    if (end.value
                            == '')
                    {
                        $('#dialog').html("Please enter a valid end date.").dialog('open');
                        return false;
                    }
                }
                else if (radioC.checked)
                {
                    var start = document.getElementById("inspStartDateString");
                    var end = document.getElementById("inspEndDateString");
                    if (start.value
                            == '')
                    {
                        $('#dialog').html("Please enter a valid start date.").dialog('open');
                        return false;
                    }
                    if (end.value
                            == '')
                    {
                        $('#dialog').html("Please enter a valid end date.").dialog('open');
                        return false;
                    }
                }
                else
                {
                    $('#dialog').html("Please select a report to run.").dialog('open');
                    return false;
                }
                return true;
            }
            $(function ()
              {
                  $("#startDateString").datepicker({autoclose:true});
                  $("#endDateString").datepicker({autoclose:true});
                  $("#inspStartDateString").datepicker({autoclose:true});
                  $("#inspEndDateString").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
