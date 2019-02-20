use cap2

IF NOT EXISTS (
 	select * from EC_INSPECTION WHERE INSPECTOR_ID IS NOT NULL
)
BEGIN

	UPDATE EC_INSPECTION set INSPECTOR_ID = UPDATE_USER_ID
	ALTER TABLE EC_INSPECTION
		 alter column INSPECTOR_ID int NOT NULL 
END

declare @audit_row_count int

select @audit_row_count = count(*) from audit_ec_inspection

IF (@audit_row_count < 1)

BEGIN	
	insert into audit_ec_inspection 
		([ID], [PROJECT_ID], [CLIENT_ID], [INSPECTION_DATE], [ENTERED_DATE], [WEATHER_TRENDS], [TEMPERATURE], [COMMENT], [PRECIP_END_DATE], [PRECIP_AMOUNT], [PRECIP_SOURCE], [INSPECTION_ACTION_COMMENT], [INSPECTION_ACTION_ID], [INSPECTION_REASON_ID], [CREATE_TS], [UPDATE_TS], [UPDATE_USER_ID], [STATUS_CD], [VERSION], [AUDIT_REC_TYPE], [AUDIT_DATE])
		select 
			[ID], [PROJECT_ID], [CLIENT_ID], [INSPECTION_DATE], [ENTERED_DATE], [WEATHER_TRENDS], [TEMPERATURE], [COMMENT], [PRECIP_END_DATE], [PRECIP_AMOUNT], [PRECIP_SOURCE], [INSPECTION_ACTION_COMMENT], [INSPECTION_ACTION_ID], [INSPECTION_REASON_ID], [CREATE_TS], [UPDATE_TS], [UPDATE_USER_ID], [STATUS_CD], [VERSION], 'INSERT', GetDate()
			from EC_INSPECTION
END

IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'CLIENT_ADMIN_SETTINGS'
 )
 BEGIN
 	CREATE TABLE [dbo].[CLIENT_ADMIN_SETTINGS] (
 		[CLIENT_ID] [int] NOT NULL ,
 		[PROJECT_STATUS_EMAIL_ENABLED] [bit] NOT NULL DEFAULT 1,
		[INSP_OVERDUE_NOTIF_ENABLED] [bit] NOT NULL DEFAULT 1,
		[INSP_OVERDUE_INIT_THRESHOLD] [int] NULL DEFAULT 10,
		[INSP_OVERDUE_INIT_MSG] [varchar] (2500) NULL,
		[INSP_OVERDUE_SEC_THRESHOLD] [int] NULL,
		[INSP_OVERDUE_SEC_MSG] [varchar] (2500) NULL,
 	) ON [PRIMARY]


 	ALTER TABLE [dbo].[CLIENT_ADMIN_SETTINGS] WITH NOCHECK ADD
 	CONSTRAINT [PK_CLIENT_ADMIN_SETTINGS] PRIMARY KEY  CLUSTERED
 	(
 		[CLIENT_ID]
 	)  ON [PRIMARY]


	ALTER TABLE [dbo].[CLIENT_ADMIN_SETTINGS]
 	ADD CONSTRAINT FK_CL_AD_ST_CL_ID_CL 
 	FOREIGN KEY (CLIENT_ID)
 	REFERENCES CLIENT ([ID]) ON DELETE CASCADE ON UPDATE CASCADE
 	
	insert into CLIENT_ADMIN_SETTINGS 
		([CLIENT_ID])
		select 
			[ID] from CLIENT
	
 END

IF NOT EXISTS (
 	SELECT * FROM [information_schema].[tables]
 	WHERE table_schema = 'dbo'
 	AND TABLE_NAME = 'EC_INSPECTION_OVERDUE_EMAIL_LOG'
 )
 BEGIN
 	CREATE TABLE [dbo].[EC_INSPECTION_OVERDUE_EMAIL_LOG] (
 		[ID] [int] IDENTITY (0, 1) NOT NULL ,
		[INSPECTION_ID] [int] NULL,
		[PROJECT_ID] [int] NULL,
 		[SEND_DATE] [datetime] NOT NULL DEFAULT getdate() ,
		[THRESHOLD_LEVEL] [int] NOT NULL
 	) ON [PRIMARY]


 	ALTER TABLE [dbo].[EC_INSPECTION_OVERDUE_EMAIL_LOG] WITH NOCHECK ADD
 	CONSTRAINT [PK_EC_INSPECTION_OVERDUE_EMAIL_LOG] PRIMARY KEY  CLUSTERED
 	(
 		[ID]
 	)  ON [PRIMARY]


	ALTER TABLE [dbo].[EC_INSPECTION_OVERDUE_EMAIL_LOG]
 	ADD CONSTRAINT FK_EC_INSP_OVRD_EM_LG_EC_INSP_ID_INSP
 	FOREIGN KEY (INSPECTION_ID)
 	REFERENCES EC_INSPECTION ([ID]) ON DELETE CASCADE ON UPDATE CASCADE	
 END