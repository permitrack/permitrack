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
<%@ page import="com.sehinc.common.CommonConstants,
                 com.sehinc.common.action.base.SessionService,
                 com.sehinc.common.security.SecurityManager,
                 com.sehinc.common.service.user.UserService,
                 com.sehinc.common.util.BeanUtil" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.common.value.client.ClientValue" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page import="com.sehinc.stormwater.server.bmpdb.BMPDBService" %>
<%@ page import="java.util.ArrayList" %>
<%
    ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
    pageContext.setAttribute("ownerList",
                             clientValue
                             == null
                                     ? null
                                     : UserService.getUserBeanList(clientValue));
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "bmp"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             +
                             (StringUtil.nullSafeToString(pageContext.getAttribute(CommonConstants.MODE))
                                      .equals("create")
                                      ? "create"
                                      : "edit"));
    pageContext.setAttribute("isClientAdmin",
                             SecurityManager.getSecurityManager(request)
                                     .getSecurityLevelId()
                             <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID);
    pageContext.setAttribute("categoryList",
                             BMPDBService.getBmpDbCategoryLabelValueList());%>
<html:form styleClass="form-horizontal"
           action='<%= (String) pageContext.getAttribute("action") %>'
           onsubmit="return validateAll(this);">
    <fieldset><legend>
        <logic:equal name="mode"
                     value="create">
            New Best Management Practice
        </logic:equal>
        <logic:notEqual name="mode"
                        value="create">
            <bean:write name="bmpForm"
                        property="name" />
            <logic:equal name="isClientAdmin"
                         value="true">
                <html:link styleClass="btn btn-mini btn-success"
                           action='<%= "goal" + StringUtil.nullSafeToString(pageContext.getAttribute("type")) + "createaction" %>'
                           paramName="bmpForm"
                           paramId="id"
                           paramProperty="id">
                    + New Goal
                </html:link>
            </logic:equal>
        </logic:notEqual>
    </legend></fieldset>
    <logic:equal name="type"
                 value="library">
        <div class="control-group">
            <label class="control-label"
                   for="isSelectCategoryId">
                <bean:message key="bmpdb.category.edit.heading" />
            </label>
            <div class="controls">
                <label class="radio"
                       for="isSelectCategoryId">
                    <html:radio styleId="isSelectCategoryId"
                                property="isNewCategory"
                                value="false"
                                onclick="toggleSelectCategory();" />
                    <bean:message key="bmpdb.existing.category" />
                </label>
                <a href="#"
                   id="bmpDbCategoryId"
                   data-type="select"
                   data-original-title='<bean:message key="bmp.category" />'
                   data-autotext='always'></a>
                <html:hidden property="bmpDbCategoryId"
                             styleId="bmpDbCategoryId" />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <label class="radio"
                       for="isNewCategoryId">
                    <html:radio property="isNewCategory"
                                styleId="isNewCategoryId"
                                value="true"
                                onclick="toggleNewCategory();" />
                    <bean:message key="bmpdb.new.category.name" />
                </label>
                <a href="#"
                   id="bmpDbCategoryName"
                   data-type="text"
                   data-original-title='Category Name'
                   data-disabled="true">
                    <bean:write name="bmpForm"
                                property="bmpDbCategoryName" />
                </a>
                <html:hidden property="bmpDbCategoryName"
                             styleId="bmpDbCategoryName" />
            </div>
        </div>
    </logic:equal>
    <div class="control-group">
        <label class="control-label"
               for="name">
            <bean:message key="bmp.name" />
        </label>
        <div class="controls">
            <a href="#"
               id="name"
               data-type="text"
               data-original-title='<bean:message key="bmp.name" />'>
                <bean:write name="bmpForm"
                            property="name" />
            </a>
            <html:hidden property="name"
                         styleId="name" />
        </div>
    </div>
    <html:hidden property="useSection" />
    <logic:equal value="true"
                 property="useSection"
                 name="bmpForm">
        <div class="control-group">
            <label class="control-label"
                   for="section">
                <bean:message key="bmp.section" />
            </label>
            <div class="controls">
                <a href="#"
                   id="section"
                   data-type="text"
                   data-original-title='<bean:message key="bmp.section" />'>
                    <bean:write name="bmpForm"
                                property="section" />
                </a>
                <html:hidden property="section"
                             styleId="section" />
            </div>
        </div>
    </logic:equal>
    <div class="control-group">
        <label class="control-label"
               for="number">
            <bean:message key="bmp.number" />
        </label>
        <div class="controls">
            <a href="#"
               id="number"
               data-type="text"
               data-original-title='<bean:message key="bmp.number" />'>
                <bean:write name="bmpForm"
                            property="number" />
            </a>
            <html:hidden property="number"
                         styleId="number" />
        </div>
    </div>
    <logic:notEqual name="type"
                    value="template">
        <logic:notEqual name="type"
                        value="library">
            <div class="control-group">
                <label class="control-label"
                       for="ownerId">
                    <bean:message key="bmp.owner" />
                </label>
                <div class="controls">
                    <a href="#"
                       id="ownerId"
                       data-type="select"
                       data-original-title='<bean:message key="bmp.owner" />'
                       data-autotext='always'></a>
                    <html:hidden property="ownerId"
                                 styleId="ownerId" />
                </div>
            </div>
        </logic:notEqual>
    </logic:notEqual>
    <div class="control-group">
        <label class="control-label"
               for="requiredStringYes">
            <bean:message key="bmp.required" />
        </label>
        <div class="controls">
            <label class="radio inline"
                   for="requiredStringYes">
                <html:radio name="bmpForm"
                            property="requiredString"
                            styleId="requiredStringYes"
                            value="Yes" />
                Yes
            </label>
            <label class="radio inline"
                   for="requiredStringNo">
                <html:radio name="bmpForm"
                            property="requiredString"
                            styleId="requiredStringNo"
                            value="No" />
                No
            </label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="description">
            <bean:write name="bmpForm"
                        property="field_name1"
                        filter="false" />
        </label>
        <div class="controls">
            <html:hidden property="input_type1" />
            <html:hidden property="field_name1" />
            <html:hidden property="explanation_1" />
            <html:hidden property="field_type1" />
            <logic:notEmpty name="bmpForm"
                            property="explanation_1">
                <div class="alert alert-info">
                    <bean:write name="bmpForm"
                                property="explanation_1"
                                filter="false" />
                </div>
            </logic:notEmpty>
            <logic:equal value="textarea"
                         property="input_type1"
                         name="bmpForm">
                <div id="description"
                     data-type="wysihtml5">
                    <bean:write name="bmpForm"
                                property="field_value1"
                                filter="false" />
                </div>
                <html:hidden property="field_value1"
                             styleId="description"
                             alt="" />
            </logic:equal>
            <logic:notEqual value="textarea"
                            property="input_type1"
                            name="bmpForm">
                <bean:write name="bmpForm"
                            property="field_value1"
                            filter="false" />
                <html:hidden name="bmpForm"
                             property="field_value1" />
            </logic:notEqual>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="justification">
            <bean:write name="bmpForm"
                        property="field_name2"
                        filter="false" />
        </label>
        <div class="controls">
            <html:hidden property="input_type2" />
            <html:hidden property="field_name2" />
            <html:hidden property="explanation_2" />
            <html:hidden property="field_type2" />
            <logic:notEmpty name="bmpForm"
                            property="explanation_2">
                <div class="alert alert-info">
                    <bean:write name="bmpForm"
                                property="explanation_2"
                                filter="false" />
                </div>
            </logic:notEmpty>
            <logic:equal value="textarea"
                         property="input_type2"
                         name="bmpForm">
                <div id="justification"
                     data-type="wysihtml5">
                    <bean:write name="bmpForm"
                                property="field_value2"
                                filter="false" />
                </div>
                <html:hidden property="field_value2"
                             styleId="justification"
                             alt="" />
            </logic:equal>
            <logic:notEqual value="textarea"
                            property="input_type2"
                            name="bmpForm">
                <bean:write name="bmpForm"
                            property="field_value2"
                            filter="false" />
                <html:hidden name="bmpForm"
                             property="field_value2" />
            </logic:notEqual>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="publicEducation">
            <bean:write name="bmpForm"
                        property="field_name3"
                        filter="false" />
        </label>
        <div class="controls">
            <html:hidden property="input_type3" />
            <html:hidden property="field_name3" />
            <html:hidden property="explanation_3" />
            <html:hidden property="field_type3" />
            <logic:notEmpty name="bmpForm"
                            property="explanation_3">
                <div class="alert alert-info">
                    <bean:write name="bmpForm"
                                property="explanation_3"
                                filter="false" />
                </div>
            </logic:notEmpty>
            <logic:equal value="textarea"
                         property="input_type3"
                         name="bmpForm">
                <div id="publicEducation"
                     data-type="wysihtml5">
                    <bean:write name="bmpForm"
                                property="field_value3"
                                filter="false" />
                </div>
                <html:hidden property="field_value3"
                             styleId="publicEducation"
                             alt="" />
            </logic:equal>
            <logic:notEqual value="textarea"
                            property="input_type3"
                            name="bmpForm">
                <bean:write name="bmpForm"
                            property="field_value3"
                            filter="false" />
                <html:hidden name="bmpForm"
                             property="field_value3" />
            </logic:notEqual>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"
               for="reportingItems">
            <bean:write name="bmpForm"
                        property="field_name4"
                        filter="false" />
        </label>
        <div class="controls">
            <html:hidden property="input_type4" />
            <html:hidden property="field_name4" />
            <html:hidden property="explanation_4" />
            <html:hidden property="field_type4" />
            <logic:notEmpty name="bmpForm"
                            property="explanation_4">
                <div class="alert alert-info">
                    <bean:write name="bmpForm"
                                property="explanation_4"
                                filter="false" />
                </div>
            </logic:notEmpty>
            <logic:equal value="textarea"
                         property="input_type4"
                         name="bmpForm">
                <div id="reportingItems"
                     data-type="wysihtml5">
                    <bean:write name="bmpForm"
                                property="field_value4"
                                filter="false" />
                </div>
                <html:hidden property="field_value4"
                             styleId="reportingItems"
                             alt="" />
            </logic:equal>
            <logic:notEqual value="textarea"
                            property="input_type4"
                            name="bmpForm">
                <bean:write name="bmpForm"
                            property="field_value4"
                            filter="false" />
                <html:hidden name="bmpForm"
                             property="field_value4" />
            </logic:notEqual>
        </div>
    </div>
    <logic:notEqual value="0"
                    property="field_type5"
                    name="bmpForm">
        <div class="control-group">
            <label class="control-label"
                   for="field_value5">
                <bean:write name="bmpForm"
                            property="field_name5"
                            filter="false" />
            </label>
            <div class="controls">
                <html:hidden property="input_type5" />
                <html:hidden property="field_name5" />
                <html:hidden property="explanation_5" />
                <html:hidden property="field_type5" />
                <logic:notEmpty name="bmpForm"
                                property="explanation_5">
                    <div class="alert alert-info">
                        <bean:write name="bmpForm"
                                    property="explanation_5"
                                    filter="false" />
                    </div>
                </logic:notEmpty>
                <logic:equal value="textarea"
                             property="input_type5"
                             name="bmpForm">
                    <div id="field_value5"
                         data-type="wysihtml5">
                        <bean:write name="bmpForm"
                                    property="field_value5"
                                    filter="false" />
                    </div>
                    <html:hidden property="field_value5"
                                 styleId="field_value5"
                                 alt="" />
                </logic:equal>
                <logic:notEqual value="textarea"
                                property="input_type5"
                                name="bmpForm">
                    <bean:write name="bmpForm"
                                property="field_value5"
                                filter="false" />
                    <html:hidden name="bmpForm"
                                 property="field_value5" />
                </logic:notEqual>
            </div>
        </div>
    </logic:notEqual>
    <logic:equal value="0"
                 property="field_type5"
                 name="bmpForm">
        <html:hidden property="input_type5" />
        <html:hidden property="field_name5" />
        <html:hidden property="explanation_5" />
        <html:hidden property="field_type5" />
        <html:hidden property="field_value5" />
    </logic:equal>
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
                <html:submit styleClass="btn btn-large btn-success pull-right"
                             property="submit"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <!-- Include the Struts validation JavaScript -->
            <html:javascript formName="bmpForm" />
            <!-- Bootstrap X-Editable -->
            <script type="text/javascript">
                //<!--
                function getDataValue(id)
                {
                <logic:notEqual name="type"
                                value="template">
                <logic:notEqual name="type"
                                value="library">
                    if (id
                            == 'ownerId')
                    {
                        return <bean:write name="bmpForm" property="ownerId" />;
                    }
                </logic:notEqual>
                </logic:notEqual>
                <logic:equal name="type"
                                value="library">
                    if (id
                            == 'bmpDbCategoryId')
                    {
                        return <bean:write name="bmpForm" property="bmpDbCategoryId" />;
                    }
                </logic:equal>
                    return null;
                }
                function getDataSource(id)
                {
                    if (id
                            == 'ownerId')
                    {
                        return getList('ownerList');
                    }
                    if (id
                            == 'bmpDbCategoryId')
                    {
                        return getList('categoryList');
                    }
                    return null;
                }
                function getList(name)
                {
                    var list;
                    if (name
                            == 'ownerList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("ownerList"))%>
                        ]
                    }
                    if (name
                            == 'categoryList')
                    {
                        list
                                =
                        [
                            <%= BeanUtil.getJavaScriptArray((ArrayList) pageContext.getAttribute("categoryList"))%>
                        ]
                    }
                    return list;
                }//-->
            </script>
            <script type="text/javascript">
                //<!--
                function bmpForm_custom()
                {
                    this.a0
                            = new Array("section",
                                        "Section is required.",
                                        null);
                    this.a1
                            = new Array("categoryName",
                                        "Category Name is required.",
                                        null);
                }
                function validateForm(form)
                {
                    /*
                     //enable fields so we can validate them
                     document.bmpForm.name.disabled
                     = false;
                     document.bmpForm.section.disabled
                     = false;
                     document.bmpForm.number.disabled
                     = false;
                     */
                    //struts-validation
                    var validated = validateBmpForm(form);
                    //manually check to see if section is required
                <logic:equal value="true" property="useSection" name="bmpForm">
                    if (document.bmpForm.section.value
                            == "")
                    {
                        //                            $('#dialog').html("Section is required.").dialog('open');
                        jcv_handleErrors("Section is required.",
                                         "");
                        validated
                                = false;
                    }
                </logic:equal>
                <logic:equal name="type"
                                value="library">
                    /*
                     var radioA = document.getElementById("isNewCategory");
                     if (radioA.checked)
                     */
                    if (bmpForm.isNewCategoryId.checked)
                    {
                        //                        var primary = $("input#bmpDbCategoryName").first();
                        if ($("input#bmpDbCategoryName").val()
                                == "")
                        {
                            //                            $('#dialog').html("Please enter a category name.").dialog('open');
                            jcv_handleErrors("Category Name is required.",
                                             "");
                            validated
                                    = false;
                        }
                    }
                </logic:equal>


                    /*
                     //If validation failed, make sure we update the controlled
                     //fields to their correct toggle state
                     if (!validated)
                     {
                     updateNameLocked();
                     }
                     */
                    return validated;
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                function toggleNewCategory()
                {
                    if (!bmpForm.isNewCategoryId.checked)
                    {
                        $("a#bmpDbCategoryId").editable("enable");
                        $("a#bmpDbCategoryName").editable("disable");
                    }
                    else
                    {
                        $("a#bmpDbCategoryName").editable("enable");
                        $("a#bmpDbCategoryId").editable("disable");
                    }
                    /*
                     if (document.bmpForm.bmpDbCategoryName.disabled)
                     {
                     document.bmpForm.bmpDbCategoryName.disabled
                     = false;
                     }
                     document.bmpForm.bmpDbCategoryId.disabled
                     = true;
                     */
                }
                function toggleSelectCategory()
                {
                    if (!bmpForm.isSelectCategoryId.checked)
                    {
                        $("a#bmpDbCategoryId").editable("disable");
                        $("a#bmpDbCategoryName").editable("enable");
                    }
                    else
                    {
                        $("a#bmpDbCategoryId").editable("enable");
                        $("a#bmpDbCategoryName").editable("disable");
                    }
                    /*
                     if (document.bmpForm.bmpDbCategoryId.disabled)
                     {
                     document.bmpForm.bmpDbCategoryId.disabled
                     = false;
                     }
                     document.bmpForm.bmpDbCategoryName.disabled
                     = true;
                     */
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
