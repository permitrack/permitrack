
USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_authorized_client_list]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_authorized_client_list]
GO

CREATE PROCEDURE dbo.sp_authorized_client_list
	-- Required Parameters
	@clientId int = NULL,
	@statusCd varchar(4) = NULL
AS

CREATE TABLE #TEMP_CLIENT_LIST (
	ID int,
	NAME varchar(100)
)

INSERT INTO #TEMP_CLIENT_LIST
EXEC sp_subordinate_clients @clientId = @clientId, @statusCd = @statusCd

INSERT INTO #TEMP_CLIENT_LIST
EXEC sp_preferred_partners @clientId = @clientId, @statusCd = @statusCd

SELECT * FROM #TEMP_CLIENT_LIST
ORDER BY NAME



GO
