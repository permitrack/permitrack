<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ page import="com.sehinc.common.CommonConstants,
                 com.sehinc.common.action.base.SessionService,
                 com.sehinc.common.value.client.ClientValue" %>
<%@ page import="com.sehinc.erosioncontrol.action.base.RequestKeys" %>
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<%@ page import="java.util.List" %>
<%--<jsp:useBean id="constants"
             class="com.sehinc.common.config.ApplicationBean"
             scope="application" />--%>
<%
    String
            strPageTitle;
    String
            strPageDescription;
    String
            strClientName =
            "";
    String
            clientId =
            "";
    String
            strMessage;
    strPageTitle =
            (String) request.getSession()
                    .getAttribute(SessionKeys.EC_PAGE_TITLE);
    if (strPageTitle
        == null)
    {
        strPageTitle =
                "";
    }
    strPageDescription =
            (String) request.getSession()
                    .getAttribute(SessionKeys.EC_PAGE_DESCRIPTION);
    if (strPageDescription
        == null)
    {
        strPageDescription =
                "";
    }
    strMessage =
            (String) request.getSession()
                    .getAttribute(SessionKeys.EC_EVENT_MESSAGE);
    if (strMessage
        == null)
    {
        strMessage =
                "";
    }
    ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.EROSION_CONTROL_MODULE);
    if (clientValue
        != null)
    {
        strClientName =
                clientValue.getName();
    }%>
<fieldset><legend>
    <%= strPageTitle %>
</legend></fieldset>
<%--
    <h6>
        <%= strPageDescription %>
    </h6>
--%>
<%--
    <h4>
        <%= strClientName %>
    </h4>
--%>
<%if (!strMessage.isEmpty())
{%>
<div class="alert alert-warning">
    <%= strMessage %>
</div>
<%}%>
<script type="text/javascript">
    //<!-- Script Begin
    function Cancel_OnClick()
    {
        var theField = document.getElementById("nextPage");
        theField.value
                = "cancel";
    }
    function Next_OnClick()
    {
        var theField = document.getElementById("nextPage");
        theField.value
                = "next";
    }
    function Back_OnClick()
    {
        var theField = document.getElementById("nextPage");
        theField.value
                = "back";
    }//  Script End -->
</script>

