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
<%@ page import="com.sehinc.common.CommonConstants,
                 com.sehinc.common.action.base.SessionService,
                 com.sehinc.common.security.SecurityManager,
                 com.sehinc.common.service.user.UserService" %>
<%@ page import="com.sehinc.common.util.BeanUtil" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.common.value.client.ClientValue" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="java.util.ArrayList" %>
<%
    ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
    pageContext.setAttribute("ownerList",
                             clientValue
                             == null
                                     ? null
                                     : UserService.getUserBeanList(clientValue));
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "mcm"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
    pageContext.setAttribute("isClientAdmin",
                             SecurityManager.getSecurityManager(request)
                                     .getSecurityLevelId()
                             <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID);
    pageContext.setAttribute("permitTypeId",
                             request.getAttribute("permitTypeId"));%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateAll(this);">
    <fieldset><legend>
        <logic:equal name="mode"
                     value="create">
            New Minimum Control Measure
        </logic:equal>
        <logic:notEqual name="mode"
                        value="create">
            <bean:write name="mcmForm"
                        property="name" />
        </logic:notEqual>
        <logic:notEqual name="mode"
                        value="create">
            <logic:equal name="isClientAdmin"
                         value="true">
                <div class="btn-group">
                    <a class="btn btn-mini btn-success dropdown-toggle"
                       data-toggle="dropdown"
                       href="#">
                        + New BMP
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <logic:equal name="permitTypeId"
                                     value="1">
                            <li>
                                <html:link action='<%= "bmp0" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                                           paramName="mcmForm"
                                           paramId="id"
                                           paramProperty="id">
                                    + New BMP
                                </html:link>
                            </li>
                        </logic:equal>
                        <logic:equal name="permitTypeId"
                                     value="2">
                        <li>
                            <html:link action='<%= "bmp1" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                                       paramName="mcmForm"
                                       paramId="id"
                                       paramProperty="id">
                                + New BMP Form 1
                            </html:link>
                        </li>
                        <li>
                            <html:link action='<%= "bmp2" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                                       paramName="mcmForm"
                                       paramId="id"
                                       paramProperty="id">
                                + New BMP Form 2
                            </html:link>
                            </logic:equal>
                        </li>
                        <li>
                            <html:link action='<%= "bmpdb" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                                       paramName="mcmForm"
                                       paramId="id"
                                       paramProperty="id">
                                + New BMP from Library
                            </html:link>
                        </li>
                    </ul>
                </div>
            </logic:equal>
        </logic:notEqual>
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="mcm.name" />
        </label>
        <div class="controls">
            <a href="#"
               id="name"
               data-type="text"
               data-original-title='<bean:message key="mcm.name" />'>
                <bean:write name="mcmForm"
                            property="name" />
            </a>
            <html:hidden property="name"
                         styleId="name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="number">
            <bean:message key="mcm.number" />
        </label>
        <div class="controls">
            <a href="#"
               id="number"
               data-type="text"
               data-original-title='<bean:message key="mcm.number" />'>
                <bean:write name="mcmForm"
                            property="number" />
            </a>
            <html:hidden property="number"
                         styleId="number" />
        </div>
    </div>
    <logic:notEqual name="type"
                    value="template">
        <div class="control-group">
            <label class="control-label"
                   for="ownerId">
                <bean:message key="mcm.owner" />
            </label>
            <div class="controls">
                <a href="#"
                   id="ownerId"
                   data-type="select"
                   data-original-title='<bean:message key="mcm.owner" />'
                   data-autotext='always'></a>
                <html:hidden property="ownerId"
                             styleId="ownerId" />
            </div>
        </div>
    </logic:notEqual>
    <div class="control-group">
        <label class="control-label"
               for="requiredDescription">
            <bean:message key="mcm.description" />
        </label>
        <div class="controls">
            <div id="requiredDescription"
                 data-type="wysihtml5">
                <bean:write name="mcmForm"
                            property="requiredDescription"
                            filter="false" />
            </div>
            <html:hidden property="requiredDescription"
                         styleId="requiredDescription"
                         alt="" />
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="tertiaryMenu"
                   direct="true">
            <div class="span6">
                <tiles:get name="tertiaryMenu"
                           flush="false" />
            </div>
        </tiles:put>
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:submit styleClass="btn btn-large btn-success pull-right"
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
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="mcmForm" />
            <!-- Bootstrap X-Editable -->
            <script type="text/javascript">
                //<!--
                function getDataValue(id)
                {
                    <logic:notEqual name="type"
                                    value="template">
                        if (id
                                == 'ownerId')
                        {
                            return <bean:write name="mcmForm" property="ownerId" />;
                        }
                    </logic:notEqual>
                    return null;
                }
                function getDataSource(id)
                {
                    <logic:notEqual name="type"
                                    value="template">
                        if (id
                                == 'ownerId')
                        {
                            return getList('ownerList');
                        }
                    </logic:notEqual>
                    return null;
                }
                function getList(name)
                {
                    var list;
                    <logic:notEqual name="type"
                                    value="template">
                        if (name
                                == 'ownerList')
                        {
                            list
                                    =
                            [
                                <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("ownerList"))%>
                            ]
                        }
                    </logic:notEqual>
                    return list;
                }//-->
            </script>
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    return validateMcmForm(form);
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
