<%@ page language="java"
         isErrorPage="true" %>
<%--
<jsp:useBean id="constants"
             class="com.sehinc.common.config.ApplicationBean"
             scope="application" />
--%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<div class="container">
    <p>
        The system has encountered an error. Any changes you made to the previous page
        may have been lost.
    </p>
    <p>
        Please include the following information when reporting this issue:
    </p>
    <dl class="dl-horizontal">
        <dt>
            Tracking code
        </dt>
        <dd>
            <%= request.getAttribute("tracking") %>
        </dd>
<%--
        <dt>
            Current software version
        </dt>
        <dd>
            <bean:write name="constants"
                        property="version" />
        </dd>
--%>
        <dt>
            Technical Support
        </dt>
        <dd>
            <html:link action="/contact">
                Contact Support
            </html:link>
        </dd>
    </dl>
</div>