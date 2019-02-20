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
<%@ page import="com.sehinc.stormwater.server.plan.PlanService" %>
<logic:present name="PublishURL"
               scope="session">
    <div class="alert alert-block">
        <h4>
            Most Recent Published Version
        </h4>
        <p>
            <bean:message key="report.access.link" />
        </p>
        <br>
        <div class="well well-small"
             style="margin-bottom: 0;">
            <div>
                <strong>
                    <a href="javascript:openWindow('<%=(String)request.getSession().getAttribute("PublishURL")%>')">
                        <bean:write name="planPublishForm"
                                    property="planName" />
                    </a>
                </strong>
            </div>
            Published by
            <bean:write name="planPublishForm"
                        property="publishedUser" />
            on
            <bean:write name="planPublishForm"
                        property="publishedDate" />
            for date range
            <bean:write name="planPublishForm"
                        property="prevStartDate" />
            -
            <bean:write name="planPublishForm"
                        property="prevEndDate" />
        </div>
    </div>
</logic:present>
<html:form styleClass="form-horizontal"
           action="/planpublish"
           onsubmit="return validateForm(this);"
           method="get">
    <fieldset><legend>
        Publish
        <bean:write name="planPublishForm"
                    property="planName" />
    </legend></fieldset>
    <html:hidden name="planPublishForm"
                 property="planName" />
    <div class="well">
        <div class="control-group">
            <label class="radio"
                   for="dateOptionPermitTimePeriod">
                <input type="radio"
                       id="dateOptionPermitTimePeriod"
                       name="dateOption"
                       value="permitTimePeriod"
                       checked="checked" />
                <bean:message key="plan.report.dateoption.permitperiod" />
            </label>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="plan.permitperiod" />
            </label>
            <div class="controls controls-row">
                <%= PlanService.renderPermitPeriodRadioBoxHTML(request,
                                                               "dateOptionPermitTimePeriod") %>
            </div>
        </div>
        <div class="control-group">
            <label class="radio">
                <input type="radio"
                       id="dateOptionCustom"
                       name="dateOption"
                       value="custom" />
                <bean:message key="plan.report.dateoption.custom" />
            </label>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="plan.start.date" />
            </label>
            <div class="controls">
                <html:text styleId="startDate"
                           name="planPublishForm"
                           property="startDate"
                           size="12"
                           onclick="toggleDateOptionCustom();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="plan.end.date" />
            </label>
            <div class="controls">
                <html:text styleId="endDate"
                           name="planPublishForm"
                           property="endDate"
                           size="12"
                           onclick="toggleDateOptionCustom();" />
            </div>
        </div>
    </div>
    <logic:equal name="planPublishForm"
                 property="isSystemAdmin"
                 value="true">
        <div class="well">
            <div class="control-group">
                <label class="checkbox"
                       for="isExport">
                    <html:checkbox name="planPublishForm"
                                   property="isExport"
                                   styleId="isExport"
                                   title="Export"
                                   value="true" />
                    <bean:message key="plan.export" />
                    <p class="muted">
                        <bean:message key="plan.export.info" />
                    </p>
                </label>
            </div>
        </div>
    </logic:equal>
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
                             value="Publish Report" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    var dateRangeToggled = document.getElementById("dateOptionCustom").checked;
                    var permitTimePeriodToggled = document.getElementById("dateOptionPermitTimePeriod").checked;
                    if (!dateRangeToggled
                            && !permitTimePeriodToggled)
                    {
                        $('#dialog').html("Please select a date option.").dialog('open');
                        return false;
                    }
                    if (dateRangeToggled)
                    {
                        if (!validateDate("startDate"))
                        {
                            $('#dialog').html("Start Date must be a valid date. (MM/dd/yyyy)").dialog('open');
                            return false;
                        }
                        if (!validateDate("endDate"))
                        {
                            $('#dialog').html("End Date must be a valid date. (MM/dd/yyyy)").dialog('open');
                            return false;
                        }
                    }
                    return true;
                }
                function toggleDateOptionCustom()
                {
                    document.getElementById("dateOptionCustom").checked
                            = true;
                }
                function validateDate(id)
                {
                    var field = document.getElementById(id);
                    var value = field.value;
                    var datePattern = 'MM/dd/yyyy';
                    if ((field.type
                                 == 'text'
                            || field.type
                            == 'textarea')
                                && (value.length
                            > 0)
                            && (datePattern.length
                            > 0))
                    {
                        var MONTH = "MM";
                        var DAY = "dd";
                        var YEAR = "yyyy";
                        var orderMonth = datePattern.indexOf(MONTH);
                        var orderDay = datePattern.indexOf(DAY);
                        var orderYear = datePattern.indexOf(YEAR);
                        if ((orderDay
                                     < orderYear
                                && orderDay
                                > orderMonth))
                        {
                            var iDelim1 = orderMonth
                                    + MONTH.length;
                            var iDelim2 = orderDay
                                    + DAY.length;
                            var delim1 = datePattern.substring(iDelim1,
                                                               iDelim1
                                                                       + 1);
                            var delim2 = datePattern.substring(iDelim2,
                                                               iDelim2
                                                                       + 1);
                            if (iDelim1
                                        == orderDay
                                    && iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                            }
                            else if (iDelim1
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            else if (iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{4})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            var matched = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[2],
                                                 matched[1],
                                                 matched[3]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else if ((orderMonth
                                          < orderYear
                                && orderMonth
                                > orderDay))
                        {
                            var iDelim1 = orderDay
                                    + DAY.length;
                            var iDelim2 = orderMonth
                                    + MONTH.length;
                            var delim1 = datePattern.substring(iDelim1,
                                                               iDelim1
                                                                       + 1);
                            var delim2 = datePattern.substring(iDelim2,
                                                               iDelim2
                                                                       + 1);
                            if (iDelim1
                                        == orderMonth
                                    && iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                            }
                            else if (iDelim1
                                    == orderMonth)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            else if (iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{4})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            var matched = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[1],
                                                 matched[2],
                                                 matched[3]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else if ((orderMonth
                                          > orderYear
                                && orderMonth
                                < orderDay))
                        {
                            var iDelim1 = orderYear
                                    + YEAR.length;
                            var iDelim2 = orderMonth
                                    + MONTH.length;
                            var delim1 = datePattern.substring(iDelim1,
                                                               iDelim1
                                                                       + 1);
                            var delim2 = datePattern.substring(iDelim2,
                                                               iDelim2
                                                                       + 1);
                            if (iDelim1
                                        == orderMonth
                                    && iDelim2
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                            }
                            else if (iDelim1
                                    == orderMonth)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{2})$");
                            }
                            else if (iDelim2
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{2})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{2})$");
                            }
                            var matched = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[3],
                                                 matched[2],
                                                 matched[1]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    return true;
                }
                function isValidDate(day, month, year)
                {
                    if (month
                                < 1
                            || month
                            > 12)
                    {
                        return false;
                    }
                    if (day
                                < 1
                            || day
                            > 31)
                    {
                        return false;
                    }
                    if ((month
                                 == 4
                                 || month
                            == 6
                                 || month
                            == 9
                            || month
                            == 11)
                            && (day
                            == 31))
                    {
                        return false;
                    }
                    if (month
                            == 2)
                    {
                        var leap = (year
                                            % 4
                                            == 0
                                && (year
                                            % 100
                                            != 0
                                || year
                                           % 400
                                == 0));
                        if (day
                                    > 29
                                || (day
                                            == 29
                                && !leap))
                        {
                            return false;
                        }
                    }
                    return true;
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#startDate").datepicker({autoclose:true});
                      $("#endDate").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

