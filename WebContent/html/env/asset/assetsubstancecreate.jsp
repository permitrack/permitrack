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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/assetsubstancecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        Add Control To Asset
    </legend></fieldset>
    <html:hidden property="assetId" />
    <h4 class="myAccordian">
        Control
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"><bean:message key="substance.substanceType" />
            </label>
            <div class="controls"><html:select name="AssetSubstanceForm"
                                               property="substanceTypeCd"
                                               styleId="substanceTypeCd">
                <html:option value="0">Select a Substance Type...</html:option>
                <html:options collection="<%=SessionKeys.EV_SUBSTANCE_TYPE_LIST%>"
                              property="code"
                              labelProperty="description" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.substance.efficiency.factor" />
            </label>
            <div class="controls"><html:text name="AssetSubstanceForm"
                                             styleId="efficiencyFactor"
                                             property="efficiencyFactor"
                                             size="4"
                                             maxlength="3" />
                %
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="asset.substance.addl.info" />
            </label>
            <div class="controls"><html:textarea name="AssetSubstanceForm"
                                                 styleId="additionalInfo"
                                                 property="additionalInfo"
                                                 cols="25"
                                                 rows="4" />
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
                             value="Add" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="AssetSubstanceForm" />
            <script type="text/javascript">
                //<!--
                var isCancelled = false;
                function validateForm(form)
                {
                    if (isCancelled)
                    {
                        return true;
                    }
                    else
                    {
                        return validateAssetSubstanceForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>