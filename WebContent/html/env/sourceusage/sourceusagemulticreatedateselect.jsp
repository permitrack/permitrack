<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
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
<html:form styleClass="form-horizontal"
           action="/sourceusagemulticreatepage"
           onsubmit="return validateForm(this);"
           method="get">
    <fieldset><legend>
        New Set of Readings
        <small>
            for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
        </small>
    </legend></fieldset>
    <h4 class="myAccordian">
        Date Range
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.usage.period.start" />
            </label>
            <div class="controls">
                <html:text styleId="startDateString"
                           property="startDateString"
                           size="12"
                           maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.usage.period.end" />
            </label>
            <div class="controls">
                <html:text styleId="endDateString"
                           property="endDateString"
                           size="12"
                           maxlength="10" />
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
                             value="Continue" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="SourceUsageMultiForm" />
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
                        try
                        {
                            var periodStart = new Date(document.getElementById("startDateString").value);
                            var periodEnd = new Date(document.getElementById("endDateString").value);
                            if (periodEnd.getTime()
                                    < periodStart.getTime())
                            {
                                $('#dialog').html("Time period is invalid. Start Date must precede End Date.").dialog('open');
                                return false;
                            }
                        }
                        catch (e)
                        {
                        }
                        return validateSourceUsageMultiForm(form);
                    }
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
