<%@ page import="com.sehinc.common.db.client.DvClientInformation" %>
<%@ page import="com.sehinc.dataview.DataViewConstants" %>
<%@ page import="java.math.BigDecimal" %>
<html>
    <body>
        <h1>DataView Index Page</h1>
        <br>
        <%
            DvClientInformation
                    clientInformation;
            clientInformation =
                    new DvClientInformation();
            clientInformation.setClientFullName("Chetek, WI");
            clientInformation.setClientName("Chetek");
            clientInformation.setImsService("Chetek2");
            clientInformation.setImsOvService("ChetekOV2");
            clientInformation.setStartRight(new BigDecimal("364950.00000000"));
            clientInformation.setStartLeft(new BigDecimal("349682.00000000"));
            clientInformation.setStartTop(new BigDecimal("73172.00000000"));
            clientInformation.setStartBottom(new BigDecimal("60292.00000000"));
            clientInformation.setLimitRight(new BigDecimal("364950.00000000"));
            clientInformation.setLimitLeft(new BigDecimal("349682.00000000"));
            clientInformation.setLimitTop(new BigDecimal("73172.00000000"));
            clientInformation.setLimitBottom(new BigDecimal("60292.00000000"));
            session.setAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION,
                                 clientInformation);
        %>
        <jsp:useBean id="dataViewClientInformation"
                     scope="session"
                     type="com.sehinc.common.db.client.DvClientInformation" />
        <hr>
        imsURL = '<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=
        <jsp:getProperty name="dataViewClientInformation"
                         property="imsService" />
        ';
        <br>
        imsOVURL = '<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=
        <jsp:getProperty name="dataViewClientInformation"
                         property="imsOvService" />
        ';
        <br>
        <br>
        <br>
        <form action="./index.jsp">
            Please select your client:
            <br>
            <select name="client">
                <option>Select One</option>
                <option value="Springfield"> Springfield, Wisconsin</option>
                <option value="Chetek2"> Chetek, Wisconsin</option>
                <option value="SantaClara"> Santa Clara</option>
            </select>
            <br>
            <input type="submit" />
        </form>
        <a href="/sehsvc/html/dvo/index.htm">Enter DataView Online</a>
        <br>
        <a href="/sehsvc/html/dvo/sendArcXML/default.htm">ArcXML tester</a>
        <br>
        Cookies</br>
    </body>
</html>
