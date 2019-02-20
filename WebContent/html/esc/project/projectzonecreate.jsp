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
           action="/projectzonecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:message key="project.zone.information.heading" />
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="project.zone.name" /> *
        </label>
        <div class="controls"><html:text styleId="name"
                                         name="projectZoneForm"
                                         property="name"
                                         size="30"
                                         maxlength="30"
                                         alt="Group Name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="project.zone.description" />
        </label>
        <div class="controls"><html:textarea styleId="description"
                                             name="projectZoneForm"
                                             property="description"
                                             cols="50"
                                             rows="4" />
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
                             property="submit"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="projectZoneForm" />
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
                        return validateProjectZoneForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
