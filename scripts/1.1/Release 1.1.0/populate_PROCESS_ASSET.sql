insert into ENV_PROCESS_ASSET
(PROCESS_ID, ASSET_ID, STATUS_CD, CREATE_TS, UPDATE_TS, UPDATE_USER_ID) 
SELECT ID, ASSET_ID, 1, CREATE_TS, UPDATE_TS, UPDATE_USER_ID from ENV_PROCESS