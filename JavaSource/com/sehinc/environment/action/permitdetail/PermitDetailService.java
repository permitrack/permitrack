package com.sehinc.environment.action.permitdetail;

import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;

import java.util.ArrayList;
import java.util.List;

public class PermitDetailService
{
    public PermitDetailService()
    {
    }

    public static ArrayList findPermittedAssets(Integer permitDetailId)
        throws Exception
    {
        ArrayList<EnvPermitAsset>
            pmtAssetList =
            new ArrayList<EnvPermitAsset>();
        List
            assetList =
            EnvPermitAsset.findByPermitDetailId(permitDetailId);
        for (Object a : assetList)
        {
            EnvPermitAsset
                pAsset =
                (EnvPermitAsset) a;
            EnvAsset
                asset =
                new EnvAsset(pAsset.getAssetId());
            asset.load();
            if (asset
                != null)
            {
                pAsset.setNumber(asset.getNumber());
                pAsset.setName(asset.getName());
                pAsset.setDescription(asset.getDescription());
            }
            pmtAssetList.add(pAsset);
        }
        return pmtAssetList;
    }
}