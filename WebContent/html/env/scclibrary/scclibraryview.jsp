<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="scc.library.number" />
    </dt>
    <dd><bean:write name="SccLibraryForm"
                    property="number" />
    </dd>
    <dt><bean:message key="scc.library.desc" />
    </dt>
    <dd>
        <bean:write name="SccLibraryForm"
                    property="description" />
    </dd>
    <dt><bean:message key="scc.library.majGrp" />
    </dt>
    <dd><bean:write name="SccLibraryForm"
                    property="majIndustrialGroup" />
    </dd>
    <dt><bean:message key="scc.library.rawMat" />
    </dt>
    <dd><bean:write name="SccLibraryForm"
                    property="rawMaterial" />
    </dd>
    <dt><bean:message key="scc.library.emitProc" />
    </dt>
    <dd><bean:write name="SccLibraryForm"
                    property="emittingProcess" />
    </dd>
</dl>


