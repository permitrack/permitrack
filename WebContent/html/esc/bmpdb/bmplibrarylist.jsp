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
<%@ page import="com.sehinc.erosioncontrol.server.bmpdb.BmpDbService" %>
<%pageContext.setAttribute("bmpDbLibraryList",
                           BmpDbService.getBmpDbLibraryLabelValueList());
    Boolean
            firstRunThroughOverLibList;
    Boolean
            firstRunThroughOverCatList;%>
<html:form styleClass="form-horizontal"
           action="/adminbmplibrarypage"
           method="get">
    <fieldset><legend>
        Best Management Practice (BMP) Library
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label"
               for="bmpDbLibraryId">
            <bean:message key="bmp.library.db.filter.heading" />
        </label>
        <div class="controls">
            <html:select name="BMPLibraryDBListForm"
                         property="bmpDbLibraryId"
                         styleId="bmpDbLibraryId"
                         onchange="librarySelectSubmit();">
                <html:option value="0">Select...</html:option>
                <html:options collection="bmpDbLibraryList"
                              property="value"
                              labelProperty="label" />
            </html:select>
            <logic:notEmpty name="BMPLibraryDBListForm"
                            property="bmpDbLibraryList">
                <span class="help-inline">
                    <html:link styleClass="btn btn-mini btn-success"
                               action="/adminbmplibraryeditpage"
                               paramId="libraryId"
                               paramName="BMPLibraryDBListForm"
                               paramProperty="bmpDbLibraryId">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                </span>
            </logic:notEmpty>
        </div>
    </div>
    <logic:notEmpty name="BMPLibraryDBListForm"
                    property="bmpDbLibraryList">
        <table class="table table-bordered">
            <logic:iterate id="bmpLabelValue"
                           name="BMPLibraryDBListForm"
                           property="bmpDbLibraryList">
                <%
                    String
                            categoryName;
                    firstRunThroughOverLibList =
                            true;
                    firstRunThroughOverCatList =
                            true;
                    String
                            firstCatName =
                            null;%>
                <logic:notEmpty name="BMPLibraryDBListForm"
                                property="bmpDbList">
                    <logic:iterate id="bmpListValue"
                                   name="BMPLibraryDBListForm"
                                   property="bmpDbList">
                        <%
                            if (firstRunThroughOverLibList)
                            {
                        %>
                        <% }
                            firstRunThroughOverLibList =
                                    false;
                            categoryName =
                                    ((com.sehinc.erosioncontrol.value.bmpdb.BMPDbValue) pageContext.getAttribute("bmpListValue")).getCategoryName();
                            if (firstRunThroughOverCatList
                                && firstCatName
                                   == null)
                            {
                                firstCatName =
                                        categoryName;
                            }
                            else
                            {
                                if (!firstCatName.equalsIgnoreCase(categoryName))
                                {
                                    firstRunThroughOverCatList =
                                            true;
                                    firstCatName =
                                            categoryName;
                                }
                            }
                            String
                                    libraryName =
                                    ((com.sehinc.erosioncontrol.value.bmpdb.BMPDbValue) pageContext.getAttribute("bmpListValue")).getLibraryName();%>
                        <logic:equal name="bmpLabelValue"
                                     property="label"
                                     value="<%= libraryName %>">

                            <%if (firstRunThroughOverCatList
                                  && firstCatName
                                     != null)
                            {%>
                            <thead>
                                <tr>
                                    <th colspan="3">
                                        <bean:write name="bmpListValue"
                                                    property="categoryName" />
                                    </th>
                                </tr>
                                <tr>
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
                            </thead>
                            <%}
                                firstRunThroughOverCatList =
                                        false;%>
                            <tr>
                                <td>
                                    <bean:write name="bmpListValue"
                                                property="name" />
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
                        </logic:equal>
                    </logic:iterate>
                </logic:notEmpty>
                <logic:empty name="BMPLibraryDBListForm"
                             property="bmpDbList">
                    <tr>
                        <td>
                            <bean:message key="bmp.library.load.empty" />
                        </td>
                    </tr>
                </logic:empty>
            </logic:iterate>
        </table>
    </logic:notEmpty>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- This JavaScript function calls the form submit() action.
            This is necessary because we are unable to portal encode
            the form name on the onchange event. -->
            <script type="text/javascript">
                //<!--
                function librarySelectSubmit()
                {
                    document.BMPLibraryDBListForm.submit();
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
