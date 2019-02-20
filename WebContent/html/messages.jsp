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
<div class="navbar-fixed-top">
    <div class="container">
        <logic:messagesPresent>
            <div class="alert alert-error fade in">
                <button type="button"
                        class="close"
                        data-dismiss="alert"><span style="font-size: 14px; font-weight: normal;">Auto-closes in 8 secs</span>&nbsp;&times;</button>
                <html:messages id="error">
                    <span class="error">
                        <strong>Warning!</strong> <bean:write name="error" />
                    </span>
                </html:messages>
            </div>
        </logic:messagesPresent>
        <logic:messagesPresent message="true">
            <div class="alert alert-info fade in">
                <button type="button"
                        class="close"
                        data-dismiss="alert"><span style="font-size: 14px; font-weight: normal;">Auto-closes in 8 secs</span>&nbsp;&times;</button>
                <html:messages message="true"
                               id="message">
                    <span class="success">
                        <bean:write name="message" />
                    </span>
                </html:messages>
            </div>
        </logic:messagesPresent>
    </div>
</div>
