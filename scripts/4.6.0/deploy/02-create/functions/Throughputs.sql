

/****** Object:  UserDefinedFunction [dbo].[Throughputs]    Script Date: 02/27/2013 18:38:01 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Throughputs]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Throughputs]
GO



/****** Object:  UserDefinedFunction [dbo].[Throughputs]    Script Date: 02/27/2013 18:38:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[Throughputs]
(
    @yearInt int = NULL, 
	@facilityId int = NULL
)
RETURNS 
@asset_actuals TABLE 
(
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
AS
BEGIN
	
	
	 DECLARE 
	    @yearChar varchar(4),
	    @aId int, 
	    @assetId int,
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
    
    ---- ASSET througputs
    --DECLARE @asset_actuals TABLE (
	   -- ASSET_ID int,
	   -- GAS int,
	   -- PAINT int,
	   -- ASSET_NUMBER varchar(20), 
	   -- ASSET_NAME varchar(50),
	   -- ASSET_RATING decimal(18, 6),
	   -- ASSET_PCT_OF_TOTAL decimal(18, 6),
	   -- ASSET_ACTUAL_MMCF decimal(18, 6),
	   -- ASSET_ACTUAL_MMBTU decimal(18, 6),
	   -- ASSET_ACTUAL_PAINT decimal(18, 6)
    --)

    -- METERS
    DECLARE @facility_meters TABLE (
	    A_ID int
    )
    -- ASSETS related to METERS
    INSERT INTO @facility_meters
     SELECT A.ID 
     FROM ENV_FACILITY_ASSET AS FA, 
 	    ENV_ASSET AS A 
     WHERE FA.FACILITY_ID = @facilityId 
     AND A.ID = FA.ASSET_ID 
     AND A.STATUS_CD = 1
     AND A.METER = 1
     
    -- PROCESS ASSETS
    DECLARE @process_equip TABLE (
	    A_ID int,
	    NUMBER varchar(20),
	    NAME varchar(50),
	    RATING decimal(18, 6),
	    LBS_VOC decimal(18, 6)
    )
     	
    -- BUILDING HEAT ASSETS
    DECLARE @building_heat TABLE (
	    A_ID int,
	    NUMBER varchar(20),
	    NAME varchar(50),
	    RATING decimal(18, 6)
    )
     
    WHILE (SELECT COUNT(*) FROM @facility_meters) > 0
        BEGIN
            SELECT TOP 1 @meterId = A_ID FROM @facility_meters
        	
	        -- 1. Sum each process ASSET_RATED and heat ASSET_RATED (by BUILDING or PROCESS flags) to get TOTAL_PROCESS_EQUIPMENT and TOTAL_BUILDING_HEAT
        	
	        -- 1a. Get all process assets for this meter 	
	        INSERT INTO @process_equip
		        SELECT *, 0 FROM Assets_process(@meterId) ORDER BY NUMBER 

	        SELECT @TOTAL_PROCESS_EQUIPMENT = ISNULL(SUM(RATING), 0) FROM @process_equip
        	
	        -- 1b. Get all building heat assets for this meter 
	        INSERT INTO @building_heat
		        SELECT * FROM Assets_heat(@meterId) ORDER BY NUMBER

	        SELECT @TOTAL_BUILDING_HEAT = ISNULL(SUM(RATING), 0) FROM @building_heat
        	
	        -- Get the percentages of the total	
	        SET @PROCESS_EQUIP_PERCENTAGE = @TOTAL_PROCESS_EQUIPMENT / (@TOTAL_PROCESS_EQUIPMENT + @TOTAL_BUILDING_HEAT) 
	        SET @BUILDING_HEAT_PERCENTAGE = @TOTAL_BUILDING_HEAT / (@TOTAL_PROCESS_EQUIPMENT + @TOTAL_BUILDING_HEAT)
        	
	        -- METER_USAGE_READING is the reading off the meter that holds these assets
	        SELECT @METER_USAGE_READING = METER_USAGE_READING FROM Meter_totals(@StartDate, @EndDate, @meterId)
        		
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
        		
		        SET @paint = 0
		        SET @ASSET_ACTUAL_PAINT = 0
        		
		        SET @gas = 1
        		
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

	-- select the PAINT totals and add them to @asset_actuals 
	INSERT INTO @process_equip 
	    SELECT * 
	    FROM   Liquids_totals(@StartDate, @EndDate, @facilityId, NULL, NULL) 

	WHILE (SELECT Count(*) 
		   FROM   @process_equip) > 0 
	   BEGIN 
	      SELECT TOP 1 @aId = a_id 
				       , @aNum = number 
				       , @aName = name 
				       , @ASSET_ACTUAL_PAINT = rating 
	      FROM   @process_equip 

	      --PRINT 'id: ' + @aName 

	      SET @assetId = 0 
	      SET @paint = 1 

	      SELECT @assetId = asset_id 
	      FROM   @asset_actuals 
	      WHERE  asset_id = @aId 

	      IF ( @assetId > 0 ) 
		    BEGIN 
			    --PRINT 'update' 

			    UPDATE @asset_actuals 
			    SET    paint = @paint 
				       , asset_actual_paint = @ASSET_ACTUAL_PAINT 
			    WHERE  asset_id = @aId 
		    END 
	      ELSE 
		    BEGIN 
			    --PRINT 'create' 

			    SET @gas = 0 
			    SET @aRating = 0 
			    SET @ASSET_PCT_OF_TOTAL = 0 
			    SET @ASSET_ACTUAL_MMCF = 0 
			    SET @ASSET_ACTUAL_MMBTU = 0 

			    INSERT INTO @asset_actuals 
						    (asset_id 
						     , gas 
						     , paint 
						     , asset_number 
						     , asset_name 
						     , asset_rating 
						     , asset_pct_of_total 
						     , asset_actual_mmcf 
						     , asset_actual_mmbtu 
						     , asset_actual_paint) 
			    VALUES      (@aId 
						     , @gas 
						     , @paint 
						     , @aNum 
						     , @aName 
						     , @aRating 
						     , @ASSET_PCT_OF_TOTAL 
						     , @ASSET_ACTUAL_MMCF 
						     , @ASSET_ACTUAL_MMBTU 
						     , @ASSET_ACTUAL_PAINT) 
		    END 

	      DELETE FROM @process_equip WHERE  a_id = @aId 
	   END 


	--SELECT * 
	--	FROM @asset_actuals
	--	ORDER BY ASSET_ID
		
	
	RETURN 
END

GO


