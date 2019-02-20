package com.sehinc.security.action.client;

import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ClientDVForm
    extends BaseValidatorForm
{
    private
    ActionMessages
        clientErrors =
        new ActionMessages();
    private
    Integer
        i =
        new Integer(0);
    private
    String
        s =
        new String("");
    private
    BigDecimal
        x =
        new BigDecimal(0.00);
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
        clientFullName =
        s;
    private
    String
        clientName =
        s;
    private
    String
        imsService =
        s;
    private
    String
        imsOvService =
        s;
    private
    String
        downLoads =
        s;
    private
    String
        attachmentLayers =
        s;
    private
    BigDecimal
        startLeft =
        new BigDecimal(0.00);
    private
    BigDecimal
        startRight =
        new BigDecimal(0.00);
    private
    BigDecimal
        startTop =
        new BigDecimal(0.00);
    private
    BigDecimal
        startBottom =
        new BigDecimal(0.00);
    private
    BigDecimal
        limitLeft =
        new BigDecimal(0.00);
    private
    BigDecimal
        limitRight =
        new BigDecimal(0.00);
    private
    BigDecimal
        limitTop =
        new BigDecimal(0.00);
    private
    BigDecimal
        limitBottom =
        new BigDecimal(0.00);

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

    public void setId(Integer id)
    {
        if (id
            == null)
        {
            this.id =
                i;
        }
        else
        {
            this.id =
                id;
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

    public void setClientId(Integer clientId)
    {
        if (clientId
            == null)
        {
            this.clientId =
                i;
        }
        else
        {
            this.clientId =
                clientId;
        }
    }

    public String getClientFullName()
    {
        if (this.clientFullName
            == null)
        {
            return s;
        }
        else
        {
            return this.clientFullName;
        }
    }

    public void setClientFullName(String v)
    {
        if (v
            == null)
        {
            this.clientFullName =
                s;
        }
        else
        {
            this.clientFullName =
                lt(v);
        }
    }

    public String getClientName()
    {
        if (this.clientName
            == null)
        {
            return s;
        }
        else
        {
            return this.clientName;
        }
    }

    public void setClientName(String v)
    {
        if (v
            == null)
        {
            this.clientName =
                s;
        }
        else
        {
            this.clientName =
                lt(v);
        }
    }

    public String getImsService()
    {
        if (this.imsService
            == null)
        {
            return s;
        }
        else
        {
            return this.imsService;
        }
    }

    public void setImsService(String v)
    {
        if (v
            == null)
        {
            this.imsService =
                s;
        }
        else
        {
            this.imsService =
                lt(v);
        }
    }

    public String getImsOvService()
    {
        if (this.imsOvService
            == null)
        {
            return s;
        }
        else
        {
            return this.imsOvService;
        }
    }

    public void setImsOvService(String v)
    {
        if (v
            == null)
        {
            this.imsOvService =
                s;
        }
        else
        {
            this.imsOvService =
                lt(v);
        }
    }

    public String getDownLoads()
    {
        if (this.downLoads
            == null)
        {
            return s;
        }
        else
        {
            return this.downLoads;
        }
    }

    public void setDownLoads(String v)
    {
        if (v
            == null)
        {
            this.downLoads =
                s;
        }
        else
        {
            this.downLoads =
                lt(v);
        }
    }

    public String getAttachmentLayers()
    {
        if (this.attachmentLayers
            == null)
        {
            return s;
        }
        else
        {
            return this.attachmentLayers;
        }
    }

    public void setAttachmentLayers(String v)
    {
        if (v
            == null)
        {
            this.attachmentLayers =
                s;
        }
        else
        {
            this.attachmentLayers =
                lt(v);
        }
    }

    public BigDecimal getStartLeft()
    {
        if (this.startLeft
            == null)
        {
            return x;
        }
        else
        {
            return this.startLeft;
        }
    }

    public void setStartLeft(BigDecimal startLeft)
    {
        if (startLeft
            == null)
        {
            this.startLeft =
                x;
        }
        else
        {
            this.startLeft =
                startLeft;
        }
    }

    public BigDecimal getStartRight()
    {
        if (this.startRight
            == null)
        {
            return x;
        }
        else
        {
            return this.startRight;
        }
    }

    public void setStartRight(BigDecimal startRight)
    {
        if (startRight
            == null)
        {
            this.startRight =
                x;
        }
        else
        {
            this.startRight =
                startRight;
        }
    }

    public BigDecimal getStartTop()
    {
        if (this.startTop
            == null)
        {
            return x;
        }
        else
        {
            return this.startTop;
        }
    }

    public void setStartTop(BigDecimal startTop)
    {
        if (startTop
            == null)
        {
            this.startTop =
                x;
        }
        else
        {
            this.startTop =
                startTop;
        }
    }

    public BigDecimal getStartBottom()
    {
        if (this.startBottom
            == null)
        {
            return x;
        }
        else
        {
            return this.startBottom;
        }
    }

    public void setStartBottom(BigDecimal startBottom)
    {
        if (startBottom
            == null)
        {
            this.startBottom =
                x;
        }
        else
        {
            this.startBottom =
                startBottom;
        }
    }

    public BigDecimal getLimitLeft()
    {
        if (this.limitLeft
            == null)
        {
            return x;
        }
        else
        {
            return this.limitLeft;
        }
    }

    public void setLimitLeft(BigDecimal limitLeft)
    {
        if (limitLeft
            == null)
        {
            this.limitLeft =
                x;
        }
        else
        {
            this.limitLeft =
                limitLeft;
        }
    }

    public BigDecimal getLimitRight()
    {
        if (this.limitRight
            == null)
        {
            return x;
        }
        else
        {
            return this.limitRight;
        }
    }

    public void setLimitRight(BigDecimal limitRight)
    {
        if (limitRight
            == null)
        {
            this.limitRight =
                x;
        }
        else
        {
            this.limitRight =
                limitRight;
        }
    }

    public BigDecimal getLimitTop()
    {
        if (this.limitTop
            == null)
        {
            return x;
        }
        else
        {
            return this.limitTop;
        }
    }

    public void setLimitTop(BigDecimal limitTop)
    {
        if (limitTop
            == null)
        {
            this.limitTop =
                x;
        }
        else
        {
            this.limitTop =
                limitTop;
        }
    }

    public BigDecimal getLimitBottom()
    {
        if (this.limitBottom
            == null)
        {
            return x;
        }
        else
        {
            return this.limitBottom;
        }
    }

    public void setLimitBottom(BigDecimal limitBottom)
    {
        if (limitBottom
            == null)
        {
            this.limitBottom =
                x;
        }
        else
        {
            this.limitBottom =
                limitBottom;
        }
    }

    public ActionMessages getClientErrors()
    {
        return this.clientErrors;
    }

    public void reset()
    {
        clientErrors.clear();
        id =
            i;
        clientId =
            i;
        clientFullName =
            s;
        clientName =
            s;
        imsService =
            s;
        imsOvService =
            s;
        downLoads =
            s;
        startLeft =
            new BigDecimal(0.00);
        startRight =
            new BigDecimal(0.00);
        startTop =
            new BigDecimal(0.00);
        startBottom =
            new BigDecimal(0.00);
        limitLeft =
            new BigDecimal(0.00);
        limitRight =
            new BigDecimal(0.00);
        limitTop =
            new BigDecimal(0.00);
        limitBottom =
            new BigDecimal(0.00);
    }

    public void checkForHTML()
    {
        clientFullName =
            html(clientFullName);
        clientName =
            html(clientName);
        imsService =
            html(imsService);
        imsOvService =
            html(imsOvService);
        downLoads =
            html(downLoads);
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
