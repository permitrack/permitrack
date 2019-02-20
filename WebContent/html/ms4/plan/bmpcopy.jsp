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
<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.action.base.SessionKeys" %>
<%pageContext.setAttribute(CommonConstants.MODE,
                           request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("action",
                             StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                     .equals("move")
                                     ? "bmpmove"
                                     : "bmpcopy");%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateForm(this);">
    <logic:equal name="mode"
                 value="move">
        <html:hidden property="operation"
                     value="move" />
    </logic:equal>
    <logic:equal name="mode"
                 value="copy">
        <html:hidden property="operation"
                     value="copy" />
    </logic:equal>
    <fieldset><legend>
        <logic:equal name="mode"
                     value="move">
            <bean:message key="bmp.move.heading" />
        </logic:equal>
        <logic:notEqual name="mode"
                        value="move">
            <bean:message key="bmp.copy.heading" />
        </logic:notEqual>
    </legend></fieldset>
    <div class="control-group">
        <label class="control-label"
               for="mcmSelect">
            <logic:equal name="mode"
                         value="move">
                <bean:message key="bmp.move.select.mcm" />
            </logic:equal>
            <logic:equal name="mode"
                         value="copy">
                <bean:message key="bmp.copy.select.mcm" />
            </logic:equal>
        </label>
        <div class="controls">
            <html:select styleId="mcmSelect"
                         property="mcmId">
                <html:option value="0">
                    <logic:equal name="mode"
                                 value="move">
                        <bean:message key="bmp.move.mcm.select.option" />
                    </logic:equal>
                    <logic:equal name="mode"
                                 value="copy">
                        <bean:message key="bmp.copy.mcm.select.option" />
                    </logic:equal>
                </html:option>
                <logic:equal name="mode"
                             value="move">
                    <html:options collection="<%= SessionKeys.BMP_MOVE_MCM_LIST %>"
                                  property="id"
                                  labelProperty="name" />
                </logic:equal>
                <logic:equal name="mode"
                             value="copy">
                    <html:options collection="<%= SessionKeys.BMP_COPY_MCM_LIST %>"
                                  property="id"
                                  labelProperty="name" />
                </logic:equal>
            </html:select>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <p class="text-warning">
                <logic:equal name="mode"
                             value="move">
                    <bean:message key="bmp.move.select.mcm.note" />
                </logic:equal>
                <logic:equal name="mode"
                             value="copy">
                    <bean:message key="bmp.copy.select.mcm.note" />
                </logic:equal>
            </p>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="tertiaryMenu"
                   direct="true">
            <div class="span6">
                <tiles:get name="tertiaryMenu"
                           flush="false" />
            </div>
        </tiles:put>
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit" />
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
                function validateForm()
                {
                    document.getElementById("mcmSelect").options
                            = true;
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
