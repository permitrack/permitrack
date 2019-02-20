

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_rolling_voc_total_per_year_by_permit]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_rolling_voc_total_per_year_by_permit]
GO



CREATE PROCEDURE dbo.sp_rolling_voc_total_per_year_by_permit
	-- Required Parameters
	@yearInt int = NULL,
	@permitId int = NULL
AS

DECLARE 
	@JanuaryPrev 		FLOAT(8),
	@FebruaryPrev 		FLOAT(8),
	@MarchPrev			FLOAT(8),
	@AprilPrev			FLOAT(8),
	@MayPrev			FLOAT(8),
	@JunePrev 			FLOAT(8),
	@JulyPrev 			FLOAT(8),
	@AugustPrev			FLOAT(8),
	@SeptemberPrev		FLOAT(8),
	@OctoberPrev		FLOAT(8),
	@NovemberPrev 		FLOAT(8),
	@DecemberPrev 		FLOAT(8),
	@JanuaryPrevTon 	FLOAT(8),
	@FebruaryPrevTon 	FLOAT(8),
	@MarchPrevTon		FLOAT(8),
	@AprilPrevTon		FLOAT(8),
	@MayPrevTon			FLOAT(8),
	@JunePrevTon 		FLOAT(8),
	@JulyPrevTon 		FLOAT(8),
	@AugustPrevTon		FLOAT(8),
	@SeptemberPrevTon	FLOAT(8),
	@OctoberPrevTon		FLOAT(8),
	@NovemberPrevTon 	FLOAT(8),
	@DecemberPrevTon 	FLOAT(8),
	@January 		FLOAT(8),
	@February 		FLOAT(8),
	@March			FLOAT(8),
	@April			FLOAT(8),
	@May			FLOAT(8),
	@June 			FLOAT(8),
	@July 			FLOAT(8),
	@August			FLOAT(8),
	@September		FLOAT(8),
	@October		FLOAT(8),
	@November 		FLOAT(8),
	@December 		FLOAT(8),
	@JanuaryTon 	FLOAT(8),
	@FebruaryTon 	FLOAT(8),
	@MarchTon		FLOAT(8),
	@AprilTon		FLOAT(8),
	@MayTon			FLOAT(8),
	@JuneTon 		FLOAT(8),
	@JulyTon 		FLOAT(8),
	@AugustTon		FLOAT(8),
	@SeptemberTon	FLOAT(8),
	@OctoberTon		FLOAT(8),
	@NovemberTon 	FLOAT(8),
	@DecemberTon 	FLOAT(8),

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
	@hold FLOAT(8)

-- write results into table
CREATE TABLE #TEMP_PERMITTED_ASSETS (
	A_ID int
)

INSERT INTO #TEMP_PERMITTED_ASSETS
 EXEC sp_permit_assets_by_permit @permitId = @permitId  -- select into scalar value function?
 
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

SET @JanuaryPrev		 = 0
SET @FebruaryPrev 		 = 0
SET @MarchPrev			 = 0
SET @AprilPrev			 = 0
SET @MayPrev			 = 0
SET @JunePrev 			 = 0
SET @JulyPrev 			 = 0
SET @AugustPrev			 = 0
SET @SeptemberPrev		 = 0
SET @OctoberPrev		 = 0
SET @NovemberPrev 		 = 0
SET @DecemberPrev 		 = 0
SET @JanuaryPrevTon 	 = 0
SET @FebruaryPrevTon 	 = 0
SET @MarchPrevTon		 = 0
SET @AprilPrevTon		 = 0
SET @MayPrevTon			 = 0
SET @JunePrevTon 		 = 0
SET @JulyPrevTon 		 = 0
SET @AugustPrevTon		 = 0
SET @SeptemberPrevTon	 = 0
SET @OctoberPrevTon		 = 0
SET @NovemberPrevTon 	 = 0
SET @DecemberPrevTon 	 = 0
SET @January 		 = 0
SET @February 		 = 0
SET @March			 = 0
SET @April			 = 0
SET @May			 = 0
SET @June 			 = 0
SET @July 			 = 0
SET @August			 = 0
SET @September		 = 0
SET @October		 = 0
SET @November 		 = 0
SET @December 		 = 0
SET @JanuaryTon 	 = 0
SET @FebruaryTon 	 = 0
SET @MarchTon		 = 0
SET @AprilTon		 = 0
SET @MayTon			 = 0
SET @JuneTon 		 = 0
SET @JulyTon 		 = 0
SET @AugustTon		 = 0
SET @SeptemberTon	 = 0
SET @OctoberTon		 = 0
SET @NovemberTon 	 = 0
SET @DecemberTon 	 = 0

WHILE (SELECT COUNT(*) FROM #TEMP_PERMITTED_ASSETS) > 0
  BEGIN
    SELECT TOP 1 @aId = A_ID FROM #TEMP_PERMITTED_ASSETS
	SET @hold = 0
	 
	 -- get previous years month to month totals
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JanPrevStartDate, @endDate=@JanPrevEndDate, @assetId=@aId
	SET @JanuaryPrev = @JanuaryPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@FebPrevStartDate, @endDate=@FebPrevEndDate, @assetId=@aId
	SET @FebruaryPrev = @FebruaryPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@MarPrevStartDate, @endDate=@MarPrevEndDate, @assetId=@aId
	SET @MarchPrev = @MarchPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@AprPrevStartDate, @endDate=@AprPrevEndDate, @assetId=@aId
	SET @AprilPrev = @AprilPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@MayPrevStartDate, @endDate=@MayPrevEndDate, @assetId=@aId
	SET @MayPrev = @MayPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JunPrevStartDate, @endDate=@JunPrevEndDate, @assetId=@aId
	SET @JunePrev = @JunePrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JulPrevStartDate, @endDate=@JulPrevEndDate, @assetId=@aId
	SET @JulyPrev = @JulyPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@AugPrevStartDate, @endDate=@AugPrevEndDate, @assetId=@aId
	SET @AugustPrev = @AugustPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@SepPrevStartDate, @endDate=@SepPrevEndDate, @assetId=@aId
	SET @SeptemberPrev = @SeptemberPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@OctPrevStartDate, @endDate=@OctPrevEndDate, @assetId=@aId
	SET @OctoberPrev = @OctoberPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@NovPrevStartDate, @endDate=@NovPrevEndDate, @assetId=@aId
	SET @NovemberPrev = @NovemberPrev + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@DecPrevStartDate, @endDate=@DecPrevEndDate, @assetId=@aId
	SET @DecemberPrev = @DecemberPrev + @hold
	SET @hold = 0
	
	
	-- get this year's month to month totals
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JanStartDate, @endDate=@JanEndDate, @assetId=@aId
	SET @January = @January + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@FebStartDate, @endDate=@FebEndDate, @assetId=@aId
	SET @February = @February + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@MarStartDate, @endDate=@MarEndDate, @assetId=@aId
	SET @March = @March + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@AprStartDate, @endDate=@AprEndDate, @assetId=@aId
	SET @April = @April + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@MayStartDate, @endDate=@MayEndDate, @assetId=@aId
	SET @May = @May + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JunStartDate, @endDate=@JunEndDate, @assetId=@aId
	SET @June = @June + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@JulStartDate, @endDate=@JulEndDate, @assetId=@aId
	SET @July = @July + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@AugStartDate, @endDate=@AugEndDate, @assetId=@aId
	SET @August = @August + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@SepStartDate, @endDate=@SepEndDate, @assetId=@aId
	SET @September = @September + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@OctStartDate, @endDate=@OctEndDate, @assetId=@aId
	SET @October = @October + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@NovStartDate, @endDate=@NovEndDate, @assetId=@aId
	SET @November = @November + @hold
	SET @hold = 0
	
	EXEC @hold = dbo.sp_total_voc_month_for_asset @startDate=@DecStartDate, @endDate=@DecEndDate, @assetId=@aId
	SET @December = @December + @hold
	SET @hold = 0
		 
    DELETE FROM #TEMP_PERMITTED_ASSETS WHERE A_ID = @aId
  END
  
DROP TABLE #TEMP_PERMITTED_ASSETS

SET @JanuaryPrevTon = @JanuaryPrev / 2000
SET @FebruaryPrevTon = @FebruaryPrev / 2000  
SET @MarchPrevTon = @MarchPrev / 2000 
SET @AprilPrevTon = @AprilPrev / 2000  
SET @MayPrevTon = @MayPrev / 2000
SET @JunePrevTon = @JunePrev / 2000 
SET @JulyPrevTon = @JulyPrev / 2000 
SET @AugustPrevTon = @AugustPrev / 2000 
SET @SeptemberPrevTon = @SeptemberPrev / 2000 
SET @OctoberPrevTon = @OctoberPrev / 2000
SET @NovemberPrevTon = @NovemberPrev / 2000 
SET @DecemberPrevTon = @DecemberPrev / 2000

SET @JanuaryTon = @January / 2000
SET @FebruaryTon = @February / 2000  
SET @MarchTon = @March / 2000 
SET @AprilTon = @April / 2000  
SET @MayTon = @May / 2000
SET @JuneTon = @June / 2000 
SET @JulyTon = @July / 2000 
SET @AugustTon = @August / 2000 
SET @SeptemberTon = @September / 2000 
SET @OctoberTon = @October / 2000
SET @NovemberTon = @November / 2000 
SET @DecemberTon = @December / 2000

SELECT 	-- values used in the report
	JAN_VOC_TON =	@JanuaryTon,
	JAN_ROLLING =	@FebruaryPrevTon + @MarchPrevTon + @AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon,

	FEB_VOC_TON = 	@FebruaryTon, 
	FEB_ROLLING = 	@MarchPrevTon + @AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon,

	MAR_VOC_TON = 	@MarchTon, 
 	MAR_ROLLING = 	@AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon + @MarchTon, 
	
	APR_VOC_TON = 	@AprilTon, 
 	APR_ROLLING = 	@MayPrevTon + @JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon,
	
	MAY_VOC_TON = 	@MayTon, 
 	MAY_ROLLING = 	@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon,
		
	JUN_VOC_TON = 	@JuneTon, 
 	JUN_ROLLING = 	@JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon + @JuneTon,
	
	JUL_VOC_TON = 	@JulyTon, 
 	JUL_ROLLING = 	@AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon,
		
	AUG_VOC_TON = 	@AugustTon, 
 	AUG_ROLLING = 	@SeptemberPrevTon + @OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + 
			@JanuaryTon + @FebruaryTon + @MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon,

	SEP_VOC_TON = 	@SeptemberTon, 
 	SEP_ROLLING = 	@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon +  @JanuaryTon + @FebruaryTon + 
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + @SeptemberTon,
	
	OCT_VOC_TON = 	@OctoberTon, 
 	OCT_ROLLING = 	@NovemberPrevTon + @DecemberPrevTon +  @JanuaryTon + @FebruaryTon + 
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + @SeptemberTon + @OctoberTon,

	NOV_VOC_TON = 	@NovemberTon, 
	NOV_ROLLING = 	@DecemberPrevTon +  @JanuaryTon + @FebruaryTon + @MarchTon + @AprilTon + @MayTon + 
			@JuneTon + @JulyTon + @AugustTon + @SeptemberTon + @OctoberTon + @NovemberTon,

	DCM_VOC_TON = 	@DecemberTon,
	DCM_ROLLING = 	@JanuaryTon + @FebruaryTon +  @MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + 
			@SeptemberTon + @OctoberTon + @NovemberTon + @DecemberTon
	
GO
