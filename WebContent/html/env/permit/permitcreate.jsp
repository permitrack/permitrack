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
           action="/permitcreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Permit
    </legend></fieldset>
    <h4 class="myAccordian">
        Permit Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="permit.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             property="name"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.desc" />
            </label>
            <div class="controls"><html:textarea styleId="description"
                                                 property="description"
                                                 rows="8"
                                                 cols="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.active.date" />
            </label>
            <div class="controls"><html:text styleId="activeTsString"
                                             property="activeTsString"
                                             size="12"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="permit.inactive.date" />
            </label>
            <div class="controls"><html:text styleId="inactiveTsString"
                                             property="inactiveTsString"
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
            <html:javascript formName="PermitForm" />
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
                        var activeDate = new Date(document.getElementById("activeTsString").value);
                        var inactiveDate = new Date(document.getElementById("inactiveTsString").value);
                        if (inactiveDate.getTime()
                                < activeDate.getTime())
                        {
                            $('#dialog').html("Permit time period is invalid. Active Date must precede Inactive Date.").dialog('open');
                            return false;
                        }
                        return validatePermitForm(form);
                    }
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#activeTsString").datepicker({autoclose:true});
                      $("#inactiveTsString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
