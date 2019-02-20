UPDATE ENV_ASSET
SET process = 0
WHERE id IN (
		SELECT id 
		FROM env_asset 
		WHERE process = 1 
		AND id NOT IN (SELECT asset_id 
				FROM env_process_asset 
				WHERE asset_id IN (SELECT id 
					           FROM env_asset 
					           WHERE process = 1)));


UPDATE ENV_ASSET
SET status_cd = 3
WHERE id IN (
             SELECT id 
             FROM env_asset 
             WHERE id NOT IN (SELECT asset_id 
                              FROM env_facility_asset 
                              WHERE asset_id IN (SELECT id 
                                                 FROM env_asset 
                                                 WHERE status_cd = 1)
                             ) and status_cd = 1 and client_id = 682
            );