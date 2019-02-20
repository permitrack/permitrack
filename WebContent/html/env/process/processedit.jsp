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
           action="/processeditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Edit Process
    </legend></fieldset>
    <html:hidden property="id" />
    <h4 class="myAccordian">
        Process Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.number" />
            </label>
            <div class="controls"><html:text styleId="processNumber"
                                             property="processNumber"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             property="name"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.description" />
            </label>
            <div class="controls"><html:text styleId="description"
                                             property="description"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.parent.process" />
            </label>
            <div class="controls"><html:select styleId="parentSelect"
                                               name="ProcessForm"
                                               property="parentProcessId">
                <html:option value="">None</html:option>
                <html:options collection="<%= SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT %>"
                              property="id"
                              labelProperty="numberAndName" />
            </html:select>
                (Optional)
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
            <html:javascript formName="ProcessForm" />
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
                        return validateProcessForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>