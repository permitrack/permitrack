package com.sehinc.stormwater.server.plan;

import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;

import java.io.File;

public class OutfallInspectionService
{
    public OutfallInspectionService()
    {
    }

    public static File getOutfallInspectionDigitalPhotoFile(Integer digitalPhotoFileLocationId)
    {
        GoalActivityFileLocationData
            digitalPhotoFileLocation =
            new GoalActivityFileLocationData();
        digitalPhotoFileLocation.setId(digitalPhotoFileLocationId);
        if (digitalPhotoFileLocation.load())
        {
            return new File(digitalPhotoFileLocation.getLocation(),
                            digitalPhotoFileLocation.getName());
        }
        return null;
    }
}