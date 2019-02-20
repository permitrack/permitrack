package com.sehinc.common.action.base;

import com.sehinc.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

abstract public class BaseValidatorForm
    extends ValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(BaseActionForm.class);

    abstract public void reset();

    abstract public void validateForm(ActionErrors errors);

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            LOG.error("Error reading request as UTF-8. Encoding: "
                      + request.getCharacterEncoding());
        }
        reset();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            LOG.error("Error reading request as UTF-8. Encoding: "
                      + request.getCharacterEncoding());
        }
        ActionErrors
            errors =
            super.validate(mapping,
                           request);
        validateForm(errors);
        return errors;
    }

    protected String viewString(String value)
    {
        return StringUtil.filterHTML(value);
    }

    protected String lt(String strS)
    {
        return StringUtil.lt(strS);
    }
}
