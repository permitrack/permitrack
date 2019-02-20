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
<%@ page import="com.sehinc.environment.action.base.SessionKeys,
                 com.sehinc.environment.action.substance.SubstanceListItem" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SUBSTANCE_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SUBSTANCE_CAN_DELETE);%>
<html:form styleClass="form-horizontal"
           action="/substancelistpage">
    <table class="table table-hover action-first action-small">
<%--
        <colgroup>
            <col style="width: 90px; white-space: nowrap;" />
        </colgroup>
--%>
        <thead>
            <tr>
                <th></th>
                <logic:present name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>"
                               scope="session">
                    <logic:iterate id="substanceListItem"
                                   name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>">
                        <th>
                            <html:link action="/substancelistpage.do"
                                       paramId="substanceListSortKey"
                                       paramName="substanceListItem"
                                       paramProperty="sortQueryKey"
                                       title="Sort">
                                <bean:write name="substanceListItem"
                                            property="name" />
                            </html:link>
                        </th>
                    </logic:iterate>
                </logic:present>
                <logic:notPresent name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>"
                                  scope="session">
                    <th>
                        <html:link action="/substancelistpage.do?substanceListSortKey=substanceListSortBySubstanceName"
                                   title="Sort">
                            Substance
                        </html:link>
                    </th>
                </logic:notPresent>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EV_ACTIVE_SUBSTANCE_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="10">
                        <bean:message key="substance.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SUBSTANCE_LIST %>"
                            scope="session">
                <logic:iterate id="substanceListValue"
                               name="<%= SessionKeys.EV_ACTIVE_SUBSTANCE_LIST %>"
                               scope="session">
                    <tr>
                        <td>

                            <%
                                if (blnCanEdit)
                                {
                            %>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/substanceeditpage.do"
                                       paramId="evSubstanceId"
                                       paramName="substanceListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <%}%>
                            <%
                                if (blnCanDelete)
                                {
                            %>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/substancedeleteaction.do"
                                       paramId="id"
                                       paramName="substanceListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <logic:present name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>"
                                       scope="session">
                            <logic:iterate id="substanceListItem"
                                           name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>">
                                <logic:equal name="substanceListItem"
                                             property="key"
                                             value="SUBSTANCE_NAME">
                                    <td>
                                        <html:link action="/substanceviewpage.do"
                                                   paramId="evSubstanceId"
                                                   paramName="substanceListValue"
                                                   paramProperty="id"
                                                   title="View Substance">
                                            <bean:write name="substanceListValue"
                                                        property="name" />
                                        </html:link>
                                    </td>
                                </logic:equal>
                                <logic:notEqual name="substanceListItem"
                                                property="key"
                                                value="SUBSTANCE_NAME">
                                    <td>
                                        <logic:notEmpty name="substanceListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='substanceListValue' property='<%= ((SubstanceListItem) pageContext.getAttribute("substanceListItem")).getImage() %>'/>" />
                                        </logic:notEmpty>
                                        <bean:write name='substanceListValue'
                                                    property='<%= ((SubstanceListItem) pageContext.getAttribute("substanceListItem")).getProperty() %>' />
                                    </td>
                                </logic:notEqual>
                            </logic:iterate>
                        </logic:present>
                        <logic:notPresent name="<%= SessionKeys.SUBSTANCE_FIELD_LIST %>"
                                          scope="session">
                            <td>
                                <html:link action="/substanceviewpage.do"
                                           paramId="evSubstanceId"
                                           paramName="substanceListValue"
                                           paramProperty="id"
                                           title="View Substance">
                                    <bean:write name="substanceListValue"
                                                property="name" />
                                </html:link>
                            </td>
                        </logic:notPresent>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
