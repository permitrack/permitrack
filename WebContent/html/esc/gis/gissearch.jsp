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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<logic:notEmpty name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                scope="request">
    <div class="alert">
        <bean:write name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                    scope="request" />
    </div>
</logic:notEmpty>
<html:form styleClass="form-horizontal"
           action="/gissearchaction"
           method="get">
    <fieldset><legend>
        Search by <bean:message key="gis.search.parcel.number.header" />
    </legend></fieldset>
    <h4 class="myAccordian">
        <bean:message key="gis.search.parcel.number.header" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="gis.search.parcel.number.header" />
            </label>
            <div class="controls">
                <html:text styleId="searchParcelNumber"
                           name="gisSearchForm"
                           property="searchParcelNumber"
                           size="45"
                           maxlength="50" />
                <span class="help-inline">
                    Search the coordinate database by parcel number.
                </span>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Search" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>