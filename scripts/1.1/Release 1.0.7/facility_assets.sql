
DECLARE @assetId int

DECLARE @assets TABLE (
	ASSET_ID int
)

INSERT INTO @assets
select id as ASSET_ID from env_asset where client_id = 682
and status_cd = 1


-- for each tank asset, get the totals and add to the grand total
WHILE (SELECT COUNT(*) FROM @assets) > 0
  BEGIN
    SELECT TOP 1 @assetId = ASSET_ID FROM @assets
	
	INSERT INTO ENV_FACILITY_ASSET
	(FACILITY_ID, ASSET_ID, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
	VALUES
	(1, @assetId, '2010-06-04', '2010-06-04', 4)
	
	-- loop and move on to the next tank in the result set
	DELETE FROM @assets WHERE ASSET_ID = @assetId
END

