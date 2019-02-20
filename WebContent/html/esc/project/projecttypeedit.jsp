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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.value.project.ProjectTypeValue" %>
<%
    Integer
            intId =
            0;
    String
            strName =
            "";
    String
            strDescription =
            "";
    Integer
            intEndPointTypeId =
            0;
    Boolean
            blnSWMP =
            false;
    Integer
            intExtendedOnLineAccessMonths =
            0;
    Integer
            intMonthsFromStart =
            0;
    ProjectTypeValue
            projectTypeValue;
    pageContext.setAttribute("endPointTypeList",
                             request.getSession()
                                     .getAttribute(SessionKeys.PROJECT_TYPE_END_POINT_TYPE_LIST));
    projectTypeValue =
            (ProjectTypeValue) request.getSession()
                    .getAttribute(SessionKeys.PROJECT_TYPE_VALUE);
    if (projectTypeValue
        != null)
    {
        intId =
                projectTypeValue.getId();
        strName =
                projectTypeValue.getName();
        if (strName
            == null)
        {
            strName =
                    "";
        }
        strDescription =
                projectTypeValue.getDescription();
        if (strDescription
            == null)
        {
            strDescription =
                    "";
        }
        intEndPointTypeId =
                projectTypeValue.getEndPointTypeId();
        if (intEndPointTypeId
            == null)
        {
            intEndPointTypeId =
                    0;
        }
        blnSWMP =
                projectTypeValue.getSwmp();
        if (blnSWMP
            == null)
        {
            blnSWMP =
                    false;
        }
        if (blnSWMP)
        {
        }
        intExtendedOnLineAccessMonths =
                projectTypeValue.getExtendedOnlineAccessMonths();
        if (intExtendedOnLineAccessMonths
            == null)
        {
            intExtendedOnLineAccessMonths =
                    0;
        }
        intMonthsFromStart =
                projectTypeValue.getMonthsFromStart();
        if (intMonthsFromStart
            == null)
        {
            intMonthsFromStart =
                    0;
        }
    }%>
<html:form styleClass="form-horizontal"
           action="/projecttypeedit"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <%= strName %>
    </legend></fieldset>
    <html:hidden name="projectTypeForm"
                 property="id"
                 value="<%= intId.toString() %>" />
    <h4 class="myAccordian">
        Project Type Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="project.type.name" /> *
            </label>
            <div class="controls">
                <html:text property="name"
                           styleId="name"
                           size="42"
                           maxlength="30"
                           value="<%= strName %>" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="project.type.description" />
            </label>
            <div class="controls"><html:text property="description"
                                             styleId="description"
                                             size="80"
                                             maxlength="200"
                                             value="<%= strDescription %>" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="project.type.ept" /> *
            </label>
            <div class="controls"><html:select name="projectTypeForm"
                                               property="endPointTypeId"
                                               styleId="endPointTypeId"
                                               onchange="return endPointTypeOnChange()">
                <html:options collection="endPointTypeList"
                              property="id"
                              labelProperty="name" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="project.type.swmp" />
            </label>
            <div class="controls"><html:checkbox name="projectTypeForm"
                                                 property="swmp"
                                                 styleId="swmp" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="project.type.eolam.abbv" />
            </label>
            <div class="controls"><html:text property="extendedOnlineAccessMonths"
                                             styleId="extendedOnlineAccessMonths"
                                             size="4"
                                             maxlength="4"
                                             value="<%= intExtendedOnLineAccessMonths.toString() %>" />
                <bean:message key="project.type.eolam" />
            </div>
        </div>
        <div class="control-group"
             id="MonthsFromStartRow">
            <label class="control-label">
                <bean:message key="project.type.mfs.abbv" />
            </label>
            <div class="controls"><html:text property="monthsFromStart"
                                             styleId="monthsFromStart"
                                             size="4"
                                             maxlength="4"
                                             value="<%= intMonthsFromStart.toString() %>" />
                <bean:message key="project.type.mfs" />
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
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
            <html:javascript formName="projectTypeForm" />
            <!-- This JavaScript function calls the validate<form> method of the
            Struts Validation JavaScript included above. This is necessary
            because we are unable to portal encode the form name on the
            html:form onsubmit event. -->
            <script type="text/javascript">
                //<!--
                bCancel
                        = false;
                function validateForm(form)
                {
                    if (bCancel)
                    {
                        return true;
                    }
                    else
                    {
                        if (document.getElementById("extendedOnlineAccessMonths").value
                                == '')
                        {
                            document.getElementById("extendedOnlineAccessMonths").value
                                    = 0;
                        }
                        return validateProjectTypeForm(form);
                    }
                }// -->
            </script>
            <script type="text/javascript">
                //<!-- Script Begin
                function initForm()
                {
                    document.getElementById("endPointTypeId").value
                            = '<%= intEndPointTypeId.toString() %>';
                <% if (blnSWMP) { %>
                    document.getElementById("swmp").checked
                            = true;
                    //            $.uniform.update(document.getElementById("swmp"));
                <% } %>
                    endPointTypeOnChange();
                }
                function endPointTypeOnChange()
                {
                    // toggle the Months from Start field on if EPT = 2, else off
                    if (document.getElementById("endPointTypeId").value
                            == '2')
                    {
                        if (document.getElementById)
                        {
                            var styleMSIE = document.getElementById("MonthsFromStartRow").style;
                            styleMSIE.display
                                    = "table-row";
                        }
                        else if (document.layers)
                        {
                            var styleNN = document.layers["MonthsFromStartRow"].style;
                            styleNN.display
                                    = "table-row";
                        }
                    }
                    else
                    {
                        if (document.getElementById)
                        {
                            var styleMSIE = document.getElementById("MonthsFromStartRow").style;
                            styleMSIE.display
                                    = "none";
                        }
                        else if (document.layers)
                        {
                            var styleNN = document.layers["MonthsFromStartRow"].style;
                            styleNN.display
                                    = "none";
                        }
                    }
                }
                initForm(); // Script End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
