
/****** Object:  StoredProcedure [dbo].[sp_permit_assets_by_permit]    Script Date: 02/20/2013 13:06:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_permit_assets_by_permit]
	-- Required Parameters
	@permitId int = NULL
AS
	SELECT ASSET_ID AS A_ID FROM Permit_assets(NULL, @permitId, NULL)
