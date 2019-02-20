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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/adminreportrunaction">
    <fieldset><legend>
        Administrator Reports
    </legend></fieldset>
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
    <logic:present name="reportForm"
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
            <div class="well well-small"
                 style="margin-bottom: 0;">
                <strong>
                    <a href="<bean:write name="reportForm" property="reportURL" scope="session" />">
                        <bean:write name="reportForm"
                                    property="title"
                                    scope="session" />
                    </a>
                </strong>
            </div>
        </div>
    </logic:present>
    <div class="well">
        <div class="control-group">
            <label class="control-label">
                <bean:message key="report.admin.client.select" />
            </label>
            <div class="controls">
                <html:select name="reportForm"
                             property="clientId">
                    <html:option value="0">All Clients</html:option>
                    <html:options collection="<%= SessionKeys.ADMIN_CLIENT_SELECT_LIST %>"
                                  property="id"
                                  labelProperty="name" />
                </html:select>
            </div>
        </div>
    </div>
    <div class="well">
        <label class="radio"
               for="invoiceSummaryReport">
            <input type="radio"
                   id="invoiceSummaryReport"
                   name="runReport"
                   value='<%= RequestKeys.EC_ADMIN_INVOICE_SUMMARY_REPORT %>'
                   checked="checked" />
            <bean:message key="report.invoice.summary" />
            <p class="muted">
                <bean:message key="report.invoice.summary.desc" />
            </p>
        </label>
        <div class="control-group">
            <label class="control-label"
                   for="startDateString">
                <bean:message key="report.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="startDateString"
                           property="startDateString"
                           size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="endDateString">
                <bean:message key="report.end.date" />
            </label>
            <div class="controls">
                <html:text styleId="endDateString"
                           property="endDateString"
                           size="12" />
            </div>
        </div>
    </div>
    <div class="well">
        <label class="radio"
               for="projectSummaryReport">
            <input type="radio"
                   id="projectSummaryReport"
                   name="runReport"
                   value='<%= RequestKeys.EC_ADMIN_PROJECT_SUMMARY_REPORT %>' />
            <bean:message key="report.project.summary" />
            <p class="muted">
                <bean:message key="report.project.summary.desc" />
            </p>
        </label>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:select styleClass="input-small"
                             name="reportForm"
                             property="reportFormat">
                    <html:option value="PDF">PDF</html:option>
                    <html:option value="XLS">MS Excel</html:option>
                </html:select>
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Run Report" />
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
            $(function ()
              {
                  $("#startDateString").datepicker({autoclose:true});
                  $("#endDateString").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
