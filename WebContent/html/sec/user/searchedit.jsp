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
<html:form styleClass="form-horizontal small-labels"
           action="/usersearcheditaction"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <html:hidden property="id"
                 name="projectSearchListForm"
                 styleId="id" />
    <fieldset><legend>
        <bean:write name="projectSearchListForm"
                    property="searchName" />
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label">
            Name
        </label>
        <div class="controls">
            <html:text name="projectSearchListForm"
                       property="searchName"
                       styleId="searchName"
                       size="35"
                       maxlength="50" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Is Default
        </label>
        <div class="controls">
            <label class="checkbox"
                   for="defaultSearch">
                <html:checkbox name="projectSearchListForm"
                               property="defaultSearch"
                               styleId="defaultSearch" />
                Use this Saved Search Filter as the user's default
            </label>
        </div>
    </div>
    <%@ include file="../../esc/project/projectfilter.jsp" %>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       onclick="bCancel=true;" />
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
            <script type="text/javascript">
                function validateForm()
                {
                    if (!validateFloat(document.getElementById("searchTotalAreaSizeMin").value))
                    {
                        $('#dialog').html("Total Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchTotalAreaSizeMax").value))
                    {
                        $('#dialog').html("Total Area Max must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchAreaSizeMin").value))
                    {
                        $('#dialog').html("Disturbed Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchAreaSizeMax").value))
                    {
                        $('#dialog').html("New Impervious Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchImpAreaSizeMin").value))
                    {
                        $('#dialog').html("New Impervious Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchImpAreaSizeMax").value))
                    {
                        $('#dialog').html("New Impervious Area Max must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchStartDateA"))
                    {
                        $('#dialog').html("Start Date A is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchStartDateB"))
                    {
                        $('#dialog').html("Start Date B is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchEffDateA"))
                    {
                        $('#dialog').html("Effective Date A is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchEffDateB"))
                    {
                        $('#dialog').html("Effective Date B is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchSeedDateA"))
                    {
                        $('#dialog').html("Seed Date A is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchSeedDateB"))
                    {
                        $('#dialog').html("Seed Date B is not a valid date. (MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    return true;
                }
                function validateFloat(value)
                {
                    var bValid = true;
                    if (value.length
                            > 0)
                    {
                        // remove '.' before checking digits
                        var tempArray = value.split('.');
                        var joinedString = tempArray.join('');
                        if (!isAllDigits(joinedString))
                        {
                            bValid
                                    = false;
                        }
                        else
                        {
                            var iValue = parseFloat(value);
                            if (isNaN(iValue))
                            {
                                bValid
                                        = false;
                            }
                        }
                    }
                    return bValid;
                }
                function isAllDigits(argvalue)
                {
                    argvalue
                            = argvalue.toString();
                    var validChars = "0123456789";
                    var startFrom = 0;
                    if (argvalue.substring(0,
                                           2)
                            == "0x")
                    {
                        validChars
                                = "0123456789abcdefABCDEF";
                        startFrom
                                = 2;
                    }
                    else if (argvalue.charAt(0)
                            == "0")
                    {
                        startFrom
                                = 1;
                    }
                    else if (argvalue.charAt(0)
                            == "-")
                    {
                        startFrom
                                = 1;
                    }
                    for (var n = startFrom; n
                            < argvalue.length; n++)
                    {
                        if (validChars.indexOf(argvalue.substring(n,
                                                                  n
                                                                          + 1))
                                == -1) return false;
                    }
                    return true;
                }
                function validateDate(id)
                {
                    var field = document.getElementById(id);
                    var value = field.value;
                    var datePattern = 'MM/dd/yyyy';
                    var dateRegexp;
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
                            iDelim1
                                    = orderMonth
                                    + MONTH.length;
                            iDelim2
                                    = orderDay
                                    + DAY.length;
                            delim1
                                    = datePattern.substring(iDelim1,
                                                            iDelim1
                                                                    + 1);
                            delim2
                                    = datePattern.substring(iDelim2,
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
                            matched
                                    = dateRegexp.exec(value);
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
                            iDelim1
                                    = orderDay
                                    + DAY.length;
                            iDelim2
                                    = orderMonth
                                    + MONTH.length;
                            delim1
                                    = datePattern.substring(iDelim1,
                                                            iDelim1
                                                                    + 1);
                            delim2
                                    = datePattern.substring(iDelim2,
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
                            matched
                                    = dateRegexp.exec(value);
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
                }
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#searchStartDateA").datepicker({autoclose:true});
                      $("#searchStartDateB").datepicker({autoclose:true});
                      $("#searchEffDateA").datepicker({autoclose:true});
                      $("#searchEffDateB").datepicker({autoclose:true});
                      $("#searchSeedDateA").datepicker({autoclose:true});
                      $("#searchSeedDateB").datepicker({autoclose:true});
                  });
                $(function ()
                  {
                      $('select[multiple]').select2();
                  }); // -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

