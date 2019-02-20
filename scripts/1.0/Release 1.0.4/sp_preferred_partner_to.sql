
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_preferred_partner_to]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_preferred_partner_to]
GO


CREATE PROCEDURE dbo.sp_preferred_partner_to
	-- Required Parameters
	@clientId int = NULL,
	@statusCd varchar(4) = NULL
AS

--Find all of the active clients that the given client has assigned as a preferred partner
SELECT
	CLIENT.ID AS ID,
	CLIENT.NAME AS NAME
FROM
	CLIENT AS CLIENT,
	CLIENT AS RELATED_CLIENT,
	EC_CLIENT_RELATIONSHIP AS CLIENT_RELATIONSHIP
WHERE
	CLIENT.ID = @clientId
	AND CLIENT.ID = CLIENT_RELATIONSHIP.CLIENT_ID
	AND CLIENT_RELATIONSHIP.RELATED_CLIENT_ID = RELATED_CLIENT.ID
	AND CLIENT_RELATIONSHIP.CLIENT_RELATIONSHIP_TYPE_ID = 1
	AND RELATED_CLIENT.STATUS_CD = @statusCd
GROUP BY CLIENT.ID, CLIENT.NAME 

GO
