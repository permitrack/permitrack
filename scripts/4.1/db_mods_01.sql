use cap2

 -- Code to add table Lookup_Project_Status_code and its data
 IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'LOOKUP_PROJECT_STATUS_CODE'
 )
 BEGIN
 	CREATE TABLE [dbo].[LOOKUP_PROJECT_STATUS_CODE] (
 		[CODE] [varchar](4) NOT NULL ,
 		[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
 		[ORDER_NUM] [int] NOT NULL
 	) ON [PRIMARY]


 	ALTER TABLE [dbo].[LOOKUP_PROJECT_STATUS_CODE] WITH NOCHECK ADD
 	CONSTRAINT [PK_LOOKUP_PROJECT_STATUS_CODE] PRIMARY KEY  CLUSTERED
 	(
 		[CODE]
 	)  ON [PRIMARY]


 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('1', 'Active',1)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('2','Inactive',2)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('3','Deleted',3)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('4','Incomplete',4)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('5','Archived',5)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('6','Closed',6)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('7','Auto Activate',7)
 	INSERT INTO LOOKUP_PROJECT_STATUS_CODE VALUES ('8','Complete',8)

 END


 -- Code to add table LOOKUP_STATUS_CODE
 IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'LOOKUP_STATUS_CODE'
 )
 BEGIN
 	CREATE TABLE [dbo].[LOOKUP_STATUS_CODE] (
 		[CODE] [varchar](4) NOT NULL ,
 		[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
 		[ORDER_NUM] [int] NOT NULL
 	) ON [PRIMARY]

 	ALTER TABLE [dbo].[LOOKUP_STATUS_CODE] WITH NOCHECK ADD
 		CONSTRAINT [PK_LOOKUP_STATUS_CODE] PRIMARY KEY  CLUSTERED
 		(
 			[CODE]
 		)  ON [PRIMARY]

 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('1', 'Active',1)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('2','Inactive',2)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('3','Deleted',3)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('4','Incomplete',4)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('5','Archived',5)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('6','Closed',6)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('7','Auto Activate',7)
 	INSERT INTO LOOKUP_STATUS_CODE VALUES ('8','Complete',8)
 END


 -- Modify the EC_Project table
 IF NOT EXISTS (
 	Select * from [information_schema].columns
 		where column_name = 'Project_STATUS_Cd'
 		AND table_name = 'ec_project'
 )
 BEGIN
 	ALTER TABLE EC_PROJECT
 	ADD PROJECT_STATUS_CD varchar(4) NOT NULL DEFAULT 1


 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = 'ec_project'
 		and column_name = 'project_status_cd'
 		and constraint_name = 'FK_EC_PRJ_PRJ_STAT_CD_LKP_PRJ_STAT_CD'
 )
 BEGIN
 	ALTER TABLE EC_PROJECT
 	ADD CONSTRAINT FK_EC_PRJ_PRJ_STAT_CD_LKP_PRJ_STAT_CD
 	FOREIGN KEY (PROJECT_STATUS_CD)
 	REFERENCES LOOKUP_PROJECT_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE
 END

 IF NOT EXISTS (
 	Select * from [information_schema].columns
 		where column_name = 'STATUS_CD'
 		AND table_name = 'ec_project'
 )
 BEGIN
 	ALTER TABLE EC_PROJECT
 	ADD STATUS_CD varchar(4)
 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = 'ec_project'
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_EC_PRJ_STAT_CD_LKUP_PRJ_STAT_CD'
 )
 BEGIN
 	ALTER TABLE EC_PROJECT
 	ADD CONSTRAINT FK_EC_PRJ_STAT_CD_LKUP_PRJ_STAT_CD
 	FOREIGN KEY (STATUS_CD)
 	REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE
 END

 IF NOT EXISTS (
 	Select * from [information_schema].columns
 		where column_name = 'COMPLETED_DATE'
 		AND table_name = 'ec_project'
 )
 BEGIN
 	ALTER TABLE EC_PROJECT
 	ADD COMPLETED_DATE DATETIME NULL
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = 'ec_project'
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE EC_PROJECT SET PROJECT_STATUS_CD = CASE WHEN STATUS_CODE = 1 THEN ''1'' WHEN STATUS_CODE = 2 THEN ''2'' WHEN STATUS_CODE = 3 THEN ''3'' WHEN STATUS_CODE = 4 THEN ''4'' WHEN STATUS_CODE = 5 THEN ''5'' WHEN STATUS_CODE = 6 THEN ''6'' WHEN STATUS_CODE = 7 THEN ''7'' WHEN STATUS_CODE = 8 THEN ''8''END')

 	EXEC('UPDATE EC_PROJECT SET STATUS_CD = CASE WHEN STATUS_CODE = 1 THEN ''1'' WHEN STATUS_CODE = 2 THEN ''1'' WHEN STATUS_CODE = 3 THEN ''3'' WHEN STATUS_CODE = 4 THEN ''1'' WHEN STATUS_CODE = 5 THEN ''5'' WHEN STATUS_CODE = 6 THEN ''1'' WHEN STATUS_CODE = 7 THEN ''1'' WHEN STATUS_CODE = 8 THEN ''1''END')

 	ALTER TABLE EC_PROJECT
 	DROP COLUMN STATUS_CODE
 END
 -- End of EC_Project Changes

 -- Altering the Status_Code table on all tables that have it
 declare @table_name varchar(100)

 set @table_name = 'BMP'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')
 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CASE WHEN STATUS_CODE IS NULL THEN ''2'' ELSE CAST (STATUS_CODE as varchar(4)) END')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END


 set @table_name = 'CAP_CONTACT'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'CAP_CONTACT_ORGANIZATION'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CASE WHEN STATUS_CODE IS NULL THEN ''2'' ELSE CAST (STATUS_CODE as varchar(4)) END')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'CAP_MODULE'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	ALTER TABLE CAP_MODULE DROP CONSTRAINT DF_MODULE_IS_ACTIVE
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')
 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'CAP_ROLE'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	ALTER TABLE CAP_ROLE DROP CONSTRAINT DF_CAPROLE_ACTIVE
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'CLIENT'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CASE WHEN STATUS_CODE IS NULL THEN ''2'' ELSE CAST (STATUS_CODE as varchar(4)) END')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'EC_BMP'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	ALTER Table EC_BMP DROP CONSTRAINT DF_EC_BMP_STATUS_CODE
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'EC_BMP_CATEGORY'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'EC_EVENT'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CASE WHEN STATUS_CODE IS NULL THEN ''2'' ELSE CAST (STATUS_CODE as varchar(4)) END')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'EC_INSPECTION'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'EC_INSPECTION_TEMPLATE'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	ALTER TABLE EC_INSPECTION_TEMPLATE DROP CONSTRAINT DF_EC_Inspection_Template_STATUS_CODE

 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'GOAL_ACTIVITY'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CASE WHEN STATUS_CODE IS NULL THEN ''2'' ELSE CAST (STATUS_CODE as varchar(4)) END')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'GOAL_ACTIVITY_FILE_LOCATION'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'GOAL'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'MCM'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'PERMIT_PERIOD'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'PLAN'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

 set @table_name = 'USER'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END


set @table_name = 'EC_PROJECT_TYPE'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
	ALTER TABLE EC_PROJECT_TYPE DROP CONSTRAINT DF_EC_PROJECT_TYPE_STATUS_CODE

	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END

set @table_name = 'EC_CLIENT_PROJECT_ROLE'

 IF NOT EXISTS (
 	Select * from [information_schema].[columns]
 		where column_name = 'STATUS_CD'
 		AND table_name = @table_name
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD STATUS_CD varchar(4) NOT NULL DEFAULT 1')

 END

 IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = @table_name
 		and column_name = 'status_cd'
 		and constraint_name = 'FK_' + @table_name + '_STAT_CD_LKP_STAT_CD'
 )
 BEGIN
 	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD CONSTRAINT FK_' + @table_name + '_STAT_CD_LKP_STAT_CD FOREIGN KEY (STATUS_CD) REFERENCES LOOKUP_STATUS_CODE (CODE) ON DELETE NO ACTION ON UPDATE CASCADE')
 END

 IF EXISTS (
 	SELECT * FROM [information_schema].[columns]
 		Where table_name = @table_name
 		and column_name = 'STATUS_CODE'
 )
 BEGIN
 	ALTER TABLE EC_CLIENT_PROJECT_ROLE DROP CONSTRAINT DF_EC_CLIENT_PROJECT_RELATIONSHIP_Active

	EXEC('UPDATE dbo.[' + @table_name + '] SET STATUS_CD = CAST (STATUS_CODE as varchar(4))')

 	EXEC('ALTER TABLE dbo.[' + @table_name + ']	DROP COLUMN STATUS_CODE')
 END


set @table_name = 'EC_INSPECTION'
IF NOT EXISTS (
	Select * from [information_schema].[columns]
 		where column_name = 'VERSION'
 		AND table_name = @table_name
)
BEGIN
	EXEC('ALTER TABLE dbo.[' + @table_name + '] ADD VERSION bigint NOT NULL DEFAULT 0')

END


set @table_name = 'AUDIT_EC_INSPECTION'
IF NOT EXISTS (
	Select * from [information_schema].[tables]
 		where table_name = @table_name
)
BEGIN
	CREATE TABLE [AUDIT_EC_INSPECTION] (
	[ID] [int] NOT NULL ,
	[VERSION] [bigint] NOT NULL, 
	[PROJECT_ID] [int] NOT NULL ,
	[CLIENT_ID] [int] NULL ,
	[INSPECTION_DATE] [datetime] NOT NULL ,
	[ENTERED_DATE] [datetime] NOT NULL ,
	[WEATHER_TRENDS] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[TEMPERATURE] [varchar] (20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[COMMENT] [varchar] (1000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PRECIP_END_DATE] [datetime] NULL ,
	[PRECIP_AMOUNT] [varchar] (20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PRECIP_SOURCE] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[INSPECTION_ACTION_COMMENT] [varchar] (1000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[INSPECTION_ACTION_ID] [int] NULL ,
	[INSPECTION_REASON_ID] [int] NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[AUDIT_REC_TYPE] [varchar] (7) NOT NULL,
	[AUDIT_DATE] [datetime] NOT NULL,
	CONSTRAINT [PK_AUDIT_EC_INSPECTION] PRIMARY KEY  CLUSTERED 
	(
		[ID],
		[VERSION]
	)  ON [PRIMARY] 
) ON [PRIMARY]
END

IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'LOOKUP_CONTACT_TYPE')
BEGIN
	CREATE TABLE [dbo].[LOOKUP_CONTACT_TYPE] (
 		[CODE] [varchar](4) NOT NULL ,
 		[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
 		[ORDER_NUM] [int] NOT NULL
 	) ON [PRIMARY]

 	ALTER TABLE [dbo].[LOOKUP_CONTACT_TYPE] WITH NOCHECK ADD
 	CONSTRAINT [PK_LOOKUP_CONTACT_TYPE] PRIMARY KEY  CLUSTERED
 	(
 		[CODE]
 	)  ON [PRIMARY]

 	INSERT INTO LOOKUP_CONTACT_TYPE VALUES ('INSP', 'Inspector',1)
END

IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'CAP_CONTACT_CONTACT_TYPE')
BEGIN
	CREATE TABLE [dbo].[CAP_CONTACT_CONTACT_TYPE] (
 		[CONTACT_TYPE_CD] [varchar](4) NOT NULL ,
 		[CAP_CONTACT_ID] [int] NOT NULL
 	) ON [PRIMARY]

 	ALTER TABLE [dbo].[CAP_CONTACT_CONTACT_TYPE] WITH NOCHECK ADD
 	CONSTRAINT [PK_CAP_CONTACT_CONTACT_TYPE] PRIMARY KEY  CLUSTERED
 	(
		[CONTACT_TYPE_CD],
 		[CAP_CONTACT_ID]
 	)  ON [PRIMARY]

	ALTER TABLE CAP_CONTACT_CONTACT_TYPE
 	ADD CONSTRAINT FK_CP_CN_CN_TP_CN_ID
 	FOREIGN KEY (CAP_CONTACT_ID)
 	REFERENCES CAP_CONTACT ([ID]) ON DELETE CASCADE ON UPDATE CASCADE

	ALTER TABLE CAP_CONTACT_CONTACT_TYPE
 	ADD CONSTRAINT FK_CP_CN_CN_TP_CN_TP_CD
 	FOREIGN KEY (CONTACT_TYPE_CD)
 	REFERENCES LOOKUP_CONTACT_TYPE ([CODE]) ON DELETE CASCADE ON UPDATE CASCADE
 	
END

IF NOT EXISTS (
	Select * from [information_schema].columns
 		where column_name = 'INSPECTOR_ID'
 		AND table_name = 'EC_INSPECTION'
)
BEGIN
 	ALTER TABLE EC_INSPECTION
 	ADD INSPECTOR_ID int NULL

	
END

IF NOT EXISTS (
	Select * from [information_schema].[tables]
 		where table_name = 'USER_SEARCHES'
)
BEGIN
	CREATE TABLE [USER_SEARCHES] (
	[ID] [int] IDENTITY (0, 1) NOT NULL ,
	[SEARCH_NAME] [varchar](60) NOT NULL,
	[USER_ID] [int] NOT NULL,
	[IS_DEFAULT_SEARCH] [bit] NOT NULL DEFAULT 0,
	[PROJECTS_PER_PAGE] [int] NOT NULL,
	[SEARCH_PROJECT_NAME] [varchar] (100) NULL,
	[SEARCH_ADDRESS] [varchar] (100) NULL,
	[SEARCH_CITY] [varchar] (100) NULL,
	[SEARCH_STATE] [varchar] (100) NULL,
	[SEARCH_ZIP] [varchar] (100) NULL,
	[SEARCH_ZONES] [varchar] (100) NULL,
	[SEARCH_PROJECT_TYPES] [varchar] (100) NULL,
	[SEARCH_PROJECT_STATUSES] [varchar] (100) NULL,
	[SORT_COLUMN] [varchar] (100) NOT NULL,
	[SORT_DIRECTION] [varchar] (100) NOT NULL,
	CONSTRAINT [PK_USER_SEARCHES] PRIMARY KEY  CLUSTERED 
	([ID])  ON [PRIMARY] 
) ON [PRIMARY]

	ALTER TABLE USER_SEARCHES
 	ADD CONSTRAINT FK_USR_SC_CON_ID_CP_CON_ID
 	FOREIGN KEY (USER_ID)
 	REFERENCES [USER] ([ID]) ON DELETE NO ACTION ON UPDATE CASCADE
END

IF NOT EXISTS (
 	select * from [information_schema].[constraint_column_usage]
 		where table_name = 'EC_INSPECTION'
 		and column_name = 'INSPECTOR_ID'
 		and constraint_name = 'FK_EC_INS_INS_ID_CP_CON_ID'
)
BEGIN
 	ALTER TABLE EC_INSPECTION
 	ADD CONSTRAINT FK_EC_INS_INS_ID_CP_CON_ID
 	FOREIGN KEY (INSPECTOR_ID)
 	REFERENCES CAP_CONTACT ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
END

IF NOT EXISTS (
	Select * from [information_schema].[tables]
 		where table_name = 'EC_INSPECTION_DOCUMENT'
)
CREATE TABLE [dbo].[EC_INSPECTION_DOCUMENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[INSPECTION_ID] [int] NOT NULL ,
	[NAME] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[LOCATION] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[COMMENT] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO



