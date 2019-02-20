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
           action="/scceditaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:write name="SccForm"
                    property="number" />
        <bean:write name="SccForm"
                    property="description" />
    </legend></fieldset>
    <html:hidden property="id" />
    <h4 class="myAccordian">
        SCC Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="scc.number" />
            </label>
            <div class="controls"><html:text property="number"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.desc" />
            </label>
            <div class="controls"><html:text property="description"
                                             size="50"
                                             maxlength="200" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.majGrp" />
            </label>
            <div class="controls"><html:text property="majIndustrialGroup"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.rawMat" />
            </label>
            <div class="controls"><html:text property="rawMaterial"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.emitProc" />
            </label>
            <div class="controls"><html:text property="emittingProcess"
                                             size="50"
                                             maxlength="50" />
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
            <html:javascript formName="SccForm" />
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
                        return validateSccForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>