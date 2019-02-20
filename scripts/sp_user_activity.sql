SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[sp_user_activity] 
	-- Add the parameters for the stored procedure here
	@userId int = 0,
	@clientId int = 0,
	@startDate datetime = null,
	@endDate datetime = null,
	@top int = 500
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	DECLARE @topT int = 50000
	
	IF @endDate is null
        SET @endDate = CONVERT(datetime, DATEADD(DAY, 1, GETDATE()))
	IF @startDate is null
        SET @startDate = CONVERT(date, DATEADD(YEAR, -10, GETDATE()))

	CREATE TABLE #Results  
		(RowID  int identity(1,1), module varchar(200), tableName varchar(200), id int, update_ts datetime, update_user_id int, project_name varchar(100))

	CREATE TABLE #SummaryResults  
		(RowID  int, module varchar(200), tableName varchar(200), id int, update_ts datetime, update_user_id int, project_name varchar(100))
	

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'USER', e.id, e.update_ts, e.update_user_id, CASE WHEN LAST_LOGIN_DATE = UPDATE_TS THEN 'Logged In' ELSE e.USERNAME + ' (' + e.FIRST_NAME + ' ' + e.LAST_NAME + ')' END FROM [USER] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'ADDRESS', e.id, e.update_ts, e.update_user_id, e.LINE1 FROM [ADDRESS] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'CAP_CONTACT', e.id, e.update_ts, e.update_user_id, e.FIRST_NAME + ' ' + e.LAST_NAME FROM [CAP_CONTACT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'CAP_CONTACT_ORGANIZATION', e.id, e.update_ts, e.update_user_id, e.NAME FROM [CAP_CONTACT_ORGANIZATION] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'CAP_ROLE', e.id, e.update_ts, e.update_user_id, e.Name FROM [CAP_ROLE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'Global', 'CLIENT', e.id, e.update_ts, e.update_user_id, e.NAME FROM [CLIENT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;


	--done - client-level
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_BMP', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_BMP] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	--done - client-level
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_BMP_CATEGORY', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_BMP_CATEGORY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_BMP_CATEGORY_DB', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_BMP_CATEGORY_DB] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_BMP_DB', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_BMP_DB] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_BMP_LIBRARY_DB', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_BMP_LIBRARY_DB] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	--done - client-level
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_EVENT', e.id, e.update_ts, e.update_user_id, e.EVENT_DATE FROM [EC_EVENT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	--done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_INSPECTION', e.id, e.update_ts, e.update_user_id, EC_PROJECT.NAME FROM [EC_INSPECTION] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN EC_PROJECT ON EC_PROJECT.ID = e.PROJECT_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	--done - client-level
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_INSPECTION_TEMPLATE', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_INSPECTION_TEMPLATE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	--done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_PROJECT', e.id, e.update_ts, e.update_user_id, e.NAME FROM [EC_PROJECT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	
	--done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'EC', 'EC_PROJECT_BMP', e.id, e.update_ts, e.update_user_id, EC_PROJECT.NAME FROM [EC_PROJECT_BMP] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN EC_PROJECT ON EC_PROJECT.ID = e.PROJECT_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;


	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_ASSET', e.id, e.update_ts, e.update_user_id, null FROM [ENV_ASSET] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_ASSET_SOURCE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_ASSET_SOURCE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_FACILITY', e.id, e.update_ts, e.update_user_id, null FROM [ENV_FACILITY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_FACILITY_ASSET', e.id, e.update_ts, e.update_user_id, null FROM [ENV_FACILITY_ASSET] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PERMIT', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PERMIT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PERMIT_ASSET', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PERMIT_ASSET] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PERMIT_DETAIL', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PERMIT_DETAIL] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PERMIT_FACILITY', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PERMIT_FACILITY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PERMIT_SUBSTANCE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PERMIT_SUBSTANCE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PROCESS', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PROCESS] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_PROCESS_ASSET', e.id, e.update_ts, e.update_user_id, null FROM [ENV_PROCESS_ASSET] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SCC_INFO', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SCC_INFO] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SCC_INFO_LIBRARY', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SCC_INFO_LIBRARY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SOURCE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SOURCE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SOURCE_SCC_INFO', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SOURCE_SCC_INFO] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SOURCE_SUBSTANCE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SOURCE_SUBSTANCE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SOURCE_USAGE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SOURCE_USAGE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SUBSTANCE', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SUBSTANCE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'ENV', 'ENV_SUBSTANCE_SCC_INFO', e.id, e.update_ts, e.update_user_id, null FROM [ENV_SUBSTANCE_SCC_INFO] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;


	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'BMP_DB', e.id, e.update_ts, e.update_user_id, null FROM [BMP_DB] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'BMP_DB_CATEGORY', e.id, e.update_ts, e.update_user_id, null FROM [BMP_DB_CATEGORY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'BMP_DB_GOAL', e.id, e.update_ts, e.update_user_id, null FROM [BMP_DB_GOAL] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'PLAN', e.id, e.update_ts, e.update_user_id, NAME FROM [PLAN] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'PLAN_DELETE_REASON', e.id, e.update_ts, e.update_user_id, null FROM [PLAN_DELETE_REASON] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'PLAN_PUBLISH', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [PLAN_PUBLISH] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN [PLAN] ON [PLAN].ID = e.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'MCM', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [MCM] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN [PLAN] ON [PLAN].ID = e.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'MCM_DELETE_REASON', e.id, e.update_ts, e.update_user_id, null FROM [MCM_DELETE_REASON] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'BMP', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [BMP] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN MCM ON MCM.ID = e.MCM_ID
	  INNER JOIN [PLAN] ON [PLAN].ID = MCM.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'BMP_DELETE_REASON', e.id, e.update_ts, e.update_user_id, null FROM [BMP_DELETE_REASON] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [GOAL] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN BMP ON BMP.ID = e.BMP_ID
	  INNER JOIN MCM ON MCM.ID = BMP.MCM_ID
	  INNER JOIN [PLAN] ON [PLAN].ID = MCM.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_DELETE_REASON', e.id, e.update_ts, e.update_user_id, null FROM [GOAL_DELETE_REASON] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_ACTIVITY', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [GOAL_ACTIVITY] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN GOAL ON GOAL.ID = e.GOAL_ID
	  INNER JOIN BMP ON BMP.ID = GOAL.BMP_ID
	  INNER JOIN MCM ON MCM.ID = BMP.MCM_ID
	  INNER JOIN [PLAN] ON [PLAN].ID = MCM.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_ACTIVITY_DELETE_REASON', e.id, e.update_ts, e.update_user_id, null FROM [GOAL_ACTIVITY_DELETE_REASON] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_ACTIVITY_FILE_LOCATION', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [GOAL_ACTIVITY_FILE_LOCATION] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN GOAL_activity ON GOAL_ACTIVITY.ID = e.GOAL_ACTIVITY_ID
	  INNER JOIN GOAL ON GOAL.ID = GOAL_ACTIVITY.GOAL_ID
	  INNER JOIN BMP ON BMP.ID = GOAL.BMP_ID
	  INNER JOIN MCM ON MCM.ID = BMP.MCM_ID
	  INNER JOIN [PLAN] ON [PLAN].ID = MCM.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;

	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_ACTIVITY_FORM_DEFAULT', e.id, e.update_ts, e.update_user_id, null FROM [GOAL_ACTIVITY_FORM_DEFAULT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

	-- done
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'GOAL_PERMIT_TIME_PERIOD', e.id, e.update_ts, e.update_user_id, [PLAN].NAME FROM [GOAL_PERMIT_TIME_PERIOD] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID 
	  INNER JOIN GOAL ON GOAL.ID = e.GOAL_ID
	  INNER JOIN BMP ON BMP.ID = GOAL.BMP_ID
	  INNER JOIN MCM ON MCM.ID = BMP.MCM_ID
	  INNER JOIN [PLAN] ON [PLAN].ID = MCM.PLAN_ID
	  WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by e.update_ts desc;


	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'PERMIT_PERIOD', e.id, e.update_ts, e.update_user_id, null FROM [PERMIT_PERIOD] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'SW', 'PERMIT_TIME_PERIOD', e.id, e.update_ts, e.update_user_id, null FROM [PERMIT_TIME_PERIOD] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;


	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'INFO_ENHANCEMENTS', e.id, e.update_ts, e.update_user_id, null FROM [INFO_ENHANCEMENTS] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'INFO_EVENTS', e.id, e.update_ts, e.update_user_id, null FROM [INFO_EVENTS] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'INFO_TIPS', e.id, e.update_ts, e.update_user_id, null FROM [INFO_TIPS] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'INFO_VERSION', e.id, e.update_ts, e.update_user_id, null FROM [INFO_VERSION] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'last_final_inspections', e.id, e.update_ts, e.update_user_id, null FROM [last_final_inspections] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;

    --SELECT * FROM #Results
    --RETURN 1

    INSERT INTO #SummaryResults
        SELECT DISTINCT a.*
            FROM #Results a
                LEFT JOIN #Results b
                ON (b.update_ts >= DATEADD(s, -5, a.update_ts)
                    AND b.update_ts < a.update_ts)
                    AND a.module = b.module
                    AND a.project_name = b.project_name
                    AND a.update_user_id = b.update_user_id
            WHERE b.update_ts IS NULL
            --ORDER BY a.update_ts desc

	SELECT TOP (@top) 
		  r.module
		--, CASE WHEN CHARINDEX('_', r.tableName)  > 0 THEN SUBSTRING(r.tableName, CHARINDEX('_', r.tableName) + 1, 200) ELSE r.tableName END as entity_table
		, REPLACE(REPLACE(REPLACE(r.tableName, 'EC_', ''), 'ENV_', ''), 'CAP_', '') as entity_table
		, r.id as entity_id
		, r.update_ts
		, r.update_user_id
		, r.project_name
	    , client.id as client_id
	    , client.name as client_name
		--, client_type.NAME as client_type
	    --, client.web_page
	    , client_parent.NAME as client_parent
	    --, isnull(client_parent.NAME, client.NAME) as client_parent
		, u.username
		, u.first_name
		, u.last_name
		, u.last_login_date
		, u.email_address
		, u.primary_phone
		, u.status_cd
		, u.title
	  from #SummaryResults r
	    inner join [user] u 
		  on r.update_user_id = u.id
	    inner join [client_user] client_user
		  on u.id = client_user.[user_id]
	    inner join [client] client 
		  on client_user.client_id = client.id

		left join EC_PROJECT project
		  on project.NAME = r.project_name 
	    left join [client] client_parent
		  on project.OWNER_CLIENT_ID = client_parent.id
		     AND project.OWNER_CLIENT_ID <> client.ID

	   -- left join [EC_CLIENT_RELATIONSHIP] client_relationship
		  --on client.id = client_relationship.RELATED_CLIENT_ID
	   -- inner join [client] client_parent
		  --on client_relationship.CLIENT_ID = client_parent.id
  --    group by
		--  r.module
		----, r.tableName as entity_table
		----, r.id as entity_id
		--, YEAR(r.update_ts)
		--, MONTH(r.update_ts)
		--, DAY(r.update_ts)
		--, DATEPART(hh, r.update_ts)
		--, r.update_user_id
		--, r.project_name
	 --   , client.id
	 --   , client_parent.NAME
      
	  order by
	   --r.module,
	   --isnull(client_parent.NAME, client.NAME),
	   --client.NAME,
	   --r.project_name,
	   r.update_ts desc
	    
	--SELECT TOP (@top) 
	--	  r.module
	--	, r.tableName as entity_table
	--	, r.id as entity_id
	--	, r.update_ts
	--	, r.update_user_id
	--	, r.project_name
	--    , client.id as client_id
	--    , client.name as client_name
	--	--, client_type.NAME as client_type
	--    --, client.web_page
	--    , client_parent.NAME as client_parent
	--    --, isnull(client_parent.NAME, client.NAME) as client_parent
	--	, u.username
	--	, u.first_name
	--	, u.last_name
	--	, u.last_login_date
	--	, u.email_address
	--	, u.primary_phone
	--	, u.status_cd
	--	, u.title
	--  from #results r
	--    inner join [user] u 
	--	  on r.update_user_id = u.id
	--    inner join [client_user] client_user
	--	  on u.id = client_user.[user_id]
	--    inner join [client] client 
	--	  on client_user.client_id = client.id

	--	left join EC_PROJECT project
	--	  on project.NAME = r.project_name 
	--    left join [client] client_parent
	--	  on project.OWNER_CLIENT_ID = client_parent.id
	--	     AND project.OWNER_CLIENT_ID <> client.ID

	--   -- left join [EC_CLIENT_RELATIONSHIP] client_relationship
	--	  --on client.id = client_relationship.RELATED_CLIENT_ID
	--   -- inner join [client] client_parent
	--	  --on client_relationship.CLIENT_ID = client_parent.id
 -- --    group by
	--	--  r.module
	--	----, r.tableName as entity_table
	--	----, r.id as entity_id
	--	--, YEAR(r.update_ts)
	--	--, MONTH(r.update_ts)
	--	--, DAY(r.update_ts)
	--	--, DATEPART(hh, r.update_ts)
	--	--, r.update_user_id
	--	--, r.project_name
	-- --   , client.id
	-- --   , client_parent.NAME
      
	--  order by
	--   --r.module,
	--   --isnull(client_parent.NAME, client.NAME),
	--   --client.NAME,
	--   --r.project_name,
	--   r.update_ts desc


	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'CAP_MODULE', e.id, e.update_ts, e.update_user_id, null FROM [CAP_MODULE] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	--INSERT INTO #Results SELECT DISTINCT TOP (@topT) 'CAP_REPORT_OBJECT', e.id, e.update_ts, e.update_user_id, null FROM [CAP_REPORT_OBJECT] e inner join [CLIENT_USER] cu on e.UPDATE_USER_ID = cu.USER_ID WHERE (@userId = 0 OR e.update_user_id = @userId) AND (@clientId = 0 OR cu.CLIENT_ID = @clientId) AND (e.UPDATE_TS <= @endDate) AND (e.UPDATE_TS >= @startDate) order by update_ts desc;
	  
END


GO

