SELECT count(usage.id) as usage_id_count, MIN(usage.id), MAX(usage.ID), MIN(usage.METER_READING), MAX(usage.meter_reading), MIN(usage.PERIOD_START_TS), MAX(usage.PERIOD_START_TS),
       subst.id, 
       subst.name, 
       subst.part_num, 
       ( usage.meter_reading * source.density * sosub.ratio ) AS HAPS, 
       usage.unit_of_measure_cd                               AS UM 
FROM   env_facility AS facility 
       INNER JOIN env_process AS process 
               ON process.facility_id = facility.id 
       INNER JOIN env_process_asset AS prcass 
               ON prcass.process_id = process.id 
       INNER JOIN env_asset AS asset 
               ON asset.id = prcass.asset_id 
       INNER JOIN env_asset_source AS aSo 
               ON aso.asset_id = asset.id 
       INNER JOIN env_source_usage AS usage 
               ON usage.asset_source_id = aSo.id 
       INNER JOIN env_source AS source 
               ON aSo.source_id = source.id 
       INNER JOIN env_source_substance AS sosub 
               ON source.id = sosub.source_id 
       INNER JOIN env_substance AS subst 
               ON sosub.substance_id = subst.id 
       INNER JOIN env_asset_type AS assetType 
               ON assetType.asset_id = asset.id 
WHERE  facility.client_id = ISNULL(NULL, facility.client_id) -- clientId 
	   AND facility.id = ISNULL(1, facility.id)
       AND asset.id = Isnull(NULL, asset.id)
       AND prcass.status_cd = 1
       AND sosub.status_cd = 1 
       AND subst.status_cd = 1 
       AND asset.status_cd = 1 
       AND assetType.asset_type_cd = 1 
       AND usage.unit_of_measure_cd IN ( 1, 2, 3 ) -- gallons, ounces, liters 
       AND usage.period_start_ts >= '01/01/2011'
       AND usage.period_end_ts <= '12/31/2011' 
       AND source.density_um = 6 
       GROUP BY subst.ID, 
		         subst.NAME, 
		         subst.PART_NUM,
		         
		         usage.METER_READING, 
		         		         --usage.ID,
		         source.DENSITY, 
		         sosub.RATIO, 
		         usage.UNIT_OF_MEASURE_CD
		         		         
		         		         
		         		         having COUNT(usage.id) > 1
		         		         
		         		         
	