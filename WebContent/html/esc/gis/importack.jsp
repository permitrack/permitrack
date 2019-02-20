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
<div>
    <fieldset><legend>
        Parcel Coordinate Lookup Table Import
    </legend></fieldset>
    <div class="mainbodytext">
        Import a delimited text file of parcel coordinates into the system.
    </div>
    <logic:notEmpty name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                    scope="request">
        <div class="success">
            <bean:write name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                        scope="request" />
        </div>
    </logic:notEmpty>
</div>