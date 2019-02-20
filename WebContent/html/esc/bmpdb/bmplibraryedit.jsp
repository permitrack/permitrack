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
<html:form styleClass="form-horizontal"
           action="/adminbmpeditaction">
    <fieldset><legend>
        <bean:write name="BMPLibraryEditForm"
                    property="libraryName" />
    </legend></fieldset>
    <html:hidden property="libraryId"
                 name="BMPLibraryEditForm" />
    <logic:notEmpty name="BMPLibraryEditForm"
                    property="bmpCategories">
        <table class="table table-bordered action-first action-small">
            <thead>
                <tr>
                    <th>
                        Delete
                    </th>
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
            <logic:iterate id="category"
                           name="BMPLibraryEditForm"
                           property="bmpCategories">
                <thead>
                    <tr>
                        <th colspan="4">
                            <bean:write name="category"
                                        property="name" />
                        </th>
                    </tr>
                </thead>
                <logic:iterate id="bmp"
                               name="category"
                               property="bmpList">
                    <tr>
                        <td>
                            <html:multibox name="BMPLibraryEditForm"
                                           property="bmpDeletes">
                                <bean:write name='bmp'
                                            property='id' />
                            </html:multibox>
                        </td>
                        <td>
                            <bean:write name="bmp"
                                        property="name" />
                        </td>
                        <td>
                            <bean:write name="bmp"
                                        property="description" />
                        </td>
                        <td>
                            <a href='<bean:write name="bmp" property="weblink" />'>
                                <bean:write name="bmp"
                                            property="weblink" />
                            </a>
                        </td>
                    </tr>
                </logic:iterate>
            </logic:iterate>
        </table>
    </logic:notEmpty>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Delete Checked Items" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
