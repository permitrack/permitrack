package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringTokenizer;

public class DvClientInformation
    extends HibernateData
{
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

    public DvClientInformation()
    {
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

    public void setClientFullName(String clientFullName)
    {
        if (clientFullName
            == null)
        {
            this.clientFullName =
                s;
        }
        else
        {
            this.clientFullName =
                clientFullName;
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

    public void setClientName(String clientName)
    {
        if (clientName
            == null)
        {
            this.clientName =
                s;
        }
        else
        {
            this.clientName =
                clientName;
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

    public void setImsService(String imsService)
    {
        if (imsService
            == null)
        {
            this.imsService =
                s;
        }
        else
        {
            this.imsService =
                imsService;
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

    public void setImsOvService(String imsOvService)
    {
        if (imsOvService
            == null)
        {
            this.imsOvService =
                s;
        }
        else
        {
            this.imsOvService =
                imsOvService;
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

    public void setDownLoads(String downLoads)
    {
        if (downLoads
            == null)
        {
            this.downLoads =
                s;
        }
        else
        {
            this.downLoads =
                downLoads;
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

    public void setAttachmentLayers(String attachmentLayers)
    {
        if (attachmentLayers
            == null)
        {
            this.attachmentLayers =
                s;
        }
        else
        {
            this.attachmentLayers =
                attachmentLayers;
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

    public void setStartTop(BigDecimal getStartTop)
    {
        if (getStartTop
            == null)
        {
            this.startTop =
                x;
        }
        else
        {
            this.startTop =
                getStartTop;
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

    public void setStartBottom(BigDecimal value)
    {
        if (value
            == null)
        {
            this.startBottom =
                x;
        }
        else
        {
            this.startBottom =
                value;
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

    public void setLimitLeft(BigDecimal startBottom)
    {
        if (startBottom
            == null)
        {
            this.limitLeft =
                x;
        }
        else
        {
            this.limitLeft =
                startBottom;
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

    public boolean isValidService(String serviceName)
    {
        if (serviceName
            == null)
        {
            return false;
        }
        return serviceName.equals(getImsService())
               || serviceName.equals(getImsOvService());
    }

    public StringTokenizer getAttachmentLayersList()
    {
        return new StringTokenizer(getAttachmentLayers());
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from DvClientInformation as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
