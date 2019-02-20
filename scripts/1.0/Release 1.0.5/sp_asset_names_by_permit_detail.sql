
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_asset_names_by_permit_detail]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_asset_names_by_permit_detail]
GO



CREATE PROCEDURE dbo.sp_asset_names_by_permit_detail
	-- Required Parameters
	@permitDetailId int = NULL
AS

DECLARE 
	@assetList varchar(3000),
	@aId int,
	@assetName varchar(50)

-- write results into table
CREATE TABLE #TEMP_PERMITTED_ASSETS (
	A_ID int
)

INSERT INTO #TEMP_PERMITTED_ASSETS
 EXEC sp_assets_by_permit_detail @permitDetailId = @permitDetailId  -- select into scalar value function?

SET @assetList = ''

WHILE (SELECT COUNT(*) FROM #TEMP_PERMITTED_ASSETS) > 0
  BEGIN
    SELECT TOP 1 @aId = A_ID FROM #TEMP_PERMITTED_ASSETS
	 
	SELECT TOP 1 @assetName = NAME FROM ENV_ASSET WHERE ID = @aId
	IF (@assetName IS NOT NULL )	
	BEGIN
		SET @assetList = @assetList + @assetName + ', '
	END

    DELETE FROM #TEMP_PERMITTED_ASSETS WHERE A_ID = @aId
  END
  
DROP TABLE #TEMP_PERMITTED_ASSETS

SELECT @assetList AS ASSET_LIST
	
GO