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
<div class="navbar-fixed-bottom">
    <div class="navbar-inner"
         style="padding-top: 7px; padding-bottom: 7px;">
        <div class="container">
            <div class="row">
                <tiles:insert attribute='tertiaryMenu'
                              ignore="true"
                              flush="false" />
                <tiles:insert attribute="controls"
                              ignore="true"
                              flush="false" />
            </div>
        </div>
    </div>
</div>
