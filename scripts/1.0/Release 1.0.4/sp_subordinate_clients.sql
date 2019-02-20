
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_subordinate_clients]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_subordinate_clients]
GO


CREATE PROCEDURE dbo.sp_subordinate_clients
	-- Required Parameters
	@clientId int = NULL,
	@statusCd varchar(4) = NULL
AS

SELECT -- Find all of the active subordianate clients related to this client
	RELATED_CLIENT.ID,
	RELATED_CLIENT.NAME 
FROM 
	CLIENT AS CLIENT,
	CLIENT AS RELATED_CLIENT,
	EC_CLIENT_RELATIONSHIP AS CLIENT_RELATIONSHIP
WHERE
	CLIENT.ID = @clientId
	AND CLIENT.ID = CLIENT_RELATIONSHIP.CLIENT_ID
	AND CLIENT_RELATIONSHIP.RELATED_CLIENT_ID = RELATED_CLIENT.ID
	AND RELATED_CLIENT.STATUS_CD = @statusCd 
GROUP BY
	RELATED_CLIENT.ID, RELATED_CLIENT.NAME 
ORDER BY RELATED_CLIENT.NAME 


GO

