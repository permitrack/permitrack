//Window functions
function openWindow(x) {
    var interfaceWindow;
    interfaceWindow = window.open(x, 'interface', 'width=800, height=600, left=20, top=20, dependent=yes,hotkeys=yes,menubar=yes,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=yes');
    interfaceWindow.focus();
}

function openWindowFullscreen(x) {
    var interfaceWindow;
    interfaceWindow = window.open(x, 'interface', 'fullscreen=yes,hotkeys=yes,menubar=yes,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=yes');
    interfaceWindow.focus();
}

//Cookie functions
function SetCookie(name, value) {
    var argv = SetCookie.arguments;
    var argc = SetCookie.arguments.length;
    var minutes = (argc > 2) ? argv[2] : null;
    //var path = (argc > 3) ? argv[3] : null;
    var domain = (argc > 4) ? argv[4] : null;
    var secure = (argc > 5) ? argv[5] : false;
    var path = "/"; //allows the tree to remain open across pages with diff names & paths
    var expires = new Date();
    if (minutes !== null) {
        var newTime = expires.getTime() + (minutes * 60 * 1000);
        expires.setTime(newTime);
    }

    document.cookie = name + "=" + $.fn.editableutils.escape(value) +
            ((minutes === null) ? "" : ("; expires=" + expires.toGMTString())) +
            ((path === null) ? "" : ("; path=" + path)) +
            ((domain === null) ? "" : ("; domain=" + domain)) +
            ((secure === true) ? "; secure" : "");
}

//Format functions
function formatPhone(strPhone, strMessage, blnValid) { // Format phone number

    var s = ''; // Return value
    var sx = ''; // parse phone extension string
    var blnExt = false;
    var numbers = '1234567890' // List of acceptable numeric values
    var i = 0; // String indexer value.
    var z = 0; // Index of the phone number extension.
    var ch; // individual characters at the specified index.

    if (strMessage == null) strMessage = '';
    if (strPhone == null) strPhone = '';

    blnValid.valid = true; // Default to phone is valid until proven otherwise.

    // Check for the presence of an extenstion number
    blnExt = (strPhone.indexOf('ext') >= 0)

    if (blnExt) { // Parse the phone number and the extension seperately

        z = strPhone.indexOf('ext'); // Get the extension index value

        //Parse the phone number up to the extension index value.
        for (i = 0; i < z; i++) { // Numeric values only
            ch = strPhone.charAt(i);

            if (numbers.indexOf(ch) >= 0) { // The character is a number
                s = s + ch;
            } // The character is a number
        } // Numeric values only.

        //Now parse the phone extension number
        for (i = z; i < strPhone.length; i++) { // Numeric values only
            ch = strPhone.charAt(i);

            if (numbers.indexOf(ch) >= 0) { // The character is a number
                sx = sx + ch;
            } // The character is a number
        } // Numeric values only.

    } // Parse the phone number and the extension seperately
    else { // Parse the entire phone number

        //Retrieve only the numeric values of the phone number
        for (i = 0; i < strPhone.length; i++) { // Numeric values only
            ch = strPhone.charAt(i);

            if (numbers.indexOf(ch) >= 0) { // The character is a number
                s = s + ch;
            } // The character is a number
        } // Numeric values only.

    } // Parse the entire phone number

    // Done parsing the phone number for numeric values only.

    //Now determine if we have a 7, 10 or more digit phone number.
    if ((s.length == 0) && (blnExt)) { // The phone number is an extension only
        s = '';
    } // The phone number is an extension only
    else if (s.length <= 6) { // Phone number has less than seven digits

        blnValid.valid = false;
        if (strMessage.length >= 1) { // Show alert message
            $('#dialog').html('Phone number, ' + strMessage + ', ' + strPhone + ', is not a valid phone number.').dialog('open');
        } // Show alert message

        s = strPhone; // Simply return the input string unformatted.

    } // Phone number has less than seven digits
    else if (s.length == 7) { // Seven digit phone number
        s = s.substring(0, 3) + '-' + s.substring(3, 7);
    } // Seven digit phone number
    else if (s.length == 10) { // Area code, exchange and number
        s = '(' + s.substring(0, 3) + ') ' + s.substring(3, 6) + '-' + s.substring(6, 10);
    } // Area code, exchange and number
    else if ((s.length == 11) && (s.charAt(0) == '1')) { // Phone number is longer than area code, exchange and number
        s = '1 (' + s.substring(1, 4) + ') ' + s.substring(4, 7) + '-' + s.substring(7, 11);
    } // Phone number is longer than area code, exchange and number
    else { // Phone number is longer than eleven digits

        blnValid.valid = false;
        if (strMessage.length >= 1) { // Show alert message
            $('#dialog').html('Phone number, ' + strMessage + ', ' + strPhone + ', is not a valid phone number.').dialog('open');
        } // Show alert message

        s = strPhone; // Simply return the input string unformatted.

    } // Phone number is longer than eleven digits.


    //Check if the phone number has an extension within it.
    if (blnExt) { // Format phone number with an extension
        s = s + ' ext: ' + sx;
    } // Format phone number with an extension

    return s; // Return the formatted phone number

} // Format phone number

function NoSpaces(sString) {
    var strSpace = " "; // Checks for   space in this case
    var blnNoSpaces = false;
    blnNoSpaces = (sString.indexOf(strSpace) == -1);
    return blnNoSpaces;
}

function ValidChars(sString) { // valid chars are letters, numbers, ., -, _
    var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_";
    var allValid = true;
    var ch = "";

    sString = Trim(sString);

    if (sString.length >= 1) {
        for (i = 0; i < sString.length; i++) {
            if (allValid) {
                ch = sString.charAt(i);
                allValid = (checkOK.indexOf(ch) >= 0);
            }
        }
    }
    else {
        allValid = false;
    }
    return allValid;
}

function Trim(sString) {
    sString = rightTrim(leftTrim(sString));
    return sString;
}

function rightTrim(sString) {
    while (sString.substring(sString.length - 1, sString.length) == ' ') {
        sString = sString.substring(0, sString.length - 1);
    }
    return sString;
}

function leftTrim(sString) {
    while (sString.substring(0, 1) == ' ') {
        sString = sString.substring(1, sString.length);
    }
    return sString;
}

//String functions
function entityify(mystring) {
    return mystring.replace(/&/g, "&amp;").replace(/</g,
            "&lt;").replace(/>/g, "&gt;");
}

function trim(mystring) {
    return mystring.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1");
}

function applyUI(label, id)
{
/*
    $("#"
        + label
        + id
        + " input,"
        + "#"
        + label
        + id
        + " textarea, "
        + "#"
        + label
        + id
        + " select, "
        + "#"
        + label
        + id
        + " button").uniform();
*/
/*
    $("#"
        + label
        + id
        + " .buttons").buttonset();
*/
}

function setFocus(c)
{
    try
    {
        //var focusControl = document.planForm.name;
        if (c.type
                != "hidden")
        {
            c.focus();
        }
    }
    catch (e)
    {
    }
}

/*
function imgError(image) {
    image.onerror = "";
    image.src = "";
    return true;
}
*/
