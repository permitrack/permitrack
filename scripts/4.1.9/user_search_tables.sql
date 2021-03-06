if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_USER_SEARCH]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_USER_SEARCH]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_SEARCH]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_SEARCH]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_USER_DEFAULT_SEARCH]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_USER_DEFAULT_SEARCH]
GO

CREATE TABLE [dbo].[EC_USER_SEARCH] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[USER_ID] [int] NOT NULL ,
	[EC_SEARCH_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[EC_SEARCH] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[NAME] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[PROJECT_NAME] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ADDRESS] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[CITY] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[STATE] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ZIP] [varchar] (12) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ZONES] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[TYPES] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[STATUSES] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PERMIT_NUM] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[AREA_MIN] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[AREA_MAX] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[TOTAL_AREA_MIN] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[TOTAL_AREA_MAX] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[IMP_AREA_MIN] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[IMP_AREA_MAX] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[START_DATE_A] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[START_DATE_B] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[EFF_DATE_A] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[EFF_DATE_B] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[SEED_DATE_A] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[SEED_DATE_B] [varchar] (30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PERMIT_AUTH_NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PERMITEE_NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[INSPECTOR_NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[EC_USER_DEFAULT_SEARCH] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[USER_ID] [int] NOT NULL ,
	[DEFAULT_SEARCH_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

