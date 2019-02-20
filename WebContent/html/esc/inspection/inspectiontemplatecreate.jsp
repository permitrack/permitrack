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
                 java.util.Iterator,
                 java.util.List" %>
<html:form styleClass="form-horizontal"
           action="/inspectiontemplatecreateaction"
           enctype="multipart/form-data"
           onsubmit="return validateForm(this);">
    <fieldset><legend>
        New <bean:message key="inspection.template.information.heading" />
    </legend></fieldset>
    <h4 class="myAccordian">
        Template Description
    </h4>
    <div>
    <div class="control-group">
        <label class="control-label">
            <bean:message key="inspection.template.name" />
        </label>
        <div class="controls">
            <input type="text"
                   id="name"
                   name="name"
                   size="50"
                   maxlength="50"
                   alt="Template Name" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><bean:message key="inspection.template.description" />
        </label>
        <div class="controls">
            <textarea id="description"
                      name="description"
                      cols="50"
                      rows="3"></textarea>
        </div>
    </div>
    </div>
    <h4 class="myAccordian">
        <bean:message key="inspection.template.bmp.heading" />
    </h4>
    <div>
    <div id="inspectionTemplateBmpList"></div>
    <a class="btn btn-success"
       href="javascript:addBmpInput();">
        + Add BMP
    </a>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <input class="btn btn-large"
                       type="submit"
                       name="org.apache.struts.taglib.html.CANCEL"
                       value="Cancel"
                       onclick="bCancel=true;" />
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
                var bCancel = false;//Validate the form on submit
                function validateForm(form)
                {
                    if (bCancel) return true;
                    if (document.getElementById("name").value
                                == null
                            || document.getElementById("name").value
                            == '')
                    {
                        $('#dialog').html("Template name is required.").dialog('open');
                        return false;
                    }
                    if (document.getElementById("name").value
                                != null
                            && document.getElementById("name").value.length
                            > 50)
                    {
                        $('#dialog').html("Template name must be less than 50 characters. The name has been truncated.").dialog('open');
                        document.getElementById("name").value
                                = document.getElementById("name").value.substr(0,
                                                                               50);
                        return false;
                    }
                    if (document.getElementById("description").value
                                != null
                            && document.getElementById("description").value.length
                            > 200)
                    {
                        $('#dialog').html("Template description must be less than 200 characters. The description has been truncated.").dialog('open');
                        document.getElementById("description").value
                                = document.getElementById("description").value.substr(0,
                                                                                      200);
                        return false;
                    }
                    var bmpIndex = 0;
                    for (bmpIndex
                                 = 0; bmpIndex
                            < inspectionTemplateBmpArray.length; bmpIndex++)
                    {
                        if (inspectionTemplateBmpArray[bmpIndex].isDeleted
                                == 'false')
                        {
                            if (inspectionTemplateBmpArray[bmpIndex].categoryId
                                    == '')
                            {
                                $('#dialog').html("Inspection BMP Category is required.").dialog('open');
                                return false;
                            }
                            if (inspectionTemplateBmpArray[bmpIndex].bmpId
                                    == '')
                            {
                                $('#dialog').html("Inspection BMP Type is required.").dialog('open');
                                return false;
                            }
                            if (inspectionTemplateBmpArray[bmpIndex].bmpDescription
                                        != null
                                    && inspectionTemplateBmpArray[bmpIndex].bmpDescription.length
                                    > 500)
                            {
                                $('#dialog').html("The description for BMP '"
                                                          + inspectionTemplateBmpArray[bmpIndex].bmpName
                                                          + "' must be less than 500 characters.  The description has been truncated.").dialog('open');
                                document.getElementById("bmps.bmp"
                                                                + bmpIndex
                                                                + ".bmpDescription").value
                                        = document.getElementById("bmps.bmp"
                                                                          + bmpIndex
                                                                          + ".bmpDescription").value.substr(1,
                                                                                                            500);
                                inspectionTemplateBmpArray[bmpIndex].bmpDescription
                                        = document.getElementById("bmps.bmp"
                                                                          + bmpIndex
                                                                          + ".bmpDescription").value;
                                return false;
                            }
                        }
                    }
                    return true;
                }// BMP JS
                var inspectionTemplateBmpArray = new Array(0);
                var bmpArray = new Array(0);
                var bmpCategoryArray = new Array(0);
                function InspectionTemplateBmp(id, inspectionTemplateBmpId, bmpId, categoryId, bmpName, bmpDescription, isRequired, isDeleted)
                {
                    this.id
                            = id;
                    this.inspectionTemplateBmpId
                            = inspectionTemplateBmpId;
                    this.bmpId
                            = bmpId;
                    this.categoryId
                            = categoryId;
                    this.bmpName
                            = bmpName;
                    this.bmpDescription
                            = bmpDescription;
                    this.isRequired
                            = isRequired;
                    this.isDeleted
                            = isDeleted;
                }
                function Bmp(id, categoryId, name, description)
                {
                    this.id
                            = id;
                    this.categoryId
                            = categoryId;
                    this.name
                            = name;
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
                        if (bmpCategoryArray[intI].id
                                == id)
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
                function initBmpReferenceArrays()
                {
                <% Iterator bmpListIterator = ((List) request.getSession().getAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT)).iterator();
        while (bmpListIterator.hasNext()) {
           EcBmp ecBmp = (EcBmp) bmpListIterator.next(); %>
                    addToBmpCategoryArray('<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getCategory().getName()) %>');
                    bmpArray.push(new Bmp('<%= ecBmp.getId() %>',
                                          '<%= ecBmp.getCategory().getId() %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getName()) %>',
                                          '<%= StringUtil.escapeSingleQuote(ecBmp.getDescription()) %>'));
                <% } %>
                }
                function getCategoryOptions(categoryId)
                {
                    var coInputHTML = "";
                    var intCO = 0;
                    coInputHTML
                            += "<option value=''>Select...</option>";
                    for (intCO
                                 = 0; intCO
                            < bmpCategoryArray.length; intCO++)
                    {
                        coInputHTML
                                += "<option "
                                           + ((bmpCategoryArray[intCO].id
                                == categoryId)
                                ? "SELECTED"
                                : "")
                                           + " value='"
                                           + bmpCategoryArray[intCO].id
                                           + "'>"
                                           + bmpCategoryArray[intCO].name
                                + "</option>";
                    }
                    return coInputHTML;
                }
                function getBmpOptions(bmpId, categoryId)
                {
                    var boInputHTML = "";
                    var intBO = 0;
                    boInputHTML
                            += "<option value=''>Select...</option>";
                    for (intBO
                                 = 0; intBO
                            < bmpArray.length; intBO++)
                    {
                        if (bmpArray[intBO].categoryId
                                == categoryId)
                        {
                            boInputHTML
                                    += "<option "
                                               + ((bmpArray[intBO].id
                                    == bmpId)
                                    ? "SELECTED"
                                    : "")
                                               + " value='"
                                               + bmpArray[intBO].id
                                               + "'>"
                                               + bmpArray[intBO].name
                                    + "</option>";
                        }
                    }
                    return boInputHTML;
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
                function getBmpName(bmpId)
                {
                    var intBD = 0;
                    for (intBD
                                 = 0; intBD
                            < bmpArray.length; intBD++)
                    {
                        if (bmpArray[intBD].id
                                == bmpId)
                        {
                            return bmpArray[intBD].name;
                        }
                    }
                    return "";
                }
                function addBmpInput()
                {
                    inspectionTemplateBmpArray.push(new InspectionTemplateBmp(inspectionTemplateBmpArray.length,
                                                                              '',
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
                        inspectionTemplateBmpArray[id].isRequired
                                = 'true';
                    }
                    else
                    {
                        inspectionTemplateBmpArray[id].isRequired
                                = 'false';
                    }
                }
                function categoryOnChange(id)
                {
                    inspectionTemplateBmpArray[id].categoryId
                            = document.getElementById('bmps.bmp'
                                                              + id
                                                              + '.categoryId').value;
                    inspectionTemplateBmpArray[id].bmpId
                            = '';
                    inspectionTemplateBmpArray[id].bmpDescription
                            = '';
                    displayBmpList();
                }
                function bmpOnChange(id)
                {
                    inspectionTemplateBmpArray[id].bmpId
                            = document.getElementById('bmps.bmp'
                                                              + id
                                                              + '.bmpId').value;
                    inspectionTemplateBmpArray[id].bmpDescription
                            = getBmpDescription(inspectionTemplateBmpArray[id].bmpId);
                    inspectionTemplateBmpArray[id].bmpName
                            = getBmpName(inspectionTemplateBmpArray[id].bmpId);
                    displayBmpList();
                }
                function bmpDescriptionOnChange(id)
                {
                    inspectionTemplateBmpArray[id].bmpDescription
                            = document.getElementById('bmps.bmp'
                                                              + id
                                                              + '.bmpDescription').value;
                }
                function deleteBmpInput(id)
                {
                    inspectionTemplateBmpArray.splice(id,
                                                      1);
                    displayBmpList();
                }
                function displayBmpList()
                {
                    var inputHTML = "";
                    if (inspectionTemplateBmpArray.length
                            > 0)
                    {
                        inputHTML
                                = "<table class='table table-condensed'><thead>"
                                          + "<tr><th>Required</th>"
                                          + "<th>Category/BMP</th>"
                                          + "<th>Description</th>"
                                + "<th></th></tr></thead><tbody>";
                        for (var intI = 0; intI
                                < inspectionTemplateBmpArray.length; intI++)
                        {
                            inputHTML
                                    += createBmpInput(intI,
                                                      inspectionTemplateBmpArray[intI]);
                        }
                        inputHTML
                                += "</tbody></table>";
                    }
                    document.getElementById('inspectionTemplateBmpList').innerHTML
                            = inputHTML;
                    applyUI("inspectionTemplateBmpList",
                            "")
                }
                function createBmpInput(id, bmp)
                {
                    var inputString = "<tr><td><input type=\"hidden\" id=\"bmps.bmp"
                                              + id
                                              + ".id\" name=\"bmps.bmp"
                                              + id
                                              + ".id\" value=\""
                                              + bmp.inspectionTemplateBmpId
                                              + "\" /><input type=\"checkbox\" "
                                              + ((bmp.isRequired
                            == 'true')
                            ? "checked"
                            : "")
                                              + " id=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" name=\"bmps.bmp"
                                              + id
                                              + ".isRequiredText\" value=\"true\" onchange=\"javascript:isRequiredOnChange('"
                                              + id
                                              + "');\"/></td>"
                                              + "<td>"
                                              + "<p><select id=\"bmps.bmp"
                                              + id
                                              + ".categoryId\" name=\"bmps.bmp"
                                              + id
                                              + ".categoryId\" onchange=\"javascript:categoryOnChange('"
                                              + id
                                              + "');\">"
                                              + getCategoryOptions(bmp.categoryId)
                                              + "</select></p>"
                                              + "<select id=\"bmps.bmp"
                                              + id
                                              + ".bmpId\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpId\" onchange=\"javascript:bmpOnChange('"
                                              + id
                                              + "');\">"
                                              + getBmpOptions(bmp.bmpId,
                                                              bmp.categoryId)
                                              + "</select>"
                                              + "</td>"
                                              + "<td><textarea id=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" name=\"bmps.bmp"
                                              + id
                                              + ".bmpDescription\" cols=\"150\" rows=\"2\" onchange=\"javascript:bmpDescriptionOnChange('"
                                              + id
                                              + "');\"/>"
                                              + bmp.bmpDescription
                                              + "</textarea></td>"
                                              + "<td><a class='btn btn-mini' href=\"javascript:deleteBmpInput('"
                                              + id
                            + "');\">Delete</a></td></tr>";
                    return inputString;
                }
                /*
                 Init BMP Arrays
                 */
                initBmpReferenceArrays();
                displayBmpList();// End -->
            </script>
        </tiles:put>
    </tiles:definition>
</html:form>
