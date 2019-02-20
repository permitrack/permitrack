

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_assets_for_facility_throughputs]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_assets_for_facility_throughputs]
GO

CREATE PROCEDURE dbo.sp_assets_for_facility_throughputs -- latest revision
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@aId int, 
	@meterId int, 
	@METER_USAGE_READING decimal(18,6),
	@TOTAL_PROCESS_EQUIPMENT decimal(18,6),
	@TOTAL_BUILDING_HEAT decimal(18,6),
	@PROCESS_EQUIP_PERCENTAGE decimal(18,6),
	@BUILDING_HEAT_PERCENTAGE decimal(18,6),
	@PROCESS_GAS_USAGE decimal(18,6),
	@BUILDING_GAS_USAGE decimal(18,6),
	@ASSET_PCT_OF_TOTAL decimal(18,6),
	@ASSET_ACTUAL_MMCF decimal(18,6),
	@ASSET_ACTUAL_MMBTU decimal(18,6),
	@HOURS int,
	@TEN_TWENTY decimal(18,6),
	@DIVISOR decimal(18,6),
	@StartDate varchar(10),
	@EndDate varchar(10),
	@aNum varchar(50),
	@aName varchar(50),
	@aRating decimal(18, 6),
	@ASSET_ACTUAL_PAINT decimal(18,6),
	@gas int, 
	@paint int

-- Create dates for use in query
SET @yearChar = @yearInt
SET @StartDate 	= '01-01-' + @yearChar
SET @EndDate 	= '12-31-' + @yearChar

-- get facility hours
SELECT @HOURS = (DAILY_HRS_OP * DAYS_OP_WEEK * WEEKS_OP_YEAR) from env_facility
where id = @facilityId

SET @TEN_TWENTY = 1020

--********************************************--
-- This table holds the asset actuals returned
--********************************************--
DECLARE @asset_actuals TABLE (
	ASSET_ID int,
	GAS int,
	PAINT int,
	ASSET_NUMBER varchar(20), 
	ASSET_NAME varchar(50),
	ASSET_RATING decimal(18, 6),
	ASSET_PCT_OF_TOTAL decimal(18, 6),
	ASSET_ACTUAL_MMCF decimal(18, 6),
	ASSET_ACTUAL_MMBTU decimal(18, 6),
	ASSET_ACTUAL_PAINT decimal(18, 6)
)


--********************************************--
-- table to hold the meters of the facility
--********************************************--
DECLARE @facility_meters TABLE (
	A_ID int
)

-- get all facility's active assets and put ASSET_IDs in the temp table
INSERT INTO @facility_meters
 SELECT A.ID 
 FROM ENV_FACILITY_ASSET AS FA, 
 	ENV_ASSET AS A 
 WHERE FA.FACILITY_ID = @facilityId 
 AND A.ID = FA.ASSET_ID 
 AND A.STATUS_CD = 1
 AND A.METER = 1
 
--********************************************--
-- GET PROCESS ASSET TOTALS
--********************************************--
DECLARE @process_equip TABLE (
	A_ID int,
	NUMBER varchar(20),
	NAME varchar(50),
	RATING decimal(18, 6)
)
 
 	
--********************************************--
-- GET BUILDING HEAT TOTALS
--********************************************--
DECLARE @building_heat TABLE (
	A_ID int,
	NUMBER varchar(20),
	NAME varchar(50),
	RATING decimal(18, 6)
)

 
WHILE (SELECT COUNT(*) FROM @facility_meters) > 0
  BEGIN
    SELECT TOP 1 @meterId = A_ID FROM @facility_meters

	-- sum each process ASSET_RATED and heat ASSET_RATED (by BUILDING or PROCESS flags) to get
	  -- TOTAL_PROCESS_EQUIPMENT and TOTAL_BUILDING_HEAT

	-- get all assets for this meter 
	INSERT INTO @process_equip
	 SELECT 
		BREAKDOWN.ID,
		BREAKDOWN.NUMBER,
		BREAKDOWN.NAME,
		COALESCE ( SUM( BREAKDOWN.SUB_BURNERS + BREAKDOWN.EP_RATED_MMBTU), BREAKDOWN.EP_RATED_MMBTU) AS RATING
	FROM (
		SELECT  A.ID, 
			A.NUMBER,
			A.NAME,
			AA.EP_RATED_MMBTU, 
			SUM(SUB_AA.EP_RATED_MMBTU) AS SUB_BURNERS
		 FROM ENV_ASSET AS A
		 LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = A.ID
		  AND ATYPE.ASSET_TYPE_CD = 2 --natural gas assets
		 LEFT JOIN ENV_ASSET_ATTR AS AA ON AA.ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET AS SUB_A ON SUB_A.PARENT_ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET_ATTR AS SUB_AA ON SUB_AA.ASSET_ID = SUB_A.ID
		 WHERE A.BELONGS_TO_METER = @meterId
		 AND A.STATUS_CD = 1 AND A.PROCESS = 1 --active process assets
		   GROUP BY A.ID, A.NUMBER, A.NAME, AA.EP_RATED_MMBTU
	) AS BREAKDOWN 
	GROUP BY BREAKDOWN.ID, BREAKDOWN.NUMBER, BREAKDOWN.NAME, BREAKDOWN.SUB_BURNERS, BREAKDOWN.EP_RATED_MMBTU
	ORDER BY BREAKDOWN.NUMBER 

	-- sum the total of the ratings
	SET @TOTAL_PROCESS_EQUIPMENT = 0
	SELECT @TOTAL_PROCESS_EQUIPMENT = SUM(RATING) FROM @process_equip
	

	-- get all assets for this meter 
	INSERT INTO @building_heat
	 SELECT 
		BREAKDOWN.ID,
		BREAKDOWN.NUMBER,
		BREAKDOWN.NAME,
		COALESCE ( SUM( BREAKDOWN.SUB_BURNERS + BREAKDOWN.EP_RATED_MMBTU), BREAKDOWN.EP_RATED_MMBTU) AS RATING
	FROM (
		SELECT  A.ID, 
			A.NUMBER,
			A.NAME,
			AA.EP_RATED_MMBTU, 
			SUM(SUB_AA.EP_RATED_MMBTU) AS SUB_BURNERS
		 FROM ENV_ASSET AS A
		 LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = A.ID
		  AND ATYPE.ASSET_TYPE_CD = 2 --natural gas assets
		 LEFT JOIN ENV_ASSET_ATTR AS AA ON AA.ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET AS SUB_A ON SUB_A.PARENT_ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET_ATTR AS SUB_AA ON SUB_AA.ASSET_ID = SUB_A.ID
		 WHERE A.BELONGS_TO_METER = @meterId 
		 AND A.STATUS_CD = 1 AND A.BUILDING_HEAT = 1 --active building heat assets
		   GROUP BY A.ID, A.NUMBER, A.NAME, AA.EP_RATED_MMBTU
	) AS BREAKDOWN 
	GROUP BY BREAKDOWN.ID, BREAKDOWN.NUMBER, BREAKDOWN.NAME, BREAKDOWN.SUB_BURNERS, BREAKDOWN.EP_RATED_MMBTU
	ORDER BY BREAKDOWN.NUMBER 
	

	-- sum the total of the ratings
	SET @TOTAL_BUILDING_HEAT = 0
	SELECT @TOTAL_BUILDING_HEAT = SUM(RATING) FROM @building_heat


	IF @TOTAL_BUILDING_HEAT IS NULL
	 SET @TOTAL_BUILDING_HEAT = 0

	IF @TOTAL_PROCESS_EQUIPMENT IS NULL
	 SET @TOTAL_PROCESS_EQUIPMENT = 0
	
	-- Get the percentages of the total	
	SET @PROCESS_EQUIP_PERCENTAGE = @TOTAL_PROCESS_EQUIPMENT / (@TOTAL_PROCESS_EQUIPMENT + @TOTAL_BUILDING_HEAT) 
	SET @BUILDING_HEAT_PERCENTAGE = @TOTAL_BUILDING_HEAT / (@TOTAL_PROCESS_EQUIPMENT + @TOTAL_BUILDING_HEAT)

	-- METER_USAGE_READING is the reading off the meter that holds these assets
	SELECT @METER_USAGE_READING = SUM(usage.METER_READING)
		FROM ENV_ASSET as asset
		 INNER JOIN ENV_ASSET_SOURCE as aSo
		  ON aso.ASSET_ID = asset.ID
		 INNER JOIN ENV_SOURCE_USAGE as usage
		  ON usage.ASSET_SOURCE_ID = aSo.ID
		 INNER JOIN ENV_SOURCE as source
		  ON aSo.SOURCE_ID = source.ID
		WHERE asset.id = @meterId -- meter id
		AND usage.UNIT_OF_MEASURE_CD = 5 -- MmCF
		AND usage.PERIOD_START_TS >= @StartDate
		AND usage.PERIOD_END_TS <= @EndDate
		
	-- get usage totals for Process assets vs. Building Heat assets
	SET @PROCESS_GAS_USAGE = @METER_USAGE_READING * @PROCESS_EQUIP_PERCENTAGE
	SET @BUILDING_GAS_USAGE = @METER_USAGE_READING * @BUILDING_HEAT_PERCENTAGE

	
	-- that does it for grand totals.
	-- now, use those numbers to break down by asset and get individual totals.
	-- ASSET_PCT_OF_TOTAL = ASSET_RATED / TOTAL_PROCESS_EQUIPMENT
	-- ASSET_ACTUAL_MMCF = ASSET_PCT_OF_TOTAL * PROCESS_GAS_USAGE 
	-- ASSET_ACTUAL_MMBTU = ASSET_ACTUAL_MMCF * (1020/hours)
	
	SET @DIVISOR = @TEN_TWENTY / @HOURS --??? is 1020 a variable...?

	WHILE (SELECT COUNT(*) FROM @process_equip) > 0
	  BEGIN
		SELECT TOP 1 @aId = A_ID, @aNum = NUMBER, @aName = NAME, @aRating = RATING FROM @process_equip
		
		SET @ASSET_PCT_OF_TOTAL = @aRating / @TOTAL_PROCESS_EQUIPMENT
		SET @ASSET_ACTUAL_MMCF = @ASSET_PCT_OF_TOTAL * @PROCESS_GAS_USAGE 
		SET @ASSET_ACTUAL_MMBTU = @ASSET_ACTUAL_MMCF *  @DIVISOR 
		
		-- get paint total
		SELECT @ASSET_ACTUAL_PAINT = COALESCE(SUM( GALLONS.USAGE ), 0) -- usage in gallons
			FROM (
				SELECT
					CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING ) -- gallons, no multiplier
						WHEN 2 THEN ( TOTALS.READING * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
						WHEN 3 THEN ( TOTALS.READING * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
					END USAGE
				FROM (
					SELECT
						usage.METER_READING as READING,
						usage.UNIT_OF_MEASURE_CD as UM
					FROM ENV_ASSET as asset
					 INNER JOIN ENV_ASSET_SOURCE as aSo
					  ON aso.ASSET_ID = asset.ID
					 INNER JOIN ENV_SOURCE_USAGE as usage
					  ON usage.ASSET_SOURCE_ID = aSo.ID
					 INNER JOIN ENV_SOURCE as source
					  ON aSo.SOURCE_ID = source.ID
					INNER JOIN ENV_ASSET_TYPE as assetType ON assetType.ASSET_ID = asset.id
					WHERE asset.id = @aId
					AND asset.status_cd = 1
					AND assetType.ASSET_TYPE_CD = 1
					AND source.source_type_cd = 1 -- paint
					AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
					AND usage.PERIOD_START_TS >= @StartDate
					AND usage.PERIOD_END_TS <= @EndDate
				) AS TOTALS 
			) AS GALLONS 
		
		

		
		SELECT @METER_USAGE_READING = SUM(usage.METER_READING)
		FROM ENV_ASSET as asset
		 INNER JOIN ENV_ASSET_SOURCE as aSo
		  ON aso.ASSET_ID = asset.ID
		 INNER JOIN ENV_SOURCE_USAGE as usage
		  ON usage.ASSET_SOURCE_ID = aSo.ID
		 INNER JOIN ENV_SOURCE as source
		  ON aSo.SOURCE_ID = source.ID
		WHERE asset.id = @meterId -- meter id
		AND usage.UNIT_OF_MEASURE_CD = 5 -- MmCF
		AND usage.PERIOD_START_TS >= @StartDate
		AND usage.PERIOD_END_TS <= @EndDate
		
		SET @paint = 0

		SELECT @paint = CASE asset_type_cd WHEN 1 THEN 1 END 
		FROM env_asset_type WHERE asset_id = @aId
		AND asset_type_cd = 1

		IF @paint IS NULL
		SET @paint = 0
	 
		SET @gas = 0

		SELECT @gas = CASE asset_type_cd WHEN 2 THEN 1 END 
		FROM env_asset_type WHERE asset_id = @aId
		AND asset_type_cd = 2

		IF @gas IS NULL
		SET @gas = 0
		
		-- put the process assets into a master table with their actuals
		INSERT INTO @asset_actuals
		(ASSET_ID, GAS, PAINT, ASSET_NUMBER, ASSET_NAME, ASSET_RATING, ASSET_PCT_OF_TOTAL, ASSET_ACTUAL_MMCF, ASSET_ACTUAL_MMBTU, ASSET_ACTUAL_PAINT)
		VALUES
		(@aId, @gas, @paint, @aNum, @aName, @aRating, @ASSET_PCT_OF_TOTAL, @ASSET_ACTUAL_MMCF, @ASSET_ACTUAL_MMBTU, @ASSET_ACTUAL_PAINT)
		
		-- loop and move on to the next meter id in the result set
		DELETE FROM @process_equip WHERE A_ID = @aId
	END

	-- Get rid of the temporary tables
	DELETE FROM @process_equip
	DELETE FROM @building_heat
	 
	-- loop and move on to the next meter id in the result set
    DELETE FROM @facility_meters WHERE A_ID = @meterId
  END
  

-- values used in the report
SELECT * FROM @asset_actuals
	
GO


