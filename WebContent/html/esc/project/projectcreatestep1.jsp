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
<%@ page import="com.sehinc.common.util.StringUtil,
                 com.sehinc.common.value.contact.ContactValue,
                 com.sehinc.common.value.user.StateValue,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.action.project.ProjectForm,
                 com.sehinc.erosioncontrol.value.project.ProjectContactTypeValue,
                 com.sehinc.erosioncontrol.value.project.ProjectContactValue,
                 com.sehinc.erosioncontrol.value.project.ProjectDocumentValue,
                 java.util.Iterator,
                 java.util.List" %>
<%pageContext.setAttribute("statusCodes",
                           request.getAttribute(SessionKeys.PROJECT_STATUS_CODE_LIST));%>
<html:form styleClass="form-horizontal"
           action="/projectcreatestep2"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Project
    </legend></fieldset>
    <input type="hidden"
           id="id"
           name="id" />
    <input type="hidden"
           id="contacts.contact0.id"
           name="contacts.contact0.id" />
    <input type="hidden"
           id="contacts.contact0.isDeletedText"
           name="contacts.contact0.isDeletedText" />
    <input type="hidden"
           id="contacts.contact0.addressId"
           name="contacts.contact0.addressId" />
    <input type="hidden"
           id="contacts.contact0.contactTypeId"
           name="contacts.contact0.contactTypeId"
           value="<%= request.getSession().getAttribute(SessionKeys.EC_PERMIT_AUTHORITY_PROJECT_CONTACT_TYPE_ID) %>" />
    <input type="hidden"
           id="contacts.contact0.organizationId"
           name="contacts.contact0.organizationId" />
    <input type="hidden"
           id="contacts.contact0.organizationName"
           name="contacts.contact0.organizationName" />
    <input type="hidden"
           id="contacts.contact0.orgRefClientId"
           name="contacts.contact0.orgRefClientId" />
    <input type="hidden"
           id="contacts.contact1.id"
           name="contacts.contact1.id" />
    <input type="hidden"
           id="contacts.contact1.isDeletedText"
           name="contacts.contact1.isDeletedText" />
    <input type="hidden"
           id="contacts.contact1.addressId"
           name="contacts.contact1.addressId" />
    <input type="hidden"
           id="contacts.contact1.contactTypeId"
           name="contacts.contact1.contactTypeId"
           value="<%= request.getSession().getAttribute(SessionKeys.EC_PERMITTED_PROJECT_CONTACT_TYPE_ID) %>" />
    <input type="hidden"
           id="contacts.contact1.organizationId"
           name="contacts.contact1.organizationId" />
    <input type="hidden"
           id="contacts.contact1.organizationName"
           name="contacts.contact1.organizationName" />
    <input type="hidden"
           id="contacts.contact1.orgRefClientId"
           name="contacts.contact1.orgRefClientId" />
    <input type="hidden"
           id="contacts.contact2.id"
           name="contacts.contact2.id" />
    <input type="hidden"
           id="contacts.contact2.isDeletedText"
           name="contacts.contact2.isDeletedText" />
    <input type="hidden"
           id="contacts.contact2.addressId"
           name="contacts.contact2.addressId" />
    <input type="hidden"
           id="contacts.contact2.contactTypeId"
           name="contacts.contact2.contactTypeId"
           value="<%= request.getSession().getAttribute(SessionKeys.EC_AUTHORIZED_INSPECTOR_PROJECT_CONTACT_TYPE_ID) %>" />
    <input type="hidden"
           id="contacts.contact2.organizationId"
           name="contacts.contact2.organizationId" />
    <input type="hidden"
           id="contacts.contact2.organizationName"
           name="contacts.contact2.organizationName" />
    <input type="hidden"
           id="contacts.contact2.orgRefClientId"
           name="contacts.contact2.orgRefClientId" />
    <h4 class="myAccordian">
        <bean:message key="project.permit.heading" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="permitNumber">
                <bean:message key="project.permit.number" /> *
            </label>
            <div class="controls">
                <input type="text"
                       id="permitNumber"
                       name="permitNumber"
                       size="25"
                       maxlength="50"
                       alt="Permit Number"
                       title="Permit Number" />
            </div>
        </div>
        <h6>
            <bean:message key="project.permit.issuing.agency" />
        </h6>
        <div class="control-group">
            <label class="control-label"
                   for="permitAuthorityClientId">
                <bean:message key="project.permit.issuing.agency" /> *
            </label>
            <div class="controls controls-row">
                <select id="permitAuthorityClientId"
                        name="permitAuthorityClientId"
                        title="Issuing Agency"
                        onchange="return projectClientSelectOnChange('0');">
                    <option value="">Select...</option>
                    <logic:iterate id="permitAuthority"
                                   name="<%= SessionKeys.EC_PERMIT_AUTHORITY_CLIENT_LIST %>">
                        <option value="<bean:write name='permitAuthority' property='value'/>">
                            <bean:write name='permitAuthority'
                                        property='label' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div id="internalProjectContactSelect0"></div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="small-labels">
                    <div class="well well-small">
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.firstName">
                                First Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact0.firstName"
                                       name="contacts.contact0.firstName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.lastName">
                                Last Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact0.lastName"
                                       name="contacts.contact0.lastName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.address">
                                Address
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact0.address"
                                       name="contacts.contact0.address"
                                       size="30"
                                       maxlength="50"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.city">
                                <bean:message key="project.city" />, <bean:message key="project.state" />, <bean:message key="project.zip" />
                            </label>
                            <div class="controls">
                                <span>
                                    <input class="span2"
                                           type="text"
                                           id="contacts.contact0.city"
                                           name="contacts.contact0.city"
                                           maxlength="25"
                                           readOnly />
                                    <span id="contactStateSelect0">
                                        <span id="contacts.contact0.state"></span>
                                    </span>
                                    <label for="contacts.contact0.zip"
                                           style="display: none;"></label>
                                    <input class="span1"
                                           type="text"
                                           id="contacts.contact0.zip"
                                           name="contacts.contact0.zip"
                                           maxlength="10"
                                           readOnly />
                                </span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.primaryPhone">
                                Phone
                            </label>
                            <div class="controls">
                                <input class="span2"
                                       type="text"
                                       id="contacts.contact0.primaryPhone"
                                       name="contacts.contact0.primaryPhone"
                                       maxlength="15"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact0.email">
                                Email
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact0.email"
                                       name="contacts.contact0.email"
                                       size="40"
                                       maxlength="255"
                                       readOnly />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h6>
            <bean:message key="project.permit.permitted" />
        </h6>
        <div class="control-group">
            <label class="control-label"
                   for="permittedClientId">
                <bean:message key="project.permit.permitted" /> *
            </label>
            <div class="controls">
                <select id="permittedClientId"
                        name="permittedClientId"
                        title="Permittee"
                        onchange="return projectClientSelectOnChange('1');">
                    <option value="">Select...</option>
                    <logic:iterate id="authorizedClient"
                                   name="<%= SessionKeys.EC_AUTHORIZED_CLIENT_LIST %>">
                        <option value="<bean:write name='authorizedClient' property='value'/>">
                            <bean:write name='authorizedClient'
                                        property='label' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div id="internalProjectContactSelect1"></div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="small-labels">
                    <div class="well well-small">
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.firstName">
                                First Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact1.firstName"
                                       name="contacts.contact1.firstName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.lastName">
                                Last Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact1.lastName"
                                       name="contacts.contact1.lastName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.address">
                                Address
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact1.address"
                                       name="contacts.contact1.address"
                                       size="30"
                                       maxlength="50"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.city">
                                <bean:message key="project.city" />, <bean:message key="project.state" />, <bean:message key="project.zip" />
                            </label>
                            <div class="controls">
                                <span>
                                    <input class="span2"
                                           type="text"
                                           id="contacts.contact1.city"
                                           name="contacts.contact1.city"
                                           maxlength="25"
                                           readOnly />
                                    <span id="contactStateSelect1">
                                        <span id="contacts.contact1.state"></span>
                                    </span>
                                    <label for="contacts.contact1.zip"
                                           style="display: none;"></label>
                                    <input class="span1"
                                           type="text"
                                           id="contacts.contact1.zip"
                                           name="contacts.contact1.zip"
                                           maxlength="10"
                                           readOnly />
                                </span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.primaryPhone">
                                Phone
                            </label>
                            <div class="controls">
                                <input class="span2"
                                       type="text"
                                       id="contacts.contact1.primaryPhone"
                                       name="contacts.contact1.primaryPhone"
                                       maxlength="15"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact1.email">
                                Email
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact1.email"
                                       name="contacts.contact1.email"
                                       size="40"
                                       maxlength="255"
                                       readOnly />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h6>
            <bean:message key="project.permit.authorized.inspector" />
        </h6>
        <div class="control-group">
            <label class="control-label"
                   for="authorizedInspectorClientId">
                <bean:message key="project.permit.authorized.inspector" />
            </label>
            <div class="controls">
                <select id="authorizedInspectorClientId"
                        name="authorizedInspectorClientId"
                        title="Authorized Inspector"
                        onchange="return projectClientSelectOnChange('2');">
                    <option value="">Select...</option>
                    <logic:iterate id="authorizedClient"
                                   name="<%= SessionKeys.EC_AUTHORIZED_CLIENT_LIST %>">
                        <option value="<bean:write name='authorizedClient' property='value'/>"><bean:write name='authorizedClient'
                                                                                                           property='label' /></option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div id="internalProjectContactSelect2"></div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="small-labels">
                    <div class="well well-small">
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.firstName">
                                First Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact2.firstName"
                                       name="contacts.contact2.firstName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.lastName">
                                Last Name
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact2.lastName"
                                       name="contacts.contact2.lastName"
                                       size="20"
                                       maxlength="20"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.address">
                                Address
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact2.address"
                                       name="contacts.contact2.address"
                                       size="30"
                                       maxlength="50"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.city">
                                <bean:message key="project.city" />, <bean:message key="project.state" />, <bean:message key="project.zip" />
                            </label>
                            <div class="controls">
                                <span>
                                    <input class="span2"
                                           type="text"
                                           id="contacts.contact2.city"
                                           name="contacts.contact2.city"
                                           maxlength="25"
                                           readOnly />
                                    <span id="contactStateSelect2">
                                        <span id="contacts.contact2.state"></span>
                                    </span>
                                    <label for="contacts.contact2.zip"
                                           style="display: none;"></label>
                                    <input class="span1"
                                           type="text"
                                           id="contacts.contact2.zip"
                                           name="contacts.contact2.zip"
                                           maxlength="10"
                                           readOnly />
                                </span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.primaryPhone">
                                Phone
                            </label>
                            <div class="controls">
                                <input class="span2"
                                       type="text"
                                       id="contacts.contact2.primaryPhone"
                                       name="contacts.contact2.primaryPhone"
                                       maxlength="15"
                                       readOnly />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"
                                   for="contacts.contact2.email">
                                Email
                            </label>
                            <div class="controls">
                                <input type="text"
                                       id="contacts.contact2.email"
                                       name="contacts.contact2.email"
                                       size="40"
                                       maxlength="255"
                                       readOnly />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Project Contacts
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="iContact0">
                <bean:message key="project.contact.heading" />
            </label>
            <div class="controls controls-row">
                <div id="iContact0"></div>
                <div id="iContact1"></div>
                <div id="iContact2"></div>
                <div id="iContact3"></div>
                <div id="iContact4"></div>
                <div id="iContact5"></div>
                <div id="iContact6"></div>
                <div id="iContact7"></div>
                <div id="iContact8"></div>
                <div id="iContact9"></div>
                <div id="iContact10"></div>
                <div id="iContact11"></div>
                <div id="iContact12"></div>
                <div id="iContact13"></div>
                <div id="iContact14"></div>
                <div id="iContact15"></div>
                <div id="iContact16"></div>
                <div id="iContact17"></div>
                <div id="iContact18"></div>
                <div id="iContact19"></div>
                <div id="iContact20"></div>
                <div id="iContact21"></div>
                <div id="iContact22"></div>
                <div id="iContact23"></div>
                <div id="iContact24"></div>
                <div id="iDelContact"></div>
                <a class='btn btn-mini btn-success'
                   href="javascript:addContactInput();">
                    Add Contact
                </a>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="project.information.heading" /></h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="name">
                <bean:message key="project.name" /> *
            </label>
            <div class="controls">
                <input type="text"
                       id="name"
                       name="name"
                       size="75"
                       maxlength="500"
                       alt="Project Name"
                       title="Project Name" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="projectStatusCode">
                <bean:message key="project.status" /> *
            </label>
            <div class="controls">
                <select id="projectStatusCode"
                        name="projectStatusCode"
                        title="Project Status">
                    <option value="">Select...</option>
                    <logic:iterate id="statusCodes"
                                   name="<%= SessionKeys.PROJECT_STATUS_CODE_LIST %>">
                        <option value="<bean:write name='statusCodes' property='code'/>">
                            <bean:write name='statusCodes'
                                        property='description' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="projectTypeId">
                <bean:message key="project.type" /> *
            </label>
            <div class="controls">
                <select id="projectTypeId"
                        name="projectTypeId"
                        title="Project Type">
                    <option value="">Select...</option>
                    <logic:iterate id="projectType"
                                   name="<%= SessionKeys.EC_PROJECT_TYPE_LIST %>">
                        <option value="<bean:write name='projectType' property='value'/>">
                            <bean:write name='projectType'
                                        property='label' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="inspectionTemplateId">
                <bean:message key="project.inspection.template" /> *
            </label>
            <div class="controls">
                <select id="inspectionTemplateId"
                        name="inspectionTemplateId"
                        title="Inspection Template">
                    <option value="">No Template</option>
                    <logic:iterate id="inspectionTemplate"
                                   name="<%= SessionKeys.EC_INSPECTION_TEMPLATE_LIST %>">
                        <option value="<bean:write name='inspectionTemplate' property='id'/>">
                            <bean:write name='inspectionTemplate'
                                        property='name' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="effectiveDateString">
                <bean:message key="project.effective.date" />
            </label>
            <div class="controls">
                <input type="text"
                       id="effectiveDateString"
                       name="effectiveDateString"
                       size="12"
                       alt="Effective Date"
                       title="Effective Date" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="startDateString">
                <bean:message key="project.start.date" />
            </label>
            <div class="controls">
                <input type="text"
                       id="startDateString"
                       name="startDateString"
                       size="12"
                       alt="Start Date"
                       title="Start Date" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="seedDateString">
                <bean:message key="project.seed.date" />
            </label>
            <div class="controls">
                <input type="text"
                       id="seedDateString"
                       name="seedDateString"
                       size="12"
                       alt="Seed Date"
                       title="Seed Date" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="description">
                <bean:message key="project.description" />
            </label>
            <div class="controls">
                <textarea id="description"
                          name="description"
                          cols="75"
                          rows="4"
                          title="Project Description"></textarea>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="project.site.information.heading" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="address">
                <bean:message key="project.street.address" />
            </label>
            <div class="controls">
                <input type="text"
                       id="address"
                       name="address"
                       size="75"
                       maxlength="200"
                       title="Project Address"
                       alt="Project Address" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="city">
                <bean:message key="project.city" />, <bean:message key="project.state" />, <bean:message key="project.zip" />
            </label>
            <div class="controls">
                <div>
                    <input class="span2"
                           type="text"
                           id="city"
                           name="city"
                           size="25"
                           maxlength="50"
                           alt="City"
                           title="City" />
                    <label for="state"
                           style="display: none;"></label>
                    <select class="span1"
                            id="state"
                            name="state"
                            title="State">
                        <option value="">Select...</option>
                        <logic:iterate id="theState"
                                       name="<%= SessionKeys.EC_STATE_LIST %>">
                            <option value="<bean:write name='theState' property='code'/>">
                                <bean:write name='theState'
                                            property='code' />
                            </option>
                        </logic:iterate>
                    </select>
                    <label for="zip"
                           style="display: none;"></label>
                    <input class="span1"
                           type="text"
                           id="zip"
                           name="zip"
                           size="10"
                           maxlength="12"
                           title="Zip Code"
                           alt="Zip Code" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="parcelNumber">
                <bean:message key="project.parcelNumber" />
            </label>
            <div class="controls">
                <input type="text"
                       id="parcelNumber"
                       name="parcelNumber"
                       size="20"
                       maxlength="50"
                       title="Parcel Number"
                       alt="Parcel Number">
                <input class="btn btn-mini"
                       type="submit"
                       name="submit"
                       value="Lookup Coordinates"
                       title="Lookup GIS coordinates based on Parcel Number"
                       onclick="return Lookup_Coordinates_OnClick();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="gisY">
                <bean:message key="project.gisy" />
            </label>
            <div class="controls">
                <input type="text"
                       id="gisY"
                       name="gisY"
                       size="20"
                       maxlength="20"
                       alt="Latitude"
                       title="Latitude"
                       onchange="return latitudeOnChange();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="gisX">
                <bean:message key="project.gisx" />
            </label>
            <div class="controls">
                <input type="text"
                       id="gisX"
                       name="gisX"
                       size="20"
                       maxlength="20"
                       alt="Longitude"
                       title="Longitude"
                       onchange="return longitudeOnChange();" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="zoneId">
                <bean:message key="project.zone" />
            </label>
            <div class="controls">
                <select id="zoneId"
                        name="zoneId"
                        title="Group">
                    <option value="1">N/A</option>
                    <logic:iterate id="zone"
                                   name="<%= SessionKeys.EC_ZONE_LIST %>">
                        <option value="<bean:write name='zone' property='value'/>">
                            <bean:write name='zone'
                                        property='label' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="rfaNumber">
                <bean:message key="project.rfaNumber" />
            </label>
            <div class="controls">
                <input type="text"
                       id="rfaNumber"
                       name="rfaNumber"
                       size="25"
                       maxlength="50"
                       alt="RFA Number"
                       title="RFA Number" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="blockNumber">
                <bean:message key="project.blockNumber" />
            </label>
            <div class="controls">
                <input type="text"
                       id="blockNumber"
                       name="blockNumber"
                       size="25"
                       maxlength="50"
                       alt="Block Number"
                       title="Block Number" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="lotNumber">
                <bean:message key="project.lotNumber" />
            </label>
            <div class="controls">
                <input type="text"
                       id="lotNumber"
                       name="lotNumber"
                       size="25"
                       maxlength="50"
                       alt="Lot Number"
                       title="Lot Number" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="totalSiteArea">
                <bean:message key="project.total.site.area" />
            </label>
            <div class="controls">
                <input type="text"
                       id="totalSiteArea"
                       name="totalSiteArea"
                       size="20"
                       maxlength="30"
                       alt="Total Site Area"
                       title="Total Site Area" />
                <label for="totalSiteAreaUnits"
                       style="display: none;"></label>
                <select id="totalSiteAreaUnits"
                        name="totalSiteAreaUnits">
                    <logic:iterate id="totalSiteAreaUnit"
                                   name="<%= SessionKeys.EC_PROJECT_AREA_UNITS_LIST %>">
                        <option value="<bean:write name='totalSiteAreaUnit' property='label'/>">
                            <bean:write name='totalSiteAreaUnit'
                                        property='value' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="disturbedArea">
                <bean:message key="project.disturbed.area" />
            </label>
            <div class="controls">
                <input type="text"
                       id="disturbedArea"
                       name="disturbedArea"
                       size="20"
                       maxlength="30"
                       alt="Disturbed Area"
                       title="Disturbed Area" />
                <label for="disturbedAreaUnits"
                       style="display: none;"></label>
                <select id="disturbedAreaUnits"
                        name="disturbedAreaUnits">
                    <logic:iterate id="disturbedAreaUnit"
                                   name="<%= SessionKeys.EC_PROJECT_AREA_UNITS_LIST %>">
                        <option value="<bean:write name='disturbedAreaUnit' property='label'/>">
                            <bean:write name='disturbedAreaUnit'
                                        property='value' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="newImperviousArea">
                <bean:message key="project.new.impervious.area" />
            </label>
            <div class="controls">
                <input type="text"
                       id="newImperviousArea"
                       name="newImperviousArea"
                       size="20"
                       maxlength="30"
                       alt="New Impervious Area"
                       title="New Impervious Area" />
                <label for="newImperviousAreaUnits"
                       style="display: none;"></label>
                <select id="newImperviousAreaUnits"
                        name="newImperviousAreaUnits">
                    <logic:iterate id="newImperviousAreaUnit"
                                   name="<%= SessionKeys.EC_PROJECT_AREA_UNITS_LIST %>">
                        <option value="<bean:write name='newImperviousAreaUnit' property='label'/>">
                            <bean:write name='newImperviousAreaUnit'
                                        property='value' />
                        </option>
                    </logic:iterate>
                </select>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="project.document.heading" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="projectDocumentList">
                Attachment(s)
            </label>
            <div class="controls">
                <div id="projectDocumentList"></div>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       title="Cancel"
                       onclick="bCancel=true;">
            </div>
            <div class="span4">
                <input class="btn btn-danger btn-large pull-right"
                       type="submit"
                       name="submit"
                       value="Save"
                       onclick="return checkCanComplete();" />
            </div>
            <div class="span2">
                <input class="btn btn-success btn-large pull-right"
                       type="submit"
                       name="submit"
                       value="Continue"
                       onclick="return checkCanComplete();" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript"
                    src='<html:rewrite module="/" page="/javascript/app/projectcreatestep1.js" />'></script>
            <script type="text/javascript">
                //<!--
                /*
                 Initialize the project form fields
                 */
                function initProjectFields()
                {
                <%
                ProjectForm projectForm = ((ProjectForm) request.getAttribute(RequestKeys.EC_PROJECT_FORM));
                %>
                    document.getElementById('id').value
                            = '<%= projectForm.getId() %>';
                <%
                if (projectForm.getId() != null && projectForm.getId() > 0)
                {
                %>
                    document.getElementById('permitAuthorityClientId').value
                            = '<%= projectForm.getPermitAuthorityClientId() %>';
                    document.getElementById('permittedClientId').value
                            = '<%= projectForm.getPermittedClientId() %>';
                <%
                    java.lang.String authorizedInspectorClientId = "";
                    if (projectForm.getAuthorizedInspectorClientId()
                        != null)
                    {
                        authorizedInspectorClientId =
                            projectForm.getAuthorizedInspectorClientId()
                                .toString();
                    }
                %>
                    document.getElementById('authorizedInspectorClientId').value
                            = '<%= authorizedInspectorClientId %>';
                    document.getElementById('projectTypeId').value
                            = '<%= projectForm.getProjectTypeId() %>';
                    document.getElementById('name').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getName()) %>';
                    document.getElementById('description').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getDescription()) %>';
                    document.getElementById('parcelNumber').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getParcelNumber()) %>';
                    document.getElementById('permitNumber').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getPermitNumber()) %>';
                    document.getElementById('rfaNumber').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getRfaNumber()) %>';
                    document.getElementById('blockNumber').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getBlockNumber()) %>';
                    document.getElementById('lotNumber').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getLotNumber()) %>';
                    document.getElementById('gisX').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getGisX()) %>';
                    document.getElementById('gisY').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getGisY()) %>';
                    document.getElementById('address').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getAddress()) %>';
                    document.getElementById('city').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getCity()) %>';
                    document.getElementById('state').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getState()) %>';
                    document.getElementById('zip').value
                            = '<%= StringUtil.escapeSingleQuote(projectForm.getZip()) %>';
                <% if (projectForm.getTotalSiteArea() != null)
                {
                %>
                    document.getElementById('totalSiteArea').value
                            = '<%= projectForm.getTotalSiteArea() %>';
                    document.getElementById('totalSiteAreaUnits').value
                            = '<%= projectForm.getTotalSiteAreaUnits() %>';
                <%
                }
                %>
                <%
                if (projectForm.getDisturbedArea() != null)
                {
                %>
                    document.getElementById('disturbedArea').value
                            = '<%= projectForm.getDisturbedArea() %>';
                    document.getElementById('disturbedAreaUnits').value
                            = '<%= projectForm.getDisturbedAreaUnits() %>';
                <%
                }
                %>
                <%
                if (projectForm.getNewImperviousArea() != null)
                {
                %>
                    document.getElementById('newImperviousArea').value
                            = '<%= projectForm.getNewImperviousArea() %>';
                    document.getElementById('newImperviousAreaUnits').value
                            = '<%= projectForm.getNewImperviousAreaUnits() %>';
                <%
                }
                %>
                    document.getElementById('startDateString').value
                            = '<%= projectForm.getStartDateString() %>';
                    document.getElementById('effectiveDateString').value
                            = '<%= projectForm.getEffectiveDateString() %>';
                    document.getElementById('seedDateString').value
                            = '<%= projectForm.getSeedDateString() %>';
                    document.getElementById('zoneId').value
                            = '<%= projectForm.getZoneId() %>';
                    document.getElementById('statusCode').value
                            = '<%= (projectForm.getStatusCode() == null?new Integer(1):projectForm.getStatusCode()) %>';
                    document.getElementById('inspectionTemplateId').value
                            = '<%= (projectForm.getInspectionTemplateId() == null?"":projectForm.getInspectionTemplateId().toString())%>';
                <%
                }
                %>
                    initInternalProjectContact('0',
                                               document.getElementById('permitAuthorityClientId').value,
                                               '<%= request.getSession().getAttribute(SessionKeys.EC_PERMIT_AUTHORITY_PROJECT_CONTACT_TYPE_ID) %>');
                    initInternalProjectContact('1',
                                               document.getElementById('permittedClientId').value,
                                               '<%= request.getSession().getAttribute(SessionKeys.EC_PERMITTED_PROJECT_CONTACT_TYPE_ID) %>');
                    initInternalProjectContact('2',
                                               document.getElementById('authorizedInspectorClientId').value,
                                               '<%= request.getSession().getAttribute(SessionKeys.EC_AUTHORIZED_INSPECTOR_PROJECT_CONTACT_TYPE_ID) %>');
                }
                /*
                 Document JS
                 */
                var projectDocumentArray = new Array(0);
                function ProjectDocument(id, documentId, projectId, fileName, comment, downloadURL, isDeleted)
                {
                    this.id
                            = id;
                    this.documentId
                            = documentId;
                    this.projectId
                            = projectId;
                    this.fileName
                            = fileName;
                    this.comment
                            = comment;
                    this.downloadURL
                            = downloadURL;
                    this.isDeleted
                            = isDeleted;
                }
                function initProjectDocumentArray()
                {
                <%
                Iterator documentListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_PROJECT_DOCUMENT_LIST)).iterator();
                int documentListIndex = 0;
                while (documentListIterator.hasNext()) {
                      ProjectDocumentValue documentValue = (ProjectDocumentValue) documentListIterator.next();
                %>
                    projectDocumentArray.push(new ProjectDocument('<%= documentListIndex++ %>',
                                                                  '<%= documentValue.getId() %>',
                                                                  '<%= documentValue.getProjectId() %>',
                                                                  '<%= StringUtil.escapeSingleQuote(documentValue.getName()) %>',
                                                                  '<%= StringUtil.escapeSingleQuote(documentValue.getComment()) %>',
                                                                  '<%= documentValue.getDownloadURL() %>',
                                                                  '<%= documentValue.getIsDeletedText() %>'));
                <%
                }
                %>
                }
                function initContactReferenceArrays()
                {
                <%
                Iterator contactTypeListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_CONTACT_TYPE_LIST)).iterator();
                while (contactTypeListIterator.hasNext()) {
                    ProjectContactTypeValue contactTypeValue = (ProjectContactTypeValue) contactTypeListIterator.next();
                %>
                    contactTypeArray.push(new ContactType('<%= contactTypeValue.getId() %>',
                                                          '<%= StringUtil.escapeSingleQuote(contactTypeValue.getName()) %>',
                                                          '<%= StringUtil.escapeSingleQuote(contactTypeValue.getCode()) %>',
                                                          '<%= contactTypeValue.getIsInternalText() %>'));
                <%
     }
     Iterator stateListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_STATE_LIST)).iterator();
     while (stateListIterator.hasNext()) {
           StateValue stateValue = (StateValue) stateListIterator.next(); %>
                    stateArray.push(new State('<%= stateValue.getId() %>',
                                              '<%= stateValue.getCode() %>',
                                              '<%= StringUtil.escapeSingleQuote(stateValue.getName()) %>',
                                              '<%= StringUtil.escapeSingleQuote(stateValue.getCountryName()) %>'));
                <%
                }
                %>
                }
                function initClientContactArray()
                {
                <%
                Iterator clientContactListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_CLIENT_CONTACT_LIST)).iterator();
                while (clientContactListIterator.hasNext()) {
                      ContactValue contactValue = (ContactValue) clientContactListIterator.next();
                %>
                    clientContactArray.push(new Contact('<%= contactValue.getId() %>',
                                                        '<%= contactValue.getOrganizationId() %>',
                                                        '<%= contactValue.getOrgRefClientId() %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getOrganizationName()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getFirstName()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getLastName()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getAddress()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getCity()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getStateCode()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getZip()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getPrimaryPhone()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(contactValue.getEmail()) %>',
                                                        '<%= contactValue.getAddressId() %>'));
                    addContactOrg(new ContactOrg('<%= contactValue.getOrganizationId() %>',
                                                 '<%= contactValue.getOrgRefClientId() %>',
                                                 '<%= StringUtil.escapeSingleQuote(contactValue.getOrganizationName()) %>'));
                <%
                }
                %>
                    sortContactOrgArray();
                }
                function initInternalProjectContactArray()
                {
                <%
                Iterator internalProjectContactListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_INTERNAL_PROJECT_CONTACT_LIST)).iterator();
                int internalProjectContactIndex = 0;
                while (internalProjectContactListIterator.hasNext()) {
                      ProjectContactValue projectContactValue = (ProjectContactValue) internalProjectContactListIterator.next();
                %>
                    internalProjectContactArray.push(new ProjectContact('<%= internalProjectContactIndex++ %>',
                                                                        '<%= projectContactValue.getId() %>',
                                                                        '<%= projectContactValue.getContactTypeId() %>',
                                                                        '<%= projectContactValue.getContactTypeCode() %>',
                                                                        '<%= projectContactValue.getContactTypeIsInternalText() %>',
                                                                        '<%= projectContactValue.getContactId() %>',
                                                                        '<%= projectContactValue.getOrganizationId() %>',
                                                                        '<%= projectContactValue.getOrgRefClientId() %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getOrganizationName()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getFirstName()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getLastName()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getAddress()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getCity()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getStateCode()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getZip()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getPrimaryPhone()) %>',
                                                                        '<%= StringUtil.escapeSingleQuote(projectContactValue.getEmail()) %>',
                                                                        '<%= projectContactValue.getAddressId() %>',
                                                                        '<%= projectContactValue.getIsDeletedText() %>'));
                <%
                }
                %>
                }
                function initProjectContactArray()
                {
                <%
                Iterator projectContactListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST)).iterator();
                /*
                  int projectContactIndex = 3;
                */
                while (projectContactListIterator.hasNext())
                {
                   ProjectContactValue projectContactValue = (ProjectContactValue) projectContactListIterator.next();
                %>
                    projectContactArray.push(new ProjectContact('',
                                                                '<%= projectContactValue.getId() %>',
                                                                '<%= projectContactValue.getContactTypeId() %>',
                                                                '<%= projectContactValue.getContactTypeCode() %>',
                                                                '<%= projectContactValue.getContactTypeIsInternalText() %>',
                                                                '<%= projectContactValue.getContactId() %>',
                                                                '<%= projectContactValue.getOrganizationId() %>',
                                                                '<%= projectContactValue.getOrgRefClientId() %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getOrganizationName()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getFirstName()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getLastName()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getAddress()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getCity()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getStateCode()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getZip()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getPrimaryPhone()) %>',
                                                                '<%= StringUtil.escapeSingleQuote(projectContactValue.getEmail()) %>',
                                                                '<%= projectContactValue.getAddressId() %>',
                                                                '<%= projectContactValue.getIsDeletedText() %>'));
                <%
                      }
                %>
                }
                /*
                 Init Arrays
                 */
                initProjectDocumentArray();
                initContactReferenceArrays();
                initClientContactArray();
                initInternalProjectContactArray();
                initProjectContactArray();
                initProjectFields();
                addDocumentInput();
                displayDocumentList();
                displayContactList();// End -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#startDateString").datepicker({autoclose:true});
                      $("#effectiveDateString").datepicker({autoclose:true});
                      $("#seedDateString").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>


