
/****** Object:  StoredProcedure [dbo].[sp_total_solids_by_asset]    Script Date: 02/20/2013 12:41:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_total_solids_by_asset]
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@assetId int = NULL
AS

SELECT A_ID, SUM_SOLIDS_VOLUME
	FROM Haps_aggregate_totals(@startDate, @endDate, @assetId)
