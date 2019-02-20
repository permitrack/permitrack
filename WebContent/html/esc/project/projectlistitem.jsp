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
                 com.sehinc.erosioncontrol.action.project.ProjectListItem,
                 java.util.Collection,
                 java.util.Iterator" %>
<html:form action="/projectlistitemsaveaction">
    <fieldset><legend>
        My Settings
    </legend></fieldset>
    <h4 class="myAccordian">
        Project List Columns
    </h4>
    <div>
    <%
        boolean
                foundItem;
        Iterator
                projectItemListIter =
                ((Collection) request.getSession()
                        .getAttribute(SessionKeys.EC_PROJECT_LIST_ITEMS)).iterator();
        while (projectItemListIter.hasNext())
        {
            ProjectListItem
                    projectListItem =
                    (ProjectListItem) projectItemListIter.next();
            foundItem =
                    false;
            Iterator
                    userItemListIter =
                    ((Collection) request.getSession()
                            .getAttribute(SessionKeys.EC_USER_PROJECT_LIST_ITEMS)).iterator();
            while (userItemListIter.hasNext())
            {
                ProjectListItem
                        userListItem =
                        (ProjectListItem) userItemListIter.next();
                if (!foundItem
                    && projectListItem.getKey()
                        .equals(userListItem.getKey()))
                {
                    foundItem =
                            true;
                }
            }
            if (foundItem)
            {
                if (projectListItem.getKey()
                        .equals(ProjectListItem.PROJECT_NAME
                                        .getKey()))
                {%>
        <label class="checkbox">
            <input type="checkbox"
                   name="ecProjectListItems"
                   value="<%= projectListItem.getKey() %>"
                   checked='checked'
                   disabled='disabled'>
            <%= projectListItem.getName() %>
        </label>
        <%
        }
        else
        {
        %>
        <label class="checkbox">
            <input type="checkbox"
                   name="ecProjectListItems"
                   value="<%= projectListItem.getKey() %>"
                   checked='checked'>
            <%= projectListItem.getName() %>
        </label>
        <%
            }
        }
        else
        {
        %>
        <label class="checkbox">
            <input type="checkbox"
                   name="ecProjectListItems"
                   value="<%= projectListItem.getKey() %>">
            <%= projectListItem.getName() %>
        </label>
        <%
                }
            }
        %>
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
                             property="submit"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
