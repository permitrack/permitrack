/****** Script for SelectTopNRows command from SSMS  ******/
SELECT *
  FROM [ENV_SOURCE_USAGE] a inner join ENV_ASSET_SOURCE on a.ASSET_SOURCE_ID = ENV_ASSET_SOURCE.id inner join env_asset on env_asset.id = ENV_ASSET_SOURCE.ASSET_ID
  
  where a.ID in (1560, 1570)
