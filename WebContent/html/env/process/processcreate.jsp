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
           action="/processcreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Process
    </legend></fieldset>
    <h4 class="myAccordian">
        Process Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="process.number" />
            </label>
            <div class="controls"><html:text property="processNumber"
                                             name="ProcessForm" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.name" />
            </label>
            <div class="controls"><html:text property="name"
                                             name="ProcessForm" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="process.description" />
            </label>
            <div class="controls"><html:text property="description"
                                             name="ProcessForm" />
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
            <html:javascript formName="ProcessForm" />
            <script type="text/javascript">
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
