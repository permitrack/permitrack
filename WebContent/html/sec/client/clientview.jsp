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
<fieldset><legend>
    <bean:write name="clientForm"
                property="name" />
</legend></fieldset>
<h4 class="myAccordian">
    Location
</h4>
<dl class="dl-horizontal">
    <dt>
        Phone
    </dt>
    <dd>
        <bean:write name="clientForm"
                    property="phone" />
    </dd>
    <dt>
        Address Line 1
    </dt>
    <dd><bean:write name="clientForm"
                    property="line1" />
    </dd>
    <dt>Address Line 2
    </dt>
    <dd><bean:write name="clientForm"
                    property="line2" />
    </dd>
    <dt>City
    </dt>
    <dd><bean:write name="clientForm"
                    property="city" />
    </dd>
    <dt>State
    </dt>
    <dd><bean:write name="clientForm"
                    property="state" />
    </dd>
    <dt>Zip
    </dt>
    <dd><bean:write name="clientForm"
                    property="postalCode" />
    </dd>
</dl>
<h4 class="myAccordian">
    General Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Federal Tax ID
    </dt>
    <dd><bean:write name="clientForm"
                    property="federalTaxId" />
    </dd>
    <dt>State Tax ID
    </dt>
    <dd><bean:write name="clientForm"
                    property="stateTaxId" />
    </dd>
    <dt>Web Page
    </dt>
    <dd><bean:write name="clientForm"
                    property="webPage" />
    </dd>
    <dt>Description
    </dt>
    <dd>
        <bean:write name="clientForm"
                    property="comment" />
    </dd>
</dl>
<h4 class="myAccordian">
    Contact Information
</h4>
<dl class="dl-horizontal">
    <dt>
        First Name
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactFirstName" />
    </dd>
    <dt>Title
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactTitle" />
    </dd>
    <dt>Last Name
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactLastName" />
    </dd>
    <dt>Address Line 1
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactAddress" />
    </dd>
    <dt>Address Line 2
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactAddress2" />
    </dd>
    <dt>City
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactCity" />
    </dd>
    <dt>State
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactState" />
    </dd>
    <dt>Zip
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactZip" />
    </dd>
    <dt>Email Address
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactEMail" />
    </dd>
    <dt>Primary Phone
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactPrimaryPhone" />
    </dd>
    <dt>Secondary Phone
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactSecondaryPhone" />
    </dd>
    <dt>Fax Phone
    </dt>
    <dd><bean:write name="clientForm"
                    property="contactFaxPhone" />
    </dd>
</dl>
<h4 class="myAccordian">
    Subscription
</h4>
<dl class="dl-horizontal">
    <dt>
        Paid Subscriptions
    </dt>
    <dd>
        <p>
            Storm/Sewer (MS4):
            <span class="label">
                <bean:write name="clientForm"
                            property="canAccessStormWaterText" />
            </span>
        </p>
        <p>
            Erosion Control (ESC):
            <span class="label">
                <bean:write name="clientForm"
                            property="canAccessErosionControlText" />
            </span>
        </p>
        <p>
            DataView Online (DVO):
            <span class="label">
                <bean:write name="clientForm"
                            property="canAccessDataViewText" />
            </span>
        </p>
        <p>
            Environment (ENV):
            <span class="label">
                <bean:write name="clientForm"
                            property="canAccessEnvironmentText" />
            </span>
        </p>
    </dd>
</dl>
<h4 class="myAccordian">
    Storm/Sewer (MS4) Specific Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Major Watershed
    </dt>
    <dd>
        <bean:write name="clientForm"
                    property="majorWaterShed" />
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
