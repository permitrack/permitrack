

/****** Object:  StoredProcedure [dbo].[sp_user_activity_generate]    Script Date: 03/11/2013 12:33:22 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_user_activity_generate]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_user_activity_generate]
GO



/****** Object:  StoredProcedure [dbo].[sp_user_activity_generate]    Script Date: 03/11/2013 12:33:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	Generates SQL for sp_user_activity
-- =============================================
create PROCEDURE [dbo].[sp_user_activity_generate] 

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	--
	-- Generates SQL for sp_user_activity
	--
	DECLARE @tableName varchar(200) = '';

	DECLARE RecSet CURSOR FOR

	SELECT a.TABLE_NAME as tableName
	FROM INFORMATION_SCHEMA.TABLES a inner join INFORMATION_SCHEMA.COLUMNS b ON a.TABLE_NAME = b.TABLE_NAME
	WHERE b.COLUMN_NAME = 'update_user_id'
	order by 1 


	OPEN RecSet
	FETCH NEXT FROM RecSet INTO @tableName

	WHILE (@@FETCH_STATUS = 0)  
			BEGIN  
    			PRINT 'INSERT INTO #Results SELECT TOP 3 ''' + @tableName + ''', id, update_ts FROM [' + @tableName + '] WHERE update_user_id = @userId order by update_ts desc;'
				
				FETCH NEXT FROM RecSet INTO @tableName
			END


	CLOSE RecSet
	DEALLOCATE RecSet

END

GO


