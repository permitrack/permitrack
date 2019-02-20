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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<bean:define id="inspector"
             name="inspectionForm"
             property="inspector" />
<bean:define id="inspectorClient"
             name="inspectionForm"
             property="inspectorClient" />
<fieldset>
    <legend>
        Inspection for
        <bean:write name="<%= SessionKeys.EC_PROJECT %>"
                    property="name" />
        on
        <bean:write name="inspectionForm"
                    property="inspectionDateString" />
        <span class="label">
            <bean:write name="inspectionForm"
                        property="status" />
        </span>
        <bean:define id="inspectionSecurityValue"
                     name="<%= SessionKeys.EC_INSPECTION %>"
                     property="inspectionSecurityPermissionValue" />
        <logic:equal name="inspectionSecurityValue"
                     property="isUpdate"
                     value="true">
            <html:link styleClass='btn btn-mini btn-success'
                       action="/inspectioneditpage.do"
                       paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                       paramName="inspectionForm"
                       paramProperty="id">
                <img src="<html:rewrite module="/" page="/img/icons/sehedit.png" />"
                     alt="Edit"
                     title="Edit this inspection" />
                Edit
            </html:link>
        </logic:equal>
        <html:link styleClass='btn btn-mini btn-primary'
                   action="/inspectionemailpage.do"
                   paramId="<%= RequestKeys.EC_INSPECTION_ID %>"
                   paramName="inspectionForm"
                   paramProperty="id">
            <img src="<html:rewrite module="/" page="/img/icons/user.gif" />"
                 alt="Email"
                 title="Email this inspection to other users" />
            Email
        </html:link>
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
    Inspector
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="inspection.id" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="id" />
    </dd>
    <dt>
        <bean:message key="inspection.entered.by" />
    </dt>
    <dd>
        <div>
            <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                        property="firstName" />
            <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                        property="lastName" />
        </div>
        <div>
            <bean:write name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                        property="primaryPhone" />
        </div>
        <div>
            <bean:define id="email"
                         name="<%= SessionKeys.EC_INSPECTOR_USER %>"
                         property="emailAddress" />
            <a href="mailto:<bean:write name='email'/>">
                <bean:write name='email' />
            </a>
        </div>
    </dd>
    <dt>
        <bean:message key="inspection.inspector" />
    </dt>
    <dd>
        <div>
            <bean:write name="inspector"
                        property="firstName" />
            <bean:write name="inspector"
                        property="lastName" />
        </div>
        <div>
            <bean:define id="email"
                         name="inspector"
                         property="email" />
            <a href="mailto:<bean:write name='email'/>">
                <bean:write name='email' />
            </a>
        </div>
        <div>
            <bean:write name="inspectorClient"
                        property="name" />
        </div>
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="inspection.information.heading" />
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="inspection.status" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="status" />
    </dd>
    <dt>
        <bean:message key="inspection.date" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="inspectionDateString" />
    </dd>
    <dt>
        <bean:message key="inspection.time" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="timeString" />
    </dd>
    <dt>
        <bean:message key="inspection.weather.trends" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="weatherTrends" />
    </dd>
    <dt>
        <bean:message key="inspection.precip.end.date" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="precipEndDateString" />
    </dd>
    <logic:notEmpty name="inspectionForm"
                    property="precipAmount">
        <dt>
            <bean:message key="inspection.precip.amount" />
        </dt>
        <dd>
            <bean:write name="inspectionForm"
                        property="precipAmount" />
            <bean:message key="inspection.precip.amount.units" />
        </dd>
    </logic:notEmpty>
    <dt>
        <bean:message key="inspection.precip.source" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="precipSource" />
    </dd>
    <logic:notEmpty name="inspectionForm"
                    property="temperature">
        <dt>
            <bean:message key="inspection.temperature" />
        </dt>
        <dd>
            <bean:write name="inspectionForm"
                        property="temperature" />
            <bean:message key="inspection.temperature.units" />
        </dd>
    </logic:notEmpty>
    <dt>
        <bean:message key="inspection.reason" />
    </dt>
    <dd>
        <bean:write name="inspectionForm"
                    property="inspectionReason.name" />
    </dd>
    <logic:notEqual name='inspectionForm'
                    property='inspectionAction.id'
                    value='1'>
        <dt>
            <bean:message key="inspection.action" />
        </dt>
        <dd>
            <bean:write name="inspectionForm"
                        property="inspectionAction.name" />
        </dd>
        <dt>
            <bean:message key="inspection.action.comment" />
        </dt>
        <dd>
            <bean:write name="inspectionForm"
                        property="inspectionActionComment" />
        </dd>
    </logic:notEqual>
</dl>
<logic:notEmpty name="inspectionForm"
                property="inspectionDocumentArray">
    <h4 class="myAccordian">
        <bean:message key="inspection.document" />s
    </h4>
    <dl>
        <logic:iterate id="inspectionDocumentArray"
                       name="inspectionForm"
                       property="inspectionDocumentArray">
            <dt>
                <bean:message key="inspection.document" />
            </dt>
            <dd>
                <logic:empty name='inspectionDocumentArray'
                             property='name'>
                    none
                </logic:empty>
                <logic:notEmpty name='inspectionDocumentArray'
                                property='name'>
                    <a href="<bean:write name='inspectionDocumentArray' property='downloadURL' />">
                        <bean:write name='inspectionDocumentArray'
                                    property='name' />
                    </a>
                </logic:notEmpty>
            </dd>
        </logic:iterate>
    </dl>
</logic:notEmpty>
<h4 class="myAccordian">
    <bean:message key="inspection.bmp.heading" />
</h4>
<logic:empty name="<%= SessionKeys.EC_INSPECTION_BMP_LIST %>"
             scope="session">
    <p class="text-warning">
        <bean:message key="inspection.bmps.empty" />
    </p>
</logic:empty>
<logic:notEmpty name="<%= SessionKeys.EC_INSPECTION_BMP_LIST %>"
                scope="session">
    <div>
        <logic:iterate id="inspectionBmp"
                       name="<%= SessionKeys.EC_INSPECTION_BMP_LIST %>"
                       scope="session">
            <dl class="dl-horizontal well well-small">
                <dt>
                    BMP
                </dt>
                <dd>
                    <strong>
                        <bean:write name='inspectionBmp'
                                    property='name' />
                    </strong>
                    <span class="label pull-right">
                        <bean:write name='inspectionBmp'
                                    property='bmpCategoryName' />
                    </span>
                    <small>
                        <bean:write name='inspectionBmp'
                                    property='description' />
                    </small>
                </dd>
                <dt>
                    Required
                </dt>
                <dd>
                    <logic:equal name='inspectionBmp'
                                 property='isRequired'
                                 value='true'>
                        <span class="label label-warning">
                            Yes
                        </span>
                    </logic:equal>
                    <logic:notEqual name='inspectionBmp'
                                    property='isRequired'
                                    value='true'>
                        <span class="label">
                            No
                        </span>
                    </logic:notEqual>
                </dd>
                <dt>
                    Inspected
                </dt>
                <dd>
                    <logic:equal name="inspectionBmp"
                                 property="isInspectedText"
                                 value="true">
                        <span class="label label-success">
                            Yes
                        </span>
                    </logic:equal>
                    <logic:notEqual name="inspectionBmp"
                                    property="isInspectedText"
                                    value="true">
                        <span class="label label-important">
                            No
                        </span>
                    </logic:notEqual>
                </dd>
                <dt>
                    Status
                </dt>
                <dd>
                    <bean:write name='inspectionBmp'
                                property='bmpStatus.name' />
                </dd>
                <dt>
                    Condition
                </dt>
                <dd>
                    <bean:write name='inspectionBmp'
                                property='bmpCondition.name' />
                </dd>
                <logic:notEmpty name='inspectionBmp'
                                property='bmpDocument.name'>
                    <dt>
                        Photo
                    </dt>
                    <dd>
                        <p>
                            <a href="<bean:write name='inspectionBmp' property='bmpDocument.downloadURL' />">
                                <img src="<bean:write name='inspectionBmp' property='bmpDocument.downloadURL' />&size=small"
                                     alt="<bean:write name='inspectionBmp' property='bmpDocument.name' />"
                                     style="max-width: 400px; max-height: 400px;" />
                            </a>
                        </p>
                        <p>
                            <a href="<bean:write name='inspectionBmp' property='bmpDocument.downloadURL' />">
                                <bean:write name='inspectionBmp'
                                            property='bmpDocument.name' />
                            </a>
                        </p>
                    </dd>
                </logic:notEmpty>
                <logic:notEmpty name='inspectionBmp'
                                property='comment'>
                    <dt>
                        Comments
                    </dt>
                    <dd>
                        <bean:write name='inspectionBmp'
                                    property='comment' />
                    </dd>
                </logic:notEmpty>
            </dl>
        </logic:iterate>
    </div>
</logic:notEmpty>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
