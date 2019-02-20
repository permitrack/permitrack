if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[AUDIT_ENV_SOURCE_USAGE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[AUDIT_ENV_SOURCE_USAGE]
GO

CREATE TABLE [dbo].[AUDIT_ENV_SOURCE_USAGE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SOURCE_USAGE_ID] [int] NOT NULL ,
	[VERSION] [bigint] NOT NULL ,
	[ASSET_SOURCE_ID] [int] NOT NULL ,
	[METER_READING] [decimal](18, 6) NULL ,
	[PERIOD_START_TS] [datetime] NULL ,
	[PERIOD_END_TS] [datetime] NULL ,
	[UNIT_OF_MEASURE_CD] [int] NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[AUDIT_REC_TYPE] [varchar] (7) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AUDIT_DATE] [datetime] NOT NULL 
) ON [PRIMARY]
GO
