<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.common.action.base.RequestKeys" %>
<tiles:insert page="/html/layout.jsp">
    <tiles:put name="title">
        Password Send Request | PermiTrack
    </tiles:put>
    <tiles:put name="portal-bar"
               type="string">
        <tiles:insert page="/html/portal-bar.jsp"
                      flush="false">
            <tiles:put name="portalMenu"
                       type="string"
                       value="" />
        </tiles:insert>
    </tiles:put>
    <tiles:put name="layout"
               direct="true">
        <div class="container">
            <h3>
                Account Password Assistance
            </h3>
            <p>
                Please enter your user account name and e-mail address in the fields provided so we can locate your account.
            </p>
            <form class="form-horizontal"
                  action="/sehsvc/password"
                  method="post"
                  onsubmit="return validateData();">
                <input type="hidden"
                       name="action"
                       id="action"
                       value="<%= RequestKeys.SEND_PASSWORD_ACTION %>">
                <div class="control-group">
                    <label class="control-label"
                           for="username">
                        User Account Name
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="<%= RequestKeys.FORGOT_PASSWORD_USERNAME %>"
                               id="username"
                               size="40">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"
                           for="emailAddress">
                        E-mail Address
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="<%= RequestKeys.FORGOT_PASSWORD_EMAIL %>"
                               id="emailAddress"
                               size="60">
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input class="btn btn-success btn-large"
                               type="submit"
                               value="Submit">
                    </div>
                </div>
            </form>
        </div>
        <tiles:definition id="scripts"
                          scope="request">
            <tiles:put name="scripts"
                       type="string"
                       direct="true">
                <script type="text/javascript">
                    //<!--
                    function validateData()
                    {
                        var errorMsg = '';
                        var testUsername = document.getElementById("username").value;
                        if (testUsername.length
                                < 1)
                            errorMsg
                                    += '<div class="mainbodytext warning">Account Name is required</div>';
                        var testEmailAddress = document.getElementById("emailAddress").value;
                        if (testEmailAddress.length
                                < 1)
                            errorMsg
                                    += '<div class="mainbodytext warning">E-mail Address is required</div>';
                        if (errorMsg.length
                                > 1)
                        {
                            $('#dialog').html(errorMsg).dialog('open');
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }//-->
                </script>
            </tiles:put>
        </tiles:definition>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
