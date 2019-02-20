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
<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.server.bmpdb.BMPDBService" %>
<bean:define id="categoryList"
             name="bmpDbSelectForm"
             property="bmpDbCategoryList" />
<%
    pageContext.setAttribute("categoryList",
                             BMPDBService.getBmpDbCategoryLabelValueList());
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "bmpdb"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             + "createaction");
%>
<html:form action='<%= (String) pageContext.getAttribute("action") %>'
           method="get">
    <fieldset><legend>
        <bean:message key="mcm.new.bmp.heading" />
    </legend></fieldset>
    <logic:notEmpty name="categoryList">
        <div class="control-group">
            <label class="control-label"
                   for="categorySelect">
                <bean:message key="bmpdb.category.select.bmp.heading" />
            </label>
            <div class="controls">
                <html:select styleId="categorySelect"
                             name="bmpDbSelectForm"
                             property="bmpDbCategoryId"
                             onchange="BmpDbSelectFormSubmit();">
                    <html:option value="0">All Categories</html:option>
                    <html:options collection="categoryList"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
    </logic:notEmpty>
    <logic:empty name="categoryList">
        <p class="text-warning">
            <bean:message key="mcm.select.bmpdb.empty.list" />
        </p>
    </logic:empty>
    <logic:notEmpty name="categoryList">
        <logic:iterate id="bmpLabelValue"
                       name="bmpDbSelectForm"
                       property="bmpDbList">
            <div class="control-group">
                <div class="controls">
                    <html:link styleClass="btn btn-mini btn-success"
                               action='<%= "bmpdb" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "create" %>'
                               paramId="bmp_db_id"
                               paramName="bmpLabelValue"
                               paramProperty="value">
                        + Add
                    </html:link>
                    <bean:write name="bmpLabelValue"
                                property="label" />
                </div>
            </div>
        </logic:iterate>
    </logic:notEmpty>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- This JavaScript function calls the form submit() action.
            This is necessary because we are unable to portal encode
            the form name on the onchange event. -->
            <script type="text/javascript">
                function BmpDbSelectFormSubmit()
                {
                    $("form").submit();
                }
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
