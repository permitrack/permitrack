USE [cap2]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_form_12_grand_total]
		@yearInt = 2011,
		@facilityId = 1,
		@clientId = 682

SELECT	'Return Value' = @return_value

GO
