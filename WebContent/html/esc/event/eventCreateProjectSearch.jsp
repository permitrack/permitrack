<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ include file="eventPageHeader.jsp" %>
<html:form styleClass="form-horizontal"
           action="/eventCreateProjectSearchAction">
    <fieldset><legend>
        Search
    </legend></fieldset>
    <html:hidden name="eventCreateForm"
                 property="page"
                 value="2" />
    <html:hidden styleId="nextPage"
                 name="eventCreateForm"
                 property="nextPage" />
    <h4 class="myAccordian">
        Filter
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">Name</label>
            <div class="controls"><html:text property="projectSearchForm.name"
                                             size="50" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">Parcel Number</label>
            <div class="controls"><html:text property="projectSearchForm.parcelNumber"
                                             size="20" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">Permit Number</label>
            <div class="controls"><html:text property="projectSearchForm.permitNumber"
                                             size="20" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">Address</label>
            <div class="controls"><html:text property="projectSearchForm.address"
                                             size="50" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">City</label>
            <div class="controls"><html:text property="projectSearchForm.city"
                                             size="50" /></div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="state">State
            </label>
            <div class="controls">
                <select id="state"
                        name="state">
                    <option value="">Select...</option>
                    <logic:iterate id="theState"
                                   name="<%= SessionKeys.EC_STATE_LIST %>">
                        <option value="<bean:write name='theState' property='code'/>">
                            <bean:write name='theState'
                                        property='code' /></option>
                    </logic:iterate>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip Code</label>
            <div class="controls">
                <html:text property="projectSearchForm.zipCode"
                           size="30" />
                <br />
                <span class="text-info">(comma separated list)</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Area Size Min</label>
            <div class="controls"><html:text property="projectSearchForm.areaSizeMin"
                                             size="12" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">Area Size Max</label>
            <div class="controls">
                <html:text property="projectSearchForm.areaSizeMax"
                           size="12" /></div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Project Create Date Between
            </label>
            <div class="controls controls-row">
                <html:text styleId="creationDateMin"
                           property="projectSearchForm.creationDateMin"
                           size="12" />
                <p class="muted">
                    and
                </p>
                <html:text styleId="creationDateMax"
                           property="projectSearchForm.creationDateMax"
                           size="12" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Project Status
            </label>
            <div class="controls">
                <label class="radio">
                    <html:radio property="projectSearchForm.projectStatusAIB"
                                value="0" />
                    Any
                </label>
                <label class="radio">
                    <html:radio property="projectSearchForm.projectStatusAIB"
                                value="1" />
                    Active
                </label>
                <label class="radio">
                    <html:radio property="projectSearchForm.projectStatusAIB"
                                value="2" />
                    Inactive
                </label>
            </div>
        </div>
        <%try
        {
            List
                    projectTypesList =
                    com.sehinc
                            .erosioncontrol
                            .server
                            .project
                            .ProjectService
                            .getProjectTypeLabelValueList(clientValue);
            pageContext.setAttribute("projectTypesList",
                                     projectTypesList);
        }
        catch (Throwable throwable)
        {
            out.println("<td>there was an exception:"
                        + throwable
                        + " and... </td>");
        }%>
        <div class="control-group">
            <label class="control-label">
                Project Types
            </label>
            <div class="controls">
                <html:select multiple="true"
                             size="10"
                             property="projectSearchForm.selectedProjectTypes">
                    <html:option value="0">Any...</html:option>
                    <html:options collection="projectTypesList"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
        <%try
        {
            List
                    projectZonesList =
                    com.sehinc
                            .erosioncontrol
                            .server
                            .project
                            .ProjectService
                            .getZonesByClientLabelValueList(clientValue);
            pageContext.setAttribute("projectZonesList",
                                     projectZonesList);
        }
        catch (Throwable throwable)
        {
            out.println("<tr><td>The project zones caused an exception: "
                        + throwable
                        + "</td></tr>");
        }%>
        <div class="control-group">
            <label class="control-label">
                Groups
            </label>
            <div class="controls">
                <html:select multiple="true"
                             size="10"
                             property="projectSearchForm.selectedProjectZones">
                    <html:option value="0">
                        Any...
                    </html:option>
                    <html:options collection="projectZonesList"
                                  property="value"
                                  labelProperty="label" />
                </html:select>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span4">
                <html:submit styleClass="btn btn-large"
                             onclick="return Cancel_OnClick();"
                             value="Cancel" />
            </div>
            <div class="span4">
                <html:submit styleClass="btn btn-danger btn-large pull-right"
                             onclick="return Back_OnClick();"
                             value="Back" />
            </div>
            <div class="span4">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             onclick="return Next_OnClick();"
                             value="Next" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            //<!--
            $(function ()
              {
                  $("#creationDateMin").datepicker({autoclose:true});
                  $("#creationDateMax").datepicker({autoclose:true});
              });// -->
        </script>
    </tiles:put>
</tiles:definition>
