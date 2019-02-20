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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.value.project.ProjectTypeValue" %>
<%
    String
            strName =
            "";
    String
            strDescription =
            "";
    /*
        Integer
            intEndPointTypeId;
    */
    String
            strEndPointTypeName =
            "";
    /*
        String
            strEndPointTypeDescription;
        Boolean
            blnSWMP;
    */
    String
            strSWMP =
            "False";
    Integer
            intExtendedOnLineAccessMonths =
            0;
    Integer
            intMonthsFromStart =
            0;
    ProjectTypeValue
            projectTypeValue;
    projectTypeValue =
            (ProjectTypeValue) request.getSession()
                    .getAttribute(SessionKeys.PROJECT_TYPE_VALUE);
    if (projectTypeValue
        != null)
    {
        strName =
                projectTypeValue.getName();
        if (strName
            == null)
        {
            strName =
                    "";
        }
        strDescription =
                projectTypeValue.getDescription();
        if (strDescription
            == null)
        {
            strDescription =
                    "";
        }
        /*
                intEndPointTypeId =
                    projectTypeValue.getEndPointTypeId();
                if (intEndPointTypeId
                    == null)
                {
                    intEndPointTypeId =
                        0;
                }
        */
        strEndPointTypeName =
                projectTypeValue.getEndPointTypeName();
        if (strEndPointTypeName
            == null)
        {
            strEndPointTypeName =
                    "";
        }
        /*
                strEndPointTypeDescription =
                    projectTypeValue.getEndPointTypeDescription();
                if (strEndPointTypeDescription
                    == null)
                {
                    strEndPointTypeDescription =
                        "";
                }
        */
        /*
                blnSWMP =
                    projectTypeValue.getSwmp();
                if (blnSWMP
                    == null)
                {
                    blnSWMP =
                        false;
                }
        */
        strSWMP =
                projectTypeValue.getSwmpText();
        intExtendedOnLineAccessMonths =
                projectTypeValue.getExtendedOnlineAccessMonths();
        if (intExtendedOnLineAccessMonths
            == null)
        {
            intExtendedOnLineAccessMonths =
                    0;
        }
        intMonthsFromStart =
                projectTypeValue.getMonthsFromStart();
        if (intMonthsFromStart
            == null)
        {
            intMonthsFromStart =
                    0;
        }
    }%>
<fieldset><legend>
    <%= strName %>
</legend></fieldset>
<h4 class="myAccordian">
    Project Type Description
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="project.type.name" />
    </dt>
    <dd>
        <%= strName %>
    </dd>
    <dt>
        <bean:message key="project.type.description" />
    </dt>
    <dd>
        <%= strDescription %>
    </dd>
    <dt>
        <bean:message key="project.type.ept" />
    </dt>
    <dd>
        <%= strEndPointTypeName %>
    </dd>
    <dt>
        <bean:message key="project.type.swmp" />
    </dt>
    <dd>
        <%= strSWMP %>
    </dd>
    <dt>
        <bean:message key="project.type.eolam.abbv" />
    </dt>
    <dd>
        <%= intExtendedOnLineAccessMonths.toString() %>
        <bean:message key="project.type.eolam" />
    </dd>
    <%if (strEndPointTypeName.equalsIgnoreCase("Months From Start"))
    {%>
    <dt>
        <bean:message key="project.type.mfs.abbv" />
    </dt>
    <dd>
        <%= intMonthsFromStart %>
        <bean:message key="project.type.mfs" />
    </dd>
    <%}%>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>