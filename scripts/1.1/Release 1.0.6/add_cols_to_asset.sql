

ALTER TABLE ENV_ASSET ADD [METER] [bit] NULL;
ALTER TABLE ENV_ASSET ADD [BELONGS_TO_METER] [int] NULL;


select * from env_asset

-- meter 1 id: 35
-- meter 2 id: 37

update env_asset 
set METER = 1
where ID = 35

update env_asset 
set METER = 1
where ID = 37

update env_asset
set BELONGS_TO_METER = 35, PARENT_ASSET_ID = NULL
where parent_asset_id = 35

update env_asset
set BELONGS_TO_METER = 37, PARENT_ASSET_ID = NULL
where parent_asset_id = 37



