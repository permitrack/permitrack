if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[AUDIT_ENV_SOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[AUDIT_ENV_SOURCE]
GO

CREATE TABLE [dbo].[AUDIT_ENV_SOURCE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SOURCE_ID] [int] NOT NULL ,
	[VERSION] [bigint] NOT NULL ,
	[DISPLAY_NAME] [varchar] (150) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[PART_NUMBER] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[BATCH_NUMBER] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[LBS_VOC] [decimal](18, 6) NULL ,
	[LBS_VOC_UM] [int] NULL ,
	[DENSITY] [decimal](18, 6) NULL ,
	[DENSITY_UM] [int] NULL ,
	[LBS_HAPS] [decimal](18, 6) NULL ,
	[LBS_HAPS_UM] [int] NULL ,
	[PCT_SOLIDS_VOLUME] [decimal](18, 6) NULL ,
	[PCT_SOLIDS_WEIGHT] [decimal](18, 6) NULL ,
	[INFO_ORIGIN] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[SOURCE_TYPE_CD] [int] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[DISPLAY_COLOR_CD] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[HC_FUEL] [int] NULL ,
	[AUDIT_REC_TYPE] [varchar] (7) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AUDIT_DATE] [datetime] NOT NULL 
) ON [PRIMARY]
GO
