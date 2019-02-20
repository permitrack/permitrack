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
<%@ page import="com.sehinc.common.config.ApplicationProperties,
                 com.sehinc.dataview.action.base.RequestKeys,
                 com.sehinc.dataview.action.base.SessionKeys" %>
<%--
<script src='<html:rewrite module="/" page="/javascript/common.js" />'
        type="text/javascript"></script>
--%>
<fieldset>
    <legend>
        <html:img module="/"
                  page="/img/dvologo.jpg"
                  border="0"
                  alt="DataView Online"
                  title="DataView Online" />
        DataView Online
    </legend>
</fieldset>
<p>
    <a class='btn btn-large'
       href="<%= ApplicationProperties.getProperty("dv.login.url") %>"
       target="_blank">
        Launch Interactive BaseMap
    </a>
</p>
<table class="restrain">
    <tr>
        <td>
            <div class="myAccordian">
                Disclaimer
            </div>
            <p>
                SEH makes no warranties or guarantees, either expressed or implied as to the completeness, accuracy, or correctness of the data contained within DataView Online, nor accepts any liability, arising from any incorrect, incomplete or misleading information contained therein. The data is neither a legally recorded map nor a survey and is not intended to be used as one. This data is a compilation of records, information and data from city, county, state and federal offices, and is to be used for reference purposes only.
            </p>
            <div class="myAccordian">
                Browser Requirements
            </div>
            <p>
                Internet Explorer 8 or higher is recommended for best results.
            </p>
            <div class="myAccordian">
                Viewing the Site:
            </div>
            <p>
                This site is best viewed at a screen resolution of 1024 * 768 or higher with colors set to thousands or higher.
            </p>
            <div class="myAccordian">
                Pop-up Blocking:
            </div>
            <p>
                Pop-up blocking will need to be <strong>disabled</strong> in order for you to use all features available on this Web site. You can also add this site to your list of sites where pop ups are allowed. In Internet Explorer, go to tools, Pop-up Blocker Settings and add <strong>mypermitrack.com</strong>. Click add and close. In Firefox go under tools, options Web Features and please add "*.mypermitrack.com" as an allowed site.
            </p>
        </td>
    </tr>
</table>
<logic:present name="<%= SessionKeys.SECURITY_GROUP_ID %>"
               scope="session">
    <logic:equal name="<%= SessionKeys.SECURITY_GROUP_ID %>"
                 value="1"
                 scope="session">
        <html:form action="/dataview">
            <div id="actions">
                <div>
                    <span>
                        <html:submit property="submit"
                                     value="Change Client" />
                    </span>
                </div>
            </div>
        </html:form>
    </logic:equal>
</logic:present>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            SetCookie("<%= RequestKeys.DV_CLIENT_ID %>",
                      "<%= request.getAttribute(RequestKeys.DV_CLIENT_ID) %>");
        </script>
    </tiles:put>
</tiles:definition>
