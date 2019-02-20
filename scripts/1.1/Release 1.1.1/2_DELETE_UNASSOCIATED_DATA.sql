--delete source substances that are attached to substances that have been deleted
UPDATE ENV_SOURCE_SUBSTANCE SET status_cd = 3 WHERE substance_id in (SELECT ID FROM ENV_SUBSTANCE WHERE STATUS_CD = 3) and status_cd = 1

--delete permit facilities that are attached to deleted facilities
DELETE FROM env_permit_facility where facility_id in (select id from env_facility where status_cd = 3)

--delete permit assets that are attached to deleted assets
DELETE FROM ENV_PERMIT_ASSET WHERE asset_id IN (SELECT ID FROM ENV_ASSET WHERE STATUS_CD = 3)

--delete process assets that are attached to deleted assets
UPDATE ENV_PROCESS_ASSET SET status_cd = 3 WHERE asset_id IN (SELECT ID FROM ENV_ASSET WHERE STATUS_CD = 3) and status_cd = 1

--set status code to deleted for asset sources that are attached to deleted sources
UPDATE env_asset_source SET status_cd = 3 where source_id in (select id from env_source where status_cd = 3) and status_cd = 1