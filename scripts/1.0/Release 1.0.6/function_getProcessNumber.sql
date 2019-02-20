
drop function dbo.GetPN
go


CREATE FUNCTION dbo.GetPN
( 
    @asset_id VARCHAR(32)
) 
RETURNS VARCHAR(32) 
AS 
BEGIN 
    DECLARE @r VARCHAR(200) 
    SELECT @r = ISNULL(@r+',', '') + PROCESS_NUMBER 
        FROM dbo.ENV_PROCESS
        WHERE asset_id = @asset_id
    RETURN @r 
END 
GO 

