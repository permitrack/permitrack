if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_INFO_ENHANCEMENTS_INFO_VERSION]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[INFO_ENHANCEMENTS] DROP CONSTRAINT FK_INFO_ENHANCEMENTS_INFO_VERSION
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[INFO_ENHANCEMENTS]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[INFO_ENHANCEMENTS]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[INFO_EVENTS]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[INFO_EVENTS]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[INFO_TIPS]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[INFO_TIPS]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[INFO_VERSION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[INFO_VERSION]
GO

CREATE TABLE [dbo].[INFO_ENHANCEMENTS] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[INFO_VERSION_ID] [int] NOT NULL ,
	[MESSAGE] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NULL ,
	[UPDATE_TS] [datetime] NULL ,
	[UPDATE_USER_ID] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[INFO_EVENTS] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[EVENT_DATE] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[TITLE] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[LINK] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[SUBTITLE] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[LOCATION] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NULL ,
	[UPDATE_TS] [datetime] NULL ,
	[UPDATE_USER_ID] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[INFO_TIPS] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[MESSAGE] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NULL ,
	[UPDATE_TS] [datetime] NULL ,
	[UPDATE_USER_ID] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[INFO_VERSION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[VERSION_ID] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[RELEASE_DATE] [varchar] (20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NULL ,
	[UPDATE_TS] [datetime] NULL ,
	[UPDATE_USER_ID] [int] NULL 
) ON [PRIMARY]
GO

