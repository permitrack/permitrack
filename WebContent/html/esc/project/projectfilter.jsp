<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<div id="searchFormDiv">
    <div class="row">
        <div class="span3 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Permit #
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchPermitNumber"
                               styleId="searchPermitNumber" />
                </div>
            </div>
        </div>
        <div class="span4 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Name
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchProjectName"
                               styleId="searchProjectName" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Address
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchAddress"
                               styleId="searchAddress" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    City, State, Zip
                </label>
                <div class="controls form-inline">
                    <html:text styleClass="span1"
                               name="projectSearchListForm"
                               property="searchCity"
                               styleId="searchCity" />
                    <html:select styleClass="span1"
                                 name="projectSearchListForm"
                                 property="searchState"
                                 styleId="searchState">
                        <html:option value=""> </html:option>
                        <html:options collection="<%=SessionKeys.EC_STATE_LIST%>"
                                      property="code"
                                      labelProperty="code" />
                    </html:select>
                    <html:text styleClass="span1"
                               name="projectSearchListForm"
                               property="searchZip"
                               styleId="searchZip" />
                </div>
            </div>
        </div>
        <div class="span4 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Status
                </label>
                <div class="controls">
                    <html:select styleClass="input-large"
                                 name="projectSearchListForm"
                                 property="searchProjectStatuses"
                                 styleId="searchProjectStatuses"
                                 multiple="true">
                        <html:options collection="<%=SessionKeys.PROJECT_STATUS_CODE_LIST%>"
                                      property="code"
                                      labelProperty="description" />
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Types
                </label>
                <div class="controls">
                    <html:select styleClass="input-large"
                                 name="projectSearchListForm"
                                 property="searchProjectTypes"
                                 styleId="searchProjectTypes"
                                 multiple="true">
                        <html:options collection="<%=SessionKeys.EC_PROJECT_TYPE_LIST%>"
                                      property="id"
                                      labelProperty="name" />
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Groups
                </label>
                <div class="controls">
                    <html:select styleClass="input-large"
                                 name="projectSearchListForm"
                                 property="searchZones"
                                 styleId="searchZones"
                                 multiple="true">
                        <html:options collection="<%=SessionKeys.EC_ZONE_LIST%>"
                                      property="id"
                                      labelProperty="name" />
                    </html:select>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span3 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Permit Auth
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchPermitAuthName"
                               styleId="searchPermitAuthName"
                               size="35"
                               maxlength="100" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Permittee
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchPermiteeName"
                               styleId="searchPermiteeName"
                               size="35"
                               maxlength="100" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Auth Inspector
                </label>
                <div class="controls">
                    <html:text styleClass="span2"
                               name="projectSearchListForm"
                               property="searchInspectorName"
                               styleId="searchInspectorName"
                               size="35"
                               maxlength="100" />
                </div>
            </div>
        </div>
        <div class="span4 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Total Area
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchTotalAreaSizeMin"
                                   styleId="searchTotalAreaSizeMin" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchTotalAreaSizeMax"
                                   styleId="searchTotalAreaSizeMax" />
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Disturbed Area
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchAreaSizeMin"
                                   styleId="searchAreaSizeMin" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchAreaSizeMax"
                                   styleId="searchAreaSizeMax" />
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Impervious Area
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchImpAreaSizeMin"
                                   styleId="searchImpAreaSizeMin" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchImpAreaSizeMax"
                                   styleId="searchImpAreaSizeMax" />
                    </div>
                </div>
            </div>
        </div>
        <div class="span4 well well-small">
            <div class="control-group">
                <label class="control-label">
                    Start Date
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchStartDateA"
                                   styleId="searchStartDateA" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchStartDateB"
                                   styleId="searchStartDateB" />
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Effective Date
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchEffDateA"
                                   styleId="searchEffDateA" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchEffDateB"
                                   styleId="searchEffDateB" />
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    Seed Date
                </label>
                <div class="controls form-inline">
                    <div class="input-prepend">
                        <span class="add-on">&gt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchSeedDateA"
                                   styleId="searchSeedDateA" />
                    </div>
                    and
                    <div class="input-prepend">
                        <span class="add-on">&lt;</span>
                        <html:text styleClass="span1"
                                   name="projectSearchListForm"
                                   property="searchSeedDateB"
                                   styleId="searchSeedDateB" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>