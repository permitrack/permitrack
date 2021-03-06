
/****** Object:  StoredProcedure [dbo].[sp_total_voc_month_for_asset]    Script Date: 02/20/2013 13:30:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_total_voc_month_for_asset]
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@assetId int = NULL
AS


	DECLARE @result float(8)

	SELECT
		@result = SUM_LBS_VOC 
		FROM Vocs_totals(@startDate, @endDate, @assetId)
	 
	RETURN @result
