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
<%@ page import="com.sehinc.common.db.contact.CapContact,
                 com.sehinc.common.security.SecurityManager,
                 com.sehinc.common.util.StringUtil,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.action.inspection.InspectionForm,
                 com.sehinc.erosioncontrol.db.bmp.EcBmp,
                 com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpCondition,
                 com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpStatus,
                 com.sehinc.erosioncontrol.value.inspection.InspectionBmpConditionValue,
                 com.sehinc.erosioncontrol.value.inspection.InspectionBmpStatusValue,
                 com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue,
                 com.sehinc.erosioncontrol.value.inspection.InspectionDocumentValue,
                 com.sehinc.erosioncontrol.value.inspection.InspectionReasonValue,
                 com.sehinc.erosioncontrol.value.inspection.InspectionInspectionReasonValue,
                 java.util.Collection,
                 java.util.Iterator,
                 java.util.List" %>
<bean:define id="inspectionSecurityValue"
             name="<%= SessionKeys.EC_PROJECT %>"
             property="inspectionSecurityPermissionValue" />
<html:form styleClass="form-horizontal"
           action="/inspectioneditaction"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <fieldset>
        <legend>
            Inspection for
            <bean:write name="<%= SessionKeys.EC_PROJECT %>"
                        property="name" />
            on
            <bean:write name="inspectionForm"
                        property="inspectionDateString" />
            (<bean:write name="inspectionForm"
                         property="id" />)
            <span class="label">
                <bean:write name="inspectionForm"
                            property="status" />
            </span>
        </legend>
    </fieldset>
    <div class="alert">
        <button type="button"
                class="close"
                data-dismiss="alert">&times;</button>
        <strong>Project</strong>

        <div>
            <bean:write name="<%= SessionKeys.EC_PROJECT %>"
                        property="name" />
        </div>
        <div>
            <bean:write name="<%= SessionKeys.EC_PROJECT %>"
                        property="viewAddress" />
        </div>
    </div>
    <h4 class="myAccordian">
        Inspector *
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="inspection.entered.by" />
            </label>
            <div class="controls">
                <div class="uneditable-input span3">
                    <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                                property="firstName" />
                    <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                                property="lastName" />
                </div>
                <span class="help-inline">
                    <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                                property="primaryPhone" />
                    <bean:define id="email"
                                 name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                                 property="emailAddress" />
                    <a href="mailto:<bean:write name='email'/>">
                        <bean:write name='email' />
                    </a>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="inspection.inspector" />
            </label>
            <div class="controls controls-row">
                <html:select name="inspectionForm"
                             property="selectedInspector"
                             styleId="selectedInspector"
                             onchange="return inspectorOnChange();">
                    <html:option value="0">Select an Inspector...</html:option>
                    <html:option value="-1">Add New Inspector</html:option>
                    <html:options collection="<%=SessionKeys.EC_INSPECTOR_LIST%>"
                                  property="id"
                                  labelProperty="fullName" />
                </html:select>
                <span class="help-inline">
                    <span class="small-labels">
                        <div id="inspectorDetails"
                             class="well well-small"></div>
                    </span>
                </span>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="inspection.information.heading" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="inspectionDateString">
                <bean:message key="inspection.date" /> *
            </label>
            <div class="controls">
                <html:text name="inspectionForm"
                           styleId="inspectionDateString"
                           property="inspectionDateString"
                           size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="hours">
                <bean:message key="inspection.time" />
            </label>
            <div class="controls">
                <div class="input-append">
                    <html:text styleClass="span1"
                               property="hourString"
                               name="inspectionForm"
                               styleId="hours" />
                    <span class="add-on">:</span>
                    <html:text styleClass="span1"
                               property="minuteString"
                               name="inspectionForm"
                               styleId="minutes" />
                    <html:select styleClass="span1"
                                 name="inspectionForm"
                                 property="timePeriod"
                                 styleId="timePeriod">
                        <html:option value="AM">
                            AM
                        </html:option>
                        <html:option value="PM">
                            PM
                        </html:option>
                    </html:select>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="weatherTrends">
                <bean:message key="inspection.weather.trends" />
            </label>
            <div class="controls">
                <input type="text"
                       id="weatherTrends"
                       name="weatherTrends"
                       size="50"
                       maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="precipEndDateString">
                <bean:message key="inspection.precip.end.date" />
            </label>
            <div class="controls">
                <input type="text"
                       id="precipEndDateString"
                       name="precipEndDateString"
                       size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="precipAmount">
                <bean:message key="inspection.precip.amount" />
            </label>
            <div class="controls">
                <input type="text"
                       id="precipAmount"
                       name="precipAmount"
                       size="10"
                       maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="precipSource">
                <bean:message key="inspection.precip.source" />
            </label>
            <div class="controls">
                <select id="precipSource"
                        name="precipSource"
                        onchange="return precipSourceOnChange();">
                    <option value="">
                    </option>
                    <logic:iterate id="precipSourceOption"
                                   name="<%= SessionKeys.EC_INSPECTION_PRECIP_SOURCE_LIST %>"
                                   scope="session">
                        <option value="<bean:write name='precipSourceOption' property='value'/>">
                            <bean:write name='precipSourceOption'
                                        property='label' />
                        </option>
                    </logic:iterate>
                </select>
                <label style="display: none;"
                       for="precipSourceOther"></label>
                <input type="text"
                       id="precipSourceOther"
                       name="precipSourceOther"
                       size="20"
                       maxlength="25" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="temperature">
                <bean:message key="inspection.temperature" />
            </label>
            <div class="controls">
                <input class="span1"
                       type="text"
                       id="temperature"
                       name="temperature"
                       maxlength="6" />
                <bean:message key="inspection.temperature.units" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="inspection.reason" /> *
            </label>
            <div class="controls">
                <%
                    boolean
                            foundItem;
                    Iterator
                            inspectionReasonListIter =
                            ((Collection) request.getSession()
                                    .getAttribute(SessionKeys.EC_INSPECTION_REASON_LIST)).iterator();
                    while (inspectionReasonListIter.hasNext())
                    {
                        InspectionReasonValue
                                inspectionReasonItem =
                                (InspectionReasonValue) inspectionReasonListIter.next();
                        foundItem =
                                false;
                        Iterator
                                inspectionInspectionReasonListIter =
                                ((Collection) request.getSession()
                                        .getAttribute(SessionKeys.EC_INSPECTION_INSPECTION_REASON_LIST)).iterator();
                        while (inspectionInspectionReasonListIter.hasNext())
                        {
                            InspectionInspectionReasonValue
                                    inspectionInspectionReasonItem =
                                    (InspectionInspectionReasonValue) inspectionInspectionReasonListIter.next();
                            if (!foundItem
                                    && inspectionReasonItem.getId()
                                    .equals(inspectionInspectionReasonItem.getInspectionReasonId()))
                            {
                                foundItem =
                                        true;
                                break;
                            }
                        }
                        if (foundItem)
                        {
                %>
                            <div>
                                <label class="checkbox" style="display: inline-block;">
                                    <input type="checkbox"
                                           id="<%= "inspectionReason" + inspectionReasonItem.getId() %>"
                                           name="ecInspectionReasonItems"
                                           value="<%= inspectionReasonItem.getId() %>"
                                           checked='checked'>
                                    <%= inspectionReasonItem.getName() %>
                                </label>
                            </div>
                <%
                        }
                        else
                        {
                %>
                            <div>
                                <label class="checkbox" style="display: inline-block;">
                                    <input type="checkbox"
                                           id="<%= "inspectionReason" + inspectionReasonItem.getId() %>"
                                           name="ecInspectionReasonItems"
                                           value="<%= inspectionReasonItem.getId() %>">
                                    <%= inspectionReasonItem.getName() %>
                                </label>
                            </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
            <%--
                    <logic:equal name='<%= SessionKeys.IS_PROJECT_PERMIT_AUTHORITY %>'
                                 value='true'
                                 scope="session">
            --%>
        <div class="control-group">
            <label class="control-label"
                   for="inspectionAction.id">
                <bean:message key="inspection.action" />
            </label>
            <div class="controls">
                <select id="inspectionAction.id"
                        name="inspectionAction.id"
                        onchange="return inspectionActionOnChange();">
                    <logic:iterate id="inspectionActionValue"
                                   name="<%= SessionKeys.EC_INSPECTION_ACTION_LIST %>">
                        <option value="<bean:write name='inspectionActionValue' property='id'/>">
                            <bean:write name='inspectionActionValue'
                                        property='name' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="inspectionActionComment">
                <bean:message key="inspection.action.comment" />
            </label>
            <div class="controls">
                <textarea id="inspectionActionComment"
                          name="inspectionActionComment"
                          cols="75"
                          rows="2"></textarea>
            </div>
        </div>
            <%--</logic:equal>--%>
    </div>
    <%--
        <logic:notEqual name='<%=SessionKeys.IS_PROJECT_PERMIT_AUTHORITY %>'
                        value='true'
                        scope="session">
            <input type="hidden"
                   name="inspectionAction.id"
                   id="inspectionAction.id"
                   value="1" />
            <input type="hidden"
                   name="inspectionActionComment"
                   id="inspectionActionComment"
                   value="" />
        </logic:notEqual>
    --%>
    <logic:present name="inspectionForm"
                   property="inspectionDocumentArray">
        <h4 class="myAccordian">
            <bean:message key="inspection.document" />s
        </h4>
        <div>
            <div class="control-group">
                <label class="control-label">
                    <bean:message key="inspection.document" />
                </label>
                <div class="controls controls-row">
                    <div id="iFormFile0"></div>
                    <div id="iFormFile1"></div>
                    <div id="iFormFile2"></div>
                    <div id="iFormFile3"></div>
                    <div id="iFormFile4"></div>
                    <div id="iFormFile5"></div>
                    <div id="iFormFile6"></div>
                    <div id="iFormFile7"></div>
                    <div id="iFormFile8"></div>
                    <div id="iFormFile9"></div>
                    <div>
                        <a class='btn btn-success'
                           href="javascript:addFormFileInput();">
                            + Add Document
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </logic:present>
    <h4 class="myAccordian">
        <bean:message key="inspection.bmp.heading" />
    </h4>
    <div>
        <div id="iBmp0"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp1"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp2"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp3"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp4"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp5"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp6"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp7"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp8"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp9"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp10"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp11"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp12"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp13"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp14"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp15"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp16"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp17"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp18"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp19"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp20"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp21"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp22"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp23"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp24"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp25"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp26"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp27"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp28"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp29"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp30"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp31"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp32"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp33"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp34"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp35"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp36"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp37"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp38"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp39"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp40"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp41"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp42"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp43"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp44"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp45"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp46"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp47"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp48"
             style="display:none;"
             class="well well-small"></div>
        <div id="iBmp49"
             style="display:none;"
             class="well well-small"></div>
        <div id="iDelBmp"></div>
        <a class='btn btn-success'
           href="javascript:addBmpInput();">
            + Add BMP
        </a>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       onclick="bCancel=true;" />
                <logic:equal name="inspectionSecurityValue"
                             property="isDelete"
                             value="true">
                    <input class="btn btn-danger btn-large warn-delete"
                           type="submit"
                           name="submit"
                           value="Delete" />
                </logic:equal>
            </div>
            <div class="span6">
                <span class="pull-right">
                    <label for="statusCode">
                        <span class="hidden-phone">
                            Save as
                        </span>
                        <select class="input-small"
                                id="statusCode"
                                name="statusCode"
                                title="Save as">
                            <option value="4">Draft</option>
                            <option value="1">Final</option>
                        </select>
                    </label>
                    <html:submit styleClass="btn btn-success btn-large"
                                 property="submit"
                                 value="Save" />
                </span>
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <html:javascript formName="inspectionForm" />
            <script type="text/javascript">
                //<!--Begin
                String.prototype.equalsIgnoreCase
                        = myEqualsIgnoreCase;
                String.prototype.equals
                        = myEquals;
                function myEquals(arg)
                {
                    return (this.toString()
                            == arg.toString());
                }
                function myEqualsIgnoreCase(arg)
                {
                    return (new String(this.toLowerCase())
                            == (new String(arg)).toLowerCase());
                }
                function isEmpty(text)
                {
                    return text
                                   == null
                                   || text
                            == 'null'
                            || text
                            == '';
                }
                var bCancel = false;
                function validateForm(form)
                {
                    if (bCancel) return true;
                    if (document.getElementById("inspectionDateString").value
                                == null
                            || document.getElementById("inspectionDateString").value
                            == '')
                    {
                        $('#dialog').html("Inspection Date is required").dialog('open');
                        return false;
                    }
                    if ($("[id^=inspectionReason]:checked").length < 1)
                    {
                        $('#dialog').html("Inspection Reason is required").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("precipAmount").value))
                    {
                        $('#dialog').html("Last Precip. Amount must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (!validateFloat(document.getElementById("temperature").value))
                    {
                        $('#dialog').html("Temperature must be a numeric value.").dialog('open');
                        return false;
                    }
                    if (document.getElementById("minutes").value
                                != null
                            && document.getElementById("minutes").value
                            != "")
                    {
                        if (document.getElementById("hours").value
                                    == null
                                || document.getElementById("hours").value
                                == "")
                        {
                            $('#dialog').html("Please enter a value for hours.").dialog('open');
                            return false;
                        }
                    }
                    else if (document.getElementById("hours").value
                                     != null
                            && document.getElementById("hours").value
                            != "")
                    {
                        $('#dialog').html("Please enter a value for minutes.").dialog('open');
                        return false;
                    }
                    //Make sure all BMP are entered correctly
                    for (bmpIndex
                                 = 0; bmpIndex
                            < inspectionBmpArray.length; bmpIndex++)
                    {
                        if (inspectionBmpArray[bmpIndex].isDeleted
                                == 'false')
                        {
                            if (inspectionBmpArray[bmpIndex].categoryName
                                    == '')
                            {
                                $('#dialog').html("BMP Category is required.").dialog('open');
                                return false;
                            }
                            if (inspectionBmpArray[bmpIndex].name
                                    == '')
                            {
                                $('#dialog').html("BMP Type is required.").dialog('open');
                                return false;
                            }
                            if (inspectionBmpArray[bmpIndex].description.length
                                    > 500)
                            {
                                $('#dialog').html("BMP Description must be less than 500 characters.").dialog('open');
                                return false;
                            }
                        }
                    }
                    if (document.getElementById("selectedInspector").value
                            == 0)
                    {
                        $('#dialog').html("You must select an Inspector.").dialog('open');
                        return false;
                    }
                <% if (!SecurityManager.getSecurityManager(request).getIsProjectPermitAuthority()) { %>
                    if (document.getElementById("statusCode").value
                            == '1')
                    { //Check on Final status only
                        for (var bmpIndex = 0; bmpIndex
                                < inspectionBmpArray.length; bmpIndex++)
                        {
                            if (inspectionBmpArray[bmpIndex].isRequired
                                        == 'true'
                                    && inspectionBmpArray[bmpIndex].statusId
                                    == '1')
                            {  //Required and Active only
                                if (inspectionBmpArray[bmpIndex].isInspected
                                            == ''
                                        || inspectionBmpArray[bmpIndex].isInspected
                                        == 'false')
                                {  //Must inspect Active BMP
                                    $('#dialog').html("You must inspect ALL BMPs marked as Required and Active.").dialog('open');
                                    return false;
                                }
                                if (inspectionBmpArray[bmpIndex].conditionId
                                        == '')
                                {  //User has not selected a condition for a required and active BMP
                                    $('#dialog').html("Please select a Condition for "
                                                              + getBmpCategoryName(inspectionBmpArray[bmpIndex].bmpCategoryId)
                                                              + " - "
                                                              + getBmpName(inspectionBmpArray[bmpIndex].bmpId)
                                                              + ".").dialog('open');
                                    return false;
                                }
                            }
                        }
                    }
                <% } %>
                    if (document.getElementById("selectedInspector").value
                            == 0)
                    {
                        $('#dialog').html("You must select an Inspector.").dialog('open');
                        return false;
                    }
                    else if (document.getElementById("selectedInspector").value
                            == -1)
                    {
                        if (document.getElementById("addClientId").value
                                == 0)
                        {
                            $('#dialog').html("Please select a client in order to Add an Inspector.").dialog('open');
                            return false;
                        }
                        if (document.getElementById("addContactFirstName").value
                                == "")
                        {
                            $('#dialog').html("Please enter the first name of the new Inspector.").dialog('open');
                            return false;
                        }
                        if (document.getElementById("addContactLastName").value
                                == "")
                        {
                            $('#dialog').html("Please enter the last name of the new Inspector.").dialog('open');
                            return false;
                        }
                        if (document.getElementById("addContactEmail").value
                                == "")
                        {
                            $('#dialog').html("Please enter the email of the new Inspector.").dialog('open');
                            return false;
                        }
                        else
                        {
                            var email = document.getElementById("addContactEmail").value;
                        <% List contacts =  (List) request.getAttribute(SessionKeys.EC_INSPECTOR_LIST);
                Iterator ci = contacts.iterator();
                while (ci.hasNext()) {
                   CapContact contact = (CapContact) ci.next(); %>
                            if ('<%= contact.getEmail() %>'
                                    == email)
                            {
                                $('#dialog').html("The email address "
                                                          + email
                                                          + " is already registered.  This contact already exists.").dialog('open');
                                return false;
                            }
                        <%
                        }
                        %>
                        }
                    }
                    return validateDate(form)
                            && validateInspectionForm(form);
                }
                function validateFloat(value)
                {
                    var bValid = true;
                    if (value.length
                            > 0)
                    {
                        // remove '.' before checking digits
                        var tempArray = value.split('.');
                        var joinedString = tempArray.join('');
                        if (!isAllDigits(joinedString))
                        {
                            bValid
                                    = false;
                        }
                        else
                        {
                            var iValue = parseFloat(value);
                            if (isNaN(iValue))
                            {
                                bValid
                                        = false;
                            }
                        }
                    }
                    return bValid;
                }
                function isAllDigits(argvalue)
                {
                    argvalue
                            = argvalue.toString();
                    var validChars = "0123456789";
                    var startFrom = 0;
                    if (argvalue.substring(0,
                                           2)
                            == "0x")
                    {
                        validChars
                                = "0123456789abcdefABCDEF";
                        startFrom
                                = 2;
                    }
                    else if (argvalue.charAt(0)
                            == "0")
                    {
                        startFrom
                                = 1;
                    }
                    else if (argvalue.charAt(0)
                            == "-")
                    {
                        startFrom
                                = 1;
                    }
                    for (var n = startFrom; n
                            < argvalue.length; n++)
                    {
                        if (validChars.indexOf(argvalue.substring(n,
                                                                  n
                                                                          + 1))
                                == -1) return false;
                    }
                    return true;
                }
                function DateValidations()
                {
                    this.aa
                            = new Array("inspectionDateString",
                                        "Inspection Date is not a valid date. (mm/dd/yyyy)",
                                        new Function("varName",
                                                     "this.datePatternStrict='MM/dd/yyyy';  return this[varName];"));
                    this.ab
                            = new Array("precipEndDateString",
                                        "Precipitation End Date is not a valid date. (mm/dd/yyyy)",
                                        new Function("varName",
                                                     "this.datePatternStrict='MM/dd/yyyy';  return this[varName];"));
                }
                function validateDate(form)
                {
                    var bValid = true;
                    var focusField = null;
                    var i = 0;
                    var fields = new Array();
                    var oDate = new DateValidations();
                    var dateRegexp;
                    for (var x in oDate)
                    {
                        var value = form[oDate[x][0]].value;
                        var datePattern = oDate[x][2]("datePatternStrict");
                        if ((form[oDate[x][0]].type
                                     == 'text'
                                || form[oDate[x][0]].type
                                == 'textarea')
                                    && (value.length
                                > 0)
                                && (datePattern.length
                                > 0))
                        {
                            var MONTH = "MM";
                            var DAY = "dd";
                            var YEAR = "yyyy";
                            var orderMonth = datePattern.indexOf(MONTH);
                            var orderDay = datePattern.indexOf(DAY);
                            var orderYear = datePattern.indexOf(YEAR);
                            if ((orderDay
                                         < orderYear
                                    && orderDay
                                    > orderMonth))
                            {
                                iDelim1
                                        = orderMonth
                                        + MONTH.length;
                                iDelim2
                                        = orderDay
                                        + DAY.length;
                                delim1
                                        = datePattern.substring(iDelim1,
                                                                iDelim1
                                                                        + 1);
                                delim2
                                        = datePattern.substring(iDelim2,
                                                                iDelim2
                                                                        + 1);
                                if (iDelim1
                                            == orderDay
                                        && iDelim2
                                        == orderYear)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                                }
                                else if (iDelim1
                                        == orderDay)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})(\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{4})$");
                                }
                                else if (iDelim2
                                        == orderYear)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})["
                                                                 + delim1
                                                                 + "](\\d{2})(\\d{4})$");
                                }
                                else
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})["
                                                                 + delim1
                                                                 + "](\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{4})$");
                                }
                                var matched = dateRegexp.exec(value);
                                if (matched
                                        != null)
                                {
                                    if (!isValidDate(matched[2],
                                                     matched[1],
                                                     matched[3]))
                                    {
                                        if (i
                                                == 0)
                                        {
                                            focusField
                                                    = form[oDate[x][0]];
                                        }
                                        fields[i++]
                                                = oDate[x][1];
                                        bValid
                                                = false;
                                    }
                                }
                                else
                                {
                                    if (i
                                            == 0)
                                    {
                                        focusField
                                                = form[oDate[x][0]];
                                    }
                                    fields[i++]
                                            = oDate[x][1];
                                    bValid
                                            = false;
                                }
                            }
                            else if ((orderMonth
                                              < orderYear
                                    && orderMonth
                                    > orderDay))
                            {
                                iDelim1
                                        = orderDay
                                        + DAY.length;
                                iDelim2
                                        = orderMonth
                                        + MONTH.length;
                                delim1
                                        = datePattern.substring(iDelim1,
                                                                iDelim1
                                                                        + 1);
                                delim2
                                        = datePattern.substring(iDelim2,
                                                                iDelim2
                                                                        + 1);
                                if (iDelim1
                                            == orderMonth
                                        && iDelim2
                                        == orderYear)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                                }
                                else if (iDelim1
                                        == orderMonth)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})(\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{4})$");
                                }
                                else if (iDelim2
                                        == orderYear)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})["
                                                                 + delim1
                                                                 + "](\\d{2})(\\d{4})$");
                                }
                                else
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{2})["
                                                                 + delim1
                                                                 + "](\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{4})$");
                                }
                                matched
                                        = dateRegexp.exec(value);
                                if (matched
                                        != null)
                                {
                                    if (!isValidDate(matched[1],
                                                     matched[2],
                                                     matched[3]))
                                    {
                                        if (i
                                                == 0)
                                        {
                                            focusField
                                                    = form[oDate[x][0]];
                                        }
                                        fields[i++]
                                                = oDate[x][1];
                                        bValid
                                                = false;
                                    }
                                }
                                else
                                {
                                    if (i
                                            == 0)
                                    {
                                        focusField
                                                = form[oDate[x][0]];
                                    }
                                    fields[i++]
                                            = oDate[x][1];
                                    bValid
                                            = false;
                                }
                            }
                            else if ((orderMonth
                                              > orderYear
                                    && orderMonth
                                    < orderDay))
                            {
                                var iDelim1 = orderYear
                                        + YEAR.length;
                                var iDelim2 = orderMonth
                                        + MONTH.length;
                                var delim1 = datePattern.substring(iDelim1,
                                                                   iDelim1
                                                                           + 1);
                                var delim2 = datePattern.substring(iDelim2,
                                                                   iDelim2
                                                                           + 1);
                                if (iDelim1
                                            == orderMonth
                                        && iDelim2
                                        == orderDay)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                                }
                                else if (iDelim1
                                        == orderMonth)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{4})(\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{2})$");
                                }
                                else if (iDelim2
                                        == orderDay)
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{4})["
                                                                 + delim1
                                                                 + "](\\d{2})(\\d{2})$");
                                }
                                else
                                {
                                    dateRegexp
                                            = new RegExp("^(\\d{4})["
                                                                 + delim1
                                                                 + "](\\d{2})["
                                                                 + delim2
                                                                 + "](\\d{2})$");
                                }
                                matched
                                        = dateRegexp.exec(value);
                                if (matched
                                        != null)
                                {
                                    if (!isValidDate(matched[3],
                                                     matched[2],
                                                     matched[1]))
                                    {
                                        if (i
                                                == 0)
                                        {
                                            focusField
                                                    = form[oDate[x][0]];
                                        }
                                        fields[i++]
                                                = oDate[x][1];
                                        bValid
                                                = false;
                                    }
                                }
                                else
                                {
                                    if (i
                                            == 0)
                                    {
                                        focusField
                                                = form[oDate[x][0]];
                                    }
                                    fields[i++]
                                            = oDate[x][1];
                                    bValid
                                            = false;
                                }
                            }
                            else
                            {
                                if (i
                                        == 0)
                                {
                                    focusField
                                            = form[oDate[x][0]];
                                }
                                fields[i++]
                                        = oDate[x][1];
                                bValid
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
                    return bValid;
                }
                function isValidDate(day, month, year)
                {
                    if (month
                                < 1
                            || month
                            > 12)
                    {
                        return false;
                    }
                    if (day
                                < 1
                            || day
                            > 31)
                    {
                        return false;
                    }
                    if ((month
                                 == 4
                                 || month
                            == 6
                                 || month
                            == 9
                            || month
                            == 11)
                            && (day
                            == 31))
                    {
                        return false;
                    }
                    if (month
                            == 2)
                    {
                        var leap = (year
                                            % 4
                                            == 0
                                && (year
                                            % 100
                                            != 0
                                || year
                                           % 400
                                == 0));
                        if (day
                                    > 29
                                || (day
                                            == 29
                                && !leap))
                        {
                            return false;
                        }
                    }
                    return true;
                }
                function precipSourceOnChange()
                {
                    var precipSource = document.getElementById('precipSource');
                    if (precipSource.options[precipSource.selectedIndex].value
                            == 'Other')
                    {
                        document.getElementById('precipSourceOther').style.visibility
                                = 'visible';
                    }
                    else
                    {
                        document.getElementById('precipSourceOther').style.visibility
                                = 'hidden';
                    }
                }
                function initInspectionFields()
                {
                <%
                    InspectionForm inspectionForm = ((InspectionForm) request.getAttribute(RequestKeys.EC_INSPECTION_FORM));
                %>
                    document.getElementById('inspectionDateString').value
                            = '<%= inspectionForm.getInspectionDateString() %>';
                    document.getElementById('weatherTrends').value
                            = '<%= StringUtil.escapeSingleQuote(inspectionForm.getWeatherTrends()) %>';
                    document.getElementById('temperature').value
                            = '<%= StringUtil.escapeSingleQuote(inspectionForm.getTemperature()) %>';
                    document.getElementById('precipEndDateString').value
                            = '<%= inspectionForm.getPrecipEndDateString() %>';
                    document.getElementById('precipAmount').value
                            = '<%= StringUtil.escapeSingleQuote(inspectionForm.getPrecipAmount()) %>';
                    document.getElementById('precipSource').value
                            = '<%= StringUtil.escapeSingleQuote(inspectionForm.getPrecipSource()) %>';
                    document.getElementById('precipSourceOther').style.visibility
                            = 'hidden';
                <% if (StringUtil.isEmpty(inspectionForm.getInspectionActionComment())) { %>
                    document.getElementById('inspectionActionComment').value
                            = '';
                <% } else { %>
                    document.getElementById('inspectionActionComment').value
                            = '<%= StringUtil.escapeSingleQuote(inspectionForm.getInspectionActionComment()) %>';
                <% } %>
                    document.getElementById('inspectionAction.id').value
                            = '<%= inspectionForm.getInspectionAction().getId() %>';
                    if (document.getElementById('inspectionAction.id').type
                            == 'checkbox')
                    {
                        inspectionActionOnChange();
                    }
                    document.getElementById('statusCode').value
                            = '<%= inspectionForm.getStatusCode() %>';
                }// BMP JS
                var inspectionBmpArray = new Array(0);
                var bmpArray = new Array(0);
                var bmpCategoryArray = new Array(0);
                var bmpStatusArray = new Array(0);
                var bmpConditionArray = new Array(0);
                var bmpAdd = 0;
                function InspectionBmp(id, inspectionBmpId, projectBmpId, name, categoryName, description, comment, location, statusId, conditionId, documentId, downloadURL, fileName, isRequired, isInspected, isDeleted)
                {
                    this.id
                            = id;
                    this.inspectionBmpId
                            = inspectionBmpId;
                    this.bmpId
                            = projectBmpId;
                    this.name
                            = name;
                    this.categoryName
                            = categoryName;
                    this.description
                            = description;
                    this.comment
                            = comment;
                    this.location
                            = location;
                    this.statusId
                            = statusId;
                    this.conditionId
                            = conditionId;
                    this.documentId
                            = documentId;
                    this.downloadURL
                            = downloadURL;
                    this.fileName
                            = fileName;
                    this.isRequired
                            = isRequired;
                    this.isInspected
                            = isInspected;
                    this.isDeleted
                            = isDeleted;
                }
                function Bmp(id, name, categoryId, categoryName, description)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                    this.categoryId
                            = categoryId;
                    this.categoryName
                            = categoryName;
                    this.description
                            = description;
                }
                function BmpCategory(id, name)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                }
                function BmpStatus(id, name)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                }
                function BmpCondition(id, name)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                }
                function addToBmpCategoryArray(id, name)
                {
                    var found = 0;
                    for (var intI = 0; intI
                            < bmpCategoryArray.length; intI++)
                    {
                        if (bmpCategoryArray[intI].name
                                == name)
                        {
                            found
                                    = 1;
                        }
                    }
                    if (found
                            == 0)
                    {
                        bmpCategoryArray.push(new BmpCategory(id,
                                                              name));
                    }
                }
                function initInspectionBmpArray()
                {
                <% Iterator bmpListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_INSPECTION_BMP_LIST)).iterator();
           while (bmpListIterator.hasNext()) {
               InspectionBmpValue bmpValue = (InspectionBmpValue) bmpListIterator.next();
               java.lang.String conditionId = "";
             java.lang.String statusId = EcInspectionBmpStatus.BMP_STATUS_INACTIVE.toString();
               if (bmpValue.getBmpCondition()
                   != null
                   && bmpValue.getBmpCondition()
                   .getId()
                      != null
                   && bmpValue.getBmpCondition()
                              .getId()
                      > 0)
               {
                   conditionId =
                       bmpValue.getBmpCondition()
                           .getId()
                           .toString();
               }
               if (bmpValue.getBmpStatus()
                   != null
                   && bmpValue.getBmpStatus()
                   .getId()
                      != null
                   && bmpValue.getBmpStatus()
                              .getId()
                      > 0)
               {
                   statusId =
                       bmpValue.getBmpStatus()
                           .getId()
                           .toString();
               }
               if (statusId.equals(EcInspectionBmpStatus.BMP_STATUS_INACTIVE.toString()))
               {
                   conditionId =
                       EcInspectionBmpCondition.BMP_CONDITION_NOT_APPLICABLE
                           .toString();
               } %>
                    inspectionBmpArray.push(new InspectionBmp('',
                                                              '<%= bmpValue.getId() %>',
                                                              '<%= bmpValue.getProjectBmpId() %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getName()) %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getBmpCategoryName()) %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getDescription()) %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getComment()) %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getLocation()) %>',
                                                              '<%= statusId %>',
                                                              '<%= conditionId %>',
                                                              '<%= bmpValue.getBmpDocument().getId() %>',
                                                              '<%= bmpValue.getBmpDocument().getDownloadURL() %>',
                                                              '<%= StringUtil.escapeSingleQuote(bmpValue.getBmpDocument().getName()) %>',
                                                              '<%= bmpValue.getIsRequired() %>',
                                                              '<%= bmpValue.getIsInspectedText() %>',
                                                              'false'));
                <% } %>
                }
                function initBmpReferenceArrays()
                {
                <% bmpListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT)).iterator();
        while (bmpListIterator.hasNext()) {
           EcBmp ecBmp = (EcBmp) bmpListIterator.next(); %>
                    addToBmpCategoryArray('<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getCategory().getName()) %>');
                    bmpArray.push(new Bmp('<%= ecBmp.getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getName()) %>',
                                          '<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getCategory().getName()) %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getDescription()) %>'));
                <% }
        Iterator bmpStatusIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_INSPECTION_BMP_STATUS_LIST)).iterator();
        while (bmpStatusIterator.hasNext()) {
           InspectionBmpStatusValue statusValue = (InspectionBmpStatusValue) bmpStatusIterator.next(); %>
                    bmpStatusArray.push(new BmpStatus('<%= statusValue.getId() %>',
                                                      '<%= StringUtil.escapeSingleQuote(statusValue.getName()) %>'));
                <% }
        Iterator bmpConditionIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_INSPECTION_BMP_CONDITION_LIST)).iterator();
        while (bmpConditionIterator.hasNext()) {
           InspectionBmpConditionValue conditionValue = (InspectionBmpConditionValue) bmpConditionIterator.next(); %>
                    bmpConditionArray.push(new BmpCondition('<%= conditionValue.getId() %>',
                                                            '<%= StringUtil.escapeSingleQuote(conditionValue.getName()) %>'));
                <% } %>
                }
                function getCategoryOptions(categoryName)
                {
                    var coInputHTML = "";
                    coInputHTML
                            += "<option value=''>Select...</option>";
                    for (var intCO = 0; intCO
                            < bmpCategoryArray.length; intCO++)
                    {
                        coInputHTML
                                += "<option "
                                           + ((bmpCategoryArray[intCO].name
                                == categoryName)
                                ? "SELECTED"
                                : "")
                                           + " value='"
                                           + bmpCategoryArray[intCO].id
                                           + "'>"
                                           + entityify(bmpCategoryArray[intCO].name)
                                + "</option>";
                    }
                    return coInputHTML;
                }
                function getBmpOptions(bmpName, categoryName)
                {
                    var boInputHTML = "";
                    boInputHTML
                            += "<option value=''>Select...</option>";
                    for (var intBO = 0; intBO
                            < bmpArray.length; intBO++)
                    {
                        if (bmpArray[intBO].categoryName
                                == categoryName)
                        {
                            boInputHTML
                                    += "<option "
                                               + ((bmpArray[intBO].name
                                    == bmpName)
                                    ? "SELECTED"
                                    : "")
                                               + " value='"
                                               + bmpArray[intBO].id
                                               + "'>"
                                               + entityify(bmpArray[intBO].name)
                                    + "</option>";
                        }
                    }
                    return boInputHTML;
                }
                function getBmpStatusOptions(statusId)
                {
                    var inputHTML = "";
                    //  inputHTML += "<option value=''>Select...</option>";
                    for (var index = 0; index
                            < bmpStatusArray.length; index++)
                    {
                        inputHTML
                                += "<option "
                                           + ((bmpStatusArray[index].id
                                == statusId)
                                ? "SELECTED"
                                : "")
                                           + " value='"
                                           + bmpStatusArray[index].id
                                           + "'>"
                                           + bmpStatusArray[index].name
                                + "</option>";
                    }
                    return inputHTML;
                }
                function getBmpConditionOptions(conditionId)
                {
                    var inputHTML = "";
                    inputHTML
                            += "<option value=''>Select...</option>";
                    for (var index = 0; index
                            < bmpConditionArray.length; index++)
                    {
                        inputHTML
                                += "<option "
                                           + ((bmpConditionArray[index].id
                                == conditionId)
                                ? "SELECTED"
                                : "")
                                           + " value='"
                                           + bmpConditionArray[index].id
                                           + "'>"
                                           + bmpConditionArray[index].name
                                + "</option>";
                    }
                    return inputHTML;
                }
                function getBmpDescription(bmpId)
                {
                    for (var intBD = 0; intBD
                            < bmpArray.length; intBD++)
                    {
                        if (bmpArray[intBD].id
                                == bmpId)
                        {
                            return bmpArray[intBD].description;
                        }
                    }
                    return "";
                }
                function getBmpCategoryName(bmpCategoryId)
                {
                    for (var index = 0; index
                            < bmpCategoryArray.length; index++)
                    {
                        if (bmpCategoryArray[index].id
                                == bmpCategoryId)
                        {
                            return bmpCategoryArray[index].name;
                        }
                    }
                    return "";
                }
                function getBmpName(bmpId)
                {
                    for (var index = 0; index
                            < bmpArray.length; index++)
                    {
                        if (bmpArray[index].id
                                == bmpId)
                        {
                            return bmpArray[index].name;
                        }
                    }
                    return "";
                }
                function isInspectedYesOnChange(index)
                {
                    if (document.getElementById('radioInspectedYes'
                                                        + inspectionBmpArray[index].id).checked)
                    {
                        inspectionBmpArray[index].isInspected
                                = 'true';
                    }
                }
                function isInspectedNoOnChange(index)
                {
                    if (document.getElementById('radioInspectedNo'
                                                        + inspectionBmpArray[index].id).checked)
                    {
                        inspectionBmpArray[index].isInspected
                                = 'false';
                    }
                }
                function isRequiredOnChange(index)
                {
                    if (document.getElementById('bmps.bmp'
                                                        + inspectionBmpArray[index].id
                                                        + '.isRequired').checked)
                    {
                        inspectionBmpArray[index].isRequired
                                = 'true';
                        //document.getElementById('bmps.bmp' + id + '.isRequired').value = 'true';
                    }
                    else
                    {
                        inspectionBmpArray[index].isRequired
                                = 'false';
                        //document.getElementById('bmps.bmp' + id + '.isRequired').value = 'false';
                    }
                }
                function getRequiredText(index)
                {
                    if (inspectionBmpArray[index].isRequired
                            == 'true')
                    {
                        return "Yes";
                    }
                    return "No";
                }
                function categoryOnChange(index)
                {
                    inspectionBmpArray[index].categoryName
                            = getBmpCategoryName(document.getElementById('bmps.bmp'
                                                                                 + inspectionBmpArray[index].id
                                                                                 + '.bmpCategoryId').value);
                    inspectionBmpArray[index].bmpId
                            = '';
                    inspectionBmpArray[index].name
                            = '';
                    inspectionBmpArray[index].description
                            = '';
                    createBmpAddInput(inspectionBmpArray[index],
                                      index);
                }
                function bmpOnChange(index)
                {
                    inspectionBmpArray[index].name
                            = getBmpName(document.getElementById('bmps.bmp'
                                                                         + inspectionBmpArray[index].id
                                                                         + '.bmpId').value);
                    inspectionBmpArray[index].description
                            = getBmpDescription(document.getElementById('bmps.bmp'
                                                                                + inspectionBmpArray[index].id
                                                                                + '.bmpId').value);
                    document.getElementById('bmps.bmp'
                                                    + inspectionBmpArray[index].id
                                                    + '.name').value
                            = inspectionBmpArray[index].name;
                    document.getElementById('bmps.bmp'
                                                    + inspectionBmpArray[index].id
                                                    + '.description').value
                            = inspectionBmpArray[index].description;
                }
                function bmpDescriptionOnChange(index)
                {
                    inspectionBmpArray[index].description
                            = document.getElementById('bmps.bmp'
                                                              + inspectionBmpArray[index].id
                                                              + '.description').value;
                }
                function bmpStatusOnChange(index)
                {
                    //var i = 0;
                    var conditionSelect = document.getElementById('bmps.bmp'
                                                                          + inspectionBmpArray[index].id
                                                                          + '.bmpCondition.id');
                    inspectionBmpArray[index].statusId
                            = document.getElementById('bmps.bmp'
                                                              + inspectionBmpArray[index].id
                                                              + '.bmpStatus.id').value;
                    if (inspectionBmpArray[index].statusId
                            == '2')
                    {  //Inactive
                        inspectionBmpArray[index].conditionId
                                = '1';  //N/A
                        $('#dialog').html("Setting the Status to 'Inactive' requires the Condition to be 'N/A'.").dialog('open');
                    }
                    if (inspectionBmpArray[index].statusId
                                != '2'
                            && inspectionBmpArray[index].conditionId
                            == '1')
                    {  //Change from Inactive with N/A condition
                        inspectionBmpArray[index].conditionId
                                = '';  //Select
                    }
                    for (var i = 0; i
                            < conditionSelect.length; i++)
                    {
                        if (conditionSelect.options[i].value
                                == inspectionBmpArray[index].conditionId)
                        {
                            conditionSelect.options[i].selected
                                    = true;
                            break;
                        }
                    }
                }
                function bmpConditionOnChange(index)
                {
                    var statusSelect = document.getElementById('bmps.bmp'
                                                                       + inspectionBmpArray[index].id
                                                                       + '.bmpStatus.id');
                    inspectionBmpArray[index].conditionId
                            = document.getElementById('bmps.bmp'
                                                              + inspectionBmpArray[index].id
                                                              + '.bmpCondition.id').value;
                    if (inspectionBmpArray[index].conditionId
                            == '1')
                    {  //N/A
                        inspectionBmpArray[index].statusId
                                = '2';  //Inactive
                        $('#dialog').html("Setting the Condition to 'N/A' requires the Status to be 'Inactive'.").dialog('open');
                    }
                    if (inspectionBmpArray[index].conditionId
                                != '1'
                            && inspectionBmpArray[index].conditionId
                            != '')
                    {
                        document.getElementById('radioInspectedYes'
                                                        + inspectionBmpArray[index].id).checked
                                = true;
                        inspectionBmpArray[index].isInspected
                                = 'true';
                        if (inspectionBmpArray[index].statusId
                                == '2')
                        {  //changing the condition from N/A status cannot be Inactive
                            inspectionBmpArray[index].statusId
                                    = '1';  //Active
                        }
                    }
                    for (var i = 0; i
                            < statusSelect.length; i++)
                    {
                        if (statusSelect.options[i].value
                                == inspectionBmpArray[index].statusId)
                        {
                            statusSelect.options[i].selected
                                    = true;
                            break;
                        }
                    }
                }
                function bmpCommentOnChange(index)
                {
                    inspectionBmpArray[index].comment
                            = document.getElementById('bmps.bmp'
                                                              + inspectionBmpArray[index].id
                                                              + '.comment').value;
                }
                function bmpLocationOnChange(index)
                {
                    inspectionBmpArray[index].location
                            = document.getElementById('bmps.bmp'
                                                              + inspectionBmpArray[index].id
                                                              + '.location').value;
                }
                function inspectionActionOnChange()
                {
                    document.getElementById('inspectionActionComment').disabled
                            = document.getElementById('inspectionAction.id').options[document.getElementById('inspectionAction.id').selectedIndex].value
                            == '1';
                }
                function buildDeletedList()
                {
                    var inputString = "";
                    for (var index = 0; index
                            < inspectionBmpArray.length; index++)
                    {
                        if (inspectionBmpArray[index].isDeleted
                                == 'true')
                        {
                            inputString
                                    += "<input type=\"hidden\" id=\"bmps.bmp"
                                               + inspectionBmpArray[index].id
                                               + ".id\" name=\"bmps.bmp"
                                               + inspectionBmpArray[index].id
                                               + ".id\" value=\""
                                               + inspectionBmpArray[index].inspectionBmpId
                                               + "\" />"
                                               + "<input type=\"hidden\" id=\"bmps.bmp"
                                               + inspectionBmpArray[index].id
                                               + ".isDeletedText\" name=\"bmps.bmp"
                                               + inspectionBmpArray[index].id
                                    + ".isDeletedText\" value=\"true\" />";
                        }
                    }
                    var idb = document.getElementById("iDelBmp");
                    idb.innerHTML
                            = inputString;
                }
                function deleteBmpInput(index)
                {
                    inspectionBmpArray[index].isDeleted
                            = 'true';
                    // TODO remove next line?
                    $('#iBmp'
                              + inspectionBmpArray[index].id).html('');
                    $('#iBmp'
                              + inspectionBmpArray[index].id).css({'display':'none'});
                    buildDeletedList();
                }
                function addBmpInput()
                {
                    if (bmpAdd
                            < 50)
                    {
                        inspectionBmpArray.push(new InspectionBmp('',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  'false',
                                                                  '',
                                                                  'false'));
                        var newIndex = inspectionBmpArray.length
                                - 1;
                        inspectionBmpArray[newIndex].id
                                = bmpAdd++;
                        createBmpAddInput(inspectionBmpArray[newIndex],
                                          newIndex);
                    }
                    else
                    {
                        $('#dialog').html("You cannot have more than 50 BMPs on this inspection.").dialog('open');
                    }
                }
                function displayBmpList()
                {
                    var index = 0;
                    if (inspectionBmpArray.length
                            > 0)
                    {
                        for (index
                                     = 0; index
                                < inspectionBmpArray.length; index++)
                        {
                            if (index
                                    < 50)
                            {
                                inspectionBmpArray[index].id
                                        = bmpAdd++;
                                createBmpInput(inspectionBmpArray[index],
                                               index);
                            }
                        }
                    }
                }
                function createBmpInput(bmp, index)
                {
                    var id = inspectionBmpArray[index].id;
                    var inputStringHidden = "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".id\" name=\"bmps.bmp"
                                                    + id
                                                    + ".id\" value=\""
                                                    + bmp.inspectionBmpId
                                                    + "\" />"
                                                    + "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".projectBmpId\" name=\"bmps.bmp"
                                                    + id
                                                    + ".projectBmpId\" value=\""
                                                    + bmp.bmpId
                                                    + "\" />"
                                                    + "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".name\" name=\"bmps.bmp"
                                                    + id
                                                    + ".name\" value=\""
                                                    + bmp.name
                                                    + "\" />"
                                                    + "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".bmpCategoryName\" name=\"bmps.bmp"
                                                    + id
                                                    + ".bmpCategoryName\" value=\""
                                                    + bmp.categoryName
                                                    + "\" />"
                                                    + "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".description\" name=\"bmps.bmp"
                                                    + id
                                                    + ".description\" value=\""
                                                    + bmp.description
                            + "\" />";
                    var inputStringIsInspected = "<div class='control-label'>Inspected</div><div class='controls'><div class='form-inline'>"
                                                         + "<input type=\"radio\" id=\"radioInspectedYes"
                                                         + id
                                                         + "\" name=\"bmps.bmp"
                                                         + id
                                                         + ".isInspectedText\" value=\"true\" "
                                                         + ((bmp.isInspected
                            == 'true')
                            ? "checked"
                            : "")
                                                         + " onchange=\"javascript:isInspectedYesOnChange('"
                                                         + index
                                                         + "');\"/><label for=\"radioInspectedYes"
                                                         + id
                                                         + "\">Yes</label>"
                                                         + "<input type=\"radio\" id=\"radioInspectedNo"
                                                         + id
                                                         + "\" name=\"bmps.bmp"
                                                         + id
                                                         + ".isInspectedText\" value=\"false\" "
                                                         + ((bmp.isInspected
                            != 'true')
                            ? "checked"
                            : "")
                                                         + " onchange=\"javascript:isInspectedNoOnChange('"
                                                         + index
                                                         + "');\"/><label for=\"radioInspectedNo"
                                                         + id
                            + "\">No</label></div></div>";
                    var inputStringCategory = "<span class='label pull-right'>"
                                                      + entityify(bmp.categoryName)
                            + "</span>";
                    var inputStringBmp = "<div class='control-label'>BMP</div><div class='controls' style='padding-top: 5px;'>"
                                                 + inputStringCategory
                                                 + "<strong>"
                                                 + entityify(bmp.name)
                                                 + "</strong>: "
                                                 + bmp.description
                            + "</div>";
                    var inputStringRequired = "<div class='control-label'>Required</div><div class='controls' style='padding-top: 5px;'><span class='label'>"
                                                      + ((bmp.isRequired
                            == 'true')
                            ? 'Yes'
                            : 'No')
                                                      + "</span>"
                            + "</div>";
                    var inputStringStatus = "<div class='control-label'>Status</div><div class='controls'><select id=\"bmps.bmp"
                                                    + id
                                                    + ".bmpStatus.id\" name=\"bmps.bmp"
                                                    + id
                                                    + ".bmpStatus.id\" onchange=\"javascript:bmpStatusOnChange('"
                                                    + index
                                                    + "');\">"
                                                    + getBmpStatusOptions(bmp.statusId)
                            + "</select></div>";
                    var inputStringCondition = "<div class='control-label'>Condition</div><div class='controls'><select id=\"bmps.bmp"
                                                       + id
                                                       + ".bmpCondition.id\" name=\"bmps.bmp"
                                                       + id
                                                       + ".bmpCondition.id\" onchange=\"javascript:bmpConditionOnChange('"
                                                       + index
                                                       + "');\">"
                                                       + getBmpConditionOptions(bmp.conditionId)
                            + "</select></div>";
                    var inputStringPhoto = "<div class='control-label'>Photo</div>";
                    if (!isEmpty(bmp.downloadURL))
                    {
                        inputStringPhoto
                                += "<div class='controls controls-row'><input type=\"hidden\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" value=\""
                                           + bmp.documentId
                                           + "\" />"
                                           + "<input type=\"file\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />"
                                           + "<a href=\""
                                           + bmp.downloadURL
                                           + "\">"
                                           + bmp.fileName
                                + "</a></div>";
                    }
                    else
                    {
                        inputStringPhoto
                                += "<div class='controls'><input type=\"hidden\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" value=\""
                                           + bmp.documentId
                                           + "\" />"
                                           + "<input type=\"file\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />"
                                + "</div>";
                    }
                    var inputStringComments = "<div class='control-label'>Comments</div><div class='controls'><textarea id=\"bmps.bmp"
                                                      + id
                                                      + ".comment\" name=\"bmps.bmp"
                                                      + id
                                                      + ".comment\" cols=\"75\" rows=\"2\" onchange=\"javascript:bmpCommentOnChange('"
                                                      + index
                                                      + "');\">"
                                                      + bmp.comment
                            + "</textarea></div>";
                    var removeButton = (bmp.isRequired
                            != 'true')
                            ? "<div class='controls'><a class='btn btn-mini btn-danger' href=\"javascript:deleteBmpInput('"
                                      + index
                            + "')\">Remove</a></div>"
                            : "";
                    $('#iBmp'
                              + id).html(inputStringHidden
                                                 + $(inputStringBmp).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringRequired).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringIsInspected).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringStatus).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringCondition).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringComments).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringPhoto).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(removeButton).wrapAll('<div class="control-group"></div>').parent().outerHTML());
                    $('#iBmp'
                              + id).css({"display":"block"});
                }
                function createBmpAddInput(bmp, index)
                {
                    var id = inspectionBmpArray[index].id;
                    var inputStringHidden = "<input type=\"hidden\" id=\"bmps.bmp"
                                                    + id
                                                    + ".id\" name=\"bmps.bmp"
                                                    + id
                                                    + ".id\" value=\""
                                                    + bmp.inspectionBmpId
                            + "\" />"
                    var inputStringIsInspected = "<div class='control-label'>Inspected</div><div class='controls'><div class='form-inline'>"
                                                         + "<input type=\"radio\" id=\"radioInspectedYes"
                                                         + id
                                                         + "\" name=\"bmps.bmp"
                                                         + id
                                                         + ".isInspectedText\" value=\"true\" "
                                                         + ((bmp.isInspected
                            == 'true')
                            ? "checked"
                            : "")
                                                         + " onchange=\"javascript:isInspectedYesOnChange('"
                                                         + index
                                                         + "');\"/><label for=\"radioInspectedYes"
                                                         + id
                                                         + "\">Yes</label>"
                                                         + "<input type=\"radio\" id=\"radioInspectedNo"
                                                         + id
                                                         + "\" name=\"bmps.bmp"
                                                         + id
                                                         + ".isInspectedText\" value=\"false\" "
                                                         + ((bmp.isInspected
                            != 'true')
                            ? "checked"
                            : "")
                                                         + " onchange=\"javascript:isInspectedNoOnChange('"
                                                         + index
                                                         + "');\"/><label for=\"radioInspectedNo"
                                                         + id
                            + "\">No</label></div></div>";
                    var inputStringCategory = "<div class='control-label'>Category</div>"
                                                      + "<div class='controls'><input type=\"hidden\" id=\"bmps.bmp"
                                                      + id
                                                      + ".bmpCategoryName\" name=\"bmps.bmp"
                                                      + id
                                                      + ".bmpCategoryName\" value=\""
                                                      + bmp.categoryName
                                                      + "\" />"
                                                      + "<select id=\"bmps.bmp"
                                                      + id
                                                      + ".bmpCategoryId\" name=\"bmps.bmp"
                                                      + id
                                                      + ".bmpCategoryId\" onchange=\"javascript:categoryOnChange('"
                                                      + index
                                                      + "');\">"
                                                      + getCategoryOptions(bmp.categoryName)
                                                      + "</select>"
                            + "</div>";
                    var inputStringBmp = "<div class='control-label'>BMP</div><div class='controls controls-row'>"
                                                 + "<input type=\"hidden\" id=\"bmps.bmp"
                                                 + id
                                                 + ".name\" name=\"bmps.bmp"
                                                 + id
                                                 + ".name\" value=\""
                                                 + bmp.name
                                                 + "\" />"
                                                 + "<select id=\"bmps.bmp"
                                                 + id
                                                 + ".bmpId\" name=\"bmps.bmp"
                                                 + id
                                                 + ".bmpId\" onchange=\"javascript:bmpOnChange('"
                                                 + index
                                                 + "');\">"
                                                 + getBmpOptions(bmp.name,
                                                                 bmp.categoryName)
                                                 + "</select> "
                                                 + "<input id=\"bmps.bmp"
                                                 + id
                                                 + ".description\" name=\"bmps.bmp"
                                                 + id
                                                 + ".description\" readonly='readonly'>"
                                                 + bmp.description
                                                 + "</input>"
                            + "</div>";
                    var inputStringRequired = "<div class='control-label'>Required</div><div class='controls'><span class='label'>"
                                                      + ((bmp.isRequired
                            == 'true')
                            ? 'Yes'
                            : 'No')
                                                      + "</span>"
                            + "</div>";
                    var inputStringStatus = "<div class='control-label'>Status</div><div class='controls'><select id=\"bmps.bmp"
                                                    + id
                                                    + ".bmpStatus.id\" name=\"bmps.bmp"
                                                    + id
                                                    + ".bmpStatus.id\" onchange=\"javascript:bmpStatusOnChange('"
                                                    + index
                                                    + "');\">"
                                                    + getBmpStatusOptions(bmp.statusId)
                            + "</select></div>";
                    var inputStringCondition = "<div class='control-label'>Condition</div><div class='controls'><select id=\"bmps.bmp"
                                                       + id
                                                       + ".bmpCondition.id\" name=\"bmps.bmp"
                                                       + id
                                                       + ".bmpCondition.id\" onchange=\"javascript:bmpConditionOnChange('"
                                                       + index
                                                       + "');\">"
                                                       + getBmpConditionOptions(bmp.conditionId)
                            + "</select></div>";
                    var inputStringPhoto = "<div class='control-label'>Photo</div>";
                    if (!isEmpty(bmp.downloadURL))
                    {
                        inputStringPhoto
                                += "<div class='controls controls-row'><input type=\"hidden\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" value=\""
                                           + bmp.documentId
                                           + "\" />"
                                           + "<input type=\"file\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />"
                                           + "<a href=\""
                                           + bmp.downloadURL
                                           + "\">"
                                           + bmp.fileName
                                + "</a></div>";
                    }
                    else
                    {
                        inputStringPhoto
                                += "<div class='controls'><input type=\"hidden\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.id\" value=\""
                                           + bmp.documentId
                                           + "\" />"
                                           + "<input type=\"file\" id=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" name=\"bmps.bmp"
                                           + id
                                           + ".bmpDocument.formFile\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />"
                                + "</div>";
                    }
                    var inputStringComments = "<div class='control-label'>Comments</div><div class='controls'><textarea id=\"bmps.bmp"
                                                      + id
                                                      + ".comment\" name=\"bmps.bmp"
                                                      + id
                                                      + ".comment\" cols=\"75\" rows=\"2\" onchange=\"javascript:bmpCommentOnChange('"
                                                      + index
                                                      + "');\">"
                                                      + bmp.comment
                            + "</textarea></div>";
                    var removeButton = (bmp.isRequired
                            != 'true')
                            ? "<div class='controls'><a class='btn btn-mini btn-danger' href=\"javascript:deleteBmpInput('"
                                      + index
                            + "')\">Remove</a></div>"
                            : "";
                    $('#iBmp'
                              + id).html(inputStringHidden
                                                 + $(inputStringCategory).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringBmp).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringRequired).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringIsInspected).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringStatus).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringCondition).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringComments).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(inputStringPhoto).wrapAll('<div class="control-group"></div>').parent().outerHTML()
                                                 + $(removeButton).wrapAll('<div class="control-group"></div>').parent().outerHTML());
                    $('#iBmp'
                              + id).css({"display":"block"});
                    refreshYesNo("#iBmp"
                                         + id
                                         + " div.form-inline");
                }
                function inspectorOnChange()
                {
                    var contactId = document.getElementById('selectedInspector').value;
                    if (contactId
                            == -1)
                    {
                        addNewInspector();
                    }
                    else
                    {
                        selectAnInspector();
                    }
                }
                function selectAnInspector()
                {
                    var contactId = document.getElementById('selectedInspector').value;
                    var email = '';
                    var clientName = '';
                <%
                List inspectors =  (List) request.getAttribute(SessionKeys.EC_INSPECTOR_LIST);
                Iterator ii = inspectors.iterator();

                while (ii.hasNext() )
                {
                    CapContact insp = (CapContact) ii.next();
                %>
                    if ('<%= insp.getId() %>'
                            == contactId)
                    {
                        email
                                = '<%= StringUtil.escapeSingleQuote(insp.getEmail()) %>';
                        clientName
                                = '<%= StringUtil.escapeSingleQuote(insp.getOrganizationName()) %>';
                    }
                <%
                }
                %>
                    var inputString = "<a href='mailto:"
                                              + email
                                              + "'>"
                                              + email
                                              + "</a> "
                            + clientName;
                    var contactDetails = document.getElementById('inspectorDetails');
                    contactDetails.innerHTML
                            = inputString;
                }
                function addNewInspector()
                {
                    var firstName = '<input type="text" id="addContactFirstName" name="addContactFirstName" size="25"/>';
                    var lastName = '<input type="text" id="addContactLastName" name="addContactLastName" size="25"/>';
                    var email = '<input type="text" id="addContactEmail" name="addContactEmail" size="25"/>';
                    var address1 = '<input type="text" id="addContactAddress1" name="addContactAddress1" size="25"/>';
                    var address2 = '<input type="text" id="addContactAddress2" name="addContactAddress2" size="25"/>';
                    var city = '<input type="text" id="addContactCity" name="addContactCity" size="25"/>';
                    var zip = '<input type="text" id="addContactZip" name="addContactZip" size="10"/>';
                    var phone = '<input type="text" id="addContactPhone" name="addContactPhone" size="15"/>';
                    var state = '<select id="addContactState" name="addContactState">'
                                        + '<option value="">Select...</option>'
                                        + '<logic:iterate id="theState" name="<%= SessionKeys.EC_STATE_LIST %>">'
                                        + '<option value="<bean:write name="theState" property="code"/>"><bean:write name="theState" property="code"/></option>'
                                        + '</logic:iterate>'
                            + '</select>';
                    var organization = '<select id="addClientId" name="addClientId">'
                                               + '<option value="">Select...</option>'
                                               + '<logic:iterate id="organization" name="<%= SessionKeys.EC_ORGANIZATION_LIST %>">'
                                               + '<option value="<bean:write name="organization" property="value"/>"><bean:write name="organization" property="label"/></option>'
                                               + '</logic:iterate>'
                            + '</select>';
                    var inputString = "<div class='control-group'>"
                                              + "<label class='control-label'>Client</label><div class='controls'>"
                                              + organization
                                              + "</div>"
                                              + "</div>"
                                              + "<div class='control-group'>"
                                              + "<label class='control-label'>First Name</label><div class='controls'>"
                                              + firstName
                                              + "</div></div><div class='control-group'>"
                                              + "<label class='control-label'>Last Name</label><div class='controls'>"
                                              + lastName
                                              + "</div>"
                                              + "</div>"
                                              + "<div class='control-group'>"
                                              + "<label class='control-label'>Email</label><div class='controls'>"
                                              + email
                                              + "</div></div><div class='control-group'>"
                                              + "<label class='control-label'>Phone</label><div class='controls'>"
                                              + phone
                                              + "</div>"
                                              + "</div>"
                                              + "<div class='control-group'>"
                                              + "<label class='control-label'>Address 1</label><div class='controls'>"
                                              + address1
                                              + "</div></div><div class='control-group'>"
                                              + "<label class='control-label'>Address 2</label><div class='controls'>"
                                              + address2
                                              + "</div>"
                                              + "</div>"
                                              + "<div class='control-group'>"
                                              + "<label class='control-label'>City, State, Zip</label><div class='controls'>"
                                              + city
                                              + state
                                              + zip
                                              + "</div>"
                            + "</div>";
                    var contactDetails = document.getElementById('inspectorDetails');
                    contactDetails.innerHTML
                            = inputString;
                }
                /*
                 INSPECTION DOCUMENTS JAVASCRIPT
                 */
                var inspectionFormFileArray = new Array(0);
                var formFileAdd = 0;
                function InspectionDocument(id, name, downloadURL, documentId)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                    this.downloadURL
                            = downloadURL;
                    this.documentId
                            = documentId;
                }
                function addFormFileInput()
                {
                    if (formFileAdd
                            <= 9)
                    {
                        inspectionFormFileArray.push(new InspectionDocument('',
                                                                            '',
                                                                            '',
                                                                            ''));
                        var newIndex = inspectionFormFileArray.length
                                - 1;
                        inspectionFormFileArray[newIndex].id
                                = formFileAdd++;
                        createFormFileAddInput(inspectionFormFileArray[newIndex],
                                               newIndex);
                    }
                    else
                    {
                        $('#dialog').html("You cannot have more than 10 documents on this inspection.").dialog('open');
                    }
                }
                function createFormFileAddInput(formFile, index)
                {
                    var id = inspectionFormFileArray[index].id;
                    var inputString = "<div>"
                                              + "<input type=\"file\" id=\"documents.formFile"
                                              + id
                                              + "\" name=\"documents.formFile"
                                              + id
                            + "\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />";
                    inputString
                            += "</div>";
                    var ni = document.getElementById('iFormFile'
                                                             + id);
                    ni.innerHTML
                            = inputString;
                    /*
                     $('#iFormFile'
                     + id
                     + ' input').uniform();
                     */
                }
                function createFormFileInput(formFile, index)
                {
                    var id = inspectionFormFileArray[index].id;
                    var inputString = +"<div>";
                    if (!isEmpty(formFile.downloadURL))
                    {
                        inputString
                                += "<input type=\"file\" id=\"documents.formFile"
                                           + id
                                           + "\" name=\"documents.formFile"
                                           + id
                                           + "\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />"
                                           + "<a href=\""
                                           + formFile.downloadURL
                                           + "\">"
                                           + formFile.name
                                           + "</a>"
                                           + "Remove?<input type=\"checkbox\" id=\"documents.delete"
                                           + id
                                           + "\" name=\"documents.delete"
                                           + id
                                           + "\" />"
                                           + "<input type=\"hidden\" id=\"documents.id"
                                           + id
                                           + "\" name=\"documents.id"
                                           + id
                                           + "\" value=\""
                                           + formFile.documentId
                                + "\" />";
                    }
                    else
                    {
                        inputString
                                += "<input type=\"file\" id=\"documents.formFile"
                                           + id
                                           + "\" name=\"documents.formFile"
                                           + id
                                + "\" accept=\"image/bmp,image/gif,image/jpeg,image/pict,image/tiff,image/png\" />";
                    }
                    inputString
                            += "</div>";
                    var ni = document.getElementById('iFormFile'
                                                             + id);
                    ni.innerHTML
                            = inputString;
                    /*
                     $('#iFormFile'
                     + id
                     + ' input').uniform();
                     */
                }
                function initInspectionFormFileArray()
                {
                <%
                    Iterator docListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_FILE_DOC_ARRAY)).iterator();
                    while (docListIterator.hasNext())
                    {
                        InspectionDocumentValue docValue = (InspectionDocumentValue) docListIterator.next();
                %>
                    inspectionFormFileArray.push(new InspectionDocument('',
                                                                        '<%= docValue.getName()%>',
                                                                        '<%= docValue.getDownloadURL()%>',
                                                                        '<%= docValue.getId()%>'));
                <%
                    }
                %>
                }
                function displayInspectionFormFileList()
                {
                    var index = 0;
                    if (inspectionFormFileArray.length
                            > 0)
                    {
                        for (index
                                     = 0; index
                                < inspectionFormFileArray.length; index++)
                        {
                            if (index
                                    <= 9)
                            {
                                inspectionFormFileArray[index].id
                                        = formFileAdd++;
                                createFormFileInput(inspectionFormFileArray[index],
                                                    index);
                            }
                        }
                    }
                }
                initInspectionFields();
                inspectorOnChange();//Init BMP Arrays
                initBmpReferenceArrays();
                initInspectionBmpArray();
                displayBmpList();
                initInspectionFormFileArray();
                displayInspectionFormFileList();// End -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#inspectionDateString").datepicker({autoclose:true});
                      $("#precipEndDateString").datepicker({autoclose:true});
                      refreshYesNo("div.form-inline");
                  });
                function refreshYesNo(a)
                {
//                    var btn = $.fn.button.noConflict(); // reverts $.fn.button to jqueryui btn
                    if($.fn.button.noConflict) {
                        $.fn.btn = $.fn.button.noConflict();
                    }
                    $(a).buttonset();
//                    $.fn.button
//                            = btn; // assigns bootstrap button functionality to $.fn.btn
                }// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>

