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
           action="/scclibrarycreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Library SCC
    </legend></fieldset>
    <h4 class="myAccordian">
        SCC Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="scc.library.number" />
            </label>
            <div class="controls"><html:text name="SccLibraryForm"
                                             property="number"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.library.desc" />
            </label>
            <div class="controls"><html:text name="SccLibraryForm"
                                             property="description"
                                             size="50"
                                             maxlength="200" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.library.majGrp" />
            </label>
            <div class="controls"><html:text name="SccLibraryForm"
                                             property="majIndustrialGroup"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.library.rawMat" />
            </label>
            <div class="controls"><html:text name="SccLibraryForm"
                                             property="rawMaterial"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="scc.library.emitProc" />
            </label>
            <div class="controls"><html:text name="SccLibraryForm"
                                             property="emittingProcess"
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
            <html:javascript formName="SccLibraryForm" />
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
                        return validateSccLibraryForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>