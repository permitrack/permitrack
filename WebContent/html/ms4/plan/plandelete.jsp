<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
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
<%
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             ""
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             + StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE)));
%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <tiles:getAsString name="title"
                           ignore="true" />
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label"
               for="deleteReason">
            <bean:message key="plan.delete.explanation" />
        </label>
        <div class="controls">
            <html:textarea property="deleteReason"
                           styleId="deleteReason"
                           cols="50"
                           rows="10"
                           alt="" />
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <html:submit styleClass="btn btn-large btn-danger"
                         property="submit"
                         value="Delete" />
        </div>
    </div>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="planDeleteForm" />
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    return validatePlanDeleteForm(form);
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
