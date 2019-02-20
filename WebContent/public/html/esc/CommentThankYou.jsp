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
        Comment Submitted | PermiTrack
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
                <logic:notEmpty name="<%= RequestKeys.CLIENT_LOGO_LOCATION %>"
                                scope="request">
                    <img alt=""
                         style="max-width: 100px;"
                         src="<%= (String) request.getAttribute(RequestKeys.CLIENT_LOGO_LOCATION) %>" />
                </logic:notEmpty>
                <bean:write name="clientData"
                            property="name"
                            filter="true" />
            </h3>
            <h5>
                Thank you!
            </h5>
            <p class="text-success">
                Your comments have been sent to the appropriate project resources.
            </p>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               value="/html/footer.jsp">
    </tiles:put>
</tiles:insert>
