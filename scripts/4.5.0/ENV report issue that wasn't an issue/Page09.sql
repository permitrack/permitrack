USE [sehtechdb]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_assets_for_facility_throughputs]
		@yearInt = 2010,
		@facilityId = 1

SELECT	'Return Value' = @return_value

GO
