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
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<fieldset><legend>
    Import Coordinates
</legend></fieldset>
<p class="text-info">
    Import a delimited text file of parcel coordinates into the system. These coordinates will be automatically added to your new projects based on the Parcel Number associated with the project.
</p>
<logic:notEmpty name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                scope="request">
    <div class="alert">
        <bean:write name="<%= RequestKeys.EC_GIS_MESSAGE %>"
                    scope="request" />
    </div>
</logic:notEmpty>
<html:form styleClass="form-horizontal"
           action="/gisimportaction"
           enctype="multipart/form-data">
    <div class="control-group">
        <label class="control-label">
            <bean:message key="gis.import.delimiter.header" />
        </label>
        <div class="controls">
            <div>
                <label class="radio"
                       for="radioDelimiterComma">
                    <input type="radio"
                           id="radioDelimiterComma"
                           name="delimiterText"
                           value="comma"
                           checked='checked' />
                    Comma
                </label>
            </div>
            <div>
                <label class="radio"
                       for="radioDelimiterTab">
                    <input type="radio"
                           id="radioDelimiterTab"
                           name="delimiterText"
                           value="tab" />
                    Tab
                </label>
            </div>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="gis.import.reload.header" />
        </label>
        <div class="controls controls-row">
            <label class="radio"
                   for="radioReload">
                <input type="radio"
                       id="radioReload"
                       name="isUpdate"
                       value="false"
                       checked='checked' />
                Reload
            </label>
            <label class="radio"
                   for="radioUpdate">
                <input type="radio"
                       id="radioUpdate"
                       name="isUpdate"
                       value="true" />
                Update
            </label>
            <div class="alert alert-info alert-block">
                <bean:message key="gis.import.reload.info" />
                <bean:message key="gis.import.reload.info.update" />
            </div>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="gis.import.file.header" />
        </label>
        <div class="controls controls-row">
            <div class="fileupload fileupload-new"
                 data-provides="fileupload">
                <div class="input-append">
                    <div class="uneditable-input span3">
                        <i class="icon-file fileupload-exists"></i>
                        <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                        <span class="fileupload-new">Select file</span>
                        <span class="fileupload-exists">Change</span>
                        <input type="file"
                               id="importFile"
                               name="importFile"
                               accept="text/plain" />
                    </span>
                    <a href="#"
                       class="btn fileupload-exists"
                       data-dismiss="fileupload">Remove
                    </a>
                </div>
            </div>
            <div class="alert alert-info alert-block">
                <p>
                    Records in the parcel coordinate data file must be in the following delimited format:
                </p>
                <code>
                    <span>
                        Parcel Number
                    </span>
                    <span class="gray">
                        &lt;delimiter&gt;
                    </span>
                    <span>
                        Latitude Coordinate
                    </span>
                    <span class="gray">
                        &lt;delimiter&gt;
                    </span>
                    <span>
                        Longitude Coordinate
                    </span>
                </code>
            </div>
            <div class="alert alert-info alert-block">
                <p>
                    Latitude and Longitude must be in decimal degrees format.
                </p>
                <code>
                    (e.g. Latitude=38.898748 Longitude=-77.037684)
                </code>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
<%--
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       onclick="bCancel=true;" />
--%>
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Import" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <!-- This JavaScript function calls the validate<form> method of the
        Struts Validation JavaScript included above. This is necessary
        because we are unable to portal encode the form name on the
        html:form onsubmit event. -->
        <script type="text/javascript">
            <!--
            function validateForm(form)
            {
                return validate(form);
            }// -->
        </script>
        <!-- Include the Struts validation JavaScript -->
        <script type="text/javascript">
            //<!-- Begin
            var bCancel = false;
            function validate(form)
            {
                if (bCancel)
                    return true;
                else
                    return validateRequired(form);
            }
            function Required()
            {
                this.aa
                        = new Array("importFile",
                                    "Import File Name is required.",
                                    new Function("varName",
                                                 " return this[varName];"));
            }
            function validateRequired(form)
            {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                var oRequired = new Required();
                for (var x in oRequired)
                {
                    var field = form[oRequired[x][0]];
                    if (field.type
                                == 'text'
                                || field.type
                            == 'textarea'
                                || field.type
                            == 'file'
                                || field.type
                            == 'select-one'
                                || field.type
                            == 'radio'
                            || field.type
                            == 'password')
                    {
                        var value = '';
                        // get field's value
                        if (field.type
                                == "select-one")
                        {
                            var si = field.selectedIndex;
                            if (si
                                    >= 0)
                            {
                                value
                                        = field.options[si].value;
                            }
                        }
                        else
                        {
                            value
                                    = field.value;
                        }
                        if (trim(value).length
                                == 0)
                        {
                            if (i
                                    == 0)
                            {
                                focusField
                                        = field;
                            }
                            fields[i++]
                                    = oRequired[x][1];
                            isValid
                                    = false;
                        }
                    }
                }
                if (fields.length
                        > 0)
                {
                    focusField.focus();
                    $('#dialog').html(fields.join('\n')).dialog('open');
                }
                return isValid;
            }// Trim whitespace from left and right sides of s.
            function trim(s)
            {
                return s.replace(/^\s*/,
                                 "").replace(/\s*$/,
                                             "");
            }//End -->
        </script>
    </tiles:put>
</tiles:definition>
