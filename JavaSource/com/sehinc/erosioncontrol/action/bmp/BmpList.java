package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.erosioncontrol.value.bmp.BMPValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BmpList
{
    private static
    Logger
        LOG =
        Logger.getLogger(BmpList.class);
    private
    Hashtable
        bmps =
        new Hashtable();

    private void initBmp(int index)
    {
        if (this.bmps
                .get(new Integer(index))
            == null)
        {
            this.bmps
                .put(new Integer(index),
                     new BMPValue());
        }
    }

    public BMPValue getBmp0()
    {
        initBmp(0);
        return (BMPValue) this.bmps
            .get(new Integer(0));
    }

    public void setBmp0(BMPValue bmp)
    {
        this.bmps
            .put(new Integer(0),
                 bmp);
    }
}
