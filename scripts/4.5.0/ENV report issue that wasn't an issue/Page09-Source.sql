SELECT asset.id as ASSET_ID, 
					asset.name as ASSET_NAME,
					asset.number as NUMBER,
					usage.METER_READING as READING,
					usage.UNIT_OF_MEASURE_CD as UM,
					source.ID as sourceid
				FROM ENV_FACILITY as facility
					INNER JOIN ENV_PROCESS as process
						ON process.facility_id = facility.id
					INNER JOIN ENV_PROCESS_ASSET as prcass
						ON prcass.process_id = process.id
					INNER JOIN ENV_ASSET as asset
						ON asset.ID = prcass.ASSET_ID
					INNER JOIN ENV_ASSET_SOURCE as aSo
						ON aso.ASSET_ID = asset.id
					INNER JOIN ENV_SOURCE_USAGE as usage
						ON usage.ASSET_SOURCE_ID = aSo.ID
						AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
						AND usage.PERIOD_START_TS >= '01/01/2010'
						AND usage.PERIOD_END_TS <= '12/31/2010'
					INNER JOIN ENV_SOURCE as source
						ON aSo.SOURCE_ID = source.ID
						AND source.source_type_cd = 1 -- paint sources
					INNER JOIN ENV_ASSET_TYPE as assetType 
						ON assetType.ASSET_ID = asset.id
						AND assetType.ASSET_TYPE_CD = 1
				WHERE facility.id = 1 and asset.ID=16