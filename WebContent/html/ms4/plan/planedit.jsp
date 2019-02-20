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
<%@ page import="com.sehinc.common.security.SecurityManager" %>
<%@ page import="com.sehinc.common.util.BeanUtil" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.server.permitperiod.PermitPeriodService" %>
<%@ page import="com.sehinc.stormwater.server.plan.PlanService" %>
<%@ page import="java.util.ArrayList" %>
<%pageContext.setAttribute(CommonConstants.MODE,
                           request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "plan"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
    pageContext.setAttribute("planList",
                             PlanService.getPlanTemplateLabelValueBeanList());
    pageContext.setAttribute("permitPeriodList",
                             PermitPeriodService.getPermitPeriodLabelValueBeanList());
    pageContext.setAttribute("permitTypeList",
                             PermitPeriodService.getPermitTypeLabelValueBeanList());
    pageContext.setAttribute("isClientAdmin",
                             SecurityManager.getSecurityManager(request)
                                     .getSecurityLevelId()
                             <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID);%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateAll(this);">
    <fieldset><legend>
        <logic:equal name="mode"
                     value="create">
            New Plan
        </logic:equal>
        <logic:notEqual name="mode"
                        value="create">
            <bean:write name="planForm"
                        property="name" />
        </logic:notEqual>
        <logic:notEqual name="mode"
                        value="create">
            <logic:equal name="isClientAdmin"
                         value="true">
                <html:link styleClass="btn btn-mini btn-success"
                           action='<%= "mcm" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                           paramName="planForm"
                           paramId="id"
                           paramProperty="id">
                    + New MCM
                </html:link>
            </logic:equal>
        </logic:notEqual>
    </legend></fieldset>
    <logic:equal name="mode"
                 value="create">
        <logic:notEqual name="type"
                        value="template">
            <div class="control-group">
                <label class="control-label"
                       for="type1">
                    <bean:message key="plan.template" />
                </label>
                <div class="controls">
                    <label class="radio">
                        <input type="radio"
                               name="type"
                               id="type1"
                               value="default"
                               checked
                               onclick="togglePlanDefaultSelect();">
                        Default plan
                    </label>
                    <label class="radio">
                        <input type="radio"
                               name="type"
                               id="type2"
                               value="template"
                               onclick="togglePlanDefaultSelect();">
                        Use a template
                    </label>
                    <a href="#"
                       id="id"
                       data-type="select"
                       data-original-title='<bean:message key="plan.template" />'
                       data-autotext='always'
                       data-disabled='true'></a>
                    <html:hidden property="id"
                                 styleId="id" />
                </div>
            </div>
        </logic:notEqual>
    </logic:equal>
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="plan.name" />
        </label>
        <div class="controls">
            <a href="#"
               id="name"
               data-type="text"
               data-original-title='<bean:message key="plan.name" />'>
                <bean:write name="planForm"
                            property="name" />
            </a>
            <html:hidden property="name"
                         styleId="name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="description">
            <bean:message key="plan.description" />
        </label>
        <div class="controls">
            <div id="description"
                 data-type="wysihtml5">
                <bean:write name="planForm"
                            property="description"
                            filter="false" />
            </div>
            <html:hidden property="description"
                         styleId="description" />
        </div>
    </div>
    <logic:notEqual name="type"
                    value="template">
        <div class="control-group">
            <label class="control-label"
                   for="permitNumber">
                <bean:message key="plan.permitnumber" />
            </label>
            <div class="controls">
                <a href="#"
                   id="permitNumber"
                   data-type="text"
                   data-original-title='<bean:message key="plan.permitnumber" />'>
                    <bean:write name="planForm"
                                property="permitNumber" />
                </a>
                <html:hidden property="permitNumber"
                             styleId="permitNumber" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="plan.permitperiod" />
            </label>
            <div class="controls">
                <a href="#"
                   id="permitPeriodId"
                   data-type="select"
                   data-original-title='<bean:message key="plan.permitperiod" />'
                   data-autotext='always'
                   data-disabled='<%=StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE)).equals("create") ? "false" : "true" %>'></a>
                <html:hidden property="permitPeriodId"
                             styleId="permitPeriodId" />
            </div>
        </div>
    </logic:notEqual>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="plan.permittype" />
        </label>
        <div class="controls">
            <a href="#"
               id="permitTypeId"
               data-type="select"
               data-original-title='<bean:message key="plan.permittype" />'
               data-autotext='always'
               data-disabled='<%=StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE)).equals("create") ? "false" : "true" %>'></a>
            <html:hidden property="permitTypeId"
                         styleId="permitTypeId" />
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
            <html:javascript formName="planForm" />
            <!-- Bootstrap X-Editable -->
            <script type="text/javascript">
                //<!--
                function getDataValue(id)
                {
                <logic:notEqual name="type"
                                value="template">
                    if (id
                            == 'permitPeriodId')
                    {
                        return <bean:write name="planForm" property="permitPeriodId" />;
                    }
                    else
                            </logic:notEqual>
                    if (id
                            == 'permitTypeId')
                    {
                        return <bean:write name="planForm" property="permitTypeId" />;
                    }
                    else if (id
                            == 'id')
                    {
                        return <bean:write name="planForm" property="id" />;
                    }
                    return null;
                }
                function getDataSource(id)
                {
                <logic:notEqual name="type"
                                value="template">
                    if (id
                            == 'permitPeriodId')
                    {
                        return getList('permitPeriodList');
                    }
                    else
                            </logic:notEqual>

                    if (id
                            == 'permitTypeId')
                    {
                        return getList('permitTypeList');
                    }
                    else if (id
                            == 'id')
                    {
                        return getList('planList');
                    }
                    return null;
                }
                function getList(name)
                {
                    var list;
                <logic:notEqual name="type"
                value="template">
                    if (name
                            == 'permitPeriodList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("permitPeriodList"))%>
                        ]
                    }
                    else

                            </logic:notEqual>
                    if (name
                            == 'permitTypeList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("permitTypeList"))%>
                        ]
                    }
                    else if (name
                            == 'planList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("planList"))%>
                        ]
                    }
                    return list;
                }//-->
            </script>
            <script type="text/javascript">
                function validateForm(form)
                {
                    return validatePlanForm(form);
                }
            </script>
            <!-- Plan Template selection -->
            <script type="text/javascript">
                //<!--
                function togglePlanDefaultSelect()
                {
                    if (planForm.type[0].checked)
                    {
                        $("a#id").editable("disable");
                    }
                    else
                    {
                        $("a#id").editable("enable");
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
