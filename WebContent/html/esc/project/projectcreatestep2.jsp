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
<%@ page import="com.sehinc.common.util.StringUtil,
                 com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.erosioncontrol.db.bmp.EcBmp,
                 com.sehinc.erosioncontrol.value.project.ProjectBmpValue,
                 java.util.Iterator,
                 java.util.List" %>
<html:form styleClass="form-horizontal"
           action="/projectcreatestep3"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        <bean:message key="project.bmp.heading" />
    </legend></fieldset>
    <input type="hidden"
           id="id"
           name="id" />
    <div id="projectBmpList"></div>
    <a class="btn btn-success"
       href="javascript:addBmpInput();">
        + Add BMP
    </a>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       onclick="bCancel=true;">
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Save" />
            </div>
        </tiles:put>
    </tiles:insert>
    <tiles:definition id="scripts"
                      scope="request">
        <tiles:put name="scripts"
                   type="string"
                   direct="true">
            <script type="text/javascript">
                //<!-- Begin
                /* This script and many more are available free online at
                 The JavaScript Source!! http://javascript.internet.com
                 Created by: Husay :: http://www.communitxt.net */
                var bCancel = false;
                function validateForm(form)
                {
                    if (bCancel) return true;
                    var bmpIndex = 0;
                    for (bmpIndex
                                 = 0; bmpIndex
                            < projectBmpArray.length; bmpIndex++)
                    {
                        if (projectBmpArray[bmpIndex].isDeleted
                                == 'false')
                        {
                            if (projectBmpArray[bmpIndex].bmpCategoryName
                                    == '')
                            {
                                $('#dialog').html("Project Inspection BMP Category is required.").dialog('open');
                                return false;
                            }
                            if (projectBmpArray[bmpIndex].bmpName
                                    == '')
                            {
                                $('#dialog').html("Project Inspection BMP Type is required.").dialog('open');
                                return false;
                            }
                            else
                            {
                                if (projectBmpArray[bmpIndex].bmpName.length
                                        > 500)
                                {
                                    $('#dialog').html("BMP name cannot exceed 500 characters.").dialog('open');
                                    return false;
                                }
                                if (projectBmpArray[bmpIndex].bmpDescription.length
                                        > 500)
                                {
                                    $('#dialog').html("BMP description cannot exceed 500 characters.").dialog('open');
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }// BMP JS
                var projectBmpArray = new Array(0);
                var bmpArray = new Array(0);
                var bmpCategoryArray = new Array(0);
                function ProjectBmp(id, projectBmpId, bmpName, bmpCategoryName, bmpDescription, isRequired, isDeleted)
                {
                    this.id
                            = id;
                    this.projectBmpId
                            = projectBmpId;
                    //  this.bmpId = bmpId;
                    //  this.bmpCategoryId = bmpCategoryId;
                    this.bmpName
                            = bmpName;
                    this.bmpCategoryName
                            = bmpCategoryName;
                    this.bmpDescription
                            = bmpDescription;
                    this.isRequired
                            = isRequired;
                    this.isDeleted
                            = isDeleted;
                }
                function Bmp(id, name, categoryId, categoryName, description)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                    this.categoryId
                            = categoryId;
                    this.categoryName
                            = categoryName;
                    this.description
                            = description;
                }
                function BmpCategory(id, name)
                {
                    this.id
                            = id;
                    this.name
                            = name;
                }
                function addToBmpCategoryArray(id, name)
                {
                    var found = 0;
                    var intI = 0;
                    for (intI
                                 = 0; intI
                            < bmpCategoryArray.length; intI++)
                    {
                        if (bmpCategoryArray[intI].name
                                == name)
                        {
                            found
                                    = 1;
                        }
                    }
                    if (found
                            == 0)
                    {
                        bmpCategoryArray.push(new BmpCategory(id,
                                                              name));
                    }
                }
                function initProjectBmpArray()
                {
                <% Iterator inspectionTemplateListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_PROJECT_INSPECTION_TEMPLATE_BMP_LIST)).iterator();
        int inspectionTemplateIndex = 0;
        while (inspectionTemplateListIterator.hasNext()) {
           ProjectBmpValue projectBmpValue = (ProjectBmpValue) inspectionTemplateListIterator.next(); %>
                    projectBmpArray.push(new ProjectBmp('<%= inspectionTemplateIndex++ %>',
                                                        '<%= projectBmpValue.getProjectBmpId() %>',
                                                        '<%= StringUtil.escapeSingleQuote(projectBmpValue.getBmpName()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(projectBmpValue.getBmpCategoryName()) %>',
                                                        '<%= StringUtil.escapeSingleQuote(projectBmpValue.getBmpDescription()) %>',
                                                        '<%= projectBmpValue.getIsRequiredText() %>',
                                                        '<%= projectBmpValue.getIsDeletedText() %>'));
                <% } %>
                }
                function initBmpReferenceArrays()
                {
                <% Iterator bmpListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT)).iterator();
        while (bmpListIterator.hasNext()) {
           EcBmp ecBmp = (EcBmp) bmpListIterator.next(); %>
                    addToBmpCategoryArray('<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getCategory().getName()) %>');
                    bmpArray.push(new Bmp('<%= ecBmp.getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getName()) %>',
                                          '<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getCategory().getName()) %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getDescription()) %>'));
                <% } %>
                }
                function getCategoryOptions(categoryName)
                {
                    var coInputHTML = "";
                    var intCO = 0;
                    coInputHTML
                            += "<option value=\"\">Select...</option>";
                    for (intCO
                                 = 0; intCO
                            < bmpCategoryArray.length; intCO++)
                    {
                        coInputHTML
                                += "<option "
                                           + ((bmpCategoryArray[intCO].name
                                == categoryName)
                                ? "SELECTED"
                                : "")
                                           + " value=\""
                                           + bmpCategoryArray[intCO].id
                                           + "\">"
                                           + entityify(bmpCategoryArray[intCO].name)
                                + "</option>";
                    }
                    return coInputHTML;
                }
                function getBmpOptions(bmpName, categoryName)
                {
                    var boInputHTML = "";
                    var intBO = 0;
                    boInputHTML
                            += "<option value=\"\">Select...</option>";
                    for (intBO
                                 = 0; intBO
                            < bmpArray.length; intBO++)
                    {
                        if (bmpArray[intBO].categoryName
                                == categoryName)
                        {
                            boInputHTML
                                    += "<option "
                                               + ((bmpArray[intBO].name
                                    == bmpName)
                                    ? "SELECTED"
                                    : "")
                                               + " value=\""
                                               + bmpArray[intBO].id
                                               + "\">"
                                               + entityify(bmpArray[intBO].name)
                                    + "</option>";
                        }
                    }
                    return boInputHTML;
                }
                function getBmpCategoryName(bmpCategoryId)
                {
                    var index = 0;
                    for (index
                                 = 0; index
                            < bmpCategoryArray.length; index++)
                    {
                        if (bmpCategoryArray[index].id
                                == bmpCategoryId)
                        {
                            return bmpCategoryArray[index].name;
                        }
                    }
                    return "";
                }
                function getBmpName(bmpId)
                {
                    var index = 0;
                    for (index
                                 = 0; index
                            < bmpArray.length; index++)
                    {
                        if (bmpArray[index].id
                                == bmpId)
                        {
                            return bmpArray[index].name;
                        }
                    }
                    return "";
                }
                function getBmpDescription(bmpId)
                {
                    var intBD = 0;
                    for (intBD
                                 = 0; intBD
                            < bmpArray.length; intBD++)
                    {
                        if (bmpArray[intBD].id
                                == bmpId)
                        {
                            return bmpArray[intBD].description;
                        }
                    }
                    return "";
                }
                function addBmpInput()
                {
                    projectBmpArray.push(new ProjectBmp(projectBmpArray.length,
                                                        '',
                                                        '',
                                                        '',
                                                        '',
                                                        'false',
                                                        'false'));
                    displayBmpList();
                }
                function isRequiredOnChange(id)
                {
                    if (document.getElementById('bmps.bmp'
                                                        + id
                                                        + '.isRequiredText').checked)
                    {
                        projectBmpArray[id].isRequired
                                = 'true';
                        document.getElementById('bmps.bmp'
                                                        + id
                                                        + '.isRequiredText').value
                                = 'true';
                    }
                    else
                    {
                        projectBmpArray[id].isRequired
                                = 'false';
                        document.getElementById('bmps.bmp'
                                                        + id
                                                        + '.isRequiredText').value
                                = 'false';
                    }
                }
                function categoryOnChange(id)
                {
                    projectBmpArray[id].bmpCategoryName
                            = getBmpCategoryName(document.getElementById('bmps.bmp'
                                                                                 + id
                                                                                 + '.bmpCategoryId').value);
                    projectBmpArray[id].bmpName
                            = '';
                    projectBmpArray[id].bmpDescription
                            = '';
                    displayBmpList();
                }
                function bmpOnChange(id)
                {
                    projectBmpArray[id].bmpName
                            = getBmpName(document.getElementById('bmps.bmp'
                                                                         + id
                                                                         + '.bmpId').value);
                    projectBmpArray[id].bmpDescription
                            = getBmpDescription(document.getElementById('bmps.bmp'
                                                                                + id
                                                                                + '.bmpId').value);
                    displayBmpList();
                }
                function bmpDescriptionOnChange(id)
                {
                    projectBmpArray[id].bmpDescription
                            = document.getElementById('bmps.bmp'
                                                              + id
                                                              + '.bmpDescription').value;
                }
                function deleteBmpInput(id)
                {
                    projectBmpArray[id].isDeleted
                            = 'true';
                    displayBmpList();
                }
                function getDeletedProjectBmps()
                {
                    var inputText = "";
                    var index = 0;
                    for (index
                                 = 0; index
                            < projectBmpArray.length; index++)
                    {
                        if (projectBmpArray[index].isDeleted
                                == 'true')
                        {
                            inputText
                                    += "<input type='hidden' id='bmps.bmp"
                                               + index
                                               + ".isDeletedText' name='bmps.bmp"
                                               + index
                                               + ".isDeletedText' value='true'/>"
                                               + "<input type='hidden' id='bmps.bmp"
                                               + index
                                               + ".projectBmpId' name='bmps.bmp"
                                               + index
                                               + ".projectBmpId' value='"
                                               + projectBmpArray[index].projectBmpId
                                    + "'/>";
                        }
                    }
                    return inputText;
                }
                function displayBmpList()
                {
                    var inputHTML = "";
                    if (projectBmpArray.length
                            > 0)
                    {
                        inputHTML
                                = "<table class='table table-hover table-condensed'><thead>"
                                          + "<tr><th>Required</th>"
                                          + "<th >Category/BMP</th>"
                                          + "<th >Description</th>"
                                + "<th >Options</th></tr></thead><tbody>";
                        for (var intI = 0; intI
                                < projectBmpArray.length; intI++)
                        {
                            if (projectBmpArray[intI].isDeleted
                                    != 'true')
                            {
                                if (projectBmpArray[intI].projectBmpId
                                        == '')
                                {
                                    inputHTML
                                            += createNewBmpInput(intI,
                                                                 projectBmpArray[intI]);
                                }
                                else
                                {
                                    inputHTML
                                            += createBmpInput(intI,
                                                              projectBmpArray[intI]);
                                }
                            }
                        }
                        inputHTML
                                += "</tbody></table>";
                        inputHTML
                                += getDeletedProjectBmps();
                    }
                    document.getElementById('projectBmpList').innerHTML
                            = inputHTML;
                }
                function createBmpInput(id, bmp)
                {
                    var inputString = "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".projectBmpId\" name=\"bmps.bmp"
                                              + id
                                              + ".projectBmpId\" value=\""
                                              + bmp.projectBmpId
                                              + "\" /><input type=\"checkbox\" "
                                              + ((bmp.isRequired
                            == 'true')
                            ? "checked"
                            : "")
                                              + " id=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" name=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" value=\""
                                              + bmp.isRequired
                                              + "\" onchange=\"javascript:isRequiredOnChange('"
                                              + id
                                              + "');\"/></td>"
                                              + "<td><table>"
                                              + "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryName\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryName\" value=\""
                                              + bmp.bmpCategoryName
                                              + "\" />"
                                              + entityify(bmp.bmpCategoryName)
                                              + "</td></tr>"
                                              + "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".bmpName\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpName\" value=\""
                                              + bmp.bmpName
                                              + "\" />"
                                              + entityify(bmp.bmpName)
                                              + "</td></tr>"
                                              + "</table></td>"
                                              + "<td ><textarea id=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" cols=\"40\" rows=\"3\" onchange=\"javascript:bmpDescriptionOnChange('"
                                              + id
                                              + "');\"/>"
                                              + bmp.bmpDescription
                                              + "</textarea></td>"
                                              + "<td><a class='btn btn-mini' href=\"javascript:deleteBmpInput('"
                                              + id
                            + "');\">Delete</a></td></tr>";
                    return inputString;
                }
                function createNewBmpInput(id, bmp)
                {
                    var inputString = "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".projectBmpId\" name=\"bmps.bmp"
                                              + id
                                              + ".projectBmpId\" value=\""
                                              + bmp.projectBmpId
                                              + "\" /><input type=\"checkbox\" "
                                              + ((bmp.isRequired
                            == 'true')
                            ? "checked"
                            : "")
                                              + " id=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" name=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" value=\""
                                              + bmp.isRequired
                                              + "\" onchange=\"javascript:isRequiredOnChange('"
                                              + id
                                              + "');\"/></td>"
                                              + "<td><table>"
                                              + "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryName\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryName\" value=\""
                                              + bmp.bmpCategoryName
                                              + "\" /><select id=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryId\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpCategoryId\" onchange=\"javascript:categoryOnChange('"
                                              + id
                                              + "');\">"
                                              + getCategoryOptions(bmp.bmpCategoryName)
                                              + "</select></td></tr>"
                                              + "<tr><td ><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".bmpName\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpName\" value=\""
                                              + bmp.bmpName
                                              + "\" /><select id=\"bmps.bmp"
                                              + id
                                              + ".bmpId\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpId\" onchange=\"javascript:bmpOnChange('"
                                              + id
                                              + "');\">"
                                              + getBmpOptions(bmp.bmpName,
                                                              bmp.bmpCategoryName)
                                              + "</select></td></tr>"
                                              + "</table></td>"
                                              + "<td ><textarea id=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" cols=\"40\" rows=\"3\" onchange=\"javascript:bmpDescriptionOnChange('"
                                              + id
                                              + "');\"/>"
                                              + bmp.bmpDescription
                                              + "</textarea></td>"
                                              + "<td  ><a href=\"javascript:deleteBmpInput('"
                                              + id
                            + "');\">Delete</a></td></tr>";
                    return inputString;
                }//Init BMP Arrays
                initBmpReferenceArrays();
                initProjectBmpArray();
                displayBmpList();// End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
