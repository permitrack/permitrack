
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_preferred_partners]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_preferred_partners]
GO


CREATE PROCEDURE dbo.sp_preferred_partners
	-- Required Parameters
	@clientId int = NULL,
	@statusCd varchar(4) = NULL
AS

--Find all of the active clients that have assigned the given client as a preferred partner
SELECT 
	CLIENT.ID, CLIENT.NAME
FROM 
	CLIENT AS CLIENT,
	CLIENT AS RELATED_CLIENT,
	EC_CLIENT_RELATIONSHIP AS CLIENT_RELATIONSHIP
WHERE
	CLIENT_RELATIONSHIP.RELATED_CLIENT_ID = @clientId 
	AND CLIENT.ID = CLIENT_RELATIONSHIP.CLIENT_ID
	AND CLIENT_RELATIONSHIP.CLIENT_RELATIONSHIP_TYPE_ID = 1
	AND CLIENT.STATUS_CD = @statusCd
GROUP BY 
	CLIENT.ID, CLIENT.NAME
ORDER BY
	CLIENT.NAME


GO
