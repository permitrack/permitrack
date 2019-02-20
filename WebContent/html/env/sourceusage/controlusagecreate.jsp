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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<%
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             pageContext.getAttribute(CommonConstants.MODE)
                                     .equals("create")
                                     ? "controlusagecreateaction"
                                     : "controlusageeditaction");
%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateForm(this);">
    <fieldset>
        <legend>
            <logic:equal name="mode"
                         value="create">
                New Control Malfunction Entry
            </logic:equal>
            <logic:notEqual name="mode"
                            value="create">
                Edit Control Malfunction Entry
            </logic:notEqual>
            <small>
                for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
            </small>
        </legend>
    </fieldset>
    <html:hidden property="meterReading"
                 value="0.00" />
    <html:hidden property="sourceUsageId" />
    <h4 class="myAccordian">
        Control Malfunction
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Control
            </label>
            <div class="controls">
                <html:select name="SourceUsageForm"
                             property="assetSourceId"
                             styleId="assetSourceId">
                    <html:option value="0">
                        Please Select an Asset Control...
                    </html:option>
                    <html:options collection="<%=SessionKeys.EV_ASSET_SUBSTANCE_LIST%>"
                                  property="assetSubstanceId"
                                  labelProperty="substanceTypeName" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Start Date
            </label>
            <div class="controls">
                <html:text name="SourceUsageForm"
                           styleId="periodStartTsString"
                           property="periodStartTsString"
                           size="12"
                           maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                End Date
            </label>
            <div class="controls">
                <html:text name="SourceUsageForm"
                           styleId="periodEndTsString"
                           property="periodEndTsString"
                           size="12"
                           maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Description
            </label>
            <div class="controls">
                <html:textarea styleId="description"
                               name="SourceUsageForm"
                               property="description" />
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel"
                             onclick="isCancelled=true;" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="SourceUsageForm" />
            <script type="text/javascript">
                //<!--
                var isCancelled = false;
                function validateForm(form)
                {
                    if (isCancelled)
                    {
                        return true;
                    }
                    else
                    {
                        var startDate = document.getElementById("periodStartTsString");
                        var endDate = document.getElementById("periodEndTsString");
                        if (startDate.value
                                == '')
                        {
                            $('#dialog').html("Please enter a start date.").dialog('open');
                            return false;
                        }
                        else if (endDate.value
                                == '')
                        {
                            $('#dialog').html("Please enter an end date.").dialog('open');
                            return false;
                        }
                        else
                        {
                            var startD = new Date(startDate.value);
                            var endD = new Date(endDate.value);
                            if (endD.getTime()
                                    < startD.getTime())
                            {
                                $('#dialog').html("Date Selection is invalid.  Start Date must precede End Date.").dialog('open');
                                return false;
                            }
                            /*
                             if (document.getElementById("unitOfMeasureCd").options[0].selected)
                             {
                             $('#dialog').html("Please select a Unit of Measure from the list.").dialog('open');
                             return false;
                             }
                             */
                        }
                        //                return validateControlUsageForm(form);
                    }
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#periodStartTsString").datepicker({autoclose:true});
                      $("#periodEndTsString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
