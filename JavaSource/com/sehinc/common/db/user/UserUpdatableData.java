package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.value.user.UserValue;

import java.util.Date;

public class UserUpdatableData
    extends HibernateData
{
    Date
        createTimestamp;
    Date
        updateTimestamp;
    Integer
        updateUserId;

    public Date getCreateTimestamp()
    {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp)
    {
        this.createTimestamp =
            createTimestamp;
    }

    public Date getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp)
    {
        this.updateTimestamp =
            updateTimestamp;
    }

    public Integer getUpdateUserId()
    {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId)
    {
        this.updateUserId =
            updateUserId;
    }

    public Integer insert()
    {
        if (true)
        {
            throw new RuntimeException("Must specify user to insert data. Call insert(UserData) instead.");
        }
        return null;
    }

    public Integer insert(User user)
    {
        setCreateTimestamp(new Date());
        setUpdateTimestamp(new Date());
        setUpdateUserId(user.getId());
        return super.insert();
    }

    public Integer insert(UserValue userValue)
    {
        setCreateTimestamp(new Date());
        setUpdateTimestamp(new Date());
        setUpdateUserId(userValue.getId());
        return super.insert();
    }

    public Integer save()
    {
        if (true)
        {
            throw new RuntimeException("Must specify user to save data. Call save(UserData) instead.");
        }
        return null;
    }

    public Integer save(User user)
    {
        if (getId()
            == null)
        {
            return this.insert(user);
        }
        else
        {
            this.update(user);
            return this.getId();
        }
    }

    public void update()
    {
        if (true)
        {
            throw new RuntimeException("Must specify user to update data. Call update(UserData) instead.");
        }
    }

    public void update(User data)
    {
        setUpdateTimestamp(new Date());
        setUpdateUserId(data.getId());
        super.update();
    }
}
