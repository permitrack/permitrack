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
                 com.sehinc.common.service.user.UserService,
                 com.sehinc.common.util.BeanUtil,
                 com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.common.value.client.ClientValue" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.server.plan.GoalService" %>
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
    pageContext.setAttribute("activityFormList",
                             clientValue
                             == null
                                     ? GoalService.getGoalActivityFormsList()
                                     : GoalService.getGoalActivityFormsList(clientValue.getId()));
    pageContext.setAttribute("activityFrequencyList",
                             GoalService.getGoalActivityFrequencyList());
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "goal"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
    pageContext.setAttribute("isClientAdmin",
                             SecurityManager.getSecurityManager(request)
                                     .getSecurityLevelId()
                             <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID);%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateAll(this);">
    <fieldset>
        <fieldset><legend>
            <logic:equal name="mode"
                         value="create">
                New Goal
            </logic:equal>
            <logic:notEqual name="mode"
                            value="create">
                <bean:write name="goalForm"
                            property="name" />
            </logic:notEqual>
            <logic:notEqual name="mode"
                            value="create">
                <logic:notEqual name="type"
                                value="library">
                    <logic:notEqual name="type"
                                    value="template">
                            <html:link styleClass="btn btn-mini btn-success"
                                       action="/goalactivitycreateaction"
                                       paramName="goalForm"
                                       paramId="id"
                                       paramProperty="id">
                                + New Activity
                            </html:link>
                    </logic:notEqual>
                </logic:notEqual>
            </logic:notEqual>
        </legend></fieldset>
    </fieldset>
    <html:hidden property="id" />
    <logic:notEqual name="type"
                    value="template">
        <logic:notEqual name="type"
                        value="library">
            <html:hidden name="goalForm"
                         property="goalActivityFrequencyDateType" />
        </logic:notEqual>
    </logic:notEqual>
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="goal.name" />
        </label>
        <div class="controls">
            <a href="#"
               id="name"
               data-type="text"
               data-original-title='<bean:message key="goal.name" />'>
                <bean:write name="goalForm"
                            property="name" />
            </a>
            <html:hidden property="name"
                         styleId="name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="number">
            <bean:message key="goal.number" />
        </label>
        <div class="controls">
            <a href="#"
               id="number"
               data-type="text"
               data-original-title='<bean:message key="goal.number" />'>
                <bean:write name="goalForm"
                            property="number" />
            </a>
            <html:hidden property="number"
                         styleId="number" />
        </div>
    </div>
    <logic:notEqual name="type"
                    value="template">
        <logic:notEqual name="type"
                        value="library">
            <div class="control-group">
                <label class="control-label"
                       for="ownerId">
                    <bean:message key="goal.owner" />
                </label>
                <div class="controls">
                    <a href="#"
                       id="ownerId"
                       data-type="select"
                       data-original-title='<bean:message key="goal.owner" />'
                       data-autotext='always'></a>
                    <html:hidden property="ownerId"
                                 styleId="ownerId" />
                </div>
            </div>
        </logic:notEqual>
    </logic:notEqual>
    <div class="control-group">
        <label class="control-label"
               for="description">
            <bean:message key="goal.description" />
        </label>
        <div class="controls">
            <div id="description"
                 data-type="wysihtml5">
                <bean:write name="goalForm"
                            property="description"
                            filter="false" />
            </div>
            <html:hidden property="description"
                         styleId="description"
                         alt="" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="goalActivityFormId">
            <bean:message key="goal.activity.form" />
        </label>
        <div class="controls">
            <a href="#"
               id="goalActivityFormId"
               data-type="select"
               data-original-title='<bean:message key="goal.activity.form" />'
               data-autotext='always'></a>
            <html:hidden property="goalActivityFormId"
                         styleId="goalActivityFormId" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="goalActivityFrequencyId">
            <bean:message key="goal.activity.frequency" />
        </label>
        <div class="controls">
            <a href="#"
               id="goalActivityFrequencyId"
               data-type="select"
               data-original-title='<bean:message key="goal.activity.frequency" />'
               data-autotext='always'></a>
            <html:hidden property="goalActivityFrequencyId"
                         styleId="goalActivityFrequencyId" />
            <logic:notEqual name="type"
                            value="template">
                <logic:notEqual name="type"
                                value="library">
                    starting
                    <a href="#"
                       id="goalDateString"
                       data-type="date"
                       data-format="mm/dd/yyyy"
                       data-viewformat="mm/dd/yyyy"
                       data-original-title='<bean:message key="goal.start.date" />'>
                        <bean:write name="goalForm"
                                    property="goalDateString" />
                    </a>
                    <html:hidden property="goalDateString"
                                 styleId="goalDateString" />
                </logic:notEqual>
            </logic:notEqual>
        </div>
    </div>
    <logic:notEqual name="type"
                    value="template">
        <logic:notEqual name="type"
                        value="library">
            <div class="control-group">
                <label class="control-label"
                       for="notifyCheckbox">
                    <bean:message key="goal.notify" />
                </label>
                <div class="controls">
                    <label class="checkbox">
                        <html:checkbox styleId="notifyCheckbox"
                                       name="goalForm"
                                       property="notify"
                                       value="true" />
                        <bean:message key="goal.notify.email" />
                    </label>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"
                       for="notifyDaysInAdvance">
                    <bean:message key="goal.notify.days.in.advance" />
                </label>
                <div class="controls">
                    <a href="#"
                       id="notifyDaysInAdvance"
                       data-type="text"
                       data-original-title='<bean:message key="goal.notify.days.in.advance" />'>
                        <bean:write name="goalForm"
                                    property="notifyDaysInAdvance" />
                    </a>
                    <html:hidden property="notifyDaysInAdvance"
                                 styleId="notifyDaysInAdvance" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    <bean:message key="goal.active.periods" />
                </label>
                <div class="controls">
                    <%=
                    GoalService.renderEditActivePermitPeriodCheckboxHTML(request,
                                                                         null,
                                                                         "goalForm")
                    %>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    <bean:message key="goal.period.status" />
                </label>
                <div class="controls">
                    <%=
                    GoalService.renderEditStatusPermitPeriodCheckboxHTML(request,
                                                                         null,
                                                                         "goalForm")
                    %>
                </div>
            </div>
        </logic:notEqual>
    </logic:notEqual>
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
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="goalForm" />
            <!-- Bootstrap X-Editable -->
            <script type="text/javascript">
                //<!--
                function getDataValue(id)
                {
                <logic:notEqual name="type"
                                value="template">
                <logic:notEqual name="type"
                                value="library">
                    if (id
                            == 'ownerId')
                    {
                        return <bean:write name="goalForm" property="ownerId" />;
                    }
                    else
                            </logic:notEqual>
                            </logic:notEqual>

                    if (id
                            == 'goalActivityFormId')
                    {
                        return <bean:write name="goalForm" property="goalActivityFormId" />;
                    }
                    else if (id
                            == 'goalActivityFrequencyId')
                    {
                        return <bean:write name="goalForm" property="goalActivityFrequencyId" />;
                    }
                    return null;
                }
                function getDataSource(id)
                {
                    if (id
                            == 'ownerId')
                    {
                        return getList('ownerList');
                    }
                    else if (id
                            == 'goalActivityFormId')
                    {
                        return getList('activityFormList');
                    }
                    else if (id
                            == 'goalActivityFrequencyId')
                    {
                        return getList('activityFrequencyList');
                    }
                    return null;
                }
                function getList(name)
                {
                    var list;
                    if (name
                            == 'ownerList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("ownerList"))%>
                        ]
                    }
                    else if (name
                            == 'activityFormList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("activityFormList"))%>
                        ]
                    }
                    else if (name
                            == 'activityFrequencyList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("activityFrequencyList"))%>
                        ]
                    }
                    return list;
                }//-->
            </script>
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    return validateGoalForm(form);
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
