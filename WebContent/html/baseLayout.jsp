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
<tiles:insert page='layout.jsp'>
    <tiles:put name="title">
        <tiles:getAsString name="title" />
    </tiles:put>
    <tiles:put name="portal-bar"
               type="string">
        <tiles:insert name="portal-bar"
                      flush="false">
            <tiles:put name="portalMenu"
                       type="string">
                <tiles:get name="portalMenu"
                           flush="false" />
            </tiles:put>
        </tiles:insert>
    </tiles:put>
    <tiles:put name="layout"
               direct="true">
        <div class="container">
            <tiles:insert attribute='primaryMenu'
                          flush="false" />
            <tiles:insert attribute='secondaryMenu'
                          flush="false" />
            <tiles:insert page="messages.jsp"
                          flush="false" />
            <tiles:insert attribute='content'
                          flush="false">
                <tiles:put name="tertiaryMenu"
                           type="string">
                    <tiles:get name="tertiaryMenu"
                               flush="false"
                               ignore="true" />
                </tiles:put>
                <tiles:put name="title">
                    <tiles:getAsString name="title"
                                       ignore="true" />
                </tiles:put>
            </tiles:insert>
        </div>
    </tiles:put>
    <tiles:put name="footer"
               type="string">
        <tiles:get name="footer"
                   flush="false" />
    </tiles:put>
</tiles:insert>
