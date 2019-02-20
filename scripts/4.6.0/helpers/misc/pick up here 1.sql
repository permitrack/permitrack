USE [cap2]
GO

DECLARE	@return_value int


EXEC	@return_value = [dbo].[sp_assets_and_sccs_for_facility_year]
		@yearInt = 2011,
		@facilityId = 1


EXEC	@return_value = [dbo].[sp_asset_scc_emissions]
		@yearInt = 2011,
		@facilityId = 1,
		@assetId = 20,
		@sccId = 9



SELECT 
								16,
								6.26,
								--@solidsContent,
								CONTROL_SUBSTANCE_TYPE_CD,
								RATING,
								MALFUNCTION_RATING
							FROM Liquids_subtotals('01/01/2011', '12/31/2011', NULL, 20, 16, NULL)
							
--select * from ENV_SCC_INFO --inner join env_sc