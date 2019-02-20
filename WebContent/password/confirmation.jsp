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
        Password Send Response | PermiTrack
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
            <logic:present name="<%= RequestKeys.FORGOT_PASSWORD_CONFIRMATION_MESSAGE %>"
                           scope="request">
                <p>
                    <bean:write name="<%= RequestKeys.FORGOT_PASSWORD_CONFIRMATION_MESSAGE %>"
                                filter="false" />
                </p>
            </logic:present>
            <logic:notPresent name="<%= RequestKeys.FORGOT_PASSWORD_IS_FOUND %>"
                              scope="request">
                <form action="/sehsvc/password"
                      method="post">
                    <input type="hidden"
                           id="action"
                           name="action"
                           value="<%= RequestKeys.FORGOT_PASSWORD_ACTION %>">
                    <input type="submit"
                           class="btn"
                           value="Try Again">
                </form>
            </logic:notPresent>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
