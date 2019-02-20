if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ENV_ASSET_ENV_FACILITY]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ENV_ASSET] DROP CONSTRAINT FK_ENV_ASSET_ENV_FACILITY
GO

ALTER TABLE env_asset drop column facility_id;

ALTER TABLE ENV_ASSET ADD [BUILDING_HEAT] [bit] NULL;
ALTER TABLE ENV_ASSET ADD [PROCESS] [bit] NULL;

update env_asset 
set building_heat = 1
where id in (
		SELECT a.id
		from ENV_ASSET_TYPE assetType, ENV_ASSET a
		where assetType.asset_id = a.id
		and   assetType.asset_type_cd = 4
             );

update env_asset 
set process = 1
where id in (
		SELECT a.id
		from ENV_ASSET_TYPE assetType, ENV_ASSET a
		where assetType.asset_id = a.id
		and   assetType.asset_type_cd = 5
             );