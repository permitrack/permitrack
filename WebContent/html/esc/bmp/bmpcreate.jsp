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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/bmpsaveaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New BMP
    </legend></fieldset>
    <h4 class="myAccordian">
        BMP Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="bmp.category" />
            </label>
            <div class="controls controls-row">
                <label class="radio"
                       for="isNewCategoryNo">
                    <html:radio styleId="isNewCategoryNo"
                                property="isNewCategoryText"
                                value="false">
                    </html:radio>
                    <bean:message key="bmp.select.category" />
                </label>
                <html:select name="BMPForm"
                             property="BMPCategoryId"
                             styleId="categorySelect"
                             onfocus="document.getElementById('isNewCategoryNo').checked = true; document.getElementById('isNewCategoryYes').checked = false; //$.uniform.update(document.getElementById('isNewCategoryNo')); //$.uniform.update(document.getElementById('isNewCategoryYes'));">
                    <html:option value="0">Select a Category...</html:option>
                    <html:options collection="<%= SessionKeys.EC_BMP_CATEGORY_LIST_BY_CLIENT %>"
                                  property="id"
                                  labelProperty="name" />
                </html:select>
                <div>
                    <b class="muted">or</b>
                </div>
                <label class="radio"
                       for="isNewCategoryYes">
                    <html:radio styleId="isNewCategoryYes"
                                property="isNewCategoryText"
                                value="true">
                    </html:radio>
                    <bean:message key="bmp.new.category" />
                </label>
                <html:text styleId="categoryName"
                           property="categoryName"
                           size="50"
                           maxlength="50"
                           onfocus="document.getElementById('isNewCategoryYes').checked = true; document.getElementById('isNewCategoryNo').checked = false; //$.uniform.update(document.getElementById('isNewCategoryYes')); //$.uniform.update(document.getElementById('isNewCategoryNo'));" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="bmp.name" />
            </label>
            <div class="controls">
                <html:text styleId="name"
                           property="name"
                           size="50"
                           maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="bmp.description" />
            </label>
            <div class="controls">
                <html:textarea styleId="description"
                               property="description"
                               cols="50"
                               rows="8" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="bmp.weblink" />
            </label>
            <div class="controls">
                <html:text styleId="weblink"
                           property="weblink"
                           size="75"
                           maxlength="254" />
                <span class="help-inline">
                    Include full web address, for example
                    <code>http://www.google.com/</code>
                </span>
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
            <html:javascript formName="BMPForm" />
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
                        return validateBMPForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

