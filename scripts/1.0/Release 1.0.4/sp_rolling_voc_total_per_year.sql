


if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_rolling_voc_total_per_year]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_rolling_voc_total_per_year]
GO


CREATE PROCEDURE dbo.sp_rolling_voc_total_per_year
	-- Required Parameters
	@yearInt int = NULL,
	@clientId int = NULL
AS

DECLARE 
	@JanuaryPrev 	FLOAT(8),
	@FebruaryPrev 	FLOAT(8),
	@MarchPrev	FLOAT(8),
	@AprilPrev	FLOAT(8),
	@MayPrev	FLOAT(8),
	@JunePrev 	FLOAT(8),
	@JulyPrev 	FLOAT(8),
	@AugustPrev	FLOAT(8),
	@SeptemberPrev	FLOAT(8),
	@OctoberPrev	FLOAT(8),
	@NovemberPrev 	FLOAT(8),
	@DecemberPrev 	FLOAT(8),
	@JanuaryPrevTon 	FLOAT(8),
	@FebruaryPrevTon 	FLOAT(8),
	@MarchPrevTon		FLOAT(8),
	@AprilPrevTon		FLOAT(8),
	@MayPrevTon		FLOAT(8),
	@JunePrevTon 		FLOAT(8),
	@JulyPrevTon 		FLOAT(8),
	@AugustPrevTon		FLOAT(8),
	@SeptemberPrevTon	FLOAT(8),
	@OctoberPrevTon		FLOAT(8),
	@NovemberPrevTon 	FLOAT(8),
	@DecemberPrevTon 	FLOAT(8),
	@January 	FLOAT(8),
	@February 	FLOAT(8),
	@March		FLOAT(8),
	@April		FLOAT(8),
	@May		FLOAT(8),
	@June 		FLOAT(8),
	@July 		FLOAT(8),
	@August		FLOAT(8),
	@September	FLOAT(8),
	@October	FLOAT(8),
	@November 	FLOAT(8),
	@December 	FLOAT(8),
	@JanuaryTon 	FLOAT(8),
	@FebruaryTon 	FLOAT(8),
	@MarchTon	FLOAT(8),
	@AprilTon	FLOAT(8),
	@MayTon		FLOAT(8),
	@JuneTon 	FLOAT(8),
	@JulyTon 	FLOAT(8),
	@AugustTon	FLOAT(8),
	@SeptemberTon	FLOAT(8),
	@OctoberTon	FLOAT(8),
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
	@nextYearChar varchar(4)

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

EXEC @JanuaryPrev 	= dbo.sp_total_voc_month @startDate=@JanPrevStartDate, @endDate=@JanPrevEndDate, @clientId=@clientId
EXEC @FebruaryPrev 	= dbo.sp_total_voc_month @startDate=@FebPrevStartDate, @endDate=@FebPrevEndDate, @clientId=@clientId
EXEC @MarchPrev 	= dbo.sp_total_voc_month @startDate=@MarPrevStartDate, @endDate=@MarPrevEndDate, @clientId=@clientId
EXEC @AprilPrev 	= dbo.sp_total_voc_month @startDate=@AprPrevStartDate, @endDate=@AprPrevEndDate, @clientId=@clientId
EXEC @MayPrev 		= dbo.sp_total_voc_month @startDate=@MayPrevStartDate, @endDate=@MayPrevEndDate, @clientId=@clientId
EXEC @JunePrev 		= dbo.sp_total_voc_month @startDate=@JunPrevStartDate, @endDate=@JunPrevEndDate, @clientId=@clientId
EXEC @JulyPrev 		= dbo.sp_total_voc_month @startDate=@JulPrevStartDate, @endDate=@JulPrevEndDate, @clientId=@clientId
EXEC @AugustPrev 	= dbo.sp_total_voc_month @startDate=@AugPrevStartDate, @endDate=@AugPrevEndDate, @clientId=@clientId
EXEC @SeptemberPrev	= dbo.sp_total_voc_month @startDate=@SepPrevStartDate, @endDate=@SepPrevEndDate, @clientId=@clientId
EXEC @OctoberPrev	= dbo.sp_total_voc_month @startDate=@OctPrevStartDate, @endDate=@OctPrevEndDate, @clientId=@clientId
EXEC @NovemberPrev	= dbo.sp_total_voc_month @startDate=@NovPrevStartDate, @endDate=@NovPrevEndDate, @clientId=@clientId
EXEC @DecemberPrev	= dbo.sp_total_voc_month @startDate=@DecPrevStartDate, @endDate=@DecPrevEndDate, @clientId=@clientId

EXEC @January 	= dbo.sp_total_voc_month @startDate=@JanStartDate, @endDate=@JanEndDate, @clientId=@clientId
EXEC @February 	= dbo.sp_total_voc_month @startDate=@FebStartDate, @endDate=@FebEndDate, @clientId=@clientId
EXEC @March 	= dbo.sp_total_voc_month @startDate=@MarStartDate, @endDate=@MarEndDate, @clientId=@clientId
EXEC @April 	= dbo.sp_total_voc_month @startDate=@AprStartDate, @endDate=@AprEndDate, @clientId=@clientId
EXEC @May 	= dbo.sp_total_voc_month @startDate=@MayStartDate, @endDate=@MayEndDate, @clientId=@clientId
EXEC @June 	= dbo.sp_total_voc_month @startDate=@JunStartDate, @endDate=@JunEndDate, @clientId=@clientId
EXEC @July 	= dbo.sp_total_voc_month @startDate=@JulStartDate, @endDate=@JulEndDate, @clientId=@clientId
EXEC @August 	= dbo.sp_total_voc_month @startDate=@AugStartDate, @endDate=@AugEndDate, @clientId=@clientId
EXEC @September	= dbo.sp_total_voc_month @startDate=@SepStartDate, @endDate=@SepEndDate, @clientId=@clientId
EXEC @October	= dbo.sp_total_voc_month @startDate=@OctStartDate, @endDate=@OctEndDate, @clientId=@clientId
EXEC @November	= dbo.sp_total_voc_month @startDate=@NovStartDate, @endDate=@NovEndDate, @clientId=@clientId
EXEC @December	= dbo.sp_total_voc_month @startDate=@DecStartDate, @endDate=@DecEndDate, @clientId=@clientId


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
	JAN_VOC_LBS =	@January, 
	JAN_VOC_TON =	@January / 2000, 
	JAN_ROLLING =	@FebruaryPrevTon + @MarchPrevTon + @AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon,

	FEB_VOC_LBS = 	@February,	
	FEB_VOC_TON = 	@February / 2000, 
	FEB_ROLLING = 	@MarchPrevTon + @AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon,

	MAR_VOC_LBS = 	@March,		
	MAR_VOC_TON = 	@March / 2000, 
 	MAR_ROLLING = 	@AprilPrevTon + @MayPrevTon +
			@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon + @MarchTon, 

	APR_VOC_LBS = 	@April,		
	APR_VOC_TON = 	@April / 2000, 
 	APR_ROLLING = 	@MayPrevTon + @JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon,

	MAY_VOC_LBS = 	@May,		
	MAY_VOC_TON = 	@May / 2000, 
 	MAY_ROLLING = 	@JunePrevTon + @JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon,

	JUN_VOC_LBS = 	@June,		
	JUN_VOC_TON = 	@June / 2000, 
 	JUN_ROLLING = 	@JulyPrevTon + @AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon + @JuneTon,

	JUL_VOC_LBS = 	@July,		
	JUL_VOC_TON = 	@July / 2000, 
 	JUL_ROLLING = 	@AugustPrevTon + @SeptemberPrevTon +
			@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + @JanuaryTon + @FebruaryTon +
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon,

	AUG_VOC_LBS = 	@August,		
	AUG_VOC_TON = 	@August / 2000, 
 	AUG_ROLLING = 	@SeptemberPrevTon + @OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon + 
			@JanuaryTon + @FebruaryTon + @MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon,

	SEP_VOC_LBS = 	@September,	
	SEP_VOC_TON = 	@September / 2000, 
 	SEP_ROLLING = 	@OctoberPrevTon + @NovemberPrevTon + @DecemberPrevTon +  @JanuaryTon + @FebruaryTon + 
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + @SeptemberTon,

	OCT_VOC_LBS = 	@October,		
	OCT_VOC_TON = 	@October / 2000, 
 	OCT_ROLLING = 	@NovemberPrevTon + @DecemberPrevTon +  @JanuaryTon + @FebruaryTon + 
			@MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + @SeptemberTon + @OctoberTon,

	NOV_VOC_LBS = 	@November,	
	NOV_VOC_TON = 	@November / 2000, 
	NOV_ROLLING = 	@DecemberPrevTon +  @JanuaryTon + @FebruaryTon + @MarchTon + @AprilTon + @MayTon + 
			@JuneTon + @JulyTon + @AugustTon + @SeptemberTon + @OctoberTon + @NovemberTon,

	DCM_VOC_LBS = 	@December,	
	DCM_VOC_TON = 	@December / 2000,
	DCM_ROLLING = 	@JanuaryTon + @FebruaryTon +  @MarchTon + @AprilTon + @MayTon + @JuneTon + @JulyTon + @AugustTon + 
			@SeptemberTon + @OctoberTon + @NovemberTon + @DecemberTon
	
GO
