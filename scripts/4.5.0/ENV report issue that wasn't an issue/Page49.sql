USE [sehtechdb]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_asset_scc_emissions]
		@yearInt = 2010,
		@facilityId = 1,
		@sccId = 9,
		@assetId = 16

SELECT	'Return Value' = @return_value

GO
