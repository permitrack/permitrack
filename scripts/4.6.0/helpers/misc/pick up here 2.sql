USE [cap2]
GO

DECLARE	@return_value int


EXEC	@return_value = [dbo].[sp_form_12_by_asset]
		@yearInt = 2011,
		@facilityId = 1


EXEC	@return_value = [dbo].[sp_asset_scc_emissions]
		@yearInt = 2011,
		@facilityId = 1,
		@assetId = 1,
		@sccId = 6


select * from ENV_SCC_INFO --inner join env_sc

select * from LOOKUP_ENV_SUBSTANCE_TYPE