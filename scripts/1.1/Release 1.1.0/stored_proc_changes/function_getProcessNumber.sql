
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
    SELECT @r = ISNULL(@r+',', '') + process.PROCESS_NUMBER 
        FROM dbo.ENV_PROCESS_ASSET as prcass
		INNER JOIN dbo.ENV_PROCESS as process
		  ON process.id = prcass.process_id
        WHERE prcass.asset_id = @asset_id
    RETURN @r 
END 
GO 

