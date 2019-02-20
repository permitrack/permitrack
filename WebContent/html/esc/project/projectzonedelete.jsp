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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/projectzonedeleteaction"
           onsubmit="return validateForm(this);">
    <legend>
        <bean:message key="project.zone.delete.heading" />
        <bean:write name="projectZoneDeleteForm"
                    property="name" /></legend>
    <html:hidden styleId="id"
                 name="projectZoneDeleteForm"
                 property="id" />
    <div class="control-group">
        <label class="control-label"
               for="radioDelete">
            Deletion method
        </label>
        <div class="controls">
            <label class="radio"
                   for="radioDelete">
                <html:radio styleId="radioDelete"
                            property="deleteOption"
                            value="delete"
                            alt="Delete Group" />
                <bean:message key="project.zone.delete" />
                <span class="help-inline text-info">This will replace all projects assigned to this Group with the Group "N/A", then delete this Group</span>
            </label>
            <label class="radio"
                   for="radioReplace">
                <html:radio styleId="radioReplace"
                            property="deleteOption"
                            value="replace"
                            alt="Replace Group" />
                <bean:message key="project.zone.replace" />
                <span class="help-inline text-info">This will replace all projects assigned to this Group with the Group chosen below, then delete this Group</span>
            </label>
            <div style="padding-top: 10px;">
                Select Group to replace "<bean:write name="projectZoneDeleteForm"
                                                    property="name" />" before deletion
                <div>
                    <html:select styleId="selectReplace"
                                 name="projectZoneDeleteForm"
                                 property="projectZoneReplaceId">
                        <html:option value="0">Select... </html:option>
                        <logic:iterate id="theZone"
                                       name="<%= SessionKeys.EC_ZONE_LIST %>"
                                       scope="session">
                            <option value="<bean:write name='theZone' property='id'/>">
                                <bean:write name='theZone'
                                            property='name' />
                            </option>
                        </logic:iterate>
                    </html:select>
                </div>
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
                             property="submit"
                             value="Delete" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!-- Begin
                /*
                 This script and many more are available free online at
                 The JavaScript Source!! http://javascript.internet.com
                 Created by: Husay :: http://www.communitxt.net
                 */
                var isCancelled = false;
                function validateForm(form)
                {
                    if (isCancelled) return true;
                    if (!document.forms[form.name].elements['radioDelete'].checked
                            && !document.forms[form.name].elements['radioReplace'].checked)
                    {
                        $('#dialog').html("Please choose a delete option.").dialog('open');
                        return false;
                    }
                    if (document.forms[form.name].elements['radioReplace'].checked
                            && document.forms[form.name].elements['selectReplace'].value
                            == '0')
                    {
                        $('#dialog').html("Please select a replacement group.").dialog('open');
                        return false;
                    }
                    return true;
                }// End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
