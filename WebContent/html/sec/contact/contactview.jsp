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
<h4 class="myAccordian">
    Contact Information
</h4>
<dl class="dl-horizontal">
    <dt>
        First Name
    </dt>
    <dd><bean:write name="ContactForm"
                    property="firstName" />
    </dd>
    <dt>Last Name
    </dt>
    <dd><bean:write name="ContactForm"
                    property="lastName" />
    </dd>
    <dt>Email Address
    </dt>
    <dd><bean:write name="ContactForm"
                    property="email" />
    </dd>
    <dt>Title
    </dt>
    <dd><bean:write name="ContactForm"
                    property="title" />
    </dd>
</dl>
<h4 class="myAccordian">
    Organization Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Organization Name
    </dt>
    <dd><bean:write name="ContactForm"
                    property="organizationName" />
    </dd>
</dl>
<h4 class="myAccordian">
    Address Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Address Line 1
    </dt>
    <dd><bean:write name="ContactForm"
                    property="address" />
    </dd>
    <dt>Address Line 2
    </dt>
    <dd><bean:write name="ContactForm"
                    property="address2" />
    </dd>
    <dt>City
    </dt>
    <dd><bean:write name="ContactForm"
                    property="city" />
    </dd>
    <dt>State
    </dt>
    <dd><logic:equal name="ContactForm"
                     property="stateCode"
                     value="0">
    </logic:equal>
        <logic:notEqual name="ContactForm"
                        property="stateCode"
                        value="0">
            <bean:write name="ContactForm"
                        property="stateCode" />
        </logic:notEqual>
    </dd>
    <dt>Zip Code
    </dt>
    <dd><bean:write name="ContactForm"
                    property="zip" />
    </dd>
    <dt>Primary Phone
    </dt>
    <dd><bean:write name="ContactForm"
                    property="primaryPhone" />
    </dd>
    <dt>Secondary Phone
    </dt>
    <dd><bean:write name="ContactForm"
                    property="secondaryPhone" />
    </dd>
    <dt>Fax
    </dt>
    <dd><bean:write name="ContactForm"
                    property="faxPhone" />
    </dd>
</dl>
<logic:equal value="true"
             name="ContactForm"
             property="canViewESC">
    <h4 class="myAccordian">Erosion Control (ESC) Specific
    </h4>
    <dl class="dl-horizontal">
        <dt>
            Contact Types
        </dt>
        <dd><logic:iterate id="types"
                           name="ContactForm"
                           property="allContactTypes">
            <div>
                <bean:write name='types'
                            property='description' />
            </div>
        </logic:iterate>
            <logic:empty name="types">
                (None)
            </logic:empty></dd>
    </dl>
</logic:equal>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
