
/****** Object:  StoredProcedure [dbo].[sp_form_12_by_asset]    Script Date: 02/18/2013 09:15:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_form_12_by_asset] 
  -- Required Parameters 
  @yearInt      INT = NULL 
  , @facilityId INT = NULL 
  , @clientId   INT = NULL 
  --, @wantresultset bit = 1
AS 
    DECLARE @yearChar                  VARCHAR(4), 
            @StartDate                 VARCHAR(10), 
            @EndDate                   VARCHAR(10), 
            @srcTypeCd                 INT, 
            @assetTotalPaintThroughput DECIMAL(18, 6), 
            @assetTotalGasThroughput   DECIMAL(18, 6)
            , @assetTotalGasThroughput2 decimal(18, 6)
            , @tankTons                  DECIMAL(18, 6), 
            @sourceId                  INT, 
            @sourceTotal               DECIMAL(18, 6), 
            @vocContent                DECIMAL(18, 6), 
            @density                   DECIMAL(18, 6), 
            @numerator                 DECIMAL(18, 6), 
            @emissionFactor            DECIMAL(18, 6), 
            @actualEmissions           DECIMAL(18, 6), 
            @substanceId               INT, 
            @substanceName             VARCHAR(50), 
            @assetId                   INT, 
            @assetTypeCd               INT, 
            @greatest_hap_amt          DECIMAL(18, 6), 
            @other_hap_amt             DECIMAL(18, 6), 
            @VOCnoHAPs                 DECIMAL(18, 6),
            @substanceTypeCd           INT, 
            @substanceTypeName         VARCHAR(50), 
            @greatestHapId             INT,
            @wantresultset             bit = 0

    -- Create dates for use in query 
    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    --********************************************-- 
    -- This table holds the substance levels returned 
    --********************************************-- 
    CREATE TABLE #temp_asset_substances 
      ( 
         asset_id            INT 
         , substance_type_cd INT 
         , substance_type    VARCHAR(50) 
         , actual_emissions  DECIMAL(18, 6) 
         , paint             INT 
         , gas               INT 
         , bl                INT 
      ) 

    ----------------------------------------- 
    -- all sources for this asset/scc pair -- 
    ----------------------------------------- 
    DECLARE @sources TABLE 
      ( 
         source_id        INT 
         , source_type_cd INT 
         , lbs_voc        DECIMAL(18, 6) 
      ) 
    ----------------------------------------- 
    -- all substances for this asset/scc pair -- 
    ----------------------------------------- 
    DECLARE @substances TABLE 
      ( 
         substance_id   INT 
         , ng_em_factor DECIMAL(18, 6) 
      ) 
    ----------------------------------------- 
    -- numerator to be used in paint calcs -- 
    ----------------------------------------- 
    DECLARE @numerators TABLE 
      ( 
         numerator DECIMAL(18, 6) 
      ) 
    ----------------------------------------- 
    -- table to hold haps info    -- 
    ----------------------------------------- 
    DECLARE @haps_totals TABLE 
      ( 
         subst_id     INT 
         , subst_name VARCHAR(50) 
         , subst_num  VARCHAR(50) 
         , haps       DECIMAL(18, 6) 
      ) 

    ----------------------------------------- 
    -- table to hold greatest haps info    -- 
    ----------------------------------------- 
    CREATE TABLE #greatest_hap 
      ( 
         subst_id     INT 
         , subst_name VARCHAR(50) 
         , haps       DECIMAL(18, 6) 
      ) 

    INSERT INTO #greatest_hap 
    EXEC Sp_greatest_hap 
      @yearInt=@yearInt, 
      @facilityId=@facilityId 

    SELECT TOP 1 @greatestHapId = subst_id 
    FROM   #greatest_hap 

    DROP TABLE #greatest_hap 

    --********************************************-- 
    -- This table holds the asset actuals returned 
    --********************************************-- 
    CREATE TABLE #temp_asset_totals 
      ( 
         asset_id             INT 
         , gas                INT 
         , paint              INT 
         , asset_number       VARCHAR(20) 
         , asset_name         VARCHAR(50) 
         , asset_rating       DECIMAL(18, 6) 
         , asset_pct_of_total DECIMAL(18, 6) 
         , asset_actual_mmcf  DECIMAL(18, 6) 
         , asset_actual_mmbtu DECIMAL(18, 6) 
         , asset_actual_paint DECIMAL(18, 6) 
      ) 

    INSERT INTO #temp_asset_totals 
    EXEC Sp_assets_for_facility_throughputs 
      @yearInt=@yearInt, 
      @facilityId=@facilityId 

    -- assets -- 
    DECLARE @assets TABLE 
      ( 
         asset_id       INT 
         , asset_number VARCHAR(50) 
      ) 

    INSERT INTO @assets 
    SELECT asset_id 
           , asset_number 
    FROM   #temp_asset_totals 

    WHILE (SELECT Count(*) 
           FROM   @assets) > 0 
      BEGIN 
          SELECT TOP 1 @assetId = asset_id 
          FROM   @assets 

			--- pull meter reading data
			SELECT 
				@assetTotalGasThroughput =  ASSET_ACTUAL_MMCF
				, @assetTotalGasThroughput2 = ASSET_ACTUAL_MMBTU
				FROM #TEMP_ASSET_TOTALS
				WHERE ASSET_ID = @assetId
				
          INSERT INTO #temp_asset_substances 
          SELECT asset_id 
                 , substance_type_cd 
                 , substance_type 
                 , actual_emissions 
                 , paint 
                 , gas 
                 , bl 
          FROM   Emissions(@yearInt, @facilityId, NULL, @assetId, @assetTotalGasThroughput, @assetTotalGasThroughput2, NULL) 

          SET @assetTypeCd = 0 

          SELECT @assetTypeCd = asset_type_cd 
          FROM   env_asset_type 
          WHERE  asset_id = @assetId 
             AND asset_type_cd = 1 

          -- HAPs for PAINT assets
          IF ( @assetTypeCd = 1 ) 
            BEGIN 
                SET @actualEmissions = 0
                
                SELECT @actualEmissions = actual_emissions
				  FROM   #temp_asset_substances 
				  WHERE  substance_type_cd = -1
					 AND asset_id = @assetId
					 AND paint = 1 

                -- COLLECT GREATEST HAPS DATA FOR THIS ASSET 
                SET @greatest_hap_amt = 0 
                SET @other_hap_amt = 0 

                -- Get the totals for the greatest hap 
                INSERT INTO @haps_totals 
                SELECT s.subst_id 
                       , s.subst_name 
                       , s.subst_num 
                       , s.haps 
                FROM   Haps_totals(@StartDate, @EndDate, NULL, NULL, @assetId) s 

                -- Get the total for the Facility-wide "Greatest Hap" 
                SELECT @greatest_hap_amt = great.haps 
                FROM   @haps_totals AS great 
                WHERE  great.subst_id = @greatestHapId 

                SELECT @other_hap_amt = total.haps 
                FROM   (SELECT Sum(alll.haps) HAPS 
                        FROM   @haps_totals AS alll) total 

                IF @greatest_hap_amt IS NULL 
                  SET @greatest_hap_amt = 0 

                SET @other_hap_amt = @other_hap_amt - @greatest_hap_amt 

                IF @other_hap_amt IS NULL 
                  SET @other_hap_amt = 0 

                DELETE FROM @haps_totals 

                SET @greatest_hap_amt = @greatest_hap_amt / 2000 --tons 
                SET @other_hap_amt = @other_hap_amt / 2000 --tons 
                
                
                INSERT INTO #temp_asset_substances 
                            (asset_id 
                             , substance_type_cd 
                             , substance_type 
                             , actual_emissions 
                             , paint 
                             , gas 
                             , bl) 
                VALUES      (@assetId 
                             , -2 
                             , 'Greatest Single HAP' 
                             , @greatest_hap_amt 
                             , 1 
                             , 0 
                             , 0) 

                INSERT INTO #temp_asset_substances 
                            (asset_id 
                             , substance_type_cd 
                             , substance_type 
                             , actual_emissions 
                             , paint 
                             , gas 
                             , bl) 
                VALUES      (@assetId 
                             , -3 
                             , 'Other HAPs' 
                             , @other_hap_amt 
                             , 1 
                             , 0 
                             , 0) 
                             
				INSERT INTO #temp_asset_substances
					        (asset_id
					         , substance_type_cd
					         , substance_type
					         , actual_emissions
					         , paint
					         , gas
					         , bl)
				VALUES      (@assetId
							 , -4
							 , 'VOCs excluding HAPs'
							 , @actualEmissions - (@greatest_hap_amt + @other_hap_amt)
							 , 1
							 , 0
							 , 0)	
                    
            END 

          DELETE FROM @assets 
          WHERE  asset_id = @assetId 
      END -- end asset loop 
      

      
      
    -- reformat data into final workable results table 
    DECLARE @f_a_id          INT, 
            @f_sub_type_cd   INT, 
            @f_act_emissions DECIMAL(18, 6), 
            @VOC             DECIMAL(18, 6), 
            @GreatestHAP     DECIMAL(18, 6), 
            @OtherHaps       DECIMAL(18, 6), 
            @insert_row      INT, 
            @master_asset_id INT, 
            @asset_number    VARCHAR(50), 
            @CO              DECIMAL(18, 6), 
            @NH3             DECIMAL(18, 6), 
            @NOx             DECIMAL(18, 6), 
            @Leads           DECIMAL(18, 6), 
            @PM10            DECIMAL(18, 6), 
            @PM2_5           DECIMAL(18, 6), 
            @SOx             DECIMAL(18, 6) 
            , @CO2 DECIMAL(18, 6)
            , @CH4 DECIMAL(18, 6)
            , @N2O DECIMAL(18, 6)

    ------------------------------------------ 
    -- holding table for asset subst levels -- 
    ------------------------------------------ 
    DECLARE @holding_table TABLE 
      ( 
         asset_id            INT 
         , substance_type_cd INT 
         , actual_emissions  DECIMAL(18, 6) 
      ) 
    ------------------------------------------ 
    -- final results table 
    ------------------------------------------ 
    IF object_id('tempdb..#form_12_by_asset') IS  NULL 
      BEGIN
        
        SET @wantresultset = 1
      
 		CREATE TABLE #form_12_by_asset --TABLE 
		  ( 
			 asset_id       INT 
			 , asset_number VARCHAR(50) 
			 , co           DECIMAL(18, 6) 
			 , nh3          DECIMAL(18, 6) 
			 , nox          DECIMAL(18, 6) 
			 , leads        DECIMAL(18, 6) 
			 , pm10         DECIMAL(18, 6) 
			 , pm2_5        DECIMAL(18, 6) 
			 , sox          DECIMAL(18, 6) 
			 , voc          DECIMAL(18, 6) 
			 , greatest_hap DECIMAL(18, 6) 
			 , other_haps   DECIMAL(18, 6) 
			 , VOCnoHAPS    decimal(18, 6)
			 , CO2    decimal(18, 6)
			 , CH4    decimal(18, 6)
			 , N2O    decimal(18, 6)
 		  ) 
      END

    WHILE (SELECT Count(*) 
           FROM   #temp_asset_substances) > 0 
      BEGIN 
          SELECT TOP 1 @master_asset_id = asset_id 
          FROM   #temp_asset_substances 

          SELECT @asset_number = number -- get asset number for insertion later 
          FROM   env_asset 
          WHERE  id = @master_asset_id 

          -- select all rows for asset / paint and insert into temp table 
          -- combine rows for VOC, Greatest HAP, and Other Haps into single row by asset 
          -- -1 VOC 
          -- -2 Greatest Single HAP 
          -- -3 Other HAPs 
          -- insert into holding_table 
          INSERT INTO @holding_table 
          SELECT asset_id 
                 , substance_type_cd 
                 , actual_emissions 
          FROM   #temp_asset_substances 
          WHERE  asset_id = @master_asset_id 
             AND paint = 1 

          -- initialize variables for building the paint insert row 
          SET @VOC = 0 
          SET @GreatestHAP = 0 
          SET @OtherHaps = 0 
          SET @VOCnoHAPs = 0
          SET @insert_row = 0 
          SET @CO = 0 
          SET @NH3 = 0 
          SET @NOx = 0 
          SET @Leads = 0 
          SET @PM10 = 0 
          SET @PM2_5 = 0 
          SET @SOx = 0 
          SET @CO2 = 0 
          SET @CH4 = 0 
          SET @N2O = 0 

          WHILE (SELECT Count(*) 
                 FROM   @holding_table) > 0 -- while (rows in holding_table) 
            BEGIN 
                SELECT TOP 1 @f_a_id = asset_id 
                             , @f_sub_type_cd = substance_type_cd 
                             , @f_act_emissions = actual_emissions 
                FROM   @holding_table 

                -- create VOC portion of data set 
                IF ( @f_sub_type_cd = -1 ) 
                  BEGIN 
                      SET @VOC = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                -- create greatest HAP portion of data set 
                IF ( @f_sub_type_cd = -2 ) 
                  BEGIN 
                      SET @GreatestHAP = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                -- create other haps portion of data set 
                IF ( @f_sub_type_cd = -3 ) 
                  BEGIN 
                      SET @OtherHaps = @f_act_emissions 
                      SET @insert_row = 1 
                  END 
                  
                -- create voc excluding haps portion of data set
				IF (@f_sub_type_cd = -4)
				BEGIN
					SET @VOCnoHAPs = @f_act_emissions
					SET @insert_row = 1
				END
				
				IF ( @f_sub_type_cd = 8 ) -- PM10 
                  BEGIN 
                      SET @PM10 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                -- loop and move on to the next id in the result set 
                DELETE FROM @holding_table 
                WHERE  asset_id = @f_a_id 
                   AND substance_type_cd = @f_sub_type_cd 
            END -- end PAINT loop 

          -- insert row into the final table with all the data. 
          IF ( @insert_row = 1 ) 
            BEGIN 
                INSERT INTO #form_12_by_asset 
                            (asset_id 
                             , asset_number 
                             , co 
                             , nh3 
                             , nox 
                             , leads 
                             , pm10 
                             , pm2_5 
                             , sox 
                             , voc 
                             , greatest_hap 
                             , other_haps
                             , VOCnoHAPS
							 , CO2
							 , CH4
							 , N2O) 
                VALUES      (@f_a_id 
                             , @asset_number 
                             , @CO 
                             , @NH3 
                             , @NOx 
                             , @Leads 
                             , @PM10 
                             , @PM2_5 
                             , @SOx 
                             , @VOC 
                             , @GreatestHAP 
                             , @OtherHaps
                             , @VOCnoHAPs 
                             , @CO2
                             , @CH4
                             , @N2O ) 
            END 

          DELETE FROM @holding_table -- clear rows 

          -- insert into holding_table 
          INSERT INTO @holding_table 
          SELECT asset_id 
                 , substance_type_cd 
                 , actual_emissions 
          FROM   #temp_asset_substances 
          WHERE  asset_id = @master_asset_id 
             AND gas = 1 

          -- initialize variables for building the gas insert row 
          SET @VOC = 0 
          SET @GreatestHAP = 0 
          SET @OtherHaps = 0 
		  SET @VOCnoHAPs = 0
          SET @insert_row = 0 
          SET @CO = 0 
          SET @NH3 = 0 
          SET @NOx = 0 
          SET @Leads = 0 
          SET @PM10 = 0 
          SET @PM2_5 = 0 
          SET @SOx = 0 
          SET @CO2 = 0 
          SET @CH4 = 0 
          SET @N2O = 0 

          -- select all rows for asset / gas and insert into temp table 
          -- combine rows for sub types of Gas into single row by asset 
          -- 1  VOC 
          -- 4  CO 
          -- 5  NH3 
          -- 6  NOx 
          -- 7  Lead 
          -- 8  PM10 
          -- 9  PM2.5 
          -- 10  SOx 
          WHILE (SELECT Count(*) 
                 FROM   @holding_table) > 0 -- while (rows in holding_table) 
            BEGIN 
                SELECT TOP 1 @f_a_id = asset_id 
                             , @f_sub_type_cd = substance_type_cd 
                             , @f_act_emissions = actual_emissions 
                FROM   @holding_table 

                -- build the insert statement, as defined above. 
                IF ( @f_sub_type_cd = 1 ) -- VOC 
                  BEGIN 
                      SET @VOC = @f_act_emissions 
                      SET @VOCnoHAPs = @VOC
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 4 ) -- CO 
                  BEGIN 
                      SET @CO = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 5 ) -- NH3 
                  BEGIN 
                      SET @NH3 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 6 ) -- NOx 
                  BEGIN 
                      SET @NOx = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 7 ) -- Lead 
                  BEGIN 
                      SET @Leads = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 8 ) -- PM10 
                  BEGIN 
                      SET @PM10 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 9 ) -- PM2.5 
                  BEGIN 
                      SET @PM2_5 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 10 ) -- SOx 
                  BEGIN 
                      SET @SOx = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 11 ) -- CO2
                  BEGIN 
                      SET @CO2 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 12 ) -- CH4 
                  BEGIN 
                      SET @CH4 = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                IF ( @f_sub_type_cd = 13 ) -- N2O 
                  BEGIN 
                      SET @N2O = @f_act_emissions 
                      SET @insert_row = 1 
                  END 

                -- loop and move on to the next id in the result set 
                DELETE FROM @holding_table 
                WHERE  asset_id = @f_a_id 
                   AND substance_type_cd = @f_sub_type_cd 
            END -- end GAS loop 

          -- insert row into the final table with all the data. 
          IF ( @insert_row = 1 ) 
            BEGIN 
                INSERT INTO #form_12_by_asset 
                            (asset_id 
                             , asset_number 
                             , co 
                             , nh3 
                             , nox 
                             , leads 
                             , pm10 
                             , pm2_5 
                             , sox 
                             , voc 
                             , greatest_hap 
                             , other_haps
                             , vocnohaps                             
							 , CO2
							 , CH4
							 , N2O) 
                VALUES      (@f_a_id 
                             , @asset_number 
                             , @CO 
                             , @NH3 
                             , @NOx 
                             , @Leads 
                             , @PM10 
                             , @PM2_5 
                             , @SOx 
                             , @VOC 
                             , @GreatestHAP 
                             , @OtherHaps
                             , @VOCnoHAPs
                             , @CO2
                             , @CH4
                             , @N2O ) 
            END 

          DELETE FROM @holding_table -- clear rows 

          -- loop and move on to the next id in the result set 
          DELETE FROM #temp_asset_substances 
          WHERE  asset_id = @master_asset_id 
      END -- end TEMP_ASSET_SUBSTANCES loop 

    -- after asset loop 
    -- perform insertion of Bulk Liquid tank VOCs 
    CREATE TABLE #tanks 
      ( 
         tank_total DECIMAL(18, 6) 
      ) 

    DECLARE @tanktotal DECIMAL(18, 6) 

    INSERT INTO #tanks 
    EXEC Sp_form_12_tanks 
      @yearInt=@yearInt, 
      @clientId=@clientId, 
      @facilityId=@facilityId 

    SELECT TOP 1 @tanktotal = tank_total 
    FROM   #tanks 

    INSERT INTO #form_12_by_asset 
                (asset_id 
                 , asset_number 
                 , co 
                 , nh3 
                 , nox 
                 , leads 
                 , pm10 
                 , pm2_5 
                 , sox 
                 , voc 
                 , greatest_hap 
                 , other_haps
                 , VOCnoHAPS
                 , CO2
                 , CH4
                 , N2O
                 ) 
    VALUES      (0 
                 , 'Form 10.1' 
                 , 0 
                 , 0 
                 , 0 
                 , 0 
                 , 0 
                 , 0 
                 , 0 
                 , @tanktotal 
                 , 0 
                 , 0
                 , @tanktotal
                 , 0
                 , 0
                 , 0 ) 

    DROP TABLE #tanks 

    DROP TABLE #temp_asset_substances 

    DROP TABLE #temp_asset_totals 

	IF @wantresultset = 1
		SELECT * 
		FROM   #form_12_by_asset 