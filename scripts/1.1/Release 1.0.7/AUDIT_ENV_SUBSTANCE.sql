if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[AUDIT_ENV_SUBSTANCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[AUDIT_ENV_SUBSTANCE]
GO

CREATE TABLE [dbo].[AUDIT_ENV_SUBSTANCE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SUBSTANCE_ID] [int] NOT NULL ,
	[VERSION] [bigint] NOT NULL ,
	[NAME] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PART_NUM] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[SUBSTANCE_TYPE_CD] [int] NOT NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[AUDIT_REC_TYPE] [varchar] (7) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AUDIT_DATE] [datetime] NOT NULL 
) ON [PRIMARY]
GO
