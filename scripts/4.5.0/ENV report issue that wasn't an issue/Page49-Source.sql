
				SELECT
					usage.METER_READING as READING,
					usage.UNIT_OF_MEASURE_CD as UM
				FROM ENV_ASSET as asset
					INNER JOIN ENV_ASSET_SOURCE as aSo
					ON aso.ASSET_ID = asset.ID
					AND aso.SOURCE_ID = 10
					INNER JOIN ENV_SOURCE_USAGE as usage
					ON usage.ASSET_SOURCE_ID = aSo.ID
					INNER JOIN ENV_ASSET_TYPE as assetType ON assetType.ASSET_ID = asset.id
				WHERE asset.id = 16
					AND asset.status_cd = 1
					AND assetType.ASSET_TYPE_CD = 1
					AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
					AND usage.PERIOD_START_TS >= '01/01/2010'
					AND usage.PERIOD_END_TS <= '12/31/2010'