
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_clients_inspectors]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_clients_inspectors]
GO


CREATE PROCEDURE dbo.sp_clients_inspectors
	-- Required Parameters
	@clientId int = NULL,
	@refClientId int = NULL,
	@statusCd varchar(4) = NULL
AS

SELECT 
	CONTACT.ID
FROM
	CAP_CONTACT AS CONTACT,
	CAP_CONTACT_CONTACT_TYPE AS CTYPE
WHERE
	CONTACT.ID IN (
		SELECT CLIENT_CONTACT.CONTACT_ID 
		FROM CAP_CLIENT_CONTACT AS CLIENT_CONTACT
		WHERE CLIENT_CONTACT.CLIENT_ID = @clientId )
	AND CONTACT.STATUS_CD = @statusCd
	AND CONTACT.ORGANIZATION_ID IN (
		SELECT ORG.ID 
		FROM CAP_CONTACT_ORGANIZATION AS ORG
		WHERE ORG.CLIENT_ID = @clientId
		AND ORG.REF_CLIENT_ID = @refClientId )
	AND CTYPE.CONTACT_TYPE_CD = 'INSP'
GROUP BY CONTACT.ID

GO
