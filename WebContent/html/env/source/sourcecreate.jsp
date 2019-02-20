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
           action="/sourcecreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Source
    </legend></fieldset>
    <html:hidden property="id" />
    <h4 class="myAccordian">
        Source Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.sourceType" />
            </label>
            <div class="controls"><html:select styleId="typeSelect"
                                               name="SourceForm"
                                               property="sourceTypeCd">
                <html:option value="">Please Select a Source Type...</html:option>
                <html:options collection="<%= SessionKeys.EV_SOURCE_TYPE_LIST %>"
                              property="code"
                              labelProperty="description" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.desc" /> *
            </label>
            <div class="controls"><html:text styleId="description"
                                             name="SourceForm"
                                             property="description"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.partNumber" /> *
            </label>
            <div class="controls"><html:text property="partNumber"
                                             name="SourceForm"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.batchNumber" /> *
            </label>
            <div class="controls">
                <html:text property="batchNumber"
                           name="SourceForm"
                           maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.info.origin" />
            </label>
            <div class="controls"><html:text styleId="infoOrigin"
                                             property="infoOrigin"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.info.color" />
            </label>
            <div class="controls"><html:select styleId="colorSelect"
                                               name="SourceForm"
                                               property="displayColorCd">
                <html:option value="">
                    Select a Color...</html:option>
                <html:option value="#000000"
                             style="background-color: Black; color: #FFFFFF;">
                    Black</html:option>
                <html:option value="#808080"
                             style="background-color: Gray;">
                    Gray</html:option>
                <html:option value="#A9A9A9"
                             style="background-color: DarkGray;">
                    Dark Gray</html:option>
                <html:option value="#D3D3D3"
                             style="background-color: #D3D3D3;">
                    Light Gray</html:option>
                <html:option value="#FFFFFF"
                             style="background-color: White;">
                    White</html:option>
                <html:option value="#7FFFD4"
                             style="background-color: Aquamarine;">
                    Aquamarine</html:option>
                <html:option value="#0000FF"
                             style="background-color: Blue;">
                    Blue</html:option>
                <html:option value="#000080"
                             style="background-color: Navy;color: #FFFFFF;">
                    Navy</html:option>
                <html:option value="#800080"
                             style="background-color: Purple;color: #FFFFFF;">
                    Purple</html:option>
                <html:option value="#FF1493"
                             style="background-color: DeepPink;">
                    Deep Pink</html:option>
                <html:option value="#EE82EE"
                             style="background-color: Violet;">
                    Violet</html:option>
                <html:option value="#FFC0CB"
                             style="background-color: Pink;">
                    Pink</html:option>
                <html:option value="#006400"
                             style="background-color: DarkGreen;color: #FFFFFF;">
                    Dark Green</html:option>
                <html:option value="#008000"
                             style="background-color: Green;color: #FFFFFF;">
                    Green</html:option>
                <html:option value="#9ACD32"
                             style="background-color: YellowGreen;">
                    Yellow Green</html:option>
                <html:option value="#FFFF00"
                             style="background-color: Yellow;">
                    Yellow</html:option>
                <html:option value="#FFA500"
                             style="background-color: Orange;">
                    Orange</html:option>
                <html:option value="#FF0000"
                             style="background-color: Red;">
                    Red</html:option>
                <html:option value="#A52A2A"
                             style="background-color: Brown;">
                    Brown</html:option>
                <html:option value="#DEB887"
                             style="background-color: BurlyWood;">
                    Burly Wood</html:option>
                <html:option value="#F5F5DC"
                             style="background-color: Beige;">
                    Beige</html:option>
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <p class="text-warning">
                    <bean:message key="source.displayNameInfo" />
                </p>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Physical Characteristics
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="source.lbs.voc" />
            </label>
            <div class="controls">
                <html:text styleId="lbsVoc"
                           property="lbsVoc"
                           name="SourceForm"
                           size="20" />
                <html:select name="SourceForm"
                             property="lbsVocUm"
                             styleId="lbsVocUm"
                             styleClass="input-medium">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_VOC_UM_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.density" />
            </label>
            <div class="controls"><html:text property="density"
                                             styleId="density"
                                             name="SourceForm"
                                             size="20" />
                <html:select name="SourceForm"
                             property="densityUm"
                             styleId="densityUm"
                             styleClass="input-medium">
                    <html:option value="0">Select a Unit...</html:option>
                    <html:options collection="<%=SessionKeys.EV_DENSITY_UM_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.lbs.haps" />
            </label>
            <div class="controls"><html:text property="lbsHaps"
                                             name="SourceForm"
                                             styleId="lbsHaps"
                                             size="20" />
                <html:select name="SourceForm"
                             property="lbsHapsUm"
                             styleId="lbsHapsUm"
                             styleClass="input-medium">
                    <html:option value="0">
                        Select a Unit...
                    </html:option>
                    <html:options collection="<%=SessionKeys.EV_HAPS_UM_LIST%>"
                                  property="code"
                                  labelProperty="description" />
                </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.pct.solids.weight" />
            </label>
            <div class="controls"><html:text property="pctSolidsWeight"
                                             name="SourceForm"
                                             size="20" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.pct.solids.volume" />
            </label>
            <div class="controls"><html:text property="pctSolidsVolume"
                                             name="SourceForm"
                                             size="20" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="source.hc.fuel" />
            </label>
            <div class="controls"><html:text property="hcFuel"
                                             name="SourceForm"
                                             size="20" />
                BTU/cf
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
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="SourceForm" />
            <script type="text/javascript">
                var isCancelled = false;
                function validateForm(form)
                {
                    if (isCancelled)
                    {
                        return true;
                    }
                    else
                    {
                        if (document.getElementById("typeSelect").options[0].selected)
                        {
                            $('#dialog').html("Please select a type from the list.").dialog('open');
                            return false;
                        }
                        var lbsVoc = document.getElementById("lbsVoc").value;
                        var density = document.getElementById("density").value;
                        var lbsHaps = document.getElementById("lbsHaps").value;
                        if (lbsVoc
                                    < 0
                                || lbsVoc
                                > 0)
                        {
                            if (document.getElementById("lbsVocUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for VOC Content").dialog('open');
                                return false;
                            }
                        }
                        if (density
                                    < 0
                                || density
                                > 0)
                        {
                            if (document.getElementById("densityUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for Density.").dialog('open');
                                return false;
                            }
                        }
                        if (lbsHaps
                                    < 0
                                || lbsHaps
                                > 0)
                        {
                            if (document.getElementById("lbsHapsUm").options[0].selected)
                            {
                                $('#dialog').html("Please select a Unit of Measure for HAPS Content.").dialog('open');
                                return false;
                            }
                        }
                        return validateSourceForm(form);
                    }
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
