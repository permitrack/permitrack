
--EXEC sp_form_12_grand_total @yearInt=2009, @facilityId=1, @clientId=682

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_form_12_grand_total]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_form_12_grand_total]
GO

CREATE PROCEDURE dbo.sp_form_12_grand_total
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL,
	@clientId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10),
	
	@srcTypeCd int,
	@assetTotalPaintThroughput decimal(18, 6),
	@assetTotalGasThroughput decimal(18, 6),
	@tankTons decimal(18, 6),
	@sourceId int,
	@sourceTotal decimal(18, 6),
	@vocContent decimal(18, 6),
	@density decimal(18, 6),
	@numerator decimal(18, 6),
	@emissionFactor decimal(18, 6),
	@actualEmissions decimal(18, 6),
	@substanceId int,
	@substanceName varchar(50),
	@infoOrigin varchar(50),
	@assetId int,
	@assetTypeCd int,
	@greatest_hap_amt decimal(18, 6),
	@other_hap_amt decimal(18, 6),
	@substanceTypeCd int, 
	@substanceTypeName varchar(50)
	

-- Create dates for use in query
SET @yearChar = @yearInt
SET @StartDate 	= '01-01-' + @yearChar
SET @EndDate 	= '12-31-' + @yearChar

--********************************************--
-- This table holds the substance levels returned
--********************************************--
CREATE TABLE #TEMP_ASSET_SUBSTANCES (
	ASSET_ID int,
	SUBSTANCE_TYPE_CD int,
	SUBSTANCE_TYPE varchar(50),
	ACTUAL_EMISSIONS decimal(18, 6),
	PAINT int,
	GAS int,
	BL int
)

-----------------------------------------
-- all sources for this asset/scc pair --
-----------------------------------------	
DECLARE @sources TABLE (
	SOURCE_ID int
)

-----------------------------------------
-- all substances for this asset/scc pair --
-----------------------------------------	
DECLARE @substances TABLE (
	SUBSTANCE_ID int,
	NG_EM_FACTOR decimal(18, 6)
)

-----------------------------------------
-- numerator to be used in paint calcs --
-----------------------------------------	
DECLARE @numerators TABLE (
	NUMERATOR decimal(18, 6)
)	

-----------------------------------------
-- table to hold greatest haps info    --
-----------------------------------------	
DECLARE @haps_totals TABLE (
	SUBST_ID int,
	SUBST_NAME varchar(50),
	SUBST_NUM varchar(50),
	HAPS decimal(18,6)
)


-----------------------------------------
-- assets --
-----------------------------------------	
DECLARE @assets TABLE (
	ASSET_ID int,
	ASSET_NUMBER varchar(50)
)	

INSERT INTO @assets -- get all facility process assets that had usages this year
SELECT ASS.ID AS ASSET_ID, ASS.NUMBER AS ASSET_NUMBER
 FROM ENV_FACILITY AS FAC
	LEFT JOIN ENV_PROCESS AS PRC ON PRC.FACILITY_ID = FAC.ID
	INNER JOIN ENV_ASSET AS ASS ON ASS.ID = PRC.ASSET_ID
	 AND ASS.STATUS_CD = 1 AND ASS.PROCESS = 1 --active process assets
	LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = ASS.ID
	 AND ATYPE.ASSET_TYPE_CD in ( 1,2 )--natural gas assets and paint
	INNER JOIN ENV_ASSET_SOURCE AS aSo ON aSo.ASSET_ID = ASS.ID
	INNER JOIN ENV_SOURCE_USAGE AS usage ON usage.ASSET_SOURCE_ID = aSo.ID
	 AND usage.PERIOD_START_TS >= @StartDate
	 AND usage.PERIOD_END_TS <= @EndDate 
 WHERE FAC.ID = @facilityId 
  GROUP BY ASS.ID, ASS.NUMBER
  ORDER BY ASS.NUMBER

--********************************************--
-- This table holds the asset actuals returned
--********************************************--
CREATE TABLE #TEMP_ASSET_TOTALS (
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

INSERT INTO #TEMP_ASSET_TOTALS
EXEC sp_assets_for_facility_throughputs @yearInt=@yearInt, @facilityId=@facilityId


WHILE (SELECT COUNT(*) FROM @assets) > 0
  BEGIN
	SELECT TOP 1 @assetId = ASSET_ID FROM @assets

	SET @assetTotalPaintThroughput = 0
	SET @assetTotalGasThroughput = 0
	
	-- get the sources that should be summed in the totals on this page.
	INSERT INTO @sources
		SELECT ASSO.SOURCE_ID 
			FROM ENV_ASSET_SOURCE AS ASSO 
			WHERE ASSO.ASSET_ID = @assetId
			AND ASSO.STATUS_CD = 1 
		 
	WHILE (SELECT COUNT(*) FROM @sources) > 0
	  BEGIN
		SELECT TOP 1 @sourceId = SOURCE_ID FROM @sources
	  
		SET @srcTypeCd = 0
		
		SELECT @srcTypeCd = SOURCE_TYPE_CD 
			FROM ENV_SOURCE
			WHERE ID = @sourceId
	  
	  
		IF (@srcTypeCd = 1) -- PAINT
		BEGIN 
		
			SET @sourceTotal = 0
				
			--- pull straight usages and sum
			SELECT @sourceTotal = COALESCE(SUM( GALLONS.USAGE ), 0) -- usage in gallons
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
						AND aso.SOURCE_ID = @sourceId
						INNER JOIN ENV_SOURCE_USAGE as usage
						ON usage.ASSET_SOURCE_ID = aSo.ID
						INNER JOIN ENV_ASSET_TYPE as assetType ON assetType.ASSET_ID = asset.id
					WHERE asset.id = @assetId
						AND asset.status_cd = 1
						AND assetType.ASSET_TYPE_CD = 1
						AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
						AND usage.PERIOD_START_TS >= @StartDate
						AND usage.PERIOD_END_TS <= @EndDate
				) AS TOTALS 
			) AS GALLONS 
				
			-- sum the entire total
			SET @assetTotalPaintThroughput = @assetTotalPaintThroughput + @sourceTotal
			
			-- output all VOCs according to pam's calculation -- no other substance types
			SELECT @vocContent = LBS_VOC, @density = DENSITY FROM ENV_SOURCE WHERE ID = @sourceId
			-- calculate the numerator (needed for final emission factor calculation)
			SET @numerator = @vocContent * @density * @sourceTotal
			-- these entries will be summed later
			INSERT INTO @numerators (NUMERATOR) VALUES (@numerator)
		END
	  
		IF (@srcTypeCd = 2) -- NATURAL GAS
		BEGIN
		
			--- pull meter reading data
			SELECT @assetTotalGasThroughput = ASSET_ACTUAL_MMCF 
				FROM #TEMP_ASSET_TOTALS
				WHERE ASSET_ID = @assetId
		
			SELECT @infoOrigin = INFO_ORIGIN
			FROM ENV_SOURCE WHERE ID = @sourceId
			
			INSERT INTO @substances			
				SELECT SUBSTANCE_ID, NG_EM_FACTOR 
				FROM ENV_SOURCE_SUBSTANCE 
				WHERE SOURCE_ID = @sourceId 
				AND STATUS_CD = 1
				AND NG_EF_UNIT = 8
				
			-- loop per substance
			-- output the emission factor for each substance on the single nat gas source
			WHILE (SELECT COUNT(*) FROM @substances) > 0
			  BEGIN
				SELECT TOP 1 @substanceId = SUBSTANCE_ID, @emissionFactor = NG_EM_FACTOR FROM @substances	
							
				SELECT @substanceTypeCd = SUB.SUBSTANCE_TYPE_CD, @substanceTypeName = TYPE.DESCRIPTION
				FROM ENV_SUBSTANCE AS SUB,
					LOOKUP_ENV_SUBSTANCE_TYPE AS TYPE
				WHERE SUB.ID = @substanceId
					AND TYPE.CODE = SUB.SUBSTANCE_TYPE_CD
				
				SET @actualEmissions = @emissionFactor * @assetTotalGasThroughput

				SET @actualEmissions = @actualEmissions / 2000 -- TONS

				-- insert substance totals into table
				INSERT INTO #TEMP_ASSET_SUBSTANCES
				(ASSET_ID, SUBSTANCE_TYPE_CD, SUBSTANCE_TYPE, ACTUAL_EMISSIONS, PAINT, GAS, BL)
				VALUES
				(@assetId, @substanceTypeCd, @substanceTypeName, @actualEmissions, 0, 1, 0)	
				
				-- loop and move on to the next meter id in the result set
				DELETE FROM @substances WHERE SUBSTANCE_ID = @substanceId
			END -- end substance loop
		END
	  
		-- loop and move on to the next id in the result set
		DELETE FROM @sources WHERE SOURCE_ID = @sourceId
	  END -- no more sources
	 
	 
	SET @assetTypeCd = 0
	
	SELECT @assetTypeCd = ASSET_TYPE_CD
	FROM ENV_ASSET_TYPE
	WHERE ASSET_ID = @assetId
	AND ASSET_TYPE_CD = 1 

	-- FOR PAINT SOURCES
	IF (@assetTypeCd = 1)
	BEGIN
		-- Get the emission factor
		SELECT @emissionFactor = SUM(NUMERATOR) FROM @numerators

		SET @emissionFactor = @emissionFactor / @assetTotalPaintThroughput

		SET @actualEmissions = @emissionFactor * @assetTotalPaintThroughput

		SET @actualEmissions = @actualEmissions / 2000 -- TONS

		INSERT INTO #TEMP_ASSET_SUBSTANCES
		(ASSET_ID, SUBSTANCE_TYPE_CD, SUBSTANCE_TYPE, ACTUAL_EMISSIONS, PAINT, GAS, BL)
		VALUES
		(@assetId, -1, 'VOC', @actualEmissions, 1, 0, 0)	

		DELETE FROM @numerators
		
		-- COLLECT GREATEST HAPS DATA FOR THIS ASSET
		SET @greatest_hap_amt = 0
		SET @other_hap_amt = 0
		
		INSERT INTO @haps_totals
		select distinct s.SUBST_ID,
			s.SUBST_NAME,
			s.SUBST_NUM,
			s.HAPS
		from (
			SELECT
				GRAND_TOTALS.ID AS SUBST_ID,
				GRAND_TOTALS.NAME AS SUBST_NAME,
				GRAND_TOTALS.PART_NUM AS SUBST_NUM,
				SUM(GRAND_TOTALS.HAPS) AS HAPS
			FROM ( 
				SELECT
					TOTALS.ID,
					TOTALS.NAME, 
					TOTALS.PART_NUM,
					CASE  TOTALS.UM WHEN 1 THEN ( HAPS ) -- gallons, no multiplier
					WHEN 2 THEN ( HAPS * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
					WHEN 3 THEN ( HAPS * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
					END HAPS
				FROM (
					select distinct 
						subst.ID, subst.NAME, subst.PART_NUM,
						( usage.METER_READING * source.DENSITY * sosub.RATIO) AS HAPS,
						usage.UNIT_OF_MEASURE_CD AS UM
					FROM ENV_ASSET as asset
						INNER JOIN ENV_ASSET_SOURCE as aSo
						ON aso.ASSET_ID = asset.id
						INNER JOIN ENV_SOURCE_USAGE as usage
						ON usage.ASSET_SOURCE_ID = aSo.ID
						INNER JOIN ENV_SOURCE as source
						ON aSo.SOURCE_ID = source.ID
						INNER JOIN ENV_SOURCE_SUBSTANCE as sosub
						ON source.ID = sosub.SOURCE_ID
						AND sosub.STATUS_CD = 1
						INNER JOIN ENV_SUBSTANCE as subst
						ON sosub.SUBSTANCE_ID = subst.ID
						AND subst.STATUS_CD = 1
						INNER JOIN ENV_ASSET_TYPE as assetType 
						ON assetType.ASSET_ID = asset.id
					WHERE asset.id = @assetId
						AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
						AND usage.PERIOD_START_TS >= @StartDate
						AND usage.PERIOD_END_TS <= @EndDate
						AND source.DENSITY_UM = 6
				) AS TOTALS 
			) AS GRAND_TOTALS GROUP BY GRAND_TOTALS.ID, GRAND_TOTALS.NAME, GRAND_TOTALS.PART_NUM 
		) s
		GROUP BY s.SUBST_ID, s.SUBST_NAME, s.SUBST_NUM, s.HAPS
		
		SELECT @greatest_hap_amt = great.HAPS
		from @haps_totals as great
		where great.HAPS = (select max(HAPS) from @haps_totals);

		SELECT @other_hap_amt = (total.HAPS - ghap.HAPS) 
		FROM (
			SELECT sum(alll.HAPS) HAPS
				from @haps_totals as alll
			) total,
		(
		SELECT b.HAPS
			from @haps_totals b
		where b.HAPS = (select max(HAPS) from @haps_totals)
		)ghap
		
		
		DELETE FROM @haps_totals
		
		SET @greatest_hap_amt = @greatest_hap_amt / 2000 --tons
		SET @other_hap_amt = @other_hap_amt / 2000 --tons
		
		INSERT INTO #TEMP_ASSET_SUBSTANCES
		(ASSET_ID, SUBSTANCE_TYPE_CD, SUBSTANCE_TYPE, ACTUAL_EMISSIONS, PAINT, GAS, BL)
		VALUES
		(@assetId, -2, 'Greatest Single HAP', @greatest_hap_amt, 1, 0, 0)	

		INSERT INTO #TEMP_ASSET_SUBSTANCES
		(ASSET_ID, SUBSTANCE_TYPE_CD, SUBSTANCE_TYPE, ACTUAL_EMISSIONS, PAINT, GAS, BL)
		VALUES
		(@assetId, -3, 'Other HAPs', @other_hap_amt, 1, 0, 0)	


	END

	DELETE FROM @assets WHERE ASSET_ID = @assetId
END -- end asset loop


-- reformat data into final workable results table

DECLARE 
	@f_a_id int,
	@f_sub_type_cd int,
	@f_act_emissions decimal(18, 6),
	@VOC decimal(18, 6),
	@GreatestHAP decimal(18, 6),
	@OtherHaps decimal(18, 6),
	@insert_row int,
	@master_asset_id int,
	@asset_number varchar(50),
	@CO decimal(18, 6),
	@NH3 decimal(18, 6),
	@NOx decimal(18, 6),
	@Leads decimal(18, 6),
	@PM10 decimal(18, 6),
	@PM2_5 decimal(18, 6),
	@SOx decimal(18, 6)

------------------------------------------
-- holding table for asset subst levels --
------------------------------------------	
DECLARE @holding_table TABLE (
	ASSET_ID int,
	SUBSTANCE_TYPE_CD int,
	ACTUAL_EMISSIONS decimal(18, 6)
)

------------------------------------------
-- final results table
------------------------------------------
DECLARE @results TABLE (
	ASSET_ID int, 
	ASSET_NUMBER varchar(50), 
	CO decimal(18, 6), 
	NH3 decimal(18, 6), 
	NOX decimal(18, 6), 
	LEADS decimal(18, 6), 
	PM10 decimal(18, 6), 
	PM2_5 decimal(18, 6), 
	SOX decimal(18, 6), 
	VOC decimal(18, 6), 
	GREATEST_HAP decimal(18, 6),
	OTHER_HAPS decimal(18, 6)
)	

WHILE (SELECT COUNT(*) FROM #TEMP_ASSET_SUBSTANCES) > 0
	BEGIN 
		SELECT TOP 1 @master_asset_id = ASSET_ID FROM #TEMP_ASSET_SUBSTANCES
	
		SELECT @asset_number = NUMBER -- get asset number for insertion later
		FROM ENV_ASSET
		WHERE ID = @master_asset_id
	
		-- select all rows for asset / paint and insert into temp table
		-- combine rows for VOC, Greatest HAP, and Other Haps into single row by asset
		-- -1 VOC	
		-- -2 Greatest Single HAP	
		-- -3 Other HAPs	
		
		-- insert into holding_table
		INSERT INTO @holding_table
		SELECT ASSET_ID, SUBSTANCE_TYPE_CD, ACTUAL_EMISSIONS
			FROM #TEMP_ASSET_SUBSTANCES 
			WHERE ASSET_ID = @master_asset_id
			AND PAINT = 1
		
		-- initialize variables for building the paint insert row
		SET @VOC = 0
		SET @GreatestHAP = 0
		SET @OtherHaps = 0
		SET @insert_row = 0
		SET @CO = 0
		SET @NH3 = 0
		SET @NOx = 0
		SET @Leads = 0
		SET @PM10 = 0
		SET @PM2_5 = 0
		SET @SOx = 0
		
		WHILE (SELECT COUNT(*) FROM @holding_table) > 0 -- while (rows in holding_table)
		BEGIN
			SELECT TOP 1
				@f_a_id = ASSET_ID,
				@f_sub_type_cd = SUBSTANCE_TYPE_CD,
				@f_act_emissions = ACTUAL_EMISSIONS
			FROM @holding_table
  
			-- if SUBSTANCE_TYPE_CD = -1
				-- create VOC portion of data set
			IF (@f_sub_type_cd = -1)
			BEGIN
				SET @VOC = @f_act_emissions
				SET @insert_row = 1
			END
				
			-- if SUBSTANCE_TYPE_CD = -2
				-- create greatest HAP portion of data set
			IF (@f_sub_type_cd = -2)
			BEGIN
				SET @GreatestHAP = @f_act_emissions
				SET @insert_row = 1
			END
			
			-- if SUBSTANCE_TYPE_CD = -3
				-- create other haps portion of data set
			IF (@f_sub_type_cd = -3)
			BEGIN
				SET @OtherHaps = @f_act_emissions
				SET @insert_row = 1
			END
			
			-- loop and move on to the next id in the result set
			DELETE FROM @holding_table WHERE ASSET_ID = @f_a_id AND SUBSTANCE_TYPE_CD = @f_sub_type_cd
		END -- end PAINT loop
			
		-- insert row into the final table with all the data.
		IF (@insert_row = 1)
		BEGIN
			INSERT INTO @results
			(ASSET_ID, ASSET_NUMBER, CO, NH3, NOX, LEADS, PM10, PM2_5, SOX, VOC, GREATEST_HAP, OTHER_HAPS)
			VALUES
			(@f_a_id, @asset_number, @CO, @NH3, @NOx, @Leads, @PM10, @PM2_5, @SOx, @VOC, @GreatestHAP, @OtherHaps )
		END
		
		DELETE FROM @holding_table -- clear rows
		
		-- insert into holding_table
		INSERT INTO @holding_table
		SELECT ASSET_ID, SUBSTANCE_TYPE_CD, ACTUAL_EMISSIONS
			FROM #TEMP_ASSET_SUBSTANCES 
			WHERE ASSET_ID = @master_asset_id
			AND GAS = 1
		
		-- initialize variables for building the gas insert row
		SET @VOC = 0
		SET @GreatestHAP = 0
		SET @OtherHaps = 0
		SET @insert_row = 0
		SET @CO = 0
		SET @NH3 = 0
		SET @NOx = 0
		SET @Leads = 0
		SET @PM10 = 0
		SET @PM2_5 = 0
		SET @SOx = 0
		
		-- select all rows for asset / gas and insert into temp table
		-- combine rows for sub types of Gas into single row by asset
		-- 1	VOC
		-- 4	CO
		-- 5	NH3
		-- 6	NOx
		-- 7	Lead
		-- 8	PM10
		-- 9	PM2.5
		-- 10	SOx

		WHILE (SELECT COUNT(*) FROM @holding_table) > 0 -- while (rows in holding_table)
		BEGIN
			SELECT TOP 1
				@f_a_id = ASSET_ID,
				@f_sub_type_cd = SUBSTANCE_TYPE_CD,
				@f_act_emissions = ACTUAL_EMISSIONS
			FROM @holding_table
	
			-- build the insert statement, as defined above.
			IF (@f_sub_type_cd = 1) -- VOC
			BEGIN
				SET @VOC = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 4) -- CO
			BEGIN
				SET @CO = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 5) -- NH3
			BEGIN
				SET @NH3 = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 6) -- NOx
			BEGIN
				SET @NOx = @f_act_emissions
				SET @insert_row = 1
			END
	
			IF (@f_sub_type_cd = 7) -- Lead
			BEGIN
				SET @Leads = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 8) -- PM10
			BEGIN
				SET @PM10 = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 9) -- PM2.5
			BEGIN
				SET @PM2_5 = @f_act_emissions
				SET @insert_row = 1
			END
			
			IF (@f_sub_type_cd = 10) -- SOx
			BEGIN
				SET @SOx = @f_act_emissions
				SET @insert_row = 1
			END
			
			-- loop and move on to the next id in the result set
			DELETE FROM @holding_table WHERE ASSET_ID = @f_a_id AND SUBSTANCE_TYPE_CD = @f_sub_type_cd
		END -- end GAS loop
		
		-- insert row into the final table with all the data.
		IF (@insert_row = 1)
		BEGIN
			INSERT INTO @results
			(ASSET_ID, ASSET_NUMBER, CO, NH3, NOX, LEADS, PM10, PM2_5, SOX, VOC, GREATEST_HAP, OTHER_HAPS)
			VALUES
			(@f_a_id, @asset_number, @CO, @NH3, @NOx, @Leads, @PM10, @PM2_5, @SOx, @VOC, @GreatestHAP, @OtherHaps )
		END
		
		DELETE FROM @holding_table -- clear rows
	
	-- loop and move on to the next id in the result set
	DELETE FROM #TEMP_ASSET_SUBSTANCES WHERE ASSET_ID = @master_asset_id
END -- end TEMP_ASSET_SUBSTANCES loop
 
-- after asset loop
-- perform insertion of Bulk Liquid tank VOCs
DECLARE @tanktotal decimal(18,6)
SET @tanktotal = 0

EXEC sp_form_12_tanks @yearInt=2009, @clientId=682, @facilityId=1, @result=@tanktotal OUTPUT

INSERT INTO @results
(ASSET_ID, ASSET_NUMBER, CO, NH3, NOX, LEADS, PM10, PM2_5, SOX, VOC, GREATEST_HAP, OTHER_HAPS)
VALUES
(-1, 'Form 10.1', 0, 0, 0, 0, 0, 0, 0, @tanktotal, 0, 0 )

 
 --DROP TABLE #TEMP_TANK_VOC
 DROP TABLE #TEMP_ASSET_SUBSTANCES
 DROP TABLE #TEMP_ASSET_TOTALS
 
 SELECT SUM(CO) AS CO,
	SUM(NH3) AS NH3, 
	SUM(NOX) AS NOX, 
	SUM(LEADS) AS LEADS, 
	SUM(PM10) AS PM10, 
	SUM(PM2_5) AS PM2_5, 
	SUM(SOX) AS SOX, 
	SUM(VOC) AS VOC, 
	SUM(GREATEST_HAP) AS GREATEST_HAP, 
	SUM(OTHER_HAPS) AS OTHER_HAPS
 FROM @results

 
 