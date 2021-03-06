
/****** Object:  StoredProcedure [dbo].[sp_total_haps_by_asset]    Script Date: 02/20/2013 12:17:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_total_haps_by_asset]
	-- Required Parameters
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@assetId int = NULL
AS
	SELECT A_ID, SUM_LBS_HAPS 
	FROM Haps_aggregate_totals(@startDate, @endDate, @assetId)
