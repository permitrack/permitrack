
/****** Object:  StoredProcedure [dbo].[sp_permit_assets_by_client]    Script Date: 02/20/2013 13:02:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_permit_assets_by_client]
	@clientId int = NULL
AS
	SELECT ASSET_ID AS A_ID FROM Permit_assets(@clientId, NULL, NULL)
