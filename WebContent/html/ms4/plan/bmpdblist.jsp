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
<%@ page import="com.sehinc.stormwater.server.bmpdb.BMPDBService" %>
<%
    pageContext.setAttribute("categoryList",
                             BMPDBService.getBmpDbCategoryLabelValueList());
%>
<html:form styleClass="form-horizontal"
           action="/bmpdbcategoryadd">
    <fieldset><legend>
        <bean:message key="bmpdb.new.bmp" />
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label"
               for="categorySelect">
            <bean:message key="bmpdb.category.select.heading" />
        </label>
        <div class="controls">
            <html:select styleId="categorySelect"
                         name="bmpDbListForm"
                         property="bmpDbCategoryId"
                         disabled="false">
                <html:option value="0">New Category</html:option>
                <html:options collection="categoryList"
                              property="value"
                              labelProperty="label" />
            </html:select>
        </div>
    </div>
    <p>Or</p>
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="bmpdb.new.category.name" />
        </label>
        <div class="controls">
            <html:text property="name"
                       styleId="name"
                       size="50"
                       maxlength="100"
                       alt="" />
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Add" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="bmpDbListForm" />
            <!-- This JavaScript function calls the validate<form> method of the
            Struts Validation JavaScript included above. This is necessary
            because we are unable to portal encode the form name on the
            html:form onsubmit event. -->
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    return validateBmpDbListForm(form);
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
