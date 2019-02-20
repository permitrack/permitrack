	         		         
		         		         --select * from env_process
		         		         
select asset.ID as asset_id 	, asset.NAME	   , SUM(usage.METER_READING)      	, source.ID, sosub.RATIO	         
FROM   
--env_facility AS facility 
--       INNER JOIN env_process AS process 
--               ON process.facility_id = facility.id 
--       INNER JOIN env_process_asset AS prcass 
--               ON prcass.process_id = process.id 
--       INNER JOIN 
       env_asset AS asset 
               --ON asset.id = prcass.asset_id 
       INNER JOIN env_asset_source AS aSo 
               ON aso.asset_id = asset.id 
       INNER JOIN env_source_usage AS usage 
               ON usage.asset_source_id = aSo.id 
       INNER JOIN env_source AS source 
               ON aSo.source_id = source.id 
       INNER JOIN env_source_substance AS sosub 
               ON source.id = sosub.source_id 
       --INNER JOIN env_substance AS subst 
       --        ON sosub.substance_id = subst.id 
       INNER JOIN env_asset_type AS assetType 
               ON assetType.asset_id = asset.id 
WHERE  --facility.client_id = ISNULL(NULL, facility.client_id) -- clientId 
	   --AND 
	   --facility.id = ISNULL(1, facility.id)
       --AND asset.id = Isnull(NULL, asset.id)
       asset.CLIENT_ID = 682
       --AND 
       --prcass.status_cd = 1
       AND sosub.status_cd = 1 
       --AND subst.status_cd = 1 
       AND 
       asset.status_cd = 1 
       AND assetType.asset_type_cd = 1 
       --AND usage.unit_of_measure_cd IN ( 1, 2, 3 ) -- gallons, ounces, liters 
       --AND usage.period_start_ts >= '01/01/2011'
       --AND usage.period_end_ts <= '12/31/2011' 
       --AND source.density_um = 6 
       --ORDER BY subst.ID, 
		     --    subst.NAME, 
		     --    subst.PART_NUM,
		         
		     --    usage.METER_READING, 
		     --    		         usage.ID,
		     --    source.DENSITY, 
		     --    sosub.RATIO, 
		     --    usage.UNIT_OF_MEASURE_CD
group by asset.id, asset.NAME  , source.id	        , sosub.RATIO