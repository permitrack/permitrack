<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<script type="text/javascript">
    function validateInteger(id)
    {
        var field = document.getElementById(id);
        if (field.type
                    == 'text'
                    || field.type
                == 'textarea'
                    || field.type
                == 'select-one'
                || field.type
                == 'radio')
        {
            var value = '';
            if (field.type
                    == 'select-one')
            {
                var si = field.selectedIndex;
                if (si
                        >= 0)
                {
                    value
                            = field.options[si].value;
                }
            }
            else
            {
                value
                        = field.value;
            }
            if (value.length
                    > 0)
            {
                if (!isAllDigits(value))
                {
                    return false;
                }
                else
                {
                    var iValue = parseInt(value);
                    if (isNaN(iValue)
                            || !(iValue
                                         >= -2147483648
                            && iValue
                            <= 2147483647))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    function isAllDigits(argvalue)
    {
        argvalue
                = argvalue.toString();
        var validChars = '0123456789';
        var startFrom = 0;
        if (argvalue.substring(0,
                               2)
                == '0x')
        {
            validChars
                    = '0123456789abcdefABCDEF';
            startFrom
                    = 2;
        }
        else if (argvalue.charAt(0)
                == '0')
        {
            startFrom
                    = 1;
        }
        else if (argvalue.charAt(0)
                == '-')
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
    function validateFloat(id)
    {
        var field = document.getElementById(id);
        if (field.type
                    == 'text'
                    || field.type
                == 'textarea'
                    || field.type
                == 'select-one'
                || field.type
                == 'radio')
        {
            var value = '';
            if (field.type
                    == 'select-one')
            {
                var si = field.selectedIndex;
                if (si
                        >= 0)
                {
                    value
                            = field.options[si].value;
                }
            }
            else
            {
                value
                        = field.value;
            }
            if (value.length
                    > 0)
            {
                var tempArray = value.split('.');
                var joinedString = tempArray.join('');
                if (!isAllDigits(joinedString))
                {
                    return false;
                }
                else
                {
                    var iValue = parseFloat(value);
                    if (isNaN(iValue))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
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
            var MONTH = 'MM';
            var DAY = 'dd';
            var YEAR = 'yyyy';
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
                            = new RegExp('^(\\d{2})(\\d{2})(\\d{4})$');
                }
                else if (iDelim1
                        == orderDay)
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})(\\d{2})['
                                                 + delim2
                                                 + '](\\d{4})$');
                }
                else if (iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})['
                                                 + delim1
                                                 + '](\\d{2})(\\d{4})$');
                }
                else
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})['
                                                 + delim1
                                                 + '](\\d{2})['
                                                 + delim2
                                                 + '](\\d{4})$');
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
                            = new RegExp('^(\\d{2})(\\d{2})(\\d{4})$');
                }
                else if (iDelim1
                        == orderMonth)
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})(\\d{2})['
                                                 + delim2
                                                 + '](\\d{4})$');
                }
                else if (iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})['
                                                 + delim1
                                                 + '](\\d{2})(\\d{4})$');
                }
                else
                {
                    dateRegexp
                            = new RegExp('^(\\d{2})['
                                                 + delim1
                                                 + '](\\d{2})['
                                                 + delim2
                                                 + '](\\d{4})$');
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
                            = new RegExp('^(\\d{4})(\\d{2})(\\d{2})$');
                }
                else if (iDelim1
                        == orderMonth)
                {
                    dateRegexp
                            = new RegExp('^(\\d{4})(\\d{2})['
                                                 + delim2
                                                 + '](\\d{2})$');
                }
                else if (iDelim2
                        == orderDay)
                {
                    dateRegexp
                            = new RegExp('^(\\d{4})['
                                                 + delim1
                                                 + '](\\d{2})(\\d{2})$');
                }
                else
                {
                    dateRegexp
                            = new RegExp('^(\\d{4})['
                                                 + delim1
                                                 + '](\\d{2})['
                                                 + delim2
                                                 + '](\\d{2})$');
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