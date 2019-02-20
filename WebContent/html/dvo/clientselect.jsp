<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.dataview.action.base.SessionKeys" %>
<script type="text/javascript">
    //<!--Begin
    function validateForm()
    {
        if (document.getElementById("clientSelect").options[0].selected)
        {
            alert("Please select a client from the list.");
            return false;
        }
        return true;
    }// -->
</script>
<logic:present name="<%= SessionKeys.DV_ADMIN_CLIENT_SELECT_LIST %>"
               scope="session">
    <html:form action="/clientselect"
               onsubmit="return validateForm();">
        <div>
            <div>
                <label for="clientSelect">
                    Select a Client
                </label>
            </div>
            <div>
                <html:select styleId="clientSelect"
                             name="clientForm"
                             property="clientId">
                    <html:option value="">Please Select a Client...</html:option>
                    <html:options collection="<%= SessionKeys.DV_ADMIN_CLIENT_SELECT_LIST %>"
                                  property="id"
                                  labelProperty="name" />
                </html:select>
            </div>
        </div>
        <div id="actionsSmall">
            <div>
                <span>
                    <html:submit property="submit"
                                 value="OK" />
                </span>
            </div>
        </div>
    </html:form>
</logic:present>

