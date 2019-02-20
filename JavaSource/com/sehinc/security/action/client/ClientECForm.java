package com.sehinc.security.action.client;

import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

public class ClientECForm
    extends BaseValidatorForm
{
    private
    ActionMessages
        clientErrors =
        new ActionMessages();
    private
    Integer
        i =
        0;
    private
    String
        s =
        "";
    private
    Integer
        id =
        i;
    private
    Integer
        clientId =
        i;
    private
    String
        publicCommentEMail =
        s;
    private
    Integer
        clientTypeId =
        i;
    private
    String
        clientTypeName =
        s;
    private
    String
        generalReplyToEMail =
        s;
    private
    boolean
        blnSystemAdmin;
    private
    boolean
        isProjectStatusEmailsEnabled;
    private
    boolean
        isInspectionOverdueNotificationEnabled;
    private
    boolean
        isSecondInspectionOverdueNotificationEnabled;
    private
    Integer
        inspectionOverdueInitialThreshold;
    private
    String
        inspectionOverdueInitialMessage;
    private
    Integer
        inspectionOverdueSecondThreshold;
    private
    String
        inspectionOverdueSecondMessage;
    private
    boolean
        isInspectionCertificationEnabled;
    private
    String
        inspectionCertificationMessage;

    public ClientECForm()
    {
    }

    public Integer getId()
    {
        if (this.id
            == null)
        {
            return i;
        }
        else
        {
            return this.id;
        }
    }

    public void setId(Integer v)
    {
        if (v
            == null)
        {
            this.id =
                i;
        }
        else
        {
            this.id =
                v;
        }
    }

    public Integer getClientId()
    {
        if (this.clientId
            == null)
        {
            return i;
        }
        else
        {
            return this.clientId;
        }
    }

    public void setClientId(Integer v)
    {
        if (v
            == null)
        {
            this.clientId =
                i;
        }
        else
        {
            this.clientId =
                v;
        }
    }

    public String getPublicCommentEMail()
    {
        if (this.publicCommentEMail
            == null)
        {
            return s;
        }
        else
        {
            return this.publicCommentEMail;
        }
    }

    public void setPublicCommentEMail(String v)
    {
        if (v
            == null)
        {
            this.publicCommentEMail =
                s;
        }
        else
        {
            this.publicCommentEMail =
                lt(v);
        }
    }

    public Integer getClientTypeId()
    {
        if (this.clientTypeId
            == null)
        {
            return i;
        }
        else
        {
            return this.clientTypeId;
        }
    }

    public void setClientTypeId(Integer v)
    {
        if (v
            == null)
        {
            this.clientTypeId =
                i;
        }
        else
        {
            this.clientTypeId =
                v;
        }
    }

    public String getClientTypeName()
    {
        if (this.clientTypeName
            == null)
        {
            return s;
        }
        else
        {
            return this.clientTypeName;
        }
    }

    public void setClientTypeName(String v)
    {
        if (v
            == null)
        {
            this.clientTypeName =
                s;
        }
        else
        {
            this.clientTypeName =
                v;
        }
    }

    public String getGeneralReplyToEMail()
    {
        if (this.generalReplyToEMail
            == null)
        {
            return s;
        }
        else
        {
            return this.generalReplyToEMail;
        }
    }

    public void setGeneralReplyToEMail(String v)
    {
        if (v
            == null)
        {
            this.generalReplyToEMail =
                s;
        }
        else
        {
            this.generalReplyToEMail =
                lt(v);
        }
    }

    public boolean getProjectStatusEmailsEnabled()
    {
        return isProjectStatusEmailsEnabled;
    }

    public void setProjectStatusEmailsEnabled(boolean projectStatusEmailsEnabled)
    {
        isProjectStatusEmailsEnabled =
            projectStatusEmailsEnabled;
    }

    public boolean getInspectionOverdueNotificationEnabled()
    {
        return isInspectionOverdueNotificationEnabled;
    }

    public void setInspectionOverdueNotificationEnabled(boolean inspectionOverdueNotificationEnabled)
    {
        isInspectionOverdueNotificationEnabled =
            inspectionOverdueNotificationEnabled;
    }

    public boolean getSecondInspectionOverdueNotificationEnabled()
    {
        return isSecondInspectionOverdueNotificationEnabled;
    }

    public void setSecondInspectionOverdueNotificationEnabled(boolean secondInspectionOverdueNotificationEnabled)
    {
        isSecondInspectionOverdueNotificationEnabled =
            secondInspectionOverdueNotificationEnabled;
    }

    public Integer getInspectionOverdueInitialThreshold()
    {
        return inspectionOverdueInitialThreshold;
    }

    public void setInspectionOverdueInitialThreshold(Integer inspectionOverdueInitialThreshold)
    {
        this.inspectionOverdueInitialThreshold =
            inspectionOverdueInitialThreshold;
    }

    public String getInspectionOverdueInitialMessage()
    {
        return inspectionOverdueInitialMessage;
    }

    public void setInspectionOverdueInitialMessage(String inspectionOverdueInitialMessage)
    {
        this.inspectionOverdueInitialMessage =
            inspectionOverdueInitialMessage;
    }

    public Integer getInspectionOverdueSecondThreshold()
    {
        return inspectionOverdueSecondThreshold;
    }

    public void setInspectionOverdueSecondThreshold(Integer inspectionOverdueSecondThreshold)
    {
        this.inspectionOverdueSecondThreshold =
            inspectionOverdueSecondThreshold;
    }

    public String getInspectionOverdueSecondMessage()
    {
        return inspectionOverdueSecondMessage;
    }

    public void setInspectionOverdueSecondMessage(String inspectionOverdueSecondMessage)
    {
        this.inspectionOverdueSecondMessage =
            inspectionOverdueSecondMessage;
    }

    public String getInspectionCertificationMessage()
    {
        return inspectionCertificationMessage;
    }

    public void setInspectionCertificationMessage(String inspectionCertificationMessage)
    {
        this.inspectionCertificationMessage =
            inspectionCertificationMessage;
    }

    public boolean getInspectionCertificationEnabled()
    {
        return isInspectionCertificationEnabled;
    }

    public void setInspectionCertificationEnabled(boolean inspectionCertificationEnabled)
    {
        isInspectionCertificationEnabled =
            inspectionCertificationEnabled;
    }

    public ActionMessages getClientErrors()
    {
        return this.clientErrors;
    }

    public boolean getBlnSystemAdmin()
    {
        return blnSystemAdmin;
    }

    public void setBlnSystemAdmin(boolean isSystemAdmin)
    {
        this.blnSystemAdmin =
            isSystemAdmin;
    }

    public void reset()
    {
        clientErrors.clear();
        id =
            i;
        clientId =
            i;
        publicCommentEMail =
            s;
        clientTypeId =
            i;
        generalReplyToEMail =
            s;
        blnSystemAdmin =
            false;
        isProjectStatusEmailsEnabled =
            false;
        isInspectionOverdueNotificationEnabled =
            false;
        isSecondInspectionOverdueNotificationEnabled =
            false;
        inspectionOverdueInitialThreshold =
            i;
        inspectionOverdueInitialMessage =
            s;
        inspectionOverdueSecondThreshold =
            i;
        inspectionOverdueSecondMessage =
            s;
        isInspectionCertificationEnabled =
            false;
        inspectionCertificationMessage =
            s;
    }

    public void checkForHTML()
    {
        publicCommentEMail =
            html(publicCommentEMail);
        generalReplyToEMail =
            html(generalReplyToEMail);
        inspectionOverdueInitialMessage =
            html(inspectionOverdueInitialMessage);
        inspectionOverdueSecondMessage =
            html(inspectionOverdueSecondMessage);
        inspectionCertificationMessage =
            html(inspectionCertificationMessage);
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
