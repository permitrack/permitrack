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
<%
    pageContext.setAttribute("bmpDbLibraryList",
                             BmpDbService.getBmpDbLibraryLabelValueList());
    /*
        Boolean
            firstRunThroughOverLibList =
            true;
        Boolean
            firstRunThroughOverCatList =
            true;
    */
    pageContext.setAttribute("bmpList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT));
%>
<html:form styleClass="form-horizontal"
           action="/bmpaddlibraryaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Save Current BMPs As BMP Library
    </legend></fieldset>
    <h4 class="myAccordian">
        BMP Library Description
    </h4>
    <div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="bmp.add.library.db.heading" />
        </label>
        <div class="controls">
            <html:text styleId="libName"
                       name="BMPLibraryDBListForm"
                       property="bmpDbLibraryName" />
        </div>
    </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel"
                             onclick="isCancelled=true;" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submitButton"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                var isCancelled = false;
                function validateForm(form)
                {
                    var libName = document.getElementById("libName").value;
                    if (!isCancelled)
                    {
                        if (trim(libName).length
                                == 0)
                        {
                            $('#dialog').html("Please enter a library name.").dialog('open');
                            return false;
                        }
                    }
                    return true; //validateBMPLibraryDBListForm(this); JRA: bug in prod! TODO: need this? validateBMPLibraryDBListForm
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

<%--

<logic:notEmpty name="<%= SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT %>"
                scope="session">
    <table class="table table-bordered">
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
            <%
                }
                categoryName =
                        ((BMPValue) pageContext.getAttribute("bmpListValue")).getCategoryName();
            %>
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
        </logic:iterate>
    </table>
</logic:notEmpty>

--%>
