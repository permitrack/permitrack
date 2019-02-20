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
<html:form styleClass="form-horizontal"
           action="/clientlogocreate"
           method="POST"
           enctype="multipart/form-data">
    <fieldset><legend>
        Client Logo
    </legend></fieldset>
    <h4 class="myAccordian">
        Logo
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Report Logo Image
            </label>
            <div class="controls controls-row">
                <logic:notEmpty name='clientLogoForm'
                                property='logoLocation'>
                    <label>
                        <bean:write name='clientLogoForm'
                                    property='logoLocation' />
                    </label>
                </logic:notEmpty>
                <html:file size="50"
                           property="logoFile" />
                <div class="alert alert-info">
                    This image will appear within the report headers within PermiTrack
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Project Map Header Image
            </label>
            <div class="controls controls-row">
                <logic:notEmpty name='clientLogoForm'
                                property='mapLogoLocation'>
                    <label>
                        <bean:write name='clientLogoForm'
                                    property='mapLogoLocation' />
                    </label>
                </logic:notEmpty>
                <html:file size="50"
                           property="mapLogoFile" />
                <div class="alert alert-info">
                    This image will appear within the map header within PermiTrack Erosion Control (ESC), if applicable
                </div>
            </div>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6"></div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
