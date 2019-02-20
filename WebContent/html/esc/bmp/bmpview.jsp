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
    <bean:write name="BMPForm"
                property="name" />
</legend></fieldset>
<h4 class="myAccordian">
    BMP Description
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="bmp.category" />
    </dt>
    <dd><bean:write name="BMPForm"
                    property="categoryName" />
    </dd>
    <dt><bean:message key="bmp.name" />
    </dt>
    <dd><bean:write name="BMPForm"
                    property="name" />
    </dd>
    <dt><bean:message key="bmp.description" />
    </dt>
    <dd><bean:write name="BMPForm"
                    property="description" />
    </dd>
    <dt><bean:message key="bmp.weblink" />
    </dt>
    <dd>
        <a href="<bean:write name="BMPForm" property="weblink"/>"
           target="_blank">
            <bean:write name="BMPForm"
                        property="weblink" />
        </a>
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>