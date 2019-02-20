

/****** Object:  StoredProcedure [dbo].[sp_user_activity]    Script Date: 03/11/2013 12:32:52 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_user_activity]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_user_activity]
GO



/****** Object:  StoredProcedure [dbo].[sp_user_activity]    Script Date: 03/11/2013 12:32:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create PROCEDURE [dbo].[sp_user_activity] 
	-- Add the parameters for the stored procedure here
	@userId int = 0
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	CREATE TABLE #Results  (RowID  int identity(1,1), tableName varchar(200), id int, update_ts datetime /*, name varchar(100)*/)
	

	INSERT INTO #Results SELECT TOP 3 'ADDRESS', id, update_ts FROM [ADDRESS] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'BMP', id, update_ts FROM [BMP] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'BMP_DB', id, update_ts FROM [BMP_DB] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'BMP_DB_CATEGORY', id, update_ts FROM [BMP_DB_CATEGORY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'BMP_DB_GOAL', id, update_ts FROM [BMP_DB_GOAL] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'BMP_DELETE_REASON', id, update_ts FROM [BMP_DELETE_REASON] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CAP_CONTACT', id, update_ts FROM [CAP_CONTACT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CAP_CONTACT_ORGANIZATION', id, update_ts FROM [CAP_CONTACT_ORGANIZATION] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CAP_MODULE', id, update_ts FROM [CAP_MODULE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CAP_REPORT_OBJECT', id, update_ts FROM [CAP_REPORT_OBJECT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CAP_ROLE', id, update_ts FROM [CAP_ROLE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'CLIENT', id, update_ts FROM [CLIENT] WHERE update_user_id = @userId order by update_ts desc;
	--INSERT INTO #Results SELECT TOP 3 'CONTACT', id, update_ts FROM [CONTACT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_BMP', id, update_ts FROM [EC_BMP] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_BMP_CATEGORY', id, update_ts FROM [EC_BMP_CATEGORY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_BMP_CATEGORY_DB', id, update_ts FROM [EC_BMP_CATEGORY_DB] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_BMP_DB', id, update_ts FROM [EC_BMP_DB] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_BMP_LIBRARY_DB', id, update_ts FROM [EC_BMP_LIBRARY_DB] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_EVENT', id, update_ts FROM [EC_EVENT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_INSPECTION', id, update_ts FROM [EC_INSPECTION] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_INSPECTION_TEMPLATE', id, update_ts FROM [EC_INSPECTION_TEMPLATE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_PROJECT', id, update_ts FROM [EC_PROJECT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'EC_PROJECT_BMP', id, update_ts FROM [EC_PROJECT_BMP] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_ASSET', id, update_ts FROM [ENV_ASSET] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_ASSET_SOURCE', id, update_ts FROM [ENV_ASSET_SOURCE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_FACILITY', id, update_ts FROM [ENV_FACILITY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_FACILITY_ASSET', id, update_ts FROM [ENV_FACILITY_ASSET] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PERMIT', id, update_ts FROM [ENV_PERMIT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PERMIT_ASSET', id, update_ts FROM [ENV_PERMIT_ASSET] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PERMIT_DETAIL', id, update_ts FROM [ENV_PERMIT_DETAIL] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PERMIT_FACILITY', id, update_ts FROM [ENV_PERMIT_FACILITY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PERMIT_SUBSTANCE', id, update_ts FROM [ENV_PERMIT_SUBSTANCE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PROCESS', id, update_ts FROM [ENV_PROCESS] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_PROCESS_ASSET', id, update_ts FROM [ENV_PROCESS_ASSET] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SCC_INFO', id, update_ts FROM [ENV_SCC_INFO] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SCC_INFO_LIBRARY', id, update_ts FROM [ENV_SCC_INFO_LIBRARY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SOURCE', id, update_ts FROM [ENV_SOURCE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SOURCE_SCC_INFO', id, update_ts FROM [ENV_SOURCE_SCC_INFO] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SOURCE_SUBSTANCE', id, update_ts FROM [ENV_SOURCE_SUBSTANCE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SOURCE_USAGE', id, update_ts FROM [ENV_SOURCE_USAGE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SUBSTANCE', id, update_ts FROM [ENV_SUBSTANCE] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'ENV_SUBSTANCE_SCC_INFO', id, update_ts FROM [ENV_SUBSTANCE_SCC_INFO] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL', id, update_ts FROM [GOAL] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_ACTIVITY', id, update_ts FROM [GOAL_ACTIVITY] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_ACTIVITY_DELETE_REASON', id, update_ts FROM [GOAL_ACTIVITY_DELETE_REASON] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_ACTIVITY_FILE_LOCATION', id, update_ts FROM [GOAL_ACTIVITY_FILE_LOCATION] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_ACTIVITY_FORM_DEFAULT', id, update_ts FROM [GOAL_ACTIVITY_FORM_DEFAULT] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_DELETE_REASON', id, update_ts FROM [GOAL_DELETE_REASON] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'GOAL_PERMIT_TIME_PERIOD', id, update_ts FROM [GOAL_PERMIT_TIME_PERIOD] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'INFO_ENHANCEMENTS', id, update_ts FROM [INFO_ENHANCEMENTS] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'INFO_EVENTS', id, update_ts FROM [INFO_EVENTS] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'INFO_TIPS', id, update_ts FROM [INFO_TIPS] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'INFO_VERSION', id, update_ts FROM [INFO_VERSION] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'last_final_inspections', id, update_ts FROM [last_final_inspections] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'MCM', id, update_ts FROM [MCM] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'MCM_DELETE_REASON', id, update_ts FROM [MCM_DELETE_REASON] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'PERMIT_PERIOD', id, update_ts FROM [PERMIT_PERIOD] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'PERMIT_TIME_PERIOD', id, update_ts FROM [PERMIT_TIME_PERIOD] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'PLAN', id, update_ts FROM [PLAN] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'PLAN_DELETE_REASON', id, update_ts FROM [PLAN_DELETE_REASON] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'PLAN_PUBLISH', id, update_ts FROM [PLAN_PUBLISH] WHERE update_user_id = @userId order by update_ts desc;
	INSERT INTO #Results SELECT TOP 3 'USER', id, update_ts FROM [USER] WHERE update_user_id = @userId order by update_ts desc;

 


	SELECT TOP 50 tableName, id, update_ts FROM #results ORDER BY 3 DESC
END

GO


