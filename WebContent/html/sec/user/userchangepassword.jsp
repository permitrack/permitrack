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
<html:form styleClass="form-horizontal"
           action="/userchangepasswordaction">
    <fieldset><legend>
        Change Password
    </legend></fieldset>
    <h4 class="myAccordian">
        User Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Username
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='username' />
                <html:hidden name='userForm'
                             property='username'
                             styleId='username' />
                <html:hidden name='userForm'
                             property='id'
                             styleId='id' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">First Name
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='firstName' />
                <html:hidden name='userForm'
                             property='firstName'
                             styleId='firstName' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Last Name
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='lastName' />
                <html:hidden name='userForm'
                             property='lastName'
                             styleId='lastName' />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Change Password
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="newPassword">
                New Password *
            </label>
            <div class="controls">
                <input type="password"
                       name="newPassword"
                       id="newPassword"
                       size="26"
                       maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="confirm">
                Confirm *
            </label>
            <div class="controls">
                <input type="password"
                       name="confirm"
                       id="confirm"
                       size="26"
                       maxlength="50" />
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
                             value="Save"
                             onclick="return RequiredFields();" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!-- Script Begin
                function RequiredFields()
                {
                    var blnReturn = false;
                    var txtPW = document.getElementById("newPassword");
                    var txtCF = document.getElementById("confirm");
                    var strMessage = "";
                    // New password value
                    if (txtPW.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Password is required, ");
                    }
                    else
                    {
                        if (txtPW.value.length
                                <= 4)
                        {
                            strMessage
                                    = strMessage.concat("Password must be at least five characters, ");
                        }
                        else
                        {
                            if (NoSpaces(txtPW.value))
                            {
                                if (!ValidChars(txtPW.value))
                                {
                                    strMessage
                                            = strMessage.concat("Password contains invalid characters, ");
                                }
                            }
                            else
                            {
                                strMessage
                                        = strMessage.concat("Password can not contain any spaces, ");
                            }
                        }
                    }
                    //Confirm Password value.
                    if (txtCF.value
                            == "")
                    {
                        strMessage
                                = strMessage.concat("Confirm password is required, ");
                    }
                    else
                    {
                        if (txtPW.value
                                != txtCF.value)
                        {
                            strMessage
                                    = strMessage.concat("Password and the Confirm password do not match, ");
                        }
                        else if (txtPW.value.length
                                != txtCF.value.length)
                        {
                            strMessage
                                    = strMessage.concat("Password and the Confirm password do not match, ");
                        }
                    }
                    if (strMessage.length
                            > 0)
                    {
                        // Trim the last comma from the message string
                        strMessage
                                = strMessage.substr(0,
                                                    strMessage.length
                                                            - 2)
                                + ".";
                        $('#dialog').html("Please provide the required fields. "
                                                  + strMessage).dialog('open');
                    }
                    else
                    {
                        blnReturn
                                = true;
                    }
                    return blnReturn;
                }
                function NoSpaces(sString)
                {
                    var strSpace = " ";
                    return (sString.indexOf(strSpace)
                            < 0);
                }
                function ValidChars(sString)
                {
                    var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                    var allValid = true;
                    var ch = "";
                    sString
                            = Trim(sString);
                    if (sString.length
                            >= 1)
                    {
                        for (i
                                     = 0; i
                                < sString.length; i++)
                        {
                            if (allValid)
                            {
                                ch
                                        = sString.charAt(i);
                                allValid
                                        = (checkOK.indexOf(ch)
                                        >= 0);
                            }
                        }
                    }
                    else
                    {
                        allValid
                                = false;
                    }
                    return allValid;
                }
                function Trim(sString)
                {
                    sString
                            = rightTrim(leftTrim(sString));
                    return sString;
                }
                function rightTrim(sString)
                {
                    while (sString.substring(sString.length
                                                     - 1,
                                             sString.length)
                            == ' ')
                    {
                        sString
                                = sString.substring(0,
                                                    sString.length
                                                            - 1);
                    }
                    return sString;
                }
                function leftTrim(sString)
                {
                    while (sString.substring(0,
                                             1)
                            == ' ')
                    {
                        sString
                                = sString.substring(1,
                                                    sString.length);
                    }
                    return sString;
                }//  Script End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
