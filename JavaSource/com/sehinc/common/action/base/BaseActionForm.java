package com.sehinc.common.action.base;

import com.sehinc.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

abstract public class BaseActionForm
    extends ActionForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(BaseActionForm.class);

    abstract public void reset();

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
        LOG.debug("Validating form: "
                  + this.getClass()
            .getName());
        try
        {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            LOG.error("Error reading request as UTF-8. Encoding: "
                      + request.getCharacterEncoding());
        }
        return super.validate(mapping,
                              request);
    }

    protected String viewString(String value)
    {
        return StringUtil.filterHTML(value);
    }
}
