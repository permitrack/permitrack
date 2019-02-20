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
                 com.sehinc.erosioncontrol.server.bmpdb.BmpDbService,
                 com.sehinc.erosioncontrol.value.bmp.BMPValue" %>
<%pageContext.setAttribute("bmpDbLibraryList",
                           BmpDbService.getBmpDbLibraryLabelValueList());
    Boolean
            firstRunThroughOverLibList =
            true;
    Boolean
            firstRunThroughOverCatList =
            true;
    pageContext.setAttribute("bmpList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT));%>
<html:form styleClass="form-horizontal"
           action="/bmploadlibraryaction"
           method="get">
    <fieldset><legend>
        Load BMPs from BMP Library
    </legend></fieldset>
    <h4 class="myAccordian">
        BMP Libraries
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="bmpDbLibraryId">
                <bean:message key="bmp.load.library.db.filter.heading" />
            </label>
            <div class="controls">
                <html:select name="BMPLibraryDBListForm"
                             property="bmpDbLibraryId"
                             styleId="bmpDbLibraryId"
                             onchange="librarySelectSubmit();">
                    <html:option value="0">Select a library...</html:option>
                    <html:options collection="bmpDbLibraryList"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="bmp.load.library.db.filter.heading" />
    </h4>
    <div>
    <logic:notEmpty name="BMPLibraryDBListForm"
                    property="bmpDbLibraryList">
        <%--
                <table class="table table-bordered table-hover">
                    <colgroup>
                        <col />
                        <col />
                        <col style="max-width: 33%; word-break: break-all; white-space: normal;" />
                    </colgroup>
        --%>
        <logic:iterate id="bmpLabelValue"
                       name="BMPLibraryDBListForm"
                       property="bmpDbLibraryList">
                                            <%
                                                String
                                                        categoryName =
                                                        "";
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

                                                <%if (firstRunThroughOverLibList)
                                                {
                                                    if (!categoryName.isEmpty())
                                                    {%>
                    </div> <%-- NEED THIS! --%>
                    <%}%>
                    <h5>
                        <bean:write name="bmpLabelValue"
                                    property="label" />
                    </h5>
                    <div class="well">
                    <%}
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
                        <%--<thead>--%>
                        <p>
                        <div class="label">
                            <bean:write name="bmpListValue"
                                        property="categoryName" />
                        </div>
                        </p>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%}
                            firstRunThroughOverCatList =
                                    false;%>
                        <%--<tr>--%>
                        <p>
                            <strong>
                                <bean:write name="bmpListValue"
                                            property="name" />
                            </strong>
                                <%--<p>--%>
                            <bean:write name="bmpListValue"
                                        property="description" />
                                <%--</p>--%>
                            <a href="<bean:write name="bmpListValue" property="weblink"/>"
                               target="_blank">
                                <bean:write name="bmpListValue"
                                            property="weblink" />
                            </a>
                        </p>
                    </logic:equal>
                </logic:iterate>
                </div>
            </logic:notEmpty>
            <logic:empty name="BMPLibraryDBListForm"
                         property="bmpDbList">
                <p class="text-warning">
                    <bean:message key="bmp.library.load.empty" />
                </p>
            </logic:empty>
        </logic:iterate>
    </logic:notEmpty>
    <logic:empty name="BMPLibraryDBListForm"
                 property="bmpDbLibraryList">
        <p>
            Choose a library to load from above
        </p>
    </logic:empty>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submitButton"
                             value="Load" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
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
<logic:notEmpty name="<%= SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT %>"
                scope="session">
    <h4 class="myAccordian">
        <bean:message key="bmp.library.current.bmp.list" />
    </h4>
    <div>

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
        </p>

    </logic:iterate>
    </div>
    </div>
</logic:notEmpty>
