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
<%@ page import="com.sehinc.common.util.BeanUtil,
                 com.sehinc.stormwater.server.bmpdb.BMPDBService" %>
<%pageContext.setAttribute("yesNo",
                           BeanUtil.getYesNo());
    pageContext.setAttribute("categoryList",
                             BMPDBService.getBmpDbCategoryLabelValueList());%>
<html:form action="/bmpdblistpageaction"
           method="get">
    <fieldset><legend>
        <bean:message key="bmpdb.list.heading" />
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="bmpdb.category.filter.heading" />
        </label>
        <div class="controls">
            <html:select name="bmpDbListForm"
                         property="bmpDbCategoryId"
                         onchange="categorySelectSubmit();">
                <html:option value="0">All Categories</html:option>
                <html:options collection="categoryList"
                              property="value"
                              labelProperty="label" />
            </html:select>
        </div>
    </div>
    <ul class="nav nav-pills nav-stacked">
        <logic:iterate id="bmpLabelValue"
                       name="bmpDbListForm"
                       property="bmpDbList">
            <li>
                <html:link action="/bmpdbviewaction.do"
                           paramId="bmp_db_id"
                           paramName="bmpLabelValue"
                           paramProperty="value">
                    <bean:write name="bmpLabelValue"
                                property="label" />
                </html:link>
            </li>
        </logic:iterate>
    </ul>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!--
                function categorySelectSubmit()
                {
                    bmpDbListForm.submit();
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
