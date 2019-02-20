EXEC sp_rename 
    @objname = 'AUDIT_ENV_ASSET.PERMIT_SHIELD', 
    @newname = 'STORAGE_TANK', 
    @objtype = 'COLUMN'