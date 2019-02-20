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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.value.bmp.BMPValue" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.BMP_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.BMP_CAN_DELETE);
    pageContext.setAttribute("bmpList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT));%>

    <fieldset><legend>
        BMPs
    </legend></fieldset>

    <h4 class="myAccordian">
        <bean:message key="bmp.library.current.bmp.list" />
    </h4>
    <div>

    <logic:notEmpty name="<%= SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT %>"
                    scope="session">

        <%
            String
                    categoryName =
                    "";%>
        <logic:iterate id="bmpListValue"
                       name="<%= SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT %>"
                       scope="session">
                                <%if (!categoryName.equals(((BMPValue) pageContext.getAttribute("bmpListValue")).getCategoryName()))
                                {
                                    if (!categoryName.isEmpty())
                                    {%>
            </div> <%-- NEED THIS! --%>
            <%}%>
            <div class="well">
            <p>
            <div class="label">
                <bean:write name="bmpListValue"
                            property="categoryName" />
            </div>
            </p>

            <%}
                categoryName =
                        ((BMPValue) pageContext.getAttribute("bmpListValue")).getCategoryName();%>

            <p>
                <div class="row">
                <div class="span1">
                <%if (blnCanEdit)
                {%>
                <html:link styleClass='btn btn-mini btn-success'
                           action="/bmpeditpage.do"
                           paramId="ecBmpId"
                           paramName="bmpListValue"
                           paramProperty="id">
                    <html:img module="/"
                              page="/img/icons/sehedit.png"
                              alt="Edit" />
                </html:link>
                <%}%>
                <%if (blnCanDelete)
                {%>
                <html:link styleClass='btn btn-mini btn-danger warn-delete'
                           action="/bmpdeleteaction.do"
                           paramId="ecBmpId"
                           paramName="bmpListValue"
                           paramProperty="id">
                    <html:img module="/"
                              page="/img/icons/sehdelete.png"
                              alt="Delete" />
                </html:link>
                <%}%>
                </div>
            <div class="span10">
                <strong>
                    <bean:write name="bmpListValue"
                                property="name" />
                </strong>

                <bean:write name="bmpListValue"
                            property="description" />

                <a href="<bean:write name="bmpListValue" property="weblink"/>"
                   target="_blank">
                    <bean:write name="bmpListValue"
                                property="weblink" />
                </a>
            </div>
            </div>
            </p>

        </logic:iterate>
        </div>
        </div>
    </logic:notEmpty>


<%--
    <table class="table table-bordered">
        <colgroup>
            <col style="width: 90px; white-space: nowrap;" />
        </colgroup>
        <%
            String
                    categoryName =
                    "";%>
        <logic:iterate id="bmpListValue"
                       name="<%= SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT %>"
                       scope="session">
            <%
                if (!categoryName.equals(((BMPValue) pageContext.getAttribute("bmpListValue")).getCategoryName()))
                {
            %>
            <tr>
                <th colspan="4">
                    <bean:write name="bmpListValue"
                                property="categoryName" />
                </th>
            </tr>
            <tr>
                <th></th>
                <th>
                    Name
                </th>
                <th>
                    Description
                </th>
                <th>
                    URL
                </th>
            </tr>
            <%}
                categoryName =
                        ((BMPValue) pageContext.getAttribute("bmpListValue")).getCategoryName();%>
            <tr>
                <td>

                    <%if (blnCanEdit)
                    {%>
                    <html:link styleClass='btn btn-mini btn-success'
                               action="/bmpeditpage.do"
                               paramId="ecBmpId"
                               paramName="bmpListValue"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                    <%}%>
                    <%if (blnCanDelete)
                    {%>
                    <html:link styleClass='btn btn-mini btn-danger warn-delete'
                               action="/bmpdeleteaction.do"
                               paramId="ecBmpId"
                               paramName="bmpListValue"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                    <%}%>
                </td>
                <td>
                    <html:link action="/bmpviewpage.do"
                               paramId="ecBmpId"
                               paramName="bmpListValue"
                               paramProperty="id">
                        <bean:write name="bmpListValue"
                                    property="name" />
                    </html:link>
                </td>
                <td>
                    <bean:write name="bmpListValue"
                                property="description" />
                </td>
                <td>
                    <a href="<bean:write name="bmpListValue" property="weblink"/>"
                       target="_blank">
                        <bean:write name="bmpListValue"
                                    property="weblink" />
                    </a>
                </td>
            </tr>
        </logic:iterate>
    </table>
--%>
<%--</logic:notEmpty>--%>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
