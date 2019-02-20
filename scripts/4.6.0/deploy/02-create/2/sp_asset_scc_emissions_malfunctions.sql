

/****** Object:  StoredProcedure [dbo].[sp_asset_scc_emissions_malfunctions]    Script Date: 02/22/2013 14:30:45 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_asset_scc_emissions_malfunctions]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_asset_scc_emissions_malfunctions]
GO



/****** Object:  StoredProcedure [dbo].[sp_asset_scc_emissions_malfunctions]    Script Date: 02/22/2013 14:30:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[sp_asset_scc_emissions_malfunctions]
	@yearInt int = NULL,
	@facilityId int = NULL,
	@assetId int = NULL,
	@controlUsageId int = NULL
AS
--DECLARE
--	@yearChar varchar(4),
--	@StartDate varchar(10),
--	@EndDate varchar(10)

	--SET @yearChar = @yearInt
	--SET @StartDate 	= '01-01-' + @yearChar
	--SET @EndDate 	= '12-31-' + @yearChar
	 
	SELECT * FROM Emissions(@yearInt, @facilityId, NULL, @assetId, NULL, NULL, @controlUsageId)

GO


