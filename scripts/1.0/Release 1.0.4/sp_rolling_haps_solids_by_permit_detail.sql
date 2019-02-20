

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_rolling_haps_solids_by_permit_detail]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_rolling_haps_solids_by_permit_detail]
GO



CREATE PROCEDURE dbo.sp_rolling_haps_solids_by_permit_detail
	-- Required Parameters
	@yearInt int = NULL,
	@permitDetailId int = NULL
AS

DECLARE 
	@JanuaryPrevHaps 		FLOAT(8),
	@FebruaryPrevHaps 		FLOAT(8),
	@MarchPrevHaps			FLOAT(8),
	@AprilPrevHaps			FLOAT(8),
	@MayPrevHaps			FLOAT(8),
	@JunePrevHaps 			FLOAT(8),
	@JulyPrevHaps 			FLOAT(8),
	@AugustPrevHaps			FLOAT(8),
	@SeptemberPrevHaps		FLOAT(8),
	@OctoberPrevHaps		FLOAT(8),
	@NovemberPrevHaps 		FLOAT(8),
	@DecemberPrevHaps 		FLOAT(8),
	
	@JanuaryPrevSolids 		FLOAT(8),
	@FebruaryPrevSolids 	FLOAT(8),
	@MarchPrevSolids		FLOAT(8),
	@AprilPrevSolids		FLOAT(8),
	@MayPrevSolids			FLOAT(8),
	@JunePrevSolids 		FLOAT(8),
	@JulyPrevSolids 		FLOAT(8),
	@AugustPrevSolids		FLOAT(8),
	@SeptemberPrevSolids	FLOAT(8),
	@OctoberPrevSolids		FLOAT(8),
	@NovemberPrevSolids 	FLOAT(8),
	@DecemberPrevSolids 	FLOAT(8),
	
	@JanuaryHaps 		FLOAT(8),
	@FebruaryHaps 		FLOAT(8),
	@MarchHaps			FLOAT(8),
	@AprilHaps			FLOAT(8),
	@MayHaps			FLOAT(8),
	@JuneHaps 			FLOAT(8),
	@JulyHaps 			FLOAT(8),
	@AugustHaps			FLOAT(8),
	@SeptemberHaps		FLOAT(8),
	@OctoberHaps		FLOAT(8),
	@NovemberHaps 		FLOAT(8),
	@DecemberHaps 		FLOAT(8),
	
	@JanuarySolids 		FLOAT(8),
	@FebruarySolids 	FLOAT(8),
	@MarchSolids		FLOAT(8),
	@AprilSolids		FLOAT(8),
	@MaySolids			FLOAT(8),
	@JuneSolids 		FLOAT(8),
	@JulySolids 		FLOAT(8),
	@AugustSolids		FLOAT(8),
	@SeptemberSolids	FLOAT(8),
	@OctoberSolids		FLOAT(8),
	@NovemberSolids 	FLOAT(8),
	@DecemberSolids 	FLOAT(8),
	
	@JanuaryHapsSolids 		FLOAT(8),
	@FebruaryHapsSolids 	FLOAT(8),
	@MarchHapsSolids		FLOAT(8),
	@AprilHapsSolids		FLOAT(8),
	@MayHapsSolids			FLOAT(8),
	@JuneHapsSolids 		FLOAT(8),
	@JulyHapsSolids 		FLOAT(8),
	@AugustHapsSolids		FLOAT(8),
	@SeptemberHapsSolids	FLOAT(8),
	@OctoberHapsSolids		FLOAT(8),
	@NovemberHapsSolids 	FLOAT(8),
	@DecemberHapsSolids 	FLOAT(8),
	
	@JanuaryRollingHaps 	FLOAT(8),
	@FebruaryRollingHaps 	FLOAT(8),
	@MarchRollingHaps		FLOAT(8),
	@AprilRollingHaps		FLOAT(8),
	@MayRollingHaps			FLOAT(8),
	@JuneRollingHaps 		FLOAT(8),
	@JulyRollingHaps 		FLOAT(8),
	@AugustRollingHaps		FLOAT(8),
	@SeptemberRollingHaps	FLOAT(8),
	@OctoberRollingHaps		FLOAT(8),
	@NovemberRollingHaps 	FLOAT(8),
	@DecemberRollingHaps 	FLOAT(8),
	
	@JanuaryRollingSolids 	FLOAT(8),
	@FebruaryRollingSolids 	FLOAT(8),
	@MarchRollingSolids		FLOAT(8),
	@AprilRollingSolids		FLOAT(8),
	@MayRollingSolids		FLOAT(8),
	@JuneRollingSolids 		FLOAT(8),
	@JulyRollingSolids 		FLOAT(8),
	@AugustRollingSolids	FLOAT(8),
	@SeptemberRollingSolids	FLOAT(8),
	@OctoberRollingSolids	FLOAT(8),
	@NovemberRollingSolids 	FLOAT(8),
	@DecemberRollingSolids 	FLOAT(8),
	
	@JanuaryRollingHapsSolids 	FLOAT(8),
	@FebruaryRollingHapsSolids 	FLOAT(8),
	@MarchRollingHapsSolids		FLOAT(8),
	@AprilRollingHapsSolids		FLOAT(8),
	@MayRollingHapsSolids		FLOAT(8),
	@JuneRollingHapsSolids 		FLOAT(8),
	@JulyRollingHapsSolids 		FLOAT(8),
	@AugustRollingHapsSolids	FLOAT(8),
	@SeptemberRollingHapsSolids	FLOAT(8),
	@OctoberRollingHapsSolids	FLOAT(8),
	@NovemberRollingHapsSolids 	FLOAT(8),
	@DecemberRollingHapsSolids 	FLOAT(8),

	@JanPrevStartDate varchar(10),
	@JanPrevEndDate varchar(10),
	@FebPrevStartDate varchar(10),
	@FebPrevEndDate varchar(10),
	@MarPrevStartDate varchar(10),
	@MarPrevEndDate varchar(10),
	@AprPrevStartDate varchar(10),
	@AprPrevEndDate varchar(10),
	@MayPrevStartDate varchar(10),
	@MayPrevEndDate varchar(10),
	@JunPrevStartDate varchar(10),
	@JunPrevEndDate varchar(10),
	@JulPrevStartDate varchar(10),
	@JulPrevEndDate varchar(10),
	@AugPrevStartDate varchar(10),
	@AugPrevEndDate varchar(10),
	@SepPrevStartDate varchar(10),
	@SepPrevEndDate varchar(10),
	@OctPrevStartDate varchar(10),
	@OctPrevEndDate varchar(10),
	@NovPrevStartDate varchar(10),
	@NovPrevEndDate varchar(10),
	@DecPrevStartDate varchar(10),
	@DecPrevEndDate varchar(10),

	@JanStartDate varchar(10),
	@JanEndDate varchar(10),
	@FebStartDate varchar(10),
	@FebEndDate varchar(10),
	@MarStartDate varchar(10),
	@MarEndDate varchar(10),
	@AprStartDate varchar(10),
	@AprEndDate varchar(10),
	@MayStartDate varchar(10),
	@MayEndDate varchar(10),
	@JunStartDate varchar(10),
	@JunEndDate varchar(10),
	@JulStartDate varchar(10),
	@JulEndDate varchar(10),
	@AugStartDate varchar(10),
	@AugEndDate varchar(10),
	@SepStartDate varchar(10),
	@SepEndDate varchar(10),
	@OctStartDate varchar(10),
	@OctEndDate varchar(10),
	@NovStartDate varchar(10),
	@NovEndDate varchar(10),
	@DecStartDate varchar(10),
	@DecEndDate varchar(10),

	@yearChar varchar(4),
	@prevYearInt int,
	@prevYearChar varchar(4),
	@nextYearInt int,
	@nextYearChar varchar(4),

	@aId int,
	@hapsHold float(8),
	@solidsHold float(8)

-- write results into table
CREATE TABLE #TEMP_PERMITTED_ASSETS (
	A_ID int
)

INSERT INTO #TEMP_PERMITTED_ASSETS
 EXEC sp_assets_by_permit_detail @permitDetailId = @permitDetailId  -- select into scalar value function?
 
-- Create dates for use in query
SET @yearChar = @yearInt
SET @prevYearInt = @yearInt - 1
SET @prevYearChar = @prevYearInt
SET @nextYearInt = @yearInt + 1
SET @nextYearChar = @nextYearInt

SET @JanPrevStartDate 	= '01-01-' + @prevYearChar
SET @JanPrevEndDate 	= '02-01-' + @prevYearChar
SET @FebPrevStartDate 	= '02-01-' + @prevYearChar
SET @FebPrevEndDate 	= '03-01-' + @prevYearChar
SET @MarPrevStartDate 	= '03-01-' + @prevYearChar
SET @MarPrevEndDate 	= '04-01-' + @prevYearChar
SET @AprPrevStartDate 	= '04-01-' + @prevYearChar
SET @AprPrevEndDate 	= '05-01-' + @prevYearChar
SET @MayPrevStartDate 	= '05-01-' + @prevYearChar
SET @MayPrevEndDate 	= '06-01-' + @prevYearChar
SET @JunPrevStartDate 	= '06-01-' + @prevYearChar
SET @JunPrevEndDate 	= '07-01-' + @prevYearChar
SET @JulPrevStartDate 	= '07-01-' + @prevYearChar
SET @JulPrevEndDate 	= '08-01-' + @prevYearChar
SET @AugPrevStartDate 	= '08-01-' + @prevYearChar
SET @AugPrevEndDate 	= '09-01-' + @prevYearChar
SET @SepPrevStartDate 	= '09-01-' + @prevYearChar
SET @SepPrevEndDate 	= '10-01-' + @prevYearChar
SET @OctPrevStartDate 	= '10-01-' + @prevYearChar
SET @OctPrevEndDate 	= '11-01-' + @prevYearChar
SET @NovPrevStartDate 	= '11-01-' + @prevYearChar
SET @NovPrevEndDate 	= '12-01-' + @prevYearChar
SET @DecPrevStartDate 	= '12-01-' + @prevYearChar
SET @DecPrevEndDate 	= '01-01-' + @yearChar

SET @JanStartDate 	= '01-01-' + @yearChar
SET @JanEndDate 	= '02-01-' + @yearChar
SET @FebStartDate 	= '02-01-' + @yearChar
SET @FebEndDate 	= '03-01-' + @yearChar
SET @MarStartDate 	= '03-01-' + @yearChar
SET @MarEndDate 	= '04-01-' + @yearChar
SET @AprStartDate 	= '04-01-' + @yearChar
SET @AprEndDate 	= '05-01-' + @yearChar
SET @MayStartDate 	= '05-01-' + @yearChar
SET @MayEndDate 	= '06-01-' + @yearChar
SET @JunStartDate 	= '06-01-' + @yearChar
SET @JunEndDate 	= '07-01-' + @yearChar
SET @JulStartDate 	= '07-01-' + @yearChar
SET @JulEndDate 	= '08-01-' + @yearChar
SET @AugStartDate 	= '08-01-' + @yearChar
SET @AugEndDate 	= '09-01-' + @yearChar
SET @SepStartDate 	= '09-01-' + @yearChar
SET @SepEndDate 	= '10-01-' + @yearChar
SET @OctStartDate 	= '10-01-' + @yearChar
SET @OctEndDate 	= '11-01-' + @yearChar
SET @NovStartDate 	= '11-01-' + @yearChar
SET @NovEndDate 	= '12-01-' + @yearChar
SET @DecStartDate 	= '12-01-' + @yearChar
SET @DecEndDate 	= '01-01-' + @nextYearChar

SET @JanuaryPrevHaps 		= 0
SET @FebruaryPrevHaps 		= 0
SET @MarchPrevHaps			= 0
SET @AprilPrevHaps			= 0
SET @MayPrevHaps			= 0
SET @JunePrevHaps 			= 0
SET @JulyPrevHaps 			= 0
SET @AugustPrevHaps			= 0
SET @SeptemberPrevHaps		= 0
SET @OctoberPrevHaps		= 0
SET @NovemberPrevHaps 		= 0
SET @DecemberPrevHaps 		= 0

SET @JanuaryPrevSolids 		= 0
SET @FebruaryPrevSolids 	= 0
SET @MarchPrevSolids		= 0
SET @AprilPrevSolids		= 0
SET @MayPrevSolids			= 0
SET @JunePrevSolids 		= 0
SET @JulyPrevSolids 		= 0
SET @AugustPrevSolids		= 0
SET @SeptemberPrevSolids	= 0
SET @OctoberPrevSolids		= 0
SET @NovemberPrevSolids 	= 0
SET @DecemberPrevSolids 	= 0
	
SET @JanuaryHaps 		= 0
SET @FebruaryHaps 		= 0
SET @MarchHaps			= 0
SET @AprilHaps			= 0
SET @MayHaps			= 0
SET @JuneHaps 			= 0
SET @JulyHaps 			= 0
SET @AugustHaps			= 0
SET @SeptemberHaps		= 0
SET @OctoberHaps		= 0
SET @NovemberHaps 		= 0
SET @DecemberHaps 		= 0

SET @JanuarySolids 		= 0
SET @FebruarySolids 	= 0
SET @MarchSolids		= 0
SET @AprilSolids		= 0
SET @MaySolids			= 0
SET @JuneSolids 		= 0
SET @JulySolids 		= 0
SET @AugustSolids		= 0
SET @SeptemberSolids	= 0
SET @OctoberSolids		= 0
SET @NovemberSolids 	= 0
SET @DecemberSolids 	= 0

SET @JanuaryRollingHaps 	= 0
SET @FebruaryRollingHaps 	= 0
SET @MarchRollingHaps		= 0
SET @AprilRollingHaps		= 0
SET @MayRollingHaps			= 0
SET @JuneRollingHaps 		= 0
SET @JulyRollingHaps 		= 0
SET @AugustRollingHaps		= 0
SET @SeptemberRollingHaps	= 0
SET @OctoberRollingHaps		= 0
SET @NovemberRollingHaps 	= 0
SET @DecemberRollingHaps 	= 0
	
SET @JanuaryRollingSolids 	= 0
SET @FebruaryRollingSolids 	= 0
SET @MarchRollingSolids		= 0
SET @AprilRollingSolids		= 0
SET @MayRollingSolids		= 0
SET @JuneRollingSolids 		= 0
SET @JulyRollingSolids 		= 0
SET @AugustRollingSolids	= 0
SET @SeptemberRollingSolids	= 0
SET @OctoberRollingSolids	= 0
SET @NovemberRollingSolids 	= 0
SET @DecemberRollingSolids 	= 0
	
SET @JanuaryRollingHapsSolids 	= 0
SET @FebruaryRollingHapsSolids 	= 0
SET @MarchRollingHapsSolids		= 0
SET @AprilRollingHapsSolids		= 0
SET @MayRollingHapsSolids		= 0
SET @JuneRollingHapsSolids 		= 0
SET @JulyRollingHapsSolids 		= 0
SET @AugustRollingHapsSolids	= 0
SET @SeptemberRollingHapsSolids	= 0
SET @OctoberRollingHapsSolids	= 0
SET @NovemberRollingHapsSolids 	= 0
SET @DecemberRollingHapsSolids 	= 0

SET @hapsHold = 0
SET @solidsHold = 0

CREATE TABLE #TEMP_HOLD_ASSET_TOTALS (
	A_ID int,
	SUM_LBS_HAPS float(8),
	SUM_SOLIDS_VOLUME float(8)
)

WHILE (SELECT COUNT(*) FROM #TEMP_PERMITTED_ASSETS) > 0
  BEGIN
    SELECT TOP 1 @aId = A_ID FROM #TEMP_PERMITTED_ASSETS
	 
	 --------------------------------------------
	 -- get previous years month to month totals
	 --------------------------------------------
	 -- JANUARY PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JanPrevStartDate, @endDate=@JanPrevEndDate, @assetId=@aId	
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @JanuaryPrevHaps = @JanuaryPrevHaps + @hapsHold
		SET @JanuaryPrevSolids = @JanuaryPrevSolids + @solidsHold
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	 
	 -- FEBRUARY PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@FebPrevStartDate, @endDate=@FebPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN	
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @FebruaryPrevHaps = @FebruaryPrevHaps + @hapsHold
		SET @FebruaryPrevSolids = @FebruaryPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- MARCH PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@MarPrevStartDate, @endDate=@MarPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN	
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @MarchPrevHaps = @MarchPrevHaps + @hapsHold
		SET @MarchPrevSolids = @MarchPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- APRIL PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@AprPrevStartDate, @endDate=@AprPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN	
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @AprilPrevHaps = @AprilPrevHaps + @hapsHold
		SET @AprilPrevSolids = @AprilPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- MAY PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@MayPrevStartDate, @endDate=@MayPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN	
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @MayPrevHaps = @MayPrevHaps + @hapsHold
		SET @MayPrevSolids = @MayPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- JUNE PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JunPrevStartDate, @endDate=@JunPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @JunePrevHaps = @JunePrevHaps + @hapsHold
		SET @JunePrevSolids = @JunePrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- JULY PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JulPrevStartDate, @endDate=@JulPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @JulyPrevHaps = @JulyPrevHaps + @hapsHold
		SET @JulyPrevSolids = @JulyPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- AUGUST PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@AugPrevStartDate, @endDate=@AugPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @AugustPrevHaps = @AugustPrevHaps + @hapsHold
		SET @AugustPrevSolids = @AugustPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- SEPTEMBER PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@SepPrevStartDate, @endDate=@SepPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @SeptemberPrevHaps = @SeptemberPrevHaps + @hapsHold
		SET @SeptemberPrevSolids = @SeptemberPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- OCTOBER PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@OctPrevStartDate, @endDate=@OctPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @OctoberPrevHaps = @OctoberPrevHaps + @hapsHold
		SET @OctoberPrevSolids = @OctoberPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- NOVEMBER PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@NovPrevStartDate, @endDate=@NovPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @NovemberPrevHaps = @NovemberPrevHaps + @hapsHold
		SET @NovemberPrevSolids = @NovemberPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- DECEMBER PREVIOUS
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@DecPrevStartDate, @endDate=@DecPrevEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @DecemberPrevHaps = @DecemberPrevHaps + @hapsHold
		SET @DecemberPrevSolids = @DecemberPrevSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	

	 --------------------------------------------
	 -- get this years month to month totals
	 --------------------------------------------
	 -- JANUARY 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JanStartDate, @endDate=@JanEndDate, @assetId=@aId	
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS		
		SET @JanuaryHaps = @JanuaryHaps + @hapsHold
		SET @JanuarySolids = @JanuarySolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- FEBRUARY 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@FebStartDate, @endDate=@FebEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @FebruaryHaps = @FebruaryHaps + @hapsHold
		SET @FebruarySolids = @FebruarySolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- MARCH 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@MarStartDate, @endDate=@MarEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @MarchHaps = @MarchHaps + @hapsHold
		SET @MarchSolids = @MarchSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- APRIL 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@AprStartDate, @endDate=@AprEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @AprilHaps = @AprilHaps + @hapsHold
		SET @AprilSolids = @AprilSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- MAY 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@MayStartDate, @endDate=@MayEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @MayHaps = @MayHaps + @hapsHold
		SET @MaySolids = @MaySolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
		
	 -- JUNE 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JunStartDate, @endDate=@JunEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @JuneHaps = @JuneHaps + @hapsHold
		SET @JuneSolids = @JuneSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- JULY 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@JulStartDate, @endDate=@JulEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @JulyHaps = @JulyHaps + @hapsHold
		SET @JulySolids = @JulySolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- AUGUST 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@AugStartDate, @endDate=@AugEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @AugustHaps = @AugustHaps + @hapsHold
		SET @AugustSolids = @AugustSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- SEPTEMBER 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@SepStartDate, @endDate=@SepEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @SeptemberHaps = @SeptemberHaps + @hapsHold
		SET @SeptemberSolids = @SeptemberSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- OCTOBER 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@OctStartDate, @endDate=@OctEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @OctoberHaps = @OctoberHaps + @hapsHold
		SET @OctoberSolids = @OctoberSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- NOVEMBER 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@NovStartDate, @endDate=@NovEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @NovemberHaps = @NovemberHaps + @hapsHold
		SET @NovemberSolids = @NovemberSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	
	 -- DECEMBER 
	INSERT INTO #TEMP_HOLD_ASSET_TOTALS
		EXEC dbo.sp_total_haps_solids_by_asset @startDate=@DecStartDate, @endDate=@DecEndDate, @assetId=@aId
	IF ((SELECT COUNT(*) FROM #TEMP_HOLD_ASSET_TOTALS) > 0 )	
	BEGIN		
		SELECT TOP 1 @hapsHold = SUM_LBS_HAPS FROM #TEMP_HOLD_ASSET_TOTALS		
		SELECT TOP 1 @solidsHold = SUM_SOLIDS_VOLUME FROM #TEMP_HOLD_ASSET_TOTALS
		SET @DecemberHaps = @DecemberHaps + @hapsHold
		SET @DecemberSolids = @DecemberSolids + @solidsHold 
		DELETE FROM #TEMP_HOLD_ASSET_TOTALS	
		SET @hapsHold = 0
		SET @solidsHold = 0
	END
	 
    DELETE FROM #TEMP_PERMITTED_ASSETS WHERE A_ID = @aId
  END
  
DROP TABLE #TEMP_HOLD_ASSET_TOTALS	
DROP TABLE #TEMP_PERMITTED_ASSETS

-- Set the HAPS/SOLIDS total for each month,
-- Don't divide by 0.
IF (@JanuarySolids != 0) 
BEGIN
	SET @JanuaryHapsSolids 	= @JanuaryHaps / @JanuarySolids
END

IF (@FebruarySolids != 0) 
BEGIN
	SET @FebruaryHapsSolids = @FebruaryHaps / @FebruarySolids
END

IF (@MarchSolids != 0) 
BEGIN
	SET @MarchHapsSolids 	= @MarchHaps / @MarchSolids
END

IF (@AprilSolids != 0) 
BEGIN
	SET @AprilHapsSolids 	= @AprilHaps / @AprilSolids
END

IF (@MaySolids != 0) 
BEGIN
	SET @MayHapsSolids 		= @MayHaps / @MaySolids
END

IF (@JuneSolids != 0) 
BEGIN
	SET @JuneHapsSolids 	= @JuneHaps / @JuneSolids
END

IF (@JulySolids != 0) 
BEGIN
	SET @JulyHapsSolids 	= @JulyHaps / @JulySolids
END

IF (@AugustSolids != 0) 
BEGIN
	SET @AugustHapsSolids 	= @AugustHaps / @AugustSolids
END

IF (@SeptemberSolids != 0) 
BEGIN
	SET @SeptemberHapsSolids = @SeptemberHaps / @SeptemberSolids
END

IF (@OctoberSolids != 0) 
BEGIN
	SET @OctoberHapsSolids = @OctoberHaps / @OctoberSolids
END

IF (@NovemberSolids != 0) 
BEGIN
	SET @NovemberHapsSolids = @NovemberHaps / @NovemberSolids
END

IF (@DecemberSolids != 0) 
BEGIN
	SET @DecemberHapsSolids = @DecemberHaps / @DecemberSolids
END


-- Calculate the rollings totals
-- Don't divide by 0.
SET @JanuaryRollingHaps = @FebruaryPrevHaps + @MarchPrevHaps + @AprilPrevHaps + @MayPrevHaps +
			@JunePrevHaps + @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps
			
SET @JanuaryRollingSolids = @FebruaryPrevSolids + @MarchPrevSolids + @AprilPrevSolids + @MayPrevSolids +
			@JunePrevSolids + @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids
			
IF (@JanuaryRollingSolids != 0) 
BEGIN
	SET @JanuaryRollingHapsSolids = @JanuaryRollingHaps / @JanuaryRollingSolids
END
						
			
SET @FebruaryRollingHaps = @MarchPrevHaps + @AprilPrevHaps + @MayPrevHaps +
			@JunePrevHaps + @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps
			
SET @FebruaryRollingSolids = @MarchPrevSolids + @AprilPrevSolids + @MayPrevSolids +
			@JunePrevSolids + @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids
			
IF (@FebruaryRollingSolids != 0) 
BEGIN
	SET @FebruaryRollingHapsSolids = @FebruaryRollingHaps / @FebruaryRollingSolids	
END
			
			
SET @MarchRollingHaps = @AprilPrevHaps + @MayPrevHaps +
			@JunePrevHaps + @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps + @MarchHaps
			
SET @MarchRollingSolids = @AprilPrevSolids + @MayPrevSolids +
			@JunePrevSolids + @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids + @MarchSolids
			
IF (@MarchRollingSolids != 0) 
BEGIN
	SET @MarchRollingHapsSolids = @MarchRollingHaps / @MarchRollingSolids				
END
			

SET @AprilRollingHaps = @MayPrevHaps + @JunePrevHaps + @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps +
			@MarchHaps + @AprilHaps
			
SET @AprilRollingSolids = @MayPrevSolids + @JunePrevSolids + @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids +
			@MarchSolids + @AprilSolids
			
IF (@AprilRollingSolids != 0) 
BEGIN
	SET @AprilRollingHapsSolids = @AprilRollingHaps / @AprilRollingSolids	
END


SET @MayRollingHaps = @JunePrevHaps + @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps +
			@MarchHaps + @AprilHaps + @MayHaps
			
SET @MayRollingSolids = @JunePrevSolids + @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids +
			@MarchSolids + @AprilSolids + @MaySolids
			
IF (@MayRollingSolids != 0) 
BEGIN
	SET @MayRollingHapsSolids = @MayRollingHaps / @MayRollingSolids		
END
			
			
SET @JuneRollingHaps = @JulyPrevHaps + @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps +
			@MarchHaps + @AprilHaps + @MayHaps + @JuneHaps
			
SET @JuneRollingSolids = @JulyPrevSolids + @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids +
			@MarchSolids + @AprilSolids + @MaySolids + @JuneSolids
			
IF (@JuneRollingSolids != 0) 
BEGIN
	SET @JuneRollingHapsSolids = @JuneRollingHaps / @JuneRollingSolids		
END
	

SET @JulyRollingHaps = @AugustPrevHaps + @SeptemberPrevHaps +
			@OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + @JanuaryHaps + @FebruaryHaps +
			@MarchHaps + @AprilHaps + @MayHaps + @JuneHaps + @JulyHaps
			
SET @JulyRollingSolids = @AugustPrevSolids + @SeptemberPrevSolids +
			@OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + @JanuarySolids + @FebruarySolids +
			@MarchSolids + @AprilSolids + @MaySolids + @JuneSolids + @JulySolids
			
IF (@JulyRollingSolids != 0) 
BEGIN
	SET @JulyRollingHapsSolids = @JulyRollingHaps / @JulyRollingSolids			
END
			
			
SET @AugustRollingHaps = @SeptemberPrevHaps + @OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps + 
			@JanuaryHaps + @FebruaryHaps + @MarchHaps + @AprilHaps + @MayHaps + @JuneHaps + @JulyHaps + @AugustHaps
			
SET @AugustRollingSolids = @SeptemberPrevSolids + @OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids + 
			@JanuarySolids + @FebruarySolids + @MarchSolids + @AprilSolids + @MaySolids + @JuneSolids + @JulySolids + @AugustSolids
			
IF (@AugustRollingSolids != 0) 
BEGIN
	SET @AugustRollingHapsSolids = @AugustRollingHaps / @AugustRollingSolids		
END


SET @SeptemberRollingHaps = @OctoberPrevHaps + @NovemberPrevHaps + @DecemberPrevHaps +  @JanuaryHaps + @FebruaryHaps + 
			@MarchHaps + @AprilHaps + @MayHaps + @JuneHaps + @JulyHaps + @AugustHaps + @SeptemberHaps
			
SET @SeptemberRollingSolids = @OctoberPrevSolids + @NovemberPrevSolids + @DecemberPrevSolids +  @JanuarySolids + @FebruarySolids + 
			@MarchSolids + @AprilSolids + @MaySolids + @JuneSolids + @JulySolids + @AugustSolids + @SeptemberSolids
			
IF (@SeptemberRollingSolids != 0) 
BEGIN
	SET @SeptemberRollingHapsSolids = @SeptemberRollingHaps / @SeptemberRollingSolids	
END


SET @OctoberRollingHaps = @NovemberPrevHaps + @DecemberPrevHaps +  @JanuaryHaps + @FebruaryHaps + 
			@MarchHaps + @AprilHaps + @MayHaps + @JuneHaps + @JulyHaps + @AugustHaps + @SeptemberHaps + @OctoberHaps
			
SET @OctoberRollingSolids = @NovemberPrevSolids + @DecemberPrevSolids +  @JanuarySolids + @FebruarySolids + 
			@MarchSolids + @AprilSolids + @MaySolids + @JuneSolids + @JulySolids + @AugustSolids + @SeptemberSolids + @OctoberSolids
			
IF (@OctoberRollingSolids != 0) 
BEGIN
	SET @OctoberRollingHapsSolids = @OctoberRollingHaps / @OctoberRollingSolids	
END
			

SET @NovemberRollingHaps = @DecemberPrevHaps +  @JanuaryHaps + @FebruaryHaps + @MarchHaps + @AprilHaps + @MayHaps + 
			@JuneHaps + @JulyHaps + @AugustHaps + @SeptemberHaps + @OctoberHaps + @NovemberHaps
			
SET @NovemberRollingSolids = @DecemberPrevSolids +  @JanuarySolids + @FebruarySolids + @MarchSolids + @AprilSolids + @MaySolids + 
			@JuneSolids + @JulySolids + @AugustSolids + @SeptemberSolids + @OctoberSolids + @NovemberSolids
			
IF (@NovemberRollingSolids != 0) 
BEGIN
	SET @NovemberRollingHapsSolids = @NovemberRollingHaps / @NovemberRollingSolids				
END
			
			
SET @DecemberRollingHaps = @JanuaryHaps + @FebruaryHaps +  @MarchHaps + @AprilHaps + @MayHaps + @JuneHaps + @JulyHaps + @AugustHaps + 
			@SeptemberHaps + @OctoberHaps + @NovemberHaps + @DecemberHaps
			
SET @DecemberRollingSolids = @JanuarySolids + @FebruarySolids +  @MarchSolids + @AprilSolids + @MaySolids + @JuneSolids + @JulySolids + @AugustSolids + 
			@SeptemberSolids + @OctoberSolids + @NovemberSolids + @DecemberSolids
			
IF (@DecemberRollingSolids != 0) 
BEGIN
	SET @DecemberRollingHapsSolids = @DecemberRollingHaps / @DecemberRollingSolids	
END

SELECT 	-- values used in the report
	JAN_HAPS = @JanuaryHaps,
	JAN_SOLIDS = @JanuarySolids,
	JAN_HAPS_SOLIDS = @JanuaryHapsSolids,
	JAN_ROLLING_HAPS = @JanuaryRollingHaps,
	JAN_ROLLING_SOLIDS = @JanuaryRollingSolids,
	JAN_ROLLING_HAPS_SOLIDS = @JanuaryRollingHapsSolids,

	FEB_HAPS = @FebruaryHaps,
	FEB_SOLIDS = @FebruarySolids,
	FEB_HAPS_SOLIDS = @FebruaryHapsSolids,
	FEB_ROLLING_HAPS = @FebruaryRollingHaps,
	FEB_ROLLING_SOLIDS = @FebruaryRollingSolids,
	FEB_ROLLING_HAPS_SOLIDS = @FebruaryRollingHapsSolids,
	
	MAR_HAPS = @MarchHaps,
	MAR_SOLIDS = @MarchSolids,
	MAR_HAPS_SOLIDS = @MarchHapsSolids,
	MAR_ROLLING_HAPS = @MarchRollingHaps,
	MAR_ROLLING_SOLIDS = @MarchRollingSolids,
	MAR_ROLLING_HAPS_SOLIDS = @MarchRollingHapsSolids,
	
	APR_HAPS = @AprilHaps,
	APR_SOLIDS = @AprilSolids,
	APR_HAPS_SOLIDS = @AprilHapsSolids,
	APR_ROLLING_HAPS = @AprilRollingHaps,
	APR_ROLLING_SOLIDS = @AprilRollingSolids,
	APR_ROLLING_HAPS_SOLIDS = @AprilRollingHapsSolids,
	
	MAY_HAPS = @MayHaps,
	MAY_SOLIDS = @MaySolids,
	MAY_HAPS_SOLIDS = @MayHapsSolids,
	MAY_ROLLING_HAPS = @MayRollingHaps,
	MAY_ROLLING_SOLIDS = @MayRollingSolids,
	MAY_ROLLING_HAPS_SOLIDS = @MayRollingHapsSolids,
	
	JUN_HAPS = @JuneHaps,
	JUN_SOLIDS = @JuneSolids,
	JUN_HAPS_SOLIDS = @JuneHapsSolids,
	JUN_ROLLING_HAPS = @JuneRollingHaps,
	JUN_ROLLING_SOLIDS = @JuneRollingSolids,
	JUN_ROLLING_HAPS_SOLIDS = @JuneRollingHapsSolids,
	
	JUL_HAPS = @JulyHaps,
	JUL_SOLIDS = @JulySolids,
	JUL_HAPS_SOLIDS = @JulyHapsSolids,
	JUL_ROLLING_HAPS = @JulyRollingHaps,
	JUL_ROLLING_SOLIDS = @JulyRollingSolids,
	JUL_ROLLING_HAPS_SOLIDS = @JulyRollingHapsSolids,
	
	AUG_HAPS = @AugustHaps,
	AUG_SOLIDS = @AugustSolids,
	AUG_HAPS_SOLIDS = @AugustHapsSolids,
	AUG_ROLLING_HAPS = @AugustRollingHaps,
	AUG_ROLLING_SOLIDS = @AugustRollingSolids,
	AUG_ROLLING_HAPS_SOLIDS = @AugustRollingHapsSolids,
	
	SEP_HAPS = @SeptemberHaps,
	SEP_SOLIDS = @SeptemberSolids,
	SEP_HAPS_SOLIDS = @SeptemberHapsSolids,
	SEP_ROLLING_HAPS = @SeptemberRollingHaps,
	SEP_ROLLING_SOLIDS = @SeptemberRollingSolids,
	SEP_ROLLING_HAPS_SOLIDS = @SeptemberRollingHapsSolids,
	
	OCT_HAPS = @OctoberHaps,
	OCT_SOLIDS = @OctoberSolids,
	OCT_HAPS_SOLIDS = @OctoberHapsSolids,
	OCT_ROLLING_HAPS = @OctoberRollingHaps,
	OCT_ROLLING_SOLIDS = @OctoberRollingSolids,
	OCT_ROLLING_HAPS_SOLIDS = @OctoberRollingHapsSolids,
	
	NOV_HAPS = @NovemberHaps,
	NOV_SOLIDS = @NovemberSolids,
	NOV_HAPS_SOLIDS = @NovemberHapsSolids,
	NOV_ROLLING_HAPS = @NovemberRollingHaps,
	NOV_ROLLING_SOLIDS = @NovemberRollingSolids,
	NOV_ROLLING_HAPS_SOLIDS = @NovemberRollingHapsSolids,
	
	DCM_HAPS = @DecemberHaps,
	DCM_SOLIDS = @DecemberSolids,
	DCM_HAPS_SOLIDS = @DecemberHapsSolids,
	DCM_ROLLING_HAPS = @DecemberRollingHaps,
	DCM_ROLLING_SOLIDS = @DecemberRollingSolids,
	DCM_ROLLING_HAPS_SOLIDS = @DecemberRollingHapsSolids
	
GO
