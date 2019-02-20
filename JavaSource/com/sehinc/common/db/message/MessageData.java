package com.sehinc.common.db.message;

import com.sehinc.common.db.user.UserUpdatableData;

public class MessageData
    extends UserUpdatableData
{
    private
    String
        to;
    private
    String
        from;
    private
    String
        subject;
    private
    String
        contextType;
    private
    String
        text;

    public String getTo()
    {
        return this.to;
    }

    public void setTo(String to)
    {
        this.to =
            to;
    }

    public String getFrom()
    {
        return this.from;
    }

    public void setFrom(String from)
    {
        this.from =
            from;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public void setSubject(String subject)
    {
        this.subject =
            subject;
    }

    public String getContextType()
    {
        return this.contextType;
    }

    public void setContextType(String contextType)
    {
        this.contextType =
            contextType;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(String text)
    {
        this.text =
            text;
    }
}
