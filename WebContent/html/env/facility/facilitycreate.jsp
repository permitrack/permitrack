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
           action="/facilitycreateaction"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New Facility
    </legend></fieldset>
    <h4 class="myAccordian">
        Facility Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="facility.number" />
            </label>
            <div class="controls"><html:text styleId="number"
                                             name="FacilityForm"
                                             property="number"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.name" />
            </label>
            <div class="controls"><html:text styleId="name"
                                             name="FacilityForm"
                                             property="name"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.desc" />
            </label>
            <div class="controls"><html:text styleId="description"
                                             name="FacilityForm"
                                             property="description"
                                             size="25"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.active.date" />
            </label>
            <div class="controls"><html:text styleId="activeTsText"
                                             name="FacilityForm"
                                             property="activeTsText"
                                             size="12"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.inactive.date" />
            </label>
            <div class="controls"><html:text styleId="inactiveTsText"
                                             name="FacilityForm"
                                             property="inactiveTsText"
                                             size="12"
                                             maxlength="10" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.phone" />
            </label>
            <div class="controls"><html:text styleId="phone"
                                             name="FacilityForm"
                                             property="phone" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.fax" />
            </label>
            <div class="controls"><html:text styleId="fax"
                                             name="FacilityForm"
                                             property="fax" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.county.name" />
            </label>
            <div class="controls"><html:text styleId="countyName"
                                             name="FacilityForm"
                                             property="countyName" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.sic.code" />
            </label>
            <div class="controls"><html:text styleId="sicCode"
                                             name="FacilityForm"
                                             property="sicCode" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.class.desc" />
            </label>
            <div class="controls"><html:text styleId="classDesc"
                                             name="FacilityForm"
                                             property="classDesc" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.daily.hrs.op" />
            </label>
            <div class="controls"><html:text styleId="dailyHrsOp"
                                             name="FacilityForm"
                                             property="dailyHrsOp" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.days.op.week" />
            </label>
            <div class="controls"><html:text styleId="daysOpWeek"
                                             name="FacilityForm"
                                             property="daysOpWeek" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.weeks.op.year" />
            </label>
            <div class="controls"><html:text styleId="weeksOpYear"
                                             name="FacilityForm"
                                             property="weeksOpYear" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.business.hrs" />
            </label>
            <div class="controls"><html:text styleId="businessHrs"
                                             name="FacilityForm"
                                             property="businessHrs" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="facility.address.physical" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="facility.address.line1" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="physAddr1"
                                             styleId="physAddr1"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.line2" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="physAddr2"
                                             styleId="physAddr2"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.line3" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="physAddr3"
                                             styleId="physAddr3"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.city" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="physAddrCity"
                                             styleId="physAddrCity"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.state" />
            </label>
            <div class="controls"><html:select name="FacilityForm"
                                               property="physAddrState"
                                               styleId="physAddrState">
                <option value="0"
                        selected>...
                </option>
                <html:options collection="<%=SessionKeys.STATE_LIST%>"
                              property="code"
                              labelProperty="code" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.zip" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="physAddrZip"
                                             styleId="physAddrZip"
                                             size="14"
                                             maxlength="10" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="facility.address.mailing" />
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                <bean:message key="facility.address.line1" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="mailAddr1"
                                             styleId="mailAddr1"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.line2" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="mailAddr2"
                                             styleId="mailAddr2"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.city" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="mailAddrCity"
                                             styleId="mailAddrCity"
                                             size="50"
                                             maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.state" />
            </label>
            <div class="controls"><html:select name="FacilityForm"
                                               property="mailAddrState"
                                               styleId="mailAddrState">
                <option value="0"
                        selected>...
                </option>
                <html:options collection="<%=SessionKeys.STATE_LIST%>"
                              property="code"
                              labelProperty="code" />
            </html:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><bean:message key="facility.address.zip" />
            </label>
            <div class="controls"><html:text name="FacilityForm"
                                             property="mailAddrZip"
                                             styleId="mailAddrZip"
                                             size="14"
                                             maxlength="10" />
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
            <html:javascript formName="FacilityForm" />
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
                        var activeDate = new Date(document.getElementById("activeTsText").value);
                        var inactiveDate = new Date(document.getElementById("inactiveTsText").value);
                        if (inactiveDate.getTime()
                                < activeDate.getTime())
                        {
                            $('#dialog').html("Time period is invalid. Active Date must precede Inactive Date.").dialog('open');
                            return false;
                        }
                        var phone = document.getElementById("phone");
                        var fax = document.getElementById("fax");
                        var blnPhone = new Object();
                        blnPhone.valid
                                = false;
                        if (phone.value
                                != "")
                        {
                            phone.value
                                    = formatPhone(phone.value,
                                                  'Phone',
                                                  blnPhone);
                            if (!blnPhone.valid)
                            {
                                return false;
                            }
                        }
                        if (fax.value
                                != "")
                        {
                            fax.value
                                    = formatPhone(fax.value,
                                                  'Fax',
                                                  blnPhone);
                            if (!blnPhone.valid)
                            {
                                return false;
                            }
                        }
                        return validateFacilityForm(form);
                    }
                }// -->
            </script>
            <script type="text/javascript">
                //<!--
                $(function ()
                  {
                      $("#activeTsText").datepicker({autoclose:true});
                      $("#inactiveTsText").datepicker({autoclose:true});
                  });// -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
