
/****** Object:  StoredProcedure [dbo].[sp_assets_for_facility_throughputs]    Script Date: 02/17/2013 11:14:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_assets_for_facility_throughputs]
	@yearInt int = NULL, 
	@facilityId int = NULL
AS

    SELECT * 
        FROM Throughputs(@yearInt, @facilityId) 
        ORDER BY ASSET_NUMBER
