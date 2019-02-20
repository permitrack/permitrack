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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<jsp:useBean id="clientData"
             scope="request"
             class="com.sehinc.common.db.client.ClientData" />
<jsp:useBean id="ecProjectValue"
             scope="request"
             class="com.sehinc.erosioncontrol.value.project.ProjectValue" />
<tiles:insert page="/html/layout.jsp">
    <tiles:put name="title">
        Submit Comment | PermiTrack
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
            <div class="page-header">
                <h1>
                    <logic:notEmpty name="<%= RequestKeys.CLIENT_LOGO_LOCATION %>"
                                    scope="request">
                        <img alt=""
                             style="max-width: 100px;"
                             src="<%= (String) request.getAttribute(RequestKeys.CLIENT_LOGO_LOCATION) %>" />
                    </logic:notEmpty>
                    <jsp:getProperty name="ecProjectValue"
                                     property="name" />
                    <small>
                        <bean:write name="clientData"
                                    property="name"
                                    filter="true" />
                    </small>
                </h1>
            </div>
            <h4 class="myAccordian">
                Project Info
            </h4>
            <dl class="dl-horizontal">
                <dt>Permit #
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="permitNumber"
                                filter="true" />
                </dd>
                <dt>Parcel #
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="parcelNumber"
                                filter="true" />
                </dd>
                <dt>Address
                </dt>
                <dd>
                    <div>
                        <bean:write name="ecProjectValue"
                                    property="address"
                                    filter="true" />
                    </div>
                    <bean:write name="ecProjectValue"
                                property="city"
                                filter="true" />,
                    <bean:write name="ecProjectValue"
                                property="stateCode"
                                filter="true" />
                    <bean:write name="ecProjectValue"
                                property="zip"
                                filter="true" />
                </dd>
                <dt>Description
                </dt>
                <dd><bean:write name="ecProjectValue"
                                property="description"
                                filter="true" />
                </dd>
            </dl>
            <h4 class="myAccordian">
                Comment
            </h4>
            <div>
            <form class="form-horizontal"
                  action="/sehsvc/ec_report"
                  method="post"
                  onsubmit="return validateData();">
                <input type="hidden"
                       id="action"
                       name="action"
                       value="ecInspectionSaveComment">
                <input type="hidden"
                       id="ecProjectId"
                       name="ecProjectId"
                       value="<bean:write name='ecProjectValue' property='encryptedProjectId' />">
                <input type="hidden"
                       id="client_id"
                       name="client_id"
                       value="<bean:write name='ecProjectValue' property='encryptedOwnerClientId' />">
                <input type="hidden"
                       id="ipAddress"
                       name="ipAddress"
                       value="getIPAddress()">
                <div class="control-group">
                    <label class="control-label" for="firstName">
                        First Name
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="firstName"
                               id="firstName"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="lastName">
                        Last Name
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="lastName"
                               id="lastName"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="street">
                        Street Address
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="street"
                               id="street"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="city">
                        City
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="city"
                               id="city"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="state">
                        State
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="state"
                               id="state"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="zip">
                        Zip
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="zip"
                               id="zip"
                               size="10" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="phone">
                        Phone #
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="phone"
                               id="phone"
                               size="16" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="emailAddress">
                        Email
                    </label>
                    <div class="controls">
                        <input type="text"
                               name="emailAddress"
                               id="emailAddress"
                               size="30" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"
                           for="CommentText">
                        Comments
                    </label>
                    <div class="controls">
                        <textarea id="CommentText"
                                  rows="4"
                                  name="CommentText"
                                  cols="60"></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input class="btn btn-success btn-large"
                               type="Submit"
                               value="Submit">
                    </div>
                </div>
<%--
                <tiles:insert page="../../toolbar.jsp">
                    <tiles:put name="controls"
                               direct="true">
--%>
<%--
                        <input class="btn btn-large"
                               type="reset">
--%>
<%--
                        <input class="btn btn-success btn-large pull-right"
                               type="Submit"
                               value="Submit">
--%>
<%--
                    </tiles:put>
                </tiles:insert>
--%>
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
                            var testMsg = document.getElementById("firstName").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'First Name is required\n';
                            testMsg
                                    = document.getElementById("lastName").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'Last Name is required\n';
                            testMsg
                                    = document.getElementById("street").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'Street Address is required\n';
                            testMsg
                                    = document.getElementById("city").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'City Name is required\n';
                            testMsg
                                    = document.getElementById("state").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'State is required\n';
                            testMsg
                                    = document.getElementById("zip").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'Zip Code is required\n';
                            testMsg
                                    = document.getElementById("emailAddress").value;
                            var testMsg2 = document.getElementById("phone").value;
                            if ((testMsg.length
                                    < 1 )
                                    && (testMsg2.length
                                    < 1))
                                errorMsg
                                        += 'Phone Number and/or Email Address is required\n';
                            testMsg
                                    = document.getElementById("CommentText").value;
                            if (testMsg.length
                                    < 1)
                                errorMsg
                                        += 'No comments have been entered.\n';
                            if (testMsg.length
                                    > 2000)
                                errorMsg
                                        += 'Your comments cannot exceed 2000 characters.  You have entered '
                                                   + testMsg.length
                                        + ' characters.';
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
                        }
                        function getIPAddress()
                        {
                            return '<%= java.net.InetAddress.getLocalHost().toString() %>';
                        }//-->
                    </script>
                </tiles:put>
            </tiles:definition>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
