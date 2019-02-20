package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings(value = {"unused"})
public class CapMarkup
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(CapMarkup.class);
    private
    String
        name;
    private
    String
        markup;

    public CapMarkup()
    {
    }

    public CapMarkup(Integer id)
    {
        setId(id);
    }

    public static List getCapMarkupByMarkupId(Integer markupId)
    {
        Object[][]
            parameters =
            {
                {
                    "markupId",
                    markupId}};
        String
            GET_MARKUP_BY_MARKUP_ID =
            "com.sehinc.common.db.user.CapMarkup.byMarkupId";
        return HibernateUtil.findByNamedQuery(GET_MARKUP_BY_MARKUP_ID,
                                              parameters);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getMarkup()
    {
        return markup;
    }

    public void setMarkup(String markup)
    {
        this.markup =
            markup;
    }
}
