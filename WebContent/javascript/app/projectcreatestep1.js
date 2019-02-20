var bCancel = false;
function checkCanComplete()
{
    var blnShowError = false;
    var blnReturn = true;
    // Check for GPS Coordinates
    // If status Active or Auto Activate
    if (document.getElementById('statusCode').value
                == '1'
            || document.getElementById('statusCode').value
            == '7')
    {

        // If no GPS coordinates
        if (document.getElementById("gisX").value
                    == null
                || document.getElementById("gisX").value.length
                == 0)
        {
            blnShowError
                    = true;
        }
        if (document.getElementById("gisY").value
                    == null
                || document.getElementById("gisY").value.length
                == 0)
        {
            blnShowError
                    = true;
        }
    }
    if (blnShowError)
    {
        blnReturn
                = window.confirm("A project must have GPS coordinates in order to appear on the interactive map. Click 'Cancel' to enter GPS coordinates for this project, or click 'OK' to proceed without GPS coordinates.")
    }
    return blnReturn;
}
function DateValidations()
{
    this.aa
            = new Array("startDateString",
                        "Start Date is not a valid date. (mm/dd/yyyy)",
                        new Function("varName",
                                     "this.datePatternStrict='MM/dd/yyyy';  return this[varName];"));
    this.ab
            = new Array("effectiveDateString",
                        "Effective Date is not a valid date. (mm/dd/yyyy)",
                        new Function("varName",
                                     "this.datePatternStrict='MM/dd/yyyy';  return this[varName];"));
    this.ac
            = new Array("seedDateString",
                        "Seed Date is not a valid date. (mm/dd/yyyy)",
                        new Function("varName",
                                     "this.datePatternStrict='MM/dd/yyyy';  return this[varName];"));
}
function FloatValidations()
{
    this.aa
            = new Array("gisX",
                        "Latitude must be a decimal number.",
                        new Function("varName",
                                     " return this[varName];"));
    this.ab
            = new Array("gisY",
                        "Longitude must be a decimal number.",
                        new Function("varName",
                                     " return this[varName];"));
    this.ac
            = new Array("totalSiteArea",
                        "Total Site Area must be a decimal number.",
                        new Function("varName",
                                     " return this[varName];"));
    this.ad
            = new Array("disturbedArea",
                        "Disturbed Area must be a decimal number.",
                        new Function("varName",
                                     " return this[varName];"));
    this.ae
            = new Array("newImperviousArea",
                        "New Impervious Area must be a decimal number.",
                        new Function("varName",
                                     " return this[varName];"));
}
function validateDate(form)
{
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oDate
            = new DateValidations();
    for (x in oDate)
    {
        var value = form[oDate[x][0]].value;
        var datePattern = oDate[x][2]("datePatternStrict");
        if ((form[oDate[x][0]].type
                     == 'text'
                || form[oDate[x][0]].type
                == 'textarea')
                    && (value.length
                > 0)
                && (datePattern.length
                > 0))
        {
            var MONTH = "MM";
            var DAY = "dd";
            var YEAR = "yyyy";
            var orderMonth = datePattern.indexOf(MONTH);
            var orderDay = datePattern.indexOf(DAY);
            var orderYear = datePattern.indexOf(YEAR);
            if ((orderDay
                         < orderYear
                    && orderDay
                    > orderMonth))
            {
                var iDelim1 = orderMonth
                        + MONTH.length;
                var iDelim2 = orderDay
                        + DAY.length;
                var delim1 = datePattern.substring(iDelim1,
                                                   iDelim1
                                                           + 1);
                var delim2 = datePattern.substring(iDelim2,
                                                   iDelim2
                                                           + 1);
                if (iDelim1
                            == orderDay
                        && iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                }
                else if (iDelim1
                        == orderDay)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})(\\d{2})["
                                                 + delim2
                                                 + "](\\d{4})$");
                }
                else if (iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})["
                                                 + delim1
                                                 + "](\\d{2})(\\d{4})$");
                }
                else
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})["
                                                 + delim1
                                                 + "](\\d{2})["
                                                 + delim2
                                                 + "](\\d{4})$");
                }
                var matched = dateRegexp.exec(value);
                if (matched
                        != null)
                {
                    if (!isValidDate(matched[2],
                                     matched[1],
                                     matched[3]))
                    {
                        if (i
                                == 0)
                        {
                            focusField
                                    = form[oDate[x][0]];
                        }
                        fields[i++]
                                = oDate[x][1];
                        bValid
                                = false;
                    }
                }
                else
                {
                    if (i
                            == 0)
                    {
                        focusField
                                = form[oDate[x][0]];
                    }
                    fields[i++]
                            = oDate[x][1];
                    bValid
                            = false;
                }
            }
            else if ((orderMonth
                              < orderYear
                    && orderMonth
                    > orderDay))
            {
                var iDelim1 = orderDay
                        + DAY.length;
                var iDelim2 = orderMonth
                        + MONTH.length;
                var delim1 = datePattern.substring(iDelim1,
                                                   iDelim1
                                                           + 1);
                var delim2 = datePattern.substring(iDelim2,
                                                   iDelim2
                                                           + 1);
                if (iDelim1
                            == orderMonth
                        && iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                }
                else if (iDelim1
                        == orderMonth)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})(\\d{2})["
                                                 + delim2
                                                 + "](\\d{4})$");
                }
                else if (iDelim2
                        == orderYear)
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})["
                                                 + delim1
                                                 + "](\\d{2})(\\d{4})$");
                }
                else
                {
                    dateRegexp
                            = new RegExp("^(\\d{2})["
                                                 + delim1
                                                 + "](\\d{2})["
                                                 + delim2
                                                 + "](\\d{4})$");
                }
                var matched = dateRegexp.exec(value);
                if (matched
                        != null)
                {
                    if (!isValidDate(matched[1],
                                     matched[2],
                                     matched[3]))
                    {
                        if (i
                                == 0)
                        {
                            focusField
                                    = form[oDate[x][0]];
                        }
                        fields[i++]
                                = oDate[x][1];
                        bValid
                                = false;
                    }
                }
                else
                {
                    if (i
                            == 0)
                    {
                        focusField
                                = form[oDate[x][0]];
                    }
                    fields[i++]
                            = oDate[x][1];
                    bValid
                            = false;
                }
            }
            else if ((orderMonth
                              > orderYear
                    && orderMonth
                    < orderDay))
            {
                var iDelim1 = orderYear
                        + YEAR.length;
                var iDelim2 = orderMonth
                        + MONTH.length;
                var delim1 = datePattern.substring(iDelim1,
                                                   iDelim1
                                                           + 1);
                var delim2 = datePattern.substring(iDelim2,
                                                   iDelim2
                                                           + 1);
                if (iDelim1
                            == orderMonth
                        && iDelim2
                        == orderDay)
                {
                    dateRegexp
                            = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                }
                else if (iDelim1
                        == orderMonth)
                {
                    dateRegexp
                            = new RegExp("^(\\d{4})(\\d{2})["
                                                 + delim2
                                                 + "](\\d{2})$");
                }
                else if (iDelim2
                        == orderDay)
                {
                    dateRegexp
                            = new RegExp("^(\\d{4})["
                                                 + delim1
                                                 + "](\\d{2})(\\d{2})$");
                }
                else
                {
                    dateRegexp
                            = new RegExp("^(\\d{4})["
                                                 + delim1
                                                 + "](\\d{2})["
                                                 + delim2
                                                 + "](\\d{2})$");
                }
                var matched = dateRegexp.exec(value);
                if (matched
                        != null)
                {
                    if (!isValidDate(matched[3],
                                     matched[2],
                                     matched[1]))
                    {
                        if (i
                                == 0)
                        {
                            focusField
                                    = form[oDate[x][0]];
                        }
                        fields[i++]
                                = oDate[x][1];
                        bValid
                                = false;
                    }
                }
                else
                {
                    if (i
                            == 0)
                    {
                        focusField
                                = form[oDate[x][0]];
                    }
                    fields[i++]
                            = oDate[x][1];
                    bValid
                            = false;
                }
            }
            else
            {
                if (i
                        == 0)
                {
                    focusField
                            = form[oDate[x][0]];
                }
                fields[i++]
                        = oDate[x][1];
                bValid
                        = false;
            }
        }
    }
    if (fields.length
            > 0)
    {
        focusField.focus();
        $('#dialog').html(fields.join('\n')).dialog('open');
    }
    return bValid;
}
function isValidDate(day, month, year)
{
    if (month
                < 1
            || month
            > 12)
    {
        return false;
    }
    if (day
                < 1
            || day
            > 31)
    {
        return false;
    }
    if ((month
                 == 4
                 || month
            == 6
                 || month
            == 9
            || month
            == 11)
            && (day
            == 31))
    {
        return false;
    }
    if (month
            == 2)
    {
        var leap = (year
                            % 4
                            == 0
                && (year
                            % 100
                            != 0
                || year
                           % 400
                == 0));
        if (day
                    > 29
                || (day
                            == 29
                && !leap))
        {
            return false;
        }
    }
    return true;
}
function validateFloat(form)
{
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oFloat
            = new FloatValidations();
    for (x in oFloat)
    {
        var field = form[oFloat[x][0]];
        if (field.type
                    == 'text'
                    || field.type
                == 'textarea'
                    || field.type
                == 'select-one'
                || field.type
                == 'radio')
        {
            var value = '';
            // get field's value
            if (field.type
                    == "select-one")
            {
                var si = field.selectedIndex;
                if (si
                        >= 0)
                {
                    value
                            = field.options[si].value;
                }
            }
            else
            {
                value
                        = field.value;
            }
            if (value.length
                    > 0)
            {
                // remove '.' before checking digits
                var tempArray = value.split('.');
                var joinedString = tempArray.join('');
                if (!isAllDigits(joinedString))
                {
                    bValid
                            = false;
                    if (i
                            == 0)
                    {
                        focusField
                                = field;
                    }
                    fields[i++]
                            = oFloat[x][1];
                }
                else
                {
                    var iValue = parseFloat(value);
                    if (isNaN(iValue))
                    {
                        if (i
                                == 0)
                        {
                            focusField
                                    = field;
                        }
                        fields[i++]
                                = oFloat[x][1];
                        bValid
                                = false;
                    }
                }
            }
        }
    }
    if (fields.length
            > 0)
    {
        focusField.focus();
        $('#dialog').html(fields.join('\n')).dialog('open');
    }
    return bValid;
}
function isAllDigits(argvalue)
{
    argvalue
            = argvalue.toString();
    var validChars = "0123456789";
    var startFrom = 0;
    if (argvalue.substring(0,
                           2)
            == "0x")
    {
        validChars
                = "0123456789abcdefABCDEF";
        startFrom
                = 2;
    }
    else if (argvalue.charAt(0)
            == "0")
    {
        startFrom
                = 1;
    }
    else if (argvalue.charAt(0)
            == "-")
    {
        startFrom
                = 1;
    }
    for (var n = startFrom; n
            < argvalue.length; n++)
    {
        if (validChars.indexOf(argvalue.substring(n,
                                                  n
                                                          + 1))
                == -1)
        {
            return false;
        }
    }
    return true;
}
function validateForm(form)
{
    if (bCancel)
    {
        return true;
    }
    if (document.getElementById("projectTypeId").value
                == null
            || document.getElementById("projectTypeId").value
            == '')
    {
        $('#dialog').html("Project Type is required").dialog('open');
        return false;
    }
    if (document.getElementById("name").value
                == null
            || document.getElementById("name").value
            == '')
    {
        $('#dialog').html("Project Name is required").dialog('open');
        return false;
    }
    else if (document.getElementById("name").value.length
            > 500)
    {
        $('#dialog').html("Project Name cannot exceed 500 characters").dialog('open');
        return false;
    }
    if (document.getElementById("permitNumber").value
                == null
            || document.getElementById("permitNumber").value
            == '')
    {
        $('#dialog').html("Permit Number is required").dialog('open');
        return false;
    }
    if (document.getElementById("permitAuthorityClientId").value
                == null
                || document.getElementById("permitAuthorityClientId").value
            == ''
            || document.getElementById("permitAuthorityClientId").value
            == '0')
    {
        $('#dialog').html("Permit Authority is required").dialog('open');
        return false;
    }
    if (document.getElementById("permittedClientId").value
                == null
                || document.getElementById("permittedClientId").value
            == ''
            || document.getElementById("permittedClientId").value
            == '0')
    {
        $('#dialog').html("Permittee is required").dialog('open');
        return false;
    }
    if (!validateDate(form))
    {
        return false;
    }
    if (!validateFloat(form))
    {
        return false;
    }
    if ((document.getElementById("gisX").value
                 == null
            || document.getElementById("gisX").value.length
            == 0)
                && document.getElementById("gisY").value
            != null
            && document.getElementById("gisY").value.length
            > 0)
    {
        $('#dialog').html("Longitude must be entered.").dialog('open');
        return false;
    }
    if ((document.getElementById("gisY").value
                 == null
            || document.getElementById("gisY").value.length
            == 0)
                && document.getElementById("gisX").value
            != null
            && document.getElementById("gisX").value.length
            > 0)
    {
        $('#dialog').html("Latitude must be entered.").dialog('open');
        return false;
    }
    // Regular expression to ensure that name and email data is not blank.
    var name_regex = /[A-Z]/i;
    // Regular expression to check email format.  This expression matches all emails with a top level domain of 2 to 4 characters.
    // It also matches multiple domains.
    // Ex: jake@server.department.company.com --> match
    // Ex: jake@company...com --> No match, due to multiple periods
    var email_regex = /^[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,4}$/i;
    // Check all dynamically added project contacts
    for (var x = 0; x
            <= contactAdd
            - 1; x++)
    {
        // Ensure that the added contact index still exists and was not removed by the user
        if (document.getElementById("contacts.contact"
                                            + x
                                            + ".contactId")
                != null)
        {

            // Ensure that the contactId (dropdown) is set to "Add New Contact" or a contact name
            if (document.getElementById("contacts.contact"
                                                + x
                                                + ".contactId").value
                    != "")
            {

                // Ensure contact names and email are not all whitespace
                if (!(document.getElementById("contacts.contact"
                                                      + x
                                                      + ".firstName").value.match(name_regex)
                              && document.getElementById("contacts.contact"
                                                                 + x
                                                                 + ".lastName").value.match(name_regex)
                        && document.getElementById("contacts.contact"
                                                           + x
                                                           + ".email").value.match(name_regex) ))
                {
                    $('#dialog').html("Contact name and email address fields are required.").dialog('open');
                    return false;
                }
                if (!(document.getElementById("contacts.contact"
                                                      + x
                                                      + ".organizationName").value.match(name_regex))
                        && x
                        > 2)
                {
                    $('#dialog').html("Contact organization field is required.").dialog('open');
                    return false;
                }
            }
            if (!( document.getElementById("contacts.contact"
                                                   + x
                                                   + ".email").value.match(email_regex) ))
            {
                if (document.getElementById("contacts.contact"
                                                    + x
                                                    + ".contactId").value
                        != "")
                {
                    $('#dialog').html("Email address '"
                                              + document.getElementById("contacts.contact"
                                                                                + x
                                                                                + ".email").value
                                              + "' is not valid.").dialog('open');
                    return false;
                }
            }
        }
    }
    return true;
}
function Lookup_Coordinates_OnClick()
{
    if (document.getElementById("parcelNumber").value
                == null
            || document.getElementById("parcelNumber").value.length
            == 0)
    {
        $('#dialog').html("Parcel Number is required for coordinate lookup.").dialog('open');
        return false;
    }
    if (window.confirm("This project must be saved before you can continue.  Is it OK to save this project now?  Click 'Cancel' if you do not want to save this project."))
    {
        return validateForm(document.projectForm);
    }
    else
    {
        return false;
    }
}
function latitudeOnChange()
{
    if (document.getElementById("gisY").value
                != null
            && document.getElementById("gisY").value.length
            > 0)
    {
        if (validateFloat(document.projectForm))
        {
/*
            document.getElementById("gisY").value
                    = Math.abs(document.getElementById("gisY").value
                                       - 0.0)
                    % 90;
*/
        }
    }
    return true;
}
function longitudeOnChange()
{
    if (document.getElementById("gisX").value
                != null
            && document.getElementById("gisX").value.length
            > 0)
    {
        if (validateFloat(document.projectForm))
        {
/*
            document.getElementById("gisX").value
                    = (Math.abs(document.getElementById("gisX").value
                                        - 0.0)
                    % 180)
                    * (-1.0);
*/
        }
    }
    return true;
}
function addDocumentInput()
{
    var index = 0;
    for (index
                 = 0; index
            < 4; index++)
    {
        projectDocumentArray.push(new ProjectDocument(projectDocumentArray.length,
                                                      '',
                                                      '',
                                                      '',
                                                      '',
                                                      '',
                                                      'false'));
    }
}
function deleteDocumentInput(id)
{
    projectDocumentArray[id].isDeleted
            = 'true';
    displayDocumentList();
}
function documentFileOnChange(id)
{
    projectDocumentArray[id].fileName
            = document.getElementById('documents.document'
                                              + id
                                              + '.formFile').value;
}
function documentCommentOnChange(id)
{
    projectDocumentArray[id].comment
            = document.getElementById('documents.document'
                                              + id
                                              + '.comment').value;
}
function displayDocumentList()
{
    var inputHTML = "";
    var index = 0;
    if (projectDocumentArray.length
            > 0)
    {
        inputHTML
                = "<table class='table table-condensed'><thead><tr>"
                          + "<th>Document</th>"
                + "<th>Comment</th></tr></thead><tbody>";
        for (index
                     = 0; index
                < 4; index++)
        {
            if (projectDocumentArray[index].isDeleted
                    != 'true')
            {
                inputHTML
                        += createDocumentInput(index,
                                               projectDocumentArray[index]);
            }
        }
        inputHTML
                += "</tbody></table>";
    }
    document.getElementById('projectDocumentList').innerHTML
            = inputHTML;
}
function createDocumentInput(id, projectDocument)
{
    return "<tr><td><input type='hidden' id='documents.document"
                   + id
                   + ".id' name='documents.document"
                   + id
                   + ".id' value='"
                   + projectDocument.documentId
                   + "' />"
                   + ((projectDocument.documentId
            == '')
            ? "<input type='file' id='documents.document"
                      + id
                      + ".formFile' name='documents.document"
                      + id
                      + ".formFile' size='50' value='"
                      + projectDocument.fileName
                      + "' onchange=\"return documentFileOnChange('"
                      + id
            + "');\"/>"
            : "<a href='"
                      + projectDocument.downloadURL
                      + "'>"
                      + projectDocument.fileName
            + "</a>")
                   + "</td>"
                   + "<td >"
                   + ((projectDocument.documentId
            == '')
            ? "<input type='text' id='documents.document"
                      + id
                      + ".comment' name='documents.document"
                      + id
                      + ".comment' size='30' maxlength='50' value='"
                      + projectDocument.comment
                      + "' onchange=\"return documentCommentOnChange('"
                      + id
            + "');\"/>"
            : projectDocument.comment)
            + "</td></tr>";
}
var internalProjectContactArray = new Array(0);
var projectContactArray = new Array(0);
var clientContactArray = new Array(0);
var contactTypeArray = new Array(0);
var contactOrgArray = new Array(0);
var stateArray = new Array(0);
var contactAdd = 3;// CONTACT JS
function Contact(id, organizationId, orgRefClientId, organizationName, firstName, lastName, address, city, stateCode, zipCode, phone, email, addressId)
{
    this.id
            = id;
    this.organizationId
            = organizationId;
    this.orgRefClientId
            = orgRefClientId;
    this.organizationName
            = organizationName;
    this.firstName
            = firstName;
    this.lastName
            = lastName;
    this.address
            = address;
    this.city
            = city;
    this.stateCode
            = stateCode;
    this.zipCode
            = zipCode;
    this.phone
            = phone;
    this.email
            = email;
    this.addressId
            = addressId;
}
function ProjectContact(id, projectContactId, contactTypeId, contactTypeCode, contactTypeIsInternal, contactId, organizationId, orgRefClientId, organizationName, firstName, lastName, address, city, stateCode, zipCode, phone, email, addressId, isDeleted)
{
    this.id
            = id;
    this.projectContactId
            = projectContactId;
    this.contactTypeId
            = contactTypeId;
    this.contactTypeCode
            = contactTypeCode;
    this.cotnactTypeIsInternal
            = contactTypeIsInternal;
    this.isDeleted
            = isDeleted;
    this.contact
            = new Contact(contactId,
                          organizationId,
                          orgRefClientId,
                          organizationName,
                          firstName,
                          lastName,
                          address,
                          city,
                          stateCode,
                          zipCode,
                          phone,
                          email,
                          addressId);
}
function ContactOrg(id, refClientId, name)
{
    this.id
            = id;
    this.refClientId
            = refClientId;
    this.name
            = name;
}
function ContactType(id, name, code, isInternal)
{
    this.id
            = id;
    this.name
            = name;
    this.code
            = code;
    this.isInternal
            = isInternal;
}
function State(id, code, name, countryName)
{
    this.id
            = id;
    this.code
            = code;
    this.name
            = name;
    this.countryName
            = countryName;
}
function isExistingProjectContact(index)
{
    return projectContactArray[index].projectContactId
                   != null
                   && projectContactArray[index].projectContactId
            != ''
            && projectContactArray[index].projectContactId
            > 0;
}
function sortContactOrgArray()
{
    var rest = contactOrgArray.length
            - 1;
    var start = 0;
    for (var i = rest
            - 1; i
            >= start; i--)
    {
        for (var j = start; j
                <= i; j++)
        {
            if (contactOrgArray[j
                    + 1].name
                    < contactOrgArray[j].name)
            {
                var tempValue = contactOrgArray[j];
                contactOrgArray[j]
                        = contactOrgArray[j
                        + 1];
                contactOrgArray[j
                        + 1]
                        = tempValue;
            }
        }
    }
}
function addContactOrg(contactOrg)
{
    var index = 0;
    for (index
                 = 0; index
            < contactOrgArray.length; index++)
    {
        if (contactOrgArray[index].id
                == contactOrg.id)
        {
            return;
        }
    }
    contactOrgArray.push(contactOrg);
}
function getContactOrg(orgId)
{
    var index = 0;
    for (index
                 = 0; index
            < contactOrgArray.length; index++)
    {
        if (contactOrgArray[index].id
                == orgId)
        {
            return contactOrgArray[index];
        }
    }
    return new ContactOrg(0,
                          '',
                          '');
}
function getContactOrgByRefClientId(clientId)
{
    var index = 0;
    for (index
                 = 0; index
            < contactOrgArray.length; index++)
    {
        if (contactOrgArray[index].refClientId
                == clientId)
        {
            return contactOrgArray[index];
        }
    }
    return new ContactOrg('-1',
                          '',
                          '');
}
function getContactTypeOptions(contactTypeId)
{
    var inputHTML = "";
    var index = 0;
    inputHTML
            += "<option value=''>Select...</option>";
    for (index
                 = 0; index
            < contactTypeArray.length; index++)
    {
        inputHTML
                += "<option "
                           + ((contactTypeArray[index].id
                == contactTypeId)
                ? "selected='selected'"
                : "")
                           + " value='"
                           + contactTypeArray[index].id
                           + "'>"
                           + contactTypeArray[index].name
                + "</option>";
    }
    return inputHTML;
}
function getContactOrgOptions(contactOrgId)
{
    var inputHTML = "";
    var index = 0;
    inputHTML
            += "<option value=''>Select...</option>";
    inputHTML
            += "<option value='-1'>Add New Contact</option>";
    for (index
                 = 0; index
            < contactOrgArray.length; index++)
    {
        inputHTML
                += "<option "
                           + ((contactOrgArray[index].id
                == contactOrgId)
                ? "selected='selected'"
                : "")
                           + " value='"
                           + contactOrgArray[index].id
                           + "'>"
                           + contactOrgArray[index].name
                + "</option>";
    }
    return inputHTML;
}
function getContactOptions(contactId, contactOrgId)
{
    var inputHTML = "";
    var index = 0;
    inputHTML
            += "<option value=''>Select...</option>";
    if (contactOrgId
            != '')
    {
        inputHTML
                += "<option value='-1'>Add New Contact</option>";
        for (index
                     = 0; index
                < clientContactArray.length; index++)
        {
            if (clientContactArray[index].organizationId
                    == contactOrgId)
            {
                inputHTML
                        += "<option "
                                   + ((clientContactArray[index].id
                        == contactId)
                        ? "selected='selected'"
                        : "")
                                   + " value='"
                                   + clientContactArray[index].id
                                   + "'>"
                                   + clientContactArray[index].lastName
                                   + ", "
                                   + clientContactArray[index].firstName
                        + "</option>";
            }
        }
    }
    return inputHTML;
}
function getStateOptions(stateCode)
{
    var inputHTML = "";
    var index = 0;
    inputHTML
            += "<option value=''>Select...</option>";
    for (index
                 = 0; index
            < stateArray.length; index++)
    {
        inputHTML
                += "<option "
                           + ((stateArray[index].code
                == stateCode)
                ? "selected='selected'"
                : "")
                           + " value='"
                           + stateArray[index].code
                           + "'>"
                           + stateArray[index].code
                + "</option>";
    }
    return inputHTML;
}
function getClientContact(contactId)
{
    var index = 0;
    for (index
                 = 0; index
            < clientContactArray.length; index++)
    {
        if (clientContactArray[index].id
                == contactId)
        {
            return clientContactArray[index];
        }
    }
    return new Contact('',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '',
                       '');
}
function contactTypeOnChange(index)
{
    projectContactArray[index].contactTypeId
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.contactTypeId').value;
}
function contactOrgOnChange(index)
{
    projectContactArray[index].contact.organizationId
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.organizationId').value;
    if (projectContactArray[index].contact.organizationId
            == '-1')
    {
        projectContactArray[index].contact.organizationName
                = '';
        projectContactArray[index].contact.organizationId
                = '-1';
        projectContactArray[index].contact.id
                = '-1';
        createContactInput(index,
                           projectContactArray[index]);
    }
    else
    {
        var contactOrg = getContactOrg(projectContactArray[index].contact.organizationId);
        projectContactArray[index].contact.organizationName
                = contactOrg.name;
        projectContactArray[index].contact.organizationId
                = contactOrg.id;
        projectContactArray[index].contact.orgRefClientId
                = contactOrg.refClientId;
        projectContactArray[index].contact.id
                = '';
        projectContactArray[index].contact.firstName
                = '';
        projectContactArray[index].contact.lastName
                = '';
        projectContactArray[index].contact.address
                = '';
        projectContactArray[index].contact.city
                = '';
        projectContactArray[index].contact.stateCode
                = '';
        projectContactArray[index].contact.zipCode
                = '';
        projectContactArray[index].contact.phone
                = '';
        projectContactArray[index].contact.email
                = '';
        projectContactArray[index].contact.addressId
                = '';
        createContactInput(index,
                           projectContactArray[index]);
    }
}
function contactOnChange(index)
{
    projectContactArray[index].contact.id
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.contactId').value;
    if (projectContactArray[index].contact.id
            == '-1')
    {
        projectContactArray[index].contact.firstName
                = '';
        projectContactArray[index].contact.lastName
                = '';
        projectContactArray[index].contact.address
                = '';
        projectContactArray[index].contact.city
                = '';
        projectContactArray[index].contact.stateCode
                = '';
        projectContactArray[index].contact.zipCode
                = '';
        projectContactArray[index].contact.phone
                = '';
        projectContactArray[index].contact.email
                = '';
        projectContactArray[index].contact.addressId
                = '';
        createContactInput(index,
                           projectContactArray[index]);
    }
    else
    {
        var contact = getClientContact(projectContactArray[index].contact.id);
        projectContactArray[index].contact.firstName
                = contact.firstName;
        projectContactArray[index].contact.lastName
                = contact.lastName;
        projectContactArray[index].contact.address
                = contact.address;
        projectContactArray[index].contact.city
                = contact.city;
        projectContactArray[index].contact.stateCode
                = contact.stateCode;
        projectContactArray[index].contact.zipCode
                = contact.zipCode;
        projectContactArray[index].contact.phone
                = contact.phone;
        projectContactArray[index].contact.email
                = contact.email;
        projectContactArray[index].contact.addressId
                = contact.addressId;
        createContactInput(index,
                           projectContactArray[index]);
    }
}
function contactOrgNameOnChange(index)
{
    //  projectContactArray[id].contact.organizationId = document.getElementById('contacts.contact' + id + '.organizationId').value;
    projectContactArray[index].contact.organizationName
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.organizationName').value;
}
function contactFirstNameOnChange(index)
{
    projectContactArray[index].contact.firstName
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.firstName').value;
}
function contactLastNameOnChange(index)
{
    projectContactArray[index].contact.lastName
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.lastName').value;
}
function contactAddressOnChange(index)
{
    projectContactArray[index].contact.address
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.address').value;
}
function contactCityOnChange(index)
{
    projectContactArray[index].contact.city
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.city').value;
}
function contactStateOnChange(index)
{
    projectContactArray[index].contact.stateCode
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.stateCode').value;
}
function contactZipCodeOnChange(index)
{
    projectContactArray[index].contact.zipCode
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.zip').value;
}
function contactPhoneOnChange(index)
{
    projectContactArray[index].contact.phone
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.primaryPhone').value;
}
function contactEmailOnChange(index)
{
    projectContactArray[index].contact.email
            = document.getElementById('contacts.contact'
                                              + projectContactArray[index].id
                                              + '.email').value;
}
function addContactInput()
{
    if (contactAdd
            < 25)
    {
        projectContactArray.push(new ProjectContact('',
                                                    '',
                                                    '',
                                                    '',
                                                    'false',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    '',
                                                    'false'));
        var newIndex = projectContactArray.length
                - 1;
        projectContactArray[newIndex].id
                = contactAdd++;
        createContactInput(newIndex,
                           projectContactArray[newIndex]);
    }
    else
    {
        $('#dialog').html("You cannot have more than 25 contacts on this project.").dialog('open');
    }
}
function deleteContactInput(index)
{
    projectContactArray[index].isDeleted
            = 'true';
    document.getElementById('iContact'
                                    + projectContactArray[index].id).innerHTML
            = '';
    document.getElementById('iContact'
                                    + projectContactArray[index].id).style.display
            = 'none';
    buildDeletedList();
}
function buildDeletedList()
{
    var inputString = "";
    var index = 0;
    for (index
                 = 0; index
            < projectContactArray.length; index++)
    {
        if (projectContactArray[index].isDeleted
                == 'true')
        {
            inputString
                    += "<input type='hidden' id='contacts.contact"
                               + projectContactArray[index].id
                               + ".isDeletedText' name='contacts.contact"
                               + projectContactArray[index].id
                               + ".isDeletedText' value='true'/>"
                               + "<input type='hidden' id='contacts.contact"
                               + projectContactArray[index].id
                               + ".id' name='contacts.contact"
                               + projectContactArray[index].id
                               + ".id' value='"
                               + projectContactArray[index].projectContactId
                    + "'/>";
        }
    }
    var idc = document.getElementById("iDelContact");
    idc.innerHTML
            = inputString;
}
function displayContactList()
{
    var index = 0;
    if (projectContactArray.length
            > 0)
    {
        for (index
                     = 0; index
                < projectContactArray.length; index++)
        {
            if (index
                    < 25)
            {
                projectContactArray[index].id
                        = contactAdd++;
                createContactInput(index,
                                   projectContactArray[index]);
            }
        }
    }
}
function createContactInput(index, projectContact)
{
    var id = projectContact.id;
    var readOnlyText;
    var stateInputString;
    if (projectContact.contact.id
            == '-1')
    {
        readOnlyText
                = " ";
        stateInputString
                = "<select class='span1' id='contacts.contact"
                          + id
                          + ".stateCode' name='contacts.contact"
                          + id
                          + ".stateCode' onchange=\"return contactStateOnChange('"
                          + index
                          + "');\" >"
                          + getStateOptions('')
                + "</select>";
    }
    else
    {
        readOnlyText
                = "readOnly";
        stateInputString
                = "<input  class='span1' type=\"text\" id=\"contacts.contact"
                          + id
                          + ".stateCode\" name=\"contacts.contact"
                          + id
                          + ".stateCode\" value=\""
                          + projectContact.contact.stateCode
                + "\" size=\"5\" readOnly/>";
    }
    var inputString = "<table class='table table-condensed'><tr>"
                              + "<td>Type</td>"
                              + "<td>"
                              + "<input type=\"hidden\" id=\"contacts.contact"
                              + id
                              + ".id\" name=\"contacts.contact"
                              + id
                              + ".id\" value=\""
                              + projectContact.projectContactId
                              + "\" /><input type=\"hidden\" id=\"contacts.contact"
                              + id
                              + ".addressId\" name=\"contacts.contact"
                              + id
                              + ".addressId\" value=\""
                              + projectContact.contact.addressId
                              + "\" />"
                              + "<select id=\"contacts.contact"
                              + id
                              + ".contactTypeId\" name=\"contacts.contact"
                              + id
                              + ".contactTypeId\" onchange=\"return contactTypeOnChange('"
                              + index
                              + "');\">"
                              + getContactTypeOptions(projectContact.contactTypeId)
                              + "</select>"
                              + "</td></tr>"
                              + "<tr><td>"
                              + "Organization</td><td>"
                          /*
                           + ((isExistingProjectContact(index)
                           || projectContact.contact.organizationId
                           == '-1'
                           || projectContact.contact.id
                           == '-1')
                           ? "<th >First Name</th><th >Last Name</th>"
                           : "<th >Contact</th>")
                           + "<th>Options</th>"
                           */
                              + ((projectContact.contact.organizationId
            == '-1')
            ? "<input type='text' id='contacts.contact"
                      + id
                      + ".organizationName' name='contacts.contact"
                      + id
                      + ".organizationName' size='25' maxlength='50' value='"
                      + projectContact.contact.organizationName
                      + "' onchange=\"return contactOrgNameOnChange('"
                      + index
                      + "');\"/><input type='hidden' id='contacts.contact"
                      + id
                      + ".organizationId' name='contacts.contact"
                      + id
                      + ".organizationId' value='"
                      + projectContact.contact.organizationId
            + "'/>"
            : ((isExistingProjectContact(index))
            ? ""
                      + projectContact.contact.organizationName
                      + "<input type='hidden' id='contacts.contact"
                      + id
                      + ".organizationId' name='contacts.contact"
                      + id
                      + ".organizationId' value='"
                      + projectContact.contact.organizationId
                      + "'/><input type='hidden' id='contacts.contact"
                      + id
                      + ".organizationName' name='contacts.contact"
                      + id
                      + ".organizationName' value='"
                      + projectContact.contact.organizationName
            + "'/>"
            : "<select id='contacts.contact"
                      + id
                      + ".organizationId' name='contacts.contact"
                      + id
                      + ".organizationId' onchange=\"return contactOrgOnChange('"
                      + index
                      + "');\">"
                      + getContactOrgOptions(projectContact.contact.organizationId)
                      + "</select><input type='hidden' id='contacts.contact"
                      + id
                      + ".organizationName' name='contacts.contact"
                      + id
                      + ".organizationName' value='"
                      + projectContact.contact.organizationName
            + "'/>"))
                              + "</td></tr><tr>"
                              + "<td>Contact</td>"
                              + "<td><span>"
                              + ((projectContact.contact.organizationId
                                          == '-1'
            || projectContact.contact.id
            == '-1')
            ? "<input type='text' id='contacts.contact"
                      + id
                      + ".firstName' name='contacts.contact"
                      + id
                      + ".firstName' size='20' maxlength='20' value='"
                      + projectContact.contact.firstName
                      + "' onchange=\"return contactFirstNameOnChange('"
                      + index
                      + "');\" "
                      + readOnlyText
                      + " /><input type='text' id='contacts.contact"
                      + id
                      + ".lastName' name='contacts.contact"
                      + id
                      + ".lastName' size='20' maxlength='20' value='"
                      + projectContact.contact.lastName
                      + "' onchange=\"return contactLastNameOnChange('"
                      + index
                      + "');\""
                      + readOnlyText
                      + " /><input type='hidden' id='contacts.contact"
                      + id
                      + ".contactId' name='contacts.contact"
                      + id
                      + ".contactId' value='"
                      + projectContact.contact.id
            + "' /></span>"
            : ((isExistingProjectContact(index))
            ? projectContact.contact.firstName
                      + "&nbsp;"
                      + projectContact.contact.lastName
                      + "<input type='hidden' id='contacts.contact"
                      + id
                      + ".contactId' name='contacts.contact"
                      + id
                      + ".contactId' value='"
                      + projectContact.contact.id
                      + "'/><input type='hidden' id='contacts.contact"
                      + id
                      + ".firstName' name='contacts.contact"
                      + id
                      + ".firstName' value='"
                      + projectContact.contact.firstName
                      + "'/><input type='hidden' id='contacts.contact"
                      + id
                      + ".lastName' name='contacts.contact"
                      + id
                      + ".lastName' value='"
                      + projectContact.contact.lastName
            + "'/>"
            : "<select id='contacts.contact"
                      + id
                      + ".contactId' name='contacts.contact"
                      + id
                      + ".contactId' onchange=\"return contactOnChange('"
                      + index
                      + "');\">"
                      + getContactOptions(projectContact.contact.id,
                                          projectContact.contact.organizationId)
                      + "</select><input type='hidden' id='contacts.contact"
                      + id
                      + ".firstName' name='contacts.contact"
                      + id
                      + ".firstName' value='"
                      + projectContact.contact.firstName
                      + "'/><input type='hidden' id='contacts.contact"
                      + id
                      + ".lastName' name='contacts.contact"
                      + id
                      + ".lastName' value='"
                      + projectContact.contact.lastName
            + "'/>"))
                              + "&nbsp;<a class='btn btn-mini' href=\"javascript:deleteContactInput('"
                              + index
                              + "');\">Remove</a>"
                              + "</td></tr>"
                              + "<tr><td>"
                              + "Address</td>"
                              + "<td><input type='text' id='contacts.contact"
                              + id
                              + ".address' name='contacts.contact"
                              + id
                              + ".address' size='30' maxlength='50' value='"
                              + projectContact.contact.address
                              + "' onchange=\"return contactAddressOnChange('"
                              + index
                              + "');\" "
                              + readOnlyText
                              + " /></td>"
                              + "</tr><tr>"
                              + "<td>City, State, Zip Code</td>"
                              + "<td><div><input  class='span2'  type='text' id='contacts.contact"
                              + id
                              + ".city' name='contacts.contact"
                              + id
                              + ".city' size='20' maxlength='25' value='"
                              + projectContact.contact.city
                              + "' onchange=\"return contactCityOnChange('"
                              + index
                              + "');\" "
                              + readOnlyText
                              + " />"
                              + stateInputString
                              + "<input  class='span1'  type='text' id='contacts.contact"
                              + id
                              + ".zip' name='contacts.contact"
                              + id
                              + ".zip' size='10' maxlength='10' value='"
                              + projectContact.contact.zipCode
                              + "' onchange=\"return contactZipCodeOnChange('"
                              + index
                              + "');\" "
                              + readOnlyText
                              + " /></td>"
                              + "</tr>"
                              + "<tr>"
                              + "<td>Phone</td>"
                              + "<td><input type='text' id='contacts.contact"
                              + id
                              + ".primaryPhone' name='contacts.contact"
                              + id
                              + ".primaryPhone' size='15' maxlength='15' value='"
                              + projectContact.contact.phone
                              + "' onchange=\"return contactPhoneOnChange('"
                              + index
                              + "');\" "
                              + readOnlyText
                              + " /></td>"
                              + "</tr><tr>"
                              + "<td >Email</td>"
                              + "<td><input type='text' id='contacts.contact"
                              + id
                              + ".email' name='contacts.contact"
                              + id
                              + ".email' size='40' maxlength='255' value='"
                              + projectContact.contact.email
                              + "' onchange=\"return contactEmailOnChange('"
                              + index
                              + "');\" "
            + readOnlyText
            + " /></td>"
            + "</tr></table>";
    var ni = document.getElementById('iContact'
                                             + id);
    ni.innerHTML
            = inputString;
    applyUI('iContact',
            id);
}
function clearInternalProjectContact(id)
{
    document.getElementById('contacts.contact'
                                    + id
                                    + '.isDeletedText').value
            = 'true';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.organizationId').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.orgRefClientId').value
            = '';
    if (document.getElementById('contacts.contact'
                                        + id
                                        + '.contactId')
            != null)
    {
        document.getElementById('contacts.contact'
                                        + id
                                        + '.contactId').value
                = '';
        /*
         $.uniform.update(document.getElementById('contacts.contact'
         + id
         + '.contactId'));
         */
    }
    document.getElementById('contacts.contact'
                                    + id
                                    + '.firstName').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.lastName').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.address').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.city').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.stateCode').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.zip').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.email').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.primaryPhone').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.addressId').value
            = '';
    document.getElementById('contacts.contact'
                                    + id
                                    + '.firstName').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.lastName').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.address').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.city').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.stateCode').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.zip').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.email').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.primaryPhone').readOnly
            = true;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.addressId').readOnly
            = true;
    // Create state textbox with no value (because we're adding a new contact.)
    var inputString = "<input  class='span1' type=\"text\" id=\"contacts.contact"
                              + id
                              + ".stateCode\" name=\"contacts.contact"
                              + id
            + ".stateCode\" readOnly/>";
    var stateElement = document.getElementById('contactStateSelect'
                                                       + id);
    stateElement.innerHTML
            = inputString;
    applyUI('contactStateSelect',
            id);
}
function initInternalProjectContact(id, clientId, contactTypeId)
{
    var index = 0;
    /*
     var found = 0;
     */
    var inputHTML = "";
    //First, clear the contact info for this id
    //clearInternalProjectContact(id);
    //Get the contact organization for the selected client
    var contactOrg = getContactOrgByRefClientId(clientId);
    //  document.getElementById('contacts.contact' + id + '.organizationName').value = contactOrg.name;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.organizationId').value
            = contactOrg.id;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.orgRefClientId').value
            = contactOrg.refClientId;
    if (clientId
            != "")
    {
        for (index
                     = 0; index
                < internalProjectContactArray.length; index++)
        {
            if (internalProjectContactArray[index].contactTypeId
                    == contactTypeId)
            {
                var contact = internalProjectContactArray[index].contact;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.id').value
                        = internalProjectContactArray[index].projectContactId;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.firstName').value
                        = contact.firstName;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.lastName').value
                        = contact.lastName;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.address').value
                        = contact.address;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.city').value
                        = contact.city;
                //  document.getElementById('contacts.contact' + id + '.stateCode').value = contact.stateCode;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.zip').value
                        = contact.zipCode;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.email').value
                        = contact.email;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.primaryPhone').value
                        = contact.phone;
                document.getElementById('contacts.contact'
                                                + id
                                                + '.addressId').value
                        = contact.addressId;
                break;
            }
        }
    }
    //Build the table that contains the contact select list
    inputHTML
            += "<div><label>Contact</label>";
    inputHTML
            += "<span><select "
                       + ((clientId
            == '')
            ? "disabled='disabled'"
            : "")
                       + " id=\"contacts.contact"
                       + id
                       + ".contactId\" name=\"contacts.contact"
                       + id
                       + ".contactId\" onchange=\"return internalProjectContactOnChange('"
                       + id
            + "');\">";
    inputHTML
            += "<option value=\"\">Select...</option>";
    inputHTML
            += "<option value=\"-1\">Add New Contact</option>";
    //Add the organization contacts to the select list
    var contactIdElement = 'contacts.contact'
                                   + id
            + '.id';
    for (index
                 = 0; index
            < clientContactArray.length; index++)
    {
        if (clientContactArray[index].organizationId
                == contactOrg.id)
        {
            inputHTML
                    += "<option "
                               + ((clientContactArray[index].id
                    == document.getElementById(contactIdElement).value)
                    ? "selected='selected'"
                    : "")
                               + " value=\""
                               + clientContactArray[index].id
                               + "\">"
                               + clientContactArray[index].lastName
                               + ", "
                               + clientContactArray[index].firstName
                    + "</option>";
        }
    }
    inputHTML
            += "</select></span>";
    inputHTML
            += "&nbsp;<a class='btn btn-mini' href=\"javascript:clearInternalProjectContact('"
                       + id
            + "');\">Remove</a></div>";
    //Update the HTML element
    document.getElementById("internalProjectContactSelect"
                                    + id).innerHTML
            = inputHTML;
    /*
     applyUI('internalProjectContactSelect',
     id);
     */
    // Create state textbox with no value (because we're contact is blank.)
    var inputString = "<input class='span1' type=\"text\" id=\"contacts.contact"
                              + id
                              + ".stateCode\" name=\"contacts.contact"
                              + id
            + ".stateCode\" readOnly/>";
    var stateElement = document.getElementById('contactStateSelect'
                                                       + id);
    stateElement.innerHTML
            = inputString;
    /*
     applyUI('contactStateSelect',
     id);
     */
}
function getProjectClientIdById(id)
{
    var clientId = '';
    //Read the proper clientId based on the id passed
    if (id
            == '0')
    {
        clientId
                = document.getElementById("permitAuthorityClientId").value;
    }
    else if (id
            == '1')
    {
        clientId
                = document.getElementById("permittedClientId").value;
    }
    else if (id
            == '2')
    {
        clientId
                = document.getElementById("authorizedInspectorClientId").value;
    }
    return clientId;
}
function projectClientSelectOnChange(id)
{
    var inputHTML = "";
    var index = 0;
    //Read the proper clientId based on the id passed
    var clientId = getProjectClientIdById(id);
    //First, clear the contact info for this id
    clearInternalProjectContact(id);
    //Get the contact organization for the selected client
    var contactOrgRefClientId/*, contactOrgName*/;
    var contactOrg = getContactOrgByRefClientId(clientId);
    if (contactOrg.id
            == '-1')
    {
        contactOrgRefClientId
                = clientId;
    }
    else
    {
        contactOrgRefClientId
                = contactOrg.refClientId;
    }
    //Set the selected organization values for the contact
    document.getElementById("contacts.contact"
                                    + id
                                    + ".organizationId").value
            = contactOrg.id;
    document.getElementById("contacts.contact"
                                    + id
                                    + ".orgRefClientId").value
            = contactOrgRefClientId;
    //  document.getElementById("contacts.contact" + id + ".organizationName").value = contactOrg.Name;
    //Build the table that contains the contact select list
    inputHTML
            += "<div><label>Contact</label>";
    inputHTML
            += "<span><select "
                       + ((clientId
            == '')
            ? "disabled='disabled'"
            : "")
                       + " id=\"contacts.contact"
                       + id
                       + ".contactId\" name=\"contacts.contact"
                       + id
                       + ".contactId\" onchange=\"return internalProjectContactOnChange('"
                       + id
            + "');\">";
    inputHTML
            += "<option selected='selected' value=\"\">Select...</option>";
    inputHTML
            += "<option value=\"-1\">Add New Contact</option>";
    //Add the organization contacts to the select list
    for (index
                 = 0; index
            < clientContactArray.length; index++)
    {
        if (clientContactArray[index].organizationId
                == contactOrg.id)
        {
            inputHTML
                    += "<option value=\""
                               + clientContactArray[index].id
                               + "\">"
                               + clientContactArray[index].lastName
                               + ", "
                               + clientContactArray[index].firstName
                    + "</option>";
        }
    }
    inputHTML
            += "</select></span>";
    inputHTML
            += "&nbsp;<a class='btn btn-mini' href=\"javascript:clearInternalProjectContact('"
                       + id
            + "');\">Remove</a></div>";
    //Update the HTML element
    document.getElementById("internalProjectContactSelect"
                                    + id).innerHTML
            = inputHTML;
    applyUI('internalProjectContactSelect',
            id);
}
function internalProjectContactOnChange(id)
{
    var contactId = document.getElementById('contacts.contact'
                                                    + id
                                                    + '.contactId').value;
    //Clear the contact fields
    clearInternalProjectContact(id);
    //Read the proper clientId based on the id passed
    var clientId = getProjectClientIdById(id);
    //Get the contact organization for the selected client
    var contactOrgRefClientId;
    var contactOrg = getContactOrgByRefClientId(clientId);
    if (contactOrg.id
            == '-1')
    {
        contactOrgRefClientId
                = clientId;
    }
    else
    {
        contactOrgRefClientId
                = contactOrg.refClientId;
    }
    //Set the selected organization values for the contact
    document.getElementById("contacts.contact"
                                    + id
                                    + ".organizationId").value
            = contactOrg.id;
    document.getElementById("contacts.contact"
                                    + id
                                    + ".orgRefClientId").value
            = contactOrgRefClientId;
    //  document.getElementById("contacts.contact" + id + ".organizationName").value = contactOrg.Name;
    document.getElementById('contacts.contact'
                                    + id
                                    + '.isDeletedText').value
            = 'false';
    if (contactId
            == '-1')
    {
        document.getElementById('contacts.contact'
                                        + id
                                        + '.contactId').value
                = '-1';
        document.getElementById('contacts.contact'
                                        + id
                                        + '.firstName').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.lastName').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.address').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.city').readOnly
                = false;
        //document.getElementById('contacts.contact' + id + '.stateCode').readOnly = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.zip').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.email').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.primaryPhone').readOnly
                = false;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.addressId').readOnly
                = false;
        // Create state dropdown with no value (because we're adding a new contact.)
        var stateHTML = "<select class='span1' id='contacts.contact"
                                + id
                                + ".stateCode' name='contacts.contact"
                                + id
                                + ".stateCode' ;\" >"
                                + getStateOptions('')
                + "</select>";
        var stateElement = document.getElementById('contactStateSelect'
                                                           + id);
        stateElement.innerHTML
                = stateHTML;
        applyUI('contactStateSelect',
                id);
    }
    else
    {
        var contact = getClientContact(contactId);
        if (contact.id
                == '')
        {
            document.getElementById('contacts.contact'
                                            + id
                                            + '.isDeletedText').value
                    = 'true';
        }
        document.getElementById('contacts.contact'
                                        + id
                                        + '.contactId').value
                = contact.id;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.firstName').value
                = contact.firstName;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.lastName').value
                = contact.lastName;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.address').value
                = contact.address;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.city').value
                = contact.city;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.stateCode').value
                = contact.stateCode;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.zip').value
                = contact.zipCode;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.email').value
                = contact.email;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.primaryPhone').value
                = contact.phone;
        document.getElementById('contacts.contact'
                                        + id
                                        + '.addressId').value
                = contact.addressId;
    }
}