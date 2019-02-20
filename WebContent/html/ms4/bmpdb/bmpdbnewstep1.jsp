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
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<html:form styleClass="form-horizontal"
           action="/bmpdbnewstep2action"
           onsubmit="return validateForm();"
           method="get">
    <div class="control-group">
        <label class="control-label"
               for="bmpForm">
            <bean:message key="bmpdb.formtype.select.heading" />
        </label>
        <div class="controls">
            <html:select name="bmpForm"
                         property="formType">
                <html:option value="">Select a BMP Form Type...</html:option>
                <html:options collection="<%= RequestKeys.BMP_DB_FORM_TYPE_LIST %>"
                              property="id"
                              labelProperty="name" />
            </html:select>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <html:submit styleClass="btn btn-success btn-large"
                         property="submit"
                         value="Continue" />
        </div>
    </div>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Validate the formType field. -->
            <script type="text/javascript">
                //<!--
                function validateForm()
                {
                    if (document.bmpForm.formType.value
                            == '')
                    {
                        $('#dialog').html("Please select a form type.").dialog('open');
                        return false;
                    }
                    return true;
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
