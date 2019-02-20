package com.sehinc.security.action.base;

import com.sehinc.common.util.StringUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

abstract public class BaseValidatorForm
    extends ValidatorForm
{
    abstract public void reset();

    abstract public void validateForm(ActionMessages errors);

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        reset();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        ActionErrors
            errors =
            super.validate(mapping,
                           request);
        validateForm(errors);
        return errors;
    }

    public static String lt(String strS)
    {
        return StringUtil.lt(strS);
    }

    public static String html(String strS)
    {
        return StringUtil.html(strS);
    }

    protected String viewString(String value)
    {
        return StringUtil.filterHTML(value);
    }
}
