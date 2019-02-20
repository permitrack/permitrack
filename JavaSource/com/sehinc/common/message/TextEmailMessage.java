package com.sehinc.common.message;

public class TextEmailMessage
    extends EmailMessage
{
    public static final
    String
        NEWLINE =
        System.getProperty("line.separator");
    public static final
    String
        SPACE =
        " ";
    public static final
    String
        CONTEXT_TYPE_VALUE =
        "text/plain";
    public static final
    String
        LANGUAGE_VALUE =
        "charset=us-ascii";
    public static final
    String
        MIME_VERSION_VALUE =
        "1.0";

    public TextEmailMessage()
    {
        setHeaderInfo(MessageHeader.CONTEXT_TYPE,
                      CONTEXT_TYPE_VALUE);
        setHeaderInfo(MessageHeader.MIME_VERSION,
                      MIME_VERSION_VALUE);
    }
}
