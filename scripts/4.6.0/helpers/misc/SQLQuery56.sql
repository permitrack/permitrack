USE [cap2]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_all_subst_haps_by_asset]
		@startDate = N'01/01/2011',
		@endDate = N'12/31/2011',
		@assetId = 27

SELECT	'Return Value' = @return_value

GO
