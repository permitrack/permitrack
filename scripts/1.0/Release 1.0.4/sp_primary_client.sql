
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_primary_client]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_primary_client]
GO


CREATE PROCEDURE dbo.sp_primary_client
	-- Required Parameters
	@clientId int = NULL,
	@statusCd varchar(4) = NULL
AS

-- Check to see if the given client is a Primary client
SELECT 
	CLIENT.ID, CLIENT.NAME
FROM 
	CLIENT AS CLIENT, 
	CAP_CLIENT_TYPE AS CLIENT_TYPE
WHERE 
	CLIENT.ID = @clientId
	AND CLIENT.STATUS_CD = @statusCd
	AND CLIENT.ID = CLIENT_TYPE.CLIENT_ID
	AND CLIENT_TYPE.CLIENT_TYPE_ID = 1

GO
