
/****** Object:  StoredProcedure [dbo].[sp_form_12_grand_total]    Script Date: 02/05/2013 10:59:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_form_12_grand_total]
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL,
	@clientId int = NULL
AS

CREATE TABLE #form_12_by_asset (
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
	OTHER_HAPS decimal(18, 6),
	VOCnoHAPS decimal(18, 6)
	 , CO2    decimal(18, 6)
	 , CH4    decimal(18, 6)
	 , N2O    decimal(18, 6)
)	


 EXEC sp_form_12_by_asset @yearInt=@yearInt, @facilityId=@facilityId, @clientId=@clientId



 SELECT SUM(CO) AS CO,
	SUM(NH3) AS NH3, 
	SUM(NOX) AS NOX, 
	SUM(LEADS) AS LEADS, 
	SUM(PM10) AS PM10, 
	SUM(PM2_5) AS PM2_5, 
	SUM(SOX) AS SOX, 
	SUM(VOC) AS VOC, 
	SUM(GREATEST_HAP) AS GREATEST_HAP, 
	SUM(OTHER_HAPS) AS OTHER_HAPS,
	SUM(VOCnoHAPS) AS VOCnoHAPS
	 , SUM(CO2) AS CO2
	 , SUM(CH4) AS CH4
	 , SUM(N2O) AS N2O
 FROM #form_12_by_asset
