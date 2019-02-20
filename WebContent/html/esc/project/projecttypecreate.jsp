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
    /*
        String
            strchecked='checked' =
            "";
    */
    Integer
            intExtenededOnLineAccessMonths =
            0;
    Integer
            intMonthsFromStart =
            0;
    /*
        String
            strMessage =
            "";
    */
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
    { // bmpCatValue found in the request
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
        /*
                if (blnSWMP)
                {
                    strchecked='checked' =
                        "checked='checked'";
                }
        */
        intExtenededOnLineAccessMonths =
                projectTypeValue.getExtendedOnlineAccessMonths();
        intMonthsFromStart =
                projectTypeValue.getMonthsFromStart();
    } // bmpCatValue found in the request%>
<html:form styleClass="form-horizontal"
           action="/projecttypecreate"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Project Type
    </legend></fieldset>
    <h4 class="myAccordian">
        Project Type Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="project.type.name" /> *
            </label>
            <div class="controls"><html:text property="name"
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
                                             value="<%= intExtenededOnLineAccessMonths.toString() %>" />
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
            <html:javascript formName="projectTypeForm" />
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
                //<!-- Script Begin//--------------------------------------------------------------------------------------------// Purpose : Set the previous value for the end point type// Inputs  :// Outputs :// Remarks :// History : 02/03/2006: Created New//--------------------------------------------------------------------------------------------
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
                { // toggle the Months from Start field on if EPT = 2, else off
                    var styleMSIE;
                    var styleNN;
                    if (document.getElementById("endPointTypeId").value
                            == '2')
                    {
                        if (document.getElementById)
                        {
                            styleMSIE
                                    = document.getElementById("MonthsFromStartRow").style;
                            styleMSIE.display
                                    = "block";
                        }
                        else if (document.layers)
                        {
                            styleNN
                                    = document.layers["MonthsFromStartRow"].style;
                            styleNN.display
                                    = "block";
                        }
                    }
                    else
                    {
                        if (document.getElementById)
                        {
                            styleMSIE
                                    = document.getElementById("MonthsFromStartRow").style;
                            styleMSIE.display
                                    = "none";
                        }
                        else if (document.layers)
                        {
                            styleNN
                                    = document.layers["MonthsFromStartRow"].style;
                            styleNN.display
                                    = "none";
                        }
                    }
                }
                initForm();//  Script End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
