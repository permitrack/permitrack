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
<fieldset><legend>
    <bean:write name="SccForm"
                property="number" />
    <bean:write name="SccForm"
                property="description" />
</legend></fieldset>
<h4 class="myAccordian">
    SCC Description
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="scc.number" />
    </dt>
    <dd>
        <bean:write name="SccForm"
                    property="number" />
    </dd>
    <dt>
        <bean:message key="scc.desc" />
    </dt>
    <dd>
        <bean:write name="SccForm"
                    property="description" />
    </dd>
    <dt><bean:message key="scc.majGrp" />
    </dt>
    <dd><bean:write name="SccForm"
                    property="majIndustrialGroup" />
    </dd>
    <dt><bean:message key="scc.rawMat" />
    </dt>
    <dd><bean:write name="SccForm"
                    property="rawMaterial" />
    </dd>
    <dt><bean:message key="scc.emitProc" />
    </dt>
    <dd><bean:write name="SccForm"
                    property="emittingProcess" />
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
