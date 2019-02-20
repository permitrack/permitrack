package com.sehinc.common.message;

public class MessageHeader
{
    private final
    String
        headerType;
    public static final
    MessageHeader
        CONTEXT_TYPE =
        new MessageHeader("Content-Type");
    public static final
    MessageHeader
        MIME_VERSION =
        new MessageHeader("MIME-Version");
    public static final
    MessageHeader
        FROM =
        new MessageHeader("From");
    public static final
    MessageHeader
        TO =
        new MessageHeader("To");
    public static final
    MessageHeader
        SUBJECT =
        new MessageHeader("Subject");
    public static final
    MessageHeader
        MESSAGE =
        new MessageHeader("Message");
    public static final
    MessageHeader
        REPLY_TO =
        new MessageHeader("Reply To");
    public static final
    MessageHeader
        CC =
        new MessageHeader("Cc");
    public static final
    MessageHeader
        BCC =
        new MessageHeader("Bcc");

    private MessageHeader(String headerType)
    {
        this.headerType =
            headerType;
    }

    public String toString()
    {
        return headerType;
    }
}
