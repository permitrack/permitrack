package com.sehinc.erosioncontrol.server.gis;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import com.sehinc.erosioncontrol.value.gis.GISCoordValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GISService
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISService.class);

    public GISService()
    {
    }

    public static List getGISCoordValueList(ClientValue clientValue, String parcelNumber)
    {
        ArrayList
            gisCoordValueList =
            new ArrayList();
        Iterator
            gisCoordValueIterator =
            EcGISCoord.findByParcelNumber(clientValue,
                                          parcelNumber)
                .iterator();
        while (gisCoordValueIterator.hasNext())
        {
            GISCoordValue
                gisCoordValue =
                new GISCoordValue((EcGISCoord) gisCoordValueIterator.next());
            gisCoordValueList.add(gisCoordValue);
        }
        return gisCoordValueList;
    }
}