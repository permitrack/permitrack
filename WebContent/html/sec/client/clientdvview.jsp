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
    Client Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Full Name
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="clientFullName" />
    </dd>
    <dt>Name
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="clientName" />
    </dd>
</dl>
<h4 class="myAccordian">
    Services
</h4>
<dl class="dl-horizontal">
    <dt>
        IMS Service
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="imsService" />
    </dd>
    <dt>IMS OV Service
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="imsOvService" />
    </dd>
    <dt>Download Directory
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="downLoads" />
    </dd>
    <dt>Attachment Layers
    </dt>
    <dd>
        <bean:write name="clientDvForm"
                    property="attachmentLayers" />
    </dd>
</dl>
<h4 class="myAccordian">
    Start
</h4>
<dl class="dl-horizontal">
    <dt>
        Start Top
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="startTop" />
    </dd>
    <dt>Start Left
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="startLeft" />
    </dd>
    <dt>Start Right
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="startRight" />
    </dd>
    <dt>Start Bottom
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="startBottom" />
    </dd>
</dl>
<h4 class="myAccordian">
    Limit
</h4>
<dl class="dl-horizontal">
    <dt>
        Limit Top
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="limitTop" />
    </dd>
    <dt>Limit Left
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="limitLeft" />
    </dd>
    <dt>Limit Right
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="limitRight" />
    </dd>
    <dt>Limit Bottom
    </dt>
    <dd><bean:write name="clientDvForm"
                    property="limitBottom" />
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
