

/****** Object:  UserDefinedFunction [dbo].[Emissions]    Script Date: 02/18/2013 16:25:21 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Emissions]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Emissions]
GO



/****** Object:  UserDefinedFunction [dbo].[Emissions]    Script Date: 02/18/2013 16:25:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE FUNCTION [dbo].[Emissions] 
(                            
     @yearInt int = NULL
     , @facilityId   int = NULL
     , @sccId        int = NULL
     , @assetId      int = NULL
     , @assetTotalGasThroughputCF decimal(18, 6) = 0
     , @assetTotalGasThroughputBTU decimal(18, 6) = 0
     , @controlUsageId int = NULL
)
RETURNS 
	@Emissions TABLE 
	(
		ASSET_ID int,
		SUBSTANCE_TYPE_CD int,
		SUBSTANCE_TYPE varchar(50),
		SUBSTANCE_NAME varchar(50),
		ACTUAL_THROUGHPUT decimal(18, 6), --in MMCF or gal
		ACT_THRU_UNITS varchar(50),
		SRC_SUB_EMIS_FCTR decimal(18, 6), --in lb/MmCF or lb/gal
		EMIS_FCTR_UNITS varchar(50),
		SRC_INFO_ORIGIN varchar(50),
		ASSET_EFF_FACTOR varchar(50),
		ACTUAL_EMISSIONS decimal(18, 6),
		PAINT int,
		GAS int,
		BL int
	)
AS
BEGIN
	
	DECLARE	
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10),
	@srcTypeCd int,
	@assetTotalPaintThroughput decimal(18, 6) = 0,
	@sourceId int,
	--@sourceTotal decimal(18, 6),
	@vocContent decimal(18, 6),
	@solidsContent decimal(18, 6),
	@density decimal(18, 6),
	@numerator decimal(18, 6),
	@emissionFactor decimal(18, 6),
	@efficiencyFactor decimal(18, 6),
	@actualEmissions decimal(18, 6),
	@substanceId int,
	@substanceName varchar(50),
	@controlName varchar(50),
	@infoOrigin varchar(50),
	@substanceTypeCd int,
	@substanceTypeName varchar(50),
	@unitOfMeasureDescription varchar(50)
	, @actualThroughputUnits varchar(50)
	, @assetTotalGasThroughput decimal(18, 6)
	, @malfunctions decimal(18, 6)

	SET @yearChar = @yearInt
	SET @StartDate 	= '01-01-' + @yearChar
	SET @EndDate 	= '12-31-' + @yearChar

	-----------------------------------------
	-- all sources for this asset pair --
	-----------------------------------------
	DECLARE @sources TABLE (
		SOURCE_ID int,
		SOURCE_TYPE_CD int,
		LBS_VOC decimal(18, 6),
		PCT_SOLIDS decimal(18, 6)
        , INFO_ORIGIN varchar(200)

	)
	-----------------------------------------
	-- all substances for this asset/scc pair --
	-----------------------------------------
	DECLARE @sourceSubstances TABLE (
		SUBSTANCE_ID int,
		NG_EM_FACTOR decimal(18, 6)
	    , NG_EF_UNIT int
	    , [DESCRIPTION] varchar(50)
	)

	DECLARE @sourceTotals TABLE (
		  SOURCE_ID int
		, VOC_CONTENT decimal(18, 6)
		, SOLIDS_CONTENT decimal(18, 6)
		, CONTROL_SUBSTANCE_TYPE_CD int
		, RATING decimal(18, 6)
		, MALFUNCTION_RATING decimal(18, 6)
		, LBS_TRANSFERRED decimal(18, 6)
	)			
							
	-- get the sources that should be summed in the totals on this page. 
	IF(@controlUsageId IS NOT NULL)

		INSERT INTO @sources 
			SELECT SRC.ID AS SOURCE_ID
				   , SRC.SOURCE_TYPE_CD
				   , SRC.LBS_VOC
				   , SRC.PCT_SOLIDS_VOLUME AS PCT_SOLIDS
				   , SRC.INFO_ORIGIN
			FROM   
				ENV_SOURCE AS SRC 
					INNER JOIN env_asset_source AS ASSO  
						ON SRC.ID = ASSO.SOURCE_ID
					INNER JOIN env_asset AS ASSET 
						ON ASSET.id = ASSO.asset_id 
					LEFT JOIN env_source_scc_info AS SCC 
						ON SRC.id = SCC.source_id 
			WHERE   ASSO.asset_id = @assetId
				AND ASSO.status_cd = 1 

	ELSE
		BEGIN
			IF(@sccId IS NOT NULL)
			
				INSERT INTO @sources 
					SELECT SRC.ID AS SOURCE_ID
						   , SRC.SOURCE_TYPE_CD
						   , SRC.LBS_VOC
						   , SRC.PCT_SOLIDS_VOLUME AS PCT_SOLIDS
						   , SRC.INFO_ORIGIN
					FROM   
						ENV_SOURCE AS SRC 
							INNER JOIN env_asset_source AS ASSO  
								ON SRC.ID = ASSO.SOURCE_ID
							INNER JOIN env_asset AS ASSET 
								ON ASSET.id = ASSO.asset_id 
							INNER JOIN env_source_scc_info AS SCC 
								ON SRC.id = SCC.source_id 
								AND SCC.scc_info_id = @sccId
					WHERE   ASSO.asset_id = @assetId
						AND ASSO.status_cd = 1 

			ELSE

				INSERT INTO @sources 
					SELECT SRC.ID AS SOURCE_ID
						   , SRC.SOURCE_TYPE_CD
						   , SRC.LBS_VOC
						   , SRC.PCT_SOLIDS_VOLUME AS PCT_SOLIDS
						   , SRC.INFO_ORIGIN
					FROM   
						ENV_SOURCE AS SRC 
							INNER JOIN env_asset_source AS ASSO  
								ON SRC.ID = ASSO.SOURCE_ID
							INNER JOIN env_asset AS ASSET 
								ON ASSET.id = ASSO.asset_id 
							LEFT JOIN env_source_scc_info AS SCC 
								ON SRC.id = SCC.source_id 
					WHERE   ASSO.asset_id = @assetId
						AND ASSO.status_cd = 1 

		END

		WHILE (SELECT COUNT(*) FROM @sources) > 0
			BEGIN

				SELECT TOP 1 
					@sourceId = SOURCE_ID, 
					@srcTypeCd = SOURCE_TYPE_CD,
					@vocContent = LBS_VOC,
					@solidsContent = PCT_SOLIDS,
					@infoOrigin = INFO_ORIGIN
				 FROM @sources

				-- get the annual throughput of these sources from the ENV_SOURCE_USAGE table
				-- according to the rules of their particular source type.

				-- ************************************************************ --
				-- *********   paint   **************************************** --
				-- ************************************************************ --
				IF (@srcTypeCd = 1)
					BEGIN

						INSERT INTO @sourceTotals 
							( SOURCE_ID
							, VOC_CONTENT
							, SOLIDS_CONTENT
							, CONTROL_SUBSTANCE_TYPE_CD
							, RATING
							, MALFUNCTION_RATING
							, LBS_TRANSFERRED
							)
							SELECT 
								@sourceId,
								@vocContent,
								@solidsContent,
								CONTROL_SUBSTANCE_TYPE_CD,
								RATING,
								MALFUNCTION_RATING,
								LBS_TRANSFERRED
							FROM Liquids_subtotals(@StartDate, @EndDate, NULL, @assetId, @sourceId, @controlUsageId)
							
					END	

				-- ************************************************************ --
				-- *********   natural gas   ********************************** --
				-- ************************************************************ --
				IF (@srcTypeCd = 2 AND @controlUsageId IS NULL)
					BEGIN
						
						IF(@assetTotalGasThroughputCF IS NOT NULL
						    OR @assetTotalGasThroughputBTU IS NOT NULL)
						    
						    BEGIN

							    INSERT INTO @Emissions
										    (asset_id
										     , substance_type_cd
										     , substance_type 
										     , substance_name
										     , actual_throughput 
										     , act_thru_units 
										     , src_sub_emis_fctr 
										     , emis_fctr_units 
										     , src_info_origin 
										     , asset_eff_factor 
										     , actual_emissions
										     , PAINT
										     , GAS
										     , BL) 
										     
								SELECT asset_id
										     , substance_type_cd
										     , substance_type 
										     , substance_name
										     , actual_throughput 
										     , act_thru_units 
										     , src_sub_emis_fctr 
										     , emis_fctr_units 
										     , src_info_origin 
										     , asset_eff_factor 
										     , actual_emissions
										     , PAINT
										     , GAS
										     , BL
									 FROM Emissions_gas(@facilityId, @assetId, @sourceId, NULL, @assetTotalGasThroughputCF, @assetTotalGasThroughputBTU)
							
                            END
		
					END
						
				DELETE FROM @sources WHERE SOURCE_ID = @sourceId
			
			END
			
			IF (SELECT COUNT(*) FROM @sourceTotals) > 0
				BEGIN

					-- VOC
					IF (@controlUsageId IS NULL) -- TO DO: VOC Controls?
						BEGIN
							-- Get the emission factor
							SELECT @emissionFactor = SUM(b.voc)
								, @assetTotalPaintThroughput = SUM(b.RATING)
								FROM (SELECT VOC_CONTENT * RATING as voc, RATING FROM @sourceTotals GROUP BY SOURCE_ID, VOC_CONTENT, RATING) as b
							
							SET @actualEmissions = 0
							
							IF (@assetTotalPaintThroughput > 0)
								BEGIN
									SET @actualEmissions = @emissionFactor / 2000 -- TONS
									SET @emissionFactor = @emissionFactor / @assetTotalPaintThroughput -- % of total
								END

							INSERT INTO @Emissions 
									(asset_id
									 , substance_type_cd
									 , substance_type 
									 , substance_name
									 , actual_throughput 
									 , act_thru_units 
									 , src_sub_emis_fctr 
									 , emis_fctr_units 
									 , src_info_origin 
									 , asset_eff_factor 
									 , actual_emissions
									 , PAINT
									 , GAS
									 , BL) 
							VALUES  (@assetId
									 , -1
									 , 'VOC' 
									 , 'Volatile Organic Compounds'
									 , @assetTotalPaintThroughput 
									 , 'gal' 
									 , @emissionFactor 
									 , 'lb/gal' 
									 , 'Vendor Information' 
									 , 'NA' 
									 , @actualEmissions
									 , 1
									 , 0
									 , 0) 
						END
					
					
					-- PM10
					-- Take care of Transfer % here, per Reading Total
					SELECT @emissionFactor = SUM(b.SOLIDS_CONTENT * (b.WORKINGFINE_RATING - b.MALFUNCTION_RATING)) 
						, @malfunctions = SUM(b.SOLIDS_CONTENT * b.MALFUNCTION_RATING) 
						, @assetTotalPaintThroughput = SUM(b.RATING)
						FROM (
						    SELECT 
						        SOLIDS_CONTENT
						        , MAX(RATING) AS RATING
						        , MAX(CASE WHEN RATING > 0 THEN RATING * (LBS_TRANSFERRED / RATING) ELSE 0 END) AS WORKINGFINE_RATING
						        , MAX(CASE WHEN CONTROL_SUBSTANCE_TYPE_CD = 8 AND RATING > 0 THEN MALFUNCTION_RATING * (LBS_TRANSFERRED / RATING) ELSE 0 END) AS MALFUNCTION_RATING
						      FROM @sourceTotals 
						      GROUP BY SOURCE_ID, SOLIDS_CONTENT) as b
					
					SET @actualEmissions = 0 
					
					IF (@assetTotalPaintThroughput > 0)
						BEGIN

							SET @efficiencyFactor = 1
							SET @controlName = ''
							
							SELECT @efficiencyFactor = Coalesce((100 - CAST(EFFICIENCY_FACTOR as DECIMAL(18, 6))) / 100, 1), 
							       @controlName = COALESCE(ADDL_INFO, '') 
						        FROM ENV_ASSET_EMITTED_SUBSTANCE_TYPE control
								WHERE ASSET_ID = @assetId
									AND control.SUBSTANCE_TYPE_CD = 8  -- TO DO

							--In the case of a paint PM emissions are usually calculated as: 
							--amount of paint x %solids in paint x (1-transfer efficiency) x (1-control efficiency)=lb PM10 
							--For example: 40 lb paint x 50% solids x (1-.5) x (1-.98)=0.2 lb PM10 

							SET @actualEmissions = 
							    @malfunctions / 2000 -- EFFICIENCY DOESN'T APPLY to PORTION THAT MALFUNCTIONED
						
							SET @actualEmissions = 
							    @actualEmissions 
							    + ((@emissionFactor * @efficiencyFactor) / 2000) -- TONS

							SET @emissionFactor = @emissionFactor / @assetTotalPaintThroughput -- % of total

						END


					INSERT INTO @Emissions 
							(asset_id
							 , substance_type_cd
							 , substance_type 
							 , substance_name
							 , actual_throughput 
							 , act_thru_units 
							 , src_sub_emis_fctr 
							 , emis_fctr_units 
							 , src_info_origin 
							 , asset_eff_factor 
							 , actual_emissions
							 , PAINT
							 , GAS
							 , BL) 
					VALUES  (@assetId
					         , 8
					         , 'PM10' 
					         , 'Particulate Matter'
							 , @assetTotalPaintThroughput 
							 , 'gal' 
							 , @emissionFactor 
							 , 'lb/gal' 
							 , 'Vendor Information' 
							 , CASE WHEN @efficiencyFactor < 1 THEN CONVERT(varchar(100), CAST(100 - (@efficiencyFactor * 100)AS int) ) + '% - ' + @controlName ELSE 'NA' END
							 , @actualEmissions
							 , 1
							 , 0
							 , 0) 
				END 

	RETURN 
END

GO


