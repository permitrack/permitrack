<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.security.action.base.SessionKeys" %>
<%
    pageContext.setAttribute("lstActive",
                             request.getSession()
                                     .getAttribute(SessionKeys.ACTIVE_CLIENT_PRIMARY_LIST));
    pageContext.setAttribute("lstActiveSecondary",
                             request.getSession()
                                     .getAttribute(SessionKeys.ACTIVE_CLIENT_SECONDARY_LIST));
    pageContext.setAttribute("lstModule",
                             request.getSession()
                                     .getAttribute(SessionKeys.MODULE_LIST));
%>
<%--<ul style="display:none;"></ul>--%>
<div data-spy="affix">
    <form class="form-search">
        <input type="text" id="treeInput" placeholder="Highlight in tree" class="input-medium search-query" />
        <button type="button" id="treeSearch" class="btn btn-mini">Go</button>
    </form>
    <div class="scrollBar img-polaroid img-rounded">
        <div class="handle"></div>
    </div>
    <div id="frame"
         class="frame img-polaroid img-rounded">
        <div class="slidee">
            <div id="treeView">
                <ul>
                    <logic:notEmpty name="lstModule">
                        <li id="bymodule">
                            <a href="#">
                                <span>
                                    By Module
                                </span>
                            </a>
                            <ul>
                                <logic:iterate id="module"
                                               name="lstModule">
                                    <li id="module_<bean:write name='module' property='code' />">
                                        <html:link module="/html/sec/client"
                                                   action="/clientlistpageaction.do"
                                                   paramId="module"
                                                   paramName="module"
                                                   paramProperty="code">
                                            <span>
                                                <bean:write name="module"
                                                            property="name" />
                                            </span>
                                        </html:link>
                                        <logic:equal value="EC"
                                                     name='module'
                                                     property='code'>
                                            <ul>
                                                <li id="primaryclients">
                                                    <a href="#">
                                                        <span>
                                                            Primary
                                                        </span>
                                                    </a>
                                                    <ul>
                                                        <logic:iterate id="client"
                                                                       name="lstActive">
                                                            <li id="client_<bean:write name='client' property='id' />">
                                                                <html:link module="/html/sec/client"
                                                                           action="/clientlistpageaction.do"
                                                                           paramId="client_id"
                                                                           paramName="client"
                                                                           paramProperty="id">
                                                                    <span>
                                                                        <bean:write name="client"
                                                                                    property="name" />
                                                                    </span>
                                                                </html:link>
                                                            </li>
                                                        </logic:iterate>
                                                    </ul>
                                                </li>
                                                <logic:notEmpty name="lstActiveSecondary">
                                                    <li id="secondaryclients">
                                                        <a href="#">
                                                            <span>
                                                                Secondary
                                                            </span>
                                                        </a>
                                                        <ul>
                                                            <logic:iterate id="client"
                                                                           name="lstActiveSecondary">
                                                                <li id="client_<bean:write name='client' property='id' />">
                                                                    <html:link module="/html/sec/client"
                                                                               action="/clientlistpageaction.do"
                                                                               paramId="client_id"
                                                                               paramName="client"
                                                                               paramProperty="id">
                                                                        <%--TODO use html:param more--%>
                                                                        <html:param name="secondary"
                                                                                    value="true" />
                                                                        <span>
                                                                            <bean:write name="client"
                                                                                        property="name" />
                                                                        </span>
                                                                    </html:link>
                                                                </li>
                                                            </logic:iterate>
                                                        </ul>
                                                    </li>
                                                </logic:notEmpty>
                                            </ul>
                                        </logic:equal>
                                    </li>
                                </logic:iterate>
                            </ul>
                        </li>
                    </logic:notEmpty>
                    <li id="phtml_1_4">
                        <html:link module="/html/sec/client"
                                   action="/clientlistpageaction.do?client_id=-1">
                            <span>
                                All
                            </span>
                        </html:link>
                    </li>
                    <logic:notEmpty name="lstModule">
                        <li id="phtml_2">
                            <a href="#">
                                <span>
                                    Non-Active Clients
                                </span>
                            </a>
                            <ul>
                                <li id="phtml_2_1">
                                    <html:link module="/html/sec/client"
                                               action="/clientlistpageaction.do?client_status=nonactive">
                                        <span>
                                            All
                                        </span>
                                    </html:link>
                                </li>
                                <li id="phtml_2_2">
                                    <html:link module="/html/sec/client"
                                               action="/clientlistpageaction.do?client_status=deleted">
                                        <span>
                                            Deleted
                                        </span>
                                    </html:link>
                                </li>
                            </ul>
                        </li>
                    </logic:notEmpty>
                </ul>
            </div>
        </div>
    </div>
</div>