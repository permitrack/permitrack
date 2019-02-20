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
<%@ page import="com.sehinc.environment.action.base.RequestKeys, com.sehinc.environment.action.base.SessionKeys" %>
<%@ page import="com.sehinc.environment.action.sourceusage.ControlUsageListItem" %>
<%
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_USAGE_CAN_DELETE);
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_USAGE_CAN_UPDATE);%>
<html:form styleClass="form-horizontal"
           action="/controlusagelistpage"
           onsubmit="return validateForm(this);"
           method="get">
    <fieldset><legend>
        Control Malfunctions
        <small>
            for <%=session.getAttribute(SessionKeys.SELECTED_ASSET_NAME)%>
        </small>
    </legend></fieldset>
    <input type="hidden"
           id="<%=RequestKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE%>"
           name="<%=RequestKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE%>" />
    <div class="control-group">
        <label class="control-label">
            <bean:message key="substance.report.start.date" />
            -
            <bean:message key="substance.report.end.date" />
        </label>
        <div class="controls">
            <html:text styleId="periodStartTsString"
                       name="SourceUsageForm"
                       property="periodStartTsString"
                       size="12"
                       maxlength="10" />
            <html:text styleId="periodEndTsString"
                       name="SourceUsageForm"
                       property="periodEndTsString"
                       size="12"
                       maxlength="10" />
            <html:submit property="id"
                         styleClass="btn btn-mini btn-success"
                         value="Find"
                         title="Find malfunctions within the specified date range" />
        </div>
    </div>
    <table class="table table-hover action-first">
<%--
        <colgroup>
            <col style="width: 90px; white-space: nowrap;" />
        </colgroup>
--%>
        <thead>
            <tr>
                <th></th>
                <logic:present name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>"
                               scope="session">
                    <logic:iterate id="sourceUsageListItem"
                                   name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>">
                        <th>
                            <html:link action="/controlusagelistpage.do"
                                       paramId="sourceUsageListSortKey"
                                       paramName="sourceUsageListItem"
                                       paramProperty="sortQueryKey"
                                       title="Sort">
                                <bean:write name="sourceUsageListItem"
                                            property="name" />
                            </html:link>
                        </th>
                    </logic:iterate>
                </logic:present>
                <logic:notPresent name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>"
                                  scope="session">
                    <th>
                        <html:link action="/controlusagelistpage.do?sourceUsageListSortKey=sourceUsageListSortBySource"
                                   title="Sort">
                            Control
                        </html:link>
                    </th>
                </logic:notPresent>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.SOURCE_USAGE_READINGS_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="10">
                        <bean:message key="source.usage.asset.source.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.SOURCE_USAGE_READINGS_LIST  %>"
                            scope="session">
                <logic:iterate id="sourceUsageReadings"
                               name="<%= SessionKeys.SOURCE_USAGE_READINGS_LIST %>"
                               scope="session">
                    <tr>
                        <td>
                            <%if (blnCanEdit)
                            {%>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/controlusageeditpage.do"
                                       paramId="id"
                                       paramName="sourceUsageReadings"
                                       paramProperty="sourceUsageId">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <%}%>
                            <%if (blnCanDelete)
                            {%>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/controlusagedeleteaction.do"
                                       paramId="id"
                                       paramName="sourceUsageReadings"
                                       paramProperty="sourceUsageId">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <logic:present name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>"
                                       scope="session">
                            <logic:iterate id="sourceUsageListItem"
                                           name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>">
                                <logic:equal name="sourceUsageListItem"
                                             property="key"
                                             value="SOURCE_NAME">
                                    <td>
                                        <logic:notEmpty name="sourceUsageListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sourceUsageReadings' property='<%= ((ControlUsageListItem) pageContext.getAttribute("sourceUsageListItem")).getImage() %>'/>" />
                                        </logic:notEmpty>
                                        <html:link action="/controlusageviewpage.do"
                                                   paramId="id"
                                                   paramName="sourceUsageReadings"
                                                   paramProperty="sourceUsageId">
                                            <bean:write name="sourceUsageReadings"
                                                        property="sourceName" />
                                        </html:link>
                                    </td>
                                </logic:equal>
                                <logic:notEqual name="sourceUsageListItem"
                                                property="key"
                                                value="SOURCE_NAME">
                                    <td>
                                        <logic:notEmpty name="sourceUsageListItem"
                                                        property="image">
                                            <img alt=""
                                                 src="<bean:write name='sourceUsageReadings' property='<%= ((ControlUsageListItem) pageContext.getAttribute("sourceUsageListItem")).getImage() %>'/>" />
                                        </logic:notEmpty>
                                        <bean:write name='sourceUsageReadings'
                                                    property='<%= ((ControlUsageListItem) pageContext.getAttribute("sourceUsageListItem")).getProperty() %>' />
                                        <logic:notEmpty name="sourceUsageListItem"
                                                        property="property2">
                                            <bean:write name='sourceUsageReadings'
                                                        property='<%= ((ControlUsageListItem) pageContext.getAttribute("sourceUsageListItem")).getProperty2() %>' />
                                        </logic:notEmpty>
                                    </td>
                                </logic:notEqual>
                            </logic:iterate>
                        </logic:present>
                        <logic:notPresent name="<%= SessionKeys.SOURCE_USAGE_FIELD_LIST %>"
                                          scope="session">
                            <td>
                                <html:link action="/controlusageviewpage.do"
                                           paramId="id"
                                           paramName="sourceUsageReadings"
                                           paramProperty="sourceUsageId">
                                    <bean:write name="sourceUsageReadings"
                                                property="sourceName" />
                                </html:link>
                            </td>
                        </logic:notPresent>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span2">
                <div class="pagination">
                    <html:select styleClass="input-small"
                                 styleId="sourceUsagesPerPageSelect"
                                 name="SourceUsageForm"
                                 property="sourceUsagesPerPage"
                                 title="Source Usages Per Page"
                                 onchange="sourceUsagesPerPageSelectOnChange();">
                        <html:option value="25">
                            25</html:option>
                        <html:option value="50">
                            50</html:option>
                        <html:option value="75">
                            75</html:option>
                        <html:option value="100">
                            100</html:option>
                    </html:select>
                </div>
            </div>
            <div class="span8">
                <logic:present name="<%= SessionKeys.EV_SOURCE_USAGE_LIST_PAGE_CONTROL %>"
                               scope="session">
                    <logic:notEmpty name="<%= SessionKeys.EV_SOURCE_USAGE_LIST_PAGE_CONTROL %>"
                                    scope="session">
                        <div class="pagination pagination-centered">
                            <ul>
                                <logic:iterate id="pageControlElement"
                                               name="<%= SessionKeys.EV_SOURCE_USAGE_LIST_PAGE_CONTROL %>"
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
            <div class="span2"></div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                function setEvAssetListCurrentPageAndSubmit(page)
                {
                    var pageField = document.getElementById('<%=RequestKeys.EV_SOURCE_USAGE_LIST_CURRENT_PAGE%>');
                    pageField.value
                            = page;
                    document.getElementsByName('SourceUsageForm')[0].submit();
                }
                function setSort(columnName)
                {
                    var pageField = document.getElementsByName('< %=SourceUsageForm.SORT_COLUMN%>')[0];
                    var currentColumn = pageField.value;
                    if (currentColumn.search(columnName)
                                == 0
                            && columnName.search(currentColumn)
                            == 0)
                    {
                        pageField
                                = document.getElementsByName('< %=SourceUsageForm.SORT_DIRECTION%>')[0];
                        if (pageField.value.search('< %=EnvSourceUsageValue.ASCENDING%>')
                                == 0)
                        {
                            pageField.value
                                    = '< %=EnvSourceUsageValue.DESCENDING%>';
                        }
                        else
                        {
                            pageField.value
                                    = '< %=EnvSourceUsageValue.ASCENDING%>';
                        }
                    }
                    else
                    {
                        pageField.value
                                = columnName;
                        document.getElementsByName('< %=SourceUsageForm.SORT_DIRECTION%>')[0].value
                                = '< %=EnvSourceUsageValue.ASCENDING%>';
                    }
                    document.getElementsByName('sourceUsageForm')[0].submit();
                }
            </script>
            <script type="text/javascript">
                <% Integer sourceUsagesPerPage = (Integer) request.getSession().getAttribute(SessionKeys.EV_SOURCE_USAGE_LIST_ASSETS_PER_PAGE); %>
                for (var i = 0; i
                        < document.getElementById('sourceUsagesPerPageSelect').length; i++)
                {
                    if (document.getElementById('sourceUsagesPerPageSelect').options[i].value
                            == <%= sourceUsagesPerPage.intValue() %>)
                    {
                        document.getElementById('sourceUsagesPerPageSelect').options[i].selected
                                = true;
                        break;
                    }
                }
                function sourceUsagesPerPageSelectOnChange()
                {
                    document.getElementsByName('SourceUsageForm')[0].submit();
                }
            </script>
            <script type="text/javascript">
                //<!--
                function validateForm(form)
                {
                    var startDate = document.getElementById("activeTsString");
                    var endDate = document.getElementById("inactiveTsString");
                    if (startDate.value
                            == '')
                    {
                        $('#dialog').html("Enter a start date.").dialog('open');
                        return false;
                    }
                    else if (endDate.value
                            == '')
                    {
                        $('#dialog').html("Enter an end date.").dialog('open');
                        return false;
                    }
                    else
                    {
                        var startD = new Date(startDate.value);
                        var endD = new Date(endDate.value);
                        if (endD.getTime()
                                < startD.getTime())
                        {
                            $('#dialog').html("Date Selection is invalid. Start Date must precede End Date.").dialog('open');
                            return false;
                        }
                    }
                    return true; //validateSourceUsageReadingsForm(form);
                }//-->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#periodStartTsString").datepicker({autoclose:true});
                      $("#periodEndTsString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

