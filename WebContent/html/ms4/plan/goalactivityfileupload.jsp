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
<html:form action="/goalactivityfileupload"
           method="POST"
           enctype="multipart/form-data">
    <fieldset><legend>
        <bean:message key="goalactivity.file.upload.heading" />
    </legend></fieldset>
    <div class="fileupload fileupload-new"
         data-provides="fileupload">
        <div class="input-append">
            <div class="uneditable-input span3">
                <i class="icon-file fileupload-exists"></i>
                <span class="fileupload-preview"></span>
            </div>
            <span class="btn btn-file">
                <span class="fileupload-new">Select file</span>
                <span class="fileupload-exists">Change</span>
                <html:file property="attachFile1"
                           styleId="attachFile1" />
            </span>
            <a href="#"
               class="btn fileupload-exists"
               data-dismiss="fileupload">Remove
            </a>
        </div>
    </div>
    <div class="fileupload fileupload-new"
         data-provides="fileupload">
        <div class="input-append">
            <div class="uneditable-input span3">
                <i class="icon-file fileupload-exists"></i>
                <span class="fileupload-preview"></span>
            </div>
            <span class="btn btn-file">
                <span class="fileupload-new">Select file</span>
                <span class="fileupload-exists">Change</span>
                <html:file property="attachFile2"
                           styleId="attachFile2" />
            </span>
            <a href="#"
               class="btn fileupload-exists"
               data-dismiss="fileupload">Remove
            </a>
        </div>
    </div>
    <div class="fileupload fileupload-new"
         data-provides="fileupload">
        <div class="input-append">
            <div class="uneditable-input span3">
                <i class="icon-file fileupload-exists"></i>
                <span class="fileupload-preview"></span>
            </div>
            <span class="btn btn-file">
                <span class="fileupload-new">Select file</span>
                <span class="fileupload-exists">Change</span>
                <html:file property="attachFile3"
                           styleId="attachFile3" />
            </span>
            <a href="#"
               class="btn fileupload-exists"
               data-dismiss="fileupload">Remove
            </a>
        </div>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Attach" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>