<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.common.util.DateUtil,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.project.ProjectListItem" %>
<%@ page import="com.sehinc.erosioncontrol.action.project.ProjectSearchListForm" %>
<%@ page import="com.sehinc.erosioncontrol.db.project.EcProjectSearchData" %>
<%@ page import="java.util.Date" %>
<html:form styleClass="form-horizontal small-labels"
           action="/projectlistpage"
           method="get">
    <html:hidden property="totalPages"
                 name="projectSearchListForm" />
    <html:hidden property="<%=ProjectSearchListForm.SORT_COLUMN%>"
                 name="projectSearchListForm" />
    <html:hidden property="<%=ProjectSearchListForm.SORT_DIRECTION%>"
                 name="projectSearchListForm" />
    <input type="hidden"
           id="<%=RequestKeys.EC_PROJECT_LIST_CURRENT_PAGE%>"
           name="<%=RequestKeys.EC_PROJECT_LIST_CURRENT_PAGE%>" />
    <div class="row"
         style="margin-bottom: 15px;">
        <div class="span7">
            <div class="btn-group">
                <button type="button"
                        class="btn"
                        data-toggle="collapse"
                        data-target="#filter,#applyFilter">
                    Filter
                </button>
                <button id="applyFilter"
                        class="btn btn-success collapse"
                        type="submit"
                        name="btnSubmit"
                        value="Apply Filter"
                        onclick="return validateForm();"
                        title="Apply the ad hoc filter">
                    Apply
                </button>
            </div>
            <logic:notEmpty name="<%=SessionKeys.USER_SEARCH_LIST%>">
                <div class="pull-right" style="display: inline-block;">
                    <div class="input-append">
                        <html:select styleClass="input-medium"
                                     name="projectSearchListForm"
                                     property="savedSearchId"
                                     styleId="savedSearchId">
                            <html:option value="">Saved Filter...</html:option>
                            <html:options collection="<%=SessionKeys.USER_SEARCH_LIST%>"
                                          property="searchId"
                                          labelProperty="ecSearch.name" />
                        </html:select>
                        <button class="btn btn-success"
                                type="submit"
                                name="btnSubmit"
                                value="<bean:message key='project.search.saved.filter' />"
                                onclick="return validateForm();"
                                title="Apply the saved filter">
                            Apply
                        </button>
                    </div>
                </div>
            </logic:notEmpty>
        </div>
        <div class="span5">
            <div class="hidden-phone">
                <div class="btn-group pull-right" style="margin-left: 5px;">
                    <logic:present name="<%=SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL%>">
                        <a class="btn"
                           title="Display a map view which only takes into account pass and fail"
                           href='<bean:write name="<%=SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL%>" />'>
                            <html:img module="/"
                                      page="/img/icons/Google_Maps_Marker.png"
                                      alt="" />
                            Status
                        </a>
                    </logic:present>
                    <logic:present name="<%=SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL%>">
                        <a class="btn"
                           title="Display a map view which takes into account pass and fail as well as last inspection age"
                           href='<bean:write name="<%=SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL%>" />'>
                            <html:img module="/"
                                      page="/img/icons/Google_Maps_Marker.png"
                                      alt="" />
                            Status + Age
                        </a>
                    </logic:present>
                </div>
                <div class="btn-group pull-right">
                    <html:link styleClass="btn"
                               title="Choose which columns to show in the the search results"
                               action="/projectlistitempage">
                        Choose Columns
                    </html:link>
                </div>
                <%--
                <div class="btn-group" style="position: absolute; top: 125px;">
                    <html:link styleClass="btn"
                               title="Choose which columns to show in the the search results"
                               action="/sendnotifications">
                        Send Overdue Notifications
                    </html:link>
                </div>
                --%>
            </div>
        </div>
    </div>
    <div id="filter"
         class="collapse">
        <%@ include file="projectfilter.jsp" %>
    </div>
    <div style="zoom: 1; overflow: auto;">
        <div style="zoom: 1;">
            <table class="table table-hover table-condensed action-samesize" style="font-size: smaller;">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <logic:present name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>"
                                       scope="session">
                            <logic:iterate id="projectListItem"
                                           name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>">
                                <logic:equal name="projectListItem"
                                             property="key"
                                             value="PROJECT_NAME">
                                    <th>
                                        <a href="javascript:setSort('<bean:write name="projectListItem" property="columnName"/>')">
                                            <bean:write name="projectListItem"
                                                        property="name" />
                                        </a>
                                    </th>
                                </logic:equal>
                                <logic:notEqual name="projectListItem"
                                                property="key"
                                                value="PROJECT_NAME">
                                    <th>
                                        <a href="javascript: setSort('<bean:write name="projectListItem" property="columnName"/>')">
                                            <bean:write name="projectListItem"
                                                        property="name" />
                                        </a>
                                    </th>
                                </logic:notEqual>
                            </logic:iterate>
                        </logic:present>
                        <logic:notPresent name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>"
                                          scope="session">
                            <th>
                                <html:link action="/projectlistpage.do?ecProjectListSortKey=ecProjectNameSort"
                                           title="Sort">
                                    Project Name
                                </html:link>
                            </th>
                        </logic:notPresent>
                    </tr>
                </thead>
                <tbody>
                    <logic:present name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                                   scope="session">
                        <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                                        scope="session">
                            <logic:iterate id="projectValue"
                                           name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                                           scope="session">
                                <tr>
                                    <bean:define id="projectSecurityValue"
                                                 name="projectValue"
                                                 property="projectSecurityPermissionValue" />
                                    <bean:define id="inspectionSecurityValue"
                                                 name="projectValue"
                                                 property="inspectionSecurityPermissionValue" />
                                    <td class="action-samesize">
                                        <logic:equal name="projectSecurityValue"
                                                     property="isUpdate"
                                                     value="true">
                                            <html:link styleClass="btn btn-small btn-primary action-samesize"
                                                       action="/projecteditpage.do"
                                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                                       paramName="projectValue"
                                                       paramProperty="projectId">
                                                <html:img module="/"
                                                          page="/img/icons/sehedit.png"
                                                          alt="EDIT: Edit the details of this project"
                                                          title="EDIT: Edit the details of this project" />
                                                <div style="line-height: 10px;"><small>Edit</small></div>
                                            </html:link>
                                        </logic:equal>
                                    </td>
                                    <td class="action-samesize">
                                        <logic:equal name="inspectionSecurityValue"
                                                     property="isCreate"
                                                     value="true">
                                            <html:link styleClass="btn btn-small btn-success action-samesize"
                                                       module="/html/esc/inspection"
                                                       action="/inspectioncreatepage.do"
                                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                                       paramName="projectValue"
                                                       paramProperty="projectId">
                                                <html:img module="/"
                                                          page="/img/icons/sehinspect.png"
                                                          alt="NEW INSPECTION: Create a new inspection for this project"
                                                          title="NEW INSPECTION: Create a new inspection for this project" />
                                                <div style="line-height: 10px;"><small>Inspect</small></div>
                                            </html:link>
                                        </logic:equal>
                                    </td>
                                    <td class="action-samesize">
                                        <logic:equal name="inspectionSecurityValue"
                                                     property="isRead"
                                                     value="true">
                                            <html:link styleClass="btn btn-small btn-success action-samesize"
                                                       module="/html/esc/inspection"
                                                       action="/inspectionlistpage.do"
                                                       paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                                       paramName="projectValue"
                                                       paramProperty="projectId">
                                                <html:img module="/"
                                                          page="/img/icons/sehhistory2.png"
                                                          alt="INSPECTION LIST: View the inspection history for this project"
                                                          title="INSPECTION LIST: View the inspection history for this project" />
                                                <div style="line-height: 10px;"><small>List</small></div>
                                            </html:link>
                                        </logic:equal>
                                    </td>
                                    <td class="action-samesize">
                                        <logic:equal name="projectSecurityValue"
                                                     property="isRead"
                                                     value="true">
                                            <a class="btn btn-small action-samesize"
                                               href="<bean:write name='projectValue' property='reportUrl' filter='false' />">
                                                <html:img module="/"
                                                          page="/img/icons/sehreport.png"
                                                          alt="PROJECT REPORT: Download a report regarding this project (PDF)"
                                                          title="PROJECT REPORT: Download a report regarding this project (PDF)" />
                                                <div style="line-height: 10px;"><small>Report</small></div>
                                            </a>
                                        </logic:equal>
                                    </td>
                                    <td class="action-samesize">
                                        <logic:equal name="projectSecurityValue"
                                                     property="isRead"
                                                     value="true">
                                            <a class="btn btn-small action-samesize"
                                               href="<bean:write name='projectValue' property='inspectionFormUrl' filter='false' />">
                                                <html:img module="/"
                                                          page="/img/icons/sehinspectform.png"
                                                          alt="INSPECTION FORM: Download a manual-entry inspection form for this project (PDF)"
                                                          title="INSPECTION FORM: Download a manual-entry inspection form for this project (PDF)" />
                                                <div style="line-height: 10px;"><small>Form</small></div>
                                            </a>
                                        </logic:equal>
                                    </td>
                                    <logic:present name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>"
                                                   scope="session">
                                        <logic:iterate id="projectListItem"
                                                       name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>"
                                                       scope="session">
                                            <logic:equal name="projectListItem"
                                                         property="key"
                                                         value="PROJECT_NAME">
                                                <td>
                                                    <logic:equal name="projectSecurityValue"
                                                                 property="isRead"
                                                                 value="true">
                                                        <html:link action="/projectviewpage.do"
                                                                   paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                                                   paramName="projectValue"
                                                                   paramProperty="projectId"
                                                                   title="View Project">
                                                            <bean:write name="projectValue"
                                                                        property="name" />
                                                            <logic:empty name="projectValue"
                                                                         property="inspectionCount">
                                                                <span class="muted">(0)</span>
                                                            </logic:empty>
                                                            <logic:notEmpty name="projectValue"
                                                                            property="inspectionCount">
                                                                <span class="muted">
                                                                    (<bean:write name="projectValue"
                                                                                 property="inspectionCount" />)
                                                                </span>
                                                            </logic:notEmpty>
                                                        </html:link>
                                                    </logic:equal>
                                                    <logic:notEqual name="projectSecurityValue"
                                                                    property="isRead"
                                                                    value="true">
                                                        <bean:write name="projectValue"
                                                                    property="name" />
                                                        <logic:empty name="projectValue"
                                                                     property="inspectionCount">
                                                            <span class="muted">(0)</span>
                                                        </logic:empty>
                                                        <logic:notEmpty name="projectValue"
                                                                        property="inspectionCount">
                                                            <span class="muted">
                                                                (<bean:write name="projectValue"
                                                                             property="inspectionCount" />)
                                                            </span>
                                                        </logic:notEmpty>
                                                    </logic:notEqual>
                                                </td>
                                            </logic:equal>
                                            <logic:notEqual name="projectListItem"
                                                            property="key"
                                                            value="<%= ProjectListItem.PROJECT_NAME.getKey() %>">
                                                <td>

                                                    <%
                                                        boolean
                                                                isLastInspectionDate =
                                                                false;
                                                        String
                                                                shortDateString =
                                                                "";
                                                        if (((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty()
                                                            == "lastInspectionDate")
                                                        {
                                                            isLastInspectionDate =
                                                                    true;
                                                            try
                                                            {
                                                                Date
                                                                        shortDate;
                                                                String inspDate = ((EcProjectSearchData) projectValue).getLastInspectionDate();
                                                                if(inspDate != null && inspDate != "")
                                                                {
                                                                    shortDate = DateUtil.MDYTT_FORMAT
                                                                                .parse(inspDate);
                                                                    shortDateString =
                                                                            DateUtil.PARSE_DATE_FORMAT
                                                                                    .format(shortDate);
                                                                }
                                                            }
                                                            catch (Exception e)
                                                            {
                                                            }
                                                        }%>
                                                    <logic:notEmpty name="projectListItem"
                                                                    property="image">
                                                        <logic:notEmpty name='projectValue'
                                                                        property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getImage() %>'>
                                                            <div class="noWrap">
                                                                <img src="<bean:write name='projectValue' property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getImage() %>'/>"
                                                                     alt="" />
                                                                <% if (isLastInspectionDate)
                                                                {%>
                                                                <%=shortDateString %>
                                                                <%}
                                                                else
                                                                {%>
                                                                <bean:write name='projectValue'
                                                                            property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty() %>' />
                                                                <%}%>
                                                            </div>
                                                            <% if (isLastInspectionDate)
                                                            {%>
                                                            <div style="visibility: hidden;"
                                                                 class="checkForDate">
                                                                <bean:write name='projectValue'
                                                                            property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty() %>' />
                                                            </div>
                                                            <%}%>
                                                        </logic:notEmpty>
                                                    </logic:notEmpty>
                                                    <logic:empty name="projectListItem"
                                                                 property="image">
                                                        <logic:notEmpty name='projectValue'
                                                                        property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty() %>'>
                                                            <bean:write name='projectValue'
                                                                        property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty() %>' />
                                                        </logic:notEmpty>
                                                    </logic:empty>
                                                    <logic:notEmpty name="projectListItem"
                                                                    property="property2">
                                                        <div>
                                                            <bean:write name='projectValue'
                                                                        property='<%= ((ProjectListItem) pageContext.getAttribute("projectListItem")).getProperty2() %>' />
                                                        </div>
                                                    </logic:notEmpty>
                                                </td>
                                            </logic:notEqual>
                                        </logic:iterate>
                                    </logic:present>
                                    <logic:notPresent name="<%= SessionKeys.EC_USER_PROJECT_LIST_ITEMS %>"
                                                      scope="session">
                                        <td>
                                            <logic:equal name="projectSecurityValue"
                                                         property="isRead"
                                                         value="true">
                                                <html:link action="/projectviewpage.do"
                                                           paramId="<%= RequestKeys.EC_PROJECT_ID %>"
                                                           paramName="projectValue"
                                                           paramProperty="projectId"
                                                           title="View Project">
                                                    <bean:write name="projectValue"
                                                                property="name" />
                                                    <logic:empty name="projectValue"
                                                                 property="inspectionCount">
                                                        <span class="muted">(0)</span>
                                                    </logic:empty>
                                                    <logic:notEmpty name="projectValue"
                                                                    property="inspectionCount">
                                                        <span class="muted">
                                                            (<bean:write name="projectValue"
                                                                         property="inspectionCount" />)
                                                        </span>
                                                    </logic:notEmpty>
                                                </html:link>
                                            </logic:equal>
                                            <logic:notEqual name="projectSecurityValue"
                                                            property="isRead"
                                                            value="true">
                                                <bean:write name="projectValue"
                                                            property="name" />
                                                <logic:empty name="projectValue"
                                                             property="inspectionCount">
                                                    <span class="muted">(0)</span>
                                                </logic:empty>
                                                <logic:notEmpty name="projectValue"
                                                                property="inspectionCount">
                                                    <span class="muted">
                                                        (<bean:write name="projectValue"
                                                                     property="inspectionCount" />)
                                                    </span>
                                                </logic:notEmpty>
                                            </logic:notEqual>
                                        </td>
                                    </logic:notPresent>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </logic:present>
                </tbody>
            </table>
        </div>
    </div>
    <logic:notPresent name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                      scope="session">
        <div class="alert">
            <bean:message key="project.empty.list" />
        </div>
        <div class="alert">
            Please note, if you are a <strong>Partner</strong>, you need to be listed on a given project as the Permit Authority, Permittee, or Authorized Inspector in order to see the project here.
        </div>
    </logic:notPresent>
    <logic:present name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                   scope="session">
        <logic:empty name="<%= SessionKeys.EC_PROJECT_LIST_DISPLAY_LIST %>"
                     scope="session">
            <div class="alert">
                <bean:message key="project.empty.list" />
            </div>
            <div class="alert">
                Please note, if you are a <strong>Partner</strong>, you need to be listed on a given project as the Permit Authority, Permittee, or Authorized Inspector in order to see the project here.
            </div>
        </logic:empty>
    </logic:present>
    <logic:present name="<%= SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL %>"
                   scope="session">
        <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL %>"
                        scope="session">
            <tiles:insert page="../../toolbar.jsp">
                <tiles:put name="controls"
                           direct="true">
                    <div class="span3">
                        <div class="pagination hidden-phone">
                            <html:select styleClass="input-medium"
                                         styleId="projectsPerPageSelect"
                                         name="projectSearchListForm"
                                         property="projectsPerPage"
                                         title="Projects Per Page"
                                         onchange="projectsPerPageSelectOnChange()">
                                <option value="25">25 per page</option>
                                <option value="50">50 per page</option>
                                <option value="75">75 per page</option>
                                <option value="100">100 per page</option>
                            </html:select>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="pagination pagination-centered hidden-phone"
                             style="overflow-x: auto;"> <%--id="frameH" --%>
                            <ul style="white-space: nowrap;"> <%--class="slidee"--%>
                                <logic:iterate id="pageControlElement"
                                               name="<%= SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL %>"
                                               scope="session"
                                               offset="4">
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
                                            <a href="javascript:setEcProjectListCurrentPageAndSubmit('<bean:write name="pageControlElement" property="value" />');">
                                                <bean:write name="pageControlElement"
                                                            property="label" />
                                            </a>
                                        </li>
                                    </logic:notEqual>
                                </logic:iterate>
                            </ul>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="pagination pull-right">
                            <ul>
                                <logic:iterate id="pageControlElement"
                                               name="<%= SessionKeys.EC_PROJECT_LIST_PAGE_CONTROL %>"
                                               scope="session"
                                               offset="0"
                                               length="4">
                                    <li>
                                        <a href="javascript:setEcProjectListCurrentPageAndSubmit('<bean:write name="pageControlElement" property="value" />');">
                                            <bean:write name="pageControlElement"
                                                        property="label" />
                                        </a>
                                    </li>
                                </logic:iterate>
                            </ul>
                        </div>
                    </div>
                </tiles:put>
            </tiles:insert>
        </logic:notEmpty>
    </logic:present>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                <% Integer projectsPerPage = (Integer) request.getSession().getAttribute(SessionKeys.EC_PROJECT_LIST_PROJECTS_PER_PAGE); %>
                if (document.getElementById('projectsPerPageSelect')
                        != null)
                {
                    for (var i = 0; i
                            < document.getElementById('projectsPerPageSelect').length; i++)
                    {
                        if (document.getElementById('projectsPerPageSelect').options[i].value
                                == <%= projectsPerPage.intValue() %>)
                        {
                            document.getElementById('projectsPerPageSelect').options[i].selected
                                    = true;
                            break;
                        }
                    }
                }
            </script>
            <script type="text/javascript">
                function setEcProjectListCurrentPageAndSubmit(page)
                {
                    var pageField = document.getElementById('<%=RequestKeys.EC_PROJECT_LIST_CURRENT_PAGE%>');
                    pageField.value
                            = page;
                    document.getElementsByName('projectSearchListForm')[0].submit();
                }
                function setSort(columnName)
                {
                    var pageField = document.getElementsByName('<%=ProjectSearchListForm.SORT_COLUMN%>')[0];
                    var currentColumn = pageField.value;
                    if (currentColumn.search(columnName)
                                == 0
                            && columnName.search(currentColumn)
                            == 0)
                    {
                        pageField
                                = document.getElementsByName('<%=ProjectSearchListForm.SORT_DIRECTION%>')[0];
                        if (pageField.value.search('<%=EcProjectSearchData.ASCENDING%>')
                                == 0)
                        {
                            pageField.value
                                    = '<%=EcProjectSearchData.DESCENDING%>';
                        }
                        else
                        {
                            pageField.value
                                    = '<%=EcProjectSearchData.ASCENDING%>';
                        }
                    }
                    else
                    {
                        pageField.value
                                = columnName;
                        document.getElementsByName('<%=ProjectSearchListForm.SORT_DIRECTION%>')[0].value
                                = '<%=EcProjectSearchData.ASCENDING%>';
                    }
                    document.getElementsByName('projectSearchListForm')[0].submit();
                }
            </script>
            <script type="text/javascript">
                function validateForm()
                {
                    if (!validateFloat(document.getElementById("searchTotalAreaSizeMin").value))
                    {
                        $('#dialog').html("Total Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchTotalAreaSizeMax").value))
                    {
                        $('#dialog').html("Total Area Max must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchAreaSizeMin").value))
                    {
                        $('#dialog').html("Disturbed Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchAreaSizeMax").value))
                    {
                        $('#dialog').html("Disturbed Area Max must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchImpAreaSizeMin").value))
                    {
                        $('#dialog').html("New Impervious Area Min must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("searchImpAreaSizeMax").value))
                    {
                        $('#dialog').html("New Impervious Area Max must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchStartDateA"))
                    {
                        $('#dialog').html("Start Date A is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchStartDateB"))
                    {
                        $('#dialog').html("Start Date B is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchEffDateA"))
                    {
                        $('#dialog').html("Effective Date A is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchEffDateB"))
                    {
                        $('#dialog').html("Effective Date B is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchSeedDateA"))
                    {
                        $('#dialog').html("Seed Date A is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    if (!validateDate("searchSeedDateB"))
                    {
                        $('#dialog').html("Seed Date B is not a valid date.(MM/dd/yyyy)").dialog('open');
                        return false;
                    }
                    return true;
                }
                function validateFloat(value)
                {
                    var bValid = true;
                    if (value.length
                            > 0)
                    {
                        // remove '.' before checking digits
                        var tempArray = value.split('.');
                        var joinedString = tempArray.join('');
                        if (!isAllDigits(joinedString))
                        {
                            bValid
                                    = false;
                        }
                        else
                        {
                            var iValue = parseFloat(value);
                            if (isNaN(iValue))
                            {
                                bValid
                                        = false;
                            }
                        }
                    }
                    return bValid;
                }
                function isAllDigits(argvalue)
                {
                    argvalue
                            = argvalue.toString();
                    var validChars = "0123456789";
                    var startFrom = 0;
                    if (argvalue.substring(0,
                                           2)
                            == "0x")
                    {
                        validChars
                                = "0123456789abcdefABCDEF";
                        startFrom
                                = 2;
                    }
                    else if (argvalue.charAt(0)
                            == "0")
                    {
                        startFrom
                                = 1;
                    }
                    else if (argvalue.charAt(0)
                            == "-")
                    {
                        startFrom
                                = 1;
                    }
                    for (var n = startFrom; n
                            < argvalue.length; n++)
                    {
                        if (validChars.indexOf(argvalue.substring(n,
                                                                  n
                                                                          + 1))
                                == -1) return false;
                    }
                    return true;
                }
                function validateDate(id)
                {
                    var field = document.getElementById(id);
                    var value = field.value;
                    var datePattern = 'MM/dd/yyyy';
                    if ((field.type
                                 == 'text'
                            || field.type
                            == 'textarea')
                                && (value.length
                            > 0)
                            && (datePattern.length
                            > 0))
                    {
                        var MONTH = "MM";
                        var DAY = "dd";
                        var YEAR = "yyyy";
                        var orderMonth = datePattern.indexOf(MONTH);
                        var orderDay = datePattern.indexOf(DAY);
                        var orderYear = datePattern.indexOf(YEAR);
                        if ((orderDay
                                     < orderYear
                                && orderDay
                                > orderMonth))
                        {
                            iDelim1
                                    = orderMonth
                                    + MONTH.length;
                            iDelim2
                                    = orderDay
                                    + DAY.length;
                            delim1
                                    = datePattern.substring(iDelim1,
                                                            iDelim1
                                                                    + 1);
                            delim2
                                    = datePattern.substring(iDelim2,
                                                            iDelim2
                                                                    + 1);
                            if (iDelim1
                                        == orderDay
                                    && iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                            }
                            else if (iDelim1
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            else if (iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{4})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            matched
                                    = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[2],
                                                 matched[1],
                                                 matched[3]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else if ((orderMonth
                                          < orderYear
                                && orderMonth
                                > orderDay))
                        {
                            iDelim1
                                    = orderDay
                                    + DAY.length;
                            iDelim2
                                    = orderMonth
                                    + MONTH.length;
                            delim1
                                    = datePattern.substring(iDelim1,
                                                            iDelim1
                                                                    + 1);
                            delim2
                                    = datePattern.substring(iDelim2,
                                                            iDelim2
                                                                    + 1);
                            if (iDelim1
                                        == orderMonth
                                    && iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                            }
                            else if (iDelim1
                                    == orderMonth)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            else if (iDelim2
                                    == orderYear)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{4})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{2})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{4})$");
                            }
                            matched
                                    = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[1],
                                                 matched[2],
                                                 matched[3]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else if ((orderMonth
                                          > orderYear
                                && orderMonth
                                < orderDay))
                        {
                            var iDelim1 = orderYear
                                    + YEAR.length;
                            var iDelim2 = orderMonth
                                    + MONTH.length;
                            var delim1 = datePattern.substring(iDelim1,
                                                               iDelim1
                                                                       + 1);
                            var delim2 = datePattern.substring(iDelim2,
                                                               iDelim2
                                                                       + 1);
                            if (iDelim1
                                        == orderMonth
                                    && iDelim2
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                            }
                            else if (iDelim1
                                    == orderMonth)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})(\\d{2})["
                                                             + delim2
                                                             + "](\\d{2})$");
                            }
                            else if (iDelim2
                                    == orderDay)
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})["
                                                             + delim1
                                                             + "](\\d{2})(\\d{2})$");
                            }
                            else
                            {
                                dateRegexp
                                        = new RegExp("^(\\d{4})["
                                                             + delim1
                                                             + "](\\d{2})["
                                                             + delim2
                                                             + "](\\d{2})$");
                            }
                            var matched = dateRegexp.exec(value);
                            if (matched
                                    != null)
                            {
                                if (!isValidDate(matched[3],
                                                 matched[2],
                                                 matched[1]))
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    return true;
                }
                function isValidDate(day, month, year)
                {
                    if (month
                                < 1
                            || month
                            > 12)
                    {
                        return false;
                    }
                    if (day
                                < 1
                            || day
                            > 31)
                    {
                        return false;
                    }
                    if ((month
                                 == 4
                                 || month
                            == 6
                                 || month
                            == 9
                            || month
                            == 11)
                            && (day
                            == 31))
                    {
                        return false;
                    }
                    if (month
                            == 2)
                    {
                        var leap = (year
                                            % 4
                                            == 0
                                && (year
                                            % 100
                                            != 0
                                || year
                                           % 400
                                == 0));
                        if (day
                                    > 29
                                || (day
                                            == 29
                                && !leap))
                        {
                            return false;
                        }
                    }
                    return true;
                }
            </script>
            <!-- This JavaScript function calls the form submit() action.
            This is necessary because we are unable to portal encode
            the form name on the onchange event. -->
            <script type="text/javascript">
                //<!--
                function projectsPerPageSelectOnChange()
                {
                    document.getElementsByName('projectSearchListForm')[0].submit();
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#searchStartDateA").datepicker({autoclose:true});
                      $("#searchStartDateB").datepicker({autoclose:true});
                      $("#searchEffDateA").datepicker({autoclose:true});
                      $("#searchEffDateB").datepicker({autoclose:true});
                      $("#searchSeedDateA").datepicker({autoclose:true});
                      $("#searchSeedDateB").datepicker({autoclose:true});
                  });
                $(function ()
                  {
                      $('select[multiple]').select2();
                  });
                $(function ()
                  {
                      $('.checkForDate').each(function ()
                                              {
                                                  var that = this;
                                                  var d = $(that).text().trim();
                                                  if (moment(d).isValid())
                                                  {
                                                      var mom = moment(d);
                                                      var label = moment({year: mom.year(), month: mom.month(), day: mom.date(), hour: moment().hour() - 4}).fromNow();
                                                      if(label.indexOf(' hours ') > -1)
                                                        label = "Today";
                                                      $(that).html("<div class='muted'><small>"
                                                                           + label
                                                                           + "</small></div>").css('visibility',
                                                                                             'visible');
                                                  }
                                              });
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
