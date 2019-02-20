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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<fieldset><legend>
    Assets
</legend></fieldset>
<h5 class="muted">
    Select an Asset for which to view or enter malfunctions
</h5>
<h4 class="myAccordian">
    Controlled Assets
</h4>
<logic:empty name="<%= SessionKeys.EV_ASSET_ACTIVE_METER_LIST_BY_CLIENT %>">
    <p class="text-warning">
        <bean:message key="meter.empty.select.list" />
    </p>
</logic:empty>
<logic:notEmpty name="<%= SessionKeys.EV_ASSET_ACTIVE_METER_LIST_BY_CLIENT %>">
    <ul class="nav nav-pills nav-stacked">
        <logic:iterate id="asset"
                       name="<%=SessionKeys.EV_ASSET_ACTIVE_METER_LIST_BY_CLIENT%>">
            <li>
                <html:link action="/controlusagelistpage.do"
                           paramId="id"
                           paramName="asset"
                           paramProperty="id">
                    <div class="row">
                        <div class="span4">
                            <bean:write name="asset"
                                        property="numberAndName" />
                        </div>
                        <div class="span5">
                            <p class="muted">
                                <bean:write name="asset"
                                            property="description" />
                            </p>
                        </div>
                        <div class="span2">
                            <p class="muted">
                                <bean:write name="asset"
                                            property="usageCount"
                                            ignore="true" />
                            </p>
                        </div>
                    </div>
                </html:link>
            </li>
        </logic:iterate>
    </ul>
</logic:notEmpty>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
