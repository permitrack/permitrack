if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[AUDIT_ENV_ASSET]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[AUDIT_ENV_ASSET]
GO

CREATE TABLE [dbo].[AUDIT_ENV_ASSET] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ASSET_ID] [int] NOT NULL ,
	[VERSION] [bigint] NOT NULL ,
	[PARENT_ASSET_ID] [int] NULL ,
	[NAME] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[NUMBER] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[ACTIVE_TS] [datetime] NULL ,
	[INACTIVE_TS] [datetime] NULL ,
	[LOCATION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PERMIT_SHIELD] [bit] NULL ,
	[POINT] [bit] NULL ,
	[BUILDING_HEAT] [bit] NULL ,
	[PROCESS] [bit] NULL ,
	[METER] [bit] NULL ,
	[BELONGS_TO_METER] [int] NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[AUDIT_REC_TYPE] [varchar] (7) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AUDIT_DATE] [datetime] NOT NULL 
) ON [PRIMARY]
GO
