<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.stormwater.action.base.SessionKeys" %>
<fieldset><legend>
    Published
    <bean:write name="planPublishForm"
                property="planName" />
</legend></fieldset>
<html:hidden name="planPublishForm"
             property="planName" />
<div class="alert alert-block">
    <h4>
        Published Report
    </h4>
    <br>
    <div class="well well-small"
         style="margin-bottom: 0;">
        <div>
            <strong>
                <logic:present name="<%= SessionKeys.STORM_PLAN_PUBLISH_URL %>"
                               scope="session">
                    <a href="javascript:openWindow('<%=(String)request.getSession().getAttribute(SessionKeys.STORM_PLAN_PUBLISH_URL) %>')">
                        <bean:write name="planPublishForm"
                                    property="planName" />
                    </a>
                </logic:present>
                <logic:present name="<%= SessionKeys.STORM_PLAN_EXPORT_FILE %>"
                               scope="session">
                    <a href="javascript:openWindow('<%=(String)request.getSession().getAttribute(SessionKeys.STORM_PLAN_EXPORT_FILE) %>')">
                        <bean:write name="planPublishForm"
                                    property="planName" />
                        (.zip)
                    </a>
                </logic:present>
            </strong>
        </div>
    </div>
</div>
