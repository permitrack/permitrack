<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page import="com.sehinc.common.action.base.SessionService" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.action.base.SessionKeys" %>
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
<%pageContext.setAttribute(CommonConstants.MODE,
                           request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("action",
                             StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                     .equals("view")
                             && SessionService.getSessionAttribute(SessionKeys.PERMIT_PERIOD,
                                                                   request)
                                == null
                                     ? "shownew"
                                     : "");%>
<html:form styleClass="form-horizontal"
           action="/permitperiodedit"
           method="POST"
           onsubmit="return validateForm(this);">
    <logic:equal name="action"
                 value="shownew">
        <p class="text-info">
            Choose a Permit Period from the left
        </p>
        <html:link styleClass="btn btn-success"
                   action="/permitperiodnewaction">
            New Permit Period
        </html:link>
    </logic:equal>
    <logic:notEqual name="action"
                    value="shownew">
        <fieldset><legend>
            <logic:equal name="mode"
                         value="create">
                New <bean:message key="permitperiod.information.heading" />
            </logic:equal>
            <logic:notEqual name="mode"
                            value="create">
                <bean:write name="permitPeriodForm"
                            property="permitPeriodName" />
            </logic:notEqual>
        </legend></fieldset>
        <html:hidden property="permitPeriodId" />
        <html:hidden property="deletePermitTimePeriodId" />
        <div class="control-group">
            <label class="control-label"
                   for="permitPeriodName">
                <bean:message key="permitperiod.name" />
            </label>
            <div class="controls">
                <html:text property="permitPeriodName"
                           styleId="permitPeriodName"
                           size="50"
                           maxlength="50"
                           alt="" />
            </div>
        </div>
        <table class="table table-bordered action-first action-small">
<%--
            <colgroup>
                <col style="width: 50px; white-space: nowrap;" />
            </colgroup>
--%>
            <thead>
                <tr>
                    <th></th>
                    <th>
                        <bean:message key="permitperiod.timeperiod.name" />
                    </th>
                    <th>
                        <bean:message key="permitperiod.timeperiod.startdate" />
                    </th>
                    <th>
                        <bean:message key="permitperiod.timeperiod.enddate" />
                    </th>
                </tr>
            </thead>
            <tbody>
                <bean:define id="permitTimePeriods"
                             name="permitPeriodForm"
                             property="permitTimePeriods" />
                <logic:iterate id="permitTimePeriod"
                               name="permitTimePeriods">
                    <tr>
                        <td>
                            <html:link styleClass="btn btn-mini btn-danger warn-delete"
                                       action="/timeperioddelete"
                                       paramId="deleteTimePeriod"
                                       paramName="permitTimePeriod"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                        </td>
                        <td>
                            <bean:write name="permitTimePeriod"
                                        property="name" />
                        </td>
                        <td>
                            <bean:write name="permitTimePeriod"
                                        property="startDateString" />
                        </td>
                        <td>
                            <bean:write name="permitTimePeriod"
                                        property="endDateString" />
                        </td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td></td>
                    <td>
                        <html:text styleClass="input-medium"
                                   styleId="timePeriodName"
                                   property="timePeriodName"
                                   size="25"
                                   maxlength="25"
                                   alt="" />
                    </td>
                    <td>
                        <html:text styleClass="input-small"
                                   styleId="startDateString"
                                   property="startDateString"
                                   size="12" />
                    </td>
                    <td>
                        <html:text styleClass="input-small"
                                   styleId="endDateString"
                                   property="endDateString"
                                   size="12" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <html:submit styleClass="btn btn-mini btn-success"
                                     property="addTimePeriod"
                                     onclick="addValidate()">
                            <bean:message key="permitperiod.timeperiod.add" />
                        </html:submit>
                    </td>
                </tr>
            </tbody>
        </table>
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
                                 property="finish"
                                 value="Save" />
                </div>
            </tiles:put>
        </tiles:insert>
        <tiles:definition id="scripts"
                          scope="request">
            <tiles:put name="scripts"
                       type="string"
                       direct="true">
                <html:javascript formName="permitPeriodForm" />
                <script type="text/javascript">
                    <!--
//                    var bCancel = false;
                    function validateForm(form)
                    {
                        if (bCancel)
                        {
                            bCancel = false;
                            return false;
                        } // Problem with the added period
                        return validatePermitPeriodForm(form);
                    }
                    function addValidate()
                    {
                        bCancel
                                = false;
                        var periodName = document.permitPeriodForm.timePeriodName.value;
//                        var startDate = document.permitPeriodForm.startDateString.value;
//                        var endDate = document.permitPeriodForm.endDateString.value;
                        if (periodName
                                    == null
                                || periodName
                                == "")
                        {
                            $('#dialog').html("Time Period Name is required.").dialog('open');
                            bCancel
                                    = true;
                        }
                        if (!validateDateCustom("startDateString"))
                        {
                            $('#dialog').html("Start Date must be a valid date. (MM/dd/yyyy)").dialog('open');
                            bCancel
                                    = true;
                        }
                        if (!validateDateCustom("endDateString"))
                        {
                            $('#dialog').html("End Date must be a valid date. (MM/dd/yyyy)").dialog('open');
                            bCancel
                                    = true;
                        }
                        return !bCancel;
                    }
                    function validateDateCustom(id)
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
                    <!--
                    $(function ()
                      {
                          $("#startDateString").datepicker({autoclose:true});
                          $("#endDateString").datepicker({autoclose:true});
                      });// -->
                </script>
            </tiles:put>
        </tiles:definition>
    </logic:notEqual>
</html:form>
