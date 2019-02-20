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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/sourceusagecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset>
        <legend>
            New Source Usage Reading
            <small>
                for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
            </small>
        </legend>
    </fieldset>
    <h4 class="myAccordian">
        Source Usage
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Source
            </label>
            <div class="controls">
                <html:select name="SourceUsageForm"
                             property="assetSourceId"
                             styleId="assetSourceId">
                    <html:option value="0">
                        Please Select an Asset Source...
                    </html:option>
                    <html:options collection="<%=SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST%>"
                                  property="assetSourceId"
                                  labelProperty="displayName" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Reading
            </label>
            <div class="controls">
                <html:text name="SourceUsageForm"
                           property="meterReading" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Unit of Measure
            </label>
            <div class="controls">
                <html:select name="SourceUsageForm"
                             property="unitOfMeasureCd"
                             styleId="unitOfMeasureCd">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_SRC_USAGE_CD_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Start Date
            </label>
            <div class="controls"><html:text name="SourceUsageForm"
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
            <label class="control-label"
                   for="transferRate">
                <bean:message key="asset.transfer.rate" />
            </label>
            <div class="controls">
                <html:text styleId="transferRate"
                           name="SourceUsageForm"
                           property="transferRate"
                           size="15" />
                <span class="help-inline">
                    <bean:message key="source.substance.ratio.note" />
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="alert">
                    <strong>Note</strong> If the selected Source does not have a Transfer Rate, or you want to use the default Transfer Rate of <code>0.55</code>, leave this field blank.
                </div>
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
                            if (document.getElementById("unitOfMeasureCd").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure from the list.").dialog('open');
                                return false;
                            }
                        }
                        return validateSourceUsageForm(form);
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
