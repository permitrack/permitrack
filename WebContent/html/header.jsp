<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<head>
    <tiles:insert page="/html/head.core.jsp">
    </tiles:insert>
    <title>
        <tiles:getAsString name="title" />
    </title>
    <meta name="description"
          content='<tiles:getAsString name="description" ignore="true" />'>
    <link href='<html:rewrite module="/" page="/css/tablesorter/style.css"/>'
          type="text/css"
          rel="Stylesheet" />
    <%--
        <!--[if gte IE 9]>
        <style type="text/css">
            .gradient
            {
                filter: none;
            }
        </style>
        <![endif]-->
    --%>
    <!--[if lte IE 8]>
    <style type="text/css">
        html {
            overflow-y: auto !important;
        }
    </style>
    <![endif]-->
    <style type="text/css">
        .jstree-default.jstree-focused, .jstree-default-rtl.jstree-focused {
            background: #fff !important;
        }
        .editable-unsaved {
            font-weight: normal;
        }
    </style>
</head>
