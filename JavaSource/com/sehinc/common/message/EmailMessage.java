package com.sehinc.common.message;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class EmailMessage
{
    private static
    Logger
        LOG =
        Logger.getLogger(EmailMessage.class);
    private
    Hashtable
        headers;
    private
    String
        message;
    private
    Address[]
        from;
    private
    Address[]
        to;
    private
    Address[]
        cc;
    private
    Address[]
        bcc;
    private
    String
        subject;
    private
    String
        text;
    private
    Multipart
        multipart;
    private
    Date
        sentDate;

    public void setHeaderInfo(MessageHeader messageHeader, String header)
    {
        if (headers
            == null)
        {
            headers =
                new Hashtable();
        }
        headers.put(messageHeader,
                    header);
    }

    public String getHeaderInfo(MessageHeader messageHeader)
    {
        if (headers
            == null)
        {
            return null;
        }
        return (String) headers.get(messageHeader);
    }

    public Enumeration getHeaders()
    {
        if (headers
            == null)
        {
            headers =
                new Hashtable();
        }
        return headers.keys();
    }

    public void setMessage(String message)
    {
        this.message =
            message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setFrom(Address[] from)
    {
        this.from =
            from;
    }

    public Address[] getFrom()
    {
        return this.from;
    }

    public void setRecipients(Message.RecipientType type, Address[] addresses)
    {
        if (type.equals(Message.RecipientType.TO))
        {
            this.to =
                addresses;
        }
        else if (type.equals(Message.RecipientType.CC))
        {
            this.cc =
                addresses;
        }
        else if (type.equals(Message.RecipientType.BCC))
        {
            this.bcc =
                addresses;
        }
    }

    public Address[] getRecipients(Message.RecipientType type)
    {
        if (type.equals(Message.RecipientType.TO))
        {
            return this.to;
        }
        else if (type.equals(Message.RecipientType.CC))
        {
            return this.cc;
        }
        else if (type.equals(Message.RecipientType.BCC))
        {
            return this.bcc;
        }
        return new Address[0];
    }

    public void setSubject(String subject)
    {
        this.subject =
            subject;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public void setText(String text)
    {
        this.text =
            text;
        this.multipart =
            null;
    }

    public String getText()
    {
        return this.text;
    }

    public void setMultipartContent(Multipart multipart)
    {
        this.multipart =
            multipart;
        this.text =
            null;
    }

    public Multipart getMultipartContent()
    {
        return this.multipart;
    }

    public void setSentDate(Date sentDate)
    {
        this.sentDate =
            sentDate;
    }

    public Date getSentDate()
    {
        return this.sentDate;
    }

    public MimeMessage getMimeMessage(Session session)
    {
        try
        {
            MimeMessage
                mimeMessage =
                new MimeMessage(session);
            Enumeration
                headers =
                getHeaders();
            while (headers.hasMoreElements())
            {
                MessageHeader
                    key =
                    (MessageHeader) headers.nextElement();
                String
                    value =
                    getHeaderInfo(key);
                mimeMessage.setHeader(key.toString(),
                                      value);
            }
            mimeMessage.addFrom(from);
            mimeMessage.addRecipients(Message.RecipientType.TO,
                                      to);
            mimeMessage.addRecipients(Message.RecipientType.CC,
                                      cc);
            mimeMessage.addRecipients(Message.RecipientType.BCC,
                                      bcc);
            mimeMessage.setSubject(subject);
            if (multipart
                != null)
            {
                mimeMessage.setContent(multipart);
            }
            if (text
                != null)
            {
                mimeMessage.setContent(text,
                                       getHeaderInfo(MessageHeader.CONTEXT_TYPE));
            }
            mimeMessage.setSentDate(sentDate);
            return mimeMessage;
        }
        catch (MessagingException me)
        {
            LOG.debug("ERROR:  "
                      + me.getMessage());
        }
        return null;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        Enumeration
            keys =
            getHeaders();
        while (keys.hasMoreElements())
        {
            MessageHeader
                messageHeader =
                (MessageHeader) keys.nextElement();
            String
                value =
                (String) headers.get(messageHeader);
            buffer.append(messageHeader.toString()
                          + ": "
                          + value);
            buffer.append("\n");
        }
        int
            i;
        if (from
            != null)
        {
            for (
                i =
                    0; i
                       < from.length; i++)
            {
                if (i
                    == 0)
                {
                    buffer.append("From:  "
                                  + from[i]);
                }
                else
                {
                    buffer.append(", "
                                  + from[i]);
                }
            }
            buffer.append("\n");
        }
        if (to
            != null)
        {
            for (
                i =
                    0; i
                       < to.length; i++)
            {
                if (i
                    == 0)
                {
                    buffer.append("To:  "
                                  + to[i]);
                }
                else
                {
                    buffer.append(", "
                                  + to[i]);
                }
            }
            buffer.append("\n");
        }
        if (cc
            != null)
        {
            for (
                i =
                    0; i
                       < cc.length; i++)
            {
                if (i
                    == 0)
                {
                    buffer.append("CC:  "
                                  + cc[i]);
                }
                else
                {
                    buffer.append(", "
                                  + cc[i]);
                }
            }
            buffer.append("\n");
        }
        if (bcc
            != null)
        {
            for (
                i =
                    0; i
                       < bcc.length; i++)
            {
                if (i
                    == 0)
                {
                    buffer.append("BCC:  "
                                  + bcc[i]);
                }
                else
                {
                    buffer.append(", "
                                  + bcc[i]);
                }
            }
            buffer.append("\n");
        }
        buffer.append("Subject:  "
                      + getSubject()
                      + "\n");
        buffer.append("Message:\n");
        buffer.append(getText());
        return buffer.toString();
    }
}
