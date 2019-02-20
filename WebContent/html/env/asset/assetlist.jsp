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
<%@ page import="com.sehinc.environment.action.base.RequestKeys,
                 com.sehinc.environment.action.base.SessionKeys" %>
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ASSET_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ASSET_CAN_DELETE);%>
<html:form styleClass="form-horizontal"
           action="/assetlistpage"
           method="get">
    <fieldset><legend>
        Assets for
        <%=session.getAttribute(SessionKeys.EV_FACILITY_NAME)%>
    </legend></fieldset>
    <logic:empty name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT %>"
                 scope="session">
        <p class="text-info">
            <bean:message key="asset.empty.list" />
        </p>
    </logic:empty>
    <logic:notEmpty name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT  %>"
                    scope="session">
        <table class="table table-hover action-first">
<%--
            <colgroup>
                <col style="width: 90px; white-space: nowrap;" />
            </colgroup>
--%>
            <thead>
                <tr>
                    <th></th>
                    <th>
                        Number
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Type
                    </th>
                    <th>
                        Meter
                    </th>
                </tr>
            </thead>
            <tbody>
                <logic:iterate id="assetListValue"
                               name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT %>"
                               scope="session">
                    <tr>
                        <td>

                            <%if (blnCanEdit)
                            {%>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/asseteditpage.do"
                                       paramId="evAssetId"
                                       paramName="assetListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <%}
                                if (blnCanDelete)
                                {%>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/assetdeleteaction.do"
                                       paramId="id"
                                       paramName="assetListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <td>
                            <bean:write name="assetListValue"
                                        property="number" />
                        </td>
                        <logic:equal name="assetListValue"
                                     property="meter"
                                     value="true">
                            <td>
                                <html:link style="color:orange"
                                           action="/assetviewpage.do"
                                           paramId="evAssetId"
                                           paramName="assetListValue"
                                           paramProperty="id">
                                    <bean:write name="assetListValue"
                                                property="name" />
                                </html:link>
                            </td>
                        </logic:equal>
                        <logic:notEqual name="assetListValue"
                                        property="meter"
                                        value="true">
                            <td>
                                <html:link action="/assetviewpage.do"
                                           paramId="evAssetId"
                                           paramName="assetListValue"
                                           paramProperty="id">
                                    <bean:write name="assetListValue"
                                                property="name" />
                                </html:link>
                            </td>
                        </logic:notEqual>
                        <td>
                            <logic:notEmpty name="assetListValue"
                                            property="assetTypes">
                                <logic:iterate id="type"
                                               name="assetListValue"
                                               property="assetTypes">
                                    <bean:write name="type"
                                                property="description" />
                                </logic:iterate>
                            </logic:notEmpty>
                        </td>
                        <td>
                            <bean:write name="assetListValue"
                                        property="meterNumberName" />
                        </td>
                    </tr>
                    <!-- Iterate through first 'tier' of children -->
                    <logic:notEmpty name="assetListValue"
                                    property="subAssets">
                        <logic:iterate id="firstChildren"
                                       name="assetListValue"
                                       property="subAssets">
                            <tr>
                                <td>

                                    <%if (blnCanEdit)
                                    {%>
                                    <html:link styleClass='btn btn-mini btn-success'
                                               action="/asseteditpage.do"
                                               paramId="evAssetId"
                                               paramName="firstChildren"
                                               paramProperty="id">
                                        <html:img module="/"
                                                  page="/img/icons/sehedit.png"
                                                  alt="Edit" />
                                    </html:link>
                                    <%}
                                        if (blnCanDelete)
                                        {%>
                                    <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                               action="/assetdeleteaction.do"
                                               paramId="id"
                                               paramName="firstChildren"
                                               paramProperty="id">
                                        <html:img module="/"
                                                  page="/img/icons/sehdelete.png"
                                                  alt="Delete" />
                                    </html:link>
                                    <%}%>
                                </td>
                                <td>
                                    <bean:write name="firstChildren"
                                                property="number" />
                                </td>
                                <logic:equal name="firstChildren"
                                             property="meter"
                                             value="true">
                                    <td>
                                        &nbsp;&nbsp;
                                        <html:img module="/"
                                                  page="/img/Arrow_Right-Grey.gif"
                                                  alt=""
                                                  style="max-width: 10px;" />
                                        <html:link style="color:orange"
                                                   action="/assetviewpage.do"
                                                   paramId="evAssetId"
                                                   paramName="firstChildren"
                                                   paramProperty="id">
                                            <bean:write name="firstChildren"
                                                        property="name" />
                                        </html:link>
                                    </td>
                                </logic:equal>
                                <logic:notEqual name="firstChildren"
                                                property="meter"
                                                value="true">
                                    <td>
                                        &nbsp;&nbsp;
                                        <html:image module="/"
                                                    page="/img/Arrow_Right-Grey.gif"
                                                    alt=""
                                                    style="max-width: 10px;" />
                                        <html:link action="/assetviewpage.do"
                                                   paramId="evAssetId"
                                                   paramName="firstChildren"
                                                   paramProperty="id">
                                            <bean:write name="firstChildren"
                                                        property="name" />
                                        </html:link>
                                    </td>
                                </logic:notEqual>
                                <td>
                                    <logic:notEmpty name="firstChildren"
                                                    property="assetTypes">
                                        <logic:iterate id="ctype"
                                                       name="firstChildren"
                                                       property="assetTypes">
                                            <bean:write name="ctype"
                                                        property="description" />
                                        </logic:iterate>
                                    </logic:notEmpty>
                                </td>
                                <td>
                                    <bean:write name="assetListValue"
                                                property="meterNumberName" />
                                </td>
                            </tr>
                            <!--Iterate through the second 'tier' of children -->
                            <logic:notEmpty name="firstChildren"
                                            property="subAssets">
                                <logic:iterate id="secondChildren"
                                               name="firstChildren"
                                               property="subAssets">
                                    <tr>
                                        <td>

                                            <%if (blnCanEdit)
                                            {%>
                                            <html:link styleClass='btn btn-mini btn-success'
                                                       action="/asseteditpage.do"
                                                       paramId="evAssetId"
                                                       paramName="secondChildren"
                                                       paramProperty="id">
                                                <html:img module="/"
                                                          page="/img/icons/sehedit.png"
                                                          alt="Edit" />
                                            </html:link>
                                            <%}
                                                if (blnCanDelete)
                                                {%>
                                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                                       action="/assetdeleteaction.do"
                                                       paramId="id"
                                                       paramName="secondChildren"
                                                       paramProperty="id">
                                                <html:img module="/"
                                                          page="/img/icons/sehdelete.png"
                                                          alt="Delete" />
                                            </html:link>
                                            <%}%>
                                        </td>
                                        <td>
                                            <bean:write name="secondChildren"
                                                        property="number" />
                                        </td>
                                        <logic:equal name="firstChildren"
                                                     property="meter"
                                                     value="true">
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <html:image module="/"
                                                            page="/img/Arrow_Right-Grey.gif"
                                                            alt=""
                                                            style="max-width: 10px;" />
                                                <html:link style="color:orange"
                                                           action="/assetviewpage.do"
                                                           paramId="evAssetId"
                                                           paramName="secondChildren"
                                                           paramProperty="id">
                                                    <bean:write name="secondChildren"
                                                                property="name" />
                                                </html:link>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual name="firstChildren"
                                                        property="meter"
                                                        value="true">
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <html:image module="/"
                                                            page="/img/Arrow_Right-Grey.gif"
                                                            alt=""
                                                            style="max-width: 10px;" />
                                                <html:link action="/assetviewpage.do"
                                                           paramId="evAssetId"
                                                           paramName="secondChildren"
                                                           paramProperty="id">
                                                    <bean:write name="secondChildren"
                                                                property="name" />
                                                </html:link>
                                            </td>
                                        </logic:notEqual>
                                        <td>
                                            <logic:notEmpty name="secondChildren"
                                                            property="assetTypes">
                                                <logic:iterate id="c2type"
                                                               name="secondChildren"
                                                               property="assetTypes">
                                                    <bean:write name="c2type"
                                                                property="description" />
                                                </logic:iterate>
                                            </logic:notEmpty>
                                        </td>
                                        <td>
                                            <bean:write name="assetListValue"
                                                        property="meterNumberName" />
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </logic:iterate>
                    </logic:notEmpty>
                </logic:iterate>
            </tbody>
        </table>
    </logic:notEmpty>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <input type="hidden"
                   id="<%=RequestKeys.EV_ASSET_LIST_CURRENT_PAGE%>"
                   name="<%=RequestKeys.EV_ASSET_LIST_CURRENT_PAGE%>" />
            <div class="span4">
                <div class="pagination">
                    <html:select styleClass="input-small"
                                 styleId="assetsPerPageSelect"
                                 name="AssetForm"
                                 property="assetsPerPage"
                                 title="Assets Per Page"
                                 onchange="assetsPerPageSelectOnChange()">
                        <html:option value="25">25</html:option>
                        <html:option value="50">50</html:option>
                        <html:option value="75">75</html:option>
                        <html:option value="100">100</html:option>
                    </html:select>
                </div>
            </div>
            <div class="span4">
                <logic:present name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_PAGE_CONTROL %>"
                               scope="session">
                    <logic:notEmpty name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_PAGE_CONTROL %>"
                                    scope="session">
                        <div class="pagination pagination-centered">
                            <ul>
                                <logic:iterate id="pageControlElement"
                                               name="<%= SessionKeys.EV_ASSET_ACTIVE_LIST_PAGE_CONTROL %>"
                                               scope="session">
                                    <logic:equal name="pageControlElement"
                                                 property="value"
                                                 value="true">
                                        <li class="active">
                                            <a href="#">
                                                <bean:write name="pageControlElement"
                                                            property="label" />
                                            </a>
                                        </li>
                                    </logic:equal>
                                    <logic:notEqual name="pageControlElement"
                                                    property="value"
                                                    value="true">
                                        <li>
                                            <a href="javascript: setEvAssetListCurrentPageAndSubmit('<bean:write name="pageControlElement" property="value"/>')">
                                                <bean:write name="pageControlElement"
                                                            property="label" />
                                            </a>
                                        </li>
                                    </logic:notEqual>
                                </logic:iterate>
                            </ul>
                        </div>
                    </logic:notEmpty>
                </logic:present>
            </div>
            <div class="span4"></div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            function setEvAssetListCurrentPageAndSubmit(page)
            {
                var pageField = document.getElementById('<%=RequestKeys.EV_ASSET_LIST_CURRENT_PAGE%>');
                pageField.value
                        = page;
                document.getElementsByName('AssetForm')[0].submit();
            }
            function setSort(columnName)
            {
                var pageField = document.getElementsByName('< %=AssetForm.SORT_COLUMN%>')[0];
                var currentColumn = pageField.value;
                if (currentColumn.search(columnName)
                            == 0
                        && columnName.search(currentColumn)
                        == 0)
                {
                    pageField
                            = document.getElementsByName('< %=AssetForm.SORT_DIRECTION%>')[0];
                    if (pageField.value.search('< %=EnvAssetValue.ASCENDING%>')
                            == 0)
                    {
                        pageField.value
                                = '< %=EnvAssetValue.DESCENDING%>';
                    }
                    else
                    {
                        pageField.value
                                = '< %=EnvAssetValue.ASCENDING%>';
                    }
                }
                else
                {
                    pageField.value
                            = columnName;
                    document.getElementsByName('< %=AssetForm.SORT_DIRECTION%>')[0].value
                            = '< %=EnvAssetValue.ASCENDING%>';
                }
                document.getElementsByName('assetForm')[0].submit();
            }
        </script>
        <script type="text/javascript">
            <% Integer assetsPerPage = (Integer) request.getSession().getAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_ASSETS_PER_PAGE); %>
            for (var i = 0; i
                    < document.getElementById('assetsPerPageSelect').length; i++)
            {
                if (document.getElementById('assetsPerPageSelect').options[i].value
                        == <%= assetsPerPage.intValue() %>)
                {
                    document.getElementById('assetsPerPageSelect').options[i].selected
                            = true;
                    break;
                }
            }
            function assetsPerPageSelectOnChange()
            {
                document.getElementsByName('AssetForm')[0].submit();
            }
        </script>
    </tiles:put>
</tiles:definition>