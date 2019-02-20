

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_permit_assets_by_permit]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_permit_assets_by_permit]
GO


CREATE PROCEDURE dbo.sp_permit_assets_by_permit
	-- Required Parameters
	@permitId int = NULL
AS

-- all assets for a single permit
SELECT 
  COALESCE(ASSET_MASTER.CA2_ID, ASSET_MASTER.CA1_ID, ASSET_MASTER.A_ID, 0)
 FROM (
SELECT	
	asset.ID as A_ID,
	child_asset1.ID as CA1_ID,
	child_asset2.ID as CA2_ID
FROM ENV_PERMIT AS permit 
    LEFT JOIN ENV_PERMIT_DETAIL as permit_detail 
        ON permit.ID = permit_detail.PERMIT_ID
    LEFT JOIN ENV_PERMIT_ASSET as permit_asset
        ON permit_detail.ID = permit_asset.PERMIT_DETAIL_ID 
	AND permit_detail.STATUS_CD = '1'
    LEFT JOIN ENV_ASSET as asset -- get assets tied to this permit detail
        ON permit_asset.ASSET_ID = asset.ID 
	AND asset.STATUS_CD = '1'
	AND asset.PROCESS = '1'
    LEFT JOIN ENV_ASSET as child_asset1 
        ON child_asset1.PARENT_ASSET_ID = asset.ID 
	AND child_asset1.STATUS_CD = '1'
	AND child_asset1.PROCESS = '1'
    LEFT JOIN ENV_ASSET as child_asset2 
        ON child_asset2.PARENT_ASSET_ID = child_asset1.ID 
	AND child_asset2.STATUS_CD = '1'
	AND child_asset2.PROCESS = '1'
WHERE permit.ID = @permitId --permit id
AND permit.STATUS_CD = '1'
) AS ASSET_MASTER
GROUP BY ASSET_MASTER.A_ID, ASSET_MASTER.CA1_ID, ASSET_MASTER.CA2_ID

GO
