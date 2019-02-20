package com.sehinc.common.message;

public class HtmlEmailMessage
    extends EmailMessage
{
    public static final
    String
        NEWLINE =
        "<br>";
    public static final
    String
        SPACE =
        "&nbsp;";
    public static final
    String
        CONTEXT_TYPE_VALUE =
        "text/html";
    public static final
    String
        LANGUAGE_VALUE =
        "charset=us-ascii";
    public static final
    String
        MIME_VERSION_VALUE =
        "1.0";

    public HtmlEmailMessage()
    {
        setHeaderInfo(MessageHeader.CONTEXT_TYPE,
                      CONTEXT_TYPE_VALUE);
        setHeaderInfo(MessageHeader.MIME_VERSION,
                      MIME_VERSION_VALUE);
    }
}
