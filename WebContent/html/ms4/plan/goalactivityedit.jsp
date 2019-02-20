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
<%@ page import="com.sehinc.stormwater.action.base.SessionKeys" %>
<%
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "goalactivity"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateAll(this);">
    <fieldset><legend>
        <logic:equal name="mode"
                     value="create">
            New Activity
        </logic:equal>
        <logic:notEqual name="mode"
                        value="create">
            <bean:write name="goalActivityForm"
                        property="name" />
        </logic:notEqual>
    </legend></fieldset>
    <html:hidden property="id" />
    <html:hidden property="goalId" />
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="goalactivity.name" />
        </label>
        <div class="controls">
            <a href="#"
               id="name"
               data-type="text"
               data-original-title='<bean:message key="goalactivity.name" />'>
                <bean:write name="goalActivityForm"
                            property="name" />
            </a>
            <html:hidden property="name"
                         styleId="name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="description">
            <bean:message key="goalactivity.description" />
        </label>
        <div class="controls">
            <div id="description"
                 data-type="wysihtml5">
                <bean:write name="goalActivityForm"
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
               for="activityDateString">
            <bean:message key="goalactivity.activity.date" />
        </label>
        <div class="controls">
            <a href="#"
               id="activityDateString"
               data-type="date"
               data-format="mm/dd/yyyy"
               data-viewformat="mm/dd/yyyy"
               data-original-title='<bean:message key="goalactivity.activity.date" />'>
                <bean:write name="goalActivityForm"
                            property="activityDateString" />
            </a>
            <html:hidden property="activityDateString"
                         styleId="activityDateString" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="goalactivity.file.name" />
        </label>
        <div class="controls controls-row">
            <logic:notEmpty name="<%= SessionKeys.FILE_LIST %>">
                <logic:iterate id="aFile"
                               name="<%= SessionKeys.FILE_LIST %>"
                               type="com.sehinc.stormwater.value.plan.GoalActivityFileLocationValue">
                    <p>
                        <html:link styleClass="btn btn-mini btn-danger warn-delete"
                                   action="/goalactivitydeletefilelocation.do"
                                   paramId="fileId"
                                   paramName="aFile"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <a href='<bean:write name="aFile" property="downloadURL"/>'>
                            <bean:write name="aFile"
                                        property="name" />
                        </a>
                    </p>
                </logic:iterate>
            </logic:notEmpty>
            <input class="btn btn-mini btn-success"
                   type="submit"
                   name="submit"
                   value="<bean:message key="goal.activity.file.upload.button" />" />
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
            <html:javascript formName="goalActivityForm" />
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    return validateGoalActivityForm(form);
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>


