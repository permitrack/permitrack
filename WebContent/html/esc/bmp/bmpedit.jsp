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
           action="/bmpeditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:write name="BMPForm"
                    property="name" />
        <span class="label">
            <bean:write name="BMPForm"
                        property="categoryName" />
        </span>
    </legend></fieldset>
    <html:hidden property="id" />
    <html:hidden name="BMPForm"
                 property="categoryName" />
    <h4 class="myAccordian">
        BMP Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="bmp.category" />
            </label>
            <div class="controls">
                <div class="label">
                    <bean:write name="BMPForm"
                                property="categoryName" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="bmp.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             property="name"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="bmp.description" />
            </label>
            <div class="controls"><html:textarea styleId="description"
                                                 property="description"
                                                 cols="50"
                                                 rows="8" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="bmp.weblink" />
            </label>
            <div class="controls"><html:text styleId="weblink"
                                             property="weblink"
                                             size="75"
                                             maxlength="254" />
                <div>
                    (Include full web address, ex: http://www.google.com/)
                </div>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
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
            <html:javascript formName="BMPForm" />
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
                        return validate
                                < BMPForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>