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
           action="/substancecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Substance
    </legend></fieldset>
    <h4 class="myAccordian">
        Substance Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="substance.partNum" />
            </label>
            <div class="controls"><html:text styleId="partNum"
                                             property="partNum"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="substance.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             property="name"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="substance.substanceType" />
            </label>
            <div class="controls"><html:select styleId="typeSelect"
                                               name="SubstanceForm"
                                               property="substanceTypeCode">
                <html:option value="">Please Select a Substance Type...</html:option>
                <html:options collection="<%= SessionKeys.EV_SUBSTANCE_TYPE_LIST %>"
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
            <html:javascript formName="SubstanceForm" />
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
                        if (document.getElementById("typeSelect").options[0].selected)
                        {
                            $('#dialog').html("Please select a type from the list.").dialog('open');
                            return false;
                        }
                        return validateSubstanceForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>