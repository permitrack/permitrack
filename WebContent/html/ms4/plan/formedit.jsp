<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ page import="com.sehinc.common.CommonConstants,
                 com.sehinc.common.forms.CustomFormBean" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "form"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
    String[]
            html =
            StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                    .equals("create")
                    ? CustomFormBean.renderEdit(request,
                                                (Integer) request.getAttribute(RequestKeys.FORM_ID))
                    : CustomFormBean.renderEdit(request,
                                                (Integer) request.getAttribute(RequestKeys.FORM_INSTANCE_ID));
%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           enctype="multipart/form-data"
           onsubmit="return validateAll(this);">
    <input type="hidden"
           name="clientId"
           id="clientId"
           value="<%= request.getAttribute(RequestKeys.CLIENT_ID) %>" />
    <input type="hidden"
           name="planId"
           id="planId"
           value="<%= request.getAttribute(RequestKeys.PLAN_ID) %>" />
    <input type="hidden"
           name="goalActivityId"
           id="goalActivityId"
           value="<%= request.getAttribute(RequestKeys.ACTIVITY_ID) %>" />
    <input type="hidden"
           name="formId"
           id="formId"
           value="<%= request.getAttribute(RequestKeys.FORM_ID) %>" />
    <input type="hidden"
           name="formInstanceId"
           id="formInstanceId"
           value="<%= request.getAttribute(RequestKeys.FORM_INSTANCE_ID) %>" />
    <input type="hidden"
           name="goalId"
           id="goalId"
           value="<%= request.getAttribute(RequestKeys.GOAL_ID) %>" />
    <input type="hidden"
           name="goalActivityFormId"
           id="goalActivityFormId"
           value="<%= request.getAttribute(RequestKeys.GOAL_ACTIVITY_FORM_ID) %>" />
    <%= html[0] %>
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
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <%@ include file="../../scripts.validation.jsp" %>
        <script type="text/javascript">
            function jcv_retrieveFormName(form)
            {
                var formName;
                if (form.getAttributeNode)
                {
                    if (form.getAttributeNode("id")
                            && form.getAttributeNode("id").value)
                    {
                        formName
                                = form.getAttributeNode("id").value;
                    }
                    else
                    {
                        formName
                                = form.getAttributeNode("name").value;
                    }
                }
                else if (form.getAttribute)
                {
                    if (form.getAttribute("id"))
                    {
                        formName
                                = form.getAttribute("id");
                    }
                    else
                    {
                        formName
                                = form.attributes["name"];
                    }
                }
                else
                {
                    if (form.id)
                    {
                        formName
                                = form.id;
                    }
                    else
                    {
                        formName
                                = form.name;
                    }
                }
                return formName;
            }
        </script>
        <%= html[1] %>
    </tiles:put>
</tiles:definition>
