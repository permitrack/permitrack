package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings(value = {"unused"})
public class CapUserAgent
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(CapUserAgent.class);
    private
    String
        name;
    private
    String
        pattern;
    private
    Integer
        capMarkupId;
    private
    Integer
        evalSequence;

    public CapUserAgent()
    {
    }

    public CapUserAgent(Integer id)
    {
        setId(id);
    }

    public static List getAllUserAgentsOrderedBySequence()
    {
        Object[][]
            parameters =
            {};
        String
            ALL_USER_AGENTS_ORDERED_BY_EVAL_SEQUENCE =
            "com.sehinc.common.db.user.CapUserAgent.allRowsOrderedBySequence";
        return HibernateUtil.findByNamedQuery(ALL_USER_AGENTS_ORDERED_BY_EVAL_SEQUENCE,
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

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern =
            pattern;
    }

    public Integer getCapMarkupId()
    {
        return capMarkupId;
    }

    public void setCapMarkupId(Integer capMarkupId)
    {
        this.capMarkupId =
            capMarkupId;
    }

    public Integer getEvalSequence()
    {
        return evalSequence;
    }

    public void setEvalSequence(Integer evalSequence)
    {
        this.evalSequence =
            evalSequence;
    }
}
