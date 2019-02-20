USE [cap2]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_asset_scc_emissions]
		@yearInt = 2011,
		@facilityId = 1,
		@sccId = 6,
		@assetId = 27


EXEC	@return_value = [dbo].[sp_asset_scc_emissions]
		@yearInt = 2011,
		@facilityId = 1,
		@sccId = 9,
		@assetId = 27
